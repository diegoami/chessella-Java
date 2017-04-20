/*    */ package com.amicabile.openingtrainer.model;
/*    */ 
/*    */ public enum PieceEnum {
/*  4 */   NONE(""), 
/*  5 */   BLACK_PAWN("bp"), 
/*  6 */   BLACK_KNIGHT("bn"), 
/*  7 */   BLACK_BISHOP("bb"), 
/*  8 */   BLACK_ROOK("br"), 
/*  9 */   BLACK_QUEEN("bq"), 
/* 10 */   BLACK_KING("bk"), 
/* 11 */   WHITE_PAWN("wp"), 
/* 12 */   WHITE_KNIGHT("wn"), 
/* 13 */   WHITE_BISHOP("wb"), 
/* 14 */   WHITE_ROOK("wr"), 
/* 15 */   WHITE_QUEEN("wq"), 
/* 16 */   WHITE_KING("wk");
/*    */   
/*    */   private String charRepr;
/*    */   
/* 20 */   private PieceEnum(String charRepr) { this.charRepr = charRepr; }
/*    */   
/*    */   public String getCharRepr() {
/* 23 */     return this.charRepr;
/*    */   }
/*    */   
/* 26 */   public String toString() { return this.charRepr; }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\PieceEnum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */