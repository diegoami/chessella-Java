/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.OutOfTurnException;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessPlayer;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.io.Annotation;
/*     */ import ictk.boardgame.io.InvalidGameFormatException;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Stack;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class PGNReader
/*     */   extends ChessReader
/*     */ {
/*  60 */   private String firstGameLine = null;
/*     */   
/*  62 */   public static final long DEBUG = Log.GameReader;
/*     */   
/*     */ 
/*  65 */   protected static FEN fen = new FEN();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  71 */   protected ChessMoveNotation notation = new SAN();
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
/*  84 */   protected static final Pattern giPattern = Pattern.compile("\\[\\s*(\\w+)\\s+\"(.*)\"\\s*\\]");
/*     */   protected ChessGame game;
/*     */   protected ChessGameInfo gameInfo;
/*     */   protected ChessBoard board;
/*     */   
/*  89 */   public PGNReader(Reader _ir) { super(_ir); }
/*     */   
/*     */ 
/*     */   public Game readGame()
/*     */     throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException
/*     */   {
/*  95 */     History history = null;
/*     */     
/*  97 */     this.gameInfo = ((ChessGameInfo)readGameInfo());
/*  98 */     this.board = ((ChessBoard)readBoard());
/*  99 */     if (this.board == null)
/* 100 */       this.board = new ChessBoard();
/*     */     try {
/* 102 */       this.game = new ChessGame(this.gameInfo, this.board);
/*     */       
/* 104 */       history = readHistory();
/*     */     } catch (Exception e) {
/* 106 */       e.printStackTrace();
/* 107 */       this.game = new ChessGame(this.gameInfo, this.board = new ChessBoard());
/*     */       try {
/* 109 */         history = readHistory();
/*     */       } catch (Exception ee) {
/* 111 */         System.out.println(this.gameInfo.toString());
/* 112 */         ee.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/* 116 */     if ((this.gameInfo == null) && (history == null)) {
/* 117 */       return null;
/*     */     }
/* 119 */     return this.game;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Game getGame()
/*     */   {
/* 128 */     return this.game;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public GameInfo readGameInfo()
/*     */     throws IOException
/*     */   {
/* 137 */     String line = null;
/* 138 */     Matcher result = null;
/* 139 */     ChessGameInfo gameInfo = new ChessGameInfo();
/* 140 */     boolean headerFound = false;boolean headerDone = false;
/* 141 */     String key = null;String value = null;
/* 142 */     this.firstGameLine = null;
/* 143 */     mark(100);
/* 144 */     while ((!headerDone) && ((line = readLine()) != null)) {
/* 145 */       if (!line.startsWith("%"))
/*     */       {
/* 147 */         if ((headerFound) && (line.equals(""))) {
/* 148 */           headerDone = true;
/*     */         } else {
/* 150 */           result = giPattern.matcher(line);
/* 151 */           if (result.find()) {
/* 152 */             headerFound = true;
/*     */             
/*     */ 
/* 155 */             Log.debug(DEBUG, "GameInfo header", result);
/*     */             
/* 157 */             key = result.group(1);
/* 158 */             value = result.group(2);
/* 159 */             _setGameInfo(gameInfo, key, value);
/* 160 */             mark(100);
/* 161 */           } else if ((line.startsWith("1.")) || (line.startsWith("{"))) {
/* 162 */             headerDone = true;
/* 163 */             this.firstGameLine = line;
/* 164 */             reset();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 170 */     if (!headerFound) {
/* 171 */       return null;
/*     */     }
/* 173 */     return gameInfo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public History readHistory()
/*     */     throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException
/*     */   {
/* 182 */     History history = this.game.getHistory();
/* 183 */     boolean finished = false;boolean done = false;
/* 184 */     String line = null;
/* 185 */     String tok = null;String tok2 = null;
/* 186 */     ChessMove move = null;
/* 187 */     ChessMove lastMove = null;
/* 188 */     int count = 0;
/* 189 */     int i = 0;
/* 190 */     ChessResult res = null;
/* 191 */     Stack forks = new Stack();
/* 192 */     ChessAnnotation anno = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 198 */     String savedComment = null;
/* 199 */     short nag = 0;
/* 200 */     StreamTokenizer st = new StreamTokenizer(this);
/* 201 */     StringBuffer sb = new StringBuffer();
/*     */     
/*     */ 
/* 204 */     Log.debug(DEBUG, "reading History");
/*     */     
/*     */ 
/* 207 */     st.ordinaryChars(33, 255);
/* 208 */     st.wordChars(33, 255);
/*     */     
/*     */ 
/* 211 */     st.ordinaryChar(46);
/* 212 */     st.ordinaryChar(40);
/* 213 */     st.ordinaryChar(41);
/* 214 */     st.ordinaryChar(123);
/* 215 */     st.ordinaryChar(125);
/* 216 */     st.ordinaryChar(59);
/* 217 */     st.ordinaryChar(42);
/*     */     
/* 219 */     st.ordinaryChar(9);
/* 220 */     st.eolIsSignificant(true);
/*     */     
/*     */ 
/* 223 */     while ((!finished) && (st.nextToken() != -1)) {
/* 224 */       tok = st.sval;
/*     */       
/*     */ 
/* 227 */       Log.debug(DEBUG, "token: " + tok);
/*     */       
/*     */ 
/* 230 */       if (tok == null) {
/* 231 */         switch (st.ttype)
/*     */         {
/*     */         case 10: 
/*     */         case 46: 
/*     */           break;
/*     */         
/*     */         case 59: 
/* 238 */           sb = new StringBuffer();
/*     */           
/* 240 */           st.ordinaryChar(32);
/* 241 */           while (st.nextToken() != 10) {
/* 242 */             tok2 = st.sval;
/* 243 */             if (st.ttype != 9)
/* 244 */               if (tok2 != null) {
/* 245 */                 sb.append(tok2);
/*     */               } else
/* 247 */                 sb.append((char)st.ttype);
/*     */           }
/* 249 */           st.whitespaceChars(32, 32);
/*     */           
/*     */ 
/*     */ 
/* 253 */           Log.debug(DEBUG, "eol comment: {" + sb.toString() + 
/* 254 */             "}");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 261 */           if ((lastMove != null) && 
/* 262 */             (lastMove == history.getCurrentMove())) {
/* 263 */             anno = (ChessAnnotation)lastMove.getAnnotation();
/*     */             
/* 265 */             if ((anno == null) || (anno.getComment() == null)) {
/* 266 */               if (anno == null)
/* 267 */                 anno = new ChessAnnotation();
/* 268 */               anno.setComment(sb.toString());
/* 269 */               lastMove.setAnnotation(anno);
/*     */               
/*     */ 
/* 272 */               Log.debug(DEBUG, "eol comment for (" + 
/* 273 */                 lastMove + 
/* 274 */                 "): " + 
/* 275 */                 lastMove.getAnnotation()
/* 276 */                 .getComment());
/*     */             } else {
/* 278 */               anno.appendComment(" " + sb.toString());
/*     */             }
/* 280 */             anno = null;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 285 */             savedComment = sb.toString();
/*     */           }
/* 287 */           break;
/*     */         
/*     */ 
/*     */         case 123: 
/* 291 */           sb = new StringBuffer();
/* 292 */           done = false;
/*     */           
/* 294 */           st.ordinaryChar(32);
/* 295 */           while ((!done) && (st.nextToken() != -1)) {
/* 296 */             tok2 = st.sval;
/* 297 */             switch (st.ttype) {
/*     */             case 125: 
/* 299 */               done = true;
/* 300 */               break;
/*     */             case 10: 
/* 302 */               sb.append(' ');
/*     */             case 9: 
/*     */               break;
/*     */             default: 
/* 306 */               if (tok2 != null) {
/* 307 */                 sb.append(tok2);
/*     */               } else
/* 309 */                 sb.append((char)st.ttype);
/*     */               break; }
/*     */           }
/* 312 */           st.whitespaceChars(32, 32);
/*     */           
/*     */ 
/* 315 */           Log.debug(DEBUG, "comment: {" + sb.toString() + "}");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 321 */           if ((lastMove != null) && 
/* 322 */             (lastMove == history.getCurrentMove())) {
/* 323 */             anno = (ChessAnnotation)lastMove.getAnnotation();
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 328 */             if ((anno == null) || (anno.getComment() == null)) {
/* 329 */               if (anno == null)
/* 330 */                 anno = new ChessAnnotation();
/* 331 */               anno.setComment(sb.toString());
/* 332 */               lastMove.setAnnotation(anno);
/* 333 */               anno = null;
/*     */ 
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/*     */ 
/*     */ 
/* 341 */               if (savedComment != null)
/* 342 */                 lastMove.getAnnotation().appendComment(
/* 343 */                   " " + savedComment);
/* 344 */               savedComment = sb.toString();
/*     */             }
/*     */             
/*     */           }
/*     */           else
/*     */           {
/* 350 */             savedComment = sb.toString();
/*     */           }
/* 352 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         case 40: 
/* 358 */           history.prev();
/* 359 */           forks.push(history.getCurrentMove());
/*     */           
/*     */ 
/* 362 */           Log.debug(DEBUG, "starting variation from " + 
/* 363 */             history.getCurrentMove());
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 368 */           if (savedComment == null) continue;
/* 369 */           anno = (ChessAnnotation)lastMove.getAnnotation();
/* 370 */           if ((anno == null) || (anno.getComment() == null)) {
/* 371 */             if (anno == null)
/* 372 */               anno = new ChessAnnotation();
/* 373 */             anno.setComment(savedComment);
/*     */           } else {
/* 375 */             anno.appendComment(" " + savedComment); }
/* 376 */           savedComment = null;
/*     */           
/* 378 */           break;
/*     */         
/*     */ 
/*     */         case 41: 
/* 382 */           ChessMove fork = (ChessMove)forks.pop();
/* 383 */           history.goTo(fork);
/*     */           
/*     */ 
/* 386 */           Log.debug(DEBUG, "ending variation from " + fork);
/*     */           
/* 388 */           history.next();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 393 */           if (savedComment != null) {
/* 394 */             anno = (ChessAnnotation)lastMove.getAnnotation();
/* 395 */             if ((anno == null) || (anno.getComment() == null)) {
/* 396 */               if (anno == null)
/* 397 */                 anno = new ChessAnnotation();
/* 398 */               anno.setComment(savedComment);
/*     */             } else {
/* 400 */               anno.appendComment(" " + savedComment); }
/* 401 */             savedComment = null;
/*     */           }
/*     */           
/*     */ 
/* 405 */           lastMove = (ChessMove)history.getCurrentMove();
/* 406 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */         case 42: 
/* 411 */           Log.debug(DEBUG, "Result token: " + tok);
/* 412 */           if (lastMove != null)
/* 413 */             lastMove.setResult(new ChessResult(
/* 414 */               0));
/* 415 */           finished = true;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */       }
/* 423 */       else if ((nag = NAG.stringToNumber(tok)) != 0)
/*     */       {
/* 425 */         Log.debug(DEBUG, "NAG symbol(nag): " + tok);
/*     */         
/* 427 */         if (lastMove != null) {
/* 428 */           anno = (ChessAnnotation)lastMove.getAnnotation();
/* 429 */           if (anno == null)
/* 430 */             anno = new ChessAnnotation();
/* 431 */           anno.addNAG(nag);
/* 432 */           lastMove.setAnnotation(anno);
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 438 */       else if (Character.isDigit(tok.charAt(0))) {
/* 439 */         if ((res = (ChessResult)this.notation.stringToResult(tok)) != null)
/*     */         {
/* 441 */           finished = true;
/*     */           
/*     */ 
/* 444 */           Log.debug(DEBUG, "Result token: " + tok);
/* 445 */           if (lastMove != null) {
/* 446 */             lastMove.setResult(res);
/*     */             
/* 448 */             Log.debug(DEBUG, "Result set(" + lastMove + "): " + 
/* 449 */               res);
/* 450 */             ChessMove prevTmp = (ChessMove)lastMove.getPrev();
/* 451 */             if (prevTmp != null) {
/* 452 */               Log.debug(DEBUG, "Result set(" + lastMove + 
/* 453 */                 "): " + res + " prev move: " + 
/* 454 */                 lastMove.getPrev().dump());
/*     */             }
/*     */           }
/*     */           else {
/* 458 */             Log.debug(DEBUG, "Result not set; no last move");
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 465 */       else if (Character.isLetter(tok.charAt(0))) {
/*     */         try {
/* 467 */           move = (ChessMove)this.notation.stringToMove(this.board, tok);
/* 468 */           if (move != null) {
/* 469 */             history.add(move);
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 474 */             if (savedComment != null) {
/* 475 */               anno = new ChessAnnotation();
/* 476 */               anno.setComment(savedComment);
/* 477 */               move.setPrenotation(anno);
/* 478 */               Log.debug(DEBUG, "prenotation set: " + 
/* 479 */                 move.getPrenotation().getComment());
/* 480 */               savedComment = null;
/*     */             }
/*     */             
/* 483 */             lastMove = move;
/*     */             
/* 485 */             count++;
/*     */           }
/*     */           else
/*     */           {
/* 489 */             Log.debug(DEBUG, "Thought this was a move: " + tok);
/* 490 */             throw new IOException("Thought this was a move: " + tok);
/*     */           }
/*     */         }
/*     */         catch (OutOfTurnException e) {
/* 494 */           Log.debug(DEBUG, e);
/* 495 */           Log.debug2(DEBUG, "From Token: " + tok);
/* 496 */           Log.debug2(DEBUG, "Board: \n" + this.board);
/*     */           
/* 498 */           throw e;
/*     */         }
/*     */         catch (AmbiguousMoveException e) {
/* 501 */           Log.debug(DEBUG, e);
/* 502 */           Log.debug2(DEBUG, "From Token: " + tok);
/* 503 */           Log.debug2(DEBUG, "Board: \n" + this.board);
/*     */           
/* 505 */           throw e;
/*     */         }
/*     */         catch (IllegalMoveException e) {
/* 508 */           Log.debug(DEBUG, e);
/* 509 */           Log.debug2(DEBUG, "From Token: " + tok);
/* 510 */           Log.debug2(DEBUG, "Board: \n" + this.board);
/*     */           
/*     */ 
/* 513 */           throw e;
/*     */         }
/* 515 */       } else if ("--".equals(tok)) {
/* 516 */         move = new ChessMove(this.board);
/* 517 */         if (move != null) {
/* 518 */           history.add(move);
/*     */         }
/* 520 */         lastMove = move;
/*     */         
/* 522 */         count++;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 528 */         Log.debug(DEBUG, "No idea what this is: <" + tok + ">");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 534 */     history.goToEnd();
/* 535 */     if (history.getCurrentMove() != null) {
/* 536 */       Log.debug(DEBUG, "final result is: " + 
/* 537 */         history.getCurrentMove().getResult());
/*     */     }
/*     */     
/*     */ 
/* 541 */     history.rewind();
/*     */     
/* 543 */     if (count == 0)
/*     */     {
/* 545 */       Log.debug(DEBUG, "finished reading History: empty");
/* 546 */       return null;
/*     */     }
/*     */     
/* 549 */     Log.debug(DEBUG, "finished reading History");
/* 550 */     return history;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Board readBoard()
/*     */     throws IOException
/*     */   {
/* 562 */     Board board = null;
/* 563 */     String fenStr = null;
/* 564 */     Board fenOut = null;
/*     */     
/* 566 */     if (this.gameInfo == null) {
/* 567 */       return null;
/*     */     }
/* 569 */     fenStr = this.gameInfo.get("FEN");
/*     */     
/* 571 */     if (fenStr == null) {
/* 572 */       return null;
/*     */     }
/*     */     try {
/* 575 */       return fen.stringToBoard(fenStr);
/*     */     }
/*     */     catch (Exception e) {
/* 578 */       System.err.println("Could not parse ");
/* 579 */       System.err.println(fenStr);
/* 580 */       e.printStackTrace(); }
/* 581 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void _setGameInfo(ChessGameInfo gi, String key, String value)
/*     */   {
/* 588 */     StringTokenizer st = null;
/* 589 */     String tok = null;
/* 590 */     ChessPlayer p = null;
/*     */     
/*     */ 
/* 593 */     if ("Event".equalsIgnoreCase(key)) {
/* 594 */       if (!value.equals("?")) {
/* 595 */         gi.setEvent(value);
/*     */       }
/*     */       
/*     */     }
/* 599 */     else if ("Site".equalsIgnoreCase(key)) {
/* 600 */       if (!value.equals("?")) {
/* 601 */         gi.setSite(value);
/*     */       }
/*     */       
/*     */     }
/* 605 */     else if ("Round".equalsIgnoreCase(key)) {
/* 606 */       if ((!value.equals("-")) && (!value.equals("?"))) {
/* 607 */         gi.setRound(value);
/*     */       }
/*     */       
/*     */     }
/* 611 */     else if ("SubRound".equalsIgnoreCase(key)) {
/* 612 */       if ((!value.equals("-")) && (!value.equals("?"))) {
/* 613 */         gi.setSubRound(value);
/*     */       }
/*     */       
/*     */     }
/* 617 */     else if ("White".equalsIgnoreCase(key)) {
/* 618 */       if (!value.equals("")) {
/* 619 */         p = new ChessPlayer(value);
/* 620 */         gi.setWhite(p);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 625 */     else if ("Black".equalsIgnoreCase(key)) {
/* 626 */       if (!value.equals("")) {
/* 627 */         p = new ChessPlayer(value);
/* 628 */         gi.setBlack(p);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 633 */     else if ("Result".equalsIgnoreCase(key)) {
/* 634 */       gi.setResult((ChessResult)this.notation.stringToResult(value));
/*     */ 
/*     */ 
/*     */     }
/* 638 */     else if ("WhiteElo".equalsIgnoreCase(key)) {
/*     */       try {
/* 640 */         gi.setWhiteRating(Integer.parseInt(value));
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException) {}
/*     */ 
/*     */     }
/* 647 */     else if ("BlackElo".equalsIgnoreCase(key)) {
/*     */       try {
/* 649 */         gi.setBlackRating(Integer.parseInt(value));
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException1) {}
/*     */ 
/*     */     }
/* 656 */     else if ("ECO".equalsIgnoreCase(key)) {
/* 657 */       gi.setECO(value);
/*     */ 
/*     */     }
/* 660 */     else if ("TimeControl".equalsIgnoreCase(key)) {
/* 661 */       st = new StringTokenizer(value, "+", false);
/*     */       try {
/* 663 */         if (!st.hasMoreTokens()) return;
/* 664 */         gi.setTimeControlInitial(Integer.parseInt(st.nextToken()));
/*     */         
/* 666 */         if (!st.hasMoreTokens()) return;
/* 667 */         gi.setTimeControlIncrement(Integer.parseInt(
/* 668 */           st.nextToken()));
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException2) {}
/*     */ 
/*     */ 
/*     */     }
/* 676 */     else if ("Date".equalsIgnoreCase(key)) {
/* 677 */       if (!value.equals("????.??.??")) {
/* 678 */         Calendar date = new GregorianCalendar();
/* 679 */         st = new StringTokenizer(value, "./", false);
/* 680 */         tok = null;
/*     */         try
/*     */         {
/* 683 */           if (st.hasMoreTokens()) {
/* 684 */             tok = st.nextToken();
/* 685 */             if (!tok.startsWith("?")) {
/* 686 */               date.set(1, Integer.parseInt(tok));
/* 687 */               gi.setYear(Integer.parseInt(tok));
/*     */             }
/*     */             
/* 690 */             if (st.hasMoreTokens()) {
/* 691 */               tok = st.nextToken();
/* 692 */               if (!tok.startsWith("?")) {
/* 693 */                 date.set(2, 
/* 694 */                   Integer.parseInt(tok) - 1);
/* 695 */                 gi.setMonth(Integer.parseInt(tok));
/*     */               }
/*     */               
/* 698 */               if (st.hasMoreTokens()) {
/* 699 */                 tok = st.nextToken();
/* 700 */                 if (!tok.startsWith("?")) {
/* 701 */                   date.set(5, 
/* 702 */                     Integer.parseInt(tok));
/* 703 */                   gi.setDay(Integer.parseInt(tok));
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (NumberFormatException localNumberFormatException3) {}
/*     */         
/* 711 */         gi.setDate(date);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 716 */       gi.add(key, value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\PGNReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */