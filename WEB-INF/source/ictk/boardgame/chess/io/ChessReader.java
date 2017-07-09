package ictk.boardgame.chess.io;

import ictk.boardgame.Game;
import ictk.boardgame.io.GameReader;
import java.io.Reader;

public abstract class ChessReader extends GameReader {

   public ChessReader(Reader _ir) {
      super(_ir);
   }

   public abstract Game getGame();
}
