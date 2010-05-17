#!./parrot
# Copyright (C) 2010, Parrot Foundation.
# $Id$

=head1 NAME

t/pmc/select.t - test the Select PMC


=head1 SYNOPSIS

    % prove t/pmc/select.t

=head1 DESCRIPTION

Tests the Select PMC.

=cut

.sub main :main
.include 'test_more.pir'

    plan(1)
    'test_getfd'()

.end


# Select constructor
.sub 'test_getfd'
    $P0 = new ['FileHandle']
    $P0.'open'('README')
    $P1 = new ['Select']
    $I0 = $P1.'getfd'($P0)
    ok($I0, 'new')
.end

# Local Variables:
#   mode: pir
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4 ft=pir:
