package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.Knight;
import java.util.List;
import junit.framework.TestCase;

public class KnightTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   List list;
   Knight knight;


   public KnightTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.knight = null;
      this.DEBUG = false;
   }

   public void testFullMoveScope() {
      this.board.setPositionClear();
      this.board.addKnight(4, 4, false);
      this.board.addKing(1, 1, false);
      this.board.addKing(8, 1, true);
      this.knight = (Knight)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.knight.getLegalDests();
      assertTrue(this.list.size() == 8);
      this.list.remove(this.board.getSquare('e', '6'));
      this.list.remove(this.board.getSquare('f', '5'));
      this.list.remove(this.board.getSquare('f', '3'));
      this.list.remove(this.board.getSquare('e', '2'));
      this.list.remove(this.board.getSquare('c', '2'));
      this.list.remove(this.board.getSquare('b', '3'));
      this.list.remove(this.board.getSquare('b', '5'));
      this.list.remove(this.board.getSquare('c', '6'));
      assertTrue(this.list.size() == 0);
   }
}
