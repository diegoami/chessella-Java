/*     */ package com.amicabile.openingtrainer.servlet.webwork.user;
/*     */ 
/*     */ import com.amicabile.openingtrainer.dao.UserDAO;
/*     */ import com.amicabile.openingtrainer.model.dataobj.User;
/*     */ import com.opensymphony.xwork.ActionContext;
/*     */ import com.opensymphony.xwork.ActionSupport;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegisterAction
/*     */   extends ActionSupport
/*     */ {
/*  18 */   private static Logger log = Logger.getLogger(RegisterAction.class.getName());
/*     */   
/*     */   private String username;
/*     */   private String password;
/*     */   private String password2;
/*     */   private String email2;
/*     */   private String email;
/*     */   
/*     */   public String execute()
/*     */   {
/*  28 */     boolean inputOk = true;
/*  29 */     if (StringUtils.isEmpty(this.username)) {
/*  30 */       addActionError("Username cannot be empty");
/*  31 */       inputOk = false;
/*     */     }
/*  33 */     if (StringUtils.isEmpty(this.password)) {
/*  34 */       addActionError("Password cannot be empty");
/*  35 */       inputOk = false;
/*     */     }
/*     */     
/*  38 */     if ((this.password != null) && (!this.password.equals(this.password2))) {
/*  39 */       addActionError("Passwords were not equal");
/*  40 */       inputOk = false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */     if (inputOk) {
/*     */       try {
/*  54 */         UserDAO userDao = UserDAO.getInstance();
/*  55 */         User user = userDao.getUser(this.username);
/*  56 */         if (user != null) {
/*  57 */           addActionError("User " + this.username + " exists already ");
/*  58 */           inputOk = false;
/*     */         } else {
/*  60 */           userDao.createUser(this.username, this.password, this.email);
/*  61 */           user = userDao.getUser(this.username);
/*     */           
/*  63 */           Map session = ActionContext.getContext().getSession();
/*  64 */           session.put("user", user);
/*     */         }
/*     */       }
/*     */       catch (HibernateException e) {
/*  68 */         log.error("HibernateException in execute", e);
/*  69 */         addActionError("User could not be created : " + e.getMessage());
/*     */       }
/*     */     }
/*  72 */     if (inputOk) {
/*  73 */       return "success";
/*     */     }
/*  75 */     return "input";
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  79 */     return this.email;
/*     */   }
/*     */   
/*  82 */   public void setEmail(String email) { this.email = email; }
/*     */   
/*     */   public String getPassword() {
/*  85 */     return this.password;
/*     */   }
/*     */   
/*  88 */   public void setPassword(String password) { this.password = password; }
/*     */   
/*     */   public String getUsername() {
/*  91 */     return this.username;
/*     */   }
/*     */   
/*  94 */   public void setUsername(String username) { this.username = username; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPassword2()
/*     */   {
/* 103 */     return this.password2;
/*     */   }
/*     */   
/* 106 */   public void setPassword2(String password2) { this.password2 = password2; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\user\RegisterAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */