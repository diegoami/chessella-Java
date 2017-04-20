/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import junit.framework.TestCase;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParserTest
/*     */   extends TestCase
/*     */ {
/*     */   public boolean debug;
/*     */   public String[] mesg;
/*     */   String filename;
/*     */   public ICSEventParser parser;
/*  43 */   String dataDir = "./data";
/*     */   
/*     */   public ParserTest(String packageName) throws IOException
/*     */   {
/*  47 */     String sysprop = packageName + ".dataDir";
/*  48 */     this.filename = getClass().getName();
/*     */     
/*  50 */     if (System.getProperty(sysprop) != null) {
/*  51 */       this.dataDir = System.getProperty(sysprop);
/*     */     }
/*     */     
/*  54 */     this.filename = 
/*  55 */       (this.filename.substring(this.filename.lastIndexOf('.') + 1, this.filename.length()) + ".data");
/*  56 */     this.filename = (this.dataDir + "/" + this.filename);
/*  57 */     this.mesg = processFile(new File(this.filename));
/*     */   }
/*     */   
/*     */   public void setUp() {}
/*     */   
/*     */   public void tearDown()
/*     */   {
/*  64 */     this.parser = null;
/*     */   }
/*     */   
/*     */   public static String[] processFile(File file)
/*     */     throws IOException
/*     */   {
/*  70 */     List list = new LinkedList();
/*     */     
/*  72 */     StringBuffer sb = new StringBuffer(80);
/*  73 */     String line = null;
/*  74 */     BufferedReader in = new BufferedReader(new FileReader(file));
/*     */     
/*  76 */     int lines = 0;
/*  77 */     while ((line = in.readLine()) != null) {
/*  78 */       if (line.startsWith("#")) {
/*  79 */         if (lines != 0) {
/*  80 */           list.add(sb.toString());
/*  81 */           sb = new StringBuffer(80);
/*  82 */           lines = 0;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  87 */         sb.append(line).append("\n");
/*  88 */         lines++;
/*     */       }
/*     */     }
/*  91 */     in.close();
/*     */     
/*  93 */     if (lines != 0) {
/*  94 */       list.add(sb.toString());
/*     */     }
/*     */     
/*  97 */     String[] mesg = new String[list.size()];
/*  98 */     for (int i = 0; i < list.size(); i++) {
/*  99 */       mesg[i] = ((String)list.get(i));
/*     */     }
/* 101 */     return mesg;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testParseAll()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore_1
/*     */     //   2: iconst_0
/*     */     //   3: istore_2
/*     */     //   4: goto +70 -> 74
/*     */     //   7: aload_0
/*     */     //   8: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   11: aload_0
/*     */     //   12: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   15: iload_2
/*     */     //   16: aaload
/*     */     //   17: invokevirtual 163	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*     */     //   20: astore_1
/*     */     //   21: aload_0
/*     */     //   22: getfield 169	ictk/boardgame/chess/net/ics/event/ParserTest:debug	Z
/*     */     //   25: ifeq +34 -> 59
/*     */     //   28: aload_1
/*     */     //   29: ifnonnull +30 -> 59
/*     */     //   32: getstatic 171	java/lang/System:err	Ljava/io/PrintStream;
/*     */     //   35: new 27	java/lang/StringBuilder
/*     */     //   38: dup
/*     */     //   39: ldc -81
/*     */     //   41: invokespecial 35	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   44: aload_0
/*     */     //   45: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   48: iload_2
/*     */     //   49: aaload
/*     */     //   50: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   53: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   56: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   59: aload_1
/*     */     //   60: ifnull +7 -> 67
/*     */     //   63: iconst_1
/*     */     //   64: goto +4 -> 68
/*     */     //   67: iconst_0
/*     */     //   68: invokestatic 182	ictk/boardgame/chess/net/ics/event/ParserTest:assertTrue	(Z)V
/*     */     //   71: iinc 2 1
/*     */     //   74: iload_2
/*     */     //   75: aload_0
/*     */     //   76: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   79: arraylength
/*     */     //   80: if_icmplt -73 -> 7
/*     */     //   83: goto +20 -> 103
/*     */     //   86: astore_3
/*     */     //   87: getstatic 186	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*     */     //   90: invokestatic 190	ictk/util/Log:removeMask	(J)V
/*     */     //   93: aload_0
/*     */     //   94: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   97: iconst_0
/*     */     //   98: invokevirtual 196	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*     */     //   101: aload_3
/*     */     //   102: athrow
/*     */     //   103: getstatic 186	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*     */     //   106: invokestatic 190	ictk/util/Log:removeMask	(J)V
/*     */     //   109: aload_0
/*     */     //   110: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   113: iconst_0
/*     */     //   114: invokevirtual 196	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*     */     //   117: return
/*     */     // Line number table:
/*     */     //   Java source line #116	-> byte code offset #0
/*     */     //   Java source line #118	-> byte code offset #2
/*     */     //   Java source line #119	-> byte code offset #7
/*     */     //   Java source line #120	-> byte code offset #21
/*     */     //   Java source line #121	-> byte code offset #32
/*     */     //   Java source line #123	-> byte code offset #59
/*     */     //   Java source line #118	-> byte code offset #71
/*     */     //   Java source line #126	-> byte code offset #86
/*     */     //   Java source line #127	-> byte code offset #87
/*     */     //   Java source line #128	-> byte code offset #93
/*     */     //   Java source line #129	-> byte code offset #101
/*     */     //   Java source line #127	-> byte code offset #103
/*     */     //   Java source line #128	-> byte code offset #109
/*     */     //   Java source line #130	-> byte code offset #117
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	118	0	this	ParserTest
/*     */     //   1	59	1	evt	ICSEvent
/*     */     //   3	72	2	i	int
/*     */     //   86	16	3	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   2	86	86	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testNative()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 169	ictk/boardgame/chess/net/ics/event/ParserTest:debug	Z
/*     */     //   4: ifeq +17 -> 21
/*     */     //   7: getstatic 186	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*     */     //   10: invokestatic 202	ictk/util/Log:addMask	(J)V
/*     */     //   13: aload_0
/*     */     //   14: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   17: iconst_1
/*     */     //   18: invokevirtual 196	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*     */     //   21: aconst_null
/*     */     //   22: astore_1
/*     */     //   23: aconst_null
/*     */     //   24: astore_2
/*     */     //   25: iconst_0
/*     */     //   26: istore_3
/*     */     //   27: goto +169 -> 196
/*     */     //   30: aload_0
/*     */     //   31: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   34: aload_0
/*     */     //   35: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   38: iload_3
/*     */     //   39: aaload
/*     */     //   40: invokevirtual 163	ictk/boardgame/chess/net/ics/event/ICSEventParser:createICSEvent	(Ljava/lang/CharSequence;)Lictk/boardgame/chess/net/ics/event/ICSEvent;
/*     */     //   43: astore_1
/*     */     //   44: aload_1
/*     */     //   45: ifnull +7 -> 52
/*     */     //   48: iconst_1
/*     */     //   49: goto +4 -> 53
/*     */     //   52: iconst_0
/*     */     //   53: invokestatic 182	ictk/boardgame/chess/net/ics/event/ParserTest:assertTrue	(Z)V
/*     */     //   56: new 27	java/lang/StringBuilder
/*     */     //   59: dup
/*     */     //   60: aload_0
/*     */     //   61: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   64: aload_1
/*     */     //   65: invokevirtual 205	ictk/boardgame/chess/net/ics/event/ICSEventParser:toNative	(Lictk/boardgame/chess/net/ics/event/ICSEvent;)Ljava/lang/String;
/*     */     //   68: invokestatic 29	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   71: invokespecial 35	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   74: ldc -121
/*     */     //   76: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   79: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   82: astore_2
/*     */     //   83: aload_0
/*     */     //   84: getfield 169	ictk/boardgame/chess/net/ics/event/ParserTest:debug	Z
/*     */     //   87: ifeq +93 -> 180
/*     */     //   90: aload_2
/*     */     //   91: aload_0
/*     */     //   92: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   95: iload_3
/*     */     //   96: aaload
/*     */     //   97: invokevirtual 209	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   100: ifne +80 -> 180
/*     */     //   103: getstatic 212	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   106: new 27	java/lang/StringBuilder
/*     */     //   109: dup
/*     */     //   110: ldc -41
/*     */     //   112: invokespecial 35	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   115: iload_3
/*     */     //   116: invokevirtual 217	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   119: ldc -36
/*     */     //   121: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   124: aload_0
/*     */     //   125: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   128: iload_3
/*     */     //   129: aaload
/*     */     //   130: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   133: ldc -34
/*     */     //   135: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   138: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   141: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   144: getstatic 212	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   147: new 27	java/lang/StringBuilder
/*     */     //   150: dup
/*     */     //   151: ldc -32
/*     */     //   153: invokespecial 35	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   156: iload_3
/*     */     //   157: invokevirtual 217	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   160: ldc -36
/*     */     //   162: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   165: aload_2
/*     */     //   166: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   169: ldc -34
/*     */     //   171: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   174: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   177: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   180: aload_2
/*     */     //   181: aload_0
/*     */     //   182: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   185: iload_3
/*     */     //   186: aaload
/*     */     //   187: invokevirtual 209	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   190: invokestatic 182	ictk/boardgame/chess/net/ics/event/ParserTest:assertTrue	(Z)V
/*     */     //   193: iinc 3 1
/*     */     //   196: iload_3
/*     */     //   197: aload_0
/*     */     //   198: getfield 89	ictk/boardgame/chess/net/ics/event/ParserTest:mesg	[Ljava/lang/String;
/*     */     //   201: arraylength
/*     */     //   202: if_icmplt -172 -> 30
/*     */     //   205: goto +22 -> 227
/*     */     //   208: astore 4
/*     */     //   210: getstatic 186	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*     */     //   213: invokestatic 190	ictk/util/Log:removeMask	(J)V
/*     */     //   216: aload_0
/*     */     //   217: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   220: iconst_0
/*     */     //   221: invokevirtual 196	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*     */     //   224: aload 4
/*     */     //   226: athrow
/*     */     //   227: getstatic 186	ictk/boardgame/chess/net/ics/event/ICSEventParser:DEBUG	J
/*     */     //   230: invokestatic 190	ictk/util/Log:removeMask	(J)V
/*     */     //   233: aload_0
/*     */     //   234: getfield 99	ictk/boardgame/chess/net/ics/event/ParserTest:parser	Lictk/boardgame/chess/net/ics/event/ICSEventParser;
/*     */     //   237: iconst_0
/*     */     //   238: invokevirtual 196	ictk/boardgame/chess/net/ics/event/ICSEventParser:setDebug	(Z)V
/*     */     //   241: return
/*     */     // Line number table:
/*     */     //   Java source line #137	-> byte code offset #0
/*     */     //   Java source line #138	-> byte code offset #7
/*     */     //   Java source line #139	-> byte code offset #13
/*     */     //   Java source line #143	-> byte code offset #21
/*     */     //   Java source line #144	-> byte code offset #23
/*     */     //   Java source line #145	-> byte code offset #25
/*     */     //   Java source line #146	-> byte code offset #30
/*     */     //   Java source line #147	-> byte code offset #44
/*     */     //   Java source line #148	-> byte code offset #56
/*     */     //   Java source line #150	-> byte code offset #83
/*     */     //   Java source line #151	-> byte code offset #103
/*     */     //   Java source line #152	-> byte code offset #144
/*     */     //   Java source line #154	-> byte code offset #180
/*     */     //   Java source line #145	-> byte code offset #193
/*     */     //   Java source line #157	-> byte code offset #208
/*     */     //   Java source line #158	-> byte code offset #210
/*     */     //   Java source line #159	-> byte code offset #216
/*     */     //   Java source line #160	-> byte code offset #224
/*     */     //   Java source line #158	-> byte code offset #227
/*     */     //   Java source line #159	-> byte code offset #233
/*     */     //   Java source line #161	-> byte code offset #241
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	242	0	this	ParserTest
/*     */     //   22	43	1	evt	ICSEvent
/*     */     //   24	157	2	nativeStr	String
/*     */     //   26	171	3	i	int
/*     */     //   208	17	4	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   21	208	208	finally
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ParserTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */