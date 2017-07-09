package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import junit.framework.TestCase;

public class ParserTest extends TestCase {

   public boolean debug;
   public String[] mesg;
   String filename;
   public ICSEventParser parser;
   String dataDir = "./data";


   public ParserTest(String packageName) throws IOException {
      String sysprop = packageName + ".dataDir";
      this.filename = this.getClass().getName();
      if(System.getProperty(sysprop) != null) {
         this.dataDir = System.getProperty(sysprop);
      }

      this.filename = this.filename.substring(this.filename.lastIndexOf(46) + 1, this.filename.length()) + ".data";
      this.filename = this.dataDir + "/" + this.filename;
      this.mesg = processFile(new File(this.filename));
   }

   public void setUp() {}

   public void tearDown() {
      this.parser = null;
   }

   public static String[] processFile(File file) throws IOException {
      LinkedList list = new LinkedList();
      StringBuffer sb = new StringBuffer(80);
      String line = null;
      BufferedReader in = new BufferedReader(new FileReader(file));
      int lines = 0;

      while((line = in.readLine()) != null) {
         if(line.startsWith("#")) {
            if(lines != 0) {
               list.add(sb.toString());
               sb = new StringBuffer(80);
               lines = 0;
            }
         } else {
            sb.append(line).append("\n");
            ++lines;
         }
      }

      in.close();
      if(lines != 0) {
         list.add(sb.toString());
      }

      String[] mesg = new String[list.size()];

      for(int i = 0; i < list.size(); ++i) {
         mesg[i] = (String)list.get(i);
      }

      return mesg;
   }

   public void testParseAll() {
      ICSEvent evt = null;

      try {
         for(int i = 0; i < this.mesg.length; ++i) {
            evt = this.parser.createICSEvent((CharSequence)this.mesg[i]);
            if(this.debug && evt == null) {
               System.err.println("Couldn\'t match: " + this.mesg[i]);
            }

            assertTrue(evt != null);
         }
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.parser.setDebug(false);
      }

   }

   public void testNative() {
      if(this.debug) {
         Log.addMask(ICSEventParser.DEBUG);
         this.parser.setDebug(true);
      }

      try {
         ICSEvent evt = null;
         String nativeStr = null;

         for(int i = 0; i < this.mesg.length; ++i) {
            evt = this.parser.createICSEvent((CharSequence)this.mesg[i]);
            assertTrue(evt != null);
            nativeStr = this.parser.toNative(evt) + "\n";
            if(this.debug && !nativeStr.equals(this.mesg[i])) {
               System.out.println("origin[" + i + "]: <<|" + this.mesg[i] + "|>>");
               System.out.println("native[" + i + "]: <<|" + nativeStr + "|>>");
            }

            assertTrue(nativeStr.equals(this.mesg[i]));
         }
      } finally {
         Log.removeMask(ICSEventParser.DEBUG);
         this.parser.setDebug(false);
      }

   }
}
