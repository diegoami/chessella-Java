package com.amicabile.openingtrainer.dwr;

import com.amicabile.openingtrainer.model.dataobj.User;
import com.amicabile.openingtrainer.repository.GameRepository;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import uk.ltd.getahead.dwr.ExecutionContext;

public class GameTableSetter {

   private static Logger log = Logger.getLogger(GameTableSetter.class.getName());


   public String deleteGameById(int id) {
      HttpServletRequest request = ExecutionContext.get().getHttpServletRequest();
      String resultSucc = "<IMG SRC=\'deleted.gif\' BORDER=0 >";
      String resultFailed = "<IMG SRC=\'delete.gif\' BORDER=0 >";
      User user = (User)request.getSession().getAttribute("user");
      if(user != null) {
         String username = user.getUsername();

         try {
            GameRepository.getInstance().deleteGame(username, (long)id);
            log.info("Successfully executed deleteGameById");
            return resultSucc;
         } catch (Exception var8) {
            log.error("Exception in deleteGameById", var8);
            return resultFailed;
         }
      } else {
         return resultFailed;
      }
   }

   public String switchGameStateById(int id) {
      HttpServletRequest request = ExecutionContext.get().getHttpServletRequest();
      User user = (User)request.getSession().getAttribute("user");
      if(user != null) {
         String username = user.getUsername();

         try {
            boolean he = GameRepository.getInstance().switchPublicStateGame(username, (long)id);
            log.info("Successfully executed switchPublicStateGame");
            return he?"<IMG SRC=\'public_co.gif\' BORDER=0 >":"<IMG SRC=\'private_co.gif\' BORDER=0 >";
         } catch (Exception var6) {
            log.error("Exception in switchPublicStateGame", var6);
            return "";
         }
      } else {
         return "";
      }
   }

   public String switchDeleteStateById(int id) {
      HttpServletRequest request = ExecutionContext.get().getHttpServletRequest();
      User user = (User)request.getSession().getAttribute("user");
      if(user != null) {
         String username = user.getUsername();

         try {
            boolean he = GameRepository.getInstance().switchDeleteStateGame(username, (long)id);
            log.info("Successfully executed switchDeleteStateGame");
            return he?"<IMG SRC=\'deleted.gif\' BORDER=0 >":"<IMG SRC=\'delete.gif\' BORDER=0 >";
         } catch (Exception var6) {
            log.error("Exception in switchPublicStateGame", var6);
            return "";
         }
      } else {
         return "";
      }
   }
}
