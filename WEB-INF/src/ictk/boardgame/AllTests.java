/*    */ package ictk.boardgame;
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
/* 37 */     TestSuite suite = new TestSuite("BoardGame Test");
/* 38 */     suite.addTest(new TestSuite(ContinuationListTest.class));
/* 39 */     suite.addTest(new TestSuite(HistoryTest.class));
/* 40 */     return suite;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\AllTests.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */