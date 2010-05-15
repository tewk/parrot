#! ./parrot-nqp
# $Id$

pir::load_bytecode("opsc.pbc");
pir::load_bytecode("Getopt/Obj.pbc");

my $core := 0;
my @files;
my $emit_lines := 1;

my $getopts := Q:PIR{ %r = new ['Getopt';'Obj'] };

$getopts.notOptStop();
my $arg := $getopts.add();
$arg.long('core');

$arg := $getopts.add();
$arg.long('dynamic');
$arg.type('String');

$arg := $getopts.add();
$arg.long('no-lines');

$arg := $getopts.add();
$arg.long('help');
$arg.short('h');

my $opts := $getopts.get_options(pir::getinterp__p()[2]);

if $opts<core> {
    @files := <
        src/ops/core.ops
        src/ops/bit.ops
        src/ops/cmp.ops
        src/ops/debug.ops
        src/ops/io.ops
        src/ops/math.ops
        src/ops/object.ops
        src/ops/pmc.ops
        src/ops/set.ops
        src/ops/string.ops
        src/ops/sys.ops
        src/ops/var.ops
        src/ops/experimental.ops
    >;
    $core := 1;
}
elsif $opts<dynamic> {
    $core := 0;
    @files.push( $opts<dynamic>);
}
elsif (+$opts == 0 || $opts<help>) {
    say("usage:
ops2c --core
ops2c --dynamic path/to/dynops.ops");
    pir::exit(0);
}

if ($opts<no-lines>) {
    #TODO: figure out how to generate line numbers
    # $emit_lines is currently ignored
    $emit_lines := 0;
}

my $trans := Ops::Trans::C.new();
my $start_time := pir::time__N();
my $f;

if $core {
    my $lib := Ops::OpLib.new(
        :num_file('src/ops/ops.num'),
        :skip_file('src/ops/ops.skip'),
    );
    $f := Ops::File.new(|@files, :oplib($lib), :core(1));
}
else {
    $f := Ops::File.new(|@files, :core(0));
}

my @spf_args := list(pir::time__N() - $start_time);
pir::sprintf(my $time, "%.3f", @spf_args);
say("# Ops parsed in $time seconds.");

my $emitter := Ops::Emitter.new(
    :ops_file($f), :trans($trans),
    :script('ops2c.nqp'), :file(@files[0]),
    :flags( hash( core => $core ) ),
);

$emitter.print_c_header_files();
$emitter.print_c_source_file();

# vim: expandtab shiftwidth=4 ft=perl6:
