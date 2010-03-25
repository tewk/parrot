#!/usr/bin/env parrot-nqp

INIT {
    pir::load_bytecode('ProfTest/ProfTest.pbc');
}

# XXX: Don't bother tryting to run these tests.  None of the supporting code
# exists.  This code is only here to help me figure out the final interface.

my $pir_code := 
'.sub main
  say "what"
.end';

my $prof := ProfTest::PIRProfile.new($pir);

#Does the profile have a version string?
my $matcher := ProfTest::Matcher.new(
    ProfTest::Want::Version.new(),
);

ok( $matcher.matches($prof), "profile has a version number");

#Does the profile have a CLI invocation?
$matcher := ProfTest::Matcher.new(
    ProfTest::Want::CLI.new()
); 

ok( $matcher.matches($prof), "profile contains a CLI string");


#Does the profile have a 'say' op somewhere?
$matcher := ProfTest::Matcher.new(
    ProfTest::Want::Op.new( :op('say') ));
);

ok( $matcher.matches($prof), "profile has a say op");


#Does the profile show a 'say' op inside the 'main' sub?
$matcher := ProfTest::Matcher.new(
    ProfTest::Want::CS.new( :ns('main')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::Op.new( :op('say')),
);
 
ok( $matcher.matches($prof), "profile shows 'say' inside main sub");


$pir_code :=
".sub first :main
  .local int i
  i = 0
  'second'()
  inc i
.end

.sub second
  .local pmc p
  p = new ['Interger']
  'third'()
  p = 1
.end

.sub third
  say 'in third'
.end";

$prof = ProfTest::PIRProfile.new($pir_code);

$matcher := ProfTest::Matcher.new(
    ProfTest::Want::CS.new( :ns('first')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('second')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('third')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('second')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('first')),
);

ok( $matcher.matches($prof), "profile properly reflects normal control flow");


#test: main calls foo, foo tailcalls bar, bar returns to main
$pir_code :=
".sub first :main
  .local int i
  i = 'foo'(9)
.end

.sub foo
  .param int i
  i = i * i
  .tailcall bar(i)
.end

.sub bar
  .param int i
  i = i + 2
  .return (i)
.end";

$prof := ProfTest::PIRProfile.new($pir_code);

$matcher := ProfTest::Matcher.new(
    ProfTest::Want::CS.new( :ns('first') ),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('foo')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('bar')),
    ProfTest::Want.new( :count('*'), :type_isnt('CS')),
    ProfTest::Want::CS.new( :ns('first')),
);

ok( $matcher.matches($prof), "profile properly reflects tailcall control flow");


#Does the profile show a 'say' op on line 2?
$matcher := ProfTest::Matcher.new(
    ProfTest::Want::Op.new( :op('say'), :line('2')),
);

ok( $matcher.matches($prof), "profile shows say on the correct line");


my $nqp_code := '
main();
sub main() {
    pir:say("nqp");
}';

$prof := ProfTest::NQPProfile.new($nqp_code, :annotations(1));

$matcher := ProfTest::Matcher.new();
$matcher.push( ProfTest::Want::CS.new( :ns('parrot;main') ) ); #matches parrot::foo::main
$matcher.push( ProfTest::Want.new(    :count('*'), :type_isnt('CS') ) );
$matcher.push( ProfTest::Want::Op.new( :op('say') ) );

ok( $matcher.matches($prof), "profile shows 'say' inside nqp sub");

