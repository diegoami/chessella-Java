package ictk.boardgame.chess.net.ics.ui.cli;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ANSIConsole implements ICSEventListener {

   public boolean debug = false;
   protected boolean showTimestamp = true;
   protected static Calendar cal = new GregorianCalendar();
   public static final char ESC = '\u001b';
   public static final String BLACK = "[0;30";
   public static final String RED = "[0;31m";
   public static final String GREEN = "[0;32m";
   public static final String YELLOW = "[0;33m";
   public static final String BLUE = "[0;34m";
   public static final String MAGENTA = "[0;35m";
   public static final String CYAN = "[0;36m";
   public static final String WHITE = "[0;37m";
   public static final String BOLD_BLACK = "[1;30m";
   public static final String BOLD_RED = "[1;31m";
   public static final String BOLD_GREEN = "[1;32m";
   public static final String BOLD_YELLOW = "[1;33m";
   public static final String BOLD_BLUE = "[1;34m";
   public static final String BOLD_MAGENTA = "[1;35m";
   public static final String BOLD_CYAN = "[1;36m";
   public static final String BOLD_WHITE = "[1;37m";
   public static final String PLAIN = "[0;m";


   public void icsEventDispatched(ICSEvent evt) {
      String prefix;
      prefix = null;
      label38:
      switch(evt.getEventType()) {
      case 1:
      case 22:
         prefix = "[0;33m";
         break;
      case 2:
      case 3:
      case 12:
      case 13:
      case 16:
      case 24:
      case 27:
         prefix = "[1;30m";
         break;
      case 4:
      case 14:
         prefix = "[0;34m";
         break;
      case 5:
      case 8:
      case 11:
      case 15:
      case 20:
      case 21:
      case 23:
      case 25:
      case 26:
      default:
         prefix = "[1;31m";
         break;
      case 6:
         switch(((ICSChannelEvent)evt).getChannel()) {
         case 1:
            prefix = "[0;36m";
            break label38;
         case 85:
         case 88:
            prefix = "[0;33m";
            break label38;
         default:
            prefix = "[1;36m";
            break label38;
         }
      case 7:
         switch(((ICSChannelEvent)evt).getChannel()) {
         case 1:
         case 2:
            prefix = "[0;32m";
            break label38;
         case 3:
         case 4:
         case 5:
            prefix = "[1;32m";
         default:
            break label38;
         }
      case 9:
      case 10:
      case 19:
         prefix = "[1;33m";
         break;
      case 17:
         prefix = "[1;35m";
         break;
      case 18:
         prefix = "[0;35m";
      }

      if(this.showTimestamp) {
         System.out.print("[1;30m" + this.getTimestampAsString(evt.getTimestamp()) + '\u001b' + "[0;m");
      }

      if(this.debug) {
         System.out.print("<" + evt.getEventType() + ">");
      }

      if(prefix != null) {
         System.out.println(prefix + evt + '\u001b' + "[0;m");
      } else {
         System.out.println(evt);
      }

      System.out.flush();
   }

   protected String getTimestampAsString(Date date) {
      StringBuffer sb = new StringBuffer(5);
      boolean tmp = false;
      cal.setTime(date);
      int tmp1 = cal.get(11);
      if(tmp1 < 10) {
         sb.append("0");
      }

      sb.append(tmp1).append(":");
      tmp1 = cal.get(12);
      if(tmp1 < 10) {
         sb.append("0");
      }

      sb.append(tmp1);
      return sb.toString();
   }

   public void setTimestampVisible(boolean t) {
      this.showTimestamp = t;
   }

   public boolean isTimestampVisible() {
      return this.showTimestamp;
   }
}
