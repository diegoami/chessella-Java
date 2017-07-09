package com.amicabile.openingtrainer.model.notation;

import com.amicabile.openingtrainer.model.notation.VelocityMoveNumberKey;
import org.apache.commons.lang.builder.EqualsBuilder;

public class VelocityMoveKey {

   private String moveString;
   private int number;
   private int variationsDeep;
   private boolean isBlackMove;
   private int boardHashCode;


   public String toString() {
      return "VelocityMoveKey(" + this.moveString + "," + this.number + "," + this.variationsDeep + "," + this.isBlackMove + "," + this.boardHashCode + ")";
   }

   public VelocityMoveKey(String moveString, int number, int variationsDeep, boolean isBlackMove, int variationNumber) {
      this.moveString = moveString;
      this.number = number;
      this.variationsDeep = variationsDeep;
      this.isBlackMove = isBlackMove;
      this.boardHashCode = variationNumber;
   }

   public void setToMainMove() {
      this.variationsDeep = 0;
   }

   public boolean isMainMove() {
      return this.variationsDeep == 0;
   }

   public boolean equals(Object object) {
      if(!(object instanceof VelocityMoveKey)) {
         return false;
      } else if(this == object) {
         return true;
      } else {
         VelocityMoveKey otherCallbackMoveKey = (VelocityMoveKey)object;
         return (new EqualsBuilder()).append((Object)this.moveString, (Object)otherCallbackMoveKey.moveString).append(this.number, otherCallbackMoveKey.number).append(this.isBlackMove, otherCallbackMoveKey.isBlackMove).append(this.variationsDeep, otherCallbackMoveKey.variationsDeep).append(this.boardHashCode, otherCallbackMoveKey.boardHashCode).isEquals();
      }
   }

   public int hashCode() {
      return this.number * 11 ^ this.variationsDeep * 7 ^ this.boardHashCode * 23 ^ this.moveString.hashCode() ^ (this.isBlackMove?0:17);
   }

   public VelocityMoveNumberKey getMoveNumberKey() {
      return new VelocityMoveNumberKey(this.number, this.isBlackMove);
   }

   public String getMoveString() {
      return this.moveString;
   }

   public void setMoveString(String moveString) {
      this.moveString = moveString;
   }
}
