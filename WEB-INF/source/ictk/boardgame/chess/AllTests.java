package ictk.boardgame.chess;

import ictk.boardgame.chess.BishopTest;
import ictk.boardgame.chess.ChessBoardTest;
import ictk.boardgame.chess.ChessGameInfoTest;
import ictk.boardgame.chess.ChessGameTest;
import ictk.boardgame.chess.ChessMoveTest;
import ictk.boardgame.chess.KingTest;
import ictk.boardgame.chess.KnightTest;
import ictk.boardgame.chess.PawnTest;
import ictk.boardgame.chess.QueenTest;
import ictk.boardgame.chess.RookTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

   public static void main(String[] args) {
      TestRunner.run(suite());
   }

   public static Test suite() {
      TestSuite suite = new TestSuite("Chess Test");
      suite.addTest(new TestSuite(ChessGameTest.class));
      suite.addTest(new TestSuite(ChessGameInfoTest.class));
      suite.addTest(new TestSuite(ChessBoardTest.class));
      suite.addTest(new TestSuite(ChessMoveTest.class));
      suite.addTest(new TestSuite(PawnTest.class));
      suite.addTest(new TestSuite(KnightTest.class));
      suite.addTest(new TestSuite(BishopTest.class));
      suite.addTest(new TestSuite(RookTest.class));
      suite.addTest(new TestSuite(QueenTest.class));
      suite.addTest(new TestSuite(KingTest.class));
      return suite;
   }
}
