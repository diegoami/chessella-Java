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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Bishop
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 3;
/*     */   protected static final int MAX_LEGAL_DESTS = 13;
/*     */   protected static final int MAX_GUARDS = 13;
/*     */   
/*     */   public Bishop()
/*     */   {
/*  42 */     super((byte)3, true, 13, 13);
/*     */   }
/*     */   
/*     */   public Bishop(boolean blackness) {
/*  46 */     super((byte)3, blackness, 13, 13);
/*     */   }
/*     */   
/*     */   public Bishop(boolean blackness, Square o, ChessBoard _board) {
/*  50 */     super((byte)3, blackness, o, _board, 13, 13);
/*     */   }
/*     */   
/*     */ 
/*     */   protected String getName()
/*     */   {
/*  56 */     return "Bishop";
/*     */   }
/*     */   
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/*  62 */     super.genLegalDests();
/*     */     
/*     */ 
/*     */ 
/*  66 */     if (this.captured) { return 0;
/*     */     }
/*     */     
/*     */ 
/*  70 */     boolean done = false;
/*     */     
/*  72 */     int f = this.orig.file + 1; for (int r = this.orig.rank + 1; 
/*  73 */         (f <= 8) && (r <= 8) && (!done); 
/*  74 */         r++)
/*     */     {
/*  76 */       Square dest = this.board.getSquare(f, r);
/*  77 */       done = !addLegalDest(dest);
/*     */       
/*  79 */       done = (done) || ((dest.isOccupied()) && (
/*  80 */         (this.board.isBlackMove == this.isBlack) || 
/*  81 */         (!dest.piece.isKing())));f++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  85 */     done = false;
/*     */     
/*  87 */     int f = this.orig.file + 1; for (int r = this.orig.rank - 1; 
/*  88 */         (f <= 8) && (r > 0) && (!done); 
/*  89 */         r--)
/*     */     {
/*  91 */       Square dest = this.board.getSquare(f, r);
/*  92 */       done = !addLegalDest(dest);
/*     */       
/*  94 */       done = (done) || ((dest.isOccupied()) && (
/*  95 */         (this.board.isBlackMove == this.isBlack) || 
/*  96 */         (!dest.piece.isKing())));f++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */     done = false;
/*     */     
/* 102 */     int f = this.orig.file - 1; for (int r = this.orig.rank - 1; 
/* 103 */         (f > 0) && (r > 0) && (!done); 
/* 104 */         r--)
/*     */     {
/* 106 */       Square dest = this.board.getSquare(f, r);
/* 107 */       done = !addLegalDest(dest);
/*     */       
/* 109 */       done = (done) || ((dest.isOccupied()) && (
/* 110 */         (this.board.isBlackMove == this.isBlack) || 
/* 111 */         (!dest.piece.isKing())));f--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 115 */     done = false;
/*     */     
/* 117 */     int f = this.orig.file - 1; for (int r = this.orig.rank + 1; 
/* 118 */         (f > 0) && (r <= 8) && (!done); 
/* 119 */         r++)
/*     */     {
/* 121 */       Square dest = this.board.getSquare(f, r);
/* 122 */       done = !addLegalDest(dest);
/*     */       
/* 124 */       done = (done) || ((dest.isOccupied()) && (
/* 125 */         (this.board.isBlackMove == this.isBlack) || 
/* 126 */         (!dest.piece.isKing())));f--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 129 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */   public void adjustPinsLegalDests(ChessPiece king, List enemyTeam)
/*     */   {
/* 134 */     Square[] line = getLineOfSight(king, false);
/* 135 */     ChessPiece pin = null;
/* 136 */     boolean done = false;
/*     */     
/* 138 */     if (this.captured) { return;
/*     */     }
/* 140 */     if (line != null) {
/* 141 */       if (this.board.staleLegalDests) {
/* 142 */         this.board.genLegalDests();
/*     */       }
/*     */       
/* 145 */       for (int i = 1; (i < line.length) && (!done); i++)
/*     */       {
/* 147 */         ChessPiece tmp = line[i].getOccupant();
/*     */         
/* 149 */         if (tmp != null)
/*     */         {
/*     */ 
/* 152 */           if (pin != null) {
/* 153 */             pin = null;
/* 154 */             done = true;
/*     */ 
/*     */ 
/*     */           }
/* 158 */           else if (tmp.isBlack == isBlack()) {
/* 159 */             done = true;
/*     */           }
/*     */           else
/*     */           {
/* 163 */             pin = tmp;
/*     */           }
/*     */         }
/*     */       }
/* 167 */       if (pin != null)
/*     */       {
/* 169 */         List maintainPins = Arrays.asList(line);
/* 170 */         pin.setPinned(this, maintainPins);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Square[] getLineOfSight(ChessPiece target, boolean inclusive)
/*     */   {
/* 177 */     return getLineOfSight(target.getSquare().file, 
/* 178 */       target.getSquare().rank, 
/* 179 */       inclusive);
/*     */   }
/*     */   
/* 182 */   public Square[] getLineOfSight(Square target, boolean inclusive) { return getLineOfSight(target.file, target.rank, inclusive); }
/*     */   
/*     */   public Square[] getLineOfSight(int t_file, int t_rank, boolean inclusive)
/*     */   {
/* 186 */     Square[] return_set = (Square[])null;
/* 187 */     Square[] return_tmp = new Square[7];
/* 188 */     int o_file = getSquare().file;
/* 189 */     int o_rank = getSquare().rank;
/* 190 */     int f = 0;int r = 0;
/* 191 */     int i = 0;
/*     */     
/*     */ 
/* 194 */     if (o_file - t_file == o_rank - t_rank)
/*     */     {
/* 196 */       if (o_rank < t_rank) {
/* 197 */         f = o_file + 1; for (r = o_rank + 1; r <= t_rank; r++) {
/* 198 */           if ((r != t_rank) || (inclusive)) {
/* 199 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 197 */           f++;
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 203 */         f = o_file - 1; for (r = o_rank - 1; r >= t_rank; r--) {
/* 204 */           if ((r != t_rank) || (inclusive)) {
/* 205 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 203 */           f--;
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/* 209 */     else if (o_file - t_file == (o_rank - t_rank) * -1)
/*     */     {
/* 211 */       if (o_rank - t_rank < 0) {
/* 212 */         f = o_file - 1; for (r = o_rank + 1; r <= t_rank; r++) {
/* 213 */           if ((r != t_rank) || (inclusive)) {
/* 214 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 212 */           f--;
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 218 */         f = o_file + 1; for (r = o_rank - 1; r >= t_rank; r--) {
/* 219 */           if ((r != t_rank) || (inclusive)) {
/* 220 */             return_tmp[(i++)] = this.board.getSquare(f, r);
/*     */           }
/* 218 */           f++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 226 */     if (i != 0) {
/* 227 */       return_set = new Square[i + 1];
/* 228 */       return_set[0] = getSquare();
/* 229 */       System.arraycopy(return_tmp, 0, return_set, 1, i);
/*     */     }
/*     */     
/* 232 */     return return_set;
/*     */   }
/*     */   
/* 235 */   public boolean isBishop() { return true; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Bishop.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */