package ictk.boardgame.chess.io;

import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.io.MoveNotation;
import ictk.util.Log;
import java.util.Locale;

public abstract class ChessMoveNotation implements MoveNotation {

   public static final long DEBUG = Log.MoveNotation;
   public static final char[][] PIECE_SETS = new char[][]{{'P', 'N', 'B', 'R', 'Q', 'K'}, {'P', 'J', 'S', 'V', 'D', 'K'}, {'B', 'S', 'L', 'T', 'D', 'K'}, {'P', 'R', 'O', 'V', 'L', 'K'}, {'P', 'R', 'L', 'T', 'D', 'K'}, {'P', 'C', 'F', 'T', 'D', 'R'}, {'B', 'S', 'L', 'T', 'D', 'K'}, {'G', 'H', 'F', 'B', 'V', 'K'}, {'P', 'R', 'B', 'H', 'D', 'K'}, {'P', 'C', 'A', 'T', 'D', 'R'}, {'B', 'S', 'L', 'T', 'D', 'K'}, {'P', 'S', 'G', 'W', 'H', 'K'}, {'P', 'C', 'B', 'T', 'D', 'R'}, {'P', 'C', 'N', 'T', 'D', 'R'}, {'P', 'C', 'A', 'T', 'D', 'R'}, {'B', 'S', 'L', 'T', 'D', 'K'}, {'P', 'S', 'L', 'T', 'D', 'K'}};
   public static final char[][] FILE_SETS = new char[][]{{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}};
   public static final char[][] RANK_SETS = new char[][]{{'1', '2', '3', '4', '5', '6', '7', '8'}};
   protected char[] pieceSet;
   protected char[] fileSet;
   protected char[] rankSet;
   protected Locale locale;


   public ChessMoveNotation() {
      this((Locale)null);
   }

   public ChessMoveNotation(Locale loc) {
      this.pieceSet = PIECE_SETS[0];
      this.fileSet = FILE_SETS[0];
      this.rankSet = RANK_SETS[0];
      this.locale = loc;
      this.setLocale(this.locale);
   }

   public boolean setLocale(Locale loc) {
      String lang = null;
      if(loc != null && !"eng".equals(lang = loc.getISO3Language())) {
         if("gem".equals(lang)) {
            this.pieceSet = PIECE_SETS[6];
         } else if("fra".equals(lang)) {
            this.pieceSet = PIECE_SETS[5];
         } else if("ces".equals(lang)) {
            this.pieceSet = PIECE_SETS[1];
         } else if("dan".equals(lang)) {
            this.pieceSet = PIECE_SETS[2];
         } else if("est".equals(lang)) {
            this.pieceSet = PIECE_SETS[3];
         } else if("fin".equals(lang)) {
            this.pieceSet = PIECE_SETS[4];
         } else if("hun".equals(lang)) {
            this.pieceSet = PIECE_SETS[7];
         } else if("isl".equals(lang)) {
            this.pieceSet = PIECE_SETS[8];
         } else if("ita".equals(lang)) {
            this.pieceSet = PIECE_SETS[9];
         } else if("nor".equals(lang)) {
            this.pieceSet = PIECE_SETS[10];
         } else if("nor".equals(lang)) {
            this.pieceSet = PIECE_SETS[10];
         } else if("pol".equals(lang)) {
            this.pieceSet = PIECE_SETS[11];
         } else if("por".equals(lang)) {
            this.pieceSet = PIECE_SETS[12];
         } else if("ron".equals(lang)) {
            this.pieceSet = PIECE_SETS[13];
         } else if("spa".equals(lang)) {
            this.pieceSet = PIECE_SETS[14];
         } else if("swe".equals(lang)) {
            this.pieceSet = PIECE_SETS[15];
         } else if("swe".equals(lang)) {
            this.pieceSet = PIECE_SETS[15];
         } else {
            if(!"hrv".equals(lang)) {
               return false;
            }

            this.pieceSet = PIECE_SETS[16];
         }
      } else {
         this.pieceSet = PIECE_SETS[0];
      }

      return true;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public char[] getPieceSet() {
      return this.pieceSet;
   }

   public void setPieceSet(char[] set) {
      if(set.length != 6) {
         throw new IllegalArgumentException("The set must contain 6 characters in the order of [PNBRQK].");
      } else {
         this.pieceSet = set;
      }
   }

   public byte pieceToNum(String s) {
      return this.pieceToNum(s.charAt(0));
   }

   public byte pieceToNum(char c) {
      byte p = ChessPiece.NULL_PIECE;
      c = Character.toUpperCase(c);
      if(c != this.pieceSet[0] && c != 32) {
         if(c == this.pieceSet[1]) {
            p = 4;
         } else if(c == this.pieceSet[2]) {
            p = 3;
         } else if(c == this.pieceSet[3]) {
            p = 2;
         } else if(c == this.pieceSet[4]) {
            p = 1;
         } else if(c == this.pieceSet[5]) {
            p = 0;
         }
      } else {
         p = 5;
      }

      if(p == ChessPiece.NULL_PIECE) {
         Log.debug(DEBUG, "unknown piece: <" + c + ">");
         throw new ArrayIndexOutOfBoundsException("Unknown ChessPiece");
      } else {
         return p;
      }
   }

   public char pieceToChar(int p) {
      boolean c = true;
      char c1;
      switch(p % ChessPiece.BLACK_OFFSET) {
      case 0:
         c1 = this.pieceSet[5];
         break;
      case 1:
         c1 = this.pieceSet[4];
         break;
      case 2:
         c1 = this.pieceSet[3];
         break;
      case 3:
         c1 = this.pieceSet[2];
         break;
      case 4:
         c1 = this.pieceSet[1];
         break;
      case 5:
         c1 = this.pieceSet[0];
         break;
      default:
         Log.debug(DEBUG, "unknown piece index: <" + p + ">");
         throw new ArrayIndexOutOfBoundsException("Unknown ChessPiece");
      }

      return c1;
   }

   public char pieceToChar(ChessPiece p) {
      return this.pieceToChar(p.getIndex());
   }

   public byte fileToNum(String s) {
      return this.fileToNum(s.charAt(0));
   }

   public byte fileToNum(char c) {
      boolean p = false;
      byte p1 = this.mapSetToNum(Character.toLowerCase(c), this.fileSet);
      if(p1 == 0) {
         if(c != 45 && c != 32) {
            Log.debug(DEBUG, "unknown file: <" + c + ">");
            throw new ArrayIndexOutOfBoundsException("file out of range: " + c);
         } else {
            return p1;
         }
      } else {
         return p1;
      }
   }

   public byte rankToNum(char c) {
      boolean p = false;
      byte p1 = this.mapSetToNum(Character.toLowerCase(c), this.rankSet);
      if(p1 == 0) {
         if(c != 45 && c != 32) {
            Log.debug(DEBUG, "unknown file: <" + c + ">");
            throw new ArrayIndexOutOfBoundsException("rank out of range: " + c);
         } else {
            return p1;
         }
      } else {
         return p1;
      }
   }

   public char fileToChar(int i) {
      boolean c = true;
      if(i > 0 && i <= this.fileSet.length) {
         char c1 = this.fileSet[i - 1];
         return c1;
      } else {
         Log.debug(DEBUG, "file out of range: <" + i + ">");
         throw new ArrayIndexOutOfBoundsException("file out of range (" + i + ")");
      }
   }

   public char rankToChar(int i) {
      boolean c = true;
      if(i > 0 && i <= this.rankSet.length) {
         char c1 = this.rankSet[i - 1];
         return c1;
      } else {
         Log.debug(DEBUG, "rank out of range: <" + i + ">");
         throw new ArrayIndexOutOfBoundsException("rank out of range");
      }
   }

   protected byte mapSetToNum(char c, char[] set) {
      byte f = 0;

      for(byte i = 0; f == 0 && i < set.length; ++i) {
         if(c == set[i]) {
            f = (byte)(i + 1);
         }
      }

      return f;
   }
}
