package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSMessageEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParser;

public class ICSKibitzEvent extends ICSMessageEvent implements ICSBoardEvent {

   protected static final int KIBITZ_EVENT = 17;
   protected String player;
   protected ICSAccountType acctType;
   protected ICSRating rating;
   protected int boardNumber;


   public ICSKibitzEvent() {
      super(17);
   }

   public String getPlayer() {
      return this.player;
   }

   public ICSAccountType getAccountType() {
      return this.acctType;
   }

   public ICSRating getRating() {
      return this.rating;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public void setAccountType(ICSAccountType acctType) {
      this.acctType = acctType;
   }

   public void setRating(ICSRating rating) {
      this.rating = rating;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public String getReadable() {
      return FICSKibitzParser.getInstance().toNative(this);
   }
}
