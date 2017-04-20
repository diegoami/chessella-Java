package ictk.boardgame.chess.net.ics.event;

public abstract interface ICSBoardEvent
{
  public static final int NO_BOARD = -1;
  
  public abstract int getBoardNumber();
  
  public abstract void setBoardNumber(int paramInt);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSBoardEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */