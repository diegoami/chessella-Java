package ictk.boardgame.chess.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import java.io.IOException;
import junit.framework.TestCase;

public class FENTest extends TestCase {

   FEN fen = null;
   SAN san = null;
   ChessMove move = null;
   ChessBoard board;
   ChessBoard board2;


   public FENTest(String name) {
      super(name);
   }

   public void setUp() {
      this.fen = new FEN();
      this.san = new SAN();
   }

   public void tearDown() {
      this.fen = null;
      this.san = null;
      this.board = null;
      this.board2 = null;
      this.move = null;
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testReadRandom1() throws IOException {
      Log.addMask(FEN.DEBUG);
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board = new ChessBoard(position);
      this.board2 = (ChessBoard)this.fen.stringToBoard("7k/4P3/8/8/8/8/8/7K w - - 0 1");
      assertTrue(this.board.isWhiteCastleableKingside() == this.board2.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableKingside() == this.board2.isBlackCastleableKingside());
      assertTrue(this.board.isWhiteCastleableQueenside() == this.board2.isWhiteCastleableQueenside());
      assertTrue(this.board.isBlackCastleableQueenside() == this.board2.isBlackCastleableQueenside());
      assertTrue(this.board.getEnPassantFile() == this.board2.getEnPassantFile());
      assertTrue(this.board.get50MoveRulePlyCount() == this.board2.get50MoveRulePlyCount());
      if(!this.board.equals(this.board2)) {
         Log.debug(FEN.DEBUG, "Boards not equal");
         Log.debug2(FEN.DEBUG, this.board.dump());
         Log.debug2(FEN.DEBUG, this.board2.dump());
      }

      assertTrue(this.board.equals(this.board2));
      assertTrue(this.board.toString().equals(this.board2.toString()));
   }

   public void testReadDefault() throws IOException {
      this.board = new ChessBoard();
      this.board2 = (ChessBoard)this.fen.stringToBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
      assertTrue(this.board.equals(this.board2));
   }

   public void testReadDefaultMove1e4() throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException {
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.board.playMove(this.move);
      this.board2 = (ChessBoard)this.fen.stringToBoard("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
      assertTrue(this.board.isWhiteCastleableKingside() == this.board2.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableKingside() == this.board2.isBlackCastleableKingside());
      assertTrue(this.board.isWhiteCastleableQueenside() == this.board2.isWhiteCastleableQueenside());
      assertTrue(this.board.isBlackCastleableQueenside() == this.board2.isBlackCastleableQueenside());
      assertTrue(this.board.getEnPassantFile() == this.board2.getEnPassantFile());
      assertTrue(this.board.get50MoveRulePlyCount() == this.board2.get50MoveRulePlyCount());
      if(!this.board.equals(this.board2)) {
         Log.debug(FEN.DEBUG, "Boards not equal");
         Log.debug2(FEN.DEBUG, this.board.dump());
         Log.debug2(FEN.DEBUG, this.board2.dump());
      }

      assertTrue(this.board.equals(this.board2));
   }

   public void testReadDefaultMove1e4c5() throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException {
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.board.playMove(this.move);
      this.move = (ChessMove)this.san.stringToMove(this.board, "c5");
      this.board.playMove(this.move);
      this.board2 = (ChessBoard)this.fen.stringToBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");
      assertTrue(this.board.isWhiteCastleableKingside() == this.board2.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableKingside() == this.board2.isBlackCastleableKingside());
      assertTrue(this.board.isWhiteCastleableQueenside() == this.board2.isWhiteCastleableQueenside());
      assertTrue(this.board.isBlackCastleableQueenside() == this.board2.isBlackCastleableQueenside());
      assertTrue(this.board.getEnPassantFile() == this.board2.getEnPassantFile());
      assertTrue(this.board.get50MoveRulePlyCount() == this.board2.get50MoveRulePlyCount());
      if(!this.board.equals(this.board2)) {
         Log.debug(FEN.DEBUG, "Boards not equal");
         Log.debug2(FEN.DEBUG, this.board.dump());
         Log.debug2(FEN.DEBUG, this.board2.dump());
      }

      assertTrue(this.board.equals(this.board2));
   }

   public void testReadDefaultMove1e4c5Nf3() throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException {
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.board.playMove(this.move);
      this.move = (ChessMove)this.san.stringToMove(this.board, "c5");
      this.board.playMove(this.move);
      this.move = (ChessMove)this.san.stringToMove(this.board, "Nf3");
      this.board.playMove(this.move);
      this.board2 = (ChessBoard)this.fen.stringToBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
      assertTrue(this.board.isWhiteCastleableKingside() == this.board2.isWhiteCastleableKingside());
      assertTrue(this.board.isBlackCastleableKingside() == this.board2.isBlackCastleableKingside());
      assertTrue(this.board.isWhiteCastleableQueenside() == this.board2.isWhiteCastleableQueenside());
      assertTrue(this.board.isBlackCastleableQueenside() == this.board2.isBlackCastleableQueenside());
      assertTrue(this.board.getEnPassantFile() == this.board2.getEnPassantFile());
      assertTrue(this.board.get50MoveRulePlyCount() == this.board2.get50MoveRulePlyCount());
      if(!this.board.equals(this.board2)) {
         Log.debug(FEN.DEBUG, "Boards not equal");
         Log.debug2(FEN.DEBUG, this.board.dump());
         Log.debug2(FEN.DEBUG, this.board2.dump());
      }

      assertTrue(this.board.equals(this.board2));
   }
}
