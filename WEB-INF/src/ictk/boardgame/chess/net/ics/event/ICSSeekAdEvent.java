/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdReadableParser;
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
/*     */ 
/*     */ 
/*     */ public class ICSSeekAdEvent
/*     */   extends ICSEvent
/*     */ {
/*     */   protected static final int SEEK_AD_EVENT = 12;
/*     */   public static final int COLOR_UNSPECIFIED = 0;
/*     */   public static final int COLOR_WHITE = 1;
/*     */   public static final int COLOR_BLACK = 2;
/*     */   protected int number;
/*     */   protected String player;
/*     */   protected ICSAccountType acctType;
/*     */   protected ICSRating rating;
/*     */   protected ICSVariant variant;
/*     */   protected boolean isRestrictedByFormula;
/*     */   protected boolean meetsFormula;
/*     */   protected int color;
/*     */   protected boolean rated;
/*     */   protected boolean notified;
/*     */   protected boolean manual;
/*     */   protected int time;
/*     */   protected int incr;
/*     */   protected int rangeLow;
/*     */   protected int rangeHigh;
/*     */   
/*     */   public ICSSeekAdEvent()
/*     */   {
/*  74 */     super(12);
/*     */   }
/*     */   
/*     */   public int getAdNumber()
/*     */   {
/*  79 */     return this.number;
/*     */   }
/*     */   
/*     */   public String getPlayer() {
/*  83 */     return this.player;
/*     */   }
/*     */   
/*     */   public ICSAccountType getAccountType() {
/*  87 */     return this.acctType;
/*     */   }
/*     */   
/*     */   public ICSRating getRating() {
/*  91 */     return this.rating;
/*     */   }
/*     */   
/*     */   public ICSVariant getVariant() {
/*  95 */     return this.variant;
/*     */   }
/*     */   
/*     */   public boolean isRestrictedByFormula() {
/*  99 */     return this.isRestrictedByFormula;
/*     */   }
/*     */   
/*     */   public boolean meetsFormula() {
/* 103 */     return this.meetsFormula;
/*     */   }
/*     */   
/*     */   public int getColor() {
/* 107 */     return this.color;
/*     */   }
/*     */   
/*     */   public boolean isRated() {
/* 111 */     return this.rated;
/*     */   }
/*     */   
/*     */   public boolean isNotification() {
/* 115 */     return this.notified;
/*     */   }
/*     */   
/*     */   public boolean isManual() {
/* 119 */     return this.manual;
/*     */   }
/*     */   
/*     */   public int getInitialTime() {
/* 123 */     return this.time;
/*     */   }
/*     */   
/*     */   public int getIncrement() {
/* 127 */     return this.incr;
/*     */   }
/*     */   
/*     */   public int getRatingRangeLow() {
/* 131 */     return this.rangeLow;
/*     */   }
/*     */   
/*     */   public int getRatingRangeHigh() {
/* 135 */     return this.rangeHigh;
/*     */   }
/*     */   
/*     */   public void setAdNumber(int number)
/*     */   {
/* 140 */     this.number = number;
/*     */   }
/*     */   
/*     */   public void setPlayer(String player) {
/* 144 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void setAccountType(ICSAccountType acctType) {
/* 148 */     this.acctType = acctType;
/*     */   }
/*     */   
/*     */   public void setRating(ICSRating rating) {
/* 152 */     this.rating = rating;
/*     */   }
/*     */   
/*     */   public void setVariant(ICSVariant variant) {
/* 156 */     this.variant = variant;
/*     */   }
/*     */   
/*     */   public void setRestrictedByFormula(boolean isRestrictedByFormula) {
/* 160 */     this.isRestrictedByFormula = isRestrictedByFormula;
/*     */   }
/*     */   
/*     */   public void meetsFormula(boolean meetsFormula) {
/* 164 */     this.meetsFormula = meetsFormula;
/*     */   }
/*     */   
/*     */   public void setColor(int color) {
/* 168 */     this.color = color;
/*     */   }
/*     */   
/*     */   public void setRated(boolean rated) {
/* 172 */     this.rated = rated;
/*     */   }
/*     */   
/*     */   public void setNotification(boolean notified) {
/* 176 */     this.notified = notified;
/*     */   }
/*     */   
/*     */   public void setManual(boolean manual) {
/* 180 */     this.manual = manual;
/*     */   }
/*     */   
/*     */   public void setInitialTime(int time) {
/* 184 */     this.time = time;
/*     */   }
/*     */   
/*     */   public void setIncrement(int incr) {
/* 188 */     this.incr = incr;
/*     */   }
/*     */   
/*     */   public void setRatingRangeLow(int rangeLow) {
/* 192 */     this.rangeLow = rangeLow;
/*     */   }
/*     */   
/*     */   public void setRatingRangeHigh(int rangeHigh) {
/* 196 */     this.rangeHigh = rangeHigh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 203 */     String str = null;
/* 204 */     switch (getEventType())
/*     */     {
/*     */     case 12: 
/* 207 */       str = FICSSeekAdParser.getInstance().toNative(this);
/* 208 */       break;
/*     */     
/*     */     case 14: 
/* 211 */       str = FICSSeekAdReadableParser.getInstance().toNative(this);
/*     */     }
/*     */     
/*     */     
/* 215 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSSeekAdEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */