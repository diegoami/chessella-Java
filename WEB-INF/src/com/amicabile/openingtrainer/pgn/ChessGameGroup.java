/*    */ package com.amicabile.openingtrainer.pgn;
/*    */ 
/*    */ import ictk.boardgame.Board;
/*    */ import ictk.boardgame.History;
/*    */ import ictk.boardgame.chess.ChessGame;
/*    */ import ictk.boardgame.chess.io.FEN;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChessGameGroup
/*    */ {
/* 14 */   private List<ChessGame> chessGameList = new ArrayList();
/*    */   
/*    */   public void addChessGame(ChessGame chessGame) {
/* 17 */     this.chessGameList.add(chessGame);
/*    */   }
/*    */   
/*    */   public List<ChessGame> getGameList() {
/* 21 */     return this.chessGameList;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ChessGame getGameAt(int counter)
/*    */   {
/* 29 */     if (this.chessGameList.size() < counter) {
/* 30 */       throw new IllegalArgumentException("No chess game at " + counter);
/*    */     }
/* 32 */     return (ChessGame)this.chessGameList.get(counter);
/*    */   }
/*    */   
/*    */   public Board getBoard(int gameCounter, int plyCounter)
/*    */   {
/* 37 */     ChessGame chessGame = getGameAt(gameCounter);
/* 38 */     History history = chessGame.getHistory();
/* 39 */     for (int i = 0; i < plyCounter; i++) {
/* 40 */       history.next();
/*    */     }
/* 42 */     Board board = chessGame.getBoard();
/* 43 */     return board;
/*    */   }
/*    */   
/*    */   public String getFenString(int gameCounter, int plyCounter) {
/* 47 */     Board board = getBoard(gameCounter, plyCounter);
/* 48 */     FEN fen = new FEN();
/* 49 */     String fenString = fen.boardToString(board);
/* 50 */     return fenString;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\ChessGameGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */