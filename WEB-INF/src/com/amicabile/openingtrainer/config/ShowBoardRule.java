/*    */ package com.amicabile.openingtrainer.config;
/*    */ 
/*    */ 
/*    */ public class ShowBoardRule
/*    */ {
/*    */   private boolean showBeforeBranch;
/*    */   
/*    */   private boolean showBeforeImportantMove;
/*    */   
/*    */   private boolean showBeforeComment;
/*    */   
/*    */   public boolean isShowBeforeComment()
/*    */   {
/* 14 */     return this.showBeforeComment;
/*    */   }
/*    */   
/* 17 */   public void setShowBeforeComment(boolean showBeforeComment) { this.showBeforeComment = showBeforeComment; }
/*    */   
/*    */   public boolean isShowBeforeBranch() {
/* 20 */     return this.showBeforeBranch;
/*    */   }
/*    */   
/* 23 */   public void setShowBeforeBranch(boolean showBeforeBranch) { this.showBeforeBranch = showBeforeBranch; }
/*    */   
/*    */   public boolean isShowBeforeImportantMove() {
/* 26 */     return this.showBeforeImportantMove;
/*    */   }
/*    */   
/* 29 */   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) { this.showBeforeImportantMove = showBeforeImportantMove; }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\config\ShowBoardRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */