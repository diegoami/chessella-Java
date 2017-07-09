package ictk.boardgame;

import ictk.boardgame.Board;
import java.util.EventListener;

public interface BoardListener extends EventListener {

   void boardUpdate(Board var1, int var2);
}
