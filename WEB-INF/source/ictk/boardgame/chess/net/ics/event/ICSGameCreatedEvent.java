package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameCreatedParser;

public class ICSGameCreatedEvent extends ICSEvent implements ICSBoardEvent {

   protected static final int GAME_CREATED_EVENT = 2;
   protected int boardNumber;
   protected String white;
   protected String black;
   protected boolean continued;
   protected boolean rated;
   protected ICSVariant variant;


   public ICSGameCreatedEvent() {
      super(2);
   }

   public String getWhitePlayer() {
      return this.white;
   }

   public String getBlackPlayer() {
      return this.black;
   }

   public boolean isContinued() {
      return this.continued;
   }

   public boolean isRated() {
      return this.rated;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public void setWhitePlayer(String white) {
      this.white = white;
   }

   public void setBlackPlayer(String black) {
      this.black = black;
   }

   public void setContinued(boolean continued) {
      this.continued = continued;
   }

   public void setRated(boolean rated) {
      this.rated = rated;
   }

   public void setVariant(ICSVariant variant) {
      this.variant = variant;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public String getReadable() {
      return FICSGameCreatedParser.getInstance().toNative(this);
   }
}
