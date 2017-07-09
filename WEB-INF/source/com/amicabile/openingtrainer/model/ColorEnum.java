package com.amicabile.openingtrainer.model;


public enum ColorEnum {

   WHITE("WHITE", 0, "w"),
   BLACK("BLACK", 1, "b"),
   NONE("NONE", 2, "");
   private String charRepr;
   // $FF: synthetic field
   private static final ColorEnum[] ENUM$VALUES = new ColorEnum[]{WHITE, BLACK, NONE};


   private ColorEnum(String var1, int var2, String charRepr) {
      this.charRepr = charRepr;
   }

   public String getCharRepr() {
      return this.charRepr;
   }

   public String toString() {
      return this.charRepr;
   }

   public ColorEnum getOtherColor() {
      return this.equals(WHITE)?BLACK:WHITE;
   }
}
