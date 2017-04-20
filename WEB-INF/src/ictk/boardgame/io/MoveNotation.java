package ictk.boardgame.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Board;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.Result;

public abstract interface MoveNotation
{
  public abstract Move stringToMove(Board paramBoard, String paramString)
    throws AmbiguousMoveException, IllegalMoveException;
  
  public abstract String moveToString(Move paramMove);
  
  public abstract Result stringToResult(String paramString);
  
  public abstract String resultToString(Result paramResult);
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\io\MoveNotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */