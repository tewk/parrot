10 REM Eliza -- Computer Psychologist
11 REM
12 REM By Joseph Weizenbaum, MIT 1965
14 REM
16 REM Parrot BASIC port by Clinton Pierce
18 REM Freely Redistributable (I think)
19 dim rep$(1), conj$(1), ans$(1), response(1), rescnt(1), resmax(1)
20 GOTO 100
49 REM Busy indicator
50 IF TWIRL$="" THEN LET TWIRL$=CHR$(92)+CHR$(124)+CHR$(47)+CHR$(45)
55 LET TA$=LEFT$(TWIRL$,1)
60 PRINT TA$+CHR$(8);
65 LET TWIRL$=RIGHT$(TWIRL$,LEN(TWIRL$)-1)+TA$
70 RETURN
100 RESTORE 10600
105 PRINT "Please wait, initializing...(This will take a minute)"
110 GOSUB 1500
120 GOSUB 1800
199 REM Top of processing loop
200 INPUT ANS$
210 GOSUB 1900
220 LET DONE=0
230 IF INSTR(ANS$, "BYE") THEN GOTO 235
232 IF INSTR(ANS$, "SHUT") THEN GOTO 235
233 GOTO 240
235 END
240 GOSUB 1200
250 IF DONE = 0 THEN PRINT "I DONT UNDERSTAND"
310 GOTO 200
999 END
1200 REM Check for key phrases
1202   IF RIGHT$(ANS$,1) <> "?" THEN GOTO 1210
1203   IF RIGHT$(ANS$,1) <> "." THEN GOTO 1210
1204   LET ANS$=LEFT$(ANS$, LEN(ANS$)-1)
1210   FOR I=1 TO REPLIES
1220     LET P=INSTR(ANS$, REP$(I))
1230     IF P > 0 THEN GOTO 1260
1240   NEXT I
1245   LET I=I-1
1250   GOTO 1360
1260   LET K$=RIGHT$(ANS$, LEN(ANS$)-(P+LEN(REP$(I))))
1265   LET K$=" "+K$+" "
1270   FOR J=1 TO WORDS
1280     LET W=INSTR(K$, CONJ$(J,2))
1290     IF W<1 THEN GOTO 1350
1300     LET FA$=LEFT$(K$,W)
1310     LET FA$=FA$+CONJ$(J,1)
1320     LET FA$=FA$+RIGHT$(K$,LEN(K$)-(W+LEN(CONJ$(J,2))))
1330     LET K$=FA$
1340     GOTO 1280
1350   NEXT J
1360   LET DONE=1
1362 REM Now select the appropriate response
1365   LET RES$=ANS$(RESCNT(I))
1370   LET RESCNT(I)=RESCNT(I)+1
1380   IF RESCNT(I)>RESMAX(I) THEN LET RESCNT(I)=RESPONSE(I)
1390   IF RIGHT$(RES$,1) <> "*" THEN GOTO 1420
1400   LET RES$=LEFT$(RES$,LEN(RES$)-1)
1410   LET RES$=RES$+" "+K$
1420   PRINT RES$
1499 RETURN
1500 REM Initialzations
1520 REM Read in keywords
1521   LET REPLIES=1
1530   READ REP$(REPLIES)
1531   GOSUB 50
1535   IF REP$(REPLIES) = "-" THEN GOTO 1560
1540   LET REPLIES=REPLIES+1
1550   GOTO 1530
1560   REM
1565   LET WORDS=1
1570   READ CONJ$(WORDS,2)
1571   GOSUB 50
1575   IF CONJ$(WORDS,2) = "-" THEN GOTO 1610
1580   READ CONJ$(WORDS,1)
1590   LET WORDS=WORDS+1
1600   GOTO 1570
1610   REM
1615   LET ANS=1
1620   READ ANS$(ANS)
1621   GOSUB 50
1630   IF ANS$(ANS) = "-" THEN GOTO 1680
1640   LET ANS=ANS+1
1650   GOTO 1620
1680   LET ANSI=1
1682   READ A
1683   IF A=-1 THEN GOTO 1750
1684   READ B
1685   GOSUB 50
1686   LET RESPONSE(ANSI)=A
1688   LET RESCNT(ANSI)=A
1689   LET RESMAX(ANSI)=A+B-1
1710   LET ANSI=ANSI+1
1720   GOTO 1682
1750 RETURN
1800 REM Greeting
1810 PRINT "I am Eliza, the Computer Psychiatrist"
1830 PRINT
1840 PRINT "Why have you requested this session?"
1850 RETURN
1900 REM Shift ANS$ to uppercase
1905   LET NA$=""
1910   FOR I=1 TO LEN(ANS$) 
1915     LET T$=MID$(ANS$,I,1)
1920     IF T$<"a" THEN GOTO 1950
1930     LET T$=CHR$(ASC(T$)-32)
1950     LET NA$=NA$+T$
1960   NEXT I
1970   LET ANS$=NA$
1990 RETURN
10600 REM
10610 DATA "CAN YOU", "CAN I", "YOU ARE", "YOU'RE", "I DON'T", "I FEEL"
10620 DATA "WHY DON'T YOU", "WHY CAN'T I", "ARE YOU", "I CAN'T", "I AM", "I'M "
10630 DATA "YOU ", "I WANT", "WHAT", "HOW", "WHO", "WHERE", "WHEN", "WHY"
10640 DATA "NAME", "CAUSE", "SORRY", "DREAM", "HELLO", "HI ", "MAYBE"
10650 DATA "NO", "YOUR", "ALWAYS", "THINK", "ALIKE", "YES", "FRIEND"
10660 DATA "COMPUTER", "-"
10670 DATA " ARE ", " AM ", " WERE ", " WAS ", " YOU ", " I ", " YOUR ", " MY "
10680 DATA " I'VE ", " YOU'VE ", " I'M ", " YOU'RE "
10690 DATA " ME ", " YOU ", "-"
10691 REM CAN YOU
10700 DATA "DON'T YOU BELIEVE THAT I CAN*"
10710 DATA "PERHAPS YOU WOULD LIKE TO BE ABLE TO*"
10720 DATA "YOU WANT ME TO BE ABLE TO*"
10721 REM CAN I
10730 DATA "PERHAPS YOU DON'T WANT TO*"
10740 DATA "DO YOU WANT TO BE ABLE TO*"
10741 REM YOU ARE / YOU'RE
10750 DATA "WHAT MAKES YOU THINK I AM*"
10760 DATA "DOES IT PLEASE YOU TO BELIEVE I AM*"
10770 DATA "PERHAPS YOU WOULD LIKE TO BE*"
10780 DATA "DO YOU SOMETIMES WISH YOU WERE*"
10781 REM I DON'T
10790 DATA "DON'T YOU REALLY*"
10800 DATA "WHY DON'T YOU*"
10810 DATA "DO YOU WISH TO BE ABLE TO*"
10820 DATA "DOES THAT TROUBLE YOU?"
10821 REM I FEEL
10830 DATA "TELL ME MORE ABOUT SUCH FEELINGS"
10840 DATA "DO YOU OFTEN FEEL*"
10850 DATA "DO YOU ENJOY FEELING*"
10851 REM WHY DON'T YOU
10860 DATA "DO YOU REALLY BELIEVE I DON'T*"
10870 DATA "PERHAPS IN GOOD TIME I WILL*"
10880 DATA "DO YOU WANT ME TO*"
10881 REM WHY CAN'T I
10890 DATA "DO YOU THINK YOU SHOULD BE ABLE TO*"
10900 DATA "WHY CAN'T YOU*"
10901 REM ARE YOU
10910 DATA "WHY ARE YOU INTERESTED IN WHETHER OR NOT I AM*"
10920 DATA "WOULD YOU PREFER IF I WERE NOT*"
10930 DATA "PERHAPS IN YOUR FANTASIES I AM*"
10931 REM I CAN'T
10940 DATA "HOW DO YOU KNOW YOU CAN'T*"
10950 DATA "HAVE YOU TRIED?"
10960 DATA "PERHAPS YOU CAN NOW*"
10961 REM I AM/I'M
10970 DATA "DID YOU COME TO ME BECAUSE YOU ARE*"
10980 DATA "HOW LONG HAVE YOU BEEN*"
10990 DATA "DO YOU BELIEVE IT IS NORMAL TO BE*"
101000 DATA "DO YOU ENJOY BEING*"
101001 REM YOU
101010 DATA "WE WERE DISCUSSING YOU NOT ME."
101020 DATA "OH I*"
101030 DATA "YOU'RE NOT REALLY TALKING ABOUT ME ARE YOU?"
101031 REM I WANT
101040 DATA "WHAT WOULD IT MEAN TO YOU IF YOU GOT*"
101050 DATA "WHY DO YOU WANT*"
101060 DATA "SUPPOSE YOU SOON GOT*"
101070 DATA "WHAT IF YOU NEVER GOT*"
101080 DATA "I SOMETIMES ALSO WANT*"
101081 REM WHAT/WHERE/WHO/HOW/WHEN/WHY
101090 DATA "WHY DO YOU ASK?"
101100 DATA "DOES THAT QUESTION INTEREST YOU?"
101110 DATA "WHAT ANSWER WOULD PLEASE YOU THE MOST?"
101120 DATA "WHAT DO YOU THINK?"
101130 DATA "ARE SUCH QUESTIONS ON YOUR MIND OFTEN?"
101140 DATA "WHAT IS IT THAT YOU REALLY WANT TO KNOW?"
101150 DATA "HAVE YOU ASKED ANYONE ELSE?"
101160 DATA "HAVE YOU ASKED SUCH QUESTIONS BEFORE?"
101170 DATA "WHAT ELSE COMES TO MIND WHEN YOU ASK THAT?"
101171 REM NAME
101180 DATA "NAMES DON'T INTEREST ME."
101190 DATA "I DON'T CARE ABOUT NAMES... PLEASE GO ON."
101191 REM CAUSE
101200 DATA "IS THAT THE REAL REASON?"
101210 DATA "DON'T ANY OTHER REASONS COME TO MIND?"
101220 DATA "DOES THAT REASON EXPLAIN ANYTHING ELSE?"
101230 DATA "WHAT OTHER REASONS MIGHT THERE BE?"
101231 REM SORRY
101240 DATA "PLEASE DON'T APOLOGIZE."
101250 DATA "APOLOGIES ARE NOT NECESSARY."
101260 DATA "WHAT FEELINGS DO YOU HAVE WHEN YOU APOLOGIZE?"
101270 DATA "DON'T BE SO DEFENSIVE."
101271 REM DREAM
101280 DATA "WHAT DOES THAT DREAM SUGGEST TO YOU?"
101290 DATA "DO YOU DREAM OFTEN?"
101300 DATA "WHAT PERSONS APPEAR IN YOUR DREAMS?"
101310 DATA "ARE YOU DISTURBED BY YOUR DREAMS?"
101311 REM HELLO/HI
101320 DATA "HOW DO YOU DO. PLEASE STATE YOUR PROBLEM."
101321 REM MAYBE
101330 DATA "YOU DON'T SEEM QUITE CERTAIN."
101340 DATA "WHY THE UNCERTAIN TONE?"
101350 DATA "CAN'T YOU BE MORE POSITIVE?"
101360 DATA "YOU AREN'T SURE?"
101370 DATA "DON'T YOU KNOW?"
101371 REM NO
101380 DATA "ARE YOU SAYING 'NO' JUST TO BE NEGATIVE?"
101390 DATA "YOU ARE BEING A BIT NEGATIVE."
101400 DATA "WHY NOT?"
101410 DATA "ARE YOU SURE?"
101420 DATA "WHY NO?"
101421 REM YOUR
101430 DATA "WHY ARE YOU CONCERNED ABOUT MY*"
101440 DATA "WHAT ABOUT YOUR OWN*"
101441 REM ALWAYS
101450 DATA "CAN YOU THINK OF AN EXAMPLE?"
101460 DATA "WHEN?"
101470 DATA "WHAT ARE YOU THINKING OF?"
101480 DATA "REALLY...ALWAYS?"
101481 REM THINK
101490 DATA "DO YOU REALLY THINK SO?"
101500 DATA "BUT YOU ARE NOT SURE YOU*"
101510 DATA "DO YOU DOUBT YOU*"
101511 REM ALIKE
101520 DATA "IN WHAT WAY?"
101530 DATA "WHAT SIMILARITY DO YOU SEE?"
101540 DATA "WHAT DOES THE SIMILARITY SUGGEST TO YOU?"
101550 DATA "WHAT OTHER CONNECTIONS DO YOU SEE?"
101560 DATA "COULD THERE REALLY BE SOME CONNECTION?"
101570 DATA "HOW?"
101580 DATA "YOU SEEM QUITE POSITIVE."
101581 REM YES
101590 DATA "ARE YOU SURE?"
101600 DATA "HMMM...I SEE."
101610 DATA "I UNDERSTAND."
101611 REM FRIEND
101620 DATA "DO YOU HAVE ANY FRIENDS?"
101630 DATA "DO YOUR FRIENDS WORRY YOU?"
101640 DATA "DO THEY PICK ON YOU?"
101650 DATA "ARE YOUR FRIENDS A SOURCE OF ANXIETY?"
101660 DATA "DO YOU IMPOSE YOUR PROBLEMS ON YOUR FRIENDS?"
101670 DATA "PERHAPS YOUR DEPENDENCE ON FRIENDS WORRIES YOU."
101671 REM COMPUTER
101680 DATA "DO COMPUTERS WORRY YOU?"
101690 DATA "ARE YOU TALKING ABOUT ME IN PARTICULAR?"
101700 DATA "ARE YOU FRIGHTENED BY MACHINES?"
101710 DATA "WHY DO YOU MENTION COMPUTERS?"
101720 DATA "WHAT DO YOU THINK MACHINES HAVE TO DO WITH YOUR PROBLEMS?"
101730 DATA "DON'T YOU THINK COMPUTERS CAN HELP YOU?"
101740 DATA "WHAT IS IT ABOUT MACHINES THAT WORRIES YOU?"
101741 REM (Leftover)
101750 DATA "DO YOU FEEL INTENSE PSYCHOLOGICAL STRESS?"
101760 DATA "WHAT DOES THAT SUGGEST TO YOU?"
101770 DATA "I SEE."
101780 DATA "I'M NOT SURE I UNDERSTAND YOU FULLY"
101790 DATA "NOW PLEASE CLARIFY YOURSELF."
101800 DATA "CAN YOU ELABORATE ON THAT?"
101810 DATA "THAT IS QUITE INTERESTING.", "-"
101820 REM  TO FIND CORRECT REPLIES
101830 DATA 1,3,4,2,6,4,6,4,10,4,14,3,17,3,20,2,22,3,25,3
101840 DATA 28,4,28,4,32,3,35,5,40,9,40,9,40,9,40,9,40,9,40,9
101850 DATA 49,2,51,4,55,4,59,4,63,1,63,1,64,5,69,5,74,2,76,4
101860 DATA 80,3,83,7,90,3,93,6,99,7,106,6,-1
