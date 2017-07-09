package com.amicabile.openingtrainer.model.board;

import com.amicabile.openingtrainer.model.ColorEnum;
import com.amicabile.openingtrainer.model.PieceAndColor;
import com.amicabile.openingtrainer.model.PieceEnum;
import java.util.Arrays;
import java.util.Collection;

public class VelocityRow {

   private ColorEnum firstColor;
   private PieceAndColor[] piecesAndColors = new PieceAndColor[8];


   public VelocityRow(ColorEnum firstColor) {
      this.firstColor = firstColor;

      for(int i = 0; i < 4; ++i) {
         this.piecesAndColors[i * 2] = new PieceAndColor(PieceEnum.NONE, firstColor);
         this.piecesAndColors[i * 2 + 1] = new PieceAndColor(PieceEnum.NONE, firstColor.getOtherColor());
      }

   }

   public void setPieceAt(int col, PieceEnum piece) {
      if(col >= 1 && col <= 8) {
         this.piecesAndColors[col - 1].setPiece(piece);
      } else {
         throw new IllegalArgumentException(" arg can be from 1 to 8");
      }
   }

   public Collection getPiecesAndColors() {
      return (Collection)Arrays.asList(this.piecesAndColors);
   }

   public boolean equals(Object object) {
      if(object instanceof VelocityRow) {
         return false;
      } else if(object == null) {
         return false;
      } else {
         VelocityRow that = (VelocityRow)object;
         return this.piecesAndColors.equals(that.piecesAndColors) && this.firstColor.equals(that.firstColor);
      }
   }

   public int hashCode() {
      return this.piecesAndColors.hashCode() ^ this.firstColor.hashCode() << 4;
   }
}
