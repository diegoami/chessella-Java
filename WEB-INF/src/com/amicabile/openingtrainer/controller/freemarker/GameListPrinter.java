/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.config.ShowBoardRule;
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*    */ import freemarker.template.Configuration;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameListPrinter
/*    */   extends GenericPrinter
/*    */ {
/* 15 */   private static Logger log = Logger.getLogger(GameListPrinter.class
/* 16 */     .getName());
/*    */   
/*    */   private ShowBoardRule showBoardRule;
/*    */   
/*    */   private ChessGameGroup chessGameGroup;
/*    */   
/*    */   public GameListPrinter(String templateName, Configuration config, VelocityMoveTree moveTree, ChessGameGroup chessGameGroup)
/*    */   {
/* 24 */     super(templateName, config);
/*    */     
/* 26 */     this.chessGameGroup = chessGameGroup;
/*    */   }
/*    */   
/*    */ 
/*    */   public GameListPrinter(String templateName, Configuration config, ChessGameGroup chessGameGroup, ShowBoardRule showBoardRule)
/*    */   {
/* 32 */     super(templateName, config);
/* 33 */     this.chessGameGroup = chessGameGroup;
/* 34 */     this.showBoardRule = showBoardRule;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fillMap(Map map)
/*    */   {
/* 42 */     map.put("showBoardRule", this.showBoardRule);
/* 43 */     map.put("chessGameGroup", this.chessGameGroup);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\GameListPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */