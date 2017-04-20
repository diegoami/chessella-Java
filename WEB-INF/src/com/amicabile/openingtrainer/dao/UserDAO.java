/*     */ package com.amicabile.openingtrainer.dao;
/*     */ 
/*     */ import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
/*     */ import com.amicabile.openingtrainer.model.dataobj.User;
/*     */ import java.util.List;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserDAO
/*     */   extends GenericDAO
/*     */ {
/*  17 */   private static Logger log = Logger.getLogger(UserDAO.class.getName());
/*     */   
/*     */ 
/*     */ 
/*  21 */   private static UserDAO userDAO = new UserDAO();
/*     */   
/*     */   public static UserDAO getInstance() {
/*  24 */     return userDAO;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void updateUser(User user)
/*     */     throws HibernateException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: getstatic 24	com/amicabile/openingtrainer/dao/UserDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   3: new 42	java/lang/StringBuilder
/*     */     //   6: dup
/*     */     //   7: ldc 44
/*     */     //   9: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   12: aload_1
/*     */     //   13: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   16: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   19: invokevirtual 56	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   22: aload_0
/*     */     //   23: invokevirtual 60	com/amicabile/openingtrainer/dao/UserDAO:createSession	()Lnet/sf/hibernate/Session;
/*     */     //   26: astore_2
/*     */     //   27: aconst_null
/*     */     //   28: astore_3
/*     */     //   29: aload_2
/*     */     //   30: invokeinterface 64 1 0
/*     */     //   35: astore_3
/*     */     //   36: aload_2
/*     */     //   37: aload_1
/*     */     //   38: invokeinterface 70 2 0
/*     */     //   43: aload_3
/*     */     //   44: invokeinterface 73 1 0
/*     */     //   49: goto +54 -> 103
/*     */     //   52: astore 4
/*     */     //   54: aload_3
/*     */     //   55: ifnull +9 -> 64
/*     */     //   58: aload_3
/*     */     //   59: invokeinterface 78 1 0
/*     */     //   64: getstatic 24	com/amicabile/openingtrainer/dao/UserDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   67: new 42	java/lang/StringBuilder
/*     */     //   70: dup
/*     */     //   71: ldc 81
/*     */     //   73: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   76: aload_1
/*     */     //   77: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   80: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   83: aload 4
/*     */     //   85: invokevirtual 83	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   88: goto +25 -> 113
/*     */     //   91: astore 5
/*     */     //   93: aload_2
/*     */     //   94: invokeinterface 87 1 0
/*     */     //   99: pop
/*     */     //   100: aload 5
/*     */     //   102: athrow
/*     */     //   103: aload_2
/*     */     //   104: invokeinterface 87 1 0
/*     */     //   109: pop
/*     */     //   110: goto +10 -> 120
/*     */     //   113: aload_2
/*     */     //   114: invokeinterface 87 1 0
/*     */     //   119: pop
/*     */     //   120: return
/*     */     // Line number table:
/*     */     //   Java source line #28	-> byte code offset #0
/*     */     //   Java source line #30	-> byte code offset #22
/*     */     //   Java source line #31	-> byte code offset #27
/*     */     //   Java source line #33	-> byte code offset #29
/*     */     //   Java source line #35	-> byte code offset #36
/*     */     //   Java source line #36	-> byte code offset #43
/*     */     //   Java source line #38	-> byte code offset #52
/*     */     //   Java source line #39	-> byte code offset #54
/*     */     //   Java source line #40	-> byte code offset #58
/*     */     //   Java source line #42	-> byte code offset #64
/*     */     //   Java source line #44	-> byte code offset #91
/*     */     //   Java source line #45	-> byte code offset #93
/*     */     //   Java source line #46	-> byte code offset #100
/*     */     //   Java source line #45	-> byte code offset #103
/*     */     //   Java source line #46	-> byte code offset #110
/*     */     //   Java source line #45	-> byte code offset #113
/*     */     //   Java source line #49	-> byte code offset #120
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	121	0	this	UserDAO
/*     */     //   0	121	1	user	User
/*     */     //   26	88	2	session	Session
/*     */     //   28	31	3	tx	net.sf.hibernate.Transaction
/*     */     //   52	32	4	e	Exception
/*     */     //   91	10	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   29	49	52	java/lang/Exception
/*     */     //   29	91	91	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public User createUser(String username, String password, String email)
/*     */     throws HibernateException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual 103	com/amicabile/openingtrainer/dao/UserDAO:getUser	(Ljava/lang/String;)Lcom/amicabile/openingtrainer/model/dataobj/User;
/*     */     //   5: astore 4
/*     */     //   7: new 107	com/amicabile/openingtrainer/model/dataobj/User
/*     */     //   10: dup
/*     */     //   11: invokespecial 109	com/amicabile/openingtrainer/model/dataobj/User:<init>	()V
/*     */     //   14: astore 5
/*     */     //   16: aload 5
/*     */     //   18: aload_1
/*     */     //   19: invokevirtual 110	com/amicabile/openingtrainer/model/dataobj/User:setUsername	(Ljava/lang/String;)V
/*     */     //   22: aload 5
/*     */     //   24: aload_2
/*     */     //   25: invokevirtual 113	com/amicabile/openingtrainer/model/dataobj/User:setPassword	(Ljava/lang/String;)V
/*     */     //   28: aload 5
/*     */     //   30: aload_3
/*     */     //   31: invokevirtual 116	com/amicabile/openingtrainer/model/dataobj/User:setEmail	(Ljava/lang/String;)V
/*     */     //   34: aload 5
/*     */     //   36: getstatic 119	com/amicabile/openingtrainer/config/ShowBoardRulePrototypes:DEFAULT_RULE	Lcom/amicabile/openingtrainer/config/ShowBoardRule;
/*     */     //   39: invokevirtual 125	com/amicabile/openingtrainer/model/dataobj/User:setShowBoardRule	(Lcom/amicabile/openingtrainer/config/ShowBoardRule;)V
/*     */     //   42: aload_0
/*     */     //   43: invokevirtual 60	com/amicabile/openingtrainer/dao/UserDAO:createSession	()Lnet/sf/hibernate/Session;
/*     */     //   46: astore 6
/*     */     //   48: aconst_null
/*     */     //   49: astore 7
/*     */     //   51: aload 6
/*     */     //   53: invokeinterface 64 1 0
/*     */     //   58: astore 7
/*     */     //   60: aload 6
/*     */     //   62: aload 5
/*     */     //   64: invokeinterface 129 2 0
/*     */     //   69: pop
/*     */     //   70: getstatic 24	com/amicabile/openingtrainer/dao/UserDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   73: new 42	java/lang/StringBuilder
/*     */     //   76: dup
/*     */     //   77: ldc -123
/*     */     //   79: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   82: aload_1
/*     */     //   83: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   86: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   89: invokevirtual 56	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   92: aload 7
/*     */     //   94: invokeinterface 73 1 0
/*     */     //   99: goto +57 -> 156
/*     */     //   102: astore 8
/*     */     //   104: aload 7
/*     */     //   106: ifnull +10 -> 116
/*     */     //   109: aload 7
/*     */     //   111: invokeinterface 78 1 0
/*     */     //   116: getstatic 24	com/amicabile/openingtrainer/dao/UserDAO:log	Lorg/apache/log4j/Logger;
/*     */     //   119: new 42	java/lang/StringBuilder
/*     */     //   122: dup
/*     */     //   123: ldc -118
/*     */     //   125: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   128: aload_1
/*     */     //   129: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   132: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   135: aload 8
/*     */     //   137: invokevirtual 83	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
/*     */     //   140: goto +27 -> 167
/*     */     //   143: astore 9
/*     */     //   145: aload 6
/*     */     //   147: invokeinterface 87 1 0
/*     */     //   152: pop
/*     */     //   153: aload 9
/*     */     //   155: athrow
/*     */     //   156: aload 6
/*     */     //   158: invokeinterface 87 1 0
/*     */     //   163: pop
/*     */     //   164: goto +11 -> 175
/*     */     //   167: aload 6
/*     */     //   169: invokeinterface 87 1 0
/*     */     //   174: pop
/*     */     //   175: aload 5
/*     */     //   177: areturn
/*     */     // Line number table:
/*     */     //   Java source line #56	-> byte code offset #0
/*     */     //   Java source line #58	-> byte code offset #7
/*     */     //   Java source line #59	-> byte code offset #16
/*     */     //   Java source line #60	-> byte code offset #22
/*     */     //   Java source line #61	-> byte code offset #28
/*     */     //   Java source line #62	-> byte code offset #34
/*     */     //   Java source line #64	-> byte code offset #42
/*     */     //   Java source line #65	-> byte code offset #48
/*     */     //   Java source line #67	-> byte code offset #51
/*     */     //   Java source line #68	-> byte code offset #60
/*     */     //   Java source line #69	-> byte code offset #70
/*     */     //   Java source line #71	-> byte code offset #92
/*     */     //   Java source line #73	-> byte code offset #102
/*     */     //   Java source line #74	-> byte code offset #104
/*     */     //   Java source line #75	-> byte code offset #109
/*     */     //   Java source line #77	-> byte code offset #116
/*     */     //   Java source line #79	-> byte code offset #143
/*     */     //   Java source line #80	-> byte code offset #145
/*     */     //   Java source line #81	-> byte code offset #153
/*     */     //   Java source line #80	-> byte code offset #156
/*     */     //   Java source line #81	-> byte code offset #164
/*     */     //   Java source line #80	-> byte code offset #167
/*     */     //   Java source line #83	-> byte code offset #175
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	178	0	this	UserDAO
/*     */     //   0	178	1	username	String
/*     */     //   0	178	2	password	String
/*     */     //   0	178	3	email	String
/*     */     //   5	3	4	oldUser	User
/*     */     //   14	162	5	user	User
/*     */     //   46	122	6	session	Session
/*     */     //   49	61	7	tx	net.sf.hibernate.Transaction
/*     */     //   102	34	8	e	Exception
/*     */     //   143	11	9	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   51	99	102	java/lang/Exception
/*     */     //   51	143	143	finally
/*     */   }
/*     */   
/*     */   public User getUser(long id)
/*     */     throws HibernateException
/*     */   {
/*  89 */     Session session = createSession();
/*     */     try {
/*  91 */       User user = new User();
/*  92 */       session.load(user, Long.valueOf(id));
/*  93 */       return user;
/*     */     } finally {
/*  95 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<User> getAllUsers()
/*     */     throws HibernateException
/*     */   {
/* 104 */     Session session = createSession();
/*     */     try
/*     */     {
/* 107 */       Query query = session
/* 108 */         .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.AllUsers");
/* 109 */       List<User> querylist = query.list();
/* 110 */       return querylist;
/*     */     }
/*     */     finally {
/* 113 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public List<User> getAllSubmitters() throws HibernateException
/*     */   {
/* 119 */     Session session = createSession();
/*     */     try
/*     */     {
/* 122 */       Query query = session
/* 123 */         .getNamedQuery("com.amicabile.openingtrainer.model.dataobj.AllSubmitters");
/* 124 */       List<User> querylist = query.list();
/* 125 */       return querylist;
/*     */     }
/*     */     finally {
/* 128 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public User getUser(String username)
/*     */     throws HibernateException
/*     */   {
/* 135 */     Session session = createSession();
/*     */     try {
/* 137 */       User user = null;
/* 138 */       GameDataObj gameDataObj = new GameDataObj();
/* 139 */       Query query = session.getNamedQuery(
/* 140 */         "com.amicabile.openingtrainer.model.dataobj.UserByName");
/* 141 */       query.setString("username", username);
/* 142 */       List<User> queryList = query.list();
/* 143 */       if ((queryList != null) && (queryList.size() > 0)) {
/* 144 */         user = (User)queryList.get(0);
/*     */       }
/* 146 */       log.debug("Successfully retrieved user " + username);
/* 147 */       return user;
/*     */     }
/*     */     finally {
/* 150 */       session.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dao\UserDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */