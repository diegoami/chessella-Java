/*     */ package ictk.boardgame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Player
/*     */ {
/*     */   protected String firstname;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String lastname;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Player() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Player(String n)
/*     */   {
/*  46 */     int i = -1;
/*  47 */     if ((i = n.indexOf(',')) != -1) {
/*  48 */       this.lastname = n.substring(0, i);
/*  49 */       if (i + 2 < n.length()) {
/*  50 */         this.firstname = n.substring(i + 2, n.length());
/*     */       }
/*     */     } else {
/*  53 */       this.lastname = n;
/*     */     } }
/*     */   
/*  56 */   public String getLastName() { return this.lastname; }
/*  57 */   public String getFirstName() { return this.firstname; }
/*     */   
/*  59 */   public void setLastName(String l) { this.lastname = l; }
/*  60 */   public void setFirstName(String f) { this.firstname = f; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  67 */     String name = null;
/*     */     
/*  69 */     if (this.lastname != null) {
/*  70 */       name = this.lastname;
/*  71 */       if (this.firstname != null) {
/*  72 */         name = name + ", " + this.firstname;
/*     */       }
/*     */     }
/*  75 */     else if (this.firstname != null) {
/*  76 */       name = this.firstname;
/*     */     }
/*     */     
/*  79 */     return name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  86 */     String str = getName();
/*  87 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  94 */     if (o == this) return true;
/*  95 */     if ((o == null) || (o.getClass() != getClass())) {
/*  96 */       return false;
/*     */     }
/*  98 */     Player p = (Player)o;
/*  99 */     boolean t = true;
/*     */     
/* 101 */     t = (this.lastname == p.lastname) || (
/* 102 */       (this.lastname != null) && (this.lastname.equals(p.lastname)));
/* 103 */     t = (t) && ((this.firstname == p.firstname) || (
/* 104 */       (this.firstname != null) && (this.firstname.equals(p.firstname))));
/*     */     
/* 106 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 113 */     int hash = 7;
/*     */     
/* 115 */     hash = 31 * hash + (this.lastname == null ? 0 : this.lastname.hashCode());
/* 116 */     hash = 31 * hash + (this.firstname == null ? 0 : this.firstname.hashCode());
/*     */     
/* 118 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\Player.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */