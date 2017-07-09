package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSBoardUpdateEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSBoardUpdateStyle12Parser extends ICSEventParser {

   public static FICSBoardUpdateStyle12Parser singleton = new FICSBoardUpdateStyle12Parser();
   public static final Pattern masterPattern = Pattern.compile("^:?(<12>\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([BW])\\s(-?[0-7])\\s([01])\\s([01])\\s([01])\\s([01])\\s(\\d+)\\s(\\d+)\\s([\\w]+)\\s([\\w]+)\\s([-]?[0-3])\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(-?\\d+)\\s(-?\\d+)\\s(\\d+)\\s(\\S+)\\s\\((\\d+):(\\d+)\\.?(\\d+)?\\)\\s(\\S+)\\s([01])\\s([01])\\s(\\d+))", 8);


   protected FICSBoardUpdateStyle12Parser() {
      super(masterPattern);
   }

   public static ICSEventParser getInstance() {
      return singleton;
   }

   public ICSEvent createICSEvent(Matcher match) {
      ICSBoardUpdateEvent evt = new ICSBoardUpdateEvent();
      this.assignMatches(match, evt);
      return evt;
   }

   public void assignMatches(Matcher m, ICSEvent event) {
      ICSBoardUpdateEvent evt = (ICSBoardUpdateEvent)event;
      if(this.debug) {
         Log.debug(DEBUG, "assigning matches", m);
      }

      int rank = 7;
      int gr = 2;
      String srank = null;

      char[][] board;
      for(board = new char[8][8]; rank < 8 && rank >= 0; ++gr) {
         srank = m.group(gr);

         for(int i = 0; i < 8; ++i) {
            if(srank.charAt(i) != 45) {
               board[i][rank] = srank.charAt(i);
            } else {
               board[i][rank] = 32;
            }
         }

         --rank;
      }

      evt.setBoardArray(board);
      if(m.group(10).charAt(0) == 66) {
         evt.setBlackMove(true);
      }

      evt.setWhitePlayer(m.group(18));
      evt.setBlackPlayer(m.group(19));
      evt.setWhiteCastleableKingside(m.group(12).charAt(0) == 49);
      evt.setWhiteCastleableQueenside(m.group(13).charAt(0) == 49);
      evt.setBlackCastleableKingside(m.group(14).charAt(0) == 49);
      evt.setBlackCastleableQueenside(m.group(15).charAt(0) == 49);
      evt.setVerboseMove(m.group(28));
      evt.setSAN(m.group(32));
      evt.setFlipBoard(m.group(33).charAt(0) == 49);
      evt.setClockMoving(m.group(34).charAt(0) == 49);
      byte var11 = 0;

      try {
         boolean var12 = true;
         evt.setEnPassantFile(Integer.parseInt(m.group(11)) + 1);
         var12 = true;
         evt.setPlySinceLastIrreversableMove(Integer.parseInt(m.group(16)));
         var12 = true;
         evt.setBoardNumber(Integer.parseInt(m.group(17)));
         var12 = true;
         evt.setRelation(Integer.parseInt(m.group(20)));
         var12 = true;
         evt.setInitialTime(Integer.parseInt(m.group(21)));
         var12 = true;
         evt.setIncrement(Integer.parseInt(m.group(22)));
         var12 = true;
         evt.setWhiteMaterial(Integer.parseInt(m.group(23)));
         var12 = true;
         evt.setBlackMaterial(Integer.parseInt(m.group(24)));
         var12 = true;
         evt.setWhiteClock(Integer.parseInt(m.group(25)));
         var12 = true;
         evt.setBlackClock(Integer.parseInt(m.group(26)));
         var12 = true;
         evt.setMoveNumber(Integer.parseInt(m.group(27)));
         var12 = true;
         int var10001 = Integer.parseInt(m.group(29)) * '\uea60';
         var12 = true;
         var10001 += Integer.parseInt(m.group(30)) * 1000;
         var12 = true;
         evt.setMoveTime(var10001 + Integer.parseInt(m.group(31)));
         var12 = true;
         evt.setLag(Integer.parseInt(m.group(35)));
      } catch (NumberFormatException var10) {
         Log.error(3, "threw NumberFormatExceptionfor(" + var11 + "): " + m.group(var11));
         evt.setEventType(0);
         evt.setMessage(m.group(0));
         Log.debug(ICSEventParser.DEBUG, "regex", m);
      }
   }

   public String toNative(ICSEvent event) {
      if(event.getEventType() == 0) {
         return event.getMessage();
      } else {
         ICSBoardUpdateEvent evt = (ICSBoardUpdateEvent)event;
         StringBuffer sb = new StringBuffer(180);
         if(evt.isFake()) {
            sb.append(":");
         }

         sb.append("<12> ");
         char[][] board = evt.getBoardArray();

         for(int r = 7; r >= 0; --r) {
            for(int f = 0; f < board.length; ++f) {
               if(board[f][r] == 32) {
                  sb.append('-');
               } else {
                  sb.append(board[f][r]);
               }
            }

            sb.append(' ');
         }

         if(evt.isBlackMove()) {
            sb.append('B');
         } else {
            sb.append('W');
         }

         sb.append(" ").append(evt.getEnPassantFile() - 1).append(evt.isWhiteCastleableKingside()?" 1":" 0").append(evt.isWhiteCastleableQueenside()?" 1":" 0").append(evt.isBlackCastleableKingside()?" 1":" 0").append(evt.isBlackCastleableQueenside()?" 1":" 0").append(" ").append(evt.getPlySinceLastIrreversableMove()).append(" ").append(evt.getBoardNumber()).append(" ").append(evt.getWhitePlayer()).append(" ").append(evt.getBlackPlayer()).append(" ").append(evt.getRelation()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement()).append(" ").append(evt.getWhiteMaterial()).append(" ").append(evt.getBlackMaterial()).append(" ").append(evt.getWhiteClock()).append(" ").append(evt.getBlackClock()).append(" ").append(evt.getMoveNumber()).append(" ").append(evt.getVerboseMove()).append(" (").append(this.getClockAsString(evt.getMoveTime(), true)).append(") ").append(evt.getSAN()).append(evt.isFlipBoard()?" 1":" 0").append(evt.isClockMoving()?" 1 ":" 0 ").append(evt.getLag());
         return sb.toString();
      }
   }

   protected String getClockAsString(int clock, boolean move) {
      StringBuffer sb = new StringBuffer(7);
      int h = clock / 3600000;
      int m = clock % 3600000 / '\uea60';
      int s = clock % '\uea60' / 1000;
      int ms = clock % 1000;
      if(move && h > 1) {
         sb.append(h).append(":");
         if(m < 10) {
            sb.append(0);
         }
      }

      sb.append(m).append(":");
      if(s < 10) {
         sb.append(0);
      }

      sb.append(s).append(".");
      if(ms < 100) {
         sb.append(0);
      }

      if(ms < 10) {
         sb.append(0);
      }

      sb.append(ms);
      return sb.toString();
   }
}
