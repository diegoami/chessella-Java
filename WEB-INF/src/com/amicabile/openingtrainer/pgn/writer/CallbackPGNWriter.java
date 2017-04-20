/*     */ package com.amicabile.openingtrainer.pgn.writer;
/*     */ 
/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.io.ChessAnnotation;
/*     */ import ictk.boardgame.chess.io.ChessMoveNotation;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import ictk.boardgame.chess.io.NAG;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.io.Annotation;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public abstract class CallbackPGNWriter
/*     */   extends PGNWriter
/*     */ {
/*  21 */   private boolean needNumber = false;
/*     */   
/*  23 */   private static Logger log = Logger.getLogger(CallbackPGNWriter.class.getName());
/*     */   
/*  25 */   private int variationsDeep = 0;
/*     */   
/*     */   private Game game;
/*     */   
/*  29 */   private FEN fen = new FEN();
/*     */   
/*     */   public CallbackPGNWriter(OutputStream _out) {
/*  32 */     super(_out);
/*     */   }
/*     */   
/*     */   public CallbackPGNWriter(Writer _out) {
/*  36 */     super(_out);
/*     */   }
/*     */   
/*     */   public void writeGame(Game game) throws IOException
/*     */   {
/*  41 */     this.game = game;
/*  42 */     this.glyphStyle = 1;
/*  43 */     super.writeHistory(game.getHistory());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void formatPrenotation(ChessMove move)
/*     */   {
/*  51 */     Annotation prenotation = move.getPrenotation();
/*  52 */     if ((this.exportComments) && 
/*  53 */       (prenotation != null) && 
/*  54 */       (prenotation.getComment() != null))
/*     */     {
/*  56 */       formatOutput(" {" + 
/*  57 */         prenotation.getComment() + 
/*  58 */         "} ", 
/*  59 */         5);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean processComments(Move move, int number)
/*     */   {
/*  65 */     boolean needNumber = false;
/*     */     
/*  67 */     Annotation annotation = move.getAnnotation();
/*     */     
/*  69 */     if ((this.exportComments) && 
/*  70 */       (annotation != null) && 
/*  71 */       (annotation.getComment() != null)) {
/*  72 */       formatComments(annotation);
/*  73 */       needNumber = true;
/*     */     }
/*     */     
/*     */ 
/*  77 */     return needNumber;
/*     */   }
/*     */   
/*     */   private String getAnnotationComment(Move move) {
/*  81 */     Annotation annotation = move.getAnnotation();
/*  82 */     if ((annotation != null) && 
/*  83 */       (annotation.getComment() != null)) {
/*  84 */       log.debug("COMMENT = " + annotation.getComment());
/*  85 */       return annotation.getComment();
/*     */     }
/*  87 */     return "";
/*     */   }
/*     */   
/*     */   private String getPrenotation(Move move) {
/*  91 */     Annotation annotation = move.getPrenotation();
/*  92 */     if ((annotation != null) && 
/*  93 */       (annotation.getComment() != null)) {
/*  94 */       return annotation.getComment();
/*     */     }
/*  96 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private short[] getNags(Move move)
/*     */   {
/* 103 */     ChessAnnotation annotation = (ChessAnnotation)move.getAnnotation();
/* 104 */     if ((annotation != null) && 
/* 105 */       (annotation.getNAGs() != null)) {
/* 106 */       return annotation.getNAGs();
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void formatComments(Annotation annotation)
/*     */   {
/* 115 */     if ((this.exportComments) && 
/* 116 */       (annotation != null) && 
/* 117 */       (annotation.getComment() != null))
/*     */     {
/* 119 */       formatOutput(" {" + 
/* 120 */         annotation.getComment() + 
/* 121 */         "} ", 
/* 122 */         5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void addMove(Move paramMove1, int paramInt1, Move paramMove2, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean);
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void fillCurrentMove(String paramString1, String paramString2, String paramString3, String paramString4, short[] paramArrayOfShort);
/*     */   
/*     */ 
/*     */   protected void walkMoveTreeBreadthFirst(ContinuationList continuation, int number)
/*     */   {
/* 137 */     ChessMove move = null;
/* 138 */     boolean isBlackMove = true;
/*     */     
/* 140 */     StringBuffer moveBuffer = new StringBuffer(12);
/* 141 */     StringBuffer moveBufferWithNumber = new StringBuffer(12);
/*     */     
/* 143 */     if ((continuation != null) && (!continuation.isTerminal())) {
/* 144 */       isBlackMove = isBlackMove(continuation, isBlackMove);
/* 145 */       int variationSize = continuation.size();
/*     */       
/* 147 */       for (int variantCount = 0; 
/* 148 */           ((variantCount == 0) || (this.exportVariations)) && (variantCount < variationSize); 
/* 149 */           variantCount++)
/*     */       {
/* 151 */         move = (ChessMove)continuation.get(variantCount);
/*     */         
/* 153 */         if (variantCount > 0)
/*     */         {
/*     */ 
/* 156 */           formatBeginVariation(variantCount);
/* 157 */           this.variationsDeep += 1;
/*     */         }
/*     */         
/*     */ 
/* 161 */         formatPrenotation(move);
/*     */         
/* 163 */         formatPoints(number, isBlackMove, moveBuffer, variantCount, this.needNumber);
/* 164 */         formatPoints(number, isBlackMove, moveBufferWithNumber, variantCount, true);
/* 165 */         this.needNumber = false;
/*     */         
/*     */ 
/* 168 */         String notationString = this.notation.moveToString(move);
/*     */         
/* 170 */         log.debug("Notation = " + notationString);
/* 171 */         moveBuffer.append(notationString);
/* 172 */         moveBufferWithNumber.append(notationString);
/*     */         
/* 174 */         formatMoveColor(isBlackMove, moveBuffer);
/* 175 */         formatMoveColor(isBlackMove, moveBufferWithNumber);
/*     */         
/*     */ 
/*     */ 
/* 179 */         if (processNags((ChessAnnotation)move.getAnnotation(), moveBuffer, moveBufferWithNumber)) {
/* 180 */           this.needNumber = true;
/*     */         }
/*     */         
/* 183 */         if (processComments(move, number)) {
/* 184 */           this.needNumber = true;
/*     */         }
/*     */         
/* 187 */         String moveBufferString = moveBuffer.toString();
/* 188 */         String moveBufferWithNumberString = moveBufferWithNumber.toString();
/*     */         
/* 190 */         moveBuffer.delete(0, moveBuffer.length());
/* 191 */         moveBufferWithNumber.delete(0, moveBufferWithNumber.length());
/*     */         
/* 193 */         if (move != null) {
/* 194 */           addMove(move.getPrev(), number - (isBlackMove ? 0 : 1), 
/* 195 */             move, number, this.variationsDeep, variantCount, isBlackMove);
/*     */           
/*     */ 
/* 198 */           fillCurrentMove(moveBufferString, 
/* 199 */             moveBufferWithNumberString, 
/* 200 */             getAnnotationComment(move), 
/* 201 */             getPrenotation(move), 
/* 202 */             getNags(move));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 207 */         if ((move != null) && (this.exportVariations) && (variantCount > 0))
/*     */         {
/* 209 */           walkMoveTreeBreadthFirst(move.getContinuationList(), 
/* 210 */             number + (isBlackMove ? 1 : 0));
/*     */           
/* 212 */           formatEndVariation();
/* 213 */           this.variationsDeep -= 1;
/* 214 */           this.needNumber = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 219 */       move = (ChessMove)continuation.get(0);
/* 220 */       if (move != null)
/*     */       {
/*     */ 
/*     */ 
/* 224 */         walkMoveTreeBreadthFirst(move.getContinuationList(), 
/* 225 */           number + (isBlackMove ? 1 : 0));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void formatEndVariation()
/*     */   {
/* 237 */     formatOutput(")", 4);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean processNags(ChessAnnotation annotation, StringBuffer moveBuffer, StringBuffer moveOtherBuffer)
/*     */   {
/* 243 */     boolean needNumber = false;
/* 244 */     short[] nags; if ((annotation != null) && 
/* 245 */       (this.glyphStyle != 0) && 
/* 246 */       (this.glyphStyle != 4) && 
/* 247 */       ((nags = annotation.getNAGs()) != null))
/*     */     {
/*     */ 
/* 250 */       for (int j = 0; 
/*     */           
/* 252 */           j < nags.length; j++) {
/* 253 */         log.debug("NAG = " + nags[j]);
/*     */         
/* 255 */         needNumber = true;
/*     */         
/* 257 */         String glyphString = NAG.numberToString(nags[j], false);
/* 258 */         if (NAG.isSymbol(nags[j])) {
/* 259 */           moveBuffer.append(glyphString);
/* 260 */           moveOtherBuffer.append(glyphString);
/*     */         }
/* 262 */         switch (this.glyphStyle)
/*     */         {
/*     */         case 2: 
/* 265 */           formatOutput(glyphString, 
/* 266 */             6);
/* 267 */           break;
/*     */         
/*     */         case 3: 
/* 270 */           if (NAG.isSymbol(nags[j]))
/* 271 */             formatOutput(glyphString, 
/* 272 */               6);
/* 273 */           break;
/*     */         
/*     */         case 1: 
/* 276 */           formatOutput(glyphString, 
/* 277 */             6);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 282 */     return needNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void formatPoints(int number, boolean isBlackMove, StringBuffer moveBuffer, int variantCount, boolean needNumber)
/*     */   {
/* 289 */     if (!isBlackMove) {
/* 290 */       moveBuffer.append(number).append(".");
/*     */     }
/* 292 */     if (((variantCount > 0) || (needNumber)) && (isBlackMove))
/* 293 */       moveBuffer.append(number).append("...");
/*     */   }
/*     */   
/*     */   protected void formatBeginVariation(int variantCount) {
/* 297 */     if (variantCount > 0) {
/* 298 */       formatOutput("(", 3);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void formatMoveColor(boolean isBlackMove, StringBuffer moveBuffer)
/*     */   {
/* 304 */     formatOutput(moveBuffer.toString(), 
/* 305 */       isBlackMove ? 2 : 1);
/*     */   }
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
/*     */   protected boolean isBlackMove(ContinuationList cont, boolean isBlackMoveArg)
/*     */   {
/* 325 */     ChessMove currMove = null;
/* 326 */     if (cont.get(0) != null) {
/* 327 */       currMove = (ChessMove)cont.get(0);
/* 328 */     } else if (cont.get(1) != null)
/* 329 */       currMove = (ChessMove)cont.get(1);
/*     */     boolean isBlackMove;
/*     */     boolean isBlackMove;
/* 332 */     if (currMove.isNullMove()) {
/* 333 */       isBlackMove = !((ChessMove)currMove.getPrev()).isBlackMove();
/*     */     } else {
/* 335 */       isBlackMove = currMove.isBlackMove();
/*     */     }
/* 337 */     return isBlackMove;
/*     */   }
/*     */   
/*     */   public void println(String str) {}
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\writer\CallbackPGNWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */