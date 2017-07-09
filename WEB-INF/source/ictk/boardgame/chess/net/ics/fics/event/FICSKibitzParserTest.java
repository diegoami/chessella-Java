package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSKibitzEvent;
import ictk.boardgame.chess.net.ics.event.ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParser;
import ictk.util.Log;
import java.io.IOException;

public class FICSKibitzParserTest extends ParserTest {

   ICSKibitzEvent evt;


   public FICSKibitzParserTest() throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp() {
      this.parser = FICSKibitzParser.getInstance();
   }

   public void tearDown() {
      this.evt = null;
      this.parser = null;
   }

   public void testMessage0() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         this.evt = (ICSKibitzEvent)this.parser.createICSEvent((CharSequence)this.mesg[0]);
         assertTrue(this.evt != null);
         assertTrue(this.evt.getPlayer().equals("Handle"));
         assertFalse(this.evt.getAccountType().is(0));
         assertTrue(this.evt.getRating().get() == 1902);
         assertTrue(this.evt.getBoardNumber() == 7);
         assertTrue(this.evt.getMessage().equals("hey"));
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.debug = false;
      }

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
