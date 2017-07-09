package com.amicabile.openingtrainer.servlet.webwork.user;

import com.amicabile.openingtrainer.dao.UserDAO;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import java.util.Map;
import net.sf.hibernate.HibernateException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RegisterAction extends ActionSupport {

   private static Logger log = Logger.getLogger(RegisterAction.class.getName());
   private String username;
   private String password;
   private String password2;
   private String email2;
   private String email;


   public String execute() {
      boolean inputOk = true;
      if(StringUtils.isEmpty(this.username)) {
         this.addActionError("Username cannot be empty");
         inputOk = false;
      }

      if(StringUtils.isEmpty(this.password)) {
         this.addActionError("Password cannot be empty");
         inputOk = false;
      }

      if(this.password != null && !this.password.equals(this.password2)) {
         this.addActionError("Passwords were not equal");
         inputOk = false;
      }

      if(inputOk) {
         try {
            UserDAO e = UserDAO.getInstance();
            User user = e.getUser(this.username);
            if(user != null) {
               this.addActionError("User " + this.username + " exists already ");
               inputOk = false;
            } else {
               e.createUser(this.username, this.password, this.email);
               user = e.getUser(this.username);
               Map session = ActionContext.getContext().getSession();
               session.put("user", user);
            }
         } catch (HibernateException var5) {
            log.error("HibernateException in execute", var5);
            this.addActionError("User could not be created : " + var5.getMessage());
         }
      }

      return inputOk?"success":"input";
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword2() {
      return this.password2;
   }

   public void setPassword2(String password2) {
      this.password2 = password2;
   }
}
