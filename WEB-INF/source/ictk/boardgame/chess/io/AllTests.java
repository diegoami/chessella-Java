package ictk.boardgame.chess.io;

import ictk.boardgame.chess.io.ChessAnnotationTest;
import ictk.boardgame.chess.io.FENTest;
import ictk.boardgame.chess.io.NAGTest;
import ictk.boardgame.chess.io.PGNReaderTest;
import ictk.boardgame.chess.io.PGNWriterTest;
import ictk.boardgame.chess.io.SANTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

   public static void main(String[] args) {
      TestRunner.run(suite());
   }

   public static Test suite() {
      TestSuite suite = new TestSuite("ictk.chess.io Test");
      suite.addTest(new TestSuite(NAGTest.class));
      suite.addTest(new TestSuite(ChessAnnotationTest.class));
      suite.addTest(new TestSuite(SANTest.class));
      suite.addTest(new TestSuite(FENTest.class));
      suite.addTest(new TestSuite(PGNReaderTest.class));
      suite.addTest(new TestSuite(PGNWriterTest.class));
      return suite;
   }
}
