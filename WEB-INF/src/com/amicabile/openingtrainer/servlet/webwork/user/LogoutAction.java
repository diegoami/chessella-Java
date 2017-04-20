/*    */ package com.amicabile.openingtrainer.servlet.webwork.user;
/*    */ 
/*    */ import com.opensymphony.webwork.ServletActionContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class LogoutAction extends com.opensymphony.xwork.ActionSupport
/*    */ {
/*    */   public String execute()
/*    */   {
/* 11 */     HttpSession session = ServletActionContext.getRequest().getSession();
/* 12 */     session.invalidate();
/*    */     
/*    */ 
/*    */ 
/* 16 */     return "success";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\user\LogoutAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */