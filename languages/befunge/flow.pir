# $Id$

# ** direction changing

#
# go east.
# befunge stack unchanged.
# delta <- (1,0)
.sub "flow__go_east"
    $P0 = get_global "status"
    $P0["dir"] = 1
    set_global "status", $P0
.end

#
# go north.
# befunge stack unchanged.
# delta <- (1,0)
.sub "flow__go_north"
    $P0 = get_global "status"
    $P0["dir"] = 0
    set_global "status", $P0
.end

#
# go south.
# befunge stack unchanged.
# delta <- (-1,0)
#
.sub "flow__go_south"
    $P0 = get_global "status"
    $P0["dir"] = 2
    set_global "status", $P0
.end

#
# go west.
# befunge stack unchanged.
# delta <- (-1,0)
#
.sub "flow__go_west"
    $P0 = get_global "status"
    $P0["dir"] = 3
    set_global "status", $P0
.end


#
# go away.
# befunge stack unchanged.
# delta <- one of N,S,E,W
# no return value
#
.sub "flow__go_away"
    $P0 = new 'Random'
    $N0 = $P0
    $N0 = $N0 * 4
    $I0 = $N0
    $P0 = get_global "status"
    $P0["dir"] = $I0
    set_global "status", $P0
.end


# ** ifs & comparisons

#
# flow__compare()
#
# greater than.
# befunge stack:
#   before:     ... a b
#   after:      ... a>b
# result is either 1 or 0.
#
.sub "flow__compare"
    .local int a, b
    b = stack__pop()
    a = stack__pop()
    
    if a < b goto FLOW__COMPARE__FALSE
    stack__push(1)
    .return()
    
  FLOW__COMPARE__FALSE:
    stack__push(0)
.end


#
# flow__if_horizontal()
#
# east/west if.
# befunge stack:
#   before:     ... b
#   after:      ...
# delta <- if (b) (-1,0) else (1,0)
.sub "flow__if_horizontal"
    $I0 = stack__pop()
    if $I0 == 0 goto FLOW__IF_HORIZONTAL__FALSE
    flow__go_west()
    .return()
  FLOW__IF_HORIZONTAL__FALSE:
    flow__go_east()
.end

#
# flow__if_vertical()
#
# north/south if.
# befunge stack:
#   before:     ... b
#   after:      ...
# delta <- if (b) (0,-1) else (0,1)
.sub "flow__if_vertical"
    $I0 = stack__pop()
    if $I0 == 0 goto FLOW__IF_HORIZONTAL__FALSE
    flow__go_south()
    .return()
  FLOW__IF_HORIZONTAL__FALSE:
    flow__go_north()
.end


# ** flag handling

#
# _flow__flag_set(val)
#
# set flag to val.
#
.sub "_flow__flag_set"
    .param int val
    $P0 = get_global "status"
    $P0["flag"] = val
    set_global "status", $P0
.end


#
# flow__toggle_string_mode()
#
# toggle string mode.
# befunge stack unchanged.
#
.sub "flow__toggle_string_mode"
    $P0 = get_global "status"
    $I0 = $P0["flag"]

    if $I0 == 1 goto FLOW__TOGGLE_STRING_MODE__OFF
    _flow__flag_set(1)
    .return()

  FLOW__TOGGLE_STRING_MODE__OFF:
    _flow__flag_set(0)
.end


#
# flow__trampoline(bool)
#
# set/remove trampoline flag.
# befunge stack unchanged.
#
.sub "flow__trampoline"
    .param int val
    if val == 0 goto FLOW__TRAMPOLINE_OFF
    _flow__flag_set(2)
    .return()
  FLOW__TRAMPOLINE_OFF:
    _flow__flag_set(0)
.end



# ** end

#
# flow__end()
#
# stop.
# befunge stack unchanged.
# end program.
# no return value.
#
.sub "flow__end"
    end
.end


########################################################################
# Local Variables:
#   mode: pir
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4 ft=pir:

