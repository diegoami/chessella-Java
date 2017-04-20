/*    */ package com.amicabile.openingtrainer.util;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.ColorEnum;
/*    */ 
/*    */ public class ColorEnumFactory {
/*    */   public static ColorEnum createFromFenNotation(char fenNotation) {
/*  7 */     switch (fenNotation) {
/*  8 */     case 'w':  return ColorEnum.WHITE;
/*  9 */     case 'b':  return ColorEnum.BLACK; }
/* 10 */     return ColorEnum.NONE;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\util\ColorEnumFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */