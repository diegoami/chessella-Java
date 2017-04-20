/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSResult;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSGameResultEvent
/*     */   extends ICSEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   protected static final int GAME_RESULT_EVENT = 3;
/*     */   protected int boardNumber;
/*     */   protected String white;
/*     */   protected String black;
/*     */   protected ICSResult result;
/*     */   
/*     */   public ICSGameResultEvent()
/*     */   {
/*  61 */     super(3);
/*     */   }
/*     */   
/*     */   public String getWhitePlayer()
/*     */   {
/*  66 */     return this.white;
/*     */   }
/*     */   
/*     */   public String getBlackPlayer() {
/*  70 */     return this.black;
/*     */   }
/*     */   
/*     */   public ICSResult getResult() {
/*  74 */     return this.result;
/*     */   }
/*     */   
/*     */   public void setWhitePlayer(String white)
/*     */   {
/*  79 */     this.white = white;
/*     */   }
/*     */   
/*     */   public void setBlackPlayer(String black) {
/*  83 */     this.black = black;
/*     */   }
/*     */   
/*     */   public void setResult(ICSResult result) {
/*  87 */     this.result = result;
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
/* 101 */     return FICSGameResultParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSGameResultEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */