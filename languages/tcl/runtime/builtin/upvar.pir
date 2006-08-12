###
# [upvar]

.HLL 'Tcl', 'tcl_group'
.namespace

.sub '&upvar'
  .param pmc argv :slurpy

  .local int argc
  argc = elements argv
  if argc < 2 goto bad_args
  
  .local pmc call_chain, call_level, __call_level
  call_chain   = get_root_global ['_tcl'], 'call_chain'
  call_level   = get_root_global ['_tcl'], 'call_level'
  __call_level = get_root_global ['_tcl'], '__call_level'

  .local pmc new_call_level, orig_call_level
  orig_call_level = new .Integer
  assign orig_call_level, call_level
  .local int defaulted
  $P0 = argv[0]
  (new_call_level,defaulted) = __call_level($P0)
  if defaulted == 1 goto skip
  $P1 = shift argv
  dec argc

skip:
  $I0 = argc % 2
  if $I0 == 1 goto bad_args
  
  # for each othervar/myvar pair, created a mapping from
 
  .local pmc __make, __set, __find_var
  __make     = get_root_global ['_tcl'], '__make'
  __set      = get_root_global ['_tcl'], '__set'
  __find_var = get_root_global ['_tcl'], '__find_var'

  .local int counter, argc
  argc       = argv
  counter    = 0
  .local int difference
  $P0 = new .Integer
  $P0 = orig_call_level - new_call_level
  difference = $P0
loop:
  if counter >= argc goto done
  
  .local string old_var, new_var
  old_var = argv[counter]
  inc counter
  new_var = argv[counter]
  
  $P0 = __find_var(new_var)
  if null $P0 goto store_var
  $S0 = 'variable "'
  $S0 .= new_var
  $S0 .= '" already exists'
  .throw($S0)

store_var:
  assign call_level, new_call_level

  .local pmc saved_call_chain
  saved_call_chain = new .ResizablePMCArray
  $I0 = 0
save_chain_loop:
  if $I0 == difference goto save_chain_end
  $P0 = pop call_chain
  push saved_call_chain, $P0
  inc $I0
  goto save_chain_loop
save_chain_end:

  $P1 = __make(old_var)

  # restore the old level
  assign call_level, orig_call_level
  $I0 = 0
restore_chain_loop:
  if $I0 == difference goto restore_chain_end
  $P0 = pop saved_call_chain
  push call_chain, $P0
  inc $I0
  goto restore_chain_loop
restore_chain_end:

  __set(new_var, $P1)

  inc counter
  goto loop
 
done:
  .return('')

bad_args:
  .throw('wrong # args: should be "upvar ?level? otherVar localVar ?otherVar localVar ...?"')
.end

