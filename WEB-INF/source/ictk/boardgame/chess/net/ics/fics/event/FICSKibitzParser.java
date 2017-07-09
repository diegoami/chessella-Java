package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSKibitzEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSKibitzParser extends ICSEventParser {

   public static FICSKibitzParser singleton = new FICSKibitzParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(([\\w]+)((?:\\([A-Z*]+\\))*)(?:\\(\\s*([0-9+-]+[EP]?)\\))?\\[(\\d+)\\]\\s(kibitzes|whispers|says):\\s((?:.|\\s+\\\\|\\s+:)*))", 8);


   protected FICSKibitzParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSKibitzEvent evt = new ICSKibitzEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSKibitzEvent evt = (ICSKibitzEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));
      evt.setPlayer(m.group(2));
      evt.setAccountType(this.parseICSAccountType(m, 3));
      evt.setRating(this.parseICSRating(m, 4));

      try {
         evt.setBoardNumber(Integer.parseInt(m.group(5)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse boardNumber for: " + m.group(5) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setMessage(m.group(7));
      if("whispers".equals(m.group(6))) {
         evt.setEventType(18);
      } else if("says".equals(m.group(6))) {
         evt.setEventType(19);
      }

   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSKibitzEvent evt = (ICSKibitzEvent)event;
         StringBuffer sb = new StringBuffer(20);
         if(evt.isFake()) {
            sb.append(":");
         }

         String str = null;
         sb.append(evt.getPlayer()).append(evt.getAccountType());
         if(evt.getEventType() != 19) {
            sb.append("(");
            str = evt.getRating().toString();

            for(int i = 0; i < 4 - str.length(); ++i) {
               sb.append(" ");
            }

            sb.append(str);
            sb.append(")");
         }

         sb.append("[").append(evt.getBoardNumber()).append("]");
         switch(evt.getEventType()) {
         case 17:
            sb.append(" kibitzes: ");
            break;
         case 18:
            sb.append(" whispers: ");
            break;
         case 19:
            sb.append(" says: ");
         }

         sb.append(evt.getMessage());
         return sb.toString();
      }
   }
}
