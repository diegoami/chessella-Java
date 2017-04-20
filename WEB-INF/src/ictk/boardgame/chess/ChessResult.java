/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChessResult
/*     */   implements Result
/*     */ {
/*     */   public static final int UNDECIDED = 0;
/*     */   public static final int DRAW = 1;
/*     */   public static final int WHITE_WIN = 2;
/*     */   public static final int BLACK_WIN = 3;
/*  44 */   protected int index = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChessResult(int i)
/*     */   {
/*  51 */     this.index = i;
/*     */   }
/*     */   
/*     */ 
/*     */   public void set(int i)
/*     */   {
/*  57 */     this.index = i;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getIndex()
/*     */   {
/*  63 */     return this.index;
/*     */   }
/*     */   
/*  66 */   public boolean isUndecided() { return this.index == 0; }
/*  67 */   public boolean isWhiteWin() { return this.index == 2; }
/*  68 */   public boolean isDraw() { return this.index == 1; }
/*  69 */   public boolean isBlackWin() { return this.index == 3; }
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
/*     */ 
/*     */   public String toString()
/*     */   {
/* 101 */     String s = null;
/*     */     
/* 103 */     switch (this.index) {
/* 104 */     case 0:  s = "*"; break;
/* 105 */     case 2:  s = "1-0"; break;
/* 106 */     case 1:  s = "1/2-1/2"; break;
/* 107 */     case 3:  s = "0-1"; break;
/*     */     default: 
/* 109 */       s = "?";
/*     */     }
/* 111 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 118 */     if (obj == this) return true;
/* 119 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 120 */       return false;
/*     */     }
/* 122 */     ChessResult r = (ChessResult)obj;
/*     */     
/* 124 */     return this.index == r.index;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 129 */     int hash = 7;
/*     */     
/* 131 */     hash = 31 * hash + this.index;
/*     */     
/* 133 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */