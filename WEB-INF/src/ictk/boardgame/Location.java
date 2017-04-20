package ictk.boardgame;

public abstract interface Location
{
  public abstract int getX();
  
  public abstract int getY();
  
  public abstract Piece getPiece();
  
  public abstract void setX(int paramInt);
  
  public abstract void setY(int paramInt);
  
  public abstract Piece setPiece(Piece paramPiece);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\Location.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */