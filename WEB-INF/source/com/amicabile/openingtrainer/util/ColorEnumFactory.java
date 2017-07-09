package com.amicabile.openingtrainer.util;

import com.amicabile.openingtrainer.model.ColorEnum;

public class ColorEnumFactory {

   public static ColorEnum createFromFenNotation(char fenNotation) {
      switch(fenNotation) {
      case 98:
         return ColorEnum.BLACK;
      case 119:
         return ColorEnum.WHITE;
      default:
         return ColorEnum.NONE;
      }
   }
}
