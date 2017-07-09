package ictk.boardgame.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Board;
import ictk.boardgame.Game;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.io.InvalidGameFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public abstract class GameReader extends BufferedReader {

   public GameReader(Reader _ir) {
      super(_ir);
   }

   public abstract Game readGame() throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException;

   public abstract GameInfo readGameInfo() throws IOException;

   public abstract History readHistory() throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException;

   public abstract Board readBoard() throws IOException;
}
