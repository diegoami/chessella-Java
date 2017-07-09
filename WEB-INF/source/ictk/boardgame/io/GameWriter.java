package ictk.boardgame.io;

import ictk.boardgame.Board;
import ictk.boardgame.Game;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.io.MoveNotation;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class GameWriter extends PrintWriter {

   public GameWriter(OutputStream _out) {
      super(_out, true);
   }

   public GameWriter(Writer _out) {
      super(_out);
   }

   public abstract void setMoveNotation(MoveNotation var1);

   public abstract MoveNotation getMoveNotation();

   public abstract void setExportVariations(boolean var1);

   public abstract boolean isExportVariations();

   public abstract void setExportComments(boolean var1);

   public abstract boolean isExportComments();

   public abstract void writeGame(Game var1) throws IOException;

   public abstract void writeGameInfo(GameInfo var1) throws IOException;

   public abstract void writeHistory(History var1) throws IOException;

   public abstract void writeBoard(Board var1) throws IOException;
}
