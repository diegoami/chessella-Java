package com.amicabile.openingtrainer.model.paging;

import java.util.List;

public class GameListWrapper {

   private List gameList;
   private int totalGameCount;


   public int getTotalGameCount() {
      return this.totalGameCount;
   }

   public void setTotalGameCount(int totalGameCount) {
      this.totalGameCount = totalGameCount;
   }

   public List getGameList() {
      return this.gameList;
   }

   public void setGameList(List gameList) {
      this.gameList = gameList;
   }
}
