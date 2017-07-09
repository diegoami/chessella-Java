package ictk.boardgame;

import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;

public class OutOfTurnException extends IllegalMoveException {

   public OutOfTurnException() {}

   public OutOfTurnException(String s) {
      super(s);
   }

   public OutOfTurnException(String s, Move m) {
      super(s, m);
   }
}
