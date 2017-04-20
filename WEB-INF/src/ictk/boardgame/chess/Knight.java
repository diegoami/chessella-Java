/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class Knight
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 4;
/*     */   protected static final int MAX_LEGAL_DESTS = 8;
/*     */   protected static final int MAX_GUARDS = 8;
/*     */   
/*     */   public Knight()
/*     */   {
/*  35 */     super((byte)4, true, 8, 8);
/*     */   }
/*     */   
/*     */   public Knight(boolean blackness) {
/*  39 */     super((byte)4, blackness, 8, 8);
/*     */   }
/*     */   
/*     */   public Knight(boolean blackness, Square o, ChessBoard _board) {
/*  43 */     super((byte)4, blackness, o, _board, 8, 8);
/*     */   }
/*     */   
/*  46 */   protected String getName() { return "Knight"; }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/*  52 */     super.genLegalDests();
/*     */     
/*     */ 
/*     */ 
/*  56 */     if (this.captured) { return 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  62 */     byte f = (byte)(this.orig.file + 1);
/*  63 */     byte r = (byte)(this.orig.rank + 2);
/*  64 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  65 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  68 */     f = (byte)(this.orig.file + 2);
/*  69 */     r = (byte)(this.orig.rank + 1);
/*  70 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  71 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  74 */     f = (byte)(this.orig.file + 2);
/*  75 */     r = (byte)(this.orig.rank - 1);
/*  76 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  77 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  80 */     f = (byte)(this.orig.file + 1);
/*  81 */     r = (byte)(this.orig.rank - 2);
/*  82 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  83 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  86 */     f = (byte)(this.orig.file - 1);
/*  87 */     r = (byte)(this.orig.rank - 2);
/*  88 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  89 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  92 */     f = (byte)(this.orig.file - 2);
/*  93 */     r = (byte)(this.orig.rank - 1);
/*  94 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  95 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/*  98 */     f = (byte)(this.orig.file - 2);
/*  99 */     r = (byte)(this.orig.rank + 1);
/* 100 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/* 101 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/*     */     
/* 104 */     f = (byte)(this.orig.file - 1);
/* 105 */     r = (byte)(this.orig.rank + 2);
/* 106 */     if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/* 107 */       addLegalDest(this.board.getSquare(f, r));
/*     */     }
/* 109 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */   public boolean isBlockable(Square target)
/*     */   {
/* 114 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlockable(Square blocker, ChessPiece target)
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */   
/* 123 */   public boolean isKnight() { return true; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Knight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */