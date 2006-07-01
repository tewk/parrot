###
# [append]

#
# append var [...]

.HLL 'Tcl', 'tcl_group'
.namespace

.sub "&append"
  .param pmc argv :slurpy

  .local int argc 
  argc = argv

  .local pmc read
  .get_from_HLL(read, '_tcl', '__read')

  .local string value
  .local int looper
  looper = 1

  if argc == 0 goto badargs

  .local string name
  name = argv[0]
  
  if argc == 1 goto getter

setter:
  push_eh new_variable
    $P1 = read(name)
  clear_eh

  value = $P1
  goto loop

new_variable:
  value = ""

loop:
  if looper == argc goto loop_done

  $S2 = argv[looper]
  concat value, $S2
  inc looper
  goto loop

loop_done:
  .local pmc set
  .get_from_HLL(set, '_tcl', '__set')
  .return set(name, value)

getter:
  .return read(name)

badargs:
  .throw ("wrong # args: should be \"append varName ?value value ...?\"")
.end
