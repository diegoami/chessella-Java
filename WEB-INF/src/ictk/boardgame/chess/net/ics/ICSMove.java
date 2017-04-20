/*    */ package ictk.boardgame.chess.net.ics;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ICSMove
/*    */ {
/*    */   protected boolean isBlack;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String san;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int moveTime;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int moveNumber;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 41 */   public String getSAN() { return this.san; }
/* 42 */   public void setSAN(String move) { this.san = move; }
/*    */   
/* 44 */   public int getMoveTime() { return this.moveTime; }
/* 45 */   public void setMoveTime(int milliseconds) { this.moveTime = milliseconds; }
/*    */   
/* 47 */   public boolean isBlack() { return this.isBlack; }
/* 48 */   public void setBlack(boolean t) { this.isBlack = t; }
/*    */   
/* 50 */   public int getMoveNumber() { return this.moveNumber; }
/* 51 */   public void setMoveNumber(int number) { this.moveNumber = number; }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getMoveTimeAsString()
/*    */   {
/* 57 */     StringBuffer sb = new StringBuffer(7);
/*    */     
/*    */ 
/* 60 */     int h = this.moveTime / 3600000;
/* 61 */     int m = this.moveTime % 3600000 / 60000;
/* 62 */     int s = this.moveTime % 60000 / 1000;
/* 63 */     int ms = this.moveTime % 1000;
/*    */     
/* 65 */     if (h > 1) {
/* 66 */       sb.append(h).append(":");
/* 67 */       if (m < 10) {
/* 68 */         sb.append(0);
/*    */       }
/*    */     }
/* 71 */     sb.append(m).append(":");
/* 72 */     if (s < 10) {
/* 73 */       sb.append(0);
/*    */     }
/* 75 */     sb.append(s).append(".");
/* 76 */     if (ms < 100)
/* 77 */       sb.append(0);
/* 78 */     if (ms < 10)
/* 79 */       sb.append(0);
/* 80 */     sb.append(ms);
/* 81 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 85 */     return 
/* 86 */       this.moveNumber + (this.isBlack ? "b" : "w") + ". " + this.san + "(" + getMoveTimeAsString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSMove.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */