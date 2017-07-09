package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;
import java.util.Arrays;
import java.util.List;

public class Rook extends ChessPiece {

   public static final byte INDEX = 2;
   protected static final int MAX_LEGAL_DESTS = 14;
   protected static final int MAX_GUARDS = 14;


   public Rook() {
      super((byte)2, true, 14, 14);
   }

   public Rook(boolean blackness) {
      super((byte)2, blackness, 14, 14);
   }

   public Rook(boolean blackness, Square o, ChessBoard _board) {
      super((byte)2, blackness, o, _board, 14, 14);
   }

   protected String getName() {
      return "Rook";
   }

   protected int genLegalDests() {
      super.genLegalDests();
      if(this.captured) {
         return 0;
      } else {
         boolean done = false;

         Square dest;
         int f;
         for(f = this.orig.rank + 1; f <= 8 && !done; ++f) {
            dest = this.board.getSquare((int)this.orig.file, f);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
         }

         done = false;

         for(f = this.orig.file + 1; f <= 8 && !done; ++f) {
            dest = this.board.getSquare(f, (int)this.orig.rank);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
         }

         done = false;

         for(f = this.orig.rank - 1; f >= 1 && !done; --f) {
            dest = this.board.getSquare((int)this.orig.file, f);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
         }

         done = false;

         for(f = this.orig.file - 1; f >= 1 && !done; --f) {
            dest = this.board.getSquare(f, (int)this.orig.rank);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
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
      if(o_file == t_file) {
         int var12;
         if(o_rank < t_rank) {
            for(var12 = o_rank + 1; var12 <= t_rank; ++var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare((int)o_file, var12);
               }
            }
         } else {
            for(var12 = o_rank - 1; var12 >= t_rank; --var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare((int)o_file, var12);
               }
            }
         }
      } else if(o_rank == t_rank) {
         int var11;
         if(o_file < t_file) {
            for(var11 = o_file + 1; var11 <= t_file; ++var11) {
               if(var11 != t_file || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, (int)o_rank);
               }
            }
         } else {
            for(var11 = o_file - 1; var11 >= t_file; --var11) {
               if(var11 != t_file || inclusive) {
                  return_tmp[i++] = this.board.getSquare(var11, (int)o_rank);
               }
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

   public boolean isRook() {
      return true;
   }
}
