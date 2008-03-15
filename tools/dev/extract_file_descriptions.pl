#! perl
# Copyright (C) 2001-2008, The Perl Foundation.
# $Id$

=head1 NAME

tools/dev/extract_file_descriptions.pl - Extract descriptions from files

=head1 SYNOPSIS

    % perl tools/dev/extract_file_descriptions.pl [--plus-misses] [files ...]

=head1 DESCRIPTION

This script extracts descriptions from the Parrot source files. If no
files are specified on the command-line then the files worth describing
will be found recursively from the current directory.

=head2 Options

=over 4

=item C<--plus-misses>

This creates a second section, with the heads of any files which had
familiar types, but from which descriptions were not obtained.

=back

=head1 TODO  (RT#46903)

=over

=item * Given a directory argument, should recursively descend.

=item * Should create descriptive hashes earlier, before files are
filtered. So one can skip, say a binary file, but still have it listed
in the output. For instance, it is nice to see where the .pbc's land.

=item * Allow indescribable heads to be mixed in with rest, for when
exploring a location is more important than big-picture browsing.

=item * Is absense of "[...]"s in the indescribable listing a ui
consistency violation?

=item * Fragment describe_file() - it shouldn't both `cat` and dispatch
on file suffix.

=item * Finish making this usable as a library.

=item * It would be nice to have a ParrotSourceFile class of course. ;)
(a jest - sort of.)

=item * Misc: rationalize indent; clarify desc emptiness contract;
review readability of intra-comment blank line elimination; pod
handling;

=back

=cut

use strict;
use warnings;
use File::Find;
use Getopt::Long;

# borrowed from Regexp::Common 2.120
my $c_re   = '(?:(?:\/\*)(?:(?:[^\*]+|\*(?!\/))*)(?:\*\/))';
my $cpp_re = '(?:(?:(?://)(?:[^\n]*)(?:\n))|(?:(?:\/\*)(?:(?:[^\*]+|\*(?!\/))*)(?:\*\/)))';

if (1)
{
    my $show_full = 0;
    GetOptions( 'plus-misses' => \$show_full )
        || die( "Usage: $0 [--plus-misses] [files...]\n\n"
            . "FILES defaults to a recursive \" find . \".\n\n"
            . "--plus-misses creates a second section, with the heads of any\n"
            . "files which had familiar types, but from which descriptions\n"
            . "were not obtained.\n\n" );

    my @files = @ARGV ? @ARGV : files_worth_describing();

    print "This file was generated by $0\non " . scalar(localtime) . ".\n\n";
    print "Files in ( parentheses ) did not have extractable descriptions.\n";
    if ($show_full)
    {
        print "Their heads are included in a second section below, "
            . "marked with \"#=#=#=\".\n";
    }
    else
    {
        print
            "Run this script with --plus-misses, and a second section will be "
            . "included,\nwith the heads of these indescribable files.\n";
    }
    print "\n";
    my @no_descriptions;
    foreach (@files)
    {
        my $info = describe_file($_);
        if ( $info->{desc} )
        {
            print "\n* $info->{path}\n\n$info->{desc}\n";
        }
        else
        {
            print "( $info->{path} )\n";
            push( @no_descriptions, $info );
        }
    }
    print "\n\n";
    if ($show_full)
    {
        print "\n"
            . ( "#=" x 35 )
            . "\n\nFiles from which descriptions were not obtained:\n\n";
        foreach (@no_descriptions)
        {
            my $top = $_->{top};
            $top =~ s/^/>  /mg;
            print "- $_->{path}\n\n$top\n";
        }
    }
    exit(0);
}

sub describe_file
{
    my ($path) = @_;

    my $info = {};
    $info->{path} = $path;
    my $text = `cat $info->{path}`;
    my ($top) = $text =~ /^(([^\n]*\n){1,15})/;
    $info->{top} = $top;
    return describe_c_file( $info, $text )
        if $path =~ /\.([chlyC]|cpp|cola|xs|pmc)$|[_\.][ch]\.in$/;
    return describe_perl_file( $info, $text )
        if $path =~ /\.(pl|pm|t|PL|pod|pasm)$|_pm\.in$/
        or $text =~ /^\#/;
    return describe_misc_file( $info, $text );
}

sub describe_misc_file
{
    my ( $info, $text ) = @_;

    my $top = $text;
    clip_excess_lines( $top, 15 );
    $top =~ s/^ {0,1}(\S)/  $1/mg;    # minimum indent
    $info->{desc} = $top;
    return $info;
}

sub describe_perl_file
{
    my ( $info, $text ) = @_;

    my $desc;
    if ( $text =~ /^\#/ )
    {                                 # perl file (or sh)
        my ($comment) = $text =~ /^(\#[^\n]*\n( *\n)?(\#[^\n]*\n)*)/s;
        die "internal bug" unless $comment;
        local $_ = $comment;
        s/^\#\* /\# /mg;                            # #*
        s/^\#(\#|\*)+\s*$/\#/mg;                    # line of "*"s or "#"s
        s/^\#\!.+//m;                               # #!
        s/^\# *[a-z0-9]+\.(p[ml]|pasm)\s*\n//mi;    # own file name - kludgy
        s/\#\s*Copyright[^\n]+\n(\# *\S[^\n]*\n)*/\#\n/s;
        s/^\#\s*(\$I[d]: .+)\n//m;
        $info->{Id} = $1;
        s/^\#\s*Author:.+//m;

        s/^\s*\n//mg;    # truly blank lines, between the # comment lines
        s/\n(\# *\n){2,}/\n\#\n/sg;    # crush down double blank lines
        s/^\s*(\# *\n)+//s;            # remove leading
        s/\n(\# *\n)+\s*$/\n/s;        # remove trailing
        s/^\#//mg;                     # get rid of #
                                       #s/^( *\n)+//s;
        s/^\s*$//s;                    # normalize emptiness
        $comment = $_;
        $desc = $info->{perl_comment} = $comment;
    }
    if ( !$desc && $text =~ /^=head1/m )
    {                                  # try an embedded pod
        my ($doc) = $text =~ /(?:^|\n)=head1(.+)/s;
        die "internal bug" unless $doc;
        local $_ = $doc;
        s/^\s*(NAME|TITLE)\s*//;
        s/\n=.*//s;

        # It would be nice to get the beginning of any DESCRIPTION. RT#46903
        $doc = $_;
        $desc = $info->{pod_doc} = $doc;
    }
    do
    {
        $desc =~ s/^ {0,1}(\S)/  $1/mg;    # minimum indent
        clip_excess_lines($desc);
    } if $desc;
    $info->{desc} = $desc;
    return $info;
}

sub describe_c_file
{
    my ( $info, $text ) = @_;

    my $comment_is_at_beginning = $text =~ /^\/\*/;
    my ($first_comment) = $text =~ /($c_re)/;

    ($first_comment)    = $text =~ /($cpp_re\s*)+)/
        unless $first_comment;

    return $info unless $first_comment;

    local $_ = $first_comment;

    s/^\/\*//;
    s/\*\/$//;          # /*  */
    s/^ *\/\///mg;      # //
    s/^ ?\*\*//mg;      # |**
    s/^ {0,2}\*//mg;    # | *

    s/^ *(\$I[d]: .+)\n//m;
    $info->{Id} = $1;

    my $desc;
    if (/Overview:/)
    {                   # normal parrot code files
        my $label = qr/ *[A-Z][a-zA-Z ]+:/;
        ($desc) = /(?:^|\n) *Overview: *\n(((?!$label) *[^\n]+\n)+)/s;
        $info->{warning} .= "There was an Overview:, but it wasn't used.";
    }
    if ( $info->{path} =~ /\Wicu\Wsource/ )
    {
        s/Copyright \(C\) [^\n]+\n *Corporation [^\n]+\n//;
        s/^ *(file name|encoding|tab size|indentation|created (on|by)):.*//mg;
        $desc = $_;
    }
    if ( !$desc )
    {
        s/^ *[a-z0-9_]+\.[chly]\s*\n//mi;    # own filename - kludgy.
        $desc = $_;
        $desc = ""    # it's only emacs variables at the end of the file
            if ( !$comment_is_at_beginning && /c-indentation-style/ );
    }

    $_ = $desc;
    s/^ *\*+\s*$//mg;          # line of "*"s.
    s/\n( *\n){2,}/\n\n/sg;    # excess blank lines
    s/^\s*//s;
    s/\s*$/\n/s;               # trim (and ends with a newline)
    s/^ {0,1}(\S)/  $1/mg;     # minimum indent
    s/^\s*$//s;                # normalize emptiness
    clip_excess_lines($_);
    $info->{desc} = $_;
    return $info;
}

sub files_worth_describing
{
    my @files;
    find(
        sub {
            my $name = $File::Find::name;
            return if $name =~ /\.svn|core|\.[oa]|\.(so|brk|dsp|tmp)$/;
            return if $name =~ /locales\W[a-z_]+\.txt$/i;
            return
                if $name =~ /icu\Wsource/;   # icu cleanup code above needs work
            return if -d $_;
            return if -B $_;
            push( @files, $name );
        },
        "."
    );
    return @files;
}

sub clip_excess_lines
{
    my $cnt = defined $_[1] ? $_[1] : 20;
    $_[0]        =~ s/(([^\n]*\n){0,$cnt}).*/$1   [...]\n/s
        if $_[0] =~ tr/\n/\n/ > $cnt;
    return;
}

# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
