/*    */ package com.amicabile.support;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static String getCurrentDateString() {
/*  8 */     Calendar cal = Calendar.getInstance();
/*  9 */     cal.setTime(new java.util.Date());
/* 10 */     StringBuffer buffer = new StringBuffer();
/*    */     
/* 12 */     buffer.append(cal.get(1));
/* 13 */     buffer.append(cal.get(2) + 1);
/* 14 */     buffer.append(cal.get(5));
/* 15 */     buffer.append("_");
/* 16 */     buffer.append(cal.get(11));
/* 17 */     buffer.append(cal.get(12));
/* 18 */     buffer.append(cal.get(13));
/* 19 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\StringUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */