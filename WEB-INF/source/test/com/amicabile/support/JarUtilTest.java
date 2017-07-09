package test.com.amicabile.support;

import com.amicabile.support.JarUtil;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.TestCase;

public class JarUtilTest extends TestCase {

   public void testRecursiveGetFiles() {
      String fileName = "/jump/jumpproject/beschaffung/modules/jump/base/src/jump-base-src.jar";
      Collection entries = JarUtil.getEntriesInFile(fileName, "\\.xml");
      Iterator var4 = entries.iterator();

      while(var4.hasNext()) {
         String entry = (String)var4.next();
         System.out.println("Entry : " + entry);
      }

   }

   public void testRecursiveGetFilestrings() {
      String fileName = "/jump/jumpproject/beschaffung/modules/jump/base/src/jump-base-src.jar";
      Collection entries = JarUtil.getFileContents(fileName, "\\.xml");
      Iterator var4 = entries.iterator();

      while(var4.hasNext()) {
         String entry = (String)var4.next();
         System.out.println(entry);
      }

   }
}
