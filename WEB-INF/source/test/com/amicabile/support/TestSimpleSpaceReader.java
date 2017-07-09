package test.com.amicabile.support;

import com.amicabile.support.SimpleReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import junit.framework.TestCase;
import junit.textui.TestRunner;

public class TestSimpleSpaceReader extends TestCase {

   public static void main(String[] args) {
      TestRunner.run(TestSimpleSpaceReader.class);
   }

   protected void setUp() throws Exception {
      super.setUp();
   }

   public void testAddSpaceReader() throws Exception {
      FileReader fileReader = new FileReader("pgn/testread.pgn");
      SimpleReader simpleSpaceReader = new SimpleReader(fileReader);
      BufferedReader bufReader = new BufferedReader(simpleSpaceReader);
      String buf = null;
      new FileWriter("pgn/testwrite.pgn");

      while((buf = bufReader.readLine()) != null) {
         System.out.println(buf);
      }

   }
}
