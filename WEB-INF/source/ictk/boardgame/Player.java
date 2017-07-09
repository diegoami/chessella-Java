package ictk.boardgame;


public class Player {

   protected String firstname;
   protected String lastname;


   public Player() {}

   public Player(String n) {
      boolean i = true;
      int i1;
      if((i1 = n.indexOf(44)) != -1) {
         this.lastname = n.substring(0, i1);
         if(i1 + 2 < n.length()) {
            this.firstname = n.substring(i1 + 2, n.length());
         }
      } else {
         this.lastname = n;
      }

   }

   public String getLastName() {
      return this.lastname;
   }

   public String getFirstName() {
      return this.firstname;
   }

   public void setLastName(String l) {
      this.lastname = l;
   }

   public void setFirstName(String f) {
      this.firstname = f;
   }

   public String getName() {
      String name = null;
      if(this.lastname != null) {
         name = this.lastname;
         if(this.firstname != null) {
            name = name + ", " + this.firstname;
         }
      } else if(this.firstname != null) {
         name = this.firstname;
      }

      return name;
   }

   public String toString() {
      String str = this.getName();
      return str;
   }

   public boolean equals(Object o) {
      if(o == this) {
         return true;
      } else if(o != null && o.getClass() == this.getClass()) {
         Player p = (Player)o;
         boolean t = true;
         t = this.lastname == p.lastname || this.lastname != null && this.lastname.equals(p.lastname);
         t = t && (this.firstname == p.firstname || this.firstname != null && this.firstname.equals(p.firstname));
         return t;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int hash1 = 31 * hash + (this.lastname == null?0:this.lastname.hashCode());
      hash1 = 31 * hash1 + (this.firstname == null?0:this.firstname.hashCode());
      return hash1;
   }
}
