/*    */ package com.amicabile.openingtrainer.dwr;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.dataobj.User;
/*    */ import com.amicabile.openingtrainer.repository.GameRepository;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.log4j.Logger;
/*    */ import uk.ltd.getahead.dwr.ExecutionContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameTableSetter
/*    */ {
/* 15 */   private static Logger log = Logger.getLogger(GameTableSetter.class.getName());
/*    */   
/*    */   public String deleteGameById(int id)
/*    */   {
/* 19 */     HttpServletRequest request = ExecutionContext.get()
/* 20 */       .getHttpServletRequest();
/* 21 */     String resultSucc = "<IMG SRC='deleted.gif' BORDER=0 >";
/* 22 */     String resultFailed = "<IMG SRC='delete.gif' BORDER=0 >";
/* 23 */     User user = (User)request.getSession().getAttribute("user");
/*    */     
/* 25 */     if (user != null) {
/* 26 */       String username = user.getUsername();
/*    */       try
/*    */       {
/* 29 */         GameRepository.getInstance().deleteGame(username, id);
/* 30 */         log.info("Successfully executed deleteGameById");
/* 31 */         return resultSucc;
/*    */       }
/*    */       catch (Exception he)
/*    */       {
/* 35 */         log.error("Exception in deleteGameById", he);
/*    */       }
/*    */     } else {
/* 38 */       return resultFailed;
/*    */     }
/* 40 */     return resultFailed;
/*    */   }
/*    */   
/*    */   public String switchGameStateById(int id)
/*    */   {
/* 45 */     HttpServletRequest request = ExecutionContext.get()
/* 46 */       .getHttpServletRequest();
/* 47 */     User user = (User)request.getSession().getAttribute("user");
/* 48 */     if (user != null) {
/* 49 */       String username = user.getUsername();
/*    */       try
/*    */       {
/* 52 */         boolean publicGame = GameRepository.getInstance().switchPublicStateGame(username, id);
/* 53 */         log.info("Successfully executed switchPublicStateGame");
/* 54 */         if (publicGame) {
/* 55 */           return "<IMG SRC='public_co.gif' BORDER=0 >";
/*    */         }
/* 57 */         return "<IMG SRC='private_co.gif' BORDER=0 >";
/*    */       }
/*    */       catch (Exception he)
/*    */       {
/* 61 */         log.error("Exception in switchPublicStateGame", he);
/* 62 */         return "";
/*    */       }
/*    */     }
/* 65 */     return "";
/*    */   }
/*    */   
/*    */ 
/*    */   public String switchDeleteStateById(int id)
/*    */   {
/* 71 */     HttpServletRequest request = ExecutionContext.get()
/* 72 */       .getHttpServletRequest();
/* 73 */     User user = (User)request.getSession().getAttribute("user");
/* 74 */     if (user != null) {
/* 75 */       String username = user.getUsername();
/*    */       try
/*    */       {
/* 78 */         boolean deleted = GameRepository.getInstance().switchDeleteStateGame(username, id);
/* 79 */         log.info("Successfully executed switchDeleteStateGame");
/* 80 */         if (deleted) {
/* 81 */           return "<IMG SRC='deleted.gif' BORDER=0 >";
/*    */         }
/* 83 */         return "<IMG SRC='delete.gif' BORDER=0 >";
/*    */       }
/*    */       catch (Exception he)
/*    */       {
/* 87 */         log.error("Exception in switchPublicStateGame", he);
/* 88 */         return "";
/*    */       }
/*    */     }
/* 91 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dwr\GameTableSetter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */