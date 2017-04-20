/*    */ package com.amicabile.openingtrainer.controller;
/*    */ 
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import ictk.boardgame.chess.ChessGame;
/*    */ import junit.framework.TestCase;
/*    */ import junit.textui.TestRunner;
/*    */ 
/*    */ public class TestMoveTreePrinter extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 12 */     TestRunner.run(TestMoveTreePrinter.class);
/*    */   }
/*    */   
/*    */   public void testFiller() throws Exception
/*    */   {
/* 17 */     ChessGameGroup chessGameGroupFromFile = 
/* 18 */       com.amicabile.openingtrainer.pgn.PGNAdapter.getChessGameGroupFromFile("testread.pgn");
/* 19 */     for (ChessGame game : chessGameGroupFromFile.getGameList())
/*    */     {
/* 21 */       MoveTreeFiller moveTreeFiller = new MoveTreeFiller(System.out);
/* 22 */       moveTreeFiller.writeGame(game);
/* 23 */       MoveTreePrinter moveTreePrinter = new MoveTreePrinter(
/* 24 */         moveTreeFiller.getMoveTree());
/* 25 */       moveTreePrinter.printOut();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\TestMoveTreePrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */