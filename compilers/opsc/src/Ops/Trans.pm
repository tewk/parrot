#! nqp

class Ops::Trans is Hash;

=begin

Base class for Runcode transformations.

=end

method prefix() { 'Parrot_' };

# We can't use yada-yada-yada
method suffix() { die("...") }

# Prepare internal structures from Ops::File.ops.
method prepare_ops($emitter, $ops_file) { die('...') }

# 
method emit_c_header_part($fh) { die('...') }

# Called from Ops::Op.
method body_prelude() { '' }

method defines() { die('...') }

method source_preamble() { '' }

method emit_source_part($fh) { die('...') }

# Default op lookup do nothing. But op_deinit referenced anyway.
method emit_op_lookup($fh) {
    $fh.print(q|static void hop_deinit(SHIM_INTERP) {}|);
}

method init_func_init1() { '' }
method init_func_disaptch() { '' }


# vim: expandtab shiftwidth=4 ft=perl6:
