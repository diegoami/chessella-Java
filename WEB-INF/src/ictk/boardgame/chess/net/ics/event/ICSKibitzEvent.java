/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParser;
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
/*     */ public class ICSKibitzEvent
/*     */   extends ICSMessageEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   protected static final int KIBITZ_EVENT = 17;
/*     */   protected String player;
/*     */   protected ICSAccountType acctType;
/*     */   protected ICSRating rating;
/*     */   protected int boardNumber;
/*     */   
/*     */   public ICSKibitzEvent()
/*     */   {
/*  61 */     super(17);
/*     */   }
/*     */   
/*     */   public String getPlayer()
/*     */   {
/*  66 */     return this.player;
/*     */   }
/*     */   
/*     */   public ICSAccountType getAccountType() {
/*  70 */     return this.acctType;
/*     */   }
/*     */   
/*     */   public ICSRating getRating() {
/*  74 */     return this.rating;
/*     */   }
/*     */   
/*     */   public void setPlayer(String player)
/*     */   {
/*  79 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void setAccountType(ICSAccountType acctType) {
/*  83 */     this.acctType = acctType;
/*     */   }
/*     */   
/*     */   public void setRating(ICSRating rating) {
/*  87 */     this.rating = rating;
/*     */   }
/*     */   
/*     */   public int getBoardNumber()
/*     */   {
/*  92 */     return this.boardNumber;
/*     */   }
/*     */   
/*     */   public void setBoardNumber(int board) {
/*  96 */     this.boardNumber = board;
/*     */   }
/*     */   
/*     */   public String getReadable()
/*     */   {
/* 101 */     return FICSKibitzParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSKibitzEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */