package com.amicabile.openingtrainer.model.notation;

import com.amicabile.openingtrainer.model.notation.VelocityComment;
import ictk.boardgame.Move;
import ictk.boardgame.chess.io.NAG;
import java.util.ArrayList;
import java.util.List;

public class VelocityMove {

   private int depth;
   private int uniqueId;
   private int variationNumber;
   private Move move;
   private String moveText;
   private String moveTextWithNumber;
   private boolean beforeBranch;
   private boolean beforeImportantMove;
   private boolean beforeComment;
   private VelocityComment comment;
   private VelocityComment nagComment;
   private VelocityComment preNagComment;
   private VelocityMove nextMove;
   private List nags;
   private List prenags;
   private short[] allnags;
   private VelocityComment preNotation;
   private boolean isFirstInSequence;
   private VelocityMove prevMove;
   private int number;
   private String moveString;
   private boolean isBlackMove;
   private int boardHashCode;
   private List variationList = new ArrayList();


   public int getBoardHashCode() {
      return this.boardHashCode;
   }

   public void setBoardHashCode(int boardHashCode) {
      this.boardHashCode = boardHashCode;
   }

   public boolean isBlackMove() {
      return this.isBlackMove;
   }

   public void setBlackMove(boolean isBlackMove) {
      this.isBlackMove = isBlackMove;
   }

   public int getNumber() {
      return this.number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public VelocityMove(Move move) {
      this.move = move;
   }

   public boolean isImportantMove() {
      boolean result = false;
      if(this.allnags != null) {
         short[] var5 = this.allnags;
         int var3 = 0;

         for(int var4 = var5.length; var3 < var4; ++var3) {
            short nag = var5[var3];
            if(nag >= 1 && nag <= 6) {
               result = true;
            }
         }
      }

      return result;
   }

   public VelocityMove getNextMove() {
      return this.nextMove;
   }

   public void setNextMove(VelocityMove nextMove) {
      this.nextMove = nextMove;
   }

   public String getMoveText() {
      return this.moveText;
   }

   public void setMoveText(String text) {
      this.moveText = text;
   }

   public List getVariationList() {
      return this.variationList;
   }

   public void addVariation(VelocityMove variationMove) {
      this.variationList.add(variationMove);
   }

   public void setVariationList(List variationList) {
      this.variationList = variationList;
   }

   public VelocityComment getComment() {
      return this.comment;
   }

   public void setComment(VelocityComment comment) {
      this.comment = comment;
   }

   public void setNagComment(VelocityComment nagComment) {
      this.nagComment = nagComment;
   }

   public VelocityComment getNagComment() {
      return this.nagComment;
   }

   public VelocityMove getPrevMove() {
      return this.prevMove;
   }

   public void setPrevMove(VelocityMove prevMove) {
      this.prevMove = prevMove;
   }

   public String getMoveTextWithNumber() {
      return this.moveTextWithNumber;
   }

   public void setMoveTextWithNumber(String moveTextWithNumber) {
      this.moveTextWithNumber = moveTextWithNumber;
   }

   public String toString() {
      return this.getMoveText();
   }

   public List getNags() {
      return this.nags;
   }

   public List getPreNags() {
      return this.prenags;
   }

   public void setNags(short[] nags) {
      if(nags != null) {
         this.allnags = nags;
         this.nags = new ArrayList();
         this.prenags = new ArrayList();
         boolean nagFound = false;
         String nagString = "";
         short[] var7 = nags;
         int var5 = 0;

         for(int var6 = nags.length; var5 < var6; ++var5) {
            short nag = var7[var5];
            if(NAG.isSymbol(nag)) {
               nagFound = true;
               nagString = nagString + NAG.numberToString(nag, false);
            } else if(nag <= 139) {
               this.nags.add(Integer.valueOf(nag));
            } else {
               this.prenags.add(Integer.valueOf(nag));
            }
         }

         if(nagFound) {
            this.nagComment = new VelocityComment(nagString);
         }

      }
   }

   public VelocityComment getPreNotation() {
      return this.preNotation;
   }

   public void setPreNotation(VelocityComment preNotation) {
      this.preNotation = preNotation;
   }

   public boolean isFirstInSequence() {
      return this.isFirstInSequence;
   }

   public void setFirstInSequence(boolean isFirstInSequence) {
      this.isFirstInSequence = isFirstInSequence;
   }

   public Move getMove() {
      return this.move;
   }

   public void setMove(Move move) {
      this.move = move;
   }

   public boolean isBeforeBranch() {
      return this.beforeBranch;
   }

   public void setBeforeBranch(boolean beforeBranch) {
      this.beforeBranch = beforeBranch;
   }

   public boolean isBeforeImportantMove() {
      return this.beforeImportantMove;
   }

   public void setBeforeImportantMove(boolean beforeImportantMove) {
      this.beforeImportantMove = beforeImportantMove;
   }

   public boolean isBeforeComment() {
      return this.beforeComment;
   }

   public void setBeforeComment(boolean beforeComment) {
      this.beforeComment = beforeComment;
   }

   public int getDepth() {
      return this.depth;
   }

   public boolean isMainVariation() {
      return this.depth == 0;
   }

   public void setDepth(int depth) {
      this.depth = depth;
   }

   public int getUniqueId() {
      return this.uniqueId;
   }

   public void setUniqueId(int uniqueId) {
      this.uniqueId = uniqueId;
   }

   public String getMoveString() {
      return this.moveString;
   }

   public void setMoveString(String moveString) {
      this.moveString = moveString;
   }

   public int getVariationNumber() {
      return this.variationNumber;
   }

   public void setVariationNumber(int variationNumber) {
      this.variationNumber = variationNumber;
   }
}
