#! perl -w

use Parrot::Test tests => 29;
use Test::More;

# Tests for stack operations, currently push*, push_*_c and pop*
# where * != p.

# Assembler code is partially generated by subs at bottom of file

#                 tests for warp, unwarp and set_warp


# This defines two macros:
# fp_eq N, N, LABEL
# fp_ne N, N, LABEL
# which will conditionally branch
# to LABEL if abs(n,n) < epsilon

my $fp_equality_macro = <<'ENDOFMACRO';
fp_eq	macro	J,K,L
	save	N0
	save	N1
	save	N2

	set	N0, J
	set	N1, K
	sub	N2, N1,N0
	abs	N2, N2
	gt	N2, 0.000001, $FPEQNOK

	restore N2
	restore	N1
	restore	N0
	branch	L
$FPEQNOK:
	restore N2
	restore	N1
	restore	N0
endm
fp_ne	macro	J,K,L
	save	N0
	save	N1
	save	N2

	set	N0, J
	set	N1, K
	sub	N2, N1,N0
	abs	N2, N2
	lt	N2, 0.000001, $FPNENOK

	restore	N2
	restore	N1
	restore	N0
	branch	L
$FPNENOK:
	restore	N2
	restore	N1
	restore	N0
endm
ENDOFMACRO

###############     Tests   ##################

output_is( <<"CODE", <<'OUTPUT', "pushi & popi" );
@{[ set_int_regs( sub { $_[0]} )]}
	pushi
@{[ set_int_regs( sub {-$_[0]} )]}
@{[ print_int_regs() ]}
	popi
@{[ print_int_regs() ]}
	end
CODE
0-1-2-3-4
-5-6-7-8-9
-10-11-12-13-14
-15-16-17-18-19
-20-21-22-23-24
-25-26-27-28-29
-30-31
01234
56789
1011121314
1516171819
2021222324
2526272829
3031
OUTPUT


my ($code, $output);
for (0..1024) {
   $code .= "   set I0, $_\n";
   $code .= "   set I31, " . (1024-$_) . "\n";
   $code .= "   pushi\n";
}
for (0..1024) {
   $code .= "   popi\n";
   $code .= "   print I0\n";
   $code .= "   print I31\n";
   $code .= "   print \"\\n\"\n";
   $output .= (1024-$_) . "$_\n";
}
$code .= "      end\n";
output_is($code, $output, "pushi & popi (deep)" );


output_is(<<"CODE", <<'OUTPUT', 'pushs & pops');
@{[ set_str_regs( sub {$_[0]%2} ) ]}
	pushs
@{[ set_str_regs( sub {($_[0]+1) %2} ) ]}
@{[ print_str_regs() ]}
	print "\\n"
	pops
@{[ print_str_regs() ]}
	print "\\n"
	end
CODE
10101010101010101010101010101010
01010101010101010101010101010101
OUTPUT


($code, $output) = ();
for (0..1024) {
   $code .= "   set S0, \"$_\"\n";
   $code .= "   set S31, \"" . (1024-$_) . "\"\n";
   $code .= "   pushs\n";
}
for (0..1024) {
   $code .= "   pops\n";
   $code .= "   print S0\n";
   $code .= "   print S31\n";
   $code .= "   print \"\\n\"\n";
   $output .= (1024-$_) . "$_\n";
}
$code .= "      end\n";
output_is($code, $output, "pushs & pops (deep)" );


output_is(<<"CODE", <<'OUTPUT', 'pushn & popn');
@{[ set_num_regs( sub { "1.0".$_ } ) ]}
	pushn
@{[ set_num_regs( sub { "-1.0".$_} ) ]}
@{[ clt_num_regs() ]}
	print "Seem to have negative Nx\\n"
	popn
@{[ cgt_num_regs() ]}
	print "Seem to have positive Nx after pop\\n"
	branch ALLOK
ERROR:	print "not ok\\n"
ALLOK:	end
CODE
Seem to have negative Nx
Seem to have positive Nx after pop
OUTPUT


output_is(<<"CODE", <<'OUTPUT', 'pushp & popp');
	new	P0, PerlString
	set	P0, "BUTTER IN HELL!\\n"
	pushp
	new	P0, PerlString
	set	P0, "THERE'LL BE NO "
	print	P0
	popp
	print	P0
	end
CODE
THERE'LL BE NO BUTTER IN HELL!
OUTPUT


($code, $output) = ();
for (0..1024) {
   $code .= "   new P0, PerlString\n";
   $code .= "   new P31, PerlString\n";
   $code .= "   set P0, \"$_\"\n";
   $code .= "   set P31, \"" . (1024-$_) . "\"\n";
   $code .= "   pushp\n";
}
for (0..1024) {
   $code .= "   popp\n";
   $code .= "   print P0\n";
   $code .= "   print P31\n";
   $code .= "   print \"\\n\"\n";
   $output .= (1024-$_) . "$_\n";
}
$code .= "      end\n";
output_is($code, $output, "pushp & popp (deep)" );


# Test proper stack chunk handling
output_is(<<CODE, <<'OUTPUT', 'save_i & restore_i');
	set     I3, 1

testloop:
	set     I0, 0
	set     I1, I3

saveloop:
	inc     I0
	save    I0
	ne      I0, I1, saveloop

restoreloop:
	restore I0
	ne      I0, I1, error
	dec	I1
	ne      I1, 0, restoreloop
	
	add     I3, I3, 1
	ne      I3, 769, testloop	# At least 3 stack chunks

	print	"OK\\n"
error:	end
CODE
OK
OUTPUT


# Now, to make it do BAD THINGS!
output_is(<<"CODE",'No more I register frames to pop!','ENO I frames');
	popi
	end
CODE
output_is(<<"CODE",'No more N register frames to pop!','ENO N frames');
	popn
	end
CODE
output_is(<<"CODE",'No more S register frames to pop!','ENO S frames');
	pops
	end
CODE

# Rotate
output_is(<<"CODE", <<'OUTPUT', 'rotate 0');
    set I0, 1
    save I0
    set I0, 2
    save I0
    rotate_up 0
    restore I0 
    print I0
    print "\\n"
    end
CODE
2
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate 1');
    set I0, 1
    save I0
    set I0, 2
    save I0
    rotate_up 1
    restore I0 
    print I0
    print "\\n"
    end
CODE
2
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate 2');
    set I0, 1
    save I0
    set I0, 2
    save I0
    rotate_up 2
    restore I0 
    print I0
    print "\\n"
    end
CODE
1
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate 3');
    set I0, 1
    save I0
    set I0, 2
    save I0
    set I0, 3
    save I0
    rotate_up 3
    restore I0 
    print I0
    restore I0 
    print I0
    restore I0 
    print I0
    print "\\n"
    end
CODE
213
OUTPUT


output_is(<<"CODE", <<'OUTPUT', 'rotate -1');
    set I0, 1
    save I0
    set I0, 2
    save I0
    set I0, 3
    save I0
    rotate_up -1
    restore I0
    print I0
    restore I0
    print I0
    restore I0
    print I0
    print "\\n"
    end
CODE
321
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate -2');
    set I0, 1
    save I0
    set I0, 2
    save I0
    set I0, 3
    save I0
    rotate_up -2
    restore I0
    print I0
    restore I0
    print I0
    restore I0
    print I0
    print "\\n"
    end
CODE
231
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate -3');
    set I0, 1
    save I0
    set I0, 2
    save I0
    set I0, 3
    save I0
    rotate_up -3
    restore I0
    print I0
    restore I0
    print I0
    restore I0
    print I0
    print "\\n"
    end
CODE
132
OUTPUT

output_is(<<'CODE', <<'OUTPUT', 'rotate with a full stack chunk');
      set I0, 0
FOO:  save I0
      inc I0
      lt I0, 256, FOO

      rotate_up 2

      restore I1
      print I1
      print "\n"
      end
CODE
254
OUTPUT

output_is(<<'CODE', <<'OUTPUT', 'rotate across stack chunk boundary');
      set I0, 0
FOO:  save I0
      inc I0
      lt I0, 257, FOO

      rotate_up 2

      restore I1
      print I1
      print "\n"
      end
CODE
255
OUTPUT

output_is(<<'CODE', <<'OUTPUT', 'rotate by stack chunk size');
      set I0, 0
FOO:  save I0
      inc I0
      lt I0, 300, FOO

      rotate_up -256

      restore I1
      print I1
      print "\n"
      end
CODE
44
OUTPUT

output_is(<<'CODE', <<'OUTPUT', 'rotate by more than stack chunk size');
      set I0, 0
FOO:  save I0
      inc I0
      lt I0, 300, FOO

      rotate_up -257

      restore I1
      print I1
      print "\n"
      end
CODE
43
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate up by more than stack size');
    set I0, 1
    save I0
    set I0, 2
    save I0
    rotate_up 3
    end
CODE
Stack too shallow!
OUTPUT

output_is(<<"CODE", <<'OUTPUT', 'rotate down by more than stack size');
    set I0, 1
    save I0
    set I0, 2
    save I0
    rotate_up -3
    end
CODE
Stack too shallow!
OUTPUT

output_is(<<'CODE', <<'OUTPUT', 'save/savec for strings');
      set S0, "Foobar"
      savec S0
      chopn S0, 3
      print S0
      print "\n"
      restore S2
      print S2
      print "\n"

      set S1, "Foobar"
      save  S1
      chopn S1, 3
      print S1
      print "\n"
      restore S3
      print S3
      print "\n"
      end

CODE
Foo
Foobar
Foo
Foo
OUTPUT

output_is(<<CODE, <<OUTPUT, "save, restore");
@{[ $fp_equality_macro ]}
	set	I0, 1
	save	I0
	set	I0, 2
	print	I0
	print	"\\n"
	restore	I0
	print	I0
	print	"\\n"

	set	N0, 1.0
	save	N0
	set	N0, 2.0
	fp_eq	N0, 2.0, EQ1
	print	"not "
EQ1:	print	"equal to 2.0\\n"
	restore	N0
	fp_eq	N0, 1.0, EQ2
	print	"not "
EQ2:	print	"equal to 1.0\\n"

	set	S0, "HONK\\n"
	save	S0
	set	S0, "HONK HONK\\n"
	print	S0
	restore	S0
	print	S0

	save	123
	restore	I0
	print	I0
	print	"\\n"

	save	3.14159
	restore	N0
	fp_eq	N0, 3.14159, EQ3
	print	"<kansas> not "
EQ3:	print	"equal to PI\\n"

	save	"All the world's people\\n"
	restore	S0
	print	S0

	new	P0, PerlString
	set	P0, "never to escape\\n"
	save	P0
	new	P0, PerlString
	set	P0, "find themselves caught in a loop\\n"
	print	P0
	restore	P0
	print	P0

	end
CODE
2
1
equal to 2.0
equal to 1.0
HONK HONK
HONK
123
equal to PI
All the world's people
find themselves caught in a loop
never to escape
OUTPUT

output_is(<<CODE, <<OUTPUT, "entrytype");
	set	I0, 12
	set	N0, 0.1
	set	S0, "Difference Engine #2"
	new	P0, PerlString
	set	P0, "Shalmaneser"

	save	P0
	save	S0
	save	"Wintermute"
	save	N0
	save	1.23
	save	I0
	save	12

	print	"starting\\n"

	set	I1, 0
LOOP:	entrytype	I0, I1
	print	I0
	print	"\\n"
	inc	I1
	lt	I1, 7, LOOP

	print	"done\\n"
	end
CODE
starting
1
1
2
2
3
3
4
done
OUTPUT

SKIP: { skip("Await exceptions", 1);
# this should throw an exception...
output_is(<<CODE, <<OUTPUT, "entrytype, beyond stack depth");
	save	12
	print	"ready\\n"
	entrytype	I0, 1
	print	"done\\n"
CODE
ready
Stack Depth Wrong
OUTPUT
}

output_is(<<'CODE', <<'OUTPUT', "intstack");
	intsave -1
	intsave 0
	intsave 1
	intsave 2
	intsave 3
	set I0, 4
	intsave I0
	
	intrestore I1
	print I1
	
	intrestore I1
	print I1
	
	intrestore I1
	print I1
	
	intrestore I1
	print I1
	
	intrestore I1
	print I1
	
	intrestore I1
	print I1

	print "\n"
CODE
43210-1
OUTPUT

##############################

# set integer registers to some value given by $code...
package main;
sub set_int_regs {
  my $code = shift;
  my $rt;
  for (0..31) {
    $rt .= "\tset I$_, ".&$code($_)."\n";
  }
  return $rt;
}
# print all integer registers, with newlines every five registers
sub print_int_regs {
  my ($rt, $foo);
  for (0..31) {
    $rt .= "\tprint I$_\n";
    $rt .= "\tprint \"\\n\"\n" unless ++$foo % 5;
  }
  $rt .= "\tprint \"\\n\"\n";
  return $rt;
}

# Set all string registers to values given by &$_[0](reg num)
sub set_str_regs {
  my $code = shift;
  my $rt;
  for (0..31) {
    $rt .= "\tset S$_, \"".&$code($_)."\"\n";
  }
  return $rt;
}
# print string registers, no additional prints
sub print_str_regs {
  my $rt;
  for (0..31) {
    $rt .= "\tprint S$_\n";
  }
  return $rt;
}

# Set "float" registers, &$_[0](reg num) should return string
sub set_num_regs {
  my $code = shift;
  my $rt;
  for (0..31) {
    $rt .= "\tset N$_, ".&$code($_[0])."\n";
  }
  return $rt;
}
# rather than printing all num regs, compare all ge 0
# if any are less, jump to ERROR
# sense of test may seem backwards, but isn't
sub cgt_num_regs {
  my $rt;
  for (0..31) {
    $rt .= "\tlt_n_nc_ic N$_, 0.0, ERROR\n";
  }
  return $rt;
}
# same, but this time lt 0
sub clt_num_regs {
  my $rt;
  for (0..31) {
    $rt .= "\tgt_n_nc_ic N$_, 0.0, ERROR\n";
  }
  return $rt;
}
