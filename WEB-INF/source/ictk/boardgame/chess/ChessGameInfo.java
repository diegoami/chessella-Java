package ictk.boardgame.chess;

import ictk.boardgame.GameInfo;
import ictk.boardgame.Player;
import ictk.boardgame.Result;
import ictk.boardgame.chess.ChessPlayer;
import ictk.boardgame.chess.ChessResult;
import ictk.util.Log;
import java.util.Enumeration;

public class ChessGameInfo extends GameInfo {

   public static final long DEBUG = Log.GameInfo;
   protected String eco;
   protected int whiteRating;
   protected int blackRating;
   protected int timeControlInitial;
   protected int timeControlIncrement;


   public ChessGameInfo() {
      this.players = new Player[2];
      this.result = new ChessResult(0);
   }

   public ChessGameInfo(ChessPlayer _white, ChessPlayer _black) {
      this.players = new Player[2];
      this.players[0] = _white;
      this.players[1] = _black;
   }

   public ChessPlayer getWhite() {
      return (ChessPlayer)this.players[0];
   }

   public ChessPlayer getBlack() {
      return (ChessPlayer)this.players[1];
   }

   public int getTimeControlInitial() {
      return this.timeControlInitial;
   }

   public int getTimeControlIncrement() {
      return this.timeControlIncrement;
   }

   public int getWhiteRating() {
      return this.whiteRating;
   }

   public int getBlackRating() {
      return this.blackRating;
   }

   public String getECO() {
      return this.eco;
   }

   public Result getResult() {
      return this.result;
   }

   public void setWhite(ChessPlayer w) {
      this.players[0] = w;
   }

   public void setBlack(ChessPlayer b) {
      this.players[1] = b;
   }

   public void setTimeControlInitial(int i) {
      this.timeControlInitial = i;
   }

   public void setTimeControlIncrement(int i) {
      this.timeControlIncrement = i;
   }

   public void setWhiteRating(int rating) {
      this.whiteRating = rating;
   }

   public void setBlackRating(int rating) {
      this.blackRating = rating;
   }

   public void setECO(String eco) {
      this.eco = eco;
   }

   public String toString() {
      String tmp = null;
      tmp = "[White: " + this.players[0] + "]\n" + "[Black: " + this.players[1] + "]\n" + "[Event: " + this.event + "]\n" + "[Site:  " + this.site + "]\n" + "[Date:  " + this.getDateString() + "]\n" + "[Round: " + this.round + "]\n" + "[SubRound: " + this.subround + "]\n" + "[TimeControlInitial: " + this.timeControlInitial + "]\n" + "[TimeControlIncrement: " + this.timeControlIncrement + "]\n" + "[Result: " + this.result + "]\n" + "[WhiteElo: " + this.whiteRating + "]\n" + "[BlackElo: " + this.blackRating + "]\n" + "[ECO: " + this.eco + "]";
      return tmp;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj != null && obj.getClass() == this.getClass()) {
         ChessGameInfo gi = (ChessGameInfo)obj;
         boolean t = true;
         Log.debug(DEBUG, "checking for equality");
         t = t && super.equals(obj);
         if(t) {
            t = t && this.timeControlInitial == gi.timeControlInitial;
            if(!t) {
               Log.debug2(DEBUG, "tcInit: " + this.timeControlInitial + " / " + gi.timeControlInitial);
            }
         }

         if(t) {
            t = t && this.timeControlIncrement == gi.timeControlIncrement;
            if(!t) {
               Log.debug2(DEBUG, "tcIncr: " + this.timeControlIncrement + " / " + gi.timeControlIncrement);
            }
         }

         if(t) {
            t = t && (this.result == null && gi.result == null || this.result != null && this.result.equals(gi.result));
            if(!t) {
               Log.debug2(DEBUG, "result: " + this.result + " / " + gi.result);
            }
         }

         if(t) {
            t = t && this.whiteRating == gi.whiteRating;
            if(!t) {
               Log.debug2(DEBUG, "whiteRating: " + this.whiteRating + " / " + gi.whiteRating);
            }
         }

         if(t) {
            t = t && this.blackRating == gi.blackRating;
            if(!t) {
               Log.debug2(DEBUG, "blackRating: " + this.blackRating + " / " + gi.blackRating);
            }
         }

         if(t) {
            t = t && (this.eco == null && gi.eco == null || this.eco != null && this.eco.equals(gi.eco));
            if(!t) {
               Log.debug2(DEBUG, "eco: " + this.eco + " / " + gi.eco);
            }
         }

         if(t) {
            t = t && (this.props == null && gi.props == null || this.props != null && this.props.equals(gi.props));
            if(!t) {
               Log.debug2(DEBUG, "aux: " + this.props + " / " + gi.props);
            }
         }

         if(t) {
            Log.debug2(DEBUG, "equal");
         }

         return t;
      } else {
         return false;
      }
   }

   public void dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("##GameInfo Dump").append(this.toString()).append("Aux Data:");
      if(this.props == null) {
         sb.append("None");
      } else {
         Enumeration enumer = this.props.propertyNames();
         String key = null;

         while(enumer.hasMoreElements()) {
            key = (String)enumer.nextElement();
            sb.append(key).append(" = ").append(this.props.getProperty(key, (String)null));
         }
      }

   }
}
