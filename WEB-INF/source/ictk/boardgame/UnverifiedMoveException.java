package ictk.boardgame;


public class UnverifiedMoveException extends RuntimeException {

   public UnverifiedMoveException() {}

   public UnverifiedMoveException(String mesg) {
      super(mesg);
   }
}
