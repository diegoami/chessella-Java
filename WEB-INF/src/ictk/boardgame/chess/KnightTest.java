/*    */ package ictk.boardgame.chess;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class KnightTest
/*    */   extends TestCase
/*    */ {
/* 36 */   boolean DEBUG = false;
/*    */   ChessBoard board;
/*    */   ChessMove move;
/*    */   List list;
/*    */   Knight knight;
/*    */   
/*    */   public KnightTest(String name) {
/* 43 */     super(name);
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 47 */     this.board = new ChessBoard();
/*    */   }
/*    */   
/*    */   public void tearDown() {
/* 51 */     this.board = null;
/* 52 */     this.move = null;
/* 53 */     this.knight = null;
/* 54 */     this.DEBUG = false;
/*    */   }
/*    */   
/*    */   public void testFullMoveScope()
/*    */   {
/* 59 */     this.board.setPositionClear();
/* 60 */     this.board.addKnight(4, 4, false);
/* 61 */     this.board.addKing(1, 1, false);
/* 62 */     this.board.addKing(8, 1, true);
/* 63 */     this.knight = ((Knight)this.board.getSquare(4, 4).getOccupant());
/*    */     
/* 65 */     this.list = this.knight.getLegalDests();
/* 66 */     assertTrue(this.list.size() == 8);
/*    */     
/* 68 */     this.list.remove(this.board.getSquare('e', '6'));
/* 69 */     this.list.remove(this.board.getSquare('f', '5'));
/* 70 */     this.list.remove(this.board.getSquare('f', '3'));
/* 71 */     this.list.remove(this.board.getSquare('e', '2'));
/* 72 */     this.list.remove(this.board.getSquare('c', '2'));
/* 73 */     this.list.remove(this.board.getSquare('b', '3'));
/* 74 */     this.list.remove(this.board.getSquare('b', '5'));
/* 75 */     this.list.remove(this.board.getSquare('c', '6'));
/*    */     
/* 77 */     assertTrue(this.list.size() == 0);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\KnightTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */