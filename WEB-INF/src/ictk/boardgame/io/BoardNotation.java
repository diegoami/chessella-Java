package ictk.boardgame.io;

import ictk.boardgame.Board;
import java.io.IOException;

public abstract interface BoardNotation
{
  public abstract Board stringToBoard(String paramString)
    throws IOException;
  
  public abstract String boardToString(Board paramBoard);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\io\BoardNotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */