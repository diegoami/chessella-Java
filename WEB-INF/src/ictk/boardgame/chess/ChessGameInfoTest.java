/*    */ package ictk.boardgame.chess;
/*    */ 
/*    */ import ictk.util.Log;
/*    */ import junit.framework.TestCase;
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
/*    */ 
/*    */ public class ChessGameInfoTest
/*    */   extends TestCase
/*    */ {
/*    */   ChessGameInfo gi;
/*    */   
/*    */   public ChessGameInfoTest(String name)
/*    */   {
/* 38 */     super(name);
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 42 */     this.gi = new ChessGameInfo();
/*    */   }
/*    */   
/*    */   public void tearDown() {
/* 46 */     this.gi = null;
/* 47 */     Log.removeMask(ChessGameInfo.DEBUG);
/*    */   }
/*    */   
/*    */   public void testEquality()
/*    */   {
/* 52 */     this.gi.add("foo", "bar");
/* 53 */     ChessGameInfo gi2 = new ChessGameInfo();
/* 54 */     gi2.add("foo", "bar");
/*    */     
/* 56 */     assertTrue(this.gi.equals(gi2));
/* 57 */     assertTrue(gi2.equals(this.gi));
/*    */   }
/*    */   
/*    */   public void testEqualityNot()
/*    */   {
/* 62 */     this.gi.add("foo", "bar");
/* 63 */     ChessGameInfo gi2 = new ChessGameInfo();
/* 64 */     gi2.add("foo", "baz");
/*    */     
/* 66 */     assertFalse(this.gi.equals(gi2));
/* 67 */     assertFalse(gi2.equals(this.gi));
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessGameInfoTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */