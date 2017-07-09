package com.amicabile.openingtrainer.servlet.webwork.user;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;
import javax.servlet.http.HttpSession;

public class LogoutAction extends ActionSupport {

   public String execute() {
      HttpSession session = ServletActionContext.getRequest().getSession();
      session.invalidate();
      return "success";
   }
}
