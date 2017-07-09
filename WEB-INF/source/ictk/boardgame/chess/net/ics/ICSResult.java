package ictk.boardgame.chess.net.ics;

import ictk.util.Log;

public class ICSResult {

   public static final int UNDECIDED = 0;
   public static final int WHITE_WIN = 1;
   public static final int DRAW = 2;
   public static final int BLACK_WIN = 3;
   protected int result;
   protected String desc;


   public ICSResult() {
      this.result = 0;
   }

   public ICSResult(String s) {
      this();
      this.setResultCode(s);
   }

   public int getResultCode() {
      return this.result;
   }

   public void setResultCode(int res) {
      this.result = res;
   }

   public void setResultCode(String s) {
      if("*".equals(s)) {
         this.result = 0;
      } else if("1-0".equals(s)) {
         this.result = 1;
      } else if("1/2-1/2".equals(s)) {
         this.result = 2;
      } else if("0-1".equals(s)) {
         this.result = 3;
      } else {
         Log.error(3, "ICSResult received \'" + s + "\' as a result.");
         this.result = 0;
      }

   }

   public String getReadableResult() {
      String s = null;
      switch(this.result) {
      case 0:
         s = "*";
         break;
      case 1:
         s = "1-0";
         break;
      case 2:
         s = "1/2-1/2";
         break;
      case 3:
         s = "0-1";
      }

      return s;
   }

   public String getDescription() {
      return this.desc;
   }

   public void setDescription(String s) {
      this.desc = s;
   }

   public String toString() {
      return this.getReadableResult();
   }
}
