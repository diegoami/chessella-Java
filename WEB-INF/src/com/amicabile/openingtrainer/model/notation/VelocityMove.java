/*     */ package com.amicabile.openingtrainer.model.notation;
/*     */ 
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.io.NAG;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VelocityMove
/*     */ {
/*     */   private int depth;
/*     */   private int uniqueId;
/*     */   private int variationNumber;
/*     */   private Move move;
/*     */   private String moveText;
/*     */   private String moveTextWithNumber;
/*     */   private boolean beforeBranch;
/*     */   private boolean beforeImportantMove;
/*     */   private boolean beforeComment;
/*     */   private VelocityComment comment;
/*     */   private VelocityComment nagComment;
/*     */   private VelocityComment preNagComment;
/*     */   private VelocityMove nextMove;
/*     */   private List<Integer> nags;
/*     */   private List<Integer> prenags;
/*     */   private short[] allnags;
/*     */   private VelocityComment preNotation;
/*     */   private boolean isFirstInSequence;
/*     */   private VelocityMove prevMove;
/*     */   private int number;
/*     */   private String moveString;
/*     */   private boolean isBlackMove;
/*     */   private int boardHashCode;
/*     */   
/*     */   public int getBoardHashCode()
/*     */   {
/*  59 */     return this.boardHashCode;
/*     */   }
/*     */   
/*     */   public void setBoardHashCode(int boardHashCode) {
/*  63 */     this.boardHashCode = boardHashCode;
/*     */   }
/*     */   
/*     */   public boolean isBlackMove() {
/*  67 */     return this.isBlackMove;
/*     */   }
/*     */   
/*     */   public void setBlackMove(boolean isBlackMove) {
/*  71 */     this.isBlackMove = isBlackMove;
/*     */   }
/*     */   
/*     */   public int getNumber() {
/*  75 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(int number) {
/*  79 */     this.number = number;
/*     */   }
/*     */   
/*     */ 
/*  83 */   public VelocityMove(Move move) { this.move = move; }
/*     */   
/*  85 */   private List<VelocityMove> variationList = new ArrayList();
/*     */   
/*     */   public boolean isImportantMove() {
/*  88 */     boolean result = false;
/*  89 */     if (this.allnags != null) {
/*  90 */       for (short nag : this.allnags) {
/*  91 */         if ((nag >= 1) && (nag <= 6)) {
/*  92 */           result = true;
/*     */         }
/*     */       }
/*     */     }
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public VelocityMove getNextMove() {
/* 100 */     return this.nextMove;
/*     */   }
/*     */   
/*     */   public void setNextMove(VelocityMove nextMove) {
/* 104 */     this.nextMove = nextMove;
/*     */   }
/*     */   
/*     */   public String getMoveText() {
/* 108 */     return this.moveText;
/*     */   }
/*     */   
/*     */   public void setMoveText(String text) {
/* 112 */     this.moveText = text;
/*     */   }
/*     */   
/*     */   public List<VelocityMove> getVariationList() {
/* 116 */     return this.variationList;
/*     */   }
/*     */   
/*     */   public void addVariation(VelocityMove variationMove) {
/* 120 */     this.variationList.add(variationMove);
/*     */   }
/*     */   
/* 123 */   public void setVariationList(List<VelocityMove> variationList) { this.variationList = variationList; }
/*     */   
/*     */   public VelocityComment getComment()
/*     */   {
/* 127 */     return this.comment;
/*     */   }
/*     */   
/*     */   public void setComment(VelocityComment comment) {
/* 131 */     this.comment = comment;
/*     */   }
/*     */   
/*     */   public void setNagComment(VelocityComment nagComment) {
/* 135 */     this.nagComment = nagComment;
/*     */   }
/*     */   
/*     */   public VelocityComment getNagComment() {
/* 139 */     return this.nagComment;
/*     */   }
/*     */   
/*     */   public VelocityMove getPrevMove() {
/* 143 */     return this.prevMove;
/*     */   }
/*     */   
/*     */   public void setPrevMove(VelocityMove prevMove) {
/* 147 */     this.prevMove = prevMove;
/*     */   }
/*     */   
/*     */   public String getMoveTextWithNumber()
/*     */   {
/* 152 */     return this.moveTextWithNumber;
/*     */   }
/*     */   
/*     */   public void setMoveTextWithNumber(String moveTextWithNumber) {
/* 156 */     this.moveTextWithNumber = moveTextWithNumber;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 160 */     return getMoveText();
/*     */   }
/*     */   
/*     */   public List<Integer> getNags() {
/* 164 */     return this.nags;
/*     */   }
/*     */   
/*     */   public List<Integer> getPreNags()
/*     */   {
/* 169 */     return this.prenags;
/*     */   }
/*     */   
/*     */   public void setNags(short[] nags) {
/* 173 */     if (nags == null) return;
/* 174 */     this.allnags = nags;
/*     */     
/* 176 */     this.nags = new ArrayList();
/* 177 */     this.prenags = new ArrayList();
/* 178 */     boolean nagFound = false;
/* 179 */     String nagString = "";
/* 180 */     for (short nag : nags) {
/* 181 */       if (NAG.isSymbol(nag)) {
/* 182 */         nagFound = true;
/* 183 */         nagString = nagString + NAG.numberToString(nag, false);
/*     */ 
/*     */       }
/* 186 */       else if (nag <= 139) {
/* 187 */         this.nags.add(Integer.valueOf(nag));
/*     */       } else {
/* 189 */         this.prenags.add(Integer.valueOf(nag));
/*     */       }
/*     */     }
/*     */     
/* 193 */     if (nagFound) {
/* 194 */       this.nagComment = new VelocityComment(nagString);
/*     */     }
/*     */   }
/*     */   
/*     */   public VelocityComment getPreNotation() {
/* 199 */     return this.preNotation;
/*     */   }
/*     */   
/*     */   public void setPreNotation(VelocityComment preNotation) {
/* 203 */     this.preNotation = preNotation;
/*     */   }
/*     */   
/*     */   public boolean isFirstInSequence() {
/* 207 */     return this.isFirstInSequence;
/*     */   }
/*     */   
/*     */   public void setFirstInSequence(boolean isFirstInSequence) {
/* 211 */     this.isFirstInSequence = isFirstInSequence;
/*     */   }
/*     */   
/*     */   public Move getMove() {
/* 215 */     return this.move;
/*     */   }
/*     */   
/*     */   public void setMove(Move move) {
/* 219 */     this.move = move;
/*     */   }
/*     */   
/*     */   public boolean isBeforeBranch() {
/* 223 */     return this.beforeBranch;
/*     */   }
/*     */   
/*     */   public void setBeforeBranch(boolean beforeBranch) {
/* 227 */     this.beforeBranch = beforeBranch;
/*     */   }
/*     */   
/*     */   public boolean isBeforeImportantMove() {
/* 231 */     return this.beforeImportantMove;
/*     */   }
/*     */   
/*     */   public void setBeforeImportantMove(boolean beforeImportantMove) {
/* 235 */     this.beforeImportantMove = beforeImportantMove;
/*     */   }
/*     */   
/*     */   public boolean isBeforeComment() {
/* 239 */     return this.beforeComment;
/*     */   }
/*     */   
/*     */   public void setBeforeComment(boolean beforeComment) {
/* 243 */     this.beforeComment = beforeComment;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getDepth()
/*     */   {
/* 249 */     return this.depth;
/*     */   }
/*     */   
/*     */   public boolean isMainVariation() {
/* 253 */     return this.depth == 0;
/*     */   }
/*     */   
/*     */   public void setDepth(int depth)
/*     */   {
/* 258 */     this.depth = depth;
/*     */   }
/*     */   
/*     */   public int getUniqueId() {
/* 262 */     return this.uniqueId;
/*     */   }
/*     */   
/*     */   public void setUniqueId(int uniqueId) {
/* 266 */     this.uniqueId = uniqueId;
/*     */   }
/*     */   
/*     */   public String getMoveString() {
/* 270 */     return this.moveString;
/*     */   }
/*     */   
/*     */   public void setMoveString(String moveString) {
/* 274 */     this.moveString = moveString;
/*     */   }
/*     */   
/*     */   public int getVariationNumber() {
/* 278 */     return this.variationNumber;
/*     */   }
/*     */   
/*     */   public void setVariationNumber(int variationNumber) {
/* 282 */     this.variationNumber = variationNumber;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\notation\VelocityMove.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */