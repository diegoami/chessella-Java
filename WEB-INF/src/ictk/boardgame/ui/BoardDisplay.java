package ictk.boardgame.ui;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;

public abstract interface BoardDisplay
  extends BoardListener
{
  public abstract void setBoard(Board paramBoard);
  
  public abstract Board getBoard();
  
  public abstract void update();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\ui\BoardDisplay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */