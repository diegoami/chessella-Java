package com.amicabile.openingtrainer.controller;

import com.amicabile.openingtrainer.controller.MoveTreeFiller;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.support.LRUCache;
import ictk.boardgame.Game;
import java.util.Map;

public class MoveTreePool {

   private static MoveTreePool moveTreePool = new MoveTreePool();
   private Map moveTreeMap = new LRUCache(200);


   public static MoveTreePool getInstance() {
      return moveTreePool;
   }

   public void clearMoveTree(long boardNumber) {
      this.moveTreeMap.remove(Long.valueOf(boardNumber));
   }

   public VelocityMoveTree retrieveMoveTree(Game game, long boardNumber) {
      if(this.moveTreeMap.containsKey(Long.valueOf(boardNumber))) {
         VelocityMoveTree filler1 = (VelocityMoveTree)this.moveTreeMap.get(Long.valueOf(boardNumber));
         return filler1;
      } else {
         MoveTreeFiller filler = new MoveTreeFiller();
         VelocityMoveTree retrieveMoveTree = filler.retrieveMoveTree(game);
         this.moveTreeMap.put(Long.valueOf(boardNumber), retrieveMoveTree);
         return retrieveMoveTree;
      }
   }

   public VelocityMoveTree retrieveMoveTree(Game game) {
      MoveTreeFiller filler = new MoveTreeFiller();
      VelocityMoveTree retrieveMoveTree = filler.retrieveMoveTree(game);
      return retrieveMoveTree;
   }
}
