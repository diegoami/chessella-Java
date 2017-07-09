package com.amicabile.openingtrainer.model.notation;

import org.apache.commons.lang.builder.EqualsBuilder;

public class VelocityMoveNumberKey {

   private int number;
   private boolean isBlackMove;
   public static VelocityMoveNumberKey START_MOVE = new VelocityMoveNumberKey(1, false);


   public String toString() {
      return "VelocityMoveNumberKey(" + this.number + "," + this.isBlackMove + ")";
   }

   public VelocityMoveNumberKey(int number, boolean isBlackMove) {
      this.number = number;
      this.isBlackMove = isBlackMove;
   }

   public boolean equals(Object object) {
      if(!(object instanceof VelocityMoveNumberKey)) {
         return false;
      } else if(this == object) {
         return true;
      } else {
         VelocityMoveNumberKey that = (VelocityMoveNumberKey)object;
         return (new EqualsBuilder()).append(this.number, that.number).append(this.isBlackMove, that.isBlackMove).isEquals();
      }
   }

   public int hashCode() {
      return this.number ^ (this.isBlackMove?0:17);
   }
}
