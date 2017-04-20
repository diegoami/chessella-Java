/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.BoardListener;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Piece;
/*     */ import ictk.boardgame.chess.io.SAN;
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
/*     */ public class ChessBoardTest
/*     */   extends TestCase
/*     */ {
/*     */   ChessBoard board;
/*     */   ChessBoard board2;
/*     */   ChessMove move;
/*  37 */   char[][] default_position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*  38 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  39 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  40 */     { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*  41 */     { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*  42 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  43 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  44 */     { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
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
/*     */   public ChessBoardTest(String name)
/*     */   {
/*  59 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  63 */     this.board = new ChessBoard();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  67 */     this.board = null;
/*  68 */     this.board2 = null;
/*  69 */     this.move = null;
/*  70 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */   public void testFileNRankTranslation()
/*     */   {
/*  75 */     Piece p = null;
/*  76 */     p = this.board.squares[0][0].getOccupant();
/*  77 */     assertTrue(p instanceof Rook);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testSetPositionDataReset()
/*     */   {
/*  84 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*  85 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  86 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*  87 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*  88 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*  89 */       { 'B', 'P', 'N', ' ', ' ', ' ', 'p', 'b' }, 
/*  90 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*  91 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*  92 */     this.board.setPosition(position);
/*  93 */     assertTrue(this.board.staleLegalDests);
/*  94 */     this.board.setWhiteCastleableKingside(false);
/*  95 */     this.board.setWhiteCastleableQueenside(false);
/*  96 */     this.board.setBlackCastleableKingside(false);
/*  97 */     this.board.setBlackCastleableQueenside(false);
/*  98 */     this.board.setEnPassantFile(2);
/*  99 */     this.board.set50MoveRulePlyCount(37);
/* 100 */     this.board.setPositionDefault();
/* 101 */     assertTrue(this.board.staleLegalDests);
/* 102 */     assertTrue(this.board.isWhiteCastleableKingside());
/* 103 */     assertTrue(this.board.isWhiteCastleableQueenside());
/* 104 */     assertTrue(this.board.isBlackCastleableKingside());
/* 105 */     assertTrue(this.board.isBlackCastleableQueenside());
/* 106 */     assertTrue(this.board.get50MoveRulePlyCount() == 0);
/* 107 */     assertTrue(this.board.getEnPassantFile() == 0);
/* 108 */     assertTrue(this.board.equals(new ChessBoard()));
/*     */   }
/*     */   
/*     */   public void testEquals()
/*     */   {
/* 113 */     this.board2 = new ChessBoard();
/* 114 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testEqualsPly()
/*     */   {
/* 119 */     this.board2 = new ChessBoard();
/* 120 */     this.board.set50MoveRulePlyCount(32);
/* 121 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testEqualsNotPieceType()
/*     */   {
/* 126 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 127 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 128 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 129 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 130 */       { 'K', 'P', ' ', 'N', ' ', ' ', 'p', 'k' }, 
/* 131 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 132 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 133 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/* 134 */     char[][] position2 = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 135 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 136 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 137 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 138 */       { 'K', 'P', ' ', 'Q', ' ', ' ', 'p', 'k' }, 
/* 139 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 140 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 141 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/* 142 */     this.board.setPosition(position);
/* 143 */     this.board2 = new ChessBoard(position2);
/*     */     
/* 145 */     assertFalse(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testEqualsNotPieceColor()
/*     */   {
/* 150 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 151 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 152 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 153 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 154 */       { 'K', 'P', ' ', 'N', ' ', ' ', 'p', 'k' }, 
/* 155 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 156 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 157 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/* 158 */     char[][] position2 = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 159 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 160 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 161 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 162 */       { 'K', 'P', ' ', 'n', ' ', ' ', 'p', 'k' }, 
/* 163 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 164 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 165 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/* 166 */     this.board.setPosition(position);
/* 167 */     this.board2 = new ChessBoard(position2);
/*     */     
/* 169 */     assertFalse(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testEqualsNotCastle()
/*     */   {
/* 174 */     this.board2 = new ChessBoard();
/* 175 */     this.board.setWhiteCastleableKingside(false);
/*     */     
/* 177 */     assertFalse(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testEqualsNotEnPassant()
/*     */   {
/* 182 */     this.board2 = new ChessBoard();
/* 183 */     this.board.setEnPassantFile(2);
/*     */     
/* 185 */     assertFalse(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */   public void testLegalMoves()
/*     */   {
/* 190 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 191 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 192 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 193 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 194 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 195 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 196 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 197 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 199 */     assertTrue(this.board.getLegalMoveCount() == 20);
/*     */   }
/*     */   
/*     */   public void testSetPositionCastle()
/*     */   {
/* 204 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 205 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 206 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 207 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 208 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 209 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 210 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 211 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 213 */     this.board.setPosition(position);
/* 214 */     assertTrue(this.board.isWhiteCastleableQueenside());
/* 215 */     assertTrue(this.board.isWhiteCastleableKingside());
/* 216 */     assertTrue(this.board.isBlackCastleableQueenside());
/* 217 */     assertTrue(this.board.isBlackCastleableKingside());
/*     */   }
/*     */   
/*     */   public void testSetPositionCastle2()
/*     */   {
/* 222 */     char[][] position = { { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 223 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 224 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 225 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 226 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 227 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 228 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 229 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 231 */     this.board.setPosition(position);
/* 232 */     assertFalse(this.board.isWhiteCastleableQueenside());
/* 233 */     assertTrue(this.board.isWhiteCastleableKingside());
/* 234 */     assertTrue(this.board.isBlackCastleableQueenside());
/* 235 */     assertTrue(this.board.isBlackCastleableKingside());
/*     */   }
/*     */   
/*     */   public void testSetPositionCastle3()
/*     */   {
/* 240 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 241 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 242 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 243 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 244 */       { ' ', 'P', ' ', 'K', ' ', ' ', 'p', 'k' }, 
/* 245 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 246 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 247 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 249 */     this.board.setPosition(position);
/* 250 */     assertFalse(this.board.isWhiteCastleableQueenside());
/* 251 */     assertFalse(this.board.isWhiteCastleableKingside());
/* 252 */     assertTrue(this.board.isBlackCastleableQueenside());
/* 253 */     assertTrue(this.board.isBlackCastleableKingside());
/*     */   }
/*     */   
/*     */   public void testMaterialCount1()
/*     */   {
/* 258 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 259 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 260 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 261 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 262 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 263 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 264 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 265 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 267 */     this.board.setPosition(position);
/* 268 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 269 */     assertTrue(this.board.getMaterialCount(false) == 39);
/*     */   }
/*     */   
/*     */   public void testMaterialCount2()
/*     */   {
/* 274 */     char[][] position = { { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 275 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 276 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 277 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 278 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 279 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 280 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 281 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 283 */     this.board.setPosition(position);
/* 284 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 285 */     assertTrue(this.board.getMaterialCount(false) == 34);
/*     */   }
/*     */   
/*     */   public void testMaterialCount3()
/*     */   {
/* 290 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 291 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 292 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 293 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 294 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 295 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 296 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 297 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 299 */     this.board.setPosition(position);
/* 300 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 301 */     assertTrue(this.board.getMaterialCount(false) == 36);
/*     */   }
/*     */   
/*     */   public void testMaterialCount4()
/*     */   {
/* 306 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 307 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 308 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 309 */       { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 310 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 311 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 312 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 313 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 315 */     this.board.setPosition(position);
/* 316 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 317 */     assertTrue(this.board.getMaterialCount(false) == 36);
/*     */   }
/*     */   
/*     */   public void testMaterialCount5()
/*     */   {
/* 322 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 323 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 324 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 325 */       { ' ', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 326 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 327 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 328 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 329 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 331 */     this.board.setPosition(position);
/* 332 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 333 */     assertTrue(this.board.getMaterialCount(false) == 30);
/*     */   }
/*     */   
/*     */   public void testMaterialCount6()
/*     */   {
/* 338 */     char[][] position = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/* 339 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 340 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 341 */       { 'Q', ' ', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/* 342 */       { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/* 343 */       { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/* 344 */       { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/* 345 */       { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*     */     
/* 347 */     this.board.setPosition(position);
/* 348 */     assertTrue(this.board.getMaterialCount(true) == 39);
/* 349 */     assertTrue(this.board.getMaterialCount(false) == 38);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testGetCaptured()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 356 */     assertTrue(this.board.getCapturedPieces(true) == null);
/* 357 */     assertTrue(this.board.getCapturedPieces(false) == null);
/*     */     
/* 359 */     assertTrue(this.board.getUnCapturedPieces(true).length == 16);
/* 360 */     assertTrue(this.board.getUnCapturedPieces(false).length == 16);
/*     */     
/* 362 */     this.move = ((ChessMove)ChessBoard.san.stringToMove(this.board, "e4"));
/* 363 */     this.board.playMove(this.move);
/* 364 */     this.move = ((ChessMove)ChessBoard.san.stringToMove(this.board, "d5"));
/* 365 */     this.board.playMove(this.move);
/* 366 */     this.move = ((ChessMove)ChessBoard.san.stringToMove(this.board, "exd5"));
/* 367 */     this.board.playMove(this.move);
/*     */     
/* 369 */     assertTrue(this.board.getCapturedPieces(true).length == 1);
/* 370 */     assertTrue(this.board.getCapturedPieces(false) == null);
/*     */     
/* 372 */     assertTrue(this.board.getUnCapturedPieces(true).length == 15);
/* 373 */     assertTrue(this.board.getUnCapturedPieces(false).length == 16);
/*     */   }
/*     */   
/*     */   public void testListenersAdd()
/*     */   {
/* 378 */     BoardListener bl = new BoardListener() {
/*     */       public void boardUpdate(Board b, int c) {}
/* 380 */     };
/* 381 */     BoardListener bl2 = new BoardListener()
/*     */     {
/*     */       public void boardUpdate(Board b, int c) {}
/* 384 */     };
/* 385 */     this.board.addBoardListener(bl);
/* 386 */     assertTrue(this.board.getBoardListeners().length == 1);
/*     */     
/*     */ 
/* 389 */     this.board.addBoardListener(bl);
/* 390 */     assertTrue(this.board.getBoardListeners().length == 1);
/*     */     
/* 392 */     this.board.addBoardListener(bl2);
/* 393 */     assertTrue(this.board.getBoardListeners().length == 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testListenersArrayRemove()
/*     */   {
/* 401 */     BoardListener bl = new BoardListener() {
/*     */       public void boardUpdate(Board b, int c) {}
/* 403 */     };
/* 404 */     BoardListener bl2 = new BoardListener()
/*     */     {
/*     */       public void boardUpdate(Board b, int c) {}
/* 407 */     };
/* 408 */     this.board.addBoardListener(bl);
/* 409 */     assertTrue(this.board.getBoardListeners().length == 1);
/*     */     
/* 411 */     this.board.addBoardListener(bl2);
/* 412 */     assertTrue(this.board.getBoardListeners().length == 2);
/*     */     
/* 414 */     this.board.removeBoardListener(bl);
/* 415 */     assertTrue(this.board.getBoardListeners().length == 1);
/*     */     
/* 417 */     assertTrue(this.board.getBoardListeners()[0] == bl2);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessBoardTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */