/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import java.util.List;
/*     */ import junit.framework.TestCase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueenTest
/*     */   extends TestCase
/*     */ {
/*  36 */   boolean DEBUG = false;
/*     */   ChessBoard board;
/*     */   ChessMove move;
/*     */   List list;
/*     */   Queen queen;
/*     */   
/*     */   public QueenTest(String name) {
/*  43 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  47 */     this.board = new ChessBoard();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  51 */     this.board = null;
/*  52 */     this.move = null;
/*  53 */     this.queen = null;
/*  54 */     this.DEBUG = false;
/*     */   }
/*     */   
/*     */   public void testFullMoveScope()
/*     */   {
/*  59 */     this.board.setPositionClear();
/*  60 */     this.board.addQueen(4, 4, false);
/*  61 */     this.board.addKing(1, 2, false);
/*  62 */     this.board.addKing(8, 2, true);
/*  63 */     this.queen = ((Queen)this.board.getSquare(4, 4).getOccupant());
/*     */     
/*  65 */     this.list = this.queen.getLegalDests();
/*  66 */     assertTrue(this.list.size() == 27);
/*     */     
/*  68 */     this.list.remove(this.board.getSquare('a', '1'));
/*  69 */     this.list.remove(this.board.getSquare('b', '2'));
/*  70 */     this.list.remove(this.board.getSquare('c', '3'));
/*  71 */     this.list.remove(this.board.getSquare('e', '5'));
/*  72 */     this.list.remove(this.board.getSquare('f', '6'));
/*  73 */     this.list.remove(this.board.getSquare('g', '7'));
/*  74 */     this.list.remove(this.board.getSquare('h', '8'));
/*     */     
/*  76 */     this.list.remove(this.board.getSquare('a', '7'));
/*  77 */     this.list.remove(this.board.getSquare('b', '6'));
/*  78 */     this.list.remove(this.board.getSquare('c', '5'));
/*  79 */     this.list.remove(this.board.getSquare('e', '3'));
/*  80 */     this.list.remove(this.board.getSquare('f', '2'));
/*  81 */     this.list.remove(this.board.getSquare('g', '1'));
/*     */     
/*     */ 
/*  84 */     this.list.remove(this.board.getSquare('a', '4'));
/*  85 */     this.list.remove(this.board.getSquare('b', '4'));
/*  86 */     this.list.remove(this.board.getSquare('c', '4'));
/*  87 */     this.list.remove(this.board.getSquare('e', '4'));
/*  88 */     this.list.remove(this.board.getSquare('f', '4'));
/*  89 */     this.list.remove(this.board.getSquare('g', '4'));
/*  90 */     this.list.remove(this.board.getSquare('h', '4'));
/*     */     
/*  92 */     this.list.remove(this.board.getSquare('d', '1'));
/*  93 */     this.list.remove(this.board.getSquare('d', '2'));
/*  94 */     this.list.remove(this.board.getSquare('d', '3'));
/*  95 */     this.list.remove(this.board.getSquare('d', '5'));
/*  96 */     this.list.remove(this.board.getSquare('d', '6'));
/*  97 */     this.list.remove(this.board.getSquare('d', '7'));
/*  98 */     this.list.remove(this.board.getSquare('d', '8'));
/*     */     
/* 100 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\QueenTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */