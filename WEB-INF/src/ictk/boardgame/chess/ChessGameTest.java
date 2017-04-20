/*    */ package ictk.boardgame.chess;
/*    */ 
/*    */ import ictk.boardgame.GameInfo;
/*    */ import ictk.boardgame.History;
/*    */ import ictk.boardgame.Result;
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
/*    */ public class ChessGameTest
/*    */   extends TestCase
/*    */ {
/*    */   ChessGame game;
/*    */   History history;
/*    */   ChessBoard board;
/*    */   GameInfo gi;
/*    */   
/*    */   public ChessGameTest(String name)
/*    */   {
/* 41 */     super(name);
/*    */   }
/*    */   
/*    */   public void setUp() {}
/*    */   
/*    */   public void tearDown()
/*    */   {
/* 48 */     this.game = null;
/* 49 */     this.history = null;
/* 50 */     this.board = null;
/* 51 */     this.gi = null;
/*    */   }
/*    */   
/*    */   public void testConstructor()
/*    */   {
/* 56 */     this.game = new ChessGame();
/*    */     
/* 58 */     assertTrue(this.game.getBoard() != null);
/* 59 */     assertTrue(this.game.getHistory() != null);
/*    */   }
/*    */   
/*    */   public void testPlayerToMove()
/*    */   {
/* 64 */     this.game = new ChessGame();
/*    */     
/* 66 */     assertTrue(this.game.getPlayerToMove() == 0);
/*    */   }
/*    */   
/*    */   public void testResutl()
/*    */   {
/* 71 */     this.game = new ChessGame();
/*    */     
/* 73 */     assertTrue(this.game.getResult().isUndecided());
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessGameTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */