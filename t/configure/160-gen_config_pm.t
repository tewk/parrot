#! perl
# Copyright (C) 2007, The Perl Foundation.
# $Id$
# 160-gen_config_pm.t

use strict;
use warnings;
use Test::More tests =>  2;
use Carp;
use lib qw( lib );
use_ok('config::gen::config_pm');

=for hints_for_testing The grand finale!  Try to maximize branch
coverage.

=cut

pass("Completed all tests in $0");

################### DOCUMENTATION ###################

=head1 NAME

160-gen_config_pm.t - test config::gen::config_pm

=head1 SYNOPSIS

    % prove t/configure/160-gen_config_pm.t

=head1 DESCRIPTION

The files in this directory test functionality used by F<Configure.pl>.

The tests in this file test subroutines exported by config::gen::config_pm.

=head1 AUTHOR

James E Keenan

=head1 SEE ALSO

config::gen::config_pm, F<Configure.pl>.

=cut

# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
