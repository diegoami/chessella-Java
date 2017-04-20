/*    */ package com.amicabile.openingtrainer.controller;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMove;
/*    */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MoveTreePrinter
/*    */ {
/*    */   private VelocityMoveTree moveTree;
/*    */   
/*    */   public MoveTreePrinter(VelocityMoveTree moveTree)
/*    */   {
/* 15 */     this.moveTree = moveTree;
/*    */   }
/*    */   
/*    */   private void print(VelocityMove move) {
/* 19 */     for (int i = 0; i < move.getDepth(); i++) {
/* 20 */       System.out.print("--");
/*    */     }
/* 22 */     System.out.print(move.getMoveTextWithNumber());
/* 23 */     if (org.apache.commons.lang.StringUtils.isNotEmpty(move.getComment().getText())) {
/* 24 */       System.out.println("{" + move.getComment().getText() + "}");
/*    */     } else {
/* 26 */       System.out.println("");
/*    */     }
/* 28 */     System.out.println(move.getMoveTextWithNumber());
/*    */     
/* 30 */     List<VelocityMove> variationList = move.getVariationList();
/* 31 */     if (variationList != null) {
/* 32 */       printVariation(variationList);
/*    */     }
/*    */   }
/*    */   
/*    */   private void printVariation(List<VelocityMove> variationList) {
/*    */     Iterator localIterator2;
/* 38 */     for (Iterator localIterator1 = variationList.iterator(); localIterator1.hasNext(); 
/*    */         
/*    */ 
/* 41 */         localIterator2.hasNext())
/*    */     {
/* 38 */       VelocityMove variationStartMove = (VelocityMove)localIterator1.next();
/* 39 */       List<VelocityMove> allMovesInVariation = this.moveTree
/* 40 */         .getAllMovesInVariation(variationStartMove);
/* 41 */       localIterator2 = allMovesInVariation.iterator(); continue;VelocityMove move = (VelocityMove)localIterator2.next();
/* 42 */       print(move);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void printOut()
/*    */   {
/* 49 */     List<VelocityMove> allMoves = this.moveTree.getAllMoves();
/* 50 */     for (VelocityMove move : allMoves) {
/* 51 */       print(move);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\MoveTreePrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */