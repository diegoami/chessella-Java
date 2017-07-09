package com.amicabile.openingtrainer.config;


public class ShowBoardRule {

   private boolean showBeforeBranch;
   private boolean showBeforeImportantMove;
   private boolean showBeforeComment;


   public boolean isShowBeforeComment() {
      return this.showBeforeComment;
   }

   public void setShowBeforeComment(boolean showBeforeComment) {
      this.showBeforeComment = showBeforeComment;
   }

   public boolean isShowBeforeBranch() {
      return this.showBeforeBranch;
   }

   public void setShowBeforeBranch(boolean showBeforeBranch) {
      this.showBeforeBranch = showBeforeBranch;
   }

   public boolean isShowBeforeImportantMove() {
      return this.showBeforeImportantMove;
   }

   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) {
      this.showBeforeImportantMove = showBeforeImportantMove;
   }
}
