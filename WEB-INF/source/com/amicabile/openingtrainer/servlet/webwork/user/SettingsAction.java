package com.amicabile.openingtrainer.servlet.webwork.user;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import com.amicabile.openingtrainer.dao.UserDAO;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import java.util.Map;
import net.sf.hibernate.HibernateException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SettingsAction extends ActionSupport {

   private static Logger log = Logger.getLogger(SettingsAction.class.getName());
   private String password;
   private String password2;
   private String email;
   private boolean showBeforeBranch = false;
   private boolean showBeforeImportantMove = false;
   private boolean showBeforeComment = false;
   private boolean showBigBoard = false;
   private String rulesSet = null;


   public boolean isShowBeforeComment() {
      return this.showBeforeComment;
   }

   public void setShowBeforeComment(boolean showBeforeComment) {
      this.showBeforeComment = showBeforeComment;
   }

   public boolean isShowBeforeBranch() {
      return this.showBeforeBranch;
   }

   public void setShowBeforeBranch(boolean showBeforeBranch) {
      this.showBeforeBranch = showBeforeBranch;
   }

   public boolean isShowBeforeImportantMove() {
      return this.showBeforeImportantMove;
   }

   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) {
      this.showBeforeImportantMove = showBeforeImportantMove;
   }

   public String execute() {
      Map session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      if(user == null) {
         return "login";
      } else if(this.rulesSet == null) {
         return "input";
      } else {
         if(!StringUtils.isEmpty(this.password) && !this.password.equals(this.password2)) {
            this.addActionError("Passwords were not equal");
         } else if(!StringUtils.isEmpty(this.password)) {
            this.addActionMessage("Password has been changed");
            user.setPassword(this.password);
         }

         ShowBoardRule showBoardRule = new ShowBoardRule();
         showBoardRule.setShowBeforeBranch(this.showBeforeBranch);
         showBoardRule.setShowBeforeComment(this.showBeforeComment);
         showBoardRule.setShowBeforeImportantMove(this.showBeforeImportantMove);
         user.setShowBoardRule(showBoardRule);
         user.setShowBigBoard(this.showBigBoard);
         user.setEmail(this.email);
         if(user != null) {
            try {
               UserDAO.getInstance().updateUser(user);
               this.addActionMessage("Settings have been saved");
               return "success";
            } catch (HibernateException var5) {
               log.error("HibernateException in execute", var5);
               return "error";
            }
         } else {
            return "login";
         }
      }
   }

   public String getRulesSet() {
      return this.rulesSet;
   }

   public void setRulesSet(String rulesSet) {
      this.rulesSet = rulesSet;
   }

   public boolean isShowBigBoard() {
      return this.showBigBoard;
   }

   public void setShowBigBoard(boolean showBigBoard) {
      this.showBigBoard = showBigBoard;
   }

   public String getEmail() {
      Map session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      if(user != null) {
         this.email = user.getEmail();
      }

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

   public String getPassword2() {
      return this.password2;
   }

   public void setPassword2(String password2) {
      this.password2 = password2;
   }
}
