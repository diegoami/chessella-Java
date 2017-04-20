/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
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
/*     */ public class AmbiguousChessMoveException
/*     */   extends AmbiguousMoveException
/*     */ {
/*     */   protected ChessPiece[] pieces;
/*     */   protected int p;
/*     */   protected int of;
/*     */   protected int or;
/*     */   protected int df;
/*     */   protected int dr;
/*     */   
/*     */   public AmbiguousChessMoveException() {}
/*     */   
/*     */   public AmbiguousChessMoveException(String mesg)
/*     */   {
/*  55 */     super(mesg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr)
/*     */   {
/*  68 */     this(mesg, p, of, or, df, dr, null);
/*     */   }
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
/*     */   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr, List dupes)
/*     */   {
/*  87 */     super(mesg);
/*  88 */     this.p = p;
/*  89 */     this.of = of;
/*  90 */     this.or = or;
/*  91 */     this.df = df;
/*  92 */     this.dr = dr;
/*     */     
/*  94 */     if (dupes != null) {
/*  95 */       Object[] objs = dupes.toArray();
/*  96 */       this.pieces = new ChessPiece[objs.length];
/*  97 */       for (int i = 0; i < objs.length; i++) {
/*  98 */         this.pieces[i] = ((ChessPiece)objs[i]);
/*     */       }
/*     */     }
/*     */   }
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
/*     */   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr, ChessPiece[] dupes)
/*     */   {
/* 118 */     super(mesg);
/* 119 */     this.p = p;
/* 120 */     this.of = of;
/* 121 */     this.or = or;
/* 122 */     this.df = df;
/* 123 */     this.dr = dr;
/* 124 */     this.pieces = dupes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChessPiece[] getPieces()
/*     */   {
/* 132 */     return this.pieces;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 136 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 138 */     sb.append(getMessage()).append(" ");
/* 139 */     if ((this.p != 0) && (this.p != 32))
/* 140 */       sb.append(this.p);
/* 141 */     if ((this.of != 0) && (this.of != 32))
/* 142 */       sb.append(this.of);
/* 143 */     if ((this.or != 0) && (this.of != 32))
/* 144 */       sb.append(this.or);
/* 145 */     if ((this.df != 0) && (this.of != 32))
/* 146 */       sb.append(this.df);
/* 147 */     if ((this.dr != 0) && (this.of != 32)) {
/* 148 */       sb.append(this.dr);
/*     */     }
/* 150 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\AmbiguousChessMoveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */