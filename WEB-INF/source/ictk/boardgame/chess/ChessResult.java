package ictk.boardgame.chess;

import ictk.boardgame.Result;

public class ChessResult implements Result {

   public static final int UNDECIDED = 0;
   public static final int DRAW = 1;
   public static final int WHITE_WIN = 2;
   public static final int BLACK_WIN = 3;
   protected int index = 0;


   public ChessResult(int i) {
      this.index = i;
   }

   public void set(int i) {
      this.index = i;
   }

   public int getIndex() {
      return this.index;
   }

   public boolean isUndecided() {
      return this.index == 0;
   }

   public boolean isWhiteWin() {
      return this.index == 2;
   }

   public boolean isDraw() {
      return this.index == 1;
   }

   public boolean isBlackWin() {
      return this.index == 3;
   }

   public String toString() {
      String s = null;
      switch(this.index) {
      case 0:
         s = "*";
         break;
      case 1:
         s = "1/2-1/2";
         break;
      case 2:
         s = "1-0";
         break;
      case 3:
         s = "0-1";
         break;
      default:
         s = "?";
      }

      return s;
   }

   public boolean equals(Object obj) {
      if(obj == this) {
         return true;
      } else if(obj != null && obj.getClass() == this.getClass()) {
         ChessResult r = (ChessResult)obj;
         return this.index == r.index;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int hash1 = 31 * hash + this.index;
      return hash1;
   }
}
