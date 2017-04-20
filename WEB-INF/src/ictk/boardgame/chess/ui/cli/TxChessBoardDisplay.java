/*     */ package ictk.boardgame.chess.ui.cli;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.BoardListener;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessPiece;
/*     */ import ictk.boardgame.chess.Square;
/*     */ import ictk.boardgame.chess.io.ChessMoveNotation;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
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
/*     */ public class TxChessBoardDisplay
/*     */   implements CLIChessBoardDisplay, BoardListener
/*     */ {
/*  61 */   protected PrintWriter out = new PrintWriter(System.out, true);
/*     */   
/*     */ 
/*     */   protected ChessBoard board;
/*     */   
/*     */ 
/*  67 */   protected ChessMoveNotation notation = new SAN();
/*     */   
/*     */ 
/*  70 */   protected boolean whiteOnBottom = true;
/*     */   
/*     */ 
/*     */ 
/*  74 */   protected boolean sideToMoveOnBottom = false;
/*     */   
/*     */ 
/*     */ 
/*  78 */   protected int coordMask = 12;
/*     */   
/*     */ 
/*     */   protected boolean compact;
/*     */   
/*     */ 
/*     */   protected boolean inverse;
/*     */   
/*     */ 
/*     */   protected boolean lowercaseCoords;
/*     */   
/*  89 */   protected boolean waitingForTraversalEnd = false;
/*     */   
/*     */ 
/*     */   public TxChessBoardDisplay(ChessBoard board, OutputStream out)
/*     */   {
/*  94 */     this(board, new PrintWriter(out));
/*     */   }
/*     */   
/*     */   public TxChessBoardDisplay(ChessBoard board, Writer out) {
/*  98 */     this(board, new PrintWriter(out, true));
/*     */   }
/*     */   
/*     */   public TxChessBoardDisplay(ChessBoard board, PrintWriter out) {
/* 102 */     this.board = board;
/* 103 */     this.out = out;
/*     */   }
/*     */   
/*     */   public TxChessBoardDisplay(ChessBoard board) {
/* 107 */     this.board = board;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void boardUpdate(Board b, int event)
/*     */   {
/* 116 */     if (event == 3) {
/* 117 */       this.waitingForTraversalEnd = true;
/*     */     }
/* 119 */     if (event == 4) {
/* 120 */       this.waitingForTraversalEnd = false;
/*     */     }
/* 122 */     if (!this.waitingForTraversalEnd) {
/* 123 */       update();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCompact(boolean t)
/*     */   {
/* 134 */     this.compact = t;
/*     */   }
/*     */   
/*     */   public boolean isCompact()
/*     */   {
/* 139 */     return this.compact;
/*     */   }
/*     */   
/* 142 */   public void setBoard(Board board) { this.board = ((ChessBoard)board); }
/*     */   
/* 144 */   public Board getBoard() { return this.board; }
/*     */   
/* 146 */   public void update() { print(); }
/*     */   
/*     */ 
/* 149 */   public void setWhiteOnBottom(boolean t) { this.whiteOnBottom = t; }
/*     */   
/* 151 */   public boolean isWhiteOnBottom() { return this.whiteOnBottom; }
/*     */   
/* 153 */   public void setSideToMoveOnBottom(boolean t) { this.sideToMoveOnBottom = t; }
/*     */   
/* 155 */   public boolean getSideToMoveOnBottom() { return this.sideToMoveOnBottom; }
/*     */   
/* 157 */   public void setVisibleCoordinates(int mask) { this.coordMask = mask; }
/*     */   
/* 159 */   public int getVisibleCoordinates() { return this.coordMask; }
/*     */   
/* 161 */   public void setLowerCaseCoordinates(boolean t) { this.lowercaseCoords = t; }
/*     */   
/* 163 */   public boolean isLowerCaseCoordinates() { return this.lowercaseCoords; }
/*     */   
/*     */ 
/*     */   public void setInverse(boolean t)
/*     */   {
/* 168 */     this.inverse = t;
/*     */   }
/*     */   
/* 171 */   public boolean isInverse() { return this.inverse; }
/*     */   
/*     */   public void setWriter(PrintWriter out)
/*     */   {
/* 175 */     this.out = out;
/*     */   }
/*     */   
/*     */   public Writer getWriter()
/*     */   {
/* 180 */     return this.out;
/*     */   }
/*     */   
/*     */   public void print()
/*     */   {
/* 185 */     print(this.board);
/*     */   }
/*     */   
/*     */   public void print(Board board)
/*     */   {
/* 190 */     ChessBoard b = (ChessBoard)board;
/* 191 */     Square sq = null;
/* 192 */     StringBuffer last_line = null;
/* 193 */     char c = ' ';
/*     */     
/*     */ 
/*     */ 
/* 197 */     boolean top = (this.coordMask & 0x1) == 1;
/* 198 */     boolean left = (this.coordMask & 0x8) == 8;
/* 199 */     boolean right = (this.coordMask & 0x2) == 2;
/* 200 */     boolean bottom = (this.coordMask & 0x4) == 4;
/* 201 */     boolean flipped = ((!this.whiteOnBottom) && (!this.sideToMoveOnBottom)) || (
/* 202 */       (b.isBlackMove()) && (this.sideToMoveOnBottom));
/* 203 */     char blackSquare = this.inverse ? '#' : ' ';
/* 204 */     char whiteSquare = this.inverse ? ' ' : '#';
/*     */     
/*     */     int i;
/* 207 */     if (top) {
/* 208 */       if (left) {
/* 209 */         this.out.print("  ");
/* 210 */         if (!this.compact) {
/* 211 */           this.out.print("  ");
/*     */         }
/*     */       }
/* 214 */       int r = 1;
/* 215 */       int f = flipped ? 8 : 1;
/* 216 */       for (; flipped ? f >= 1 : f <= 8; 
/* 217 */           i = flipped ? f-- : f++)
/*     */       {
/* 219 */         sq = b.getSquare(f, r);
/*     */         
/* 221 */         if (this.lowercaseCoords) {
/* 222 */           this.out.print(Character.toLowerCase(
/* 223 */             this.notation.fileToChar(sq.getFile())));
/*     */         } else {
/* 225 */           this.out.print(Character.toUpperCase(
/* 226 */             this.notation.fileToChar(sq.getFile())));
/*     */         }
/* 228 */         if (!this.compact) if (f != (flipped ? 1 : 8))
/* 229 */             this.out.print(" ");
/*     */       }
/* 231 */       this.out.println();
/* 232 */       this.out.println();
/*     */     }
/*     */     
/*     */ 
/* 236 */     if (bottom) {
/* 237 */       last_line = new StringBuffer(this.compact ? 10 : 20);
/* 238 */       last_line.append("\n");
/* 239 */       if (left) {
/* 240 */         last_line.append("  ");
/* 241 */         if (!this.compact) {
/* 242 */           last_line.append("  ");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 249 */     int r = flipped ? 1 : 8;
/* 250 */     int f = flipped ? 8 : 1;
/* 251 */     for (; flipped ? r <= 8 : r >= 1; 
/* 252 */         i = flipped ? r++ : r--)
/*     */     {
/* 254 */       sq = b.getSquare(f, r);
/*     */       
/*     */ 
/* 257 */       if (left) {
/* 258 */         if (this.lowercaseCoords) {
/* 259 */           this.out.print(Character.toLowerCase(
/* 260 */             this.notation.rankToChar(sq.getRank())));
/*     */         } else {
/* 262 */           this.out.print(Character.toUpperCase(
/* 263 */             this.notation.rankToChar(sq.getRank())));
/*     */         }
/* 265 */         this.out.print(" ");
/*     */         
/* 267 */         if (((top) || (bottom)) && (!this.compact)) {
/* 268 */           this.out.print(" ");
/*     */         }
/* 270 */         if (!this.compact) {
/* 271 */           this.out.print(" ");
/*     */         }
/*     */       }
/*     */       
/* 275 */       f = flipped ? 8 : 1;
/* 276 */       for (; flipped ? f >= 1 : f <= 8; 
/* 277 */           i = flipped ? f-- : f++)
/*     */       {
/* 279 */         sq = b.getSquare(f, r);
/* 280 */         if (sq.isOccupied()) {
/* 281 */           c = this.notation.pieceToChar((ChessPiece)sq.getPiece());
/* 282 */           if (((ChessPiece)sq.getPiece()).isBlack())
/* 283 */             c = Character.toLowerCase(c);
/* 284 */           this.out.print(c);
/*     */ 
/*     */         }
/* 287 */         else if (sq.isBlack()) {
/* 288 */           this.out.print(blackSquare);
/*     */         } else {
/* 290 */           this.out.print(whiteSquare);
/*     */         }
/* 292 */         if (!this.compact) if (f != (flipped ? 1 : 8)) {
/* 293 */             this.out.print(" ");
/*     */           }
/* 295 */         if (bottom) if (r == (flipped ? 1 : 8)) {
/* 296 */             if (this.lowercaseCoords) {
/* 297 */               last_line.append(Character.toLowerCase(
/* 298 */                 this.notation.fileToChar(sq.getFile())));
/*     */             } else {
/* 300 */               last_line.append(Character.toUpperCase(
/* 301 */                 this.notation.fileToChar(sq.getFile())));
/*     */             }
/* 303 */             if (!this.compact) { if (f != (flipped ? 1 : 8)) {
/* 304 */                 last_line.append(" ");
/*     */               }
/*     */             }
/*     */           }
/*     */       }
/* 309 */       if (right) {
/* 310 */         this.out.print(" ");
/*     */         
/* 312 */         if (((top) || (bottom)) && (!this.compact)) {
/* 313 */           this.out.print(" ");
/*     */         }
/* 315 */         if (!this.compact) {
/* 316 */           this.out.print(" ");
/*     */         }
/* 318 */         if (this.lowercaseCoords) {
/* 319 */           this.out.print(Character.toLowerCase(
/* 320 */             this.notation.rankToChar(sq.getRank())));
/*     */         } else {
/* 322 */           this.out.print(Character.toUpperCase(
/* 323 */             this.notation.rankToChar(sq.getRank())));
/*     */         }
/*     */       }
/* 326 */       if (flipped) {
/* 327 */         f = 8;
/*     */       } else {
/* 329 */         f = 1;
/*     */       }
/* 331 */       this.out.println();
/*     */     }
/* 333 */     if (bottom) {
/* 334 */       this.out.println(last_line.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ui\cli\TxChessBoardDisplay.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */