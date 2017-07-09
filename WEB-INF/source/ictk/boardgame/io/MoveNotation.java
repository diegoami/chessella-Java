package ictk.boardgame.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Board;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.Result;

public interface MoveNotation {

   Move stringToMove(Board var1, String var2) throws AmbiguousMoveException, IllegalMoveException;

   String moveToString(Move var1);

   Result stringToResult(String var1);

   String resultToString(Result var1);
}
