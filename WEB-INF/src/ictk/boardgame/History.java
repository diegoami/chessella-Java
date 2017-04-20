/*     */ package ictk.boardgame;
/*     */ 
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import ictk.util.Log;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
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
/*     */ public class History
/*     */ {
/*  89 */   public static final long DEBUG = Log.History;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Game game;
/*     */   
/*     */ 
/*     */   protected ContinuationList head;
/*     */   
/*     */ 
/*     */   protected Move currMove;
/*     */   
/*     */ 
/* 102 */   protected int initialMoveNumber = 1;
/*     */   
/* 104 */   protected int currMoveNumber = 0;
/*     */   
/*     */ 
/*     */   public History(Game game)
/*     */   {
/* 109 */     this(game, 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public History(Game game, int initMoveNum)
/*     */   {
/* 115 */     this.game = game;
/* 116 */     this.head = new ContinuationArrayList(null);
/* 117 */     this.currMove = null;
/* 118 */     this.currMoveNumber = (this.initialMoveNumber = initMoveNum);
/*     */   }
/*     */   
/*     */   public void setGame(Game newGame) {
/* 122 */     this.game = newGame;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 126 */     FEN fen = new FEN();
/* 127 */     String boardString = fen.boardToString(getGame().getBoard());
/* 128 */     ChessGame newGame = new ChessGame(getGame().getGameInfo(), 
/* 129 */       getGame().getBoard(), 
/* 130 */       this);
/*     */     
/*     */ 
/* 133 */     History history = new History(newGame, this.currMoveNumber);
/* 134 */     newGame.setHistory(history);
/* 135 */     history.currMove = this.currMove;
/* 136 */     history.head = this.head;
/* 137 */     return history;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getInitialMoveNumber()
/*     */   {
/* 144 */     return this.initialMoveNumber;
/*     */   }
/*     */   
/*     */   public void setInitialMoveNumber(int i) {
/* 148 */     this.currMoveNumber += i - this.initialMoveNumber;
/* 149 */     this.initialMoveNumber = i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCurrentMoveNumber()
/*     */   {
/* 157 */     return this.currMoveNumber;
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
/*     */   public void add(Move _move, boolean asMainLine)
/*     */     throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 182 */     Move next = null;
/* 183 */     boolean found = false;
/* 184 */     ContinuationList cont = null;
/*     */     
/* 186 */     _move.setHistory(this);
/*     */     
/* 188 */     if (this.currMove != null) {
/* 189 */       cont = this.currMove.getContinuationList();
/*     */     } else {
/* 191 */       cont = this.head;
/*     */     }
/* 193 */     cont.add(_move, asMainLine);
/*     */     try
/*     */     {
/* 196 */       _next(_move);
/*     */     }
/*     */     catch (OutOfTurnException e) {
/* 199 */       _delBadAdd(_move);
/* 200 */       throw e;
/*     */     }
/*     */     catch (IllegalMoveException e)
/*     */     {
/* 204 */       _delBadAdd(_move);
/* 205 */       throw e;
/*     */     }
/*     */     catch (AmbiguousMoveException e) {
/* 208 */       _delBadAdd(_move);
/* 209 */       throw e;
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
/*     */   public void add(Move m)
/*     */     throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 225 */     ContinuationList cont = this.head;
/* 226 */     if (this.currMove != null) {
/* 227 */       cont = this.currMove.getContinuationList();
/*     */     }
/* 229 */     if (cont.hasMainLine()) {
/* 230 */       add(m, false);
/*     */     } else {
/* 232 */       add(m, true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addVariation(Move m)
/*     */     throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 244 */     add(m, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move getFirst()
/*     */   {
/* 254 */     return this.head.getMainLine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContinuationList getFirstAll()
/*     */   {
/* 263 */     return this.head;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move next()
/*     */   {
/*     */     try
/*     */     {
/* 273 */       return next(0);
/*     */     }
/*     */     catch (IndexOutOfBoundsException e) {
/* 276 */       throw new IndexOutOfBoundsException(
/* 277 */         "No main line for move:" + this.currMove);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Move next(int i)
/*     */   {
/*     */     try
/*     */     {
/* 286 */       return _next(i);
/*     */     }
/*     */     catch (MoveException e) {
/* 289 */       if (!$assertionsDisabled)
/* 290 */         throw new AssertionError("Already verified move threw: " + e.getMessage() + 
/* 291 */           " for move: " + e.getMove().dump()); }
/* 292 */     return null;
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
/*     */   public Move next(Move m)
/*     */   {
/* 306 */     ContinuationList cont = this.head;
/*     */     
/* 308 */     if (this.currMove != null) {
/* 309 */       cont = this.currMove.getContinuationList();
/*     */     }
/* 311 */     if (!cont.exists(m)) {
/* 312 */       throw new IndexOutOfBoundsException(
/* 313 */         "this move is not found in the current list of continuations.");
/*     */     }
/*     */     try
/*     */     {
/* 317 */       return _next(m);
/*     */     }
/*     */     catch (MoveException e) {
/* 320 */       if (!$assertionsDisabled)
/* 321 */         throw new AssertionError("Already verified move threw: " + e.getMessage() + 
/* 322 */           " for move: " + e.getMove().dump()); }
/* 323 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Move _next(Move m)
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 336 */     m.execute();
/* 337 */     this.currMoveNumber += 1;
/* 338 */     this.currMove = m;
/* 339 */     return this.currMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Move _next(int i)
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 352 */     Move m = null;
/*     */     
/* 354 */     if (this.currMove != null) {
/* 355 */       m = this.currMove.getContinuationList().get(i);
/*     */     } else {
/* 357 */       m = this.head.get(i);
/*     */     }
/*     */     
/* 360 */     if (m == null) {
/* 361 */       throw new IndexOutOfBoundsException(
/* 362 */         "variation [" + i + "] does not exist for " + this.currMove);
/*     */     }
/* 364 */     _next(m);
/*     */     
/*     */ 
/* 367 */     return this.currMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move getNext()
/*     */   {
/* 375 */     return getNext(0);
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
/*     */   public Move getNext(int i)
/*     */   {
/* 390 */     if (this.currMove == null) {
/* 391 */       return this.head.get(i);
/*     */     }
/* 393 */     return this.currMove.getContinuationList().get(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContinuationList getContinuationList()
/*     */   {
/* 401 */     if (this.currMove == null) return this.head;
/* 402 */     return this.currMove.getContinuationList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move prev()
/*     */   {
/* 411 */     Move prev = null;
/* 412 */     if (this.currMove == null) {
/* 413 */       prev = null;
/*     */     } else {
/* 415 */       this.currMove.unexecute();
/* 416 */       prev = this.currMove;
/* 417 */       this.currMove = this.currMove.prev;
/* 418 */       this.currMoveNumber -= 1;
/*     */     }
/* 420 */     return prev;
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
/*     */   public Move goTo(Move m)
/*     */   {
/* 435 */     if (m == null) {
/* 436 */       rewind();
/* 437 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 444 */     LinkedList tracks = new LinkedList();
/*     */     
/* 446 */     Move walker = m;
/*     */     do
/*     */     {
/* 449 */       tracks.addFirst(walker);
/* 450 */     } while ((walker = walker.prev) != null);
/*     */     
/* 452 */     notifyBoardsOfTraversal(true);
/*     */     
/* 454 */     _rewind();
/*     */     
/* 456 */     ListIterator li = tracks.listIterator(0);
/* 457 */     while (li.hasNext()) {
/* 458 */       walker = (Move)li.next();
/*     */       try {
/* 460 */         walker.execute();
/* 461 */         this.currMoveNumber += 1;
/*     */       }
/*     */       catch (OutOfTurnException e) {
/* 464 */         if (!$assertionsDisabled) {
/* 465 */           throw new AssertionError("Previously verified move threw " + e + 
/* 466 */             " History is out of sequence for some reason.");
/*     */         }
/*     */       } catch (IllegalMoveException e) {
/* 469 */         if (!$assertionsDisabled) {
/* 470 */           throw new AssertionError("Previously verified move threw " + e);
/*     */         }
/*     */       }
/* 473 */       this.currMove = walker;
/*     */     }
/* 475 */     notifyBoardsOfTraversal(false);
/*     */     
/* 477 */     assert (getCurrentMove() == m) : 
/* 478 */       ("History couldn't walk back to " + m + " last move is " + getCurrentMove());
/*     */     
/* 480 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void _rewind()
/*     */   {
/* 488 */     while (this.currMove != null) {
/* 489 */       this.currMove.unexecute();
/* 490 */       this.currMove = this.currMove.prev;
/*     */     }
/* 492 */     this.currMoveNumber = this.initialMoveNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rewind()
/*     */   {
/* 499 */     notifyBoardsOfTraversal(true);
/*     */     
/* 501 */     _rewind();
/*     */     
/* 503 */     notifyBoardsOfTraversal(false);
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
/*     */   public int fastforward(int n)
/*     */   {
/* 516 */     notifyBoardsOfTraversal(true);
/*     */     
/* 518 */     int count = 0;
/* 519 */     while ((hasNext()) && (count < n)) {
/* 520 */       next();
/* 521 */       count++;
/*     */     }
/*     */     
/* 524 */     notifyBoardsOfTraversal(false);
/*     */     
/* 526 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void goToEnd()
/*     */   {
/* 534 */     notifyBoardsOfTraversal(true);
/*     */     
/* 536 */     while (hasNext()) {
/* 537 */       next();
/*     */     }
/*     */     
/* 540 */     notifyBoardsOfTraversal(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move rewindToLastFork()
/*     */   {
/* 550 */     if (this.currMove == null) { return this.currMove;
/*     */     }
/* 552 */     notifyBoardsOfTraversal(true);
/*     */     do
/*     */     {
/* 555 */       this.currMove.unexecute();
/* 556 */       this.currMoveNumber -= 1;
/* 557 */       this.currMove = this.currMove.prev;
/* 558 */     } while ((this.currMove != null) && (!
/* 559 */       this.currMove.getContinuationList().hasVariations()));
/*     */     
/* 561 */     notifyBoardsOfTraversal(false);
/*     */     
/* 563 */     return this.currMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 572 */     boolean hasNext = true;
/* 573 */     if (this.currMove == null) {
/* 574 */       hasNext = this.head.hasMainLine();
/*     */     } else {
/* 576 */       hasNext = this.currMove.getContinuationList().hasMainLine();
/*     */     }
/* 578 */     return hasNext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 585 */     return this.head.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move getCurrentMove()
/*     */   {
/* 594 */     return this.currMove;
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
/*     */   public Move getFinalMove(boolean trueMainLine)
/*     */   {
/* 612 */     Move walker = null;
/* 613 */     if (trueMainLine) {
/* 614 */       walker = this.head.get(0);
/*     */     } else {
/* 616 */       walker = this.currMove;
/*     */     }
/* 618 */     while ((walker != null) && (!walker.getContinuationList().isTerminal())) {
/* 619 */       walker = walker.getContinuationList().getMainLine();
/*     */     }
/* 621 */     return walker;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move removeLastMove()
/*     */   {
/* 633 */     Move gonner = null;
/*     */     
/* 635 */     gonner = prev();
/*     */     
/* 637 */     if (this.currMove != null) {
/* 638 */       this.currMove.getContinuationList().remove(gonner);
/*     */     } else {
/* 640 */       this.head.remove(gonner);
/*     */     }
/* 642 */     return gonner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move truncate(int i)
/*     */   {
/* 654 */     if (i == -1) {
/* 655 */       if (this.currMove != null) {
/* 656 */         this.currMove.getContinuationList().removeAll();
/*     */       } else {
/* 658 */         this.head.removeAll();
/*     */       }
/* 660 */     } else if (this.currMove != null) {
/* 661 */       this.currMove.getContinuationList().remove(i);
/*     */     } else {
/* 663 */       this.head.remove(i);
/*     */     }
/* 665 */     return this.currMove;
/*     */   }
/*     */   
/*     */   public Move truncate()
/*     */   {
/* 670 */     return truncate(-1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void _delBadAdd(Move gonner)
/*     */   {
/* 678 */     if (this.currMove != null) {
/* 679 */       this.currMove.getContinuationList().remove(gonner);
/*     */     } else {
/* 681 */       this.head.remove(gonner);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 688 */     Move walker = null;
/* 689 */     int i = 0;
/*     */     
/* 691 */     walker = this.head.getMainLine();
/*     */     
/* 693 */     while (walker != null) {
/* 694 */       i++;
/* 695 */       walker = walker.getContinuationList().getMainLine();
/*     */     }
/*     */     
/* 698 */     return i;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 703 */     Move walker = null;
/* 704 */     String str = new String("");
/* 705 */     String tmp = null;
/*     */     
/* 707 */     walker = this.head.getMainLine();
/*     */     
/* 709 */     int i = 1;
/* 710 */     while (walker != null) {
/* 711 */       if (i % 2 == 1) {
/* 712 */         tmp = walker.toString();
/* 713 */         for (int j = tmp.length(); j < 9; j++)
/* 714 */           tmp = tmp + " ";
/* 715 */         str = str + (i / 2 + 1) + (i < 19 ? ".  " : ". ");
/* 716 */         str = str + tmp;
/*     */       }
/*     */       else {
/* 719 */         str = str + " " + walker + "\n"; }
/* 720 */       i++;
/* 721 */       if (walker.getContinuationList().hasMainLine()) {
/* 722 */         walker = walker.getContinuationList().getMainLine();
/*     */       } else {
/* 724 */         walker = null;
/*     */       }
/*     */     }
/* 727 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(History hist)
/*     */   {
/* 735 */     Move walker = this.head.getMainLine();
/* 736 */     Move walker2 = hist.head.getMainLine();
/*     */     
/* 738 */     while ((walker != null) && (walker2 != null) && (walker.equals(walker2))) {
/* 739 */       walker = walker.getContinuationList().getMainLine();
/* 740 */       walker2 = walker2.getContinuationList().getMainLine();
/*     */     }
/* 742 */     if ((walker == walker2) && (walker == null)) {
/* 743 */       return true;
/*     */     }
/* 745 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equalsPartly(History hist)
/*     */   {
/* 750 */     Move walker = this.head.getMainLine();
/* 751 */     Move walker2 = hist.head.getMainLine();
/*     */     
/*     */ 
/* 754 */     Move previous = null;
/* 755 */     Move previous2 = null;
/* 756 */     while ((walker != null) && (walker2 != null) && (walker.equals(walker2))) {
/* 757 */       previous = walker;
/* 758 */       previous2 = walker2;
/* 759 */       walker = walker.getContinuationList().getMainLine();
/* 760 */       walker2 = walker2.getContinuationList().getMainLine();
/*     */     }
/*     */     
/* 763 */     if ((previous == null) && (previous2 == null)) {
/* 764 */       return true;
/*     */     }
/* 766 */     if ((previous == null) && (previous2 != null)) {
/* 767 */       return false;
/*     */     }
/* 769 */     if ((previous2 == null) && (previous != null)) {
/* 770 */       return false;
/*     */     }
/* 772 */     if (previous.toString().equals(previous2.toString()))
/*     */     {
/* 774 */       return true;
/*     */     }
/* 776 */     return false;
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
/*     */   public boolean deepEquals(History hist, boolean checkAnno)
/*     */   {
/* 796 */     boolean t = false;
/*     */     
/*     */ 
/* 799 */     Log.debug(DEBUG, "beginning probe:" + (
/* 800 */       checkAnno ? "" : " not") + " checking Annotations");
/*     */     
/* 802 */     t = probeDeepEquals(this.head, hist.head, checkAnno);
/*     */     
/*     */ 
/* 805 */     Log.debug(DEBUG, "histories: " + (
/* 806 */       t ? "same" : "different"));
/*     */     
/* 808 */     return t;
/*     */   }
/*     */   
/*     */   public Game getGame() {
/* 812 */     return this.game;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean probeDeepEquals(ContinuationList cont, ContinuationList cont2, boolean checkAnno)
/*     */   {
/* 821 */     Move move1 = null;
/* 822 */     Move move2 = null;
/* 823 */     Move[] possibleMatches = (Move[])null;
/* 824 */     boolean t = true;
/* 825 */     boolean found = false;
/* 826 */     boolean isMatch = true;
/* 827 */     int j = 0;
/*     */     
/*     */ 
/* 830 */     if ((cont.isTerminal()) && (cont2.isTerminal()))
/*     */     {
/* 832 */       Log.debug(DEBUG, "+ both histories terminate");
/* 833 */       return t;
/*     */     }
/*     */     
/*     */ 
/* 837 */     for (int i = 0; (t) && (i < cont.size()); i++) {
/* 838 */       move1 = cont.get(i);
/*     */       
/*     */ 
/* 841 */       if ((i == 0) && (move1 == null) && (cont2.get(0) == null)) {
/* 842 */         Log.debug(DEBUG, "+ both mainlines are null");
/* 843 */         t = true;
/*     */ 
/*     */ 
/*     */       }
/* 847 */       else if ((possibleMatches = cont2.find(move1)) == null)
/*     */       {
/* 849 */         Log.debug(DEBUG, "- move (" + move1 + 
/* 850 */           ") not found in continuation list of game 2");
/* 851 */         t = false;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 857 */         found = false;
/*     */         
/*     */ 
/* 860 */         Log.debug(DEBUG, 
/* 861 */           "+ (" + possibleMatches.length + 
/* 862 */           ") possible matches of (" + move1 + 
/* 863 */           ")");
/*     */         
/* 865 */         for (j = 0; (j < possibleMatches.length) && (!found); j++) {
/* 866 */           move2 = possibleMatches[j];
/*     */           
/* 868 */           isMatch = move2 != null;
/*     */           
/* 870 */           if (!isMatch) {
/* 871 */             Log.error(2, 
/* 872 */               "- hmm, possible match was null?");
/*     */           }
/*     */           
/* 875 */           if ((isMatch) && (checkAnno)) {
/* 876 */             if (move1.getAnnotation() != 
/* 877 */               move2.getAnnotation()) {}
/* 876 */             isMatch = 
/*     */             
/* 878 */               (move1.getAnnotation() != null) && 
/* 879 */               (move2.getAnnotation() != null) && 
/*     */               
/* 881 */               (move1.getAnnotation().equals(move2.getAnnotation()));
/*     */             
/*     */ 
/*     */ 
/* 885 */             Log.debug(DEBUG, 
/* 886 */               (isMatch ? "+" : "-") + 
/* 887 */               " [" + (j + 1) + "/" + possibleMatches.length + 
/* 888 */               "] (" + move1 + ") annotation: " + (
/* 889 */               isMatch ? "same" : "different"));
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 894 */           if ((isMatch) && (checkAnno)) {
/* 895 */             if (move1.getPrenotation() != 
/* 896 */               move2.getPrenotation()) {}
/* 895 */             isMatch = 
/*     */             
/* 897 */               (move1.getPrenotation() != null) && 
/* 898 */               (move2.getPrenotation() != null) && 
/*     */               
/* 900 */               (move1.getPrenotation().equals(move2.getPrenotation()));
/*     */             
/*     */ 
/*     */ 
/* 904 */             Log.debug(DEBUG, 
/* 905 */               (isMatch ? "+" : "-") + 
/* 906 */               " [" + (j + 1) + "/" + possibleMatches.length + 
/* 907 */               "] (" + move1 + ") prenotation: " + (
/* 908 */               isMatch ? "same" : "different"));
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 915 */           if ((isMatch) && (checkAnno)) {
/* 916 */             isMatch = ((move1.getResult() == null) || 
/* 917 */               (move1.getResult().isUndecided())) && (
/* 918 */               (move2.getResult() == null) || 
/* 919 */               (move2.getResult().isUndecided()) || (
/* 920 */               (move1.getResult() != null) && 
/* 921 */               (move2.getResult() != null) && 
/*     */               
/* 923 */               (move1.getResult().equals(move2.getResult()))));
/*     */             
/*     */ 
/*     */ 
/* 927 */             if ((move1.getResult() != null) || 
/* 928 */               (move2.getResult() != null)) {
/* 929 */               Log.debug(DEBUG, 
/* 930 */                 (isMatch ? "+" : "-") + 
/* 931 */                 " [" + (j + 1) + "/" + possibleMatches.length + 
/* 932 */                 "] (" + move1 + ") result: " + (
/* 933 */                 isMatch ? "same" : "different") + (
/* 934 */                 (move1.getResult() == null) || 
/* 935 */                 (move2.getResult() == null) ? 
/* 936 */                 " {" + (move1.getResult() == null ? 
/* 937 */                 "null/undecided" : 
/* 938 */                 "undecided/null") + 
/*     */                 
/* 940 */                 "}" : 
/* 941 */                 ""));
/*     */             }
/*     */           }
/*     */           
/* 945 */           isMatch = (isMatch) && 
/* 946 */             (probeDeepEquals(move1.getContinuationList(), 
/* 947 */             move2.getContinuationList(), 
/* 948 */             checkAnno));
/* 949 */           found = isMatch;
/*     */         }
/*     */         
/*     */ 
/* 953 */         Log.debug(DEBUG, "(" + 
/* 954 */           move1 + ") continuation: " + (
/* 955 */           found ? "[" + j + 
/* 956 */           "/" + possibleMatches.length + 
/* 957 */           "] matched" : 
/* 958 */           "no match found"));
/* 959 */         t = found;
/*     */       }
/*     */     }
/* 962 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 971 */     return 0;
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
/*     */   protected void notifyBoardsOfTraversal(boolean begin)
/*     */   {
/* 986 */     Board[] boards = (Board[])null;
/* 987 */     int event = begin ? 3 : 
/* 988 */       4;
/*     */     
/* 990 */     boards = this.game.getBoards();
/*     */     
/* 992 */     if (boards != null) {
/* 993 */       for (int i = 0; i < boards.length; i++) {
/* 994 */         boards[i].fireBoardEvent(event);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\History.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */