package ictk.boardgame;

import java.util.EventListener;

public abstract interface BoardListener
  extends EventListener
{
  public abstract void boardUpdate(Board paramBoard, int paramInt);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\BoardListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */