package com.amicabile.openingtrainer.model.state;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.io.FEN;
import org.apache.commons.lang.StringUtils;

public class GamePrinterState {

   private long boardNumber;
   private String moveString;
   private int moveNumber;
   private boolean blackMove;
   private int depth;
   private static final String STARTING_BOARD_FEN;
   private static final int STARTING_BOARD_HASH_CODE;
   public static final int MASTER_HASH_CODE = 997;
   private int boardHashCode;
   private String lastDivString;


   static {
      ChessBoard board = new ChessBoard();
      FEN fen = new FEN();
      STARTING_BOARD_FEN = fen.boardToString(board);
      STARTING_BOARD_HASH_CODE = fen.hashCode() % 997;
   }

   public String toString() {
      return this.boardNumber + "_" + this.moveString + "_" + this.moveNumber + "_" + this.blackMove + "_" + this.depth + "_" + this.boardHashCode;
   }

   public GamePrinterState() {}

   public GamePrinterState(String divString) {
      String[] elements = StringUtils.split(divString, '_');

      try {
         this.boardNumber = (long)Integer.parseInt(elements[0]);
         this.moveString = elements[1];
         this.moveNumber = Integer.parseInt(elements[2]);
         this.blackMove = "true".equals(elements[3]);
         this.depth = Integer.parseInt(elements[4]);
         this.boardHashCode = Integer.parseInt(elements[5]);
         if(this.moveNumber == 0) {
            ;
         }
      } catch (NumberFormatException var4) {
         var4.printStackTrace();
      }

   }

   public boolean getBlackMove() {
      return this.blackMove;
   }

   public void setBlackMove(boolean blackMove) {
      this.blackMove = blackMove;
   }

   public long getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(long boardNumber) {
      this.boardNumber = boardNumber;
   }

   public int getMoveNumber() {
      return this.moveNumber;
   }

   public void setMoveNumber(int moveNumber) {
      this.moveNumber = moveNumber;
   }

   public String getLastDivString() {
      return this.lastDivString;
   }

   public void setLastDivString(String lastDivString) {
      this.lastDivString = lastDivString;
   }

   public int getDepth() {
      return this.depth;
   }

   public void setDepth(int depth) {
      this.depth = depth;
   }

   public String getMoveString() {
      return this.moveString;
   }

   public void setMoveString(String moveString) {
      this.moveString = moveString;
   }

   public int getBoardHashCode() {
      return this.boardHashCode;
   }

   public void setBoardHashCode(int boardHashCode) {
      this.boardHashCode = boardHashCode;
   }
}
