package ictk.boardgame.chess.io;

import ictk.boardgame.chess.io.ChessAnnotation;
import junit.framework.TestCase;

public class ChessAnnotationTest extends TestCase {

   ChessAnnotation anno;


   public ChessAnnotationTest(String name) {
      super(name);
   }

   public void setUp() {
      this.anno = new ChessAnnotation();
   }

   public void tearDown() {
      this.anno = null;
   }

   public void testNAGAdd() {
      this.anno.addNAG(1);
      assertTrue(this.anno.getSuffix() == 1);
      assertTrue(this.anno.getNAG(0) == 1);
   }

   public void testNAGAdd2() {
      this.anno.addNAG(123);
      assertTrue(this.anno.getSuffix() == 0);
      assertTrue(this.anno.getNAG(0) == 123);
   }

   public void testNAGAdd3() {
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      assertTrue(this.anno.getNAGs().length == 2);
   }

   public void testNAGRemove() {
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      this.anno.addNAG(8);
      assertTrue(this.anno.getNAGs().length == 3);
      this.anno.removeNAG(1);
      assertTrue(this.anno.getNAGs().length == 2);
      assertTrue(this.anno.getNAGs()[0] == 123);
      assertTrue(this.anno.getNAGs()[1] == 8);
      this.anno.removeNAG(1);
      assertTrue(this.anno.getNAGs().length == 1);
      assertTrue(this.anno.getNAGs()[0] == 123);
      this.anno.removeNAG(0);
      assertTrue(this.anno.getNAGs() == null);
   }

   public void testNAGRemove2() {
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      this.anno.addNAG(8);
      this.anno.addNAG(55);
      assertTrue(this.anno.getNAGs().length == 4);
      this.anno.removeNAG(0);
      assertTrue(this.anno.getNAGs().length == 3);
      assertTrue(this.anno.getNAGs()[0] == 2);
      assertTrue(this.anno.getNAGs()[1] == 8);
      assertTrue(this.anno.getNAGs()[2] == 55);
      this.anno.removeNAG(2);
      assertTrue(this.anno.getNAGs().length == 2);
      assertTrue(this.anno.getNAGs()[0] == 2);
      assertTrue(this.anno.getNAGs()[1] == 8);
   }

   public void testEquality() {
      ChessAnnotation anno2 = new ChessAnnotation();
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      anno2.addNAG(123);
      anno2.addNAG(2);
      this.anno.setComment("best by test");
      anno2.setComment("best by test");
      assertTrue(this.anno.equals(anno2));
   }

   public void testNotEquality() {
      ChessAnnotation anno2 = new ChessAnnotation();
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      anno2.addNAG(123);
      anno2.addNAG(3);
      this.anno.setComment("best by test");
      anno2.setComment("best by test");
      assertFalse(this.anno.equals(anno2));
   }

   public void testNAGStoString() {
      this.anno.addNAG(123);
      this.anno.addNAG(2);
      assertTrue(this.anno.getNAGs().length == 2);
      assertTrue(this.anno.getNAGString().equals("$123 ?"));
   }
}
