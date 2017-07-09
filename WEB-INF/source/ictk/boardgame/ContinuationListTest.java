package ictk.boardgame;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.ContinuationList;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import junit.framework.TestCase;

public class ContinuationListTest extends TestCase {

   ChessGame game;
   SAN san;
   History history;
   ChessBoard board;
   ChessBoard board2;
   Move move;
   ContinuationList cont;


   public ContinuationListTest(String name) {
      super(name);
   }

   public void setUp() {
      this.san = new SAN();
   }

   public void tearDown() {
      this.game = null;
      this.history = null;
      this.board = this.board2 = null;
      this.san = null;
      this.cont = null;
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testHeadList() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.board = (ChessBoard)this.game.getBoard();
      assertTrue(this.board != null);
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.exists(3));
      assertFalse(this.cont.exists(4));
      assertTrue(this.cont.hasMainLine());
      assertFalse(this.cont.isTerminal());
      assertTrue(this.cont.hasVariations());
      assertTrue(this.cont.getMainLine() == this.move);
      assertTrue(this.cont.get(0) == this.move);
      assertTrue(this.cont.sizeOfVariations() == 3);
      assertTrue(this.cont.getIndex(this.move) == 0);
   }

   public void testShufflePromote1() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Move move4 = null;
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      Move move2;
      this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      Move move3;
      this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == this.move);
      this.cont.promote(move2, 1);
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == move2);
      assertTrue(this.cont.get(1) == this.move);
      assertTrue(this.cont.get(3) == move4);
      this.cont.promote(move4, 2);
      assertTrue(this.cont.get(1) == move4);
      assertTrue(this.cont.get(3) == move3);
   }

   public void testMainLineTerminal() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Object move4 = null;
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == this.move);
      assertTrue(this.cont.setMainLineTerminal());
      assertTrue(this.cont.get(0) == null);
      assertTrue(this.cont.get(1) == this.move);
      assertTrue(this.cont.size() == 5);
   }

   public void testShufflePromote2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Object move4 = null;
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      Move move3;
      this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == this.move);
      assertTrue(this.cont.setMainLineTerminal());
      assertTrue(this.cont.get(0) == null);
      assertTrue(this.cont.get(1) == this.move);
      assertTrue(this.cont.size() == 5);
      this.cont.promote(move3, 0);
      assertTrue(this.cont.size() == 4);
   }

   public void testShuffleDemote() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Object move4 = null;
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      Move move2;
      this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      Move move3;
      this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      this.cont.demote(this.move, 1);
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == move2);
      assertTrue(this.cont.get(1) == this.move);
      assertTrue(this.cont.get(2) == move3);
   }

   public void testShuffleDemote2() throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Move move4 = null;
      this.game = new ChessGame();
      this.history = this.game.getHistory();
      this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
      this.history.prev();
      Move move2;
      this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
      this.history.prev();
      Move move3;
      this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
      this.history.prev();
      this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
      this.history.prev();
      this.cont = this.history.getFirstAll();
      assertTrue(this.cont.size() == 4);
      this.cont.demote(this.move, 0);
      assertTrue(this.cont.size() == 4);
      assertTrue(this.cont.getMainLine() == move2);
      assertTrue(this.cont.get(1) == move3);
      assertTrue(this.cont.get(2) == move4);
      assertTrue(this.cont.get(3) == this.move);
   }
}
