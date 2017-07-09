package ictk.boardgame.chess.net.ics.event;


public interface ICSBoardEvent {

   int NO_BOARD = -1;


   int getBoardNumber();

   void setBoardNumber(int var1);
}
