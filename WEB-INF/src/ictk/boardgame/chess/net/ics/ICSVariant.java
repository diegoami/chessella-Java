/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSVariant
/*     */ {
/*     */   public static final int UNKNOWN = 0;
/*     */   
/*     */ 
/*     */   public static final int UNTIMED = 1;
/*     */   
/*     */ 
/*     */   public static final int STANDARD = 2;
/*     */   
/*     */ 
/*     */   public static final int BLITZ = 3;
/*     */   
/*     */ 
/*     */   public static final int BULLET = 4;
/*     */   
/*     */ 
/*     */   public static final int LIGHTNING = 5;
/*     */   
/*     */ 
/*     */   public static final int MINUTE_5 = 6;
/*     */   
/*     */   public static final int MINUTE_1 = 7;
/*     */   
/*     */   public static final int FISCHER_RANDOM = 8;
/*     */   
/*     */   public static final int BUGHOUSE = 9;
/*     */   
/*     */   public static final int CRAZYHOUSE = 10;
/*     */   
/*     */   public static final int SUICIDE = 11;
/*     */   
/*     */   public static final int LOSERS = 12;
/*     */   
/*     */   public static final int ATOMIC = 13;
/*     */   
/*     */   public static final int KRIEGSPIEL = 14;
/*     */   
/*     */   public static final int WILD = 15;
/*     */   
/*     */   protected int variant;
/*     */   
/*     */   protected String name;
/*     */   
/*     */ 
/*     */   public ICSVariant() {}
/*     */   
/*     */ 
/*     */   public ICSVariant(String s)
/*     */   {
/*  55 */     setType(s);
/*     */   }
/*     */   
/*     */   public ICSVariant(char c) {
/*  59 */     setType(c);
/*     */   }
/*     */   
/*     */   public void setType(char c) {
/*  63 */     switch (c) {
/*  64 */     case 'u':  this.variant = 1; break;
/*  65 */     case 's':  this.variant = 2; break;
/*  66 */     case 'S':  this.variant = 11; break;
/*  67 */     case 'b':  this.variant = 3; break;
/*  68 */     case 'B':  this.variant = 9; break;
/*  69 */     case 'z':  this.variant = 10; break;
/*  70 */     case 'l':  this.variant = 5; break;
/*  71 */     case 'L':  this.variant = 12;
/*     */     }
/*     */   }
/*     */   
/*  75 */   public void setType(int type) { this.variant = type; }
/*  76 */   public int getType() { return this.variant; }
/*     */   
/*     */   public void setType(String type) {
/*  79 */     String vtype = type.toLowerCase();
/*     */     
/*  81 */     if ("blitz".equals(vtype)) {
/*  82 */       this.variant = 3;
/*  83 */     } else if ("lightning".equals(vtype)) {
/*  84 */       this.variant = 5;
/*  85 */     } else if ("standard".equals(vtype)) {
/*  86 */       this.variant = 2;
/*  87 */     } else if ("bughouse".equals(vtype)) {
/*  88 */       this.variant = 9;
/*  89 */     } else if ("crazyhouse".equals(vtype)) {
/*  90 */       this.variant = 10;
/*  91 */     } else if ("suicide".equals(vtype)) {
/*  92 */       this.variant = 11;
/*  93 */     } else if ("losers".equals(vtype)) {
/*  94 */       this.variant = 12;
/*     */     }
/*     */     else
/*     */     {
/*  98 */       this.variant = 0;
/*  99 */       this.name = type;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getName() {
/* 104 */     String rvalue = null;
/*     */     
/*     */ 
/* 107 */     switch (this.variant) {
/* 108 */     case 2:  rvalue = "standard"; break;
/* 109 */     case 3:  rvalue = "blitz"; break;
/* 110 */     case 5:  rvalue = "lightning"; break;
/* 111 */     case 9:  rvalue = "bughouse"; break;
/* 112 */     case 10:  rvalue = "crazyhouse"; break;
/* 113 */     case 11:  rvalue = "suicide"; break;
/* 114 */     case 12:  rvalue = "losers"; break;
/*     */     case 4: case 6: case 7: case 8: default: 
/* 116 */       rvalue = this.name;
/*     */     }
/*     */     
/* 119 */     return rvalue;
/*     */   }
/*     */   
/*     */   public boolean isChess() {
/* 123 */     switch (this.variant) {
/*     */     case 2: 
/*     */     case 3: 
/*     */     case 4: 
/*     */     case 5: 
/*     */     case 6: 
/*     */     case 7: 
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 137 */     return getName();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSVariant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */