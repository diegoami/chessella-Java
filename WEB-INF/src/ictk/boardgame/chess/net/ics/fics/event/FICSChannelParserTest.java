/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
/*    */ import ictk.boardgame.chess.net.ics.event.ParserTest;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FICSChannelParserTest
/*    */   extends ParserTest
/*    */ {
/*    */   ICSChannelEvent evt;
/*    */   
/*    */   public FICSChannelParserTest()
/*    */     throws IOException
/*    */   {
/* 39 */     super("ictk.boardgame.chess.net.ics.fics.event");
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 43 */     this.parser = FICSChannelParser.getInstance();
/*    */   }
/*    */   
/*    */   public void tearDown()
/*    */   {
/* 48 */     this.evt = null;
/* 49 */     this.parser = null;
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testMessage0()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_0
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSChannelEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   56: ldc 70
/*    */     //   58: aload_0
/*    */     //   59: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   62: invokevirtual 72	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getPlayer	()Ljava/lang/String;
/*    */     //   65: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   68: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   71: aload_0
/*    */     //   72: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   75: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getChannel	()I
/*    */     //   78: bipush 50
/*    */     //   80: if_icmpne +7 -> 87
/*    */     //   83: iconst_1
/*    */     //   84: goto +4 -> 88
/*    */     //   87: iconst_0
/*    */     //   88: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   91: ldc 86
/*    */     //   93: aload_0
/*    */     //   94: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   97: invokevirtual 88	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getMessage	()Ljava/lang/String;
/*    */     //   100: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   103: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   106: goto +17 -> 123
/*    */     //   109: astore_1
/*    */     //   110: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   113: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   116: aload_0
/*    */     //   117: iconst_0
/*    */     //   118: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   121: aload_1
/*    */     //   122: athrow
/*    */     //   123: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   126: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   129: aload_0
/*    */     //   130: iconst_0
/*    */     //   131: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   134: return
/*    */     // Line number table:
/*    */     //   Java source line #55	-> byte code offset #0
/*    */     //   Java source line #56	-> byte code offset #7
/*    */     //   Java source line #57	-> byte code offset #13
/*    */     //   Java source line #61	-> byte code offset #21
/*    */     //   Java source line #62	-> byte code offset #41
/*    */     //   Java source line #66	-> byte code offset #56
/*    */     //   Java source line #67	-> byte code offset #71
/*    */     //   Java source line #68	-> byte code offset #91
/*    */     //   Java source line #72	-> byte code offset #109
/*    */     //   Java source line #73	-> byte code offset #110
/*    */     //   Java source line #74	-> byte code offset #116
/*    */     //   Java source line #75	-> byte code offset #121
/*    */     //   Java source line #73	-> byte code offset #123
/*    */     //   Java source line #74	-> byte code offset #129
/*    */     //   Java source line #76	-> byte code offset #134
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	135	0	this	FICSChannelParserTest
/*    */     //   109	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	109	109	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testMessage1()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_1
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSChannelEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   56: aload_0
/*    */     //   57: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   60: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   63: bipush 7
/*    */     //   65: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   68: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   71: goto +17 -> 88
/*    */     //   74: astore_1
/*    */     //   75: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   78: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   81: aload_0
/*    */     //   82: iconst_0
/*    */     //   83: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   86: aload_1
/*    */     //   87: athrow
/*    */     //   88: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   91: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   94: aload_0
/*    */     //   95: iconst_0
/*    */     //   96: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   99: return
/*    */     // Line number table:
/*    */     //   Java source line #81	-> byte code offset #0
/*    */     //   Java source line #82	-> byte code offset #7
/*    */     //   Java source line #83	-> byte code offset #13
/*    */     //   Java source line #87	-> byte code offset #21
/*    */     //   Java source line #88	-> byte code offset #41
/*    */     //   Java source line #92	-> byte code offset #56
/*    */     //   Java source line #96	-> byte code offset #74
/*    */     //   Java source line #97	-> byte code offset #75
/*    */     //   Java source line #98	-> byte code offset #81
/*    */     //   Java source line #99	-> byte code offset #86
/*    */     //   Java source line #97	-> byte code offset #88
/*    */     //   Java source line #98	-> byte code offset #94
/*    */     //   Java source line #100	-> byte code offset #99
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	100	0	this	FICSChannelParserTest
/*    */     //   74	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	74	74	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testMessage3()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_3
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSChannelEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   56: aload_0
/*    */     //   57: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   60: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   63: iconst_4
/*    */     //   64: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   67: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   70: aload_0
/*    */     //   71: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   74: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   77: iconst_5
/*    */     //   78: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   81: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   84: aload_0
/*    */     //   85: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   88: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   91: bipush 7
/*    */     //   93: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   96: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   99: aload_0
/*    */     //   100: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   103: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   106: bipush 8
/*    */     //   108: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   111: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   114: aload_0
/*    */     //   115: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSChannelEvent;
/*    */     //   118: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSChannelEvent:getChannel	()I
/*    */     //   121: bipush 49
/*    */     //   123: if_icmpne +7 -> 130
/*    */     //   126: iconst_1
/*    */     //   127: goto +4 -> 131
/*    */     //   130: iconst_0
/*    */     //   131: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:assertTrue	(Z)V
/*    */     //   134: goto +17 -> 151
/*    */     //   137: astore_1
/*    */     //   138: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   141: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   144: aload_0
/*    */     //   145: iconst_0
/*    */     //   146: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   149: aload_1
/*    */     //   150: athrow
/*    */     //   151: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   154: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   157: aload_0
/*    */     //   158: iconst_0
/*    */     //   159: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   162: return
/*    */     // Line number table:
/*    */     //   Java source line #105	-> byte code offset #0
/*    */     //   Java source line #106	-> byte code offset #7
/*    */     //   Java source line #107	-> byte code offset #13
/*    */     //   Java source line #111	-> byte code offset #21
/*    */     //   Java source line #112	-> byte code offset #41
/*    */     //   Java source line #116	-> byte code offset #56
/*    */     //   Java source line #117	-> byte code offset #70
/*    */     //   Java source line #118	-> byte code offset #84
/*    */     //   Java source line #119	-> byte code offset #99
/*    */     //   Java source line #120	-> byte code offset #114
/*    */     //   Java source line #124	-> byte code offset #137
/*    */     //   Java source line #125	-> byte code offset #138
/*    */     //   Java source line #126	-> byte code offset #144
/*    */     //   Java source line #127	-> byte code offset #149
/*    */     //   Java source line #125	-> byte code offset #151
/*    */     //   Java source line #126	-> byte code offset #157
/*    */     //   Java source line #128	-> byte code offset #162
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	163	0	this	FICSChannelParserTest
/*    */     //   137	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	137	137	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testParseAll()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 107	ictk/boardgame/chess/net/ics/event/ParserTest:testParseAll	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #133	-> byte code offset #0
/*    */     //   Java source line #134	-> byte code offset #7
/*    */     //   Java source line #135	-> byte code offset #13
/*    */     //   Java source line #138	-> byte code offset #21
/*    */     //   Java source line #140	-> byte code offset #28
/*    */     //   Java source line #141	-> byte code offset #29
/*    */     //   Java source line #142	-> byte code offset #35
/*    */     //   Java source line #143	-> byte code offset #40
/*    */     //   Java source line #141	-> byte code offset #42
/*    */     //   Java source line #142	-> byte code offset #48
/*    */     //   Java source line #144	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSChannelParserTest
/*    */     //   28	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	28	28	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testNative()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 110	ictk/boardgame/chess/net/ics/event/ParserTest:testNative	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 91	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSChannelParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #149	-> byte code offset #0
/*    */     //   Java source line #150	-> byte code offset #7
/*    */     //   Java source line #151	-> byte code offset #13
/*    */     //   Java source line #154	-> byte code offset #21
/*    */     //   Java source line #156	-> byte code offset #28
/*    */     //   Java source line #157	-> byte code offset #29
/*    */     //   Java source line #158	-> byte code offset #35
/*    */     //   Java source line #159	-> byte code offset #40
/*    */     //   Java source line #157	-> byte code offset #42
/*    */     //   Java source line #158	-> byte code offset #48
/*    */     //   Java source line #160	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSChannelParserTest
/*    */     //   28	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	28	28	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSChannelParserTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */