package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.fics.event.FICSBoardUpdateStyle12ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameCreatedParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameNotificationParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSMoveListParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerConnectionParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerNotificationParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdReadableParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekClearParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekRemoveParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSTellParserTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

   public static void main(String[] args) {
      TestRunner.run(suite());
   }

   public static Test suite() {
      TestSuite suite = new TestSuite("FICS event parsers");
      suite.addTest(new TestSuite(FICSKibitzParserTest.class));
      suite.addTest(new TestSuite(FICSChannelParserTest.class));
      suite.addTest(new TestSuite(FICSGameCreatedParserTest.class));
      suite.addTest(new TestSuite(FICSGameNotificationParserTest.class));
      suite.addTest(new TestSuite(FICSGameResultParserTest.class));
      suite.addTest(new TestSuite(FICSPlayerConnectionParserTest.class));
      suite.addTest(new TestSuite(FICSPlayerNotificationParserTest.class));
      suite.addTest(new TestSuite(FICSSeekAdParserTest.class));
      suite.addTest(new TestSuite(FICSSeekAdReadableParserTest.class));
      suite.addTest(new TestSuite(FICSSeekClearParserTest.class));
      suite.addTest(new TestSuite(FICSSeekRemoveParserTest.class));
      suite.addTest(new TestSuite(FICSShoutParserTest.class));
      suite.addTest(new TestSuite(FICSTellParserTest.class));
      suite.addTest(new TestSuite(FICSMoveListParserTest.class));
      suite.addTest(new TestSuite(FICSBoardUpdateStyle12ParserTest.class));
      return suite;
   }
}
