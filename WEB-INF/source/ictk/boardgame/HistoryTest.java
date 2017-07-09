package ictk.boardgame;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import junit.framework.TestCase;

public class HistoryTest extends TestCase {

   ChessGame game;
   SAN san;
   History history;
   ChessBoard board;
   ChessBoard board2;
   Move move;
   Move[] moves;


   public HistoryTest(String name) {
      super(name);
   }

   public void setUp() {
      this.san = new SAN();
   }

   public void tearDown() {
      this.game = null;
      this.history = null;
      this.moves = null;
      this.board = this.board2 = null;
      this.san = null;
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testAdd() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
   }

   public void testVCR() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.prev();
      this.history.next();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCR2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.next();
      this.history.next();
      this.history.next();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCR3() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.prev();
      this.history.next();
      this.history.next();
      this.history.next();
      this.history.next();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCRRewind() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.rewind();
      this.history.next();
      this.history.next();
      this.history.next();
      this.history.next();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCRFastForward() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
      this.history.rewind();
      this.history.fastforward(3);
      assertTrue(this.move == this.history.getCurrentMove());
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCRRewindFF() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.rewind();
      this.history.goToEnd();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCRRewindGoto() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.history.rewind();
      this.history.goTo(this.move);
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVCRFFGoto() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
      this.history.rewind();
      this.history.goToEnd();
      this.history.goTo(this.move);
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVariations() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
   }

   public void testVariations2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.prev();
      this.history.prev();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
   }

   public void testVariations3() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewind();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
   }

   public void testVariations4Goto() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.rewind();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.goTo(this.move);
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVariations4aGotoBad() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      ChessGame game2 = new ChessGame();
      game2.getHistory().add(this.move = this.san.stringToMove(game2.getBoard(), "c4"));
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));

      try {
         this.history.goTo(this.move);
         fail("shouldn\'t be able to goto a move outside the history list.");
      } catch (IllegalArgumentException var3) {
         ;
      }

   }

   public void testVariations5Next() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.rewind();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.rewind();
      this.history.next(1);
      this.history.goToEnd();
      assertTrue(this.board.equals(this.game.getBoard()));
   }

   public void testVariations6NextBad() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
      this.board = (ChessBoard)this.game.getBoard();
      this.history.rewind();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.rewind();

      try {
         this.history.next(2);
         fail("Next index should have been out of range");
      } catch (ArrayIndexOutOfBoundsException var2) {
         ;
      }

   }

   public void testRewindToFork() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewindToLastFork();
      assertTrue(this.history.getCurrentMove() == null);
      this.history.next();
      assertTrue(this.history.getCurrentMove() == this.move);
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
   }

   public void testRewindToFork2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf6"));
      this.history.rewindToLastFork();
      assertTrue(this.history.getCurrentMove() == this.move);
   }

   public void testEquality() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
      assertTrue(this.history.equals(history2));
      assertTrue(history2.equals(this.history));
   }

   public void testEqualityBad() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Bc4"));
      assertFalse(this.history.equals(history2));
      assertFalse(history2.equals(this.history));
   }

   public void testDeepEquality() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewindToLastFork();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.prev();
      history2.add(this.san.stringToMove(game2.getBoard(), "d4"));
      history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
      history2.rewindToLastFork();
      history2.next();
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
      assertTrue(this.history.equals(history2));
      assertTrue(history2.equals(this.history));
      assertTrue(this.history.deepEquals(history2, false));
      assertTrue(history2.deepEquals(this.history, false));
   }

   public void testDeepEquality2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
      ChessAnnotation anno = new ChessAnnotation();
      anno.addNAG(1);
      this.move.setAnnotation(anno);
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewindToLastFork();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.prev();
      history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
      anno = new ChessAnnotation();
      anno.addNAG(2);
      this.move.setAnnotation(anno);
      history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
      history2.rewindToLastFork();
      history2.next();
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
      assertTrue(this.history.equals(history2));
      assertTrue(history2.equals(this.history));
      assertTrue(this.history.deepEquals(history2, false));
      assertTrue(history2.deepEquals(this.history, false));
   }

   public void testDeepEquality3() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
      ChessAnnotation anno = new ChessAnnotation();
      anno.addNAG(1);
      this.move.setAnnotation(anno);
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewindToLastFork();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.prev();
      history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
      anno = new ChessAnnotation();
      anno.addNAG(1);
      this.move.setAnnotation(anno);
      history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
      history2.rewindToLastFork();
      history2.next();
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
      assertTrue(this.history.equals(history2));
      assertTrue(history2.equals(this.history));
      assertTrue(this.history.deepEquals(history2, true));
      assertTrue(history2.deepEquals(this.history, true));
   }

   public void testDeepEquality4() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      ChessGame game2 = new ChessGame();
      History history2 = game2.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
      ChessAnnotation anno = new ChessAnnotation();
      anno.addNAG(1);
      this.move.setAnnotation(anno);
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
      this.history.rewindToLastFork();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
      history2.prev();
      history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
      anno = new ChessAnnotation();
      anno.addNAG(3);
      this.move.setAnnotation(anno);
      history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
      history2.rewindToLastFork();
      history2.next();
      history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
      history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
      assertTrue(this.history.equals(history2));
      assertTrue(history2.equals(this.history));
      assertFalse(this.history.deepEquals(history2, true));
      assertFalse(history2.deepEquals(this.history, true));
      assertTrue(this.history.deepEquals(history2, false));
      assertTrue(history2.deepEquals(this.history, false));
   }

   public void testSize() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));

      assert this.history.size() == 1;

      this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));

      assert this.history.size() == 3;

      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc3"));

      assert this.history.size() == 3;

      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf6"));
      this.history.rewindToLastFork();
      this.history.next();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));

      assert this.history.size() == 4;

   }
}
