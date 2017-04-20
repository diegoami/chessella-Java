/*    */ package test.com.amicabile.openingtrainer.pgn;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.board.VelocityBoard;
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*    */ import junit.framework.TestCase;
/*    */ 
/*    */ public class TestPGNAdapter extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 12 */     junit.textui.TestRunner.run(TestPGNAdapter.class);
/*    */   }
/*    */   
/*    */   protected void setUp() throws Exception {
/* 16 */     super.setUp();
/*    */   }
/*    */   
/*    */   public void testGetChessGameGroupFromFile() throws Exception
/*    */   {
/* 21 */     VelocityBoard vboard = new VelocityBoard();
/* 22 */     ChessGameGroup chessGameGroup = PGNAdapter.getChessGameGroupFromFile("testread.pgn");
/*    */     
/*    */ 
/* 25 */     PGNAdapter.writeGroupToFile("testwrite.pgn", chessGameGroup);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\test\com\amicabile\openingtrainer\pgn\TestPGNAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */