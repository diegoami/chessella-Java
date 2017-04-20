/*      */ package ictk.boardgame.chess;
/*      */ 
/*      */ import ictk.boardgame.Board;
/*      */ import ictk.boardgame.ContinuationList;
/*      */ import ictk.boardgame.IllegalMoveException;
/*      */ import ictk.boardgame.Move;
/*      */ import ictk.boardgame.OutOfTurnException;
/*      */ import ictk.boardgame.Result;
/*      */ import ictk.boardgame.UnverifiedMoveException;
/*      */ import ictk.boardgame.chess.io.SAN;
/*      */ import ictk.boardgame.io.Annotation;
/*      */ import ictk.util.Log;
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
/*      */ public class ChessMove
/*      */   extends Move
/*      */ {
/*  100 */   public static long DEBUG = Log.Move;
/*      */   
/*  102 */   private boolean nullMove = false;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int CASTLE_QUEENSIDE = -1;
/*      */   
/*      */ 
/*      */   public static final int CASTLE_KINGSIDE = 1;
/*      */   
/*      */ 
/*      */   protected ChessBoard board;
/*      */   
/*      */ 
/*      */   protected Square orig;
/*      */   
/*      */ 
/*      */   protected Square dest;
/*      */   
/*      */ 
/*      */   protected ChessPiece piece;
/*      */   
/*      */ 
/*      */   protected ChessPiece casualty;
/*      */   
/*      */ 
/*      */   protected ChessPiece promotion;
/*      */   
/*      */ 
/*      */   protected int prevEnPassantFile;
/*      */   
/*      */ 
/*      */   protected int prevPlyCount50;
/*      */   
/*      */ 
/*      */   protected boolean check;
/*      */   
/*      */ 
/*      */   protected boolean doublecheck;
/*      */   
/*      */ 
/*      */   protected boolean checkmate;
/*      */   
/*      */ 
/*      */   protected boolean stalemate;
/*      */   
/*      */ 
/*      */   protected boolean castleQueenside;
/*      */   
/*      */ 
/*      */   protected boolean castleKingside;
/*      */   
/*      */ 
/*  154 */   protected boolean[] unique = new boolean[2];
/*      */   
/*      */   public ChessMove(ChessBoard b) throws IllegalMoveException {
/*  157 */     if (b == null)
/*  158 */       throw new IllegalArgumentException(
/*  159 */         "A ChessMove cannot be associated with a null ChessBoard");
/*  160 */     this.board = b;
/*  161 */     this.nullMove = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessMove(ChessBoard b, int i)
/*      */     throws IllegalMoveException
/*      */   {
/*  171 */     if (b == null)
/*  172 */       throw new IllegalArgumentException(
/*  173 */         "A ChessMove cannot be associated with a null ChessBoard");
/*  174 */     this.board = b;
/*  175 */     switch (i) {
/*      */     case -1: 
/*  177 */       this.castleQueenside = true;
/*  178 */       break;
/*      */     case 1: 
/*  180 */       this.castleKingside = true;
/*  181 */       break;
/*      */     case 0: default: 
/*  183 */       throw new IllegalArgumentException(
/*  184 */         "illegal parameter sent to ChessMove Castle Constructor; check docs");
/*      */     }
/*      */     
/*  187 */     if (!this.board.isLegalMove(this)) {
/*  188 */       throw new IllegalMoveException();
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
/*      */   public ChessMove(ChessBoard b, int of, int or, int df, int dr)
/*      */     throws IllegalMoveException
/*      */   {
/*  206 */     this(b, of, or, df, dr, ChessPiece.NULL_PIECE);
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
/*      */   public ChessMove(ChessBoard b, int of, int or, int df, int dr, int promo)
/*      */     throws IllegalMoveException
/*      */   {
/*  225 */     this.board = b;
/*  226 */     this.orig = this.board.squares[(of - 1)][(or - 1)];
/*  227 */     this.dest = this.board.squares[(df - 1)][(dr - 1)];
/*  228 */     if (promo > 0) {
/*  229 */       this.promotion = ChessPiece.toChessPiece(promo);
/*      */     }
/*  231 */     if ((this.promotion != null) && ((this.promotion.isKing()) || (this.promotion.isPawn()))) {
/*  232 */       throw new IllegalMoveException(
/*  233 */         "Can't promote a pawn to King or Pawn");
/*      */     }
/*  235 */     if (!this.board.isLegalMove(this)) {
/*  236 */       throw new IllegalMoveException();
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
/*      */   public ChessMove(ChessBoard b, Square o, Square d)
/*      */     throws IllegalMoveException
/*      */   {
/*  251 */     this(b, o, d, null);
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
/*      */   public ChessMove(ChessBoard b, Square o, Square d, ChessPiece promo)
/*      */     throws IllegalMoveException
/*      */   {
/*  267 */     if (b == null) {
/*  268 */       throw new IllegalArgumentException(
/*  269 */         "A ChessMove cannot be associated with a null ChessBoard");
/*      */     }
/*  271 */     this.board = b;
/*  272 */     this.orig = o;
/*  273 */     this.dest = d;
/*  274 */     this.promotion = promo;
/*  275 */     if ((this.promotion != null) && ((this.promotion.isKing()) || (this.promotion.isPawn()))) {
/*  276 */       throw new IllegalMoveException(
/*  277 */         "Can't promote a pawn to King or Pawn");
/*      */     }
/*  279 */     if (!this.board.isLegalMove(this)) {
/*  280 */       throw new IllegalMoveException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ChessMove(ChessPiece piece, Square destination)
/*      */     throws IllegalMoveException
/*      */   {
/*  289 */     this(piece, destination, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessMove(ChessPiece piece, Square destination, ChessPiece promotion)
/*      */     throws IllegalMoveException
/*      */   {
/*  300 */     this((ChessBoard)piece.getBoard(), piece.getSquare(), destination, promotion);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ChessMove(Square o, Square d, ChessPiece promo, ChessBoard b)
/*      */   {
/*  310 */     this.board = b;
/*  311 */     this.orig = o;
/*  312 */     this.dest = d;
/*  313 */     this.promotion = promo;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void dispose()
/*      */   {
/*  325 */     super.dispose();
/*  326 */     this.piece = null;
/*  327 */     this.casualty = null;
/*  328 */     this.promotion = null;
/*  329 */     this.orig = null;
/*  330 */     this.dest = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void locateCastlingSquares()
/*      */   {
/*  341 */     if (this.castleQueenside) {
/*  342 */       if (this.board.isBlackMove()) {
/*  343 */         this.orig = this.board.getSquare('e', '8');
/*  344 */         this.dest = this.board.getSquare('c', '8');
/*      */       } else {
/*  346 */         this.orig = this.board.getSquare('e', '1');
/*  347 */         this.dest = this.board.getSquare('c', '1');
/*      */       }
/*  349 */     } else if (this.castleKingside) {
/*  350 */       if (this.board.isBlackMove()) {
/*  351 */         this.orig = this.board.getSquare('e', '8');
/*  352 */         this.dest = this.board.getSquare('g', '8');
/*      */       } else {
/*  354 */         this.orig = this.board.getSquare('e', '1');
/*  355 */         this.dest = this.board.getSquare('g', '1');
/*      */       }
/*      */     }
/*  358 */     else if (!$assertionsDisabled) { throw new AssertionError("locateCastlingSquares() called for non-castle move");
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
/*      */   protected void execute()
/*      */     throws IllegalMoveException, OutOfTurnException
/*      */   {
/*  374 */     if (!isNullMove()) {
/*  375 */       this.prevEnPassantFile = this.board.enpassantFile;
/*      */       
/*  377 */       if ((this.castleQueenside) || (this.castleKingside)) {
/*  378 */         locateCastlingSquares();
/*      */       }
/*  380 */       if (this.orig.piece != null) {
/*  381 */         this.piece = this.orig.piece;
/*      */       }
/*      */       
/*  384 */       Log.debug(DEBUG, "executing move: " + this);
/*  385 */       Log.debug2(DEBUG, this.piece.dump());
/*  386 */       Log.debug2(DEBUG, this.board);
/*      */       
/*      */ 
/*  389 */       if (this.piece == null) {
/*  390 */         throw new IllegalMoveException("No piece to move.", this);
/*      */       }
/*  392 */       if (this.piece.isBlack() != this.board.isBlackMove) {
/*  393 */         throw new OutOfTurnException("It is " + (
/*  394 */           this.board.isBlackMove ? "Black" : "White") + "'s move");
/*      */       }
/*  396 */       if ((!this.verified) && (!this.piece.isLegalDest(this.dest)))
/*      */       {
/*  398 */         Log.debug(DEBUG, 
/*  399 */           "tried to execute move with illegal destination");
/*  400 */         Log.debug2(DEBUG, "piece is: " + this.piece.dump());
/*  401 */         Log.debug2(DEBUG, "dest is: " + this.dest);
/*      */         
/*  403 */         throw new IllegalMoveException("Illegal move " + this, this);
/*      */       }
/*      */       
/*  406 */       this.casualty = this.dest.piece;
/*      */       
/*  408 */       if ((this.piece.isPawn()) && (this.casualty == null) && (this.orig.file != this.dest.file)) {
/*  409 */         this.casualty = this.board.getSquare(this.dest.file, this.orig.rank).piece;
/*      */         
/*  411 */         Log.debug(DEBUG, "enpassant encounterd: " + this + "orig: " + 
/*  412 */           this.orig + " dest:" + this.dest + " casualty: " + this.casualty);
/*      */       }
/*      */       else {
/*  415 */         this.board.enpassantFile = 0;
/*      */       }
/*      */       
/*      */ 
/*  419 */       if ((this.piece.isPawn()) || (this.casualty != null)) {
/*  420 */         this.prevPlyCount50 = this.board.plyCount50;
/*  421 */         this.board.plyCount50 = 0;
/*      */       } else {
/*  423 */         this.board.plyCount50 += 1;
/*      */       }
/*      */       
/*  426 */       if (this.casualty != null) {
/*  427 */         this.casualty.setCaptured(true);
/*  428 */         this.casualty.orig.piece = null;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  433 */       if ((this.piece.isPawn()) && (
/*  434 */         (this.orig.rank - this.dest.rank == 2) || (this.orig.rank - this.dest.rank == -2))) {
/*  435 */         this.board.setEnPassantFile(this.orig.file);
/*      */       }
/*      */       
/*  438 */       if ((this.piece.isKing()) && (this.piece.moveCount == 0))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  443 */         if (this.dest.file == 3) {
/*  444 */           Square rook_orig = this.board.getSquare(1, this.orig.rank);
/*  445 */           Square rook_dest = this.board.getSquare(4, this.orig.rank);
/*  446 */           rook_dest.piece = rook_orig.piece;
/*  447 */           rook_orig.piece = null;
/*  448 */           rook_dest.piece.orig = rook_dest; ChessPiece 
/*  449 */             tmp664_661 = rook_dest.piece;tmp664_661.moveCount = ((short)(tmp664_661.moveCount + 1));
/*  450 */           this.castleQueenside = true;
/*      */ 
/*      */         }
/*  453 */         else if (this.dest.file == 7) {
/*  454 */           Square rook_orig = this.board.getSquare(8, this.orig.rank);
/*  455 */           Square rook_dest = this.board.getSquare(6, this.orig.rank);
/*  456 */           rook_dest.piece = rook_orig.piece;
/*  457 */           rook_orig.piece = null;
/*  458 */           rook_dest.piece.orig = rook_dest; ChessPiece 
/*  459 */             tmp753_750 = rook_dest.piece;tmp753_750.moveCount = ((short)(tmp753_750.moveCount + 1));
/*  460 */           this.castleKingside = true;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  465 */       this.unique = this.board.isDestUniqueForClass(this.dest, this.piece);
/*      */       
/*      */ 
/*  468 */       this.dest.piece = this.piece;
/*  469 */       this.piece.orig = this.dest;
/*  470 */       this.orig.piece = null; ChessPiece 
/*  471 */         tmp821_818 = this.piece;tmp821_818.moveCount = ((short)(tmp821_818.moveCount + 1));
/*      */       
/*      */ 
/*  474 */       if ((this.piece.isPawn()) && (Pawn.isPromotionSquare(this.dest, this.piece.isBlack))) {
/*  475 */         if (this.promotion == null)
/*  476 */           this.promotion = new Queen();
/*  477 */         this.board.promote(this.piece, this.promotion);
/*      */       } else {
/*  479 */         this.promotion = null;
/*      */       }
/*      */       
/*  482 */       this.board.isBlackMove = (!this.piece.isBlack);
/*      */       
/*  484 */       if (this.piece.isBlack)
/*  485 */         this.board.moveNumber += 1;
/*      */     } else {
/*  487 */       this.board.isBlackMove = ((ChessMove)this.prev).piece.isBlack;
/*  488 */       if (!((ChessMove)this.prev).piece.isBlack) {
/*  489 */         this.board.moveNumber += 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  494 */     this.prev = this.board.lastMove;
/*  495 */     this.board.lastMove = this;
/*  496 */     this.executed = true;
/*      */     
/*  498 */     this.board.staleLegalDests = true;
/*      */     
/*      */ 
/*  501 */     if ((!this.verified) || ((this.continuation.isTerminal()) && (!isEndOfGame()))) {
/*  502 */       this.board.genLegalDests();
/*      */     }
/*  504 */     this.verified = true;
/*      */     
/*      */ 
/*  507 */     this.board.fireBoardEvent(1);
/*      */     
/*      */ 
/*  510 */     Log.debug(DEBUG, "execute successful");
/*  511 */     Log.debug2(DEBUG, this.board);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void unexecute()
/*      */   {
/*  522 */     Log.debug(DEBUG, "unexecuting move: " + this);
/*  523 */     Log.debug2(DEBUG, this.board);
/*      */     
/*      */ 
/*  526 */     if (!isNullMove()) {
/*  527 */       this.board.setEnPassantFile(this.prevEnPassantFile);
/*      */       
/*      */ 
/*  530 */       if ((this.piece.isKing()) && (this.piece.moveCount == 1))
/*      */       {
/*      */ 
/*      */ 
/*  534 */         if (this.dest.file == 3) {
/*  535 */           Square rook_orig = this.board.getSquare(1, this.orig.rank);
/*  536 */           Square rook_dest = this.board.getSquare(4, this.orig.rank);
/*  537 */           rook_orig.piece = rook_dest.piece;
/*  538 */           rook_dest.piece = null;
/*  539 */           rook_orig.piece.orig = rook_orig; ChessPiece 
/*  540 */             tmp140_137 = rook_orig.piece;tmp140_137.moveCount = ((short)(tmp140_137.moveCount - 1));
/*      */ 
/*      */         }
/*  543 */         else if (this.dest.file == 7) {
/*  544 */           Square rook_orig = this.board.getSquare(8, this.orig.rank);
/*  545 */           Square rook_dest = this.board.getSquare(6, this.orig.rank);
/*  546 */           rook_orig.piece = rook_dest.piece;
/*  547 */           rook_dest.piece = null;
/*  548 */           rook_orig.piece.orig = rook_orig; ChessPiece 
/*  549 */             tmp224_221 = rook_orig.piece;tmp224_221.moveCount = ((short)(tmp224_221.moveCount - 1));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  554 */       if ((this.piece.isPawn()) && (Pawn.isPromotionSquare(this.dest, this.piece.isBlack))) {
/*  555 */         this.board.promote(this.promotion, this.piece);
/*      */       }
/*      */       
/*  558 */       ChessPiece tmp283_280 = this.dest.piece;tmp283_280.moveCount = ((short)(tmp283_280.moveCount - 1));
/*  559 */       this.orig.piece = this.dest.piece;
/*  560 */       this.piece.orig = this.orig;
/*  561 */       this.dest.piece = null;
/*      */       
/*  563 */       if (this.casualty != null) {
/*  564 */         this.casualty.setCaptured(false);
/*  565 */         this.casualty.orig.piece = this.casualty;
/*      */       }
/*      */       
/*      */ 
/*  569 */       if ((this.piece.isPawn()) || (this.casualty != null)) {
/*  570 */         this.board.plyCount50 = this.prevPlyCount50;
/*      */       } else
/*  572 */         this.board.plyCount50 -= 1;
/*  573 */       this.board.isBlackMove = this.piece.isBlack;
/*  574 */       if (this.piece.isBlack) {
/*  575 */         this.board.moveNumber -= 1;
/*      */       }
/*      */     } else {
/*  578 */       this.board.isBlackMove = (!((ChessMove)this.prev).piece.isBlack);
/*  579 */       if (!((ChessMove)this.prev).piece.isBlack) {
/*  580 */         this.board.moveNumber -= 1;
/*      */       }
/*      */     }
/*  583 */     this.board.lastMove = ((ChessMove)this.prev);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  588 */     this.executed = false;
/*      */     
/*  590 */     this.board.staleLegalDests = true;
/*      */     
/*      */ 
/*  593 */     this.board.fireBoardEvent(2);
/*      */     
/*      */ 
/*  596 */     Log.debug(DEBUG, "unexecute successful");
/*  597 */     Log.debug2(DEBUG, this.board);
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
/*      */   public boolean isLegal()
/*      */   {
/*  612 */     if (isNullMove()) {
/*  613 */       return true;
/*      */     }
/*  615 */     if (!this.verified)
/*  616 */       throw new UnverifiedMoveException(
/*  617 */         "Cannot determine if an unverified move is Check.");
/*  618 */     return this.piece.isLegalDest(this.dest);
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
/*      */   public boolean[] getUniqueness()
/*      */   {
/*  631 */     return this.unique;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isFileUnique()
/*      */   {
/*  639 */     return this.unique[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRankUnique()
/*      */   {
/*  647 */     return this.unique[1];
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isCheck()
/*      */   {
/*  653 */     if (!this.verified)
/*  654 */       throw new UnverifiedMoveException(
/*  655 */         "Cannot determine if an unverified move is Check.");
/*  656 */     return this.check;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isDoubleCheck()
/*      */   {
/*  662 */     if (!this.verified)
/*  663 */       throw new UnverifiedMoveException(
/*  664 */         "Cannot determine if an unverified move is Double Check.");
/*  665 */     return this.doublecheck;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isCheckmate()
/*      */   {
/*  673 */     if (!this.verified)
/*  674 */       throw new UnverifiedMoveException(
/*  675 */         "Cannot determine if an unverified move is Checkmate.");
/*  676 */     return this.checkmate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isStalemate()
/*      */   {
/*  685 */     if (!this.verified)
/*  686 */       throw new UnverifiedMoveException(
/*  687 */         "Cannot determine if an unverified move is Stalemate.");
/*  688 */     return this.stalemate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEndOfGame()
/*      */   {
/*  699 */     return (this.checkmate) || (this.stalemate) || ((this.result != null) && (!this.result.isUndecided()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Result getResult()
/*      */   {
/*  707 */     return this.result;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void setCheck(boolean t)
/*      */   {
/*  713 */     this.check = t;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setDoubleCheck(boolean t)
/*      */   {
/*  721 */     this.doublecheck = t;
/*  722 */     if (t) {
/*  723 */       this.check = t;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setCheckmate(boolean t)
/*      */   {
/*  734 */     this.checkmate = t;
/*  735 */     if (this.result == null) {
/*  736 */       this.result = new ChessResult(
/*  737 */         this.board.isBlackMove() ? 3 : 
/*  738 */         2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setStalemate(boolean t)
/*      */   {
/*  749 */     this.stalemate = t;
/*  750 */     if (this.result == null) {
/*  751 */       this.result = new ChessResult(1);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setResult(Result res) {
/*  756 */     this.result = res;
/*      */     
/*      */ 
/*  759 */     if ((res != null) && (!res.isUndecided())) {
/*  760 */       this.continuation.setMainLineTerminal();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isExecuted()
/*      */   {
/*  772 */     return this.executed;
/*      */   }
/*      */   
/*      */   public void setExecuted(boolean value) {
/*  776 */     this.executed = value;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isVerified()
/*      */   {
/*  784 */     return this.verified;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece getChessPiece()
/*      */   {
/*  792 */     if (!this.verified)
/*  793 */       throw new UnverifiedMoveException(
/*  794 */         "Cannot determine which piece an unverified move will affect.");
/*  795 */     return this.piece;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece getCasualty()
/*      */   {
/*  803 */     if (!this.verified)
/*  804 */       throw new UnverifiedMoveException(
/*  805 */         "Cannot determine which piece an unverified move will affect.");
/*  806 */     return this.casualty;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChessPiece getPromotion()
/*      */   {
/*  815 */     if (!this.verified)
/*  816 */       throw new UnverifiedMoveException(
/*  817 */         "Cannot determine which piece an unverified move will affect.");
/*  818 */     return this.promotion;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBlackMove()
/*      */   {
/*  826 */     if (isNullMove()) {
/*  827 */       return ((ChessMove)this.prev).isBlackMove();
/*      */     }
/*  829 */     if (!this.verified) {
/*  830 */       throw new UnverifiedMoveException(
/*  831 */         "Cannot determine if an unverified move will affect black or white.");
/*      */     }
/*  833 */     return this.piece.isBlack();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isCastleQueenside()
/*      */   {
/*  841 */     if (!this.verified)
/*  842 */       throw new UnverifiedMoveException(
/*  843 */         "Cannot determine if an unverified move is Castle Queenside.");
/*  844 */     return this.castleQueenside;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isCastleKingside()
/*      */   {
/*  852 */     if (!this.verified)
/*  853 */       throw new UnverifiedMoveException(
/*  854 */         "Cannot determine if an unverified move is Castle Kingside.");
/*  855 */     return this.castleKingside;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int[] getCoordinates()
/*      */   {
/*  864 */     int[] coords = (int[])null;
/*      */     
/*  866 */     if ((this.castleQueenside) || (this.castleKingside)) {
/*  867 */       coords = new int[1];
/*  868 */       coords[0] = (this.castleQueenside ? -1 : 1);
/*      */     } else {
/*  870 */       if (this.promotion != null) {
/*  871 */         coords = new int[5];
/*  872 */         coords[4] = this.promotion.getIndex();
/*      */       } else {
/*  874 */         coords = new int[4];
/*      */       }
/*  876 */       coords[0] = this.orig.file;
/*  877 */       coords[1] = this.orig.rank;
/*  878 */       coords[2] = this.dest.file;
/*  879 */       coords[3] = this.dest.rank;
/*      */     }
/*      */     
/*  882 */     return coords;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int[] getDestinationCoordinates()
/*      */   {
/*  891 */     int[] coords = new int[2];
/*  892 */     int[] full = getCoordinates();
/*      */     
/*  894 */     coords[0] = full[2];
/*  895 */     coords[1] = full[3];
/*      */     
/*  897 */     return coords;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Square getDestination()
/*      */   {
/*  905 */     return this.dest;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Square getOrigin()
/*      */   {
/*  913 */     return this.orig;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int[] getOriginCoordinates()
/*      */   {
/*  921 */     int[] coords = new int[2];
/*  922 */     int[] full = getCoordinates();
/*      */     
/*  924 */     coords[0] = full[0];
/*  925 */     coords[1] = full[1];
/*      */     
/*  927 */     return coords;
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
/*      */   public String toString()
/*      */   {
/*  941 */     if (isNullMove()) {
/*  942 */       return "--";
/*      */     }
/*  944 */     String move_seperator = "-";
/*  945 */     String take_seperator = "x";
/*  946 */     String check_name = "+";
/*  947 */     String doublecheck_name = "+";
/*  948 */     String checkmate_name = "#";
/*      */     
/*      */     String s;
/*      */     String s;
/*  952 */     if (this.castleQueenside) {
/*  953 */       s = "O-O-O";
/*      */     } else { String s;
/*  955 */       if (this.castleKingside) {
/*  956 */         s = "O-O";
/*      */       }
/*  958 */       else if (!this.verified) {
/*  959 */         String s = this.orig.toString() + "-" + this.dest.toString();
/*  960 */         if (this.promotion != null) {
/*  961 */           s = 
/*  962 */             s + "=" + Character.toUpperCase(ChessBoard.san
/*  963 */             .pieceToChar(this.promotion.getIndex()));
/*      */         }
/*      */       }
/*      */       else {
/*  967 */         s = 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  972 */           ((this.piece != null) && (!(this.piece instanceof Pawn)) ? Character.toUpperCase(ChessBoard.san.pieceToChar(this.piece.getIndex())) : " ") + this.orig.toString() + (this.casualty != null ? take_seperator : move_seperator) + this.dest.toString();
/*      */         
/*  974 */         if (this.promotion != null) {
/*  975 */           s = 
/*  976 */             s + "=" + Character.toUpperCase(ChessBoard.san
/*  977 */             .pieceToChar(this.promotion.getIndex()));
/*      */         }
/*      */         
/*  980 */         if (this.checkmate) {
/*  981 */           s = s + checkmate_name;
/*      */         } else {
/*  983 */           if (this.check)
/*  984 */             s = s + check_name;
/*  985 */           if (this.doublecheck)
/*  986 */             s = s + doublecheck_name;
/*      */         }
/*      */       }
/*      */     }
/*  990 */     if (this.annotation != null) {
/*  991 */       s = s + "{" + this.annotation.getComment() + "}";
/*      */     }
/*  993 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object o)
/*      */   {
/* 1001 */     if (o == this)
/* 1002 */       return true;
/* 1003 */     if ((o == null) || (o.getClass() != getClass())) {
/* 1004 */       return false;
/*      */     }
/* 1006 */     ChessMove m = (ChessMove)o;
/*      */     
/* 1008 */     if (isNullMove()) {
/* 1009 */       return m.isNullMove();
/*      */     }
/*      */     
/* 1012 */     return (this.orig.equals(m.orig)) && (this.dest.equals(m.dest));
/*      */   }
/*      */   
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1018 */     int hash = 7;
/* 1019 */     if (isNullMove()) {
/* 1020 */       return 0;
/*      */     }
/*      */     
/* 1023 */     hash = 31 * hash + (this.orig == null ? 0 : this.orig.hashCode());
/* 1024 */     hash = 31 * hash + (this.dest == null ? 0 : this.dest.hashCode());
/*      */     
/* 1026 */     return hash;
/*      */   }
/*      */   
/*      */   public String dump() {
/* 1030 */     StringBuffer sb = new StringBuffer();
/* 1031 */     int[] coord = getCoordinates();
/*      */     
/* 1033 */     if (!isNullMove()) {
/* 1034 */       sb.append("Move: \n").append("   coordinates: ");
/*      */       
/* 1036 */       if (coord.length == 1) {
/* 1037 */         sb.append(coord[0] == -1 ? "O-O-O" : "O-O");
/*      */       } else {
/* 1039 */         sb.append(coord[0] + coord[1] + coord[2] + coord[3]);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1048 */       sb.append("\n").append("   verified: " + this.verified + "\n").append("   origin: " + this.orig + "\n").append("   destination: " + this.dest + "\n").append("   isFileUnique: " + this.unique[0] + "\n").append("   isRankUnique: " + this.unique[1] + "\n").append("   piece: " + this.piece + "\n");
/* 1049 */       if (this.piece != null) {
/* 1050 */         sb.append("      " + this.piece.dump());
/*      */       }
/* 1052 */       sb.append("   casualty: " + this.casualty + "\n");
/*      */       
/* 1054 */       if (this.casualty != null) {
/* 1055 */         sb.append(this.casualty.dump());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1066 */       sb.append("   promotion: " + this.promotion + "\n").append("   isCheck: " + this.check + "\n").append("   isDoubleCheck: " + this.doublecheck + "\n").append("   isCheckmate: " + this.checkmate + "\n").append("   isStalemate: " + this.stalemate + "\n").append("   result: " + this.result + "\n").append("   isEndOfGame: " + isEndOfGame() + "\n").append("   prenotation: ").append(getPrenotation()).append("\n").append("   annotation: ").append(getAnnotation()).append("\n").append("   previous: ").append(getPrev()).append("\n");
/*      */       
/* 1068 */       if ((this.continuation == null) || (this.continuation.isTerminal())) {
/* 1069 */         sb.append("   #continuations: terminal");
/*      */       } else
/* 1071 */         sb.append("   ").append(this.continuation.dump());
/*      */     }
/* 1073 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public void setBoard(Board b)
/*      */   {
/* 1078 */     this.board = ((ChessBoard)b);
/*      */   }
/*      */   
/*      */   public Board getBoard() {
/* 1082 */     return this.board;
/*      */   }
/*      */   
/*      */   public boolean isNullMove() {
/* 1086 */     return this.nullMove;
/*      */   }
/*      */   
/*      */   public void setNullMove(boolean nullMove) {
/* 1090 */     this.nullMove = nullMove;
/*      */   }
/*      */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessMove.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */