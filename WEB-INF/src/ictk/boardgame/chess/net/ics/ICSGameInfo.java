/*    */ package ictk.boardgame.chess.net.ics;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ICSGameInfo
/*    */ {
/*    */   protected boolean isBlack;
/*    */   protected boolean isRated;
/*    */   protected int index;
/*    */   protected int initTime;
/*    */   protected int incrTime;
/*    */   protected String player;
/*    */   protected String opponent;
/*    */   protected String endBy;
/*    */   protected ICSRating playerRating;
/*    */   protected ICSRating oppRating;
/*    */   protected ICSVariant variant;
/*    */   protected ICSEco eco;
/*    */   protected ICSResult result;
/*    */   protected Calendar date;
/*    */   
/* 53 */   public int getIndex() { return this.index; }
/* 54 */   public void setIndex(int idx) { this.index = idx; }
/*    */   
/* 56 */   public String getPlayer() { return this.player; }
/* 57 */   public void setPlayer(String player) { this.player = player; }
/*    */   
/* 59 */   public String getOpponent() { return this.opponent; }
/* 60 */   public void setOpponent(String name) { this.opponent = name; }
/*    */   
/* 62 */   public ICSVariant getVariant() { return this.variant; }
/* 63 */   public void setVariant(ICSVariant gameType) { this.variant = gameType; }
/*    */   
/* 65 */   public ICSRating getPlayerRating() { return this.playerRating; }
/* 66 */   public void setPlayerRating(ICSRating rating) { this.playerRating = rating; }
/*    */   
/* 68 */   public ICSRating getOpponentRating() { return this.oppRating; }
/* 69 */   public void setOpponentRating(ICSRating rating) { this.oppRating = rating; }
/*    */   
/* 71 */   public ICSEco getEco() { return this.eco; }
/* 72 */   public void setEco(ICSEco eco) { this.eco = eco; }
/*    */   
/* 74 */   public Calendar getDate() { return this.date; }
/* 75 */   public void setDate(Calendar date) { this.date = date; }
/*    */   
/* 77 */   public ICSResult getResult() { return this.result; }
/* 78 */   public void setResult(ICSResult res) { this.result = res; }
/*    */   
/*    */ 
/*    */ 
/* 82 */   public boolean isBlack() { return this.isBlack; }
/* 83 */   public void setBlack(boolean t) { this.isBlack = t; }
/*    */   
/* 85 */   public int getInitialTime() { return this.initTime; }
/* 86 */   public void setInitialTime(int minutes) { this.initTime = minutes; }
/*    */   
/* 88 */   public int getIncrementTime() { return this.incrTime; }
/* 89 */   public void setIncrementTime(int seconds) { this.incrTime = seconds; }
/*    */   
/* 91 */   public boolean isRated() { return this.isRated; }
/* 92 */   public void setRated(boolean rated) { this.isRated = rated; }
/*    */   
/*    */   public String toString() {
/* 95 */     return 
/* 96 */       "HISTORY: " + getPlayer() + "[" + getPlayerRating() + "] v " + getOpponent() + "[" + getOpponentRating() + "]";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSGameInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */