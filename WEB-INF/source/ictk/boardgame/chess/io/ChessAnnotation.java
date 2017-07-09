package ictk.boardgame.chess.io;

import ictk.boardgame.chess.io.NAG;
import ictk.boardgame.io.Annotation;

public class ChessAnnotation implements Annotation {

   protected String comment;
   protected short[] nags;


   public ChessAnnotation() {}

   public ChessAnnotation(String comment) {
      this.comment = comment;
   }

   public short getSuffix() {
      return this.nags != null && this.nags.length > 0 && this.nags[0] > 0 && NAG.isSuffix(this.nags[0])?this.nags[0]:0;
   }

   public void addNAG(int nag) {
      int size = 0;
      short[] newNag = (short[])null;
      if(this.nags != null) {
         size = this.nags.length;
      }

      newNag = new short[size + 1];
      if(this.nags != null) {
         System.arraycopy(this.nags, 0, newNag, 0, this.nags.length);
      }

      newNag[newNag.length - 1] = (short)nag;
      this.nags = newNag;
   }

   public void setNAG(int i, int nag) {
      if(this.nags != null && this.nags.length < i) {
         this.nags[i] = (short)nag;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public short getNAG(int i) {
      if(this.nags == null) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         return this.nags[i];
      }
   }

   public short[] getNAGs() {
      return this.nags;
   }

   public String getNAGString(boolean allNumeric) {
      if(this.nags == null) {
         return null;
      } else {
         StringBuffer sb = new StringBuffer();
         String suff = null;
         int count = 0;

         for(int i = 0; i < this.nags.length; ++i) {
            suff = NAG.numberToString(this.nags[i], allNumeric);
            if(suff != null) {
               if(count++ > 0) {
                  sb.append(" ");
               }

               sb.append(suff);
            }
         }

         return sb.toString();
      }
   }

   public String getNAGString() {
      return this.getNAGString(false);
   }

   public void removeNAG(int i) {
      short[] newNag = (short[])null;
      if(this.nags != null && i < this.nags.length) {
         if(this.nags.length == 1) {
            this.nags = null;
         } else {
            this.nags[i] = 0;
            newNag = new short[this.nags.length - 1];
            if(i == 0) {
               System.arraycopy(this.nags, 1, newNag, 0, newNag.length);
            } else {
               System.arraycopy(this.nags, 0, newNag, 0, i);
               System.arraycopy(this.nags, i + 1, newNag, i, newNag.length - i);
            }

            this.nags = newNag;
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public void setComment(String com) {
      this.comment = com;
   }

   public void appendComment(String com) {
      if(this.comment != null) {
         this.comment = this.comment + com;
      } else {
         this.comment = com;
      }

   }

   public String getComment() {
      return this.comment;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj != null && obj.getClass() == this.getClass()) {
         ChessAnnotation anno = (ChessAnnotation)obj;
         boolean t = true;
         if(this.nags == null) {
            if(this.nags == anno.nags) {
               t = true;
            } else {
               t = false;
            }
         } else if(anno.nags != null) {
            t = t && this.nags.length == anno.nags.length;

            int i;
            for(i = 0; i < this.nags.length && t; ++i) {
               t = this.nags[i] == anno.nags[i];
            }

            if(i != this.nags.length) {
               t = false;
            }
         }

         if(t && this.comment == null) {
            if(this.comment != anno.comment) {
               t = false;
            }
         } else if(t && anno.comment != null) {
            t = this.comment.equals(anno.comment);
         }

         return t;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int var3 = 31 * hash + (this.comment == null?0:this.comment.hashCode());
      if(this.nags != null) {
         for(int i = 0; i < this.nags.length; ++i) {
            var3 = 31 * var3 + this.nags[i];
         }
      }

      return var3;
   }

   public String toString() {
      return this.dump();
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("comment: ").append(this.comment).append(" nags: ");
      if(this.nags != null) {
         for(int i = 0; i < this.nags.length; ++i) {
            sb.append(this.nags[i]).append(" ");
         }
      }

      return sb.toString();
   }
}
