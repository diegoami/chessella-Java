/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.io.Annotation;
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
/*     */ public class ChessAnnotation
/*     */   implements Annotation
/*     */ {
/*     */   protected String comment;
/*     */   protected short[] nags;
/*     */   
/*     */   public ChessAnnotation() {}
/*     */   
/*     */   public ChessAnnotation(String comment)
/*     */   {
/*  44 */     this.comment = comment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short getSuffix()
/*     */   {
/*  53 */     if ((this.nags != null) && 
/*  54 */       (this.nags.length > 0) && (this.nags[0] > 0) && (NAG.isSuffix(this.nags[0]))) {
/*  55 */       return this.nags[0];
/*     */     }
/*  57 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addNAG(int nag)
/*     */   {
/*  65 */     int size = 0;
/*  66 */     short[] newNag = (short[])null;
/*     */     
/*  68 */     if (this.nags != null) {
/*  69 */       size = this.nags.length;
/*     */     }
/*  71 */     newNag = new short[size + 1];
/*     */     
/*  73 */     if (this.nags != null) {
/*  74 */       System.arraycopy(this.nags, 0, newNag, 0, this.nags.length);
/*     */     }
/*  76 */     newNag[(newNag.length - 1)] = ((short)nag);
/*  77 */     this.nags = newNag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNAG(int i, int nag)
/*     */   {
/*  85 */     if ((this.nags == null) || (this.nags.length >= i)) {
/*  86 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/*  88 */     this.nags[i] = ((short)nag);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short getNAG(int i)
/*     */   {
/*  97 */     if (this.nags == null)
/*  98 */       throw new ArrayIndexOutOfBoundsException();
/*  99 */     return this.nags[i];
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short[] getNAGs()
/*     */   {
/* 131 */     return this.nags;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNAGString(boolean allNumeric)
/*     */   {
/* 144 */     if (this.nags == null) { return null;
/*     */     }
/* 146 */     StringBuffer sb = new StringBuffer();
/* 147 */     String suff = null;
/* 148 */     int count = 0;
/*     */     
/* 150 */     for (int i = 0; i < this.nags.length; i++) {
/* 151 */       suff = NAG.numberToString(this.nags[i], allNumeric);
/*     */       
/* 153 */       if (suff != null) {
/* 154 */         if (count++ > 0)
/* 155 */           sb.append(" ");
/* 156 */         sb.append(suff);
/*     */       }
/*     */     }
/* 159 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNAGString()
/*     */   {
/* 167 */     return getNAGString(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeNAG(int i)
/*     */   {
/* 175 */     short[] newNag = (short[])null;
/*     */     
/* 177 */     if ((this.nags == null) || (i >= this.nags.length)) {
/* 178 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/* 180 */     if (this.nags.length == 1) {
/* 181 */       this.nags = null;
/* 182 */       return;
/*     */     }
/*     */     
/* 185 */     this.nags[i] = 0;
/* 186 */     newNag = new short[this.nags.length - 1];
/* 187 */     if (i == 0) {
/* 188 */       System.arraycopy(this.nags, 1, newNag, 0, newNag.length);
/*     */     }
/*     */     else
/*     */     {
/* 192 */       System.arraycopy(this.nags, 0, newNag, 0, i);
/*     */       
/* 194 */       System.arraycopy(this.nags, i + 1, newNag, i, newNag.length - i);
/*     */     }
/* 196 */     this.nags = newNag;
/*     */   }
/*     */   
/*     */ 
/* 200 */   public void setComment(String com) { this.comment = com; }
/*     */   
/* 202 */   public void appendComment(String com) { if (this.comment != null) this.comment += com; else
/* 203 */       this.comment = com; }
/*     */   
/* 205 */   public String getComment() { return this.comment; }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 211 */     if (this == obj) return true;
/* 212 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 213 */       return false;
/*     */     }
/* 215 */     ChessAnnotation anno = (ChessAnnotation)obj;
/* 216 */     boolean t = true;
/*     */     
/*     */ 
/* 219 */     if (this.nags == null) {
/* 220 */       if (this.nags == anno.nags) {
/* 221 */         t = true;
/*     */       } else
/* 223 */         t = false;
/* 224 */     } else if (anno.nags != null) {
/* 225 */       t = (t) && (this.nags.length == anno.nags.length);
/* 226 */       int i = 0;
/* 227 */       while ((i < this.nags.length) && (t)) {
/* 228 */         t = this.nags[i] == anno.nags[i];
/* 229 */         i++;
/*     */       }
/* 231 */       if (i != this.nags.length) {
/* 232 */         t = false;
/*     */       }
/*     */     }
/*     */     
/* 236 */     if ((t) && (this.comment == null)) {
/* 237 */       if (this.comment != anno.comment) {
/* 238 */         t = false;
/*     */       }
/* 240 */     } else if ((t) && (anno.comment != null)) {
/* 241 */       t = this.comment.equals(anno.comment);
/*     */     }
/*     */     
/* 244 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 251 */     int hash = 7;
/*     */     
/* 253 */     hash = 31 * hash + (this.comment == null ? 0 : this.comment.hashCode());
/* 254 */     if (this.nags != null) {
/* 255 */       for (int i = 0; i < this.nags.length; i++)
/* 256 */         hash = 31 * hash + this.nags[i];
/*     */     }
/* 258 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 265 */     return dump();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String dump()
/*     */   {
/* 272 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 274 */     sb.append("comment: ")
/* 275 */       .append(this.comment)
/* 276 */       .append(" nags: ");
/* 277 */     if (this.nags != null) {
/* 278 */       for (int i = 0; i < this.nags.length; i++)
/* 279 */         sb.append(this.nags[i]).append(" ");
/*     */     }
/* 281 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\ChessAnnotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */