package ictk.boardgame.chess;

import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.Pawn;
import java.util.List;
import junit.framework.TestCase;

public class PawnTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   Pawn pawn;
   List list;


   public PawnTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.pawn = null;
      this.DEBUG = false;
   }

   public void testDefaultAFile() {
      this.pawn = (Pawn)this.board.getSquare('a', '2').getOccupant();
      assertTrue(!this.pawn.isBlack());
      this.list = this.pawn.getLegalDests();
      assertTrue(this.list.size() == 2);
      this.list.remove(this.board.getSquare('a', '3'));
      this.list.remove(this.board.getSquare('a', '4'));
      assertTrue(this.list.size() == 0);
      assertTrue(!this.pawn.hasMoved());
   }

   public void testDefaultBFileOpposition() {
      this.board.setBlackMove(true);
      this.pawn = (Pawn)this.board.getSquare('b', '2').getOccupant();
      assertTrue(!this.pawn.isBlack());
      assertTrue(this.pawn.isLegalAttack(this.board.getSquare('a', '3')));
      assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '3')));
      assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '4')));
      assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '5')));
      assertTrue(this.pawn.isLegalAttack(this.board.getSquare('c', '3')));
      this.list = this.pawn.getLegalDests();
      assertTrue(this.list.size() == 4);
      this.list.remove(this.board.getSquare('a', '3'));
      this.list.remove(this.board.getSquare('b', '3'));
      this.list.remove(this.board.getSquare('b', '4'));
      this.list.remove(this.board.getSquare('c', '3'));
      assertTrue(this.list.size() == 0);
   }

   public void testEnPassant() throws IllegalMoveException {
      this.DEBUG = false;
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', 'p', ' ', ' ', ' ', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.board.playMove(new ChessMove(this.board, 2, 2, 2, 4, 0));
      assertTrue(this.board.isEnPassantFile('b'));
      this.pawn = (Pawn)this.board.getSquare('c', '4').getOccupant();
      if(this.DEBUG) {
         System.err.println(this.board);
         System.err.println(this.pawn.dump());
      }

      assertTrue(this.pawn.isBlack());
      assertTrue(this.pawn.isLegalAttack(this.board.getSquare('b', '3')));
      assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('d', '3')));
      this.list = this.pawn.getLegalDests();
      assertTrue(this.list.size() == 2);
      this.list.remove(this.board.getSquare('b', '3'));
      this.list.remove(this.board.getSquare('c', '3'));
      assertTrue(this.list.size() == 0);
   }

   public void testCapture() throws IllegalMoveException {
      this.DEBUG = false;
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', ' ', 'P', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', 'p', ' ', ' ', ' ', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.pawn = (Pawn)this.board.getSquare('b', '3').getOccupant();
      if(this.DEBUG) {
         System.err.println(this.board);
         System.err.println(this.pawn.dump());
      }

      assertTrue(!this.pawn.isBlack());
      assertTrue(this.pawn.isLegalAttack(this.board.getSquare('c', '4')));
      assertTrue(!this.pawn.isLegalAttack(this.board.getSquare('b', '4')));
      this.list = this.pawn.getLegalDests();
      assertTrue(this.list.size() == 2);
      this.list.remove(this.board.getSquare('c', '4'));
      this.list.remove(this.board.getSquare('b', '4'));
      assertTrue(this.list.size() == 0);
   }
}
