package com.amicabile.openingtrainer.servlet;

import com.amicabile.openingtrainer.dao.KeepAliveDAO;
import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class QuartzServlet extends GenericServlet {

   public void init(ServletConfig config) throws ServletException {
      KeepAliveDAO.init();
   }

   public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {}

   public String getServletInfo() {
      return null;
   }
}
