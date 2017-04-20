/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KingTest
/*     */   extends TestCase
/*     */ {
/*  36 */   boolean DEBUG = false;
/*     */   ChessBoard board;
/*     */   ChessMove move;
/*     */   List list;
/*     */   King king;
/*     */   
/*     */   public KingTest(String name) {
/*  43 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  47 */     this.board = new ChessBoard();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  51 */     this.board = null;
/*  52 */     this.move = null;
/*  53 */     this.king = null;
/*  54 */     this.DEBUG = false;
/*     */   }
/*     */   
/*     */   public void testFullMoveScope()
/*     */   {
/*  59 */     this.board.setPositionClear();
/*  60 */     this.board.addKing(4, 4, false);
/*  61 */     this.board.addKing(8, 2, true);
/*  62 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/*  64 */     this.list = this.king.getLegalDests();
/*  65 */     assertTrue(this.list.size() == 8);
/*     */     
/*  67 */     this.list.remove(this.board.getSquare('c', '3'));
/*  68 */     this.list.remove(this.board.getSquare('c', '4'));
/*  69 */     this.list.remove(this.board.getSquare('c', '5'));
/*  70 */     this.list.remove(this.board.getSquare('d', '3'));
/*  71 */     this.list.remove(this.board.getSquare('d', '5'));
/*  72 */     this.list.remove(this.board.getSquare('e', '3'));
/*  73 */     this.list.remove(this.board.getSquare('e', '4'));
/*  74 */     this.list.remove(this.board.getSquare('e', '5'));
/*     */     
/*  76 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testNotIntoCheck1()
/*     */   {
/*  82 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  83 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  84 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/*  85 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' }, 
/*  86 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  87 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  88 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  89 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/*  91 */     this.board.setPosition(position);
/*  92 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/*  94 */     this.list = this.king.getLegalDests();
/*  95 */     assertTrue(this.list.size() == 5);
/*     */     
/*  97 */     this.list.remove(this.board.getSquare('d', '3'));
/*  98 */     this.list.remove(this.board.getSquare('d', '5'));
/*  99 */     this.list.remove(this.board.getSquare('e', '3'));
/* 100 */     this.list.remove(this.board.getSquare('e', '4'));
/* 101 */     this.list.remove(this.board.getSquare('e', '5'));
/*     */     
/* 103 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testNotIntoCheck2()
/*     */   {
/* 109 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 110 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 111 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 112 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' }, 
/* 113 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 114 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 115 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 116 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 118 */     this.board.setPosition(position);
/* 119 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/* 121 */     this.list = this.king.getLegalDests();
/* 122 */     assertTrue(this.list.size() == 2);
/*     */     
/* 124 */     this.list.remove(this.board.getSquare('d', '3'));
/* 125 */     this.list.remove(this.board.getSquare('d', '5'));
/*     */     
/* 127 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testNotIntoCheck3()
/*     */   {
/* 133 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 134 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 135 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 136 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' }, 
/* 137 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 138 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 139 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 140 */       { ' ', ' ', ' ', ' ', 'r', ' ', ' ', 'k' } };
/*     */     
/* 142 */     this.board.setPosition(position);
/* 143 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/* 145 */     this.list = this.king.getLegalDests();
/* 146 */     assertTrue(this.list.size() == 1);
/*     */     
/* 148 */     this.list.remove(this.board.getSquare('d', '3'));
/*     */     
/* 150 */     assertTrue(this.list.size() == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testStalemate()
/*     */   {
/* 156 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 157 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 158 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 159 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' }, 
/* 160 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 161 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 162 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 163 */       { ' ', ' ', 'r', ' ', 'r', ' ', ' ', 'k' } };
/*     */     
/* 165 */     this.board.setPosition(position);
/* 166 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/* 168 */     this.list = this.king.getLegalDests();
/* 169 */     assertTrue(this.list.size() == 0);
/* 170 */     assertTrue(this.board.isStalemate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmate()
/*     */   {
/* 176 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 177 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 178 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 179 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', 'r' }, 
/* 180 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 181 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 182 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 183 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 185 */     this.board.setPosition(position);
/* 186 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/* 188 */     this.list = this.king.getLegalDests();
/* 189 */     assertTrue(this.list.size() == 0);
/* 190 */     assertFalse(this.board.isStalemate());
/* 191 */     assertTrue(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmate2()
/*     */   {
/* 197 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 198 */       { ' ', ' ', ' ', ' ', 'n', ' ', ' ', ' ' }, 
/* 199 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 200 */       { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' }, 
/* 201 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
/* 202 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 203 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 204 */       { ' ', ' ', 'r', ' ', 'r', ' ', ' ', 'k' } };
/*     */     
/* 206 */     this.board.setPosition(position);
/* 207 */     this.king = ((King)this.board.getSquare(4, 4).getOccupant());
/*     */     
/* 209 */     this.list = this.king.getLegalDests();
/* 210 */     assertTrue(this.list.size() == 0);
/* 211 */     assertFalse(this.board.isStalemate());
/* 212 */     assertTrue(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateSmother()
/*     */   {
/* 218 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 219 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 220 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 221 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 222 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 223 */       { ' ', 'n', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 224 */       { 'R', 'P', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 225 */       { 'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 227 */     this.board.setPosition(position);
/* 228 */     this.king = ((King)this.board.getSquare(8, 1).getOccupant());
/*     */     
/* 230 */     this.list = this.king.getLegalDests();
/* 231 */     assertTrue(this.list.size() == 0);
/* 232 */     assertFalse(this.board.isStalemate());
/* 233 */     assertTrue(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateNotSmother()
/*     */   {
/* 239 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 240 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 241 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 242 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 243 */       { 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 244 */       { ' ', 'n', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 245 */       { 'R', 'P', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 246 */       { 'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 248 */     this.board.setPosition(position);
/* 249 */     this.king = ((King)this.board.getSquare(8, 1).getOccupant());
/*     */     
/* 251 */     this.list = this.king.getLegalDests();
/* 252 */     assertTrue(this.list.size() == 0);
/* 253 */     assertFalse(this.board.isStalemate());
/* 254 */     assertFalse(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateSmother2()
/*     */   {
/* 260 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 261 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 262 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 263 */       { 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 264 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 265 */       { ' ', 'n', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 266 */       { 'B', 'P', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 267 */       { 'K', 'P', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 269 */     this.board.setPosition(position);
/* 270 */     this.king = ((King)this.board.getSquare(8, 1).getOccupant());
/*     */     
/* 272 */     this.list = this.king.getLegalDests();
/* 273 */     assertTrue(this.list.size() == 0);
/* 274 */     assertFalse(this.board.isStalemate());
/* 275 */     assertTrue(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateSmother3()
/*     */   {
/* 281 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 282 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 283 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 284 */       { 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 285 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 286 */       { ' ', 'n', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 287 */       { 'B', 'p', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 288 */       { 'K', 'p', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 290 */     this.board.setPosition(position);
/* 291 */     this.king = ((King)this.board.getSquare(8, 1).getOccupant());
/*     */     
/* 293 */     this.list = this.king.getLegalDests();
/* 294 */     assertTrue(this.list.size() == 2);
/* 295 */     assertFalse(this.board.isStalemate());
/* 296 */     assertFalse(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateSave1()
/*     */   {
/* 302 */     char[][] position = { { ' ', 'r', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 303 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 304 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 305 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 306 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 307 */       { ' ', ' ', 'R', ' ', ' ', ' ', ' ', ' ' }, 
/* 308 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 309 */       { 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 311 */     this.board.setPosition(position);
/* 312 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/*     */     
/* 314 */     this.list = this.king.getLegalDests();
/* 315 */     assertTrue(this.list.size() == 0);
/* 316 */     assertFalse(this.board.isStalemate());
/* 317 */     assertFalse(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testCheckmateSave2Non()
/*     */   {
/* 323 */     char[][] position = { { ' ', 'r', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 324 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 325 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 326 */       { ' ', ' ', 'R', ' ', ' ', ' ', ' ', ' ' }, 
/* 327 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 328 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 329 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 330 */       { 'r', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 332 */     this.board.setPosition(position);
/* 333 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/*     */     
/* 335 */     this.list = this.king.getLegalDests();
/* 336 */     assertTrue(this.list.size() == 0);
/* 337 */     assertFalse(this.board.isStalemate());
/* 338 */     assertTrue(this.board.isCheckmate());
/*     */   }
/*     */   
/*     */ 
/*     */   public void testFindRook1()
/*     */   {
/* 344 */     char[][] position = { { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 345 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 346 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 347 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 348 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 349 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 350 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 351 */       { 'B', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 353 */     this.board.setPosition(position);
/* 354 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/* 355 */     Rook rook = (Rook)this.board.getSquare(1, 1).getOccupant();
/*     */     
/* 357 */     assertTrue(rook == this.king.findMyRook(true));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testFindRook2()
/*     */   {
/* 363 */     char[][] position = { { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 364 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 365 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 366 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 367 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 368 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 369 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 370 */       { 'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 372 */     this.board.setPosition(position);
/* 373 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/* 374 */     Rook rook = (Rook)this.board.getSquare(8, 1).getOccupant();
/*     */     
/* 376 */     assertTrue(rook == this.king.findMyRook(false));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testFindRook3()
/*     */   {
/* 382 */     char[][] position = { { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 383 */       { 'N', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 384 */       { 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 385 */       { 'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 386 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 387 */       { 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 388 */       { 'N', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 389 */       { 'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 391 */     this.board.setPosition(position);
/* 392 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/* 393 */     Rook rook = (Rook)this.board.getSquare(1, 1).getOccupant();
/*     */     
/* 395 */     assertTrue(rook == this.king.findMyRook(true));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testFindRook4()
/*     */   {
/* 401 */     char[][] position = { { 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 402 */       { 'N', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 403 */       { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 404 */       { 'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 405 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 406 */       { 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 407 */       { 'N', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 408 */       { 'R', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 410 */     this.board.setPosition(position);
/* 411 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/* 412 */     Rook rook = (Rook)this.board.getSquare(3, 1).getOccupant();
/*     */     
/* 414 */     assertTrue(rook == this.king.findMyRook(true));
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
/*     */   public void testFindRook5Problem()
/*     */   {
/* 427 */     char[][] position = { { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 428 */       { 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 429 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 430 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 431 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 432 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 433 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 434 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 436 */     this.board.setPosition(position);
/* 437 */     this.king = ((King)this.board.getSquare(5, 1).getOccupant());
/* 438 */     Rook krook = (Rook)this.board.getSquare(1, 1).getOccupant();
/* 439 */     krook.moveCount = 2;
/* 440 */     Rook qrook = (Rook)this.board.getSquare(2, 1).getOccupant();
/*     */     
/*     */ 
/* 443 */     assertFalse(qrook == this.king.findMyRook(true));
/* 444 */     assertTrue(krook == this.king.findMyRook(true));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testEnPassantEscapesCheck()
/*     */   {
/* 452 */     char[][] position = { { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' }, 
/* 453 */       { ' ', ' ', ' ', 'P', 'p', ' ', ' ', ' ' }, 
/* 454 */       { ' ', ' ', ' ', 'K', 'P', 'k', ' ', ' ' }, 
/* 455 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 456 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 457 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 458 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 459 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 461 */     this.board.setPosition(position);
/* 462 */     this.board.setEnPassantFile('b');
/* 463 */     Pawn pawn = (Pawn)this.board.getSquare(3, 5).getOccupant();
/* 464 */     assertTrue(pawn.isLegalDest(this.board.getSquare(2, 6)));
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\KingTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */