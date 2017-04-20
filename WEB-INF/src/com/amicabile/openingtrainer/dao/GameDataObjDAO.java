/*     */ package com.amicabile.openingtrainer.dao;
/*     */ 
/*     */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*     */ import com.amicabile.openingtrainer.model.dataobj.User;
/*     */ import com.amicabile.openingtrainer.model.search.GameSearchCriteria;
/*     */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.Player;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.chess.io.PGNReader;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.Transaction;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameDataObjDAO
/*     */   extends GenericDAO
/*     */ {
/*     */   private static final int MAX_MINE_RESULT = 500;
/*     */   private static final int MAX_LAST_RESULT = 500;
/*     */   private static final int MAX_SEARCH_RESULT = 500;
/*  39 */   private static Logger log = Logger.getLogger(GameDataObjDAO.class.getName());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  44 */   private static GameDataObjDAO gameDataObjDAO = new GameDataObjDAO();
/*     */   
/*     */   public static GameDataObjDAO getInstance() {
/*  47 */     return gameDataObjDAO;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GameDataObj getGameDataObj(long gameId)
/*     */     throws HibernateException
/*     */   {
/*  59 */     log.debug("getGameDataObjDAO(" + gameId + ")");
/*  60 */     Session session = createSession();
/*     */     try {
/*  62 */       GameDataObj gameDataObj = new GameDataObj();
/*  63 */       session.load(gameDataObj, Long.valueOf(gameId));
/*  64 */       return gameDataObj;
/*     */     }
/*     */     finally {
/*  67 */       session.close();
/*     */     }
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
/*     */ 
/*     */ 
/*     */   public List<GameDataObj> getGameDataObjForUsername(String username)
/*     */     throws HibernateException
/*     */   {
/*  90 */     log.debug("getGameDataObjForUsername(" + username + ")");
/*     */     
/*  92 */     Session session = createSession();
/*     */     try {
/*  94 */       Query query = createQueryForUsername(username, session);
/*  95 */       query.setMaxResults(500);
/*  96 */       List<GameDataObj> querylist = query.list();
/*  97 */       if (querylist != null) {
/*  98 */         log.debug("returned size =" + querylist.size());
/*     */       }
/*     */       
/*     */ 
/* 102 */       return querylist;
/*     */     }
/*     */     finally {
/* 105 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void deleteGamesDeleted(String username)
/*     */     throws HibernateException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual 140	com/amicabile/openingtrainer/dao/GameDataObjDAO:getGameDataObjForUsernameDeleted	(Ljava/lang/String;)Ljava/util/List;
/*     */     //   5: astore_2
/*     */     //   6: aload_0
/*     */     //   7: invokevirtual 71	com/amicabile/openingtrainer/dao/GameDataObjDAO:createSession	()Lnet/sf/hibernate/Session;
/*     */     //   10: astore_3
/*     */     //   11: aconst_null
/*     */     //   12: astore 4
/*     */     //   14: aload_3
/*     */     //   15: invokeinterface 143 1 0
/*     */     //   20: astore 4
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface 147 1 0
/*     */     //   28: astore 6
/*     */     //   30: goto +23 -> 53
/*     */     //   33: aload 6
/*     */     //   35: invokeinterface 151 1 0
/*     */     //   40: checkcast 75	com/amicabile/openingtrainer/model/dataobj/GameDataObj
/*     */     //   43: astore 5
/*     */     //   45: aload_3
/*     */     //   46: aload 5
/*     */     //   48: invokeinterface 157 2 0
/*     */     //   53: aload 6
/*     */     //   55: invokeinterface 160 1 0
/*     */     //   60: ifne -27 -> 33
/*     */     //   63: aload 4
/*     */     //   65: invokeinterface 164 1 0
/*     */     //   70: goto +42 -> 112
/*     */     //   73: astore 5
/*     */     //   75: aload 4
/*     */     //   77: ifnull +10 -> 87
/*     */     //   80: aload 4
/*     */     //   82: invokeinterface 169 1 0
/*     */     //   87: getstatic 30	com/amicabile/openingtrainer/dao/GameDataObjDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   90: ldc -84
/*     */     //   92: aload 5
/*     */     //   94: invokevirtual 174	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   97: goto +25 -> 122
/*     */     //   100: astore 7
/*     */     //   102: aload_3
/*     */     //   103: invokeinterface 90 1 0
/*     */     //   108: pop
/*     */     //   109: aload 7
/*     */     //   111: athrow
/*     */     //   112: aload_3
/*     */     //   113: invokeinterface 90 1 0
/*     */     //   118: pop
/*     */     //   119: goto +10 -> 129
/*     */     //   122: aload_3
/*     */     //   123: invokeinterface 90 1 0
/*     */     //   128: pop
/*     */     //   129: return
/*     */     // Line number table:
/*     */     //   Java source line #111	-> byte code offset #0
/*     */     //   Java source line #112	-> byte code offset #6
/*     */     //   Java source line #113	-> byte code offset #11
/*     */     //   Java source line #115	-> byte code offset #14
/*     */     //   Java source line #117	-> byte code offset #22
/*     */     //   Java source line #118	-> byte code offset #45
/*     */     //   Java source line #117	-> byte code offset #53
/*     */     //   Java source line #120	-> byte code offset #63
/*     */     //   Java source line #121	-> byte code offset #73
/*     */     //   Java source line #122	-> byte code offset #75
/*     */     //   Java source line #123	-> byte code offset #80
/*     */     //   Java source line #125	-> byte code offset #87
/*     */     //   Java source line #127	-> byte code offset #100
/*     */     //   Java source line #128	-> byte code offset #102
/*     */     //   Java source line #129	-> byte code offset #109
/*     */     //   Java source line #128	-> byte code offset #112
/*     */     //   Java source line #129	-> byte code offset #119
/*     */     //   Java source line #128	-> byte code offset #122
/*     */     //   Java source line #130	-> byte code offset #129
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	130	0	this	GameDataObjDAO
/*     */     //   0	130	1	username	String
/*     */     //   5	18	2	usernameDeletedGames	List<GameDataObj>
/*     */     //   10	113	3	session	Session
/*     */     //   12	69	4	tx	Transaction
/*     */     //   43	4	5	game	GameDataObj
/*     */     //   73	20	5	e	Exception
/*     */     //   28	26	6	localIterator	Iterator
/*     */     //   100	10	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   14	70	73	java/lang/Exception
/*     */     //   14	100	100	finally
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getGameDataObjForUsernameDeleted(String username)
/*     */     throws HibernateException
/*     */   {
/* 134 */     log.debug("getGameDataObjForUsernameDelete(" + username + ")");
/*     */     
/* 136 */     Session session = createSession();
/*     */     try {
/* 138 */       Query query = createQueryForUsernameDeleted(username, session);
/* 139 */       List<GameDataObj> querylist = query.list();
/* 140 */       if (querylist != null) {
/* 141 */         log.debug("returned size =" + querylist.size());
/*     */       }
/*     */       
/*     */ 
/* 145 */       return querylist;
/*     */     }
/*     */     finally {
/* 148 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private Query createQueryForUsername(String username, Session session)
/*     */     throws HibernateException
/*     */   {
/* 155 */     Query query = session
/* 156 */       .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.GameByUserName");
/* 157 */     query.setString("username", username);
/* 158 */     return query;
/*     */   }
/*     */   
/*     */   private Query createQueryForUsernameDeleted(String username, Session session) throws HibernateException
/*     */   {
/* 163 */     Query query = session
/* 164 */       .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.GameByUserNameDeleted");
/* 165 */     query.setString("username", username);
/* 166 */     return query;
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getAllGames() throws HibernateException {
/* 170 */     Session session = createSession();
/*     */     
/* 172 */     Query query = session
/* 173 */       .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.AllGames");
/* 174 */     query.setMaxResults(500);
/* 175 */     List<GameDataObj> querylist = query.list();
/*     */     
/* 177 */     setAnnotationFlag(querylist);
/*     */     
/* 179 */     session.close();
/* 180 */     return querylist;
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getLastGames() throws HibernateException {
/* 184 */     Session session = createSession();
/*     */     try
/*     */     {
/* 187 */       Query query = session
/* 188 */         .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.LastGames");
/* 189 */       query.setMaxResults(500);
/*     */       
/* 191 */       List<GameDataObj> querylist = query.list();
/*     */       
/*     */ 
/* 194 */       setAnnotationFlag(querylist);
/*     */       
/* 196 */       return querylist;
/*     */     }
/*     */     finally {
/* 199 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getNumGameDataObjForSearchCriteria(GameSearchCriteria gameSearchCriteria) throws HibernateException
/*     */   {
/* 205 */     Session session = createSession();
/*     */     try
/*     */     {
/* 208 */       Query query = createQueryForSearch(gameSearchCriteria, session);
/*     */       
/* 210 */       int numGameDataObj = ((Integer)query.iterate().next()).intValue();
/* 211 */       return numGameDataObj;
/*     */     } finally {
/* 213 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public List<GameDataObj> getGameDataObjForSearchCriteria(GameSearchCriteria gameSearchCriteria)
/*     */     throws HibernateException
/*     */   {
/* 220 */     Session session = createSession();
/*     */     
/*     */     try
/*     */     {
/* 224 */       Query query = createQueryForSearch(gameSearchCriteria, session);
/* 225 */       query.setMaxResults(500);
/*     */       
/* 227 */       List<GameDataObj> list = query.list();
/*     */       
/* 229 */       setAnnotationFlag(list);
/* 230 */       return list;
/*     */     } finally {
/* 232 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private Query createQueryForSearch(GameSearchCriteria gameSearchCriteria, Session session)
/*     */     throws HibernateException
/*     */   {
/* 239 */     StringBuffer queryString = new StringBuffer();
/* 240 */     boolean conditionFound = false;
/* 241 */     if (gameSearchCriteria.isIgnoreColor())
/*     */     {
/* 243 */       queryString.append("  ( (lower(white) like lower(:white) or lower(black) like lower(:white)) and ( lower(white) like lower(:black) or lower(black) like lower(:black) )  ) ");
/*     */     } else {
/* 245 */       queryString.append(" lower(white) like lower(:white) ");
/* 246 */       queryString.append(" and lower(black) like lower(:black) ");
/*     */     }
/*     */     
/* 249 */     if (gameSearchCriteria.getAfterDate() != null) {
/* 250 */       queryString.append(" and date >= :afterDate ");
/*     */     }
/*     */     
/* 253 */     if (gameSearchCriteria.getBeforeDate() != null) {
/* 254 */       queryString.append(" and date <= :beforeDate ");
/*     */     }
/* 256 */     if ((StringUtils.isNotEmpty(gameSearchCriteria.getAfterECO())) && 
/* 257 */       (StringUtils.isNotEmpty(gameSearchCriteria.getBeforeECO()))) {
/* 258 */       queryString.append(" and eco >= :afterECO ");
/* 259 */       queryString.append(" and eco <= :beforeECO ");
/*     */     }
/*     */     
/* 262 */     if (gameSearchCriteria.getBeforeDate() != null) {
/* 263 */       queryString.append(" and date <= :beforeDate ");
/*     */     }
/* 265 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getSubmitter()))
/*     */     {
/* 267 */       queryString.append(" and  gameDataObj.user.username like :username ");
/*     */     }
/*     */     
/* 270 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getResult())) {
/* 271 */       queryString.append(" and  gameDataObj.result = :result ");
/*     */     }
/*     */     
/* 274 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getEvent()))
/*     */     {
/* 276 */       queryString.append(" and  lower(gameDataObj.event) like lower(:event) ");
/*     */     }
/*     */     
/* 279 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getSite()))
/*     */     {
/* 281 */       queryString.append(" and  lower(gameDataObj.site) like  lower(:site)");
/*     */     }
/*     */     
/* 284 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getTags()))
/*     */     {
/* 286 */       queryString.append(" and  lower(gameDataObj.tags) like  lower(:tags)");
/*     */     }
/*     */     
/* 289 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getLoggedinUser())) {
/* 290 */       queryString.append(" and ( gameDataObj.publicgame = 1 or  gameDataObj.user.username like :loggedinuser )");
/*     */     }
/*     */     else {
/* 293 */       queryString.append(" and gameDataObj.publicgame = 1");
/*     */     }
/* 295 */     queryString.append(" and gameDataObj.deleted = 0");
/*     */     
/* 297 */     String fromClause = "from com.amicabile.openingtrainer.model.dataobj.GameDataObj gameDataObj where ";
/*     */     
/* 299 */     queryString.insert(0, fromClause);
/* 300 */     Query query = session.createQuery(queryString.toString());
/*     */     
/* 302 */     query.setString("white", '%' + gameSearchCriteria.getWhite() + '%');
/* 303 */     if (gameSearchCriteria.isIgnoreColor()) {
/* 304 */       query.setString("white", '%' + gameSearchCriteria.getWhite() + '%');
/*     */     }
/*     */     
/* 307 */     query.setString("black", '%' + gameSearchCriteria.getBlack() + '%');
/*     */     
/* 309 */     if (gameSearchCriteria.isIgnoreColor()) {
/* 310 */       query.setString("black", '%' + gameSearchCriteria.getBlack() + '%');
/*     */     }
/*     */     
/* 313 */     if (gameSearchCriteria.getAfterDate() != null) {
/* 314 */       query.setDate("afterDate", gameSearchCriteria.getAfterDate());
/*     */     }
/*     */     
/* 317 */     if (gameSearchCriteria.getBeforeDate() != null) {
/* 318 */       query.setDate("beforeDate", gameSearchCriteria.getBeforeDate());
/*     */     }
/*     */     
/* 321 */     if ((StringUtils.isNotEmpty(gameSearchCriteria.getAfterECO())) && 
/* 322 */       (StringUtils.isNotEmpty(gameSearchCriteria.getBeforeECO()))) {
/* 323 */       query.setString("afterECO", gameSearchCriteria.getAfterECO());
/* 324 */       query.setString("beforeECO", gameSearchCriteria.getBeforeECO());
/*     */     }
/*     */     
/* 327 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getSubmitter())) {
/* 328 */       query.setString("username", "%" + gameSearchCriteria.getSubmitter() + 
/* 329 */         "%");
/*     */     }
/*     */     
/* 332 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getResult())) {
/* 333 */       query.setString("result", gameSearchCriteria.getResult());
/*     */     }
/*     */     
/* 336 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getLoggedinUser())) {
/* 337 */       query.setString("loggedinuser", gameSearchCriteria.getLoggedinUser());
/*     */     }
/*     */     
/*     */ 
/* 341 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getEvent())) {
/* 342 */       query.setString("event", "%" + gameSearchCriteria.getEvent() + "%");
/*     */     }
/*     */     
/* 345 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getSite())) {
/* 346 */       query.setString("site", "%" + gameSearchCriteria.getSite() + "%");
/*     */     }
/*     */     
/* 349 */     if (StringUtils.isNotEmpty(gameSearchCriteria.getTags())) {
/* 350 */       query.setString("tags", "%" + gameSearchCriteria.getTags() + "%");
/*     */     }
/*     */     
/* 353 */     return query;
/*     */   }
/*     */   
/*     */   private void clearListFromGames(List<GameDataObj> gameList) {
/* 357 */     for (GameDataObj game : gameList) {
/* 358 */       game.setPgnstring(null);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setAnnotationFlag(List<GameDataObj> gameList)
/*     */   {
/* 364 */     for (GameDataObj game : gameList) {
/* 365 */       if ((StringUtils.isEmpty(game.getAnnotator())) && 
/* 366 */         (!StringUtils.isEmpty(game.getPgnstring()))) {
/* 367 */         game.setAnnotator(game.getPgnstring().contains("{") ? "Yes" : 
/* 368 */           "");
/*     */       }
/*     */     }
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
/*     */   private void fillGameDataObj(GameDataObj gameDataObj, Game game)
/*     */   {
/* 383 */     GameInfo gameInfo = game.getGameInfo();
/* 384 */     if (gameInfo != null) {
/* 385 */       Player[] players = gameInfo.getPlayers();
/* 386 */       if (players != null) {
/* 387 */         if (players[0] != null) {
/* 388 */           String white = players[0].getName();
/* 389 */           gameDataObj.setWhite(white);
/*     */         }
/* 391 */         if (players[1] != null) {
/* 392 */           String black = players[1].getName();
/* 393 */           gameDataObj.setBlack(black);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 398 */       Calendar date = gameInfo.getDate();
/* 399 */       if (date != null) {
/* 400 */         gameDataObj.setDate(date.getTime());
/*     */       }
/* 402 */       gameDataObj.setEvent(gameInfo.getEvent());
/* 403 */       gameDataObj.setAnnotator(gameInfo.get("Annotator"));
/* 404 */       gameDataObj.setEco(((ChessGameInfo)gameInfo).getECO());
/*     */       
/* 406 */       gameDataObj.setSite(gameInfo.getSite());
/* 407 */       if (gameInfo.getResult() != null) {
/* 408 */         chessResult = (ChessResult)gameInfo.getResult();
/* 409 */         gameDataObj.setResult(chessResult.toString());
/*     */       }
/* 411 */       gameDataObj.setRound(gameInfo.getRound());
/* 412 */       ChessResult chessResult = "";
/*     */     }
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
/*     */   public Game getGame(GameDataObj gameDataObj)
/*     */   {
/* 427 */     Game game = null;
/*     */     
/*     */     try
/*     */     {
/* 431 */       String pgnstring = gameDataObj.getPgnstring();
/*     */       
/* 433 */       StringReader sr = new StringReader(pgnstring);
/* 434 */       PGNReader reader = new PGNReader(sr);
/* 435 */       game = reader.readGame();
/* 436 */       game.getGameInfo().getAuxilleryProperties().setProperty(
/* 437 */         "GameId", 
/* 438 */         String.valueOf(gameDataObj.getId()));
/*     */     }
/*     */     catch (IOException e) {
/* 441 */       log.error("IOException in getGame", e);
/* 442 */       e.printStackTrace();
/*     */     } catch (Exception me) {
/* 444 */       log.error("Exception in getGame", me);
/* 445 */       me.printStackTrace();
/*     */     }
/*     */     
/* 448 */     return game;
/*     */   }
/*     */   
/*     */   public ChessGameGroup getGameGroup(String userName) throws HibernateException
/*     */   {
/* 453 */     ChessGameGroup gameGroup = new ChessGameGroup();
/* 454 */     List<GameDataObj> gameDataObjForUsername = getGameDataObjForUsername(userName);
/* 455 */     for (GameDataObj gameDataObj : gameDataObjForUsername) {
/* 456 */       gameGroup.addChessGame((ChessGame)getGame(gameDataObj));
/*     */     }
/* 458 */     return gameGroup;
/*     */   }
/*     */   
/*     */   public ChessGameGroup getGameGroup(GameSearchCriteria gameSearchCriteria) throws HibernateException
/*     */   {
/* 463 */     ChessGameGroup gameGroup = new ChessGameGroup();
/* 464 */     List<GameDataObj> gameDataObjForUsername = getGameDataObjForSearchCriteria(gameSearchCriteria);
/* 465 */     for (GameDataObj gameDataObj : gameDataObjForUsername) {
/* 466 */       gameGroup.addChessGame((ChessGame)getGame(gameDataObj));
/*     */     }
/* 468 */     return gameGroup;
/*     */   }
/*     */   
/*     */   public boolean switchPublicStateGame(String username, long gameId) throws HibernateException
/*     */   {
/* 473 */     GameDataObj gameDataObj = getGameDataObj(gameId);
/* 474 */     Session session = createSession();
/* 475 */     Transaction tx = null;
/*     */     try {
/* 477 */       tx = session.beginTransaction();
/* 478 */       gameDataObj.setPublicgame(!gameDataObj.isPublicgame());
/* 479 */       if (gameDataObj.getUser().getUsername().equals(username)) {
/* 480 */         log.info("About to switch stat" + gameId);
/* 481 */         session.update(gameDataObj);
/* 482 */         log.info("Game " + gameId + " is now " + 
/* 483 */           gameDataObj.isPublicgame());
/*     */       } else {
/* 485 */         log.warn("Game " + gameId + " cannot be modified by user " + 
/* 486 */           username);
/*     */       }
/*     */       
/* 489 */       tx.commit();
/* 490 */       return gameDataObj.isPublicgame();
/*     */     }
/*     */     catch (Exception e) {
/* 493 */       if (tx != null) {
/* 494 */         tx.rollback();
/*     */       }
/* 496 */       log.error("Exception while deleting game " + gameId, e);
/*     */     }
/*     */     finally {
/* 499 */       session.close(); } session.close();
/*     */     
/* 501 */     return false;
/*     */   }
/*     */   
/*     */   public boolean switchDeleteGame(String username, long gameId)
/*     */     throws HibernateException
/*     */   {
/* 507 */     GameDataObj gameDataObj = getGameDataObj(gameId);
/* 508 */     Session session = createSession();
/* 509 */     Transaction tx = null;
/*     */     try {
/* 511 */       tx = session.beginTransaction();
/* 512 */       gameDataObj.setDeleted(!gameDataObj.isDeleted());
/* 513 */       if (gameDataObj.getUser().getUsername().equals(username)) {
/* 514 */         log.info("About to switch stat" + gameId);
/* 515 */         session.update(gameDataObj);
/* 516 */         log.info("Game " + gameId + " is now " + 
/* 517 */           gameDataObj.isPublicgame());
/*     */       } else {
/* 519 */         log.warn("Game " + gameId + " cannot be modified by user " + 
/* 520 */           username);
/*     */       }
/*     */       
/* 523 */       tx.commit();
/* 524 */       return gameDataObj.isDeleted();
/*     */     }
/*     */     catch (Exception e) {
/* 527 */       if (tx != null) {
/* 528 */         tx.rollback();
/*     */       }
/* 530 */       log.error("Exception while deleting game " + gameId, e);
/*     */     }
/*     */     finally {
/* 533 */       session.close(); } session.close();
/*     */     
/* 535 */     return false;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void deleteGame(String username, long gameId)
/*     */     throws HibernateException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual 71	com/amicabile/openingtrainer/dao/GameDataObjDAO:createSession	()Lnet/sf/hibernate/Session;
/*     */     //   4: astore 4
/*     */     //   6: aconst_null
/*     */     //   7: astore 5
/*     */     //   9: aload 4
/*     */     //   11: invokeinterface 143 1 0
/*     */     //   16: astore 5
/*     */     //   18: aload_0
/*     */     //   19: lload_2
/*     */     //   20: invokevirtual 553	com/amicabile/openingtrainer/dao/GameDataObjDAO:getGameDataObj	(J)Lcom/amicabile/openingtrainer/model/dataobj/GameDataObj;
/*     */     //   23: astore 6
/*     */     //   25: aload 6
/*     */     //   27: invokevirtual 562	com/amicabile/openingtrainer/model/dataobj/GameDataObj:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   30: invokevirtual 566	com/amicabile/openingtrainer/model/dataobj/User:getUsername	()Ljava/lang/String;
/*     */     //   33: aload_1
/*     */     //   34: invokevirtual 571	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   37: ifeq +67 -> 104
/*     */     //   40: getstatic 30	com/amicabile/openingtrainer/dao/GameDataObjDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   43: new 48	java/lang/StringBuilder
/*     */     //   46: dup
/*     */     //   47: ldc_w 606
/*     */     //   50: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   53: lload_2
/*     */     //   54: invokevirtual 55	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
/*     */     //   57: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   60: invokevirtual 577	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   63: aload 4
/*     */     //   65: aload 6
/*     */     //   67: invokeinterface 157 2 0
/*     */     //   72: getstatic 30	com/amicabile/openingtrainer/dao/GameDataObjDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   75: new 48	java/lang/StringBuilder
/*     */     //   78: dup
/*     */     //   79: ldc_w 583
/*     */     //   82: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   85: lload_2
/*     */     //   86: invokevirtual 55	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
/*     */     //   89: ldc_w 608
/*     */     //   92: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   95: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   98: invokevirtual 577	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   101: goto +36 -> 137
/*     */     //   104: getstatic 30	com/amicabile/openingtrainer/dao/GameDataObjDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   107: new 48	java/lang/StringBuilder
/*     */     //   110: dup
/*     */     //   111: ldc_w 583
/*     */     //   114: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   117: lload_2
/*     */     //   118: invokevirtual 55	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
/*     */     //   121: ldc_w 610
/*     */     //   124: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   127: aload_1
/*     */     //   128: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   131: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   134: invokevirtual 592	org/apache/log4j/Logger:warn	(Ljava/lang/Object;)V
/*     */     //   137: aload 5
/*     */     //   139: invokeinterface 164 1 0
/*     */     //   144: goto +58 -> 202
/*     */     //   147: astore 6
/*     */     //   149: aload 5
/*     */     //   151: ifnull +10 -> 161
/*     */     //   154: aload 5
/*     */     //   156: invokeinterface 169 1 0
/*     */     //   161: getstatic 30	com/amicabile/openingtrainer/dao/GameDataObjDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   164: new 48	java/lang/StringBuilder
/*     */     //   167: dup
/*     */     //   168: ldc_w 595
/*     */     //   171: invokespecial 52	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   174: lload_2
/*     */     //   175: invokevirtual 55	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
/*     */     //   178: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   181: aload 6
/*     */     //   183: invokevirtual 174	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   186: goto +27 -> 213
/*     */     //   189: astore 7
/*     */     //   191: aload 4
/*     */     //   193: invokeinterface 90 1 0
/*     */     //   198: pop
/*     */     //   199: aload 7
/*     */     //   201: athrow
/*     */     //   202: aload 4
/*     */     //   204: invokeinterface 90 1 0
/*     */     //   209: pop
/*     */     //   210: goto +11 -> 221
/*     */     //   213: aload 4
/*     */     //   215: invokeinterface 90 1 0
/*     */     //   220: pop
/*     */     //   221: return
/*     */     // Line number table:
/*     */     //   Java source line #542	-> byte code offset #0
/*     */     //   Java source line #543	-> byte code offset #6
/*     */     //   Java source line #545	-> byte code offset #9
/*     */     //   Java source line #547	-> byte code offset #18
/*     */     //   Java source line #549	-> byte code offset #25
/*     */     //   Java source line #550	-> byte code offset #40
/*     */     //   Java source line #551	-> byte code offset #63
/*     */     //   Java source line #552	-> byte code offset #72
/*     */     //   Java source line #554	-> byte code offset #104
/*     */     //   Java source line #555	-> byte code offset #127
/*     */     //   Java source line #554	-> byte code offset #134
/*     */     //   Java source line #558	-> byte code offset #137
/*     */     //   Java source line #560	-> byte code offset #147
/*     */     //   Java source line #561	-> byte code offset #149
/*     */     //   Java source line #562	-> byte code offset #154
/*     */     //   Java source line #564	-> byte code offset #161
/*     */     //   Java source line #566	-> byte code offset #189
/*     */     //   Java source line #567	-> byte code offset #191
/*     */     //   Java source line #568	-> byte code offset #199
/*     */     //   Java source line #567	-> byte code offset #202
/*     */     //   Java source line #568	-> byte code offset #210
/*     */     //   Java source line #567	-> byte code offset #213
/*     */     //   Java source line #569	-> byte code offset #221
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	222	0	this	GameDataObjDAO
/*     */     //   0	222	1	username	String
/*     */     //   0	222	2	gameId	long
/*     */     //   4	210	4	session	Session
/*     */     //   7	148	5	tx	Transaction
/*     */     //   23	43	6	gameDataObj	GameDataObj
/*     */     //   147	35	6	e	Exception
/*     */     //   189	11	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   9	144	147	java/lang/Exception
/*     */     //   9	189	189	finally
/*     */   }
/*     */   
/*     */   public int getCountGames()
/*     */     throws HibernateException
/*     */   {
/* 573 */     Session session = createSession();
/*     */     try {
/* 575 */       Query query = session.getNamedQuery("com.amicabile.openingtrainer.model.dataobj.CountGames");
/*     */       
/*     */ 
/* 578 */       int result = ((Integer)query.uniqueResult()).intValue();
/* 579 */       return result;
/*     */     }
/*     */     finally {
/* 582 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public GameDataObj updateGameDataObj(GameDataObj gameDataObj)
/*     */     throws HibernateException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual 71	com/amicabile/openingtrainer/dao/GameDataObjDAO:createSession	()Lnet/sf/hibernate/Session;
/*     */     //   4: astore_2
/*     */     //   5: aconst_null
/*     */     //   6: astore_3
/*     */     //   7: aload_2
/*     */     //   8: invokeinterface 143 1 0
/*     */     //   13: astore_3
/*     */     //   14: aload_2
/*     */     //   15: aload_1
/*     */     //   16: invokeinterface 620 2 0
/*     */     //   21: aload_3
/*     */     //   22: invokeinterface 164 1 0
/*     */     //   27: goto +30 -> 57
/*     */     //   30: astore 4
/*     */     //   32: aload_3
/*     */     //   33: ifnull +34 -> 67
/*     */     //   36: aload_3
/*     */     //   37: invokeinterface 169 1 0
/*     */     //   42: goto +25 -> 67
/*     */     //   45: astore 5
/*     */     //   47: aload_2
/*     */     //   48: invokeinterface 90 1 0
/*     */     //   53: pop
/*     */     //   54: aload 5
/*     */     //   56: athrow
/*     */     //   57: aload_2
/*     */     //   58: invokeinterface 90 1 0
/*     */     //   63: pop
/*     */     //   64: goto +10 -> 74
/*     */     //   67: aload_2
/*     */     //   68: invokeinterface 90 1 0
/*     */     //   73: pop
/*     */     //   74: aload_1
/*     */     //   75: areturn
/*     */     // Line number table:
/*     */     //   Java source line #589	-> byte code offset #0
/*     */     //   Java source line #590	-> byte code offset #5
/*     */     //   Java source line #592	-> byte code offset #7
/*     */     //   Java source line #594	-> byte code offset #14
/*     */     //   Java source line #595	-> byte code offset #21
/*     */     //   Java source line #596	-> byte code offset #30
/*     */     //   Java source line #597	-> byte code offset #32
/*     */     //   Java source line #598	-> byte code offset #36
/*     */     //   Java source line #601	-> byte code offset #45
/*     */     //   Java source line #603	-> byte code offset #47
/*     */     //   Java source line #604	-> byte code offset #54
/*     */     //   Java source line #603	-> byte code offset #57
/*     */     //   Java source line #604	-> byte code offset #64
/*     */     //   Java source line #603	-> byte code offset #67
/*     */     //   Java source line #605	-> byte code offset #74
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	76	0	this	GameDataObjDAO
/*     */     //   0	76	1	gameDataObj	GameDataObj
/*     */     //   4	64	2	session	Session
/*     */     //   6	31	3	tx	Transaction
/*     */     //   30	3	4	e	Exception
/*     */     //   45	10	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	27	30	java/lang/Exception
/*     */     //   7	45	45	finally
/*     */   }
/*     */   
/*     */   public GameDataObj createGameDataObj(String username, String pgnString)
/*     */     throws HibernateException
/*     */   {
/* 611 */     return createGameDataObj(username, pgnString, "", true);
/*     */   }
/*     */   
/*     */   public GameDataObj createGameDataObj(String username, String pgnString, String tag, boolean publicgame)
/*     */     throws HibernateException
/*     */   {
/* 617 */     GameDataObj gameDataObj = null;
/*     */     
/* 619 */     UserDAO userdao = UserDAO.getInstance();
/* 620 */     User user = userdao.getUser(username);
/*     */     
/* 622 */     gameDataObj = new GameDataObj();
/* 623 */     gameDataObj.setPgnstring(pgnString);
/* 624 */     gameDataObj.setUser(user);
/* 625 */     gameDataObj.setTags(tag);
/* 626 */     gameDataObj.setPublicgame(publicgame);
/*     */     
/* 628 */     updateAndFillGameDataObj(gameDataObj);
/*     */     
/* 630 */     return gameDataObj;
/*     */   }
/*     */   
/*     */   public void updateAndFillGameDataObj(GameDataObj gameDataObj) throws HibernateException
/*     */   {
/* 635 */     Game game = getGame(gameDataObj);
/* 636 */     fillGameDataObj(gameDataObj, game);
/* 637 */     updateGameDataObj(gameDataObj);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dao\GameDataObjDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */