package ictk.boardgame.chess;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;

public class Knight extends ChessPiece {

   public static final byte INDEX = 4;
   protected static final int MAX_LEGAL_DESTS = 8;
   protected static final int MAX_GUARDS = 8;


   public Knight() {
      super((byte)4, true, 8, 8);
   }

   public Knight(boolean blackness) {
      super((byte)4, blackness, 8, 8);
   }

   public Knight(boolean blackness, Square o, ChessBoard _board) {
      super((byte)4, blackness, o, _board, 8, 8);
   }

   protected String getName() {
      return "Knight";
   }

   protected int genLegalDests() {
      super.genLegalDests();
      if(this.captured) {
         return 0;
      } else {
         byte f = (byte)(this.orig.file + 1);
         byte r = (byte)(this.orig.rank + 2);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file + 2);
         r = (byte)(this.orig.rank + 1);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file + 2);
         r = (byte)(this.orig.rank - 1);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file + 1);
         r = (byte)(this.orig.rank - 2);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file - 1);
         r = (byte)(this.orig.rank - 2);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file - 2);
         r = (byte)(this.orig.rank - 1);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file - 2);
         r = (byte)(this.orig.rank + 1);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         f = (byte)(this.orig.file - 1);
         r = (byte)(this.orig.rank + 2);
         if(this.board.isFileValid(f) && this.board.isRankValid(r)) {
            this.addLegalDest(this.board.getSquare((int)f, (int)r));
         }

         return this.legalDests.size();
      }
   }

   public boolean isBlockable(Square target) {
      return false;
   }

   public boolean isBlockable(Square blocker, ChessPiece target) {
      return false;
   }

   public boolean isKnight() {
      return true;
   }
}
