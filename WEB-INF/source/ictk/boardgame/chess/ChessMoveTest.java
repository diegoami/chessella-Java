package ictk.boardgame.chess;

import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.Knight;
import ictk.boardgame.chess.Queen;
import ictk.util.Log;
import junit.framework.TestCase;

public class ChessMoveTest extends TestCase {

   ChessBoard board;
   ChessBoard board2;
   ChessResult res;
   ChessMove move;
   char[][] default_position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};


   public ChessMoveTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.board2 = null;
      this.move = null;
      Log.removeMask(ChessMove.DEBUG);
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testIllegalCastle() {
      try {
         this.move = new ChessMove(this.board, -1);
         fail("shouldn\'t be able to castle queenside on the initial board");
      } catch (IllegalMoveException var3) {
         ;
      }

      try {
         this.move = new ChessMove(this.board, 1);
         fail("shouldn\'t be able to castle kingside on the initial board");
      } catch (IllegalMoveException var2) {
         ;
      }

   }

   public void testCastleQsideWhite() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.move = new ChessMove(this.board, -1);
   }

   public void testCastleKsideWhite() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.move = new ChessMove(this.board, 1);
   }

   public void testCastleQsideBlack() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', ' '}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', ' '}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', ' '}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.board.setBlackMove(true);
      this.move = new ChessMove(this.board, -1);
   }

   public void testCastleKsideBlack() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', ' '}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', ' '}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.board.setBlackMove(true);
      this.move = new ChessMove(this.board, 1);
   }

   public void testA2A3() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', ' ', 'P', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.move = new ChessMove(this.board, 1, 2, 1, 3);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position);
      this.board2.setBlackMove(true);
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 0);
   }

   public void testA2A4() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', ' ', ' ', 'P', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.move = new ChessMove(this.board, 1, 2, 1, 4);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position);
      this.board2.setBlackMove(true);
      this.board2.setEnPassantFile('a');
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 0);
   }

   public void testNf3() throws IllegalMoveException {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', 'N', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      assertTrue(this.board != null);
      this.move = new ChessMove(this.board, 7, 1, 6, 3);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position);
      this.board2.setBlackMove(true);
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 1);
   }

   public void testPawnPromotion() throws IllegalMoveException {
      ChessPiece piece = null;
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      char[][] position2 = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board.setPosition(position);
      this.move = new ChessMove(this.board, 7, 7, 7, 8, 4);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position2);
      this.board2.setBlackMove(true);
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 0);
      piece = this.board.getSquare('g', '8').getOccupant();
      assertTrue(piece instanceof Knight);
   }

   public void testPawnPromotionAutoQueen() throws IllegalMoveException {
      ChessPiece piece = null;
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      char[][] position2 = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board.setPosition(position);
      this.move = new ChessMove(this.board, 7, 7, 7, 8);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position2);
      this.board2.setBlackMove(true);
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 0);
      piece = this.board.getSquare('g', '8').getOccupant();
      assertTrue(piece instanceof Queen);
   }

   public void testPawnPromotionBad() throws IllegalMoveException {
      Object piece = null;
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board.setPosition(position);

      try {
         this.move = new ChessMove(this.board, 7, 7, 7, 8, 5);
         fail("Can\'t promote to King in standard chess rules");
      } catch (IllegalMoveException var4) {
         ;
      }

   }

   public void testPromotionCaptureWithDoubleCheck() throws IllegalMoveException {
      ChessPiece piece = null;
      char[][] position = new char[][]{{' ', 'P', ' ', ' ', 'p', ' ', ' ', ' '}, {' ', 'P', 'K', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'}, {' ', ' ', 'R', ' ', ' ', ' ', 'P', 'k'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'r', ' ', ' ', ' ', 'p', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      char[][] position2 = new char[][]{{' ', 'P', ' ', ' ', 'p', ' ', ' ', ' '}, {' ', 'P', 'K', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'}, {' ', ' ', 'R', ' ', ' ', ' ', ' ', 'k'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', 'r', ' ', ' ', ' ', 'p', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board.setPosition(position);
      this.move = new ChessMove(this.board, 4, 7, 3, 8, 1);
      this.board.playMove(this.move);
      this.board2 = new ChessBoard(position2);
      this.board2.setBlackMove(true);
      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.plyCount50 == 0);
      piece = this.board.getSquare('c', '8').getOccupant();
      assertTrue(piece instanceof Queen);
      assertTrue(this.board.getLegalMoveCount() == 2);
      assertTrue(!this.board.isCheckmate());
      assertTrue(this.board.isCheck());
      assertTrue(this.board.isDoubleCheck());
   }
}
