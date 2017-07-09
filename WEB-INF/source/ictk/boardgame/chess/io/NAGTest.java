package ictk.boardgame.chess.io;

import ictk.boardgame.chess.io.NAG;
import junit.framework.TestCase;

public class NAGTest extends TestCase {

   NAG nag;
   short[] nags;


   public NAGTest(String name) {
      super(name);
   }

   public void setUp() {
      this.nag = new NAG();
   }

   public void tearDown() {
      this.nag = null;
      this.nags = null;
   }

   public void testVerboseString() {
      assertTrue(this.nag.numberToDescription(1).equals("good move"));
   }

   public void testNumberToString() {
      assertTrue(NAG.numberToString(1).equals("!"));
   }

   public void testIsSuffixNumber() {
      assertTrue(NAG.isSuffix(1));
   }

   public void testIsSuffixNumberNot() {
      assertFalse(NAG.isSuffix(7));
   }

   public void testIsSuffixString() {
      assertTrue(NAG.isSuffix("!?"));
   }

   public void testIsSuffixStringNot() {
      assertFalse(NAG.isSuffix("N"));
   }

   public void testIsSymbol() {
      assertTrue(NAG.isSymbol(145));
   }

   public void testString() {
      assertTrue(NAG.numberToString(14).equals("+="));
   }

   public void testStringNumeric() {
      assertTrue(NAG.numberToString(14, true).equals("$14"));
   }

   public void testStringReverse() {
      this.nags = NAG.stringToNumbers("$9");
      assertTrue(this.nags.length == 1);
      assertTrue(this.nags[0] == 9);
   }

   public void testSymbolToNumber() {
      assertTrue(NAG.symbolToNumber("N") == 146);
   }

   public void testStringToNumbers() {
      this.nags = NAG.stringToNumbers("! +=");
      assertTrue(this.nags.length == 2);
      assertTrue(this.nags[0] == 1);
      assertTrue(this.nags[1] == 14);
   }

   public void testStringToNumbersOne() {
      this.nags = NAG.stringToNumbers("$5");
      assertTrue(this.nags.length == 1);
      assertTrue(this.nags[0] == 5);
   }

   public void testStringToNumbersEmpty() {
      this.nags = NAG.stringToNumbers("");
      assertTrue(this.nags == null);
   }

   public void testStringToNumbersJunk() {
      this.nags = NAG.stringToNumbers("fjdkslfj32n23jdnj 3jerk32 jrker");
      assertTrue(this.nags == null);
   }
}
