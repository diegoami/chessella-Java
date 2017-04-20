/*    */ package ictk.boardgame.chess.net.ics.fics.event;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestChunker
/*    */ {
/*    */   public static String[] processFile(File file)
/*    */     throws IOException
/*    */   {
/* 45 */     List list = new LinkedList();
/*    */     
/* 47 */     StringBuffer sb = new StringBuffer(80);
/* 48 */     String line = null;
/* 49 */     BufferedReader in = new BufferedReader(new FileReader(file));
/*    */     
/* 51 */     int lines = 0;
/* 52 */     while ((line = in.readLine()) != null) {
/* 53 */       if (line.startsWith("#")) {
/* 54 */         if (lines != 0) {
/* 55 */           list.add(sb.toString());
/* 56 */           sb = new StringBuffer(80);
/* 57 */           lines = 0;
/*    */         }
/*    */       }
/*    */       else
/*    */       {
/* 62 */         sb.append(line);
/* 63 */         lines++;
/*    */       }
/*    */     }
/*    */     
/* 67 */     if (lines != 0) {
/* 68 */       list.add(sb.toString());
/*    */     }
/*    */     
/* 71 */     String[] mesg = new String[list.size()];
/* 72 */     for (int i = 0; i < list.size(); i++) {
/* 73 */       mesg[i] = ((String)list.get(i));
/*    */     }
/* 75 */     return mesg;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\TestChunker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */