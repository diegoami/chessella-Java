/*    */ package com.amicabile.support;
/*    */ 
/*    */ import java.io.FilterReader;
/*    */ 
/*    */ public class SimpleReader extends FilterReader
/*    */ {
/*    */   public SimpleReader(java.io.Reader in)
/*    */   {
/*  9 */     super(in);
/*    */   }
/*    */   
/*    */   public int read(char[] cbuf, int off, int len) throws java.io.IOException {
/* 13 */     int i = 0;
/* 14 */     while (i < len) {
/* 15 */       int ch = read();
/* 16 */       if (ch == -1) {
/* 17 */         if (i == 0) {
/* 18 */           return -1;
/*    */         }
/* 20 */         return i;
/*    */       }
/*    */       
/*    */ 
/* 24 */       System.out.println(ch);
/* 25 */       cbuf[(off + i++)] = ((char)ch);
/*    */     }
/* 27 */     return len;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\SimpleReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */