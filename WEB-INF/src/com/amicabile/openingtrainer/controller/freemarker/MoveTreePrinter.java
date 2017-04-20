/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.config.ShowBoardRule;
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import freemarker.template.Configuration;
/*    */ import ictk.boardgame.Game;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoveTreePrinter
/*    */   extends GenericPrinter
/*    */ {
/* 15 */   private static Logger log = Logger.getLogger(MoveTreePrinter.class
/* 16 */     .getName());
/*    */   
/*    */   private ShowBoardRule showBoardRule;
/*    */   private Game game;
/*    */   private VelocityMoveTree moveTree;
/*    */   
/*    */   public MoveTreePrinter(String templateName, Configuration config, VelocityMoveTree moveTree, Game game)
/*    */   {
/* 24 */     super(templateName, config);
/* 25 */     this.moveTree = moveTree;
/* 26 */     this.game = game;
/*    */   }
/*    */   
/*    */ 
/*    */   public MoveTreePrinter(String templateName, Configuration config, VelocityMoveTree moveTree, Game game, ShowBoardRule showBoardRule)
/*    */   {
/* 32 */     super(templateName, config);
/* 33 */     this.moveTree = moveTree;
/* 34 */     this.game = game;
/* 35 */     this.showBoardRule = showBoardRule;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fillMap(Map map)
/*    */   {
/* 43 */     map.put("moveTree", this.moveTree);
/* 44 */     map.put("showBoardRule", this.showBoardRule);
/* 45 */     map.put("game", this.game);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\MoveTreePrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */