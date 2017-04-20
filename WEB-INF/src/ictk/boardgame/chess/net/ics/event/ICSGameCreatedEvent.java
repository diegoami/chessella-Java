/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameCreatedParser;
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
/*     */ 
/*     */ public class ICSGameCreatedEvent
/*     */   extends ICSEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   protected static final int GAME_CREATED_EVENT = 2;
/*     */   protected int boardNumber;
/*     */   protected String white;
/*     */   protected String black;
/*     */   protected boolean continued;
/*     */   protected boolean rated;
/*     */   protected ICSVariant variant;
/*     */   
/*     */   public ICSGameCreatedEvent()
/*     */   {
/*  63 */     super(2);
/*     */   }
/*     */   
/*     */   public String getWhitePlayer()
/*     */   {
/*  68 */     return this.white;
/*     */   }
/*     */   
/*     */   public String getBlackPlayer() {
/*  72 */     return this.black;
/*     */   }
/*     */   
/*     */   public boolean isContinued() {
/*  76 */     return this.continued;
/*     */   }
/*     */   
/*     */   public boolean isRated() {
/*  80 */     return this.rated;
/*     */   }
/*     */   
/*     */   public ICSVariant getVariant() {
/*  84 */     return this.variant;
/*     */   }
/*     */   
/*     */   public void setWhitePlayer(String white)
/*     */   {
/*  89 */     this.white = white;
/*     */   }
/*     */   
/*     */   public void setBlackPlayer(String black) {
/*  93 */     this.black = black;
/*     */   }
/*     */   
/*     */   public void setContinued(boolean continued) {
/*  97 */     this.continued = continued;
/*     */   }
/*     */   
/*     */   public void setRated(boolean rated) {
/* 101 */     this.rated = rated;
/*     */   }
/*     */   
/*     */   public void setVariant(ICSVariant variant) {
/* 105 */     this.variant = variant;
/*     */   }
/*     */   
/*     */   public int getBoardNumber()
/*     */   {
/* 110 */     return this.boardNumber;
/*     */   }
/*     */   
/*     */   public void setBoardNumber(int board) {
/* 114 */     this.boardNumber = board;
/*     */   }
/*     */   
/*     */   public String getReadable()
/*     */   {
/* 119 */     return FICSGameCreatedParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSGameCreatedEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */