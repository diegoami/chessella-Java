/*    */ package com.amicabile.openingtrainer.servlet.webwork.user;
/*    */ 
/*    */ import com.amicabile.openingtrainer.dao.UserDAO;
/*    */ import com.amicabile.openingtrainer.model.dataobj.User;
/*    */ import com.opensymphony.xwork.ActionContext;
/*    */ import com.opensymphony.xwork.ActionSupport;
/*    */ import com.opensymphony.xwork.validator.ValidationException;
/*    */ import java.util.Map;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginAction
/*    */   extends ActionSupport
/*    */ {
/* 18 */   private static Logger log = Logger.getLogger(LoginAction.class.getName());
/*    */   private String username;
/*    */   private String password;
/*    */   
/*    */   public String getPassword()
/*    */   {
/* 24 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 28 */     this.password = password;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 32 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 36 */     this.username = username;
/*    */   }
/*    */   
/*    */   public boolean isValid() throws ValidationException {
/* 40 */     boolean inputOk = true;
/* 41 */     if (this.username == null) {
/* 42 */       inputOk = false;
/*    */     }
/* 44 */     return inputOk;
/*    */   }
/*    */   
/*    */   public String execute() {
/* 48 */     if (StringUtils.isEmpty(this.username)) {
/* 49 */       addActionError("Username is empty");
/* 50 */       return "input";
/*    */     }
/* 52 */     if (StringUtils.isEmpty(this.password)) {
/* 53 */       addActionError("Password is empty");
/* 54 */       return "input";
/*    */     }
/*    */     try
/*    */     {
/* 58 */       UserDAO userDao = UserDAO.getInstance();
/* 59 */       User user = userDao.getUser(this.username);
/* 60 */       if (user == null) {
/* 61 */         addActionError("User " + this.username + " not found ");
/* 62 */         return "input";
/*    */       }
/* 64 */       user.getPassword();
/* 65 */       if (this.password.equals(user.getPassword())) {
/* 66 */         Map session = ActionContext.getContext().getSession();
/* 67 */         session.put("user", user);
/* 68 */         return "success";
/*    */       }
/* 70 */       addActionError("Wrong password");
/* 71 */       return "input";
/*    */     }
/*    */     catch (HibernateException e)
/*    */     {
/* 75 */       log.error("HibernateException in execute", e);
/* 76 */       addActionError("User could not be logged in: " + e.getMessage()); }
/* 77 */     return "error";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\user\LoginAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */