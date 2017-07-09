package com.amicabile.openingtrainer.pgn.writer;

import ictk.boardgame.ContinuationList;
import ictk.boardgame.Game;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.NAG;
import ictk.boardgame.chess.io.PGNWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.log4j.Logger;

public abstract class ExtendedPGNWriter extends PGNWriter {

   private boolean needNumber = false;
   private static Logger log = Logger.getLogger(ExtendedPGNWriter.class.getName());
   private int variationsDeep = 0;
   private StringBuffer mainBuffer = new StringBuffer();
   private StringBuffer variationBuffer = new StringBuffer();
   private Game game;
   private int startVariation;
   private int endVariation;
   private String currentComment = "";
   private StringBuffer runningBuffer = new StringBuffer();
   private StringBuffer totalBuffer = new StringBuffer();


   public ExtendedPGNWriter(OutputStream _out) {
      super(_out);
   }

   public ExtendedPGNWriter(Writer _out) {
      super(_out);
   }

   public void writeGame(Game game) throws IOException {
      this.game = game;
      super.writeHistory(game.getHistory());
   }

   private void saveComment(String arg) {
      if(this.variationsDeep == 0) {
         this.currentComment = this.currentComment + arg;
      }

   }

   protected void formatPrenotation(ChessMove move) {
      if(this.exportComments && move.getPrenotation() != null && move.getPrenotation().getComment() != null) {
         if(this.variationsDeep == 0) {
            this.saveComment(move.getPrenotation().getComment());
         } else {
            this.formatOutput(" {" + move.getPrenotation().getComment() + "} ", 5);
         }
      }

   }

   protected boolean formatComments(ChessAnnotation annotation) {
      boolean needNumber = false;
      if(this.exportComments && annotation != null && annotation.getComment() != null) {
         if(this.variationsDeep == 0) {
            this.saveComment(annotation.getComment());
         } else {
            this.formatOutput(" {" + annotation.getComment() + "} ", 5);
         }
      }

      needNumber = true;
      return needNumber;
   }

   protected abstract void showBoard(Game var1, Move var2, String var3, String var4, String var5, String var6, String var7);

   protected void walkMoveTreeBreadthFirst(ContinuationList continuation, int number) {
      ChessMove move = null;
      boolean isBlackMove = true;
      StringBuffer moveBuffer = new StringBuffer(12);
      if(continuation != null && !continuation.isTerminal()) {
         isBlackMove = this.isBlackMove(continuation, isBlackMove);
         int variationSize = continuation.size();

         for(int variantCount = 0; (variantCount == 0 || this.exportVariations) && variantCount < variationSize; ++variantCount) {
            move = (ChessMove)continuation.get(variantCount);
            if(variantCount > 0) {
               if(this.variationsDeep == 0) {
                  this.startVariation = this.getCurrentBuffer().length();
               }

               this.formatBeginVariation(variantCount);
               ++this.variationsDeep;
            }

            this.formatPrenotation(move);
            this.formatPoints(number, isBlackMove, moveBuffer, variantCount, this.needNumber);
            this.needNumber = false;
            moveBuffer.append(this.notation.moveToString(move));
            boolean willShowBoard = this.isWillShowBoard(move, variationSize, variantCount);
            if(willShowBoard) {
               this.doShowBoard(move, moveBuffer);
            }

            ChessAnnotation annotation = this.retrieveAnnotation(move, moveBuffer);
            this.formatMoveColor(isBlackMove, moveBuffer);
            moveBuffer.delete(0, moveBuffer.length());
            if(this.formatComments(annotation)) {
               this.needNumber = true;
            }

            if(move != null && this.exportVariations && variantCount > 0) {
               this.walkMoveTreeBreadthFirst(move.getContinuationList(), number + (isBlackMove?1:0));
               this.formatEndVariation();
               --this.variationsDeep;
               if(this.variationsDeep == 0) {
                  this.endVariation = this.getCurrentBuffer().length();
               }

               this.needNumber = true;
            }
         }

         move = (ChessMove)continuation.get(0);
         if(move != null) {
            if(move.isEndOfGame()) {
               this.doShowBoard(move, moveBuffer);
            }

            this.walkMoveTreeBreadthFirst(move.getContinuationList(), number + (isBlackMove?1:0));
         }
      }

   }

   private boolean isWillShowBoard(ChessMove move, int variationSize, int variantCount) {
      Move nextMove = move.getNext();
      return this.variationsDeep == 0 && (variationSize > 1 && variantCount == 0 || this.currentComment.length() > 0 || move.isEndOfGame());
   }

   private void doShowBoard(ChessMove move, StringBuffer moveBuffer) {
      String bufferString = this.getCurrentBuffer().toString();
      String mainString = this.startVariation > 1 && this.startVariation < bufferString.length()?bufferString.substring(0, this.startVariation):bufferString;
      String variationString = this.endVariation < bufferString.length()?bufferString.substring(this.startVariation, this.endVariation):bufferString.substring(this.startVariation, bufferString.length());
      String mainAftervariationString = this.endVariation < bufferString.length() && this.startVariation > 1?bufferString.substring(this.endVariation, this.getCurrentBuffer().length()):"";
      this.showBoard(this.game, move.getPrev(), moveBuffer.toString(), mainString, variationString, mainAftervariationString, this.currentComment);
      this.runningBuffer.delete(0, this.runningBuffer.length());
      this.totalBuffer.delete(0, this.totalBuffer.length());
      this.buffer.delete(0, this.buffer.length());
      this.currentComment = "";
      this.startVariation = 0;
      this.endVariation = 0;
   }

   private void formatEndVariation() {
      this.formatOutput(")", 4);
   }

   protected boolean formatNags(ChessAnnotation annotation) {
      boolean needNumber = false;
      short[] nags;
      if(annotation != null && this.glyphStyle != 0 && this.glyphStyle != 4 && (nags = annotation.getNAGs()) != null) {
         for(int j = NAG.isSuffix(nags[0]) && this.glyphStyle != 2?1:0; j < nags.length; ++j) {
            needNumber = true;
            switch(this.glyphStyle) {
            case 1:
               this.formatOutput(NAG.numberToString(nags[j]), 6);
               break;
            case 2:
               this.formatOutput(NAG.numberToString(nags[j], true), 6);
               break;
            case 3:
               if(NAG.isSymbol(nags[j])) {
                  this.formatOutput(NAG.numberToString(nags[j]), 6);
               }
            }
         }
      }

      return needNumber;
   }

   protected ChessAnnotation retrieveAnnotation(ChessMove move, StringBuffer moveBuffer) {
      ChessAnnotation annotation = (ChessAnnotation)move.getAnnotation();
      if(annotation != null && this.glyphStyle != 0 && this.glyphStyle != 2 && annotation.getSuffix() != 0) {
         moveBuffer.append(NAG.numberToString(annotation.getSuffix()));
      }

      return annotation;
   }

   protected void formatPoints(int number, boolean isBlackMove, StringBuffer moveBuffer, int variantCount, boolean needNumber) {
      if(!isBlackMove) {
         moveBuffer.append(number).append(".");
      }

      if((variantCount > 0 || needNumber) && isBlackMove) {
         moveBuffer.append(number).append("...");
      }

   }

   protected void formatBeginVariation(int variantCount) {
      if(variantCount > 0) {
         this.formatOutput("(", 3);
      }

   }

   protected void formatMoveColor(boolean isBlackMove, StringBuffer moveBuffer) {
      this.formatOutput(moveBuffer.toString(), isBlackMove?2:1);
   }

   protected boolean isBlackMove(ContinuationList cont, boolean isBlackMove) {
      ChessMove currMove = null;
      if(cont.get(0) != null) {
         currMove = (ChessMove)cont.get(0);
      } else if(cont.get(1) != null) {
         currMove = (ChessMove)cont.get(1);
      }

      if(currMove.isNullMove()) {
         currMove = (ChessMove)currMove.getPrev();
      }

      isBlackMove = currMove.isBlackMove();
      return isBlackMove;
   }

   protected void formatOutput(String str, int type) {
      super.formatOutput(str, type);
      if(this.buffer.length() > this.runningBuffer.length()) {
         this.runningBuffer = new StringBuffer();
         this.runningBuffer.append(this.buffer.toString());
      } else {
         this.totalBuffer.append(this.runningBuffer.toString());
         this.runningBuffer = new StringBuffer();
      }

   }

   private StringBuffer getCurrentBuffer() {
      StringBuffer currentBuffer = new StringBuffer();
      currentBuffer.append(this.totalBuffer.toString());
      currentBuffer.append(this.runningBuffer.toString());
      return currentBuffer;
   }

   public void println(String str) {}
}
