package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.util.Log;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ICSEventParser {

   public static final long DEBUG = Log.ICSEventParser;
   public static final String REGEX_date = "((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))";
   protected int eventType = 0;
   protected Pattern pattern;
   protected boolean debug;


   protected ICSEventParser(Pattern master) {
      this.pattern = master;
   }

   public void setDebug(boolean t) {
      this.debug = t;
   }

   public Pattern getPattern() {
      return this.pattern;
   }

   public int getEventType() {
      return this.eventType;
   }

   public Matcher match(CharSequence s) {
      Matcher m = this.pattern.matcher(s);
      if(m.find()) {
         if(this.debug) {
            Log.debug(DEBUG, "matched: " + s, m);
         }

         return m;
      } else {
         if(this.debug) {
            Log.debug(DEBUG, "failed: " + s);
         }

         return null;
      }
   }

   public ICSEvent createICSEvent(CharSequence s) {
      Matcher m = this.match(s);
      return m != null?this.createICSEvent(m):null;
   }

   public abstract ICSEvent createICSEvent(Matcher var1);

   public abstract void assignMatches(Matcher var1, ICSEvent var2);

   public boolean detectFake(CharSequence s) {
      return s.charAt(0) == 58;
   }

   public abstract String toNative(ICSEvent var1);

   protected ICSAccountType parseICSAccountType(Matcher match, int index) {
      ICSAccountType acct = null;

      try {
         if(match.group(index) != null) {
            acct = new ICSAccountType(match.group(index));
         } else {
            acct = new ICSAccountType();
         }
      } catch (IOException var5) {
         Log.error(3, "Can\'t parse account type: " + match.group(index) + " of " + match.group(0));
         acct = new ICSAccountType();
         if(this.debug) {
            Log.debug(DEBUG, "regex:", match);
         }
      }

      return acct;
   }

   protected ICSRating parseICSRating(Matcher match, int index) {
      ICSRating rating = null;

      try {
         if(match.group(index) != null) {
            rating = new ICSRating(match.group(index));
         }
      } catch (NumberFormatException var5) {
         Log.error(3, "Can\'t parse rating" + match.group(index) + " of " + match.group(0));
         if(this.debug) {
            Log.debug(DEBUG, "regex:", match);
         }
      }

      return rating;
   }
}
