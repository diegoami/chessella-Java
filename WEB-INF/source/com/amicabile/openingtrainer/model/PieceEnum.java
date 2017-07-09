package com.amicabile.openingtrainer.model;


public enum PieceEnum {

   NONE("NONE", 0, ""),
   BLACK_PAWN("BLACK_PAWN", 1, "bp"),
   BLACK_KNIGHT("BLACK_KNIGHT", 2, "bn"),
   BLACK_BISHOP("BLACK_BISHOP", 3, "bb"),
   BLACK_ROOK("BLACK_ROOK", 4, "br"),
   BLACK_QUEEN("BLACK_QUEEN", 5, "bq"),
   BLACK_KING("BLACK_KING", 6, "bk"),
   WHITE_PAWN("WHITE_PAWN", 7, "wp"),
   WHITE_KNIGHT("WHITE_KNIGHT", 8, "wn"),
   WHITE_BISHOP("WHITE_BISHOP", 9, "wb"),
   WHITE_ROOK("WHITE_ROOK", 10, "wr"),
   WHITE_QUEEN("WHITE_QUEEN", 11, "wq"),
   WHITE_KING("WHITE_KING", 12, "wk");
   private String charRepr;
   // $FF: synthetic field
   private static final PieceEnum[] ENUM$VALUES = new PieceEnum[]{NONE, BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING, WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING};


   private PieceEnum(String var1, int var2, String charRepr) {
      this.charRepr = charRepr;
   }

   public String getCharRepr() {
      return this.charRepr;
   }

   public String toString() {
      return this.charRepr;
   }
}
