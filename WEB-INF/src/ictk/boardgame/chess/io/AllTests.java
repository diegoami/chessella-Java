/*    */ package ictk.boardgame.chess.io;
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
/* 37 */     TestSuite suite = new TestSuite("ictk.chess.io Test");
/* 38 */     suite.addTest(new TestSuite(NAGTest.class));
/* 39 */     suite.addTest(new TestSuite(ChessAnnotationTest.class));
/* 40 */     suite.addTest(new TestSuite(SANTest.class));
/* 41 */     suite.addTest(new TestSuite(FENTest.class));
/* 42 */     suite.addTest(new TestSuite(PGNReaderTest.class));
/* 43 */     suite.addTest(new TestSuite(PGNWriterTest.class));
/* 44 */     return suite;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\AllTests.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */