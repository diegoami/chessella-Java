package com.amicabile.openingtrainer.config;

import com.amicabile.openingtrainer.config.ShowBoardRule;

public class ShowBoardRulePrototypes {

   public static ShowBoardRule SHOW_BEFORE_IMPORTANT_MOVE = new ShowBoardRule();
   public static ShowBoardRule SHOW_ALL = new ShowBoardRule();
   public static ShowBoardRule DEFAULT_RULE = SHOW_ALL;
   public static ShowBoardRule NO_SHOW = new ShowBoardRule();


   static {
      SHOW_BEFORE_IMPORTANT_MOVE.setShowBeforeImportantMove(true);
      SHOW_ALL.setShowBeforeBranch(true);
      SHOW_ALL.setShowBeforeComment(true);
      SHOW_ALL.setShowBeforeImportantMove(true);
   }

}
