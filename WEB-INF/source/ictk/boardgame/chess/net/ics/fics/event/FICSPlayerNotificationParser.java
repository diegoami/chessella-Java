package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSPlayerConnectionEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSPlayerNotificationParser extends ICSEventParser {

   public static FICSPlayerNotificationParser singleton = new FICSPlayerNotificationParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(Notification:\\s([\\w]+)((?:\\([A-Z*]+\\))*)\\shas\\s(arrived|departed)(\\sand\\sisn\'t\\son\\syour\\snotify\\slist)?\\.)", 8);


   protected FICSPlayerNotificationParser() {
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

      evt.setFake(this.detectFake(m.group(0)));
      evt.setPlayer(m.group(2));
      evt.setAccountType(this.parseICSAccountType(m, 3));
      evt.setConnected("arrived".equals(m.group(4)));
      evt.setNotification(true);
      evt.setOnNotifyList(m.group(5) != null);
      evt.setEventType(24);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
         StringBuffer sb = new StringBuffer(40);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("Notification: ").append(evt.getPlayer()).append(evt.getAccountType()).append(" has ");
         if(evt.isConnected()) {
            sb.append("arrived");
         } else {
            sb.append("departed");
         }

         if(evt.isOnNotifyList()) {
            sb.append(" and isn\'t on your notify list");
         }

         sb.append(".");
         return sb.toString();
      }
   }
}
