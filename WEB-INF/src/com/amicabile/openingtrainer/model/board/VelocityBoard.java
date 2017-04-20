/*    */ package com.amicabile.openingtrainer.model.board;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.ColorEnum;
/*    */ import com.amicabile.openingtrainer.model.PieceEnum;
/*    */ 
/*    */ public class VelocityBoard
/*    */ {
/*  8 */   private ColorEnum toMove = ColorEnum.WHITE;
/*    */   
/* 10 */   private VelocityRow[] velocityRows = {
/* 11 */     new VelocityRow(ColorEnum.WHITE), 
/* 12 */     new VelocityRow(ColorEnum.BLACK), 
/* 13 */     new VelocityRow(ColorEnum.WHITE), 
/* 14 */     new VelocityRow(ColorEnum.BLACK), 
/* 15 */     new VelocityRow(ColorEnum.WHITE), 
/* 16 */     new VelocityRow(ColorEnum.BLACK), 
/* 17 */     new VelocityRow(ColorEnum.WHITE), 
/* 18 */     new VelocityRow(ColorEnum.BLACK) };
/*    */   
/*    */ 
/*    */   public VelocityRow getRowAt(int row)
/*    */   {
/* 23 */     return this.velocityRows[(row - 1)];
/*    */   }
/*    */   
/*    */   public void setPieceAt(int row, int col, PieceEnum piece) {
/* 27 */     getRowAt(row).setPieceAt(col, piece);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public VelocityRow[] getRows()
/*    */   {
/* 34 */     return this.velocityRows;
/*    */   }
/*    */   
/*    */   public ColorEnum getToMove() {
/* 38 */     return this.toMove;
/*    */   }
/*    */   
/*    */   public void setToMove(ColorEnum toMove) {
/* 42 */     this.toMove = toMove;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\board\VelocityBoard.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */