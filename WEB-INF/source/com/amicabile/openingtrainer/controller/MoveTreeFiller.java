package com.amicabile.openingtrainer.controller;

import com.amicabile.openingtrainer.model.notation.VelocityComment;
import com.amicabile.openingtrainer.model.notation.VelocityMove;
import com.amicabile.openingtrainer.model.notation.VelocityMoveKey;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.openingtrainer.pgn.writer.CallbackPGNWriter;
import ictk.boardgame.Game;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.io.FEN;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

class MoveTreeFiller extends CallbackPGNWriter {

   private static Logger log = Logger.getLogger(MoveTreeFiller.class.getName());
   private Game game;
   private FEN fen;
   private VelocityMoveTree moveTree;
   private VelocityMove currentVelocityMove;


   public VelocityMoveTree getMoveTree() {
      return this.moveTree;
   }

   VelocityMoveTree retrieveMoveTree(Game game) {
      log.debug("retrieving MOVE Tree");

      try {
         this.writeGame(game);
         ChessGameInfo e = (ChessGameInfo)game.getGameInfo();
         if(StringUtils.isEmpty(e.get("Annotator")) && this.moveTree.isHasComments()) {
            e.props.put("Annotator", "Yes");
         }
      } catch (IOException var3) {
         log.error("IOException while retrieving moveTree", var3);
      }

      this.moveTree.setGame(game);
      return this.moveTree;
   }

   public MoveTreeFiller(OutputStream _out) {
      super(_out);
      this.fen = new FEN();
      this.moveTree = new VelocityMoveTree();
   }

   public void writeGame(Game game) throws IOException {
      this.moveTree = new VelocityMoveTree();
      this.game = game;
      super.writeGame(game);
   }

   public MoveTreeFiller() {
      this((OutputStream)System.out);
   }

   public MoveTreeFiller(Writer _out) {
      super(_out);
      this.fen = new FEN();
      this.moveTree = new VelocityMoveTree();
   }

   protected void fillCurrentMove(String moveText, String moveTextWithNumber, String comment, String prenotation, short[] nags) {
      log.debug("fillCurrentMove(" + moveText + "," + moveTextWithNumber + "," + comment + ")");
      this.currentVelocityMove.setMoveText(moveText);
      this.currentVelocityMove.setMoveTextWithNumber(moveTextWithNumber);
      this.currentVelocityMove.setComment(new VelocityComment(comment));
      this.currentVelocityMove.setNags(nags);
      this.currentVelocityMove.setPreNotation(new VelocityComment(prenotation));
      if(this.currentVelocityMove.getPrevMove() != null) {
         if(this.currentVelocityMove.isImportantMove()) {
            this.currentVelocityMove.getPrevMove().setBeforeImportantMove(true);
         }

         if(StringUtils.isNotEmpty(comment)) {
            this.currentVelocityMove.getPrevMove().setBeforeComment(true);
         }
      }

   }

   protected void addMove(Move prevMove, int prevNumber, Move nextMove, int number, int variationsDeep, int variantCount, boolean isBlackMove) {
      log.debug("addMove(" + prevMove + "," + prevNumber + "," + nextMove + "," + number + "," + variationsDeep + "," + variantCount + "," + isBlackMove + ")");
      this.currentVelocityMove = new VelocityMove(nextMove);
      this.currentVelocityMove.setNumber(number);
      this.currentVelocityMove.setBlackMove(isBlackMove);
      this.currentVelocityMove.setDepth(variationsDeep);
      int nextFenHashCode = 0;
      int prevFenHashCode = 0;
      if(prevMove != null) {
         this.game.getHistory().goTo(prevMove);
         prevFenHashCode = this.getHashCodeForMove(prevMove);
      }

      if(nextMove != null) {
         this.game.getHistory().goTo(nextMove);
         nextFenHashCode = this.getHashCodeForMove(nextMove);
      }

      this.currentVelocityMove.setBoardHashCode(nextFenHashCode);
      ChessMove prevChessMove = (ChessMove)prevMove;
      ChessMove nextChessMove = (ChessMove)nextMove;
      VelocityMoveKey currentMoveKey = new VelocityMoveKey(this.getMoveString(nextChessMove), number, variationsDeep, isBlackMove, nextFenHashCode);
      if(prevMove != null) {
         VelocityMoveKey prevMoveKey = new VelocityMoveKey(this.getMoveString(prevChessMove), prevNumber, variantCount == 0?variationsDeep:Math.max(0, variationsDeep - 1), !isBlackMove, prevFenHashCode);
         this.currentVelocityMove = this.moveTree.connectMove(prevMoveKey, currentMoveKey, this.currentVelocityMove, variantCount);
         if(this.currentVelocityMove != null && variantCount > 0 && this.currentVelocityMove.getPrevMove() != null) {
            this.currentVelocityMove.getPrevMove().setBeforeBranch(true);
         }
      } else if(this.moveTree.getFirstMove() == null) {
         this.currentVelocityMove = this.moveTree.setFirstMove(currentMoveKey, this.currentVelocityMove);
      } else {
         this.currentVelocityMove = this.moveTree.addVariationMove(currentMoveKey, this.currentVelocityMove);
      }

   }

   private String getMoveString(ChessMove nextChessMove) {
      return nextChessMove.isNullMove()?"--":nextChessMove.getOrigin().toString() + "-" + nextChessMove.getDestination().toString();
   }

   private int getHashCodeForMove(Move prevMove) {
      String prevFen = this.fen.boardToString(prevMove.getBoard());
      int prevFenHashCode = Math.abs(prevFen.hashCode()) % 997;
      return prevFenHashCode;
   }
}
