package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessGameInfo;
import ictk.util.Log;
import junit.framework.TestCase;

public class ChessGameInfoTest extends TestCase {

   ChessGameInfo gi;


   public ChessGameInfoTest(String name) {
      super(name);
   }

   public void setUp() {
      this.gi = new ChessGameInfo();
   }

   public void tearDown() {
      this.gi = null;
      Log.removeMask(ChessGameInfo.DEBUG);
   }

   public void testEquality() {
      this.gi.add("foo", "bar");
      ChessGameInfo gi2 = new ChessGameInfo();
      gi2.add("foo", "bar");
      assertTrue(this.gi.equals(gi2));
      assertTrue(gi2.equals(this.gi));
   }

   public void testEqualityNot() {
      this.gi.add("foo", "bar");
      ChessGameInfo gi2 = new ChessGameInfo();
      gi2.add("foo", "baz");
      assertFalse(this.gi.equals(gi2));
      assertFalse(gi2.equals(this.gi));
   }
}
