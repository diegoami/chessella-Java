package ictk.boardgame.chess;

import ictk.boardgame.Board;
import ictk.boardgame.Piece;
import ictk.boardgame.chess.Bishop;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.King;
import ictk.boardgame.chess.Knight;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Queen;
import ictk.boardgame.chess.Rook;
import ictk.boardgame.chess.Square;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ChessPiece extends Piece {

   public static byte NULL_PIECE = -1;
   public static byte BLACK_OFFSET = 70;
   protected byte index;
   protected boolean isBlack;
   protected boolean captured;
   protected short moveCount;
   protected List legalDests;
   protected List guardSquares;
   protected Square orig;
   protected ChessBoard board;
   protected ChessPiece pinnedBy;


   public ChessPiece(byte ind, int maxlegaldests, int maxguards) {
      this(ind, false, maxlegaldests, maxguards);
   }

   public ChessPiece(byte ind, boolean _isBlack, int maxlegaldests, int maxguards) {
      this(ind, _isBlack, (Square)null, (ChessBoard)null, maxlegaldests, maxguards);
   }

   public ChessPiece(byte ind, boolean _isBlack, Square _orig, ChessBoard _board, int maxlegaldests, int maxguards) {
      this.index = ind;
      this.isBlack = _isBlack;
      this.moveCount = 0;
      this.orig = _orig;
      this.board = _board;
      this.legalDests = new ArrayList(maxlegaldests);
      this.guardSquares = new ArrayList(maxguards);
   }

   public boolean isCaptured() {
      return this.captured;
   }

   public void setCaptured(boolean t) {
      this.legalDests.clear();
      this.guardSquares.clear();
      this.captured = t;
   }

   public ChessPiece getPinnedBy() {
      return this.pinnedBy;
   }

   public boolean isBlack() {
      return this.isBlack;
   }

   public void setBoard(ChessBoard b) {
      this.board = b;
   }

   protected int genLegalDests() {
      this.pinnedBy = null;
      this.legalDests.clear();
      this.guardSquares.clear();
      return 0;
   }

   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat) {
      Iterator oldLegals = this.legalDests.iterator();
      Square sq = null;
      if(!this.captured) {
         this.legalDests = new ArrayList(3);

         while(oldLegals.hasNext()) {
            sq = (Square)oldLegals.next();
            if(threat.isBlockable(sq, king)) {
               this.legalDests.add(sq);
            } else if(sq.equals(threat.getSquare())) {
               this.legalDests.add(sq);
            }
         }

      }
   }

   protected boolean addLegalDest(Square dest) {
      boolean valid = true;
      if(dest.isOccupied() && dest.piece.isBlack() == this.isBlack()) {
         this.guardSquares.add(dest);
         valid = false;
      } else {
         this.legalDests.add(dest);
      }

      return valid;
   }

   protected void setPinned(ChessPiece pinner, List lineOfSight) {
      this.pinnedBy = pinner;

      assert !pinner.isCaptured() : "Captured Pinner: " + pinner.dump();

      this.legalDests.retainAll(lineOfSight);
   }

   public boolean isLegalDest(Square dest) {
      if(!this.captured) {
         if(this.board.staleLegalDests) {
            this.board.genLegalDests();
         }

         return this.legalDests.contains(dest);
      } else {
         return false;
      }
   }

   public boolean isLegalAttack(Square dest) {
      return this.isLegalDest(dest);
   }

   public List getLegalDests() {
      if(this.board.staleLegalDests) {
         this.board.genLegalDests();
      }

      return this.legalDests;
   }

   public void removeLegalDests() {
      this.legalDests.clear();
      this.guardSquares.clear();
   }

   public List getGuardSquares() {
      if(this.board.staleLegalDests) {
         this.board.genLegalDests();
      }

      return this.guardSquares;
   }

   public boolean isGuarding(Square dest) {
      if(!this.captured) {
         if(this.board.staleLegalDests) {
            this.board.genLegalDests();
         }

         return this.guardSquares.contains(dest);
      } else {
         return false;
      }
   }

   public boolean isGuarding(ChessPiece piece) {
      return this.guardSquares.contains(piece.orig);
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
      byte i = 0;
      byte var12;
      if(o_file == t_file) {
         if(o_rank < t_rank) {
            for(var12 = (byte)(o_rank + 1); var12 <= t_rank; ++var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare((int)o_file, (int)var12);
               }
            }
         } else {
            for(var12 = (byte)(o_rank - 1); var12 >= t_rank; --var12) {
               if(var12 != t_rank || inclusive) {
                  return_tmp[i++] = this.board.getSquare((int)o_file, (int)var12);
               }
            }
         }
      } else {
         byte var11;
         if(o_rank == t_rank) {
            if(o_file < t_file) {
               for(var11 = (byte)(o_file + 1); var11 <= t_file; ++var11) {
                  if(var11 != t_file || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)o_rank);
                  }
               }
            } else {
               for(var11 = (byte)(o_file - 1); var11 >= t_file; --var11) {
                  if(var11 != t_file || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)o_rank);
                  }
               }
            }
         } else if(o_file - t_file == o_rank - t_rank) {
            if(o_rank < t_rank) {
               var11 = (byte)(o_file + 1);

               for(var12 = (byte)(o_rank + 1); var12 <= t_rank; ++var12) {
                  if(var12 != t_rank || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)var12);
                  }

                  ++var11;
               }
            } else {
               var11 = (byte)(o_file - 1);

               for(var12 = (byte)(o_rank - 1); var12 >= t_rank; --var12) {
                  if(var12 != t_rank || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)var12);
                  }

                  --var11;
               }
            }
         } else if(o_file - t_file == (o_rank - t_rank) * -1) {
            if(o_rank - t_rank < 0) {
               var11 = (byte)(o_file - 1);

               for(var12 = (byte)(o_rank + 1); var12 <= t_rank; ++var12) {
                  if(var12 != t_rank || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)var12);
                  }

                  --var11;
               }
            } else {
               var11 = (byte)(o_file + 1);

               for(var12 = (byte)(o_rank - 1); var12 >= t_rank; --var12) {
                  if(var12 != t_rank || inclusive) {
                     return_tmp[i++] = this.board.getSquare((int)var11, (int)var12);
                  }

                  ++var11;
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

   public boolean isBlockable(Square blocker, ChessPiece target) {
      boolean blockable = false;
      Square dest = target.getSquare();
      Square[] lineOfSight = (Square[])null;
      if(this.board.staleLegalDests) {
         this.board.genLegalDests();
      }

      if(!this.isLegalDest(dest)) {
         throw new IllegalArgumentException(this + "cannot be blocked for illegal destination square (" + dest + ")");
      } else {
         lineOfSight = this.getLineOfSight(target, false);

         for(int i = 0; !blockable && lineOfSight != null && i < lineOfSight.length; blockable = blocker.equals(lineOfSight[i++])) {
            ;
         }

         return blockable;
      }
   }

   protected void adjustPinsLegalDests(ChessPiece king, List enemyTeam) {}

   public byte getIndex() {
      return (byte)(this.index + (this.isBlack?BLACK_OFFSET:0));
   }

   static boolean isBlackIndex(byte i) {
      return i > BLACK_OFFSET;
   }

   public Square getSquare() {
      return this.orig;
   }

   public Board getBoard() {
      return this.board;
   }

   public String toString() {
      return this.getName();
   }

   protected abstract String getName();

   public boolean isKing() {
      return false;
   }

   public boolean isQueen() {
      return false;
   }

   public boolean isRook() {
      return false;
   }

   public boolean isBishop() {
      return false;
   }

   public boolean isKnight() {
      return false;
   }

   public boolean isPawn() {
      return false;
   }

   public static ChessPiece toChessPiece(int i) {
      Object p = null;
      switch(i % BLACK_OFFSET) {
      case 0:
         p = new King();
         break;
      case 1:
         p = new Queen();
         break;
      case 2:
         p = new Rook();
         break;
      case 3:
         p = new Bishop();
         break;
      case 4:
         p = new Knight();
         break;
      case 5:
         p = new Pawn();
         break;
      default:
         throw new IllegalArgumentException("Illegal ChessPiece INDEX(" + i + ")");
      }

      return (ChessPiece)p;
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append(this.getName()).append(" captured: ").append(this.captured).append(" square: ").append(this.orig).append(" legalDests: ").append(this.legalDests).append(" guardSquares: ").append(this.guardSquares).append(" pinnedBy: ").append(this.pinnedBy);
      return sb.toString();
   }
}
