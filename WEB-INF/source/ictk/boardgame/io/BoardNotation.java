package ictk.boardgame.io;

import ictk.boardgame.Board;
import java.io.IOException;

public interface BoardNotation {

   Board stringToBoard(String var1) throws IOException;

   String boardToString(Board var1);
}
