/*     */ package com.amicabile.openingtrainer.pgn.writer;
/*     */ 
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityComment;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMove;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveNumberKey;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MoveTreeDumper
/*     */ {
/*  14 */   private static Logger log = Logger.getLogger(MoveTreeDumper.class
/*  15 */     .getName());
/*     */   
/*     */   private PrintStream writer;
/*     */   
/*     */   public MoveTreeDumper(PrintStream writer)
/*     */   {
/*  21 */     this.writer = writer;
/*     */   }
/*     */   
/*     */   public void printFromBoardToBoard(VelocityMoveTree velocityMoveTree) {
/*  25 */     velocityMoveTree.resetCurrentMove();
/*  26 */     List<VelocityMove> movesUntilBoard = null;
/*  27 */     while (!(movesUntilBoard = velocityMoveTree.getMovesUntilBoard())
/*  28 */       .isEmpty()) {
/*  29 */       for (VelocityMove move : movesUntilBoard) {
/*  30 */         printSingleMove(move, move.isFirstInSequence());
/*  31 */         log.debug("printBoardToBoardForVelocity : variationList(" + move + " )" + move.getVariationList());
/*  32 */         if (move.getPrevMove() != null) {
/*  33 */           printVariationList(1, move.getPrevMove());
/*     */         }
/*     */       }
/*     */       
/*  37 */       this.writer.println("");
/*     */     }
/*     */   }
/*     */   
/*     */   public void printFromBoardToBoardForVelocity(VelocityMoveTree velocityMoveTree)
/*     */   {
/*  43 */     velocityMoveTree.resetCurrentMove();
/*     */     
/*  45 */     while (!velocityMoveTree.isAtFinish()) {
/*  46 */       List<VelocityMove> movesUntilBoard = velocityMoveTree.getMovesUntilBoard();
/*  47 */       for (VelocityMove move : movesUntilBoard)
/*     */       {
/*  49 */         printSingleMove(move, move.isFirstInSequence());
/*  50 */         if (move.getPrevMove() != null) {
/*  51 */           for (VelocityMove variationMove : move.getPrevMove().getVariationList()) {
/*  52 */             this.writer.print(" ( ");
/*  53 */             List<VelocityMove> movesInVariation = velocityMoveTree.getAllMovesInVariation(variationMove);
/*  54 */             for (VelocityMove moveInVariation : movesInVariation) {
/*  55 */               printSingleMove(moveInVariation);
/*     */             }
/*  57 */             this.writer.print(" ) ");
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  62 */       this.writer.println("");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void printMainLine(VelocityMoveTree velocityMoveTree, VelocityMoveNumberKey velocityMoveNumberKey)
/*     */   {
/*  69 */     VelocityMove startMove = velocityMoveTree
/*  70 */       .getMoveFor(velocityMoveNumberKey);
/*  71 */     printMainLine(startMove);
/*     */   }
/*     */   
/*     */   public void printMainLine(VelocityMoveTree velocityMoveTree)
/*     */   {
/*  76 */     printMainLine(velocityMoveTree, VelocityMoveNumberKey.START_MOVE);
/*     */   }
/*     */   
/*     */   public void printWithVariation(VelocityMoveTree velocityMoveTree, int depth, VelocityMoveNumberKey velocityMoveNumberKey)
/*     */   {
/*  81 */     VelocityMove startMove = velocityMoveTree
/*  82 */       .getMoveFor(velocityMoveNumberKey);
/*     */     
/*  84 */     printSingleMove(startMove, true);
/*     */     
/*  86 */     printWithVariation(startMove, depth);
/*     */   }
/*     */   
/*     */   public void printWithVariation(VelocityMoveTree velocityMoveTree, int depth)
/*     */   {
/*  91 */     printWithVariation(velocityMoveTree, depth, 
/*  92 */       VelocityMoveNumberKey.START_MOVE);
/*     */   }
/*     */   
/*     */   private void printMainLine(VelocityMove startMove)
/*     */   {
/*  97 */     VelocityMove currentMove = startMove;
/*  98 */     while (currentMove != null) {
/*  99 */       printSingleMove(currentMove);
/* 100 */       currentMove = currentMove.getNextMove();
/*     */     }
/*     */   }
/*     */   
/*     */   public void printWithFirstVariation(VelocityMoveTree velocityMoveTree)
/*     */   {
/* 106 */     printWithFirstVariation(velocityMoveTree, 
/* 107 */       VelocityMoveNumberKey.START_MOVE);
/*     */   }
/*     */   
/*     */   public void printWithFirstVariation(VelocityMoveTree velocityMoveTree, VelocityMoveNumberKey velocityMoveNumberKey)
/*     */   {
/* 112 */     VelocityMove startMove = velocityMoveTree
/* 113 */       .getMoveFor(velocityMoveNumberKey);
/* 114 */     printWithFirstVariation(startMove);
/*     */   }
/*     */   
/*     */   private void printWithFirstVariation(VelocityMove firstMove)
/*     */   {
/* 119 */     VelocityMove currentMove = firstMove;
/* 120 */     while (currentMove != null) {
/* 121 */       printSingleMove(currentMove);
/* 122 */       if (currentMove.getVariationList() != null) {
/* 123 */         for (VelocityMove velocityMove : currentMove.getVariationList()) {
/* 124 */           this.writer.print(" ( ");
/* 125 */           printMainLine(velocityMove);
/* 126 */           this.writer.print(" ) ");
/*     */         }
/*     */       }
/*     */       
/* 130 */       currentMove = currentMove.getNextMove();
/*     */     }
/*     */   }
/*     */   
/*     */   private void printSingleMove(VelocityMove currentMove) {
/* 135 */     printSingleMove(currentMove, false);
/*     */   }
/*     */   
/*     */   private void printSingleMove(VelocityMove currentMove, boolean completeMove) {
/* 139 */     log.debug("printSingleMove(" + currentMove + "," + completeMove + ")");
/* 140 */     if (org.apache.commons.lang.StringUtils.isNotEmpty(currentMove.getPreNotation().getText())) {
/* 141 */       this.writer.print("{ " + currentMove.getPreNotation().getText() + " } ");
/*     */     }
/*     */     
/* 144 */     this.writer.print((completeMove ? currentMove.getMoveTextWithNumber() : 
/* 145 */       currentMove.getMoveText()) + 
/* 146 */       " ");
/*     */     
/* 148 */     if (org.apache.commons.lang.StringUtils.isNotEmpty(currentMove.getComment().getText())) {
/* 149 */       this.writer.print("{ " + currentMove.getComment().getText() + " } ");
/*     */     }
/*     */     
/* 152 */     if (currentMove.getNags() != null) {
/* 153 */       this.writer.print(" ");
/* 154 */       for (Iterator localIterator = currentMove.getNags().iterator(); localIterator.hasNext();) { int nag = ((Integer)localIterator.next()).intValue();
/* 155 */         this.writer.print("$" + nag + " ");
/*     */       }
/* 157 */       this.writer.print(" ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void printWithVariation(VelocityMove firstMove, int depth)
/*     */   {
/* 166 */     VelocityMove currentMove = firstMove;
/* 167 */     while (currentMove != null) {
/* 168 */       if (currentMove.getNextMove() != null) {
/* 169 */         printSingleMove(currentMove.getNextMove());
/*     */       }
/*     */       
/* 172 */       printVariationList(depth, currentMove);
/* 173 */       currentMove = currentMove.getNextMove();
/*     */     }
/*     */   }
/*     */   
/*     */   private void printVariationList(int depth, VelocityMove currentMove) {
/* 178 */     if ((depth > 0) && 
/* 179 */       (currentMove.getVariationList() != null)) {
/* 180 */       for (VelocityMove velocityMove : currentMove.getVariationList()) {
/* 181 */         this.writer.print(" ( ");
/* 182 */         printSingleMove(velocityMove);
/* 183 */         printWithVariation(velocityMove, depth - 1);
/* 184 */         this.writer.print(" ) ");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\writer\MoveTreeDumper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */