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
/*     */ public class Queen
/*     */   extends ChessPiece
/*     */ {
/*     */   public static final byte INDEX = 1;
/*     */   protected static final int MAX_LEGAL_DESTS = 27;
/*     */   protected static final int MAX_GUARDS = 27;
/*     */   
/*     */   public Queen()
/*     */   {
/*  37 */     super((byte)1, true, 27, 27);
/*     */   }
/*     */   
/*     */   public Queen(boolean blackness) {
/*  41 */     super((byte)1, blackness, 27, 27);
/*     */   }
/*     */   
/*     */   public Queen(boolean blackness, Square o, ChessBoard _board) {
/*  45 */     super((byte)1, blackness, o, _board, 27, 27);
/*     */   }
/*     */   
/*  48 */   protected String getName() { return "Queen"; }
/*     */   
/*     */ 
/*     */ 
/*     */   protected int genLegalDests()
/*     */   {
/*  54 */     super.genLegalDests();
/*     */     
/*  56 */     boolean done = false;
/*     */     
/*  58 */     if (this.captured) { return 0;
/*     */     }
/*     */     
/*     */ 
/*  62 */     for (int r = this.orig.rank + 1; (r <= 8) && (!done); r++) {
/*  63 */       Square dest = this.board.getSquare(this.orig.file, r);
/*  64 */       done = !addLegalDest(dest);
/*     */       
/*  66 */       done = (done) || ((dest.isOccupied()) && (
/*  67 */         (this.board.isBlackMove == this.isBlack) || 
/*  68 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/*  72 */     done = false;
/*     */     
/*  74 */     int f = this.orig.file + 1; for (int r = this.orig.rank + 1; 
/*  75 */         (f <= 8) && (r <= 8) && (!done); 
/*  76 */         r++)
/*     */     {
/*  78 */       Square dest = this.board.getSquare(f, r);
/*  79 */       done = !addLegalDest(dest);
/*     */       
/*  81 */       done = (done) || ((dest.isOccupied()) && (
/*  82 */         (this.board.isBlackMove == this.isBlack) || 
/*  83 */         (!dest.piece.isKing())));f++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  87 */     done = false;
/*     */     
/*  89 */     for (int f = this.orig.file + 1; (f <= 8) && (!done); f++) {
/*  90 */       Square dest = this.board.getSquare(f, this.orig.rank);
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
/* 101 */     int f = this.orig.file + 1; for (int r = this.orig.rank - 1; 
/* 102 */         (f <= 8) && (r > 0) && (!done); 
/* 103 */         r--)
/*     */     {
/* 105 */       Square dest = this.board.getSquare(f, r);
/* 106 */       done = !addLegalDest(dest);
/*     */       
/* 108 */       done = (done) || ((dest.isOccupied()) && (
/* 109 */         (this.board.isBlackMove == this.isBlack) || 
/* 110 */         (!dest.piece.isKing())));f++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 114 */     done = false;
/*     */     
/* 116 */     for (int r = this.orig.rank - 1; (r > 0) && (!done); r--) {
/* 117 */       Square dest = this.board.getSquare(this.orig.file, r);
/* 118 */       done = !addLegalDest(dest);
/*     */       
/* 120 */       done = (done) || ((dest.isOccupied()) && (
/* 121 */         (this.board.isBlackMove == this.isBlack) || 
/* 122 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/* 126 */     done = false;
/*     */     
/* 128 */     int f = this.orig.file - 1; for (int r = this.orig.rank - 1; 
/* 129 */         (f > 0) && (r > 0) && (!done); 
/* 130 */         r--)
/*     */     {
/* 132 */       Square dest = this.board.getSquare(f, r);
/* 133 */       done = !addLegalDest(dest);
/*     */       
/* 135 */       done = (done) || ((dest.isOccupied()) && (
/* 136 */         (this.board.isBlackMove == this.isBlack) || 
/* 137 */         (!dest.piece.isKing())));f--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 141 */     done = false;
/*     */     
/* 143 */     for (int f = this.orig.file - 1; (f > 0) && (!done); f--) {
/* 144 */       Square dest = this.board.getSquare(f, this.orig.rank);
/* 145 */       done = !addLegalDest(dest);
/*     */       
/* 147 */       done = (done) || ((dest.isOccupied()) && (
/* 148 */         (this.board.isBlackMove == this.isBlack) || 
/* 149 */         (!dest.piece.isKing())));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 154 */     done = false;
/*     */     
/* 156 */     int f = this.orig.file - 1; for (int r = this.orig.rank + 1; 
/* 157 */         (f > 0) && (r <= 8) && (!done); 
/* 158 */         r++)
/*     */     {
/* 160 */       Square dest = this.board.getSquare(f, r);
/* 161 */       done = !addLegalDest(dest);
/*     */       
/* 163 */       done = (done) || ((dest.isOccupied()) && (
/* 164 */         (this.board.isBlackMove == this.isBlack) || 
/* 165 */         (!dest.piece.isKing())));f--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */     return this.legalDests.size();
/*     */   }
/*     */   
/*     */   public void adjustPinsLegalDests(ChessPiece king, List enemyTeam)
/*     */   {
/* 173 */     Square[] line = getLineOfSight(king, false);
/* 174 */     ChessPiece pin = null;
/* 175 */     boolean done = false;
/*     */     
/* 177 */     if (this.captured) { return;
/*     */     }
/* 179 */     if (line != null) {
/* 180 */       if (this.board.staleLegalDests) {
/* 181 */         this.board.genLegalDests();
/*     */       }
/*     */       
/* 184 */       for (int i = 1; (i < line.length) && (!done); i++)
/*     */       {
/* 186 */         ChessPiece tmp = line[i].getOccupant();
/*     */         
/* 188 */         if (tmp != null)
/*     */         {
/*     */ 
/* 191 */           if (pin != null) {
/* 192 */             pin = null;
/* 193 */             done = true;
/*     */ 
/*     */ 
/*     */           }
/* 197 */           else if (tmp.isBlack == isBlack()) {
/* 198 */             done = true;
/*     */           }
/*     */           else
/*     */           {
/* 202 */             pin = tmp;
/*     */           }
/*     */         }
/*     */       }
/* 206 */       if (pin != null)
/*     */       {
/* 208 */         List maintainPins = Arrays.asList(line);
/* 209 */         pin.setPinned(this, maintainPins);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 214 */   public boolean isQueen() { return true; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\Queen.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */