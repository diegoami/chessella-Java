/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.Piece;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public abstract class ChessPiece
/*     */   extends Piece
/*     */ {
/*  40 */   public static byte NULL_PIECE = -1;
/*     */   
/*  42 */   public static byte BLACK_OFFSET = 70;
/*     */   
/*     */ 
/*     */   protected byte index;
/*     */   
/*     */ 
/*     */   protected boolean isBlack;
/*     */   
/*     */ 
/*     */   protected boolean captured;
/*     */   
/*     */ 
/*     */   protected short moveCount;
/*     */   
/*     */ 
/*     */   protected List legalDests;
/*     */   
/*     */ 
/*     */   protected List guardSquares;
/*     */   
/*     */ 
/*     */   protected Square orig;
/*     */   
/*     */   protected ChessBoard board;
/*     */   
/*     */   protected ChessPiece pinnedBy;
/*     */   
/*     */ 
/*     */   public ChessPiece(byte ind, int maxlegaldests, int maxguards)
/*     */   {
/*  72 */     this(ind, false, maxlegaldests, maxguards);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChessPiece(byte ind, boolean _isBlack, int maxlegaldests, int maxguards)
/*     */   {
/*  83 */     this(ind, _isBlack, null, null, maxlegaldests, maxguards);
/*     */   }
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
/*     */   public ChessPiece(byte ind, boolean _isBlack, Square _orig, ChessBoard _board, int maxlegaldests, int maxguards)
/*     */   {
/*  97 */     this.index = ind;
/*  98 */     this.isBlack = _isBlack;
/*  99 */     this.moveCount = 0;
/* 100 */     this.orig = _orig;
/* 101 */     this.board = _board;
/* 102 */     this.legalDests = new ArrayList(maxlegaldests);
/* 103 */     this.guardSquares = new ArrayList(maxguards);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCaptured()
/*     */   {
/* 112 */     return this.captured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCaptured(boolean t)
/*     */   {
/* 119 */     this.legalDests.clear();
/* 120 */     this.guardSquares.clear();
/* 121 */     this.captured = t;
/*     */   }
/*     */   
/* 124 */   public ChessPiece getPinnedBy() { return this.pinnedBy; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBlack()
/*     */   {
/* 134 */     return this.isBlack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBoard(ChessBoard b)
/*     */   {
/* 141 */     this.board = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/* 152 */     this.pinnedBy = null;
/* 153 */     this.legalDests.clear();
/* 154 */     this.guardSquares.clear();
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat)
/*     */   {
/* 166 */     Iterator oldLegals = this.legalDests.iterator();
/* 167 */     Square sq = null;
/*     */     
/* 169 */     if (this.captured) { return;
/*     */     }
/*     */     
/* 172 */     this.legalDests = new ArrayList(3);
/*     */     
/* 174 */     while (oldLegals.hasNext()) {
/* 175 */       sq = (Square)oldLegals.next();
/*     */       
/*     */ 
/* 178 */       if (threat.isBlockable(sq, king)) {
/* 179 */         this.legalDests.add(sq);
/*     */ 
/*     */       }
/* 182 */       else if (sq.equals(threat.getSquare())) {
/* 183 */         this.legalDests.add(sq);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean addLegalDest(Square dest)
/*     */   {
/* 198 */     boolean valid = true;
/* 199 */     if ((dest.isOccupied()) && 
/* 200 */       (dest.piece.isBlack() == isBlack())) {
/* 201 */       this.guardSquares.add(dest);
/* 202 */       valid = false;
/*     */     }
/*     */     else {
/* 205 */       this.legalDests.add(dest);
/*     */     }
/* 207 */     return valid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setPinned(ChessPiece pinner, List lineOfSight)
/*     */   {
/* 215 */     this.pinnedBy = pinner;
/* 216 */     assert (!pinner.isCaptured()) : ("Captured Pinner: " + pinner.dump());
/* 217 */     this.legalDests.retainAll(lineOfSight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLegalDest(Square dest)
/*     */   {
/* 229 */     if (!this.captured) {
/* 230 */       if (this.board.staleLegalDests)
/* 231 */         this.board.genLegalDests();
/* 232 */       return this.legalDests.contains(dest);
/*     */     }
/*     */     
/* 235 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLegalAttack(Square dest)
/*     */   {
/* 243 */     return isLegalDest(dest);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List getLegalDests()
/*     */   {
/* 250 */     if (this.board.staleLegalDests)
/* 251 */       this.board.genLegalDests();
/* 252 */     return this.legalDests;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeLegalDests()
/*     */   {
/* 261 */     this.legalDests.clear();
/* 262 */     this.guardSquares.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getGuardSquares()
/*     */   {
/* 270 */     if (this.board.staleLegalDests)
/* 271 */       this.board.genLegalDests();
/* 272 */     return this.guardSquares;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isGuarding(Square dest)
/*     */   {
/* 279 */     if (!this.captured) {
/* 280 */       if (this.board.staleLegalDests)
/* 281 */         this.board.genLegalDests();
/* 282 */       return this.guardSquares.contains(dest);
/*     */     }
/*     */     
/* 285 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isGuarding(ChessPiece piece)
/*     */   {
/* 292 */     return this.guardSquares.contains(piece.orig);
/*     */   }
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
/*     */   public Square[] getLineOfSight(ChessPiece target, boolean inclusive)
/*     */   {
/* 306 */     return getLineOfSight(target.getSquare().file, 
/* 307 */       target.getSquare().rank, 
/* 308 */       inclusive);
/*     */   }
/*     */   
/* 311 */   public Square[] getLineOfSight(Square target, boolean inclusive) { return getLineOfSight(target.file, target.rank, inclusive); }
/*     */   
/*     */   public Square[] getLineOfSight(int t_file, int t_rank, boolean inclusive)
/*     */   {
/* 315 */     Square[] return_set = (Square[])null;
/* 316 */     Square[] return_tmp = new Square[7];
/* 317 */     byte o_file = getSquare().file;
/* 318 */     byte o_rank = getSquare().rank;
/* 319 */     byte f = 0;byte r = 0;
/* 320 */     byte i = 0;
/*     */     
/*     */ 
/* 323 */     if (o_file == t_file)
/*     */     {
/* 325 */       if (o_rank < t_rank) {
/* 326 */         for (r = (byte)(o_rank + 1); r <= t_rank; r = (byte)(r + 1)) {
/* 327 */           if ((r != t_rank) || (inclusive)) {
/* 328 */             return_tmp[(i++)] = this.board.getSquare(o_file, r);
/*     */           }
/*     */         }
/*     */       } else {
/* 332 */         for (r = (byte)(o_rank - 1); r >= t_rank; r = (byte)(r - 1)) {
/* 333 */           if ((r != t_rank) || (inclusive)) {
/* 334 */             return_tmp[(i++)] = this.board.getSquare(o_file, r);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 339 */     else if (o_rank == t_rank)
/*     */     {
/* 341 */       if (o_file < t_file) {
/* 342 */         for (f = (byte)(o_file + 1); f <= t_file; f = (byte)(f + 1)) {
/* 343 */           if ((f != t_file) || (inclusive)) {
/* 344 */             return_tmp[(i++)] = this.board.getSquare(f, o_rank);
/*     */           }
/*     */         }
/*     */       } else {
/* 348 */         for (f = (byte)(o_file - 1); f >= t_file; f = (byte)(f - 1)) {
/* 349 */           if ((f != t_file) || (inclusive)) {
/* 350 */             return_tmp[(i++)] = this.board.getSquare(f, o_rank);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 355 */     else if (o_file - t_file == o_rank - t_rank)
/*     */     {
/* 357 */       if (o_rank < t_rank) {
/* 358 */         f = (byte)(o_file + 1); for (r = (byte)(o_rank + 1); 
/* 359 */             r <= t_rank; r = (byte)(r + 1)) {
/* 360 */           if ((r != t_rank) || (inclusive)) {
/* 361 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 359 */           f = (byte)(f + 1);
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 365 */         f = (byte)(o_file - 1); for (r = (byte)(o_rank - 1); 
/* 366 */             r >= t_rank; r = (byte)(r - 1)) {
/* 367 */           if ((r != t_rank) || (inclusive)) {
/* 368 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 366 */           f = (byte)(f - 1);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/* 372 */     else if (o_file - t_file == (o_rank - t_rank) * -1)
/*     */     {
/* 374 */       if (o_rank - t_rank < 0) {
/* 375 */         f = (byte)(o_file - 1); for (r = (byte)(o_rank + 1); 
/* 376 */             r <= t_rank; r = (byte)(r + 1)) {
/* 377 */           if ((r != t_rank) || (inclusive)) {
/* 378 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 376 */           f = (byte)(f - 1);
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 382 */         f = (byte)(o_file + 1); for (r = (byte)(o_rank - 1); 
/* 383 */             r >= t_rank; r = (byte)(r - 1)) {
/* 384 */           if ((r != t_rank) || (inclusive)) {
/* 385 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 383 */           f = (byte)(f + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 391 */     if (i != 0) {
/* 392 */       return_set = new Square[i + 1];
/* 393 */       return_set[0] = getSquare();
/* 394 */       System.arraycopy(return_tmp, 0, return_set, 1, i);
/*     */     }
/*     */     
/* 397 */     return return_set;
/*     */   }
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
/*     */   public boolean isBlockable(Square blocker, ChessPiece target)
/*     */   {
/* 411 */     boolean blockable = false;
/* 412 */     Square dest = target.getSquare();
/* 413 */     Square[] lineOfSight = (Square[])null;
/*     */     
/* 415 */     if (this.board.staleLegalDests) {
/* 416 */       this.board.genLegalDests();
/*     */     }
/*     */     
/* 419 */     if (!isLegalDest(dest)) {
/* 420 */       throw new IllegalArgumentException(this + 
/* 421 */         "cannot be blocked for illegal destination square (" + 
/* 422 */         dest + ")");
/*     */     }
/* 424 */     lineOfSight = getLineOfSight(target, false);
/* 425 */     int i = 0;
/* 426 */     while ((!blockable) && (lineOfSight != null) && (i < lineOfSight.length)) {
/* 427 */       blockable = blocker.equals(lineOfSight[(i++)]);
/*     */     }
/* 429 */     return blockable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void adjustPinsLegalDests(ChessPiece king, List enemyTeam) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte getIndex()
/*     */   {
/* 444 */     return (byte)(this.index + (this.isBlack ? BLACK_OFFSET : 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static boolean isBlackIndex(byte i)
/*     */   {
/* 451 */     return i > BLACK_OFFSET;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Square getSquare()
/*     */   {
/* 460 */     return this.orig;
/*     */   }
/*     */   
/*     */   public Board getBoard()
/*     */   {
/* 465 */     return this.board;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 469 */     return getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract String getName();
/*     */   
/*     */ 
/*     */ 
/* 479 */   public boolean isKing() { return false; }
/* 480 */   public boolean isQueen() { return false; }
/* 481 */   public boolean isRook() { return false; }
/* 482 */   public boolean isBishop() { return false; }
/* 483 */   public boolean isKnight() { return false; }
/* 484 */   public boolean isPawn() { return false; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ChessPiece toChessPiece(int i)
/*     */   {
/* 491 */     ChessPiece p = null;
/* 492 */     switch (i % BLACK_OFFSET) {
/* 493 */     case 0:  p = new King(); break;
/* 494 */     case 1:  p = new Queen(); break;
/* 495 */     case 2:  p = new Rook(); break;
/* 496 */     case 3:  p = new Bishop(); break;
/* 497 */     case 4:  p = new Knight(); break;
/* 498 */     case 5:  p = new Pawn(); break;
/*     */     default: 
/* 500 */       throw new IllegalArgumentException(
/* 501 */         "Illegal ChessPiece INDEX(" + i + ")");
/*     */     }
/* 503 */     return p;
/*     */   }
/*     */   
/*     */   public String dump() {
/* 507 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 509 */     sb.append(getName())
/* 510 */       .append(" captured: ").append(this.captured)
/* 511 */       .append(" square: ").append(this.orig)
/* 512 */       .append(" legalDests: ").append(this.legalDests)
/* 513 */       .append(" guardSquares: ").append(this.guardSquares)
/* 514 */       .append(" pinnedBy: ").append(this.pinnedBy);
/* 515 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessPiece.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */