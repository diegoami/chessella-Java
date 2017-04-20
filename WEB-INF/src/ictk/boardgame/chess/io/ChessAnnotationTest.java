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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChessAnnotationTest
/*     */   extends TestCase
/*     */ {
/*     */   ChessAnnotation anno;
/*     */   
/*     */   public ChessAnnotationTest(String name)
/*     */   {
/*  37 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  41 */     this.anno = new ChessAnnotation();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  45 */     this.anno = null;
/*     */   }
/*     */   
/*     */   public void testNAGAdd()
/*     */   {
/*  50 */     this.anno.addNAG(1);
/*  51 */     assertTrue(this.anno.getSuffix() == 1);
/*  52 */     assertTrue(this.anno.getNAG(0) == 1);
/*     */   }
/*     */   
/*     */   public void testNAGAdd2()
/*     */   {
/*  57 */     this.anno.addNAG(123);
/*  58 */     assertTrue(this.anno.getSuffix() == 0);
/*  59 */     assertTrue(this.anno.getNAG(0) == 123);
/*     */   }
/*     */   
/*     */   public void testNAGAdd3()
/*     */   {
/*  64 */     this.anno.addNAG(123);
/*  65 */     this.anno.addNAG(2);
/*  66 */     assertTrue(this.anno.getNAGs().length == 2);
/*     */   }
/*     */   
/*     */   public void testNAGRemove()
/*     */   {
/*  71 */     this.anno.addNAG(123);
/*  72 */     this.anno.addNAG(2);
/*  73 */     this.anno.addNAG(8);
/*  74 */     assertTrue(this.anno.getNAGs().length == 3);
/*  75 */     this.anno.removeNAG(1);
/*  76 */     assertTrue(this.anno.getNAGs().length == 2);
/*  77 */     assertTrue(this.anno.getNAGs()[0] == 123);
/*  78 */     assertTrue(this.anno.getNAGs()[1] == 8);
/*  79 */     this.anno.removeNAG(1);
/*  80 */     assertTrue(this.anno.getNAGs().length == 1);
/*  81 */     assertTrue(this.anno.getNAGs()[0] == 123);
/*  82 */     this.anno.removeNAG(0);
/*  83 */     assertTrue(this.anno.getNAGs() == null);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testNAGRemove2()
/*     */   {
/*  89 */     this.anno.addNAG(123);
/*  90 */     this.anno.addNAG(2);
/*  91 */     this.anno.addNAG(8);
/*  92 */     this.anno.addNAG(55);
/*  93 */     assertTrue(this.anno.getNAGs().length == 4);
/*  94 */     this.anno.removeNAG(0);
/*  95 */     assertTrue(this.anno.getNAGs().length == 3);
/*  96 */     assertTrue(this.anno.getNAGs()[0] == 2);
/*  97 */     assertTrue(this.anno.getNAGs()[1] == 8);
/*  98 */     assertTrue(this.anno.getNAGs()[2] == 55);
/*  99 */     this.anno.removeNAG(2);
/* 100 */     assertTrue(this.anno.getNAGs().length == 2);
/* 101 */     assertTrue(this.anno.getNAGs()[0] == 2);
/* 102 */     assertTrue(this.anno.getNAGs()[1] == 8);
/*     */   }
/*     */   
/*     */   public void testEquality()
/*     */   {
/* 107 */     ChessAnnotation anno2 = new ChessAnnotation();
/*     */     
/* 109 */     this.anno.addNAG(123);
/* 110 */     this.anno.addNAG(2);
/* 111 */     anno2.addNAG(123);
/* 112 */     anno2.addNAG(2);
/* 113 */     this.anno.setComment("best by test");
/* 114 */     anno2.setComment("best by test");
/*     */     
/* 116 */     assertTrue(this.anno.equals(anno2));
/*     */   }
/*     */   
/*     */   public void testNotEquality()
/*     */   {
/* 121 */     ChessAnnotation anno2 = new ChessAnnotation();
/*     */     
/* 123 */     this.anno.addNAG(123);
/* 124 */     this.anno.addNAG(2);
/* 125 */     anno2.addNAG(123);
/* 126 */     anno2.addNAG(3);
/* 127 */     this.anno.setComment("best by test");
/* 128 */     anno2.setComment("best by test");
/*     */     
/* 130 */     assertFalse(this.anno.equals(anno2));
/*     */   }
/*     */   
/*     */   public void testNAGStoString()
/*     */   {
/* 135 */     this.anno.addNAG(123);
/* 136 */     this.anno.addNAG(2);
/* 137 */     assertTrue(this.anno.getNAGs().length == 2);
/*     */     
/* 139 */     assertTrue(this.anno.getNAGString().equals("$123 ?"));
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\ChessAnnotationTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */