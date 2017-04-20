/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Location;
/*     */ import ictk.boardgame.Piece;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Square
/*     */   implements Location, Cloneable
/*     */ {
/*     */   public ChessPiece piece;
/*     */   byte file;
/*     */   byte rank;
/*     */   
/*     */   public Square() {}
/*     */   
/*     */   public Square(byte f, byte r)
/*     */   {
/*  46 */     this.file = f;
/*  47 */     this.rank = r;
/*     */   }
/*     */   
/*     */   public Square(char f, char r) {
/*  51 */     this.file = ChessBoard.san.fileToNum(f);
/*  52 */     this.rank = ChessBoard.san.rankToNum(r);
/*     */   }
/*     */   
/*     */ 
/*  56 */   public int getX() { return this.file; }
/*  57 */   public int getY() { return this.rank; }
/*     */   
/*     */ 
/*  60 */   public void setX(int x) { this.file = ((byte)x); }
/*  61 */   public void setY(int y) { this.rank = ((byte)y); }
/*     */   
/*     */ 
/*     */ 
/*     */   public Piece getPiece()
/*     */   {
/*  67 */     return this.piece;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Piece setPiece(Piece p)
/*     */   {
/*  75 */     ChessPiece old_p = this.piece;
/*  76 */     this.piece = ((ChessPiece)p);
/*     */     
/*  78 */     return old_p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isBlack()
/*     */   {
/*  85 */     return this.file % 2 == this.rank % 2;
/*     */   }
/*     */   
/*     */   public byte getFile()
/*     */   {
/*  90 */     return this.file;
/*     */   }
/*     */   
/*     */   public byte getRank() {
/*  94 */     return this.rank;
/*     */   }
/*     */   
/*     */ 
/*     */   public char getFileAsChar()
/*     */   {
/* 100 */     return ChessBoard.san.fileToChar(this.file);
/*     */   }
/*     */   
/*     */ 
/*     */   public char getRankAsChar()
/*     */   {
/* 106 */     return ChessBoard.san.rankToChar(this.rank);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isOccupied()
/*     */   {
/* 113 */     return this.piece != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean setOccupant(ChessPiece p)
/*     */   {
/* 120 */     boolean wasOccupied = isOccupied();
/*     */     
/* 122 */     this.piece = p;
/* 123 */     this.piece.orig = this;
/* 124 */     return wasOccupied;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ChessPiece getOccupant()
/*     */   {
/* 131 */     return this.piece;
/*     */   }
/*     */   
/*     */   public void set(byte f, byte r) {
/* 135 */     this.file = f;
/* 136 */     this.rank = r;
/*     */   }
/*     */   
/*     */   public void set(char f, char r) {
/* 140 */     this.file = ChessBoard.san.fileToNum(f);
/* 141 */     this.rank = ChessBoard.san.rankToNum(r);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] getCoordinatesNumeric()
/*     */   {
/* 149 */     int[] coords = new int[2];
/*     */     
/* 151 */     coords[0] = this.file;
/* 152 */     coords[1] = this.rank;
/*     */     
/* 154 */     return coords;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 162 */     if (this == obj) return true;
/* 163 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 164 */       return false;
/*     */     }
/* 166 */     Square s = (Square)obj;
/* 167 */     return (this.file == s.file) && (this.rank == s.rank);
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 172 */     int hash = 5;
/*     */     
/* 174 */     hash = 31 * hash + this.file;
/* 175 */     hash = 31 * hash + 10 * this.rank;
/*     */     
/* 177 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 181 */     return getFileAsChar() + getRankAsChar();
/*     */   }
/*     */   
/*     */   public String dump() {
/* 185 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 187 */     sb.append("file=").append(this.file)
/* 188 */       .append(" rank=").append(this.rank)
/* 189 */       .append(" cfile=").append(getFileAsChar())
/* 190 */       .append(" crank=").append(getRankAsChar())
/* 191 */       .append(" piece=").append(this.piece);
/* 192 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Square.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */