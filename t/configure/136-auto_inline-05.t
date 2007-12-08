#! perl
# Copyright (C) 2007, The Perl Foundation.
# $Id$
# 136-auto_inline-05.t

use strict;
use warnings;
use Test::More tests => 19;
use Carp;
use lib qw( lib t/configure/testlib );
use_ok('config::init::defaults');
use_ok('config::auto::inline');
use Parrot::Configure;
use Parrot::Configure::Options qw( process_options );
use Parrot::Configure::Test qw( test_step_thru_runstep);
use Parrot::IO::Capture::Mini;

my $args = process_options(
    {
        argv => [ q{--verbose} ],
        mode => q{configure},
    }
);

my $conf = Parrot::Configure->new;

test_step_thru_runstep( $conf, q{init::defaults}, $args );

my $pkg = q{auto::inline};

$conf->add_steps($pkg);
$conf->options->set( %{$args} );

my ( $task, $step_name, $step);
$task        = $conf->steps->[1];
$step_name   = $task->step;

$step = $step_name->new();
ok( defined $step, "$step_name constructor returned defined value" );
isa_ok( $step, $step_name );
ok( $step->description(), "$step_name has description" );

{
    my $tie_out = tie *STDOUT, "Parrot::IO::Capture::Mini"
        or croak "Unable to tie";
    my $test = 1;
    ok($step->_evaluate_inline($conf, $test),
        "_evaluate_inline() returned true value");
    my @more_lines = $tie_out->READLINE;
    ok( @more_lines, "verbose output captured" );
    is($step->result, q{yes}, "Got expected result");;
    is($conf->data->get( 'inline' ), 1,
        "'inline' attribute has expected value");
}
untie *STDOUT;

{
    my $tie_out = tie *STDOUT, "Parrot::IO::Capture::Mini"
        or croak "Unable to tie";
    my $test = 0;
    ok($step->_evaluate_inline($conf, $test),
        "_evaluate_inline() returned true value");
    my @more_lines = $tie_out->READLINE;
    ok( @more_lines, "verbose output captured" );
    is($step->result, q{no}, "Got expected result");;
    is($conf->data->get( 'inline' ), q{},
        "'inline' attribute has expected value");
}
untie *STDOUT;

pass("Keep Devel::Cover happy");
pass("Completed all tests in $0");

################### DOCUMENTATION ###################

=head1 NAME

136-auto_inline-05.t - test config::auto::inline

=head1 SYNOPSIS

    % prove t/configure/136-auto_inline-05.t

=head1 DESCRIPTION

The files in this directory test functionality used by F<Configure.pl>.

The tests in this file test subroutines exported by config::auto::inline.

=head1 AUTHOR

James E Keenan

=head1 SEE ALSO

config::auto::inline, F<Configure.pl>.

=cut

# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
