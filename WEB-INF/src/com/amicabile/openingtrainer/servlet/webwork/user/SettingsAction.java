/*     */ package com.amicabile.openingtrainer.servlet.webwork.user;
/*     */ 
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRule;
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
/*     */ public class SettingsAction
/*     */   extends ActionSupport
/*     */ {
/*  18 */   private static Logger log = Logger.getLogger(SettingsAction.class.getName());
/*     */   
/*     */   private String password;
/*     */   
/*     */   private String password2;
/*     */   private String email;
/*  24 */   private boolean showBeforeBranch = false;
/*  25 */   private boolean showBeforeImportantMove = false;
/*  26 */   private boolean showBeforeComment = false;
/*  27 */   private boolean showBigBoard = false;
/*     */   
/*  29 */   private String rulesSet = null;
/*     */   
/*     */   public boolean isShowBeforeComment() {
/*  32 */     return this.showBeforeComment;
/*     */   }
/*     */   
/*  35 */   public void setShowBeforeComment(boolean showBeforeComment) { this.showBeforeComment = showBeforeComment; }
/*     */   
/*     */   public boolean isShowBeforeBranch() {
/*  38 */     return this.showBeforeBranch;
/*     */   }
/*     */   
/*  41 */   public void setShowBeforeBranch(boolean showBeforeBranch) { this.showBeforeBranch = showBeforeBranch; }
/*     */   
/*     */   public boolean isShowBeforeImportantMove() {
/*  44 */     return this.showBeforeImportantMove;
/*     */   }
/*     */   
/*  47 */   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) { this.showBeforeImportantMove = showBeforeImportantMove; }
/*     */   
/*     */   public String execute()
/*     */   {
/*  51 */     Map session = ActionContext.getContext().getSession();
/*  52 */     User user = (User)session.get("user");
/*  53 */     if (user == null) {
/*  54 */       return "login";
/*     */     }
/*     */     
/*  57 */     if (this.rulesSet == null) {
/*  58 */       return "input";
/*     */     }
/*     */     
/*  61 */     if ((!StringUtils.isEmpty(this.password)) && (!this.password.equals(this.password2))) {
/*  62 */       addActionError("Passwords were not equal");
/*     */     }
/*  64 */     else if (!StringUtils.isEmpty(this.password)) {
/*  65 */       addActionMessage("Password has been changed");
/*  66 */       user.setPassword(this.password);
/*     */     }
/*     */     
/*     */ 
/*  70 */     ShowBoardRule showBoardRule = new ShowBoardRule();
/*  71 */     showBoardRule.setShowBeforeBranch(this.showBeforeBranch);
/*  72 */     showBoardRule.setShowBeforeComment(this.showBeforeComment);
/*  73 */     showBoardRule.setShowBeforeImportantMove(this.showBeforeImportantMove);
/*     */     
/*  75 */     user.setShowBoardRule(showBoardRule);
/*  76 */     user.setShowBigBoard(this.showBigBoard);
/*  77 */     user.setEmail(this.email);
/*  78 */     if (user != null) {
/*     */       try {
/*  80 */         UserDAO.getInstance().updateUser(user);
/*  81 */         addActionMessage("Settings have been saved");
/*  82 */         return "success";
/*     */       } catch (HibernateException e) {
/*  84 */         log.error("HibernateException in execute", e);
/*  85 */         return "error";
/*     */       }
/*     */     }
/*  88 */     return "login";
/*     */   }
/*     */   
/*     */   public String getRulesSet() {
/*  92 */     return this.rulesSet;
/*     */   }
/*     */   
/*  95 */   public void setRulesSet(String rulesSet) { this.rulesSet = rulesSet; }
/*     */   
/*     */   public boolean isShowBigBoard() {
/*  98 */     return this.showBigBoard;
/*     */   }
/*     */   
/* 101 */   public void setShowBigBoard(boolean showBigBoard) { this.showBigBoard = showBigBoard; }
/*     */   
/*     */   public String getEmail() {
/* 104 */     Map session = ActionContext.getContext().getSession();
/*     */     
/* 106 */     User user = (User)session.get("user");
/* 107 */     if (user != null) {
/* 108 */       this.email = user.getEmail();
/*     */     }
/*     */     
/* 111 */     return this.email;
/*     */   }
/*     */   
/* 114 */   public void setEmail(String email) { this.email = email; }
/*     */   
/*     */   public String getPassword()
/*     */   {
/* 118 */     return this.password;
/*     */   }
/*     */   
/* 121 */   public void setPassword(String password) { this.password = password; }
/*     */   
/*     */   public String getPassword2() {
/* 124 */     return this.password2;
/*     */   }
/*     */   
/* 127 */   public void setPassword2(String password2) { this.password2 = password2; }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\user\SettingsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */