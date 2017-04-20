/*    */ package com.amicabile.openingtrainer.config;
/*    */ 
/*    */ public class ShowBoardRulePrototypes {
/*  4 */   public static ShowBoardRule SHOW_BEFORE_IMPORTANT_MOVE = new ShowBoardRule();
/*  5 */   public static ShowBoardRule SHOW_ALL = new ShowBoardRule();
/*    */   
/*  7 */   public static ShowBoardRule DEFAULT_RULE = SHOW_ALL;
/*  8 */   public static ShowBoardRule NO_SHOW = new ShowBoardRule();
/*    */   
/*    */   static {
/* 11 */     SHOW_BEFORE_IMPORTANT_MOVE.setShowBeforeImportantMove(true);
/*    */     
/* 13 */     SHOW_ALL.setShowBeforeBranch(true);
/* 14 */     SHOW_ALL.setShowBeforeComment(true);
/* 15 */     SHOW_ALL.setShowBeforeImportantMove(true);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\config\ShowBoardRulePrototypes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */