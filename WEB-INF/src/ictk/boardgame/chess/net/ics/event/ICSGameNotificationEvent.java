/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameNotificationParser;
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
/*     */ 
/*     */ 
/*     */ public class ICSGameNotificationEvent
/*     */   extends ICSEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   protected static final int GAME_NOTIFICATION_EVENT = 4;
/*     */   protected int boardNumber;
/*     */   protected String white;
/*     */   protected ICSRating whiteRating;
/*     */   protected String black;
/*     */   protected ICSRating blackRating;
/*     */   protected boolean rated;
/*     */   protected ICSVariant variant;
/*     */   protected int time;
/*     */   protected int incr;
/*     */   
/*     */   public ICSGameNotificationEvent()
/*     */   {
/*  66 */     super(4);
/*     */   }
/*     */   
/*     */   public String getWhitePlayer()
/*     */   {
/*  71 */     return this.white;
/*     */   }
/*     */   
/*     */   public ICSRating getWhiteRating() {
/*  75 */     return this.whiteRating;
/*     */   }
/*     */   
/*     */   public String getBlackPlayer() {
/*  79 */     return this.black;
/*     */   }
/*     */   
/*     */   public ICSRating getBlackRating() {
/*  83 */     return this.blackRating;
/*     */   }
/*     */   
/*     */   public boolean isRated() {
/*  87 */     return this.rated;
/*     */   }
/*     */   
/*     */   public ICSVariant getVariant() {
/*  91 */     return this.variant;
/*     */   }
/*     */   
/*     */   public int getInitialTime() {
/*  95 */     return this.time;
/*     */   }
/*     */   
/*     */   public int getIncrement() {
/*  99 */     return this.incr;
/*     */   }
/*     */   
/*     */   public void setWhitePlayer(String white)
/*     */   {
/* 104 */     this.white = white;
/*     */   }
/*     */   
/*     */   public void setWhiteRating(ICSRating whiteRating) {
/* 108 */     this.whiteRating = whiteRating;
/*     */   }
/*     */   
/*     */   public void setBlackPlayer(String black) {
/* 112 */     this.black = black;
/*     */   }
/*     */   
/*     */   public void setBlackRating(ICSRating blackRating) {
/* 116 */     this.blackRating = blackRating;
/*     */   }
/*     */   
/*     */   public void setRated(boolean rated) {
/* 120 */     this.rated = rated;
/*     */   }
/*     */   
/*     */   public void setVariant(ICSVariant variant) {
/* 124 */     this.variant = variant;
/*     */   }
/*     */   
/*     */   public void setInitialTime(int time) {
/* 128 */     this.time = time;
/*     */   }
/*     */   
/*     */   public void setIncrement(int incr) {
/* 132 */     this.incr = incr;
/*     */   }
/*     */   
/*     */   public int getBoardNumber()
/*     */   {
/* 137 */     return this.boardNumber;
/*     */   }
/*     */   
/*     */   public void setBoardNumber(int board) {
/* 141 */     this.boardNumber = board;
/*     */   }
/*     */   
/*     */   public String getReadable()
/*     */   {
/* 146 */     return FICSGameNotificationParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSGameNotificationEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */