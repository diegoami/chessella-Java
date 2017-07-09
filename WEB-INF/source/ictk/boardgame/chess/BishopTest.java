package ictk.boardgame.chess;

import ictk.boardgame.chess.Bishop;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import java.util.List;
import junit.framework.TestCase;

public class BishopTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   List list;
   Bishop bishop;


   public BishopTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.bishop = null;
      this.DEBUG = false;
   }

   public void testFullMoveScope() {
      this.board.setPositionClear();
      this.board.addBishop(4, 4, false);
      this.board.addKing(4, 1, false);
      this.board.addKing(4, 1, true);
      this.bishop = (Bishop)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.bishop.getLegalDests();
      assertTrue(this.list.size() == 13);
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
      assertTrue(this.list.size() == 0);
   }
}
