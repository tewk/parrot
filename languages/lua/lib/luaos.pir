# Copyright (C) 2005-2007, The Perl Foundation.
# $Id$

=head1 NAME

lib/luaos.pir - Lua Operating System Library

=head1 DESCRIPTION

This library is implemented through table C<os>.

See "Lua 5.1 Reference Manual", section 5.8 "Operating System Facilities",
L<http://www.lua.org/manual/5.1/manual.html#5.8>.

=head2 Functions

=over 4

=cut

.HLL 'Lua', 'lua_group'

.sub 'init_os' :load :anon

    load_bytecode 'languages/lua/lib/luabasic.pbc'

#    print "init Lua OS\n"

    .local pmc _lua__GLOBAL
    _lua__GLOBAL = global '_G'
    new $P1, .LuaString

    .local pmc _os
    new _os, .LuaTable
    set $P1, 'os'
    _lua__GLOBAL[$P1] = _os

    lua_register($P1, _os)

    .const .Sub _os_clock = 'clock'
    _os_clock.'setfenv'(_lua__GLOBAL)
    set $P1, 'clock'
    _os[$P1] = _os_clock

    .const .Sub _os_date = 'date'
    _os_date.'setfenv'(_lua__GLOBAL)
    set $P1, 'date'
    _os[$P1] = _os_date

    .const .Sub _os_difftime = 'difftime'
    _os_difftime.'setfenv'(_lua__GLOBAL)
    set $P1, 'difftime'
    _os[$P1] = _os_difftime

    .const .Sub _os_execute = 'execute'
    _os_execute.'setfenv'(_lua__GLOBAL)
    set $P1, 'execute'
    _os[$P1] = _os_execute

    .const .Sub _os_exit = 'exit'
    _os_exit.'setfenv'(_lua__GLOBAL)
    set $P1, 'exit'
    _os[$P1] = _os_exit

    .const .Sub _os_getenv = 'getenv'
    _os_getenv.'setfenv'(_lua__GLOBAL)
    set $P1, 'getenv'
    _os[$P1] = _os_getenv

    .const .Sub _os_remove = 'remove'
    _os_remove.'setfenv'(_lua__GLOBAL)
    set $P1, 'remove'
    _os[$P1] = _os_remove

    .const .Sub _os_rename = 'rename'
    _os_rename.'setfenv'(_lua__GLOBAL)
    set $P1, 'rename'
    _os[$P1] = _os_rename

    .const .Sub _os_setlocale = 'setlocale'
    _os_setlocale.'setfenv'(_lua__GLOBAL)
    set $P1, 'setlocale'
    _os[$P1] = _os_setlocale

    .const .Sub _os_time = 'time'
    _os_time.'setfenv'(_lua__GLOBAL)
    set $P1, 'time'
    _os[$P1] = _os_time

    .const .Sub _os_tmpname = 'tmpname'
    _os_tmpname.'setfenv'(_lua__GLOBAL)
    set $P1, 'tmpname'
    _os[$P1] = _os_tmpname

.end


=item C<os.clock ()>

Returns an approximation of the amount in seconds of CPU time used by the
program.

NOT YET IMPLEMENTED (no clock).

=cut

.sub 'clock' :anon
    .local pmc ret
    new ret, .LuaNumber
    not_implemented()
.end


=item C<os.date ([format [, time]])>

Returns a string or a table containing date and time, formatted according to
the given string C<format>.

If the C<time> argument is present, this is the time to be formatted (see
the C<os.time> function for a description of this value). Otherwise, C<date>
formats the current time.

If C<format> starts with C<�!�>, then the date is formatted in Coordinated
Universal Time. After that optional character, if C<format> is C<*t>, then
C<date> returns a table with the following fields: C<year> (four digits),
C<month> (1-12), C<day> (1-31), C<hour> (0-23), C<min> (0-59), C<sec> (0-61),
C<wday> (weekday, Sunday is 1), C<yday> (day of the year), and C<isdst>
(daylight saving flag, a boolean).

If C<format> is not C<*t>, then C<date> returns the date as a string,
formatted according with the same rules as the C function C<strftime>.

When called without arguments, C<date> returns a reasonable date and time
representation that depends on the host system and on the current locale
(that is, C<os.date()> is equivalent to C<os.date("%c")>).

=cut

.include 'tm.pasm'

.sub 'date' :anon
    .param pmc format :optional
    .param pmc time_ :optional
    .local pmc ret
    .local int t
    $S1 = lua_optstring(1, format, '%c')
    $I0 = time
    t = lua_optint(2, time_, $I0)
    $S0 = substr $S1, 0, 1
    unless $S0 == '!' goto L1
    $P0 = decodetime t
    $S1 = substr $S1, 1
    goto L2
L1:
    $P0 = decodelocaltime t
L2:
    unless null $P0 goto L3
    new ret, .LuaNil
    .return (ret)
L3:
    unless $S1 == '*t' goto L4
    new ret, .LuaTable
    new $P1, .LuaString
    new $P2, .LuaNumber
    set $P1, 'sec'
    $I0 = $P0[.TM_SEC]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'min'
    $I0 = $P0[.TM_MIN]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'hour'
    $I0 = $P0[.TM_HOUR]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'day'
    $I0 = $P0[.TM_MDAY]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'month'
    $I0 = $P0[.TM_MON]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'year'
    $I0 = $P0[.TM_YEAR]
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'wday'
    $I0 = $P0[.TM_WDAY]
    inc $I0
    set $P2, $I0
    ret[$P1] = $P2
    set $P1, 'yday'
    $I0 = $P0[.TM_YDAY]
    inc $I0
    set $P2, $I0
    ret[$P1] = $P2
    new $P2, .LuaBoolean
    set $P1, 'isdst'
    $I0 = $P0[.TM_ISDST]
    set $P2, $I0
    ret[$P1] = $P2
    .return (ret)
L4:
    .local string b
    .local int idx
    b = ''
    idx = 0
    $I1 = length $S1
    new $P1, .Lua
L5:
    unless idx < $I1 goto L6
    $S0 = substr $S1, idx, 1
    if $S0 != '%' goto L7
    inc idx
    if idx == $I1 goto L7
    $S0 = substr $S1, idx, 1
    $S2 = '%' . $S0
    $S0 = $P1.'strftime'($S2, $P0)
L7:
    b .= $S0
    inc idx
    goto L5
L6:
    new ret, .LuaString
    set ret, b
    .return (ret)
.end


=item C<os.difftime (t2, t1)>

Returns the number of seconds from time C<t1> to time C<t2>. In Posix,
Windows, and some other systems, this value is exactly C<t2-t1>.

=cut

.sub 'difftime' :anon
    .param pmc t2 :optional
    .param pmc t1 :optional
    .local pmc ret
    $I2 = lua_checknumber(1, t2)
    $I1 = lua_optint(2, t1, 0)
    $I0 = $I2 - $I1
    new ret, .LuaNumber
    set ret, $I0
    .return (ret)
.end


=item C<os.execute ([command])>

This function is equivalent to the C function C<system>. It passes C<command>
to be executed by an operating system shell. It returns a status code, which
is system-dependent.

This function is equivalent to the C function C<system>. It passes C<command>
to be executed by an operating system shell. It returns a status code, which
is system-dependent. If C<command> is absent, then it returns nonzero if a
shell is available and zero otherwise.

=cut

.sub 'execute' :anon
    .param pmc command :optional
    .local pmc ret
    $S1 = lua_optstring(1, command, '')
    unless $S1 == '' goto L1
    $I0 = 1
    goto L2
L1:
    $I0 = spawnw $S1
    $I0 = $I0 / 256
L2:
    new ret, .LuaNumber
    ret = $I0
    .return (ret)
.end


=item C<os.exit ([code])>

Calls the C function C<exit>, with an optional C<code>, to terminate the host
program. The default value for C<code> is the success code.

=cut

.sub 'exit' :anon
    .param pmc code :optional
    $I1 = lua_optint(1, code, 0)
    exit $I1
.end


=item C<os.getenv (varname)>

Returns the value of the process environment variable C<varname>, or B<nil>
if the variable is not defined.

=cut

.sub 'getenv' :anon
    .param pmc varname :optional
    .local pmc ret
    $S1 = lua_checkstring(1, varname)
    new $P0, .Env
    $S0 = $P0[$S1]
    if $S0 goto L1
    new ret, .LuaNil
    .return (ret)
L1:
    new ret, .LuaString
    set ret, $S0
    .return (ret)
.end


=item C<os.remove (filename)>

Deletes the file or directory with the given name. Directories must be empty
to be removed. If this function fails, it returns B<nil>, plus a string
describing the error.

=cut

.sub 'remove' :anon
    .param pmc filename :optional
    .local pmc ret
    $S1 = lua_checkstring(1, filename)
    $S0 = $S1
    new $P0, .OS
    push_eh _handler
    $P0.'rm'($S1)
    new ret, .LuaBoolean
    set ret, 1
    .return (ret)
_handler:
    .local pmc nil
    .local pmc msg
    .local pmc e
    .local string s
    .get_results (e, s)
    concat $S0, ': '
    concat $S0, s
    new nil, .LuaNil
    new msg, .LuaString
    set msg, $S0
    .return (nil, msg)
.end


=item C<os.rename (oldname, newname)>

Renames file or directory named C<oldname> to C<newname>. If this function
fails, it returns B<nil>, plus a string describing the error.

=cut

.sub 'rename' :anon
    .param pmc oldname :optional
    .param pmc newname :optional
    .local pmc ret
    $S1 = lua_checkstring(1, oldname)
    $S0 = $S1
    $S2 = lua_checkstring(2, newname)
    new $P0, .OS
    push_eh _handler
    $P0.'rename'($S1, $S2)
    new ret, .LuaBoolean
    set ret, 1
    .return (ret)
_handler:
    .local pmc nil
    .local pmc msg
    .local pmc e
    .local string s
    .get_results (e, s)
    concat $S0, ': '
    concat $S0, s
    new nil, .LuaNil
    new msg, .LuaString
    set msg, $S0
    .return (nil, msg)
.end


=item C<os.setlocale (locale [, category])>

Sets the current locale of the program. C<locale> is a string specifying a
locale; C<category> is an optional string describing which category to change:
C<"all">, C<"collate">, C<"ctype">, C<"monetary">, C<"numeric">, or C<"time">;
the default category is C<"all">. The function returns the name of the new
locale, or B<nil> if the request cannot be honored.

NOT YET IMPLEMENTED (no setlocale).

=cut

.sub 'setlocale' :anon
    .param pmc locale :optional
    .param pmc category :optional
    $S1 = lua_optstring(1, locale, '')
    $S2 = lua_optstring(2, category, 'all')
    $I2 = lua_checkoption(2, $S2, 'all collate ctype monetary numeric time')
    not_implemented()
.end


=item C<os.time ([table])>

Returns the current time when called without arguments, or a time representing
the date and time specified by the given table. This table must have fields
C<year>, C<month>, and C<day>, and may have fields C<hour>, C<min>, C<sec>,
and C<isdst> (for a description of these fields, see the C<os.date> function).

The returned value is a number, whose meaning depends on your system. In
Posix, Windows, and some other systems, this number counts the number of
seconds since some given start time (the "epoch"). In other systems, the
meaning is not specified, and the number returned by C<time> can be used only
as an argument to C<date> and C<difftime>.

STILL INCOMPLETE (no mktime).

=cut

.sub 'time' :anon
    .param pmc table :optional
    .local pmc ret
    if null table goto L1
    $I0 = isa table, 'LuaNil'
    unless $I0 goto L2
L1:
    $I0 = time
    new ret, .LuaNumber
    set ret, $I0
    .return (ret)
L2:
    lua_checktype(1, table, 'table')
    $I1 = getfield(table, 'sec', 0)
    $I2 = getfield(table, 'min', 0)
    $I3 = getfield(table, 'hour', 12)
    $I4 = getfield(table, 'day', -1)
    $I5 = getfield(table, 'month', -1)
    $I5 -= 1
    $I6 = getfield(table, 'year', -1)
    $I6 -= 1900
    $I7 = getboolfield(table, 'isdst')
    not_implemented()
.end

.sub 'getfield' :anon
    .param pmc t
    .param string key
    .param int d
    .local int ret
    new $P1, .LuaString
    set $P1, key
    $P0 = t[$P1]
    $P0 = $P0.'tonumber'()
    $I0 = isa $P0, 'LuaNumber'
    unless $I0 goto L1
    ret = $P0
    goto L2
L1:
    unless d < 0 goto L3
    lua_error("field '", key, "' missing in date table")
L3:
    ret = d
L2:
    .return (ret)
.end

.sub 'getboolfield' :anon
    .param pmc t
    .param string key
    .local int ret
    new $P1, .LuaString
    set $P1, key
    $P0 = t[$P1]
    $I0 = isa $P0, 'LuaNil'
    unless $I0 goto L1
    ret = -1
    goto L2
L1:
    ret = istrue $P0
L2:
    .return (ret)
.end


=item C<os.tmpname ()>

Returns a string with a file name that can be used for a temporary file.
The file must be explicitly opened before its use and explicitly removed
when no longer needed.

NOT YET IMPLEMENTED (no tmpname).

=cut

.sub 'tmpname' :anon
    .local pmc ret
    new ret, .LuaString
    not_implemented()
.end

=back

=head1 AUTHORS

Francois Perrad.

=cut


# Local Variables:
#   mode: pir
#   fill-column: 100
# End:
# vim: expandtab shiftwidth=4:
