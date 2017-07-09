package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Rook;
import ictk.boardgame.chess.Square;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class King extends ChessPiece {

   public static final byte INDEX = 0;
   protected static final int MAX_LEGAL_DESTS = 8;
   protected static final int MAX_GUARDS = 8;


   public King() {
      super((byte)0, true, 8, 8);
   }

   public King(boolean blackness) {
      super((byte)0, blackness, 8, 8);
   }

   public King(boolean blackness, Square o, ChessBoard _board) {
      super((byte)0, blackness, o, _board, 8, 8);
   }

   protected String getName() {
      return "King";
   }

   protected int genLegalDests() {
      super.genLegalDests();

      for(byte f = (byte)(-1 + this.orig.file); f <= 1 + this.orig.file; ++f) {
         for(byte r = (byte)(-1 + this.orig.rank); r <= 1 + this.orig.rank; ++r) {
            if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
               this.addLegalDest(this.board.getSquare((int)f, (int)r));
            }
         }
      }

      return this.legalDests.size();
   }

   protected int genLegalDestsFinal() {
      boolean blocked = false;
      List tmpLegalDests = this.legalDests;
      Iterator perlimMoves = tmpLegalDests.iterator();
      this.legalDests = new ArrayList(8);

      while(perlimMoves.hasNext()) {
         Square dest = (Square)perlimMoves.next();
         if(!this.board.isThreatened(dest, !this.isBlack) && !this.board.isGuarded(dest, !this.isBlack)) {
            this.addLegalDest(dest);
         }
      }

      if(this.moveCount == 0) {
         ChessPiece rook = this.board.getSquare((int)1, (int)this.orig.rank).piece;
         byte f;
         if(rook != null && rook.moveCount == 0) {
            blocked = false;

            for(f = (byte)(rook.orig.file + 1); f <= this.orig.file && !blocked; ++f) {
               if(f < this.orig.file) {
                  blocked = this.board.getSquare((int)f, (int)this.orig.rank).isOccupied();
               }

               if(!blocked && f >= this.orig.file - 2) {
                  blocked = this.board.isThreatened(this.board.getSquare((int)f, (int)this.orig.rank), !this.isBlack);
               }
            }

            if(!blocked) {
               this.addLegalDest(this.getQueensideCastleSquare());
            }
         }

         rook = this.board.getSquare(this.board.getMaxFile(), (int)this.orig.rank).piece;
         if(rook != null && rook.moveCount == 0) {
            blocked = false;

            for(f = (byte)(rook.orig.file - 1); f >= this.orig.file && !blocked; --f) {
               if(f > this.orig.file) {
                  blocked = this.board.getSquare((int)f, (int)this.orig.rank).isOccupied();
               }

               if(!blocked) {
                  blocked = this.board.isThreatened(this.board.getSquare((int)f, (int)this.orig.rank), !this.isBlack);
               }
            }

            if(!blocked) {
               this.addLegalDest(this.getKingsideCastleSquare());
            }
         }
      }

      return this.legalDests.size();
   }

   public Square getQueensideCastleSquare() {
      return this.board.getSquare('c', (char)(this.isBlack?'8':'1'));
   }

   public Square getKingsideCastleSquare() {
      return this.board.getSquare('g', (char)(this.isBlack?'8':'1'));
   }

   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat) {}

   public boolean isInCheck() {
      return this.board.isThreatened(this);
   }

   public boolean isCastleableQueenside() {
      Rook rook = this.findMyRook(true);
      return this.moveCount == 0 && rook != null && rook.moveCount == 0;
   }

   public boolean isCastleableKingside() {
      Rook rook = this.findMyRook(false);
      return this.moveCount == 0 && rook != null && rook.moveCount == 0;
   }

   public void setCastleableQueenside(boolean t) {
      Rook rook = this.findMyRook(true);
      if(rook == null && t) {
         throw new IllegalStateException("can\'t set castleable when there\'s no rook on that side of the board.");
      } else {
         if(t) {
            this.moveCount = 0;
            rook.moveCount = 0;
         } else if(rook != null && rook.moveCount == 0) {
            rook.moveCount = 1;
         }

      }
   }

   public void setCastleableKingside(boolean t) {
      Rook rook = this.findMyRook(false);
      if(rook == null && t) {
         throw new IllegalStateException("can\'t set castleable when there\'s no rook on that side of the board.");
      } else {
         if(t) {
            this.moveCount = 0;
            rook.moveCount = 0;
         } else if(rook != null && rook.moveCount == 0) {
            rook.moveCount = 1;
         }

      }
   }

   protected Rook findMyRook(boolean qside) {
      boolean file = true;
      ChessPiece p = null;
      byte var4;
      if(qside) {
         for(var4 = 1; p == null && var4 < this.orig.file; ++var4) {
            p = this.board.getSquare((int)var4, (int)this.orig.rank).piece;
            if(p == null || !p.isRook() || p.isBlack() != this.isBlack()) {
               p = null;
            }
         }
      } else {
         for(var4 = 8; p == null && var4 > this.orig.file; --var4) {
            p = this.board.getSquare((int)var4, (int)this.orig.rank).piece;
            if(p == null || !p.isRook() || p.isBlack() != this.isBlack()) {
               p = null;
            }
         }
      }

      return (Rook)p;
   }

   public boolean isBlockable(Square target) {
      return false;
   }

   public boolean isBlockable(Square blocker, ChessPiece target) {
      return false;
   }

   public boolean isLegalAttack(Square target) {
      return Math.abs(target.file - this.orig.file) == 2?false:this.isLegalDest(target);
   }

   public boolean isKing() {
      return true;
   }
}
