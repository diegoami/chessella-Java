/*     */ package com.amicabile.openingtrainer.servlet.webwork.games;
/*     */ 
/*     */ import com.amicabile.openingtrainer.dao.GameDataObjDAO;
/*     */ import com.amicabile.openingtrainer.dao.UserDAO;
/*     */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*     */ import com.amicabile.openingtrainer.model.dataobj.User;
/*     */ import com.amicabile.openingtrainer.model.search.GameSearchCriteria;
/*     */ import com.opensymphony.xwork.ActionContext;
/*     */ import com.opensymphony.xwork.ActionSupport;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class ListGamesAction
/*     */   extends ActionSupport
/*     */ {
/*  22 */   private static Logger log = Logger.getLogger(ListGamesAction.class.getName());
/*     */   private int gamesCount;
/*     */   
/*     */   public int getGamesCount()
/*     */   {
/*  27 */     return this.gamesCount;
/*     */   }
/*     */   
/*     */   public void setGamesCount(int gamesCount) {
/*  31 */     this.gamesCount = gamesCount;
/*     */   }
/*     */   
/*     */   public boolean isOnlyMine() {
/*  35 */     Map session = ActionContext.getContext().getSession();
/*  36 */     return session.get("onlyMine") != null;
/*     */   }
/*     */   
/*     */   public void setOnlyMine(boolean onlyMine) {
/*  40 */     Map session = ActionContext.getContext().getSession();
/*     */     
/*  42 */     if (onlyMine) {
/*  43 */       session.put("onlyMine", new Boolean(true));
/*     */     } else {
/*  45 */       session.remove("onlyMine");
/*     */     }
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getGameList() {
/*  50 */     return this.gameList;
/*     */   }
/*     */   
/*     */   public List<Long> getGameIds() {
/*  54 */     Map session = ActionContext.getContext().getSession();
/*  55 */     List<Long> gameIds = (List)session.get("gameIds");
/*  56 */     return gameIds;
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getGames()
/*     */   {
/*  61 */     Map session = ActionContext.getContext().getSession();
/*  62 */     List<GameDataObj> games = (List)session.get("games");
/*  63 */     return games;
/*     */   }
/*     */   
/*     */   public boolean isExistsGamesDeleted()
/*     */   {
/*  68 */     List<GameDataObj> myGames = getGames();
/*  69 */     boolean existGamesDeleted = false;
/*  70 */     if (myGames != null) {
/*  71 */       for (GameDataObj gameDataObj : myGames) {
/*  72 */         if (gameDataObj.isDeleted()) {
/*  73 */           existGamesDeleted = true;
/*  74 */           break;
/*     */         }
/*     */       }
/*     */     }
/*  78 */     return existGamesDeleted;
/*     */   }
/*     */   
/*     */ 
/*     */   public String deletedGames()
/*     */   {
/*  84 */     Map session = ActionContext.getContext().getSession();
/*  85 */     User user = (User)session.get("user");
/*  86 */     if (user != null) {
/*  87 */       GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();
/*     */       try {
/*  89 */         gameDataObjDAO.deleteGamesDeleted(user.getUsername());
/*  90 */         return "success";
/*     */       } catch (HibernateException e) {
/*  92 */         log.error("Exception in listForUsername", e);
/*  93 */         return "error";
/*     */       }
/*     */     }
/*  96 */     return "login";
/*     */   }
/*     */   
/*     */   public List<Long> getGameIdList()
/*     */   {
/* 101 */     List<Long> resultLong = new ArrayList();
/*     */     
/* 103 */     if (this.gameList == null) {
/* 104 */       return resultLong;
/*     */     }
/*     */     
/* 107 */     StringBuffer resultStringBuffer = new StringBuffer("");
/* 108 */     for (GameDataObj gameDataObj : this.gameList) {
/* 109 */       resultLong.add(gameDataObj.getId());
/* 110 */       if (!"".equals(resultStringBuffer)) {
/* 111 */         resultStringBuffer.append(",");
/*     */       }
/* 113 */       resultStringBuffer.append(gameDataObj.getId());
/*     */     }
/* 115 */     return resultLong;
/*     */   }
/*     */   
/*     */   public void setGameList(List<GameDataObj> gameList) {
/* 119 */     this.gameList = gameList;
/*     */   }
/*     */   
/* 122 */   private List<GameDataObj> gameList = new ArrayList();
/*     */   
/*     */ 
/* 125 */   private GameSearchCriteria gameSearchCriteria = new GameSearchCriteria();
/*     */   
/*     */   public GameSearchCriteria getGameSearchCriteria() {
/* 128 */     return this.gameSearchCriteria;
/*     */   }
/*     */   
/*     */ 
/*     */   public String initSearch()
/*     */   {
/* 134 */     UserDAO userDAO = UserDAO.getInstance();
/* 135 */     GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();
/* 136 */     List<User> allUsersList = new ArrayList();
/*     */     try
/*     */     {
/* 139 */       this.gamesCount = gameDataObjDAO.getCountGames();
/*     */       
/* 141 */       allUsersList = userDAO.getAllSubmitters();
/*     */     } catch (HibernateException e) {
/* 143 */       log.error("Exception in getAllUsers", e);
/* 144 */       e.printStackTrace();
/*     */     }
/*     */     
/* 147 */     this.allUsers = new LinkedHashMap();
/* 148 */     this.allUsers.put("", "");
/* 149 */     for (User user : allUsersList) {
/* 150 */       this.allUsers.put(user.getUsername(), user.getUsername());
/*     */     }
/*     */     
/* 153 */     return "success";
/*     */   }
/*     */   
/*     */ 
/* 157 */   private Map<String, String> allUsers = new LinkedHashMap();
/*     */   
/*     */   public Map<String, String> getAllUsers()
/*     */   {
/*     */     String str;
/* 162 */     for (Iterator localIterator = this.allUsers.values().iterator(); localIterator.hasNext(); str = (String)localIterator.next()) {}
/*     */     
/*     */ 
/* 165 */     return this.allUsers;
/*     */   }
/*     */   
/*     */ 
/*     */   public String searchByCriteria()
/*     */   {
/* 171 */     initSearch();
/* 172 */     Map session = ActionContext.getContext().getSession();
/* 173 */     User user = (User)session.get("user");
/* 174 */     if (user != null) {
/* 175 */       this.gameSearchCriteria.setLoggedinUser(user.getUsername());
/*     */     } else {
/* 177 */       this.gameSearchCriteria.setLoggedinUser(null);
/*     */     }
/*     */     
/* 180 */     if (this.gameSearchCriteria.getSearchValid() != null) {
/* 181 */       if (this.gameSearchCriteria.isEnough()) {
/*     */         try {
/* 183 */           this.gameList = GameDataObjDAO.getInstance().getGameDataObjForSearchCriteria(this.gameSearchCriteria);
/* 184 */           this.gameSearchCriteria.setSearchValid(null);
/* 185 */           return "success";
/*     */         } catch (HibernateException e) {
/* 187 */           log.error("Exception in searchByCriteria", e);
/* 188 */           return "error";
/*     */         }
/*     */       }
/* 191 */       addActionError("Not enough search criteria");
/* 192 */       return "input";
/*     */     }
/*     */     
/* 195 */     return "input";
/*     */   }
/*     */   
/*     */   public String listForUsername()
/*     */   {
/* 200 */     Map session = ActionContext.getContext().getSession();
/* 201 */     setOnlyMine(true);
/*     */     
/*     */ 
/* 204 */     User user = (User)session.get("user");
/*     */     try {
/* 206 */       if (user != null) {
/* 207 */         String username = user.getUsername();
/* 208 */         this.gameList = GameDataObjDAO.getInstance().getGameDataObjForUsername(username);
/*     */       }
/*     */       else {
/* 211 */         this.gameList = GameDataObjDAO.getInstance().getLastGames();
/*     */       }
/*     */       
/* 214 */       return "success";
/*     */     }
/*     */     catch (HibernateException e) {
/* 217 */       log.error("Exception in listForUsername", e); }
/* 218 */     return "error";
/*     */   }
/*     */   
/*     */ 
/*     */   public String listLastGames()
/*     */   {
/* 224 */     Map session = ActionContext.getContext().getSession();
/*     */     
/*     */     try
/*     */     {
/* 228 */       this.gameList = GameDataObjDAO.getInstance().getLastGames();
/*     */       
/* 230 */       return "success";
/*     */     }
/*     */     catch (HibernateException e) {
/* 233 */       log.error("Exception in listLastGames", e); }
/* 234 */     return "error";
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String listSessionGames()
/*     */   {
/* 253 */     Map session = ActionContext.getContext().getSession();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */     if (session.get("user") != null) {
/* 260 */       return listForUsername();
/*     */     }
/*     */     
/* 263 */     return listLastGames();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\games\ListGamesAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */