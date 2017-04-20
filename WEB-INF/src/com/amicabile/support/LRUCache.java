/*    */ package com.amicabile.support;
/*    */ 
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ public class LRUCache<K, V> extends java.util.LinkedHashMap<K, V>
/*    */ {
/*    */   protected int maxsize;
/*    */   
/*    */   public LRUCache(int maxsize) {
/* 10 */     super(maxsize * 4 / 3 + 1, 0.75F, true);
/* 11 */     this.maxsize = maxsize;
/*    */   }
/*    */   
/*    */   protected boolean removeEldestEntry(Map.Entry eldest) {
/* 15 */     return size() > this.maxsize;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\LRUCache.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */