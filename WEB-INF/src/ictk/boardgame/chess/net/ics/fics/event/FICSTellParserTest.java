/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.event.ICSTellEvent;
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
/*    */ public class FICSTellParserTest
/*    */   extends ParserTest
/*    */ {
/*    */   ICSTellEvent evt;
/*    */   
/*    */   public FICSTellParserTest()
/*    */     throws IOException
/*    */   {
/* 39 */     super("ictk.boardgame.chess.net.ics.fics.event");
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 43 */     this.parser = FICSTellParser.getInstance();
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
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_0
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSTellEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   56: aload_0
/*    */     //   57: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   60: invokevirtual 70	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getPlayer	()Ljava/lang/String;
/*    */     //   63: ldc 74
/*    */     //   65: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   68: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   71: aload_0
/*    */     //   72: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   75: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getMessage	()Ljava/lang/String;
/*    */     //   78: ldc 85
/*    */     //   80: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   83: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   86: aload_0
/*    */     //   87: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   90: invokevirtual 87	ictk/boardgame/chess/net/ics/event/ICSTellEvent:isFake	()Z
/*    */     //   93: invokestatic 91	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertFalse	(Z)V
/*    */     //   96: aload_0
/*    */     //   97: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   100: invokevirtual 94	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getEventType	()I
/*    */     //   103: bipush 9
/*    */     //   105: if_icmpne +7 -> 112
/*    */     //   108: iconst_1
/*    */     //   109: goto +4 -> 113
/*    */     //   112: iconst_0
/*    */     //   113: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   116: aload_0
/*    */     //   117: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   120: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getMessage	()Ljava/lang/String;
/*    */     //   123: ldc 98
/*    */     //   125: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   128: invokestatic 91	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertFalse	(Z)V
/*    */     //   131: goto +17 -> 148
/*    */     //   134: astore_1
/*    */     //   135: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   138: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   141: aload_0
/*    */     //   142: iconst_0
/*    */     //   143: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   146: aload_1
/*    */     //   147: athrow
/*    */     //   148: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   151: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   154: aload_0
/*    */     //   155: iconst_0
/*    */     //   156: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   159: return
/*    */     // Line number table:
/*    */     //   Java source line #55	-> byte code offset #0
/*    */     //   Java source line #56	-> byte code offset #7
/*    */     //   Java source line #57	-> byte code offset #13
/*    */     //   Java source line #61	-> byte code offset #21
/*    */     //   Java source line #62	-> byte code offset #41
/*    */     //   Java source line #66	-> byte code offset #56
/*    */     //   Java source line #67	-> byte code offset #71
/*    */     //   Java source line #68	-> byte code offset #86
/*    */     //   Java source line #69	-> byte code offset #96
/*    */     //   Java source line #71	-> byte code offset #116
/*    */     //   Java source line #75	-> byte code offset #134
/*    */     //   Java source line #76	-> byte code offset #135
/*    */     //   Java source line #77	-> byte code offset #141
/*    */     //   Java source line #78	-> byte code offset #146
/*    */     //   Java source line #76	-> byte code offset #148
/*    */     //   Java source line #77	-> byte code offset #154
/*    */     //   Java source line #79	-> byte code offset #159
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	160	0	this	FICSTellParserTest
/*    */     //   134	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	134	134	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testMessage1()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_1
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSTellEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   56: aload_0
/*    */     //   57: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   60: invokevirtual 70	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getPlayer	()Ljava/lang/String;
/*    */     //   63: ldc 74
/*    */     //   65: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   68: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   71: aload_0
/*    */     //   72: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   75: invokevirtual 104	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   78: iconst_1
/*    */     //   79: invokevirtual 108	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   82: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   85: aload_0
/*    */     //   86: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   89: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getMessage	()Ljava/lang/String;
/*    */     //   92: ldc 85
/*    */     //   94: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   97: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   100: aload_0
/*    */     //   101: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   104: invokevirtual 87	ictk/boardgame/chess/net/ics/event/ICSTellEvent:isFake	()Z
/*    */     //   107: invokestatic 91	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertFalse	(Z)V
/*    */     //   110: aload_0
/*    */     //   111: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   114: invokevirtual 94	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getEventType	()I
/*    */     //   117: bipush 9
/*    */     //   119: if_icmpne +7 -> 126
/*    */     //   122: iconst_1
/*    */     //   123: goto +4 -> 127
/*    */     //   126: iconst_0
/*    */     //   127: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertTrue	(Z)V
/*    */     //   130: aload_0
/*    */     //   131: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSTellEvent;
/*    */     //   134: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSTellEvent:getMessage	()Ljava/lang/String;
/*    */     //   137: ldc 98
/*    */     //   139: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   142: invokestatic 91	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:assertFalse	(Z)V
/*    */     //   145: goto +17 -> 162
/*    */     //   148: astore_1
/*    */     //   149: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   152: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   155: aload_0
/*    */     //   156: iconst_0
/*    */     //   157: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   160: aload_1
/*    */     //   161: athrow
/*    */     //   162: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   165: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   168: aload_0
/*    */     //   169: iconst_0
/*    */     //   170: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   173: return
/*    */     // Line number table:
/*    */     //   Java source line #84	-> byte code offset #0
/*    */     //   Java source line #85	-> byte code offset #7
/*    */     //   Java source line #86	-> byte code offset #13
/*    */     //   Java source line #90	-> byte code offset #21
/*    */     //   Java source line #91	-> byte code offset #41
/*    */     //   Java source line #95	-> byte code offset #56
/*    */     //   Java source line #96	-> byte code offset #71
/*    */     //   Java source line #97	-> byte code offset #85
/*    */     //   Java source line #98	-> byte code offset #100
/*    */     //   Java source line #99	-> byte code offset #110
/*    */     //   Java source line #101	-> byte code offset #130
/*    */     //   Java source line #105	-> byte code offset #148
/*    */     //   Java source line #106	-> byte code offset #149
/*    */     //   Java source line #107	-> byte code offset #155
/*    */     //   Java source line #108	-> byte code offset #160
/*    */     //   Java source line #106	-> byte code offset #162
/*    */     //   Java source line #107	-> byte code offset #168
/*    */     //   Java source line #109	-> byte code offset #173
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	174	0	this	FICSTellParserTest
/*    */     //   148	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	148	148	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testParseAll()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 115	ictk/boardgame/chess/net/ics/event/ParserTest:testParseAll	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #114	-> byte code offset #0
/*    */     //   Java source line #115	-> byte code offset #7
/*    */     //   Java source line #116	-> byte code offset #13
/*    */     //   Java source line #119	-> byte code offset #21
/*    */     //   Java source line #121	-> byte code offset #28
/*    */     //   Java source line #122	-> byte code offset #29
/*    */     //   Java source line #123	-> byte code offset #35
/*    */     //   Java source line #124	-> byte code offset #40
/*    */     //   Java source line #122	-> byte code offset #42
/*    */     //   Java source line #123	-> byte code offset #48
/*    */     //   Java source line #125	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSTellParserTest
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
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 118	ictk/boardgame/chess/net/ics/event/ParserTest:testNative	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 100	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSTellParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #130	-> byte code offset #0
/*    */     //   Java source line #131	-> byte code offset #7
/*    */     //   Java source line #132	-> byte code offset #13
/*    */     //   Java source line #135	-> byte code offset #21
/*    */     //   Java source line #137	-> byte code offset #28
/*    */     //   Java source line #138	-> byte code offset #29
/*    */     //   Java source line #139	-> byte code offset #35
/*    */     //   Java source line #140	-> byte code offset #40
/*    */     //   Java source line #138	-> byte code offset #42
/*    */     //   Java source line #139	-> byte code offset #48
/*    */     //   Java source line #141	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSTellParserTest
/*    */     //   28	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	28	28	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSTellParserTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */