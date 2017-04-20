/*    */ package com.amicabile.openingtrainer.model.board;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.ColorEnum;
/*    */ import com.amicabile.openingtrainer.model.PieceAndColor;
/*    */ import com.amicabile.openingtrainer.model.PieceEnum;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VelocityRow
/*    */ {
/*    */   private ColorEnum firstColor;
/* 14 */   private PieceAndColor[] piecesAndColors = new PieceAndColor[8];
/*    */   
/*    */ 
/*    */ 
/*    */   public VelocityRow(ColorEnum firstColor)
/*    */   {
/* 20 */     this.firstColor = firstColor;
/* 21 */     for (int i = 0; i < 4; i++) {
/* 22 */       this.piecesAndColors[(i * 2)] = new PieceAndColor(PieceEnum.NONE, firstColor);
/* 23 */       this.piecesAndColors[(i * 2 + 1)] = new PieceAndColor(PieceEnum.NONE, firstColor.getOtherColor());
/*    */     }
/*    */   }
/*    */   
/*    */   public void setPieceAt(int col, PieceEnum piece)
/*    */   {
/* 29 */     if ((col < 1) || (col > 8)) {
/* 30 */       throw new IllegalArgumentException(" arg can be from 1 to 8");
/*    */     }
/* 32 */     this.piecesAndColors[(col - 1)].setPiece(piece);
/*    */   }
/*    */   
/*    */   public Collection<PieceAndColor> getPiecesAndColors()
/*    */   {
/* 37 */     return (Collection)Arrays.asList(this.piecesAndColors);
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 41 */     if ((object instanceof VelocityRow)) return false;
/* 42 */     if (object == null) return false;
/* 43 */     VelocityRow that = (VelocityRow)object;
/*    */     
/* 45 */     return (this.piecesAndColors.equals(that.piecesAndColors)) && (this.firstColor.equals(that.firstColor));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 49 */     return this.piecesAndColors.hashCode() ^ this.firstColor.hashCode() << 4;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\board\VelocityRow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */