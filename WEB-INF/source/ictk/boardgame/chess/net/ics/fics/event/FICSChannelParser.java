package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSChannelParser extends ICSEventParser {

   public static FICSChannelParser singleton = new FICSChannelParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(([\\w]+)((?:\\([A-Z*]+\\))*)\\(([T])?(\\d+)\\):\\s((?:.|\\s+\\\\|\\s+:)*))", 8);


   protected FICSChannelParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSChannelEvent evt = new ICSChannelEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSChannelEvent evt = (ICSChannelEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));
      evt.setPlayer(m.group(2));
      evt.setAccountType(this.parseICSAccountType(m, 3));

      try {
         evt.setChannel(Integer.parseInt(m.group(5)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse channel for: " + m.group(5) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setMessage(m.group(6));
      if(m.group(4) != null && "T".equals(m.group(4))) {
         evt.setEventType(8);
      }

   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSChannelEvent evt = (ICSChannelEvent)event;
         StringBuffer sb = new StringBuffer(80);
         if(evt.isFake()) {
            sb.append(":");
         }

         Object str = null;
         sb.append(evt.getPlayer()).append(evt.getAccountType());
         sb.append("(");
         if(evt.getEventType() == 8) {
            sb.append("T");
         }

         sb.append(evt.getChannel()).append("): ");
         sb.append(evt.getMessage());
         return sb.toString();
      }
   }
}
