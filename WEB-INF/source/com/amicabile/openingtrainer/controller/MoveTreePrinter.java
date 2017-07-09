package com.amicabile.openingtrainer.controller;

import com.amicabile.openingtrainer.model.notation.VelocityMove;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class MoveTreePrinter {

   private VelocityMoveTree moveTree;


   public MoveTreePrinter(VelocityMoveTree moveTree) {
      this.moveTree = moveTree;
   }

   private void print(VelocityMove move) {
      for(int variationList = 0; variationList < move.getDepth(); ++variationList) {
         System.out.print("--");
      }

      System.out.print(move.getMoveTextWithNumber());
      if(StringUtils.isNotEmpty(move.getComment().getText())) {
         System.out.println("{" + move.getComment().getText() + "}");
      } else {
         System.out.println("");
      }

      System.out.println(move.getMoveTextWithNumber());
      List var3 = move.getVariationList();
      if(var3 != null) {
         this.printVariation(var3);
      }

   }

   private void printVariation(List variationList) {
      Iterator var3 = variationList.iterator();

      while(var3.hasNext()) {
         VelocityMove variationStartMove = (VelocityMove)var3.next();
         List allMovesInVariation = this.moveTree.getAllMovesInVariation(variationStartMove);
         Iterator var6 = allMovesInVariation.iterator();

         while(var6.hasNext()) {
            VelocityMove move = (VelocityMove)var6.next();
            this.print(move);
         }
      }

   }

   public void printOut() {
      List allMoves = this.moveTree.getAllMoves();
      Iterator var3 = allMoves.iterator();

      while(var3.hasNext()) {
         VelocityMove move = (VelocityMove)var3.next();
         this.print(move);
      }

   }
}
