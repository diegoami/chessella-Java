/*     */ package ictk.boardgame.chess.net.ics.fics;
/*     */ 
/*     */ import free.freechess.timeseal.TimesealingSocket;
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.ICSEventRouter;
/*     */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSBoardUpdateStyle12Parser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSChallengeParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameCreatedParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameNotificationParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSMoveListParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerConnectionParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerNotificationParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdReadableParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekClearParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekRemoveParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSTellParser;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.CharBuffer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class FICSProtocolHandler
/*     */   extends ICSProtocolHandler
/*     */ {
/*     */   public static final int SOCKET_TIMEOUT = 180000;
/*  57 */   protected int SOCKET_DELAY = 1000;
/*     */   
/*     */ 
/*  60 */   protected String LOGIN_PROMPT = "login:";
/*  61 */   protected String PASSWD_PROMPT = "password:";
/*  62 */   protected String CMD_PROMPT = "\nfics% ";
/*  63 */   protected String GUEST_PROMPT = "Press return to enter the server as \"";
/*  64 */   protected String START_SESSION = "**** Starting FICS session as ";
/*  65 */   protected String INVALID_PASSWD = "**** Invalid password! ****";
/*     */   
/*  67 */   protected String INTERFACE_NAME = "-=[ ictk ]=- v0.2 http://ictk.sourceforge.net";
/*     */   
/*     */ 
/*     */ 
/*     */   protected static final String REGEX_handle = "([\\w]+)";
/*     */   
/*     */ 
/*     */ 
/*     */   protected static final String REGEX_acct_type = "(\\(\\S*\\))?";
/*     */   
/*     */ 
/*     */   protected final ICSEventParser[] eventFactories;
/*     */   
/*     */ 
/*  81 */   boolean isBlockMode = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  86 */   int BUFFER_SIZE = 134144;
/*     */   
/*     */ 
/*  89 */   CharBuffer buffer = CharBuffer.allocate(this.BUFFER_SIZE);
/*     */   
/*     */   public FICSProtocolHandler()
/*     */   {
/*  93 */     this.host = "64.71.131.140";
/*  94 */     this.port = 5000;
/*     */     
/*  96 */     int i = 0;
/*  97 */     this.eventFactories = new ICSEventParser[16];
/*  98 */     this.eventFactories[(i++)] = FICSBoardUpdateStyle12Parser.getInstance();
/*  99 */     this.eventFactories[(i++)] = FICSMoveListParser.getInstance();
/* 100 */     this.eventFactories[(i++)] = FICSTellParser.getInstance();
/* 101 */     this.eventFactories[(i++)] = FICSKibitzParser.getInstance();
/* 102 */     this.eventFactories[(i++)] = FICSChannelParser.getInstance();
/* 103 */     this.eventFactories[(i++)] = FICSShoutParser.getInstance();
/* 104 */     this.eventFactories[(i++)] = FICSGameResultParser.getInstance();
/* 105 */     this.eventFactories[(i++)] = FICSGameCreatedParser.getInstance();
/* 106 */     this.eventFactories[(i++)] = FICSPlayerConnectionParser.getInstance();
/* 107 */     this.eventFactories[(i++)] = FICSPlayerNotificationParser.getInstance();
/* 108 */     this.eventFactories[(i++)] = FICSGameNotificationParser.getInstance();
/* 109 */     this.eventFactories[(i++)] = FICSSeekClearParser.getInstance();
/* 110 */     this.eventFactories[(i++)] = FICSSeekAdParser.getInstance();
/* 111 */     this.eventFactories[(i++)] = FICSSeekRemoveParser.getInstance();
/* 112 */     this.eventFactories[(i++)] = FICSSeekAdReadableParser.getInstance();
/* 113 */     this.eventFactories[(i++)] = FICSChallengeParser.getInstance();
/*     */     
/* 115 */     this.router = new ICSEventRouter();
/*     */   }
/*     */   
/*     */   public FICSProtocolHandler(String host, int port) {
/* 119 */     this();
/* 120 */     this.host = host;
/* 121 */     this.port = port;
/*     */   }
/*     */   
/*     */ 
/*     */   public void connect()
/*     */     throws UnknownHostException, IOException
/*     */   {
/* 128 */     if ((this.handle == null) || (this.passwd == null)) {
/* 129 */       throw new IllegalStateException(
/* 130 */         "Both handle and password must be set before login");
/*     */     }
/* 132 */     if (this.isLagCompensated) {
/* 133 */       this.socket = new TimesealingSocket(this.host, this.port);
/*     */     } else {
/* 135 */       this.socket = new Socket(this.host, this.port);
/*     */     }
/*     */     try
/*     */     {
/* 139 */       this.socket.setKeepAlive(true);
/*     */     }
/*     */     catch (SocketException e) {
/* 142 */       Log.error(6, e.getMessage());
/*     */     }
/*     */     
/*     */ 
/* 146 */     this.in = new InputStreamReader(this.socket.getInputStream());
/* 147 */     this.out = new PrintWriter(this.socket.getOutputStream());
/* 148 */     this.thread.start();
/*     */   }
/*     */   
/*     */   public void run()
/*     */   {
/*     */     try {
/* 154 */       this.isLoggedIn = doLogin();
/* 155 */       if (this.isLoggedIn) {
/* 156 */         setLoginVars();
/* 157 */         processServerOutput();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 163 */       if (this.socket != null) this.socket.isClosed();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 167 */       e.printStackTrace();
/*     */     }
/*     */     
/* 170 */     dispatchConnectionEvent(new ICSConnectionEvent(this));
/*     */   }
/*     */   
/*     */   protected boolean doLogin() throws IOException
/*     */   {
/* 175 */     boolean seenLogin = false;
/* 176 */     boolean seenPasswd = false;
/* 177 */     String tmp = null;
/* 178 */     int b = 0;
/* 179 */     char c = ' ';
/* 180 */     int mark = 0;
/*     */     
/* 182 */     Matcher match = null;
/*     */     
/* 184 */     Pattern REGEX_sessionStart = Pattern.compile(
/* 185 */       "^\\*\\*\\*\\* Starting FICS session as ([\\w]+)(\\(\\S*\\))? \\*\\*\\*\\*", 
/*     */       
/*     */ 
/*     */ 
/* 189 */       8);
/*     */     
/* 191 */     while ((b = this.in.read()) != -1)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 196 */       if ((b == 10) || (b == 13) || ((b >= 32) && (b <= 126)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 207 */         c = (char)b;
/*     */         
/* 209 */         if (c != '\r')
/*     */         {
/* 211 */           this.buffer.put(c);
/*     */         }
/*     */         
/* 214 */         if ((c == '\n') && (!seenPasswd)) {
/* 215 */           this.buffer.limit(this.buffer.position());
/* 216 */           this.buffer.rewind();
/* 217 */           System.out.print(this.buffer.toString());
/* 218 */           System.out.flush();
/* 219 */           this.buffer.clear();
/*     */ 
/*     */ 
/*     */         }
/* 223 */         else if (c == ':') {
/* 224 */           mark = this.buffer.position();
/* 225 */           this.buffer.limit(mark);
/* 226 */           this.buffer.rewind();
/* 227 */           tmp = this.buffer.toString();
/*     */           
/*     */ 
/* 230 */           if ((!seenLogin) && 
/* 231 */             (tmp.lastIndexOf(this.LOGIN_PROMPT) > -1)) {
/* 232 */             System.out.print(tmp);
/* 233 */             System.out.print(" ");
/* 234 */             System.out.flush();
/* 235 */             this.buffer.rewind();
/* 236 */             this.buffer.clear();
/*     */             
/* 238 */             sendCommand(this.handle);
/* 239 */             seenLogin = true;
/*     */ 
/*     */ 
/*     */           }
/* 243 */           else if ((seenLogin) && (!seenPasswd) && 
/* 244 */             (tmp.lastIndexOf(this.PASSWD_PROMPT) > -1)) {
/* 245 */             System.out.print(tmp);
/* 246 */             System.out.print(" ");
/* 247 */             System.out.flush();
/* 248 */             this.buffer.rewind();
/* 249 */             this.buffer.clear();
/*     */             
/* 251 */             sendCommand(this.passwd, false);
/* 252 */             seenPasswd = true;
/* 253 */             System.out.println();
/*     */ 
/*     */ 
/*     */           }
/* 257 */           else if ((seenLogin) && (!seenPasswd) && 
/* 258 */             (tmp.lastIndexOf(this.GUEST_PROMPT) > -1)) {
/* 259 */             System.out.print(tmp);
/* 260 */             System.out.flush();
/* 261 */             this.buffer.rewind();
/* 262 */             this.buffer.clear();
/*     */             
/* 264 */             sendCommand("");
/* 265 */             seenPasswd = true;
/*     */           }
/*     */           else {
/* 268 */             this.buffer.limit(this.buffer.capacity());
/* 269 */             this.buffer.position(mark);
/*     */           }
/*     */           
/*     */ 
/*     */         }
/* 274 */         else if ((c == '\n') && (seenPasswd)) {
/* 275 */           mark = this.buffer.position();
/* 276 */           this.buffer.limit(mark);
/* 277 */           this.buffer.rewind();
/* 278 */           tmp = this.buffer.toString();
/*     */           
/*     */ 
/* 281 */           if (tmp.lastIndexOf(this.INVALID_PASSWD) > -1) {
/* 282 */             System.out.print(tmp);
/* 283 */             System.out.flush();
/* 284 */             this.buffer.rewind();
/* 285 */             this.buffer.clear();
/*     */             
/* 287 */             return false;
/*     */           }
/*     */           
/*     */ 
/* 291 */           if (tmp.lastIndexOf(this.START_SESSION) > -1) {
/* 292 */             match = REGEX_sessionStart.matcher(tmp);
/* 293 */             if (match.find()) {
/* 294 */               this.handle = match.group(1);
/*     */               try {
/* 296 */                 if (match.group(2) == null) {
/* 297 */                   this.acctType = new ICSAccountType();
/*     */                 } else {
/* 299 */                   this.acctType = new ICSAccountType(match.group(2));
/*     */                 }
/*     */               } catch (IOException e) {
/* 302 */                 Log.error(2, 
/* 303 */                   "On Login: " + e.getMessage());
/*     */               }
/*     */             }
/*     */             else {
/* 307 */               Log.error(2, 
/* 308 */                 "On Login: never matched session start: " + 
/* 309 */                 tmp);
/*     */             }
/* 311 */             System.out.print(tmp);
/* 312 */             System.out.flush();
/* 313 */             this.buffer.rewind();
/* 314 */             this.buffer.clear();
/*     */             
/* 316 */             return true;
/*     */           }
/*     */           
/*     */ 
/* 320 */           this.buffer.limit(this.buffer.capacity());
/* 321 */           this.buffer.position(mark);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 326 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setLoginVars()
/*     */   {
/* 336 */     sendCommand("set prompt", false);
/* 337 */     sendCommand("set style 12", false);
/* 338 */     sendCommand("iset ms 1", false);
/* 339 */     sendCommand("set interface " + this.INTERFACE_NAME, false);
/* 340 */     sendCommand("set bell 0", false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void processServerOutput()
/*     */   {
/* 348 */     if (this.isBlockMode) {
/* 349 */       chunkByBlockMode();
/*     */     } else {
/* 351 */       chunkByPrompt();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void chunkByPrompt()
/*     */   {
/* 362 */     int b = -1;
/* 363 */     char c = ' ';
/* 364 */     byte ptr = 0;
/* 365 */     char[] prompt = this.CMD_PROMPT.toCharArray();
/*     */     
/*     */     try
/*     */     {
/* 369 */       while ((b = this.in.read()) != -1)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 375 */         if ((b == 10) || (b == 13) || ((b >= 32) && (b <= 126)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 385 */           c = (char)b;
/*     */           
/* 387 */           if (c != '\r') {
/* 388 */             this.buffer.put(c);
/*     */             
/* 390 */             if (c == prompt[ptr]) {
/* 391 */               ptr = (byte)(ptr + 1);
/* 392 */               if (prompt.length == ptr) {
/* 393 */                 this.buffer.limit(this.buffer.position() - prompt.length);
/* 394 */                 this.buffer.rewind();
/*     */                 
/*     */ 
/* 397 */                 parse(this.buffer);
/*     */                 
/* 399 */                 this.buffer.clear();
/* 400 */                 ptr = 0;
/*     */               }
/*     */             }
/* 403 */             else if ((ptr != 1) || (c != prompt[0]))
/*     */             {
/*     */ 
/*     */ 
/* 407 */               ptr = 0;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 413 */       if (this.buffer.position() > 0) {
/* 414 */         this.buffer.limit(this.buffer.position());
/* 415 */         this.buffer.rewind();
/* 416 */         parse(this.buffer);
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 420 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   protected void chunkByBlockMode()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: iconst_0
/*     */     //   3: istore_2
/*     */     //   4: iconst_0
/*     */     //   5: istore_3
/*     */     //   6: bipush 32
/*     */     //   8: istore 4
/*     */     //   10: bipush 32
/*     */     //   12: istore 5
/*     */     //   14: iconst_0
/*     */     //   15: istore 6
/*     */     //   17: bipush 6
/*     */     //   19: invokestatic 83	java/nio/CharBuffer:allocate	(I)Ljava/nio/CharBuffer;
/*     */     //   22: astore 7
/*     */     //   24: iconst_3
/*     */     //   25: invokestatic 83	java/nio/CharBuffer:allocate	(I)Ljava/nio/CharBuffer;
/*     */     //   28: astore 8
/*     */     //   30: aload_0
/*     */     //   31: getfield 60	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:CMD_PROMPT	Ljava/lang/String;
/*     */     //   34: invokevirtual 412	java/lang/String:toCharArray	()[C
/*     */     //   37: astore 9
/*     */     //   39: goto +620 -> 659
/*     */     //   42: iload 5
/*     */     //   44: bipush 21
/*     */     //   46: if_icmpeq +10 -> 56
/*     */     //   49: iload 5
/*     */     //   51: bipush 22
/*     */     //   53: if_icmpne +11 -> 64
/*     */     //   56: iload_1
/*     */     //   57: iconst_1
/*     */     //   58: iadd
/*     */     //   59: i2s
/*     */     //   60: istore_1
/*     */     //   61: goto +598 -> 659
/*     */     //   64: iload 5
/*     */     //   66: bipush 23
/*     */     //   68: if_icmpne +285 -> 353
/*     */     //   71: aload 7
/*     */     //   73: aload 7
/*     */     //   75: invokevirtual 293	java/nio/CharBuffer:position	()I
/*     */     //   78: invokevirtual 297	java/nio/CharBuffer:limit	(I)Ljava/nio/Buffer;
/*     */     //   81: pop
/*     */     //   82: aload 7
/*     */     //   84: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   87: pop
/*     */     //   88: aload 8
/*     */     //   90: aload 8
/*     */     //   92: invokevirtual 293	java/nio/CharBuffer:position	()I
/*     */     //   95: invokevirtual 297	java/nio/CharBuffer:limit	(I)Ljava/nio/Buffer;
/*     */     //   98: pop
/*     */     //   99: aload 8
/*     */     //   101: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   104: pop
/*     */     //   105: aload_0
/*     */     //   106: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   109: aload_0
/*     */     //   110: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   113: invokevirtual 293	java/nio/CharBuffer:position	()I
/*     */     //   116: invokevirtual 297	java/nio/CharBuffer:limit	(I)Ljava/nio/Buffer;
/*     */     //   119: pop
/*     */     //   120: aload_0
/*     */     //   121: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   124: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   127: pop
/*     */     //   128: aload 7
/*     */     //   130: invokevirtual 310	java/nio/CharBuffer:toString	()Ljava/lang/String;
/*     */     //   133: invokestatic 424	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   136: istore_2
/*     */     //   137: aload 8
/*     */     //   139: invokevirtual 310	java/nio/CharBuffer:toString	()Ljava/lang/String;
/*     */     //   142: invokestatic 424	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   145: istore_3
/*     */     //   146: goto +131 -> 277
/*     */     //   149: astore 10
/*     */     //   151: aload 7
/*     */     //   153: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   156: pop
/*     */     //   157: aload 8
/*     */     //   159: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   162: pop
/*     */     //   163: iconst_2
/*     */     //   164: new 369	java/lang/StringBuilder
/*     */     //   167: dup
/*     */     //   168: ldc_w 429
/*     */     //   171: invokespecial 373	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   174: iload_1
/*     */     //   175: invokevirtual 431	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   178: ldc_w 434
/*     */     //   181: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   184: ldc_w 436
/*     */     //   187: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   190: aload 7
/*     */     //   192: invokevirtual 310	java/nio/CharBuffer:toString	()Ljava/lang/String;
/*     */     //   195: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   198: ldc_w 438
/*     */     //   201: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   204: aload 8
/*     */     //   206: invokevirtual 310	java/nio/CharBuffer:toString	()Ljava/lang/String;
/*     */     //   209: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   212: ldc_w 440
/*     */     //   215: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   218: aload_0
/*     */     //   219: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   222: invokevirtual 310	java/nio/CharBuffer:toString	()Ljava/lang/String;
/*     */     //   225: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   228: invokevirtual 379	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   231: invokestatic 209	ictk/util/Log:error	(ILjava/lang/String;)V
/*     */     //   234: goto +81 -> 315
/*     */     //   237: astore 11
/*     */     //   239: aload_0
/*     */     //   240: iload_2
/*     */     //   241: iload_3
/*     */     //   242: aload_0
/*     */     //   243: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   246: invokespecial 442	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:parseResponse	(IILjava/lang/CharSequence;)V
/*     */     //   249: aload 7
/*     */     //   251: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   254: pop
/*     */     //   255: aload 8
/*     */     //   257: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   260: pop
/*     */     //   261: aload_0
/*     */     //   262: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   265: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   268: pop
/*     */     //   269: iconst_0
/*     */     //   270: istore_1
/*     */     //   271: iconst_0
/*     */     //   272: istore 6
/*     */     //   274: aload 11
/*     */     //   276: athrow
/*     */     //   277: aload_0
/*     */     //   278: iload_2
/*     */     //   279: iload_3
/*     */     //   280: aload_0
/*     */     //   281: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   284: invokespecial 442	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:parseResponse	(IILjava/lang/CharSequence;)V
/*     */     //   287: aload 7
/*     */     //   289: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   292: pop
/*     */     //   293: aload 8
/*     */     //   295: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   298: pop
/*     */     //   299: aload_0
/*     */     //   300: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   303: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   306: pop
/*     */     //   307: iconst_0
/*     */     //   308: istore_1
/*     */     //   309: iconst_0
/*     */     //   310: istore 6
/*     */     //   312: goto +347 -> 659
/*     */     //   315: aload_0
/*     */     //   316: iload_2
/*     */     //   317: iload_3
/*     */     //   318: aload_0
/*     */     //   319: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   322: invokespecial 442	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:parseResponse	(IILjava/lang/CharSequence;)V
/*     */     //   325: aload 7
/*     */     //   327: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   330: pop
/*     */     //   331: aload 8
/*     */     //   333: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   336: pop
/*     */     //   337: aload_0
/*     */     //   338: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   341: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   344: pop
/*     */     //   345: iconst_0
/*     */     //   346: istore_1
/*     */     //   347: iconst_0
/*     */     //   348: istore 6
/*     */     //   350: goto +309 -> 659
/*     */     //   353: iload 5
/*     */     //   355: bipush 10
/*     */     //   357: if_icmpeq +87 -> 444
/*     */     //   360: iload 5
/*     */     //   362: bipush 13
/*     */     //   364: if_icmpeq +80 -> 444
/*     */     //   367: iload 5
/*     */     //   369: bipush 32
/*     */     //   371: if_icmplt +10 -> 381
/*     */     //   374: iload 5
/*     */     //   376: bipush 126
/*     */     //   378: if_icmple +66 -> 444
/*     */     //   381: new 369	java/lang/StringBuilder
/*     */     //   384: dup
/*     */     //   385: ldc_w 446
/*     */     //   388: invokespecial 373	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   391: iload 5
/*     */     //   393: invokevirtual 431	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   396: ldc_w 448
/*     */     //   399: invokevirtual 375	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   402: invokevirtual 379	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   405: astore 10
/*     */     //   407: iconst_0
/*     */     //   408: istore 11
/*     */     //   410: goto +21 -> 431
/*     */     //   413: aload_0
/*     */     //   414: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   417: aload 10
/*     */     //   419: iload 11
/*     */     //   421: invokevirtual 450	java/lang/String:charAt	(I)C
/*     */     //   424: invokevirtual 289	java/nio/CharBuffer:put	(C)Ljava/nio/CharBuffer;
/*     */     //   427: pop
/*     */     //   428: iinc 11 1
/*     */     //   431: iload 11
/*     */     //   433: aload 10
/*     */     //   435: invokevirtual 454	java/lang/String:length	()I
/*     */     //   438: if_icmplt -25 -> 413
/*     */     //   441: goto +218 -> 659
/*     */     //   444: iload 5
/*     */     //   446: i2c
/*     */     //   447: istore 4
/*     */     //   449: iload_1
/*     */     //   450: tableswitch	default:+169->619, 0:+72->522, 1:+30->480, 2:+41->491, 3:+52->502
/*     */     //   480: aload 7
/*     */     //   482: iload 4
/*     */     //   484: invokevirtual 289	java/nio/CharBuffer:put	(C)Ljava/nio/CharBuffer;
/*     */     //   487: pop
/*     */     //   488: goto +171 -> 659
/*     */     //   491: aload 8
/*     */     //   493: iload 4
/*     */     //   495: invokevirtual 289	java/nio/CharBuffer:put	(C)Ljava/nio/CharBuffer;
/*     */     //   498: pop
/*     */     //   499: goto +160 -> 659
/*     */     //   502: iload 4
/*     */     //   504: bipush 13
/*     */     //   506: if_icmpeq +153 -> 659
/*     */     //   509: aload_0
/*     */     //   510: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   513: iload 4
/*     */     //   515: invokevirtual 289	java/nio/CharBuffer:put	(C)Ljava/nio/CharBuffer;
/*     */     //   518: pop
/*     */     //   519: goto +140 -> 659
/*     */     //   522: iload 4
/*     */     //   524: bipush 13
/*     */     //   526: if_icmpeq +133 -> 659
/*     */     //   529: aload_0
/*     */     //   530: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   533: iload 4
/*     */     //   535: invokevirtual 289	java/nio/CharBuffer:put	(C)Ljava/nio/CharBuffer;
/*     */     //   538: pop
/*     */     //   539: iload 4
/*     */     //   541: aload 9
/*     */     //   543: iload 6
/*     */     //   545: caload
/*     */     //   546: if_icmpne +67 -> 613
/*     */     //   549: iload 6
/*     */     //   551: iconst_1
/*     */     //   552: iadd
/*     */     //   553: i2s
/*     */     //   554: istore 6
/*     */     //   556: aload 9
/*     */     //   558: arraylength
/*     */     //   559: iload 6
/*     */     //   561: if_icmpne +98 -> 659
/*     */     //   564: aload_0
/*     */     //   565: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   568: aload_0
/*     */     //   569: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   572: invokevirtual 293	java/nio/CharBuffer:position	()I
/*     */     //   575: aload 9
/*     */     //   577: arraylength
/*     */     //   578: isub
/*     */     //   579: invokevirtual 297	java/nio/CharBuffer:limit	(I)Ljava/nio/Buffer;
/*     */     //   582: pop
/*     */     //   583: aload_0
/*     */     //   584: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   587: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   590: pop
/*     */     //   591: aload_0
/*     */     //   592: aload_0
/*     */     //   593: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   596: invokevirtual 416	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:parse	(Ljava/lang/CharSequence;)V
/*     */     //   599: aload_0
/*     */     //   600: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   603: invokevirtual 321	java/nio/CharBuffer:clear	()Ljava/nio/Buffer;
/*     */     //   606: pop
/*     */     //   607: iconst_0
/*     */     //   608: istore 6
/*     */     //   610: goto +49 -> 659
/*     */     //   613: iconst_0
/*     */     //   614: istore 6
/*     */     //   616: goto +43 -> 659
/*     */     //   619: iconst_2
/*     */     //   620: new 369	java/lang/StringBuilder
/*     */     //   623: dup
/*     */     //   624: ldc_w 457
/*     */     //   627: invokespecial 373	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   630: iload_1
/*     */     //   631: invokevirtual 431	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   634: invokevirtual 379	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   637: invokestatic 209	ictk/util/Log:error	(ILjava/lang/String;)V
/*     */     //   640: iconst_0
/*     */     //   641: istore_1
/*     */     //   642: getstatic 41	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:$assertionsDisabled	Z
/*     */     //   645: ifne +14 -> 659
/*     */     //   648: new 459	java/lang/AssertionError
/*     */     //   651: dup
/*     */     //   652: ldc_w 461
/*     */     //   655: invokespecial 463	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
/*     */     //   658: athrow
/*     */     //   659: aload_0
/*     */     //   660: getfield 224	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:in	Ljava/io/InputStreamReader;
/*     */     //   663: invokevirtual 382	java/io/InputStreamReader:read	()I
/*     */     //   666: dup
/*     */     //   667: istore 5
/*     */     //   669: iconst_m1
/*     */     //   670: if_icmpne -628 -> 42
/*     */     //   673: aload_0
/*     */     //   674: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   677: invokevirtual 293	java/nio/CharBuffer:position	()I
/*     */     //   680: ifle +29 -> 709
/*     */     //   683: aload_0
/*     */     //   684: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   687: invokevirtual 301	java/nio/CharBuffer:rewind	()Ljava/nio/Buffer;
/*     */     //   690: pop
/*     */     //   691: aload_0
/*     */     //   692: aload_0
/*     */     //   693: getfield 89	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:buffer	Ljava/nio/CharBuffer;
/*     */     //   696: invokevirtual 416	ictk/boardgame/chess/net/ics/fics/FICSProtocolHandler:parse	(Ljava/lang/CharSequence;)V
/*     */     //   699: goto +10 -> 709
/*     */     //   702: astore 10
/*     */     //   704: aload 10
/*     */     //   706: invokevirtual 268	java/io/IOException:printStackTrace	()V
/*     */     //   709: return
/*     */     // Line number table:
/*     */     //   Java source line #433	-> byte code offset #0
/*     */     //   Java source line #434	-> byte code offset #2
/*     */     //   Java source line #435	-> byte code offset #4
/*     */     //   Java source line #436	-> byte code offset #6
/*     */     //   Java source line #437	-> byte code offset #10
/*     */     //   Java source line #438	-> byte code offset #14
/*     */     //   Java source line #439	-> byte code offset #17
/*     */     //   Java source line #440	-> byte code offset #24
/*     */     //   Java source line #441	-> byte code offset #30
/*     */     //   Java source line #444	-> byte code offset #39
/*     */     //   Java source line #447	-> byte code offset #42
/*     */     //   Java source line #448	-> byte code offset #49
/*     */     //   Java source line #449	-> byte code offset #56
/*     */     //   Java source line #451	-> byte code offset #64
/*     */     //   Java source line #452	-> byte code offset #71
/*     */     //   Java source line #453	-> byte code offset #82
/*     */     //   Java source line #454	-> byte code offset #88
/*     */     //   Java source line #455	-> byte code offset #99
/*     */     //   Java source line #456	-> byte code offset #105
/*     */     //   Java source line #457	-> byte code offset #120
/*     */     //   Java source line #459	-> byte code offset #128
/*     */     //   Java source line #460	-> byte code offset #137
/*     */     //   Java source line #462	-> byte code offset #149
/*     */     //   Java source line #463	-> byte code offset #151
/*     */     //   Java source line #464	-> byte code offset #157
/*     */     //   Java source line #465	-> byte code offset #163
/*     */     //   Java source line #466	-> byte code offset #164
/*     */     //   Java source line #467	-> byte code offset #174
/*     */     //   Java source line #468	-> byte code offset #184
/*     */     //   Java source line #469	-> byte code offset #198
/*     */     //   Java source line #470	-> byte code offset #218
/*     */     //   Java source line #466	-> byte code offset #228
/*     */     //   Java source line #465	-> byte code offset #231
/*     */     //   Java source line #472	-> byte code offset #237
/*     */     //   Java source line #473	-> byte code offset #239
/*     */     //   Java source line #474	-> byte code offset #249
/*     */     //   Java source line #475	-> byte code offset #255
/*     */     //   Java source line #476	-> byte code offset #261
/*     */     //   Java source line #477	-> byte code offset #269
/*     */     //   Java source line #478	-> byte code offset #271
/*     */     //   Java source line #479	-> byte code offset #274
/*     */     //   Java source line #473	-> byte code offset #277
/*     */     //   Java source line #474	-> byte code offset #287
/*     */     //   Java source line #475	-> byte code offset #293
/*     */     //   Java source line #476	-> byte code offset #299
/*     */     //   Java source line #477	-> byte code offset #307
/*     */     //   Java source line #478	-> byte code offset #309
/*     */     //   Java source line #479	-> byte code offset #312
/*     */     //   Java source line #473	-> byte code offset #315
/*     */     //   Java source line #474	-> byte code offset #325
/*     */     //   Java source line #475	-> byte code offset #331
/*     */     //   Java source line #476	-> byte code offset #337
/*     */     //   Java source line #477	-> byte code offset #345
/*     */     //   Java source line #478	-> byte code offset #347
/*     */     //   Java source line #483	-> byte code offset #353
/*     */     //   Java source line #484	-> byte code offset #381
/*     */     //   Java source line #485	-> byte code offset #407
/*     */     //   Java source line #486	-> byte code offset #413
/*     */     //   Java source line #485	-> byte code offset #428
/*     */     //   Java source line #491	-> byte code offset #444
/*     */     //   Java source line #493	-> byte code offset #449
/*     */     //   Java source line #495	-> byte code offset #480
/*     */     //   Java source line #496	-> byte code offset #488
/*     */     //   Java source line #498	-> byte code offset #491
/*     */     //   Java source line #499	-> byte code offset #499
/*     */     //   Java source line #501	-> byte code offset #502
/*     */     //   Java source line #502	-> byte code offset #509
/*     */     //   Java source line #503	-> byte code offset #519
/*     */     //   Java source line #505	-> byte code offset #522
/*     */     //   Java source line #506	-> byte code offset #529
/*     */     //   Java source line #507	-> byte code offset #539
/*     */     //   Java source line #508	-> byte code offset #549
/*     */     //   Java source line #509	-> byte code offset #556
/*     */     //   Java source line #510	-> byte code offset #564
/*     */     //   Java source line #511	-> byte code offset #583
/*     */     //   Java source line #512	-> byte code offset #591
/*     */     //   Java source line #513	-> byte code offset #599
/*     */     //   Java source line #514	-> byte code offset #607
/*     */     //   Java source line #517	-> byte code offset #613
/*     */     //   Java source line #520	-> byte code offset #616
/*     */     //   Java source line #522	-> byte code offset #619
/*     */     //   Java source line #523	-> byte code offset #620
/*     */     //   Java source line #524	-> byte code offset #630
/*     */     //   Java source line #523	-> byte code offset #634
/*     */     //   Java source line #522	-> byte code offset #637
/*     */     //   Java source line #525	-> byte code offset #640
/*     */     //   Java source line #526	-> byte code offset #642
/*     */     //   Java source line #444	-> byte code offset #659
/*     */     //   Java source line #530	-> byte code offset #673
/*     */     //   Java source line #531	-> byte code offset #683
/*     */     //   Java source line #532	-> byte code offset #691
/*     */     //   Java source line #535	-> byte code offset #702
/*     */     //   Java source line #536	-> byte code offset #704
/*     */     //   Java source line #538	-> byte code offset #709
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	710	0	this	FICSProtocolHandler
/*     */     //   1	641	1	block_state	short
/*     */     //   3	314	2	id	int
/*     */     //   5	313	3	cmd	int
/*     */     //   8	532	4	c	char
/*     */     //   12	656	5	b	int
/*     */     //   15	600	6	ptr	short
/*     */     //   22	459	7	idBuff	CharBuffer
/*     */     //   28	464	8	cmdBuff	CharBuffer
/*     */     //   37	539	9	prompt	char[]
/*     */     //   149	3	10	e	NumberFormatException
/*     */     //   405	29	10	foo	String
/*     */     //   702	3	10	e	IOException
/*     */     //   237	38	11	localObject	Object
/*     */     //   408	31	11	z	int
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   128	146	149	java/lang/NumberFormatException
/*     */     //   128	237	237	finally
/*     */     //   39	699	702	java/io/IOException
/*     */   }
/*     */   
/*     */   public void disconnect()
/*     */   {
/* 542 */     sendCommand("exit");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void sendCommand(String cmd, boolean echo)
/*     */   {
/* 549 */     if (echo) {
/* 550 */       System.out.println(cmd);
/*     */     }
/* 552 */     if (this.isBlockMode) {
/* 553 */       this.out.println("1 " + cmd);
/*     */     } else
/* 555 */       this.out.println(cmd);
/* 556 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public void sendCommand(String cmd) {
/* 560 */     sendCommand(cmd, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void parse(CharSequence str)
/*     */   {
/* 569 */     ICSEvent icsEvent = null;
/* 570 */     Matcher matcher = null;
/* 571 */     boolean found = false;
/*     */     
/* 573 */     for (int i = 0; (i < this.eventFactories.length) && (!found); i++)
/*     */     {
/* 575 */       if ((matcher = this.eventFactories[i].match(str)) != null) {
/* 576 */         icsEvent = this.eventFactories[i].createICSEvent(matcher);
/* 577 */         assert (icsEvent != null) : "parser matched, but event null?";
/* 578 */         icsEvent.setServer(this);
/* 579 */         this.router.dispatch(icsEvent);
/*     */         
/* 581 */         found = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 587 */     CharSequence more = str;
/* 588 */     while ((matcher != null) && 
/* 589 */       (icsEvent != null) && (
/* 590 */       (icsEvent.getEventType() == 16) || 
/* 591 */       (icsEvent.getEventType() == 12)))
/*     */     {
/* 593 */       matcher = FICSSeekAdParser.getInstance().match(
/* 594 */         more = more.subSequence(matcher.end(), more.length()));
/*     */       
/* 596 */       if (matcher != null) {
/* 597 */         icsEvent = FICSSeekAdParser.getInstance().createICSEvent(matcher);
/* 598 */         assert (icsEvent != null) : "parser matched, but event null?";
/* 599 */         icsEvent.setServer(this);
/* 600 */         this.router.dispatch(icsEvent);
/*     */       }
/*     */     }
/*     */     
/* 604 */     if (!found) {
/* 605 */       System.out.println(str);
/*     */     }
/*     */     
/* 608 */     if (matcher != null) {
/* 609 */       int end = matcher.end();
/* 610 */       if ((end < str.length()) && (str.charAt(end) == '\n'))
/* 611 */         end++;
/* 612 */       if (end < str.length()) {
/* 613 */         System.out.println(str.subSequence(end, str.length()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void parseResponse(int id, int cmd, CharSequence str) {}
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\FICSProtocolHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */