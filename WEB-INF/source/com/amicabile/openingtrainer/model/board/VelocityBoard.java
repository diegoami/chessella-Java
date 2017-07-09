package com.amicabile.openingtrainer.model.board;

import com.amicabile.openingtrainer.model.ColorEnum;
import com.amicabile.openingtrainer.model.PieceEnum;
import com.amicabile.openingtrainer.model.board.VelocityRow;

public class VelocityBoard {

   private ColorEnum toMove;
   private VelocityRow[] velocityRows;


   public VelocityBoard() {
      this.toMove = ColorEnum.WHITE;
      this.velocityRows = new VelocityRow[]{new VelocityRow(ColorEnum.WHITE), new VelocityRow(ColorEnum.BLACK), new VelocityRow(ColorEnum.WHITE), new VelocityRow(ColorEnum.BLACK), new VelocityRow(ColorEnum.WHITE), new VelocityRow(ColorEnum.BLACK), new VelocityRow(ColorEnum.WHITE), new VelocityRow(ColorEnum.BLACK)};
   }

   public VelocityRow getRowAt(int row) {
      return this.velocityRows[row - 1];
   }

   public void setPieceAt(int row, int col, PieceEnum piece) {
      this.getRowAt(row).setPieceAt(col, piece);
   }

   public VelocityRow[] getRows() {
      return this.velocityRows;
   }

   public ColorEnum getToMove() {
      return this.toMove;
   }

   public void setToMove(ColorEnum toMove) {
      this.toMove = toMove;
   }
}
