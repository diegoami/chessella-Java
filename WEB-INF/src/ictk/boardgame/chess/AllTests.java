/*    */ package ictk.boardgame.chess;
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
/* 37 */     TestSuite suite = new TestSuite("Chess Test");
/* 38 */     suite.addTest(new TestSuite(ChessGameTest.class));
/* 39 */     suite.addTest(new TestSuite(ChessGameInfoTest.class));
/* 40 */     suite.addTest(new TestSuite(ChessBoardTest.class));
/* 41 */     suite.addTest(new TestSuite(ChessMoveTest.class));
/* 42 */     suite.addTest(new TestSuite(PawnTest.class));
/* 43 */     suite.addTest(new TestSuite(KnightTest.class));
/* 44 */     suite.addTest(new TestSuite(BishopTest.class));
/* 45 */     suite.addTest(new TestSuite(RookTest.class));
/* 46 */     suite.addTest(new TestSuite(QueenTest.class));
/* 47 */     suite.addTest(new TestSuite(KingTest.class));
/* 48 */     return suite;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\AllTests.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */