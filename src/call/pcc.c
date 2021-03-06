/*
Copyright (C) 2001-2010, Parrot Foundation.
$Id$

=head1 NAME

src/call/pcc.c

=head1 DESCRIPTION

B<Parrot Calling Conventions>:  Functions in this file handle argument/return
value passing to and from subroutines.

=head1 FUNCTIONS

=over 4

=cut

*/

#include "parrot/parrot.h"
#include "parrot/runcore_api.h"
#include "parrot/oplib/ops.h"
#include "pcc.str"
#include "pmc/pmc_key.h"
#include "pmc/pmc_continuation.h"

/* HEADERIZER HFILE: include/parrot/call.h */

/* HEADERIZER BEGIN: static */
/* Don't modify between HEADERIZER BEGIN / HEADERIZER END.  Your changes will be lost. */

static int do_run_ops(PARROT_INTERP, ARGIN(PMC *sub_obj))
        __attribute__nonnull__(1)
        __attribute__nonnull__(2);

static void Parrot_pcc_add_invocant(PARROT_INTERP,
    ARGIN(PMC *call_obj),
    ARGIN(PMC *pmc))
        __attribute__nonnull__(1)
        __attribute__nonnull__(2)
        __attribute__nonnull__(3);

#define ASSERT_ARGS_do_run_ops __attribute__unused__ int _ASSERT_ARGS_CHECK = (\
       PARROT_ASSERT_ARG(interp) \
    , PARROT_ASSERT_ARG(sub_obj))
#define ASSERT_ARGS_Parrot_pcc_add_invocant __attribute__unused__ int _ASSERT_ARGS_CHECK = (\
       PARROT_ASSERT_ARG(interp) \
    , PARROT_ASSERT_ARG(call_obj) \
    , PARROT_ASSERT_ARG(pmc))
/* Don't modify between HEADERIZER BEGIN / HEADERIZER END.  Your changes will be lost. */
/* HEADERIZER END: static */


/*

=item C<void Parrot_pcc_invoke_sub_from_c_args(PARROT_INTERP, PMC *sub_obj,
const char *sig, ...)>

Follows the same conventions as C<Parrot_PCCINVOKE>, but the subroutine object
to invoke is passed as an argument rather than looked up by name. The signature
string and call arguments are converted to a CallSignature PMC.

=cut

*/

PARROT_EXPORT
void
Parrot_pcc_invoke_sub_from_c_args(PARROT_INTERP, ARGIN(PMC *sub_obj),
        ARGIN(const char *sig), ...)
{
    ASSERT_ARGS(Parrot_pcc_invoke_sub_from_c_args)
    PMC         *call_obj;
    va_list      args;
    const char  *arg_sig, *ret_sig;
    PMC         * const old_call_obj =
        Parrot_pcc_get_signature(interp, CURRENT_CONTEXT(interp));

    Parrot_pcc_split_signature_string(sig, &arg_sig, &ret_sig);

    va_start(args, sig);
    call_obj = Parrot_pcc_build_call_from_varargs(interp, PMCNULL,
         arg_sig, &args);
    Parrot_pcc_set_signature(interp, CURRENT_CONTEXT(interp), call_obj);
    Parrot_pcc_invoke_from_sig_object(interp, sub_obj, call_obj);
    call_obj = Parrot_pcc_get_signature(interp, CURRENT_CONTEXT(interp));
    Parrot_pcc_fill_params_from_varargs(interp, call_obj, ret_sig, &args,
            PARROT_ERRORS_RESULT_COUNT_FLAG);
    va_end(args);
    Parrot_pcc_set_signature(interp, CURRENT_CONTEXT(interp), old_call_obj);
}


/*

=item C<void Parrot_PCCINVOKE(PARROT_INTERP, PMC* pmc, STRING *method_name,
const char *signature, ...)>

DEPRECATED. See TT #443. Use Parrot_pcc_invoke_method_from_c_args instead.

C<pmc> is the invocant.

C<method_name> is the same C<method_name> used in the C<find_method>
VTABLE call

C<signature> is a C string describing the Parrot calling conventions for
Parrot_PCCINVOKE.  ... variable args contains the IN arguments followed
by the OUT results variables.  You must pass the address_of(&) the OUT
results, of course.

Signatures:
  uppercase letters repesent each arg and denote its types

  I INTVAL
  N FLOATVAL
  S STRING *
  P PMC *

  lowercase letters are adverb modifiers to the preceeding uppercase arg
  identifier

  f flatten
  n named
  s slurpy
  o optional
  p opt flag
  l lookahead parameter (next positional, or next named if no positionals)
  i invocant

  -> is the separator between args and results, similar to type theory
  notation.

  Named args require two arg slots. The first is the name, the second the arg.

Example signature:

  "SnIPf->INPs"

  The args to the method invocation are
    a named INTVAL:  SnI
    a flattened PMC:  Pf

  The results of the method invocation are
    a INTVAL: I
    a FLOATVAL: N
    a slurpy PMC: Ps

invokes a PMC method

=cut

*/

PARROT_EXPORT
void
Parrot_PCCINVOKE(PARROT_INTERP, ARGIN(PMC* pmc), ARGMOD(STRING *method_name),
        ARGIN(const char *signature), ...)
{
    ASSERT_ARGS(Parrot_PCCINVOKE)
    PMC *sig_obj;
    PMC *sub_obj;
    va_list args;
    va_start(args, signature);
    sig_obj = Parrot_pcc_build_sig_object_from_varargs(interp, pmc, signature, args);
    va_end(args);

    /* Find the subroutine object as a named method on pmc */
    sub_obj = VTABLE_find_method(interp, pmc, method_name);
    if (PMC_IS_NULL(sub_obj))
         Parrot_ex_throw_from_c_args(interp, NULL, EXCEPTION_METHOD_NOT_FOUND,
             "Method '%Ss' not found", method_name);

    /* Invoke the subroutine object with the given CallSignature object */
    Parrot_pcc_invoke_from_sig_object(interp, sub_obj, sig_obj);
}


/*

=item C<static void Parrot_pcc_add_invocant(PARROT_INTERP, PMC *call_obj, PMC
*pmc)>

Adds the given PMC as an invocant to the given CallContext PMC.  You should
never have to use this, and it should go away with interp->current_object.

*/

static void
Parrot_pcc_add_invocant(PARROT_INTERP, ARGIN(PMC *call_obj), ARGIN(PMC *pmc))
{
    ASSERT_ARGS(Parrot_pcc_add_invocant)
    PMC *arg_flags;
    GETATTR_CallContext_arg_flags(interp, call_obj, arg_flags);

    VTABLE_unshift_integer(interp, arg_flags,
          PARROT_ARG_PMC | PARROT_ARG_INVOCANT);
          VTABLE_unshift_pmc(interp, call_obj, pmc);
}

/*

=item C<void Parrot_pcc_invoke_method_from_c_args(PARROT_INTERP, PMC* pmc,
STRING *method_name, const char *signature, ...)>

Makes a method call given the name of the method and the arguments as a C
variadic argument list. C<pmc> is the invocant, C<method_name> is the string
name of the method, C<signature> is a C string describing the signature of the
invocation, according to the Parrot calling conventions.  The variadic argument
list contains the input arguments followed by the output results in the same
order that they appear in the function signature.

=cut

*/

PARROT_EXPORT
void
Parrot_pcc_invoke_method_from_c_args(PARROT_INTERP, ARGIN(PMC* pmc),
        ARGMOD(STRING *method_name),
        ARGIN(const char *signature), ...)
{
    ASSERT_ARGS(Parrot_pcc_invoke_method_from_c_args)
    PMC        *call_obj;
    PMC        *sub_obj;
    va_list     args;
    const char *arg_sig, *ret_sig;
    PMC        * const old_call_obj =
        Parrot_pcc_get_signature(interp, CURRENT_CONTEXT(interp));

    Parrot_pcc_split_signature_string(signature, &arg_sig, &ret_sig);

    va_start(args, signature);
    call_obj = Parrot_pcc_build_call_from_varargs(interp, PMCNULL, arg_sig, &args);
    Parrot_pcc_add_invocant(interp, call_obj, pmc);

    Parrot_pcc_set_signature(interp, CURRENT_CONTEXT(interp), call_obj);

    /* Find the subroutine object as a named method on pmc */
    sub_obj = VTABLE_find_method(interp, pmc, method_name);

    if (PMC_IS_NULL(sub_obj))
        Parrot_ex_throw_from_c_args(interp, NULL, EXCEPTION_METHOD_NOT_FOUND,
            "Method '%Ss' not found", method_name);

    /* Invoke the subroutine object with the given CallContext object */
    Parrot_pcc_invoke_from_sig_object(interp, sub_obj, call_obj);
    call_obj = Parrot_pcc_get_signature(interp, CURRENT_CONTEXT(interp));
    Parrot_pcc_fill_params_from_varargs(interp, call_obj, ret_sig, &args,
            PARROT_ERRORS_RESULT_COUNT_FLAG);
    va_end(args);
    Parrot_pcc_set_signature(interp, CURRENT_CONTEXT(interp), old_call_obj);
}


/*

=item C<static int is_invokable(PARROT_INTERP, PMC *sub_obj)>

Check if the PMC is a Sub or does invokable. Helper for do_run_ops.

=cut

*/

PARROT_INLINE
static int
is_invokable(PARROT_INTERP, ARGIN(PMC*sub_obj)) /* HEADERIZER SKIP */
{
    if (VTABLE_isa(interp, sub_obj, CONST_STRING(interp, "Sub")))
        return 1;
    else
        return VTABLE_does(interp, sub_obj, CONST_STRING(interp, "invokable"));
}

/*

=item C<static int do_run_ops(PARROT_INTERP, PMC *sub_obj)>

Check should we run ops.

PIR Subs need runops to run their opcodes. Methods and NCI subs don't.

=cut

*/

static int
do_run_ops(PARROT_INTERP, ARGIN(PMC *sub_obj))
{
    ASSERT_ARGS(do_run_ops)

    if (sub_obj->vtable->base_type < enum_class_core_max)
        return sub_obj->vtable->base_type == enum_class_Sub
            || sub_obj->vtable->base_type == enum_class_MultiSub
            || sub_obj->vtable->base_type == enum_class_Eval;
    else
        return is_invokable(interp, sub_obj);
}

/*

=item C<INTVAL Parrot_pcc_do_run_ops(PARROT_INTERP, PMC *sub_obj)>

Check if current object require running ops. Used in tailcall for updating
RetContinuation.

=cut

*/
PARROT_EXPORT
INTVAL
Parrot_pcc_do_run_ops(PARROT_INTERP, ARGIN(PMC *sub_obj))
{
    ASSERT_ARGS(Parrot_pcc_do_run_ops)
    return do_run_ops(interp, sub_obj);
}

/*

=item C<void Parrot_pcc_invoke_from_sig_object(PARROT_INTERP, PMC *sub_obj, PMC
*call_object)>

Follows the same conventions as C<Parrot_PCCINVOKE>, but the subroutine object
to invoke is passed as an argument rather than looked up by name, and the
signature string and call arguments are passed in a CallSignature PMC.

=cut

*/

PARROT_EXPORT
void
Parrot_pcc_invoke_from_sig_object(PARROT_INTERP, ARGIN(PMC *sub_obj),
        ARGIN(PMC *call_object))
{
    ASSERT_ARGS(Parrot_pcc_invoke_from_sig_object)

    opcode_t    *dest;
    UINTVAL      n_regs_used[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
    PMC         *ctx  = Parrot_push_context(interp, n_regs_used);
    PMC * const  ret_cont = pmc_new(interp, enum_class_Continuation);

    Parrot_pcc_set_signature(interp, ctx, call_object);
    Parrot_pcc_set_continuation(interp, ctx, ret_cont);
    interp->current_cont         = NEED_CONTINUATION;
    PARROT_CONTINUATION(ret_cont)->from_ctx = ctx;

    /* Invoke the function */
    dest = VTABLE_invoke(interp, sub_obj, NULL);

    /* PIR Subs need runops to run their opcodes. Methods and NCI subs
     * don't. */
    if (do_run_ops(interp, sub_obj)) {
        Parrot_runcore_t *old_core = interp->run_core;
        const opcode_t offset = dest - interp->code->base.data;

        runops(interp, offset);
        Interp_core_SET(interp, old_core);
    }
    Parrot_pop_context(interp);
    Parrot_pcc_set_signature(interp, CURRENT_CONTEXT(interp),
            Parrot_pcc_get_signature(interp, ctx));
}

/*

=back

=head1 SEE ALSO

F<include/parrot/interpreter.h>, F<src/call/ops.c>, F<src/pmc/sub.pmc>.

=cut

*/

/*
 * Local variables:
 *   c-file-style: "parrot"
 * End:
 * vim: expandtab shiftwidth=4:
 */
