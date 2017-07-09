package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSSeekRemoveEvent;
import ictk.util.Log;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSSeekRemoveParser extends ICSEventParser {

   public static FICSSeekRemoveParser singleton = new FICSSeekRemoveParser();
   public static final Pattern masterPattern = Pattern.compile("^(<sr>((?:\\s\\d+)+))", 8);


   protected FICSSeekRemoveParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSSeekRemoveEvent evt = new ICSSeekRemoveEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSSeekRemoveEvent evt = (ICSSeekRemoveEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      StringTokenizer st = new StringTokenizer(m.group(2));
      int[] ads = new int[st.countTokens()];
      int i = 0;

      try {
         while(st.hasMoreTokens()) {
            ads[i] = Integer.parseInt(st.nextToken());
            ++i;
         }
      } catch (NumberFormatException var8) {
         Log.error(3, "Can\'t parse ads[" + i + "] " + "of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setAds(ads);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSSeekRemoveEvent evt = (ICSSeekRemoveEvent)event;
         StringBuffer sb = new StringBuffer(5);
         int[] ads = evt.getAds();
         sb.append("<sr>");

         for(int i = 0; i < ads.length; ++i) {
            sb.append(" ").append(ads[i]);
         }

         return sb.toString();
      }
   }
}
