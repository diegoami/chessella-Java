package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSGameNotificationEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSGameNotificationParser extends ICSEventParser {

   public static FICSGameNotificationParser singleton = new FICSGameNotificationParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(Game\\snotification:\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\svs\\.\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(\\w+)\\s(\\S+)\\s(\\d+)\\s(\\d+):\\sGame\\s(\\d+))", 8);


   protected FICSGameNotificationParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSGameNotificationEvent evt = new ICSGameNotificationEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSGameNotificationEvent evt = (ICSGameNotificationEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));
      evt.setWhitePlayer(m.group(2));
      evt.setWhiteRating(this.parseICSRating(m, 3));
      evt.setBlackPlayer(m.group(4));
      evt.setBlackRating(this.parseICSRating(m, 5));
      evt.setVariant(new ICSVariant(m.group(7)));

      try {
         evt.setInitialTime(Integer.parseInt(m.group(8)));
      } catch (NumberFormatException var7) {
         Log.error(3, "Can\'t parse time for: " + m.group(8) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setIncrement(Integer.parseInt(m.group(9)));
      } catch (NumberFormatException var6) {
         Log.error(3, "Can\'t parse incr for: " + m.group(9) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setBoardNumber(Integer.parseInt(m.group(10)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse boardNumber for: " + m.group(10) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setRated("rated".equals(m.group(6)));
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSGameNotificationEvent evt = (ICSGameNotificationEvent)event;
         StringBuffer sb = new StringBuffer(50);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("Game notification: ").append(evt.getWhitePlayer()).append(" (").append(evt.getWhiteRating()).append(") vs. ").append(evt.getBlackPlayer()).append(" (").append(evt.getBlackRating()).append(") ");
         if(!evt.isRated()) {
            sb.append("un");
         }

         sb.append("rated ").append(evt.getVariant()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement()).append(": Game ").append(evt.getBoardNumber());
         return sb.toString();
      }
   }
}
