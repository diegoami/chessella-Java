package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;
import java.util.Arrays;
import java.util.List;

public class Queen extends ChessPiece {

   public static final byte INDEX = 1;
   protected static final int MAX_LEGAL_DESTS = 27;
   protected static final int MAX_GUARDS = 27;


   public Queen() {
      super((byte)1, true, 27, 27);
   }

   public Queen(boolean blackness) {
      super((byte)1, blackness, 27, 27);
   }

   public Queen(boolean blackness, Square o, ChessBoard _board) {
      super((byte)1, blackness, o, _board, 27, 27);
   }

   protected String getName() {
      return "Queen";
   }

   protected int genLegalDests() {
      super.genLegalDests();
      boolean done = false;
      if(this.captured) {
         return 0;
      } else {
         Square dest;
         int f;
         for(f = this.orig.rank + 1; f <= 8 && !done; ++f) {
            dest = this.board.getSquare((int)this.orig.file, f);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
         }

         done = false;
         f = this.orig.file + 1;

         int r;
         for(r = this.orig.rank + 1; f <= 8 && r <= 8 && !done; ++r) {
            dest = this.board.getSquare(f, r);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
            ++f;
         }

         done = false;

         for(f = this.orig.file + 1; f <= 8 && !done; ++f) {
            dest = this.board.getSquare(f, (int)this.orig.rank);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
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

         for(f = this.orig.rank - 1; f > 0 && !done; --f) {
            dest = this.board.getSquare((int)this.orig.file, f);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
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

         for(f = this.orig.file - 1; f > 0 && !done; --f) {
            dest = this.board.getSquare(f, (int)this.orig.rank);
            done = !this.addLegalDest(dest);
            done = done || dest.isOccupied() && (this.board.isBlackMove == this.isBlack || !dest.piece.isKing());
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

   public boolean isQueen() {
      return true;
   }
}
