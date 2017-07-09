package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSTellEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSTellParser extends ICSEventParser {

   public static FICSTellParser singleton = new FICSTellParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(([\\w]+)((?:\\([A-Z*]+\\))*)\\s(tells\\syou|says):\\s((?:.|\\s+\\\\|\\s+:)*))", 8);


   protected FICSTellParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSTellEvent evt = new ICSTellEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSTellEvent evt = (ICSTellEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));
      evt.setPlayer(m.group(2));
      evt.setAccountType(this.parseICSAccountType(m, 3));
      evt.setMessage(m.group(5));
      if("tells you".equals(m.group(4))) {
         evt.setEventType(9);
      } else if("says".equals(m.group(4))) {
         evt.setEventType(10);
      }

   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSTellEvent evt = (ICSTellEvent)event;
         StringBuffer sb = new StringBuffer(20);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append(evt.getPlayer()).append(evt.getAccountType());
         switch(evt.getEventType()) {
         case 9:
            sb.append(" tells you: ");
            break;
         case 10:
            sb.append(" says: ");
         }

         sb.append(evt.getMessage());
         return sb.toString();
      }
   }
}
