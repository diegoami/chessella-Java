/*    */ package ictk.boardgame.chess.ui.cli;
/*    */ 
/*    */ import ictk.boardgame.chess.ChessBoard;
/*    */ import ictk.boardgame.chess.ChessMove;
/*    */ import ictk.util.Log;
/*    */ import java.io.StringWriter;
/*    */ import junit.framework.TestCase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TxChessBoardDisplayTest
/*    */   extends TestCase
/*    */ {
/*    */   TxChessBoardDisplay display;
/*    */   ChessBoard board;
/*    */   ChessMove move;
/*    */   StringWriter swriter;
/*    */   String str;
/*    */   String str2;
/*    */   
/*    */   public TxChessBoardDisplayTest(String name)
/*    */   {
/* 47 */     super(name);
/*    */   }
/*    */   
/*    */   public void setUp() {
/* 51 */     this.board = new ChessBoard();
/* 52 */     this.display = new TxChessBoardDisplay(this.board, this.swriter = new StringWriter());
/*    */   }
/*    */   
/*    */   public void tearDown() {
/* 56 */     this.board = null;
/* 57 */     this.move = null;
/* 58 */     this.swriter = null;
/* 59 */     this.str = null;
/* 60 */     this.str2 = null;
/* 61 */     this.display = null;
/* 62 */     Log.removeMask(ChessBoard.DEBUG);
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testDefaultInitialPosition()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 64
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   13: aload_0
/*    */     //   14: aload_0
/*    */     //   15: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   18: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   21: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   24: aload_0
/*    */     //   25: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   28: aload_0
/*    */     //   29: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   32: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   35: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   38: goto +12 -> 50
/*    */     //   41: astore_1
/*    */     //   42: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   45: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   48: aload_1
/*    */     //   49: athrow
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: return
/*    */     // Line number table:
/*    */     //   Java source line #69	-> byte code offset #0
/*    */     //   Java source line #82	-> byte code offset #6
/*    */     //   Java source line #83	-> byte code offset #13
/*    */     //   Java source line #84	-> byte code offset #24
/*    */     //   Java source line #86	-> byte code offset #41
/*    */     //   Java source line #87	-> byte code offset #42
/*    */     //   Java source line #88	-> byte code offset #48
/*    */     //   Java source line #87	-> byte code offset #50
/*    */     //   Java source line #89	-> byte code offset #56
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	57	0	this	TxChessBoardDisplayTest
/*    */     //   41	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	41	41	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testDefaultInitialPositionFlipped()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 84
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_0
/*    */     //   11: invokevirtual 86	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setWhiteOnBottom	(Z)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #95	-> byte code offset #0
/*    */     //   Java source line #108	-> byte code offset #6
/*    */     //   Java source line #109	-> byte code offset #14
/*    */     //   Java source line #110	-> byte code offset #21
/*    */     //   Java source line #111	-> byte code offset #32
/*    */     //   Java source line #113	-> byte code offset #49
/*    */     //   Java source line #114	-> byte code offset #50
/*    */     //   Java source line #115	-> byte code offset #56
/*    */     //   Java source line #114	-> byte code offset #58
/*    */     //   Java source line #116	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testNoCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 90
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_0
/*    */     //   11: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #122	-> byte code offset #0
/*    */     //   Java source line #132	-> byte code offset #6
/*    */     //   Java source line #133	-> byte code offset #14
/*    */     //   Java source line #134	-> byte code offset #21
/*    */     //   Java source line #135	-> byte code offset #32
/*    */     //   Java source line #137	-> byte code offset #49
/*    */     //   Java source line #138	-> byte code offset #50
/*    */     //   Java source line #139	-> byte code offset #56
/*    */     //   Java source line #138	-> byte code offset #58
/*    */     //   Java source line #140	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testTopCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 97
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #146	-> byte code offset #0
/*    */     //   Java source line #158	-> byte code offset #6
/*    */     //   Java source line #159	-> byte code offset #14
/*    */     //   Java source line #160	-> byte code offset #21
/*    */     //   Java source line #161	-> byte code offset #32
/*    */     //   Java source line #163	-> byte code offset #49
/*    */     //   Java source line #164	-> byte code offset #50
/*    */     //   Java source line #165	-> byte code offset #56
/*    */     //   Java source line #164	-> byte code offset #58
/*    */     //   Java source line #166	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testRightCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 100
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_2
/*    */     //   11: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #172	-> byte code offset #0
/*    */     //   Java source line #182	-> byte code offset #6
/*    */     //   Java source line #183	-> byte code offset #14
/*    */     //   Java source line #184	-> byte code offset #21
/*    */     //   Java source line #185	-> byte code offset #32
/*    */     //   Java source line #187	-> byte code offset #49
/*    */     //   Java source line #188	-> byte code offset #50
/*    */     //   Java source line #189	-> byte code offset #56
/*    */     //   Java source line #188	-> byte code offset #58
/*    */     //   Java source line #190	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testBottomCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 103
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_4
/*    */     //   11: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #196	-> byte code offset #0
/*    */     //   Java source line #208	-> byte code offset #6
/*    */     //   Java source line #209	-> byte code offset #14
/*    */     //   Java source line #210	-> byte code offset #21
/*    */     //   Java source line #211	-> byte code offset #32
/*    */     //   Java source line #213	-> byte code offset #49
/*    */     //   Java source line #214	-> byte code offset #50
/*    */     //   Java source line #215	-> byte code offset #56
/*    */     //   Java source line #214	-> byte code offset #58
/*    */     //   Java source line #216	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testLeftCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 106
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: bipush 8
/*    */     //   12: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   15: aload_0
/*    */     //   16: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   19: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   22: aload_0
/*    */     //   23: aload_0
/*    */     //   24: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   27: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   30: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   33: aload_0
/*    */     //   34: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   37: aload_0
/*    */     //   38: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   41: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   44: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   47: goto +12 -> 59
/*    */     //   50: astore_1
/*    */     //   51: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   54: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   57: aload_1
/*    */     //   58: athrow
/*    */     //   59: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   62: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   65: return
/*    */     // Line number table:
/*    */     //   Java source line #222	-> byte code offset #0
/*    */     //   Java source line #232	-> byte code offset #6
/*    */     //   Java source line #233	-> byte code offset #15
/*    */     //   Java source line #234	-> byte code offset #22
/*    */     //   Java source line #235	-> byte code offset #33
/*    */     //   Java source line #237	-> byte code offset #50
/*    */     //   Java source line #238	-> byte code offset #51
/*    */     //   Java source line #239	-> byte code offset #57
/*    */     //   Java source line #238	-> byte code offset #59
/*    */     //   Java source line #240	-> byte code offset #65
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	66	0	this	TxChessBoardDisplayTest
/*    */     //   50	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	50	50	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testAllCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 109
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: iconst_2
/*    */     //   12: ior
/*    */     //   13: iconst_4
/*    */     //   14: ior
/*    */     //   15: bipush 8
/*    */     //   17: ior
/*    */     //   18: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   21: aload_0
/*    */     //   22: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   25: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   28: aload_0
/*    */     //   29: aload_0
/*    */     //   30: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   33: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   36: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   39: aload_0
/*    */     //   40: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   43: aload_0
/*    */     //   44: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   47: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   50: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   53: goto +12 -> 65
/*    */     //   56: astore_1
/*    */     //   57: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   60: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   63: aload_1
/*    */     //   64: athrow
/*    */     //   65: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   68: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   71: return
/*    */     // Line number table:
/*    */     //   Java source line #246	-> byte code offset #0
/*    */     //   Java source line #247	-> byte code offset #1
/*    */     //   Java source line #246	-> byte code offset #3
/*    */     //   Java source line #262	-> byte code offset #6
/*    */     //   Java source line #263	-> byte code offset #11
/*    */     //   Java source line #264	-> byte code offset #13
/*    */     //   Java source line #265	-> byte code offset #15
/*    */     //   Java source line #262	-> byte code offset #18
/*    */     //   Java source line #266	-> byte code offset #21
/*    */     //   Java source line #267	-> byte code offset #28
/*    */     //   Java source line #268	-> byte code offset #39
/*    */     //   Java source line #270	-> byte code offset #56
/*    */     //   Java source line #271	-> byte code offset #57
/*    */     //   Java source line #272	-> byte code offset #63
/*    */     //   Java source line #271	-> byte code offset #65
/*    */     //   Java source line #273	-> byte code offset #71
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	72	0	this	TxChessBoardDisplayTest
/*    */     //   56	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	56	56	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testCompact()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 112
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: invokevirtual 114	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setCompact	(Z)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #279	-> byte code offset #0
/*    */     //   Java source line #291	-> byte code offset #6
/*    */     //   Java source line #292	-> byte code offset #14
/*    */     //   Java source line #293	-> byte code offset #21
/*    */     //   Java source line #294	-> byte code offset #32
/*    */     //   Java source line #296	-> byte code offset #49
/*    */     //   Java source line #297	-> byte code offset #50
/*    */     //   Java source line #298	-> byte code offset #56
/*    */     //   Java source line #297	-> byte code offset #58
/*    */     //   Java source line #299	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testCompactAllCoordinates()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 118
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: iconst_2
/*    */     //   12: ior
/*    */     //   13: iconst_4
/*    */     //   14: ior
/*    */     //   15: bipush 8
/*    */     //   17: ior
/*    */     //   18: invokevirtual 92	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setVisibleCoordinates	(I)V
/*    */     //   21: aload_0
/*    */     //   22: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   25: iconst_1
/*    */     //   26: invokevirtual 114	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setCompact	(Z)V
/*    */     //   29: aload_0
/*    */     //   30: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   33: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   36: aload_0
/*    */     //   37: aload_0
/*    */     //   38: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   41: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   44: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   47: aload_0
/*    */     //   48: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   51: aload_0
/*    */     //   52: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   55: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   58: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   61: goto +12 -> 73
/*    */     //   64: astore_1
/*    */     //   65: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   68: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   71: aload_1
/*    */     //   72: athrow
/*    */     //   73: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   76: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   79: return
/*    */     // Line number table:
/*    */     //   Java source line #304	-> byte code offset #0
/*    */     //   Java source line #305	-> byte code offset #1
/*    */     //   Java source line #304	-> byte code offset #3
/*    */     //   Java source line #319	-> byte code offset #6
/*    */     //   Java source line #320	-> byte code offset #11
/*    */     //   Java source line #321	-> byte code offset #13
/*    */     //   Java source line #322	-> byte code offset #15
/*    */     //   Java source line #319	-> byte code offset #18
/*    */     //   Java source line #323	-> byte code offset #21
/*    */     //   Java source line #324	-> byte code offset #29
/*    */     //   Java source line #325	-> byte code offset #36
/*    */     //   Java source line #326	-> byte code offset #47
/*    */     //   Java source line #328	-> byte code offset #64
/*    */     //   Java source line #329	-> byte code offset #65
/*    */     //   Java source line #330	-> byte code offset #71
/*    */     //   Java source line #329	-> byte code offset #73
/*    */     //   Java source line #331	-> byte code offset #79
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	80	0	this	TxChessBoardDisplayTest
/*    */     //   64	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	64	64	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testLowerCaseCoords()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 121
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: invokevirtual 123	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setLowerCaseCoordinates	(Z)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #337	-> byte code offset #0
/*    */     //   Java source line #350	-> byte code offset #6
/*    */     //   Java source line #351	-> byte code offset #14
/*    */     //   Java source line #352	-> byte code offset #21
/*    */     //   Java source line #353	-> byte code offset #32
/*    */     //   Java source line #355	-> byte code offset #49
/*    */     //   Java source line #356	-> byte code offset #50
/*    */     //   Java source line #357	-> byte code offset #56
/*    */     //   Java source line #356	-> byte code offset #58
/*    */     //   Java source line #358	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void testInverse()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: ldc 127
/*    */     //   3: putfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   6: aload_0
/*    */     //   7: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   10: iconst_1
/*    */     //   11: invokevirtual 129	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:setInverse	(Z)V
/*    */     //   14: aload_0
/*    */     //   15: getfield 44	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:display	Lictk/boardgame/chess/ui/cli/TxChessBoardDisplay;
/*    */     //   18: invokevirtual 66	ictk/boardgame/chess/ui/cli/TxChessBoardDisplay:print	()V
/*    */     //   21: aload_0
/*    */     //   22: aload_0
/*    */     //   23: getfield 39	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:swriter	Ljava/io/StringWriter;
/*    */     //   26: invokevirtual 69	java/io/StringWriter:toString	()Ljava/lang/String;
/*    */     //   29: putfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   32: aload_0
/*    */     //   33: getfield 49	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str	Ljava/lang/String;
/*    */     //   36: aload_0
/*    */     //   37: getfield 51	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:str2	Ljava/lang/String;
/*    */     //   40: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   43: invokestatic 79	ictk/boardgame/chess/ui/cli/TxChessBoardDisplayTest:assertTrue	(Z)V
/*    */     //   46: goto +12 -> 58
/*    */     //   49: astore_1
/*    */     //   50: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   53: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   56: aload_1
/*    */     //   57: athrow
/*    */     //   58: getstatic 53	ictk/boardgame/chess/ChessBoard:DEBUG	J
/*    */     //   61: invokestatic 57	ictk/util/Log:removeMask	(J)V
/*    */     //   64: return
/*    */     // Line number table:
/*    */     //   Java source line #364	-> byte code offset #0
/*    */     //   Java source line #377	-> byte code offset #6
/*    */     //   Java source line #378	-> byte code offset #14
/*    */     //   Java source line #379	-> byte code offset #21
/*    */     //   Java source line #380	-> byte code offset #32
/*    */     //   Java source line #382	-> byte code offset #49
/*    */     //   Java source line #383	-> byte code offset #50
/*    */     //   Java source line #384	-> byte code offset #56
/*    */     //   Java source line #383	-> byte code offset #58
/*    */     //   Java source line #385	-> byte code offset #64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	65	0	this	TxChessBoardDisplayTest
/*    */     //   49	8	1	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   0	49	49	finally
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ui\cli\TxChessBoardDisplayTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */