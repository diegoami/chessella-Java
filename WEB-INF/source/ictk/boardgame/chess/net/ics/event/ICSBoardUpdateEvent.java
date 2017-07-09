package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;

public class ICSBoardUpdateEvent extends ICSEvent implements ICSBoardEvent {

   public static final int EVENT_TYPE = 1;
   public static final int ISO_POSITION_RELATION = -3;
   public static final int OBSERVING_EXAMINED_RELATION = -2;
   public static final int EXAMINING_RELATION = 2;
   public static final int PLAYING_NOT_MY_MOVE_RELATION = -1;
   public static final int PLAYING_MY_MOVE_RELATION = 1;
   public static final int OBSERVING_PLAY_RELATION = 0;
   String white;
   String black;
   String verboseMove;
   String sanMove;
   protected boolean isBlackMove;
   protected boolean canWhiteCastleOO;
   protected boolean canWhiteCastleOOO;
   protected boolean canBlackCastleOO;
   protected boolean canBlackCastleOOO;
   protected boolean flipBoard;
   protected boolean clockMoving;
   protected char[][] board = new char[8][8];
   protected int enpassantFile = -1;
   protected int irreversable;
   protected int boardNumber;
   protected int moveNumber;
   protected int relation;
   protected int itime;
   protected int incr;
   protected int whiteMaterial;
   protected int blackMaterial;
   protected int whiteClock;
   protected int blackClock;
   protected int moveTime;
   protected int timesealDelta;


   public ICSBoardUpdateEvent() {
      super(1);
   }

   public char[][] getBoardArray() {
      return this.board;
   }

   public boolean isBlackToMove() {
      return this.isBlackMove;
   }

   public String getWhitePlayer() {
      return this.white;
   }

   public String getBlackPlayer() {
      return this.black;
   }

   public int getMoveNumber() {
      return this.moveNumber;
   }

   public int getEnPassantFile() {
      return this.enpassantFile;
   }

   public int getPlySinceLastIrreversableMove() {
      return this.irreversable;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public int getRelation() {
      return this.relation;
   }

   public int getInitialTime() {
      return this.itime;
   }

   public int getIncrement() {
      return this.incr;
   }

   public int getWhiteMaterial() {
      return this.whiteMaterial;
   }

   public int getBlackMaterial() {
      return this.blackMaterial;
   }

   public int getWhiteClock() {
      return this.whiteClock;
   }

   public int getBlackClock() {
      return this.blackClock;
   }

   public int getLag() {
      return this.timesealDelta;
   }

   public String getSAN() {
      return this.sanMove;
   }

   public boolean isBlackMove() {
      return this.isBlackMove;
   }

   public boolean isWhiteCastleableKingside() {
      return this.canWhiteCastleOO;
   }

   public boolean isWhiteCastleableQueenside() {
      return this.canWhiteCastleOOO;
   }

   public boolean isBlackCastleableKingside() {
      return this.canBlackCastleOO;
   }

   public boolean isBlackCastleableQueenside() {
      return this.canBlackCastleOOO;
   }

   public int getMoveTime() {
      return this.moveTime;
   }

   public String getVerboseMove() {
      return this.verboseMove;
   }

   public boolean isFlipBoard() {
      return this.flipBoard;
   }

   public boolean isClockMoving() {
      return this.clockMoving;
   }

   public String getMoveTimeAsString() {
      return this.getClockAsString(this.moveTime, true);
   }

   public String getBlackClockAsString() {
      return this.getClockAsString(this.blackClock, false);
   }

   public String getWhiteClockAsString() {
      return this.getClockAsString(this.whiteClock, false);
   }

   public void setBoardArray(char[][] b) {
      this.board = b;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public void setBlackMove(boolean t) {
      this.isBlackMove = t;
   }

   public void setWhitePlayer(String player) {
      this.white = player;
   }

   public void setBlackPlayer(String player) {
      this.black = player;
   }

   public void setMoveNumber(int num) {
      this.moveNumber = num;
   }

   public void setEnPassantFile(int ep) {
      this.enpassantFile = ep;
   }

   public void setPlySinceLastIrreversableMove(int ply) {
      this.irreversable = ply;
   }

   public void setRelation(int pov) {
      this.relation = pov;
   }

   public void setInitialTime(int seconds) {
      this.itime = seconds;
   }

   public void setIncrement(int seconds) {
      this.incr = seconds;
   }

   public void setWhiteMaterial(int value) {
      this.whiteMaterial = value;
   }

   public void setBlackMaterial(int value) {
      this.blackMaterial = value;
   }

   public void setWhiteClock(int ms) {
      this.whiteClock = ms;
   }

   public void setBlackClock(int ms) {
      this.blackClock = ms;
   }

   public void setLag(int ms) {
      this.timesealDelta = ms;
   }

   public void setSAN(String san) {
      this.sanMove = san;
   }

   public void setWhiteCastleableKingside(boolean t) {
      this.canWhiteCastleOO = t;
   }

   public void setWhiteCastleableQueenside(boolean t) {
      this.canWhiteCastleOOO = t;
   }

   public void setBlackCastleableKingside(boolean t) {
      this.canBlackCastleOO = t;
   }

   public void setBlackCastleableQueenside(boolean t) {
      this.canBlackCastleOOO = t;
   }

   public void setVerboseMove(String move) {
      this.verboseMove = move;
   }

   public void setFlipBoard(boolean t) {
      this.flipBoard = t;
   }

   public void setClockMoving(boolean t) {
      this.clockMoving = t;
   }

   public void setMoveTime(int ms) {
      this.moveTime = ms;
   }

   protected String getClockAsString(int clock, boolean move) {
      StringBuffer sb = new StringBuffer(7);
      int h = clock / 3600000;
      int m = clock % 3600000 / '\uea60';
      int s = clock % '\uea60' / 1000;
      int ms = clock % 1000;
      if(move && h > 1) {
         sb.append(h).append(":");
         if(m < 10) {
            sb.append(0);
         }
      }

      sb.append(m).append(":");
      if(s < 10) {
         sb.append(0);
      }

      sb.append(s).append(".");
      if(ms < 100) {
         sb.append(0);
      }

      if(ms < 10) {
         sb.append(0);
      }

      sb.append(ms);
      return sb.toString();
   }

   public String getReadable() {
      StringBuffer sb = new StringBuffer(80);
      sb.append("Board Update(" + this.getBoardNumber() + "): ").append(this.getWhitePlayer()).append(" vs. ").append(this.getBlackPlayer()).append("\n\n");
      int r = this.board.length - 1;
      boolean f = false;

      for(r = this.board[0].length - 1; r >= 0; --r) {
         sb.append("   ").append(r + 1).append("  ");

         for(int var4 = 0; var4 < this.board.length; ++var4) {
            if(this.board[var4][r] != 32) {
               sb.append(this.board[var4][r]);
            } else if((var4 + r) % 2 == 1) {
               sb.append("#");
            } else {
               sb.append(" ");
            }

            sb.append(" ");
         }

         if(r == 5) {
            sb.append("  ").append(this.getBlackClockAsString());
         }

         if(r == 3) {
            sb.append("  ").append(this.getWhiteClockAsString());
         }

         sb.append("\n");
      }

      sb.append("\n      A B C D E F G H\n\n");
      if(!this.isBlackMove()) {
         sb.append("         ").append(this.getMoveNumber()).append(".").append(this.getSAN()).append("\n");
      } else {
         sb.append("       ").append(this.getMoveNumber()).append("..").append(this.getSAN()).append("\n");
      }

      return sb.toString();
   }

   public String toString() {
      return this.getReadable();
   }
}
