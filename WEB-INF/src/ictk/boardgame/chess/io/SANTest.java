/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.chess.AmbiguousChessMoveException;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.util.Log;
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
/*     */ public class SANTest
/*     */   extends TestCase
/*     */ {
/*     */   SAN san;
/*     */   ChessBoard board;
/*     */   ChessResult res;
/*     */   ChessMove move;
/*     */   
/*     */   public SANTest(String name)
/*     */   {
/*  41 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  45 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  49 */     this.san = null;
/*  50 */     this.board = null;
/*  51 */     this.res = null;
/*  52 */     this.move = null;
/*  53 */     Log.removeMask(SAN.DEBUG);
/*  54 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */   public void testStringToResult()
/*     */   {
/*  59 */     this.res = ((ChessResult)this.san.stringToResult("1-0"));
/*  60 */     assertTrue(this.res.equals(new ChessResult(2)));
/*  61 */     this.res = ((ChessResult)this.san.stringToResult("0-1"));
/*  62 */     assertTrue(this.res.equals(new ChessResult(3)));
/*  63 */     this.res = ((ChessResult)this.san.stringToResult("1/2-1/2"));
/*  64 */     assertTrue(this.res.equals(new ChessResult(1)));
/*  65 */     this.res = ((ChessResult)this.san.stringToResult("*"));
/*  66 */     assertTrue(this.res.equals(new ChessResult(0)));
/*  67 */     this.res = ((ChessResult)this.san.stringToResult(""));
/*  68 */     assertTrue(this.res == null);
/*  69 */     this.res = ((ChessResult)this.san.stringToResult("fjdkslfjs"));
/*  70 */     assertTrue(this.res == null);
/*     */   }
/*     */   
/*     */   public void testResultToString()
/*     */   {
/*  75 */     this.res = new ChessResult(2);
/*  76 */     assertTrue("1-0".equals(this.san.resultToString(this.res)));
/*  77 */     this.res = new ChessResult(3);
/*  78 */     assertTrue("0-1".equals(this.san.resultToString(this.res)));
/*  79 */     this.res = new ChessResult(1);
/*  80 */     assertTrue("1/2-1/2".equals(this.san.resultToString(this.res)));
/*  81 */     this.res = new ChessResult(0);
/*  82 */     assertTrue("*".equals(this.san.resultToString(this.res)));
/*     */   }
/*     */   
/*     */ 
/*     */   public void testStringToMove_1()
/*     */     throws IllegalMoveException, AmbiguousMoveException
/*     */   {
/*  89 */     this.board = new ChessBoard();
/*  90 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/*  91 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testMoveToString_1()
/*     */     throws IllegalMoveException, AmbiguousMoveException
/*     */   {
/*  98 */     this.board = new ChessBoard();
/*  99 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 100 */     assertTrue(this.san.moveToString(this.move).equals("e4"));
/*     */   }
/*     */   
/*     */   public void testStringToMove_2()
/*     */   {
/* 105 */     this.board = new ChessBoard();
/*     */     try {
/* 107 */       assertTrue((ChessMove)this.san.stringToMove(this.board, "24") == null);
/* 108 */       assertTrue((ChessMove)this.san.stringToMove(this.board, "fdfd") == null);
/* 109 */       assertTrue((ChessMove)this.san.stringToMove(this.board, "") == null);
/* 110 */       assertTrue((ChessMove)this.san.stringToMove(this.board, "\tf5#@") == null);
/*     */       try {
/* 112 */         this.move = ((ChessMove)this.san.stringToMove(this.board, "e5"));
/*     */       }
/*     */       catch (IllegalMoveException localIllegalMoveException) {}
/*     */       return;
/*     */     } catch (Exception e) {
/* 117 */       fail("san parsed garbage and threw and exception: " + e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testPromotion()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 126 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 127 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 128 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 129 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 130 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 131 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 132 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 133 */       { 'K', ' ', ' ', ' ', ' ', ' ', 'k', ' ' } };
/*     */     
/* 135 */     this.board = new ChessBoard(position);
/* 136 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e8=Q"));
/* 137 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testPromotionWithCheck()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 145 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 146 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 147 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 148 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 149 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 150 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 151 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 152 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 154 */     this.board = new ChessBoard(position);
/* 155 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e8=Q+"));
/* 156 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testPromotionWithCapture()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 165 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 166 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 167 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 168 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n' }, 
/* 169 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 170 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 171 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 172 */       { 'K', ' ', ' ', ' ', ' ', ' ', 'k', ' ' } };
/*     */     
/* 174 */     this.board = new ChessBoard(position);
/* 175 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "exd8=Q"));
/* 176 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testPromotionWithCaptureAndCheck()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 184 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 185 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 186 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 187 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n' }, 
/* 188 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/* 189 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 190 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 191 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/* 193 */     this.board = new ChessBoard(position);
/* 194 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "exd8=Q+"));
/* 195 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testPromotionWithCaptureAndDoubleCheck()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 203 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 204 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 205 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 206 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n' }, 
/* 207 */       { 'R', ' ', ' ', ' ', ' ', ' ', 'P', 'k' }, 
/* 208 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 209 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 210 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
/*     */     
/* 212 */     this.board = new ChessBoard(position);
/* 213 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "exd8=Q++"));
/* 214 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testFullDisAmbiguation()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 224 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 225 */       { 'q', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 226 */       { 'q', ' ', ' ', 'p', 'n', ' ', ' ', ' ' }, 
/* 227 */       { ' ', ' ', ' ', 'P', 'p', ' ', ' ', 'k' }, 
/* 228 */       { ' ', ' ', ' ', ' ', 'P', 'p', 'q', 'b' }, 
/* 229 */       { 'N', ' ', ' ', ' ', ' ', 'Q', ' ', ' ' }, 
/* 230 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/* 231 */       { 'K', ' ', ' ', ' ', ' ', 'Q', ' ', 'Q' } };
/*     */     
/* 233 */     this.board = new ChessBoard(position);
/* 234 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "Qh6f8"));
/* 235 */     assertTrue(this.move != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testNAG()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 243 */     ChessAnnotation anno = null;
/*     */     
/* 245 */     this.board = new ChessBoard();
/* 246 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4!"));
/* 247 */     assertTrue(this.move != null);
/* 248 */     anno = (ChessAnnotation)this.move.getAnnotation();
/* 249 */     assertTrue(anno != null);
/* 250 */     assertTrue(anno.getNAGString().equals("!"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testNAG1()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 258 */     ChessAnnotation anno = null;
/*     */     
/* 260 */     this.board = new ChessBoard();
/* 261 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4! +="));
/* 262 */     assertTrue(this.move != null);
/* 263 */     anno = (ChessAnnotation)this.move.getAnnotation();
/* 264 */     assertTrue(anno != null);
/* 265 */     assertTrue(anno.getNAGString().equals("! +="));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testNAGNumeric()
/*     */     throws IllegalMoveException, AmbiguousChessMoveException
/*     */   {
/* 273 */     ChessAnnotation anno = null;
/*     */     
/* 275 */     this.board = new ChessBoard();
/* 276 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4 $9"));
/* 277 */     assertTrue(this.move != null);
/* 278 */     anno = (ChessAnnotation)this.move.getAnnotation();
/* 279 */     assertTrue(anno != null);
/* 280 */     assertTrue(anno.getNAGString().equals("$9"));
/*     */   }
/*     */   
/*     */   public void testFileToChar()
/*     */   {
/* 285 */     assertTrue(this.san.fileToChar(1) == 'a');
/* 286 */     assertTrue(this.san.fileToChar(8) == 'h');
/*     */     try {
/* 288 */       this.san.fileToChar(0);
/* 289 */       fail("FileToChar bounds not correct 0");
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/*     */     try
/*     */     {
/* 294 */       this.san.fileToChar(9);
/* 295 */       fail("FileToChar bounds not correct 9");
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void testRankToChar()
/*     */   {
/* 303 */     assertTrue(this.san.rankToChar(1) == '1');
/* 304 */     assertTrue(this.san.rankToChar(8) == '8');
/*     */     try {
/* 306 */       this.san.rankToChar(0);
/* 307 */       fail("RankToChar bounds not correct 0");
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/*     */     try
/*     */     {
/* 312 */       this.san.rankToChar(9);
/* 313 */       fail("RankToChar bounds not correct 9");
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\SANTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */