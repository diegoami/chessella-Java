package ictk.boardgame.ui.cli;

import ictk.boardgame.Board;
import ictk.boardgame.ui.BoardDisplay;
import java.io.PrintWriter;
import java.io.Writer;

public abstract interface CLIBoardDisplay
  extends BoardDisplay
{
  public abstract void setInverse(boolean paramBoolean);
  
  public abstract boolean isInverse();
  
  public abstract void setWriter(PrintWriter paramPrintWriter);
  
  public abstract Writer getWriter();
  
  public abstract void print();
  
  public abstract void print(Board paramBoard);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\ui\cli\CLIBoardDisplay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */