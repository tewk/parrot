
class Ops::Trans::C is Ops::Trans;

method new() {
    # Storage for generated ops functions.
    self<op_funcs>  := list();
    # Storage for generated ops functions prototypes.
    self<op_protos> := list();

    self;
}

method suffix() { '' };

method prepare_ops($emitter, $ops_file) {

    my $index := 0;
    my @op_protos;
    my @op_funcs;
    my @op_func_table;

    for $ops_file.ops -> $op {
        #say("# preparing " ~ $op);
        my $func_name := self.suffix ~ $op.full_name;
        my $definition := "opcode_t *\n$func_name (opcode_t *cur_opcode, PARROT_INTERP)";
        my $prototype := $emitter.sym_export
                ~ "opcode_t * $func_name (opcode_t *cur_opcode, PARROT_INTERP);\n";

        my $src := $op.source( self );

        @op_func_table.push(sprintf( "  %-50s /* %6ld */\n", "$func_name,", $index ));

        my $body := join(' ', $definition, '{', "\n", $src, '}', "\n\n");
        @op_funcs.push($body);
        @op_protos.push($prototype);
        $index++;
    }

    self<op_funcs>      := @op_funcs;
    self<op_protos>     := @op_protos;
    self<op_func_table> := @op_func_table;
}

method emit_c_header_part($fh) {
    for self<op_protos> -> $proto {
        $fh.print($proto);
    }
}

=begin

=item C<defines()>

Returns the C C<#define> macros for register access etc.

=end

method defines() {
    return qq|
#include "pmc/pmc_callcontext.h"

/* defines - Ops::Trans::C */
#undef CONST
#define REL_PC     ((size_t)(cur_opcode - (opcode_t *)interp->code->base.data))
#define CUR_OPCODE cur_opcode
#define IREG(i) (CUR_CTX->bp.regs_i[cur_opcode[i]])
#define NREG(i) (CUR_CTX->bp.regs_n[-1L - cur_opcode[i]])
#define PREG(i) (CUR_CTX->bp_ps.regs_p[-1L - cur_opcode[i]])
#define SREG(i) (CUR_CTX->bp_ps.regs_s[cur_opcode[i]])
#define CONST(i) Parrot_pcc_get_constants(interp, interp->ctx)[cur_opcode[i]]
|;

}

method source_preamble() {
q/
static int get_op(PARROT_INTERP, const char * name, int full);
/
}

method emit_source_part($fh) {
}


method emit_op_lookup($emitter, $fh) {

    if $emitter.flags<dynamic> {
        return;
    }

    my $hash_size := 3041;
#    my $tot       := $self->{index} + scalar keys( %{ $self->{names} } );
#    if ( $hash_size < $tot * 1.2 ) {
#        print STDERR "please increase hash_size ($hash_size) in lib/Parrot/Ops2c/Utils.pm "
#            . "to a prime number > ", $tot * 1.2, "\n";
#    }
    # Due bug in NQP do it in two passes.
    my $res := q|
/*
** Op lookup function:
*/

#define OP_HASH_SIZE 3041

/* we could calculate a prime somewhat bigger than
 * n of fullnames + n of names
 * for now this should be ok
 *
 * look up an op_code: at first call to op_code() a hash
 * of short and full opcode names is created
 * hash functions are from imcc, thanks to Melvin.
 */


typedef struct hop {
    op_info_t * info;
    struct hop *next;
} HOP;
static HOP **hop;

static void hop_init(PARROT_INTERP);
static size_t hash_str(const char *str);
static void store_op(PARROT_INTERP, op_info_t *info, int full);

/* XXX on changing interpreters, this should be called,
   through a hook */

static void hop_deinit(PARROT_INTERP);

/*
 * find a short or full opcode
 * usage:
 *
 * interp->op_lib->op_code("set", 0)
 * interp->op_lib->op_code("set_i_i", 1)
 *
 * returns >= 0 (found idx into info_table), -1 if not
 */

static size_t hash_str(const char *str) {
    size_t      key = 0;
    const char *s   = str;

    while (*s) {
        key *= 65599;
        key += *s++;
    }

    return key;
}

static void store_op(PARROT_INTERP, op_info_t *info, int full) {
    HOP * const p     = mem_gc_allocate_zeroed_typed(interp, HOP);
    const size_t hidx =
        hash_str(full ? info->full_name : info->name) % OP_HASH_SIZE;

    p->info   = info;
    p->next   = hop[hidx];
    hop[hidx] = p;
}
static int get_op(PARROT_INTERP, const char * name, int full) {
    const HOP * p;
    const size_t hidx = hash_str(name) % OP_HASH_SIZE;
    if (!hop) {
        hop = mem_gc_allocate_n_zeroed_typed(interp, OP_HASH_SIZE,HOP *);
        hop_init(interp);
    }
    for (p = hop[hidx]; p; p = p->next) {
        if(STREQ(name, full ? p->info->full_name : p->info->name))
            return p->info - [[BS]]op_lib.op_info_table;
    }
    return -1;
}
static void hop_init(PARROT_INTERP) {
    size_t i;
    op_info_t * const info = [[BS]]op_lib.op_info_table;
    /* store full names */
    for (i = 0; i < [[BS]]op_lib.op_count; i++)
        store_op(interp, info + i, 1);
    /* plus one short name */
    for (i = 0; i < [[BS]]op_lib.op_count; i++)
        if (get_op(interp, info[i].name, 0) == -1)
            store_op(interp, info + i, 0);
}
static void hop_deinit(PARROT_INTERP)
{
    if (hop) {
        size_t i;
        for (i = 0; i < OP_HASH_SIZE; i++) {
            HOP *p = hop[i];
            while (p) {
                HOP * const next = p->next;
                mem_gc_free(interp, p);
                p = next;
            }
        }
        mem_sys_free(hop);
        hop = NULL;
    }
}|;

    $fh.print(subst($res, /'[[' BS ']]'/, $emitter.bs));
}

# vim: expandtab shiftwidth=4 ft=perl6:
