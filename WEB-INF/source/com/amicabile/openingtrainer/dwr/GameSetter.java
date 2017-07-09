package com.amicabile.openingtrainer.dwr;

import com.amicabile.openingtrainer.dao.GameDataObjDAO;
import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import net.sf.hibernate.HibernateException;
import org.apache.log4j.Logger;

public class GameSetter {

   private static Logger log = Logger.getLogger(GameSetter.class.getName());


   public String setTagFor(long gameId, String tags) {
      GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();

      try {
         GameDataObj he = gameDataObjDAO.getGameDataObj(gameId);
         he.setTags(tags);
         gameDataObjDAO.updateGameDataObj(he);
         return "SUCCESS";
      } catch (HibernateException var6) {
         log.error(var6);
         return "FAILURE";
      }
   }
}
