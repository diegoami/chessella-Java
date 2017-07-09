package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSShoutParser extends ICSEventParser {

   public static FICSShoutParser singleton = new FICSShoutParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(((-->\\s)(?:\\n\\\\\\s+)?([\\w]+)((?:\\([A-Z*]+\\))*)((?:.|\\s+\\\\|\\s+:)*))|(([\\w]+)((?:\\([A-Z*]+\\))*)\\s+(?:([sct])-)?shouts:\\s)((?:.|\\s+\\\\|\\s+:)*))", 8);


   protected FICSShoutParser() {
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
      evt.setEventType(7);
      if(m.group(3) != null) {
         evt.setChannel(2);
         evt.setPlayer(m.group(4));
         evt.setAccountType(this.parseICSAccountType(m, 5));
         evt.setMessage(m.group(6));
      } else {
         evt.setPlayer(m.group(8));
         evt.setAccountType(this.parseICSAccountType(m, 9));
         evt.setMessage(m.group(11));
         if(m.group(10) != null) {
            switch(m.group(10).charAt(0)) {
            case 99:
               evt.setChannel(3);
               break;
            case 115:
               evt.setChannel(4);
               break;
            case 116:
               evt.setChannel(5);
               break;
            default:
               Log.error(3, "Received unknown shout type: \'" + m.group(10).charAt(0) + "\' from " + m.group(0));
               evt.setEventType(0);
               evt.setMessage(m.group(0));
               return;
            }
         } else {
            evt.setChannel(1);
         }
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

         if(evt.getChannel() == 2) {
            sb.append("--> ").append(evt.getPlayer()).append(evt.getAccountType());
         } else {
            sb.append(evt.getPlayer()).append(evt.getAccountType()).append(" ");
            switch(evt.getChannel()) {
            case 1:
               break;
            case 2:
            default:
               throw new IllegalStateException("Tried to get a toNative() with the ShoutParser when the channel is not a shout -- should use the Channel Parser for channel(" + evt.getChannel() + ")");
            case 3:
               sb.append("c-");
               break;
            case 4:
               sb.append("s-");
               break;
            case 5:
               sb.append("t-");
            }

            sb.append("shouts: ");
         }

         sb.append(evt.getMessage());
         return sb.toString();
      }
   }
}
