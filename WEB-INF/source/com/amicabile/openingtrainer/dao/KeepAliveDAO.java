package com.amicabile.openingtrainer.dao;

import com.amicabile.openingtrainer.dao.GameDataObjDAO;
import com.amicabile.openingtrainer.dao.GenericDAO;
import net.sf.hibernate.HibernateException;

public class KeepAliveDAO extends GenericDAO implements Runnable {

   private static final int PING_TIME = 900000;
   private static KeepAliveDAO keepAliveDAO = new KeepAliveDAO();


   public static KeepAliveDAO getInstance() {
      return keepAliveDAO;
   }

   public void doPingSelect() throws HibernateException {
      GameDataObjDAO.getInstance().getCountGames();
   }

   public static void init() {
      System.out.println("INITIALIZING KEEP ALIVE DAO");
      (new Thread(getInstance())).start();
   }

   public void run() {
      while(true) {
         try {
            while(true) {
               Thread.currentThread();
               Thread.sleep(900000L);
               this.doPingSelect();
            }
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }
   }
}
