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
/*     */ public class Pawn
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 5;
/*     */   protected static final int MAX_LEGAL_DESTS = 4;
/*     */   protected static final int MAX_GUARDS = 2;
/*     */   
/*     */   public Pawn()
/*     */   {
/*  39 */     super((byte)5, true, 4, 2);
/*     */   }
/*     */   
/*     */   public Pawn(boolean blackness) {
/*  43 */     super((byte)5, blackness, 4, 2);
/*     */   }
/*     */   
/*     */   public Pawn(boolean blackness, Square o, ChessBoard _board) {
/*  47 */     super((byte)5, blackness, o, _board, 4, 2);
/*     */   }
/*     */   
/*     */   protected String getName() {
/*  51 */     return "Pawn";
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
/*     */   protected int genLegalDests()
/*     */   {
/*  65 */     super.genLegalDests();
/*  66 */     Square dest = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */     if (this.captured) { return 0;
/*     */     }
/*  74 */     byte dir = this.isBlack ? -1 : 1;
/*     */     
/*     */ 
/*  77 */     if (this.board.isRankValid((byte)(this.orig.rank + 1 * dir))) {
/*  78 */       dest = this.board.getSquare(this.orig.file, 
/*  79 */         (byte)(this.orig.rank + 1 * dir));
/*  80 */       if (!dest.isOccupied()) {
/*  81 */         addLegalDest(dest);
/*     */       }
/*     */     }
/*     */     byte rank;
/*  85 */     if (this.board.isRankValid(rank = (byte)(this.orig.rank + 1 * dir))) {
/*  86 */       byte file = this.orig.file;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  91 */       if ((this.board.isFileValid((byte)(file + 1))) && (
/*  92 */         ((this.board.getSquare((byte)(file + 1), rank).isOccupied()) && 
/*     */         
/*     */ 
/*  95 */         (this.isBlack != this.board.getSquare((byte)(file + 1), rank).getOccupant().isBlack())) || 
/*  96 */         (this.board.isBlackMove != this.isBlack)))
/*     */       {
/*     */ 
/*  99 */         addLegalDest(this.board
/* 100 */           .getSquare((byte)(file + 1), rank)); }
/* 101 */       if ((this.board.isFileValid((byte)(file - 1))) && (
/* 102 */         ((this.board.getSquare((byte)(file - 1), rank).isOccupied()) && 
/*     */         
/*     */ 
/* 105 */         (this.isBlack != this.board.getSquare((byte)(file - 1), rank).getOccupant().isBlack())) || 
/* 106 */         (this.board.isBlackMove != this.isBlack)))
/*     */       {
/*     */ 
/* 109 */         addLegalDest(this.board
/* 110 */           .getSquare((byte)(file - 1), rank));
/*     */       }
/*     */     }
/*     */     
/* 114 */     if ((!hasMoved()) && 
/* 115 */       (this.board.isRankValid((byte)(this.orig.rank + 2 * dir)))) {
/* 116 */       dest = this.board.getSquare(this.orig.file, 
/* 117 */         (byte)(this.orig.rank + 2 * dir));
/* 118 */       if (!dest.isOccupied())
/*     */       {
/* 120 */         if (!this.board.getSquare(this.orig.file, (byte)(this.orig.rank + 1 * dir)).isOccupied()) {
/* 121 */           addLegalDest(dest);
/*     */         }
/*     */       }
/*     */     }
/* 125 */     if ((this.isBlack == this.board.isBlackMove) && 
/* 126 */       (onEnPassantRank())) {
/* 127 */       byte file = this.orig.file;
/*     */       
/* 129 */       if ((this.board.isFileValid((byte)(file + 1))) && 
/* 130 */         (this.board.isEnPassantFile((byte)(file + 1)))) {
/* 131 */         addLegalDest(this.board.getSquare((byte)(file + 1), rank));
/*     */       }
/* 133 */       if ((this.board.isFileValid((byte)(file - 1))) && 
/* 134 */         (this.board.isEnPassantFile((byte)(file - 1)))) {
/* 135 */         addLegalDest(this.board.getSquare((byte)(file - 1), rank));
/*     */       }
/*     */     }
/* 138 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void genLegalDestsSaveKing(ChessPiece king, ChessPiece threat)
/*     */   {
/* 148 */     Iterator oldLegals = this.legalDests.iterator();
/* 149 */     Square sq = null;
/*     */     
/* 151 */     if (this.captured) { return;
/*     */     }
/* 153 */     this.legalDests = new ArrayList(2);
/*     */     
/* 155 */     while (oldLegals.hasNext()) {
/* 156 */       sq = (Square)oldLegals.next();
/*     */       
/*     */ 
/* 159 */       if (threat.isBlockable(sq, king)) {
/* 160 */         this.legalDests.add(sq);
/*     */ 
/*     */       }
/* 163 */       else if (sq.equals(threat.getSquare())) {
/* 164 */         this.legalDests.add(sq);
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 169 */       else if ((threat.isPawn()) && 
/* 170 */         (threat.getSquare().getFile() == 
/* 171 */         this.board.getEnPassantFile())) {
/* 172 */         if (sq.getFile() == 
/* 173 */           this.board.getEnPassantFile())
/*     */         {
/* 175 */           this.legalDests.add(sq);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onEnPassantRank()
/*     */   {
/* 184 */     return ((!this.isBlack) && (this.orig.rank == 5)) || ((this.isBlack) && (this.orig.rank == 4));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlockable(Square target)
/*     */   {
/* 190 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBlockable(Square blocker, ChessPiece target)
/*     */   {
/* 196 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isLegalAttack(Square target)
/*     */   {
/* 202 */     if (this.board.staleLegalDests) {
/* 203 */       this.board.genLegalDests();
/*     */     }
/* 205 */     if (target.file == this.orig.file) {
/* 206 */       return false;
/*     */     }
/*     */     
/* 209 */     return isLegalDest(target);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean hasMoved()
/*     */   {
/* 215 */     if (((this.isBlack) && (this.orig.rank == 7)) || (
/* 216 */       (!this.isBlack) && (this.orig.rank == 2))) {
/* 217 */       return false;
/*     */     }
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isPromotionSquare(Square sq, boolean isBlack)
/*     */   {
/* 228 */     if ((isBlack) && (sq.rank == 1)) return true;
/* 229 */     if ((!isBlack) && (sq.rank == 8)) return true;
/* 230 */     return false;
/*     */   }
/*     */   
/* 233 */   public boolean isPawn() { return true; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Pawn.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */