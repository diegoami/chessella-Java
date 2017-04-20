/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.io.InvalidGameFormatException;
/*     */ import ictk.util.Log;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
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
/*     */ public class PGNWriterTest
/*     */   extends TestCase
/*     */ {
/*  39 */   public String dataDir = "./";
/*  40 */   String pgn_nonvariation = "test_nonvariation.pgn";
/*  41 */   String pgn_variation = "test_variation.pgn";
/*  42 */   String pgn_annotation = "test_annotation.pgn";
/*  43 */   String pgn_debug = "test_debug.pgn";
/*     */   PGNWriter writer;
/*     */   SAN san;
/*     */   ChessBoard board;
/*     */   ChessResult res;
/*     */   ChessMove move;
/*     */   ChessMove move2;
/*     */   ChessReader in;
/*     */   Game game;
/*     */   Game game2;
/*     */   StringWriter sw;
/*     */   StringReader sr;
/*     */   PGNReader spgnin;
/*     */   ChessAnnotation anno;
/*     */   List list;
/*     */   
/*  59 */   public PGNWriterTest(String name) { super(name);
/*     */     
/*  61 */     if (System.getProperty("ictk.boardgame.chess.io.dataDir") != null)
/*  62 */       this.dataDir = System.getProperty("ictk.boardgame.chess.io.dataDir");
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  66 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  70 */     this.spgnin = null;
/*  71 */     this.sw = null;
/*  72 */     this.sr = null;
/*  73 */     this.writer = null;
/*  74 */     this.san = null;
/*  75 */     this.board = null;
/*  76 */     this.res = null;
/*  77 */     this.move = null;
/*  78 */     this.game = null;
/*  79 */     this.game2 = null;
/*  80 */     this.in = null;
/*  81 */     this.anno = null;
/*  82 */     Log.removeMask(SAN.DEBUG);
/*  83 */     Log.removeMask(ChessBoard.DEBUG);
/*  84 */     Log.removeMask(ChessGameInfo.DEBUG);
/*  85 */     Log.removeMask(PGNWriter.DEBUG);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testWrongDisambiguationBug()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: new 139	java/lang/StringBuilder
/*     */     //   4: dup
/*     */     //   5: aload_0
/*     */     //   6: getfield 44	ictk/boardgame/chess/io/PGNWriterTest:dataDir	Ljava/lang/String;
/*     */     //   9: invokestatic 141	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   12: invokespecial 147	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   15: aload_0
/*     */     //   16: getfield 52	ictk/boardgame/chess/io/PGNWriterTest:pgn_variation	Ljava/lang/String;
/*     */     //   19: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   22: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   25: iconst_0
/*     */     //   26: bipush 9
/*     */     //   28: invokestatic 156	ictk/boardgame/chess/io/PGNReaderTest:loadGames	(Ljava/lang/String;ZI)Ljava/util/List;
/*     */     //   31: putfield 162	ictk/boardgame/chess/io/PGNWriterTest:list	Ljava/util/List;
/*     */     //   34: aload_0
/*     */     //   35: aload_0
/*     */     //   36: getfield 162	ictk/boardgame/chess/io/PGNWriterTest:list	Ljava/util/List;
/*     */     //   39: bipush 9
/*     */     //   41: invokeinterface 164 2 0
/*     */     //   46: checkcast 170	ictk/boardgame/chess/ChessGame
/*     */     //   49: putfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   52: aload_0
/*     */     //   53: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   56: dup
/*     */     //   57: aload_0
/*     */     //   58: new 172	java/io/StringWriter
/*     */     //   61: dup
/*     */     //   62: invokespecial 174	java/io/StringWriter:<init>	()V
/*     */     //   65: dup_x1
/*     */     //   66: putfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   69: invokespecial 175	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   72: putfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   75: aload_0
/*     */     //   76: getfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   79: aload_0
/*     */     //   80: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   83: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   86: aload_0
/*     */     //   87: getfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   90: invokevirtual 182	java/io/StringWriter:toString	()Ljava/lang/String;
/*     */     //   93: astore_1
/*     */     //   94: aload_1
/*     */     //   95: ldc -73
/*     */     //   97: invokevirtual 185	java/lang/String:indexOf	(Ljava/lang/String;)I
/*     */     //   100: ifge +7 -> 107
/*     */     //   103: iconst_1
/*     */     //   104: goto +4 -> 108
/*     */     //   107: iconst_0
/*     */     //   108: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   111: goto +30 -> 141
/*     */     //   114: astore_2
/*     */     //   115: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   118: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   121: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   124: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   127: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   130: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   133: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   136: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   139: aload_2
/*     */     //   140: athrow
/*     */     //   141: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   144: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   147: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   150: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   153: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   156: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   159: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   162: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   165: return
/*     */     // Line number table:
/*     */     //   Java source line #106	-> byte code offset #0
/*     */     //   Java source line #107	-> byte code offset #34
/*     */     //   Java source line #109	-> byte code offset #52
/*     */     //   Java source line #110	-> byte code offset #75
/*     */     //   Java source line #112	-> byte code offset #86
/*     */     //   Java source line #113	-> byte code offset #94
/*     */     //   Java source line #115	-> byte code offset #114
/*     */     //   Java source line #116	-> byte code offset #115
/*     */     //   Java source line #117	-> byte code offset #121
/*     */     //   Java source line #118	-> byte code offset #127
/*     */     //   Java source line #119	-> byte code offset #133
/*     */     //   Java source line #120	-> byte code offset #139
/*     */     //   Java source line #116	-> byte code offset #141
/*     */     //   Java source line #117	-> byte code offset #147
/*     */     //   Java source line #118	-> byte code offset #153
/*     */     //   Java source line #119	-> byte code offset #159
/*     */     //   Java source line #121	-> byte code offset #165
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	166	0	this	PGNWriterTest
/*     */     //   93	2	1	pgn	String
/*     */     //   114	26	2	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	114	114	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testVariationNesting()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: new 170	ictk/boardgame/chess/ChessGame
/*     */     //   4: dup
/*     */     //   5: invokespecial 198	ictk/boardgame/chess/ChessGame:<init>	()V
/*     */     //   8: putfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   11: aload_0
/*     */     //   12: aload_0
/*     */     //   13: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   16: checkcast 170	ictk/boardgame/chess/ChessGame
/*     */     //   19: invokevirtual 199	ictk/boardgame/chess/ChessGame:getBoard	()Lictk/boardgame/Board;
/*     */     //   22: checkcast 117	ictk/boardgame/chess/ChessBoard
/*     */     //   25: putfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   28: aload_0
/*     */     //   29: aload_0
/*     */     //   30: getfield 81	ictk/boardgame/chess/io/PGNWriterTest:san	Lictk/boardgame/chess/io/SAN;
/*     */     //   33: aload_0
/*     */     //   34: getfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   37: ldc -53
/*     */     //   39: invokevirtual 205	ictk/boardgame/chess/io/SAN:stringToMove	(Lictk/boardgame/Board;Ljava/lang/String;)Lictk/boardgame/Move;
/*     */     //   42: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   45: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   48: aload_0
/*     */     //   49: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   52: invokeinterface 211 1 0
/*     */     //   57: aload_0
/*     */     //   58: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   61: invokevirtual 217	ictk/boardgame/History:add	(Lictk/boardgame/Move;)V
/*     */     //   64: aload_0
/*     */     //   65: aload_0
/*     */     //   66: getfield 81	ictk/boardgame/chess/io/PGNWriterTest:san	Lictk/boardgame/chess/io/SAN;
/*     */     //   69: aload_0
/*     */     //   70: getfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   73: ldc -33
/*     */     //   75: invokevirtual 205	ictk/boardgame/chess/io/SAN:stringToMove	(Lictk/boardgame/Board;Ljava/lang/String;)Lictk/boardgame/Move;
/*     */     //   78: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   81: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   84: aload_0
/*     */     //   85: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   88: invokeinterface 211 1 0
/*     */     //   93: aload_0
/*     */     //   94: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   97: invokevirtual 217	ictk/boardgame/History:add	(Lictk/boardgame/Move;)V
/*     */     //   100: aload_0
/*     */     //   101: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   104: invokeinterface 211 1 0
/*     */     //   109: invokevirtual 225	ictk/boardgame/History:prev	()Lictk/boardgame/Move;
/*     */     //   112: pop
/*     */     //   113: aload_0
/*     */     //   114: aload_0
/*     */     //   115: getfield 81	ictk/boardgame/chess/io/PGNWriterTest:san	Lictk/boardgame/chess/io/SAN;
/*     */     //   118: aload_0
/*     */     //   119: getfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   122: ldc -27
/*     */     //   124: invokevirtual 205	ictk/boardgame/chess/io/SAN:stringToMove	(Lictk/boardgame/Board;Ljava/lang/String;)Lictk/boardgame/Move;
/*     */     //   127: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   130: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   133: aload_0
/*     */     //   134: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   137: invokeinterface 211 1 0
/*     */     //   142: aload_0
/*     */     //   143: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   146: invokevirtual 217	ictk/boardgame/History:add	(Lictk/boardgame/Move;)V
/*     */     //   149: aload_0
/*     */     //   150: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   153: invokeinterface 211 1 0
/*     */     //   158: invokevirtual 225	ictk/boardgame/History:prev	()Lictk/boardgame/Move;
/*     */     //   161: pop
/*     */     //   162: aload_0
/*     */     //   163: aload_0
/*     */     //   164: getfield 81	ictk/boardgame/chess/io/PGNWriterTest:san	Lictk/boardgame/chess/io/SAN;
/*     */     //   167: aload_0
/*     */     //   168: getfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   171: ldc -25
/*     */     //   173: invokevirtual 205	ictk/boardgame/chess/io/SAN:stringToMove	(Lictk/boardgame/Board;Ljava/lang/String;)Lictk/boardgame/Move;
/*     */     //   176: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   179: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   182: aload_0
/*     */     //   183: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   186: invokeinterface 211 1 0
/*     */     //   191: aload_0
/*     */     //   192: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   195: invokevirtual 217	ictk/boardgame/History:add	(Lictk/boardgame/Move;)V
/*     */     //   198: aload_0
/*     */     //   199: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   202: dup
/*     */     //   203: aload_0
/*     */     //   204: new 172	java/io/StringWriter
/*     */     //   207: dup
/*     */     //   208: invokespecial 174	java/io/StringWriter:<init>	()V
/*     */     //   211: dup_x1
/*     */     //   212: putfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   215: invokespecial 175	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   218: putfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   221: aload_0
/*     */     //   222: getfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   225: aload_0
/*     */     //   226: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   229: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   232: new 233	java/io/BufferedReader
/*     */     //   235: dup
/*     */     //   236: new 235	java/io/StringReader
/*     */     //   239: dup
/*     */     //   240: aload_0
/*     */     //   241: getfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   244: invokevirtual 182	java/io/StringWriter:toString	()Ljava/lang/String;
/*     */     //   247: invokespecial 237	java/io/StringReader:<init>	(Ljava/lang/String;)V
/*     */     //   250: invokespecial 238	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
/*     */     //   253: astore_1
/*     */     //   254: iconst_0
/*     */     //   255: istore_2
/*     */     //   256: aconst_null
/*     */     //   257: astore_3
/*     */     //   258: goto +10 -> 268
/*     */     //   261: aload_3
/*     */     //   262: ldc -15
/*     */     //   264: invokevirtual 243	java/lang/String:startsWith	(Ljava/lang/String;)Z
/*     */     //   267: istore_2
/*     */     //   268: iload_2
/*     */     //   269: ifne +12 -> 281
/*     */     //   272: aload_1
/*     */     //   273: invokevirtual 247	java/io/BufferedReader:readLine	()Ljava/lang/String;
/*     */     //   276: dup
/*     */     //   277: astore_3
/*     */     //   278: ifnonnull -17 -> 261
/*     */     //   281: iload_2
/*     */     //   282: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   285: aload_3
/*     */     //   286: bipush 40
/*     */     //   288: invokevirtual 250	java/lang/String:indexOf	(I)I
/*     */     //   291: istore 4
/*     */     //   293: iload 4
/*     */     //   295: iconst_m1
/*     */     //   296: if_icmpeq +7 -> 303
/*     */     //   299: iconst_1
/*     */     //   300: goto +4 -> 304
/*     */     //   303: iconst_0
/*     */     //   304: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   307: aload_3
/*     */     //   308: bipush 41
/*     */     //   310: invokevirtual 250	java/lang/String:indexOf	(I)I
/*     */     //   313: istore 5
/*     */     //   315: iload 5
/*     */     //   317: iconst_m1
/*     */     //   318: if_icmpeq +7 -> 325
/*     */     //   321: iconst_1
/*     */     //   322: goto +4 -> 326
/*     */     //   325: iconst_0
/*     */     //   326: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   329: aload_3
/*     */     //   330: bipush 40
/*     */     //   332: iload 4
/*     */     //   334: iconst_1
/*     */     //   335: iadd
/*     */     //   336: invokevirtual 253	java/lang/String:indexOf	(II)I
/*     */     //   339: istore 6
/*     */     //   341: iload 6
/*     */     //   343: iconst_m1
/*     */     //   344: if_icmpeq +7 -> 351
/*     */     //   347: iconst_1
/*     */     //   348: goto +4 -> 352
/*     */     //   351: iconst_0
/*     */     //   352: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   355: iload 5
/*     */     //   357: iload 6
/*     */     //   359: if_icmpge +7 -> 366
/*     */     //   362: iconst_1
/*     */     //   363: goto +4 -> 367
/*     */     //   366: iconst_0
/*     */     //   367: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   370: goto +32 -> 402
/*     */     //   373: astore 7
/*     */     //   375: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   378: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   381: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   384: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   387: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   390: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   393: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   396: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   399: aload 7
/*     */     //   401: athrow
/*     */     //   402: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   405: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   408: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   411: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   414: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   417: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   420: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   423: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   426: return
/*     */     // Line number table:
/*     */     //   Java source line #141	-> byte code offset #0
/*     */     //   Java source line #142	-> byte code offset #11
/*     */     //   Java source line #144	-> byte code offset #28
/*     */     //   Java source line #145	-> byte code offset #48
/*     */     //   Java source line #148	-> byte code offset #64
/*     */     //   Java source line #149	-> byte code offset #84
/*     */     //   Java source line #151	-> byte code offset #100
/*     */     //   Java source line #154	-> byte code offset #113
/*     */     //   Java source line #155	-> byte code offset #133
/*     */     //   Java source line #157	-> byte code offset #149
/*     */     //   Java source line #160	-> byte code offset #162
/*     */     //   Java source line #161	-> byte code offset #182
/*     */     //   Java source line #163	-> byte code offset #198
/*     */     //   Java source line #164	-> byte code offset #221
/*     */     //   Java source line #166	-> byte code offset #232
/*     */     //   Java source line #167	-> byte code offset #236
/*     */     //   Java source line #166	-> byte code offset #250
/*     */     //   Java source line #168	-> byte code offset #254
/*     */     //   Java source line #169	-> byte code offset #256
/*     */     //   Java source line #171	-> byte code offset #258
/*     */     //   Java source line #172	-> byte code offset #261
/*     */     //   Java source line #171	-> byte code offset #268
/*     */     //   Java source line #175	-> byte code offset #281
/*     */     //   Java source line #176	-> byte code offset #285
/*     */     //   Java source line #177	-> byte code offset #293
/*     */     //   Java source line #179	-> byte code offset #307
/*     */     //   Java source line #180	-> byte code offset #315
/*     */     //   Java source line #182	-> byte code offset #329
/*     */     //   Java source line #183	-> byte code offset #341
/*     */     //   Java source line #186	-> byte code offset #355
/*     */     //   Java source line #189	-> byte code offset #373
/*     */     //   Java source line #190	-> byte code offset #375
/*     */     //   Java source line #191	-> byte code offset #381
/*     */     //   Java source line #192	-> byte code offset #387
/*     */     //   Java source line #193	-> byte code offset #393
/*     */     //   Java source line #194	-> byte code offset #399
/*     */     //   Java source line #190	-> byte code offset #402
/*     */     //   Java source line #191	-> byte code offset #408
/*     */     //   Java source line #192	-> byte code offset #414
/*     */     //   Java source line #193	-> byte code offset #420
/*     */     //   Java source line #195	-> byte code offset #426
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	427	0	this	PGNWriterTest
/*     */     //   253	20	1	in	java.io.BufferedReader
/*     */     //   255	27	2	found	boolean
/*     */     //   257	73	3	line	String
/*     */     //   291	42	4	openVarIndex	int
/*     */     //   313	43	5	closeVarIndex	int
/*     */     //   339	19	6	nextOpenVarIndex	int
/*     */     //   373	27	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	373	373	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testBulkNonVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: aload_0
/*     */     //   3: new 194	ictk/boardgame/chess/io/PGNReader
/*     */     //   6: dup
/*     */     //   7: new 265	java/io/FileReader
/*     */     //   10: dup
/*     */     //   11: new 267	java/io/File
/*     */     //   14: dup
/*     */     //   15: new 139	java/lang/StringBuilder
/*     */     //   18: dup
/*     */     //   19: aload_0
/*     */     //   20: getfield 44	ictk/boardgame/chess/io/PGNWriterTest:dataDir	Ljava/lang/String;
/*     */     //   23: invokestatic 141	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   26: invokespecial 147	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   29: aload_0
/*     */     //   30: getfield 48	ictk/boardgame/chess/io/PGNWriterTest:pgn_nonvariation	Ljava/lang/String;
/*     */     //   33: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   36: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   39: invokespecial 269	java/io/File:<init>	(Ljava/lang/String;)V
/*     */     //   42: invokespecial 270	java/io/FileReader:<init>	(Ljava/io/File;)V
/*     */     //   45: invokespecial 273	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   48: putfield 102	ictk/boardgame/chess/io/PGNWriterTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   51: goto +124 -> 175
/*     */     //   54: aload_0
/*     */     //   55: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   58: dup
/*     */     //   59: aload_0
/*     */     //   60: new 172	java/io/StringWriter
/*     */     //   63: dup
/*     */     //   64: invokespecial 174	java/io/StringWriter:<init>	()V
/*     */     //   67: dup_x1
/*     */     //   68: putfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   71: invokespecial 175	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   74: putfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   77: aload_0
/*     */     //   78: getfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   81: aload_0
/*     */     //   82: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   85: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   88: aload_0
/*     */     //   89: new 194	ictk/boardgame/chess/io/PGNReader
/*     */     //   92: dup
/*     */     //   93: new 235	java/io/StringReader
/*     */     //   96: dup
/*     */     //   97: aload_0
/*     */     //   98: getfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   101: invokevirtual 182	java/io/StringWriter:toString	()Ljava/lang/String;
/*     */     //   104: invokespecial 237	java/io/StringReader:<init>	(Ljava/lang/String;)V
/*     */     //   107: invokespecial 273	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   110: putfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   113: aload_0
/*     */     //   114: aload_0
/*     */     //   115: getfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   118: invokevirtual 274	ictk/boardgame/chess/io/PGNReader:readGame	()Lictk/boardgame/Game;
/*     */     //   121: putfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   124: aload_0
/*     */     //   125: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   128: invokeinterface 211 1 0
/*     */     //   133: aload_0
/*     */     //   134: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   137: invokeinterface 211 1 0
/*     */     //   142: invokevirtual 278	ictk/boardgame/History:equals	(Lictk/boardgame/History;)Z
/*     */     //   145: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   148: aload_0
/*     */     //   149: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   152: invokeinterface 282 1 0
/*     */     //   157: checkcast 120	ictk/boardgame/chess/ChessGameInfo
/*     */     //   160: aload_0
/*     */     //   161: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   164: invokeinterface 282 1 0
/*     */     //   169: invokevirtual 286	ictk/boardgame/chess/ChessGameInfo:equals	(Ljava/lang/Object;)Z
/*     */     //   172: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   175: aload_0
/*     */     //   176: aload_0
/*     */     //   177: getfield 102	ictk/boardgame/chess/io/PGNWriterTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   180: invokevirtual 289	ictk/boardgame/chess/io/ChessReader:readGame	()Lictk/boardgame/Game;
/*     */     //   183: dup_x1
/*     */     //   184: putfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   187: ifnonnull -133 -> 54
/*     */     //   190: goto +30 -> 220
/*     */     //   193: astore_2
/*     */     //   194: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   197: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   200: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   203: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   206: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   209: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   212: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   215: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   218: aload_2
/*     */     //   219: athrow
/*     */     //   220: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   223: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   226: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   229: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   232: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   235: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   238: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   241: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   244: return
/*     */     // Line number table:
/*     */     //   Java source line #209	-> byte code offset #0
/*     */     //   Java source line #211	-> byte code offset #2
/*     */     //   Java source line #212	-> byte code offset #7
/*     */     //   Java source line #213	-> byte code offset #11
/*     */     //   Java source line #212	-> byte code offset #42
/*     */     //   Java source line #211	-> byte code offset #48
/*     */     //   Java source line #215	-> byte code offset #51
/*     */     //   Java source line #216	-> byte code offset #54
/*     */     //   Java source line #217	-> byte code offset #77
/*     */     //   Java source line #218	-> byte code offset #88
/*     */     //   Java source line #219	-> byte code offset #113
/*     */     //   Java source line #220	-> byte code offset #124
/*     */     //   Java source line #221	-> byte code offset #148
/*     */     //   Java source line #222	-> byte code offset #160
/*     */     //   Java source line #221	-> byte code offset #172
/*     */     //   Java source line #215	-> byte code offset #175
/*     */     //   Java source line #225	-> byte code offset #193
/*     */     //   Java source line #226	-> byte code offset #194
/*     */     //   Java source line #227	-> byte code offset #200
/*     */     //   Java source line #228	-> byte code offset #206
/*     */     //   Java source line #229	-> byte code offset #212
/*     */     //   Java source line #230	-> byte code offset #218
/*     */     //   Java source line #226	-> byte code offset #220
/*     */     //   Java source line #227	-> byte code offset #226
/*     */     //   Java source line #228	-> byte code offset #232
/*     */     //   Java source line #229	-> byte code offset #238
/*     */     //   Java source line #231	-> byte code offset #244
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	245	0	this	PGNWriterTest
/*     */     //   1	2	1	count	int
/*     */     //   193	26	2	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	193	193	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testBulkVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: aload_0
/*     */     //   3: new 194	ictk/boardgame/chess/io/PGNReader
/*     */     //   6: dup
/*     */     //   7: new 265	java/io/FileReader
/*     */     //   10: dup
/*     */     //   11: new 267	java/io/File
/*     */     //   14: dup
/*     */     //   15: new 139	java/lang/StringBuilder
/*     */     //   18: dup
/*     */     //   19: aload_0
/*     */     //   20: getfield 44	ictk/boardgame/chess/io/PGNWriterTest:dataDir	Ljava/lang/String;
/*     */     //   23: invokestatic 141	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   26: invokespecial 147	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   29: aload_0
/*     */     //   30: getfield 52	ictk/boardgame/chess/io/PGNWriterTest:pgn_variation	Ljava/lang/String;
/*     */     //   33: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   36: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   39: invokespecial 269	java/io/File:<init>	(Ljava/lang/String;)V
/*     */     //   42: invokespecial 270	java/io/FileReader:<init>	(Ljava/io/File;)V
/*     */     //   45: invokespecial 273	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   48: putfield 102	ictk/boardgame/chess/io/PGNWriterTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   51: goto +177 -> 228
/*     */     //   54: aload_0
/*     */     //   55: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   58: dup
/*     */     //   59: aload_0
/*     */     //   60: new 172	java/io/StringWriter
/*     */     //   63: dup
/*     */     //   64: invokespecial 174	java/io/StringWriter:<init>	()V
/*     */     //   67: dup_x1
/*     */     //   68: putfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   71: invokespecial 175	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   74: putfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   77: aload_0
/*     */     //   78: getfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   81: aload_0
/*     */     //   82: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   85: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   88: aload_0
/*     */     //   89: new 194	ictk/boardgame/chess/io/PGNReader
/*     */     //   92: dup
/*     */     //   93: new 235	java/io/StringReader
/*     */     //   96: dup
/*     */     //   97: aload_0
/*     */     //   98: getfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   101: invokevirtual 182	java/io/StringWriter:toString	()Ljava/lang/String;
/*     */     //   104: invokespecial 237	java/io/StringReader:<init>	(Ljava/lang/String;)V
/*     */     //   107: invokespecial 273	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   110: putfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   113: aload_0
/*     */     //   114: aload_0
/*     */     //   115: getfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   118: invokevirtual 274	ictk/boardgame/chess/io/PGNReader:readGame	()Lictk/boardgame/Game;
/*     */     //   121: putfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   124: aload_0
/*     */     //   125: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   128: invokeinterface 282 1 0
/*     */     //   133: checkcast 120	ictk/boardgame/chess/ChessGameInfo
/*     */     //   136: aload_0
/*     */     //   137: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   140: invokeinterface 282 1 0
/*     */     //   145: invokevirtual 286	ictk/boardgame/chess/ChessGameInfo:equals	(Ljava/lang/Object;)Z
/*     */     //   148: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   151: aload_0
/*     */     //   152: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   155: invokeinterface 211 1 0
/*     */     //   160: aload_0
/*     */     //   161: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   164: invokeinterface 211 1 0
/*     */     //   169: invokevirtual 278	ictk/boardgame/History:equals	(Lictk/boardgame/History;)Z
/*     */     //   172: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   175: aload_0
/*     */     //   176: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   179: invokeinterface 211 1 0
/*     */     //   184: aload_0
/*     */     //   185: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   188: invokeinterface 211 1 0
/*     */     //   193: iconst_0
/*     */     //   194: invokevirtual 294	ictk/boardgame/History:deepEquals	(Lictk/boardgame/History;Z)Z
/*     */     //   197: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   200: aload_0
/*     */     //   201: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   204: invokeinterface 211 1 0
/*     */     //   209: aload_0
/*     */     //   210: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   213: invokeinterface 211 1 0
/*     */     //   218: iconst_1
/*     */     //   219: invokevirtual 294	ictk/boardgame/History:deepEquals	(Lictk/boardgame/History;Z)Z
/*     */     //   222: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   225: iinc 1 1
/*     */     //   228: aload_0
/*     */     //   229: aload_0
/*     */     //   230: getfield 102	ictk/boardgame/chess/io/PGNWriterTest:in	Lictk/boardgame/chess/io/ChessReader;
/*     */     //   233: invokevirtual 289	ictk/boardgame/chess/io/ChessReader:readGame	()Lictk/boardgame/Game;
/*     */     //   236: dup_x1
/*     */     //   237: putfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   240: ifnonnull -186 -> 54
/*     */     //   243: goto +88 -> 331
/*     */     //   246: astore_2
/*     */     //   247: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   250: invokestatic 298	ictk/util/Log:isDebug	(J)Z
/*     */     //   253: ifeq +43 -> 296
/*     */     //   256: getstatic 302	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   259: new 139	java/lang/StringBuilder
/*     */     //   262: dup
/*     */     //   263: ldc_w 306
/*     */     //   266: invokespecial 147	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   269: iload_1
/*     */     //   270: invokevirtual 308	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   273: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   276: invokevirtual 311	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   279: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   282: dup
/*     */     //   283: getstatic 316	java/lang/System:err	Ljava/io/PrintStream;
/*     */     //   286: invokespecial 319	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/OutputStream;)V
/*     */     //   289: aload_0
/*     */     //   290: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   293: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   296: aload_2
/*     */     //   297: athrow
/*     */     //   298: astore_3
/*     */     //   299: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   302: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   305: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   308: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   311: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   314: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   317: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   320: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   323: getstatic 322	ictk/boardgame/History:DEBUG	J
/*     */     //   326: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   329: aload_3
/*     */     //   330: athrow
/*     */     //   331: getstatic 106	ictk/boardgame/chess/io/SAN:DEBUG	J
/*     */     //   334: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   337: getstatic 193	ictk/boardgame/chess/io/PGNReader:DEBUG	J
/*     */     //   340: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   343: getstatic 119	ictk/boardgame/chess/ChessGameInfo:DEBUG	J
/*     */     //   346: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   349: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   352: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   355: getstatic 322	ictk/boardgame/History:DEBUG	J
/*     */     //   358: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   361: return
/*     */     // Line number table:
/*     */     //   Java source line #247	-> byte code offset #0
/*     */     //   Java source line #250	-> byte code offset #2
/*     */     //   Java source line #251	-> byte code offset #7
/*     */     //   Java source line #252	-> byte code offset #11
/*     */     //   Java source line #251	-> byte code offset #42
/*     */     //   Java source line #250	-> byte code offset #48
/*     */     //   Java source line #254	-> byte code offset #51
/*     */     //   Java source line #255	-> byte code offset #54
/*     */     //   Java source line #256	-> byte code offset #77
/*     */     //   Java source line #257	-> byte code offset #88
/*     */     //   Java source line #258	-> byte code offset #113
/*     */     //   Java source line #260	-> byte code offset #124
/*     */     //   Java source line #261	-> byte code offset #136
/*     */     //   Java source line #260	-> byte code offset #148
/*     */     //   Java source line #263	-> byte code offset #151
/*     */     //   Java source line #264	-> byte code offset #175
/*     */     //   Java source line #265	-> byte code offset #200
/*     */     //   Java source line #266	-> byte code offset #225
/*     */     //   Java source line #254	-> byte code offset #228
/*     */     //   Java source line #269	-> byte code offset #246
/*     */     //   Java source line #270	-> byte code offset #247
/*     */     //   Java source line #271	-> byte code offset #256
/*     */     //   Java source line #272	-> byte code offset #279
/*     */     //   Java source line #275	-> byte code offset #296
/*     */     //   Java source line #277	-> byte code offset #298
/*     */     //   Java source line #278	-> byte code offset #299
/*     */     //   Java source line #279	-> byte code offset #305
/*     */     //   Java source line #280	-> byte code offset #311
/*     */     //   Java source line #281	-> byte code offset #317
/*     */     //   Java source line #282	-> byte code offset #323
/*     */     //   Java source line #283	-> byte code offset #329
/*     */     //   Java source line #278	-> byte code offset #331
/*     */     //   Java source line #279	-> byte code offset #337
/*     */     //   Java source line #280	-> byte code offset #343
/*     */     //   Java source line #281	-> byte code offset #349
/*     */     //   Java source line #282	-> byte code offset #355
/*     */     //   Java source line #284	-> byte code offset #361
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	362	0	this	PGNWriterTest
/*     */     //   1	269	1	count	int
/*     */     //   246	51	2	e	Exception
/*     */     //   298	32	3	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   2	243	246	java/lang/Exception
/*     */     //   2	298	298	finally
/*     */   }
/*     */   
/*     */   public void testZeroGameInfoZeroHistory()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 297 */     this.game = new ChessGame();
/* 298 */     this.board = ((ChessBoard)((ChessGame)this.game).getBoard());
/*     */     
/* 300 */     this.writer = new PGNWriter(this.sw = new StringWriter());
/*     */     
/* 302 */     this.writer.writeGame(this.game);
/*     */     
/* 304 */     Log.removeMask(PGNWriter.DEBUG);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public void testPrenotation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: new 170	ictk/boardgame/chess/ChessGame
/*     */     //   4: dup
/*     */     //   5: invokespecial 198	ictk/boardgame/chess/ChessGame:<init>	()V
/*     */     //   8: putfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   11: aload_0
/*     */     //   12: aload_0
/*     */     //   13: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   16: checkcast 170	ictk/boardgame/chess/ChessGame
/*     */     //   19: invokevirtual 199	ictk/boardgame/chess/ChessGame:getBoard	()Lictk/boardgame/Board;
/*     */     //   22: checkcast 117	ictk/boardgame/chess/ChessBoard
/*     */     //   25: putfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   28: aload_0
/*     */     //   29: aload_0
/*     */     //   30: getfield 81	ictk/boardgame/chess/io/PGNWriterTest:san	Lictk/boardgame/chess/io/SAN;
/*     */     //   33: aload_0
/*     */     //   34: getfield 92	ictk/boardgame/chess/io/PGNWriterTest:board	Lictk/boardgame/chess/ChessBoard;
/*     */     //   37: ldc -53
/*     */     //   39: invokevirtual 205	ictk/boardgame/chess/io/SAN:stringToMove	(Lictk/boardgame/Board;Ljava/lang/String;)Lictk/boardgame/Move;
/*     */     //   42: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   45: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   48: aload_0
/*     */     //   49: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   52: invokeinterface 211 1 0
/*     */     //   57: aload_0
/*     */     //   58: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   61: invokevirtual 217	ictk/boardgame/History:add	(Lictk/boardgame/Move;)V
/*     */     //   64: aload_0
/*     */     //   65: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   68: new 327	ictk/boardgame/chess/io/ChessAnnotation
/*     */     //   71: dup
/*     */     //   72: ldc_w 329
/*     */     //   75: invokespecial 331	ictk/boardgame/chess/io/ChessAnnotation:<init>	(Ljava/lang/String;)V
/*     */     //   78: invokevirtual 332	ictk/boardgame/chess/ChessMove:setPrenotation	(Lictk/boardgame/io/Annotation;)V
/*     */     //   81: aload_0
/*     */     //   82: new 123	ictk/boardgame/chess/io/PGNWriter
/*     */     //   85: dup
/*     */     //   86: aload_0
/*     */     //   87: new 172	java/io/StringWriter
/*     */     //   90: dup
/*     */     //   91: invokespecial 174	java/io/StringWriter:<init>	()V
/*     */     //   94: dup_x1
/*     */     //   95: putfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   98: invokespecial 175	ictk/boardgame/chess/io/PGNWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   101: putfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   104: aload_0
/*     */     //   105: getfield 90	ictk/boardgame/chess/io/PGNWriterTest:writer	Lictk/boardgame/chess/io/PGNWriter;
/*     */     //   108: aload_0
/*     */     //   109: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   112: invokevirtual 178	ictk/boardgame/chess/io/PGNWriter:writeGame	(Lictk/boardgame/Game;)V
/*     */     //   115: aload_0
/*     */     //   116: new 194	ictk/boardgame/chess/io/PGNReader
/*     */     //   119: dup
/*     */     //   120: new 235	java/io/StringReader
/*     */     //   123: dup
/*     */     //   124: aload_0
/*     */     //   125: getfield 86	ictk/boardgame/chess/io/PGNWriterTest:sw	Ljava/io/StringWriter;
/*     */     //   128: invokevirtual 182	java/io/StringWriter:toString	()Ljava/lang/String;
/*     */     //   131: invokespecial 237	java/io/StringReader:<init>	(Ljava/lang/String;)V
/*     */     //   134: invokespecial 273	ictk/boardgame/chess/io/PGNReader:<init>	(Ljava/io/Reader;)V
/*     */     //   137: putfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   140: aload_0
/*     */     //   141: aload_0
/*     */     //   142: getfield 84	ictk/boardgame/chess/io/PGNWriterTest:spgnin	Lictk/boardgame/chess/io/PGNReader;
/*     */     //   145: invokevirtual 274	ictk/boardgame/chess/io/PGNReader:readGame	()Lictk/boardgame/Game;
/*     */     //   148: putfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   151: aload_0
/*     */     //   152: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   155: invokeinterface 211 1 0
/*     */     //   160: aload_0
/*     */     //   161: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   164: invokeinterface 211 1 0
/*     */     //   169: invokevirtual 278	ictk/boardgame/History:equals	(Lictk/boardgame/History;)Z
/*     */     //   172: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   175: aload_0
/*     */     //   176: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   179: invokeinterface 211 1 0
/*     */     //   184: aload_0
/*     */     //   185: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   188: invokeinterface 211 1 0
/*     */     //   193: iconst_0
/*     */     //   194: invokevirtual 294	ictk/boardgame/History:deepEquals	(Lictk/boardgame/History;Z)Z
/*     */     //   197: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   200: aload_0
/*     */     //   201: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   204: invokeinterface 211 1 0
/*     */     //   209: aload_0
/*     */     //   210: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   213: invokeinterface 211 1 0
/*     */     //   218: iconst_1
/*     */     //   219: invokevirtual 294	ictk/boardgame/History:deepEquals	(Lictk/boardgame/History;Z)Z
/*     */     //   222: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   225: aload_0
/*     */     //   226: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   229: invokeinterface 211 1 0
/*     */     //   234: invokevirtual 336	ictk/boardgame/History:next	()Lictk/boardgame/Move;
/*     */     //   237: pop
/*     */     //   238: aload_0
/*     */     //   239: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   242: invokeinterface 211 1 0
/*     */     //   247: invokevirtual 336	ictk/boardgame/History:next	()Lictk/boardgame/Move;
/*     */     //   250: pop
/*     */     //   251: aload_0
/*     */     //   252: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   255: invokeinterface 211 1 0
/*     */     //   260: invokevirtual 339	ictk/boardgame/History:getCurrentMove	()Lictk/boardgame/Move;
/*     */     //   263: aload_0
/*     */     //   264: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   267: invokeinterface 211 1 0
/*     */     //   272: invokevirtual 339	ictk/boardgame/History:getCurrentMove	()Lictk/boardgame/Move;
/*     */     //   275: invokevirtual 342	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*     */     //   278: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   281: aload_0
/*     */     //   282: aload_0
/*     */     //   283: getfield 98	ictk/boardgame/chess/io/PGNWriterTest:game	Lictk/boardgame/Game;
/*     */     //   286: invokeinterface 211 1 0
/*     */     //   291: invokevirtual 339	ictk/boardgame/History:getCurrentMove	()Lictk/boardgame/Move;
/*     */     //   294: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   297: putfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   300: aload_0
/*     */     //   301: aload_0
/*     */     //   302: getfield 100	ictk/boardgame/chess/io/PGNWriterTest:game2	Lictk/boardgame/Game;
/*     */     //   305: invokeinterface 211 1 0
/*     */     //   310: invokevirtual 339	ictk/boardgame/History:getCurrentMove	()Lictk/boardgame/Move;
/*     */     //   313: checkcast 209	ictk/boardgame/chess/ChessMove
/*     */     //   316: putfield 345	ictk/boardgame/chess/io/PGNWriterTest:move2	Lictk/boardgame/chess/ChessMove;
/*     */     //   319: aload_0
/*     */     //   320: getfield 96	ictk/boardgame/chess/io/PGNWriterTest:move	Lictk/boardgame/chess/ChessMove;
/*     */     //   323: invokevirtual 347	ictk/boardgame/chess/ChessMove:getPrenotation	()Lictk/boardgame/io/Annotation;
/*     */     //   326: aload_0
/*     */     //   327: getfield 345	ictk/boardgame/chess/io/PGNWriterTest:move2	Lictk/boardgame/chess/ChessMove;
/*     */     //   330: invokevirtual 347	ictk/boardgame/chess/ChessMove:getPrenotation	()Lictk/boardgame/io/Annotation;
/*     */     //   333: invokevirtual 342	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*     */     //   336: invokestatic 189	ictk/boardgame/chess/io/PGNWriterTest:assertTrue	(Z)V
/*     */     //   339: goto +21 -> 360
/*     */     //   342: astore_1
/*     */     //   343: aload_1
/*     */     //   344: athrow
/*     */     //   345: astore_2
/*     */     //   346: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   349: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   352: getstatic 322	ictk/boardgame/History:DEBUG	J
/*     */     //   355: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   358: aload_2
/*     */     //   359: athrow
/*     */     //   360: getstatic 122	ictk/boardgame/chess/io/PGNWriter:DEBUG	J
/*     */     //   363: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   366: getstatic 322	ictk/boardgame/History:DEBUG	J
/*     */     //   369: invokestatic 110	ictk/util/Log:removeMask	(J)V
/*     */     //   372: return
/*     */     // Line number table:
/*     */     //   Java source line #320	-> byte code offset #0
/*     */     //   Java source line #321	-> byte code offset #11
/*     */     //   Java source line #323	-> byte code offset #28
/*     */     //   Java source line #325	-> byte code offset #48
/*     */     //   Java source line #326	-> byte code offset #64
/*     */     //   Java source line #328	-> byte code offset #81
/*     */     //   Java source line #330	-> byte code offset #104
/*     */     //   Java source line #332	-> byte code offset #115
/*     */     //   Java source line #333	-> byte code offset #140
/*     */     //   Java source line #335	-> byte code offset #151
/*     */     //   Java source line #336	-> byte code offset #175
/*     */     //   Java source line #337	-> byte code offset #200
/*     */     //   Java source line #339	-> byte code offset #225
/*     */     //   Java source line #340	-> byte code offset #238
/*     */     //   Java source line #342	-> byte code offset #251
/*     */     //   Java source line #343	-> byte code offset #263
/*     */     //   Java source line #342	-> byte code offset #275
/*     */     //   Java source line #346	-> byte code offset #281
/*     */     //   Java source line #347	-> byte code offset #300
/*     */     //   Java source line #348	-> byte code offset #319
/*     */     //   Java source line #350	-> byte code offset #342
/*     */     //   Java source line #351	-> byte code offset #343
/*     */     //   Java source line #353	-> byte code offset #345
/*     */     //   Java source line #354	-> byte code offset #346
/*     */     //   Java source line #355	-> byte code offset #352
/*     */     //   Java source line #356	-> byte code offset #358
/*     */     //   Java source line #354	-> byte code offset #360
/*     */     //   Java source line #355	-> byte code offset #366
/*     */     //   Java source line #357	-> byte code offset #372
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	373	0	this	PGNWriterTest
/*     */     //   342	2	1	e	Exception
/*     */     //   345	14	2	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	339	342	java/lang/Exception
/*     */     //   0	345	345	finally
/*     */   }
/*     */   
/*     */   public void testAnnotationThenPrenotation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 370 */     this.game = new ChessGame();
/* 371 */     this.board = ((ChessBoard)((ChessGame)this.game).getBoard());
/*     */     
/* 373 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/*     */     
/* 375 */     this.game.getHistory().add(this.move);
/* 376 */     this.move.setAnnotation(new ChessAnnotation("after1"));
/*     */     
/* 378 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e5"));
/* 379 */     this.game.getHistory().add(this.move);
/* 380 */     this.move.setPrenotation(new ChessAnnotation("before2"));
/*     */     
/* 382 */     this.writer = new PGNWriter(this.sw = new StringWriter());
/*     */     
/* 384 */     this.writer.writeGame(this.game);
/*     */     
/* 386 */     this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
/* 387 */     this.game2 = this.spgnin.readGame();
/*     */     
/* 389 */     assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
/* 390 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
/* 391 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
/*     */     
/* 393 */     this.game.getHistory().next();
/* 394 */     this.game2.getHistory().next();
/* 395 */     assertTrue(this.game.getHistory().getCurrentMove().equals(
/* 396 */       this.game2.getHistory().getCurrentMove()));
/*     */     
/*     */ 
/* 399 */     this.move = ((ChessMove)this.game.getHistory().getCurrentMove());
/* 400 */     this.move2 = ((ChessMove)this.game2.getHistory().getCurrentMove());
/* 401 */     assertTrue(this.move.getAnnotation().equals(this.move2.getAnnotation()));
/*     */     
/* 403 */     this.game.getHistory().next();
/* 404 */     this.game2.getHistory().next();
/* 405 */     this.move = ((ChessMove)this.game.getHistory().getCurrentMove());
/* 406 */     this.move2 = ((ChessMove)this.game2.getHistory().getCurrentMove());
/* 407 */     assertTrue(this.move.getPrenotation().equals(this.move2.getPrenotation()));
/*     */     
/* 409 */     Log.removeMask(PGNWriter.DEBUG);
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
/*     */   public void testPrenotationForVariation()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 423 */     this.game = new ChessGame();
/* 424 */     this.board = ((ChessBoard)((ChessGame)this.game).getBoard());
/*     */     
/* 426 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 427 */     this.game.getHistory().add(this.move);
/*     */     
/* 429 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e5"));
/* 430 */     this.game.getHistory().add(this.move);
/*     */     
/*     */ 
/* 433 */     this.game.getHistory().prev();
/* 434 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "c5"));
/* 435 */     this.game.getHistory().add(this.move);
/* 436 */     this.move.setPrenotation(new ChessAnnotation("Sicilian"));
/*     */     
/* 438 */     this.writer = new PGNWriter(this.sw = new StringWriter());
/*     */     
/* 440 */     this.writer.writeGame(this.game);
/*     */     
/* 442 */     this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
/* 443 */     this.game2 = this.spgnin.readGame();
/*     */     
/* 445 */     assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
/* 446 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
/* 447 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
/*     */     
/* 449 */     this.game.getHistory().next();
/* 450 */     this.game2.getHistory().next();
/*     */     
/*     */ 
/* 453 */     this.game.getHistory().next(1);
/* 454 */     this.game2.getHistory().next(1);
/*     */     
/* 456 */     this.move = ((ChessMove)this.game.getHistory().getCurrentMove());
/* 457 */     this.move2 = ((ChessMove)this.game2.getHistory().getCurrentMove());
/* 458 */     assertTrue(this.move.getPrenotation().equals(this.move2.getPrenotation()));
/*     */     
/* 460 */     Log.removeMask(PGNWriter.DEBUG);
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
/*     */   public void testNAGSymetry()
/*     */     throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception
/*     */   {
/* 474 */     this.game = new ChessGame();
/* 475 */     this.board = ((ChessBoard)((ChessGame)this.game).getBoard());
/*     */     
/* 477 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 478 */     this.anno = new ChessAnnotation();
/* 479 */     this.anno.addNAG(1);
/* 480 */     this.anno.addNAG(123);
/* 481 */     this.anno.addNAG(145);
/* 482 */     this.move.setAnnotation(this.anno);
/* 483 */     this.game.getHistory().add(this.move);
/*     */     
/* 485 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e5"));
/* 486 */     this.game.getHistory().add(this.move);
/*     */     
/* 488 */     this.writer = new PGNWriter(this.sw = new StringWriter());
/*     */     
/* 490 */     this.writer.writeGame(this.game);
/*     */     
/* 492 */     if (Log.isDebug(PGNWriter.DEBUG)) {
/* 493 */       this.writer = new PGNWriter(System.out);
/* 494 */       this.writer.writeGame(this.game);
/*     */     }
/*     */     
/* 497 */     this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
/* 498 */     this.game2 = this.spgnin.readGame();
/*     */     
/* 500 */     assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
/* 501 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
/* 502 */     assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
/*     */     
/* 504 */     Log.removeMask(PGNWriter.DEBUG);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\PGNWriterTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */