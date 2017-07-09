package com.amicabile.openingtrainer.model;

import com.amicabile.openingtrainer.model.ColorEnum;
import com.amicabile.openingtrainer.model.PieceEnum;

public class PieceAndColor {

   private PieceEnum piece;
   private ColorEnum color;


   public ColorEnum getColor() {
      return this.color;
   }

   public void setColor(ColorEnum color) {
      this.color = color;
   }

   public PieceEnum getPiece() {
      return this.piece;
   }

   public void setPiece(PieceEnum piece) {
      this.piece = piece;
   }

   public PieceAndColor(PieceEnum piece, ColorEnum color) {
      this.piece = piece;
      this.color = color;
   }
}
