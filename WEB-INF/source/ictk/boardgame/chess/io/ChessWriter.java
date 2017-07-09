package ictk.boardgame.chess.io;

import ictk.boardgame.io.GameWriter;
import java.io.OutputStream;
import java.io.Writer;

public abstract class ChessWriter extends GameWriter {

   public ChessWriter(OutputStream _out) {
      super(_out);
   }

   public ChessWriter(Writer _out) {
      super(_out);
   }
}
