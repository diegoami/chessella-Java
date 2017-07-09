package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameNotificationParser;

public class ICSGameNotificationEvent extends ICSEvent implements ICSBoardEvent {

   protected static final int GAME_NOTIFICATION_EVENT = 4;
   protected int boardNumber;
   protected String white;
   protected ICSRating whiteRating;
   protected String black;
   protected ICSRating blackRating;
   protected boolean rated;
   protected ICSVariant variant;
   protected int time;
   protected int incr;


   public ICSGameNotificationEvent() {
      super(4);
   }

   public String getWhitePlayer() {
      return this.white;
   }

   public ICSRating getWhiteRating() {
      return this.whiteRating;
   }

   public String getBlackPlayer() {
      return this.black;
   }

   public ICSRating getBlackRating() {
      return this.blackRating;
   }

   public boolean isRated() {
      return this.rated;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public int getInitialTime() {
      return this.time;
   }

   public int getIncrement() {
      return this.incr;
   }

   public void setWhitePlayer(String white) {
      this.white = white;
   }

   public void setWhiteRating(ICSRating whiteRating) {
      this.whiteRating = whiteRating;
   }

   public void setBlackPlayer(String black) {
      this.black = black;
   }

   public void setBlackRating(ICSRating blackRating) {
      this.blackRating = blackRating;
   }

   public void setRated(boolean rated) {
      this.rated = rated;
   }

   public void setVariant(ICSVariant variant) {
      this.variant = variant;
   }

   public void setInitialTime(int time) {
      this.time = time;
   }

   public void setIncrement(int incr) {
      this.incr = incr;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public String getReadable() {
      return FICSGameNotificationParser.getInstance().toNative(this);
   }
}
