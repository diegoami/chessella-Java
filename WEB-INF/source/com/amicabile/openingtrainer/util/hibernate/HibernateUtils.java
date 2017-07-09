package com.amicabile.openingtrainer.util.hibernate;

import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import com.amicabile.openingtrainer.model.dataobj.User;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

public class HibernateUtils {

   private static HibernateUtils hibernateUtils = new HibernateUtils();
   private SessionFactory sessionFactory = null;


   private HibernateUtils() {
      try {
         this.init();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static HibernateUtils getInstance() {
      return hibernateUtils;
   }

   public void init() throws MappingException, HibernateException {
      Configuration config = new Configuration();
      config.addClass(GameDataObj.class);
      config.addClass(User.class);
      this.sessionFactory = config.buildSessionFactory();
   }

   public void finalize() {
      try {
         this.sessionFactory.close();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public SessionFactory getSessionFactory() {
      return this.sessionFactory;
   }
}
