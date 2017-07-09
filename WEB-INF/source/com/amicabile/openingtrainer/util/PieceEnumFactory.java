package com.amicabile.openingtrainer.util;

import com.amicabile.openingtrainer.model.PieceEnum;

public class PieceEnumFactory {

   public static PieceEnum createFromFenNotation(char fenNotation) {
      switch(fenNotation) {
      case 66:
         return PieceEnum.WHITE_BISHOP;
      case 75:
         return PieceEnum.WHITE_KING;
      case 78:
         return PieceEnum.WHITE_KNIGHT;
      case 80:
         return PieceEnum.WHITE_PAWN;
      case 81:
         return PieceEnum.WHITE_QUEEN;
      case 82:
         return PieceEnum.WHITE_ROOK;
      case 98:
         return PieceEnum.BLACK_BISHOP;
      case 107:
         return PieceEnum.BLACK_KING;
      case 110:
         return PieceEnum.BLACK_KNIGHT;
      case 112:
         return PieceEnum.BLACK_PAWN;
      case 113:
         return PieceEnum.BLACK_QUEEN;
      case 114:
         return PieceEnum.BLACK_ROOK;
      default:
         return PieceEnum.NONE;
      }
   }
}
