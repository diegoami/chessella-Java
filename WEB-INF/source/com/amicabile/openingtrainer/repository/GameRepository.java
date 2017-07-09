package com.amicabile.openingtrainer.repository;

import com.amicabile.openingtrainer.dao.GameDataObjDAO;
import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import com.amicabile.openingtrainer.repository.GameSaver;
import com.amicabile.support.LRUCache;
import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Game;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.PGNWriter;
import ictk.boardgame.io.InvalidGameFormatException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import net.sf.hibernate.HibernateException;
import org.apache.log4j.Logger;

public class GameRepository implements GameSaver {

   private static Logger log = Logger.getLogger(GameRepository.class.getName());
   private static final int MAX_GAMES_PER_USER = 5000;
   private static GameRepository gameRepository = new GameRepository();
   private Map gameMap = new LRUCache(200);
   private GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();


   public static GameRepository getInstance() {
      return gameRepository;
   }

   public boolean switchPublicStateGame(String username, long gameId) {
      try {
         return this.gameDataObjDAO.switchPublicStateGame(username, gameId);
      } catch (HibernateException var5) {
         log.error("State of game for " + username + " could not be switched ", var5);
         var5.printStackTrace();
         return false;
      }
   }

   public boolean switchDeleteStateGame(String username, long gameId) {
      try {
         return this.gameDataObjDAO.switchDeleteGame(username, gameId);
      } catch (HibernateException var5) {
         log.error("State of game for " + username + " could not be switched ", var5);
         var5.printStackTrace();
         return false;
      }
   }

   public void deleteGame(String username, long gameId) {
      try {
         this.gameDataObjDAO.deleteGame(username, gameId);
      } catch (HibernateException var5) {
         log.error("Game for " + username + " could not be deleted", var5);
         var5.printStackTrace();
      }

   }

   public boolean canAddGames(String username) {
      return true;
   }

   public long addGame(Game vgame, String username) {
      return this.addGame(vgame, username, "", true);
   }

   public long addGame(Game vgame, String username, String tag, boolean publicgame) {
      long boardNumber = 0L;
      StringWriter sw = new StringWriter();
      PGNWriter writer = new PGNWriter(sw);

      try {
         writer.writeGame(vgame);
         String e = sw.toString();
         GameDataObj gameDataObj = this.createGameObj(username, e, tag, publicgame);
         boardNumber = gameDataObj.getId().longValue();
         log.info("Successfully added game " + boardNumber + " in repository");
      } catch (HibernateException var11) {
         log.error("HibernateException in addGame", var11);
      } catch (IOException var12) {
         log.error("IOException in addGame", var12);
      }

      return boardNumber;
   }

   public GameDataObj createGameObj(String username, String pgnstring, String tag, boolean publicgame) throws HibernateException {
      GameDataObj gameDataObj = this.gameDataObjDAO.createGameDataObj(username, pgnstring, tag, publicgame);
      return gameDataObj;
   }

   public GameDataObj updateAndFillGameDataObj(GameDataObj gameDataObj) throws HibernateException {
      this.gameDataObjDAO.updateAndFillGameDataObj(gameDataObj);
      this.gameMap.remove(gameDataObj.getId());
      System.out.println("UPDATING " + gameDataObj.getId());
      return gameDataObj;
   }

   public Game getGame(long argBoard) {
      Game game = null;

      try {
         if(this.gameMap.containsKey(Long.valueOf(argBoard))) {
            game = (Game)this.gameMap.get(Long.valueOf(argBoard));
         } else {
            GameDataObj me = this.getGameDataObj(argBoard);
            game = this.getGameForGameDataObj(argBoard, me);
            this.gameMap.put(Long.valueOf(argBoard), game);
         }
      } catch (IOException var5) {
         log.error("IOException in getGame", var5);
      } catch (Exception var6) {
         log.error("Exception in getGame", var6);
      }

      return game;
   }

   public GameDataObj getGameDataObj(long argBoard) throws HibernateException {
      try {
         GameDataObj e = this.gameDataObjDAO.getGameDataObj(argBoard);
         return e;
      } catch (HibernateException var4) {
         log.error("HibernateException in getGameDataObj(" + argBoard + ")", var4);
         return null;
      }
   }

   public Game getGameForGameDataObj(long argBoard, GameDataObj gameDataObj) throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException {
      String pgnstring = gameDataObj.getPgnstring();
      StringReader sr = new StringReader(pgnstring);
      PGNReader reader = new PGNReader(sr);
      Game game = reader.readGame();
      game.getGameInfo().getAuxilleryProperties().setProperty("GameId", String.valueOf(argBoard));
      return game;
   }
}
