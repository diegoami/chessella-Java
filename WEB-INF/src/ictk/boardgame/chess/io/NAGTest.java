/*     */ package ictk.boardgame.chess.io;
/*     */ 
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
/*     */ public class NAGTest
/*     */   extends TestCase
/*     */ {
/*     */   NAG nag;
/*     */   short[] nags;
/*     */   
/*     */   public NAGTest(String name)
/*     */   {
/*  35 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  39 */     this.nag = new NAG();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  43 */     this.nag = null;
/*  44 */     this.nags = null;
/*     */   }
/*     */   
/*     */   public void testVerboseString()
/*     */   {
/*  49 */     assertTrue(this.nag.numberToDescription(1).equals("good move"));
/*     */   }
/*     */   
/*     */   public void testNumberToString()
/*     */   {
/*  54 */     assertTrue(NAG.numberToString(1).equals("!"));
/*     */   }
/*     */   
/*     */   public void testIsSuffixNumber()
/*     */   {
/*  59 */     assertTrue(NAG.isSuffix(1));
/*     */   }
/*     */   
/*     */   public void testIsSuffixNumberNot()
/*     */   {
/*  64 */     assertFalse(NAG.isSuffix(7));
/*     */   }
/*     */   
/*     */   public void testIsSuffixString()
/*     */   {
/*  69 */     assertTrue(NAG.isSuffix("!?"));
/*     */   }
/*     */   
/*     */   public void testIsSuffixStringNot()
/*     */   {
/*  74 */     assertFalse(NAG.isSuffix("N"));
/*     */   }
/*     */   
/*     */   public void testIsSymbol()
/*     */   {
/*  79 */     assertTrue(NAG.isSymbol(145));
/*     */   }
/*     */   
/*     */   public void testString()
/*     */   {
/*  84 */     assertTrue(NAG.numberToString(14).equals("+="));
/*     */   }
/*     */   
/*     */   public void testStringNumeric()
/*     */   {
/*  89 */     assertTrue(NAG.numberToString(14, true).equals("$14"));
/*     */   }
/*     */   
/*     */   public void testStringReverse()
/*     */   {
/*  94 */     this.nags = NAG.stringToNumbers("$9");
/*  95 */     assertTrue(this.nags.length == 1);
/*  96 */     assertTrue(this.nags[0] == 9);
/*     */   }
/*     */   
/*     */   public void testSymbolToNumber()
/*     */   {
/* 101 */     assertTrue(NAG.symbolToNumber("N") == 146);
/*     */   }
/*     */   
/*     */   public void testStringToNumbers()
/*     */   {
/* 106 */     this.nags = NAG.stringToNumbers("! +=");
/* 107 */     assertTrue(this.nags.length == 2);
/* 108 */     assertTrue(this.nags[0] == 1);
/* 109 */     assertTrue(this.nags[1] == 14);
/*     */   }
/*     */   
/*     */   public void testStringToNumbersOne()
/*     */   {
/* 114 */     this.nags = NAG.stringToNumbers("$5");
/* 115 */     assertTrue(this.nags.length == 1);
/* 116 */     assertTrue(this.nags[0] == 5);
/*     */   }
/*     */   
/*     */   public void testStringToNumbersEmpty()
/*     */   {
/* 121 */     this.nags = NAG.stringToNumbers("");
/* 122 */     assertTrue(this.nags == null);
/*     */   }
/*     */   
/*     */   public void testStringToNumbersJunk()
/*     */   {
/* 127 */     this.nags = NAG.stringToNumbers("fjdkslfj32n23jdnj 3jerk32 jrker");
/* 128 */     assertTrue(this.nags == null);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\NAGTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */