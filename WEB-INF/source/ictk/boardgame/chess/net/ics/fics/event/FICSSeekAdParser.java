package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSSeekAdEvent;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSSeekAdParser extends ICSEventParser {

   public static FICSSeekAdParser singleton = new FICSSeekAdParser();
   public static final Pattern masterPattern = Pattern.compile("^(<s(n)?>\\s(\\d+)\\sw=([\\w]+)\\sti=(\\d+)\\srt=(\\d+[\\sPE])\\st=(\\d+)\\si=(\\d+)\\sr=([ur])\\stp=([\\S]+)\\sc=([BW\\?])\\srr=(\\d+)-(\\d+)\\sa=([tf])\\sf=([tf]))", 8);


   protected FICSSeekAdParser() {
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

      try {
         evt.setAdNumber(Integer.parseInt(m.group(3)));
      } catch (NumberFormatException var11) {
         Log.error(3, "Can\'t parse number for: " + m.group(3) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setPlayer(m.group(4));
      evt.setRating(this.parseICSRating(m, 6));

      try {
         evt.setInitialTime(Integer.parseInt(m.group(7)));
      } catch (NumberFormatException var10) {
         Log.error(3, "Can\'t parse time for: " + m.group(7) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setIncrement(Integer.parseInt(m.group(8)));
      } catch (NumberFormatException var9) {
         Log.error(3, "Can\'t parse incr for: " + m.group(8) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      evt.setVariant(new ICSVariant(m.group(10)));

      try {
         evt.setRatingRangeLow(Integer.parseInt(m.group(12)));
      } catch (NumberFormatException var8) {
         Log.error(3, "Can\'t parse rangeLow for: " + m.group(12) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      try {
         evt.setRatingRangeHigh(Integer.parseInt(m.group(13)));
      } catch (NumberFormatException var7) {
         Log.error(3, "Can\'t parse rangeHigh for: " + m.group(13) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
         return;
      }

      if(m.group(2) == null) {
         evt.meetsFormula(true);
      } else if(m.group(2).charAt(0) == 110) {
         evt.meetsFormula(false);
      } else {
         Log.error(3, "Received unknown character in <s[n]?> area: " + m.group(2) + " of " + m.group(0));
      }

      evt.setRated(m.group(9).charAt(0) == 114);
      switch(m.group(11).charAt(0)) {
      case 63:
         evt.setColor(0);
         break;
      case 66:
         evt.setColor(2);
         break;
      case 87:
         evt.setColor(1);
         break;
      default:
         Log.error(3, "Received unknown character in c=[WB\\?] area: " + m.group(11) + " of " + m.group(0));
      }

      evt.setManual(m.group(14).charAt(0) == 102);
      evt.setRestrictedByFormula(m.group(15).charAt(0) == 116);
      boolean acct = false;

      try {
         int acct1 = Integer.parseInt(m.group(5), 16);
      } catch (NumberFormatException var6) {
         Log.error(3, "Can\'t parser number " + m.group(5) + " of " + m.group(0));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         return;
      }

      ICSAccountType accttype = new ICSAccountType();
      evt.setAccountType(accttype);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
         StringBuffer sb = new StringBuffer(80);
         sb.append("<s");
         if(!evt.meetsFormula()) {
            sb.append("n");
         }

         sb.append("> ").append(evt.getAdNumber()).append(" w=").append(evt.getPlayer()).append(" ti=").append("00").append(" rt=").append(evt.getRating()).append(" t=").append(evt.getInitialTime()).append(" i=").append(evt.getIncrement()).append(" r=").append((char)(evt.isRated()?'r':'u')).append(" tp=").append(evt.getVariant()).append(" c=");
         switch(evt.getColor()) {
         case 0:
            sb.append("?");
            break;
         case 1:
            sb.append("W");
            break;
         case 2:
            sb.append("B");
         }

         sb.append(" rr=").append(evt.getRatingRangeLow()).append("-").append(evt.getRatingRangeHigh()).append(" a=").append((char)(evt.isManual()?'f':'t')).append(" f=").append((char)(evt.isRestrictedByFormula()?'t':'f'));
         return sb.toString();
      }
   }
}
