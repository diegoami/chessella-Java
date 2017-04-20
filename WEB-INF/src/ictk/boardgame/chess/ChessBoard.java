/*      */ package ictk.boardgame.chess;
/*      */ 
/*      */ import ictk.boardgame.Board;
/*      */ import ictk.boardgame.BoardListener;
/*      */ import ictk.boardgame.IllegalMoveException;
/*      */ import ictk.boardgame.Move;
/*      */ import ictk.boardgame.OutOfTurnException;
/*      */ import ictk.boardgame.chess.io.FEN;
/*      */ import ictk.boardgame.chess.io.SAN;
/*      */ import ictk.util.Log;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ChessBoard
/*      */   implements Board
/*      */ {
/*   48 */   public static final long DEBUG = Log.Board;
/*      */   
/*      */ 
/*      */   public static final byte NULL_FILE = 0;
/*      */   
/*      */ 
/*      */   public static final byte NULL_RANK = 0;
/*      */   
/*      */   public static final byte NO_ENPASSANT = 0;
/*      */   
/*   58 */   public static final char[][] DEFAULT_POSITION = { { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' }, 
/*   59 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*   60 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*   61 */     { 'Q', 'P', ' ', ' ', ' ', ' ', 'p', 'q' }, 
/*   62 */     { 'K', 'P', ' ', ' ', ' ', ' ', 'p', 'k' }, 
/*   63 */     { 'B', 'P', ' ', ' ', ' ', ' ', 'p', 'b' }, 
/*   64 */     { 'N', 'P', ' ', ' ', ' ', ' ', 'p', 'n' }, 
/*   65 */     { 'R', 'P', ' ', ' ', ' ', ' ', 'p', 'r' } };
/*      */   
/*      */   public static final byte MAX_FILE = 8;
/*      */   
/*      */   public static final byte MAX_RANK = 8;
/*      */   
/*   71 */   protected static final SAN san = new SAN();
/*      */   
/*   73 */   protected static final FEN fen = new FEN();
/*      */   
/*      */ 
/*      */   protected BoardListener[] listeners;
/*      */   
/*      */ 
/*      */   protected Square[][] squares;
/*      */   
/*      */ 
/*      */   protected List whiteTeam;
/*      */   
/*      */   protected List blackTeam;
/*      */   
/*      */   protected King whiteKing;
/*      */   
/*      */   protected King blackKing;
/*      */   
/*   90 */   protected boolean isBlackMove = false;
/*      */   
/*   92 */   protected ChessMove lastMove = null;
/*      */   
/*   94 */   protected byte enpassantFile = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   99 */   protected boolean isInitialPositionDefault = true;
/*      */   
/*      */ 
/*  102 */   protected int plyCount50 = 0;
/*      */   
/*      */ 
/*  105 */   protected int moveNumber = 0;
/*      */   
/*      */ 
/*      */ 
/*  109 */   protected boolean staleLegalDests = true;
/*      */   
/*      */ 
/*      */   public ChessBoard()
/*      */   {
/*  114 */     this(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessBoard(boolean defaultBoard)
/*      */   {
/*  122 */     this.squares = new Square[8][8];
/*      */     
/*  124 */     this.whiteTeam = new ArrayList(16);
/*  125 */     this.blackTeam = new ArrayList(16);
/*      */     
/*  127 */     byte f = 0; for (byte r = 0; f < 8; f = (byte)(f + 1))
/*  128 */       for (r = 0; r < 8; r = (byte)(r + 1))
/*  129 */         this.squares[f][r] = new Square((byte)(f + 1), (byte)(r + 1));
/*  130 */     if (defaultBoard) {
/*  131 */       setPositionDefault();
/*      */     } else {
/*  133 */       setPositionClear();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessBoard(char[][] matrix)
/*      */   {
/*  142 */     this();
/*  143 */     setPosition(matrix);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessBoard(char[][] matrix, boolean isBlackMove, boolean castleWK, boolean castleWQ, boolean castleBK, boolean castleBQ, char enpassantFile, int plyCount, int moveNum)
/*      */   {
/*  169 */     this();
/*  170 */     setPosition(matrix);
/*      */     
/*  172 */     setWhiteCastleableKingside(castleWK);
/*  173 */     setWhiteCastleableQueenside(castleWQ);
/*  174 */     setBlackCastleableKingside(castleBK);
/*  175 */     setBlackCastleableQueenside(castleBQ);
/*  176 */     setEnPassantFile(enpassantFile);
/*  177 */     this.plyCount50 = plyCount;
/*  178 */     this.moveNumber = moveNum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRankValid(int r)
/*      */   {
/*  186 */     return (r >= 1) && (r <= 8);
/*      */   }
/*      */   
/*      */   public boolean isFileValid(int f)
/*      */   {
/*  191 */     return (f >= 1) && (f <= 8);
/*      */   }
/*      */   
/*      */   public int getMaxRank()
/*      */   {
/*  196 */     return 8;
/*      */   }
/*      */   
/*      */   public int getMaxFile()
/*      */   {
/*  201 */     return 8;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void playMove(Move move)
/*      */     throws IllegalMoveException, OutOfTurnException
/*      */   {
/*  213 */     ChessMove m = (ChessMove)move;
/*  214 */     m.execute();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getPlayerToMove()
/*      */   {
/*  221 */     return this.isBlackMove ? 1 : 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void genLegalDests()
/*      */   {
/*  238 */     ChessPiece[] threats = (ChessPiece[])null;
/*  239 */     King movingKing = null;
/*  240 */     King otherKing = null;
/*  241 */     List movingTeam = null;
/*  242 */     List otherTeam = null;
/*      */     
/*      */ 
/*  245 */     Log.debug(DEBUG, "generating legal moves");
/*      */     
/*  247 */     this.staleLegalDests = false;
/*      */     
/*      */ 
/*  250 */     byte f = 0; for (byte r = 0; f < 8; f = (byte)(f + 1)) {
/*  251 */       for (r = 0; r < 8; r = (byte)(r + 1)) {
/*  252 */         if (this.squares[f][r].piece != null) {
/*  253 */           this.squares[f][r].piece.genLegalDests();
/*      */         }
/*      */       }
/*      */     }
/*  257 */     movingKing = this.isBlackMove ? this.blackKing : this.whiteKing;
/*  258 */     movingTeam = this.isBlackMove ? this.blackTeam : this.whiteTeam;
/*  259 */     otherKing = this.isBlackMove ? this.whiteKing : this.blackKing;
/*  260 */     otherTeam = this.isBlackMove ? this.whiteTeam : this.blackTeam;
/*      */     
/*  262 */     movingKing.genLegalDestsFinal();
/*  263 */     otherKing.genLegalDestsFinal();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  268 */     for (byte i = 0; i < otherTeam.size(); i = (byte)(i + 1))
/*      */     {
/*  270 */       ((ChessPiece)otherTeam.get(i)).adjustPinsLegalDests(movingKing, movingTeam);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  275 */     if (movingKing.isInCheck()) {
/*  276 */       if (this.lastMove != null)
/*  277 */         this.lastMove.setCheck(true);
/*  278 */       threats = getThreats(movingKing);
/*      */       
/*      */ 
/*  281 */       Log.debug(DEBUG, "THREATS TO MOVING KING! (" + 
/*  282 */         threats.length + ")");
/*  283 */       Log.debug2(DEBUG, "Threat: " + threats[0]);
/*      */       
/*      */ 
/*  286 */       switch (threats.length)
/*      */       {
/*      */       case 1: 
/*  289 */         for (byte i = 0; i < movingTeam.size(); i = (byte)(i + 1))
/*      */         {
/*  291 */           ((ChessPiece)movingTeam.get(i)).genLegalDestsSaveKing(movingKing, threats[0]);
/*      */         }
/*  293 */         break;
/*      */       
/*      */ 
/*      */       case 2: 
/*  297 */         for (byte i = 0; i < movingTeam.size(); i = (byte)(i + 1)) {
/*  298 */           ChessPiece p = (ChessPiece)movingTeam.get(i);
/*  299 */           if (p != movingKing)
/*  300 */             p.removeLegalDests();
/*      */         }
/*  302 */         if (this.lastMove != null)
/*  303 */           this.lastMove.setDoubleCheck(true);
/*  304 */         break;
/*      */       
/*      */ 
/*      */       default: 
/*  308 */         if (!$assertionsDisabled) {
/*  309 */           throw new AssertionError("King reports in check with " + threats.length + 
/*  310 */             " threats.");
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*  317 */     if (getLegalMoveCount() == 0) {
/*  318 */       if (movingKing.isInCheck()) {
/*  319 */         if (this.lastMove != null) {
/*  320 */           this.lastMove.setCheckmate(true);
/*      */         }
/*      */       }
/*  323 */       else if (this.lastMove != null) {
/*  324 */         this.lastMove.setStalemate(true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isLegalMove(Move m)
/*      */   {
/*  342 */     if (m == null) return false;
/*      */     try {
/*  344 */       ((ChessMove)m).execute();
/*  345 */       ((ChessMove)m).unexecute();
/*      */     }
/*      */     catch (Exception e) {
/*  348 */       return false;
/*      */     }
/*  350 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getThreats(Square sq, boolean isBlack)
/*      */   {
/*  367 */     Iterator team = null;
/*  368 */     List attackers = null;
/*  369 */     ChessPiece piece = null;
/*  370 */     ChessPiece[] threats = (ChessPiece[])null;
/*      */     
/*  372 */     if (sq == null) {
/*  373 */       throw new NullPointerException(
/*  374 */         "cannot assess threats to null square");
/*      */     }
/*  376 */     if (this.staleLegalDests) {
/*  377 */       genLegalDests();
/*      */     }
/*  379 */     attackers = new LinkedList();
/*  380 */     team = isBlack ? this.blackTeam.iterator() : this.whiteTeam.iterator();
/*      */     
/*      */ 
/*  383 */     Log.debug(DEBUG, "Finding " + (
/*  384 */       isBlack ? "Black" : "White") + 
/*  385 */       " attackers on " + sq);
/*      */     
/*  387 */     while (team.hasNext()) {
/*  388 */       piece = (ChessPiece)team.next();
/*  389 */       if (piece.isLegalAttack(sq)) {
/*  390 */         attackers.add(piece);
/*      */         
/*  392 */         Log.debug2(DEBUG, 
/*  393 */           "attacker: " + piece + "(" + piece.getSquare() + ")");
/*      */       }
/*      */     }
/*      */     
/*  397 */     if (attackers.size() > 0) {
/*  398 */       threats = new ChessPiece[attackers.size()];
/*  399 */       threats = (ChessPiece[])attackers.toArray(threats);
/*  400 */       return threats;
/*      */     }
/*      */     
/*      */ 
/*  404 */     Log.debug(DEBUG, "no attackers found.");
/*      */     
/*      */ 
/*  407 */     return threats;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getThreats(ChessPiece piece)
/*      */   {
/*  416 */     if (piece == null) {
/*  417 */       throw new NullPointerException(
/*  418 */         "cannot assess threats to null piece");
/*      */     }
/*  420 */     return getThreats(piece.orig, !piece.isBlack);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isThreatened(Square sq, boolean isBlack)
/*      */   {
/*  430 */     return getThreats(sq, isBlack) != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isThreatened(ChessPiece piece)
/*      */   {
/*  439 */     if (piece == null) {
/*  440 */       throw new NullPointerException(
/*  441 */         "cannot assess threats to null piece");
/*      */     }
/*  443 */     return isThreatened(piece.orig, !piece.isBlack);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getGuards(Square sq, boolean isBlack)
/*      */   {
/*  462 */     Iterator team = null;
/*  463 */     List attackers = null;
/*  464 */     ChessPiece piece = null;
/*  465 */     ChessPiece[] guards = (ChessPiece[])null;
/*      */     
/*  467 */     if (sq == null) {
/*  468 */       throw new NullPointerException(
/*  469 */         "cannot assess guards of null square");
/*      */     }
/*  471 */     if (this.staleLegalDests) {
/*  472 */       genLegalDests();
/*      */     }
/*  474 */     attackers = new LinkedList();
/*  475 */     team = isBlack ? this.blackTeam.iterator() : this.whiteTeam.iterator();
/*      */     
/*  477 */     while (team.hasNext()) {
/*  478 */       piece = (ChessPiece)team.next();
/*  479 */       if (piece.isGuarding(sq)) {
/*  480 */         attackers.add(piece);
/*      */       }
/*      */     }
/*  483 */     if (attackers.size() > 0) {
/*  484 */       guards = new ChessPiece[attackers.size()];
/*  485 */       guards = (ChessPiece[])attackers.toArray(guards);
/*      */     }
/*  487 */     return guards;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getGuards(ChessPiece piece)
/*      */   {
/*  503 */     if (piece == null) {
/*  504 */       throw new NullPointerException(
/*  505 */         "cannot assess threats to null piece");
/*      */     }
/*  507 */     return getThreats(piece.orig, !piece.isBlack);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isGuarded(Square sq, boolean isBlack)
/*      */   {
/*  514 */     return getGuards(sq, isBlack) != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isGuarded(ChessPiece piece)
/*      */   {
/*  521 */     if (piece == null) {
/*  522 */       throw new NullPointerException(
/*  523 */         "cannot assess threats to null piece");
/*      */     }
/*  525 */     return isGuarded(piece.orig, !piece.isBlack);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getLegalMoveCount()
/*      */   {
/*  535 */     int count = 0;
/*  536 */     List movingTeam = this.isBlackMove ? this.blackTeam : this.whiteTeam;
/*      */     
/*  538 */     if (this.staleLegalDests) {
/*  539 */       genLegalDests();
/*      */     }
/*  541 */     for (int i = 0; i < movingTeam.size(); i++) {
/*  542 */       count += ((ChessPiece)movingTeam.get(i)).getLegalDests().size();
/*      */     }
/*  544 */     return count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public List getLegalMoves()
/*      */   {
/*  551 */     List list = new LinkedList();
/*  552 */     List movingTeam = this.isBlackMove ? this.blackTeam : this.whiteTeam;
/*  553 */     List dests = null;
/*  554 */     ChessPiece piece = null;
/*  555 */     Square orig = null;
/*  556 */     Square dest = null;
/*      */     
/*  558 */     if (this.staleLegalDests) {
/*  559 */       genLegalDests();
/*      */     }
/*  561 */     for (int i = 0; i < movingTeam.size(); i++) {
/*  562 */       piece = (ChessPiece)movingTeam.get(i);
/*  563 */       dests = piece.getLegalDests();
/*  564 */       orig = piece.orig;
/*  565 */       for (int j = 0; j < dests.size(); j++) {
/*  566 */         dest = (Square)dests.get(j);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  571 */         list.add(new ChessMove(orig, dest, dest.piece, this));
/*      */       }
/*      */     }
/*      */     
/*  575 */     return list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean[] isDestUniqueForClass(Square dest, ChessPiece p)
/*      */   {
/*  587 */     boolean[] unique = { true, true };
/*  588 */     List movingTeam = this.isBlackMove ? this.blackTeam : this.whiteTeam;
/*  589 */     List dests = null;
/*  590 */     ChessPiece piece = null;
/*      */     
/*  592 */     if (p.isKing()) { return unique;
/*      */     }
/*  594 */     for (int i = 0; i < movingTeam.size(); i++) {
/*  595 */       piece = (ChessPiece)movingTeam.get(i);
/*      */       
/*  597 */       if ((piece != p) && 
/*  598 */         (!piece.isCaptured()) && 
/*  599 */         (piece.getIndex() == p.getIndex())) {
/*  600 */         dests = piece.getLegalDests();
/*      */         
/*  602 */         if (dests.contains(dest)) {
/*  603 */           if (p.orig.file == piece.orig.file)
/*  604 */             unique[0] = false;
/*  605 */           if (p.orig.rank == piece.orig.rank) {
/*  606 */             unique[1] = false;
/*      */           }
/*      */           
/*      */ 
/*  610 */           if ((unique[0] != 0) && (unique[1] != 0)) {
/*  611 */             unique[1] = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  616 */     return unique;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Square getOrigin(byte piece_index, Square dest)
/*      */     throws AmbiguousChessMoveException, IllegalMoveException
/*      */   {
/*  626 */     return getOrigin(piece_index, -1, -1, dest);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Square getOrigin(byte piece_index, int file, int rank, Square dest)
/*      */     throws AmbiguousChessMoveException, IllegalMoveException
/*      */   {
/*  637 */     if ((file > 8) || (rank > 8)) {
/*  638 */       throw new IllegalArgumentException("origin or rank too big");
/*      */     }
/*  640 */     byte orig_f = (byte)file;
/*  641 */     byte orig_r = (byte)rank;
/*      */     
/*  643 */     List movingTeam = this.isBlackMove ? this.blackTeam : this.whiteTeam;
/*  644 */     List dests = null;
/*  645 */     List dupes = new ArrayList(1);
/*  646 */     ChessPiece piece = null;
/*  647 */     ChessPiece mover = null;
/*  648 */     boolean found = false;
/*  649 */     int count = 0;
/*      */     
/*  651 */     for (int i = 0; i < movingTeam.size(); i++) {
/*  652 */       piece = (ChessPiece)movingTeam.get(i);
/*      */       
/*  654 */       if ((piece.getIndex() % ChessPiece.BLACK_OFFSET == piece_index) && 
/*  655 */         (piece.isLegalDest(dest)))
/*      */       {
/*  657 */         if (((orig_f < 1) && (orig_r < 1)) || 
/*  658 */           ((orig_r < 1) && (piece.orig.file == orig_f)) || (
/*  659 */           (orig_f < 1) && (piece.orig.rank == orig_r))) {
/*  660 */           found = true;
/*  661 */           count++; if (count > 1) {
/*  662 */             if (dupes == null) {
/*  663 */               dupes = new ArrayList(2);
/*  664 */               dupes.add(mover);
/*      */             }
/*  666 */             dupes.add(piece);
/*      */           }
/*  668 */           mover = piece;
/*      */         }
/*      */       }
/*      */     }
/*  672 */     if (!found)
/*      */     {
/*  674 */       Log.debug(DEBUG, 
/*  675 */         "Illegal Move piece: " + 
/*  676 */         piece_index + " file: " + orig_f + 
/*  677 */         " rank: " + orig_r + " dest: " + dest);
/*  678 */       Log.debug2(DEBUG, this);
/*  679 */       Log.debug2(DEBUG, dumpLegalMoves());
/*  680 */       Log.debug2(DEBUG, dumpLegalMoves(!this.isBlackMove));
/*      */       
/*  682 */       throw new IllegalMoveException("Illegal Move");
/*      */     }
/*      */     
/*  685 */     if ((found) && (count > 1))
/*      */     {
/*  687 */       Log.debug(DEBUG, 
/*  688 */         "AMBIGUOUSMOVE!!!! to " + dest);
/*  689 */       Log.debug2(DEBUG, this);
/*  690 */       Log.debug2(DEBUG, dumpLegalMoves());
/*  691 */       Log.debug2(DEBUG, dumpLegalMoves(!this.isBlackMove));
/*      */       
/*  693 */       throw new AmbiguousChessMoveException("Ambiguous Move", 
/*  694 */         piece_index, 
/*  695 */         orig_f, 
/*  696 */         orig_r, 
/*  697 */         dest.file, 
/*  698 */         dest.rank, 
/*  699 */         dupes);
/*      */     }
/*      */     
/*  702 */     return mover.orig;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void promote(ChessPiece pawn, ChessPiece promo)
/*      */   {
/*  712 */     promo.orig = pawn.orig;
/*  713 */     promo.orig.piece = promo;
/*  714 */     promo.board = this;
/*  715 */     promo.isBlack = pawn.isBlack;
/*      */     
/*  717 */     if (pawn.isBlack) {
/*  718 */       this.blackTeam.set(this.blackTeam.indexOf(pawn), promo);
/*      */     }
/*      */     else {
/*  721 */       this.whiteTeam.set(this.whiteTeam.indexOf(pawn), promo);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isCheckmate()
/*      */   {
/*  731 */     if (this.lastMove != null) {
/*  732 */       return this.lastMove.isCheckmate();
/*      */     }
/*  734 */     if (this.staleLegalDests) {
/*  735 */       genLegalDests();
/*      */     }
/*  737 */     if ((getLegalMoveCount() == 0) && (isCheck())) {
/*  738 */       return true;
/*      */     }
/*  740 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isCheck()
/*      */   {
/*  748 */     boolean check = false;
/*      */     
/*  750 */     if (this.lastMove != null) {
/*  751 */       return this.lastMove.isCheck();
/*      */     }
/*  753 */     if (this.staleLegalDests) {
/*  754 */       genLegalDests();
/*      */     }
/*      */     
/*  757 */     check = this.isBlackMove ? this.blackKing.isInCheck() : this.whiteKing.isInCheck();
/*      */     
/*      */ 
/*  760 */     Log.debug(DEBUG, "the King in check: " + check);
/*      */     
/*  762 */     return check;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDoubleCheck()
/*      */   {
/*  770 */     boolean dcheck = false;
/*      */     
/*  772 */     if (this.lastMove != null) {
/*  773 */       return this.lastMove.isDoubleCheck();
/*      */     }
/*  775 */     if (this.staleLegalDests) {
/*  776 */       genLegalDests();
/*      */     }
/*  778 */     dcheck = getThreats(this.isBlackMove ? this.blackKing : this.whiteKing).length == 2;
/*      */     
/*  780 */     return dcheck;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isStalemate()
/*      */   {
/*  787 */     if (this.lastMove != null) {
/*  788 */       return this.lastMove.isStalemate();
/*      */     }
/*  790 */     if (this.staleLegalDests) {
/*  791 */       genLegalDests();
/*      */     }
/*  793 */     if ((getLegalMoveCount() == 0) && (!isCheck())) {
/*  794 */       return true;
/*      */     }
/*  796 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char[][] toCharArray()
/*      */   {
/*  807 */     char[][] board = new char[9][9];
/*  808 */     for (byte r = 0; r < 8; r = (byte)(r + 1))
/*  809 */       for (byte f = 0; f < 8; f = (byte)(f + 1))
/*  810 */         if (this.squares[f][r].isOccupied()) {
/*  811 */           switch (this.squares[f][r].piece.getIndex() % 
/*  812 */             ChessPiece.BLACK_OFFSET) {
/*  813 */           case 5:  board[f][r] = 80; break;
/*  814 */           case 4:  board[f][r] = 78; break;
/*  815 */           case 3:  board[f][r] = 66; break;
/*  816 */           case 2:  board[f][r] = 82; break;
/*  817 */           case 1:  board[f][r] = 81; break;
/*  818 */           case 0:  board[f][r] = 75;
/*      */           }
/*      */           
/*  821 */           if (this.squares[f][r].piece.getIndex() >= ChessPiece.BLACK_OFFSET)
/*  822 */             board[f][r] = Character.toLowerCase(board[f][r]);
/*      */         }
/*  824 */     return board;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getCapturedPieces(boolean isBlack)
/*      */   {
/*  832 */     return getCaptures(isBlack, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece[] getUnCapturedPieces(boolean isBlack)
/*      */   {
/*  840 */     return getCaptures(isBlack, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected ChessPiece[] getCaptures(boolean isBlack, boolean isCaptured)
/*      */   {
/*  849 */     ChessPiece[] pows = (ChessPiece[])null;
/*  850 */     List team = isBlack ? this.blackTeam : this.whiteTeam;
/*  851 */     int count = 0;
/*  852 */     ChessPiece piece = null;
/*      */     
/*  854 */     for (int i = 0; i < team.size(); i++) {
/*  855 */       if (((ChessPiece)team.get(i)).isCaptured() == isCaptured) {
/*  856 */         count++;
/*      */       }
/*      */     }
/*  859 */     if (count > 0) {
/*  860 */       pows = new ChessPiece[count];
/*  861 */       count = 0;
/*      */       
/*  863 */       for (int i = 0; i < team.size(); i++) {
/*  864 */         piece = (ChessPiece)team.get(i);
/*  865 */         if (piece.isCaptured() == isCaptured) {
/*  866 */           pows[(count++)] = piece;
/*      */         }
/*      */       }
/*      */     }
/*  870 */     return pows;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaterialCount(boolean isBlack)
/*      */   {
/*  886 */     int material = 0;
/*  887 */     List team = isBlack ? this.blackTeam : this.whiteTeam;
/*  888 */     ChessPiece piece = null;
/*      */     
/*  890 */     for (int i = 0; i < team.size(); i++) {
/*  891 */       piece = (ChessPiece)team.get(i);
/*  892 */       if (!piece.isCaptured()) {
/*  893 */         switch (piece.getIndex() % ChessPiece.BLACK_OFFSET) {
/*  894 */         case 5:  material++; break;
/*      */         case 3: case 4: 
/*  896 */           material += 3; break;
/*  897 */         case 2:  material += 5; break;
/*  898 */         case 1:  material += 9;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  903 */     return material;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlackMove(boolean t)
/*      */   {
/*  913 */     if (this.lastMove != null)
/*  914 */       throw new IllegalStateException(
/*  915 */         "can't set the move color for a game in progress.");
/*  916 */     this.isBlackMove = t;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBlackMove()
/*      */   {
/*  923 */     return this.isBlackMove;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Square getSquare(char file, char rank)
/*      */   {
/*  938 */     Square sq = this.squares[(san.fileToNum(file) - 1)][(san.rankToNum(rank) - 1)];
/*  939 */     return sq;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Square getSquare(int x, int y)
/*      */   {
/*  946 */     return this.squares[(x - 1)][(y - 1)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  955 */     SAN san = new SAN();
/*  956 */     StringBuffer s_buffer = new StringBuffer();
/*  957 */     StringBuffer last_line = new StringBuffer();
/*  958 */     char c = ' ';
/*      */     
/*  960 */     last_line.append("\n    ");
/*  961 */     byte r = 7; for (byte f = 0; r >= 0; f = 0) {
/*  962 */       s_buffer.append(san.rankToChar(this.squares[f][r].rank) + "   ");
/*      */       
/*      */ 
/*  965 */       for (f = 0; f < 8; f = (byte)(f + 1)) {
/*  966 */         if (this.squares[f][r].isOccupied()) {
/*  967 */           c = san.pieceToChar(this.squares[f][r].piece);
/*  968 */           if (this.squares[f][r].piece.isBlack())
/*  969 */             c = Character.toLowerCase(c);
/*  970 */           s_buffer.append(c + " ");
/*      */ 
/*      */         }
/*  973 */         else if (this.squares[f][r].isBlack()) {
/*  974 */           s_buffer.append("  ");
/*      */         } else {
/*  976 */           s_buffer.append("# "); }
/*  977 */         if (r == 7)
/*  978 */           last_line.append(Character.toUpperCase(
/*  979 */             san.fileToChar(this.squares[f][r].file)) + " ");
/*      */       }
/*  981 */       s_buffer.append('\n');r = (byte)(r - 1);
/*      */     }
/*      */     
/*  984 */     s_buffer.append(last_line);
/*  985 */     return s_buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPositionDefault()
/*      */   {
/*  997 */     if (this.lastMove != null)
/*  998 */       throw new IllegalStateException(
/*  999 */         "can't set the board position for a game in progress.");
/* 1000 */     setPositionClear();
/* 1001 */     setPosition(DEFAULT_POSITION);
/* 1002 */     this.isInitialPositionDefault = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPositionClear()
/*      */   {
/* 1012 */     if (this.lastMove != null) {
/* 1013 */       throw new IllegalStateException(
/* 1014 */         "can't set the board position for a game in progress.");
/*      */     }
/* 1016 */     for (byte r = 0; r < 8; r = (byte)(r + 1))
/* 1017 */       for (byte f = 0; f < 8; f = (byte)(f + 1))
/* 1018 */         this.squares[f][r].piece = null;
/* 1019 */     this.blackTeam.clear();
/* 1020 */     this.whiteTeam.clear();
/* 1021 */     this.blackKing = null;
/* 1022 */     this.whiteKing = null;
/* 1023 */     this.isInitialPositionDefault = false;
/* 1024 */     this.plyCount50 = 0;
/* 1025 */     this.enpassantFile = 0;
/* 1026 */     this.moveNumber = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPosition(char[][] matrix)
/*      */   {
/* 1043 */     if (this.lastMove != null) {
/* 1044 */       throw new IllegalStateException(
/* 1045 */         "can't set the board position for a game in progress.");
/*      */     }
/* 1047 */     boolean wking = false;boolean bking = false;
/*      */     
/* 1049 */     if ((matrix.length != 8) || (matrix[0].length != 8)) {
/* 1050 */       throw new IllegalArgumentException(
/* 1051 */         "setPosition() takes a matrix the same dimensions as the board.");
/*      */     }
/*      */     
/* 1054 */     this.isInitialPositionDefault = false;
/*      */     
/*      */ 
/* 1057 */     setPositionClear();
/* 1058 */     for (byte file = 0; file < matrix.length; file = (byte)(file + 1)) {
/* 1059 */       for (byte rank = 0; rank < matrix[file].length; rank = (byte)(rank + 1))
/*      */       {
/* 1061 */         switch (matrix[file][rank]) {
/*      */         case 'p': 
/* 1063 */           addPawn(file + 1, rank + 1, true); break;
/* 1064 */         case 'P':  addPawn(file + 1, rank + 1, false); break;
/* 1065 */         case 'n':  addKnight(file + 1, rank + 1, true); break;
/* 1066 */         case 'N':  addKnight(file + 1, rank + 1, false); break;
/* 1067 */         case 'b':  addBishop(file + 1, rank + 1, true); break;
/* 1068 */         case 'B':  addBishop(file + 1, rank + 1, false); break;
/* 1069 */         case 'r':  addRook(file + 1, rank + 1, true); break;
/* 1070 */         case 'R':  addRook(file + 1, rank + 1, false); break;
/* 1071 */         case 'q':  addQueen(file + 1, rank + 1, true); break;
/* 1072 */         case 'Q':  addQueen(file + 1, rank + 1, false); break;
/* 1073 */         case 'k':  addKing(file + 1, rank + 1, true);
/* 1074 */           bking = true;
/* 1075 */           break;
/* 1076 */         case 'K':  addKing(file + 1, rank + 1, false);
/* 1077 */           wking = true;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1094 */     if (wking) {
/* 1095 */       if (matrix[4][0] == 'K') {
/* 1096 */         if (matrix[0][0] != 'R')
/*      */         {
/* 1098 */           Log.debug(DEBUG, "setting white q-side castle: false");
/* 1099 */           setWhiteCastleableQueenside(false);
/*      */         }
/* 1101 */         if (matrix[7][0] != 'R')
/*      */         {
/* 1103 */           Log.debug(DEBUG, "setting white k-side castle: false");
/* 1104 */           setWhiteCastleableKingside(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1109 */         Log.debug(DEBUG, "setting white castleable: false");
/* 1110 */         this.whiteKing.moveCount = 1;
/*      */       }
/*      */     }
/* 1113 */     if (bking) {
/* 1114 */       if (matrix[4][7] == 'k') {
/* 1115 */         if (matrix[0][7] != 'r')
/*      */         {
/* 1117 */           Log.debug(DEBUG, "setting black q-side castle: false");
/* 1118 */           setBlackCastleableQueenside(false);
/*      */         }
/* 1120 */         if (matrix[7][7] != 'r')
/*      */         {
/* 1122 */           Log.debug(DEBUG, "setting black k-side castle: false");
/* 1123 */           setBlackCastleableKingside(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1128 */         Log.debug(DEBUG, "setting black castleable: false");
/* 1129 */         this.blackKing.moveCount = 1;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addPawn(int file, int rank, boolean isBlack)
/*      */   {
/* 1145 */     Square orig = getSquare(file, rank);
/* 1146 */     ChessPiece p; orig.setOccupant(p = new Pawn(isBlack, orig, this));
/* 1147 */     if (isBlack) this.blackTeam.add(p); else {
/* 1148 */       this.whiteTeam.add(p);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addKnight(int file, int rank, boolean isBlack)
/*      */   {
/* 1163 */     Square orig = getSquare(file, rank);
/* 1164 */     ChessPiece p; orig.setOccupant(p = new Knight(isBlack, orig, this));
/* 1165 */     if (isBlack) this.blackTeam.add(p); else {
/* 1166 */       this.whiteTeam.add(p);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addBishop(int file, int rank, boolean isBlack)
/*      */   {
/* 1181 */     Square orig = getSquare(file, rank);
/* 1182 */     ChessPiece p; orig.setOccupant(p = new Bishop(isBlack, orig, this));
/* 1183 */     if (isBlack) this.blackTeam.add(p); else {
/* 1184 */       this.whiteTeam.add(p);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addRook(int file, int rank, boolean isBlack)
/*      */   {
/* 1199 */     Square orig = getSquare(file, rank);
/* 1200 */     ChessPiece p; orig.setOccupant(p = new Rook(isBlack, orig, this));
/* 1201 */     if (isBlack) this.blackTeam.add(p); else {
/* 1202 */       this.whiteTeam.add(p);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addQueen(int file, int rank, boolean isBlack)
/*      */   {
/* 1217 */     Square orig = getSquare(file, rank);
/* 1218 */     ChessPiece p; orig.setOccupant(p = new Queen(isBlack, orig, this));
/* 1219 */     if (isBlack) this.blackTeam.add(p); else {
/* 1220 */       this.whiteTeam.add(p);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addKing(int file, int rank, boolean isBlack)
/*      */   {
/* 1236 */     Square orig = getSquare(file, rank);
/* 1237 */     ChessPiece p; orig.setOccupant(p = new King(isBlack, orig, this));
/* 1238 */     if (isBlack) {
/* 1239 */       if (this.blackKing != null)
/* 1240 */         this.blackTeam.remove(this.blackKing);
/* 1241 */       this.blackTeam.add(p);
/* 1242 */       this.blackKing = ((King)p);
/*      */     }
/*      */     else {
/* 1245 */       if (this.whiteKing != null)
/* 1246 */         this.whiteTeam.remove(this.whiteKing);
/* 1247 */       this.whiteTeam.add(p);
/* 1248 */       this.whiteKing = ((King)p);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isWhiteCastleableKingside() {
/* 1253 */     return this.whiteKing.isCastleableKingside();
/*      */   }
/*      */   
/*      */   public boolean isWhiteCastleableQueenside() {
/* 1257 */     return this.whiteKing.isCastleableQueenside();
/*      */   }
/*      */   
/*      */   public boolean isBlackCastleableKingside() {
/* 1261 */     return this.blackKing.isCastleableKingside();
/*      */   }
/*      */   
/*      */   public boolean isBlackCastleableQueenside() {
/* 1265 */     return this.blackKing.isCastleableQueenside();
/*      */   }
/*      */   
/*      */   public void setWhiteCastleableKingside(boolean t) {
/* 1269 */     this.whiteKing.setCastleableKingside(t);
/*      */   }
/*      */   
/*      */   public void setWhiteCastleableQueenside(boolean t) {
/* 1273 */     this.whiteKing.setCastleableQueenside(t);
/*      */   }
/*      */   
/*      */   public void setBlackCastleableKingside(boolean t) {
/* 1277 */     this.blackKing.setCastleableKingside(t);
/*      */   }
/*      */   
/*      */   public void setBlackCastleableQueenside(boolean t) {
/* 1281 */     this.blackKing.setCastleableQueenside(t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getEnPassantFile()
/*      */   {
/* 1289 */     return this.enpassantFile;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEnPassantFile(int f)
/*      */   {
/* 1297 */     if (f > 8)
/* 1298 */       throw new IllegalArgumentException(
/* 1299 */         "EnPassant file cannot be larget than MAX_FILE");
/* 1300 */     this.enpassantFile = ((byte)f);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEnPassantFile(char f)
/*      */   {
/* 1308 */     this.enpassantFile = san.fileToNum(f);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isEnPassantFile(char f)
/*      */   {
/* 1315 */     if (this.enpassantFile == 0) return false;
/* 1316 */     return this.enpassantFile == san.fileToNum(f);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isEnPassantFile(int f)
/*      */   {
/* 1323 */     if (this.enpassantFile == 0) return false;
/* 1324 */     return this.enpassantFile == f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int get50MoveRulePlyCount()
/*      */   {
/* 1331 */     return this.plyCount50;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void set50MoveRulePlyCount(int i)
/*      */   {
/* 1338 */     this.plyCount50 = i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean is50MoveRuleApplicible()
/*      */   {
/* 1346 */     return this.plyCount50 > 99;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getCurrentMoveNumber()
/*      */   {
/* 1353 */     return this.moveNumber;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInitialPositionDefault()
/*      */   {
/* 1361 */     return this.isInitialPositionDefault;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addBoardListener(BoardListener bl)
/*      */   {
/* 1368 */     int size = 0;
/* 1369 */     boolean found = false;
/* 1370 */     BoardListener[] bls = (BoardListener[])null;
/*      */     
/* 1372 */     if (this.listeners != null) {
/* 1373 */       size = this.listeners.length;
/*      */       
/* 1375 */       for (int i = 0; (!found) && (i < size); i++) {
/* 1376 */         found = this.listeners[i] == bl;
/*      */       }
/* 1378 */       if (found) { return;
/*      */       }
/*      */     }
/* 1381 */     bls = new BoardListener[size + 1];
/* 1382 */     if (this.listeners != null)
/* 1383 */       System.arraycopy(this.listeners, 0, bls, 0, size);
/* 1384 */     bls[size] = bl;
/*      */     
/* 1386 */     this.listeners = bls;
/*      */   }
/*      */   
/*      */   public BoardListener[] getBoardListeners()
/*      */   {
/* 1391 */     return this.listeners;
/*      */   }
/*      */   
/*      */   public void removeBoardListener(BoardListener bl)
/*      */   {
/* 1396 */     int size = 0;
/* 1397 */     int idx = 0;
/* 1398 */     boolean found = false;
/* 1399 */     BoardListener[] bls = (BoardListener[])null;
/*      */     
/* 1401 */     if (this.listeners == null) {
/* 1402 */       return;
/*      */     }
/* 1404 */     size = this.listeners.length;
/*      */     
/* 1406 */     for (int i = 0; (!found) && (i < size); i++) {
/* 1407 */       found = this.listeners[i] == bl;
/* 1408 */       if (found) { idx = i;
/*      */       }
/*      */     }
/* 1411 */     if (!found) { return;
/*      */     }
/* 1413 */     this.listeners[idx] = null;
/*      */     
/* 1415 */     bls = new BoardListener[size - 1];
/*      */     
/* 1417 */     if (idx != 0) {
/* 1418 */       System.arraycopy(this.listeners, 0, bls, 0, idx);
/*      */     }
/* 1420 */     if (idx != size - 1) {
/* 1421 */       System.arraycopy(this.listeners, idx + 1, bls, idx, size - 1);
/*      */     }
/* 1423 */     this.listeners = bls;
/*      */   }
/*      */   
/*      */   public void fireBoardEvent(int event)
/*      */   {
/* 1428 */     if (this.listeners != null) {
/* 1429 */       for (int i = 0; i < this.listeners.length; i++) {
/* 1430 */         this.listeners[i].boardUpdate(this, event);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object o)
/*      */   {
/* 1452 */     if (o == this) return true;
/* 1453 */     if ((o == null) || (o.getClass() != getClass())) {
/* 1454 */       return false;
/*      */     }
/*      */     
/* 1457 */     boolean equal = true;
/* 1458 */     ChessBoard b = (ChessBoard)o;
/*      */     
/*      */ 
/* 1461 */     Log.debug(DEBUG, "comparing boards");
/*      */     
/*      */ 
/* 1464 */     equal = this.isBlackMove == b.isBlackMove;
/* 1465 */     if (!equal) {
/* 1466 */       Log.debug2(DEBUG, "move parity failed");
/*      */     }
/* 1468 */     if (equal) {
/* 1469 */       equal = this.squares.length == b.squares.length;
/*      */     }
/* 1471 */     if (!equal) {
/* 1472 */       Log.debug2(DEBUG, "board dimension(f) failed");
/*      */     }
/* 1474 */     if (equal) {
/* 1475 */       equal = this.squares[0].length == b.squares[0].length;
/*      */     }
/* 1477 */     if (!equal) {
/* 1478 */       Log.debug2(DEBUG, "board dimension(r) failed");
/*      */     }
/* 1480 */     if (equal) {
/* 1481 */       equal = isWhiteCastleableQueenside() == 
/* 1482 */         b.isWhiteCastleableQueenside();
/*      */     }
/* 1484 */     if (!equal) {
/* 1485 */       Log.debug2(DEBUG, "castling QW failed");
/*      */     }
/* 1487 */     if (equal) {
/* 1488 */       equal = isBlackCastleableQueenside() == 
/* 1489 */         b.isBlackCastleableQueenside();
/*      */     }
/* 1491 */     if (!equal) {
/* 1492 */       Log.debug2(DEBUG, "castling QB failed");
/*      */     }
/* 1494 */     if (equal) {
/* 1495 */       equal = isWhiteCastleableKingside() == 
/* 1496 */         b.isWhiteCastleableKingside();
/*      */     }
/* 1498 */     if (!equal) {
/* 1499 */       Log.debug2(DEBUG, "castling KW failed");
/*      */     }
/* 1501 */     if (equal) {
/* 1502 */       equal = isBlackCastleableKingside() == 
/* 1503 */         b.isBlackCastleableKingside();
/*      */     }
/* 1505 */     if (!equal) {
/* 1506 */       Log.debug2(DEBUG, "castling KB failed");
/*      */     }
/* 1508 */     if (equal) {
/* 1509 */       equal = this.enpassantFile == b.enpassantFile;
/*      */     }
/* 1511 */     if (!equal) {
/* 1512 */       Log.debug2(DEBUG, "enpassant failed");
/*      */     }
/* 1514 */     if (equal) {
/* 1515 */       for (byte i = 0; (i < this.squares.length) && (equal); i = (byte)(i + 1)) {
/* 1516 */         for (byte j = 0; (j < this.squares[i].length) && (equal); j = (byte)(j + 1)) {
/* 1517 */           if (this.squares[i][j].getOccupant() == null) {
/* 1518 */             equal = b.squares[i][j].getOccupant() == null;
/*      */           } else {
/* 1520 */             equal = b.squares[i][j].getOccupant() != null;
/*      */             
/* 1522 */             if (!equal) {
/* 1523 */               Log.debug2(DEBUG, "squares[" + i + "][" + 
/* 1524 */                 j + "] nulls failed");
/*      */             }
/* 1526 */             if (equal) {
/* 1527 */               equal = this.squares[i][j].getOccupant().getIndex() == 
/* 1528 */                 b.squares[i][j].getOccupant().getIndex();
/*      */               
/* 1530 */               if (!equal) {
/* 1531 */                 Log.debug2(DEBUG, "squares[" + i + "][" + 
/* 1532 */                   j + "].Occupant failed");
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1540 */     if (equal) {
/* 1541 */       Log.debug2(DEBUG, "boards are the same");
/*      */     }
/* 1543 */     return equal;
/*      */   }
/*      */   
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1549 */     int hash = 7;
/* 1550 */     hash = 31 * hash + fen.boardToString(this).hashCode();
/*      */     
/* 1552 */     return hash;
/*      */   }
/*      */   
/*      */   public String dumpLegalMoves()
/*      */   {
/* 1557 */     return dumpLegalMoves(this.isBlackMove);
/*      */   }
/*      */   
/*      */   public String dumpOpposingMoves() {
/* 1561 */     return dumpLegalMoves(!this.isBlackMove);
/*      */   }
/*      */   
/*      */   public String dumpLegalMoves(boolean blacksMoves) {
/* 1565 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1567 */     List team = blacksMoves ? this.blackTeam : this.whiteTeam;
/*      */     
/* 1569 */     if (blacksMoves) {
/* 1570 */       sb.append("Black's team moves-----------------------\n");
/*      */     } else {
/* 1572 */       sb.append("White's team moves-----------------------\n");
/*      */     }
/* 1574 */     for (int i = 0; i < team.size(); i++) {
/* 1575 */       ChessPiece p = (ChessPiece)team.get(i);
/* 1576 */       sb.append(!p.captured ? " " : "x");
/* 1577 */       sb.append(p)
/* 1578 */         .append("(")
/* 1579 */         .append(p.orig)
/* 1580 */         .append(") ")
/* 1581 */         .append(p.getLegalDests())
/* 1582 */         .append("\n");
/*      */     }
/* 1584 */     return sb.toString();
/*      */   }
/*      */   
/*      */ 
/*      */   public String dump()
/*      */   {
/* 1590 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1592 */     sb.append(toString())
/* 1593 */       .append("\n")
/* 1594 */       .append("isInitialPositionDefault: ")
/* 1595 */       .append(this.isInitialPositionDefault)
/* 1596 */       .append("\n")
/* 1597 */       .append("isBlackMove: ")
/* 1598 */       .append(this.isBlackMove)
/* 1599 */       .append("\n")
/* 1600 */       .append("enpassant file: ");
/* 1601 */     if (this.enpassantFile > 0) {
/* 1602 */       sb.append(san.fileToChar(this.enpassantFile));
/*      */     } else {
/* 1604 */       sb.append("-");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1617 */     sb.append("\n").append("lastMove: ").append(this.lastMove).append("\n").append("moveNumber: ").append(this.moveNumber).append("\n").append("plyCount50: ").append(this.plyCount50).append("\n").append("staleLegalDests: ").append(this.staleLegalDests).append("\n");
/* 1618 */     sb.append(dumpLegalMoves())
/* 1619 */       .append(dumpOpposingMoves());
/*      */     
/* 1621 */     return sb.toString();
/*      */   }
/*      */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessBoard.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */