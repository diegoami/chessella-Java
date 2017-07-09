package ictk.boardgame;

import ictk.boardgame.BoardListener;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.OutOfTurnException;
import java.util.List;

public interface Board {

   void setPositionDefault();

   boolean isInitialPositionDefault();

   void setPositionClear();

   List getLegalMoves();

   int getLegalMoveCount();

   boolean isLegalMove(Move var1);

   int getPlayerToMove();

   void playMove(Move var1) throws IllegalMoveException, OutOfTurnException;

   void addBoardListener(BoardListener var1);

   void removeBoardListener(BoardListener var1);

   BoardListener[] getBoardListeners();

   void fireBoardEvent(int var1);

   boolean isBlackMove();
}
