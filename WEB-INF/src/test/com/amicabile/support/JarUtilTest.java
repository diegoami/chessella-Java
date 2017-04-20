/*    */ package test.com.amicabile.support;
/*    */ 
/*    */ import com.amicabile.support.JarUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collection;
/*    */ import junit.framework.TestCase;
/*    */ 
/*    */ public class JarUtilTest
/*    */   extends TestCase
/*    */ {
/*    */   public void testRecursiveGetFiles()
/*    */   {
/* 13 */     String fileName = "/jump/jumpproject/beschaffung/modules/jump/base/src/jump-base-src.jar";
/* 14 */     Collection<String> entries = 
/* 15 */       JarUtil.getEntriesInFile(fileName, "\\.xml");
/* 16 */     for (String entry : entries) {
/* 17 */       System.out.println("Entry : " + entry);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void testRecursiveGetFilestrings()
/*    */   {
/* 24 */     String fileName = "/jump/jumpproject/beschaffung/modules/jump/base/src/jump-base-src.jar";
/* 25 */     Collection<String> entries = 
/* 26 */       JarUtil.getFileContents(fileName, "\\.xml");
/* 27 */     for (String entry : entries) {
/* 28 */       System.out.println(entry);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\test\com\amicabile\support\JarUtilTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */