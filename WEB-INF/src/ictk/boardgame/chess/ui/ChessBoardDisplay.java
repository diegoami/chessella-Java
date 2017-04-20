package ictk.boardgame.chess.ui;

public abstract interface ChessBoardDisplay
{
  public static final int NO_COORDINATES = 0;
  public static final int TOP_COORDINATES = 1;
  public static final int RIGHT_COORDINATES = 2;
  public static final int BOTTOM_COORDINATES = 4;
  public static final int LEFT_COORDINATES = 8;
  
  public abstract void setWhiteOnBottom(boolean paramBoolean);
  
  public abstract boolean isWhiteOnBottom();
  
  public abstract void setSideToMoveOnBottom(boolean paramBoolean);
  
  public abstract boolean getSideToMoveOnBottom();
  
  public abstract void setVisibleCoordinates(int paramInt);
  
  public abstract int getVisibleCoordinates();
  
  public abstract void setLowerCaseCoordinates(boolean paramBoolean);
  
  public abstract boolean isLowerCaseCoordinates();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ui\ChessBoardDisplay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */