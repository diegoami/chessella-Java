package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSResult;
import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParser;

public class ICSGameResultEvent extends ICSEvent implements ICSBoardEvent {

   protected static final int GAME_RESULT_EVENT = 3;
   protected int boardNumber;
   protected String white;
   protected String black;
   protected ICSResult result;


   public ICSGameResultEvent() {
      super(3);
   }

   public String getWhitePlayer() {
      return this.white;
   }

   public String getBlackPlayer() {
      return this.black;
   }

   public ICSResult getResult() {
      return this.result;
   }

   public void setWhitePlayer(String white) {
      this.white = white;
   }

   public void setBlackPlayer(String black) {
      this.black = black;
   }

   public void setResult(ICSResult result) {
      this.result = result;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public String getReadable() {
      return FICSGameResultParser.getInstance().toNative(this);
   }
}
