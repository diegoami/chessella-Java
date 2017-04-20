/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.Result;
/*     */ import ictk.boardgame.SingleBoardGame;
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
/*     */ public class ChessGame
/*     */   implements SingleBoardGame
/*     */ {
/*     */   public static final boolean BLACK = true;
/*     */   public static final boolean WHITE = false;
/*     */   protected Board board;
/*     */   protected History history;
/*     */   protected GameInfo gameInfo;
/*     */   
/*     */   public ChessGame()
/*     */   {
/*  49 */     this(null);
/*     */   }
/*     */   
/*     */   public ChessGame(ChessGameInfo _gameInfo) {
/*  53 */     this(_gameInfo, null, null);
/*     */   }
/*     */   
/*     */   public ChessGame(GameInfo _gameInfo, Board _board) {
/*  57 */     this(_gameInfo, _board, null);
/*     */   }
/*     */   
/*     */ 
/*     */   public ChessGame(GameInfo _gameInfo, Board _board, History _hist)
/*     */   {
/*  63 */     this.gameInfo = _gameInfo;
/*  64 */     this.history = _hist;
/*  65 */     this.board = _board;
/*     */     
/*  67 */     if (this.board == null) {
/*  68 */       this.board = new ChessBoard();
/*     */     }
/*     */     
/*  71 */     if (this.history == null) {
/*  72 */       this.history = new History(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  77 */   public int getNumberOfPlayers() { return 2; }
/*     */   
/*  79 */   public GameInfo getGameInfo() { return this.gameInfo; }
/*     */   
/*  81 */   public void setGameInfo(GameInfo gi) { this.gameInfo = ((ChessGameInfo)gi); }
/*     */   
/*  83 */   public History getHistory() { return this.history; }
/*     */   
/*  85 */   public Board getBoard() { return this.board; }
/*     */   
/*     */   public Board[] getBoards() {
/*  88 */     Board[] b = new Board[1];
/*  89 */     b[0] = this.board;
/*     */     
/*  91 */     return b;
/*     */   }
/*     */   
/*     */   public void setResult(Result result) {
/*  95 */     Move m = this.history.getFinalMove(true);
/*  96 */     m.setResult(result);
/*  97 */     if (this.gameInfo != null) {
/*  98 */       this.gameInfo.setResult(result);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int getPlayerToMove()
/*     */   {
/* 105 */     return this.board.isBlackMove() ? 1 : 0;
/*     */   }
/*     */   
/*     */   public int[] getPlayersToMove() {
/* 109 */     int[] i = new int[1];
/* 110 */     i[0] = getPlayerToMove();
/*     */     
/* 112 */     return i;
/*     */   }
/*     */   
/*     */   public void setBoard(Board _board)
/*     */   {
/* 117 */     this.board = ((ChessBoard)_board);
/*     */   }
/*     */   
/*     */   public void setHistory(History _hist) {
/* 121 */     this.history = _hist;
/*     */   }
/*     */   
/*     */   public Result getCurrentResult()
/*     */   {
/* 126 */     Result r = null;
/* 127 */     Move m = null;
/*     */     
/* 129 */     m = this.history.getCurrentMove();
/* 130 */     if (m != null) {
/* 131 */       r = m.getResult();
/*     */     }
/* 133 */     if (r == null) {
/* 134 */       return new ChessResult(0);
/*     */     }
/* 136 */     return r;
/*     */   }
/*     */   
/*     */   public Result getResult()
/*     */   {
/* 141 */     Result r = null;
/* 142 */     Move m = null;
/*     */     
/* 144 */     m = this.history.getFinalMove(true);
/* 145 */     if (m != null) {
/* 146 */       r = m.getResult();
/*     */     } else {
/* 148 */       return new ChessResult(0);
/*     */     }
/* 150 */     return r;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 158 */     String sboard = this.board.toString();
/* 159 */     return (this.gameInfo == null ? "No Game Info" : this.gameInfo.toString()) + 
/* 160 */       "\n" + this.history + "\n" + sboard;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String dump()
/*     */   {
/* 167 */     String sboard = this.board.toString();
/* 168 */     return (this.gameInfo == null ? "No Game Info" : this.gameInfo.toString()) + 
/* 169 */       "\n" + this.history + "\n" + sboard;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 177 */     if (this == obj) return true;
/* 178 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 179 */       return false;
/*     */     }
/* 181 */     boolean t = true;
/* 182 */     ChessGame g = (ChessGame)obj;
/*     */     
/* 184 */     t = (t) && ((this.gameInfo == g.gameInfo) || (
/* 185 */       (this.gameInfo != null) && (this.gameInfo.equals(g.gameInfo))));
/* 186 */     t = (t) && ((this.history == g.history) || (
/* 187 */       (this.history != null) && (this.history.equals(g.history))));
/*     */     
/* 189 */     return t;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 194 */     int hash = 7;
/*     */     
/* 196 */     hash = 31 * hash + (this.history == null ? 0 : this.history.hashCode());
/* 197 */     hash = 31 * hash + (this.gameInfo == null ? 0 : this.gameInfo.hashCode());
/*     */     
/* 199 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessGame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */