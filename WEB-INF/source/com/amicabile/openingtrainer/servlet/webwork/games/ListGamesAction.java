package com.amicabile.openingtrainer.servlet.webwork.games;

import com.amicabile.openingtrainer.dao.GameDataObjDAO;
import com.amicabile.openingtrainer.dao.UserDAO;
import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.amicabile.openingtrainer.model.search.GameSearchCriteria;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.hibernate.HibernateException;
import org.apache.log4j.Logger;

public class ListGamesAction extends ActionSupport {

   private static Logger log = Logger.getLogger(ListGamesAction.class.getName());
   private int gamesCount;
   private List gameList = new ArrayList();
   private GameSearchCriteria gameSearchCriteria = new GameSearchCriteria();
   private Map allUsers = new LinkedHashMap();


   public int getGamesCount() {
      return this.gamesCount;
   }

   public void setGamesCount(int gamesCount) {
      this.gamesCount = gamesCount;
   }

   public boolean isOnlyMine() {
      Map session = ActionContext.getContext().getSession();
      return session.get("onlyMine") != null;
   }

   public void setOnlyMine(boolean onlyMine) {
      Map session = ActionContext.getContext().getSession();
      if(onlyMine) {
         session.put("onlyMine", new Boolean(true));
      } else {
         session.remove("onlyMine");
      }

   }

   public List getGameList() {
      return this.gameList;
   }

   public List getGameIds() {
      Map session = ActionContext.getContext().getSession();
      List gameIds = (List)((List)session.get("gameIds"));
      return gameIds;
   }

   public List getGames() {
      Map session = ActionContext.getContext().getSession();
      List games = (List)((List)session.get("games"));
      return games;
   }

   public boolean isExistsGamesDeleted() {
      List myGames = this.getGames();
      boolean existGamesDeleted = false;
      if(myGames != null) {
         Iterator var4 = myGames.iterator();

         while(var4.hasNext()) {
            GameDataObj gameDataObj = (GameDataObj)var4.next();
            if(gameDataObj.isDeleted()) {
               existGamesDeleted = true;
               break;
            }
         }
      }

      return existGamesDeleted;
   }

   public String deletedGames() {
      Map session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      if(user != null) {
         GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();

         try {
            gameDataObjDAO.deleteGamesDeleted(user.getUsername());
            return "success";
         } catch (HibernateException var5) {
            log.error("Exception in listForUsername", var5);
            return "error";
         }
      } else {
         return "login";
      }
   }

   public List getGameIdList() {
      ArrayList resultLong = new ArrayList();
      if(this.gameList == null) {
         return resultLong;
      } else {
         StringBuffer resultStringBuffer = new StringBuffer("");

         GameDataObj gameDataObj;
         for(Iterator var4 = this.gameList.iterator(); var4.hasNext(); resultStringBuffer.append(gameDataObj.getId())) {
            gameDataObj = (GameDataObj)var4.next();
            resultLong.add(gameDataObj.getId());
            if(!"".equals(resultStringBuffer)) {
               resultStringBuffer.append(",");
            }
         }

         return resultLong;
      }
   }

   public void setGameList(List gameList) {
      this.gameList = gameList;
   }

   public GameSearchCriteria getGameSearchCriteria() {
      return this.gameSearchCriteria;
   }

   public String initSearch() {
      UserDAO userDAO = UserDAO.getInstance();
      GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();
      Object allUsersList = new ArrayList();

      try {
         this.gamesCount = gameDataObjDAO.getCountGames();
         allUsersList = userDAO.getAllSubmitters();
      } catch (HibernateException var6) {
         log.error("Exception in getAllUsers", var6);
         var6.printStackTrace();
      }

      this.allUsers = new LinkedHashMap();
      this.allUsers.put("", "");
      Iterator var5 = ((List)allUsersList).iterator();

      while(var5.hasNext()) {
         User user = (User)var5.next();
         this.allUsers.put(user.getUsername(), user.getUsername());
      }

      return "success";
   }

   public Map getAllUsers() {
      String var1;
      for(Iterator var2 = this.allUsers.values().iterator(); var2.hasNext(); var1 = (String)var2.next()) {
         ;
      }

      return this.allUsers;
   }

   public String searchByCriteria() {
      this.initSearch();
      Map session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      if(user != null) {
         this.gameSearchCriteria.setLoggedinUser(user.getUsername());
      } else {
         this.gameSearchCriteria.setLoggedinUser((String)null);
      }

      if(this.gameSearchCriteria.getSearchValid() != null) {
         if(this.gameSearchCriteria.isEnough()) {
            try {
               this.gameList = GameDataObjDAO.getInstance().getGameDataObjForSearchCriteria(this.gameSearchCriteria);
               this.gameSearchCriteria.setSearchValid((String)null);
               return "success";
            } catch (HibernateException var4) {
               log.error("Exception in searchByCriteria", var4);
               return "error";
            }
         } else {
            this.addActionError("Not enough search criteria");
            return "input";
         }
      } else {
         return "input";
      }
   }

   public String listForUsername() {
      Map session = ActionContext.getContext().getSession();
      this.setOnlyMine(true);
      User user = (User)session.get("user");

      try {
         if(user != null) {
            String e = user.getUsername();
            this.gameList = GameDataObjDAO.getInstance().getGameDataObjForUsername(e);
         } else {
            this.gameList = GameDataObjDAO.getInstance().getLastGames();
         }

         return "success";
      } catch (HibernateException var4) {
         log.error("Exception in listForUsername", var4);
         return "error";
      }
   }

   public String listLastGames() {
      Map session = ActionContext.getContext().getSession();

      try {
         this.gameList = GameDataObjDAO.getInstance().getLastGames();
         return "success";
      } catch (HibernateException var3) {
         log.error("Exception in listLastGames", var3);
         return "error";
      }
   }

   public String listSessionGames() {
      Map session = ActionContext.getContext().getSession();
      return session.get("user") != null?this.listForUsername():this.listLastGames();
   }
}
