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
/*    */ public class BishopTest
/*    */   extends TestCase
/*    */ {
/* 36 */   boolean DEBUG = false;
/*    */   ChessBoard board;
/*    */   ChessMove move;
/*    */   List list;
/*    */   Bishop bishop;
/*    */   
/*    */   public BishopTest(String name) {
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
/* 53 */     this.bishop = null;
/* 54 */     this.DEBUG = false;
/*    */   }
/*    */   
/*    */   public void testFullMoveScope()
/*    */   {
/* 59 */     this.board.setPositionClear();
/* 60 */     this.board.addBishop(4, 4, false);
/* 61 */     this.board.addKing(4, 1, false);
/* 62 */     this.board.addKing(4, 1, true);
/* 63 */     this.bishop = ((Bishop)this.board.getSquare(4, 4).getOccupant());
/*    */     
/* 65 */     this.list = this.bishop.getLegalDests();
/* 66 */     assertTrue(this.list.size() == 13);
/*    */     
/* 68 */     this.list.remove(this.board.getSquare('a', '1'));
/* 69 */     this.list.remove(this.board.getSquare('b', '2'));
/* 70 */     this.list.remove(this.board.getSquare('c', '3'));
/* 71 */     this.list.remove(this.board.getSquare('e', '5'));
/* 72 */     this.list.remove(this.board.getSquare('f', '6'));
/* 73 */     this.list.remove(this.board.getSquare('g', '7'));
/* 74 */     this.list.remove(this.board.getSquare('h', '8'));
/*    */     
/* 76 */     this.list.remove(this.board.getSquare('a', '7'));
/* 77 */     this.list.remove(this.board.getSquare('b', '6'));
/* 78 */     this.list.remove(this.board.getSquare('c', '5'));
/* 79 */     this.list.remove(this.board.getSquare('e', '3'));
/* 80 */     this.list.remove(this.board.getSquare('f', '2'));
/* 81 */     this.list.remove(this.board.getSquare('g', '1'));
/*    */     
/* 83 */     assertTrue(this.list.size() == 0);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\BishopTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */