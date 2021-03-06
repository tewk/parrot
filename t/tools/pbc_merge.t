#! perl
# Copyright (C) 2005-2007, Parrot Foundation.
# $Id$

=head1 NAME

t/tools/pbc_merge.t - test the PBC merge tool

=head1 SYNOPSIS

    % prove t/tools/pbc_merge.t

=head1 DESCRIPTION

Tests the C<pbc_merge> utility by providing it with a number of source
bytecode files, having it merge them and then checking the output is what
would be expected.

=cut

use strict;
use warnings;
use lib qw( . lib ../lib ../../lib );
use Test::More;
use Parrot::Test;
use Parrot::Config;

my $PARROT    = ".$PConfig{slash}$PConfig{test_prog}";
my $PBC_MERGE = ".$PConfig{slash}pbc_merge$PConfig{exe}";

# Only test if we have the PBC merge tool built.
if ( -e $PBC_MERGE ) {
    plan tests => 5;
}
else {
    plan skip_all => "PBC Merge tool not built or test disabled";
}

sub pir_to_pbc {
    my ( $name, $pir ) = @_;

    open my $FILE, '>', "t$PConfig{slash}tools$PConfig{slash}$name.pir";
    print $FILE $pir;
    close $FILE;
    system(
"$PARROT -o t$PConfig{slash}tools$PConfig{slash}$name.pbc t$PConfig{slash}tools$PConfig{slash}$name.pir"
    );
}

sub pbc_merge {
    my $outname = "t$PConfig{slash}tools$PConfig{slash}" . shift() . ".pbc";
    my $inputs = join( ' ', map { "t$PConfig{slash}tools$PConfig{slash}$_.pbc" } @_ );
    system("$PBC_MERGE -o $outname $inputs");
}

sub run_pbc {
    return `$PARROT t$PConfig{slash}tools$PConfig{slash}$_[0].pbc`;
}

# First test - check sub relocation works.
{
    pir_to_pbc( "pbc_merge_t1_1", <<'PIR' );
.sub _main :main
    _testcall()
.end
PIR

    pir_to_pbc( "pbc_merge_t1_2", <<'PIR' );
.sub _testcall
    print 42
.end
PIR
    pbc_merge( "pbc_merge_t1", "pbc_merge_t1_1", "pbc_merge_t1_2" );
    is( run_pbc("pbc_merge_t1"), "42" );
}

# Second test - check constant table pointers in bytecode are fixed up.
{
    pir_to_pbc( "pbc_merge_t2_1", <<'PIR' );
.sub _main :main
    .local num years
    .local string rockers

    years = _get_years()
    rockers = _band()

    print rockers
    print " have rocked for over "
    print years
    print " years!"
.end
PIR

    pir_to_pbc( "pbc_merge_t2_2", <<'PIR' );
.sub _band
    .local string s
    s = "Rammstein"
    .return(s)
.end

.sub _get_years
    .local num n
    n = 10.398571
    .return(n)
.end
PIR
    pbc_merge( "pbc_merge_t2", "pbc_merge_t2_1", "pbc_merge_t2_2" );
    is( run_pbc("pbc_merge_t2"), "Rammstein have rocked for over 10.398571 years!" );
}

# Third test - sub calls back and forth between blocks.
{
    pir_to_pbc( "pbc_merge_t3_1", <<'PIR' );
.sub main :main
    .local string s
    s = test1()
    print s
.end

.sub test2
    .local string s
    s = "Stirb nicht vor mir"
    .return(s)
.end
PIR

    pir_to_pbc( "pbc_merge_t3_2", <<'PIR' );
.sub test1
    .local string s
    s = test2()
    .return(s)
.end
PIR
    pbc_merge( "pbc_merge_t3", "pbc_merge_t3_1", "pbc_merge_t3_2" );
    is( run_pbc("pbc_merge_t3"), "Stirb nicht vor mir" );
}

# Fourth test - passing constant string arguments.
{
    pir_to_pbc( "pbc_merge_t4_1", <<'PIR' );
.sub main :main
    elephant()
.end
PIR

    pir_to_pbc( "pbc_merge_t4_2", <<'PIR' );
.sub elephant
    trunk_action("spray")
.end
.sub trunk_action
    .param string s
    print s
.end
PIR
    pbc_merge( "pbc_merge_t4", "pbc_merge_t4_1", "pbc_merge_t4_2" );
    is( run_pbc("pbc_merge_t4"), "spray" );
}

# Fifth test - passing constant-string-named arguments
{
    pir_to_pbc( "pbc_merge_t5_1", <<'PIR' );
.sub main :main
    t5_other_sub()
.end

.sub t5_say_arg
    .param pmc args :named :slurpy

    $S0 = args['t5_named_arg']
    if null $S0 goto no_arg
    print $S0
    goto end

no_arg:
    print "got no named arg"

end:
.end
PIR

    pir_to_pbc( "pbc_merge_t5_2", <<'PIR' );
.sub t5_other_sub
    t5_say_arg('success' :named("t5_named_arg"))
.end
PIR

    pbc_merge( "pbc_merge_t5", "pbc_merge_t5_1", "pbc_merge_t5_2" );
    is( run_pbc( "pbc_merge_t5" ), "success");
}
# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
