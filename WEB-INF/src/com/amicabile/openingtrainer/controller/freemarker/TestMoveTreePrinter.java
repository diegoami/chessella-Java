/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
/*    */ import com.amicabile.openingtrainer.controller.MoveTreePool;
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*    */ import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestMoveTreePrinter
/*    */   extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 28 */     new TestMoveTreePrinter().testFiller();
/*    */   }
/*    */   
/*    */   public void testFiller() throws Exception
/*    */   {
/* 33 */     Configuration cfg = new Configuration();
/*    */     try {
/* 35 */       cfg.setDirectoryForTemplateLoading(new File("templates"));
/* 36 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/*    */     }
/*    */     catch (IOException e) {
/* 39 */       e.printStackTrace();
/*    */     }
/*    */     
/* 42 */     ChessGameGroup chessGameGroupFromFile = 
/* 43 */       PGNAdapter.getChessGameGroupFromFile("pgn/kehrsam.pgn");
/* 44 */     for (ChessGame game : chessGameGroupFromFile.getGameList())
/*    */     {
/* 46 */       BufferedWriter bufferedWriter = new BufferedWriter(
/* 47 */         new OutputStreamWriter(System.out));
/*    */       
/*    */ 
/* 50 */       VelocityMoveTree moveTree = MoveTreePool.getInstance().retrieveMoveTree(game);
/*    */       
/* 52 */       MoveTreePrinter moveTreePrinter = new MoveTreePrinter(
/* 53 */         "notationtemplate.ftl", cfg, moveTree, game, ShowBoardRulePrototypes.DEFAULT_RULE);
/* 54 */       Map map = new HashMap();
/*    */       
/* 56 */       map.put("velocityBoardFactory", VelocityBoardFactory.getInstance());
/*    */       
/* 58 */       moveTreePrinter.showTemplate(map, bufferedWriter);
/* 59 */       moveTree.resetCurrentMove();
/* 60 */       bufferedWriter.close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\TestMoveTreePrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */