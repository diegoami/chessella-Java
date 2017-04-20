/*     */ package com.amicabile.openingtrainer.servlet.webwork.games;
/*     */ 
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRule;
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
/*     */ import com.amicabile.openingtrainer.controller.MoveTreePool;
/*     */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*     */ import com.amicabile.openingtrainer.model.dataobj.User;
/*     */ import com.amicabile.openingtrainer.pgn.ChessGameGroup;
/*     */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*     */ import com.amicabile.openingtrainer.pgn.PGNException;
/*     */ import com.amicabile.openingtrainer.repository.GameRepository;
/*     */ import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
/*     */ import com.opensymphony.xwork.ActionContext;
/*     */ import com.opensymphony.xwork.ActionSupport;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import java.io.File;
/*     */ import java.io.Reader;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ import org.apache.log4j.Logger;
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
/*     */ public class GameAction
/*     */   extends ActionSupport
/*     */ {
/*  38 */   private static Logger log = Logger.getLogger(GameAction.class.getName());
/*     */   
/*     */ 
/*     */   private String tag;
/*     */   
/*     */   private long id;
/*     */   
/*     */   private String pgnString;
/*     */   
/*     */   private String urlString;
/*     */   
/*  49 */   private boolean publicgame = false;
/*     */   
/*     */   private GameDataObj currentGameDataObj;
/*  52 */   private ShowBoardRule currentShowBoardRule = null;
/*     */   
/*     */   private File file;
/*     */   
/*     */   private String contentType;
/*     */   private String filename;
/*     */   
/*     */   public void setUpload(File file)
/*     */   {
/*  61 */     this.file = file;
/*     */   }
/*     */   
/*     */   public boolean isCanRead() {
/*  65 */     boolean canRead = false;
/*  66 */     if ((this.currentGameDataObj != null) && (
/*  67 */       (this.currentGameDataObj.isPublicgame()) || 
/*  68 */       (this.currentGameDataObj.getUser().getUsername().equals(getUser().getUsername())))) {
/*  69 */       canRead = true;
/*     */     }
/*     */     
/*  72 */     return canRead;
/*     */   }
/*     */   
/*     */   public void setUploadContentType(String contentType) {
/*  76 */     this.contentType = contentType;
/*     */   }
/*     */   
/*     */   public void setUploadFileName(String filename) {
/*  80 */     this.filename = filename;
/*     */   }
/*     */   
/*     */   public String getUrlString() {
/*  84 */     return this.urlString;
/*     */   }
/*     */   
/*     */   public void setUrlString(String urlString) {
/*  88 */     this.urlString = urlString;
/*     */   }
/*     */   
/*     */   public boolean isEditable() {
/*  92 */     User user = getUser();
/*  93 */     if ((user != null) && 
/*  94 */       (this.currentGameDataObj != null) && 
/*  95 */       (this.currentGameDataObj.getUser().getUsername().equals(
/*  96 */       user.getUsername()))) {
/*  97 */       return true;
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 104 */   private VelocityBoardFactory velocityBoardFactory = VelocityBoardFactory.getInstance();
/*     */   private ChessGameGroup chessGameGroup;
/*     */   
/*     */   public ShowBoardRule getShowBoardRule()
/*     */   {
/* 109 */     if (getUser() != null)
/* 110 */       return getUser().getShowBoardRule();
/* 111 */     if (this.currentShowBoardRule != null) {
/* 112 */       return this.currentShowBoardRule;
/*     */     }
/* 114 */     return ShowBoardRulePrototypes.DEFAULT_RULE;
/*     */   }
/*     */   
/*     */   private User getUser()
/*     */   {
/* 119 */     Map session = ActionContext.getContext().getSession();
/* 120 */     User user = (User)session.get("user");
/* 121 */     return user;
/*     */   }
/*     */   
/*     */   public String getStringId() {
/* 125 */     return String.valueOf(this.id);
/*     */   }
/*     */   
/*     */   public long getId() {
/* 129 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 133 */     this.id = id;
/*     */   }
/*     */   
/*     */   public List<Long> getGameIds() {
/* 137 */     Map session = ActionContext.getContext().getSession();
/* 138 */     List<Long> gameIds = (List)session.get("gameIds");
/* 139 */     return gameIds;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getNextGameId()
/*     */   {
/* 145 */     List<Long> gameIds = getGameIds();
/* 146 */     int indexOfId = gameIds.indexOf(Long.valueOf(getId()));
/* 147 */     int indexOfNextId = indexOfId + 1;
/* 148 */     if (gameIds.size() > indexOfNextId) {
/* 149 */       return String.valueOf(gameIds.get(indexOfNextId));
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getPrevGameId()
/*     */   {
/* 157 */     List<Long> gameIds = getGameIds();
/* 158 */     int indexOfId = gameIds.indexOf(Long.valueOf(getId()));
/* 159 */     int indexOfPrevId = indexOfId - 1;
/* 160 */     if (indexOfPrevId >= 0) {
/* 161 */       return String.valueOf(gameIds.get(indexOfPrevId));
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ChessGameGroup getChessGameGroup()
/*     */   {
/* 169 */     return this.chessGameGroup;
/*     */   }
/*     */   
/*     */   public void setChessGameGroup(ChessGameGroup chessGameGroup) {
/* 173 */     this.chessGameGroup = chessGameGroup;
/*     */   }
/*     */   
/*     */   public MoveTreePool getMoveTreePool() {
/* 177 */     return MoveTreePool.getInstance();
/*     */   }
/*     */   
/*     */   public VelocityBoardFactory getVelocityBoardFactory() {
/* 181 */     return this.velocityBoardFactory;
/*     */   }
/*     */   
/*     */   public String getPgnString() {
/* 185 */     return this.pgnString;
/*     */   }
/*     */   
/*     */   public String getEncodedPgnString() {
/* 189 */     return this.pgnString != null ? StringEscapeUtils.escapeHtml(this.pgnString) : "";
/*     */   }
/*     */   
/*     */   public void setPgnString(String pgnString)
/*     */   {
/* 194 */     this.pgnString = pgnString;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public String saveGameFromLink()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   4: astore_1
/*     */     //   5: aload_1
/*     */     //   6: ifnull +196 -> 202
/*     */     //   9: aload_1
/*     */     //   10: invokevirtual 80	com/amicabile/openingtrainer/model/dataobj/User:getUsername	()Ljava/lang/String;
/*     */     //   13: astore_2
/*     */     //   14: aload_0
/*     */     //   15: getfield 101	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:urlString	Ljava/lang/String;
/*     */     //   18: ifnull +141 -> 159
/*     */     //   21: invokestatic 213	com/amicabile/openingtrainer/repository/GameRepository:getInstance	()Lcom/amicabile/openingtrainer/repository/GameRepository;
/*     */     //   24: aload_2
/*     */     //   25: invokevirtual 218	com/amicabile/openingtrainer/repository/GameRepository:canAddGames	(Ljava/lang/String;)Z
/*     */     //   28: ifeq +120 -> 148
/*     */     //   31: new 222	org/apache/commons/httpclient/HttpClient
/*     */     //   34: dup
/*     */     //   35: invokespecial 224	org/apache/commons/httpclient/HttpClient:<init>	()V
/*     */     //   38: astore_3
/*     */     //   39: new 225	org/apache/commons/httpclient/methods/GetMethod
/*     */     //   42: dup
/*     */     //   43: aload_0
/*     */     //   44: getfield 101	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:urlString	Ljava/lang/String;
/*     */     //   47: invokespecial 227	org/apache/commons/httpclient/methods/GetMethod:<init>	(Ljava/lang/String;)V
/*     */     //   50: astore 4
/*     */     //   52: aload_3
/*     */     //   53: aload 4
/*     */     //   55: invokevirtual 229	org/apache/commons/httpclient/HttpClient:executeMethod	(Lorg/apache/commons/httpclient/HttpMethod;)I
/*     */     //   58: pop
/*     */     //   59: aload 4
/*     */     //   61: invokevirtual 233	org/apache/commons/httpclient/methods/GetMethod:getResponseBodyAsStream	()Ljava/io/InputStream;
/*     */     //   64: astore 5
/*     */     //   66: new 237	java/io/InputStreamReader
/*     */     //   69: dup
/*     */     //   70: aload 5
/*     */     //   72: invokespecial 239	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
/*     */     //   75: astore 6
/*     */     //   77: aload_0
/*     */     //   78: aload_2
/*     */     //   79: aload 6
/*     */     //   81: aload_0
/*     */     //   82: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   85: invokevirtual 242	com/amicabile/openingtrainer/model/dataobj/User:getMaxgames	()I
/*     */     //   88: invokespecial 245	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:fillChessGameGroupFromReader	(Ljava/lang/String;Ljava/io/Reader;I)V
/*     */     //   91: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   94: ldc -7
/*     */     //   96: invokevirtual 251	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   99: goto +42 -> 141
/*     */     //   102: astore 7
/*     */     //   104: aload_0
/*     */     //   105: aload 7
/*     */     //   107: invokevirtual 255	com/amicabile/openingtrainer/pgn/PGNException:getMessage	()Ljava/lang/String;
/*     */     //   110: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   113: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   116: aload 7
/*     */     //   118: invokevirtual 255	com/amicabile/openingtrainer/pgn/PGNException:getMessage	()Ljava/lang/String;
/*     */     //   121: aload 7
/*     */     //   123: invokevirtual 263	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   126: aconst_null
/*     */     //   127: astore 6
/*     */     //   129: ldc_w 267
/*     */     //   132: areturn
/*     */     //   133: astore 8
/*     */     //   135: aconst_null
/*     */     //   136: astore 6
/*     */     //   138: aload 8
/*     */     //   140: athrow
/*     */     //   141: aconst_null
/*     */     //   142: astore 6
/*     */     //   144: ldc_w 269
/*     */     //   147: areturn
/*     */     //   148: aload_0
/*     */     //   149: ldc_w 271
/*     */     //   152: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   155: ldc_w 267
/*     */     //   158: areturn
/*     */     //   159: ldc_w 267
/*     */     //   162: areturn
/*     */     //   163: astore_2
/*     */     //   164: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   167: ldc_w 273
/*     */     //   170: aload_2
/*     */     //   171: invokevirtual 263	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   174: aload_0
/*     */     //   175: new 275	java/lang/StringBuilder
/*     */     //   178: dup
/*     */     //   179: ldc_w 277
/*     */     //   182: invokespecial 279	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   185: aload_2
/*     */     //   186: invokevirtual 280	java/lang/Exception:getMessage	()Ljava/lang/String;
/*     */     //   189: invokevirtual 283	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   192: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   195: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   198: ldc_w 290
/*     */     //   201: areturn
/*     */     //   202: ldc_w 291
/*     */     //   205: areturn
/*     */     // Line number table:
/*     */     //   Java source line #198	-> byte code offset #0
/*     */     //   Java source line #199	-> byte code offset #5
/*     */     //   Java source line #201	-> byte code offset #9
/*     */     //   Java source line #203	-> byte code offset #14
/*     */     //   Java source line #204	-> byte code offset #21
/*     */     //   Java source line #206	-> byte code offset #31
/*     */     //   Java source line #207	-> byte code offset #39
/*     */     //   Java source line #208	-> byte code offset #52
/*     */     //   Java source line #209	-> byte code offset #59
/*     */     //   Java source line #210	-> byte code offset #61
/*     */     //   Java source line #209	-> byte code offset #64
/*     */     //   Java source line #211	-> byte code offset #66
/*     */     //   Java source line #214	-> byte code offset #77
/*     */     //   Java source line #215	-> byte code offset #81
/*     */     //   Java source line #214	-> byte code offset #88
/*     */     //   Java source line #216	-> byte code offset #91
/*     */     //   Java source line #218	-> byte code offset #102
/*     */     //   Java source line #219	-> byte code offset #104
/*     */     //   Java source line #220	-> byte code offset #113
/*     */     //   Java source line #224	-> byte code offset #126
/*     */     //   Java source line #222	-> byte code offset #129
/*     */     //   Java source line #223	-> byte code offset #133
/*     */     //   Java source line #224	-> byte code offset #135
/*     */     //   Java source line #225	-> byte code offset #138
/*     */     //   Java source line #224	-> byte code offset #141
/*     */     //   Java source line #227	-> byte code offset #144
/*     */     //   Java source line #229	-> byte code offset #148
/*     */     //   Java source line #230	-> byte code offset #149
/*     */     //   Java source line #231	-> byte code offset #155
/*     */     //   Java source line #235	-> byte code offset #159
/*     */     //   Java source line #237	-> byte code offset #163
/*     */     //   Java source line #238	-> byte code offset #164
/*     */     //   Java source line #239	-> byte code offset #174
/*     */     //   Java source line #241	-> byte code offset #198
/*     */     //   Java source line #246	-> byte code offset #202
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	206	0	this	GameAction
/*     */     //   4	6	1	user	User
/*     */     //   13	66	2	username	String
/*     */     //   163	23	2	he	Exception
/*     */     //   38	15	3	httpClient	org.apache.commons.httpclient.HttpClient
/*     */     //   50	10	4	method	org.apache.commons.httpclient.methods.GetMethod
/*     */     //   64	7	5	resourceAsStream	java.io.InputStream
/*     */     //   75	68	6	reader	Reader
/*     */     //   102	20	7	pe	PGNException
/*     */     //   133	6	8	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   77	99	102	com/amicabile/openingtrainer/pgn/PGNException
/*     */     //   77	126	133	finally
/*     */     //   9	129	163	java/lang/Exception
/*     */     //   133	144	163	java/lang/Exception
/*     */     //   148	155	163	java/lang/Exception
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public String uploadPgn()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   4: astore_1
/*     */     //   5: aload_1
/*     */     //   6: ifnull +197 -> 203
/*     */     //   9: aload_1
/*     */     //   10: invokevirtual 80	com/amicabile/openingtrainer/model/dataobj/User:getUsername	()Ljava/lang/String;
/*     */     //   13: astore_2
/*     */     //   14: aload_0
/*     */     //   15: getfield 65	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:file	Ljava/io/File;
/*     */     //   18: ifnonnull +7 -> 25
/*     */     //   21: ldc_w 267
/*     */     //   24: areturn
/*     */     //   25: invokestatic 213	com/amicabile/openingtrainer/repository/GameRepository:getInstance	()Lcom/amicabile/openingtrainer/repository/GameRepository;
/*     */     //   28: aload_2
/*     */     //   29: invokevirtual 218	com/amicabile/openingtrainer/repository/GameRepository:canAddGames	(Ljava/lang/String;)Z
/*     */     //   32: ifeq +117 -> 149
/*     */     //   35: new 307	java/io/FileReader
/*     */     //   38: dup
/*     */     //   39: aload_0
/*     */     //   40: getfield 98	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:filename	Ljava/lang/String;
/*     */     //   43: invokespecial 309	java/io/FileReader:<init>	(Ljava/lang/String;)V
/*     */     //   46: astore_3
/*     */     //   47: aload_0
/*     */     //   48: aload_2
/*     */     //   49: aload_3
/*     */     //   50: aload_0
/*     */     //   51: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   54: invokevirtual 242	com/amicabile/openingtrainer/model/dataobj/User:getMaxgames	()I
/*     */     //   57: invokespecial 245	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:fillChessGameGroupFromReader	(Ljava/lang/String;Ljava/io/Reader;I)V
/*     */     //   60: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   63: ldc_w 310
/*     */     //   66: invokevirtual 251	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   69: goto +67 -> 136
/*     */     //   72: astore 4
/*     */     //   74: aload_0
/*     */     //   75: new 275	java/lang/StringBuilder
/*     */     //   78: dup
/*     */     //   79: ldc_w 312
/*     */     //   82: invokespecial 279	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   85: aload_0
/*     */     //   86: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   89: invokevirtual 242	com/amicabile/openingtrainer/model/dataobj/User:getMaxgames	()I
/*     */     //   92: invokevirtual 314	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   95: ldc_w 317
/*     */     //   98: invokevirtual 283	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   101: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   104: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   107: aload_3
/*     */     //   108: ifnull +7 -> 115
/*     */     //   111: aload_3
/*     */     //   112: invokevirtual 319	java/io/Reader:close	()V
/*     */     //   115: aconst_null
/*     */     //   116: astore_3
/*     */     //   117: ldc_w 267
/*     */     //   120: areturn
/*     */     //   121: astore 5
/*     */     //   123: aload_3
/*     */     //   124: ifnull +7 -> 131
/*     */     //   127: aload_3
/*     */     //   128: invokevirtual 319	java/io/Reader:close	()V
/*     */     //   131: aconst_null
/*     */     //   132: astore_3
/*     */     //   133: aload 5
/*     */     //   135: athrow
/*     */     //   136: aload_3
/*     */     //   137: ifnull +7 -> 144
/*     */     //   140: aload_3
/*     */     //   141: invokevirtual 319	java/io/Reader:close	()V
/*     */     //   144: aconst_null
/*     */     //   145: astore_3
/*     */     //   146: goto +14 -> 160
/*     */     //   149: aload_0
/*     */     //   150: ldc_w 271
/*     */     //   153: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   156: ldc_w 267
/*     */     //   159: areturn
/*     */     //   160: ldc_w 269
/*     */     //   163: areturn
/*     */     //   164: astore_3
/*     */     //   165: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   168: ldc_w 324
/*     */     //   171: aload_3
/*     */     //   172: invokevirtual 263	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   175: aload_0
/*     */     //   176: new 275	java/lang/StringBuilder
/*     */     //   179: dup
/*     */     //   180: ldc_w 277
/*     */     //   183: invokespecial 279	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   186: aload_3
/*     */     //   187: invokevirtual 280	java/lang/Exception:getMessage	()Ljava/lang/String;
/*     */     //   190: invokevirtual 283	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   193: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   196: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   199: ldc_w 290
/*     */     //   202: areturn
/*     */     //   203: ldc_w 291
/*     */     //   206: areturn
/*     */     // Line number table:
/*     */     //   Java source line #251	-> byte code offset #0
/*     */     //   Java source line #252	-> byte code offset #5
/*     */     //   Java source line #253	-> byte code offset #9
/*     */     //   Java source line #255	-> byte code offset #14
/*     */     //   Java source line #257	-> byte code offset #21
/*     */     //   Java source line #259	-> byte code offset #25
/*     */     //   Java source line #261	-> byte code offset #35
/*     */     //   Java source line #263	-> byte code offset #47
/*     */     //   Java source line #264	-> byte code offset #50
/*     */     //   Java source line #263	-> byte code offset #57
/*     */     //   Java source line #266	-> byte code offset #60
/*     */     //   Java source line #267	-> byte code offset #72
/*     */     //   Java source line #268	-> byte code offset #74
/*     */     //   Java source line #269	-> byte code offset #85
/*     */     //   Java source line #268	-> byte code offset #104
/*     */     //   Java source line #273	-> byte code offset #107
/*     */     //   Java source line #274	-> byte code offset #111
/*     */     //   Java source line #276	-> byte code offset #115
/*     */     //   Java source line #271	-> byte code offset #117
/*     */     //   Java source line #272	-> byte code offset #121
/*     */     //   Java source line #273	-> byte code offset #123
/*     */     //   Java source line #274	-> byte code offset #127
/*     */     //   Java source line #276	-> byte code offset #131
/*     */     //   Java source line #277	-> byte code offset #133
/*     */     //   Java source line #273	-> byte code offset #136
/*     */     //   Java source line #274	-> byte code offset #140
/*     */     //   Java source line #276	-> byte code offset #144
/*     */     //   Java source line #280	-> byte code offset #149
/*     */     //   Java source line #281	-> byte code offset #150
/*     */     //   Java source line #282	-> byte code offset #156
/*     */     //   Java source line #285	-> byte code offset #160
/*     */     //   Java source line #286	-> byte code offset #164
/*     */     //   Java source line #287	-> byte code offset #165
/*     */     //   Java source line #288	-> byte code offset #175
/*     */     //   Java source line #290	-> byte code offset #199
/*     */     //   Java source line #293	-> byte code offset #203
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	207	0	this	GameAction
/*     */     //   4	6	1	user	User
/*     */     //   13	36	2	username	String
/*     */     //   46	100	3	reader	Reader
/*     */     //   164	23	3	he	Exception
/*     */     //   72	3	4	pe	PGNException
/*     */     //   121	13	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   47	69	72	com/amicabile/openingtrainer/pgn/PGNException
/*     */     //   47	107	121	finally
/*     */     //   14	21	164	java/lang/Exception
/*     */     //   25	117	164	java/lang/Exception
/*     */     //   121	156	164	java/lang/Exception
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public String saveGameFromPgn()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   4: astore_1
/*     */     //   5: aload_1
/*     */     //   6: ifnull +182 -> 188
/*     */     //   9: aload_1
/*     */     //   10: invokevirtual 80	com/amicabile/openingtrainer/model/dataobj/User:getUsername	()Ljava/lang/String;
/*     */     //   13: astore_2
/*     */     //   14: aload_0
/*     */     //   15: getfield 200	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:pgnString	Ljava/lang/String;
/*     */     //   18: invokestatic 327	org/apache/commons/lang/StringUtils:isEmpty	(Ljava/lang/String;)Z
/*     */     //   21: ifeq +13 -> 34
/*     */     //   24: aload_0
/*     */     //   25: ldc -47
/*     */     //   27: putfield 200	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:pgnString	Ljava/lang/String;
/*     */     //   30: ldc_w 267
/*     */     //   33: areturn
/*     */     //   34: invokestatic 213	com/amicabile/openingtrainer/repository/GameRepository:getInstance	()Lcom/amicabile/openingtrainer/repository/GameRepository;
/*     */     //   37: aload_2
/*     */     //   38: invokevirtual 218	com/amicabile/openingtrainer/repository/GameRepository:canAddGames	(Ljava/lang/String;)Z
/*     */     //   41: ifeq +93 -> 134
/*     */     //   44: new 332	java/io/StringReader
/*     */     //   47: dup
/*     */     //   48: aload_0
/*     */     //   49: getfield 200	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:pgnString	Ljava/lang/String;
/*     */     //   52: invokespecial 334	java/io/StringReader:<init>	(Ljava/lang/String;)V
/*     */     //   55: astore_3
/*     */     //   56: aload_0
/*     */     //   57: aload_2
/*     */     //   58: aload_3
/*     */     //   59: aload_0
/*     */     //   60: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   63: invokevirtual 242	com/amicabile/openingtrainer/model/dataobj/User:getMaxgames	()I
/*     */     //   66: invokespecial 245	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:fillChessGameGroupFromReader	(Ljava/lang/String;Ljava/io/Reader;I)V
/*     */     //   69: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   72: ldc_w 335
/*     */     //   75: invokevirtual 251	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   78: goto +51 -> 129
/*     */     //   81: astore 4
/*     */     //   83: aload_0
/*     */     //   84: new 275	java/lang/StringBuilder
/*     */     //   87: dup
/*     */     //   88: ldc_w 312
/*     */     //   91: invokespecial 279	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   94: aload_0
/*     */     //   95: invokespecial 85	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:getUser	()Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   98: invokevirtual 242	com/amicabile/openingtrainer/model/dataobj/User:getMaxgames	()I
/*     */     //   101: invokevirtual 314	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   104: ldc_w 317
/*     */     //   107: invokevirtual 283	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   110: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   113: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   116: aconst_null
/*     */     //   117: astore_3
/*     */     //   118: ldc_w 267
/*     */     //   121: areturn
/*     */     //   122: astore 5
/*     */     //   124: aconst_null
/*     */     //   125: astore_3
/*     */     //   126: aload 5
/*     */     //   128: athrow
/*     */     //   129: aconst_null
/*     */     //   130: astore_3
/*     */     //   131: goto +14 -> 145
/*     */     //   134: aload_0
/*     */     //   135: ldc_w 271
/*     */     //   138: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   141: ldc_w 267
/*     */     //   144: areturn
/*     */     //   145: ldc_w 269
/*     */     //   148: areturn
/*     */     //   149: astore_3
/*     */     //   150: getstatic 42	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:log	Lorg/apache/log4j/Logger;
/*     */     //   153: ldc_w 324
/*     */     //   156: aload_3
/*     */     //   157: invokevirtual 263	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   160: aload_0
/*     */     //   161: new 275	java/lang/StringBuilder
/*     */     //   164: dup
/*     */     //   165: ldc_w 277
/*     */     //   168: invokespecial 279	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   171: aload_3
/*     */     //   172: invokevirtual 280	java/lang/Exception:getMessage	()Ljava/lang/String;
/*     */     //   175: invokevirtual 283	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   178: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   181: invokevirtual 260	com/amicabile/openingtrainer/servlet/webwork/games/GameAction:addActionError	(Ljava/lang/String;)V
/*     */     //   184: ldc_w 290
/*     */     //   187: areturn
/*     */     //   188: ldc_w 291
/*     */     //   191: areturn
/*     */     // Line number table:
/*     */     //   Java source line #300	-> byte code offset #0
/*     */     //   Java source line #301	-> byte code offset #5
/*     */     //   Java source line #302	-> byte code offset #9
/*     */     //   Java source line #305	-> byte code offset #14
/*     */     //   Java source line #307	-> byte code offset #24
/*     */     //   Java source line #308	-> byte code offset #30
/*     */     //   Java source line #310	-> byte code offset #34
/*     */     //   Java source line #312	-> byte code offset #44
/*     */     //   Java source line #314	-> byte code offset #56
/*     */     //   Java source line #315	-> byte code offset #59
/*     */     //   Java source line #314	-> byte code offset #66
/*     */     //   Java source line #317	-> byte code offset #69
/*     */     //   Java source line #318	-> byte code offset #81
/*     */     //   Java source line #319	-> byte code offset #83
/*     */     //   Java source line #320	-> byte code offset #94
/*     */     //   Java source line #319	-> byte code offset #113
/*     */     //   Java source line #324	-> byte code offset #116
/*     */     //   Java source line #322	-> byte code offset #118
/*     */     //   Java source line #323	-> byte code offset #122
/*     */     //   Java source line #324	-> byte code offset #124
/*     */     //   Java source line #325	-> byte code offset #126
/*     */     //   Java source line #324	-> byte code offset #129
/*     */     //   Java source line #328	-> byte code offset #134
/*     */     //   Java source line #329	-> byte code offset #135
/*     */     //   Java source line #330	-> byte code offset #141
/*     */     //   Java source line #333	-> byte code offset #145
/*     */     //   Java source line #334	-> byte code offset #149
/*     */     //   Java source line #335	-> byte code offset #150
/*     */     //   Java source line #336	-> byte code offset #160
/*     */     //   Java source line #338	-> byte code offset #184
/*     */     //   Java source line #341	-> byte code offset #188
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	192	0	this	GameAction
/*     */     //   4	6	1	user	User
/*     */     //   13	45	2	username	String
/*     */     //   55	76	3	reader	Reader
/*     */     //   149	23	3	he	Exception
/*     */     //   81	3	4	pe	PGNException
/*     */     //   122	5	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   56	78	81	com/amicabile/openingtrainer/pgn/PGNException
/*     */     //   56	116	122	finally
/*     */     //   14	30	149	java/lang/Exception
/*     */     //   34	118	149	java/lang/Exception
/*     */     //   122	141	149	java/lang/Exception
/*     */   }
/*     */   
/*     */   public String showGameById()
/*     */   {
/*     */     try
/*     */     {
/* 347 */       this.chessGameGroup = new ChessGameGroup();
/* 348 */       GameRepository gameRepository = GameRepository.getInstance();
/* 349 */       GameDataObj gameDataObj = gameRepository.getGameDataObj(this.id);
/* 350 */       this.currentGameDataObj = gameDataObj;
/*     */       
/* 352 */       this.currentShowBoardRule = gameDataObj.getUser().getShowBoardRule();
/*     */       
/* 354 */       Game game = gameRepository.getGameForGameDataObj(this.id, gameDataObj);
/*     */       
/* 356 */       this.chessGameGroup.addChessGame((ChessGame)game);
/*     */       
/* 358 */       log.info("Successfully executed showGameById");
/* 359 */       return "success";
/*     */     } catch (Exception he) {
/* 361 */       log.error("Exception in showGameById", he);
/* 362 */       addActionError("Could not show game: " + he.getMessage());
/*     */     }
/* 364 */     return "error";
/*     */   }
/*     */   
/*     */   public String modifyPgnStringById()
/*     */   {
/*     */     try {
/* 370 */       GameRepository gameRepository = GameRepository.getInstance();
/* 371 */       GameDataObj gameDataObj = gameRepository.getGameDataObj(this.id);
/* 372 */       gameDataObj.setPgnstring(getPgnString());
/* 373 */       gameDataObj.setPublicgame(isPublicgame());
/* 374 */       gameRepository.updateAndFillGameDataObj(gameDataObj);
/* 375 */       MoveTreePool.getInstance().clearMoveTree(gameDataObj.getId().longValue());
/*     */       
/* 377 */       showGameById();
/*     */       
/* 379 */       return "success";
/*     */     } catch (Exception he) {
/* 381 */       log.error("Exception in modifyPgnStringById", he); }
/* 382 */     return "error";
/*     */   }
/*     */   
/*     */   public String showPgnStringById()
/*     */   {
/*     */     try {
/* 388 */       GameRepository gameRepository = GameRepository.getInstance();
/* 389 */       GameDataObj gameDataObj = gameRepository.getGameDataObj(this.id);
/* 390 */       setPgnString(gameDataObj.getPgnstring());
/* 391 */       setPublicgame(gameDataObj.isPublicgame());
/* 392 */       this.currentGameDataObj = gameDataObj;
/*     */       
/* 394 */       log.info("Successfully executed showPgnStringById");
/* 395 */       return "success";
/*     */     } catch (Exception he) {
/* 397 */       log.error("Exception in showPgnStringById", he); }
/* 398 */     return "error";
/*     */   }
/*     */   
/*     */   public String deleteGameById()
/*     */   {
/* 403 */     User user = getUser();
/* 404 */     if (user != null) {
/* 405 */       String username = user.getUsername();
/*     */       try
/*     */       {
/* 408 */         GameRepository.getInstance().deleteGame(username, this.id);
/* 409 */         log.info("Successfully executed deleteGameById");
/*     */         
/* 411 */         return "success";
/*     */       } catch (Exception he) {
/* 413 */         log.error("Exception in deleteGameById", he);
/* 414 */         return "error";
/*     */       }
/*     */     }
/* 417 */     return "login";
/*     */   }
/*     */   
/*     */ 
/*     */   public String switchGameStateById()
/*     */   {
/* 423 */     User user = getUser();
/* 424 */     if (user != null) {
/* 425 */       String username = user.getUsername();
/*     */       try
/*     */       {
/* 428 */         GameRepository.getInstance().switchPublicStateGame(username, this.id);
/* 429 */         log.info("Successfully executed switchPublicStateGame");
/*     */         
/* 431 */         return "success";
/*     */       } catch (Exception he) {
/* 433 */         log.error("Exception in switchPublicStateGame", he);
/* 434 */         return "error";
/*     */       }
/*     */     }
/* 437 */     return "login";
/*     */   }
/*     */   
/*     */ 
/*     */   private void fillChessGameGroupFromReader(String username, Reader reader, int maxgames)
/*     */     throws PGNException
/*     */   {
/* 444 */     this.chessGameGroup = PGNAdapter.getChessGameGroupFromStream(reader);
/* 445 */     if (this.chessGameGroup.getGameList().size() <= maxgames) {
/* 446 */       for (Game game : this.chessGameGroup.getGameList()) {
/* 447 */         long addGame = GameRepository.getInstance().addGame(game, 
/* 448 */           username, this.tag, this.publicgame);
/* 449 */         game.getGameInfo().getAuxilleryProperties().setProperty(
/* 450 */           "GameId", String.valueOf(addGame));
/*     */       }
/*     */       
/* 453 */       setChessGameGroup(this.chessGameGroup);
/*     */     }
/*     */     else {
/* 456 */       throw new PGNException("You cannot add more than " + 
/* 457 */         getUser().getMaxgames() + " games ");
/*     */     }
/*     */   }
/*     */   
/*     */   public GameDataObj getCurrentGameDataObj() {
/* 462 */     return this.currentGameDataObj;
/*     */   }
/*     */   
/*     */   public void setCurrentGameDataObj(GameDataObj currentGameDataObj) {
/* 466 */     this.currentGameDataObj = currentGameDataObj;
/*     */   }
/*     */   
/*     */   public String getTag() {
/* 470 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/* 474 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public boolean isPublicgame() {
/* 478 */     return this.publicgame;
/*     */   }
/*     */   
/*     */   public void setPublicgame(boolean publicgame) {
/* 482 */     this.publicgame = publicgame;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\servlet\webwork\games\GameAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */