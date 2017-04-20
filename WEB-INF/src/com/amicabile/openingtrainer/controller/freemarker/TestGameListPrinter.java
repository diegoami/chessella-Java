/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
/*    */ import com.amicabile.openingtrainer.controller.MoveTreePool;
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*    */ import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.DefaultObjectWrapper;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import junit.framework.TestCase;
/*    */ 
/*    */ 
/*    */ public class TestGameListPrinter
/*    */   extends TestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 25 */     new TestGameListPrinter().testFiller();
/*    */   }
/*    */   
/*    */   public void testFiller() throws Exception
/*    */   {
/* 30 */     Configuration cfg = new Configuration();
/*    */     try {
/* 32 */       cfg.setDirectoryForTemplateLoading(new File("templates"));
/* 33 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/*    */     }
/*    */     catch (IOException e) {
/* 36 */       e.printStackTrace();
/*    */     }
/*    */     
/* 39 */     BufferedWriter bufferedWriter = new BufferedWriter(
/* 40 */       new OutputStreamWriter(System.out));
/*    */     
/* 42 */     ChessGameGroup chessGameGroupFromFile = 
/* 43 */       PGNAdapter.getChessGameGroupFromFile("pgn/1-4.pgn");
/*    */     
/*    */ 
/* 46 */     GameListPrinter gameListPrinter = new GameListPrinter(
/* 47 */       "gamelisttemplate.ftl", cfg, chessGameGroupFromFile, ShowBoardRulePrototypes.DEFAULT_RULE);
/* 48 */     Map map = new HashMap();
/*    */     
/* 50 */     map.put("velocityBoardFactory", VelocityBoardFactory.getInstance());
/* 51 */     map.put("moveTreePool", MoveTreePool.getInstance());
/*    */     
/* 53 */     gameListPrinter.showTemplate(map, bufferedWriter);
/*    */     
/*    */ 
/* 56 */     bufferedWriter.close();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\TestGameListPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */