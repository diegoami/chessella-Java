package ictk;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

   public static void main(String[] args) {
      TestRunner.run(suite());
   }

   public static Test suite() {
      Object dataDir = null;
      TestSuite suite = new TestSuite("ictk Test");
      suite.addTest(ictk.boardgame.AllTests.suite());
      suite.addTest(ictk.boardgame.chess.AllTests.suite());
      suite.addTest(ictk.boardgame.chess.io.AllTests.suite());
      return suite;
   }
}
