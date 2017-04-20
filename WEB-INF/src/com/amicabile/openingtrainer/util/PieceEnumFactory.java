/*    */ package com.amicabile.openingtrainer.util;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.PieceEnum;
/*    */ 
/*    */ public class PieceEnumFactory {
/*    */   public static PieceEnum createFromFenNotation(char fenNotation) {
/*  7 */     switch (fenNotation) {
/*  8 */     case 'b':  return PieceEnum.BLACK_BISHOP;
/*  9 */     case 'n':  return PieceEnum.BLACK_KNIGHT;
/* 10 */     case 'r':  return PieceEnum.BLACK_ROOK;
/* 11 */     case 'q':  return PieceEnum.BLACK_QUEEN;
/* 12 */     case 'k':  return PieceEnum.BLACK_KING;
/* 13 */     case 'p':  return PieceEnum.BLACK_PAWN;
/* 14 */     case 'B':  return PieceEnum.WHITE_BISHOP;
/* 15 */     case 'N':  return PieceEnum.WHITE_KNIGHT;
/* 16 */     case 'R':  return PieceEnum.WHITE_ROOK;
/* 17 */     case 'Q':  return PieceEnum.WHITE_QUEEN;
/* 18 */     case 'K':  return PieceEnum.WHITE_KING;
/* 19 */     case 'P':  return PieceEnum.WHITE_PAWN; }
/* 20 */     return PieceEnum.NONE;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\util\PieceEnumFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */