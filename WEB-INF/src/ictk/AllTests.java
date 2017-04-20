/*    */ package ictk;
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
/* 37 */     String dataDir = null;
/*    */     
/* 39 */     TestSuite suite = new TestSuite("ictk Test");
/* 40 */     suite.addTest(ictk.boardgame.AllTests.suite());
/* 41 */     suite.addTest(ictk.boardgame.chess.AllTests.suite());
/* 42 */     suite.addTest(ictk.boardgame.chess.io.AllTests.suite());
/*    */     
/* 44 */     return suite;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\AllTests.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */