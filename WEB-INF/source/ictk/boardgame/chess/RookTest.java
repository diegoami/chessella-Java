package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.Rook;
import java.util.List;
import junit.framework.TestCase;

public class RookTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   List list;
   Rook rook;


   public RookTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.rook = null;
      this.DEBUG = false;
   }

   public void testFullMoveScope() {
      this.board.setPositionClear();
      this.board.addRook(4, 4, false);
      this.board.addKing(1, 1, false);
      this.board.addKing(8, 1, true);
      this.rook = (Rook)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.rook.getLegalDests();
      assertTrue(this.list.size() == 14);
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
