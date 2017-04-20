/*    */ package com.amicabile.support;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class RegExUtil
/*    */ {
/*    */   public static boolean matches(String input, String regex)
/*    */   {
/* 10 */     Pattern pattern = Pattern.compile(regex);
/* 11 */     Matcher matcher = pattern.matcher(input);
/*    */     
/* 13 */     return matcher.find();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\RegExUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */