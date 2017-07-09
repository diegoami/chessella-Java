package ictk.boardgame.chess;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.AmbiguousChessMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Rook;
import ictk.util.Log;
import junit.framework.TestCase;

public class ChessBoardTest extends TestCase {

   ChessBoard board;
   ChessBoard board2;
   ChessMove move;
   char[][] default_position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};


   public ChessBoardTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
   }

   public void tearDown() {
      this.board = null;
      this.board2 = null;
      this.move = null;
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testFileNRankTranslation() {
      ChessPiece p = null;
      p = this.board.squares[0][0].getOccupant();
      assertTrue(p instanceof Rook);
   }

   public void testSetPositionDataReset() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', 'N', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.staleLegalDests);
      this.board.setWhiteCastleableKingside(false);
      this.board.setWhiteCastleableQueenside(false);
      this.board.setBlackCastleableKingside(false);
      this.board.setBlackCastleableQueenside(false);
      this.board.setEnPassantFile((int)2);
      this.board.set50MoveRulePlyCount(37);
      this.board.setPositionDefault();
      assertTrue(this.board.staleLegalDests);
      assertTrue(this.board.isWhiteCastleableKingside());
      assertTrue(this.board.isWhiteCastleableQueenside());
      assertTrue(this.board.isBlackCastleableKingside());
      assertTrue(this.board.isBlackCastleableQueenside());
      assertTrue(this.board.get50MoveRulePlyCount() == 0);
      assertTrue(this.board.getEnPassantFile() == 0);
      assertTrue(this.board.equals(new ChessBoard()));
   }

   public void testEquals() {
      this.board2 = new ChessBoard();
      assertTrue(this.board.equals(this.board2));
   }

   public void testEqualsPly() {
      this.board2 = new ChessBoard();
      this.board.set50MoveRulePlyCount(32);
      assertTrue(this.board.equals(this.board2));
   }

   public void testEqualsNotPieceType() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', 'N', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      char[][] position2 = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', 'Q', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.board2 = new ChessBoard(position2);
      assertFalse(this.board.equals(this.board2));
   }

   public void testEqualsNotPieceColor() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', 'N', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      char[][] position2 = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', 'n', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      this.board2 = new ChessBoard(position2);
      assertFalse(this.board.equals(this.board2));
   }

   public void testEqualsNotCastle() {
      this.board2 = new ChessBoard();
      this.board.setWhiteCastleableKingside(false);
      assertFalse(this.board.equals(this.board2));
   }

   public void testEqualsNotEnPassant() {
      this.board2 = new ChessBoard();
      this.board.setEnPassantFile((int)2);
      assertFalse(this.board.equals(this.board2));
   }

   public void testLegalMoves() {
      char[][] var10000 = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      assertTrue(this.board.getLegalMoveCount() == 20);
   }

   public void testSetPositionCastle() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.isWhiteCastleableQueenside());
      assertTrue(this.board.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableQueenside());
      assertTrue(this.board.isBlackCastleableKingside());
   }

   public void testSetPositionCastle2() {
      char[][] position = new char[][]{{' ', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertFalse(this.board.isWhiteCastleableQueenside());
      assertTrue(this.board.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableQueenside());
      assertTrue(this.board.isBlackCastleableKingside());
   }

   public void testSetPositionCastle3() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {' ', 'P', ' ', 'K', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertFalse(this.board.isWhiteCastleableQueenside());
      assertFalse(this.board.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableQueenside());
      assertTrue(this.board.isBlackCastleableKingside());
   }

   public void testMaterialCount1() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 39);
   }

   public void testMaterialCount2() {
      char[][] position = new char[][]{{' ', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 34);
   }

   public void testMaterialCount3() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 36);
   }

   public void testMaterialCount4() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 36);
   }

   public void testMaterialCount5() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 30);
   }

   public void testMaterialCount6() {
      char[][] position = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', ' ', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
      this.board.setPosition(position);
      assertTrue(this.board.getMaterialCount(true) == 39);
      assertTrue(this.board.getMaterialCount(false) == 38);
   }

   public void testGetCaptured() throws IllegalMoveException, AmbiguousChessMoveException {
      assertTrue(this.board.getCapturedPieces(true) == null);
      assertTrue(this.board.getCapturedPieces(false) == null);
      assertTrue(this.board.getUnCapturedPieces(true).length == 16);
      assertTrue(this.board.getUnCapturedPieces(false).length == 16);
      this.move = (ChessMove)ChessBoard.san.stringToMove(this.board, "e4");
      this.board.playMove(this.move);
      this.move = (ChessMove)ChessBoard.san.stringToMove(this.board, "d5");
      this.board.playMove(this.move);
      this.move = (ChessMove)ChessBoard.san.stringToMove(this.board, "exd5");
      this.board.playMove(this.move);
      assertTrue(this.board.getCapturedPieces(true).length == 1);
      assertTrue(this.board.getCapturedPieces(false) == null);
      assertTrue(this.board.getUnCapturedPieces(true).length == 15);
      assertTrue(this.board.getUnCapturedPieces(false).length == 16);
   }

   public void testListenersAdd() {
      BoardListener bl = new BoardListener() {
         public void boardUpdate(Board b, int c) {}
      };
      BoardListener bl2 = new BoardListener() {
         public void boardUpdate(Board b, int c) {}
      };
      this.board.addBoardListener(bl);
      assertTrue(this.board.getBoardListeners().length == 1);
      this.board.addBoardListener(bl);
      assertTrue(this.board.getBoardListeners().length == 1);
      this.board.addBoardListener(bl2);
      assertTrue(this.board.getBoardListeners().length == 2);
   }

   public void testListenersArrayRemove() {
      BoardListener bl = new BoardListener() {
         public void boardUpdate(Board b, int c) {}
      };
      BoardListener bl2 = new BoardListener() {
         public void boardUpdate(Board b, int c) {}
      };
      this.board.addBoardListener(bl);
      assertTrue(this.board.getBoardListeners().length == 1);
      this.board.addBoardListener(bl2);
      assertTrue(this.board.getBoardListeners().length == 2);
      this.board.removeBoardListener(bl);
      assertTrue(this.board.getBoardListeners().length == 1);
      assertTrue(this.board.getBoardListeners()[0] == bl2);
   }
}
