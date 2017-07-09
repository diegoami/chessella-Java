package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.ICSDate;
import ictk.boardgame.chess.net.ics.ICSMove;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSResult;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSMoveListEvent;
import ictk.util.Log;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSMoveListParser extends ICSEventParser {

   public static FICSMoveListParser singleton = new FICSMoveListParser();
   public static final Pattern masterPattern = Pattern.compile("^:?Movelist for game (\\d+):\\n:?\\n(\\S+)\\s(?:\\(\\s*([0-9+-]+[EP]?)\\))\\svs\\.\\s(\\S+)\\s(?:\\(\\s*([0-9+-]+[EP]?)\\))\\s---\\s((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))\\n^:?(Rated|Unrated)\\s(\\w+)\\smatch, initial time:\\s(\\d+)\\sminutes, increment:\\s(\\d+)\\sseconds\\.\\n\\nMove\\s+(\\S+)\\s+(\\S+)\\s*\\n^----\\s+---------------------\\s+---------------------\\n(.*)^\\s{6}\\{([^}]+)\\}\\s(\\S)", 40);
   public static final Pattern moveLinePattern = Pattern.compile("^:?\\s*(\\d+)\\.\\s+(\\S+)\\s+(\\((\\d+):(\\d\\d)\\.?(\\d{3})?\\))?\\s*((\\S+)?\\s*(\\((\\d+):(\\d\\d)\\.(\\d{3})?\\))?)?\\s*$", 8);


   protected FICSMoveListParser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSMoveListEvent evt = new ICSMoveListEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSMoveListEvent evt = (ICSMoveListEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      evt.setWhitePlayer(m.group(2));
      evt.setBlackPlayer(m.group(4));
      evt.setWhiteRating(new ICSRating(m.group(3)));
      evt.setBlackRating(new ICSRating(m.group(5)));
      evt.setVariant(new ICSVariant(m.group(15)));
      evt.setStatus(m.group(21));
      evt.setResult(new ICSResult(m.group(22)));
      evt.setRated("Rated".equals(m.group(14)));
      byte i = 0;
      ICSDate date = new ICSDate(m.group(6));
      evt.setDate(date);

      try {
         i = 1;
         evt.setBoardNumber(Integer.parseInt(m.group(i)));
         i = 16;
         evt.setInitialTime(Integer.parseInt(m.group(i)));
         i = 17;
         evt.setIncrement(Integer.parseInt(m.group(i)));
      } catch (NumberFormatException var15) {
         Log.error(3, "Couldn\'t parse a number in: " + m.group(i));
      }

      Matcher mvMatch = moveLinePattern.matcher(m.group(20));
      LinkedList tmplist = new LinkedList();
      ICSMove tmp = null;
      boolean min = false;
      boolean s = false;
      boolean ms = false;

      while(mvMatch.find()) {
         if(this.debug) {
            Log.debug(ICSEventParser.DEBUG, "move", mvMatch);
         }

         boolean var16;
         int var18;
         int var19;
         int var20;
         if(mvMatch.group(2) != null && !mvMatch.group(2).equals("...")) {
            tmp = new ICSMove();

            try {
               var16 = true;
               tmp.setSAN(mvMatch.group(2));
               tmp.setBlack(false);
               var16 = true;
               tmp.setMoveNumber(Integer.parseInt(mvMatch.group(1)));
               var16 = true;
               var18 = Integer.parseInt(mvMatch.group(4));
               var16 = true;
               var19 = Integer.parseInt(mvMatch.group(5));
               i = 6;
               var20 = Integer.parseInt(mvMatch.group(6));
               tmp.setMoveTime(var18 * '\uea60' + var19 * 1000 + var20);
            } catch (NumberFormatException var14) {
               Log.error(3, "threw NumberFormatExceptionfor(" + i + "): " + mvMatch.group(i) + " of " + mvMatch.group(0));
               evt.setEventType(0);
               evt.setMessage(m.group(0));
               Log.debug(ICSEventParser.DEBUG, "regex", mvMatch);
               return;
            }

            tmplist.add(tmp);
         }

         if(mvMatch.group(8) != null) {
            tmp = new ICSMove();

            try {
               var16 = true;
               tmp.setSAN(mvMatch.group(8));
               tmp.setBlack(true);
               var16 = true;
               tmp.setMoveNumber(Integer.parseInt(mvMatch.group(1)));
               var16 = true;
               var18 = Integer.parseInt(mvMatch.group(10));
               var16 = true;
               var19 = Integer.parseInt(mvMatch.group(11));
               i = 12;
               var20 = Integer.parseInt(mvMatch.group(12));
               tmp.setMoveTime(var18 * '\uea60' + var19 * 1000 + var20);
            } catch (NumberFormatException var13) {
               Log.error(3, "threw NumberFormatExceptionfor(" + i + "): " + mvMatch.group(i) + " of " + mvMatch.group(0));
               evt.setEventType(0);
               evt.setMessage(m.group(0));
               Log.debug(ICSEventParser.DEBUG, "regex", mvMatch);
               return;
            }

            tmplist.add(tmp);
         }
      }

      ICSMove[] moves = (ICSMove[])null;
      if(tmplist.size() > 0) {
         moves = new ICSMove[tmplist.size()];

         for(int var17 = 0; var17 < tmplist.size(); ++var17) {
            moves[var17] = (ICSMove)tmplist.get(var17);
         }
      }

      evt.setMoves(moves);
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSMoveListEvent evt = (ICSMoveListEvent)event;
         StringBuffer sb = new StringBuffer(20);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("Movelist for game ").append(evt.getBoardNumber()).append(":\n\n").append(evt.getWhitePlayer()).append(" (").append(evt.getWhiteRating()).append(") vs. ").append(evt.getBlackPlayer()).append(" (").append(evt.getBlackRating()).append(") --- ").append(evt.getDate()).append("\n");
         if(evt.isRated()) {
            sb.append("Rated ");
         } else {
            sb.append("Unrated ");
         }

         sb.append(evt.getVariant()).append(" match, initial time:");
         int time = evt.getInitialTime();
         if(time < 100) {
            sb.append(" ");
         }

         if(time < 10) {
            sb.append(" ");
         }

         sb.append(time).append(" minutes, increment:");
         int incr = evt.getIncrement();
         if(incr < 100) {
            sb.append(" ");
         }

         if(incr < 10) {
            sb.append(" ");
         }

         sb.append(incr).append(" seconds.\n\n");
         sb.append("Move  ");
         this.pad(sb, evt.getWhitePlayer(), 21, false);
         sb.append("   ").append(evt.getBlackPlayer()).append("\n----  ---------------------   ---------------------\n");
         ICSMove[] moves = evt.getMoves();
         if(moves != null) {
            if(moves[0].isBlack()) {
               sb.append("  1.  ...     (0:00.000)      ");
            }

            for(int i = 0; i < moves.length; ++i) {
               if(!moves[i].isBlack()) {
                  this.pad(sb, "" + moves[i].getMoveNumber(), 3, true);
                  sb.append(".  ");
               } else {
                  sb.append("   ");
               }

               this.pad(sb, moves[i].getSAN(), 8, false);
               if(!moves[i].isBlack() && i < moves.length - 1) {
                  this.pad(sb, "(" + moves[i].getMoveTimeAsString() + ")", 13, false);
               } else {
                  sb.append("(").append(moves[i].getMoveTimeAsString()).append(")\n");
               }
            }
         }

         sb.append("      {").append(evt.getStatus()).append("} ").append(evt.getResult()).append("\n");
         return sb.toString();
      }
   }

   protected void pad(StringBuffer sb, String str, int max, boolean rj) {
      int len = str.length();
      if(!rj) {
         sb.append(str);
      }

      for(int i = len; i < max; ++i) {
         sb.append(" ");
      }

      if(rj) {
         sb.append(str);
      }

   }
}
