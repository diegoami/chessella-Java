/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.event.ICSKibitzEvent;
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
/*    */ public class FICSKibitzParserTest
/*    */   extends ParserTest
/*    */ {
/*    */   ICSKibitzEvent evt;
/*    */   
/*    */   public FICSKibitzParserTest()
/*    */     throws IOException
/*    */   {
/* 39 */     super("ictk.boardgame.chess.net.ics.fics.event");
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 43 */     this.parser = FICSKibitzParser.getInstance();
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
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   26: aload_0
/*    */     //   27: getfield 57	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:mesg	[Ljava/lang/String;
/*    */     //   30: iconst_0
/*    */     //   31: aaload
/*    */     //   32: invokevirtual 61	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*    */     //   35: checkcast 65	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent
/*    */     //   38: putfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   41: aload_0
/*    */     //   42: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   45: ifnull +7 -> 52
/*    */     //   48: iconst_1
/*    */     //   49: goto +4 -> 53
/*    */     //   52: iconst_0
/*    */     //   53: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertTrue	(Z)V
/*    */     //   56: aload_0
/*    */     //   57: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   60: invokevirtual 70	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent:getPlayer	()Ljava/lang/String;
/*    */     //   63: ldc 74
/*    */     //   65: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   68: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertTrue	(Z)V
/*    */     //   71: aload_0
/*    */     //   72: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   75: invokevirtual 82	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent:getAccountType	()Lictk/boardgame/chess/net/ics/ICSAccountType;
/*    */     //   78: iconst_0
/*    */     //   79: invokevirtual 86	ictk/boardgame/chess/net/ics/ICSAccountType:is	(I)Z
/*    */     //   82: invokestatic 92	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertFalse	(Z)V
/*    */     //   85: aload_0
/*    */     //   86: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   89: invokevirtual 95	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent:getRating	()Lictk/boardgame/chess/net/ics/ICSRating;
/*    */     //   92: invokevirtual 99	ictk/boardgame/chess/net/ics/ICSRating:get	()I
/*    */     //   95: sipush 1902
/*    */     //   98: if_icmpne +7 -> 105
/*    */     //   101: iconst_1
/*    */     //   102: goto +4 -> 106
/*    */     //   105: iconst_0
/*    */     //   106: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertTrue	(Z)V
/*    */     //   109: aload_0
/*    */     //   110: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   113: invokevirtual 105	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent:getBoardNumber	()I
/*    */     //   116: bipush 7
/*    */     //   118: if_icmpne +7 -> 125
/*    */     //   121: iconst_1
/*    */     //   122: goto +4 -> 126
/*    */     //   125: iconst_0
/*    */     //   126: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertTrue	(Z)V
/*    */     //   129: aload_0
/*    */     //   130: getfield 34	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:evt	Lictk/boardgame/chess/net/ics/event/ICSKibitzEvent;
/*    */     //   133: invokevirtual 108	ictk/boardgame/chess/net/ics/event/ICSKibitzEvent:getMessage	()Ljava/lang/String;
/*    */     //   136: ldc 111
/*    */     //   138: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   141: invokestatic 67	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:assertTrue	(Z)V
/*    */     //   144: goto +17 -> 161
/*    */     //   147: astore_1
/*    */     //   148: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   151: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   154: aload_0
/*    */     //   155: iconst_0
/*    */     //   156: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   159: aload_1
/*    */     //   160: athrow
/*    */     //   161: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   164: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   167: aload_0
/*    */     //   168: iconst_0
/*    */     //   169: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   172: return
/*    */     // Line number table:
/*    */     //   Java source line #55	-> byte code offset #0
/*    */     //   Java source line #56	-> byte code offset #7
/*    */     //   Java source line #57	-> byte code offset #13
/*    */     //   Java source line #61	-> byte code offset #21
/*    */     //   Java source line #62	-> byte code offset #41
/*    */     //   Java source line #66	-> byte code offset #56
/*    */     //   Java source line #67	-> byte code offset #71
/*    */     //   Java source line #68	-> byte code offset #85
/*    */     //   Java source line #69	-> byte code offset #109
/*    */     //   Java source line #70	-> byte code offset #129
/*    */     //   Java source line #74	-> byte code offset #147
/*    */     //   Java source line #75	-> byte code offset #148
/*    */     //   Java source line #76	-> byte code offset #154
/*    */     //   Java source line #77	-> byte code offset #159
/*    */     //   Java source line #75	-> byte code offset #161
/*    */     //   Java source line #76	-> byte code offset #167
/*    */     //   Java source line #78	-> byte code offset #172
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	173	0	this	FICSKibitzParserTest
/*    */     //   147	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	147	147	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testParseAll()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 117	ictk/boardgame/chess/net/ics/event/ParserTest:testParseAll	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #83	-> byte code offset #0
/*    */     //   Java source line #84	-> byte code offset #7
/*    */     //   Java source line #85	-> byte code offset #13
/*    */     //   Java source line #88	-> byte code offset #21
/*    */     //   Java source line #90	-> byte code offset #28
/*    */     //   Java source line #91	-> byte code offset #29
/*    */     //   Java source line #92	-> byte code offset #35
/*    */     //   Java source line #93	-> byte code offset #40
/*    */     //   Java source line #91	-> byte code offset #42
/*    */     //   Java source line #92	-> byte code offset #48
/*    */     //   Java source line #94	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSKibitzParserTest
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
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 120	ictk/boardgame/chess/net/ics/event/ParserTest:testNative	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 113	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSKibitzParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #99	-> byte code offset #0
/*    */     //   Java source line #100	-> byte code offset #7
/*    */     //   Java source line #101	-> byte code offset #13
/*    */     //   Java source line #104	-> byte code offset #21
/*    */     //   Java source line #106	-> byte code offset #28
/*    */     //   Java source line #107	-> byte code offset #29
/*    */     //   Java source line #108	-> byte code offset #35
/*    */     //   Java source line #109	-> byte code offset #40
/*    */     //   Java source line #107	-> byte code offset #42
/*    */     //   Java source line #108	-> byte code offset #48
/*    */     //   Java source line #110	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSKibitzParserTest
/*    */     //   28	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	28	28	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSKibitzParserTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */