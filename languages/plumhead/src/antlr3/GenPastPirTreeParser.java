// $ANTLR 3.0b5 src/antlr3/GenPastPir.g 2007-01-26 21:49:23

  import java.util.regex.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class GenPastPirTreeParser extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROGRAM", "NOQUOTE_STRING", "STMTS", "SEA", "CODE_START", "CODE_END", "WS", "DOUBLEQUOTE_STRING", "SINGLEQUOTE_STRING", "ECHO", "IDENT", "SCALAR", "ARRAY", "INTEGER", "NUMBER", "MINUS", "PLUS", "MUL_OP", "ASSIGN_OP", "REL_OP", "IF", "ELSE", "';'", "'('", "')'", "'{'", "'}'"
    };
    public static final int CODE_START=8;
    public static final int MINUS=19;
    public static final int ARRAY=16;
    public static final int IDENT=14;
    public static final int WS=10;
    public static final int NUMBER=18;
    public static final int SINGLEQUOTE_STRING=12;
    public static final int MUL_OP=21;
    public static final int SEA=7;
    public static final int CODE_END=9;
    public static final int STMTS=6;
    public static final int PROGRAM=4;
    public static final int ASSIGN_OP=22;
    public static final int INTEGER=17;
    public static final int DOUBLEQUOTE_STRING=11;
    public static final int ECHO=13;
    public static final int ELSE=25;
    public static final int IF=24;
    public static final int EOF=-1;
    public static final int REL_OP=23;
    public static final int PLUS=20;
    public static final int NOQUOTE_STRING=5;
    public static final int SCALAR=15;

        public GenPastPirTreeParser(TreeNodeStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "src/antlr3/GenPastPir.g"; }


      // used for generating unique register names
      public static int reg_num = 200;



    // $ANTLR start gen_pir_past
    // src/antlr3/GenPastPir.g:28:1: gen_pir_past : ^( PROGRAM ( node["past_stmts"] )* ) ;
    public void gen_pir_past() throws RecognitionException {   
        try {
            // src/antlr3/GenPastPir.g:29:5: ( ^( PROGRAM ( node[\"past_stmts\"] )* ) )
            // src/antlr3/GenPastPir.g:29:5: ^( PROGRAM ( node[\"past_stmts\"] )* )
            {

                  System.out.println( 
                      "#!/usr/bin/env parrot                                             \n"
                    + "                                                                  \n"
                    + "# Do not edit this file.                                          \n"
                    + "# This file has been generated by GenPastPir.xsl                  \n"
                    + "                                                                  \n"
                    + ".sub 'php_init' :load :init                                       \n"
                    + "                                                                  \n"
                    + "  load_bytecode 'languages/plumhead/src/common/plumheadlib.pbc'   \n"
                    + "  load_bytecode 'PAST-pm.pbc'                                     \n"
                    + "  load_bytecode 'MIME/Base64.pbc'                                 \n"
                    + "  load_bytecode 'dumper.pbc'                                      \n"
                    + "  load_bytecode 'PGE.pbc'                                         \n"
                    + "  load_bytecode 'CGI/QueryHash.pbc'                               \n"
                    + "                                                                  \n"
                    + ".end                                                              \n"
                    + "                                                                  \n"
                    + ".sub plumhead :main                                               \n"
                    + "                                                                  \n"
                    + "    # look for subs in other namespaces                           \n"
                    + "    .local pmc parse_get_sub, parse_post_sub   \n"
                    + "    parse_get_sub  = get_global [ 'CGI'; 'QueryHash' ], 'parse_get'         \n"
                    + "    parse_post_sub = get_global [ 'CGI'; 'QueryHash' ], 'parse_post'        \n"
                    + "                                                                  \n"
                    + "    # the superglobals                                            \n"
                    + "    .local pmc superglobal_GET                                    \n"
                    + "    ( superglobal_GET ) = parse_get_sub()                         \n"
                    + "    set_global '_GET', superglobal_GET                            \n"
                    + "                                                                  \n"
                    + "    .local pmc superglobal_POST                                   \n"
                    + "    ( superglobal_POST ) = parse_post_sub()                       \n"
                    + "    set_global '_POST', superglobal_POST                          \n"
                    + "                                                                  \n"
                    + "    # The root node of PAST.                                      \n"
                    + "    .local pmc past_root                                          \n"
                    + "    past_root  = new 'PAST::Block'                                \n"
                    + "    past_root.init('name' => 'plumhead_main')                     \n"
                    + "                                                                  \n"
                    + "    .local pmc past_stmts                                         \n"
                    + "    past_stmts = new 'PAST::Stmts'                                \n"
                    + "                                                                  \n"
                    + "    .sym pmc past_temp                                            \n"
                    + "    .sym pmc past_if_op                                           \n"
                    + "                                                                  \n"
                  );
                
            match(input,PROGRAM,FOLLOW_PROGRAM_in_gen_pir_past75); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // src/antlr3/GenPastPir.g:76:16: ( node[\"past_stmts\"] )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);
                    if ( ((LA1_0>=NOQUOTE_STRING && LA1_0<=STMTS)||(LA1_0>=DOUBLEQUOTE_STRING && LA1_0<=ECHO)||LA1_0==SCALAR||(LA1_0>=NUMBER && LA1_0<=IF)) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // src/antlr3/GenPastPir.g:76:16: node[\"past_stmts\"]
                	    {
                	    pushFollow(FOLLOW_node_in_gen_pir_past77);
                	    node("past_stmts");
                	    _fsp--;


                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

                  System.out.println( 
                      "                                                                  \n"
                    + "                                                                  \n"
                    + "  past_root.'push'( past_stmts )                                  \n"
                    + "                                                                  \n"
                    + "    # '_dumper'(past_root, 'past')                                \n"
                    + "    # '_dumper'(superglobal_POST , 'superglobal_POST')            \n"
                    + "    # '_dumper'(superglobal_GET , 'superglobal_GET')              \n"
                    + "                                                                  \n"
                    + "    # .local pmc post                                             \n"
                    + "    # post = past_root.'compile'( 'target' => 'post' )            \n"
                    + "    # '_dumper'(post, 'post')                                     \n"
                    + "                                                                  \n"
                    + "    # .local pmc pir                                              \n"
                    + "    # pir = past_root.'compile'( 'target' => 'pir' )              \n"
                    + "    # print pir                                                   \n"
                    + "                                                                  \n"
                    + "    .local pmc eval_past                                          \n"
                    + "    eval_past = past_root.'compile'( )                            \n"
                    + "    eval_past()                                                   \n"
                    + "                                                                  \n"
                    + ".end                                                              \n"
                    + "                                                                  \n"
                  );
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
       }
        return ;
    }
    // $ANTLR end gen_pir_past


    // $ANTLR start node
    // src/antlr3/GenPastPir.g:105:1: node[String reg_mother] : ( ^( ECHO node["past_echo"] ) | NOQUOTE_STRING | SINGLEQUOTE_STRING | DOUBLEQUOTE_STRING | NUMBER | ^(infix= (PLUS|MINUS|MUL_OP) node[reg] node[reg] ) | ^( REL_OP node[reg] node[reg] ) | ^( IF node["past_if_op"] node["past_if_op"] ( node["past_if_op"] )? ) | ^( STMTS ( node[reg_stmts] )* ) | ^( ASSIGN_OP node[reg_assign] node[reg_assign] ) | SCALAR );
    public void node(String reg_mother) throws RecognitionException {   
        CommonTree infix=null;
        CommonTree NOQUOTE_STRING1=null;
        CommonTree SINGLEQUOTE_STRING2=null;
        CommonTree DOUBLEQUOTE_STRING3=null;
        CommonTree NUMBER4=null;
        CommonTree REL_OP5=null;
        CommonTree SCALAR6=null;

        try {
            // src/antlr3/GenPastPir.g:106:5: ( ^( ECHO node[\"past_echo\"] ) | NOQUOTE_STRING | SINGLEQUOTE_STRING | DOUBLEQUOTE_STRING | NUMBER | ^(infix= (PLUS|MINUS|MUL_OP) node[reg] node[reg] ) | ^( REL_OP node[reg] node[reg] ) | ^( IF node[\"past_if_op\"] node[\"past_if_op\"] ( node[\"past_if_op\"] )? ) | ^( STMTS ( node[reg_stmts] )* ) | ^( ASSIGN_OP node[reg_assign] node[reg_assign] ) | SCALAR )
            int alt4=11;
            switch ( input.LA(1) ) {
            case ECHO:
                alt4=1;
                break;
            case NOQUOTE_STRING:
                alt4=2;
                break;
            case SINGLEQUOTE_STRING:
                alt4=3;
                break;
            case DOUBLEQUOTE_STRING:
                alt4=4;
                break;
            case NUMBER:
                alt4=5;
                break;
            case MINUS:
            case PLUS:
            case MUL_OP:
                alt4=6;
                break;
            case REL_OP:
                alt4=7;
                break;
            case IF:
                alt4=8;
                break;
            case STMTS:
                alt4=9;
                break;
            case ASSIGN_OP:
                alt4=10;
                break;
            case SCALAR:
                alt4=11;
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("105:1: node[String reg_mother] : ( ^( ECHO node[\"past_echo\"] ) | NOQUOTE_STRING | SINGLEQUOTE_STRING | DOUBLEQUOTE_STRING | NUMBER | ^(infix= (PLUS|MINUS|MUL_OP) node[reg] node[reg] ) | ^( REL_OP node[reg] node[reg] ) | ^( IF node[\"past_if_op\"] node[\"past_if_op\"] ( node[\"past_if_op\"] )? ) | ^( STMTS ( node[reg_stmts] )* ) | ^( ASSIGN_OP node[reg_assign] node[reg_assign] ) | SCALAR );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // src/antlr3/GenPastPir.g:106:5: ^( ECHO node[\"past_echo\"] )
                    {

                          System.out.println( 
                              "                                                                  \n"
                            + "  # start of ECHO node                                            \n"
                            + "  .local pmc past_echo                                            \n"
                            + "  past_echo = new 'PAST::Op'                                      \n"
                            + "  past_echo.'attr'( 'name', 'echo', 1 )                           \n"
                          );
                        
                    match(input,ECHO,FOLLOW_ECHO_in_node109); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_node_in_node111);
                    node("past_echo");
                    _fsp--;


                    match(input, Token.UP, null); 

                          System.out.println( 
                              "                                                                  \n"
                            + "  " + reg_mother + ".'push'( past_echo )                    \n"
                            + "  # end of ECHO node                                              \n"
                          );
                        

                    }
                    break;
                case 2 :
                    // src/antlr3/GenPastPir.g:123:5: NOQUOTE_STRING
                    {
                    NOQUOTE_STRING1=(CommonTree)input.LT(1);
                    match(input,NOQUOTE_STRING,FOLLOW_NOQUOTE_STRING_in_node126); 

                          String noquote = NOQUOTE_STRING1.getText();
                          noquote = noquote.replace( "\n", "\\n" );
                          System.out.println( 
                              "                                                                  \n"
                            + "  # start of NOQUOTE_STRING                                       \n"
                            + "  .local string val                                               \n"
                            + "  val = \"" + noquote + "\"                                       \n"
                            + "  past_temp = new 'PAST::Val'                                     \n"
                            + "  .local pmc code_string                                          \n"
                            + "  code_string = new 'PGE::CodeString'                             \n"
                            + "  ( val ) = code_string.'escape'( val )                           \n"
                            + "      past_temp.'init'( 'name' => val, 'vtype' => '.Undef' )      \n"
                            + "  " + reg_mother + ".'push'( past_temp )                    \n"
                            + "  # end of NOQUOTE_STRING                                         \n"
                            + "                                                                  \n"
                          );
                        

                    }
                    break;
                case 3 :
                    // src/antlr3/GenPastPir.g:142:5: SINGLEQUOTE_STRING
                    {
                    SINGLEQUOTE_STRING2=(CommonTree)input.LT(1);
                    match(input,SINGLEQUOTE_STRING,FOLLOW_SINGLEQUOTE_STRING_in_node138); 

                          String singlequote = SINGLEQUOTE_STRING2.getText();
                          singlequote = singlequote.replace( "\n", "\\n" );
                          System.out.println( 
                              "                                                                  \n"
                            + "  # start of SINGLEQUOTE_STRING                                   \n"
                            + "  .local string val                                               \n"
                            + "  val = " + singlequote + "                                      \n"
                            + "  past_temp = new 'PAST::Val'                                     \n"
                            + "  .local pmc code_string                                          \n"
                            + "  code_string = new 'PGE::CodeString'                             \n"
                            + "  ( val ) = code_string.'escape'( val )                           \n"
                            + "      past_temp.'init'( 'name' => val, 'vtype' => '.Undef' )      \n"
                            + "  " + reg_mother + ".'push'( past_temp )                    \n"
                            + "  # end of SINGLEQUOTE_STRING                                                 \n"
                            + "                                                                  \n"
                          );
                        

                    }
                    break;
                case 4 :
                    // src/antlr3/GenPastPir.g:161:5: DOUBLEQUOTE_STRING
                    {
                    DOUBLEQUOTE_STRING3=(CommonTree)input.LT(1);
                    match(input,DOUBLEQUOTE_STRING,FOLLOW_DOUBLEQUOTE_STRING_in_node150); 

                          String doublequote = DOUBLEQUOTE_STRING3.getText();
                          doublequote = doublequote.replace( "\n", "\\n" );
                          System.out.println( 
                              "                                                                  \n"
                            + "  # start of DOUBLEQUOTE_STRING                                   \n"
                            + "  .local string val                                               \n"
                            + "  val = " + doublequote + "                                      \n"
                            + "  past_temp = new 'PAST::Val'                                     \n"
                            + "  .local pmc code_string                                          \n"
                            + "  code_string = new 'PGE::CodeString'                             \n"
                            + "  ( val ) = code_string.'escape'( val )                           \n"
                            + "      past_temp.'init'( 'name' => val, 'vtype' => '.Undef' )      \n"
                            + "  " + reg_mother + ".'push'( past_temp )                    \n"
                            + "  # end of DOUBLEQUOTE_STRING                                                 \n"
                            + "                                                                  \n"
                          );
                        

                    }
                    break;
                case 5 :
                    // src/antlr3/GenPastPir.g:180:5: NUMBER
                    {
                    NUMBER4=(CommonTree)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_node162); 

                          System.out.println( 
                              "                                                                  \n"
                            + "  # start of NUMBER                                               \n"
                            + "  past_temp = new 'PAST::Val'                                     \n"
                            + "      past_temp.'attr'( 'name', '" + NUMBER4.getText() + "', 1 )       \n"
                            + "      past_temp.'attr'( 'ctype', 'n+', 1 )                        \n"
                            + "      past_temp.'attr'( 'vtype', '.Float', 1 )                    \n"
                            + "  " + reg_mother + ".'push'( past_temp )                    \n"
                            + "  # end of NUMBER                                                 \n"
                          );
                        

                    }
                    break;
                case 6 :
                    // src/antlr3/GenPastPir.g:193:5: ^(infix= (PLUS|MINUS|MUL_OP) node[reg] node[reg] )
                    {

                          reg_num++;
                          String reg = "reg_" + reg_num;
                          System.out.print( 
                              "                                                                   \n"
                            + "    # entering PLUS | MINUS | MUL_OP                               \n"
                            + "      .sym pmc " + reg + "                                         \n"
                            + "      " + reg + " = new 'PAST::Op'                                 \n"
                          );
                        
                    infix=(CommonTree)input.LT(1);
                    if ( (input.LA(1)>=MINUS && input.LA(1)<=MUL_OP) ) {
                        input.consume();
                        errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_node186);    throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_node_in_node198);
                    node(reg);
                    _fsp--;

                    pushFollow(FOLLOW_node_in_node201);
                    node(reg);
                    _fsp--;


                    match(input, Token.UP, null); 

                          // Todo. This is not nice, handle pirops in Plumhead.g
                          String pirop = infix.getText();
                          if      ( pirop.equals( "+" ) )  { pirop = "n_add"; }
                          else if ( pirop.equals( "-" ) )  { pirop = "n_sub"; }
                          else if ( pirop.equals( "*" ) )  { pirop = "n_mul"; }
                          else if ( pirop.equals( "/" ) )  { pirop = "n_div"; }
                          else if ( pirop.equals( "%" ) ) { pirop = "n_mod"; }
                          
                          System.out.print( 
                              "  " + reg + ".'attr'( 'pirop', '" + pirop + "' , 1 )               \n"
                            + "  " + reg_mother + ".'push'( " + reg + " )                   \n"
                            + "    # leaving PLUS | MINUS | MUL_OP                                \n"
                          );
                        

                    }
                    break;
                case 7 :
                    // src/antlr3/GenPastPir.g:219:5: ^( REL_OP node[reg] node[reg] )
                    {

                          reg_num++;
                          String reg = "reg_" + reg_num;
                          System.out.print( 
                              "                                                                   \n"
                            + "    # entering REL_OP                                              \n"
                            + "      .sym pmc " + reg + "                                         \n"
                            + "      " + reg + " = new 'PAST::Op'                                 \n"
                          );
                        
                    REL_OP5=(CommonTree)input.LT(1);
                    match(input,REL_OP,FOLLOW_REL_OP_in_node224); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_node_in_node226);
                    node(reg);
                    _fsp--;

                    pushFollow(FOLLOW_node_in_node229);
                    node(reg);
                    _fsp--;


                    match(input, Token.UP, null); 

                          // Todo. This is not nice, handle pirops in Plumhead.g
                          String name = REL_OP5.getText();
                          if      ( name.equals( "==" ) )  { name = "eq"; }
                          else if ( name.equals( "!=" ) )  { name = "ne"; }
                          name = "infix:" + name;
                          
                          System.out.print( 
                              "  " + reg + ".'attr'( 'name', '" + name + "' , 1 )               \n"
                            + "  " + reg_mother + ".'push'( " + reg + " )                 \n"
                            + "    # leaving REL_OL                                              \n"
                          );
                        

                    }
                    break;
                case 8 :
                    // src/antlr3/GenPastPir.g:243:5: ^( IF node[\"past_if_op\"] node[\"past_if_op\"] ( node[\"past_if_op\"] )? )
                    {

                          reg_num++;
                          String reg_exp   = "reg_expression_" + reg_num;
                          System.out.print( 
                              "                                                                   \n"
                            + "  # entering IF                                                    \n"
                            + "      past_if_op = new 'PAST::Op'                                  \n"
                            + "      past_if_op.'attr'( 'pasttype', 'if' , 1 )                    \n"
                            + "        .sym pmc " + reg_exp + "                                   \n"
                            + "        " + reg_exp + " = new 'PAST::Block'                        \n"
                            + "                                                                   \n"
                          );
                        
                    match(input,IF,FOLLOW_IF_in_node252); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_node_in_node254);
                    node("past_if_op");
                    _fsp--;

                    pushFollow(FOLLOW_node_in_node257);
                    node("past_if_op");
                    _fsp--;

                    // src/antlr3/GenPastPir.g:256:49: ( node[\"past_if_op\"] )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);
                    if ( ((LA2_0>=NOQUOTE_STRING && LA2_0<=STMTS)||(LA2_0>=DOUBLEQUOTE_STRING && LA2_0<=ECHO)||LA2_0==SCALAR||(LA2_0>=NUMBER && LA2_0<=IF)) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // src/antlr3/GenPastPir.g:256:49: node[\"past_if_op\"]
                            {
                            pushFollow(FOLLOW_node_in_node260);
                            node("past_if_op");
                            _fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                          System.out.print( 
                              "                                                                   \n"
                            + "  " + reg_mother + ".'push'( past_if_op )                     \n"
                            + "  # leaving IF                                                     \n"
                          );
                        

                    }
                    break;
                case 9 :
                    // src/antlr3/GenPastPir.g:264:5: ^( STMTS ( node[reg_stmts] )* )
                    {

                          reg_num++;
                          String reg_stmts = "reg_stmts_" + reg_num;
                          System.out.print( 
                              "                                                                   \n"
                            + "    # entering STMTS                                               \n"
                            + "        .sym pmc " + reg_stmts + "                                 \n"
                            + "        " + reg_stmts + " = new 'PAST::Stmts'                      \n"
                          );
                        
                    match(input,STMTS,FOLLOW_STMTS_in_node284); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // src/antlr3/GenPastPir.g:274:14: ( node[reg_stmts] )*
                        loop3:
                        do {
                            int alt3=2;
                            int LA3_0 = input.LA(1);
                            if ( ((LA3_0>=NOQUOTE_STRING && LA3_0<=STMTS)||(LA3_0>=DOUBLEQUOTE_STRING && LA3_0<=ECHO)||LA3_0==SCALAR||(LA3_0>=NUMBER && LA3_0<=IF)) ) {
                                alt3=1;
                            }


                            switch (alt3) {
                        	case 1 :
                        	    // src/antlr3/GenPastPir.g:274:14: node[reg_stmts]
                        	    {
                        	    pushFollow(FOLLOW_node_in_node286);
                        	    node(reg_stmts);
                        	    _fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop3;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }

                          System.out.print( 
                              "  " + reg_mother + ".'push'( " + reg_stmts + " )             \n"
                            + "  # leaving 'STMTS node*'                                          \n"
                          );
                        

                    }
                    break;
                case 10 :
                    // src/antlr3/GenPastPir.g:281:5: ^( ASSIGN_OP node[reg_assign] node[reg_assign] )
                    {

                          reg_num++;
                          String reg_assign = "reg_assign_" + reg_num;
                          System.out.print( 
                              "                                                                   \n"
                            + "    # entering ASSIGN_OP                                           \n"
                            + "    .sym pmc " + reg_assign + "                                    \n"
                            + "    " + reg_assign + " = new 'PAST::Op'                            \n"
                          );
                        
                    match(input,ASSIGN_OP,FOLLOW_ASSIGN_OP_in_node310); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_node_in_node312);
                    node(reg_assign);
                    _fsp--;

                    pushFollow(FOLLOW_node_in_node315);
                    node(reg_assign);
                    _fsp--;


                    match(input, Token.UP, null); 

                          System.out.print( 
                              "  " + reg_assign + ".'attr'( 'name', 'infix:=' , 1 )               \n"
                            + "  " + reg_assign + ".'attr'( 'pasttype', 'assign' , 1 )            \n"
                            + "  " + reg_mother + ".'push'( " + reg_assign + " )            \n"
                            + "  # leaving ASSIGN_OP                                              \n"
                          );
                        

                    }
                    break;
                case 11 :
                    // src/antlr3/GenPastPir.g:300:5: SCALAR
                    {
                    SCALAR6=(CommonTree)input.LT(1);
                    match(input,SCALAR,FOLLOW_SCALAR_in_node330); 

                          System.out.println( 
                              "                                                                  \n"
                            + "  # entering SCALAR                                               \n"
                            + "  past_temp = new 'PAST::Var'                                     \n"
                            + "      past_temp.'init'( 'name' => '" + SCALAR6.getText() + "', 'viviself' => '.Undef', 'islvalue' => 1 )      \n"
                            + "  " + reg_mother + ".'push'( past_temp )                    \n"
                            + "  # end of NUMBER                                                 \n"
                          );
                        

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
       }
        return ;
    }
    // $ANTLR end node


 

    public static final BitSet FOLLOW_PROGRAM_in_gen_pir_past75 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_gen_pir_past77 = new BitSet(new long[]{0x0000000001FCB868L});
    public static final BitSet FOLLOW_ECHO_in_node109 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node111 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOQUOTE_STRING_in_node126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SINGLEQUOTE_STRING_in_node138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLEQUOTE_STRING_in_node150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_node162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_node186 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node198 = new BitSet(new long[]{0x0000000001FCB860L});
    public static final BitSet FOLLOW_node_in_node201 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REL_OP_in_node224 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node226 = new BitSet(new long[]{0x0000000001FCB860L});
    public static final BitSet FOLLOW_node_in_node229 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_node252 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node254 = new BitSet(new long[]{0x0000000001FCB860L});
    public static final BitSet FOLLOW_node_in_node257 = new BitSet(new long[]{0x0000000001FCB868L});
    public static final BitSet FOLLOW_node_in_node260 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STMTS_in_node284 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node286 = new BitSet(new long[]{0x0000000001FCB868L});
    public static final BitSet FOLLOW_ASSIGN_OP_in_node310 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_node_in_node312 = new BitSet(new long[]{0x0000000001FCB860L});
    public static final BitSet FOLLOW_node_in_node315 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SCALAR_in_node330 = new BitSet(new long[]{0x0000000000000002L});

}