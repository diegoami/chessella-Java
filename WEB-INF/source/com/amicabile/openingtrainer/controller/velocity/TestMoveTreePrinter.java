package com.amicabile.openingtrainer.controller.velocity;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.apache.log4j.Logger;

public class TestMoveTreePrinter extends TestCase {

   private static Logger log = Logger.getLogger(TestMoveTreePrinter.class.getName());


   public static void main(String[] args) throws Exception {
      TestRunner.run(TestMoveTreePrinter.class);
   }

   protected void setUp() throws Exception {
      super.setUp();
   }
}
