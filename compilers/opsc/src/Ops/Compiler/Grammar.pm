# Copyright (C) 2009, Parrot Foundation.
# $Id$

INIT { pir::load_bytecode('HLL.pbc'); }

grammar Ops::Compiler::Grammar is HLL::Grammar;

rule TOP {
    <body> 
    [ $ || <panic: 'Syntax error'> ]
    {*}
}

rule body {
    [  <preamble> |  <op> ]* {*}
}

token preamble {
    <begin_preamble>
    <preamble_guts>
    <end_preamble>  {*}
}

regex preamble_guts {
    .*? <?end_preamble>
}

token begin_preamble {
    ^^'BEGIN_OPS_PREAMBLE'
}

token end_preamble {
    ^^'END_OPS_PREAMBLE'
}

rule op {
    <op_type>? 'op' <op_name=identifier> '(' 
        [ <op_params>? || <panic: "Fail to parse params"> ]
    ')' <op_flag>* 
    [ <op_body> || <panic: "Fail to parse op body"> ]
    {*}
}

token op_type {
    [ 'inline' | 'function' ]
}

rule op_params {
    <op_param> [ ',' <op_param> ]*
    {*}
}

rule op_param {
    <op_param_direction> <op_param_type>
    {*}
}

token op_param_direction {
    # Order is crucial. PGE doesn't support LTM yet.
    [ 
    | 'inout'
    | 'inconst'
    | 'invar'
    | 'in'
    | 'out'
    ]
}

token op_param_type {
    # Order is crucial. PGE doesn't support LTM yet.
    [
    | 'INTKEY'
    | 'INT'
    | 'NUM'
    | 'STR'
    | 'PMC'
    | 'KEY'
    | 'LABEL'
    ]
}

rule op_flag {
    ':' <identifier>
}

# OpBody starts with '{' and ends with single '}' on line.
token op_body {
    '{' .*? ^^ '}' {*}
}

token identifier {
    <.ident>
}

# ws handles whitespace, pod and perl and C comments
token ws {
  [
  | \s+
  | '#' \N*
  | ^^ '=' .*? \n '=cut'
  | '/*' .*? '*/'
  ]*
}


# vim: expandtab shiftwidth=4 ft=perl6: