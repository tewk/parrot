/*
 * $Id$
 * Copyright (C) 2008, The Perl Foundation.
 */

/* This file declared parser/lexer related functions, so that any changes
 * need only be made once.
 */

#ifndef PARROT_PIR_PIRYY_H_GUARD
#define PARROT_PIR_PIRYY_H_GUARD

int yyerror(yyscan_t yyscanner, lexer_state * const lexer, char const * const message, ...);

#endif /* PARROT_PIR_PIRYY_H_GUARD */
