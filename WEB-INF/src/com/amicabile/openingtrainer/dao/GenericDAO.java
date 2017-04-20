/*    */ package com.amicabile.openingtrainer.dao;
/*    */ 
/*    */ import com.amicabile.openingtrainer.util.hibernate.HibernateUtils;
/*    */ import net.sf.hibernate.Session;
/*    */ import net.sf.hibernate.SessionFactory;
/*    */ 
/*    */ public class GenericDAO
/*    */ {
/*    */   Session createSession() throws net.sf.hibernate.HibernateException
/*    */   {
/* 11 */     SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();
/* 12 */     return sessionFactory.openSession();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dao\GenericDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */