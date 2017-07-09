package com.amicabile.openingtrainer.pgn;

import ictk.boardgame.Board;
import ictk.boardgame.History;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.io.FEN;
import java.util.ArrayList;
import java.util.List;

public class ChessGameGroup {

   private List chessGameList = new ArrayList();


   public void addChessGame(ChessGame chessGame) {
      this.chessGameList.add(chessGame);
   }

   public List getGameList() {
      return this.chessGameList;
   }

   public ChessGame getGameAt(int counter) {
      if(this.chessGameList.size() < counter) {
         throw new IllegalArgumentException("No chess game at " + counter);
      } else {
         return (ChessGame)this.chessGameList.get(counter);
      }
   }

   public Board getBoard(int gameCounter, int plyCounter) {
      ChessGame chessGame = this.getGameAt(gameCounter);
      History history = chessGame.getHistory();

      for(int board = 0; board < plyCounter; ++board) {
         history.next();
      }

      Board var6 = chessGame.getBoard();
      return var6;
   }

   public String getFenString(int gameCounter, int plyCounter) {
      Board board = this.getBoard(gameCounter, plyCounter);
      FEN fen = new FEN();
      String fenString = fen.boardToString(board);
      return fenString;
   }
}
