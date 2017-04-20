/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessPlayer;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.io.Annotation;
/*     */ import ictk.boardgame.io.MoveNotation;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class PGNWriter
/*     */   extends ChessWriter
/*     */ {
/*  60 */   public static final long DEBUG = Log.GameWriter;
/*     */   
/*     */   public static final int NO_GLYPH = 0;
/*     */   
/*     */   public static final int SYMBOLIC_AND_NUMERIC_GLYPH = 1;
/*     */   
/*     */   public static final int NUMERIC_GLYPH = 2;
/*     */   
/*     */   public static final int SYMBOLIC_ONLY_GLYPH = 3;
/*     */   
/*     */   public static final int SUFFIX_ONLY_GLYPH = 4;
/*     */   
/*     */   protected static final int _NOTHING = 0;
/*     */   
/*     */   protected static final int _MOVE_W = 1;
/*     */   
/*     */   protected static final int _MOVE_B = 2;
/*     */   
/*     */   protected static final int _VARIATION_BEGIN = 3;
/*     */   
/*     */   protected static final int _VARIATION_END = 4;
/*     */   
/*     */   protected static final int _COMMENT = 5;
/*     */   
/*     */   protected static final int _GLYPH = 6;
/*     */   
/*     */   protected static final int _RESULT = 7;
/*     */   protected static final int _MISC = 8;
/*  88 */   protected StringBuffer buffer = null;
/*     */   
/*     */ 
/*  91 */   protected int colWidth = 80;
/*     */   
/*  93 */   protected int glyphStyle = 1;
/*     */   
/*  95 */   protected boolean exportComments = true;
/*     */   
/*  97 */   protected boolean exportVariations = true;
/*     */   
/*  99 */   protected boolean indentComments = false;
/*     */   
/* 101 */   protected boolean indentVariations = false;
/*     */   
/* 103 */   protected boolean oneMovePerLine = false;
/*     */   
/*     */ 
/* 106 */   private boolean needNumber = false;
/*     */   
/* 108 */   private int variationsDeep = 0;
/* 109 */   private String indentStr = "    ";
/*     */   
/*     */ 
/* 112 */   protected ChessMoveNotation notation = new SAN();
/*     */   
/*     */   public PGNWriter(OutputStream _out)
/*     */   {
/* 116 */     super(_out);
/*     */   }
/*     */   
/*     */   public PGNWriter(Writer _out) {
/* 120 */     super(_out);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMoveNotation(MoveNotation notation)
/*     */   {
/* 126 */     this.notation = ((ChessMoveNotation)notation);
/*     */   }
/*     */   
/* 129 */   public MoveNotation getMoveNotation() { return this.notation; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setColumnWidth(int col)
/*     */   {
/* 136 */     this.colWidth = col;
/*     */   }
/*     */   
/*     */   public int getColumnWidth()
/*     */   {
/* 141 */     return this.colWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setExportComments(boolean t)
/*     */   {
/* 148 */     this.exportComments = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isExportComments()
/*     */   {
/* 155 */     return this.exportComments;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setExportVariations(boolean t)
/*     */   {
/* 162 */     this.exportVariations = t;
/*     */   }
/*     */   
/*     */   public boolean isExportVariations()
/*     */   {
/* 167 */     return this.exportVariations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 175 */   public void setIndentComments(boolean t) { this.indentComments = t; }
/* 176 */   public boolean isIndentComments() { return this.indentComments; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 184 */   public void setIndentVariations(boolean t) { this.indentVariations = t; }
/* 185 */   public boolean isIndentVariations() { return this.indentVariations; }
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
/*     */   public void setAnnotationGlyphStyle(int style)
/*     */   {
/* 205 */     this.glyphStyle = style;
/*     */   }
/*     */   
/*     */   public int getAnnotationGlyphStyle()
/*     */   {
/* 210 */     return this.glyphStyle;
/*     */   }
/*     */   
/*     */   public void writeGame(Game game) throws IOException
/*     */   {
/* 215 */     ChessGame g = (ChessGame)game;
/*     */     
/* 217 */     if (g == null)
/*     */     {
/* 219 */       Log.debug(DEBUG, "can't write a null game");
/* 220 */       throw new NullPointerException("can't write null game");
/*     */     }
/*     */     
/*     */ 
/* 224 */     Log.debug(DEBUG, "Writing game");
/*     */     
/* 226 */     g.getHistory().rewind();
/* 227 */     writeGameInfo(g.getGameInfo());
/* 228 */     if (!g.getBoard().isInitialPositionDefault())
/* 229 */       writeBoard(g.getBoard());
/* 230 */     println();
/* 231 */     writeHistory(g.getHistory());
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeGameInfo(GameInfo gameinfo)
/*     */     throws IOException
/*     */   {
/* 238 */     ChessGameInfo gi = (ChessGameInfo)gameinfo;
/* 239 */     StringBuffer sb = new StringBuffer();
/*     */     String result;
/*     */     String black;
/* 242 */     String white; String round; String date; String site; String event = site = date = round = white = black = result = null;
/*     */     
/*     */ 
/* 245 */     if (gi == null) {
/* 246 */       Log.debug(DEBUG, "gameInfo is null, so writing default header");
/*     */     }
/* 248 */     if (gi != null) {
/* 249 */       event = gi.getEvent();
/* 250 */       site = gi.getSite();
/* 251 */       date = gi.getDateString();
/* 252 */       round = gi.getRound();
/* 253 */       if (gi.getWhite() != null)
/* 254 */         white = gi.getWhite().getName();
/* 255 */       if (gi.getBlack() != null) {
/* 256 */         black = gi.getBlack().getName();
/*     */       }
/* 258 */       result = this.notation.resultToString(gi.getResult());
/*     */     }
/*     */     
/* 261 */     if (event == null) {
/* 262 */       event = "?";
/*     */     }
/* 264 */     if (site == null) {
/* 265 */       site = "?";
/*     */     }
/* 267 */     if (date == null) {
/* 268 */       date = "????.??.??";
/*     */     }
/* 270 */     if (white == null) {
/* 271 */       white = "?";
/*     */     }
/* 273 */     if (black == null) {
/* 274 */       black = "?";
/*     */     }
/* 276 */     if (round == null) {
/* 277 */       round = "-";
/*     */     }
/* 279 */     if (result == null) {
/* 280 */       result = "*";
/*     */     }
/*     */     
/* 283 */     sb.append("[Event \"").append(event).append("\"]\n");
/* 284 */     sb.append("[Site \"").append(site).append("\"]\n");
/* 285 */     sb.append("[Date \"").append(date).append("\"]\n");
/* 286 */     sb.append("[Round \"").append(round).append("\"]\n");
/* 287 */     if ((gi != null) && 
/* 288 */       (gi.getSubRound() != null) && 
/* 289 */       (!gi.getSubRound().equals("")))
/* 290 */       sb.append("[SubRound \"").append(gi.getSubRound()).append("\"]\n");
/* 291 */     sb.append("[White \"").append(white).append("\"]\n");
/* 292 */     sb.append("[Black \"").append(black).append("\"]\n");
/* 293 */     sb.append("[Result \"").append(result).append("\"]\n");
/*     */     
/*     */ 
/* 296 */     if (gi != null) {
/* 297 */       if (gi.getWhiteRating() > 0)
/*     */       {
/* 299 */         sb.append("[WhiteElo \"").append(gi.getWhiteRating()).append("\"]\n");
/*     */       }
/* 301 */       if (gi.getBlackRating() > 0)
/*     */       {
/* 303 */         sb.append("[BlackElo \"").append(gi.getBlackRating()).append("\"]\n");
/*     */       }
/* 305 */       if (gi.getECO() != null) {
/* 306 */         sb.append("[ECO \"").append(gi.getECO()).append("\"]\n");
/*     */       }
/* 308 */       if (gi.getTimeControlInitial() > 0)
/*     */       {
/*     */ 
/* 311 */         sb.append("[TimeControl \"").append(gi.getTimeControlInitial()).append("+").append(gi.getTimeControlIncrement()).append("\"]\n");
/*     */       }
/* 313 */       Enumeration keys = gi.props.propertyNames();
/* 314 */       String key = null;String value = null;
/* 315 */       while (keys.hasMoreElements()) {
/* 316 */         key = (String)keys.nextElement();
/* 317 */         value = gi.props.getProperty(key);
/* 318 */         sb.append("[").append(key).append(" \"")
/* 319 */           .append(value).append("\"]\n");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 324 */     Log.debug(DEBUG, "writing gameInfo block to stream");
/* 325 */     print(sb);
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
/*     */ 
/*     */   public synchronized void writeHistory(History history)
/*     */     throws IOException
/*     */   {
/* 344 */     Move currMove = null;
/* 345 */     Move lastMove = null;
/* 346 */     Move walker = null;
/* 347 */     ChessResult result = null;
/* 348 */     int num = history.getInitialMoveNumber();
/*     */     
/* 350 */     if (history == null)
/*     */     {
/* 352 */       Log.debug(DEBUG, "can't write a null history");
/* 353 */       throw new NullPointerException("can't write null history");
/*     */     }
/*     */     
/* 356 */     this.buffer = new StringBuffer(this.colWidth);
/*     */     
/*     */ 
/* 359 */     Log.debug(DEBUG, "walking the History move tree");
/*     */     
/* 361 */     walkMoveTreeBreadthFirst(history.getFirstAll(), num);
/*     */     
/* 363 */     walker = history.getFinalMove(true);
/*     */     
/* 365 */     if (walker != null) {
/* 366 */       result = (ChessResult)walker.getResult();
/*     */     }
/* 368 */     if ((result == null) || (result.isUndecided())) {
/* 369 */       formatOutput("*", 7);
/*     */     } else {
/* 371 */       formatOutput(this.notation.resultToString(result), 7);
/*     */     }
/*     */     
/* 374 */     if (this.buffer.length() != 0) {
/* 375 */       print(this.buffer.toString());
/*     */     }
/* 377 */     println();
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
/*     */   protected void walkMoveTreeBreadthFirst(ContinuationList cont, int num)
/*     */   {
/* 390 */     ChessMove m = null;
/* 391 */     boolean isBlackMove = true;
/* 392 */     short[] nags = (short[])null;
/*     */     
/*     */ 
/* 395 */     StringBuffer sbtmp = new StringBuffer(12);
/*     */     
/* 397 */     ChessAnnotation anno = null;
/*     */     
/*     */ 
/* 400 */     Log.debug(DEBUG, "continuations(" + cont.size() + ")" + (
/* 401 */       cont.isTerminal() ? " is " : " is not ") + 
/* 402 */       "terminal");
/*     */     
/* 404 */     if ((cont != null) && (!cont.isTerminal())) {
/* 405 */       ChessMove currMove = null;
/*     */       
/* 407 */       if (cont.get(0) != null) {
/* 408 */         currMove = (ChessMove)cont.get(0);
/*     */       }
/* 410 */       else if (cont.get(1) != null) {
/* 411 */         currMove = (ChessMove)cont.get(1);
/*     */       }
/* 413 */       else if (!$assertionsDisabled) {
/* 414 */         throw new AssertionError("ContinuationList contained null mainline, no variations and was not isTerminal().");
/*     */       }
/* 416 */       if (currMove.isNullMove()) {
/* 417 */         isBlackMove = !((ChessMove)currMove.getPrev()).isBlackMove();
/*     */       } else {
/* 419 */         isBlackMove = currMove.isBlackMove();
/*     */       }
/*     */       
/* 422 */       for (int i = 0; ((i == 0) || (this.exportVariations)) && (i < cont.size()); i++) {
/* 423 */         m = (ChessMove)cont.get(i);
/*     */         
/* 425 */         if (i > 0) {
/* 426 */           this.variationsDeep += 1;
/* 427 */           formatOutput("(", 3);
/*     */         }
/*     */         
/*     */ 
/* 431 */         if ((this.exportComments) && 
/* 432 */           (m.getPrenotation() != null) && 
/* 433 */           (m.getPrenotation().getComment() != null))
/*     */         {
/* 435 */           formatOutput(" {" + 
/* 436 */             m.getPrenotation().getComment() + 
/* 437 */             "} ", 
/* 438 */             5);
/*     */         }
/*     */         
/*     */ 
/* 442 */         if (!isBlackMove) {
/* 443 */           sbtmp.append(num).append(".");
/*     */         }
/* 445 */         if (((i > 0) || (this.needNumber)) && (isBlackMove)) {
/* 446 */           sbtmp.append(num).append("...");
/*     */         }
/* 448 */         this.needNumber = false;
/*     */         
/*     */ 
/* 451 */         sbtmp.append(this.notation.moveToString(m));
/*     */         
/* 453 */         anno = (ChessAnnotation)m.getAnnotation();
/*     */         
/*     */ 
/* 456 */         if ((anno != null) && 
/* 457 */           (this.glyphStyle != 0) && 
/* 458 */           (this.glyphStyle != 2) && 
/* 459 */           (anno.getSuffix() != 0)) {
/* 460 */           sbtmp.append(NAG.numberToString(anno.getSuffix()));
/*     */         }
/*     */         
/*     */ 
/* 464 */         formatOutput(sbtmp.toString(), 
/* 465 */           isBlackMove ? 2 : 1);
/*     */         
/*     */ 
/* 468 */         sbtmp.delete(0, sbtmp.length());
/*     */         
/*     */ 
/* 471 */         if ((anno != null) && 
/* 472 */           (this.glyphStyle != 0) && 
/* 473 */           (this.glyphStyle != 4) && 
/* 474 */           ((nags = anno.getNAGs()) != null))
/*     */         {
/*     */ 
/* 477 */           for (int j = (NAG.isSuffix(nags[0])) && 
/* 478 */                 (this.glyphStyle != 2) ? 1 : 0; 
/* 479 */               j < nags.length; j++)
/*     */           {
/* 481 */             this.needNumber = true;
/*     */             
/* 483 */             switch (this.glyphStyle)
/*     */             {
/*     */             case 2: 
/* 486 */               formatOutput(NAG.numberToString(nags[j], true), 
/* 487 */                 6);
/* 488 */               break;
/*     */             
/*     */             case 3: 
/* 491 */               if (NAG.isSymbol(nags[j]))
/* 492 */                 formatOutput(NAG.numberToString(nags[j]), 
/* 493 */                   6);
/* 494 */               break;
/*     */             
/*     */             case 1: 
/* 497 */               formatOutput(NAG.numberToString(nags[j]), 
/* 498 */                 6);
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 505 */         if ((this.exportComments) && 
/* 506 */           (anno != null) && 
/* 507 */           (anno.getComment() != null)) {
/* 508 */           formatOutput("{" + 
/* 509 */             anno.getComment() + 
/* 510 */             "}", 
/* 511 */             5);
/* 512 */           this.needNumber = true;
/*     */         }
/*     */         
/*     */ 
/* 516 */         if ((m != null) && (this.exportVariations) && (i > 0))
/*     */         {
/* 518 */           Log.debug(DEBUG, m + " descending variation");
/* 519 */           walkMoveTreeBreadthFirst(m.getContinuationList(), 
/* 520 */             num + (isBlackMove ? 1 : 0));
/*     */           
/* 522 */           formatOutput(")", 4);
/* 523 */           this.variationsDeep -= 1;
/* 524 */           this.needNumber = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 529 */       m = (ChessMove)cont.get(0);
/* 530 */       if (m != null)
/*     */       {
/* 532 */         Log.debug(DEBUG, m + " descending mainline");
/* 533 */         walkMoveTreeBreadthFirst(m.getContinuationList(), 
/* 534 */           num + (isBlackMove ? 1 : 0));
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void formatOutput(String str, int type)
/*     */   {
/* 561 */     boolean spacer = this.buffer.length() != 0;
/* 562 */     int length = this.buffer.length() + 
/* 563 */       str.length() + (
/* 564 */       spacer ? 1 : 0);
/*     */     
/*     */ 
/* 567 */     Log.debug(DEBUG, 
/* 568 */       "[" + length + "/" + this.colWidth + "] " + 
/* 569 */       "buffer(" + this.buffer.length() + 
/* 570 */       ") + \"" + str + "\"(" + str.length() + ")");
/*     */     
/*     */ 
/*     */ 
/* 574 */     if ((this.indentVariations) && 
/* 575 */       (type == 3) && 
/* 576 */       (this.variationsDeep > 0)) {
/* 577 */       println(this.buffer.toString());
/* 578 */       this.buffer.delete(0, this.buffer.length());
/*     */       
/* 580 */       if (this.variationsDeep == 1) {
/* 581 */         this.buffer.append(this.indentStr);
/*     */       } else {
/* 583 */         this.buffer.append(this.indentStr).append(this.indentStr);
/*     */       }
/* 585 */       this.buffer.append(str);
/*     */ 
/*     */ 
/*     */     }
/* 589 */     else if ((this.indentVariations) && 
/* 590 */       (type == 4) && 
/* 591 */       (this.variationsDeep > 0))
/*     */     {
/* 593 */       if (this.buffer.length() == 0) {
/* 594 */         if (this.variationsDeep == 1) {
/* 595 */           print(this.indentStr);
/* 596 */           println(str);
/*     */         }
/*     */         else {
/* 599 */           print(this.indentStr);
/* 600 */           print(this.indentStr);
/* 601 */           println(str);
/* 602 */           if (this.variationsDeep == 2) {
/* 603 */             this.buffer.append(this.indentStr);
/*     */           } else {
/* 605 */             this.buffer.append(this.indentStr).append(this.indentStr);
/*     */           }
/*     */         }
/*     */       } else {
/* 609 */         print(this.buffer.toString());
/* 610 */         print(" ");
/* 611 */         println(str);
/* 612 */         this.buffer.delete(0, this.buffer.length());
/*     */       }
/*     */       
/*     */     }
/* 616 */     else if ((this.indentComments) && (type == 5) && (this.variationsDeep == 0)) {
/* 617 */       println(this.buffer.toString());
/* 618 */       this.buffer.delete(0, this.buffer.length());
/*     */       
/* 620 */       if (this.indentStr.length() + str.length() > this.colWidth) {
/* 621 */         formatLongComment(str);
/*     */       } else {
/* 623 */         println(this.indentStr + str);
/*     */       }
/*     */       
/*     */     }
/* 627 */     else if (length <= this.colWidth) {
/* 628 */       if (spacer)
/* 629 */         this.buffer.append(" ");
/* 630 */       this.buffer.append(str);
/*     */       
/* 632 */       Log.debug(DEBUG, "appending: " + str);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 637 */     else if (type != 5) {
/* 638 */       println(this.buffer.toString());
/*     */       
/* 640 */       Log.debug(DEBUG, "writing: " + this.buffer.toString());
/* 641 */       this.buffer.delete(0, this.buffer.length());
/*     */       
/* 643 */       if (this.indentVariations) {
/* 644 */         if (this.variationsDeep > 0)
/* 645 */           this.buffer.append(this.indentStr);
/* 646 */         if (this.variationsDeep > 1) {
/* 647 */           this.buffer.append(this.indentStr);
/*     */         }
/*     */       }
/* 650 */       this.buffer.append(str);
/*     */     }
/*     */     else
/*     */     {
/* 654 */       formatLongComment(str);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void formatLongComment(String str)
/*     */   {
/* 660 */     int len = this.buffer.length();
/* 661 */     String tok = null;
/* 662 */     StringTokenizer st = new StringTokenizer(str, " ", false);
/*     */     
/* 664 */     while (st.hasMoreTokens()) {
/* 665 */       tok = st.nextToken();
/*     */       
/*     */ 
/* 668 */       if (len + 1 + tok.length() <= this.colWidth)
/*     */       {
/* 670 */         if (this.buffer.length() == 0) {
/* 671 */           if ((this.indentComments) && (this.variationsDeep == 0)) {
/* 672 */             this.buffer.append(this.indentStr);
/*     */           }
/*     */         } else {
/* 675 */           this.buffer.append(" ");
/*     */         }
/* 677 */         this.buffer.append(tok);
/* 678 */         len = this.buffer.length();
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 684 */         Log.debug(DEBUG, "writing: " + this.buffer.toString());
/*     */         
/* 686 */         println(this.buffer.toString());
/*     */         
/* 688 */         this.buffer.delete(0, this.buffer.length());
/*     */         
/* 690 */         if (this.indentComments) {
/* 691 */           if (this.variationsDeep == 0) {
/* 692 */             this.buffer.append(this.indentStr);
/* 693 */           } else if ((this.indentVariations) && (this.variationsDeep > 0)) {
/* 694 */             if (this.variationsDeep == 1) {
/* 695 */               this.buffer.append(this.indentStr);
/*     */             } else {
/* 697 */               this.buffer.append(this.indentStr).append(this.indentStr);
/*     */             }
/*     */           }
/*     */         }
/* 701 */         this.buffer.append(tok);
/* 702 */         len = this.buffer.length();
/*     */       }
/*     */     }
/*     */     
/* 706 */     if ((this.indentComments) && (this.variationsDeep == 0) && (this.buffer.length() > 0)) {
/* 707 */       println(this.buffer.toString());
/* 708 */       this.buffer.delete(0, this.buffer.length());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeBoard(Board board)
/*     */     throws IOException
/*     */   {
/* 718 */     StringBuffer buff = new StringBuffer();
/*     */     
/* 720 */     buff.append("[FEN \"");
/* 721 */     buff.append(new FEN().boardToString(board));
/* 722 */     buff.append("\"]");
/*     */     
/* 724 */     println(buff.toString());
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\PGNWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */