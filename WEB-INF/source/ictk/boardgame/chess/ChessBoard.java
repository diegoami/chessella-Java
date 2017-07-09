package ictk.boardgame.chess;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.AmbiguousChessMoveException;
import ictk.boardgame.chess.Bishop;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.King;
import ictk.boardgame.chess.Knight;
import ictk.boardgame.chess.Pawn;
import ictk.boardgame.chess.Queen;
import ictk.boardgame.chess.Rook;
import ictk.boardgame.chess.Square;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChessBoard implements Board {

   public static final long DEBUG = Log.Board;
   public static final byte NULL_FILE = 0;
   public static final byte NULL_RANK = 0;
   public static final byte NO_ENPASSANT = 0;
   public static final char[][] DEFAULT_POSITION = new char[][]{{'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q'}, {'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k'}, {'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b'}, {'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n'}, {'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r'}};
   public static final byte MAX_FILE = 8;
   public static final byte MAX_RANK = 8;
   protected static final SAN san = new SAN();
   protected static final FEN fen = new FEN();
   protected BoardListener[] listeners;
   protected Square[][] squares;
   protected List whiteTeam;
   protected List blackTeam;
   protected King whiteKing;
   protected King blackKing;
   protected boolean isBlackMove;
   protected ChessMove lastMove;
   protected byte enpassantFile;
   protected boolean isInitialPositionDefault;
   protected int plyCount50;
   protected int moveNumber;
   protected boolean staleLegalDests;


   public ChessBoard() {
      this(true);
   }

   public ChessBoard(boolean defaultBoard) {
      this.isBlackMove = false;
      this.lastMove = null;
      this.enpassantFile = 0;
      this.isInitialPositionDefault = true;
      this.plyCount50 = 0;
      this.moveNumber = 0;
      this.staleLegalDests = true;
      this.squares = new Square[8][8];
      this.whiteTeam = new ArrayList(16);
      this.blackTeam = new ArrayList(16);
      byte f = 0;

      for(boolean r = false; f < 8; ++f) {
         for(byte var4 = 0; var4 < 8; ++var4) {
            this.squares[f][var4] = new Square((byte)(f + 1), (byte)(var4 + 1));
         }
      }

      if(defaultBoard) {
         this.setPositionDefault();
      } else {
         this.setPositionClear();
      }

   }

   public ChessBoard(char[][] matrix) {
      this();
      this.setPosition(matrix);
   }

   public ChessBoard(char[][] matrix, boolean isBlackMove, boolean castleWK, boolean castleWQ, boolean castleBK, boolean castleBQ, char enpassantFile, int plyCount, int moveNum) {
      this();
      this.setPosition(matrix);
      this.isBlackMove = isBlackMove;
      this.setWhiteCastleableKingside(castleWK);
      this.setWhiteCastleableQueenside(castleWQ);
      this.setBlackCastleableKingside(castleBK);
      this.setBlackCastleableQueenside(castleBQ);
      this.setEnPassantFile(enpassantFile);
      this.plyCount50 = plyCount;
      this.moveNumber = moveNum;
   }

   public boolean isRankValid(int r) {
      return r >= 1 && r <= 8;
   }

   public boolean isFileValid(int f) {
      return f >= 1 && f <= 8;
   }

   public int getMaxRank() {
      return 8;
   }

   public int getMaxFile() {
      return 8;
   }

   public void playMove(Move move) throws IllegalMoveException, OutOfTurnException {
      ChessMove m = (ChessMove)move;
      m.execute();
   }

   public int getPlayerToMove() {
      return this.isBlackMove?1:0;
   }

   protected void genLegalDests() {
      ChessPiece[] threats = (ChessPiece[])null;
      King movingKing = null;
      King otherKing = null;
      List movingTeam = null;
      List otherTeam = null;
      Log.debug(DEBUG, "generating legal moves");
      this.staleLegalDests = false;
      byte i = 0;

      for(boolean p = false; i < 8; ++i) {
         for(byte var8 = 0; var8 < 8; ++var8) {
            if(this.squares[i][var8].piece != null) {
               this.squares[i][var8].piece.genLegalDests();
            }
         }
      }

      movingKing = this.isBlackMove?this.blackKing:this.whiteKing;
      movingTeam = this.isBlackMove?this.blackTeam:this.whiteTeam;
      otherKing = this.isBlackMove?this.whiteKing:this.blackKing;
      otherTeam = this.isBlackMove?this.whiteTeam:this.blackTeam;
      movingKing.genLegalDestsFinal();
      otherKing.genLegalDestsFinal();

      for(i = 0; i < otherTeam.size(); ++i) {
         ((ChessPiece)otherTeam.get(i)).adjustPinsLegalDests(movingKing, movingTeam);
      }

      if(movingKing.isInCheck()) {
         if(this.lastMove != null) {
            this.lastMove.setCheck(true);
         }

         threats = this.getThreats(movingKing);
         Log.debug(DEBUG, "THREATS TO MOVING KING! (" + threats.length + ")");
         Log.debug2(DEBUG, "Threat: " + threats[0]);
         label82:
         switch(threats.length) {
         case 1:
            i = 0;

            while(true) {
               if(i >= movingTeam.size()) {
                  break label82;
               }

               ((ChessPiece)movingTeam.get(i)).genLegalDestsSaveKing(movingKing, threats[0]);
               ++i;
            }
         case 2:
            for(i = 0; i < movingTeam.size(); ++i) {
               ChessPiece var9 = (ChessPiece)movingTeam.get(i);
               if(var9 != movingKing) {
                  var9.removeLegalDests();
               }
            }

            if(this.lastMove != null) {
               this.lastMove.setDoubleCheck(true);
            }
            break;
         default:
            assert false : "King reports in check with " + threats.length + " threats.";
         }
      }

      if(this.getLegalMoveCount() == 0) {
         if(movingKing.isInCheck()) {
            if(this.lastMove != null) {
               this.lastMove.setCheckmate(true);
            }
         } else if(this.lastMove != null) {
            this.lastMove.setStalemate(true);
         }
      }

   }

   public boolean isLegalMove(Move m) {
      if(m == null) {
         return false;
      } else {
         try {
            ((ChessMove)m).execute();
            ((ChessMove)m).unexecute();
            return true;
         } catch (Exception var3) {
            return false;
         }
      }
   }

   public ChessPiece[] getThreats(Square sq, boolean isBlack) {
      Iterator team = null;
      LinkedList attackers = null;
      ChessPiece piece = null;
      ChessPiece[] threats = (ChessPiece[])null;
      if(sq == null) {
         throw new NullPointerException("cannot assess threats to null square");
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         attackers = new LinkedList();
         team = isBlack?this.blackTeam.iterator():this.whiteTeam.iterator();
         Log.debug(DEBUG, "Finding " + (isBlack?"Black":"White") + " attackers on " + sq);

         while(team.hasNext()) {
            piece = (ChessPiece)team.next();
            if(piece.isLegalAttack(sq)) {
               attackers.add(piece);
               Log.debug2(DEBUG, "attacker: " + piece + "(" + piece.getSquare() + ")");
            }
         }

         if(attackers.size() > 0) {
            threats = new ChessPiece[attackers.size()];
            threats = (ChessPiece[])attackers.toArray(threats);
            return threats;
         } else {
            Log.debug(DEBUG, "no attackers found.");
            return threats;
         }
      }
   }

   public ChessPiece[] getThreats(ChessPiece piece) {
      if(piece == null) {
         throw new NullPointerException("cannot assess threats to null piece");
      } else {
         return this.getThreats(piece.orig, !piece.isBlack);
      }
   }

   public boolean isThreatened(Square sq, boolean isBlack) {
      return this.getThreats(sq, isBlack) != null;
   }

   public boolean isThreatened(ChessPiece piece) {
      if(piece == null) {
         throw new NullPointerException("cannot assess threats to null piece");
      } else {
         return this.isThreatened(piece.orig, !piece.isBlack);
      }
   }

   public ChessPiece[] getGuards(Square sq, boolean isBlack) {
      Iterator team = null;
      LinkedList attackers = null;
      ChessPiece piece = null;
      ChessPiece[] guards = (ChessPiece[])null;
      if(sq == null) {
         throw new NullPointerException("cannot assess guards of null square");
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         attackers = new LinkedList();
         team = isBlack?this.blackTeam.iterator():this.whiteTeam.iterator();

         while(team.hasNext()) {
            piece = (ChessPiece)team.next();
            if(piece.isGuarding(sq)) {
               attackers.add(piece);
            }
         }

         if(attackers.size() > 0) {
            guards = new ChessPiece[attackers.size()];
            guards = (ChessPiece[])attackers.toArray(guards);
         }

         return guards;
      }
   }

   public ChessPiece[] getGuards(ChessPiece piece) {
      if(piece == null) {
         throw new NullPointerException("cannot assess threats to null piece");
      } else {
         return this.getThreats(piece.orig, !piece.isBlack);
      }
   }

   public boolean isGuarded(Square sq, boolean isBlack) {
      return this.getGuards(sq, isBlack) != null;
   }

   public boolean isGuarded(ChessPiece piece) {
      if(piece == null) {
         throw new NullPointerException("cannot assess threats to null piece");
      } else {
         return this.isGuarded(piece.orig, !piece.isBlack);
      }
   }

   public int getLegalMoveCount() {
      int count = 0;
      List movingTeam = this.isBlackMove?this.blackTeam:this.whiteTeam;
      if(this.staleLegalDests) {
         this.genLegalDests();
      }

      for(int i = 0; i < movingTeam.size(); ++i) {
         count += ((ChessPiece)movingTeam.get(i)).getLegalDests().size();
      }

      return count;
   }

   public List getLegalMoves() {
      LinkedList list = new LinkedList();
      List movingTeam = this.isBlackMove?this.blackTeam:this.whiteTeam;
      List dests = null;
      ChessPiece piece = null;
      Square orig = null;
      Square dest = null;
      if(this.staleLegalDests) {
         this.genLegalDests();
      }

      for(int i = 0; i < movingTeam.size(); ++i) {
         piece = (ChessPiece)movingTeam.get(i);
         dests = piece.getLegalDests();
         orig = piece.orig;

         for(int j = 0; j < dests.size(); ++j) {
            dest = (Square)dests.get(j);
            list.add(new ChessMove(orig, dest, dest.piece, this));
         }
      }

      return list;
   }

   protected boolean[] isDestUniqueForClass(Square dest, ChessPiece p) {
      boolean[] unique = new boolean[]{true, true};
      List movingTeam = this.isBlackMove?this.blackTeam:this.whiteTeam;
      List dests = null;
      ChessPiece piece = null;
      if(p.isKing()) {
         return unique;
      } else {
         for(int i = 0; i < movingTeam.size(); ++i) {
            piece = (ChessPiece)movingTeam.get(i);
            if(piece != p && !piece.isCaptured() && piece.getIndex() == p.getIndex()) {
               dests = piece.getLegalDests();
               if(dests.contains(dest)) {
                  if(p.orig.file == piece.orig.file) {
                     unique[0] = false;
                  }

                  if(p.orig.rank == piece.orig.rank) {
                     unique[1] = false;
                  }

                  if(unique[0] && unique[1]) {
                     unique[1] = false;
                  }
               }
            }
         }

         return unique;
      }
   }

   public Square getOrigin(byte piece_index, Square dest) throws AmbiguousChessMoveException, IllegalMoveException {
      return this.getOrigin(piece_index, -1, -1, dest);
   }

   public Square getOrigin(byte piece_index, int file, int rank, Square dest) throws AmbiguousChessMoveException, IllegalMoveException {
      if(file <= 8 && rank <= 8) {
         byte orig_f = (byte)file;
         byte orig_r = (byte)rank;
         List movingTeam = this.isBlackMove?this.blackTeam:this.whiteTeam;
         Object dests = null;
         ArrayList dupes = new ArrayList(1);
         ChessPiece piece = null;
         ChessPiece mover = null;
         boolean found = false;
         int count = 0;

         for(int i = 0; i < movingTeam.size(); ++i) {
            piece = (ChessPiece)movingTeam.get(i);
            if(piece.getIndex() % ChessPiece.BLACK_OFFSET == piece_index && piece.isLegalDest(dest) && (orig_f < 1 && orig_r < 1 || orig_r < 1 && piece.orig.file == orig_f || orig_f < 1 && piece.orig.rank == orig_r)) {
               found = true;
               ++count;
               if(count > 1) {
                  if(dupes == null) {
                     dupes = new ArrayList(2);
                     dupes.add(mover);
                  }

                  dupes.add(piece);
               }

               mover = piece;
            }
         }

         if(!found) {
            Log.debug(DEBUG, "Illegal Move piece: " + piece_index + " file: " + orig_f + " rank: " + orig_r + " dest: " + dest);
            Log.debug2(DEBUG, this);
            Log.debug2(DEBUG, this.dumpLegalMoves());
            Log.debug2(DEBUG, this.dumpLegalMoves(!this.isBlackMove));
            throw new IllegalMoveException("Illegal Move");
         } else if(found && count > 1) {
            Log.debug(DEBUG, "AMBIGUOUSMOVE!!!! to " + dest);
            Log.debug2(DEBUG, this);
            Log.debug2(DEBUG, this.dumpLegalMoves());
            Log.debug2(DEBUG, this.dumpLegalMoves(!this.isBlackMove));
            throw new AmbiguousChessMoveException("Ambiguous Move", piece_index, orig_f, orig_r, dest.file, dest.rank, dupes);
         } else {
            return mover.orig;
         }
      } else {
         throw new IllegalArgumentException("origin or rank too big");
      }
   }

   protected void promote(ChessPiece pawn, ChessPiece promo) {
      promo.orig = pawn.orig;
      promo.orig.piece = promo;
      promo.board = this;
      promo.isBlack = pawn.isBlack;
      if(pawn.isBlack) {
         this.blackTeam.set(this.blackTeam.indexOf(pawn), promo);
      } else {
         this.whiteTeam.set(this.whiteTeam.indexOf(pawn), promo);
      }

   }

   public boolean isCheckmate() {
      if(this.lastMove != null) {
         return this.lastMove.isCheckmate();
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         return this.getLegalMoveCount() == 0 && this.isCheck();
      }
   }

   public boolean isCheck() {
      boolean check = false;
      if(this.lastMove != null) {
         return this.lastMove.isCheck();
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         check = this.isBlackMove?this.blackKing.isInCheck():this.whiteKing.isInCheck();
         Log.debug(DEBUG, "the King in check: " + check);
         return check;
      }
   }

   public boolean isDoubleCheck() {
      boolean dcheck = false;
      if(this.lastMove != null) {
         return this.lastMove.isDoubleCheck();
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         dcheck = this.getThreats(this.isBlackMove?this.blackKing:this.whiteKing).length == 2;
         return dcheck;
      }
   }

   public boolean isStalemate() {
      if(this.lastMove != null) {
         return this.lastMove.isStalemate();
      } else {
         if(this.staleLegalDests) {
            this.genLegalDests();
         }

         return this.getLegalMoveCount() == 0 && !this.isCheck();
      }
   }

   public char[][] toCharArray() {
      char[][] board = new char[9][9];

      for(byte r = 0; r < 8; ++r) {
         for(byte f = 0; f < 8; ++f) {
            if(this.squares[f][r].isOccupied()) {
               switch(this.squares[f][r].piece.getIndex() % ChessPiece.BLACK_OFFSET) {
               case 0:
                  board[f][r] = 75;
                  break;
               case 1:
                  board[f][r] = 81;
                  break;
               case 2:
                  board[f][r] = 82;
                  break;
               case 3:
                  board[f][r] = 66;
                  break;
               case 4:
                  board[f][r] = 78;
                  break;
               case 5:
                  board[f][r] = 80;
               }

               if(this.squares[f][r].piece.getIndex() >= ChessPiece.BLACK_OFFSET) {
                  board[f][r] = Character.toLowerCase(board[f][r]);
               }
            }
         }
      }

      return board;
   }

   public ChessPiece[] getCapturedPieces(boolean isBlack) {
      return this.getCaptures(isBlack, true);
   }

   public ChessPiece[] getUnCapturedPieces(boolean isBlack) {
      return this.getCaptures(isBlack, false);
   }

   protected ChessPiece[] getCaptures(boolean isBlack, boolean isCaptured) {
      ChessPiece[] pows = (ChessPiece[])null;
      List team = isBlack?this.blackTeam:this.whiteTeam;
      int count = 0;
      ChessPiece piece = null;

      int i;
      for(i = 0; i < team.size(); ++i) {
         if(((ChessPiece)team.get(i)).isCaptured() == isCaptured) {
            ++count;
         }
      }

      if(count > 0) {
         pows = new ChessPiece[count];
         count = 0;

         for(i = 0; i < team.size(); ++i) {
            piece = (ChessPiece)team.get(i);
            if(piece.isCaptured() == isCaptured) {
               pows[count++] = piece;
            }
         }
      }

      return pows;
   }

   public int getMaterialCount(boolean isBlack) {
      int material = 0;
      List team = isBlack?this.blackTeam:this.whiteTeam;
      ChessPiece piece = null;

      for(int i = 0; i < team.size(); ++i) {
         piece = (ChessPiece)team.get(i);
         if(!piece.isCaptured()) {
            switch(piece.getIndex() % ChessPiece.BLACK_OFFSET) {
            case 1:
               material += 9;
               break;
            case 2:
               material += 5;
               break;
            case 3:
            case 4:
               material += 3;
               break;
            case 5:
               ++material;
            }
         }
      }

      return material;
   }

   public void setBlackMove(boolean t) {
      if(this.lastMove != null) {
         throw new IllegalStateException("can\'t set the move color for a game in progress.");
      } else {
         this.isBlackMove = t;
      }
   }

   public boolean isBlackMove() {
      return this.isBlackMove;
   }

   public Square getSquare(char file, char rank) {
      Square sq = this.squares[san.fileToNum(file) - 1][san.rankToNum(rank) - 1];
      return sq;
   }

   public Square getSquare(int x, int y) {
      return this.squares[x - 1][y - 1];
   }

   public String toString() {
      SAN san = new SAN();
      StringBuffer s_buffer = new StringBuffer();
      StringBuffer last_line = new StringBuffer();
      boolean c = true;
      last_line.append("\n    ");
      byte r = 7;

      for(byte f = 0; r >= 0; f = 0) {
         s_buffer.append(san.rankToChar(this.squares[f][r].rank) + "   ");

         for(byte var8 = 0; var8 < 8; ++var8) {
            if(this.squares[var8][r].isOccupied()) {
               char var7 = san.pieceToChar(this.squares[var8][r].piece);
               if(this.squares[var8][r].piece.isBlack()) {
                  var7 = Character.toLowerCase(var7);
               }

               s_buffer.append(var7 + " ");
            } else if(this.squares[var8][r].isBlack()) {
               s_buffer.append("  ");
            } else {
               s_buffer.append("# ");
            }

            if(r == 7) {
               last_line.append(Character.toUpperCase(san.fileToChar(this.squares[var8][r].file)) + " ");
            }
         }

         s_buffer.append('\n');
         --r;
      }

      s_buffer.append(last_line);
      return s_buffer.toString();
   }

   public void setPositionDefault() {
      if(this.lastMove != null) {
         throw new IllegalStateException("can\'t set the board position for a game in progress.");
      } else {
         this.setPositionClear();
         this.setPosition(DEFAULT_POSITION);
         this.isInitialPositionDefault = true;
      }
   }

   public void setPositionClear() {
      if(this.lastMove != null) {
         throw new IllegalStateException("can\'t set the board position for a game in progress.");
      } else {
         for(byte r = 0; r < 8; ++r) {
            for(byte f = 0; f < 8; ++f) {
               this.squares[f][r].piece = null;
            }
         }

         this.blackTeam.clear();
         this.whiteTeam.clear();
         this.blackKing = null;
         this.whiteKing = null;
         this.isInitialPositionDefault = false;
         this.plyCount50 = 0;
         this.enpassantFile = 0;
         this.moveNumber = 0;
      }
   }

   public void setPosition(char[][] matrix) {
      if(this.lastMove != null) {
         throw new IllegalStateException("can\'t set the board position for a game in progress.");
      } else {
         boolean wking = false;
         boolean bking = false;
         if(matrix.length == 8 && matrix[0].length == 8) {
            this.isInitialPositionDefault = false;
            this.setPositionClear();

            for(byte file = 0; file < matrix.length; ++file) {
               for(byte rank = 0; rank < matrix[file].length; ++rank) {
                  switch(matrix[file][rank]) {
                  case 66:
                     this.addBishop(file + 1, rank + 1, false);
                     break;
                  case 75:
                     this.addKing(file + 1, rank + 1, false);
                     wking = true;
                     break;
                  case 78:
                     this.addKnight(file + 1, rank + 1, false);
                     break;
                  case 80:
                     this.addPawn(file + 1, rank + 1, false);
                     break;
                  case 81:
                     this.addQueen(file + 1, rank + 1, false);
                     break;
                  case 82:
                     this.addRook(file + 1, rank + 1, false);
                     break;
                  case 98:
                     this.addBishop(file + 1, rank + 1, true);
                     break;
                  case 107:
                     this.addKing(file + 1, rank + 1, true);
                     bking = true;
                     break;
                  case 110:
                     this.addKnight(file + 1, rank + 1, true);
                     break;
                  case 112:
                     this.addPawn(file + 1, rank + 1, true);
                     break;
                  case 113:
                     this.addQueen(file + 1, rank + 1, true);
                     break;
                  case 114:
                     this.addRook(file + 1, rank + 1, true);
                  }
               }
            }

            if(wking) {
               if(matrix[4][0] == 75) {
                  if(matrix[0][0] != 82) {
                     Log.debug(DEBUG, "setting white q-side castle: false");
                     this.setWhiteCastleableQueenside(false);
                  }

                  if(matrix[7][0] != 82) {
                     Log.debug(DEBUG, "setting white k-side castle: false");
                     this.setWhiteCastleableKingside(false);
                  }
               } else {
                  Log.debug(DEBUG, "setting white castleable: false");
                  this.whiteKing.moveCount = 1;
               }
            }

            if(bking) {
               if(matrix[4][7] == 107) {
                  if(matrix[0][7] != 114) {
                     Log.debug(DEBUG, "setting black q-side castle: false");
                     this.setBlackCastleableQueenside(false);
                  }

                  if(matrix[7][7] != 114) {
                     Log.debug(DEBUG, "setting black k-side castle: false");
                     this.setBlackCastleableKingside(false);
                  }
               } else {
                  Log.debug(DEBUG, "setting black castleable: false");
                  this.blackKing.moveCount = 1;
               }
            }

         } else {
            throw new IllegalArgumentException("setPosition() takes a matrix the same dimensions as the board.");
         }
      }
   }

   public void addPawn(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      Pawn p;
      orig.setOccupant(p = new Pawn(isBlack, orig, this));
      if(isBlack) {
         this.blackTeam.add(p);
      } else {
         this.whiteTeam.add(p);
      }

   }

   public void addKnight(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      Knight p;
      orig.setOccupant(p = new Knight(isBlack, orig, this));
      if(isBlack) {
         this.blackTeam.add(p);
      } else {
         this.whiteTeam.add(p);
      }

   }

   public void addBishop(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      Bishop p;
      orig.setOccupant(p = new Bishop(isBlack, orig, this));
      if(isBlack) {
         this.blackTeam.add(p);
      } else {
         this.whiteTeam.add(p);
      }

   }

   public void addRook(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      Rook p;
      orig.setOccupant(p = new Rook(isBlack, orig, this));
      if(isBlack) {
         this.blackTeam.add(p);
      } else {
         this.whiteTeam.add(p);
      }

   }

   public void addQueen(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      Queen p;
      orig.setOccupant(p = new Queen(isBlack, orig, this));
      if(isBlack) {
         this.blackTeam.add(p);
      } else {
         this.whiteTeam.add(p);
      }

   }

   public void addKing(int file, int rank, boolean isBlack) {
      Square orig = this.getSquare(file, rank);
      King p;
      orig.setOccupant(p = new King(isBlack, orig, this));
      if(isBlack) {
         if(this.blackKing != null) {
            this.blackTeam.remove(this.blackKing);
         }

         this.blackTeam.add(p);
         this.blackKing = (King)p;
      } else {
         if(this.whiteKing != null) {
            this.whiteTeam.remove(this.whiteKing);
         }

         this.whiteTeam.add(p);
         this.whiteKing = (King)p;
      }

   }

   public boolean isWhiteCastleableKingside() {
      return this.whiteKing.isCastleableKingside();
   }

   public boolean isWhiteCastleableQueenside() {
      return this.whiteKing.isCastleableQueenside();
   }

   public boolean isBlackCastleableKingside() {
      return this.blackKing.isCastleableKingside();
   }

   public boolean isBlackCastleableQueenside() {
      return this.blackKing.isCastleableQueenside();
   }

   public void setWhiteCastleableKingside(boolean t) {
      this.whiteKing.setCastleableKingside(t);
   }

   public void setWhiteCastleableQueenside(boolean t) {
      this.whiteKing.setCastleableQueenside(t);
   }

   public void setBlackCastleableKingside(boolean t) {
      this.blackKing.setCastleableKingside(t);
   }

   public void setBlackCastleableQueenside(boolean t) {
      this.blackKing.setCastleableQueenside(t);
   }

   public byte getEnPassantFile() {
      return this.enpassantFile;
   }

   public void setEnPassantFile(int f) {
      if(f > 8) {
         throw new IllegalArgumentException("EnPassant file cannot be larget than MAX_FILE");
      } else {
         this.enpassantFile = (byte)f;
      }
   }

   public void setEnPassantFile(char f) {
      this.enpassantFile = san.fileToNum(f);
   }

   public boolean isEnPassantFile(char f) {
      return this.enpassantFile == 0?false:this.enpassantFile == san.fileToNum(f);
   }

   public boolean isEnPassantFile(int f) {
      return this.enpassantFile == 0?false:this.enpassantFile == f;
   }

   public int get50MoveRulePlyCount() {
      return this.plyCount50;
   }

   public void set50MoveRulePlyCount(int i) {
      this.plyCount50 = i;
   }

   public boolean is50MoveRuleApplicible() {
      return this.plyCount50 > 99;
   }

   public int getCurrentMoveNumber() {
      return this.moveNumber;
   }

   public boolean isInitialPositionDefault() {
      return this.isInitialPositionDefault;
   }

   public void addBoardListener(BoardListener bl) {
      int size = 0;
      boolean found = false;
      BoardListener[] bls = (BoardListener[])null;
      if(this.listeners != null) {
         size = this.listeners.length;

         for(int i = 0; !found && i < size; ++i) {
            found = this.listeners[i] == bl;
         }

         if(found) {
            return;
         }
      }

      bls = new BoardListener[size + 1];
      if(this.listeners != null) {
         System.arraycopy(this.listeners, 0, bls, 0, size);
      }

      bls[size] = bl;
      this.listeners = bls;
   }

   public BoardListener[] getBoardListeners() {
      return this.listeners;
   }

   public void removeBoardListener(BoardListener bl) {
      boolean size = false;
      int idx = 0;
      boolean found = false;
      BoardListener[] bls = (BoardListener[])null;
      if(this.listeners != null) {
         int var7 = this.listeners.length;

         for(int i = 0; !found && i < var7; ++i) {
            found = this.listeners[i] == bl;
            if(found) {
               idx = i;
            }
         }

         if(found) {
            this.listeners[idx] = null;
            bls = new BoardListener[var7 - 1];
            if(idx != 0) {
               System.arraycopy(this.listeners, 0, bls, 0, idx);
            }

            if(idx != var7 - 1) {
               System.arraycopy(this.listeners, idx + 1, bls, idx, var7 - 1);
            }

            this.listeners = bls;
         }
      }
   }

   public void fireBoardEvent(int event) {
      if(this.listeners != null) {
         for(int i = 0; i < this.listeners.length; ++i) {
            this.listeners[i].boardUpdate(this, event);
         }
      }

   }

   public boolean equals(Object o) {
      if(o == this) {
         return true;
      } else if(o != null && o.getClass() == this.getClass()) {
         boolean equal = true;
         ChessBoard b = (ChessBoard)o;
         Log.debug(DEBUG, "comparing boards");
         equal = this.isBlackMove == b.isBlackMove;
         if(!equal) {
            Log.debug2(DEBUG, "move parity failed");
         }

         if(equal) {
            equal = this.squares.length == b.squares.length;
         }

         if(!equal) {
            Log.debug2(DEBUG, "board dimension(f) failed");
         }

         if(equal) {
            equal = this.squares[0].length == b.squares[0].length;
         }

         if(!equal) {
            Log.debug2(DEBUG, "board dimension(r) failed");
         }

         if(equal) {
            equal = this.isWhiteCastleableQueenside() == b.isWhiteCastleableQueenside();
         }

         if(!equal) {
            Log.debug2(DEBUG, "castling QW failed");
         }

         if(equal) {
            equal = this.isBlackCastleableQueenside() == b.isBlackCastleableQueenside();
         }

         if(!equal) {
            Log.debug2(DEBUG, "castling QB failed");
         }

         if(equal) {
            equal = this.isWhiteCastleableKingside() == b.isWhiteCastleableKingside();
         }

         if(!equal) {
            Log.debug2(DEBUG, "castling KW failed");
         }

         if(equal) {
            equal = this.isBlackCastleableKingside() == b.isBlackCastleableKingside();
         }

         if(!equal) {
            Log.debug2(DEBUG, "castling KB failed");
         }

         if(equal) {
            equal = this.enpassantFile == b.enpassantFile;
         }

         if(!equal) {
            Log.debug2(DEBUG, "enpassant failed");
         }

         if(equal) {
            for(byte i = 0; i < this.squares.length && equal; ++i) {
               for(byte j = 0; j < this.squares[i].length && equal; ++j) {
                  if(this.squares[i][j].getOccupant() == null) {
                     equal = b.squares[i][j].getOccupant() == null;
                  } else {
                     equal = b.squares[i][j].getOccupant() != null;
                     if(!equal) {
                        Log.debug2(DEBUG, "squares[" + i + "][" + j + "] nulls failed");
                     }

                     if(equal) {
                        equal = this.squares[i][j].getOccupant().getIndex() == b.squares[i][j].getOccupant().getIndex();
                        if(!equal) {
                           Log.debug2(DEBUG, "squares[" + i + "][" + j + "].Occupant failed");
                        }
                     }
                  }
               }
            }
         }

         if(equal) {
            Log.debug2(DEBUG, "boards are the same");
         }

         return equal;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int hash1 = 31 * hash + fen.boardToString(this).hashCode();
      return hash1;
   }

   public String dumpLegalMoves() {
      return this.dumpLegalMoves(this.isBlackMove);
   }

   public String dumpOpposingMoves() {
      return this.dumpLegalMoves(!this.isBlackMove);
   }

   public String dumpLegalMoves(boolean blacksMoves) {
      StringBuffer sb = new StringBuffer();
      List team = blacksMoves?this.blackTeam:this.whiteTeam;
      if(blacksMoves) {
         sb.append("Black\'s team moves-----------------------\n");
      } else {
         sb.append("White\'s team moves-----------------------\n");
      }

      for(int i = 0; i < team.size(); ++i) {
         ChessPiece p = (ChessPiece)team.get(i);
         sb.append(!p.captured?" ":"x");
         sb.append(p).append("(").append(p.orig).append(") ").append(p.getLegalDests()).append("\n");
      }

      return sb.toString();
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append(this.toString()).append("\n").append("isInitialPositionDefault: ").append(this.isInitialPositionDefault).append("\n").append("isBlackMove: ").append(this.isBlackMove).append("\n").append("enpassant file: ");
      if(this.enpassantFile > 0) {
         sb.append(san.fileToChar(this.enpassantFile));
      } else {
         sb.append("-");
      }

      sb.append("\n").append("lastMove: ").append(this.lastMove).append("\n").append("moveNumber: ").append(this.moveNumber).append("\n").append("plyCount50: ").append(this.plyCount50).append("\n").append("staleLegalDests: ").append(this.staleLegalDests).append("\n");
      sb.append(this.dumpLegalMoves()).append(this.dumpOpposingMoves());
      return sb.toString();
   }
}
