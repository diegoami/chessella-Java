/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.controller.MoveTreePool;
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.DefaultObjectWrapper;
/*    */ import ictk.boardgame.chess.ChessGame;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import junit.framework.TestCase;
/*    */ import junit.textui.TestRunner;
/*    */ 
/*    */ 
/*    */ public class TestBoardPrinter
/*    */   extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 25 */     TestRunner.run(TestBoardPrinter.class);
/*    */   }
/*    */   
/*    */   public void testFiller()
/*    */     throws Exception
/*    */   {
/* 31 */     Configuration cfg = new Configuration();
/*    */     try {
/* 33 */       cfg.setDirectoryForTemplateLoading(
/* 34 */         new File("templates"));
/* 35 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/*    */     }
/*    */     catch (IOException e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */     
/*    */ 
/* 42 */     ChessGameGroup chessGameGroupFromFile = PGNAdapter.getChessGameGroupFromFile("pgn/kehrsam.pgn");
/* 43 */     for (ChessGame game : chessGameGroupFromFile.getGameList())
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 50 */       BufferedWriter bufferedWriter = new BufferedWriter(
/* 51 */         new OutputStreamWriter(System.out));
/*    */       
/*    */ 
/* 54 */       VelocityMoveTree moveTree = MoveTreePool.getInstance().retrieveMoveTree(game);
/*    */       
/* 56 */       BoardPrinter boardPrinter = 
/* 57 */         new BoardPrinter("chessnetboardtemplate.ftl", cfg, game);
/* 58 */       Map map = new HashMap();
/* 59 */       boardPrinter.showTemplate(map, bufferedWriter);
/* 60 */       moveTree.resetCurrentMove();
/* 61 */       bufferedWriter.close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\TestBoardPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */