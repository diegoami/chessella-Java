package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSGameResultEvent;
import ictk.boardgame.chess.net.ics.event.ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParser;
import ictk.util.Log;
import java.io.IOException;

public class FICSGameResultParserTest extends ParserTest {

   ICSGameResultEvent evt;


   public FICSGameResultParserTest() throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp() {
      this.parser = FICSGameResultParser.getInstance();
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
