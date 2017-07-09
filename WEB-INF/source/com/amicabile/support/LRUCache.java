package com.amicabile.support;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache extends LinkedHashMap {

   protected int maxsize;


   public LRUCache(int maxsize) {
      super(maxsize * 4 / 3 + 1, 0.75F, true);
      this.maxsize = maxsize;
   }

   protected boolean removeEldestEntry(Entry eldest) {
      return this.size() > this.maxsize;
   }
}
