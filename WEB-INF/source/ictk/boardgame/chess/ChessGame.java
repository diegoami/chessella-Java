package ictk.boardgame.chess;

import ictk.boardgame.Board;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.Move;
import ictk.boardgame.Result;
import ictk.boardgame.SingleBoardGame;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessResult;

public class ChessGame implements SingleBoardGame {

   public static final boolean BLACK = true;
   public static final boolean WHITE = false;
   protected Board board;
   protected History history;
   protected GameInfo gameInfo;


   public ChessGame() {
      this((ChessGameInfo)null);
   }

   public ChessGame(ChessGameInfo _gameInfo) {
      this(_gameInfo, (Board)null, (History)null);
   }

   public ChessGame(GameInfo _gameInfo, Board _board) {
      this(_gameInfo, _board, (History)null);
   }

   public ChessGame(GameInfo _gameInfo, Board _board, History _hist) {
      this.gameInfo = _gameInfo;
      this.history = _hist;
      this.board = _board;
      if(this.board == null) {
         this.board = new ChessBoard();
      }

      if(this.history == null) {
         this.history = new History(this);
      }

   }

   public int getNumberOfPlayers() {
      return 2;
   }

   public GameInfo getGameInfo() {
      return this.gameInfo;
   }

   public void setGameInfo(GameInfo gi) {
      this.gameInfo = (ChessGameInfo)gi;
   }

   public History getHistory() {
      return this.history;
   }

   public Board getBoard() {
      return this.board;
   }

   public Board[] getBoards() {
      Board[] b = new Board[]{this.board};
      return b;
   }

   public void setResult(Result result) {
      Move m = this.history.getFinalMove(true);
      m.setResult(result);
      if(this.gameInfo != null) {
         this.gameInfo.setResult(result);
      }

   }

   public int getPlayerToMove() {
      return this.board.isBlackMove()?1:0;
   }

   public int[] getPlayersToMove() {
      int[] i = new int[]{this.getPlayerToMove()};
      return i;
   }

   public void setBoard(Board _board) {
      this.board = (ChessBoard)_board;
   }

   public void setHistory(History _hist) {
      this.history = _hist;
   }

   public Result getCurrentResult() {
      Result r = null;
      Move m = null;
      m = this.history.getCurrentMove();
      if(m != null) {
         r = m.getResult();
      }

      return (Result)(r == null?new ChessResult(0):r);
   }

   public Result getResult() {
      Result r = null;
      Move m = null;
      m = this.history.getFinalMove(true);
      if(m != null) {
         r = m.getResult();
         return r;
      } else {
         return new ChessResult(0);
      }
   }

   public String toString() {
      String sboard = this.board.toString();
      return (this.gameInfo == null?"No Game Info":this.gameInfo.toString()) + "\n" + this.history + "\n" + sboard;
   }

   public String dump() {
      String sboard = this.board.toString();
      return (this.gameInfo == null?"No Game Info":this.gameInfo.toString()) + "\n" + this.history + "\n" + sboard;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj != null && obj.getClass() == this.getClass()) {
         boolean t = true;
         ChessGame g = (ChessGame)obj;
         t = t && (this.gameInfo == g.gameInfo || this.gameInfo != null && this.gameInfo.equals(g.gameInfo));
         t = t && (this.history == g.history || this.history != null && this.history.equals(g.history));
         return t;
      } else {
         return false;
      }
   }

   public int hashCode() {
      byte hash = 7;
      int hash1 = 31 * hash + (this.history == null?0:this.history.hashCode());
      hash1 = 31 * hash1 + (this.gameInfo == null?0:this.gameInfo.hashCode());
      return hash1;
   }
}
