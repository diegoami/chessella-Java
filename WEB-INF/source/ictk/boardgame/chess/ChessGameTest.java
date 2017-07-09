package ictk.boardgame.chess;

import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import junit.framework.TestCase;

public class ChessGameTest extends TestCase {

   ChessGame game;
   History history;
   ChessBoard board;
   GameInfo gi;


   public ChessGameTest(String name) {
      super(name);
   }

   public void setUp() {}

   public void tearDown() {
      this.game = null;
      this.history = null;
      this.board = null;
      this.gi = null;
   }

   public void testConstructor() {
      this.game = new ChessGame();
      assertTrue(this.game.getBoard() != null);
      assertTrue(this.game.getHistory() != null);
   }

   public void testPlayerToMove() {
      this.game = new ChessGame();
      assertTrue(this.game.getPlayerToMove() == 0);
   }

   public void testResutl() {
      this.game = new ChessGame();
      assertTrue(this.game.getResult().isUndecided());
   }
}
