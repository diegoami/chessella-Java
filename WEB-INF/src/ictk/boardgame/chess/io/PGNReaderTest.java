/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.Result;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.io.InvalidGameFormatException;
/*     */ import ictk.util.Log;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
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
/*     */ public class PGNReaderTest
/*     */   extends TestCase
/*     */ {
/*  39 */   public String dataDir = "./";
/*  40 */   String pgn_nonvariation = "test_nonvariation.pgn";
/*  41 */   String pgn_variation = "test_variation.pgn";
/*  42 */   String pgn_annotation = "test_annotation.pgn";
/*  43 */   String pgn_bad = "test_bad.pgn";
/*  44 */   String pgn_debug = "test_debug.pgn";
/*     */   SAN san;
/*     */   ChessBoard board;
/*     */   ChessResult res;
/*     */   ChessMove move;
/*     */   ChessReader in;
/*     */   Game game;
/*     */   List games;
/*     */   ChessAnnotation anno;
/*     */   
/*     */   public PGNReaderTest(String name)
/*     */   {
/*  56 */     super(name);
/*     */     
/*     */ 
/*  59 */     if (System.getProperty("ictk.boardgame.chess.io.dataDir") != null)
/*  60 */       this.dataDir = System.getProperty("ictk.boardgame.chess.io.dataDir");
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  64 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  68 */     this.san = null;
/*  69 */     this.board = null;
/*  70 */     this.res = null;
/*  71 */     this.move = null;
/*  72 */     this.game = null;
/*  73 */     this.in = null;
/*  74 */     this.games = null;
/*  75 */     this.anno = null;
/*  76 */     Log.removeMask(SAN.DEBUG);
/*  77 */     Log.removeMask(ChessBoard.DEBUG);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testDebug()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*  95 */     this.games = loadGames(this.dataDir + this.pgn_debug, false, -1);
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
/*     */ 
/*     */ 
/*     */   public void testBulkNonVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 112 */     this.games = loadGames(this.dataDir + this.pgn_nonvariation, false, -1);
/* 113 */     assertTrue(this.games.size() > 0);
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
/*     */   public void testBulkVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 127 */     this.games = loadGames(this.dataDir + this.pgn_variation, false, -1);
/* 128 */     assertTrue(this.games.size() > 0);
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
/*     */   public void testDuplicateContinuation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 143 */     this.games = loadGames(this.dataDir + this.pgn_variation, false, 7);
/* 144 */     assertTrue(this.games.size() > 0);
/* 145 */     this.game = ((ChessGame)this.games.get(7));
/* 146 */     this.game.getHistory().fastforward(50);
/* 147 */     ContinuationList cont = this.game.getHistory()
/* 148 */       .getCurrentMove()
/* 149 */       .getContinuationList();
/* 150 */     assertTrue(cont.size() == 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testMainLineResultOnVariationBugTest()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 163 */     this.games = loadGames(this.dataDir + this.pgn_variation, false, 8);
/* 164 */     assertTrue(this.games.size() > 0);
/* 165 */     this.game = ((ChessGame)this.games.get(8));
/*     */     
/* 167 */     ChessResult res1 = (ChessResult)this.game.getResult();
/* 168 */     ChessResult res2 = (ChessResult)this.game.getHistory()
/* 169 */       .getFinalMove(true).getResult();
/*     */     
/* 171 */     assertTrue(res1 != null);
/* 172 */     assertTrue(res2 != null);
/* 173 */     assertTrue(res1.equals(res2));
/* 174 */     assertTrue(res1.equals(new ChessResult(1)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testBulkAnnotation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 187 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, -1);
/* 188 */     assertTrue(this.games.size() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationCommentAfterMove()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 200 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 0);
/*     */     
/* 202 */     this.game = ((Game)this.games.get(0));
/* 203 */     History history = this.game.getHistory();
/*     */     
/* 205 */     history.rewind();
/* 206 */     history.next();
/* 207 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/* 208 */     assertTrue(this.anno.getComment().equals("Best by test"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testNonVariationResultSwitchBug()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 220 */     this.games = loadGames(this.dataDir + this.pgn_nonvariation, false, 4);
/* 221 */     this.game = ((ChessGame)this.games.get(4));
/*     */     
/* 223 */     this.game.getHistory().rewind();
/* 224 */     this.res = ((ChessResult)this.game.getResult());
/* 225 */     this.game.getHistory().goToEnd();
/* 226 */     Result res2 = this.game.getHistory().getCurrentMove().getResult();
/* 227 */     assertTrue(this.res.equals(res2));
/* 228 */     assertTrue(this.res.isWhiteWin());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationExclaimation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 239 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 1);
/*     */     
/* 241 */     this.game = ((Game)this.games.get(1));
/* 242 */     History history = this.game.getHistory();
/*     */     
/* 244 */     history.rewind();
/* 245 */     history.next();
/* 246 */     history.next();
/* 247 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/* 248 */     assertTrue(this.anno.getSuffix() == 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationCommentBeforeGame()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 259 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 2);
/*     */     
/* 261 */     this.game = ((Game)this.games.get(2));
/* 262 */     assertTrue(this.game != null);
/* 263 */     History history = this.game.getHistory();
/*     */     
/* 265 */     history.rewind();
/* 266 */     history.next();
/*     */     
/* 268 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 270 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getPrenotation());
/*     */     
/* 272 */     assertTrue(this.anno != null);
/* 273 */     assertTrue(this.anno.getComment().equals("Comment Before Game"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationComment2ndMoveWithCommentAfter1st()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 284 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 3);
/*     */     
/* 286 */     this.game = ((Game)this.games.get(3));
/* 287 */     assertTrue(this.game != null);
/* 288 */     History history = this.game.getHistory();
/*     */     
/* 290 */     history.rewind();
/* 291 */     history.next();
/*     */     
/* 293 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/*     */ 
/* 296 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 298 */     assertTrue(this.anno != null);
/* 299 */     assertTrue(this.anno.getComment().equals("after1"));
/*     */     
/*     */ 
/* 302 */     history.next();
/* 303 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 305 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getPrenotation());
/*     */     
/* 307 */     assertTrue(this.anno != null);
/* 308 */     assertTrue(this.anno.getComment().equals("before2"));
/*     */     
/* 310 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 312 */     assertTrue(this.anno != null);
/* 313 */     assertTrue(this.anno.getComment().equals("after2"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotation2CommentsAfter1stOneBefore2nd()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 324 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 4);
/*     */     
/* 326 */     this.game = ((Game)this.games.get(4));
/* 327 */     assertTrue(this.game != null);
/* 328 */     History history = this.game.getHistory();
/*     */     
/* 330 */     history.rewind();
/* 331 */     history.next();
/*     */     
/* 333 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/*     */ 
/* 336 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 338 */     assertTrue(this.anno != null);
/* 339 */     assertTrue(this.anno.getComment().equals("after1 after1a"));
/*     */     
/*     */ 
/* 342 */     history.next();
/* 343 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 345 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getPrenotation());
/*     */     
/* 347 */     assertTrue(this.anno != null);
/* 348 */     assertTrue(this.anno.getComment().equals("before2"));
/*     */     
/* 350 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 352 */     assertTrue(this.anno != null);
/* 353 */     assertTrue(this.anno.getComment().equals("after2"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationEndLineComment()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 364 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 6);
/*     */     
/* 366 */     this.game = ((Game)this.games.get(6));
/* 367 */     assertTrue(this.game != null);
/* 368 */     History history = this.game.getHistory();
/*     */     
/* 370 */     history.rewind();
/* 371 */     history.next();
/*     */     
/* 373 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 375 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 377 */     assertTrue(this.anno != null);
/* 378 */     assertTrue(this.anno.getComment().equals("Best by test"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotation2EndLineCommentsInARow()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 389 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 7);
/*     */     
/* 391 */     this.game = ((Game)this.games.get(7));
/* 392 */     assertTrue(this.game != null);
/* 393 */     History history = this.game.getHistory();
/*     */     
/* 395 */     history.rewind();
/* 396 */     history.next();
/*     */     
/* 398 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 400 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 402 */     assertTrue(this.anno != null);
/* 403 */     assertTrue(this.anno.getComment().equals("Best by test so says Fischer"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationHeadingVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 414 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 8);
/*     */     
/* 416 */     this.game = ((Game)this.games.get(8));
/* 417 */     assertTrue(this.game != null);
/* 418 */     History history = this.game.getHistory();
/*     */     
/* 420 */     history.rewind();
/* 421 */     history.next();
/* 422 */     history.next(1);
/*     */     
/* 424 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 426 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getPrenotation());
/*     */     
/* 428 */     assertTrue(this.anno != null);
/* 429 */     assertTrue(this.anno.getComment().equals("Sicilian"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationHeadingVariationEOL()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 440 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 9);
/*     */     
/* 442 */     this.game = ((Game)this.games.get(9));
/* 443 */     assertTrue(this.game != null);
/* 444 */     History history = this.game.getHistory();
/*     */     
/* 446 */     history.rewind();
/* 447 */     history.next();
/* 448 */     history.next(1);
/*     */     
/* 450 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 452 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getPrenotation());
/*     */     
/* 454 */     assertTrue(this.anno != null);
/* 455 */     assertTrue(this.anno.getComment().equals("Sicilian"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationBeforeVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 466 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 10);
/*     */     
/* 468 */     this.game = ((Game)this.games.get(10));
/* 469 */     assertTrue(this.game != null);
/* 470 */     History history = this.game.getHistory();
/*     */     
/* 472 */     history.rewind();
/* 473 */     history.next();
/* 474 */     history.next();
/*     */     
/* 476 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 478 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 480 */     assertTrue(this.anno != null);
/* 481 */     assertTrue(this.anno.getComment() != null);
/* 482 */     assertTrue(this.anno.getComment().equals("post"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testAnnotationAfterNAGBeforeVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 493 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 11);
/*     */     
/* 495 */     this.game = ((Game)this.games.get(11));
/* 496 */     assertTrue(this.game != null);
/* 497 */     History history = this.game.getHistory();
/*     */     
/* 499 */     history.rewind();
/* 500 */     history.next();
/* 501 */     history.next();
/*     */     
/* 503 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 505 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 507 */     assertTrue(this.anno != null);
/* 508 */     assertTrue(this.anno.getComment() != null);
/* 509 */     assertTrue(this.anno.getComment().equals("post"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testDoubleAnnotationBeforeVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 520 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 12);
/*     */     
/* 522 */     this.game = ((Game)this.games.get(12));
/* 523 */     assertTrue(this.game != null);
/* 524 */     History history = this.game.getHistory();
/*     */     
/* 526 */     history.rewind();
/* 527 */     history.next();
/* 528 */     history.next();
/*     */     
/* 530 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 532 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 534 */     assertTrue(this.anno != null);
/* 535 */     assertTrue(this.anno.getComment() != null);
/* 536 */     assertTrue(this.anno.getComment().equals("post repost"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testDoubleAnnotationBeforeVariationEnd()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 547 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 13);
/*     */     
/* 549 */     this.game = ((Game)this.games.get(13));
/* 550 */     assertTrue(this.game != null);
/* 551 */     History history = this.game.getHistory();
/*     */     
/* 553 */     history.rewind();
/* 554 */     history.next();
/* 555 */     history.next(1);
/*     */     
/* 557 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 559 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 561 */     assertTrue(this.anno != null);
/* 562 */     assertTrue(this.anno.getComment() != null);
/* 563 */     assertTrue(this.anno.getComment().equals("post repost"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testNAGOver8()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 574 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 14);
/*     */     
/* 576 */     this.game = ((Game)this.games.get(14));
/* 577 */     assertTrue(this.game != null);
/* 578 */     History history = this.game.getHistory();
/*     */     
/* 580 */     history.rewind();
/* 581 */     history.next();
/*     */     
/* 583 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 585 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 587 */     assertTrue(this.anno != null);
/* 588 */     assertTrue(this.anno.getNAG(0) == 9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testNAGSymbol()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 599 */     this.games = loadGames(this.dataDir + this.pgn_annotation, false, 15);
/*     */     
/* 601 */     this.game = ((Game)this.games.get(15));
/* 602 */     assertTrue(this.game != null);
/* 603 */     History history = this.game.getHistory();
/*     */     
/* 605 */     history.rewind();
/* 606 */     history.next();
/*     */     
/* 608 */     assertTrue(history.getCurrentMove() != null);
/*     */     
/* 610 */     this.anno = ((ChessAnnotation)history.getCurrentMove().getAnnotation());
/*     */     
/* 612 */     assertTrue(this.anno != null);
/* 613 */     assertTrue(this.anno.getNAG(0) == 145);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testBadPGNs()
/*     */     throws FileNotFoundException, InvalidGameFormatException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: aload_0
/*     */     //   3: new 286	ictk/boardgame/chess/io/PGNReader
/*     */     //   6: dup
/*     */     //   7: new 288	java/io/FileReader
/*     */     //   10: dup
/*     */     //   11: new 290	java/io/File
/*     */     //   14: dup
/*     */     //   15: new 120	java/lang/StringBuilder
/*     */     //   18: dup
/*     */     //   19: aload_0
/*     */     //   20: getfield 35	ictk/boardgame/chess/io/PGNReaderTest:dataDir	Ljava/lang/String;
/*     */     //   23: invokestatic 122	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   26: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   29: aload_0
/*     */     //   30: getfield 51	ictk/boardgame/chess/io/PGNReaderTest:pgn_bad	Ljava/lang/String;
/*     */     //   33: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   36: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   39: invokespecial 292	java/io/File:<init>	(Ljava/lang/String;)V
/*     */     //   42: invokespecial 293	java/io/FileReader:<init>	(Ljava/io/File;)V
/*     */     //   45: invokespecial 296	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   48: putfield 87	ictk/boardgame/chess/io/PGNReaderTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   51: aload_0
/*     */     //   52: aload_0
/*     */     //   53: getfield 87	ictk/boardgame/chess/io/PGNReaderTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   56: invokevirtual 299	ictk/boardgame/chess/io/ChessReader:readGame	()Lictk/boardgame/Game;
/*     */     //   59: putfield 85	ictk/boardgame/chess/io/PGNReaderTest:game	Lictk/boardgame/Game;
/*     */     //   62: ldc_w 305
/*     */     //   65: invokestatic 307	ictk/boardgame/chess/io/PGNReaderTest:fail	(Ljava/lang/String;)V
/*     */     //   68: goto +106 -> 174
/*     */     //   71: astore_2
/*     */     //   72: aload_0
/*     */     //   73: aload_0
/*     */     //   74: getfield 87	ictk/boardgame/chess/io/PGNReaderTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   77: invokevirtual 310	ictk/boardgame/chess/io/ChessReader:getGame	()Lictk/boardgame/Game;
/*     */     //   80: putfield 85	ictk/boardgame/chess/io/PGNReaderTest:game	Lictk/boardgame/Game;
/*     */     //   83: bipush 28
/*     */     //   85: aload_0
/*     */     //   86: getfield 85	ictk/boardgame/chess/io/PGNReaderTest:game	Lictk/boardgame/Game;
/*     */     //   89: invokeinterface 160 1 0
/*     */     //   94: invokevirtual 313	ictk/boardgame/History:size	()I
/*     */     //   97: if_icmpne +7 -> 104
/*     */     //   100: iconst_1
/*     */     //   101: goto +4 -> 105
/*     */     //   104: iconst_0
/*     */     //   105: invokestatic 148	ictk/boardgame/chess/io/PGNReaderTest:assertTrue	(Z)V
/*     */     //   108: goto +81 -> 189
/*     */     //   111: astore_2
/*     */     //   112: new 120	java/lang/StringBuilder
/*     */     //   115: dup
/*     */     //   116: ldc_w 314
/*     */     //   119: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   122: aload_2
/*     */     //   123: invokevirtual 316	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   126: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   129: invokestatic 307	ictk/boardgame/chess/io/PGNReaderTest:fail	(Ljava/lang/String;)V
/*     */     //   132: goto +57 -> 189
/*     */     //   135: astore_2
/*     */     //   136: new 120	java/lang/StringBuilder
/*     */     //   139: dup
/*     */     //   140: ldc_w 314
/*     */     //   143: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   146: aload_2
/*     */     //   147: invokevirtual 316	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   150: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   153: invokestatic 307	ictk/boardgame/chess/io/PGNReaderTest:fail	(Ljava/lang/String;)V
/*     */     //   156: goto +33 -> 189
/*     */     //   159: astore_3
/*     */     //   160: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   163: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   166: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   169: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   172: aload_3
/*     */     //   173: athrow
/*     */     //   174: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   177: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   180: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   183: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   186: goto +15 -> 201
/*     */     //   189: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   192: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   195: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   198: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   201: return
/*     */     // Line number table:
/*     */     //   Java source line #625	-> byte code offset #0
/*     */     //   Java source line #627	-> byte code offset #2
/*     */     //   Java source line #628	-> byte code offset #7
/*     */     //   Java source line #629	-> byte code offset #11
/*     */     //   Java source line #628	-> byte code offset #42
/*     */     //   Java source line #627	-> byte code offset #48
/*     */     //   Java source line #632	-> byte code offset #51
/*     */     //   Java source line #633	-> byte code offset #62
/*     */     //   Java source line #635	-> byte code offset #71
/*     */     //   Java source line #636	-> byte code offset #72
/*     */     //   Java source line #637	-> byte code offset #83
/*     */     //   Java source line #639	-> byte code offset #111
/*     */     //   Java source line #640	-> byte code offset #112
/*     */     //   Java source line #642	-> byte code offset #135
/*     */     //   Java source line #643	-> byte code offset #136
/*     */     //   Java source line #645	-> byte code offset #159
/*     */     //   Java source line #646	-> byte code offset #160
/*     */     //   Java source line #647	-> byte code offset #166
/*     */     //   Java source line #648	-> byte code offset #172
/*     */     //   Java source line #646	-> byte code offset #174
/*     */     //   Java source line #647	-> byte code offset #180
/*     */     //   Java source line #648	-> byte code offset #186
/*     */     //   Java source line #646	-> byte code offset #189
/*     */     //   Java source line #647	-> byte code offset #195
/*     */     //   Java source line #649	-> byte code offset #201
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	202	0	this	PGNReaderTest
/*     */     //   1	2	1	count	int
/*     */     //   71	2	2	e	IOException
/*     */     //   111	12	2	e	IllegalMoveException
/*     */     //   135	12	2	e	AmbiguousMoveException
/*     */     //   159	14	3	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   51	68	71	java/io/IOException
/*     */     //   51	68	111	ictk/boardgame/IllegalMoveException
/*     */     //   51	68	135	ictk/boardgame/AmbiguousMoveException
/*     */     //   51	159	159	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   protected static List loadGames(String file, boolean debug, int gameToDebug)
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore_3
/*     */     //   2: aconst_null
/*     */     //   3: astore 4
/*     */     //   5: new 326	java/util/LinkedList
/*     */     //   8: dup
/*     */     //   9: invokespecial 328	java/util/LinkedList:<init>	()V
/*     */     //   12: astore 5
/*     */     //   14: iload_1
/*     */     //   15: ifeq +19 -> 34
/*     */     //   18: iload_2
/*     */     //   19: ifge +15 -> 34
/*     */     //   22: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   25: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   28: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   31: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   34: iconst_0
/*     */     //   35: istore 6
/*     */     //   37: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   40: new 120	java/lang/StringBuilder
/*     */     //   43: dup
/*     */     //   44: ldc_w 332
/*     */     //   47: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   50: aload_0
/*     */     //   51: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   54: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   57: invokestatic 334	ictk/util/Log:debug	(JLjava/lang/Object;)V
/*     */     //   60: new 286	ictk/boardgame/chess/io/PGNReader
/*     */     //   63: dup
/*     */     //   64: new 288	java/io/FileReader
/*     */     //   67: dup
/*     */     //   68: new 290	java/io/File
/*     */     //   71: dup
/*     */     //   72: aload_0
/*     */     //   73: invokespecial 292	java/io/File:<init>	(Ljava/lang/String;)V
/*     */     //   76: invokespecial 293	java/io/FileReader:<init>	(Ljava/io/File;)V
/*     */     //   79: invokespecial 296	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   82: astore 4
/*     */     //   84: iload_1
/*     */     //   85: ifeq +100 -> 185
/*     */     //   88: iload_2
/*     */     //   89: iload 6
/*     */     //   91: if_icmpne +94 -> 185
/*     */     //   94: getstatic 338	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   97: ldc_w 342
/*     */     //   100: invokevirtual 344	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   103: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   106: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   109: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   112: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   115: goto +70 -> 185
/*     */     //   118: aload 5
/*     */     //   120: aload_3
/*     */     //   121: invokeinterface 349 2 0
/*     */     //   126: pop
/*     */     //   127: iload_1
/*     */     //   128: ifeq +21 -> 149
/*     */     //   131: iload_2
/*     */     //   132: iload 6
/*     */     //   134: if_icmpne +15 -> 149
/*     */     //   137: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   140: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   143: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   146: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   149: iinc 6 1
/*     */     //   152: iload_1
/*     */     //   153: ifeq +30 -> 183
/*     */     //   156: iload_2
/*     */     //   157: iload 6
/*     */     //   159: if_icmpne +24 -> 183
/*     */     //   162: getstatic 338	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   165: ldc_w 342
/*     */     //   168: invokevirtual 344	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   171: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   174: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   177: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   180: invokestatic 329	ictk/util/Log:addMask	(J)V
/*     */     //   183: aconst_null
/*     */     //   184: astore_3
/*     */     //   185: aload 4
/*     */     //   187: invokevirtual 352	ictk/boardgame/chess/io/PGNReader:readGame	()Lictk/boardgame/Game;
/*     */     //   190: dup
/*     */     //   191: astore_3
/*     */     //   192: ifnonnull -74 -> 118
/*     */     //   195: goto +29 -> 224
/*     */     //   198: astore 6
/*     */     //   200: aload 6
/*     */     //   202: athrow
/*     */     //   203: astore 7
/*     */     //   205: iload_1
/*     */     //   206: ifeq +15 -> 221
/*     */     //   209: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   212: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   215: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   218: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   221: aload 7
/*     */     //   223: athrow
/*     */     //   224: iload_1
/*     */     //   225: ifeq +15 -> 240
/*     */     //   228: getstatic 93	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   231: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   234: getstatic 319	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   237: invokestatic 97	ictk/util/Log:removeMask	(J)V
/*     */     //   240: aload 5
/*     */     //   242: areturn
/*     */     // Line number table:
/*     */     //   Java source line #661	-> byte code offset #0
/*     */     //   Java source line #662	-> byte code offset #2
/*     */     //   Java source line #663	-> byte code offset #5
/*     */     //   Java source line #665	-> byte code offset #14
/*     */     //   Java source line #666	-> byte code offset #22
/*     */     //   Java source line #667	-> byte code offset #28
/*     */     //   Java source line #671	-> byte code offset #34
/*     */     //   Java source line #673	-> byte code offset #37
/*     */     //   Java source line #674	-> byte code offset #60
/*     */     //   Java source line #675	-> byte code offset #64
/*     */     //   Java source line #676	-> byte code offset #68
/*     */     //   Java source line #675	-> byte code offset #76
/*     */     //   Java source line #674	-> byte code offset #79
/*     */     //   Java source line #679	-> byte code offset #84
/*     */     //   Java source line #680	-> byte code offset #94
/*     */     //   Java source line #681	-> byte code offset #103
/*     */     //   Java source line #682	-> byte code offset #109
/*     */     //   Java source line #685	-> byte code offset #115
/*     */     //   Java source line #686	-> byte code offset #118
/*     */     //   Java source line #689	-> byte code offset #127
/*     */     //   Java source line #690	-> byte code offset #137
/*     */     //   Java source line #691	-> byte code offset #143
/*     */     //   Java source line #694	-> byte code offset #149
/*     */     //   Java source line #697	-> byte code offset #152
/*     */     //   Java source line #698	-> byte code offset #162
/*     */     //   Java source line #699	-> byte code offset #171
/*     */     //   Java source line #700	-> byte code offset #177
/*     */     //   Java source line #702	-> byte code offset #183
/*     */     //   Java source line #685	-> byte code offset #185
/*     */     //   Java source line #705	-> byte code offset #198
/*     */     //   Java source line #706	-> byte code offset #200
/*     */     //   Java source line #708	-> byte code offset #203
/*     */     //   Java source line #709	-> byte code offset #205
/*     */     //   Java source line #710	-> byte code offset #209
/*     */     //   Java source line #711	-> byte code offset #215
/*     */     //   Java source line #713	-> byte code offset #221
/*     */     //   Java source line #709	-> byte code offset #224
/*     */     //   Java source line #710	-> byte code offset #228
/*     */     //   Java source line #711	-> byte code offset #234
/*     */     //   Java source line #714	-> byte code offset #240
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	243	0	file	String
/*     */     //   0	243	1	debug	boolean
/*     */     //   0	243	2	gameToDebug	int
/*     */     //   1	191	3	game	Game
/*     */     //   3	183	4	in	PGNReader
/*     */     //   12	229	5	list	List
/*     */     //   35	123	6	count	int
/*     */     //   198	3	6	e	Exception
/*     */     //   203	19	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   34	195	198	java/lang/Exception
/*     */     //   34	203	203	finally
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\PGNReaderTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */