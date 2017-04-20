/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import java.io.PrintStream;
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
/*     */ public class PawnTest
/*     */   extends TestCase
/*     */ {
/*  36 */   boolean DEBUG = false;
/*     */   ChessBoard board;
/*     */   ChessMove move;
/*     */   Pawn pawn;
/*     */   List list;
/*     */   
/*     */   public PawnTest(String name) {
/*  43 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp()
/*     */   {
/*  48 */     this.board = new ChessBoard();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  52 */     this.board = null;
/*  53 */     this.move = null;
/*  54 */     this.pawn = null;
/*  55 */     this.DEBUG = false;
/*     */   }
/*     */   
/*     */   public void testDefaultAFile()
/*     */   {
/*  60 */     this.pawn = ((Pawn)this.board.getSquare('a', '2').getOccupant());
/*  61 */     assertTrue(!this.pawn.isBlack());
/*  62 */     this.list = this.pawn.getLegalDests();
/*  63 */     assertTrue(this.list.size() == 2);
/*  64 */     this.list.remove(this.board.getSquare('a', '3'));
/*  65 */     this.list.remove(this.board.getSquare('a', '4'));
/*  66 */     assertTrue(this.list.size() == 0);
/*  67 */     assertTrue(!this.pawn.hasMoved());
/*     */   }
/*     */   
/*     */   public void testDefaultBFileOpposition()
/*     */   {
/*  72 */     this.board.setBlackMove(true);
/*  73 */     this.pawn = ((Pawn)this.board.getSquare('b', '2').getOccupant());
/*  74 */     assertTrue(!this.pawn.isBlack());
/*  75 */     assertTrue(this.pawn.isLegalAttack(this.board.getSquare('a', '3')));
/*  76 */     assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '3')));
/*  77 */     assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '4')));
/*  78 */     assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '5')));
/*  79 */     assertTrue(this.pawn.isLegalAttack(this.board.getSquare('c', '3')));
/*  80 */     this.list = this.pawn.getLegalDests();
/*  81 */     assertTrue(this.list.size() == 4);
/*  82 */     this.list.remove(this.board.getSquare('a', '3'));
/*  83 */     this.list.remove(this.board.getSquare('b', '3'));
/*  84 */     this.list.remove(this.board.getSquare('b', '4'));
/*  85 */     this.list.remove(this.board.getSquare('c', '3'));
/*  86 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */   public void testEnPassant() throws IllegalMoveException
/*     */   {
/*  91 */     this.DEBUG = false;
/*  92 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*  93 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  94 */       { 'B', 'P', ' ', 'p', ' ', ' ', ' ', 'b' }, 
/*  95 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*  96 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*  97 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  98 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  99 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 101 */     this.board.setPosition(position);
/* 102 */     this.board.playMove(new ChessMove(this.board, 2, 2, 2, 4, 0));
/* 103 */     assertTrue(this.board.isEnPassantFile('b'));
/*     */     
/* 105 */     this.pawn = ((Pawn)this.board.getSquare('c', '4').getOccupant());
/*     */     
/* 107 */     if (this.DEBUG) {
/* 108 */       System.err.println(this.board);
/* 109 */       System.err.println(this.pawn.dump());
/*     */     }
/*     */     
/* 112 */     assertTrue(this.pawn.isBlack());
/* 113 */     assertTrue(this.pawn.isLegalAttack(this.board.getSquare('b', '3')));
/* 114 */     assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('d', '3')));
/* 115 */     this.list = this.pawn.getLegalDests();
/* 116 */     assertTrue(this.list.size() == 2);
/* 117 */     this.list.remove(this.board.getSquare('b', '3'));
/* 118 */     this.list.remove(this.board.getSquare('c', '3'));
/* 119 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */   public void testCapture() throws IllegalMoveException
/*     */   {
/* 124 */     this.DEBUG = false;
/* 125 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 126 */       { 'N', ' ', 'P', ' ', ' ', ' ', 'p', 'n' }, 
/* 127 */       { 'B', 'P', ' ', 'p', ' ', ' ', ' ', 'b' }, 
/* 128 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 129 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 130 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 131 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 132 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 134 */     this.board.setPosition(position);
/*     */     
/* 136 */     this.pawn = ((Pawn)this.board.getSquare('b', '3').getOccupant());
/*     */     
/* 138 */     if (this.DEBUG) {
/* 139 */       System.err.println(this.board);
/* 140 */       System.err.println(this.pawn.dump());
/*     */     }
/*     */     
/* 143 */     assertTrue(!this.pawn.isBlack());
/* 144 */     assertTrue(this.pawn.isLegalAttack(this.board.getSquare('c', '4')));
/* 145 */     assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '4')));
/* 146 */     this.list = this.pawn.getLegalDests();
/* 147 */     assertTrue(this.list.size() == 2);
/* 148 */     this.list.remove(this.board.getSquare('c', '4'));
/* 149 */     this.list.remove(this.board.getSquare('b', '4'));
/* 150 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\PawnTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */