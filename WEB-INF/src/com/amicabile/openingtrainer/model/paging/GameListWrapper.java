/*    */ package com.amicabile.openingtrainer.model.paging;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameListWrapper
/*    */ {
/*    */   private List<GameDataObj> gameList;
/*    */   private int totalGameCount;
/*    */   
/*    */   public int getTotalGameCount()
/*    */   {
/* 15 */     return this.totalGameCount;
/*    */   }
/*    */   
/*    */   public void setTotalGameCount(int totalGameCount) {
/* 19 */     this.totalGameCount = totalGameCount;
/*    */   }
/*    */   
/*    */   public List<GameDataObj> getGameList() {
/* 23 */     return this.gameList;
/*    */   }
/*    */   
/*    */   public void setGameList(List<GameDataObj> gameList) {
/* 27 */     this.gameList = gameList;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\paging\GameListWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */