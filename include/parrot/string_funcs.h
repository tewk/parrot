/* string_funcs.h
 *  Copyright (C) 2001-2007, The Perl Foundation.
 *  SVN Info
 *     $Id$
 *  Overview:
 *     This is the api header for the string subsystem
 *  Data Structure and Algorithms:
 *  History:
 *  Notes:
 *  References:
 */

#ifndef PARROT_STRING_FUNCS_H_GUARD
#define PARROT_STRING_FUNCS_H_GUARD

#include "parrot/compiler.h"

#ifdef PARROT_IN_CORE

/* Declarations of accessors */

/* HEADERIZER BEGIN: src/string.c */
PARROT_API STRING * Parrot_make_COW_reference( Interp *interp,
    STRING *s /*NULLOK*/ );

PARROT_API STRING* Parrot_reuse_COW_reference( Interp *interp,
    STRING *s /*NULLOK*/,
    STRING *d /*NN*/ )
        __attribute__nonnull__(3);

PARROT_API const char * Parrot_string_cstring( Interp *interp,
    const STRING *str /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API INTVAL Parrot_string_find_cclass( Interp *interp,
    INTVAL flags,
    STRING *s,
    UINTVAL offset,
    UINTVAL count );

PARROT_API INTVAL Parrot_string_find_not_cclass( Interp *interp,
    INTVAL flags,
    STRING *s /*NULLOK*/,
    UINTVAL offset,
    UINTVAL count );

PARROT_API INTVAL Parrot_string_is_cclass( Interp *interp,
    INTVAL flags,
    STRING *s,
    UINTVAL offset );

PARROT_API STRING* Parrot_string_trans_charset( Interp *interp,
    STRING *src /*NULLOK*/,
    INTVAL charset_nr,
    STRING *dest /*NULLOK*/ );

PARROT_API STRING* Parrot_string_trans_encoding( Interp *interp,
    STRING *src /*NULLOK*/,
    INTVAL encoding_nr,
    STRING *dest /*NULLOK*/ );

PARROT_API void Parrot_unmake_COW( Interp *interp, STRING *s /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * const_string( Interp *interp, const char *buffer /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * int_to_str( Interp *interp,
    char *tc /*NN*/,
    HUGEINTVAL num,
    char base )
        __attribute__nonnull__(2);

PARROT_API STRING * string_append( Interp *interp,
    STRING *a /*NULLOK*/,
    STRING *b /*NULLOK*/ );

PARROT_API STRING * string_bitwise_and( Interp *interp,
    STRING *s1 /*NULLOK*/,
    STRING *s2 /*NULLOK*/,
    STRING **dest /*NULLOK*/ );

PARROT_API STRING * string_bitwise_not( Interp *interp,
    STRING *s /*NULLOK*/,
    STRING **dest /*NULLOK*/ );

PARROT_API STRING * string_bitwise_or( Interp *interp,
    STRING *s1 /*NULLOK*/,
    STRING *s2 /*NULLOK*/,
    STRING **dest /*NULLOK*/ );

PARROT_API STRING * string_bitwise_xor( Interp *interp,
    STRING *s1 /*NULLOK*/,
    STRING *s2 /*NULLOK*/,
    STRING **dest /*NULLOK*/ );

PARROT_API INTVAL string_bool( Interp *interp, const STRING *s /*NULLOK*/ );
PARROT_API UINTVAL string_capacity( Interp *interp, const STRING *s /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * string_chopn( Interp *interp,
    STRING *s /*NULLOK*/,
    INTVAL n,
    int in_place );

PARROT_API STRING * string_chr( Interp *interp, UINTVAL character );
PARROT_API INTVAL string_compare( Interp *interp,
    const STRING *s1 /*NULLOK*/,
    const STRING *s2 /*NULLOK*/ );

PARROT_API STRING * string_compose( Interp *interp, STRING *src /*NULLOK*/ );
PARROT_API INTVAL string_compute_strlen( Interp *interp, STRING *s /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * string_concat( Interp *interp,
    STRING *a /*NULLOK*/,
    STRING *b /*NULLOK*/,
    UINTVAL Uflags );

PARROT_API STRING * string_copy( Interp *interp, STRING *s /*NULLOK*/ );
PARROT_API void string_cstring_free( char *p /*NULLOK*/ );
PARROT_API void string_deinit( Parrot_Interp interp );
PARROT_API STRING * string_downcase( Interp *interp,
    const STRING *s /*NULLOK*/ );

PARROT_API void string_downcase_inplace( Interp *interp,
    STRING *s /*NULLOK*/ );

PARROT_API INTVAL string_equal( Interp *interp,
    const STRING *s1 /*NULLOK*/,
    const STRING *s2 /*NULLOK*/ );

PARROT_API STRING * string_escape_string( Interp *interp,
    const STRING *src /*NULLOK*/ );

PARROT_API STRING * string_escape_string_delimited( Interp *interp,
    const STRING *src /*NULLOK*/,
    UINTVAL limit );

PARROT_API STRING * string_from_const_cstring( Interp *interp,
    const char *buffer,
    UINTVAL len );

PARROT_API STRING * string_from_cstring( Interp *interp,
    const char *buffer /*NULLOK*/,
    UINTVAL len );

PARROT_API STRING * string_from_int( Interp *interp, INTVAL i );
PARROT_API STRING * string_from_num( Interp *interp, FLOATVAL f );
PARROT_API STRING * string_grow( Interp * interp,
    STRING *s /*NN*/,
    INTVAL addlen )
        __attribute__nonnull__(2);

PARROT_API size_t string_hash( Interp *interp,
    STRING *s /*NULLOK*/,
    size_t seed );

PARROT_API STRING * string_increment( Interp *interp,
    const STRING *s /*NULLOK*/ );

PARROT_API INTVAL string_index( Interp *interp,
    const STRING *s /*NN*/,
    UINTVAL idx )
        __attribute__nonnull__(2);

PARROT_API void string_init( Parrot_Interp interp );
PARROT_API STRING* string_join( Interp *interp,
    STRING *j /*NULLOK*/,
    PMC *ar );

PARROT_API UINTVAL string_length( Interp *interp, const STRING *s /*NULLOK*/ );
PARROT_API STRING * string_make( Interp *interp,
    const char *buffer /*NULLOK*/,
    UINTVAL len,
    const char *charset_name /*NULLOK*/,
    UINTVAL flags );

PARROT_API STRING * string_make_direct( Interp *interp,
    const char *buffer /*NULLOK*/,
    UINTVAL len,
    ENCODING *encoding /*NN*/,
    CHARSET *charset /*NN*/,
    UINTVAL flags )
        __attribute__nonnull__(4)
        __attribute__nonnull__(5);

PARROT_API STRING * string_make_empty( Interp *interp,
    parrot_string_representation_t representation,
    UINTVAL capacity );

PARROT_API INTVAL string_max_bytes( Interp *interp,
    const STRING *s /*NN*/,
    INTVAL nchars )
        __attribute__nonnull__(2);

PARROT_API STRING* string_nprintf( Interp *interp,
    STRING *dest,
    INTVAL bytelen,
    const char *format /*NN*/,
    ... )
        __attribute__nonnull__(4);

PARROT_API INTVAL string_ord( Interp *interp,
    const STRING *s /*NULLOK*/,
    INTVAL idx );

PARROT_API void string_pin( Interp *interp, STRING *s /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API const char* string_primary_encoding_for_representation( Interp *interp,
    parrot_string_representation_t representation );

PARROT_API STRING* string_printf( Interp *interp,
    const char *format /*NN*/,
    ... )
        __attribute__nonnull__(2);

PARROT_API CHARSET * string_rep_compatible( Interp *interp,
    const STRING *a /*NN*/,
    const STRING *b /*NN*/,
    ENCODING **e /*NN*/ )
        __attribute__nonnull__(2)
        __attribute__nonnull__(3)
        __attribute__nonnull__(4);

PARROT_API STRING * string_repeat( Interp *interp,
    const STRING *s /*NN*/,
    UINTVAL num,
    STRING **d /*NULLOK*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * string_replace( Interp *interp,
    STRING *src /*NULLOK*/,
    INTVAL offset,
    INTVAL length,
    STRING *rep /*NULLOK*/,
    STRING **d /*NULLOK*/ );

PARROT_API STRING * string_set( Interp *interp,
    STRING *dest /*NULLOK*/,
    STRING *src /*NULLOK*/ );

PARROT_API PMC* string_split( Interp *interp,
    STRING *delim /*NN*/,
    STRING *str /*NN*/ )
        __attribute__nonnull__(2)
        __attribute__nonnull__(3);

PARROT_API INTVAL string_str_index( Interp *interp,
    const STRING *s /*NN*/,
    const STRING *s2 /*NN*/,
    INTVAL start )
        __attribute__nonnull__(2)
        __attribute__nonnull__(3);

PARROT_API STRING * string_substr( Interp *interp,
    STRING *src /*NN*/,
    INTVAL offset,
    INTVAL length,
    STRING **d /*NULLOK*/,
    int replace_dest )
        __attribute__nonnull__(2);

PARROT_API STRING * string_titlecase( Interp *interp,
    const STRING *s /*NULLOK*/ );

PARROT_API void string_titlecase_inplace( Interp *interp,
    STRING *s /*NULLOK*/ );

PARROT_API char * string_to_cstring( Interp *interp,
    const STRING * const s /* NULLOK */ );

PARROT_API INTVAL string_to_int( Interp *interp, const STRING *s /*NULLOK*/ );
PARROT_API FLOATVAL string_to_num( Interp *interp,
    const STRING *s /*NULLOK*/ );

PARROT_API STRING * string_unescape_cstring( Interp *interp,
    const char *cstring /*NN*/,
    char delimiter,
    const char *enc_char /*NULLOK*/ )
        __attribute__nonnull__(2);

PARROT_API void string_unpin( Interp *interp, STRING *s /*NN*/ )
        __attribute__nonnull__(2);

PARROT_API STRING * string_upcase( Interp *interp,
    const STRING *s /*NULLOK*/ );

PARROT_API void string_upcase_inplace( Interp *interp, STRING *s /*NULLOK*/ );
PARROT_API STRING* uint_to_str( Interp *interp,
    char *tc /*NN*/,
    UHUGEINTVAL num,
    char base,
    int minus )
        __attribute__nonnull__(2);
/* HEADERIZER END: src/string.c */
/* HEADERIZER BEGIN: src/string_primitives.c */
PARROT_API UINTVAL Parrot_char_digit_value( Interp *interp,
    UINTVAL character );

PARROT_API void string_fill_from_buffer( Interp *interp,
    const void *buffer,
    UINTVAL len,
    const char *encoding_name,
    STRING *s /*NULLOK*/ );

PARROT_API void string_set_data_directory( const char *dir );
PARROT_API Parrot_UInt4 string_unescape_one( Interp *interp,
    UINTVAL *offset /*NN*/,
    STRING *string )
        __attribute__nonnull__(2);
/* HEADERIZER END: src/string_primitives.c */

#endif /* PARROT_IN_CORE */
#endif /* PARROT_STRING_FUNCS_H_GUARD */

/*
 * Local variables:
 *   c-file-style: "parrot"
 * End:
 * vim: expandtab shiftwidth=4:
 */
