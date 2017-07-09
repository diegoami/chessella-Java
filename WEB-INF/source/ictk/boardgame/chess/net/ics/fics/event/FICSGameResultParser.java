package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSResult;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSGameResultEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSGameResultParser extends ICSEventParser {

   public static FICSGameResultParser singleton = new FICSGameResultParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(\\{Game\\s(\\d+)\\s\\(([\\w]+)\\svs\\.\\s([\\w]+)\\)\\s([^}]+)\\}\\s(\\S+))", 8);


   protected FICSGameResultParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSGameResultEvent evt = new ICSGameResultEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSGameResultEvent evt = (ICSGameResultEvent)event;
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
      evt.setResult(new ICSResult(m.group(6)));
      evt.getResult().setDescription(m.group(5));
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSGameResultEvent evt = (ICSGameResultEvent)event;
         StringBuffer sb = new StringBuffer(40);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("{Game ").append(evt.getBoardNumber()).append(" (").append(evt.getWhitePlayer()).append(" vs. ").append(evt.getBlackPlayer()).append(") ").append(evt.getResult().getDescription()).append("} ").append(evt.getResult());
         return sb.toString();
      }
   }
}
