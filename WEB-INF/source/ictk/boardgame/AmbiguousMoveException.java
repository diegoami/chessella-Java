package ictk.boardgame;

import ictk.boardgame.MoveException;

public class AmbiguousMoveException extends MoveException {

   public AmbiguousMoveException() {}

   public AmbiguousMoveException(String mesg) {
      super(mesg);
   }
}
