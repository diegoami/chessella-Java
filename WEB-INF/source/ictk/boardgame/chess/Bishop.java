package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;
import java.util.Arrays;
import java.util.List;

public class Bishop extends ChessPiece {

   public static final byte INDEX = 3;
   protected static final int MAX_LEGAL_DESTS = 13;
   protected static final int MAX_GUARDS = 13;


   public Bishop() {
      super((byte)3, true, 13, 13);
   }

   public Bishop(boolean blackness) {
      super((byte)3, blackness, 13, 13);
   }

   public Bishop(boolean blackness, Square o, ChessBoard _board) {
      super((byte)3, blackness, o, _board, 13, 13);
   }

   protected String getName() {
      return "Bishop";
   }

   protected int genLegalDests() {
      super.genLegalDests();
      if(this.captured) {
         return 0;
      } else {
         boolean done = false;
         int f = this.orig.file + 1;

         Square dest;
         int r;
         for(r = this.orig.rank + 1; f <= 8 && r <= 8 && !done; ++r) {
            dest = this.board.getSquare(f, r);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
            ++f;
         }

         done = false;
         f = this.orig.file + 1;

         for(r = this.orig.rank - 1; f <= 8 && r > 0 && !done; --r) {
            dest = this.board.getSquare(f, r);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
            ++f;
         }

         done = false;
         f = this.orig.file - 1;

         for(r = this.orig.rank - 1; f > 0 && r > 0 && !done; --r) {
            dest = this.board.getSquare(f, r);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
            --f;
         }

         done = false;
         f = this.orig.file - 1;

         for(r = this.orig.rank + 1; f > 0 && r <= 8 && !done; ++r) {
            dest = this.board.getSquare(f, r);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
            --f;
         }

         return this.legalDests.size();
      }
   }

   public void adjustPinsLegalDests(ChessPiece king, List enemyTeam) {
      Square[] line = this.getLineOfSight(king, false);
      ChessPiece pin = null;
      boolean done = false;
      if(!this.captured) {
         if(line != null) {
            if(this.board.staleLegalDests) {
               this.board.genLegalDests();
            }

            for(int maintainPins = 1; maintainPins < line.length && !done; ++maintainPins) {
               ChessPiece tmp = line[maintainPins].getOccupant();
               if(tmp != null) {
                  if(pin != null) {
                     pin = null;
                     done = true;
                  } else if(tmp.isBlack == this.isBlack()) {
                     done = true;
                  } else {
                     pin = tmp;
                  }
               }
            }

            if(pin != null) {
               List var8 = Arrays.asList(line);
               pin.setPinned(this, var8);
            }
         }

      }
   }

   public Square[] getLineOfSight(ChessPiece target, boolean inclusive) {
      return this.getLineOfSight(target.getSquare().file, target.getSquare().rank, inclusive);
   }

   public Square[] getLineOfSight(Square target, boolean inclusive) {
      return this.getLineOfSight(target.file, target.rank, inclusive);
   }

   public Square[] getLineOfSight(int t_file, int t_rank, boolean inclusive) {
      Square[] return_set = (Square[])null;
      Square[] return_tmp = new Square[7];
      byte o_file = this.getSquare().file;
      byte o_rank = this.getSquare().rank;
      boolean f = false;
      boolean r = false;
      int i = 0;
      int var11;
      int var12;
      if(o_file - t_file == o_rank - t_rank) {
         if(o_rank < t_rank) {
            var11 = o_file + 1;

            for(var12 = o_rank + 1; var12 <= t_rank; ++var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, var12);
               }

               ++var11;
            }
         } else {
            var11 = o_file - 1;

            for(var12 = o_rank - 1; var12 >= t_rank; --var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, var12);
               }

               --var11;
            }
         }
      } else if(o_file - t_file == (o_rank - t_rank) * -1) {
         if(o_rank - t_rank < 0) {
            var11 = o_file - 1;

            for(var12 = o_rank + 1; var12 <= t_rank; ++var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, var12);
               }

               --var11;
            }
         } else {
            var11 = o_file + 1;

            for(var12 = o_rank - 1; var12 >= t_rank; --var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, var12);
               }

               ++var11;
            }
         }
      }

      if(i != 0) {
         return_set = new Square[i + 1];
         return_set[0] = this.getSquare();
         System.arraycopy(return_tmp, 0, return_set, 1, i);
      }

      return return_set;
   }

   public boolean isBishop() {
      return true;
   }
}
