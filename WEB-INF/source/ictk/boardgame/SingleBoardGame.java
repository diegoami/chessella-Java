package ictk.boardgame;

import ictk.boardgame.Board;
import ictk.boardgame.Game;

public interface SingleBoardGame extends Game {

   Board getBoard();

   void setBoard(Board var1);

   int getPlayerToMove();
}
