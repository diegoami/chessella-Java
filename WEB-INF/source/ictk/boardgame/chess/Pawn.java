package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;
import java.util.ArrayList;
import java.util.Iterator;

public class Pawn extends ChessPiece {

   public static final byte INDEX = 5;
   protected static final int MAX_LEGAL_DESTS = 4;
   protected static final int MAX_GUARDS = 2;


   public Pawn() {
      super((byte)5, true, 4, 2);
   }

   public Pawn(boolean blackness) {
      super((byte)5, blackness, 4, 2);
   }

   public Pawn(boolean blackness, Square o, ChessBoard _board) {
      super((byte)5, blackness, o, _board, 4, 2);
   }

   protected String getName() {
      return "Pawn";
   }

   protected int genLegalDests() {
      super.genLegalDests();
      Square dest = null;
      if(this.captured) {
         return 0;
      } else {
         int dir = this.isBlack?-1:1;
         if(this.board.isRankValid((byte)(this.orig.rank + 1 * dir))) {
            dest = this.board.getSquare((int)this.orig.file, (int)((byte)(this.orig.rank + 1 * dir)));
            if(!dest.isOccupied()) {
               this.addLegalDest(dest);
            }
         }

         byte file;
         byte rank;
         if(this.board.isRankValid(rank = (byte)(this.orig.rank + 1 * dir))) {
            file = this.orig.file;
            if(this.board.isFileValid((byte)(file + 1)) && (this.board.getSquare((int)((byte)(file + 1)), (int)rank).isOccupied() && this.isBlack != this.board.getSquare((int)((byte)(file + 1)), (int)rank).getOccupant().isBlack() || this.board.isBlackMove != this.isBlack)) {
               this.addLegalDest(this.board.getSquare((int)((byte)(file + 1)), (int)rank));
            }

            if(this.board.isFileValid((byte)(file - 1)) && (this.board.getSquare((int)((byte)(file - 1)), (int)rank).isOccupied() && this.isBlack != this.board.getSquare((int)((byte)(file - 1)), (int)rank).getOccupant().isBlack() || this.board.isBlackMove != this.isBlack)) {
               this.addLegalDest(this.board.getSquare((int)((byte)(file - 1)), (int)rank));
            }
         }

         if(!this.hasMoved() && this.board.isRankValid((byte)(this.orig.rank + 2 * dir))) {
            dest = this.board.getSquare((int)this.orig.file, (int)((byte)(this.orig.rank + 2 * dir)));
            if(!dest.isOccupied() && !this.board.getSquare((int)this.orig.file, (int)((byte)(this.orig.rank + 1 * dir))).isOccupied()) {
               this.addLegalDest(dest);
            }
         }

         if(this.isBlack == this.board.isBlackMove && this.onEnPassantRank()) {
            file = this.orig.file;
            if(this.board.isFileValid((byte)(file + 1)) && this.board.isEnPassantFile((int)((byte)(file + 1)))) {
               this.addLegalDest(this.board.getSquare((int)((byte)(file + 1)), (int)rank));
            }

            if(this.board.isFileValid((byte)(file - 1)) && this.board.isEnPassantFile((int)((byte)(file - 1)))) {
               this.addLegalDest(this.board.getSquare((int)((byte)(file - 1)), (int)rank));
            }
         }

         return this.legalDests.size();
      }
   }

   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat) {
      Iterator oldLegals = this.legalDests.iterator();
      Square sq = null;
      if(!this.captured) {
         this.legalDests = new ArrayList(2);

         while(oldLegals.hasNext()) {
            sq = (Square)oldLegals.next();
            if(threat.isBlockable(sq, king)) {
               this.legalDests.add(sq);
            } else if(sq.equals(threat.getSquare())) {
               this.legalDests.add(sq);
            } else if(threat.isPawn() && threat.getSquare().getFile() == this.board.getEnPassantFile() && sq.getFile() == this.board.getEnPassantFile()) {
               this.legalDests.add(sq);
            }
         }

      }
   }

   public boolean onEnPassantRank() {
      return !this.isBlack && this.orig.rank == 5 || this.isBlack && this.orig.rank == 4;
   }

   public boolean isBlockable(Square target) {
      return false;
   }

   public boolean isBlockable(Square blocker, ChessPiece target) {
      return false;
   }

   public boolean isLegalAttack(Square target) {
      if(this.board.staleLegalDests) {
         this.board.genLegalDests();
      }

      return target.file == this.orig.file?false:this.isLegalDest(target);
   }

   public boolean hasMoved() {
      return (!this.isBlack || this.orig.rank != 7) && (this.isBlack || this.orig.rank != 2);
   }

   public static boolean isPromotionSquare(Square sq, boolean isBlack) {
      return isBlack && sq.rank == 1?true:!isBlack && sq.rank == 8;
   }

   public boolean isPawn() {
      return true;
   }
}
