package ictk.boardgame.ui;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;

public interface BoardDisplay extends BoardListener {

   void setBoard(Board var1);

   Board getBoard();

   void update();
}
