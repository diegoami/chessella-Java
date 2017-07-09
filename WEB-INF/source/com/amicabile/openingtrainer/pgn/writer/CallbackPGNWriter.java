package com.amicabile.openingtrainer.pgn.writer;

import ictk.boardgame.ContinuationList;
import ictk.boardgame.Game;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.NAG;
import ictk.boardgame.chess.io.PGNWriter;
import ictk.boardgame.io.Annotation;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.log4j.Logger;

public abstract class CallbackPGNWriter extends PGNWriter {

   private boolean needNumber = false;
   private static Logger log = Logger.getLogger(CallbackPGNWriter.class.getName());
   private int variationsDeep = 0;
   private Game game;
   private FEN fen = new FEN();


   public CallbackPGNWriter(OutputStream _out) {
      super(_out);
   }

   public CallbackPGNWriter(Writer _out) {
      super(_out);
   }

   public void writeGame(Game game) throws IOException {
      this.game = game;
      this.glyphStyle = 1;
      super.writeHistory(game.getHistory());
   }

   protected void formatPrenotation(ChessMove move) {
      Annotation prenotation = move.getPrenotation();
      if(this.exportComments && prenotation != null && prenotation.getComment() != null) {
         this.formatOutput(" {" + prenotation.getComment() + "} ", 5);
      }

   }

   protected boolean processComments(Move move, int number) {
      boolean needNumber = false;
      Annotation annotation = move.getAnnotation();
      if(this.exportComments && annotation != null && annotation.getComment() != null) {
         this.formatComments(annotation);
         needNumber = true;
      }

      return needNumber;
   }

   private String getAnnotationComment(Move move) {
      Annotation annotation = move.getAnnotation();
      if(annotation != null && annotation.getComment() != null) {
         log.debug("COMMENT = " + annotation.getComment());
         return annotation.getComment();
      } else {
         return "";
      }
   }

   private String getPrenotation(Move move) {
      Annotation annotation = move.getPrenotation();
      return annotation != null && annotation.getComment() != null?annotation.getComment():"";
   }

   private short[] getNags(Move move) {
      ChessAnnotation annotation = (ChessAnnotation)move.getAnnotation();
      return annotation != null && annotation.getNAGs() != null?annotation.getNAGs():null;
   }

   protected void formatComments(Annotation annotation) {
      if(this.exportComments && annotation != null && annotation.getComment() != null) {
         this.formatOutput(" {" + annotation.getComment() + "} ", 5);
      }

   }

   protected abstract void addMove(Move var1, int var2, Move var3, int var4, int var5, int var6, boolean var7);

   protected abstract void fillCurrentMove(String var1, String var2, String var3, String var4, short[] var5);

   protected void walkMoveTreeBreadthFirst(ContinuationList continuation, int number) {
      ChessMove move = null;
      boolean isBlackMove = true;
      StringBuffer moveBuffer = new StringBuffer(12);
      StringBuffer moveBufferWithNumber = new StringBuffer(12);
      if(continuation != null && !continuation.isTerminal()) {
         isBlackMove = this.isBlackMove(continuation, isBlackMove);
         int variationSize = continuation.size();

         for(int variantCount = 0; (variantCount == 0 || this.exportVariations) && variantCount < variationSize; ++variantCount) {
            move = (ChessMove)continuation.get(variantCount);
            if(variantCount > 0) {
               this.formatBeginVariation(variantCount);
               ++this.variationsDeep;
            }

            this.formatPrenotation(move);
            this.formatPoints(number, isBlackMove, moveBuffer, variantCount, this.needNumber);
            this.formatPoints(number, isBlackMove, moveBufferWithNumber, variantCount, true);
            this.needNumber = false;
            String notationString = this.notation.moveToString(move);
            log.debug("Notation = " + notationString);
            moveBuffer.append(notationString);
            moveBufferWithNumber.append(notationString);
            this.formatMoveColor(isBlackMove, moveBuffer);
            this.formatMoveColor(isBlackMove, moveBufferWithNumber);
            if(this.processNags((ChessAnnotation)move.getAnnotation(), moveBuffer, moveBufferWithNumber)) {
               this.needNumber = true;
            }

            if(this.processComments(move, number)) {
               this.needNumber = true;
            }

            String moveBufferString = moveBuffer.toString();
            String moveBufferWithNumberString = moveBufferWithNumber.toString();
            moveBuffer.delete(0, moveBuffer.length());
            moveBufferWithNumber.delete(0, moveBufferWithNumber.length());
            if(move != null) {
               this.addMove(move.getPrev(), number - (isBlackMove?0:1), move, number, this.variationsDeep, variantCount, isBlackMove);
               this.fillCurrentMove(moveBufferString, moveBufferWithNumberString, this.getAnnotationComment(move), this.getPrenotation(move), this.getNags(move));
            }

            if(move != null && this.exportVariations && variantCount > 0) {
               this.walkMoveTreeBreadthFirst(move.getContinuationList(), number + (isBlackMove?1:0));
               this.formatEndVariation();
               --this.variationsDeep;
               this.needNumber = true;
            }
         }

         move = (ChessMove)continuation.get(0);
         if(move != null) {
            this.walkMoveTreeBreadthFirst(move.getContinuationList(), number + (isBlackMove?1:0));
         }
      }

   }

   private void formatEndVariation() {
      this.formatOutput(")", 4);
   }

   protected boolean processNags(ChessAnnotation annotation, StringBuffer moveBuffer, StringBuffer moveOtherBuffer) {
      boolean needNumber = false;
      short[] nags;
      if(annotation != null && this.glyphStyle != 0 && this.glyphStyle != 4 && (nags = annotation.getNAGs()) != null) {
         for(int j = 0; j < nags.length; ++j) {
            log.debug("NAG = " + nags[j]);
            needNumber = true;
            String glyphString = NAG.numberToString(nags[j], false);
            if(NAG.isSymbol(nags[j])) {
               moveBuffer.append(glyphString);
               moveOtherBuffer.append(glyphString);
            }

            switch(this.glyphStyle) {
            case 1:
               this.formatOutput(glyphString, 6);
               break;
            case 2:
               this.formatOutput(glyphString, 6);
               break;
            case 3:
               if(NAG.isSymbol(nags[j])) {
                  this.formatOutput(glyphString, 6);
               }
            }
         }
      }

      return needNumber;
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

   protected boolean isBlackMove(ContinuationList cont, boolean isBlackMoveArg) {
      ChessMove currMove = null;
      if(cont.get(0) != null) {
         currMove = (ChessMove)cont.get(0);
      } else if(cont.get(1) != null) {
         currMove = (ChessMove)cont.get(1);
      }

      boolean isBlackMove;
      if(currMove.isNullMove()) {
         isBlackMove = !((ChessMove)currMove.getPrev()).isBlackMove();
      } else {
         isBlackMove = currMove.isBlackMove();
      }

      return isBlackMove;
   }

   public void println(String str) {}
}
