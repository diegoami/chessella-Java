package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParser;
import ictk.util.Log;
import java.io.IOException;

public class FICSShoutParserTest extends ParserTest {

   ICSChannelEvent evt;


   public FICSShoutParserTest() throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp() {
      this.parser = FICSShoutParser.getInstance();
   }

   public void tearDown() {
      this.evt = null;
      this.parser = null;
   }

   public void testParseAll() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         super.testParseAll();
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.debug = false;
      }

   }

   public void testNative() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         super.testNative();
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.debug = false;
      }

   }
}
