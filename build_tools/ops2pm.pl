#! /usr/bin/perl -w
#
# ops2pm.pl
#
# Generate a Perl module from the operation definitions in an .ops file.
#

use strict;
use Parrot::OpsFile;

use Data::Dumper;
$Data::Dumper::Useqq  = 1;
#$Data::Dumper::Terse  = 1;
#$Data::Dumper::Indent = 0;


#
# Process command-line argument:
#

if (@ARGV != 1) {
  die "ops2c.pl: usage: perl ops2pm.pl input.ops\n";
}

my $file = $ARGV[0];

my $base = $file;
$base =~ s/\.ops$//;

my $package = "${base}";
my $module  = "Parrot/OpLib/${package}.pm";


#
# Read the input file:
#

my $ops = new Parrot::OpsFile $file;

die "ops2pm.pl: Could not read ops file '$file'!\n" unless $ops;

my $num_ops     = scalar $ops->ops;
my $num_entries = $num_ops + 1; # For trailing NULL


#
# Open the output file:
#

open MODULE, ">$module"
  or die "ops2pm.pl: Could not open module file '$module' for writing: $!!\n";


#
# Print the preamble for the MODULE file:
#

my $preamble = <<END_C;
#! perl -w
#
# !!!!!!!   DO NOT EDIT THIS FILE   !!!!!!!
#
# This file is generated automatically from '$file'.
# Any changes made here will be lost!
#

use strict;

package Parrot::OpLib::$package;

use vars qw(\$ops);

END_C

print MODULE $preamble;
print MODULE Data::Dumper->Dump([[ $ops->ops ]], [ qw($ops) ]);

print MODULE <<END_C;

1;
END_C

exit 0;

