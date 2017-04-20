/*    */ package com.amicabile.openingtrainer.util.hibernate;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ import net.sf.hibernate.SessionFactory;
/*    */ import net.sf.hibernate.cfg.Configuration;
/*    */ 
/*    */ public class HibernateUtils
/*    */ {
/*    */   private HibernateUtils()
/*    */   {
/*    */     try
/*    */     {
/* 14 */       init();
/*    */     } catch (Exception e) {
/* 16 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/* 20 */   private static HibernateUtils hibernateUtils = new HibernateUtils();
/*    */   
/*    */   public static HibernateUtils getInstance() {
/* 23 */     return hibernateUtils;
/*    */   }
/*    */   
/* 26 */   private SessionFactory sessionFactory = null;
/*    */   
/*    */   public void init() throws net.sf.hibernate.MappingException, HibernateException
/*    */   {
/* 30 */     Configuration config = new Configuration();
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 35 */     config.addClass(GameDataObj.class);
/* 36 */     config.addClass(com.amicabile.openingtrainer.model.dataobj.User.class);
/*    */     
/* 38 */     this.sessionFactory = config.buildSessionFactory();
/*    */   }
/*    */   
/*    */   public void finalize()
/*    */   {
/*    */     try {
/* 44 */       this.sessionFactory.close();
/*    */     } catch (Exception e) {
/* 46 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public SessionFactory getSessionFactory()
/*    */   {
/* 52 */     return this.sessionFactory;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\util\hibernate\HibernateUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */