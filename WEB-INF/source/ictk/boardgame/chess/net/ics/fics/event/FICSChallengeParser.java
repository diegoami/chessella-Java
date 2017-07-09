package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSChallengeEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSChallengeParser extends ICSEventParser {

   public static FICSChallengeParser singleton = new FICSChallengeParser();
   public static final Pattern masterPattern = Pattern.compile("^:?(Challenge:\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(?:\\[(white|black)\\])?\\s?([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(rated|unrated)\\s(\\S+)\\s(\\d+)\\s(\\d+)\\.\\n(?:--\\*\\*\\s([\\w]+)\\sis\\san?\\s(computer|abuser)\\s\\*\\*--\\n)?You\\scan\\s\"accept\"\\sor\\s\"decline\",\\sor\\spropose\\sdifferent\\sparameters\\.)", 8);


   protected FICSChallengeParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSChallengeEvent evt = new ICSChallengeEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSChallengeEvent evt = (ICSChallengeEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setFake(this.detectFake(m.group(0)));
      evt.setChallenger(m.group(2));
      evt.setChallengerRating(this.parseICSRating(m, 3));
      evt.setChallenged(m.group(5));
      evt.setChallengedRating(this.parseICSRating(m, 6));
      evt.setVariant(new ICSVariant(m.group(8)));

      try {
         evt.setInitialTime(Integer.parseInt(m.group(9)));
      } catch (NumberFormatException var6) {
         Log.error(3, "Can\'t parse time for: " + m.group(9) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setIncrementTime(Integer.parseInt(m.group(10)));
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse incr for: " + m.group(10) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      if(m.group(4) != null) {
         evt.setColorSpecified(true);
         if(m.group(4).equals("white")) {
            evt.setWhite(true);
         }
      }

      evt.setRated("rated".equals(m.group(7)));
      if(m.group(12) != null) {
         if("computer".equals(m.group(12))) {
            evt.setComputer(true);
         } else if("abuser".equals(m.group(12))) {
            evt.setAbuser(true);
         } else {
            Log.error(3, "unknown Challenge event alert: " + m.group(12));
         }
      }

   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSChallengeEvent evt = (ICSChallengeEvent)event;
         StringBuffer sb = new StringBuffer(154);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("Challenge: ").append(evt.getChallenger()).append(" (").append(evt.getChallengerRating()).append(") ");
         if(evt.isColorSpecified()) {
            if(evt.isWhite()) {
               sb.append("[white] ");
            } else {
               sb.append("[black] ");
            }
         }

         sb.append(evt.getChallenged()).append(" (").append(evt.getChallengedRating()).append(") ");
         if(evt.isRated()) {
            sb.append("rated ");
         } else {
            sb.append("unrated ");
         }

         sb.append(evt.getVariant()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrementTime()).append(".\n");
         if(evt.isComputer()) {
            sb.append("--** ").append(evt.getChallenger()).append(" is a computer **--\n");
         }

         if(evt.isAbuser()) {
            sb.append("--** ").append(evt.getChallenger()).append(" is an abuser **--\n");
         }

         sb.append("You can \"accept\" or \"decline\", or propose different parameters.");
         return sb.toString();
      }
   }
}
