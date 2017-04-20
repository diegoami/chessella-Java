/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public class Rook
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 2;
/*     */   protected static final int MAX_LEGAL_DESTS = 14;
/*     */   protected static final int MAX_GUARDS = 14;
/*     */   
/*     */   public Rook()
/*     */   {
/*  37 */     super((byte)2, true, 14, 14);
/*     */   }
/*     */   
/*     */   public Rook(boolean blackness) {
/*  41 */     super((byte)2, blackness, 14, 14);
/*     */   }
/*     */   
/*     */   public Rook(boolean blackness, Square o, ChessBoard _board) {
/*  45 */     super((byte)2, blackness, o, _board, 14, 14);
/*     */   }
/*     */   
/*  48 */   protected String getName() { return "Rook"; }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/*  54 */     super.genLegalDests();
/*     */     
/*     */ 
/*     */ 
/*  58 */     if (this.captured) { return 0;
/*     */     }
/*     */     
/*  61 */     boolean done = false;
/*     */     
/*     */ 
/*     */ 
/*  65 */     for (int r = this.orig.rank + 1; (r <= 8) && (!done); r++) {
/*  66 */       Square dest = this.board.getSquare(this.orig.file, r);
/*  67 */       done = !addLegalDest(dest);
/*     */       
/*  69 */       done = (done) || ((dest.isOccupied()) && (
/*  70 */         (this.board.isBlackMove == this.isBlack) || 
/*  71 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/*  75 */     done = false;
/*     */     
/*  77 */     for (int f = this.orig.file + 1; (f <= 8) && (!done); f++) {
/*  78 */       Square dest = this.board.getSquare(f, this.orig.rank);
/*  79 */       done = !addLegalDest(dest);
/*     */       
/*  81 */       done = (done) || ((dest.isOccupied()) && (
/*  82 */         (this.board.isBlackMove == this.isBlack) || 
/*  83 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/*  87 */     done = false;
/*     */     
/*  89 */     for (int r = this.orig.rank - 1; (r >= 1) && (!done); r--) {
/*  90 */       Square dest = this.board.getSquare(this.orig.file, r);
/*  91 */       done = !addLegalDest(dest);
/*     */       
/*  93 */       done = (done) || ((dest.isOccupied()) && (
/*  94 */         (this.board.isBlackMove == this.isBlack) || 
/*  95 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/*  99 */     done = false;
/*     */     
/* 101 */     for (int f = this.orig.file - 1; (f >= 1) && (!done); f--) {
/* 102 */       Square dest = this.board.getSquare(f, this.orig.rank);
/* 103 */       done = !addLegalDest(dest);
/*     */       
/* 105 */       done = (done) || ((dest.isOccupied()) && (
/* 106 */         (this.board.isBlackMove == this.isBlack) || 
/* 107 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/* 110 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */   public void adjustPinsLegalDests(ChessPiece king, List enemyTeam)
/*     */   {
/* 115 */     Square[] line = getLineOfSight(king, false);
/* 116 */     ChessPiece pin = null;
/* 117 */     boolean done = false;
/*     */     
/* 119 */     if (this.captured) { return;
/*     */     }
/* 121 */     if (line != null) {
/* 122 */       if (this.board.staleLegalDests) {
/* 123 */         this.board.genLegalDests();
/*     */       }
/*     */       
/* 126 */       for (int i = 1; (i < line.length) && (!done); i++)
/*     */       {
/* 128 */         ChessPiece tmp = line[i].getOccupant();
/*     */         
/* 130 */         if (tmp != null)
/*     */         {
/*     */ 
/* 133 */           if (pin != null) {
/* 134 */             pin = null;
/* 135 */             done = true;
/*     */ 
/*     */ 
/*     */           }
/* 139 */           else if (tmp.isBlack == isBlack()) {
/* 140 */             done = true;
/*     */           }
/*     */           else
/*     */           {
/* 144 */             pin = tmp;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 150 */       if (pin != null)
/*     */       {
/* 152 */         List maintainPins = Arrays.asList(line);
/* 153 */         pin.setPinned(this, maintainPins);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Square[] getLineOfSight(ChessPiece target, boolean inclusive)
/*     */   {
/* 161 */     return getLineOfSight(target.getSquare().file, 
/* 162 */       target.getSquare().rank, 
/* 163 */       inclusive);
/*     */   }
/*     */   
/* 166 */   public Square[] getLineOfSight(Square target, boolean inclusive) { return getLineOfSight(target.file, target.rank, inclusive); }
/*     */   
/*     */   public Square[] getLineOfSight(int t_file, int t_rank, boolean inclusive)
/*     */   {
/* 170 */     Square[] return_set = (Square[])null;
/* 171 */     Square[] return_tmp = new Square[7];
/* 172 */     int o_file = getSquare().file;
/* 173 */     int o_rank = getSquare().rank;
/* 174 */     int f = 0;int r = 0;
/* 175 */     int i = 0;
/*     */     
/*     */ 
/* 178 */     if (o_file == t_file)
/*     */     {
/* 180 */       if (o_rank < t_rank) {
/* 181 */         for (r = o_rank + 1; r <= t_rank; r++) {
/* 182 */           if ((r != t_rank) || (inclusive)) {
/* 183 */             return_tmp[(i++)] = this.board.getSquare(o_file, r);
/*     */           }
/*     */         }
/*     */       } else {
/* 187 */         for (r = o_rank - 1; r >= t_rank; r--) {
/* 188 */           if ((r != t_rank) || (inclusive)) {
/* 189 */             return_tmp[(i++)] = this.board.getSquare(o_file, r);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 194 */     else if (o_rank == t_rank)
/*     */     {
/* 196 */       if (o_file < t_file) {
/* 197 */         for (f = o_file + 1; f <= t_file; f++) {
/* 198 */           if ((f != t_file) || (inclusive)) {
/* 199 */             return_tmp[(i++)] = this.board.getSquare(f, o_rank);
/*     */           }
/*     */         }
/*     */       } else {
/* 203 */         for (f = o_file - 1; f >= t_file; f--) {
/* 204 */           if ((f != t_file) || (inclusive)) {
/* 205 */             return_tmp[(i++)] = this.board.getSquare(f, o_rank);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 211 */     if (i != 0) {
/* 212 */       return_set = new Square[i + 1];
/* 213 */       return_set[0] = getSquare();
/* 214 */       System.arraycopy(return_tmp, 0, return_set, 1, i);
/*     */     }
/*     */     
/* 217 */     return return_set;
/*     */   }
/*     */   
/* 220 */   public boolean isRook() { return true; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Rook.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */