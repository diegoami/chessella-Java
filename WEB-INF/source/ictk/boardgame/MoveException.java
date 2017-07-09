package ictk.boardgame;

import ictk.boardgame.BoardGameException;
import ictk.boardgame.Move;

public class MoveException extends BoardGameException {

   protected Move m;


   public MoveException() {}

   public MoveException(String s) {
      super(s);
   }

   public MoveException(String s, Move _m) {
      super(s);
      this.m = _m;
   }

   public Move getMove() {
      return this.m;
   }
}
