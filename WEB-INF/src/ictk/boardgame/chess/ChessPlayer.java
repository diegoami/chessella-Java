/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Player;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChessPlayer
/*     */   extends Player
/*     */ {
/*     */   public static final byte NO_TITLE = 0;
/*     */   public static final byte GM = 1;
/*     */   public static final byte WGM = 2;
/*     */   public static final byte IM = 3;
/*     */   public static final byte WIM = 4;
/*     */   public static final byte FM = 5;
/*     */   public static final byte WFM = 6;
/*     */   public static final byte NM = 7;
/*     */   public static final byte WNM = 8;
/*     */   public static final byte OTHER_TITLE = 9;
/*     */   protected byte title;
/*     */   protected int rating;
/*     */   
/*     */   public ChessPlayer() {}
/*     */   
/*     */   public ChessPlayer(String n)
/*     */   {
/*  63 */     super(n);
/*     */   }
/*     */   
/*     */   public ChessPlayer(String n, int _rating) {
/*  67 */     super(n);
/*  68 */     this.rating = _rating;
/*     */   }
/*     */   
/*     */ 
/*     */   public byte getTitle()
/*     */   {
/*  74 */     return this.title;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getRating()
/*     */   {
/*  80 */     return this.rating;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTitle(int t)
/*     */   {
/*  86 */     if ((t > 128) || (t < -128))
/*  87 */       throw new IllegalArgumentException("Title needs to be of byte size");
/*  88 */     this.title = ((byte)t);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRating(int rating)
/*     */   {
/*  95 */     this.rating = rating;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 101 */     String str = getName();
/* 102 */     if (this.rating > 0)
/* 103 */       str = str + "(" + this.rating + ")";
/* 104 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessPlayer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */