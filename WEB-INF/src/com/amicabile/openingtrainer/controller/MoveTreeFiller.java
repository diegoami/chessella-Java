/*     */ package com.amicabile.openingtrainer.controller;
/*     */ 
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityComment;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMove;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveKey;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*     */ import com.amicabile.openingtrainer.pgn.writer.CallbackPGNWriter;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.Square;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ class MoveTreeFiller extends CallbackPGNWriter
/*     */ {
/*  24 */   private static Logger log = Logger.getLogger(MoveTreeFiller.class.getName());
/*     */   
/*     */   private Game game;
/*  27 */   private FEN fen = new FEN();
/*  28 */   private VelocityMoveTree moveTree = new VelocityMoveTree();
/*     */   private VelocityMove currentVelocityMove;
/*     */   
/*  31 */   public VelocityMoveTree getMoveTree() { return this.moveTree; }
/*     */   
/*     */   VelocityMoveTree retrieveMoveTree(Game game)
/*     */   {
/*  35 */     log.debug("retrieving MOVE Tree");
/*     */     try {
/*  37 */       writeGame(game);
/*  38 */       ChessGameInfo chessGameInfo = (ChessGameInfo)game.getGameInfo();
/*  39 */       if ((StringUtils.isEmpty(chessGameInfo.get("Annotator"))) && 
/*  40 */         (this.moveTree.isHasComments())) {
/*  41 */         chessGameInfo.props.put("Annotator", "Yes");
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  46 */       log.error("IOException while retrieving moveTree", e);
/*     */     }
/*  48 */     this.moveTree.setGame(game);
/*  49 */     return this.moveTree;
/*     */   }
/*     */   
/*     */ 
/*     */   public MoveTreeFiller(OutputStream _out)
/*     */   {
/*  55 */     super(_out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void writeGame(Game game)
/*     */     throws IOException
/*     */   {
/*  63 */     this.moveTree = new VelocityMoveTree();
/*  64 */     this.game = game;
/*  65 */     super.writeGame(game);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MoveTreeFiller()
/*     */   {
/*  72 */     this(System.out);
/*     */   }
/*     */   
/*     */   public MoveTreeFiller(Writer _out)
/*     */   {
/*  77 */     super(_out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void fillCurrentMove(String moveText, String moveTextWithNumber, String comment, String prenotation, short[] nags)
/*     */   {
/*  85 */     log.debug("fillCurrentMove(" + moveText + "," + moveTextWithNumber + "," + comment + ")");
/*  86 */     this.currentVelocityMove.setMoveText(moveText);
/*  87 */     this.currentVelocityMove.setMoveTextWithNumber(moveTextWithNumber);
/*  88 */     this.currentVelocityMove.setComment(new VelocityComment(comment));
/*  89 */     this.currentVelocityMove.setNags(nags);
/*  90 */     this.currentVelocityMove.setPreNotation(new VelocityComment(prenotation));
/*     */     
/*  92 */     if (this.currentVelocityMove.getPrevMove() != null) {
/*  93 */       if (this.currentVelocityMove.isImportantMove())
/*     */       {
/*  95 */         this.currentVelocityMove.getPrevMove().setBeforeImportantMove(true);
/*     */       }
/*     */       
/*  98 */       if (StringUtils.isNotEmpty(comment)) {
/*  99 */         this.currentVelocityMove.getPrevMove().setBeforeComment(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addMove(Move prevMove, int prevNumber, Move nextMove, int number, int variationsDeep, int variantCount, boolean isBlackMove)
/*     */   {
/* 109 */     log.debug("addMove(" + prevMove + "," + prevNumber + "," + nextMove + "," + 
/* 110 */       number + "," + variationsDeep + "," + variantCount + "," + isBlackMove + ")");
/*     */     
/*     */ 
/* 113 */     this.currentVelocityMove = new VelocityMove(nextMove);
/*     */     
/* 115 */     this.currentVelocityMove.setNumber(number);
/* 116 */     this.currentVelocityMove.setBlackMove(isBlackMove);
/* 117 */     this.currentVelocityMove.setDepth(variationsDeep);
/*     */     
/* 119 */     int nextFenHashCode = 0;
/* 120 */     int prevFenHashCode = 0;
/*     */     
/*     */ 
/* 123 */     if (prevMove != null) {
/* 124 */       this.game.getHistory().goTo(prevMove);
/*     */       
/* 126 */       prevFenHashCode = getHashCodeForMove(prevMove);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 131 */     if (nextMove != null) {
/* 132 */       this.game.getHistory().goTo(nextMove);
/*     */       
/* 134 */       nextFenHashCode = getHashCodeForMove(nextMove);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 139 */     this.currentVelocityMove.setBoardHashCode(nextFenHashCode);
/*     */     
/*     */ 
/*     */ 
/* 143 */     ChessMove prevChessMove = (ChessMove)prevMove;
/* 144 */     ChessMove nextChessMove = (ChessMove)nextMove;
/*     */     
/*     */ 
/* 147 */     VelocityMoveKey currentMoveKey = new VelocityMoveKey(
/* 148 */       getMoveString(nextChessMove), 
/* 149 */       number, 
/* 150 */       variationsDeep, isBlackMove, nextFenHashCode);
/*     */     
/* 152 */     if (prevMove != null)
/*     */     {
/* 154 */       VelocityMoveKey prevMoveKey = new VelocityMoveKey(
/* 155 */         getMoveString(prevChessMove), 
/* 156 */         prevNumber, 
/* 157 */         variantCount == 0 ? variationsDeep : 
/* 158 */         Math.max(0, 
/* 159 */         variationsDeep - 1), !isBlackMove, prevFenHashCode);
/*     */       
/*     */ 
/*     */ 
/* 163 */       this.currentVelocityMove = this.moveTree.connectMove(prevMoveKey, currentMoveKey, this.currentVelocityMove, variantCount);
/* 164 */       if ((this.currentVelocityMove != null) && 
/* 165 */         (variantCount > 0) && 
/* 166 */         (this.currentVelocityMove.getPrevMove() != null)) {
/* 167 */         this.currentVelocityMove.getPrevMove().setBeforeBranch(true);
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */     }
/* 175 */     else if (this.moveTree.getFirstMove() == null) {
/* 176 */       this.currentVelocityMove = this.moveTree.setFirstMove(currentMoveKey, this.currentVelocityMove);
/*     */     } else {
/* 178 */       this.currentVelocityMove = this.moveTree.addVariationMove(currentMoveKey, this.currentVelocityMove);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getMoveString(ChessMove nextChessMove)
/*     */   {
/* 187 */     if (nextChessMove.isNullMove()) {
/* 188 */       return "--";
/*     */     }
/* 190 */     return nextChessMove.getOrigin().toString() + "-" + nextChessMove.getDestination().toString();
/*     */   }
/*     */   
/*     */ 
/*     */   private int getHashCodeForMove(Move prevMove)
/*     */   {
/* 196 */     String prevFen = this.fen.boardToString(prevMove.getBoard());
/* 197 */     int prevFenHashCode = Math.abs(prevFen.hashCode()) % 997;
/*     */     
/* 199 */     return prevFenHashCode;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\MoveTreeFiller.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */