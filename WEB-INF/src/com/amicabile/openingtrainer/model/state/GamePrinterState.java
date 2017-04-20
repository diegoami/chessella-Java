/*     */ package com.amicabile.openingtrainer.model.state;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GamePrinterState
/*     */ {
/*     */   private long boardNumber;
/*     */   private String moveString;
/*     */   private int moveNumber;
/*     */   private boolean blackMove;
/*     */   private int depth;
/*     */   private static final String STARTING_BOARD_FEN;
/*     */   private static final int STARTING_BOARD_HASH_CODE;
/*     */   public static final int MASTER_HASH_CODE = 997;
/*     */   private int boardHashCode;
/*     */   private String lastDivString;
/*     */   
/*     */   public String toString()
/*     */   {
/*  30 */     return 
/*  31 */       this.boardNumber + "_" + this.moveString + "_" + this.moveNumber + "_" + this.blackMove + "_" + this.depth + "_" + this.boardHashCode;
/*     */   }
/*     */   
/*     */   static {
/*  35 */     Board board = new ChessBoard();
/*  36 */     FEN fen = new FEN();
/*  37 */     STARTING_BOARD_FEN = fen.boardToString(board);
/*  38 */     STARTING_BOARD_HASH_CODE = fen.hashCode() % 997;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public GamePrinterState(String divString)
/*     */   {
/*  46 */     String[] elements = StringUtils.split(divString, '_');
/*     */     try {
/*  48 */       this.boardNumber = Integer.parseInt(elements[0]);
/*  49 */       this.moveString = elements[1];
/*  50 */       this.moveNumber = Integer.parseInt(elements[2]);
/*  51 */       this.blackMove = "true".equals(elements[3]);
/*  52 */       this.depth = Integer.parseInt(elements[4]);
/*  53 */       this.boardHashCode = Integer.parseInt(elements[5]);
/*  54 */       if (this.moveNumber != 0) {}
/*     */       
/*     */ 
/*     */       return;
/*     */     }
/*     */     catch (NumberFormatException e)
/*     */     {
/*  61 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getBlackMove()
/*     */   {
/*  67 */     return this.blackMove;
/*     */   }
/*     */   
/*     */   public void setBlackMove(boolean blackMove) {
/*  71 */     this.blackMove = blackMove;
/*     */   }
/*     */   
/*     */   public long getBoardNumber() {
/*  75 */     return this.boardNumber;
/*     */   }
/*     */   
/*     */   public void setBoardNumber(long boardNumber) {
/*  79 */     this.boardNumber = boardNumber;
/*     */   }
/*     */   
/*     */   public int getMoveNumber() {
/*  83 */     return this.moveNumber;
/*     */   }
/*     */   
/*     */   public void setMoveNumber(int moveNumber) {
/*  87 */     this.moveNumber = moveNumber;
/*     */   }
/*     */   
/*     */   public String getLastDivString() {
/*  91 */     return this.lastDivString;
/*     */   }
/*     */   
/*     */   public void setLastDivString(String lastDivString) {
/*  95 */     this.lastDivString = lastDivString;
/*     */   }
/*     */   
/*     */   public int getDepth() {
/*  99 */     return this.depth;
/*     */   }
/*     */   
/*     */   public void setDepth(int depth) {
/* 103 */     this.depth = depth;
/*     */   }
/*     */   
/*     */   public String getMoveString() {
/* 107 */     return this.moveString;
/*     */   }
/*     */   
/*     */   public void setMoveString(String moveString) {
/* 111 */     this.moveString = moveString;
/*     */   }
/*     */   
/*     */   public int getBoardHashCode() {
/* 115 */     return this.boardHashCode;
/*     */   }
/*     */   
/*     */   public void setBoardHashCode(int boardHashCode) {
/* 119 */     this.boardHashCode = boardHashCode;
/*     */   }
/*     */   
/*     */   public GamePrinterState() {}
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\state\GamePrinterState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */