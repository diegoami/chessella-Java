package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSPlayerConnectionEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSPlayerConnectionParser extends ICSEventParser {

   public static FICSPlayerConnectionParser singleton = new FICSPlayerConnectionParser();
   public static final Pattern masterPattern = Pattern.compile("^(\\[([\\w]+)\\shas\\s(connected|disconnected)\\.\\])", 8);


   protected FICSPlayerConnectionParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSPlayerConnectionEvent evt = new ICSPlayerConnectionEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setPlayer(m.group(2));
      evt.setConnected("connected".equals(m.group(3)));
      evt.setNotification(false);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
         StringBuffer sb = new StringBuffer(50);
         sb.append("[").append(evt.getPlayer()).append(" has ");
         if(evt.isConnected()) {
            sb.append("connected");
         } else {
            sb.append("disconnected");
         }

         sb.append(".]");
         return sb.toString();
      }
   }
}
