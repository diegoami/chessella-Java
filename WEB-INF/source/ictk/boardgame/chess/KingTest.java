package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.King;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Rook;
import java.util.List;
import junit.framework.TestCase;

public class KingTest extends TestCase {

   boolean DEBUG = false;
   ChessBoard board;
   ChessMove move;
   List list;
   King king;


   public KingTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.king = null;
      this.DEBUG = false;
   }

   public void testFullMoveScope() {
      this.board.setPositionClear();
      this.board.addKing(4, 4, false);
      this.board.addKing(8, 2, true);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 8);
      this.list.remove(this.board.getSquare('c', '3'));
      this.list.remove(this.board.getSquare('c', '4'));
      this.list.remove(this.board.getSquare('c', '5'));
      this.list.remove(this.board.getSquare('d', '3'));
      this.list.remove(this.board.getSquare('d', '5'));
      this.list.remove(this.board.getSquare('e', '3'));
      this.list.remove(this.board.getSquare('e', '4'));
      this.list.remove(this.board.getSquare('e', '5'));
      assertTrue(this.list.size() == 0);
   }

   public void testNotIntoCheck1() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 5);
      this.list.remove(this.board.getSquare('d', '3'));
      this.list.remove(this.board.getSquare('d', '5'));
      this.list.remove(this.board.getSquare('e', '3'));
      this.list.remove(this.board.getSquare('e', '4'));
      this.list.remove(this.board.getSquare('e', '5'));
      assertTrue(this.list.size() == 0);
   }

   public void testNotIntoCheck2() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 2);
      this.list.remove(this.board.getSquare('d', '3'));
      this.list.remove(this.board.getSquare('d', '5'));
      assertTrue(this.list.size() == 0);
   }

   public void testNotIntoCheck3() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', 'r', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 1);
      this.list.remove(this.board.getSquare('d', '3'));
      assertTrue(this.list.size() == 0);
   }

   public void testStalemate() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', 'r', ' ', 'r', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertTrue(this.board.isStalemate());
   }

   public void testCheckmate() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertTrue(this.board.isCheckmate());
   }

   public void testCheckmate2() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', 'n', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', 'r', ' ', 'r', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)4, (int)4).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertTrue(this.board.isCheckmate());
   }

   public void testCheckmateSmother() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'n', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', 'P', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)8, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertTrue(this.board.isCheckmate());
   }

   public void testCheckmateNotSmother() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'n', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', 'P', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)8, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertFalse(this.board.isCheckmate());
   }

   public void testCheckmateSmother2() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'r', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'n', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', 'P', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)8, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertTrue(this.board.isCheckmate());
   }

   public void testCheckmateSmother3() {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'r', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'n', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', 'p', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', 'p', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)8, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 2);
      assertFalse(this.board.isStalemate());
      assertFalse(this.board.isCheckmate());
   }

   public void testCheckmateSave1() {
      char[][] position = new char[][]{{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'r', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertFalse(this.board.isCheckmate());
   }

   public void testCheckmateSave2Non() {
      char[][] position = new char[][]{{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'r', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      this.list = this.king.getLegalDests();
      assertTrue(this.list.size() == 0);
      assertFalse(this.board.isStalemate());
      assertTrue(this.board.isCheckmate());
   }

   public void testFindRook1() {
      char[][] position = new char[][]{{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      Rook rook = (Rook)this.board.getSquare((int)1, (int)1).getOccupant();
      assertTrue(rook == this.king.findMyRook(true));
   }

   public void testFindRook2() {
      char[][] position = new char[][]{{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      Rook rook = (Rook)this.board.getSquare((int)8, (int)1).getOccupant();
      assertTrue(rook == this.king.findMyRook(false));
   }

   public void testFindRook3() {
      char[][] position = new char[][]{{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'N', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'N', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      Rook rook = (Rook)this.board.getSquare((int)1, (int)1).getOccupant();
      assertTrue(rook == this.king.findMyRook(true));
   }

   public void testFindRook4() {
      char[][] position = new char[][]{{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'N', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'N', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      Rook rook = (Rook)this.board.getSquare((int)3, (int)1).getOccupant();
      assertTrue(rook == this.king.findMyRook(true));
   }

   public void testFindRook5Problem() {
      char[][] position = new char[][]{{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board.setPosition(position);
      this.king = (King)this.board.getSquare((int)5, (int)1).getOccupant();
      Rook krook = (Rook)this.board.getSquare((int)1, (int)1).getOccupant();
      krook.moveCount = 2;
      Rook qrook = (Rook)this.board.getSquare((int)2, (int)1).getOccupant();
      assertFalse(qrook == this.king.findMyRook(true));
      assertTrue(krook == this.king.findMyRook(true));
   }

   public void testEnPassantEscapesCheck() {
      char[][] position = new char[][]{{' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', 'P', 'p', ' ', ' ', ' '}, {' ', ' ', ' ', 'K', 'P', 'k', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board.setPosition(position);
      this.board.setEnPassantFile('b');
      Pawn pawn = (Pawn)this.board.getSquare((int)3, (int)5).getOccupant();
      assertTrue(pawn.isLegalDest(this.board.getSquare((int)2, (int)6)));
   }
}
