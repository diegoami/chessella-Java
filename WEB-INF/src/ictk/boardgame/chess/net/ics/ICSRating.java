/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSRating
/*     */ {
/*     */   int rating;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean isProvisional;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean isEstimated;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean isNotApplicable;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean isNotSet;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ICSRating() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ICSRating(String s)
/*     */     throws NumberFormatException
/*     */   {
/*  44 */     set(s);
/*     */   }
/*     */   
/*     */   public void set(int rate) {
/*  48 */     this.rating = rate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  54 */   public int get() { return this.rating; }
/*     */   
/*  56 */   public boolean isNotSet() { return this.isNotSet; }
/*  57 */   public boolean isNotApplicable() { return this.isNotApplicable; }
/*  58 */   public boolean isProvisional() { return this.isProvisional; }
/*  59 */   public boolean isEstimated() { return this.isEstimated; }
/*     */   
/*     */   public void setNotApplicable(boolean t) {
/*  62 */     this.isNotApplicable = t;
/*     */   }
/*     */   
/*     */   public void setProvisional(boolean t) {
/*  66 */     this.isProvisional = t;
/*     */   }
/*     */   
/*     */   public void setEstimated(boolean t) {
/*  70 */     this.isEstimated = t;
/*     */   }
/*     */   
/*     */   public void set(String s) throws NumberFormatException
/*     */   {
/*  75 */     if (s.equals("UNR")) {
/*  76 */       this.isNotSet = true;
/*  77 */     } else if (s.charAt(0) == '-') {
/*  78 */       this.isNotSet = true;
/*  79 */     } else if (s.charAt(0) == '+') {
/*  80 */       this.isNotApplicable = true;
/*     */     }
/*  82 */     else if (!Character.isDigit(s.charAt(s.length() - 1))) {
/*  83 */       this.rating = Integer.parseInt(s.substring(0, s.length() - 1));
/*     */       
/*     */ 
/*  86 */       if (s.charAt(s.length() - 1) == 'P') {
/*  87 */         this.isProvisional = true;
/*  88 */       } else if (s.charAt(s.length() - 1) == 'E') {
/*  89 */         this.isEstimated = true;
/*     */       }
/*     */     } else {
/*  92 */       this.rating = Integer.parseInt(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  98 */     String s = null;
/*     */     
/* 100 */     if (this.isNotSet) {
/* 101 */       s = "----";
/* 102 */     } else if (this.isNotApplicable) {
/* 103 */       s = "++++";
/* 104 */     } else if (this.isProvisional) {
/* 105 */       s = this.rating + "P";
/* 106 */     } else if (this.isEstimated) {
/* 107 */       s = this.rating + "E";
/*     */     } else {
/* 109 */       s = this.rating;
/*     */     }
/* 111 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSRating.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */