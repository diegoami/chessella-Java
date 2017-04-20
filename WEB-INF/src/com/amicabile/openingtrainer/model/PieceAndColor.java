/*    */ package com.amicabile.openingtrainer.model;
/*    */ 
/*    */ public class PieceAndColor {
/*    */   private PieceEnum piece;
/*    */   private ColorEnum color;
/*    */   
/*    */   public ColorEnum getColor() {
/*  8 */     return this.color;
/*    */   }
/*    */   
/* 11 */   public void setColor(ColorEnum color) { this.color = color; }
/*    */   
/*    */   public PieceEnum getPiece() {
/* 14 */     return this.piece;
/*    */   }
/*    */   
/* 17 */   public void setPiece(PieceEnum piece) { this.piece = piece; }
/*    */   
/*    */   public PieceAndColor(PieceEnum piece, ColorEnum color) {
/* 20 */     this.piece = piece;
/* 21 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\PieceAndColor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */