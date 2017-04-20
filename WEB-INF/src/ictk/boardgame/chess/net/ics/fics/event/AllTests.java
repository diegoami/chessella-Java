/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestSuite;
/*    */ import junit.textui.TestRunner;
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
/*    */ public class AllTests
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 33 */     TestRunner.run(suite());
/*    */   }
/*    */   
/*    */   public static Test suite() {
/* 37 */     TestSuite suite = new TestSuite("FICS event parsers");
/*    */     
/* 39 */     suite.addTest(new TestSuite(FICSKibitzParserTest.class));
/* 40 */     suite.addTest(new TestSuite(FICSChannelParserTest.class));
/* 41 */     suite.addTest(new TestSuite(FICSGameCreatedParserTest.class));
/* 42 */     suite.addTest(new TestSuite(FICSGameNotificationParserTest.class));
/* 43 */     suite.addTest(new TestSuite(FICSGameResultParserTest.class));
/* 44 */     suite.addTest(new TestSuite(FICSPlayerConnectionParserTest.class));
/* 45 */     suite.addTest(new TestSuite(FICSPlayerNotificationParserTest.class));
/* 46 */     suite.addTest(new TestSuite(FICSSeekAdParserTest.class));
/* 47 */     suite.addTest(new TestSuite(FICSSeekAdReadableParserTest.class));
/* 48 */     suite.addTest(new TestSuite(FICSSeekClearParserTest.class));
/* 49 */     suite.addTest(new TestSuite(FICSSeekRemoveParserTest.class));
/* 50 */     suite.addTest(new TestSuite(FICSShoutParserTest.class));
/* 51 */     suite.addTest(new TestSuite(FICSTellParserTest.class));
/* 52 */     suite.addTest(new TestSuite(FICSMoveListParserTest.class));
/* 53 */     suite.addTest(new TestSuite(FICSBoardUpdateStyle12ParserTest.class));
/*    */     
/* 55 */     return suite;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\AllTests.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */