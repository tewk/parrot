#! /usr/bin/perl -w
#
# ops2c.pl
#
# Generate a C header and source file from the operation definitions in
# an .ops file, using a supplied transform.
#
# $Id$
#

use strict;
use Parrot::OpsFile;

sub Usage {
    print STDERR <<_EOF_;
usage: $0 trans input.ops [input2.ops ...]
_EOF_
    exit 1;
}

#
# Process command-line argument:
#

Usage() unless @ARGV >= 2;

my $trans_class = "Parrot::OpTrans::" . shift @ARGV;

eval "require $trans_class";

my $trans = $trans_class->new;

my $prefix  = $trans->prefix;
my $suffix  = $trans->suffix;
my $defines = $trans->defines;
my $opsarraytype = $trans->opsarraytype;

my $file = shift @ARGV;

my $base = $file;
$base =~ s/\.ops$//;

my $incdir  = "include/parrot/oplib";
my $include = "parrot/oplib/${base}_ops${suffix}.h";
my $header  = "include/$include";
my $source  = "${base}_ops${suffix}.c";


#
# Read the input files:
#

my %opsfiles;
my @opsfiles;

foreach my $opsfile ($file, @ARGV) {
  if ($opsfiles{$opsfile}) {
    print STDERR "$0: Ops file '$opsfile' mentioned more than once!\n";
    next;
  }

  $opsfiles{$opsfile} = 1;
  push @opsfiles, $opsfile;

  die "$0: Could not read ops file '$opsfile'!\n" unless -r $opsfile;
}

my $ops = new Parrot::OpsFile @opsfiles;

my $version       = $ops->version;
my $major_version = $ops->major_version;
my $minor_version = $ops->minor_version;
my $patch_version = $ops->patch_version;

my $cur_code = 0;
for(@{$ops->{OPS}}) {
   $_->{CODE}=$cur_code++;
}

my $num_ops     = scalar $ops->ops;
my $num_entries = $num_ops + 1; # For trailing NULL


#
# Open the output files:
#

if (! -d $incdir) {
    mkdir($incdir, 0755) or die "ops2c.pl: Could not mkdir $incdir $!!\n";
}

open HEADER, ">$header"
  or die "ops2c.pl: Could not open header file '$header' for writing: $!!\n";

open SOURCE, ">$source"
  or die "ops2c.pl: Could not open source file '$source' for writing: $!!\n";


#
# Print the preamble for the HEADER and SOURCE files:
#

my $preamble = <<END_C;
/*
** !!!!!!!   DO NOT EDIT THIS FILE   !!!!!!!
**
** This file is generated automatically from '$file' (and possibly other
** .ops files). Any changes made here will be lost!
*/

END_C

print $header;
print HEADER $preamble;
print HEADER <<END_C;
#include "parrot/parrot.h"
#include "parrot/oplib.h"

extern op_lib_t * Parrot_DynOp_${base}${suffix}_${major_version}_${minor_version}_${patch_version}(void);

END_C

print SOURCE $preamble;
print SOURCE <<END_C;
#include "$include"

${defines}

END_C

print SOURCE $ops->preamble($trans);


#
# Iterate over the ops, appending HEADER and SOURCE fragments:
#

my @op_funcs;
my @op_func_table;

my $index = 0;

foreach my $op ($ops->ops) {
    my $func_name  = $op->func_name;
    my $arg_types  = "$opsarraytype *, struct Parrot_Interp *";
    my $prototype  = "$opsarraytype * $func_name ($arg_types)";
    my $args       = "$opsarraytype *cur_opcode, struct Parrot_Interp * interpreter";
    my $definition = "static $opsarraytype *\n$func_name ($args)";
    my $source     = $op->source($trans);

#    print HEADER "$prototype;\n";

    push @op_func_table, sprintf("  %-50s /* %6ld */\n", "$func_name,", $index++);
    push @op_funcs,      "$definition {\n$source}\n\n";
}

print SOURCE <<END_C;

/*
** Op Function Definitions:
*/

END_C

print SOURCE @op_funcs;

#
# Finish the SOURCE file's array initializer:
#

print SOURCE <<END_C;

INTVAL ${base}_numops${suffix} = $num_ops;

/*
** Op Function Table:
*/

static op_func${suffix}_t op_func_table\[$num_entries] = {
END_C

print SOURCE @op_func_table;

print SOURCE <<END_C;
  NULL
};


END_C


#
# Op Info Table:
#

print SOURCE <<END_C;

/*
** Op Info Table:
*/

static op_info_t op_info_table\[$num_entries] = {
END_C

$index = 0;

foreach my $op ($ops->ops) {
    my $type       = sprintf("PARROT_%s_OP", uc $op->type);
    my $name       = $op->name;
    my $full_name  = $op->full_name;
    my $func_name  = $op->func_name;
    my $body       = $op->body;
    my $arg_count  = $op->size;
    my $arg_types  = "{ " . join(", ", map { sprintf("PARROT_ARG_%s", uc $_) } $op->arg_types) . " }";

    print SOURCE <<END_C;
  { /* $index */
    $type,
    "$name",
    "$full_name",
    "$func_name",
    "", /* TODO: Put the body here */
    $arg_count,
    $arg_types
  },
END_C

  $index++;
}

print SOURCE <<END_C;
};

/*
** op lib descriptor:
*/

static op_lib_t op_lib = {
  "$base",
  $major_version,
  $minor_version,
  $patch_version,
  $num_ops,
  op_info_table,
  op_func_table
};

op_lib_t * Parrot_DynOp_${base}${suffix}_${major_version}_${minor_version}_${patch_version}(void) {
  return &op_lib;
}

END_C

exit 0;

