/*     */ package ictk.boardgame.chess;
/*     */ 
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
/*     */ public class King
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 0;
/*     */   protected static final int MAX_LEGAL_DESTS = 8;
/*     */   protected static final int MAX_GUARDS = 8;
/*     */   
/*     */   public King()
/*     */   {
/*  39 */     super((byte)0, true, 8, 8);
/*     */   }
/*     */   
/*     */   public King(boolean blackness) {
/*  43 */     super((byte)0, blackness, 8, 8);
/*     */   }
/*     */   
/*     */   public King(boolean blackness, Square o, ChessBoard _board) {
/*  47 */     super((byte)0, blackness, o, _board, 8, 8);
/*     */   }
/*     */   
/*  50 */   protected String getName() { return "King"; }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/*  56 */     super.genLegalDests();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */     for (byte f = (byte)(-1 + this.orig.file); f <= 1 + this.orig.file; f = (byte)(f + 1)) {
/*  66 */       for (byte r = (byte)(-1 + this.orig.rank); 
/*  67 */           r <= 1 + this.orig.rank; 
/*  68 */           r = (byte)(r + 1)) {
/*  69 */         if ((this.board.isFileValid(f)) && (this.board.isRankValid(r))) {
/*  70 */           addLegalDest(this.board.getSquare(f, r));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  76 */     return this.legalDests.size();
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
/*     */   protected int genLegalDestsFinal()
/*     */   {
/*  89 */     boolean blocked = false;
/*     */     
/*  91 */     List tmpLegalDests = this.legalDests;
/*  92 */     Iterator perlimMoves = tmpLegalDests.iterator();
/*     */     
/*  94 */     this.legalDests = new ArrayList(8);
/*     */     
/*     */ 
/*  97 */     while (perlimMoves.hasNext()) {
/*  98 */       Square dest = (Square)perlimMoves.next();
/*     */       
/* 100 */       if (!this.board.isThreatened(dest, !this.isBlack)) {
/* 101 */         if (!this.board.isGuarded(dest, !this.isBlack)) {
/* 102 */           addLegalDest(dest);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */     if (this.moveCount == 0)
/*     */     {
/*     */ 
/* 114 */       ChessPiece rook = this.board.getSquare(1, this.orig.rank).piece;
/* 115 */       if ((rook != null) && 
/* 116 */         (rook.moveCount == 0)) {
/* 117 */         blocked = false;
/*     */         
/* 119 */         for (byte f = (byte)(rook.orig.file + 1); 
/* 120 */             (f <= this.orig.file) && (!blocked); f = (byte)(f + 1))
/*     */         {
/*     */ 
/* 123 */           if (f < this.orig.file) {
/* 124 */             blocked = 
/* 125 */               this.board.getSquare(f, this.orig.rank).isOccupied();
/*     */           }
/*     */           
/* 128 */           if ((!blocked) && (f >= this.orig.file - 2)) {
/* 129 */             blocked = this.board.isThreatened(
/* 130 */               this.board.getSquare(f, this.orig.rank), 
/* 131 */               !this.isBlack);
/*     */           }
/*     */         }
/* 134 */         if (!blocked) {
/* 135 */           addLegalDest(getQueensideCastleSquare());
/*     */         }
/*     */       }
/*     */       
/* 139 */       rook = this.board.getSquare(this.board.getMaxFile(), this.orig.rank).piece;
/* 140 */       if ((rook != null) && 
/* 141 */         (rook.moveCount == 0)) {
/* 142 */         blocked = false;
/*     */         
/*     */ 
/* 145 */         for (byte f = (byte)(rook.orig.file - 1); 
/* 146 */             (f >= this.orig.file) && (!blocked); f = (byte)(f - 1)) {
/* 147 */           if (f > this.orig.file) {
/* 148 */             blocked = 
/* 149 */               this.board.getSquare(f, this.orig.rank).isOccupied();
/*     */           }
/* 151 */           if (!blocked) {
/* 152 */             blocked = this.board.isThreatened(
/* 153 */               this.board.getSquare(f, this.orig.rank), 
/* 154 */               !this.isBlack);
/*     */           }
/*     */         }
/* 157 */         if (!blocked) {
/* 158 */           addLegalDest(getKingsideCastleSquare());
/*     */         }
/*     */       }
/*     */     }
/* 162 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */   public Square getQueensideCastleSquare()
/*     */   {
/* 167 */     return this.board.getSquare('c', this.isBlack ? '8' : '1');
/*     */   }
/*     */   
/*     */   public Square getKingsideCastleSquare()
/*     */   {
/* 172 */     return this.board.getSquare('g', this.isBlack ? '8' : '1');
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isInCheck()
/*     */   {
/* 187 */     return this.board.isThreatened(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isCastleableQueenside()
/*     */   {
/* 194 */     ChessPiece rook = findMyRook(true);
/* 195 */     return (this.moveCount == 0) && (rook != null) && (rook.moveCount == 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCastleableKingside()
/*     */   {
/* 204 */     ChessPiece rook = findMyRook(false);
/* 205 */     return (this.moveCount == 0) && (rook != null) && (rook.moveCount == 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCastleableQueenside(boolean t)
/*     */   {
/* 215 */     ChessPiece rook = findMyRook(true);
/* 216 */     if ((rook == null) && (t))
/* 217 */       throw new IllegalStateException(
/* 218 */         "can't set castleable when there's no rook on that side of the board.");
/* 219 */     if (t) {
/* 220 */       this.moveCount = 0;
/* 221 */       rook.moveCount = 0;
/*     */     }
/* 223 */     else if ((rook != null) && (rook.moveCount == 0)) {
/* 224 */       rook.moveCount = 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCastleableKingside(boolean t)
/*     */   {
/* 234 */     ChessPiece rook = findMyRook(false);
/* 235 */     if ((rook == null) && (t))
/* 236 */       throw new IllegalStateException(
/* 237 */         "can't set castleable when there's no rook on that side of the board.");
/* 238 */     if (t) {
/* 239 */       this.moveCount = 0;
/* 240 */       rook.moveCount = 0;
/*     */     }
/* 242 */     else if ((rook != null) && (rook.moveCount == 0)) {
/* 243 */       rook.moveCount = 1;
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
/*     */ 
/*     */   protected Rook findMyRook(boolean qside)
/*     */   {
/* 258 */     byte file = 1;
/* 259 */     ChessPiece p = null;
/* 260 */     if (qside) {
/* 261 */       file = 1;
/* 262 */       do { p = this.board.getSquare(file, this.orig.rank).piece;
/* 263 */         if ((p == null) || (!p.isRook()) || (p.isBlack() != isBlack())) {
/* 264 */           p = null;
/*     */         }
/* 261 */         file = (byte)(file + 1); if (p != null) break; } while (file < this.orig.file);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 269 */       for (file = 8; (p == null) && (file > this.orig.file); file = (byte)(file - 1)) {
/* 270 */         p = this.board.getSquare(file, this.orig.rank).piece;
/* 271 */         if ((p == null) || (!p.isRook()) || (p.isBlack() != isBlack()))
/* 272 */           p = null;
/*     */       }
/*     */     }
/* 275 */     return (Rook)p;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlockable(Square target)
/*     */   {
/* 281 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBlockable(Square blocker, ChessPiece target)
/*     */   {
/* 286 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLegalAttack(Square target)
/*     */   {
/* 294 */     if (Math.abs(target.file - this.orig.file) == 2) {
/* 295 */       return false;
/*     */     }
/*     */     
/* 298 */     return isLegalDest(target);
/*     */   }
/*     */   
/*     */   public boolean isKing()
/*     */   {
/* 303 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\King.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */