// $ANTLR 3.0b4 grammar/antlr_3/bc.g 2006-08-24 22:45:18

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class BcParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARRAY", "FUNCTION", "PRINT", "PROGRAM", "STMTS", "VAR", "NEWLINE", "STRING", "LETTER", "INTEGER", "NUMBER", "MINUS", "PLUS", "MUL_OP", "ASSIGN_OP", "REL_OP", "INCR", "DECR", "Define", "Break", "Quit", "Length", "Return", "For", "If", "While", "Sqrt", "Scale", "Ibase", "Obase", "Auto", "ML_COMMENT", "WS", "';'", "'('", "')'", "'{'", "'}'", "'['", "']'", "','"
    };
    public static final int LETTER=12;
    public static final int MINUS=15;
    public static final int INCR=20;
    public static final int ARRAY=4;
    public static final int If=28;
    public static final int WS=36;
    public static final int DECR=21;
    public static final int NUMBER=14;
    public static final int Break=23;
    public static final int Sqrt=30;
    public static final int STRING=11;
    public static final int Ibase=32;
    public static final int Scale=31;
    public static final int MUL_OP=17;
    public static final int Auto=34;
    public static final int Define=22;
    public static final int Quit=24;
    public static final int PROGRAM=7;
    public static final int STMTS=8;
    public static final int NEWLINE=10;
    public static final int VAR=9;
    public static final int INTEGER=13;
    public static final int ASSIGN_OP=18;
    public static final int PRINT=6;
    public static final int Obase=33;
    public static final int FUNCTION=5;
    public static final int While=29;
    public static final int EOF=-1;
    public static final int REL_OP=19;
    public static final int ML_COMMENT=35;
    public static final int PLUS=16;
    public static final int Length=25;
    public static final int For=27;
    public static final int Return=26;

        public BcParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "grammar/antlr_3/bc.g"; }


    public static class program_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start program
    // grammar/antlr_3/bc.g:91:1: program : ( input_item )+ Quit NEWLINE -> ^( PROGRAM ^( ASSIGN_OP ^( VAR LETTER["a"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["b"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["c"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["d"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["e"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["f"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["g"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["h"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["i"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["j"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["k"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["l"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["m"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["n"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["o"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["p"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["q"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["r"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["s"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["t"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["u"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["v"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["w"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["x"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["y"] ) NUMBER["0"] ) ^( ASSIGN_OP ^( VAR LETTER["z"] ) NUMBER["0"] ) ( input_item )+ ) ;
    public program_return program() throws RecognitionException {   
        program_return retval = new program_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token Quit2=null;
        Token NEWLINE3=null;
        input_item_return input_item1 = null;

        List list_input_item=new ArrayList();
        List list_Quit=new ArrayList();
        List list_NEWLINE=new ArrayList();
        CommonTree Quit2_tree=null;
        CommonTree NEWLINE3_tree=null;

        try {
            // grammar/antlr_3/bc.g:92:5: ( ( input_item )+ Quit NEWLINE -> ^( PROGRAM ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"b\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"c\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"d\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"e\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"f\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"g\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"h\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"i\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"j\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"k\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"l\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"m\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"n\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"o\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"p\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"q\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"r\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"s\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"t\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"u\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"v\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"w\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"x\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"y\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"z\"] ) NUMBER[\"0\"] ) ( input_item )+ ) )
            // grammar/antlr_3/bc.g:92:5: ( input_item )+ Quit NEWLINE
            {
            // grammar/antlr_3/bc.g:92:5: ( input_item )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);
                if ( ((LA1_0>=NEWLINE && LA1_0<=LETTER)||(LA1_0>=NUMBER && LA1_0<=MINUS)||(LA1_0>=INCR && LA1_0<=Define)||LA1_0==If||(LA1_0>=37 && LA1_0<=38)||LA1_0==40) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:92:5: input_item
            	    {
            	    pushFollow(FOLLOW_input_item_in_program602);
            	    input_item1=input_item();
            	    _fsp--;

            	    list_input_item.add(input_item1.tree);

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            Quit2=(Token)input.LT(1);
            match(input,Quit,FOLLOW_Quit_in_program605); 
            list_Quit.add(Quit2);

            NEWLINE3=(Token)input.LT(1);
            match(input,NEWLINE,FOLLOW_NEWLINE_in_program607); 
            list_NEWLINE.add(NEWLINE3);


            // AST REWRITE
            int i_0 = 0;
            retval.tree = root_0;
            root_0 = (CommonTree)adaptor.nil();
            // 92:30: -> ^( PROGRAM ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"b\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"c\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"d\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"e\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"f\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"g\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"h\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"i\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"j\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"k\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"l\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"m\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"n\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"o\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"p\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"q\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"r\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"s\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"t\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"u\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"v\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"w\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"x\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"y\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"z\"] ) NUMBER[\"0\"] ) ( input_item )+ )
            {
                // grammar/antlr_3/bc.g:92:33: ^( PROGRAM ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"b\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"c\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"d\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"e\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"f\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"g\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"h\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"i\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"j\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"k\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"l\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"m\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"n\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"o\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"p\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"q\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"r\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"s\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"t\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"u\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"v\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"w\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"x\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"y\"] ) NUMBER[\"0\"] ) ^( ASSIGN_OP ^( VAR LETTER[\"z\"] ) NUMBER[\"0\"] ) ( input_item )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PROGRAM, "PROGRAM"), root_1);

                // grammar/antlr_3/bc.g:93:36: ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:93:49: ^( VAR LETTER[\"a\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "a"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:94:36: ^( ASSIGN_OP ^( VAR LETTER[\"b\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:94:49: ^( VAR LETTER[\"b\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "b"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:95:36: ^( ASSIGN_OP ^( VAR LETTER[\"c\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:95:49: ^( VAR LETTER[\"c\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "c"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:96:36: ^( ASSIGN_OP ^( VAR LETTER[\"d\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:96:49: ^( VAR LETTER[\"d\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "d"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:97:36: ^( ASSIGN_OP ^( VAR LETTER[\"e\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:97:49: ^( VAR LETTER[\"e\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "e"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:98:36: ^( ASSIGN_OP ^( VAR LETTER[\"f\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:98:49: ^( VAR LETTER[\"f\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "f"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:99:36: ^( ASSIGN_OP ^( VAR LETTER[\"g\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:99:49: ^( VAR LETTER[\"g\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "g"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:100:36: ^( ASSIGN_OP ^( VAR LETTER[\"h\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:100:49: ^( VAR LETTER[\"h\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "h"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:101:36: ^( ASSIGN_OP ^( VAR LETTER[\"i\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:101:49: ^( VAR LETTER[\"i\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "i"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:102:36: ^( ASSIGN_OP ^( VAR LETTER[\"j\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:102:49: ^( VAR LETTER[\"j\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "j"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:103:36: ^( ASSIGN_OP ^( VAR LETTER[\"k\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:103:49: ^( VAR LETTER[\"k\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "k"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:104:36: ^( ASSIGN_OP ^( VAR LETTER[\"l\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:104:49: ^( VAR LETTER[\"l\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "l"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:105:36: ^( ASSIGN_OP ^( VAR LETTER[\"m\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:105:49: ^( VAR LETTER[\"m\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "m"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:106:36: ^( ASSIGN_OP ^( VAR LETTER[\"n\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:106:49: ^( VAR LETTER[\"n\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "n"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:107:36: ^( ASSIGN_OP ^( VAR LETTER[\"o\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:107:49: ^( VAR LETTER[\"o\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "o"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:108:36: ^( ASSIGN_OP ^( VAR LETTER[\"p\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:108:49: ^( VAR LETTER[\"p\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "p"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:109:36: ^( ASSIGN_OP ^( VAR LETTER[\"q\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:109:49: ^( VAR LETTER[\"q\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "q"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:110:36: ^( ASSIGN_OP ^( VAR LETTER[\"r\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:110:49: ^( VAR LETTER[\"r\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "r"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:111:36: ^( ASSIGN_OP ^( VAR LETTER[\"s\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:111:49: ^( VAR LETTER[\"s\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "s"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:112:36: ^( ASSIGN_OP ^( VAR LETTER[\"t\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:112:49: ^( VAR LETTER[\"t\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "t"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:113:36: ^( ASSIGN_OP ^( VAR LETTER[\"u\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:113:49: ^( VAR LETTER[\"u\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "u"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:114:36: ^( ASSIGN_OP ^( VAR LETTER[\"v\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:114:49: ^( VAR LETTER[\"v\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "v"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:115:36: ^( ASSIGN_OP ^( VAR LETTER[\"w\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:115:49: ^( VAR LETTER[\"w\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "w"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:116:36: ^( ASSIGN_OP ^( VAR LETTER[\"x\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:116:49: ^( VAR LETTER[\"x\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "x"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:117:36: ^( ASSIGN_OP ^( VAR LETTER[\"y\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:117:49: ^( VAR LETTER[\"y\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "y"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:118:36: ^( ASSIGN_OP ^( VAR LETTER[\"z\"] ) NUMBER[\"0\"] )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_2);

                // grammar/antlr_3/bc.g:118:49: ^( VAR LETTER[\"z\"] )
                {
                CommonTree root_3 = (CommonTree)adaptor.nil();
                root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                adaptor.addChild(root_3, adaptor.create(LETTER, "z"));

                adaptor.addChild(root_2, root_3);
                }
                adaptor.addChild(root_2, adaptor.create(NUMBER, "0"));

                adaptor.addChild(root_1, root_2);
                }
                // grammar/antlr_3/bc.g:119:36: ( input_item )+
                {
                int n_1 = list_input_item == null ? 0 : list_input_item.size();
                 


                if ( n_1==0 ) throw new RuntimeException("Must have more than one element for (...)+ loops");
                for (int i_1=0; i_1<n_1; i_1++) {
                    adaptor.addChild(root_1, list_input_item.get(i_1));

                }
                }

                adaptor.addChild(root_0, root_1);
                }

            }



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end program

    public static class input_item_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start input_item
    // grammar/antlr_3/bc.g:123:1: input_item : ( semicolon_list NEWLINE! | function );
    public input_item_return input_item() throws RecognitionException {   
        input_item_return retval = new input_item_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NEWLINE5=null;
        semicolon_list_return semicolon_list4 = null;

        function_return function6 = null;


        CommonTree NEWLINE5_tree=null;

        try {
            // grammar/antlr_3/bc.g:124:5: ( semicolon_list NEWLINE! | function )
            int alt2=2;
            int LA2_0 = input.LA(1);
            if ( ((LA2_0>=NEWLINE && LA2_0<=LETTER)||(LA2_0>=NUMBER && LA2_0<=MINUS)||(LA2_0>=INCR && LA2_0<=DECR)||LA2_0==If||(LA2_0>=37 && LA2_0<=38)||LA2_0==40) ) {
                alt2=1;
            }
            else if ( (LA2_0==Define) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("123:1: input_item : ( semicolon_list NEWLINE! | function );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // grammar/antlr_3/bc.g:124:5: semicolon_list NEWLINE!
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_semicolon_list_in_input_item2026);
                    semicolon_list4=semicolon_list();
                    _fsp--;

                    adaptor.addChild(root_0, semicolon_list4.tree);
                    NEWLINE5=(Token)input.LT(1);
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_input_item2028); 

                    }
                    break;
                case 2 :
                    // grammar/antlr_3/bc.g:126:5: function
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_function_in_input_item2041);
                    function6=function();
                    _fsp--;

                    adaptor.addChild(root_0, function6.tree);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end input_item

    public static class semicolon_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start semicolon_list
    // grammar/antlr_3/bc.g:129:1: semicolon_list : ( statement )? ( ';'! ( statement )? )* ;
    public semicolon_list_return semicolon_list() throws RecognitionException {   
        semicolon_list_return retval = new semicolon_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal8=null;
        statement_return statement7 = null;

        statement_return statement9 = null;


        CommonTree char_literal8_tree=null;

        try {
            // grammar/antlr_3/bc.g:130:5: ( ( statement )? ( ';'! ( statement )? )* )
            // grammar/antlr_3/bc.g:130:5: ( statement )? ( ';'! ( statement )? )*
            {
            root_0 = (CommonTree)adaptor.nil();

            // grammar/antlr_3/bc.g:130:5: ( statement )?
            int alt3=2;
            int LA3_0 = input.LA(1);
            if ( ((LA3_0>=STRING && LA3_0<=LETTER)||(LA3_0>=NUMBER && LA3_0<=MINUS)||(LA3_0>=INCR && LA3_0<=DECR)||LA3_0==If||LA3_0==38||LA3_0==40) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // grammar/antlr_3/bc.g:130:5: statement
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_semicolon_list2055);
                    statement7=statement();
                    _fsp--;

                    adaptor.addChild(root_1, statement7.tree);

                    adaptor.addChild(root_0, root_1);

                    }
                    break;

            }

            // grammar/antlr_3/bc.g:130:16: ( ';'! ( statement )? )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);
                if ( (LA5_0==37) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:130:18: ';'! ( statement )?
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    char_literal8=(Token)input.LT(1);
            	    match(input,37,FOLLOW_37_in_semicolon_list2060); 
            	    // grammar/antlr_3/bc.g:130:23: ( statement )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);
            	    if ( ((LA4_0>=STRING && LA4_0<=LETTER)||(LA4_0>=NUMBER && LA4_0<=MINUS)||(LA4_0>=INCR && LA4_0<=DECR)||LA4_0==If||LA4_0==38||LA4_0==40) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // grammar/antlr_3/bc.g:130:23: statement
            	            {
            	            CommonTree root_2 = (CommonTree)adaptor.nil();

            	            pushFollow(FOLLOW_statement_in_semicolon_list2063);
            	            statement9=statement();
            	            _fsp--;

            	            adaptor.addChild(root_2, statement9.tree);

            	            adaptor.addChild(root_1, root_2);

            	            }
            	            break;

            	    }


            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end semicolon_list

    public static class statement_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start statement_list
    // grammar/antlr_3/bc.g:133:1: statement_list : ( statement | NEWLINE! | ';'! )* ;
    public statement_list_return statement_list() throws RecognitionException {   
        statement_list_return retval = new statement_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NEWLINE11=null;
        Token char_literal12=null;
        statement_return statement10 = null;


        CommonTree NEWLINE11_tree=null;
        CommonTree char_literal12_tree=null;

        try {
            // grammar/antlr_3/bc.g:134:5: ( ( statement | NEWLINE! | ';'! )* )
            // grammar/antlr_3/bc.g:134:5: ( statement | NEWLINE! | ';'! )*
            {
            root_0 = (CommonTree)adaptor.nil();

            // grammar/antlr_3/bc.g:134:5: ( statement | NEWLINE! | ';'! )*
            loop6:
            do {
                int alt6=4;
                switch ( input.LA(1) ) {
                case STRING:
                case LETTER:
                case NUMBER:
                case MINUS:
                case INCR:
                case DECR:
                case If:
                case 38:
                case 40:
                    alt6=1;
                    break;
                case NEWLINE:
                    alt6=2;
                    break;
                case 37:
                    alt6=3;
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:134:7: statement
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    pushFollow(FOLLOW_statement_in_statement_list2087);
            	    statement10=statement();
            	    _fsp--;

            	    adaptor.addChild(root_1, statement10.tree);

            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;
            	case 2 :
            	    // grammar/antlr_3/bc.g:134:19: NEWLINE!
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    NEWLINE11=(Token)input.LT(1);
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement_list2091); 

            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;
            	case 3 :
            	    // grammar/antlr_3/bc.g:134:30: ';'!
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    char_literal12=(Token)input.LT(1);
            	    match(input,37,FOLLOW_37_in_statement_list2096); 

            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end statement_list

    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start statement
    // grammar/antlr_3/bc.g:138:1: statement : ( named_expression ASSIGN_OP^^ expression | expression -> ^( PRINT expression ) ^( PRINT NEWLINE ) | STRING -> ^( PRINT STRING ) | If '(' relational_expression ')' statement -> ^( If relational_expression ^( STMTS statement ) ) | '{'! statement_list '}'! );
    public statement_return statement() throws RecognitionException {   
        statement_return retval = new statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSIGN_OP14=null;
        Token STRING17=null;
        Token If18=null;
        Token char_literal19=null;
        Token char_literal21=null;
        Token char_literal23=null;
        Token char_literal25=null;
        named_expression_return named_expression13 = null;

        expression_return expression15 = null;

        expression_return expression16 = null;

        relational_expression_return relational_expression20 = null;

        statement_return statement22 = null;

        statement_list_return statement_list24 = null;

        List list_statement=new ArrayList();
        List list_relational_expression=new ArrayList();
        List list_expression=new ArrayList();
        List list_38=new ArrayList();
        List list_If=new ArrayList();
        List list_39=new ArrayList();
        List list_STRING=new ArrayList();
        CommonTree ASSIGN_OP14_tree=null;
        CommonTree STRING17_tree=null;
        CommonTree If18_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree char_literal21_tree=null;
        CommonTree char_literal23_tree=null;
        CommonTree char_literal25_tree=null;

        try {
            // grammar/antlr_3/bc.g:139:5: ( named_expression ASSIGN_OP^^ expression | expression -> ^( PRINT expression ) ^( PRINT NEWLINE ) | STRING -> ^( PRINT STRING ) | If '(' relational_expression ')' statement -> ^( If relational_expression ^( STMTS statement ) ) | '{'! statement_list '}'! )
            int alt7=5;
            switch ( input.LA(1) ) {
            case LETTER:
                int LA7_1 = input.LA(2);
                if ( (LA7_1==ASSIGN_OP) ) {
                    alt7=1;
                }
                else if ( ((LA7_1>=NEWLINE && LA7_1<=LETTER)||(LA7_1>=NUMBER && LA7_1<=MUL_OP)||(LA7_1>=INCR && LA7_1<=DECR)||LA7_1==If||(LA7_1>=37 && LA7_1<=38)||(LA7_1>=40 && LA7_1<=41)) ) {
                    alt7=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("138:1: statement : ( named_expression ASSIGN_OP^^ expression | expression -> ^( PRINT expression ) ^( PRINT NEWLINE ) | STRING -> ^( PRINT STRING ) | If '(' relational_expression ')' statement -> ^( If relational_expression ^( STMTS statement ) ) | '{'! statement_list '}'! );", 7, 1, input);

                    throw nvae;
                }
                break;
            case NUMBER:
            case MINUS:
            case INCR:
            case DECR:
            case 38:
                alt7=2;
                break;
            case STRING:
                alt7=3;
                break;
            case If:
                alt7=4;
                break;
            case 40:
                alt7=5;
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("138:1: statement : ( named_expression ASSIGN_OP^^ expression | expression -> ^( PRINT expression ) ^( PRINT NEWLINE ) | STRING -> ^( PRINT STRING ) | If '(' relational_expression ')' statement -> ^( If relational_expression ^( STMTS statement ) ) | '{'! statement_list '}'! );", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // grammar/antlr_3/bc.g:139:5: named_expression ASSIGN_OP^^ expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_named_expression_in_statement2114);
                    named_expression13=named_expression();
                    _fsp--;

                    adaptor.addChild(root_0, named_expression13.tree);
                    ASSIGN_OP14=(Token)input.LT(1);
                    match(input,ASSIGN_OP,FOLLOW_ASSIGN_OP_in_statement2116); 
                    ASSIGN_OP14_tree = (CommonTree)adaptor.create(ASSIGN_OP14);
                    root_0 = (CommonTree)adaptor.becomeRoot(ASSIGN_OP14_tree, root_0);

                    pushFollow(FOLLOW_expression_in_statement2119);
                    expression15=expression();
                    _fsp--;

                    adaptor.addChild(root_0, expression15.tree);

                    }
                    break;
                case 2 :
                    // grammar/antlr_3/bc.g:141:5: expression
                    {
                    pushFollow(FOLLOW_expression_in_statement2131);
                    expression16=expression();
                    _fsp--;

                    list_expression.add(expression16.tree);

                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 141:16: -> ^( PRINT expression ) ^( PRINT NEWLINE )
                    {
                        // grammar/antlr_3/bc.g:141:19: ^( PRINT expression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PRINT, "PRINT"), root_1);

                        adaptor.addChild(root_1, list_expression.get(i_0));

                        adaptor.addChild(root_0, root_1);
                        }
                        // grammar/antlr_3/bc.g:141:41: ^( PRINT NEWLINE )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PRINT, "PRINT"), root_1);

                        adaptor.addChild(root_1, adaptor.create(NEWLINE, "NEWLINE"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 3 :
                    // grammar/antlr_3/bc.g:143:5: STRING
                    {
                    STRING17=(Token)input.LT(1);
                    match(input,STRING,FOLLOW_STRING_in_statement2161); 
                    list_STRING.add(STRING17);


                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 143:12: -> ^( PRINT STRING )
                    {
                        // grammar/antlr_3/bc.g:143:15: ^( PRINT STRING )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(PRINT, "PRINT"), root_1);

                        adaptor.addChild(root_1, (Token)list_STRING.get(i_0));

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 4 :
                    // grammar/antlr_3/bc.g:145:5: If '(' relational_expression ')' statement
                    {
                    If18=(Token)input.LT(1);
                    match(input,If,FOLLOW_If_in_statement2184); 
                    list_If.add(If18);

                    char_literal19=(Token)input.LT(1);
                    match(input,38,FOLLOW_38_in_statement2186); 
                    list_38.add(char_literal19);

                    pushFollow(FOLLOW_relational_expression_in_statement2188);
                    relational_expression20=relational_expression();
                    _fsp--;

                    list_relational_expression.add(relational_expression20.tree);
                    char_literal21=(Token)input.LT(1);
                    match(input,39,FOLLOW_39_in_statement2190); 
                    list_39.add(char_literal21);

                    pushFollow(FOLLOW_statement_in_statement2192);
                    statement22=statement();
                    _fsp--;

                    list_statement.add(statement22.tree);

                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 145:48: -> ^( If relational_expression ^( STMTS statement ) )
                    {
                        // grammar/antlr_3/bc.g:145:51: ^( If relational_expression ^( STMTS statement ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((Token)list_If.get(i_0), root_1);

                        adaptor.addChild(root_1, list_relational_expression.get(i_0));
                        // grammar/antlr_3/bc.g:145:79: ^( STMTS statement )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(STMTS, "STMTS"), root_2);

                        adaptor.addChild(root_2, list_statement.get(i_0));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 5 :
                    // grammar/antlr_3/bc.g:147:5: '{'! statement_list '}'!
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal23=(Token)input.LT(1);
                    match(input,40,FOLLOW_40_in_statement2223); 
                    pushFollow(FOLLOW_statement_list_in_statement2226);
                    statement_list24=statement_list();
                    _fsp--;

                    adaptor.addChild(root_0, statement_list24.tree);
                    char_literal25=(Token)input.LT(1);
                    match(input,41,FOLLOW_41_in_statement2228); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end statement

    public static class function_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start function
    // grammar/antlr_3/bc.g:151:1: function : Define LETTER '(' ( parameter_list )? ')' '{' NEWLINE ( auto_define_list )? statement_list '}' -> ^( FUNCTION LETTER ) ;
    public function_return function() throws RecognitionException {   
        function_return retval = new function_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token Define26=null;
        Token LETTER27=null;
        Token char_literal28=null;
        Token char_literal30=null;
        Token char_literal31=null;
        Token NEWLINE32=null;
        Token char_literal35=null;
        parameter_list_return parameter_list29 = null;

        auto_define_list_return auto_define_list33 = null;

        statement_list_return statement_list34 = null;

        List list_statement_list=new ArrayList();
        List list_parameter_list=new ArrayList();
        List list_auto_define_list=new ArrayList();
        List list_LETTER=new ArrayList();
        List list_41=new ArrayList();
        List list_38=new ArrayList();
        List list_Define=new ArrayList();
        List list_40=new ArrayList();
        List list_39=new ArrayList();
        List list_NEWLINE=new ArrayList();
        CommonTree Define26_tree=null;
        CommonTree LETTER27_tree=null;
        CommonTree char_literal28_tree=null;
        CommonTree char_literal30_tree=null;
        CommonTree char_literal31_tree=null;
        CommonTree NEWLINE32_tree=null;
        CommonTree char_literal35_tree=null;

        try {
            // grammar/antlr_3/bc.g:152:5: ( Define LETTER '(' ( parameter_list )? ')' '{' NEWLINE ( auto_define_list )? statement_list '}' -> ^( FUNCTION LETTER ) )
            // grammar/antlr_3/bc.g:152:5: Define LETTER '(' ( parameter_list )? ')' '{' NEWLINE ( auto_define_list )? statement_list '}'
            {
            Define26=(Token)input.LT(1);
            match(input,Define,FOLLOW_Define_in_function2243); 
            list_Define.add(Define26);

            LETTER27=(Token)input.LT(1);
            match(input,LETTER,FOLLOW_LETTER_in_function2245); 
            list_LETTER.add(LETTER27);

            char_literal28=(Token)input.LT(1);
            match(input,38,FOLLOW_38_in_function2247); 
            list_38.add(char_literal28);

            // grammar/antlr_3/bc.g:152:23: ( parameter_list )?
            int alt8=2;
            int LA8_0 = input.LA(1);
            if ( (LA8_0==LETTER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // grammar/antlr_3/bc.g:152:23: parameter_list
                    {
                    pushFollow(FOLLOW_parameter_list_in_function2249);
                    parameter_list29=parameter_list();
                    _fsp--;

                    list_parameter_list.add(parameter_list29.tree);

                    }
                    break;

            }

            char_literal30=(Token)input.LT(1);
            match(input,39,FOLLOW_39_in_function2252); 
            list_39.add(char_literal30);

            char_literal31=(Token)input.LT(1);
            match(input,40,FOLLOW_40_in_function2254); 
            list_40.add(char_literal31);

            NEWLINE32=(Token)input.LT(1);
            match(input,NEWLINE,FOLLOW_NEWLINE_in_function2256); 
            list_NEWLINE.add(NEWLINE32);

            // grammar/antlr_3/bc.g:152:55: ( auto_define_list )?
            int alt9=2;
            int LA9_0 = input.LA(1);
            if ( (LA9_0==Auto) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // grammar/antlr_3/bc.g:152:55: auto_define_list
                    {
                    pushFollow(FOLLOW_auto_define_list_in_function2258);
                    auto_define_list33=auto_define_list();
                    _fsp--;

                    list_auto_define_list.add(auto_define_list33.tree);

                    }
                    break;

            }

            pushFollow(FOLLOW_statement_list_in_function2261);
            statement_list34=statement_list();
            _fsp--;

            list_statement_list.add(statement_list34.tree);
            char_literal35=(Token)input.LT(1);
            match(input,41,FOLLOW_41_in_function2263); 
            list_41.add(char_literal35);


            // AST REWRITE
            int i_0 = 0;
            retval.tree = root_0;
            root_0 = (CommonTree)adaptor.nil();
            // 152:92: -> ^( FUNCTION LETTER )
            {
                // grammar/antlr_3/bc.g:152:95: ^( FUNCTION LETTER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(FUNCTION, "FUNCTION"), root_1);

                adaptor.addChild(root_1, (Token)list_LETTER.get(i_0));

                adaptor.addChild(root_0, root_1);
                }

            }



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end function

    public static class parameter_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parameter_list
    // grammar/antlr_3/bc.g:156:1: parameter_list : define_list ;
    public parameter_list_return parameter_list() throws RecognitionException {   
        parameter_list_return retval = new parameter_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        define_list_return define_list36 = null;



        try {
            // grammar/antlr_3/bc.g:157:5: ( define_list )
            // grammar/antlr_3/bc.g:157:5: define_list
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_define_list_in_parameter_list2294);
            define_list36=define_list();
            _fsp--;

            adaptor.addChild(root_0, define_list36.tree);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end parameter_list

    public static class auto_define_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start auto_define_list
    // grammar/antlr_3/bc.g:160:1: auto_define_list : Auto define_list (NEWLINE|';');
    public auto_define_list_return auto_define_list() throws RecognitionException {   
        auto_define_list_return retval = new auto_define_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token Auto37=null;
        Token set39=null;
        define_list_return define_list38 = null;


        CommonTree Auto37_tree=null;
        CommonTree set39_tree=null;

        try {
            // grammar/antlr_3/bc.g:161:5: ( Auto define_list (NEWLINE|';'))
            // grammar/antlr_3/bc.g:161:5: Auto define_list (NEWLINE|';')
            {
            root_0 = (CommonTree)adaptor.nil();

            Auto37=(Token)input.LT(1);
            match(input,Auto,FOLLOW_Auto_in_auto_define_list2307); 
            Auto37_tree = (CommonTree)adaptor.create(Auto37);
            adaptor.addChild(root_0, Auto37_tree);

            pushFollow(FOLLOW_define_list_in_auto_define_list2309);
            define_list38=define_list();
            _fsp--;

            adaptor.addChild(root_0, define_list38.tree);
            set39=(Token)input.LT(1);
            if ( input.LA(1)==NEWLINE||input.LA(1)==37 ) {
                adaptor.addChild(root_0, adaptor.create(set39));
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_auto_define_list2313);    throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end auto_define_list

    public static class define_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start define_list
    // grammar/antlr_3/bc.g:164:1: define_list : LETTER ( '[' ']' )? ( ',' LETTER ( '[' ']' )? )* ;
    public define_list_return define_list() throws RecognitionException {   
        define_list_return retval = new define_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LETTER40=null;
        Token char_literal41=null;
        Token char_literal42=null;
        Token char_literal43=null;
        Token LETTER44=null;
        Token char_literal45=null;
        Token char_literal46=null;

        CommonTree LETTER40_tree=null;
        CommonTree char_literal41_tree=null;
        CommonTree char_literal42_tree=null;
        CommonTree char_literal43_tree=null;
        CommonTree LETTER44_tree=null;
        CommonTree char_literal45_tree=null;
        CommonTree char_literal46_tree=null;

        try {
            // grammar/antlr_3/bc.g:165:5: ( LETTER ( '[' ']' )? ( ',' LETTER ( '[' ']' )? )* )
            // grammar/antlr_3/bc.g:165:5: LETTER ( '[' ']' )? ( ',' LETTER ( '[' ']' )? )*
            {
            root_0 = (CommonTree)adaptor.nil();

            LETTER40=(Token)input.LT(1);
            match(input,LETTER,FOLLOW_LETTER_in_define_list2332); 
            LETTER40_tree = (CommonTree)adaptor.create(LETTER40);
            adaptor.addChild(root_0, LETTER40_tree);

            // grammar/antlr_3/bc.g:165:12: ( '[' ']' )?
            int alt10=2;
            int LA10_0 = input.LA(1);
            if ( (LA10_0==42) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // grammar/antlr_3/bc.g:165:14: '[' ']'
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();

                    char_literal41=(Token)input.LT(1);
                    match(input,42,FOLLOW_42_in_define_list2336); 
                    char_literal41_tree = (CommonTree)adaptor.create(char_literal41);
                    adaptor.addChild(root_1, char_literal41_tree);

                    char_literal42=(Token)input.LT(1);
                    match(input,43,FOLLOW_43_in_define_list2338); 
                    char_literal42_tree = (CommonTree)adaptor.create(char_literal42);
                    adaptor.addChild(root_1, char_literal42_tree);


                    adaptor.addChild(root_0, root_1);

                    }
                    break;

            }

            // grammar/antlr_3/bc.g:165:25: ( ',' LETTER ( '[' ']' )? )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);
                if ( (LA12_0==44) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:165:27: ',' LETTER ( '[' ']' )?
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    char_literal43=(Token)input.LT(1);
            	    match(input,44,FOLLOW_44_in_define_list2345); 
            	    char_literal43_tree = (CommonTree)adaptor.create(char_literal43);
            	    adaptor.addChild(root_1, char_literal43_tree);

            	    LETTER44=(Token)input.LT(1);
            	    match(input,LETTER,FOLLOW_LETTER_in_define_list2347); 
            	    LETTER44_tree = (CommonTree)adaptor.create(LETTER44);
            	    adaptor.addChild(root_1, LETTER44_tree);

            	    // grammar/antlr_3/bc.g:165:38: ( '[' ']' )?
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);
            	    if ( (LA11_0==42) ) {
            	        alt11=1;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // grammar/antlr_3/bc.g:165:40: '[' ']'
            	            {
            	            CommonTree root_2 = (CommonTree)adaptor.nil();

            	            char_literal45=(Token)input.LT(1);
            	            match(input,42,FOLLOW_42_in_define_list2351); 
            	            char_literal45_tree = (CommonTree)adaptor.create(char_literal45);
            	            adaptor.addChild(root_2, char_literal45_tree);

            	            char_literal46=(Token)input.LT(1);
            	            match(input,43,FOLLOW_43_in_define_list2353); 
            	            char_literal46_tree = (CommonTree)adaptor.create(char_literal46);
            	            adaptor.addChild(root_2, char_literal46_tree);


            	            adaptor.addChild(root_1, root_2);

            	            }
            	            break;

            	    }


            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end define_list

    public static class argument_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start argument_list
    // grammar/antlr_3/bc.g:168:1: argument_list : ( expression | LETTER '[' ']' ',' argument_list );
    public argument_list_return argument_list() throws RecognitionException {   
        argument_list_return retval = new argument_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LETTER48=null;
        Token char_literal49=null;
        Token char_literal50=null;
        Token char_literal51=null;
        expression_return expression47 = null;

        argument_list_return argument_list52 = null;


        CommonTree LETTER48_tree=null;
        CommonTree char_literal49_tree=null;
        CommonTree char_literal50_tree=null;
        CommonTree char_literal51_tree=null;

        try {
            // grammar/antlr_3/bc.g:169:5: ( expression | LETTER '[' ']' ',' argument_list )
            int alt13=2;
            int LA13_0 = input.LA(1);
            if ( ((LA13_0>=NUMBER && LA13_0<=MINUS)||(LA13_0>=INCR && LA13_0<=DECR)||LA13_0==38) ) {
                alt13=1;
            }
            else if ( (LA13_0==LETTER) ) {
                int LA13_2 = input.LA(2);
                if ( (LA13_2==42) ) {
                    alt13=2;
                }
                else if ( ((LA13_2>=MINUS && LA13_2<=MUL_OP)) ) {
                    alt13=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("168:1: argument_list : ( expression | LETTER '[' ']' ',' argument_list );", 13, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("168:1: argument_list : ( expression | LETTER '[' ']' ',' argument_list );", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // grammar/antlr_3/bc.g:169:5: expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expression_in_argument_list2372);
                    expression47=expression();
                    _fsp--;

                    adaptor.addChild(root_0, expression47.tree);

                    }
                    break;
                case 2 :
                    // grammar/antlr_3/bc.g:171:5: LETTER '[' ']' ',' argument_list
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LETTER48=(Token)input.LT(1);
                    match(input,LETTER,FOLLOW_LETTER_in_argument_list2384); 
                    LETTER48_tree = (CommonTree)adaptor.create(LETTER48);
                    adaptor.addChild(root_0, LETTER48_tree);

                    char_literal49=(Token)input.LT(1);
                    match(input,42,FOLLOW_42_in_argument_list2386); 
                    char_literal49_tree = (CommonTree)adaptor.create(char_literal49);
                    adaptor.addChild(root_0, char_literal49_tree);

                    char_literal50=(Token)input.LT(1);
                    match(input,43,FOLLOW_43_in_argument_list2388); 
                    char_literal50_tree = (CommonTree)adaptor.create(char_literal50);
                    adaptor.addChild(root_0, char_literal50_tree);

                    char_literal51=(Token)input.LT(1);
                    match(input,44,FOLLOW_44_in_argument_list2390); 
                    char_literal51_tree = (CommonTree)adaptor.create(char_literal51);
                    adaptor.addChild(root_0, char_literal51_tree);

                    pushFollow(FOLLOW_argument_list_in_argument_list2392);
                    argument_list52=argument_list();
                    _fsp--;

                    adaptor.addChild(root_0, argument_list52.tree);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end argument_list

    public static class relational_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start relational_expression
    // grammar/antlr_3/bc.g:174:1: relational_expression : expression ( REL_OP^^ expression )? ;
    public relational_expression_return relational_expression() throws RecognitionException {   
        relational_expression_return retval = new relational_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token REL_OP54=null;
        expression_return expression53 = null;

        expression_return expression55 = null;


        CommonTree REL_OP54_tree=null;

        try {
            // grammar/antlr_3/bc.g:175:5: ( expression ( REL_OP^^ expression )? )
            // grammar/antlr_3/bc.g:175:5: expression ( REL_OP^^ expression )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_expression_in_relational_expression2406);
            expression53=expression();
            _fsp--;

            adaptor.addChild(root_0, expression53.tree);
            // grammar/antlr_3/bc.g:175:16: ( REL_OP^^ expression )?
            int alt14=2;
            int LA14_0 = input.LA(1);
            if ( (LA14_0==REL_OP) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // grammar/antlr_3/bc.g:175:18: REL_OP^^ expression
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();

                    REL_OP54=(Token)input.LT(1);
                    match(input,REL_OP,FOLLOW_REL_OP_in_relational_expression2410); 
                    REL_OP54_tree = (CommonTree)adaptor.create(REL_OP54);
                    root_0 = (CommonTree)adaptor.becomeRoot(REL_OP54_tree, root_0);

                    pushFollow(FOLLOW_expression_in_relational_expression2413);
                    expression55=expression();
                    _fsp--;

                    adaptor.addChild(root_1, expression55.tree);

                    adaptor.addChild(root_0, root_1);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end relational_expression

    public static class return_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start return_expression
    // grammar/antlr_3/bc.g:178:1: return_expression : ( expression )? ;
    public return_expression_return return_expression() throws RecognitionException {   
        return_expression_return retval = new return_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        expression_return expression56 = null;



        try {
            // grammar/antlr_3/bc.g:179:5: ( ( expression )? )
            // grammar/antlr_3/bc.g:179:5: ( expression )?
            {
            root_0 = (CommonTree)adaptor.nil();

            // grammar/antlr_3/bc.g:179:5: ( expression )?
            int alt15=2;
            int LA15_0 = input.LA(1);
            if ( (LA15_0==LETTER||(LA15_0>=NUMBER && LA15_0<=MINUS)||(LA15_0>=INCR && LA15_0<=DECR)||LA15_0==38) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // grammar/antlr_3/bc.g:179:5: expression
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expression_in_return_expression2429);
                    expression56=expression();
                    _fsp--;

                    adaptor.addChild(root_1, expression56.tree);

                    adaptor.addChild(root_0, root_1);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end return_expression

    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expression
    // grammar/antlr_3/bc.g:182:1: expression : adding_expression ;
    public expression_return expression() throws RecognitionException {   
        expression_return retval = new expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        adding_expression_return adding_expression57 = null;



        try {
            // grammar/antlr_3/bc.g:183:5: ( adding_expression )
            // grammar/antlr_3/bc.g:183:5: adding_expression
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_adding_expression_in_expression2443);
            adding_expression57=adding_expression();
            _fsp--;

            adaptor.addChild(root_0, adding_expression57.tree);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end expression

    public static class named_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start named_expression
    // grammar/antlr_3/bc.g:186:1: named_expression : LETTER -> ^( VAR LETTER ) ;
    public named_expression_return named_expression() throws RecognitionException {   
        named_expression_return retval = new named_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LETTER58=null;
        List list_LETTER=new ArrayList();
        CommonTree LETTER58_tree=null;

        try {
            // grammar/antlr_3/bc.g:187:5: ( LETTER -> ^( VAR LETTER ) )
            // grammar/antlr_3/bc.g:187:5: LETTER
            {
            LETTER58=(Token)input.LT(1);
            match(input,LETTER,FOLLOW_LETTER_in_named_expression2456); 
            list_LETTER.add(LETTER58);


            // AST REWRITE
            int i_0 = 0;
            retval.tree = root_0;
            root_0 = (CommonTree)adaptor.nil();
            // 187:12: -> ^( VAR LETTER )
            {
                // grammar/antlr_3/bc.g:187:15: ^( VAR LETTER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_1);

                adaptor.addChild(root_1, (Token)list_LETTER.get(i_0));

                adaptor.addChild(root_0, root_1);
                }

            }



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end named_expression

    public static class adding_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start adding_expression
    // grammar/antlr_3/bc.g:192:1: adding_expression : multiplying_expression ( ( PLUS^^ | MINUS^^ ) multiplying_expression )* ;
    public adding_expression_return adding_expression() throws RecognitionException {   
        adding_expression_return retval = new adding_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS60=null;
        Token MINUS61=null;
        multiplying_expression_return multiplying_expression59 = null;

        multiplying_expression_return multiplying_expression62 = null;


        CommonTree PLUS60_tree=null;
        CommonTree MINUS61_tree=null;

        try {
            // grammar/antlr_3/bc.g:193:5: ( multiplying_expression ( ( PLUS^^ | MINUS^^ ) multiplying_expression )* )
            // grammar/antlr_3/bc.g:193:5: multiplying_expression ( ( PLUS^^ | MINUS^^ ) multiplying_expression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multiplying_expression_in_adding_expression2482);
            multiplying_expression59=multiplying_expression();
            _fsp--;

            adaptor.addChild(root_0, multiplying_expression59.tree);
            // grammar/antlr_3/bc.g:193:28: ( ( PLUS^^ | MINUS^^ ) multiplying_expression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);
                if ( (LA17_0==MINUS) ) {
                    switch ( input.LA(2) ) {
                    case NUMBER:
                        alt17=1;
                        break;
                    case LETTER:
                    case MINUS:
                    case INCR:
                    case DECR:
                        alt17=1;
                        break;
                    case 38:
                        alt17=1;
                        break;

                    }

                }
                else if ( (LA17_0==PLUS) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:193:30: ( PLUS^^ | MINUS^^ ) multiplying_expression
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    // grammar/antlr_3/bc.g:193:30: ( PLUS^^ | MINUS^^ )
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);
            	    if ( (LA16_0==PLUS) ) {
            	        alt16=1;
            	    }
            	    else if ( (LA16_0==MINUS) ) {
            	        alt16=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("193:30: ( PLUS^^ | MINUS^^ )", 16, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // grammar/antlr_3/bc.g:193:32: PLUS^^
            	            {
            	            CommonTree root_2 = (CommonTree)adaptor.nil();

            	            PLUS60=(Token)input.LT(1);
            	            match(input,PLUS,FOLLOW_PLUS_in_adding_expression2488); 
            	            PLUS60_tree = (CommonTree)adaptor.create(PLUS60);
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS60_tree, root_0);


            	            adaptor.addChild(root_1, root_2);

            	            }
            	            break;
            	        case 2 :
            	            // grammar/antlr_3/bc.g:193:41: MINUS^^
            	            {
            	            CommonTree root_2 = (CommonTree)adaptor.nil();

            	            MINUS61=(Token)input.LT(1);
            	            match(input,MINUS,FOLLOW_MINUS_in_adding_expression2493); 
            	            MINUS61_tree = (CommonTree)adaptor.create(MINUS61);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS61_tree, root_0);


            	            adaptor.addChild(root_1, root_2);

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplying_expression_in_adding_expression2498);
            	    multiplying_expression62=multiplying_expression();
            	    _fsp--;

            	    adaptor.addChild(root_1, multiplying_expression62.tree);

            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end adding_expression

    public static class multiplying_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multiplying_expression
    // grammar/antlr_3/bc.g:196:1: multiplying_expression : unary_expression ( MUL_OP^^ unary_expression )* ;
    public multiplying_expression_return multiplying_expression() throws RecognitionException {   
        multiplying_expression_return retval = new multiplying_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MUL_OP64=null;
        unary_expression_return unary_expression63 = null;

        unary_expression_return unary_expression65 = null;


        CommonTree MUL_OP64_tree=null;

        try {
            // grammar/antlr_3/bc.g:197:5: ( unary_expression ( MUL_OP^^ unary_expression )* )
            // grammar/antlr_3/bc.g:197:5: unary_expression ( MUL_OP^^ unary_expression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_unary_expression_in_multiplying_expression2515);
            unary_expression63=unary_expression();
            _fsp--;

            adaptor.addChild(root_0, unary_expression63.tree);
            // grammar/antlr_3/bc.g:197:22: ( MUL_OP^^ unary_expression )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);
                if ( (LA18_0==MUL_OP) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/antlr_3/bc.g:197:24: MUL_OP^^ unary_expression
            	    {
            	    CommonTree root_1 = (CommonTree)adaptor.nil();

            	    MUL_OP64=(Token)input.LT(1);
            	    match(input,MUL_OP,FOLLOW_MUL_OP_in_multiplying_expression2519); 
            	    MUL_OP64_tree = (CommonTree)adaptor.create(MUL_OP64);
            	    root_0 = (CommonTree)adaptor.becomeRoot(MUL_OP64_tree, root_0);

            	    pushFollow(FOLLOW_unary_expression_in_multiplying_expression2522);
            	    unary_expression65=unary_expression();
            	    _fsp--;

            	    adaptor.addChild(root_1, unary_expression65.tree);

            	    adaptor.addChild(root_0, root_1);

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end multiplying_expression

    public static class unary_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start unary_expression
    // grammar/antlr_3/bc.g:200:1: unary_expression : ( postfix_expression | MINUS postfix_expression -> ^( MUL_OP["*"] NUMBER["-1"] postfix_expression ) | INCR named_expression -> ^( ASSIGN_OP ^( VAR LETTER["a"] ) ^( PLUS["+"] ^( VAR LETTER["a"] ) NUMBER["1"] ) ) | DECR named_expression -> ^( ASSIGN_OP ^( VAR LETTER["a"] ) ^( MINUS["-"] ^( VAR LETTER["a"] ) NUMBER["1"] ) ) );
    public unary_expression_return unary_expression() throws RecognitionException {   
        unary_expression_return retval = new unary_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MINUS67=null;
        Token INCR69=null;
        Token DECR71=null;
        postfix_expression_return postfix_expression66 = null;

        postfix_expression_return postfix_expression68 = null;

        named_expression_return named_expression70 = null;

        named_expression_return named_expression72 = null;

        List list_named_expression=new ArrayList();
        List list_postfix_expression=new ArrayList();
        List list_MINUS=new ArrayList();
        List list_INCR=new ArrayList();
        List list_DECR=new ArrayList();
        CommonTree MINUS67_tree=null;
        CommonTree INCR69_tree=null;
        CommonTree DECR71_tree=null;

        try {
            // grammar/antlr_3/bc.g:201:5: ( postfix_expression | MINUS postfix_expression -> ^( MUL_OP[\"*\"] NUMBER[\"-1\"] postfix_expression ) | INCR named_expression -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( PLUS[\"+\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) ) | DECR named_expression -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( MINUS[\"-\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) ) )
            int alt19=4;
            switch ( input.LA(1) ) {
            case LETTER:
            case NUMBER:
            case 38:
                alt19=1;
                break;
            case MINUS:
                alt19=2;
                break;
            case INCR:
                alt19=3;
                break;
            case DECR:
                alt19=4;
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("200:1: unary_expression : ( postfix_expression | MINUS postfix_expression -> ^( MUL_OP[\"*\"] NUMBER[\"-1\"] postfix_expression ) | INCR named_expression -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( PLUS[\"+\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) ) | DECR named_expression -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( MINUS[\"-\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) ) );", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // grammar/antlr_3/bc.g:201:5: postfix_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_postfix_expression_in_unary_expression2538);
                    postfix_expression66=postfix_expression();
                    _fsp--;

                    adaptor.addChild(root_0, postfix_expression66.tree);

                    }
                    break;
                case 2 :
                    // grammar/antlr_3/bc.g:203:5: MINUS postfix_expression
                    {
                    MINUS67=(Token)input.LT(1);
                    match(input,MINUS,FOLLOW_MINUS_in_unary_expression2550); 
                    list_MINUS.add(MINUS67);

                    pushFollow(FOLLOW_postfix_expression_in_unary_expression2552);
                    postfix_expression68=postfix_expression();
                    _fsp--;

                    list_postfix_expression.add(postfix_expression68.tree);

                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 203:30: -> ^( MUL_OP[\"*\"] NUMBER[\"-1\"] postfix_expression )
                    {
                        // grammar/antlr_3/bc.g:203:33: ^( MUL_OP[\"*\"] NUMBER[\"-1\"] postfix_expression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(MUL_OP, "*"), root_1);

                        adaptor.addChild(root_1, adaptor.create(NUMBER, "-1"));
                        adaptor.addChild(root_1, list_postfix_expression.get(i_0));

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 3 :
                    // grammar/antlr_3/bc.g:205:5: INCR named_expression
                    {
                    INCR69=(Token)input.LT(1);
                    match(input,INCR,FOLLOW_INCR_in_unary_expression2578); 
                    list_INCR.add(INCR69);

                    pushFollow(FOLLOW_named_expression_in_unary_expression2580);
                    named_expression70=named_expression();
                    _fsp--;

                    list_named_expression.add(named_expression70.tree);

                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 205:27: -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( PLUS[\"+\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) )
                    {
                        // grammar/antlr_3/bc.g:205:30: ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( PLUS[\"+\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_1);

                        // grammar/antlr_3/bc.g:205:43: ^( VAR LETTER[\"a\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_2);

                        adaptor.addChild(root_2, adaptor.create(LETTER, "a"));

                        adaptor.addChild(root_1, root_2);
                        }
                        // grammar/antlr_3/bc.g:205:62: ^( PLUS[\"+\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(PLUS, "+"), root_2);

                        // grammar/antlr_3/bc.g:205:75: ^( VAR LETTER[\"a\"] )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                        adaptor.addChild(root_3, adaptor.create(LETTER, "a"));

                        adaptor.addChild(root_2, root_3);
                        }
                        adaptor.addChild(root_2, adaptor.create(NUMBER, "1"));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 4 :
                    // grammar/antlr_3/bc.g:207:5: DECR named_expression
                    {
                    DECR71=(Token)input.LT(1);
                    match(input,DECR,FOLLOW_DECR_in_unary_expression2624); 
                    list_DECR.add(DECR71);

                    pushFollow(FOLLOW_named_expression_in_unary_expression2626);
                    named_expression72=named_expression();
                    _fsp--;

                    list_named_expression.add(named_expression72.tree);

                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 207:27: -> ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( MINUS[\"-\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) )
                    {
                        // grammar/antlr_3/bc.g:207:30: ^( ASSIGN_OP ^( VAR LETTER[\"a\"] ) ^( MINUS[\"-\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ASSIGN_OP, "ASSIGN_OP"), root_1);

                        // grammar/antlr_3/bc.g:207:43: ^( VAR LETTER[\"a\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_2);

                        adaptor.addChild(root_2, adaptor.create(LETTER, "a"));

                        adaptor.addChild(root_1, root_2);
                        }
                        // grammar/antlr_3/bc.g:207:62: ^( MINUS[\"-\"] ^( VAR LETTER[\"a\"] ) NUMBER[\"1\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(MINUS, "-"), root_2);

                        // grammar/antlr_3/bc.g:207:76: ^( VAR LETTER[\"a\"] )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(VAR, "VAR"), root_3);

                        adaptor.addChild(root_3, adaptor.create(LETTER, "a"));

                        adaptor.addChild(root_2, root_3);
                        }
                        adaptor.addChild(root_2, adaptor.create(NUMBER, "1"));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end unary_expression

    public static class postfix_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start postfix_expression
    // grammar/antlr_3/bc.g:210:1: postfix_expression : ( NUMBER | named_expression | '(' expression ')' -> expression );
    public postfix_expression_return postfix_expression() throws RecognitionException {   
        postfix_expression_return retval = new postfix_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NUMBER73=null;
        Token char_literal75=null;
        Token char_literal77=null;
        named_expression_return named_expression74 = null;

        expression_return expression76 = null;

        List list_expression=new ArrayList();
        List list_38=new ArrayList();
        List list_39=new ArrayList();
        CommonTree NUMBER73_tree=null;
        CommonTree char_literal75_tree=null;
        CommonTree char_literal77_tree=null;

        try {
            // grammar/antlr_3/bc.g:211:5: ( NUMBER | named_expression | '(' expression ')' -> expression )
            int alt20=3;
            switch ( input.LA(1) ) {
            case NUMBER:
                alt20=1;
                break;
            case LETTER:
                alt20=2;
                break;
            case 38:
                alt20=3;
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("210:1: postfix_expression : ( NUMBER | named_expression | '(' expression ')' -> expression );", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // grammar/antlr_3/bc.g:211:5: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NUMBER73=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_postfix_expression2671); 
                    NUMBER73_tree = (CommonTree)adaptor.create(NUMBER73);
                    adaptor.addChild(root_0, NUMBER73_tree);


                    }
                    break;
                case 2 :
                    // grammar/antlr_3/bc.g:213:5: named_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_named_expression_in_postfix_expression2683);
                    named_expression74=named_expression();
                    _fsp--;

                    adaptor.addChild(root_0, named_expression74.tree);

                    }
                    break;
                case 3 :
                    // grammar/antlr_3/bc.g:215:5: '(' expression ')'
                    {
                    char_literal75=(Token)input.LT(1);
                    match(input,38,FOLLOW_38_in_postfix_expression2700); 
                    list_38.add(char_literal75);

                    pushFollow(FOLLOW_expression_in_postfix_expression2702);
                    expression76=expression();
                    _fsp--;

                    list_expression.add(expression76.tree);
                    char_literal77=(Token)input.LT(1);
                    match(input,39,FOLLOW_39_in_postfix_expression2704); 
                    list_39.add(char_literal77);


                    // AST REWRITE
                    int i_0 = 0;
                    retval.tree = root_0;
                    root_0 = (CommonTree)adaptor.nil();
                    // 215:24: -> expression
                    {
                        adaptor.addChild(root_0, list_expression.get(i_0));

                    }



                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        return retval;
    }
    // $ANTLR end postfix_expression


 

    public static final BitSet FOLLOW_input_item_in_program602 = new BitSet(new long[]{0x000001601170DC00L});
    public static final BitSet FOLLOW_Quit_in_program605 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NEWLINE_in_program607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_semicolon_list_in_input_item2026 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NEWLINE_in_input_item2028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_input_item2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_semicolon_list2055 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_semicolon_list2060 = new BitSet(new long[]{0x000001601030D802L});
    public static final BitSet FOLLOW_statement_in_semicolon_list2063 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_statement_in_statement_list2087 = new BitSet(new long[]{0x000001601030DC02L});
    public static final BitSet FOLLOW_NEWLINE_in_statement_list2091 = new BitSet(new long[]{0x000001601030DC02L});
    public static final BitSet FOLLOW_37_in_statement_list2096 = new BitSet(new long[]{0x000001601030DC02L});
    public static final BitSet FOLLOW_named_expression_in_statement2114 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ASSIGN_OP_in_statement2116 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_expression_in_statement2119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_statement2161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_statement2184 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_statement2186 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_relational_expression_in_statement2188 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_statement2190 = new BitSet(new long[]{0x000001401030D800L});
    public static final BitSet FOLLOW_statement_in_statement2192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_statement2223 = new BitSet(new long[]{0x000003601030DC00L});
    public static final BitSet FOLLOW_statement_list_in_statement2226 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_statement2228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Define_in_function2243 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LETTER_in_function2245 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_function2247 = new BitSet(new long[]{0x0000008000001000L});
    public static final BitSet FOLLOW_parameter_list_in_function2249 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_function2252 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_function2254 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NEWLINE_in_function2256 = new BitSet(new long[]{0x000003641030DC00L});
    public static final BitSet FOLLOW_auto_define_list_in_function2258 = new BitSet(new long[]{0x000003601030DC00L});
    public static final BitSet FOLLOW_statement_list_in_function2261 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_function2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_define_list_in_parameter_list2294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Auto_in_auto_define_list2307 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_define_list_in_auto_define_list2309 = new BitSet(new long[]{0x0000002000000400L});
    public static final BitSet FOLLOW_set_in_auto_define_list2313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_define_list2332 = new BitSet(new long[]{0x0000140000000002L});
    public static final BitSet FOLLOW_42_in_define_list2336 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_define_list2338 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_44_in_define_list2345 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LETTER_in_define_list2347 = new BitSet(new long[]{0x0000140000000002L});
    public static final BitSet FOLLOW_42_in_define_list2351 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_define_list2353 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_expression_in_argument_list2372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_argument_list2384 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_argument_list2386 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_argument_list2388 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_argument_list2390 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_argument_list_in_argument_list2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_relational_expression2406 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_REL_OP_in_relational_expression2410 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_expression_in_relational_expression2413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_return_expression2429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adding_expression_in_expression2443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LETTER_in_named_expression2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplying_expression_in_adding_expression2482 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_PLUS_in_adding_expression2488 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_MINUS_in_adding_expression2493 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_multiplying_expression_in_adding_expression2498 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_unary_expression_in_multiplying_expression2515 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_MUL_OP_in_multiplying_expression2519 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_unary_expression_in_multiplying_expression2522 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_postfix_expression_in_unary_expression2538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_unary_expression2550 = new BitSet(new long[]{0x0000004000005000L});
    public static final BitSet FOLLOW_postfix_expression_in_unary_expression2552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCR_in_unary_expression2578 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_named_expression_in_unary_expression2580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECR_in_unary_expression2624 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_named_expression_in_unary_expression2626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_postfix_expression2671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_named_expression_in_postfix_expression2683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_postfix_expression2700 = new BitSet(new long[]{0x000000400030D000L});
    public static final BitSet FOLLOW_expression_in_postfix_expression2702 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_postfix_expression2704 = new BitSet(new long[]{0x0000000000000002L});

}