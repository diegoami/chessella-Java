package ictk.boardgame;

public abstract interface Game
{
  public abstract int getNumberOfPlayers();
  
  public abstract GameInfo getGameInfo();
  
  public abstract void setGameInfo(GameInfo paramGameInfo);
  
  public abstract History getHistory();
  
  public abstract void setHistory(History paramHistory);
  
  public abstract Result getCurrentResult();
  
  public abstract Result getResult();
  
  public abstract void setResult(Result paramResult);
  
  public abstract Board[] getBoards();
  
  public abstract Board getBoard();
  
  public abstract int[] getPlayersToMove();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\Game.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */