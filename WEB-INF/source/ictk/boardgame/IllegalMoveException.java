package ictk.boardgame;

import ictk.boardgame.Move;
import ictk.boardgame.MoveException;

public class IllegalMoveException extends MoveException {

   String moveString;


   public IllegalMoveException() {}

   public IllegalMoveException(String s) {
      super(s);
   }

   public IllegalMoveException(String s, Move _m) {
      super(s, _m);
   }

   public void setMoveString(String m) {
      this.moveString = m;
   }

   public String getMoveString() {
      return this.moveString;
   }

   public String toString() {
      return this.moveString == null?this.getMessage():this.getMessage() + ": " + this.moveString;
   }
}
