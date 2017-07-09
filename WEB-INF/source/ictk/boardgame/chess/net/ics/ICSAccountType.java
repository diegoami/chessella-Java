package ictk.boardgame.chess.net.ics;

import java.io.IOException;
import java.util.StringTokenizer;

public class ICSAccountType {

   public static final int UNREGISTERED = 0;
   public static final int COMPUTER = 1;
   public static final int BLIND = 2;
   public static final int TEAM = 3;
   public static final int ADMIN = 4;
   public static final int SERVICE_REP = 5;
   public static final int HELPER = 6;
   public static final int CHESS_ADVISOR = 7;
   public static final int TOURNAMENT_MANAGER = 8;
   public static final int TOURNAMENT_DIRECTOR = 9;
   public static final int DISPLAY_MANAGER = 10;
   public static final int WFM = 11;
   public static final int FM = 12;
   public static final int WIM = 13;
   public static final int IM = 14;
   public static final int WGM = 15;
   public static final int GM = 16;
   public static final int DEMO = 17;
   public static final int NUM_FLAGS = 18;
   protected boolean[] flag;


   public ICSAccountType() {
      this.flag = new boolean[18];
   }

   public ICSAccountType(String s) throws IOException {
      this();
      if(s != null) {
         this.set(s);
      }

   }

   public void set(int type, boolean t) {
      this.flag[type] = t;
   }

   public boolean is(int type) {
      return this.flag[type];
   }

   public void set(String s) throws IOException {
      StringTokenizer st = new StringTokenizer(s, "()", false);
      String tok = null;

      while(st.hasMoreTokens()) {
         tok = st.nextToken();
         if("U".equals(tok)) {
            this.flag[0] = true;
         } else if("*".equals(tok)) {
            this.flag[4] = true;
         } else if("C".equals(tok)) {
            this.flag[1] = true;
         } else if("SR".equals(tok)) {
            this.flag[5] = true;
         } else if("H".equals(tok)) {
            this.flag[6] = true;
         } else if("TD".equals(tok)) {
            this.flag[9] = true;
         } else if("TM".equals(tok)) {
            this.flag[8] = true;
         } else if("FM".equals(tok)) {
            this.flag[12] = true;
         } else if("IM".equals(tok)) {
            this.flag[14] = true;
         } else if("B".equals(tok)) {
            this.flag[2] = true;
         } else if("T".equals(tok)) {
            this.flag[3] = true;
         } else if("D".equals(tok)) {
            this.flag[17] = true;
         } else if("WIM".equals(tok)) {
            this.flag[13] = true;
         } else if("WFM".equals(tok)) {
            this.flag[15] = true;
         } else if("WGM".equals(tok)) {
            this.flag[15] = true;
         } else if("GM".equals(tok)) {
            this.flag[16] = true;
         } else if("CA".equals(tok)) {
            this.flag[7] = true;
         } else {
            if(!"DM".equals(tok)) {
               throw new IOException("Unrecognized account type: " + tok);
            }

            this.flag[10] = true;
         }
      }

   }

   public static String typeToString(int type) {
      String s = null;
      switch(type) {
      case 0:
         s = "U";
         break;
      case 1:
         s = "C";
         break;
      case 2:
         s = "B";
         break;
      case 3:
         s = "T";
         break;
      case 4:
         s = "*";
         break;
      case 5:
         s = "SR";
         break;
      case 6:
         s = "H";
         break;
      case 7:
         s = "CA";
         break;
      case 8:
         s = "TM";
         break;
      case 9:
         s = "TD";
         break;
      case 10:
         s = "DM";
         break;
      case 11:
         s = "WFM";
         break;
      case 12:
         s = "FM";
         break;
      case 13:
         s = "WIM";
         break;
      case 14:
         s = "IM";
         break;
      case 15:
         s = "WGM";
         break;
      case 16:
         s = "GM";
         break;
      case 17:
         s = "D";
         break;
      default:
         System.err.println("Received illegal account type: " + type);
      }

      return s;
   }

   public String toString() {
      StringBuffer sb = null;

      for(int i = 0; i < this.flag.length; ++i) {
         if(this.flag[i]) {
            if(sb == null) {
               sb = new StringBuffer();
            }

            sb.append("(").append(typeToString(i)).append(")");
         }
      }

      if(sb == null) {
         return "";
      } else {
         return sb.toString();
      }
   }
}
