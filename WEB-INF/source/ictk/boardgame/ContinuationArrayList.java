package ictk.boardgame;

import ictk.boardgame.ContinuationList;
import ictk.boardgame.Move;
import java.util.Arrays;

public class ContinuationArrayList implements ContinuationList {

   protected Move departureMove;
   protected Move[] branches;


   public ContinuationList subList(int sizearg) {
      ContinuationArrayList list = new ContinuationArrayList();
      int n = Math.min(sizearg, this.size());
      list.branches = new Move[n];

      for(int i = 0; i < n; ++i) {
         list.branches[i] = this.branches[i];
      }

      if(n < this.size()) {
         list.departureMove = this.branches[n];
      } else {
         list.departureMove = this.departureMove;
      }

      return list;
   }

   public boolean equals(Object o) {
      if(o == this) {
         return true;
      } else if(o != null && o.getClass() == this.getClass()) {
         ContinuationArrayList otherList = (ContinuationArrayList)o;
         if(this.branches == null && otherList.branches != null) {
            return false;
         } else if(otherList.branches == null && this.branches != null) {
            return false;
         } else if(this.departureMove == null && otherList.departureMove != null) {
            return false;
         } else if(otherList.departureMove == null && this.departureMove != null) {
            return false;
         } else {
            boolean branchesEqual = Arrays.equals(this.branches, otherList.branches);
            boolean departureEqual = this.departureMove == null?otherList.departureMove == null:this.departureMove.equals(otherList.departureMove);
            return branchesEqual && departureEqual;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int hash1 = 31 * hash + (this.branches == null?0:this.branches.hashCode());
      hash1 = 31 * hash1 + (this.departureMove == null?0:this.departureMove.hashCode());
      return hash1;
   }

   public ContinuationArrayList(Move m) {
      this.branches = new Move[1];
      this.setDepartureMove(m);
   }

   public ContinuationArrayList() {
      this((Move)null);
   }

   protected void setDepartureMove(Move m) {
      this.departureMove = m;
   }

   public Move getDepartureMove() {
      return this.departureMove;
   }

   public boolean isTerminal() {
      return this.getMainLine() == null && this.sizeOfVariations() == 0;
   }

   public boolean setMainLineTerminal() {
      if(this.branches != null && this.branches[0] != null) {
         this.add((Move)null, true);
         return true;
      } else {
         return false;
      }
   }

   public boolean exists(int variation) {
      try {
         return this.branches != null && this.branches[variation] != null;
      } catch (ArrayIndexOutOfBoundsException var3) {
         return false;
      }
   }

   public boolean exists(Move move) {
      return this.getIndex(move) != -1;
   }

   public boolean hasMainLine() {
      return this.exists(0);
   }

   public Move getMainLine() {
      return this.branches == null?null:this.branches[0];
   }

   public boolean hasVariations() {
      return this.sizeOfVariations() > 0;
   }

   public Move get(int i) {
      return i == 0 && this.branches == null?null:this.branches[i];
   }

   public int size() {
      boolean len = false;
      if(this.branches == null) {
         return 0;
      } else {
         int len1 = this.branches.length;
         if(len1 == 1 && this.branches[0] == null) {
            len1 = 0;
         }

         return len1;
      }
   }

   public int sizeOfVariations() {
      return this.branches == null?0:this.branches.length - 1;
   }

   public void add(Move m, boolean isMain) {
      Move[] tmp = (Move[])null;

      assert this.departureMove == null || this.departureMove.getHistory().getCurrentMove() == this.departureMove : "You cannot add a continuation to moves other than the last move played on the board (History.getCurrentMove())";

      if(this.branches == null) {
         if(isMain) {
            this.branches = new Move[1];
            this.branches[0] = m;
         } else {
            this.branches = new Move[2];
            this.branches[1] = m;
         }
      } else if(isMain && this.branches[0] == null) {
         this.branches[0] = m;
      } else {
         tmp = new Move[this.branches.length + 1];
         if(isMain) {
            tmp[0] = m;
            System.arraycopy(this.branches, 0, tmp, 1, this.branches.length);
         } else {
            System.arraycopy(this.branches, 0, tmp, 0, this.branches.length);
            tmp[tmp.length - 1] = m;
         }

         this.branches = tmp;
      }

      if(m != null) {
         m.prev = this.departureMove;
      }

   }

   public void add(Move m) {
      Move[] tmp = (Move[])null;

      assert this.departureMove.getHistory().getCurrentMove() == this.departureMove : "You cannot add a continuation to moves other than the last move played on the board (History.getCurrentMove())";

      if(this.branches == null) {
         this.branches = new Move[1];
         this.branches[0] = m;
      } else if(this.branches.length == 1 && this.branches[0] == null) {
         this.branches[0] = m;
      } else {
         tmp = new Move[this.branches.length + 1];
         System.arraycopy(this.branches, 0, tmp, 0, this.branches.length);
         tmp[tmp.length - 1] = m;
      }

      m.prev = this.departureMove;
   }

   public int getIndex(Move m) {
      boolean i = false;
      if(this.branches == null) {
         return -1;
      } else {
         int var3;
         for(var3 = 0; var3 < this.branches.length && this.branches[var3] != m; ++var3) {
            ;
         }

         return var3 < this.branches.length?var3:-1;
      }
   }

   public Move[] find(Move m) {
      Move[] moves = (Move[])null;
      int[] idx = this.findIndex(m);
      if(idx != null) {
         moves = new Move[idx.length];

         for(int i = 0; i < idx.length; ++i) {
            moves[i] = this.branches[idx[i]];
         }
      }

      return moves;
   }

   public int[] findIndex(Move m) {
      boolean i = false;
      int count = 0;
      int[] rvalue = (int[])null;
      int[] tmp = (int[])null;
      if(this.branches == null) {
         return null;
      } else {
         tmp = new int[this.branches.length];

         for(int var6 = 0; var6 < this.branches.length; ++var6) {
            if(this.branches[var6] != null && this.branches[var6].equals(m)) {
               tmp[count++] = var6;
            }
         }

         if(count == 0) {
            return null;
         } else if(count == this.branches.length) {
            return tmp;
         } else {
            rvalue = new int[count];
            System.arraycopy(tmp, 0, rvalue, 0, rvalue.length);
            return rvalue;
         }
      }
   }

   protected boolean compressVariations() {
      boolean compressed = false;
      Move[] compBranches = (Move[])null;
      int count = 0;

      int i;
      for(i = 1; i < this.branches.length; ++i) {
         if(this.branches[i] != null) {
            ++count;
         } else {
            compressed = true;
         }
      }

      if(compressed && count > 0) {
         compBranches = new Move[count + 1];
         count = 0;

         for(i = 0; i < this.branches.length; ++i) {
            if(i < 1 || this.branches[i] != null) {
               compBranches[count++] = this.branches[i];
            }
         }

         this.branches = compBranches;
      }

      return compressed && count > 0;
   }

   public void remove(int i) {
      if(this.branches == null) {
         throw new ArrayIndexOutOfBoundsException("no moves to remove");
      } else if(i == -1) {
         throw new ArrayIndexOutOfBoundsException("move does not exist in continuation list");
      } else {
         if(this.branches[i] != null) {
            if(this.branches[i].isExecuted()) {
               this.departureMove.getHistory().goTo(this.departureMove);
            }

            this.branches[i].dispose();
            this.branches[i] = null;
            this.compressVariations();
         }

      }
   }

   public void remove(Move m) {
      this.remove(this.getIndex(m));
   }

   public void removeAll() {
      if(this.branches != null) {
         for(int i = 0; i < this.branches.length; ++i) {
            if(this.branches[i].isExecuted()) {
               this.departureMove.getHistory().goTo(this.departureMove);
            }

            this.branches[i].dispose();
            this.branches[i] = null;
         }
      }

      this.branches = null;
   }

   public void removeAllVariations() {
      if(this.branches != null && this.sizeOfVariations() > 0) {
         for(int i = 1; i < this.branches.length; ++i) {
            if(this.branches[i] != null) {
               if(this.branches[i].isExecuted()) {
                  this.departureMove.getHistory().goTo(this.departureMove);
               }

               this.branches[i].dispose();
               this.branches[i] = null;
            }
         }

         this.compressVariations();
      }

   }

   public void dispose() {
      this.departureMove = null;
      this.removeAll();
      this.branches = null;
   }

   public int promote(Move move, int num) {
      if(num < 0) {
         throw new IllegalArgumentException("cannot perform negative promotion");
      } else {
         Move tmp = null;
         int newIndex = 0;
         int oldIndex = this.getIndex(move);
         if(oldIndex == -1) {
            throw new NullPointerException("Move is not a current variation");
         } else {
            move = this.branches[oldIndex];
            if(num != 0) {
               newIndex = oldIndex - num;
            }

            if(newIndex < 0) {
               throw new ArrayIndexOutOfBoundsException("Move cannot be promoted beyond the main line");
            } else {
               if(newIndex == 0 && this.branches[0] == null) {
                  this.branches[oldIndex] = null;
                  this.branches[0] = move;
                  this.compressVariations();
               } else {
                  this.branches[oldIndex] = null;

                  for(int i = newIndex; i <= oldIndex; ++i) {
                     tmp = this.branches[i];
                     this.branches[i] = move;
                     move = tmp;
                  }

                  this.compressVariations();
               }

               return newIndex;
            }
         }
      }
   }

   public int demote(Move move, int num) {
      if(num < 0) {
         throw new IllegalArgumentException("cannot perform negative demotion");
      } else {
         Object tmp = null;
         boolean newIndex = false;
         int oldIndex = this.getIndex(move);
         if(oldIndex == -1) {
            throw new NullPointerException("Move is not a current variation");
         } else {
            move = this.branches[oldIndex];
            int var7;
            if(num != 0) {
               var7 = oldIndex + num;
            } else {
               var7 = this.branches.length - 1;
            }

            if(var7 >= this.branches.length) {
               throw new ArrayIndexOutOfBoundsException("Move cannot be demoted beyond the end of the list");
            } else {
               for(int i = oldIndex; i < var7; ++i) {
                  this.branches[i] = this.branches[i + 1];
               }

               this.branches[var7] = move;
               return var7;
            }
         }
      }
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("branches(").append(this.branches.length).append("): \n");

      for(int i = 0; i < this.branches.length; ++i) {
         sb.append("   branches[").append(i).append("]:");
         if(this.branches[i] == null) {
            sb.append("null");
         } else {
            sb.append(this.branches[i].toString());
         }

         sb.append("\n");
      }

      return sb.toString();
   }
}
