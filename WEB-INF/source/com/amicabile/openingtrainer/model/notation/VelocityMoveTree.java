package com.amicabile.openingtrainer.model.notation;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
import com.amicabile.openingtrainer.model.notation.VelocityComment;
import com.amicabile.openingtrainer.model.notation.VelocityMove;
import com.amicabile.openingtrainer.model.notation.VelocityMoveKey;
import com.amicabile.openingtrainer.model.notation.VelocityMoveNumberKey;
import ictk.boardgame.Game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class VelocityMoveTree {

   private static Logger log = Logger.getLogger(VelocityMoveTree.class.getName());
   private Game game;
   private int boardNumber;
   private VelocityMove currentMove;
   private VelocityMove firstMove;
   private List firstMoves = new ArrayList();
   private List variationMoves = new ArrayList();
   private List emptyMoves = new ArrayList();
   private Map moveHashMap = new HashMap();
   private Map mainMovesHashMap = new HashMap();
   private boolean hasComments = false;


   public VelocityMove getCurrentMove() {
      return this.currentMove;
   }

   public VelocityMove getOrAddMove(VelocityMoveKey moveKey, VelocityMove move) {
      VelocityMove workMove = move;
      if(this.moveHashMap.containsKey(moveKey)) {
         workMove = (VelocityMove)this.moveHashMap.get(moveKey);
      } else {
         if(moveKey.isMainMove()) {
            this.mainMovesHashMap.put(moveKey.getMoveNumberKey(), moveKey);
         }

         this.moveHashMap.put(moveKey, move);
         move.setMoveString(moveKey.getMoveString());
      }

      return workMove;
   }

   public VelocityMove setFirstMove(VelocityMoveKey moveKey, VelocityMove move) {
      this.setFirstMove(move);
      VelocityMove firstMove = this.getOrAddMove(moveKey, move);
      return firstMove;
   }

   public VelocityMove addFirstMove(VelocityMoveKey moveKey, VelocityMove move) {
      this.addFirstMove(move);
      VelocityMove addedMove = this.getOrAddMove(moveKey, move);
      return addedMove;
   }

   public VelocityMove addVariationMove(VelocityMoveKey moveKey, VelocityMove move) {
      this.addVariationMove(move);
      VelocityMove addedMove = this.getOrAddMove(moveKey, move);
      return addedMove;
   }

   public VelocityMove connectMove(VelocityMoveKey prevMoveKey, VelocityMoveKey currentMoveKey, VelocityMove currentMove, int variantCounter) {
      log.debug("connectMove(" + prevMoveKey + "," + currentMoveKey + "," + currentMove + "," + variantCounter + ")");
      VelocityMove workPrevMove = (VelocityMove)this.moveHashMap.get(prevMoveKey);
      log.debug("connectMove(workPrevMove = " + workPrevMove + ")");
      VelocityMove workCurrentMove = this.getOrAddMove(currentMoveKey, currentMove);
      workCurrentMove.setPrevMove(workPrevMove);
      if(workPrevMove == null) {
         log.warn("Could not connectMove : " + prevMoveKey.getMoveString() + " to " + currentMoveKey.getMoveString());
         return workCurrentMove;
      } else {
         if(variantCounter == 0) {
            workPrevMove.setNextMove(workCurrentMove);
         } else {
            workPrevMove.addVariation(workCurrentMove);
         }

         return workCurrentMove;
      }
   }

   public void setComment(VelocityMoveKey moveKey, String comment) {
      VelocityMove workPrevMove = (VelocityMove)this.moveHashMap.get(moveKey);
      workPrevMove.setComment(new VelocityComment(comment));
      if(StringUtils.isNotEmpty(comment)) {
         this.hasComments = true;
      }

   }

   public VelocityMove getFirstMove() {
      return this.firstMove;
   }

   public void setFirstMove(VelocityMove firstMove) {
      this.firstMove = firstMove;
      this.currentMove = firstMove;
   }

   public void addFirstMove(VelocityMove firstMoveArg) {
      this.firstMoves.add(firstMoveArg);
      this.currentMove = firstMoveArg;
   }

   public void addVariationMove(VelocityMove firstMoveArg) {
      this.variationMoves.add(firstMoveArg);
      this.currentMove = firstMoveArg;
   }

   public VelocityMove getMoveFor(VelocityMoveKey velocityMoveKey) {
      return (VelocityMove)this.moveHashMap.get(velocityMoveKey);
   }

   public VelocityMove getMoveFor(int number, boolean isBlackMove) {
      VelocityMoveNumberKey velocityMoveNumberKey = new VelocityMoveNumberKey(number, isBlackMove);
      return this.getMoveFor(velocityMoveNumberKey);
   }

   public void resetCurrentMove() {
      this.currentMove = this.firstMove;
      Iterator var2 = this.moveHashMap.values().iterator();

      while(var2.hasNext()) {
         VelocityMove move = (VelocityMove)var2.next();
         move.setFirstInSequence(false);
      }

   }

   public VelocityMove getMoveFor(VelocityMoveNumberKey velocityMoveNumberKey) {
      VelocityMoveKey velocityMoveKey = (VelocityMoveKey)this.mainMovesHashMap.get(velocityMoveNumberKey);
      return this.getMoveFor(velocityMoveKey);
   }

   public List getMovesUntilBoard() {
      return this.getMovesUntilBoard(this.currentMove);
   }

   public List getAllMovesInVariation(VelocityMove argMove) {
      ArrayList velocityMoveList = new ArrayList();

      for(VelocityMove loopMove = argMove; loopMove != null; loopMove = loopMove.getNextMove()) {
         velocityMoveList.add(loopMove);
      }

      return velocityMoveList;
   }

   public boolean isAtFinish() {
      return this.currentMove == null;
   }

   public boolean willShowBoard(VelocityMove move) {
      return this.willShowBoard(move, ShowBoardRulePrototypes.DEFAULT_RULE);
   }

   public boolean willShowBoard(VelocityMove move, ShowBoardRule boardRule) {
      boolean result = false;
      if(move.isMainVariation()) {
         if(move.isBeforeBranch() && boardRule.isShowBeforeBranch()) {
            result = true;
         }

         if(move.isBeforeComment() && boardRule.isShowBeforeComment()) {
            result = true;
         }

         if(move.isBeforeImportantMove() && boardRule.isShowBeforeImportantMove()) {
            result = true;
         }

         if(move.getNextMove() == null) {
            result = true;
         }
      }

      return result;
   }

   public List getMovesUntilBoard(VelocityMove argMove) {
      return this.getMovesUntilBoard(argMove, ShowBoardRulePrototypes.DEFAULT_RULE);
   }

   public List getAllMoves() {
      return this.getMovesUntilBoard(this.getFirstMove(), ShowBoardRulePrototypes.NO_SHOW);
   }

   public List getAllMoves(VelocityMove moveArg) {
      return this.getMovesUntilBoard(moveArg, ShowBoardRulePrototypes.NO_SHOW);
   }

   public List getMovesUntilBoard(VelocityMove argMove, ShowBoardRule boardRule) {
      log.debug("VelocityMoveTree.getMovesUntilBoard(" + argMove + ")");
      ArrayList velocityMoveList = new ArrayList();
      this.currentMove = argMove;
      if(this.currentMove != null) {
         this.currentMove.setFirstInSequence(true);
      }

      while(this.currentMove != null) {
         log.debug("VelocityMoveTree.getMovesUntilBoard(Trying : " + this.currentMove + ")");
         velocityMoveList.add(this.currentMove);
         if(this.willShowBoard(this.currentMove, boardRule)) {
            this.currentMove = this.currentMove.getNextMove();
            break;
         }

         this.currentMove = this.currentMove.getNextMove();
         log.debug("VelocityMoveTree.getMovesUntilBoard(Going to : " + this.currentMove + ")");
      }

      log.debug("VelocityMoveTree.getMovesUntilBoard . returning : (" + velocityMoveList + ")");
      return velocityMoveList;
   }

   public List getMovesUntilBoard(VelocityMoveNumberKey velocityMoveNumberKey, ShowBoardRule boardRule) {
      this.currentMove = this.getMoveFor(velocityMoveNumberKey);
      return this.getMovesUntilBoard(this.currentMove, boardRule);
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setBoardNumber(int boardNumber) {
      this.boardNumber = boardNumber;
   }

   public Game getGame() {
      return this.game;
   }

   public void setGame(Game game) {
      this.game = game;
   }

   public boolean isHasComments() {
      return this.hasComments;
   }

   public void setHasComments(boolean hasComments) {
      this.hasComments = hasComments;
   }

   public List getFirstMoves() {
      return this.firstMoves;
   }

   public void setFirstMoves(List firstMoves) {
      this.firstMoves = firstMoves;
   }

   public List getVariationMoves() {
      return this.variationMoves;
   }

   public boolean isHasMainVariations() {
      return this.variationMoves.size() > 0;
   }

   public void setVariationMoves(List variationMoves) {
      this.variationMoves = variationMoves;
   }

   public List getEmptyMoves() {
      return this.emptyMoves;
   }

   public void setEmptyMoves(List emptyMoves) {
      this.emptyMoves = emptyMoves;
   }
}
