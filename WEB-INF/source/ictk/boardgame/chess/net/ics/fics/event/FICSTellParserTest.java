package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ICSTellEvent;
import ictk.boardgame.chess.net.ics.event.ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSTellParser;
import ictk.util.Log;
import java.io.IOException;

public class FICSTellParserTest extends ParserTest {

   ICSTellEvent evt;


   public FICSTellParserTest() throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp() {
      this.parser = FICSTellParser.getInstance();
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
         this.evt = (ICSTellEvent)this.parser.createICSEvent((CharSequence)this.mesg[0]);
         assertTrue(this.evt != null);
         assertTrue(this.evt.getPlayer().equals("Handle"));
         assertTrue(this.evt.getMessage().equals("Hey"));
         assertFalse(this.evt.isFake());
         assertTrue(this.evt.getEventType() == 9);
         assertFalse(this.evt.getMessage().equals("hey"));
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.debug = false;
      }

   }

   public void testMessage1() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         this.evt = (ICSTellEvent)this.parser.createICSEvent((CharSequence)this.mesg[1]);
         assertTrue(this.evt != null);
         assertTrue(this.evt.getPlayer().equals("Handle"));
         assertTrue(this.evt.getAccountType().is(1));
         assertTrue(this.evt.getMessage().equals("Hey"));
         assertFalse(this.evt.isFake());
         assertTrue(this.evt.getEventType() == 9);
         assertFalse(this.evt.getMessage().equals("hey"));
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
