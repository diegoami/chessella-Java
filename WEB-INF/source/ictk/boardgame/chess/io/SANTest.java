package ictk.boardgame.chess.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.AmbiguousChessMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import junit.framework.TestCase;

public class SANTest extends TestCase {

   SAN san;
   ChessBoard board;
   ChessResult res;
   ChessMove move;


   public SANTest(String name) {
      super(name);
   }

   public void setUp() {
      this.san = new SAN();
   }

   public void tearDown() {
      this.san = null;
      this.board = null;
      this.res = null;
      this.move = null;
      Log.removeMask(SAN.DEBUG);
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testStringToResult() {
      this.res = (ChessResult)this.san.stringToResult("1-0");
      assertTrue(this.res.equals(new ChessResult(2)));
      this.res = (ChessResult)this.san.stringToResult("0-1");
      assertTrue(this.res.equals(new ChessResult(3)));
      this.res = (ChessResult)this.san.stringToResult("1/2-1/2");
      assertTrue(this.res.equals(new ChessResult(1)));
      this.res = (ChessResult)this.san.stringToResult("*");
      assertTrue(this.res.equals(new ChessResult(0)));
      this.res = (ChessResult)this.san.stringToResult("");
      assertTrue(this.res == null);
      this.res = (ChessResult)this.san.stringToResult("fjdkslfjs");
      assertTrue(this.res == null);
   }

   public void testResultToString() {
      this.res = new ChessResult(2);
      assertTrue("1-0".equals(this.san.resultToString(this.res)));
      this.res = new ChessResult(3);
      assertTrue("0-1".equals(this.san.resultToString(this.res)));
      this.res = new ChessResult(1);
      assertTrue("1/2-1/2".equals(this.san.resultToString(this.res)));
      this.res = new ChessResult(0);
      assertTrue("*".equals(this.san.resultToString(this.res)));
   }

   public void testStringToMove_1() throws IllegalMoveException, AmbiguousMoveException {
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      assertTrue(this.move != null);
   }

   public void testMoveToString_1() throws IllegalMoveException, AmbiguousMoveException {
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      assertTrue(this.san.moveToString(this.move).equals("e4"));
   }

   public void testStringToMove_2() {
      this.board = new ChessBoard();

      try {
         assertTrue((ChessMove)this.san.stringToMove(this.board, "24") == null);
         assertTrue((ChessMove)this.san.stringToMove(this.board, "fdfd") == null);
         assertTrue((ChessMove)this.san.stringToMove(this.board, "") == null);
         assertTrue((ChessMove)this.san.stringToMove(this.board, "\tf5#@") == null);

         try {
            this.move = (ChessMove)this.san.stringToMove(this.board, "e5");
         } catch (IllegalMoveException var2) {
            ;
         }
      } catch (Exception var3) {
         fail("san parsed garbage and threw and exception: " + var3);
      }

   }

   public void testPromotion() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', 'k', ' '}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "e8=Q");
      assertTrue(this.move != null);
   }

   public void testPromotionWithCheck() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "e8=Q+");
      assertTrue(this.move != null);
   }

   public void testPromotionWithCapture() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', 'k', ' '}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "exd8=Q");
      assertTrue(this.move != null);
   }

   public void testPromotionWithCaptureAndCheck() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'}, {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k'}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "exd8=Q+");
      assertTrue(this.move != null);
   }

   public void testPromotionWithCaptureAndDoubleCheck() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'}, {'R', ' ', ' ', ' ', ' ', ' ', 'P', 'k'}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "exd8=Q++");
      assertTrue(this.move != null);
   }

   public void testFullDisAmbiguation() throws IllegalMoveException, AmbiguousChessMoveException {
      char[][] position = new char[][]{{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'q', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'q', ' ', ' ', 'p', 'n', ' ', ' ', ' '}, {' ', ' ', ' ', 'P', 'p', ' ', ' ', 'k'}, {' ', ' ', ' ', ' ', 'P', 'p', 'q', 'b'}, {'N', ' ', ' ', ' ', ' ', 'Q', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {'K', ' ', ' ', ' ', ' ', 'Q', ' ', 'Q'}};
      this.board = new ChessBoard(position);
      this.move = (ChessMove)this.san.stringToMove(this.board, "Qh6f8");
      assertTrue(this.move != null);
   }

   public void testNAG() throws IllegalMoveException, AmbiguousChessMoveException {
      ChessAnnotation anno = null;
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4!");
      assertTrue(this.move != null);
      anno = (ChessAnnotation)this.move.getAnnotation();
      assertTrue(anno != null);
      assertTrue(anno.getNAGString().equals("!"));
   }

   public void testNAG1() throws IllegalMoveException, AmbiguousChessMoveException {
      ChessAnnotation anno = null;
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4! +=");
      assertTrue(this.move != null);
      anno = (ChessAnnotation)this.move.getAnnotation();
      assertTrue(anno != null);
      assertTrue(anno.getNAGString().equals("! +="));
   }

   public void testNAGNumeric() throws IllegalMoveException, AmbiguousChessMoveException {
      ChessAnnotation anno = null;
      this.board = new ChessBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4 $9");
      assertTrue(this.move != null);
      anno = (ChessAnnotation)this.move.getAnnotation();
      assertTrue(anno != null);
      assertTrue(anno.getNAGString().equals("$9"));
   }

   public void testFileToChar() {
      assertTrue(this.san.fileToChar(1) == 97);
      assertTrue(this.san.fileToChar(8) == 104);

      try {
         this.san.fileToChar(0);
         fail("FileToChar bounds not correct 0");
      } catch (ArrayIndexOutOfBoundsException var3) {
         ;
      }

      try {
         this.san.fileToChar(9);
         fail("FileToChar bounds not correct 9");
      } catch (ArrayIndexOutOfBoundsException var2) {
         ;
      }

   }

   public void testRankToChar() {
      assertTrue(this.san.rankToChar(1) == 49);
      assertTrue(this.san.rankToChar(8) == 56);

      try {
         this.san.rankToChar(0);
         fail("RankToChar bounds not correct 0");
      } catch (ArrayIndexOutOfBoundsException var3) {
         ;
      }

      try {
         this.san.rankToChar(9);
         fail("RankToChar bounds not correct 9");
      } catch (ArrayIndexOutOfBoundsException var2) {
         ;
      }

   }
}
