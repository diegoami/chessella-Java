/*     */ package com.amicabile.openingtrainer.pgn;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessPiece;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import ictk.boardgame.chess.io.PGNReader;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.io.InvalidGameFormatException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class PGNAdapter
/*     */ {
/*  25 */   private static transient Logger log = Logger.getLogger(PGNAdapter.class);
/*     */   
/*  27 */   private static int MAX_GAMES = 5000;
/*     */   
/*     */   public static String getFenStringForMove(Game game, Move move) {
/*  30 */     FEN fen = new FEN();
/*  31 */     game.getHistory().rewind();
/*  32 */     if (move != null) {
/*  33 */       game.getHistory().goTo(move);
/*     */     }
/*  35 */     String fenString = fen.boardToString(((ChessGame)game).getBoard());
/*  36 */     return fenString;
/*     */   }
/*     */   
/*     */   public static String getFenStringForMove(Game game)
/*     */   {
/*  41 */     return getFenStringForMove(game, null);
/*     */   }
/*     */   
/*     */   public static String getLetterFromPiece(ChessPiece piece)
/*     */   {
/*  46 */     if (piece.isBishop()) return "b";
/*  47 */     if (piece.isQueen()) return "q";
/*  48 */     if (piece.isKnight()) return "n";
/*  49 */     if (piece.isRook()) return "r";
/*  50 */     if (piece.isKing()) return "k";
/*  51 */     if (piece.isPawn()) return "p";
/*  52 */     return "";
/*     */   }
/*     */   
/*     */   public static ChessGameGroup getChessGameGroupFromFile(String gameFile)
/*     */     throws PGNException
/*     */   {
/*  58 */     log.info("Reading File : " + gameFile);
/*     */     
/*     */ 
/*  61 */     FileReader freader = null;
/*     */     try {
/*  63 */       freader = new FileReader(gameFile);
/*  64 */       ChessGameGroup gameGroup = getChessGameGroupFromStream(freader);
/*  65 */       freader.close();
/*  66 */       log.info("Finished reading file " + gameFile);
/*  67 */       return gameGroup;
/*     */     } catch (Exception e) {
/*  69 */       throw new PGNException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ChessGameGroup getChessGameGroupFromStream(Reader freader)
/*     */   {
/*  77 */     ChessGameGroup gameGroup = new ChessGameGroup();
/*     */     
/*  79 */     PGNReader reader = new PGNReader(freader);
/*     */     
/*     */ 
/*  82 */     ChessGame game = null;
/*  83 */     int gameCounter = 0;
/*     */     do {
/*  85 */       gameCounter++;
/*     */       try {
/*  87 */         log.debug("Processing game : " + gameCounter);
/*     */         
/*     */ 
/*  90 */         game = (ChessGame)reader.readGame();
/*     */         
/*  92 */         if (game != null) {
/*  93 */           log.debug("Successfully read game");
/*  94 */           log.debug("--Game Info--");
/*  95 */           log.debug(game.getGameInfo());
/*  96 */           log.debug("--Game History--");
/*  97 */           log.debug(game.getHistory());
/*     */         }
/*     */         
/*     */ 
/* 101 */         if (game != null) {
/* 102 */           gameGroup.addChessGame(game);
/* 103 */           log.debug("Successfully read game " + game.getGameInfo().get("Board"));
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 108 */         log.warn("COULD NOT READ GAME " + gameCounter + " in file ", e);
/* 109 */         log.warn("Adding empty game ");
/*     */         
/* 111 */         gameGroup.addChessGame(new ChessGame());
/*     */       }
/* 113 */     } while ((game != null) && (gameCounter < MAX_GAMES));
/* 114 */     return gameGroup;
/*     */   }
/*     */   
/*     */   public static ChessGame getGameFromStream(Reader freader)
/*     */     throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException
/*     */   {
/* 120 */     PGNReader reader = new PGNReader(freader);
/*     */     
/* 122 */     ChessGame game = (ChessGame)reader.readGame();
/* 123 */     if (game != null) {
/* 124 */       log.debug("Successfully read game");
/* 125 */       log.debug("--Game Info--");
/* 126 */       log.debug(game.getGameInfo());
/* 127 */       log.debug("--Game History--");
/* 128 */       log.debug(game.getHistory());
/*     */     }
/*     */     
/* 131 */     return game;
/*     */   }
/*     */   
/*     */   public static void writeGroupToFile(Writer fWriter, ChessGameGroup gameGroup) throws PGNException {
/*     */     try {
/* 136 */       PGNWriter writer = new PGNWriter(fWriter);
/*     */       
/* 138 */       int counter = 0;
/*     */       
/* 140 */       List<ChessGame> chessGameList = gameGroup.getGameList();
/* 141 */       log.info("Got sorted ordered games");
/*     */       
/* 143 */       for (ChessGame game : chessGameList) {
/*     */         try {
/* 145 */           counter++;
/*     */           
/* 147 */           writer.writeGame(game);
/*     */         } catch (Exception e) {
/* 149 */           log.error("Could not write game " + counter);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 155 */       e.printStackTrace();
/* 156 */       throw new PGNException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public static void writeGroupToFile(String gameFile, ChessGameGroup gameGroup)
/*     */     throws PGNException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: getstatic 18	com/amicabile/openingtrainer/pgn/PGNAdapter:log	Lorg/apache/log4j/Logger;
/*     */     //   3: new 114	java/lang/StringBuilder
/*     */     //   6: dup
/*     */     //   7: ldc_w 280
/*     */     //   10: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   17: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   20: invokevirtual 129	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
/*     */     //   23: aconst_null
/*     */     //   24: astore_2
/*     */     //   25: aconst_null
/*     */     //   26: astore_3
/*     */     //   27: aconst_null
/*     */     //   28: astore 4
/*     */     //   30: new 282	java/io/BufferedWriter
/*     */     //   33: dup
/*     */     //   34: new 284	java/io/FileWriter
/*     */     //   37: dup
/*     */     //   38: aload_0
/*     */     //   39: invokespecial 286	java/io/FileWriter:<init>	(Ljava/lang/String;)V
/*     */     //   42: dup
/*     */     //   43: astore_3
/*     */     //   44: invokespecial 287	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
/*     */     //   47: astore_2
/*     */     //   48: aload_2
/*     */     //   49: aload_1
/*     */     //   50: invokestatic 288	com/amicabile/openingtrainer/pgn/PGNAdapter:writeGroupToFile	(Ljava/io/Writer;Lcom/amicabile/openingtrainer/pgn/ChessGameGroup;)V
/*     */     //   53: goto +51 -> 104
/*     */     //   56: astore 5
/*     */     //   58: aload 5
/*     */     //   60: invokevirtual 267	java/lang/Exception:printStackTrace	()V
/*     */     //   63: new 112	com/amicabile/openingtrainer/pgn/PGNException
/*     */     //   66: dup
/*     */     //   67: aload 5
/*     */     //   69: invokespecial 145	com/amicabile/openingtrainer/pgn/PGNException:<init>	(Ljava/lang/Throwable;)V
/*     */     //   72: athrow
/*     */     //   73: astore 6
/*     */     //   75: aload_2
/*     */     //   76: ifnull +7 -> 83
/*     */     //   79: aload_2
/*     */     //   80: invokevirtual 290	java/io/BufferedWriter:close	()V
/*     */     //   83: aload_3
/*     */     //   84: ifnull +17 -> 101
/*     */     //   87: aload_3
/*     */     //   88: invokevirtual 291	java/io/FileWriter:close	()V
/*     */     //   91: goto +10 -> 101
/*     */     //   94: astore 7
/*     */     //   96: aload 7
/*     */     //   98: invokevirtual 292	java/io/IOException:printStackTrace	()V
/*     */     //   101: aload 6
/*     */     //   103: athrow
/*     */     //   104: aload_2
/*     */     //   105: ifnull +7 -> 112
/*     */     //   108: aload_2
/*     */     //   109: invokevirtual 290	java/io/BufferedWriter:close	()V
/*     */     //   112: aload_3
/*     */     //   113: ifnull +17 -> 130
/*     */     //   116: aload_3
/*     */     //   117: invokevirtual 291	java/io/FileWriter:close	()V
/*     */     //   120: goto +10 -> 130
/*     */     //   123: astore 7
/*     */     //   125: aload 7
/*     */     //   127: invokevirtual 292	java/io/IOException:printStackTrace	()V
/*     */     //   130: return
/*     */     // Line number table:
/*     */     //   Java source line #164	-> byte code offset #0
/*     */     //   Java source line #165	-> byte code offset #23
/*     */     //   Java source line #166	-> byte code offset #25
/*     */     //   Java source line #167	-> byte code offset #27
/*     */     //   Java source line #169	-> byte code offset #30
/*     */     //   Java source line #170	-> byte code offset #48
/*     */     //   Java source line #171	-> byte code offset #56
/*     */     //   Java source line #172	-> byte code offset #58
/*     */     //   Java source line #173	-> byte code offset #63
/*     */     //   Java source line #174	-> byte code offset #73
/*     */     //   Java source line #176	-> byte code offset #75
/*     */     //   Java source line #178	-> byte code offset #79
/*     */     //   Java source line #180	-> byte code offset #83
/*     */     //   Java source line #182	-> byte code offset #87
/*     */     //   Java source line #186	-> byte code offset #94
/*     */     //   Java source line #187	-> byte code offset #96
/*     */     //   Java source line #189	-> byte code offset #101
/*     */     //   Java source line #176	-> byte code offset #104
/*     */     //   Java source line #178	-> byte code offset #108
/*     */     //   Java source line #180	-> byte code offset #112
/*     */     //   Java source line #182	-> byte code offset #116
/*     */     //   Java source line #186	-> byte code offset #123
/*     */     //   Java source line #187	-> byte code offset #125
/*     */     //   Java source line #190	-> byte code offset #130
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	131	0	gameFile	String
/*     */     //   0	131	1	gameGroup	ChessGameGroup
/*     */     //   24	85	2	bw	java.io.BufferedWriter
/*     */     //   26	91	3	fw	java.io.FileWriter
/*     */     //   28	3	4	fwriter	Writer
/*     */     //   56	12	5	e	Exception
/*     */     //   73	29	6	localObject	Object
/*     */     //   94	3	7	ioe	IOException
/*     */     //   123	3	7	ioe	IOException
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   30	53	56	java/lang/Exception
/*     */     //   30	73	73	finally
/*     */     //   75	91	94	java/io/IOException
/*     */     //   104	120	123	java/io/IOException
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\PGNAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */