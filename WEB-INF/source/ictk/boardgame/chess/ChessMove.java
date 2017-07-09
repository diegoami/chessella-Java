package ictk.boardgame.chess;

import ictk.boardgame.Board;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.Result;
import ictk.boardgame.UnverifiedMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Queen;
import ictk.boardgame.chess.Square;
import ictk.util.Log;

public class ChessMove extends Move {

   public static long DEBUG = Log.Move;
   private boolean nullMove;
   public static final int CASTLE_QUEENSIDE = -1;
   public static final int CASTLE_KINGSIDE = 1;
   protected ChessBoard board;
   protected Square orig;
   protected Square dest;
   protected ChessPiece piece;
   protected ChessPiece casualty;
   protected ChessPiece promotion;
   protected int prevEnPassantFile;
   protected int prevPlyCount50;
   protected boolean check;
   protected boolean doublecheck;
   protected boolean checkmate;
   protected boolean stalemate;
   protected boolean castleQueenside;
   protected boolean castleKingside;
   protected boolean[] unique;


   public ChessMove(ChessBoard b) throws IllegalMoveException {
      this.nullMove = false;
      this.unique = new boolean[2];
      if(b == null) {
         throw new IllegalArgumentException("A ChessMove cannot be associated with a null ChessBoard");
      } else {
         this.board = b;
         this.nullMove = true;
      }
   }

   public ChessMove(ChessBoard b, int i) throws IllegalMoveException {
      this.nullMove = false;
      this.unique = new boolean[2];
      if(b == null) {
         throw new IllegalArgumentException("A ChessMove cannot be associated with a null ChessBoard");
      } else {
         this.board = b;
         switch(i) {
         case -1:
            this.castleQueenside = true;
            break;
         case 0:
         default:
            throw new IllegalArgumentException("illegal parameter sent to ChessMove Castle Constructor; check docs");
         case 1:
            this.castleKingside = true;
         }

         if(!this.board.isLegalMove(this)) {
            throw new IllegalMoveException();
         }
      }
   }

   public ChessMove(ChessBoard b, int of, int or, int df, int dr) throws IllegalMoveException {
      this(b, of, or, df, dr, ChessPiece.NULL_PIECE);
   }

   public ChessMove(ChessBoard b, int of, int or, int df, int dr, int promo) throws IllegalMoveException {
      this.nullMove = false;
      this.unique = new boolean[2];
      this.board = b;
      this.orig = this.board.squares[of - 1][or - 1];
      this.dest = this.board.squares[df - 1][dr - 1];
      if(promo > 0) {
         this.promotion = ChessPiece.toChessPiece(promo);
      }

      if(this.promotion != null && (this.promotion.isKing() || this.promotion.isPawn())) {
         throw new IllegalMoveException("Can\'t promote a pawn to King or Pawn");
      } else if(!this.board.isLegalMove(this)) {
         throw new IllegalMoveException();
      }
   }

   public ChessMove(ChessBoard b, Square o, Square d) throws IllegalMoveException {
      this(b, o, d, (ChessPiece)null);
   }

   public ChessMove(ChessBoard b, Square o, Square d, ChessPiece promo) throws IllegalMoveException {
      this.nullMove = false;
      this.unique = new boolean[2];
      if(b == null) {
         throw new IllegalArgumentException("A ChessMove cannot be associated with a null ChessBoard");
      } else {
         this.board = b;
         this.orig = o;
         this.dest = d;
         this.promotion = promo;
         if(this.promotion != null && (this.promotion.isKing() || this.promotion.isPawn())) {
            throw new IllegalMoveException("Can\'t promote a pawn to King or Pawn");
         } else if(!this.board.isLegalMove(this)) {
            throw new IllegalMoveException();
         }
      }
   }

   public ChessMove(ChessPiece piece, Square destination) throws IllegalMoveException {
      this(piece, destination, (ChessPiece)null);
   }

   public ChessMove(ChessPiece piece, Square destination, ChessPiece promotion) throws IllegalMoveException {
      this((ChessBoard)piece.getBoard(), piece.getSquare(), destination, promotion);
   }

   ChessMove(Square o, Square d, ChessPiece promo, ChessBoard b) {
      this.nullMove = false;
      this.unique = new boolean[2];
      this.board = b;
      this.orig = o;
      this.dest = d;
      this.promotion = promo;
   }

   public void dispose() {
      super.dispose();
      this.piece = null;
      this.casualty = null;
      this.promotion = null;
      this.orig = null;
      this.dest = null;
   }

   protected void locateCastlingSquares() {
      if(this.castleQueenside) {
         if(this.board.isBlackMove()) {
            this.orig = this.board.getSquare('e', '8');
            this.dest = this.board.getSquare('c', '8');
         } else {
            this.orig = this.board.getSquare('e', '1');
            this.dest = this.board.getSquare('c', '1');
         }
      } else if(this.castleKingside) {
         if(this.board.isBlackMove()) {
            this.orig = this.board.getSquare('e', '8');
            this.dest = this.board.getSquare('g', '8');
         } else {
            this.orig = this.board.getSquare('e', '1');
            this.dest = this.board.getSquare('g', '1');
         }
      } else {
         assert false : "locateCastlingSquares() called for non-castle move";
      }

   }

   protected void execute() throws IllegalMoveException, OutOfTurnException {
      if(!this.isNullMove()) {
         this.prevEnPassantFile = this.board.enpassantFile;
         if(this.castleQueenside || this.castleKingside) {
            this.locateCastlingSquares();
         }

         if(this.orig.piece != null) {
            this.piece = this.orig.piece;
         }

         Log.debug(DEBUG, "executing move: " + this);
         Log.debug2(DEBUG, this.piece.dump());
         Log.debug2(DEBUG, this.board);
         if(this.piece == null) {
            throw new IllegalMoveException("No piece to move.", this);
         }

         if(this.piece.isBlack() != this.board.isBlackMove) {
            throw new OutOfTurnException("It is " + (this.board.isBlackMove?"Black":"White") + "\'s move");
         }

         if(!this.verified && !this.piece.isLegalDest(this.dest)) {
            Log.debug(DEBUG, "tried to execute move with illegal destination");
            Log.debug2(DEBUG, "piece is: " + this.piece.dump());
            Log.debug2(DEBUG, "dest is: " + this.dest);
            throw new IllegalMoveException("Illegal move " + this, this);
         }

         this.casualty = this.dest.piece;
         if(this.piece.isPawn() && this.casualty == null && this.orig.file != this.dest.file) {
            this.casualty = this.board.getSquare((int)this.dest.file, (int)this.orig.rank).piece;
            Log.debug(DEBUG, "enpassant encounterd: " + this + "orig: " + this.orig + " dest:" + this.dest + " casualty: " + this.casualty);
         } else {
            this.board.enpassantFile = 0;
         }

         if(!this.piece.isPawn() && this.casualty == null) {
            ++this.board.plyCount50;
         } else {
            this.prevPlyCount50 = this.board.plyCount50;
            this.board.plyCount50 = 0;
         }

         if(this.casualty != null) {
            this.casualty.setCaptured(true);
            this.casualty.orig.piece = null;
         }

         if(this.piece.isPawn() && (this.orig.rank - this.dest.rank == 2 || this.orig.rank - this.dest.rank == -2)) {
            this.board.setEnPassantFile((int)this.orig.file);
         }

         if(this.piece.isKing() && this.piece.moveCount == 0) {
            Square rook_orig;
            Square rook_dest;
            if(this.dest.file == 3) {
               rook_orig = this.board.getSquare((int)1, (int)this.orig.rank);
               rook_dest = this.board.getSquare((int)4, (int)this.orig.rank);
               rook_dest.piece = rook_orig.piece;
               rook_orig.piece = null;
               rook_dest.piece.orig = rook_dest;
               ++rook_dest.piece.moveCount;
               this.castleQueenside = true;
            } else if(this.dest.file == 7) {
               rook_orig = this.board.getSquare((int)8, (int)this.orig.rank);
               rook_dest = this.board.getSquare((int)6, (int)this.orig.rank);
               rook_dest.piece = rook_orig.piece;
               rook_orig.piece = null;
               rook_dest.piece.orig = rook_dest;
               ++rook_dest.piece.moveCount;
               this.castleKingside = true;
            }
         }

         this.unique = this.board.isDestUniqueForClass(this.dest, this.piece);
         this.dest.piece = this.piece;
         this.piece.orig = this.dest;
         this.orig.piece = null;
         ++this.piece.moveCount;
         if(this.piece.isPawn() && Pawn.isPromotionSquare(this.dest, this.piece.isBlack)) {
            if(this.promotion == null) {
               this.promotion = new Queen();
            }

            this.board.promote(this.piece, this.promotion);
         } else {
            this.promotion = null;
         }

         this.board.isBlackMove = !this.piece.isBlack;
         if(this.piece.isBlack) {
            ++this.board.moveNumber;
         }
      } else {
         this.board.isBlackMove = ((ChessMove)this.prev).piece.isBlack;
         if(!((ChessMove)this.prev).piece.isBlack) {
            ++this.board.moveNumber;
         }
      }

      this.prev = this.board.lastMove;
      this.board.lastMove = this;
      this.executed = true;
      this.board.staleLegalDests = true;
      if(!this.verified || this.continuation.isTerminal() && !this.isEndOfGame()) {
         this.board.genLegalDests();
      }

      this.verified = true;
      this.board.fireBoardEvent(1);
      Log.debug(DEBUG, "execute successful");
      Log.debug2(DEBUG, this.board);
   }

   protected void unexecute() {
      Log.debug(DEBUG, "unexecuting move: " + this);
      Log.debug2(DEBUG, this.board);
      if(!this.isNullMove()) {
         this.board.setEnPassantFile(this.prevEnPassantFile);
         if(this.piece.isKing() && this.piece.moveCount == 1) {
            Square rook_orig;
            Square rook_dest;
            if(this.dest.file == 3) {
               rook_orig = this.board.getSquare((int)1, (int)this.orig.rank);
               rook_dest = this.board.getSquare((int)4, (int)this.orig.rank);
               rook_orig.piece = rook_dest.piece;
               rook_dest.piece = null;
               rook_orig.piece.orig = rook_orig;
               --rook_orig.piece.moveCount;
            } else if(this.dest.file == 7) {
               rook_orig = this.board.getSquare((int)8, (int)this.orig.rank);
               rook_dest = this.board.getSquare((int)6, (int)this.orig.rank);
               rook_orig.piece = rook_dest.piece;
               rook_dest.piece = null;
               rook_orig.piece.orig = rook_orig;
               --rook_orig.piece.moveCount;
            }
         }

         if(this.piece.isPawn() && Pawn.isPromotionSquare(this.dest, this.piece.isBlack)) {
            this.board.promote(this.promotion, this.piece);
         }

         --this.dest.piece.moveCount;
         this.orig.piece = this.dest.piece;
         this.piece.orig = this.orig;
         this.dest.piece = null;
         if(this.casualty != null) {
            this.casualty.setCaptured(false);
            this.casualty.orig.piece = this.casualty;
         }

         if(!this.piece.isPawn() && this.casualty == null) {
            --this.board.plyCount50;
         } else {
            this.board.plyCount50 = this.prevPlyCount50;
         }

         this.board.isBlackMove = this.piece.isBlack;
         if(this.piece.isBlack) {
            --this.board.moveNumber;
         }
      } else {
         this.board.isBlackMove = !((ChessMove)this.prev).piece.isBlack;
         if(!((ChessMove)this.prev).piece.isBlack) {
            --this.board.moveNumber;
         }
      }

      this.board.lastMove = (ChessMove)this.prev;
      this.executed = false;
      this.board.staleLegalDests = true;
      this.board.fireBoardEvent(2);
      Log.debug(DEBUG, "unexecute successful");
      Log.debug2(DEBUG, this.board);
   }

   public boolean isLegal() {
      if(this.isNullMove()) {
         return true;
      } else if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Check.");
      } else {
         return this.piece.isLegalDest(this.dest);
      }
   }

   public boolean[] getUniqueness() {
      return this.unique;
   }

   public boolean isFileUnique() {
      return this.unique[0];
   }

   public boolean isRankUnique() {
      return this.unique[1];
   }

   public boolean isCheck() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Check.");
      } else {
         return this.check;
      }
   }

   public boolean isDoubleCheck() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Double Check.");
      } else {
         return this.doublecheck;
      }
   }

   public boolean isCheckmate() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Checkmate.");
      } else {
         return this.checkmate;
      }
   }

   public boolean isStalemate() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Stalemate.");
      } else {
         return this.stalemate;
      }
   }

   public boolean isEndOfGame() {
      return this.checkmate || this.stalemate || this.result != null && !this.result.isUndecided();
   }

   public Result getResult() {
      return this.result;
   }

   protected void setCheck(boolean t) {
      this.check = t;
   }

   protected void setDoubleCheck(boolean t) {
      this.doublecheck = t;
      if(t) {
         this.check = t;
      }

   }

   protected void setCheckmate(boolean t) {
      this.checkmate = t;
      if(this.result == null) {
         this.result = new ChessResult(this.board.isBlackMove()?3:2);
      }

   }

   protected void setStalemate(boolean t) {
      this.stalemate = t;
      if(this.result == null) {
         this.result = new ChessResult(1);
      }

   }

   public void setResult(Result res) {
      this.result = res;
      if(res != null && !res.isUndecided()) {
         this.continuation.setMainLineTerminal();
      }

   }

   public boolean isExecuted() {
      return this.executed;
   }

   public void setExecuted(boolean value) {
      this.executed = value;
   }

   public boolean isVerified() {
      return this.verified;
   }

   public ChessPiece getChessPiece() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine which piece an unverified move will affect.");
      } else {
         return this.piece;
      }
   }

   public ChessPiece getCasualty() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine which piece an unverified move will affect.");
      } else {
         return this.casualty;
      }
   }

   public ChessPiece getPromotion() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine which piece an unverified move will affect.");
      } else {
         return this.promotion;
      }
   }

   public boolean isBlackMove() {
      if(this.isNullMove()) {
         return ((ChessMove)this.prev).isBlackMove();
      } else if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move will affect black or white.");
      } else {
         return this.piece.isBlack();
      }
   }

   public boolean isCastleQueenside() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Castle Queenside.");
      } else {
         return this.castleQueenside;
      }
   }

   public boolean isCastleKingside() {
      if(!this.verified) {
         throw new UnverifiedMoveException("Cannot determine if an unverified move is Castle Kingside.");
      } else {
         return this.castleKingside;
      }
   }

   public int[] getCoordinates() {
      int[] coords = (int[])null;
      if(!this.castleQueenside && !this.castleKingside) {
         if(this.promotion != null) {
            coords = new int[5];
            coords[4] = this.promotion.getIndex();
         } else {
            coords = new int[4];
         }

         coords[0] = this.orig.file;
         coords[1] = this.orig.rank;
         coords[2] = this.dest.file;
         coords[3] = this.dest.rank;
      } else {
         coords = new int[]{this.castleQueenside?-1:1};
      }

      return coords;
   }

   public int[] getDestinationCoordinates() {
      int[] coords = new int[2];
      int[] full = this.getCoordinates();
      coords[0] = full[2];
      coords[1] = full[3];
      return coords;
   }

   public Square getDestination() {
      return this.dest;
   }

   public Square getOrigin() {
      return this.orig;
   }

   public int[] getOriginCoordinates() {
      int[] coords = new int[2];
      int[] full = this.getCoordinates();
      coords[0] = full[0];
      coords[1] = full[1];
      return coords;
   }

   public String toString() {
      if(this.isNullMove()) {
         return "--";
      } else {
         String move_seperator = "-";
         String take_seperator = "x";
         String check_name = "+";
         String doublecheck_name = "+";
         String checkmate_name = "#";
         String s;
         if(this.castleQueenside) {
            s = "O-O-O";
         } else if(this.castleKingside) {
            s = "O-O";
         } else if(!this.verified) {
            s = this.orig.toString() + "-" + this.dest.toString();
            if(this.promotion != null) {
               s = s + "=" + Character.toUpperCase(ChessBoard.san.pieceToChar(this.promotion.getIndex()));
            }
         } else {
            s = (this.piece != null && !(this.piece instanceof Pawn)?String.valueOf(Character.toUpperCase(ChessBoard.san.pieceToChar(this.piece.getIndex()))):" ") + this.orig.toString() + (this.casualty != null?take_seperator:move_seperator) + this.dest.toString();
            if(this.promotion != null) {
               s = s + "=" + Character.toUpperCase(ChessBoard.san.pieceToChar(this.promotion.getIndex()));
            }

            if(this.checkmate) {
               s = s + checkmate_name;
            } else {
               if(this.check) {
                  s = s + check_name;
               }

               if(this.doublecheck) {
                  s = s + doublecheck_name;
               }
            }
         }

         if(this.annotation != null) {
            s = s + "{" + this.annotation.getComment() + "}";
         }

         return s;
      }
   }

   public boolean equals(Object o) {
      if(o == this) {
         return true;
      } else if(o != null && o.getClass() == this.getClass()) {
         ChessMove m = (ChessMove)o;
         return this.isNullMove()?m.isNullMove():this.orig.equals(m.orig) && this.dest.equals(m.dest);
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      if(this.isNullMove()) {
         return 0;
      } else {
         int hash1 = 31 * hash + (this.orig == null?0:this.orig.hashCode());
         hash1 = 31 * hash1 + (this.dest == null?0:this.dest.hashCode());
         return hash1;
      }
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      int[] coord = this.getCoordinates();
      if(!this.isNullMove()) {
         sb.append("Move: \n").append("   coordinates: ");
         if(coord.length == 1) {
            sb.append(coord[0] == -1?"O-O-O":"O-O");
         } else {
            sb.append("" + coord[0] + coord[1] + coord[2] + coord[3]);
         }

         sb.append("\n").append("   verified: " + this.verified + "\n").append("   origin: " + this.orig + "\n").append("   destination: " + this.dest + "\n").append("   isFileUnique: " + this.unique[0] + "\n").append("   isRankUnique: " + this.unique[1] + "\n").append("   piece: " + this.piece + "\n");
         if(this.piece != null) {
            sb.append("      " + this.piece.dump());
         }

         sb.append("   casualty: " + this.casualty + "\n");
         if(this.casualty != null) {
            sb.append(this.casualty.dump());
         }

         sb.append("   promotion: " + this.promotion + "\n").append("   isCheck: " + this.check + "\n").append("   isDoubleCheck: " + this.doublecheck + "\n").append("   isCheckmate: " + this.checkmate + "\n").append("   isStalemate: " + this.stalemate + "\n").append("   result: " + this.result + "\n").append("   isEndOfGame: " + this.isEndOfGame() + "\n").append("   prenotation: ").append(this.getPrenotation()).append("\n").append("   annotation: ").append(this.getAnnotation()).append("\n").append("   previous: ").append(this.getPrev()).append("\n");
         if(this.continuation != null && !this.continuation.isTerminal()) {
            sb.append("   ").append(this.continuation.dump());
         } else {
            sb.append("   #continuations: terminal");
         }
      }

      return sb.toString();
   }

   public void setBoard(Board b) {
      this.board = (ChessBoard)b;
   }

   public Board getBoard() {
      return this.board;
   }

   public boolean isNullMove() {
      return this.nullMove;
   }

   public void setNullMove(boolean nullMove) {
      this.nullMove = nullMove;
   }
}
