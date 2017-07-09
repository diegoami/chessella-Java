package com.amicabile.openingtrainer.dao;

import com.amicabile.openingtrainer.dao.GenericDAO;
import com.amicabile.openingtrainer.dao.UserDAO;
import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.amicabile.openingtrainer.model.search.GameSearchCriteria;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import ictk.boardgame.Game;
import ictk.boardgame.GameInfo;
import ictk.boardgame.Player;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.PGNReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class GameDataObjDAO extends GenericDAO {

   private static final int MAX_MINE_RESULT = 500;
   private static final int MAX_LAST_RESULT = 500;
   private static final int MAX_SEARCH_RESULT = 500;
   private static Logger log = Logger.getLogger(GameDataObjDAO.class.getName());
   private static GameDataObjDAO gameDataObjDAO = new GameDataObjDAO();


   public static GameDataObjDAO getInstance() {
      return gameDataObjDAO;
   }

   public GameDataObj getGameDataObj(long gameId) throws HibernateException {
      log.debug("getGameDataObjDAO(" + gameId + ")");
      Session session = this.createSession();

      GameDataObj var6;
      try {
         GameDataObj gameDataObj = new GameDataObj();
         session.load((Object)gameDataObj, Long.valueOf(gameId));
         var6 = gameDataObj;
      } finally {
         session.close();
      }

      return var6;
   }

   public List getGameDataObjForUsername(String username) throws HibernateException {
      log.debug("getGameDataObjForUsername(" + username + ")");
      Session session = this.createSession();

      List var6;
      try {
         Query query = this.createQueryForUsername(username, session);
         query.setMaxResults(500);
         List querylist = query.list();
         if(querylist != null) {
            log.debug("returned size =" + querylist.size());
         }

         var6 = querylist;
      } finally {
         session.close();
      }

      return var6;
   }

   public void deleteGamesDeleted(String username) throws HibernateException {
      List usernameDeletedGames = this.getGameDataObjForUsernameDeleted(username);
      Session session = this.createSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         Iterator var6 = usernameDeletedGames.iterator();

         while(var6.hasNext()) {
            GameDataObj e = (GameDataObj)var6.next();
            session.delete((Object)e);
         }

         tx.commit();
      } catch (Exception var10) {
         if(tx != null) {
            tx.rollback();
         }

         log.error("Exception while deleting games ", var10);
      } finally {
         session.close();
      }

   }

   public List getGameDataObjForUsernameDeleted(String username) throws HibernateException {
      log.debug("getGameDataObjForUsernameDelete(" + username + ")");
      Session session = this.createSession();

      List var6;
      try {
         Query query = this.createQueryForUsernameDeleted(username, session);
         List querylist = query.list();
         if(querylist != null) {
            log.debug("returned size =" + querylist.size());
         }

         var6 = querylist;
      } finally {
         session.close();
      }

      return var6;
   }

   private Query createQueryForUsername(String username, Session session) throws HibernateException {
      Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.GameByUserName");
      query.setString("username", username);
      return query;
   }

   private Query createQueryForUsernameDeleted(String username, Session session) throws HibernateException {
      Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.GameByUserNameDeleted");
      query.setString("username", username);
      return query;
   }

   public List getAllGames() throws HibernateException {
      Session session = this.createSession();
      Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.AllGames");
      query.setMaxResults(500);
      List querylist = query.list();
      this.setAnnotationFlag(querylist);
      session.close();
      return querylist;
   }

   public List getLastGames() throws HibernateException {
      Session session = this.createSession();

      List var5;
      try {
         Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.LastGames");
         query.setMaxResults(500);
         List querylist = query.list();
         this.setAnnotationFlag(querylist);
         var5 = querylist;
      } finally {
         session.close();
      }

      return var5;
   }

   public int getNumGameDataObjForSearchCriteria(GameSearchCriteria gameSearchCriteria) throws HibernateException {
      Session session = this.createSession();

      int var6;
      try {
         Query query = this.createQueryForSearch(gameSearchCriteria, session);
         int numGameDataObj = ((Integer)query.iterate().next()).intValue();
         var6 = numGameDataObj;
      } finally {
         session.close();
      }

      return var6;
   }

   public List getGameDataObjForSearchCriteria(GameSearchCriteria gameSearchCriteria) throws HibernateException {
      Session session = this.createSession();

      List var6;
      try {
         Query query = this.createQueryForSearch(gameSearchCriteria, session);
         query.setMaxResults(500);
         List list = query.list();
         this.setAnnotationFlag(list);
         var6 = list;
      } finally {
         session.close();
      }

      return var6;
   }

   private Query createQueryForSearch(GameSearchCriteria gameSearchCriteria, Session session) throws HibernateException {
      StringBuffer queryString = new StringBuffer();
      boolean conditionFound = false;
      if(gameSearchCriteria.isIgnoreColor()) {
         queryString.append("  ( (lower(white) like lower(:white) or lower(black) like lower(:white)) and ( lower(white) like lower(:black) or lower(black) like lower(:black) )  ) ");
      } else {
         queryString.append(" lower(white) like lower(:white) ");
         queryString.append(" and lower(black) like lower(:black) ");
      }

      if(gameSearchCriteria.getAfterDate() != null) {
         queryString.append(" and date >= :afterDate ");
      }

      if(gameSearchCriteria.getBeforeDate() != null) {
         queryString.append(" and date <= :beforeDate ");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getAfterECO()) && StringUtils.isNotEmpty(gameSearchCriteria.getBeforeECO())) {
         queryString.append(" and eco >= :afterECO ");
         queryString.append(" and eco <= :beforeECO ");
      }

      if(gameSearchCriteria.getBeforeDate() != null) {
         queryString.append(" and date <= :beforeDate ");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getSubmitter())) {
         queryString.append(" and  gameDataObj.user.username like :username ");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getResult())) {
         queryString.append(" and  gameDataObj.result = :result ");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getEvent())) {
         queryString.append(" and  lower(gameDataObj.event) like lower(:event) ");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getSite())) {
         queryString.append(" and  lower(gameDataObj.site) like  lower(:site)");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getTags())) {
         queryString.append(" and  lower(gameDataObj.tags) like  lower(:tags)");
      }

      if (StringUtils.isNotEmpty(gameSearchCriteria.getLoggedinUser())) {
         
      } else {
         queryString.append(" and gameDataObj.publicgame = 1");
      }

      queryString.append(" and gameDataObj.deleted = 0");
      String fromClause = "from com.amicabile.openingtrainer.model.dataobj.GameDataObj gameDataObj where ";
      queryString.insert(0, fromClause);
      Query query = session.createQuery(queryString.toString());
      query.setString("white", '%' + gameSearchCriteria.getWhite() + '%');
      if(gameSearchCriteria.isIgnoreColor()) {
         query.setString("white", '%' + gameSearchCriteria.getWhite() + '%');
      }

      query.setString("black", '%' + gameSearchCriteria.getBlack() + '%');
      if(gameSearchCriteria.isIgnoreColor()) {
         query.setString("black", '%' + gameSearchCriteria.getBlack() + '%');
      }

      if(gameSearchCriteria.getAfterDate() != null) {
         query.setDate("afterDate", gameSearchCriteria.getAfterDate());
      }

      if(gameSearchCriteria.getBeforeDate() != null) {
         query.setDate("beforeDate", gameSearchCriteria.getBeforeDate());
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getAfterECO()) && StringUtils.isNotEmpty(gameSearchCriteria.getBeforeECO())) {
         query.setString("afterECO", gameSearchCriteria.getAfterECO());
         query.setString("beforeECO", gameSearchCriteria.getBeforeECO());
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getSubmitter())) {
         query.setString("username", "%" + gameSearchCriteria.getSubmitter() + "%");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getResult())) {
         query.setString("result", gameSearchCriteria.getResult());
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getEvent())) {
         query.setString("event", "%" + gameSearchCriteria.getEvent() + "%");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getSite())) {
         query.setString("site", "%" + gameSearchCriteria.getSite() + "%");
      }

      if(StringUtils.isNotEmpty(gameSearchCriteria.getTags())) {
         query.setString("tags", "%" + gameSearchCriteria.getTags() + "%");
      }

      return query;
   }

   private void clearListFromGames(List gameList) {
      Iterator var3 = gameList.iterator();

      while(var3.hasNext()) {
         GameDataObj game = (GameDataObj)var3.next();
         game.setPgnstring((String)null);
      }

   }

   private void setAnnotationFlag(List gameList) {
      Iterator var3 = gameList.iterator();

      while(var3.hasNext()) {
         GameDataObj game = (GameDataObj)var3.next();
         if(StringUtils.isEmpty(game.getAnnotator()) && !StringUtils.isEmpty(game.getPgnstring())) {
            game.setAnnotator(game.getPgnstring().contains("{")?"Yes":"");
         }
      }

   }

   private void fillGameDataObj(GameDataObj gameDataObj, Game game) {
      GameInfo gameInfo = game.getGameInfo();
      if(gameInfo != null) {
         Player[] players = gameInfo.getPlayers();
         if(players != null) {
            String date;
            if(players[0] != null) {
               date = players[0].getName();
               gameDataObj.setWhite(date);
            }

            if(players[1] != null) {
               date = players[1].getName();
               gameDataObj.setBlack(date);
            }
         }

         Calendar date1 = gameInfo.getDate();
         if(date1 != null) {
            gameDataObj.setDate(date1.getTime());
         }

         gameDataObj.setEvent(gameInfo.getEvent());
         gameDataObj.setAnnotator(gameInfo.get("Annotator"));
         gameDataObj.setEco(((ChessGameInfo)gameInfo).getECO());
         gameDataObj.setSite(gameInfo.getSite());
         if(gameInfo.getResult() != null) {
            ChessResult chessResult = (ChessResult)gameInfo.getResult();
            gameDataObj.setResult(chessResult.toString());
         }

         gameDataObj.setRound(gameInfo.getRound());
         String chessResult1 = "";
      }

   }

   public Game getGame(GameDataObj gameDataObj) {
      Game game = null;

      try {
         String me = gameDataObj.getPgnstring();
         StringReader sr = new StringReader(me);
         PGNReader reader = new PGNReader(sr);
         game = reader.readGame();
         game.getGameInfo().getAuxilleryProperties().setProperty("GameId", String.valueOf(gameDataObj.getId()));
      } catch (IOException var6) {
         log.error("IOException in getGame", var6);
         var6.printStackTrace();
      } catch (Exception var7) {
         log.error("Exception in getGame", var7);
         var7.printStackTrace();
      }

      return game;
   }

   public ChessGameGroup getGameGroup(String userName) throws HibernateException {
      ChessGameGroup gameGroup = new ChessGameGroup();
      List gameDataObjForUsername = this.getGameDataObjForUsername(userName);
      Iterator var5 = gameDataObjForUsername.iterator();

      while(var5.hasNext()) {
         GameDataObj gameDataObj = (GameDataObj)var5.next();
         gameGroup.addChessGame((ChessGame)this.getGame(gameDataObj));
      }

      return gameGroup;
   }

   public ChessGameGroup getGameGroup(GameSearchCriteria gameSearchCriteria) throws HibernateException {
      ChessGameGroup gameGroup = new ChessGameGroup();
      List gameDataObjForUsername = this.getGameDataObjForSearchCriteria(gameSearchCriteria);
      Iterator var5 = gameDataObjForUsername.iterator();

      while(var5.hasNext()) {
         GameDataObj gameDataObj = (GameDataObj)var5.next();
         gameGroup.addChessGame((ChessGame)this.getGame(gameDataObj));
      }

      return gameGroup;
   }

   public boolean switchPublicStateGame(String username, long gameId) throws HibernateException {
      GameDataObj gameDataObj = this.getGameDataObj(gameId);
      Session session = this.createSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         gameDataObj.setPublicgame(!gameDataObj.isPublicgame());
         if(gameDataObj.getUser().getUsername().equals(username)) {
            log.info("About to switch stat" + gameId);
            session.update(gameDataObj);
            log.info("Game " + gameId + " is now " + gameDataObj.isPublicgame());
         } else {
            log.warn("Game " + gameId + " cannot be modified by user " + username);
         }

         tx.commit();
         boolean var9 = gameDataObj.isPublicgame();
         return var9;
      } catch (Exception var12) {
         if(tx != null) {
            tx.rollback();
         }

         log.error("Exception while deleting game " + gameId, var12);
      } finally {
         session.close();
      }

      return false;
   }

   public boolean switchDeleteGame(String username, long gameId) throws HibernateException {
      GameDataObj gameDataObj = this.getGameDataObj(gameId);
      Session session = this.createSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         gameDataObj.setDeleted(!gameDataObj.isDeleted());
         if(gameDataObj.getUser().getUsername().equals(username)) {
            log.info("About to switch stat" + gameId);
            session.update(gameDataObj);
            log.info("Game " + gameId + " is now " + gameDataObj.isPublicgame());
         } else {
            log.warn("Game " + gameId + " cannot be modified by user " + username);
         }

         tx.commit();
         boolean var9 = gameDataObj.isDeleted();
         return var9;
      } catch (Exception var12) {
         if(tx != null) {
            tx.rollback();
         }

         log.error("Exception while deleting game " + gameId, var12);
      } finally {
         session.close();
      }

      return false;
   }

   public void deleteGame(String username, long gameId) throws HibernateException {
      Session session = this.createSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         GameDataObj e = this.getGameDataObj(gameId);
         if(e.getUser().getUsername().equals(username)) {
            log.info("About to delete " + gameId);
            session.delete((Object)e);
            log.info("Game " + gameId + " was successfully deleted");
         } else {
            log.warn("Game " + gameId + " cannot be deleted by user " + username);
         }

         tx.commit();
      } catch (Exception var10) {
         if(tx != null) {
            tx.rollback();
         }

         log.error("Exception while deleting game " + gameId, var10);
      } finally {
         session.close();
      }

   }

   public int getCountGames() throws HibernateException {
      Session session = this.createSession();

      int var5;
      try {
         Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.CountGames");
         int result = ((Integer)query.uniqueResult()).intValue();
         var5 = result;
      } finally {
         session.close();
      }

      return var5;
   }

   public GameDataObj updateGameDataObj(GameDataObj gameDataObj) throws HibernateException {
      Session session = this.createSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         session.saveOrUpdate(gameDataObj);
         tx.commit();
      } catch (Exception var8) {
         if(tx != null) {
            tx.rollback();
         }
      } finally {
         session.close();
      }

      return gameDataObj;
   }

   public GameDataObj createGameDataObj(String username, String pgnString) throws HibernateException {
      return this.createGameDataObj(username, pgnString, "", true);
   }

   public GameDataObj createGameDataObj(String username, String pgnString, String tag, boolean publicgame) throws HibernateException {
      GameDataObj gameDataObj = null;
      UserDAO userdao = UserDAO.getInstance();
      User user = userdao.getUser(username);
      gameDataObj = new GameDataObj();
      gameDataObj.setPgnstring(pgnString);
      gameDataObj.setUser(user);
      gameDataObj.setTags(tag);
      gameDataObj.setPublicgame(publicgame);
      this.updateAndFillGameDataObj(gameDataObj);
      return gameDataObj;
   }

   public void updateAndFillGameDataObj(GameDataObj gameDataObj) throws HibernateException {
      Game game = this.getGame(gameDataObj);
      this.fillGameDataObj(gameDataObj, game);
      this.updateGameDataObj(gameDataObj);
   }
}
