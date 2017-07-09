package ictk.boardgame;

import ictk.boardgame.ContinuationListTest;
import ictk.boardgame.HistoryTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

   public static void main(String[] args) {
      TestRunner.run(suite());
   }

   public static Test suite() {
      TestSuite suite = new TestSuite("BoardGame Test");
      suite.addTest(new TestSuite(ContinuationListTest.class));
      suite.addTest(new TestSuite(HistoryTest.class));
      return suite;
   }
}
