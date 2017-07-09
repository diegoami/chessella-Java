package test.com.amicabile.openingtrainer.pgn;

import com.amicabile.openingtrainer.model.board.VelocityBoard;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import junit.framework.TestCase;
import junit.textui.TestRunner;

public class TestPGNAdapter extends TestCase {

   public static void main(String[] args) {
      TestRunner.run(TestPGNAdapter.class);
   }

   protected void setUp() throws Exception {
      super.setUp();
   }

   public void testGetChessGameGroupFromFile() throws Exception {
      new VelocityBoard();
      ChessGameGroup chessGameGroup = PGNAdapter.getChessGameGroupFromFile("testread.pgn");
      PGNAdapter.writeGroupToFile("testwrite.pgn", chessGameGroup);
   }
}
