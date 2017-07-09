package ictk.boardgame.chess.net.ics;


public class ICSVariant {

   public static final int UNKNOWN = 0;
   public static final int UNTIMED = 1;
   public static final int STANDARD = 2;
   public static final int BLITZ = 3;
   public static final int BULLET = 4;
   public static final int LIGHTNING = 5;
   public static final int MINUTE_5 = 6;
   public static final int MINUTE_1 = 7;
   public static final int FISCHER_RANDOM = 8;
   public static final int BUGHOUSE = 9;
   public static final int CRAZYHOUSE = 10;
   public static final int SUICIDE = 11;
   public static final int LOSERS = 12;
   public static final int ATOMIC = 13;
   public static final int KRIEGSPIEL = 14;
   public static final int WILD = 15;
   protected int variant;
   protected String name;


   public ICSVariant() {}

   public ICSVariant(String s) {
      this.setType(s);
   }

   public ICSVariant(char c) {
      this.setType(c);
   }

   public void setType(char c) {
      switch(c) {
      case 66:
         this.variant = 9;
         break;
      case 76:
         this.variant = 12;
         break;
      case 83:
         this.variant = 11;
         break;
      case 98:
         this.variant = 3;
         break;
      case 108:
         this.variant = 5;
         break;
      case 115:
         this.variant = 2;
         break;
      case 117:
         this.variant = 1;
         break;
      case 122:
         this.variant = 10;
      }

   }

   public void setType(int type) {
      this.variant = type;
   }

   public int getType() {
      return this.variant;
   }

   public void setType(String type) {
      String vtype = type.toLowerCase();
      if("blitz".equals(vtype)) {
         this.variant = 3;
      } else if("lightning".equals(vtype)) {
         this.variant = 5;
      } else if("standard".equals(vtype)) {
         this.variant = 2;
      } else if("bughouse".equals(vtype)) {
         this.variant = 9;
      } else if("crazyhouse".equals(vtype)) {
         this.variant = 10;
      } else if("suicide".equals(vtype)) {
         this.variant = 11;
      } else if("losers".equals(vtype)) {
         this.variant = 12;
      } else {
         this.variant = 0;
         this.name = type;
      }

   }

   public String getName() {
      String rvalue = null;
      switch(this.variant) {
      case 2:
         rvalue = "standard";
         break;
      case 3:
         rvalue = "blitz";
         break;
      case 4:
      case 6:
      case 7:
      case 8:
      default:
         rvalue = this.name;
         break;
      case 5:
         rvalue = "lightning";
         break;
      case 9:
         rvalue = "bughouse";
         break;
      case 10:
         rvalue = "crazyhouse";
         break;
      case 11:
         rvalue = "suicide";
         break;
      case 12:
         rvalue = "losers";
      }

      return rvalue;
   }

   public boolean isChess() {
      switch(this.variant) {
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
         return true;
      default:
         return false;
      }
   }

   public String toString() {
      return this.getName();
   }
}
