/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Piece;
/*     */ import ictk.util.Log;
/*     */ import junit.framework.TestCase;
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
/*     */ public class ChessMoveTest
/*     */   extends TestCase
/*     */ {
/*     */   ChessBoard board;
/*     */   ChessBoard board2;
/*     */   ChessResult res;
/*     */   ChessMove move;
/*  38 */   char[][] default_position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*  39 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  40 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  41 */     { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*  42 */     { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*  43 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  44 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  45 */     { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */   
/*     */   public ChessMoveTest(String name)
/*     */   {
/*  49 */     super(name);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setUp()
/*     */   {
/*  55 */     this.board = new ChessBoard();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  59 */     this.board = null;
/*  60 */     this.board2 = null;
/*  61 */     this.move = null;
/*  62 */     Log.removeMask(ChessMove.DEBUG);
/*  63 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testIllegalCastle()
/*     */   {
/*     */     try
/*     */     {
/*  71 */       this.move = new ChessMove(this.board, -1);
/*  72 */       fail("shouldn't be able to castle queenside on the initial board");
/*     */     }
/*     */     catch (IllegalMoveException localIllegalMoveException) {}
/*     */     try
/*     */     {
/*  77 */       this.move = new ChessMove(this.board, 1);
/*  78 */       fail("shouldn't be able to castle kingside on the initial board");
/*     */     }
/*     */     catch (IllegalMoveException localIllegalMoveException1) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCastleQsideWhite()
/*     */     throws IllegalMoveException
/*     */   {
/*  87 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*  88 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  89 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  90 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*  91 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*  92 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  93 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  94 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/*  96 */     this.board.setPosition(position);
/*     */     
/*  98 */     this.move = new ChessMove(this.board, -1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCastleKsideWhite()
/*     */     throws IllegalMoveException
/*     */   {
/* 105 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 106 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 107 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 108 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 109 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 110 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 111 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 112 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 114 */     this.board.setPosition(position);
/*     */     
/* 116 */     this.move = new ChessMove(this.board, 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCastleQsideBlack()
/*     */     throws IllegalMoveException
/*     */   {
/* 123 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 124 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', ' ' }, 
/* 125 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', ' ' }, 
/* 126 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', ' ' }, 
/* 127 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 128 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 129 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 130 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 132 */     this.board.setPosition(position);
/* 133 */     this.board.setBlackMove(true);
/* 134 */     this.move = new ChessMove(this.board, -1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCastleKsideBlack()
/*     */     throws IllegalMoveException
/*     */   {
/* 141 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 142 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 143 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 144 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 145 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 146 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', ' ' }, 
/* 147 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', ' ' }, 
/* 148 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 150 */     this.board.setPosition(position);
/* 151 */     this.board.setBlackMove(true);
/* 152 */     this.move = new ChessMove(this.board, 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testA2A3()
/*     */     throws IllegalMoveException
/*     */   {
/* 159 */     char[][] position = { { 'R', ' ', 'P', ' ', ' ', ' ', 'p', 'r' }, 
/* 160 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 161 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 162 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 163 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 164 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 165 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 166 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/*     */ 
/* 169 */     this.move = new ChessMove(this.board, 1, 2, 1, 3);
/* 170 */     this.board.playMove(this.move);
/* 171 */     this.board2 = new ChessBoard(position);
/* 172 */     this.board2.setBlackMove(true);
/*     */     
/*     */ 
/* 175 */     assertTrue(this.board.equals(this.board2));
/* 176 */     assertTrue(this.board.plyCount50 == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testA2A4()
/*     */     throws IllegalMoveException
/*     */   {
/* 183 */     char[][] position = { { 'R', ' ', ' ', 'P', ' ', ' ', 'p', 'r' }, 
/* 184 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 185 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 186 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 187 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 188 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 189 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 190 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/*     */ 
/* 193 */     this.move = new ChessMove(this.board, 1, 2, 1, 4);
/* 194 */     this.board.playMove(this.move);
/* 195 */     this.board2 = new ChessBoard(position);
/* 196 */     this.board2.setBlackMove(true);
/* 197 */     this.board2.setEnPassantFile('a');
/* 198 */     assertTrue(this.board.equals(this.board2));
/* 199 */     assertTrue(this.board.plyCount50 == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testNf3()
/*     */     throws IllegalMoveException
/*     */   {
/* 206 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 207 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 208 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 209 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 210 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 211 */       { 'B', 'P', 'N', ' ', ' ', ' ', 'p', 'b' }, 
/* 212 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 213 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/*     */ 
/* 216 */     assertTrue(this.board != null);
/* 217 */     this.move = new ChessMove(this.board, 7, 1, 6, 3);
/* 218 */     this.board.playMove(this.move);
/* 219 */     this.board2 = new ChessBoard(position);
/* 220 */     this.board2.setBlackMove(true);
/* 221 */     assertTrue(this.board.equals(this.board2));
/* 222 */     assertTrue(this.board.plyCount50 == 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testPawnPromotion()
/*     */     throws IllegalMoveException
/*     */   {
/* 229 */     Piece piece = null;
/* 230 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 231 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ' }, 
/* 232 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 233 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 234 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 235 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 236 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 237 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 239 */     char[][] position2 = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 240 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ' }, 
/* 241 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 242 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 243 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 244 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 245 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N' }, 
/* 246 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/*     */ 
/* 249 */     this.board.setPosition(position);
/*     */     
/* 251 */     this.move = new ChessMove(this.board, 7, 7, 7, 8, 4);
/* 252 */     this.board.playMove(this.move);
/* 253 */     this.board2 = new ChessBoard(position2);
/* 254 */     this.board2.setBlackMove(true);
/* 255 */     assertTrue(this.board.equals(this.board2));
/* 256 */     assertTrue(this.board.plyCount50 == 0);
/* 257 */     piece = this.board.getSquare('g', '8').getOccupant();
/* 258 */     assertTrue(piece instanceof Knight);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testPawnPromotionAutoQueen()
/*     */     throws IllegalMoveException
/*     */   {
/* 265 */     Piece piece = null;
/* 266 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 267 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ' }, 
/* 268 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 269 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 270 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 271 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 272 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 273 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 275 */     char[][] position2 = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 276 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ' }, 
/* 277 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 278 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 279 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 280 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 281 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q' }, 
/* 282 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/*     */ 
/* 285 */     this.board.setPosition(position);
/*     */     
/* 287 */     this.move = new ChessMove(this.board, 7, 7, 7, 8);
/* 288 */     this.board.playMove(this.move);
/* 289 */     this.board2 = new ChessBoard(position2);
/* 290 */     this.board2.setBlackMove(true);
/* 291 */     assertTrue(this.board.equals(this.board2));
/* 292 */     assertTrue(this.board.plyCount50 == 0);
/* 293 */     piece = this.board.getSquare('g', '8').getOccupant();
/* 294 */     assertTrue(piece instanceof Queen);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testPawnPromotionBad()
/*     */     throws IllegalMoveException
/*     */   {
/* 301 */     Piece piece = null;
/* 302 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 303 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'k', ' ' }, 
/* 304 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 305 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 306 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 307 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 308 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 309 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 311 */     this.board.setPosition(position);
/*     */     
/*     */     try
/*     */     {
/* 315 */       this.move = new ChessMove(this.board, 7, 7, 7, 8, 5);
/* 316 */       fail("Can't promote to King in standard chess rules");
/*     */     }
/*     */     catch (IllegalMoveException localIllegalMoveException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testPromotionCaptureWithDoubleCheck()
/*     */     throws IllegalMoveException
/*     */   {
/* 328 */     Piece piece = null;
/* 329 */     char[][] position = {
/* 330 */       { ' ', 'P', ' ', ' ', 'p', ' ', ' ', ' ' }, 
/* 331 */       { ' ', 'P', 'K', ' ', ' ', ' ', ' ', ' ' }, 
/* 332 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n' }, 
/* 333 */       { ' ', ' ', 'R', ' ', ' ', ' ', 'P', 'k' }, 
/* 334 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 335 */       { ' ', 'r', ' ', ' ', ' ', 'p', ' ', ' ' }, 
/* 336 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 337 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 339 */     char[][] position2 = {
/* 340 */       { ' ', 'P', ' ', ' ', 'p', ' ', ' ', ' ' }, 
/* 341 */       { ' ', 'P', 'K', ' ', ' ', ' ', ' ', ' ' }, 
/* 342 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q' }, 
/* 343 */       { ' ', ' ', 'R', ' ', ' ', ' ', ' ', 'k' }, 
/* 344 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 345 */       { ' ', 'r', ' ', ' ', ' ', 'p', ' ', ' ' }, 
/* 346 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 347 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/*     */ 
/* 350 */     this.board.setPosition(position);
/*     */     
/* 352 */     this.move = new ChessMove(this.board, 4, 7, 3, 8, 1);
/* 353 */     this.board.playMove(this.move);
/* 354 */     this.board2 = new ChessBoard(position2);
/* 355 */     this.board2.setBlackMove(true);
/* 356 */     assertTrue(this.board.equals(this.board2));
/* 357 */     assertTrue(this.board.plyCount50 == 0);
/* 358 */     piece = this.board.getSquare('c', '8').getOccupant();
/* 359 */     assertTrue(piece instanceof Queen);
/* 360 */     assertTrue(this.board.getLegalMoveCount() == 2);
/* 361 */     assertTrue(!this.board.isCheckmate());
/* 362 */     assertTrue(this.board.isCheck());
/* 363 */     assertTrue(this.board.isDoubleCheck());
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessMoveTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */