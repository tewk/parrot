#! perl
# Copyright (C) 2008, The Perl Foundation.
# $Id$

=head1 NAME

t/php/info.t - Standard Library info

=head1 SYNOPSIS

    % perl -I../lib plumhead/t/php/info.t

=head1 DESCRIPTION

Tests PHP Standard Library info
(implemented in F<languages/plumhead/src/common/php_info.pir>).

See L<http://www.php.net/manual/en/ref.?.php>.

=cut

use strict;
use warnings;

use FindBin;
use lib "$FindBin::Bin/../../lib";

use Test::More     tests => 8;
use Parrot::Test;

language_output_is( 'Plumhead', <<'CODE', <<'OUTPUT', 'php_egg_logo_guid()', todo => 'awaiting compiler updates' );
<?php
  echo php_egg_logo_guid(), "\n";
?>
CODE
PHPE9568F36-D428-11d2-A769-00AA001ACF42
OUTPUT

language_output_like( 'Plumhead', <<'CODE', <<'OUTPUT', 'php_logo_guid()', todo => 'awaiting compiler updates' );
<?php
  echo php_logo_guid(), "\n";
?>
CODE
/PHPE9568F3[46]-D428-11d2-A769-00AA001ACF42/
OUTPUT

language_output_is( 'Plumhead', <<'CODE', <<'OUTPUT', 'php_real_logo_guid()', todo => 'awaiting compiler updates' );
<?php
  echo php_real_logo_guid(), "\n";
?>
CODE
PHPE9568F34-D428-11d2-A769-00AA001ACF42
OUTPUT

language_output_like( 'Plumhead', <<'CODE', <<'OUTPUT', 'php_uname()' );
<?php
  echo php_uname(), "\n";
?>
CODE
/.+/
OUTPUT

language_output_like( 'Plumhead', <<'CODE', <<'OUTPUT', 'php_uname("a")' );
<?php
  echo php_uname('a'), "\n";
?>
CODE
/.+/
OUTPUT

language_output_like( 'Plumhead', <<'CODE', <<'OUTPUT', 'phpversion()', todo => 'awaiting compiler updates' );
<?php
  echo phpversion(), "\n";
?>
CODE
/^5\.2/
OUTPUT

language_output_is( 'Plumhead', <<'CODE', <<'OUTPUT', 'phpversion("ctype")', todo => 'awaiting compiler updates' );
<?php
  echo phpversion('ctype'), "\n";
?>
CODE

OUTPUT

language_output_is( 'Plumhead', <<'CODE', <<'OUTPUT', 'zend_logo_guid()', todo => 'awaiting compiler updates' );
<?php
  echo zend_logo_guid(), "\n";
?>
CODE
PHPE9568F35-D428-11d2-A769-00AA001ACF42
OUTPUT

# Local Variables:
#   mode: cperl
#   cperl-indent-level: 4
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
