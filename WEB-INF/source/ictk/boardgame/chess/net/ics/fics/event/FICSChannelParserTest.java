package ictk.boardgame.chess.net.ics.fics.event;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.event.ParserTest;
import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParser;
import ictk.util.Log;
import java.io.IOException;

public class FICSChannelParserTest extends ParserTest {

   ICSChannelEvent evt;


   public FICSChannelParserTest() throws IOException {
      super("ictk.boardgame.chess.net.ics.fics.event");
   }

   public void setUp() {
      this.parser = FICSChannelParser.getInstance();
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
         this.evt = (ICSChannelEvent)this.parser.createICSEvent((CharSequence)this.mesg[0]);
         assertTrue(this.evt != null);
         assertTrue("Gorgonian".equals(this.evt.getPlayer()));
         assertTrue(this.evt.getChannel() == 50);
         assertTrue("da".equals(this.evt.getMessage()));
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
         this.evt = (ICSChannelEvent)this.parser.createICSEvent((CharSequence)this.mesg[1]);
         assertTrue(this.evt != null);
         assertTrue(this.evt.getAccountType().is(7));
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.debug = false;
      }

   }

   public void testMessage3() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         this.evt = (ICSChannelEvent)this.parser.createICSEvent((CharSequence)this.mesg[3]);
         assertTrue(this.evt != null);
         assertTrue(this.evt.getAccountType().is(4));
         assertTrue(this.evt.getAccountType().is(5));
         assertTrue(this.evt.getAccountType().is(7));
         assertTrue(this.evt.getAccountType().is(8));
         assertTrue(this.evt.getChannel() == 49);
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
