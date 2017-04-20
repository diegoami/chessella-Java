/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.event.ICSGameNotificationEvent;
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
/*    */ public class FICSGameNotificationParserTest
/*    */   extends ParserTest
/*    */ {
/*    */   ICSGameNotificationEvent evt;
/*    */   
/*    */   public FICSGameNotificationParserTest()
/*    */     throws IOException
/*    */   {
/* 39 */     super("ictk.boardgame.chess.net.ics.fics.event");
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 43 */     this.parser = FICSGameNotificationParser.getInstance();
/*    */   }
/*    */   
/*    */   public void tearDown()
/*    */   {
/* 48 */     this.evt = null;
/* 49 */     this.parser = null;
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testParseAll()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 57	ictk/boardgame/chess/net/ics/event/ParserTest:testParseAll	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 59	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 59	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #55	-> byte code offset #0
/*    */     //   Java source line #56	-> byte code offset #7
/*    */     //   Java source line #57	-> byte code offset #13
/*    */     //   Java source line #60	-> byte code offset #21
/*    */     //   Java source line #62	-> byte code offset #28
/*    */     //   Java source line #63	-> byte code offset #29
/*    */     //   Java source line #64	-> byte code offset #35
/*    */     //   Java source line #65	-> byte code offset #40
/*    */     //   Java source line #63	-> byte code offset #42
/*    */     //   Java source line #64	-> byte code offset #48
/*    */     //   Java source line #66	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSGameNotificationParserTest
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
/*    */     //   1: getfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   4: ifeq +17 -> 21
/*    */     //   7: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   10: invokestatic 47	ictk/util/Log:addMask	(J)V
/*    */     //   13: aload_0
/*    */     //   14: getfield 29	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*    */     //   17: iconst_1
/*    */     //   18: invokevirtual 53	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*    */     //   21: aload_0
/*    */     //   22: invokespecial 63	ictk/boardgame/chess/net/ics/event/ParserTest:testNative	()V
/*    */     //   25: goto +17 -> 42
/*    */     //   28: astore_1
/*    */     //   29: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   32: invokestatic 59	ictk/util/Log:removeMask	(J)V
/*    */     //   35: aload_0
/*    */     //   36: iconst_0
/*    */     //   37: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   40: aload_1
/*    */     //   41: athrow
/*    */     //   42: getstatic 41	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*    */     //   45: invokestatic 59	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_0
/*    */     //   49: iconst_0
/*    */     //   50: putfield 37	ictk/boardgame/chess/net/ics/fics/event/FICSGameNotificationParserTest:debug	Z
/*    */     //   53: return
/*    */     // Line number table:
/*    */     //   Java source line #71	-> byte code offset #0
/*    */     //   Java source line #72	-> byte code offset #7
/*    */     //   Java source line #73	-> byte code offset #13
/*    */     //   Java source line #76	-> byte code offset #21
/*    */     //   Java source line #78	-> byte code offset #28
/*    */     //   Java source line #79	-> byte code offset #29
/*    */     //   Java source line #80	-> byte code offset #35
/*    */     //   Java source line #81	-> byte code offset #40
/*    */     //   Java source line #79	-> byte code offset #42
/*    */     //   Java source line #80	-> byte code offset #48
/*    */     //   Java source line #82	-> byte code offset #53
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	54	0	this	FICSGameNotificationParserTest
/*    */     //   28	13	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   21	28	28	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSGameNotificationParserTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */