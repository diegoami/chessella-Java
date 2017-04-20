/*    */ package test.com.amicabile.support;
/*    */ 
/*    */ import com.amicabile.support.AddSpaceReader;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import junit.framework.TestCase;
/*    */ import junit.textui.TestRunner;
/*    */ 
/*    */ public class TestAddSpaceReader extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 14 */     TestRunner.run(TestAddSpaceReader.class);
/*    */   }
/*    */   
/*    */   protected void setUp() throws Exception {
/* 18 */     super.setUp();
/*    */   }
/*    */   
/*    */   public void testAddSpaceReader()
/*    */     throws Exception
/*    */   {
/* 24 */     FileReader fileReader = new FileReader("pgn/testread.pgn");
/* 25 */     AddSpaceReader addSpaceReader = new AddSpaceReader(fileReader);
/*    */     
/* 27 */     BufferedReader bufReader = new BufferedReader(addSpaceReader);
/* 28 */     String buf = null;
/* 29 */     FileWriter writer = new FileWriter("pgn/testwrite.pgn");
/* 30 */     while ((buf = bufReader.readLine()) != null) {
/* 31 */       System.out.println(buf);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\test\com\amicabile\support\TestAddSpaceReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */