/*     */ package com.amicabile.openingtrainer.repository;
/*     */ 
/*     */ import com.amicabile.openingtrainer.dao.GameDataObjDAO;
/*     */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*     */ import com.amicabile.support.LRUCache;
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.chess.io.PGNReader;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.io.InvalidGameFormatException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class GameRepository
/*     */   implements GameSaver
/*     */ {
/*  26 */   private static Logger log = Logger.getLogger(GameRepository.class.getName());
/*     */   
/*     */   private static final int MAX_GAMES_PER_USER = 5000;
/*  29 */   private static GameRepository gameRepository = new GameRepository();
/*  30 */   private Map<Long, Game> gameMap = new LRUCache(200);
/*     */   
/*  32 */   private GameDataObjDAO gameDataObjDAO = GameDataObjDAO.getInstance();
/*     */   
/*     */   public static GameRepository getInstance() {
/*  35 */     return gameRepository;
/*     */   }
/*     */   
/*     */   public boolean switchPublicStateGame(String username, long gameId)
/*     */   {
/*     */     try
/*     */     {
/*  42 */       return this.gameDataObjDAO.switchPublicStateGame(username, gameId);
/*     */     } catch (HibernateException e) {
/*  44 */       log.error("State of game for " + username + " could not be switched ", e);
/*  45 */       e.printStackTrace();
/*     */     }
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   public boolean switchDeleteStateGame(String username, long gameId) {
/*     */     try {
/*  52 */       return this.gameDataObjDAO.switchDeleteGame(username, gameId);
/*     */     } catch (HibernateException e) {
/*  54 */       log.error("State of game for " + username + " could not be switched ", e);
/*  55 */       e.printStackTrace();
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public void deleteGame(String username, long gameId)
/*     */   {
/*     */     try {
/*  63 */       this.gameDataObjDAO.deleteGame(username, gameId);
/*     */     } catch (HibernateException e) {
/*  65 */       log.error("Game for " + username + " could not be deleted", e);
/*  66 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canAddGames(String username)
/*     */   {
/*  72 */     return true;
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
/*     */   public long addGame(Game vgame, String username)
/*     */   {
/*  86 */     return addGame(vgame, username, "", true);
/*     */   }
/*     */   
/*     */   public long addGame(Game vgame, String username, String tag, boolean publicgame) {
/*  90 */     long boardNumber = 0L;
/*  91 */     StringWriter sw = new StringWriter();
/*  92 */     PGNWriter writer = new PGNWriter(sw);
/*     */     try
/*     */     {
/*  95 */       writer.writeGame(vgame);
/*  96 */       String pgnstring = sw.toString();
/*  97 */       GameDataObj gameDataObj = createGameObj(username, pgnstring, tag, publicgame);
/*     */       
/*     */ 
/* 100 */       boardNumber = gameDataObj.getId().longValue();
/* 101 */       log.info("Successfully added game " + boardNumber + " in repository");
/*     */     }
/*     */     catch (HibernateException e) {
/* 104 */       log.error("HibernateException in addGame", e);
/*     */     } catch (IOException e) {
/* 106 */       log.error("IOException in addGame", e);
/*     */     }
/*     */     
/*     */ 
/* 110 */     return boardNumber;
/*     */   }
/*     */   
/*     */   public GameDataObj createGameObj(String username, String pgnstring, String tag, boolean publicgame) throws HibernateException {
/* 114 */     GameDataObj gameDataObj = this.gameDataObjDAO.createGameDataObj(username, pgnstring, tag, publicgame);
/* 115 */     return gameDataObj;
/*     */   }
/*     */   
/*     */   public GameDataObj updateAndFillGameDataObj(GameDataObj gameDataObj) throws HibernateException
/*     */   {
/* 120 */     this.gameDataObjDAO.updateAndFillGameDataObj(gameDataObj);
/* 121 */     this.gameMap.remove(gameDataObj.getId());
/* 122 */     System.out.println("UPDATING " + gameDataObj.getId());
/* 123 */     return gameDataObj;
/*     */   }
/*     */   
/*     */   public Game getGame(long argBoard)
/*     */   {
/* 128 */     Game game = null;
/*     */     
/*     */     try
/*     */     {
/* 132 */       if (this.gameMap.containsKey(Long.valueOf(argBoard))) {
/* 133 */         game = (Game)this.gameMap.get(Long.valueOf(argBoard));
/*     */       } else {
/* 135 */         GameDataObj gameDataObj = getGameDataObj(argBoard);
/* 136 */         game = getGameForGameDataObj(argBoard, gameDataObj);
/* 137 */         this.gameMap.put(Long.valueOf(argBoard), game);
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 141 */       log.error("IOException in getGame", e);
/*     */     } catch (Exception me) {
/* 143 */       log.error("Exception in getGame", me);
/*     */     }
/*     */     
/* 146 */     return game;
/*     */   }
/*     */   
/*     */   public GameDataObj getGameDataObj(long argBoard) throws HibernateException
/*     */   {
/*     */     try {
/* 152 */       return this.gameDataObjDAO.getGameDataObj(argBoard);
/*     */     }
/*     */     catch (HibernateException e) {
/* 155 */       log.error("HibernateException in getGameDataObj(" + argBoard + ")", e);
/*     */     }
/*     */     
/* 158 */     return null;
/*     */   }
/*     */   
/*     */   public Game getGameForGameDataObj(long argBoard, GameDataObj gameDataObj) throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException
/*     */   {
/* 163 */     String pgnstring = gameDataObj.getPgnstring();
/*     */     
/* 165 */     StringReader sr = new StringReader(pgnstring);
/* 166 */     PGNReader reader = new PGNReader(sr);
/* 167 */     Game game = reader.readGame();
/* 168 */     game.getGameInfo().getAuxilleryProperties().setProperty(
/* 169 */       "GameId", 
/* 170 */       String.valueOf(argBoard));
/*     */     
/* 172 */     return game;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\repository\GameRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */