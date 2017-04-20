/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSBoardUpdateEvent
/*     */   extends ICSEvent
/*     */   implements ICSBoardEvent
/*     */ {
/*     */   public static final int EVENT_TYPE = 1;
/*     */   
/*     */ 
/*     */   public static final int ISO_POSITION_RELATION = -3;
/*     */   
/*     */ 
/*     */   public static final int OBSERVING_EXAMINED_RELATION = -2;
/*     */   
/*     */ 
/*     */   public static final int EXAMINING_RELATION = 2;
/*     */   
/*     */ 
/*     */   public static final int PLAYING_NOT_MY_MOVE_RELATION = -1;
/*     */   
/*     */ 
/*     */   public static final int PLAYING_MY_MOVE_RELATION = 1;
/*     */   
/*     */ 
/*     */   public static final int OBSERVING_PLAY_RELATION = 0;
/*     */   
/*     */ 
/*     */   String white;
/*     */   
/*     */ 
/*     */   String black;
/*     */   
/*     */ 
/*     */   String verboseMove;
/*     */   
/*     */ 
/*     */   String sanMove;
/*     */   
/*     */ 
/*     */   protected boolean isBlackMove;
/*     */   
/*     */ 
/*     */   protected boolean canWhiteCastleOO;
/*     */   
/*     */ 
/*     */   protected boolean canWhiteCastleOOO;
/*     */   
/*     */ 
/*     */   protected boolean canBlackCastleOO;
/*     */   
/*     */   protected boolean canBlackCastleOOO;
/*     */   
/*     */   protected boolean flipBoard;
/*     */   
/*     */   protected boolean clockMoving;
/*     */   
/*     */   protected char[][] board;
/*     */   
/*  61 */   protected int enpassantFile = -1;
/*     */   
/*     */ 
/*     */   protected int irreversable;
/*     */   
/*     */ 
/*     */   protected int boardNumber;
/*     */   
/*     */ 
/*     */   protected int moveNumber;
/*     */   
/*     */ 
/*     */   protected int relation;
/*     */   
/*     */   protected int itime;
/*     */   
/*     */   protected int incr;
/*     */   
/*     */   protected int whiteMaterial;
/*     */   
/*     */   protected int blackMaterial;
/*     */   
/*     */   protected int whiteClock;
/*     */   
/*     */   protected int blackClock;
/*     */   
/*     */   protected int moveTime;
/*     */   
/*     */   protected int timesealDelta;
/*     */   
/*     */ 
/*     */   public ICSBoardUpdateEvent()
/*     */   {
/*  94 */     super(1);
/*  95 */     this.board = new char[8][8];
/*     */   }
/*     */   
/*     */ 
/*  99 */   public char[][] getBoardArray() { return this.board; }
/*     */   
/* 101 */   public boolean isBlackToMove() { return this.isBlackMove; }
/*     */   
/* 103 */   public String getWhitePlayer() { return this.white; }
/* 104 */   public String getBlackPlayer() { return this.black; }
/* 105 */   public int getMoveNumber() { return this.moveNumber; }
/* 106 */   public int getEnPassantFile() { return this.enpassantFile; }
/* 107 */   public int getPlySinceLastIrreversableMove() { return this.irreversable; }
/* 108 */   public int getBoardNumber() { return this.boardNumber; }
/* 109 */   public int getRelation() { return this.relation; }
/* 110 */   public int getInitialTime() { return this.itime; }
/* 111 */   public int getIncrement() { return this.incr; }
/* 112 */   public int getWhiteMaterial() { return this.whiteMaterial; }
/* 113 */   public int getBlackMaterial() { return this.blackMaterial; }
/* 114 */   public int getWhiteClock() { return this.whiteClock; }
/* 115 */   public int getBlackClock() { return this.blackClock; }
/* 116 */   public int getLag() { return this.timesealDelta; }
/* 117 */   public String getSAN() { return this.sanMove; }
/* 118 */   public boolean isBlackMove() { return this.isBlackMove; }
/* 119 */   public boolean isWhiteCastleableKingside() { return this.canWhiteCastleOO; }
/* 120 */   public boolean isWhiteCastleableQueenside() { return this.canWhiteCastleOOO; }
/* 121 */   public boolean isBlackCastleableKingside() { return this.canBlackCastleOO; }
/* 122 */   public boolean isBlackCastleableQueenside() { return this.canBlackCastleOOO; }
/*     */   
/* 124 */   public int getMoveTime() { return this.moveTime; }
/* 125 */   public String getVerboseMove() { return this.verboseMove; }
/*     */   
/* 127 */   public boolean isFlipBoard() { return this.flipBoard; }
/* 128 */   public boolean isClockMoving() { return this.clockMoving; }
/*     */   
/*     */   public String getMoveTimeAsString() {
/* 131 */     return getClockAsString(this.moveTime, true);
/*     */   }
/*     */   
/*     */   public String getBlackClockAsString() {
/* 135 */     return getClockAsString(this.blackClock, false);
/*     */   }
/*     */   
/*     */   public String getWhiteClockAsString() {
/* 139 */     return getClockAsString(this.whiteClock, false);
/*     */   }
/*     */   
/*     */ 
/* 143 */   public void setBoardArray(char[][] b) { this.board = b; }
/* 144 */   public void setBoardNumber(int board) { this.boardNumber = board; }
/*     */   
/* 146 */   public void setBlackMove(boolean t) { this.isBlackMove = t; }
/*     */   
/* 148 */   public void setWhitePlayer(String player) { this.white = player; }
/* 149 */   public void setBlackPlayer(String player) { this.black = player; }
/* 150 */   public void setMoveNumber(int num) { this.moveNumber = num; }
/* 151 */   public void setEnPassantFile(int ep) { this.enpassantFile = ep; }
/* 152 */   public void setPlySinceLastIrreversableMove(int ply) { this.irreversable = ply; }
/* 153 */   public void setRelation(int pov) { this.relation = pov; }
/* 154 */   public void setInitialTime(int seconds) { this.itime = seconds; }
/* 155 */   public void setIncrement(int seconds) { this.incr = seconds; }
/* 156 */   public void setWhiteMaterial(int value) { this.whiteMaterial = value; }
/* 157 */   public void setBlackMaterial(int value) { this.blackMaterial = value; }
/* 158 */   public void setWhiteClock(int ms) { this.whiteClock = ms; }
/* 159 */   public void setBlackClock(int ms) { this.blackClock = ms; }
/* 160 */   public void setLag(int ms) { this.timesealDelta = ms; }
/* 161 */   public void setSAN(String san) { this.sanMove = san; }
/* 162 */   public void setWhiteCastleableKingside(boolean t) { this.canWhiteCastleOO = t; }
/*     */   
/* 164 */   public void setWhiteCastleableQueenside(boolean t) { this.canWhiteCastleOOO = t; }
/*     */   
/* 166 */   public void setBlackCastleableKingside(boolean t) { this.canBlackCastleOO = t; }
/*     */   
/* 168 */   public void setBlackCastleableQueenside(boolean t) { this.canBlackCastleOOO = t; }
/*     */   
/* 170 */   public void setVerboseMove(String move) { this.verboseMove = move; }
/* 171 */   public void setFlipBoard(boolean t) { this.flipBoard = t; }
/* 172 */   public void setClockMoving(boolean t) { this.clockMoving = t; }
/*     */   
/* 174 */   public void setMoveTime(int ms) { this.moveTime = ms; }
/*     */   
/*     */ 
/*     */   protected String getClockAsString(int clock, boolean move)
/*     */   {
/* 179 */     StringBuffer sb = new StringBuffer(7);
/*     */     
/*     */ 
/* 182 */     int h = clock / 3600000;
/* 183 */     int m = clock % 3600000 / 60000;
/* 184 */     int s = clock % 60000 / 1000;
/* 185 */     int ms = clock % 1000;
/*     */     
/* 187 */     if ((move) && (h > 1)) {
/* 188 */       sb.append(h).append(":");
/* 189 */       if (m < 10)
/* 190 */         sb.append(0);
/*     */     }
/* 192 */     sb.append(m).append(":");
/* 193 */     if (s < 10)
/* 194 */       sb.append(0);
/* 195 */     sb.append(s).append(".");
/* 196 */     if (ms < 100)
/* 197 */       sb.append(0);
/* 198 */     if (ms < 10)
/* 199 */       sb.append(0);
/* 200 */     sb.append(ms);
/* 201 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 208 */     StringBuffer sb = new StringBuffer(80);
/* 209 */     sb.append("Board Update(" + getBoardNumber() + "): ")
/* 210 */       .append(getWhitePlayer())
/* 211 */       .append(" vs. ")
/* 212 */       .append(getBlackPlayer())
/* 213 */       .append("\n\n");
/*     */     
/* 215 */     int r = this.board.length - 1;int f = 0;
/* 216 */     for (r = this.board[0].length - 1; r >= 0; r--) {
/* 217 */       sb.append("   ").append(r + 1).append("  ");
/* 218 */       for (f = 0; f < this.board.length; f++) {
/* 219 */         if (this.board[f][r] != ' ') {
/* 220 */           sb.append(this.board[f][r]);
/*     */         }
/* 222 */         else if ((f + r) % 2 == 1) {
/* 223 */           sb.append("#");
/*     */         } else
/* 225 */           sb.append(" ");
/* 226 */         sb.append(" ");
/*     */       }
/*     */       
/* 229 */       if (r == 5)
/*     */       {
/* 231 */         sb.append("  ").append(getBlackClockAsString()); }
/* 232 */       if (r == 3)
/*     */       {
/* 234 */         sb.append("  ").append(getWhiteClockAsString()); }
/* 235 */       sb.append("\n");
/*     */     }
/* 237 */     sb.append("\n      A B C D E F G H\n\n");
/* 238 */     if (!isBlackMove())
/*     */     {
/*     */ 
/*     */ 
/* 242 */       sb.append("         ").append(getMoveNumber()).append(".").append(getSAN()).append("\n");
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 247 */       sb.append("       ").append(getMoveNumber()).append("..").append(getSAN()).append("\n");
/*     */     }
/* 249 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 253 */     return getReadable();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSBoardUpdateEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */