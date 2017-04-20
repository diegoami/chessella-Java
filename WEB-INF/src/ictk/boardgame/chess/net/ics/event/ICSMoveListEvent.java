/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSDate;
/*     */ import ictk.boardgame.chess.net.ics.ICSMove;
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.ICSResult;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSMoveListParser;
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
/*     */ public class ICSMoveListEvent
/*     */   extends ICSEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   protected static final int MOVE_LIST_EVENT = 22;
/*     */   protected int boardNumber;
/*     */   protected int initTime;
/*     */   protected int incrTime;
/*     */   protected String white;
/*     */   protected String black;
/*     */   protected String status;
/*     */   protected ICSRating whiteRating;
/*     */   protected ICSRating blackRating;
/*     */   protected ICSDate date;
/*     */   protected boolean isRated;
/*     */   protected ICSVariant variant;
/*     */   protected ICSResult result;
/*     */   protected ICSMove[] moves;
/*     */   
/*     */   public ICSMoveListEvent()
/*     */   {
/*  54 */     super(22);
/*     */   }
/*     */   
/*     */ 
/*  58 */   public ICSMove[] getMoves() { return this.moves; }
/*  59 */   public void setMoves(ICSMove[] list) { this.moves = list; }
/*     */   
/*  61 */   public String getWhitePlayer() { return this.white; }
/*  62 */   public void setWhitePlayer(String player) { this.white = player; }
/*     */   
/*  64 */   public String getBlackPlayer() { return this.black; }
/*  65 */   public void setBlackPlayer(String player) { this.black = player; }
/*     */   
/*  67 */   public ICSVariant getVariant() { return this.variant; }
/*  68 */   public void setVariant(ICSVariant gameType) { this.variant = gameType; }
/*     */   
/*  70 */   public ICSRating getWhiteRating() { return this.whiteRating; }
/*  71 */   public void setWhiteRating(ICSRating rating) { this.whiteRating = rating; }
/*     */   
/*  73 */   public ICSRating getBlackRating() { return this.blackRating; }
/*  74 */   public void setBlackRating(ICSRating rating) { this.blackRating = rating; }
/*     */   
/*  76 */   public ICSResult getResult() { return this.result; }
/*  77 */   public void setResult(ICSResult res) { this.result = res; }
/*     */   
/*  79 */   public int getInitialTime() { return this.initTime; }
/*  80 */   public void setInitialTime(int minutes) { this.initTime = minutes; }
/*     */   
/*  82 */   public int getIncrement() { return this.incrTime; }
/*  83 */   public void setIncrement(int seconds) { this.incrTime = seconds; }
/*     */   
/*  85 */   public boolean isRated() { return this.isRated; }
/*  86 */   public void setRated(boolean rated) { this.isRated = rated; }
/*     */   
/*  88 */   public void setBoardNumber(int board) { this.boardNumber = board; }
/*     */   
/*  90 */   public int getBoardNumber() { return this.boardNumber; }
/*     */   
/*     */   public void setStatus(String status) {
/*  93 */     this.status = status;
/*     */   }
/*     */   
/*  96 */   public String getStatus() { return this.status; }
/*     */   
/*     */ 
/*  99 */   public void setDate(ICSDate date) { this.date = date; }
/* 100 */   public ICSDate getDate() { return this.date; }
/*     */   
/*     */   public String getReadable()
/*     */   {
/* 104 */     return FICSMoveListParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSMoveListEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */