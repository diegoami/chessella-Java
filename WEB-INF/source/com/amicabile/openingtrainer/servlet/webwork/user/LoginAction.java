package com.amicabile.openingtrainer.servlet.webwork.user;

import com.amicabile.openingtrainer.dao.UserDAO;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import com.opensymphony.xwork.validator.ValidationException;
import java.util.Map;
import net.sf.hibernate.HibernateException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class LoginAction extends ActionSupport {

   private static Logger log = Logger.getLogger(LoginAction.class.getName());
   private String username;
   private String password;


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

   public boolean isValid() throws ValidationException {
      boolean inputOk = true;
      if(this.username == null) {
         inputOk = false;
      }

      return inputOk;
   }

   public String execute() {
      if(StringUtils.isEmpty(this.username)) {
         this.addActionError("Username is empty");
         return "input";
      } else if(StringUtils.isEmpty(this.password)) {
         this.addActionError("Password is empty");
         return "input";
      } else {
         try {
            UserDAO e = UserDAO.getInstance();
            User user = e.getUser(this.username);
            if(user == null) {
               this.addActionError("User " + this.username + " not found ");
               return "input";
            } else {
               user.getPassword();
               if(this.password.equals(user.getPassword())) {
                  Map session = ActionContext.getContext().getSession();
                  session.put("user", user);
                  return "success";
               } else {
                  this.addActionError("Wrong password");
                  return "input";
               }
            }
         } catch (HibernateException var4) {
            log.error("HibernateException in execute", var4);
            this.addActionError("User could not be logged in: " + var4.getMessage());
            return "error";
         }
      }
   }
}
