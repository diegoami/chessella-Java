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
/*    */ public class RookTest
/*    */   extends TestCase
/*    */ {
/* 36 */   boolean DEBUG = false;
/*    */   ChessBoard board;
/*    */   ChessMove move;
/*    */   List list;
/*    */   Rook rook;
/*    */   
/*    */   public RookTest(String name) {
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
/* 53 */     this.rook = null;
/* 54 */     this.DEBUG = false;
/*    */   }
/*    */   
/*    */   public void testFullMoveScope()
/*    */   {
/* 59 */     this.board.setPositionClear();
/* 60 */     this.board.addRook(4, 4, false);
/* 61 */     this.board.addKing(1, 1, false);
/* 62 */     this.board.addKing(8, 1, true);
/* 63 */     this.rook = ((Rook)this.board.getSquare(4, 4).getOccupant());
/*    */     
/* 65 */     this.list = this.rook.getLegalDests();
/* 66 */     assertTrue(this.list.size() == 14);
/*    */     
/* 68 */     this.list.remove(this.board.getSquare('a', '4'));
/* 69 */     this.list.remove(this.board.getSquare('b', '4'));
/* 70 */     this.list.remove(this.board.getSquare('c', '4'));
/* 71 */     this.list.remove(this.board.getSquare('e', '4'));
/* 72 */     this.list.remove(this.board.getSquare('f', '4'));
/* 73 */     this.list.remove(this.board.getSquare('g', '4'));
/* 74 */     this.list.remove(this.board.getSquare('h', '4'));
/*    */     
/* 76 */     this.list.remove(this.board.getSquare('d', '1'));
/* 77 */     this.list.remove(this.board.getSquare('d', '2'));
/* 78 */     this.list.remove(this.board.getSquare('d', '3'));
/* 79 */     this.list.remove(this.board.getSquare('d', '5'));
/* 80 */     this.list.remove(this.board.getSquare('d', '6'));
/* 81 */     this.list.remove(this.board.getSquare('d', '7'));
/* 82 */     this.list.remove(this.board.getSquare('d', '8'));
/*    */     
/* 84 */     assertTrue(this.list.size() == 0);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\RookTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */