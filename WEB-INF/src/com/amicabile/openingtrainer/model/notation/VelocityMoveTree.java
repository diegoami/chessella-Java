/*     */ package com.amicabile.openingtrainer.model.notation;
/*     */ 
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRule;
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
/*     */ import ictk.boardgame.Game;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VelocityMoveTree
/*     */ {
/*  18 */   private static Logger log = Logger.getLogger(VelocityMoveTree.class
/*  19 */     .getName());
/*     */   
/*     */   private Game game;
/*     */   
/*     */   private int boardNumber;
/*     */   
/*     */   private VelocityMove currentMove;
/*     */   
/*     */   private VelocityMove firstMove;
/*  28 */   private List<VelocityMove> firstMoves = new ArrayList();
/*  29 */   private List<VelocityMove> variationMoves = new ArrayList();
/*  30 */   private List<VelocityMove> emptyMoves = new ArrayList();
/*     */   
/*  32 */   private Map<VelocityMoveKey, VelocityMove> moveHashMap = new HashMap();
/*     */   
/*  34 */   private Map<VelocityMoveNumberKey, VelocityMoveKey> mainMovesHashMap = new HashMap();
/*     */   
/*     */   public VelocityMove getCurrentMove() {
/*  37 */     return this.currentMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public VelocityMove getOrAddMove(VelocityMoveKey moveKey, VelocityMove move)
/*     */   {
/*  44 */     VelocityMove workMove = move;
/*     */     
/*  46 */     if (this.moveHashMap.containsKey(moveKey)) {
/*  47 */       workMove = (VelocityMove)this.moveHashMap.get(moveKey);
/*     */     } else {
/*  49 */       if (moveKey.isMainMove()) {
/*  50 */         this.mainMovesHashMap.put(moveKey.getMoveNumberKey(), moveKey);
/*     */       }
/*  52 */       this.moveHashMap.put(moveKey, workMove);
/*  53 */       workMove.setMoveString(moveKey.getMoveString());
/*     */     }
/*  55 */     return workMove;
/*     */   }
/*     */   
/*     */   public VelocityMove setFirstMove(VelocityMoveKey moveKey, VelocityMove move) {
/*  59 */     setFirstMove(move);
/*  60 */     VelocityMove firstMove = getOrAddMove(moveKey, move);
/*  61 */     return firstMove;
/*     */   }
/*     */   
/*     */   public VelocityMove addFirstMove(VelocityMoveKey moveKey, VelocityMove move) {
/*  65 */     addFirstMove(move);
/*  66 */     VelocityMove addedMove = getOrAddMove(moveKey, move);
/*  67 */     return addedMove;
/*     */   }
/*     */   
/*     */   public VelocityMove addVariationMove(VelocityMoveKey moveKey, VelocityMove move)
/*     */   {
/*  72 */     addVariationMove(move);
/*  73 */     VelocityMove addedMove = getOrAddMove(moveKey, move);
/*  74 */     return addedMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public VelocityMove connectMove(VelocityMoveKey prevMoveKey, VelocityMoveKey currentMoveKey, VelocityMove currentMove, int variantCounter)
/*     */   {
/*  81 */     log.debug("connectMove(" + prevMoveKey + "," + currentMoveKey + "," + 
/*  82 */       currentMove + "," + variantCounter + ")");
/*  83 */     VelocityMove workPrevMove = (VelocityMove)this.moveHashMap.get(prevMoveKey);
/*  84 */     log.debug("connectMove(workPrevMove = " + workPrevMove + ")");
/*     */     
/*  86 */     VelocityMove workCurrentMove = getOrAddMove(currentMoveKey, currentMove);
/*     */     
/*  88 */     workCurrentMove.setPrevMove(workPrevMove);
/*     */     
/*  90 */     if (workPrevMove == null) {
/*  91 */       log.warn("Could not connectMove : " + prevMoveKey.getMoveString() + 
/*  92 */         " to " + currentMoveKey.getMoveString());
/*     */       
/*  94 */       return workCurrentMove;
/*     */     }
/*  96 */     if (variantCounter == 0) {
/*  97 */       workPrevMove.setNextMove(workCurrentMove);
/*     */     }
/*     */     else {
/* 100 */       workPrevMove.addVariation(workCurrentMove);
/*     */     }
/* 102 */     return workCurrentMove;
/*     */   }
/*     */   
/*     */   public void setComment(VelocityMoveKey moveKey, String comment) {
/* 106 */     VelocityMove workPrevMove = (VelocityMove)this.moveHashMap.get(moveKey);
/* 107 */     workPrevMove.setComment(new VelocityComment(comment));
/* 108 */     if (StringUtils.isNotEmpty(comment)) {
/* 109 */       this.hasComments = true;
/*     */     }
/*     */   }
/*     */   
/* 113 */   private boolean hasComments = false;
/*     */   
/*     */   public VelocityMove getFirstMove() {
/* 116 */     return this.firstMove;
/*     */   }
/*     */   
/*     */   public void setFirstMove(VelocityMove firstMove) {
/* 120 */     this.firstMove = firstMove;
/* 121 */     this.currentMove = firstMove;
/*     */   }
/*     */   
/*     */   public void addFirstMove(VelocityMove firstMoveArg) {
/* 125 */     this.firstMoves.add(firstMoveArg);
/* 126 */     this.currentMove = firstMoveArg;
/*     */   }
/*     */   
/*     */   public void addVariationMove(VelocityMove firstMoveArg) {
/* 130 */     this.variationMoves.add(firstMoveArg);
/* 131 */     this.currentMove = firstMoveArg;
/*     */   }
/*     */   
/*     */ 
/*     */   public VelocityMove getMoveFor(VelocityMoveKey velocityMoveKey)
/*     */   {
/* 137 */     return (VelocityMove)this.moveHashMap.get(velocityMoveKey);
/*     */   }
/*     */   
/*     */   public VelocityMove getMoveFor(int number, boolean isBlackMove) {
/* 141 */     VelocityMoveNumberKey velocityMoveNumberKey = new VelocityMoveNumberKey(
/* 142 */       number, isBlackMove);
/*     */     
/* 144 */     return getMoveFor(velocityMoveNumberKey);
/*     */   }
/*     */   
/*     */   public void resetCurrentMove() {
/* 148 */     this.currentMove = this.firstMove;
/* 149 */     for (VelocityMove move : this.moveHashMap.values()) {
/* 150 */       move.setFirstInSequence(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public VelocityMove getMoveFor(VelocityMoveNumberKey velocityMoveNumberKey) {
/* 155 */     VelocityMoveKey velocityMoveKey = 
/* 156 */       (VelocityMoveKey)this.mainMovesHashMap.get(velocityMoveNumberKey);
/* 157 */     return getMoveFor(velocityMoveKey);
/*     */   }
/*     */   
/*     */   public List<VelocityMove> getMovesUntilBoard()
/*     */   {
/* 162 */     return getMovesUntilBoard(this.currentMove);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getAllMovesInVariation(VelocityMove argMove)
/*     */   {
/* 169 */     List<VelocityMove> velocityMoveList = new ArrayList();
/* 170 */     VelocityMove loopMove = argMove;
/* 171 */     while (loopMove != null)
/*     */     {
/* 173 */       velocityMoveList.add(loopMove);
/* 174 */       loopMove = loopMove.getNextMove();
/*     */     }
/*     */     
/* 177 */     return velocityMoveList;
/*     */   }
/*     */   
/*     */   public boolean isAtFinish()
/*     */   {
/* 182 */     return this.currentMove == null;
/*     */   }
/*     */   
/*     */   public boolean willShowBoard(VelocityMove move) {
/* 186 */     return willShowBoard(move, ShowBoardRulePrototypes.DEFAULT_RULE);
/*     */   }
/*     */   
/*     */   public boolean willShowBoard(VelocityMove move, ShowBoardRule boardRule) {
/* 190 */     boolean result = false;
/*     */     
/* 192 */     if (move.isMainVariation()) {
/* 193 */       if ((move.isBeforeBranch()) && (boardRule.isShowBeforeBranch())) {
/* 194 */         result = true;
/*     */       }
/* 196 */       if ((move.isBeforeComment()) && (boardRule.isShowBeforeComment())) {
/* 197 */         result = true;
/*     */       }
/* 199 */       if (move.isBeforeImportantMove())
/*     */       {
/* 201 */         if (boardRule.isShowBeforeImportantMove())
/* 202 */           result = true; }
/* 203 */       if (move.getNextMove() == null) {
/* 204 */         result = true;
/*     */       }
/*     */     }
/* 207 */     return result;
/*     */   }
/*     */   
/*     */   public List<VelocityMove> getMovesUntilBoard(VelocityMove argMove)
/*     */   {
/* 212 */     return getMovesUntilBoard(argMove, ShowBoardRulePrototypes.DEFAULT_RULE);
/*     */   }
/*     */   
/*     */   public List<VelocityMove> getAllMoves() {
/* 216 */     return getMovesUntilBoard(getFirstMove(), 
/* 217 */       ShowBoardRulePrototypes.NO_SHOW);
/*     */   }
/*     */   
/*     */   public List<VelocityMove> getAllMoves(VelocityMove moveArg)
/*     */   {
/* 222 */     return getMovesUntilBoard(moveArg, 
/* 223 */       ShowBoardRulePrototypes.NO_SHOW);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getMovesUntilBoard(VelocityMove argMove, ShowBoardRule boardRule)
/*     */   {
/* 230 */     log.debug("VelocityMoveTree.getMovesUntilBoard(" + argMove + ")");
/* 231 */     List<VelocityMove> velocityMoveList = new ArrayList();
/* 232 */     this.currentMove = argMove;
/* 233 */     if (this.currentMove != null)
/* 234 */       this.currentMove.setFirstInSequence(true);
/* 235 */     while (this.currentMove != null) {
/* 236 */       log.debug("VelocityMoveTree.getMovesUntilBoard(Trying : " + 
/* 237 */         this.currentMove + ")");
/*     */       
/* 239 */       velocityMoveList.add(this.currentMove);
/*     */       
/* 241 */       if (willShowBoard(this.currentMove, boardRule)) {
/* 242 */         this.currentMove = this.currentMove.getNextMove();
/* 243 */         break;
/*     */       }
/* 245 */       this.currentMove = this.currentMove.getNextMove();
/* 246 */       log.debug("VelocityMoveTree.getMovesUntilBoard(Going to : " + 
/* 247 */         this.currentMove + ")");
/*     */     }
/*     */     
/*     */ 
/* 251 */     log.debug("VelocityMoveTree.getMovesUntilBoard . returning : (" + 
/* 252 */       velocityMoveList + ")");
/*     */     
/* 254 */     return velocityMoveList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getMovesUntilBoard(VelocityMoveNumberKey velocityMoveNumberKey, ShowBoardRule boardRule)
/*     */   {
/* 266 */     this.currentMove = getMoveFor(velocityMoveNumberKey);
/* 267 */     return getMovesUntilBoard(this.currentMove, boardRule);
/*     */   }
/*     */   
/*     */   public int getBoardNumber()
/*     */   {
/* 272 */     return this.boardNumber;
/*     */   }
/*     */   
/*     */   public void setBoardNumber(int boardNumber) {
/* 276 */     this.boardNumber = boardNumber;
/*     */   }
/*     */   
/*     */   public Game getGame()
/*     */   {
/* 281 */     return this.game;
/*     */   }
/*     */   
/*     */   public void setGame(Game game)
/*     */   {
/* 286 */     this.game = game;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isHasComments()
/*     */   {
/* 293 */     return this.hasComments;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setHasComments(boolean hasComments)
/*     */   {
/* 300 */     this.hasComments = hasComments;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getFirstMoves()
/*     */   {
/* 307 */     return this.firstMoves;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFirstMoves(List<VelocityMove> firstMoves)
/*     */   {
/* 314 */     this.firstMoves = firstMoves;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getVariationMoves()
/*     */   {
/* 321 */     return this.variationMoves;
/*     */   }
/*     */   
/*     */   public boolean isHasMainVariations()
/*     */   {
/* 326 */     return this.variationMoves.size() > 0;
/*     */   }
/*     */   
/*     */   public void setVariationMoves(List<VelocityMove> variationMoves) {
/* 330 */     this.variationMoves = variationMoves;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<VelocityMove> getEmptyMoves()
/*     */   {
/* 337 */     return this.emptyMoves;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEmptyMoves(List<VelocityMove> emptyMoves)
/*     */   {
/* 344 */     this.emptyMoves = emptyMoves;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\notation\VelocityMoveTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */