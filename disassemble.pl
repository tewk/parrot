#! /usr/bin/perl -w
#
# disassemble.pl
#
# Turn a parrot bytecode file into text.
#
# Copyright (C) 2001-2002 The Parrot Team. All rights reserved.
# This program is free software. It is subject to the same license
# as the Parrot interpreter.
#
# $Id$
#

use strict;
use FindBin;
use lib "$FindBin::Bin/lib";
use Parrot::Config;

use Parrot::OpLib::core;
use Parrot::Op;

use Parrot::Types;
use Parrot::PackFile;
use Parrot::PackFile::ConstTable;
use Parrot::String;
use Parrot::Key;

use Data::Dumper;
$Data::Dumper::Useqq  = 1;
$Data::Dumper::Terse  = 1;
$Data::Dumper::Indent = 0;

use Getopt::Std;

my %opts;

getopts('x', \%opts);

#
# GLOBAL VARIABLES:
#

my @opcodes = @$Parrot::OpLib::core::ops;

#my $opcode_fingerprint = Parrot::Opcode::fingerprint();


#
# dump_const_table()
#

sub dump_const_table {
    my ($pf) = @_;

    #
    # Check for the opcode table fingerprint:
    #
    # TODO: This is a really poor way to do this. Consider having a 'properties' table
    # as part of the file format. Then we can have whatever properties we want. Probably
    # these can be pairs of IVs as indexes into the constants table. Then we can have
    # a 'fingerprint' property if we want.
    #

    my $count = $pf->const_table->const_count;

=no
    if ($count < 1) {
	warn "Disassembling without opcode table fingerprint (no constants)!";
	return;
    }

    my $type = $pf->const_table->constant(0)->type;
    if ($type ne Parrot::PackFile::Constant->type_code('PFC_STRING')) {
        $type = Parrot::PackFile::Constant->type_name($type);
        warn "Disassembling without opcode table fingerprint (first constant isn't a string; type = '$type')!";
    }

    my $ref = ref $pf->const_table->constant(0)->value;
    if ($ref ne 'Parrot::String') {
        warn "Cannot disassemble (malformed string as first constant; type = '$ref'!";
    }

    my $data = ref $pf->const_table->constant(0)->value->data;
    if ($data ne $opcode_fingerprint) {
        warn "Cannot disassemble (differing opcode table; data = '$data')!";
    }
=cut

    #
    # Dump the constant table:
    #

    print "#\n";
    print "# Constant  Type          Data\n";
    print "# --------  ------------  ------------------------------\n";

    my $constant_num = 0;

    foreach ($pf->const_table->constants) {
      my $value;
      my $type  = Parrot::PackFile::Constant->type_name($_->type + 0);

      if ($type eq 'PFC_STRING') {
        $value = Dumper($_->value->data);
      } elsif ($type eq 'PFC_KEY') {
        $value = $_->value->dump($pf->const_table);
      } else {
        $value = Dumper($_->value);
      }

      printf("# %8ld  %-12s  %-30s\n", $constant_num, $type, $value);

      $constant_num++;
    }

    print "#\n";
}


#
# disassemble_byte_code()
#

my %rtype_map = (
  "i" => "I",
  "n" => "N",
  "p" => "P",
  "s" => "S",
  "k" => "K",
  "ki" => "KI",

  "ic" => "i",
  "nc" => "n",
  "pc" => "p",
  "sc" => "s",
  "kc" => "k",
  "kic" => "ki",
);

