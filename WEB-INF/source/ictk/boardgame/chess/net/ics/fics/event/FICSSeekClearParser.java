package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSSeekClearEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSSeekClearParser extends ICSEventParser {

   public static FICSSeekClearParser singleton = new FICSSeekClearParser();
   public static final Pattern masterPattern = Pattern.compile("^(<sc>)", 8);


   protected FICSSeekClearParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSSeekClearEvent evt = new ICSSeekClearEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSSeekClearEvent evt = (ICSSeekClearEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSSeekClearEvent evt = (ICSSeekClearEvent)event;
         StringBuffer sb = new StringBuffer(5);
         sb.append("<sc>");
         return sb.toString();
      }
   }
}
