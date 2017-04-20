package ictk.boardgame;

public abstract interface SingleBoardGame
  extends Game
{
  public abstract Board getBoard();
  
  public abstract void setBoard(Board paramBoard);
  
  public abstract int getPlayerToMove();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\SingleBoardGame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */