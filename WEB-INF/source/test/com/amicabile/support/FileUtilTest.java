package test.com.amicabile.support;

import com.amicabile.support.FileUtil;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.TestCase;

public class FileUtilTest extends TestCase {

   public void testRecursiveGetFiles() {
      Collection files = FileUtil.getFilesInDir("/eclipse/workspace/letsplaychess/", ".pgn", true);
      Iterator var3 = files.iterator();

      while(var3.hasNext()) {
         File file = (File)var3.next();
         System.out.println(file.getPath());
      }

   }
}
