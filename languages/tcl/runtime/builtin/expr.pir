###
# [expr]

#
# expr arg [... arg arg]

.HLL 'Tcl', 'tcl_group'
.namespace

.sub '&expr'
  .param pmc argv :slurpy
 
  .local string expr
  .local int argc
  .local int looper

  .local pmc retval
  .local pmc expression_compiler,pir_compiler
  .get_from_HLL(expression_compiler, '_tcl', '__expr')

  expr = ''
  looper = 0
  argc = argv 
  unless argc goto no_args

loop:
  if looper == argc goto loop_done
  $S0 = argv[looper]
  concat expr, $S0
  inc looper
  if looper == argc goto loop_done
  concat expr,' '

  goto loop

loop_done:
  $P1 = expression_compiler(expr)
  $P2 = $P1()
  .return ($P2)

no_args:
  .throw('wrong # args: should be "expr arg ?arg ...?"')
.end
