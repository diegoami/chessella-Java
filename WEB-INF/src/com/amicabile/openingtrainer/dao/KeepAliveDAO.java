/*    */ package com.amicabile.openingtrainer.dao;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ 
/*    */ 
/*    */ public class KeepAliveDAO
/*    */   extends GenericDAO
/*    */   implements Runnable
/*    */ {
/*    */   private static final int PING_TIME = 900000;
/* 12 */   private static KeepAliveDAO keepAliveDAO = new KeepAliveDAO();
/*    */   
/*    */   public static KeepAliveDAO getInstance() {
/* 15 */     return keepAliveDAO;
/*    */   }
/*    */   
/*    */ 
/* 19 */   public void doPingSelect() throws HibernateException { GameDataObjDAO.getInstance().getCountGames(); }
/*    */   
/*    */   public static void init() {
/* 22 */     System.out.println("INITIALIZING KEEP ALIVE DAO");
/* 23 */     new Thread(getInstance()).start();
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/*    */     try {
/*    */       for (;;) {
/* 30 */         Thread.currentThread();Thread.sleep(900000L);
/* 31 */         doPingSelect();
/*    */       }
/* 33 */     } catch (Exception e) { e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dao\KeepAliveDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */