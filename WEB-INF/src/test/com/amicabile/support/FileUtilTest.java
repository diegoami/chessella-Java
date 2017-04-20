/*    */ package test.com.amicabile.support;
/*    */ 
/*    */ import com.amicabile.support.FileUtil;
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collection;
/*    */ import junit.framework.TestCase;
/*    */ 
/*    */ public class FileUtilTest
/*    */   extends TestCase
/*    */ {
/*    */   public void testRecursiveGetFiles()
/*    */   {
/* 14 */     Collection<File> files = FileUtil.getFilesInDir("/eclipse/workspace/letsplaychess/", ".pgn", true);
/* 15 */     for (File file : files) {
/* 16 */       System.out.println(file.getPath());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\test\com\amicabile\support\FileUtilTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */