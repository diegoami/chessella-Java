package com.amicabile.openingtrainer.pgn.writer;

import com.amicabile.openingtrainer.model.notation.VelocityMove;
import com.amicabile.openingtrainer.model.notation.VelocityMoveNumberKey;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MoveTreeDumper {

   private static Logger log = Logger.getLogger(MoveTreeDumper.class.getName());
   private PrintStream writer;


   public MoveTreeDumper(PrintStream writer) {
      this.writer = writer;
   }

   public void printFromBoardToBoard(VelocityMoveTree velocityMoveTree) {
      velocityMoveTree.resetCurrentMove();
      List movesUntilBoard = null;

      while(!(movesUntilBoard = velocityMoveTree.getMovesUntilBoard()).isEmpty()) {
         Iterator var4 = movesUntilBoard.iterator();

         while(var4.hasNext()) {
            VelocityMove move = (VelocityMove)var4.next();
            this.printSingleMove(move, move.isFirstInSequence());
            log.debug("printBoardToBoardForVelocity : variationList(" + move + " )" + move.getVariationList());
            if(move.getPrevMove() != null) {
               this.printVariationList(1, move.getPrevMove());
            }
         }

         this.writer.println("");
      }

   }

   public void printFromBoardToBoardForVelocity(VelocityMoveTree velocityMoveTree) {
      velocityMoveTree.resetCurrentMove();

      while(!velocityMoveTree.isAtFinish()) {
         List movesUntilBoard = velocityMoveTree.getMovesUntilBoard();
         Iterator var4 = movesUntilBoard.iterator();

         while(var4.hasNext()) {
            VelocityMove move = (VelocityMove)var4.next();
            this.printSingleMove(move, move.isFirstInSequence());
            if(move.getPrevMove() != null) {
               Iterator var6 = move.getPrevMove().getVariationList().iterator();

               while(var6.hasNext()) {
                  VelocityMove variationMove = (VelocityMove)var6.next();
                  this.writer.print(" ( ");
                  List movesInVariation = velocityMoveTree.getAllMovesInVariation(variationMove);
                  Iterator var9 = movesInVariation.iterator();

                  while(var9.hasNext()) {
                     VelocityMove moveInVariation = (VelocityMove)var9.next();
                     this.printSingleMove(moveInVariation);
                  }

                  this.writer.print(" ) ");
               }
            }
         }

         this.writer.println("");
      }

   }

   public void printMainLine(VelocityMoveTree velocityMoveTree, VelocityMoveNumberKey velocityMoveNumberKey) {
      VelocityMove startMove = velocityMoveTree.getMoveFor(velocityMoveNumberKey);
      this.printMainLine(startMove);
   }

   public void printMainLine(VelocityMoveTree velocityMoveTree) {
      this.printMainLine(velocityMoveTree, VelocityMoveNumberKey.START_MOVE);
   }

   public void printWithVariation(VelocityMoveTree velocityMoveTree, int depth, VelocityMoveNumberKey velocityMoveNumberKey) {
      VelocityMove startMove = velocityMoveTree.getMoveFor(velocityMoveNumberKey);
      this.printSingleMove(startMove, true);
      this.printWithVariation(startMove, depth);
   }

   public void printWithVariation(VelocityMoveTree velocityMoveTree, int depth) {
      this.printWithVariation(velocityMoveTree, depth, VelocityMoveNumberKey.START_MOVE);
   }

   private void printMainLine(VelocityMove startMove) {
      for(VelocityMove currentMove = startMove; currentMove != null; currentMove = currentMove.getNextMove()) {
         this.printSingleMove(currentMove);
      }

   }

   public void printWithFirstVariation(VelocityMoveTree velocityMoveTree) {
      this.printWithFirstVariation(velocityMoveTree, VelocityMoveNumberKey.START_MOVE);
   }

   public void printWithFirstVariation(VelocityMoveTree velocityMoveTree, VelocityMoveNumberKey velocityMoveNumberKey) {
      VelocityMove startMove = velocityMoveTree.getMoveFor(velocityMoveNumberKey);
      this.printWithFirstVariation(startMove);
   }

   private void printWithFirstVariation(VelocityMove firstMove) {
      for(VelocityMove currentMove = firstMove; currentMove != null; currentMove = currentMove.getNextMove()) {
         this.printSingleMove(currentMove);
         if(currentMove.getVariationList() != null) {
            Iterator var4 = currentMove.getVariationList().iterator();

            while(var4.hasNext()) {
               VelocityMove velocityMove = (VelocityMove)var4.next();
               this.writer.print(" ( ");
               this.printMainLine(velocityMove);
               this.writer.print(" ) ");
            }
         }
      }

   }

   private void printSingleMove(VelocityMove currentMove) {
      this.printSingleMove(currentMove, false);
   }

   private void printSingleMove(VelocityMove currentMove, boolean completeMove) {
      log.debug("printSingleMove(" + currentMove + "," + completeMove + ")");
      if(StringUtils.isNotEmpty(currentMove.getPreNotation().getText())) {
         this.writer.print("{ " + currentMove.getPreNotation().getText() + " } ");
      }

      this.writer.print((completeMove?currentMove.getMoveTextWithNumber():currentMove.getMoveText()) + " ");
      if(StringUtils.isNotEmpty(currentMove.getComment().getText())) {
         this.writer.print("{ " + currentMove.getComment().getText() + " } ");
      }

      if(currentMove.getNags() != null) {
         this.writer.print(" ");
         Iterator var4 = currentMove.getNags().iterator();

         while(var4.hasNext()) {
            int nag = ((Integer)var4.next()).intValue();
            this.writer.print("$" + nag + " ");
         }

         this.writer.print(" ");
      }

   }

   private void printWithVariation(VelocityMove firstMove, int depth) {
      for(VelocityMove currentMove = firstMove; currentMove != null; currentMove = currentMove.getNextMove()) {
         if(currentMove.getNextMove() != null) {
            this.printSingleMove(currentMove.getNextMove());
         }

         this.printVariationList(depth, currentMove);
      }

   }

   private void printVariationList(int depth, VelocityMove currentMove) {
      if(depth > 0 && currentMove.getVariationList() != null) {
         Iterator var4 = currentMove.getVariationList().iterator();

         while(var4.hasNext()) {
            VelocityMove velocityMove = (VelocityMove)var4.next();
            this.writer.print(" ( ");
            this.printSingleMove(velocityMove);
            this.printWithVariation(velocityMove, depth - 1);
            this.writer.print(" ) ");
         }
      }

   }
}
