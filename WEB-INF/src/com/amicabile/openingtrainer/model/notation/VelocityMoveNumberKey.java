/*    */ package com.amicabile.openingtrainer.model.notation;
/*    */ 
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ 
/*    */ 
/*    */ public class VelocityMoveNumberKey
/*    */ {
/*    */   private int number;
/*    */   private boolean isBlackMove;
/* 10 */   public static VelocityMoveNumberKey START_MOVE = new VelocityMoveNumberKey(1, false);
/*    */   
/*    */   public String toString()
/*    */   {
/* 14 */     return "VelocityMoveNumberKey(" + this.number + "," + this.isBlackMove + ")";
/*    */   }
/*    */   
/*    */   public VelocityMoveNumberKey(int number, boolean isBlackMove)
/*    */   {
/* 19 */     this.number = number;
/* 20 */     this.isBlackMove = isBlackMove;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object)
/*    */   {
/* 25 */     if (!(object instanceof VelocityMoveNumberKey)) return false;
/* 26 */     if (this == object) return true;
/* 27 */     VelocityMoveNumberKey that = (VelocityMoveNumberKey)object;
/* 28 */     return new EqualsBuilder()
/* 29 */       .append(this.number, that.number)
/* 30 */       .append(this.isBlackMove, that.isBlackMove)
/* 31 */       .isEquals();
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 35 */     return this.number ^ (this.isBlackMove ? 0 : 17);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\notation\VelocityMoveNumberKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */