/*    */ package com.amicabile.openingtrainer.pgn;
/*    */ 
/*    */ import ictk.boardgame.ContinuationList;
/*    */ import ictk.boardgame.History;
/*    */ import ictk.boardgame.Move;
/*    */ import ictk.boardgame.chess.ChessGame;
/*    */ import ictk.boardgame.chess.io.PGNReader;
/*    */ import ictk.boardgame.chess.io.PGNWriter;
/*    */ import ictk.boardgame.io.Annotation;
/*    */ import java.io.Reader;
/*    */ import java.io.Writer;
/*    */ import org.apache.commons.digester.RegexMatcher;
/*    */ import org.apache.commons.digester.SimpleRegexMatcher;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PGNStripper
/*    */ {
/* 24 */   private static transient Logger log = Logger.getLogger(PGNStripper.class);
/*    */   
/*    */ 
/*    */   private String stripAnnotation(Annotation annotation)
/*    */   {
/* 29 */     String comment = annotation.getComment();
/* 30 */     if (comment == null)
/* 31 */       return null;
/* 32 */     if (comment.contains("[")) {
/* 33 */       return "";
/*    */     }
/* 35 */     RegexMatcher regexMatcher = new SimpleRegexMatcher();
/* 36 */     if (regexMatcher.match(comment, "[a-zA-Z0-9 ]*")) {
/* 37 */       return "";
/*    */     }
/* 39 */     return comment;
/*    */   }
/*    */   
/*    */   public void stripComments(Reader freader, Writer writer)
/*    */   {
/* 44 */     PGNReader reader = new PGNReader(freader);
/* 45 */     PGNWriter pgnWriter = new PGNWriter(writer);
/*    */     
/* 47 */     ChessGame game = null;
/* 48 */     int gameCounter = 0;
/*    */     do {
/* 50 */       gameCounter++;
/*    */       try {
/* 52 */         game = (ChessGame)reader.readGame();
/* 53 */         log.debug("READ GAME " + gameCounter);
/* 54 */         if (game != null) {
/* 55 */           History history = game.getHistory();
/* 56 */           history.rewind();
/* 57 */           Move firstMove = history.getFirst();
/* 58 */           ContinuationList continuationList = firstMove.getContinuationList();
/*    */           
/* 60 */           while (history.getNext() != null) {
/* 61 */             Move nextMove = history.next();
/* 62 */             log.debug("READ MOVE = " + nextMove);
/*    */             
/* 64 */             Annotation annotation = nextMove.getAnnotation();
/* 65 */             Annotation prenotation = nextMove.getPrenotation();
/* 66 */             if (annotation != null)
/* 67 */               annotation.setComment(stripAnnotation(annotation));
/* 68 */             if (prenotation != null)
/* 69 */               prenotation.setComment(stripAnnotation(prenotation));
/*    */           }
/* 71 */           pgnWriter.writeGame(game);
/*    */         }
/*    */       }
/*    */       catch (Exception e) {
/* 75 */         e.printStackTrace();
/*    */       }
/* 77 */     } while (game != null);
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: arraylength
/*    */     //   2: iconst_2
/*    */     //   3: if_icmpge +14 -> 17
/*    */     //   6: getstatic 172	java/lang/System:out	Ljava/io/PrintStream;
/*    */     //   9: ldc -78
/*    */     //   11: invokevirtual 180	java/io/PrintStream:println	(Ljava/lang/String;)V
/*    */     //   14: goto +127 -> 141
/*    */     //   17: aload_0
/*    */     //   18: iconst_0
/*    */     //   19: aaload
/*    */     //   20: astore_1
/*    */     //   21: aload_0
/*    */     //   22: iconst_1
/*    */     //   23: aaload
/*    */     //   24: astore_2
/*    */     //   25: aconst_null
/*    */     //   26: astore_3
/*    */     //   27: aconst_null
/*    */     //   28: astore 4
/*    */     //   30: aconst_null
/*    */     //   31: astore 5
/*    */     //   33: aconst_null
/*    */     //   34: astore 6
/*    */     //   36: aconst_null
/*    */     //   37: astore 7
/*    */     //   39: aconst_null
/*    */     //   40: astore 8
/*    */     //   42: new 185	java/io/BufferedWriter
/*    */     //   45: dup
/*    */     //   46: new 187	java/io/FileWriter
/*    */     //   49: dup
/*    */     //   50: aload_2
/*    */     //   51: invokespecial 189	java/io/FileWriter:<init>	(Ljava/lang/String;)V
/*    */     //   54: dup
/*    */     //   55: astore_3
/*    */     //   56: invokespecial 190	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
/*    */     //   59: astore 5
/*    */     //   61: new 191	java/io/BufferedReader
/*    */     //   64: dup
/*    */     //   65: new 193	java/io/FileReader
/*    */     //   68: dup
/*    */     //   69: aload_1
/*    */     //   70: invokespecial 195	java/io/FileReader:<init>	(Ljava/lang/String;)V
/*    */     //   73: dup
/*    */     //   74: astore 6
/*    */     //   76: invokespecial 196	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
/*    */     //   79: astore 8
/*    */     //   81: new 1	com/amicabile/openingtrainer/pgn/PGNStripper
/*    */     //   84: dup
/*    */     //   85: invokespecial 197	com/amicabile/openingtrainer/pgn/PGNStripper:<init>	()V
/*    */     //   88: aload 8
/*    */     //   90: aload 5
/*    */     //   92: invokevirtual 198	com/amicabile/openingtrainer/pgn/PGNStripper:stripComments	(Ljava/io/Reader;Ljava/io/Writer;)V
/*    */     //   95: goto +27 -> 122
/*    */     //   98: astore 9
/*    */     //   100: aload 8
/*    */     //   102: invokevirtual 200	java/io/BufferedReader:close	()V
/*    */     //   105: aload 5
/*    */     //   107: invokevirtual 203	java/io/BufferedWriter:close	()V
/*    */     //   110: aload 6
/*    */     //   112: invokevirtual 204	java/io/FileReader:close	()V
/*    */     //   115: aload_3
/*    */     //   116: invokevirtual 205	java/io/FileWriter:close	()V
/*    */     //   119: aload 9
/*    */     //   121: athrow
/*    */     //   122: aload 8
/*    */     //   124: invokevirtual 200	java/io/BufferedReader:close	()V
/*    */     //   127: aload 5
/*    */     //   129: invokevirtual 203	java/io/BufferedWriter:close	()V
/*    */     //   132: aload 6
/*    */     //   134: invokevirtual 204	java/io/FileReader:close	()V
/*    */     //   137: aload_3
/*    */     //   138: invokevirtual 205	java/io/FileWriter:close	()V
/*    */     //   141: return
/*    */     // Line number table:
/*    */     //   Java source line #81	-> byte code offset #0
/*    */     //   Java source line #82	-> byte code offset #6
/*    */     //   Java source line #84	-> byte code offset #17
/*    */     //   Java source line #85	-> byte code offset #21
/*    */     //   Java source line #86	-> byte code offset #25
/*    */     //   Java source line #87	-> byte code offset #27
/*    */     //   Java source line #88	-> byte code offset #30
/*    */     //   Java source line #89	-> byte code offset #33
/*    */     //   Java source line #90	-> byte code offset #36
/*    */     //   Java source line #91	-> byte code offset #39
/*    */     //   Java source line #94	-> byte code offset #42
/*    */     //   Java source line #95	-> byte code offset #61
/*    */     //   Java source line #96	-> byte code offset #81
/*    */     //   Java source line #97	-> byte code offset #98
/*    */     //   Java source line #98	-> byte code offset #100
/*    */     //   Java source line #99	-> byte code offset #105
/*    */     //   Java source line #100	-> byte code offset #110
/*    */     //   Java source line #101	-> byte code offset #115
/*    */     //   Java source line #104	-> byte code offset #119
/*    */     //   Java source line #98	-> byte code offset #122
/*    */     //   Java source line #99	-> byte code offset #127
/*    */     //   Java source line #100	-> byte code offset #132
/*    */     //   Java source line #101	-> byte code offset #137
/*    */     //   Java source line #108	-> byte code offset #141
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	142	0	args	String[]
/*    */     //   20	50	1	fileInString	String
/*    */     //   24	27	2	fileOutString	String
/*    */     //   26	112	3	fw	java.io.FileWriter
/*    */     //   28	3	4	fwriter	Writer
/*    */     //   31	97	5	bw	java.io.BufferedWriter
/*    */     //   34	99	6	fr	java.io.FileReader
/*    */     //   37	3	7	freader	Reader
/*    */     //   40	83	8	br	java.io.BufferedReader
/*    */     //   98	22	9	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   42	98	98	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\PGNStripper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */