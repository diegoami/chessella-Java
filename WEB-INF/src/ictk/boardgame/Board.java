package ictk.boardgame;

import java.util.List;

public abstract interface Board
{
  public abstract void setPositionDefault();
  
  public abstract boolean isInitialPositionDefault();
  
  public abstract void setPositionClear();
  
  public abstract List getLegalMoves();
  
  public abstract int getLegalMoveCount();
  
  public abstract boolean isLegalMove(Move paramMove);
  
  public abstract int getPlayerToMove();
  
  public abstract void playMove(Move paramMove)
    throws IllegalMoveException, OutOfTurnException;
  
  public abstract void addBoardListener(BoardListener paramBoardListener);
  
  public abstract void removeBoardListener(BoardListener paramBoardListener);
  
  public abstract BoardListener[] getBoardListeners();
  
  public abstract void fireBoardEvent(int paramInt);
  
  public abstract boolean isBlackMove();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\Board.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */