/*     */ package ictk.boardgame;
/*     */ 
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.io.ChessAnnotation;
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
/*     */ public class HistoryTest
/*     */   extends TestCase
/*     */ {
/*     */   ChessGame game;
/*     */   SAN san;
/*     */   History history;
/*     */   ChessBoard board;
/*     */   ChessBoard board2;
/*     */   Move move;
/*     */   Move[] moves;
/*     */   
/*     */   public HistoryTest(String name)
/*     */   {
/*  55 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  59 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  63 */     this.game = null;
/*  64 */     this.history = null;
/*  65 */     this.moves = null;
/*  66 */     this.board = (this.board2 = null);
/*  67 */     this.san = null;
/*  68 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testAdd()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/*  75 */     this.game = new ChessGame();
/*  76 */     this.history = this.game.getHistory();
/*     */     
/*  78 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/*  79 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*  80 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*  81 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCR()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/*  88 */     this.game = new ChessGame();
/*  89 */     this.history = this.game.getHistory();
/*     */     
/*  91 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/*  92 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*  93 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*  94 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/*  95 */     this.board = ((ChessBoard)this.game.getBoard());
/*  96 */     this.history.prev();
/*  97 */     this.history.next();
/*  98 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCR2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 105 */     this.game = new ChessGame();
/* 106 */     this.history = this.game.getHistory();
/*     */     
/* 108 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 109 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 110 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 111 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 112 */     this.board = ((ChessBoard)this.game.getBoard());
/* 113 */     this.history.prev();
/* 114 */     this.history.prev();
/* 115 */     this.history.prev();
/* 116 */     this.history.next();
/* 117 */     this.history.next();
/* 118 */     this.history.next();
/* 119 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCR3()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 126 */     this.game = new ChessGame();
/* 127 */     this.history = this.game.getHistory();
/*     */     
/* 129 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 130 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 131 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 132 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 133 */     this.board = ((ChessBoard)this.game.getBoard());
/* 134 */     this.history.prev();
/* 135 */     this.history.prev();
/* 136 */     this.history.prev();
/* 137 */     this.history.prev();
/* 138 */     this.history.prev();
/* 139 */     this.history.prev();
/* 140 */     this.history.prev();
/* 141 */     this.history.prev();
/* 142 */     this.history.prev();
/* 143 */     this.history.prev();
/* 144 */     this.history.next();
/* 145 */     this.history.next();
/* 146 */     this.history.next();
/* 147 */     this.history.next();
/* 148 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCRRewind()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 155 */     this.game = new ChessGame();
/* 156 */     this.history = this.game.getHistory();
/*     */     
/* 158 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 159 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 160 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 161 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 162 */     this.board = ((ChessBoard)this.game.getBoard());
/* 163 */     this.history.rewind();
/* 164 */     this.history.next();
/* 165 */     this.history.next();
/* 166 */     this.history.next();
/* 167 */     this.history.next();
/* 168 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCRFastForward()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 175 */     this.game = new ChessGame();
/* 176 */     this.history = this.game.getHistory();
/*     */     
/* 178 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 179 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 180 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 181 */     this.board = ((ChessBoard)this.game.getBoard());
/* 182 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 183 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
/*     */     
/* 185 */     this.history.rewind();
/* 186 */     this.history.fastforward(3);
/* 187 */     assertTrue(this.move == this.history.getCurrentMove());
/* 188 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testVCRRewindFF()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 196 */     this.game = new ChessGame();
/* 197 */     this.history = this.game.getHistory();
/*     */     
/* 199 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 200 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 201 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 202 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 203 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
/* 204 */     this.board = ((ChessBoard)this.game.getBoard());
/*     */     
/* 206 */     this.history.rewind();
/* 207 */     this.history.goToEnd();
/* 208 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCRRewindGoto()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 215 */     this.game = new ChessGame();
/* 216 */     this.history = this.game.getHistory();
/*     */     
/* 218 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 219 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 220 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 221 */     this.board = ((ChessBoard)this.game.getBoard());
/* 222 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/*     */     
/* 224 */     this.history.rewind();
/* 225 */     this.history.goTo(this.move);
/* 226 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVCRFFGoto()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 233 */     this.game = new ChessGame();
/* 234 */     this.history = this.game.getHistory();
/*     */     
/* 236 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 237 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 238 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 239 */     this.board = ((ChessBoard)this.game.getBoard());
/* 240 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 241 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Bc4"));
/*     */     
/* 243 */     this.history.rewind();
/* 244 */     this.history.goToEnd();
/* 245 */     this.history.goTo(this.move);
/* 246 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVariations()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 253 */     this.game = new ChessGame();
/* 254 */     this.history = this.game.getHistory();
/*     */     
/* 256 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 257 */     this.history.prev();
/*     */     
/* 259 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 260 */     this.history.prev();
/* 261 */     this.history.next();
/*     */     
/*     */ 
/* 264 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 266 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVariations2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 273 */     this.game = new ChessGame();
/* 274 */     this.history = this.game.getHistory();
/*     */     
/* 276 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 277 */     this.history.prev();
/*     */     
/* 279 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 280 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/* 281 */     this.history.prev();
/* 282 */     this.history.prev();
/* 283 */     this.history.next();
/*     */     
/*     */ 
/* 286 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 288 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVariations3()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 295 */     this.game = new ChessGame();
/* 296 */     this.history = this.game.getHistory();
/*     */     
/* 298 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 299 */     this.history.prev();
/*     */     
/* 301 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 302 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/* 303 */     this.history.rewind();
/* 304 */     this.history.next();
/*     */     
/*     */ 
/* 307 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 309 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testVariations4Goto()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 316 */     this.game = new ChessGame();
/* 317 */     this.history = this.game.getHistory();
/*     */     
/* 319 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 320 */     this.history.prev();
/*     */     
/* 322 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 323 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
/* 324 */     this.board = ((ChessBoard)this.game.getBoard());
/* 325 */     this.history.rewind();
/* 326 */     this.history.next();
/*     */     
/*     */ 
/* 329 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 331 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */     
/* 333 */     this.history.goTo(this.move);
/*     */     
/* 335 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testVariations4aGotoBad()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 343 */     ChessGame game2 = new ChessGame();
/* 344 */     game2.getHistory().add(this.move = this.san.stringToMove(game2.getBoard(), "c4"));
/*     */     
/* 346 */     this.game = new ChessGame();
/* 347 */     this.history = this.game.getHistory();
/*     */     
/* 349 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 350 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 351 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */     
/*     */     try
/*     */     {
/* 355 */       this.history.goTo(this.move);
/* 356 */       fail("shouldn't be able to goto a move outside the history list.");
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testVariations5Next()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 368 */     this.game = new ChessGame();
/* 369 */     this.history = this.game.getHistory();
/*     */     
/* 371 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 372 */     this.history.prev();
/*     */     
/* 374 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 375 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
/* 376 */     this.board = ((ChessBoard)this.game.getBoard());
/* 377 */     this.history.rewind();
/* 378 */     this.history.next();
/*     */     
/*     */ 
/* 381 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 383 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */     
/* 385 */     this.history.rewind();
/* 386 */     this.history.next(1);
/* 387 */     this.history.goToEnd();
/*     */     
/* 389 */     assertTrue(this.board.equals(this.game.getBoard()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testVariations6NextBad()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 397 */     this.game = new ChessGame();
/* 398 */     this.history = this.game.getHistory();
/*     */     
/* 400 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 401 */     this.history.prev();
/*     */     
/* 403 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 404 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d5"));
/* 405 */     this.board = ((ChessBoard)this.game.getBoard());
/* 406 */     this.history.rewind();
/* 407 */     this.history.next();
/*     */     
/*     */ 
/* 410 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/*     */     
/* 412 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*     */     
/* 414 */     this.history.rewind();
/*     */     try
/*     */     {
/* 417 */       this.history.next(2);
/* 418 */       fail("Next index should have been out of range");
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testRewindToFork()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 428 */     this.game = new ChessGame();
/* 429 */     this.history = this.game.getHistory();
/*     */     
/* 431 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 432 */     this.history.prev();
/* 433 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 434 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/*     */     
/* 436 */     this.history.rewindToLastFork();
/* 437 */     assertTrue(this.history.getCurrentMove() == null);
/*     */     
/* 439 */     this.history.next();
/* 440 */     assertTrue(this.history.getCurrentMove() == this.move);
/*     */     
/* 442 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 443 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testRewindToFork2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 451 */     this.game = new ChessGame();
/* 452 */     this.history = this.game.getHistory();
/*     */     
/* 454 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 455 */     this.history.prev();
/* 456 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 457 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/* 458 */     this.history.prev();
/* 459 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf6"));
/*     */     
/* 461 */     this.history.rewindToLastFork();
/* 462 */     assertTrue(this.history.getCurrentMove() == this.move);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testEquality()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 470 */     this.game = new ChessGame();
/* 471 */     this.history = this.game.getHistory();
/*     */     
/* 473 */     ChessGame game2 = new ChessGame();
/* 474 */     History history2 = game2.getHistory();
/*     */     
/* 476 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 477 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 478 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/* 480 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 481 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 482 */     history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
/*     */     
/* 484 */     assertTrue(this.history.equals(history2));
/* 485 */     assertTrue(history2.equals(this.history));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testEqualityBad()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 492 */     this.game = new ChessGame();
/* 493 */     this.history = this.game.getHistory();
/*     */     
/* 495 */     ChessGame game2 = new ChessGame();
/* 496 */     History history2 = game2.getHistory();
/*     */     
/* 498 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 499 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 500 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/* 502 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 503 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 504 */     history2.add(this.san.stringToMove(game2.getBoard(), "Bc4"));
/*     */     
/* 506 */     assertFalse(this.history.equals(history2));
/* 507 */     assertFalse(history2.equals(this.history));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testDeepEquality()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 514 */     this.game = new ChessGame();
/* 515 */     this.history = this.game.getHistory();
/*     */     
/* 517 */     ChessGame game2 = new ChessGame();
/* 518 */     History history2 = game2.getHistory();
/*     */     
/* 520 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 521 */     this.history.prev();
/* 522 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/* 523 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/*     */     
/* 525 */     this.history.rewindToLastFork();
/* 526 */     this.history.next();
/*     */     
/* 528 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 529 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/*     */ 
/* 532 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 533 */     history2.prev();
/* 534 */     history2.add(this.san.stringToMove(game2.getBoard(), "d4"));
/* 535 */     history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
/*     */     
/* 537 */     history2.rewindToLastFork();
/* 538 */     history2.next();
/*     */     
/* 540 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 541 */     history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
/*     */     
/* 543 */     assertTrue(this.history.equals(history2));
/* 544 */     assertTrue(history2.equals(this.history));
/*     */     
/* 546 */     assertTrue(this.history.deepEquals(history2, false));
/* 547 */     assertTrue(history2.deepEquals(this.history, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testDeepEquality2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 554 */     this.game = new ChessGame();
/* 555 */     this.history = this.game.getHistory();
/*     */     
/* 557 */     ChessGame game2 = new ChessGame();
/* 558 */     History history2 = game2.getHistory();
/*     */     
/* 560 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 561 */     this.history.prev();
/* 562 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 563 */     ChessAnnotation anno = new ChessAnnotation();
/* 564 */     anno.addNAG(1);
/* 565 */     this.move.setAnnotation(anno);
/* 566 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/*     */     
/* 568 */     this.history.rewindToLastFork();
/* 569 */     this.history.next();
/*     */     
/* 571 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 572 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/*     */ 
/* 575 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 576 */     history2.prev();
/* 577 */     history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
/* 578 */     anno = new ChessAnnotation();
/* 579 */     anno.addNAG(2);
/* 580 */     this.move.setAnnotation(anno);
/* 581 */     history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
/*     */     
/* 583 */     history2.rewindToLastFork();
/* 584 */     history2.next();
/*     */     
/* 586 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 587 */     history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
/*     */     
/* 589 */     assertTrue(this.history.equals(history2));
/* 590 */     assertTrue(history2.equals(this.history));
/*     */     
/* 592 */     assertTrue(this.history.deepEquals(history2, false));
/* 593 */     assertTrue(history2.deepEquals(this.history, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testDeepEquality3()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 600 */     this.game = new ChessGame();
/* 601 */     this.history = this.game.getHistory();
/*     */     
/* 603 */     ChessGame game2 = new ChessGame();
/* 604 */     History history2 = game2.getHistory();
/*     */     
/* 606 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 607 */     this.history.prev();
/* 608 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 609 */     ChessAnnotation anno = new ChessAnnotation();
/* 610 */     anno.addNAG(1);
/* 611 */     this.move.setAnnotation(anno);
/* 612 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/*     */     
/* 614 */     this.history.rewindToLastFork();
/* 615 */     this.history.next();
/*     */     
/* 617 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 618 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/*     */ 
/* 621 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 622 */     history2.prev();
/* 623 */     history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
/* 624 */     anno = new ChessAnnotation();
/* 625 */     anno.addNAG(1);
/* 626 */     this.move.setAnnotation(anno);
/* 627 */     history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
/*     */     
/* 629 */     history2.rewindToLastFork();
/* 630 */     history2.next();
/*     */     
/* 632 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 633 */     history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
/*     */     
/* 635 */     assertTrue(this.history.equals(history2));
/* 636 */     assertTrue(history2.equals(this.history));
/*     */     
/* 638 */     assertTrue(this.history.deepEquals(history2, true));
/* 639 */     assertTrue(history2.deepEquals(this.history, true));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testDeepEquality4()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 646 */     this.game = new ChessGame();
/* 647 */     this.history = this.game.getHistory();
/*     */     
/* 649 */     ChessGame game2 = new ChessGame();
/* 650 */     History history2 = game2.getHistory();
/*     */     
/* 652 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 653 */     this.history.prev();
/* 654 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 655 */     ChessAnnotation anno = new ChessAnnotation();
/* 656 */     anno.addNAG(1);
/* 657 */     this.move.setAnnotation(anno);
/* 658 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d5"));
/*     */     
/* 660 */     this.history.rewindToLastFork();
/* 661 */     this.history.next();
/*     */     
/* 663 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 664 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*     */     
/*     */ 
/* 667 */     history2.add(this.san.stringToMove(game2.getBoard(), "e4"));
/* 668 */     history2.prev();
/* 669 */     history2.add(this.move = this.san.stringToMove(game2.getBoard(), "d4"));
/* 670 */     anno = new ChessAnnotation();
/* 671 */     anno.addNAG(3);
/* 672 */     this.move.setAnnotation(anno);
/* 673 */     history2.add(this.san.stringToMove(game2.getBoard(), "d5"));
/*     */     
/* 675 */     history2.rewindToLastFork();
/* 676 */     history2.next();
/*     */     
/* 678 */     history2.add(this.san.stringToMove(game2.getBoard(), "e5"));
/* 679 */     history2.add(this.san.stringToMove(game2.getBoard(), "Nf3"));
/*     */     
/* 681 */     assertTrue(this.history.equals(history2));
/* 682 */     assertTrue(history2.equals(this.history));
/*     */     
/* 684 */     assertFalse(this.history.deepEquals(history2, true));
/* 685 */     assertFalse(history2.deepEquals(this.history, true));
/*     */     
/* 687 */     assertTrue(this.history.deepEquals(history2, false));
/* 688 */     assertTrue(history2.deepEquals(this.history, false));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testSize()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 695 */     this.game = new ChessGame();
/* 696 */     this.history = this.game.getHistory();
/* 697 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e4"));
/* 698 */     assert (this.history.size() == 1);
/* 699 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "e5"));
/* 700 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 701 */     assert (this.history.size() == 3);
/* 702 */     this.history.prev();
/* 703 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc3"));
/* 704 */     assert (this.history.size() == 3);
/* 705 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf6"));
/* 706 */     this.history.rewindToLastFork();
/* 707 */     this.history.next();
/* 708 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nc6"));
/* 709 */     assert (this.history.size() == 4);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\HistoryTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */