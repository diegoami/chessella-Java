/*    */ package com.amicabile.openingtrainer.controller;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import com.amicabile.support.LRUCache;
/*    */ import ictk.boardgame.Game;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoveTreePool
/*    */ {
/* 12 */   private static MoveTreePool moveTreePool = new MoveTreePool();
/*    */   
/*    */   public static MoveTreePool getInstance() {
/* 15 */     return moveTreePool;
/*    */   }
/*    */   
/*    */ 
/* 19 */   private Map<Long, VelocityMoveTree> moveTreeMap = new LRUCache(200);
/*    */   
/*    */   public void clearMoveTree(long boardNumber) {
/* 22 */     this.moveTreeMap.remove(Long.valueOf(boardNumber));
/*    */   }
/*    */   
/*    */   public VelocityMoveTree retrieveMoveTree(Game game, long boardNumber) {
/* 26 */     if (this.moveTreeMap.containsKey(Long.valueOf(boardNumber))) {
/* 27 */       VelocityMoveTree velocityMoveTree = (VelocityMoveTree)this.moveTreeMap.get(Long.valueOf(boardNumber));
/*    */       
/* 29 */       return velocityMoveTree;
/*    */     }
/* 31 */     MoveTreeFiller filler = new MoveTreeFiller();
/* 32 */     VelocityMoveTree retrieveMoveTree = filler.retrieveMoveTree(game);
/* 33 */     this.moveTreeMap.put(Long.valueOf(boardNumber), retrieveMoveTree);
/*    */     
/* 35 */     return retrieveMoveTree;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public VelocityMoveTree retrieveMoveTree(Game game)
/*    */   {
/* 43 */     MoveTreeFiller filler = new MoveTreeFiller();
/* 44 */     VelocityMoveTree retrieveMoveTree = filler.retrieveMoveTree(game);
/*    */     
/* 46 */     return retrieveMoveTree;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\MoveTreePool.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */