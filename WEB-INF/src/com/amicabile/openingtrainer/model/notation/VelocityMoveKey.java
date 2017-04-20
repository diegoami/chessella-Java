/*    */ package com.amicabile.openingtrainer.model.notation;
/*    */ 
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VelocityMoveKey
/*    */ {
/*    */   private String moveString;
/*    */   private int number;
/*    */   private int variationsDeep;
/*    */   private boolean isBlackMove;
/*    */   private int boardHashCode;
/*    */   
/*    */   public String toString()
/*    */   {
/* 17 */     return 
/*    */     
/* 19 */       "VelocityMoveKey(" + this.moveString + "," + this.number + "," + this.variationsDeep + "," + this.isBlackMove + "," + this.boardHashCode + ")";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public VelocityMoveKey(String moveString, int number, int variationsDeep, boolean isBlackMove, int variationNumber)
/*    */   {
/* 27 */     this.moveString = moveString;
/* 28 */     this.number = number;
/* 29 */     this.variationsDeep = variationsDeep;
/* 30 */     this.isBlackMove = isBlackMove;
/* 31 */     this.boardHashCode = variationNumber;
/*    */   }
/*    */   
/*    */   public void setToMainMove()
/*    */   {
/* 36 */     this.variationsDeep = 0;
/*    */   }
/*    */   
/*    */   public boolean isMainMove()
/*    */   {
/* 41 */     return this.variationsDeep == 0;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 45 */     if (!(object instanceof VelocityMoveKey)) return false;
/* 46 */     if (this == object) return true;
/* 47 */     VelocityMoveKey otherCallbackMoveKey = (VelocityMoveKey)object;
/* 48 */     return new EqualsBuilder()
/* 49 */       .append(this.moveString, otherCallbackMoveKey.moveString)
/* 50 */       .append(this.number, otherCallbackMoveKey.number).append(this.isBlackMove, otherCallbackMoveKey.isBlackMove)
/* 51 */       .append(this.variationsDeep, otherCallbackMoveKey.variationsDeep)
/* 52 */       .append(this.boardHashCode, otherCallbackMoveKey.boardHashCode).isEquals();
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 56 */     return this.number * 11 ^ 
/* 57 */       this.variationsDeep * 7 ^ 
/* 58 */       this.boardHashCode * 23 ^ 
/* 59 */       this.moveString.hashCode() ^ 
/* 60 */       (this.isBlackMove ? 0 : 17);
/*    */   }
/*    */   
/*    */   public VelocityMoveNumberKey getMoveNumberKey() {
/* 64 */     return new VelocityMoveNumberKey(this.number, this.isBlackMove);
/*    */   }
/*    */   
/*    */   public String getMoveString() {
/* 68 */     return this.moveString;
/*    */   }
/*    */   
/*    */   public void setMoveString(String moveString) {
/* 72 */     this.moveString = moveString;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\notation\VelocityMoveKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */