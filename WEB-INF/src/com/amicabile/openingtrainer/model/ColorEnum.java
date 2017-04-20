/*    */ package com.amicabile.openingtrainer.model;
/*    */ 
/*    */ public enum ColorEnum {
/*  4 */   WHITE("w"), 
/*  5 */   BLACK("b"), 
/*  6 */   NONE("");
/*    */   
/*    */   private String charRepr;
/*    */   
/* 10 */   private ColorEnum(String charRepr) { this.charRepr = charRepr; }
/*    */   
/*    */   public String getCharRepr()
/*    */   {
/* 14 */     return this.charRepr;
/*    */   }
/*    */   
/* 17 */   public String toString() { return this.charRepr; }
/*    */   
/*    */   public ColorEnum getOtherColor()
/*    */   {
/* 21 */     if (equals(WHITE)) {
/* 22 */       return BLACK;
/*    */     }
/* 24 */     return WHITE;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\ColorEnum.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */