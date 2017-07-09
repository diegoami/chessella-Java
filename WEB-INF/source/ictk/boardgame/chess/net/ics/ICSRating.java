package ictk.boardgame.chess.net.ics;


public class ICSRating {

   int rating;
   boolean isProvisional;
   boolean isEstimated;
   boolean isNotApplicable;
   boolean isNotSet;


   public ICSRating() {}

   public ICSRating(String s) throws NumberFormatException {
      this.set(s);
   }

   public void set(int rate) {
      this.rating = rate;
   }

   public int get() {
      return this.rating;
   }

   public boolean isNotSet() {
      return this.isNotSet;
   }

   public boolean isNotApplicable() {
      return this.isNotApplicable;
   }

   public boolean isProvisional() {
      return this.isProvisional;
   }

   public boolean isEstimated() {
      return this.isEstimated;
   }

   public void setNotApplicable(boolean t) {
      this.isNotApplicable = t;
   }

   public void setProvisional(boolean t) {
      this.isProvisional = t;
   }

   public void setEstimated(boolean t) {
      this.isEstimated = t;
   }

   public void set(String s) throws NumberFormatException {
      if(s.equals("UNR")) {
         this.isNotSet = true;
      } else if(s.charAt(0) == 45) {
         this.isNotSet = true;
      } else if(s.charAt(0) == 43) {
         this.isNotApplicable = true;
      } else if(!Character.isDigit(s.charAt(s.length() - 1))) {
         this.rating = Integer.parseInt(s.substring(0, s.length() - 1));
         if(s.charAt(s.length() - 1) == 80) {
            this.isProvisional = true;
         } else if(s.charAt(s.length() - 1) == 69) {
            this.isEstimated = true;
         }
      } else {
         this.rating = Integer.parseInt(s);
      }

   }

   public String toString() {
      String s = null;
      if(this.isNotSet) {
         s = "----";
      } else if(this.isNotApplicable) {
         s = "++++";
      } else if(this.isProvisional) {
         s = this.rating + "P";
      } else if(this.isEstimated) {
         s = this.rating + "E";
      } else {
         s = "" + this.rating;
      }

      return s;
   }
}
