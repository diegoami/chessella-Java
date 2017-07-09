package ictk.boardgame.chess;

import ictk.boardgame.Location;
import ictk.boardgame.Piece;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;

public class Square implements Location, Cloneable {

   public ChessPiece piece;
   byte file;
   byte rank;


   public Square() {}

   public Square(byte f, byte r) {
      this.file = f;
      this.rank = r;
   }

   public Square(char f, char r) {
      this.file = ChessBoard.san.fileToNum(f);
      this.rank = ChessBoard.san.rankToNum(r);
   }

   public int getX() {
      return this.file;
   }

   public int getY() {
      return this.rank;
   }

   public void setX(int x) {
      this.file = (byte)x;
   }

   public void setY(int y) {
      this.rank = (byte)y;
   }

   public Piece getPiece() {
      return this.piece;
   }

   public Piece setPiece(Piece p) {
      ChessPiece old_p = this.piece;
      this.piece = (ChessPiece)p;
      return old_p;
   }

   public boolean isBlack() {
      return this.file % 2 == this.rank % 2;
   }

   public byte getFile() {
      return this.file;
   }

   public byte getRank() {
      return this.rank;
   }

   public char getFileAsChar() {
      return ChessBoard.san.fileToChar(this.file);
   }

   public char getRankAsChar() {
      return ChessBoard.san.rankToChar(this.rank);
   }

   public boolean isOccupied() {
      return this.piece != null;
   }

   public boolean setOccupant(ChessPiece p) {
      boolean wasOccupied = this.isOccupied();
      this.piece = p;
      this.piece.orig = this;
      return wasOccupied;
   }

   public ChessPiece getOccupant() {
      return this.piece;
   }

   public void set(byte f, byte r) {
      this.file = f;
      this.rank = r;
   }

   public void set(char f, char r) {
      this.file = ChessBoard.san.fileToNum(f);
      this.rank = ChessBoard.san.rankToNum(r);
   }

   public int[] getCoordinatesNumeric() {
      int[] coords = new int[]{this.file, this.rank};
      return coords;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj != null && obj.getClass() == this.getClass()) {
         Square s = (Square)obj;
         return this.file == s.file && this.rank == s.rank;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 5;
      int hash1 = 31 * hash + this.file;
      hash1 = 31 * hash1 + 10 * this.rank;
      return hash1;
   }

   public String toString() {
      return "" + this.getFileAsChar() + this.getRankAsChar();
   }

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("file=").append(this.file).append(" rank=").append(this.rank).append(" cfile=").append(this.getFileAsChar()).append(" crank=").append(this.getRankAsChar()).append(" piece=").append(this.piece);
      return sb.toString();
   }
}
