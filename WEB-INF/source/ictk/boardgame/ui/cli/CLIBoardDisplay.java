package ictk.boardgame.ui.cli;

import ictk.boardgame.Board;
import ictk.boardgame.ui.BoardDisplay;
import java.io.PrintWriter;
import java.io.Writer;

public interface CLIBoardDisplay extends BoardDisplay {

   void setInverse(boolean var1);

   boolean isInverse();

   void setWriter(PrintWriter var1);

   Writer getWriter();

   void print();

   void print(Board var1);
}
