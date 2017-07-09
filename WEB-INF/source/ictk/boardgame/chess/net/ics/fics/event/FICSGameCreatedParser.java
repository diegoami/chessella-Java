package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSGameCreatedEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSGameCreatedParser extends ICSEventParser {

   public static FICSGameCreatedParser singleton = new FICSGameCreatedParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(\\{Game\\s(\\d+)\\s\\(([\\w]+)\\svs\\.\\s([\\w]+)\\)\\s(Creating|Continuing)\\s(rated|unrated)\\s(\\S+)\\smatch\\.})", 8);


   protected FICSGameCreatedParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSGameCreatedEvent evt = new ICSGameCreatedEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSGameCreatedEvent evt = (ICSGameCreatedEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));

      try {
         evt.setBoardNumber(Integer.parseInt(m.group(2)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse boardNumber for: " + m.group(2) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setWhitePlayer(m.group(3));
      evt.setBlackPlayer(m.group(4));
      evt.setVariant(new ICSVariant(m.group(7)));
      evt.setContinued("Continuing".equals(m.group(5)));
      evt.setRated("rated".equals(m.group(6)));
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSGameCreatedEvent evt = (ICSGameCreatedEvent)event;
         StringBuffer sb = new StringBuffer(50);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("{Game ").append(evt.getBoardNumber()).append(" (").append(evt.getWhitePlayer()).append(" vs. ").append(evt.getBlackPlayer()).append(") ");
         if(evt.isContinued()) {
            sb.append("Continuing ");
         } else {
            sb.append("Creating ");
         }

         if(!evt.isRated()) {
            sb.append("un");
         }

         sb.append("rated ").append(evt.getVariant()).append(" match.}");
         return sb.toString();
      }
   }
}
