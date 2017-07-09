package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSSeekAdEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSSeekAdReadableParser extends ICSEventParser {

   public static FICSSeekAdReadableParser singleton = new FICSSeekAdReadableParser();
   public static final Pattern masterPattern = Pattern.compile("^(([\\w]+)((?:\\([A-Z*]+\\))*)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\sseeking\\s(\\d+)\\s(\\d+)\\s(rated|unrated)\\s(\\S+)(?:\\s\\[(\\w+)\\])?(?:\\s(m))?(?:\\s(f))?\\s\\(\"play\\s(\\d+)\"\\sto\\srespond\\))", 8);


   protected FICSSeekAdReadableParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSSeekAdEvent evt = new ICSSeekAdEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setPlayer(m.group(2));
      evt.setAccountType(this.parseICSAccountType(m, 3));
      evt.setRating(this.parseICSRating(m, 4));

      try {
         evt.setInitialTime(Integer.parseInt(m.group(5)));
      } catch (NumberFormatException var7) {
         Log.error(3, "Can\'t parse time for: " + m.group(5) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setIncrement(Integer.parseInt(m.group(6)));
      } catch (NumberFormatException var6) {
         Log.error(3, "Can\'t parse incr for: " + m.group(6) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setVariant(new ICSVariant(m.group(8)));

      try {
         evt.setAdNumber(Integer.parseInt(m.group(12)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse number for: " + m.group(12) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setEventType(14);
      evt.setRated(m.group(7).equals("rated"));
      if(m.group(9) == null) {
         evt.setColor(0);
      } else if(m.group(9).equals("white")) {
         evt.setColor(1);
      } else {
         evt.setColor(2);
      }

      evt.setManual(m.group(10) != null);
      evt.setRestrictedByFormula(m.group(11) != null);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
         StringBuffer sb = new StringBuffer(80);
         sb.append(evt.getPlayer()).append(evt.getAccountType()).append(" (");
         int rating = evt.getRating().get();
         if(!evt.getRating().isNotSet() && !evt.getRating().isNotApplicable()) {
            if(rating < 1000) {
               sb.append(" ");
            }

            if(rating < 100) {
               sb.append(" ");
            }

            if(rating < 10) {
               sb.append(" ");
            }
         }

         sb.append(evt.getRating()).append(") seeking ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement());
         if(evt.isRated()) {
            sb.append(" rated ");
         } else {
            sb.append(" unrated ");
         }

         sb.append(evt.getVariant());
         if(evt.getColor() == 1) {
            sb.append(" [white]");
         } else if(evt.getColor() == 2) {
            sb.append(" [black]");
         }

         if(evt.isManual()) {
            sb.append(" m");
         }

         if(evt.isRestrictedByFormula()) {
            sb.append(" f");
         }

         sb.append(" (\"play ").append(evt.getAdNumber()).append("\" to respond)");
         return sb.toString();
      }
   }
}