sub disassemble_byte_code {
    my ($pf) = @_;

    my $cursor = 0;

    my $offset=0;
    my $bytecode = defined $pf->byte_code ? $pf->byte_code : '';
    my $length = length($bytecode);

    my $label_counter = 0;

    my %pasm;

    #
    # Scan the byte code, storing the disasembled ops and linking
    # branch destinations to autogenerated labels.
    #

    while ($offset < $length) {
	my $op_start = $offset;
	my $op_code = shift_op($bytecode);
	$offset += sizeof("op");

        if (!defined $op_code) {
            warn "$0: Unable to get next opcode at offset $op_start!\n";
            last;
        }

        if (!defined $opcodes[$op_code]) {
            warn "$0: Unrecognized opcode '$op_code' at offset $op_start!\n";
            next;
        }

        if (exists $pasm{$op_start}) {
            $pasm{$op_start} = [ $pasm{$op_start}[0], [ $op_code ], $opcodes[$op_code]{NAME}, [ ] ];
        } else {
            $pasm{$op_start} = [ undef,               [ $op_code ], $opcodes[$op_code]{NAME}, [ ] ];
        }

	my $arg_count = $opcodes[$op_code]->size - 1;
	my @args = ();

	foreach (1 .. $arg_count) {
	    my $type        = $rtype_map{$opcodes[$op_code]->arg_type($_)};
	    my $unpack_size = sizeof($type);

	    if (($offset + $unpack_size) > $length) {
	        warn "$0: Premature end of bytecode in argument.\n";
	        last;
            }

	    my $arg = shift_arg($type, $bytecode);

            push @{$pasm{$op_start}[1]}, $arg;

	    $offset += $unpack_size;

	    if($type =~ m/^[INPS]$/) { # Register
		push @{$pasm{$op_start}[3]}, $type . $arg;
	    } elsif($type eq "D") { # destination address
                my $dest = $op_start + sizeof('op') * $arg;
                if (!exists $pasm{$dest}) {
                    $pasm{$dest}    = [ "L" . $label_counter++, [ ], undef, [ ] ];
                } elsif (!defined $pasm{$dest}[0]) {
                    $pasm{$dest}[0] = "L" . $label_counter++;
                }
		push @{$pasm{$op_start}[3]}, $pasm{$dest}[0];
	    } elsif($type eq "K") { # key
		push @{$pasm{$op_start}[3]}, sprintf("[P$arg]");
	    } elsif($type eq "KI") { # integer key
		push @{$pasm{$op_start}[3]}, sprintf("[I$arg]");
	    } elsif($type eq "n") { # number constant
		push @{$pasm{$op_start}[3]}, sprintf("[nc:$arg]");
	    } elsif($type eq "s") { # string constant
		push @{$pasm{$op_start}[3]}, sprintf("[sc:$arg]");
	    } elsif($type eq "k") { # key constant
		push @{$pasm{$op_start}[3]}, sprintf("[kc:$arg]");
	    } elsif($type eq "ki") { # integer key constant
		push @{$pasm{$op_start}[3]}, sprintf("[$arg]");
	    } else { # constant
		push @{$pasm{$op_start}[3]}, $arg;
	    }
	}
    }

    #
    # Print out the code:
    #

    printf "# WORD     BYTE         BYTE CODE                                         LABEL   OPERATION        ARGUMENTS\n";
    printf "# -------- ----------   ------------------------------------------------  ------  ---------------  --------------------\n";

    foreach my $pc (sort { $a <=> $b } keys %pasm) {
        my ($label, $code, $op_name, $args) = @{$pasm{$pc}};
        $label = defined $label ? "$label:" : '';
        my $words;

        if ($opts{x}) {
          $words = join('  ', map { sprintf "%08x", $_ } @$code);
        } else {
          $words = join('  ', map { sprintf "%08d", $_ } @$code);
        }

        my @print_args = ($pc / 4, $pc, $words, $label, $op_name);

        if ($opts{x}) {
	  printf "  %08x [%08x]:  %-48s  %-6s  %-15s  ", @print_args;
        } else {
	  printf "  %08d [%08d]:  %-48s  %-6s  %-15s  ", @print_args;
        }

	print join(", ", @$args), "\n";
    }
}


#
# disassemble_file()
#

sub disassemble_file {
    my ($file_name) = @_;

    my $pf = Parrot::PackFile->new;
    $pf->unpack_file($file_name);

    printf "#\n";
    printf "# Disassembly of Parrot Byte Code from '%s'\n", $_;
    printf "#\n";
    printf "# Segments:\n";
    printf "#\n";
    printf "#   * Wordsize:     %8d bytes (%d)\n", sizeof('byte'), $pf->wordsize;
    printf "#   * Byteorder:    %8d bytes (%d)\n", sizeof('byte'), $pf->byteorder;
    printf "#   * Major:        %8d bytes (%d)\n", sizeof('byte'), $pf->major;
    printf "#   * Minor:        %8d bytes (%d)\n", sizeof('byte'), $pf->minor;
    printf "#   * Flags:        %8d bytes (%d)\n", sizeof('byte'), $pf->flags;
    printf "#   * FloatType:    %8d bytes (%d)\n", sizeof('byte'), $pf->floattype;
    printf "#   * Fingerprint:  %8d bytes (". "0x%02x," x9 . "0x%02x)\n", length($pf->pad),
        unpack("C10", $pf->pad);
    printf "#   * Magic Number: %8d bytes (0x%08x)\n", sizeof('op'), $pf->magic;
    printf "#   * Opcode Type:  %8d bytes (0x%08x)\n", sizeof('op'), $pf->opcodetype;
    printf "#   * Fixup Table:  %8d bytes\n", $pf->fixup_table->packed_size;
    printf "#   * Const Table:  %8d bytes\n", $pf->const_table->packed_size;
    printf "#   * Byte Code:    %8d bytes (%d opcode_ts)\n", length($pf->byte_code), length($pf->byte_code) / sizeof('op');

    dump_const_table($pf);
    disassemble_byte_code($pf);

    undef $pf;

    return;
}


#
# MAIN PROGRAM:
#

@ARGV = qw(-) unless @ARGV;

foreach (@ARGV) {
    disassemble_file($_)
}

exit 0;

__END__

=head1 NAME

disassemble.pl - disassemble the byte code from Parrot Pack Files

=head1 SYNOPSIS

  perl disassemble.pl FILE

=head1 DESCRIPTION

Disassembles the Parrot Pack Files listed on the command line, or
from standard input if no file is named.

=head1 COPYRIGHT

Copyright (C) 2001 The Parrot Team. All rights reserved.

=head1 LICENSE

This program is free software. It is subject to the same license
as the Parrot interpreter.

