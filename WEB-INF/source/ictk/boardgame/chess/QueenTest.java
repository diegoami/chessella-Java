package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.Queen;
import java.util.List;
import junit.framework.TestCase;

public class QueenTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   List list;
   Queen queen;


   public QueenTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.queen = null;
      this.DEBUG = false;
   }

   public void testFullMoveScope() {
      this.board.setPositionClear();
      this.board.addQueen(4, 4, false);
      this.board.addKing(1, 2, false);
      this.board.addKing(8, 2, true);
      this.queen = (Queen)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.queen.getLegalDests();
      assertTrue(this.list.size() == 27);
      this.list.remove(this.board.getSquare('a', '1'));
      this.list.remove(this.board.getSquare('b', '2'));
      this.list.remove(this.board.getSquare('c', '3'));
      this.list.remove(this.board.getSquare('e', '5'));
      this.list.remove(this.board.getSquare('f', '6'));
      this.list.remove(this.board.getSquare('g', '7'));
      this.list.remove(this.board.getSquare('h', '8'));
      this.list.remove(this.board.getSquare('a', '7'));
      this.list.remove(this.board.getSquare('b', '6'));
      this.list.remove(this.board.getSquare('c', '5'));
      this.list.remove(this.board.getSquare('e', '3'));
      this.list.remove(this.board.getSquare('f', '2'));
      this.list.remove(this.board.getSquare('g', '1'));
      this.list.remove(this.board.getSquare('a', '4'));
      this.list.remove(this.board.getSquare('b', '4'));
      this.list.remove(this.board.getSquare('c', '4'));
      this.list.remove(this.board.getSquare('e', '4'));
      this.list.remove(this.board.getSquare('f', '4'));
      this.list.remove(this.board.getSquare('g', '4'));
      this.list.remove(this.board.getSquare('h', '4'));
      this.list.remove(this.board.getSquare('d', '1'));
      this.list.remove(this.board.getSquare('d', '2'));
      this.list.remove(this.board.getSquare('d', '3'));
      this.list.remove(this.board.getSquare('d', '5'));
      this.list.remove(this.board.getSquare('d', '6'));
      this.list.remove(this.board.getSquare('d', '7'));
      this.list.remove(this.board.getSquare('d', '8'));
      assertTrue(this.list.size() == 0);
   }
}
