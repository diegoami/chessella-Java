package ictk.boardgame.chess.net.ics;


public class ICSMove {

   protected boolean isBlack;
   protected String san;
   protected int moveTime;
   protected int moveNumber;


   public String getSAN() {
      return this.san;
   }

   public void setSAN(String move) {
      this.san = move;
   }

   public int getMoveTime() {
      return this.moveTime;
   }

   public void setMoveTime(int milliseconds) {
      this.moveTime = milliseconds;
   }

   public boolean isBlack() {
      return this.isBlack;
   }

   public void setBlack(boolean t) {
      this.isBlack = t;
   }

   public int getMoveNumber() {
      return this.moveNumber;
   }

   public void setMoveNumber(int number) {
      this.moveNumber = number;
   }

   public String getMoveTimeAsString() {
      StringBuffer sb = new StringBuffer(7);
      int h = this.moveTime / 3600000;
      int m = this.moveTime % 3600000 / '\uea60';
      int s = this.moveTime % '\uea60' / 1000;
      int ms = this.moveTime % 1000;
      if(h > 1) {
         sb.append(h).append(":");
         if(m < 10) {
            sb.append(0);
         }
      }

      sb.append(m).append(":");
      if(s < 10) {
         sb.append(0);
      }

      sb.append(s).append(".");
      if(ms < 100) {
         sb.append(0);
      }

      if(ms < 10) {
         sb.append(0);
      }

      sb.append(ms);
      return sb.toString();
   }

   public String toString() {
      return this.moveNumber + (this.isBlack?"b":"w") + ". " + this.san + "(" + this.getMoveTimeAsString() + ")";
   }
}
