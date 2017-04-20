/*    */ package com.amicabile.openingtrainer.dwr;
/*    */ 
/*    */ import com.amicabile.openingtrainer.dao.GameDataObjDAO;
/*    */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameSetter
/*    */ {
/* 12 */   private static Logger log = Logger.getLogger(GameSetter.class.getName());
/*    */   
/*    */   public String setTagFor(long gameId, String tags) {
/* 15 */     GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();
/*    */     try {
/* 17 */       GameDataObj gameDataObj = gameDataObjDAO.getGameDataObj(gameId);
/* 18 */       gameDataObj.setTags(tags);
/*    */       
/* 20 */       gameDataObjDAO.updateGameDataObj(gameDataObj);
/*    */     } catch (HibernateException he) {
/* 22 */       log.error(he);
/* 23 */       return "FAILURE";
/*    */     }
/* 25 */     return "SUCCESS";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dwr\GameSetter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */