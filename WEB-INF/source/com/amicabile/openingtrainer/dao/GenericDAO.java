package com.amicabile.openingtrainer.dao;

import com.amicabile.openingtrainer.util.hibernate.HibernateUtils;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

public class GenericDAO {

   Session createSession() throws HibernateException {
      SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();
      return sessionFactory.openSession();
   }
}
