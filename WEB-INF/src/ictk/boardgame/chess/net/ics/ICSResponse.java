package ictk.boardgame.chess.net.ics;

public abstract interface ICSResponse
{
  public abstract boolean isResponse();
  
  public abstract void setResponseKey(int paramInt);
  
  public abstract int getResponseKey();
  
  public abstract void setResponseKey(String paramString);
  
  public abstract String getResponseKeyString();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSResponse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */