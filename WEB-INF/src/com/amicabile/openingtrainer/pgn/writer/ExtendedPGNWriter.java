/*     */ package com.amicabile.openingtrainer.pgn.writer;
/*     */ 
/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.io.ChessAnnotation;
/*     */ import ictk.boardgame.chess.io.ChessMoveNotation;
/*     */ import ictk.boardgame.chess.io.NAG;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.io.Annotation;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public abstract class ExtendedPGNWriter extends PGNWriter
/*     */ {
/*  19 */   private boolean needNumber = false;
/*  20 */   private static Logger log = Logger.getLogger(ExtendedPGNWriter.class.getName());
/*     */   
/*  22 */   private int variationsDeep = 0;
/*     */   
/*  24 */   private StringBuffer mainBuffer = new StringBuffer();
/*  25 */   private StringBuffer variationBuffer = new StringBuffer();
/*     */   
/*     */   private Game game;
/*     */   private int startVariation;
/*     */   private int endVariation;
/*  30 */   private String currentComment = "";
/*     */   
/*  32 */   private StringBuffer runningBuffer = new StringBuffer();
/*  33 */   private StringBuffer totalBuffer = new StringBuffer();
/*     */   
/*     */   public ExtendedPGNWriter(OutputStream _out) {
/*  36 */     super(_out);
/*     */   }
/*     */   
/*     */   public ExtendedPGNWriter(Writer _out) {
/*  40 */     super(_out);
/*     */   }
/*     */   
/*     */   public void writeGame(Game game) throws IOException
/*     */   {
/*  45 */     this.game = game;
/*  46 */     super.writeHistory(game.getHistory());
/*     */   }
/*     */   
/*     */ 
/*     */   private void saveComment(String arg)
/*     */   {
/*  52 */     if (this.variationsDeep == 0) {
/*  53 */       this.currentComment += arg;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void formatPrenotation(ChessMove move)
/*     */   {
/*  60 */     if ((this.exportComments) && 
/*  61 */       (move.getPrenotation() != null) && 
/*  62 */       (move.getPrenotation().getComment() != null))
/*     */     {
/*  64 */       if (this.variationsDeep == 0) {
/*  65 */         saveComment(move.getPrenotation().getComment());
/*     */       } else {
/*  67 */         formatOutput(" {" + 
/*  68 */           move.getPrenotation().getComment() + 
/*  69 */           "} ", 
/*  70 */           5);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean formatComments(ChessAnnotation annotation)
/*     */   {
/*  77 */     boolean needNumber = false;
/*  78 */     if ((this.exportComments) && 
/*  79 */       (annotation != null) && 
/*  80 */       (annotation.getComment() != null)) {
/*  81 */       if (this.variationsDeep == 0) {
/*  82 */         saveComment(annotation.getComment());
/*     */       } else {
/*  84 */         formatOutput(" {" + 
/*  85 */           annotation.getComment() + 
/*  86 */           "} ", 
/*  87 */           5);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  92 */     needNumber = true;
/*  93 */     return needNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void showBoard(Game paramGame, Move paramMove, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
/*     */   
/*     */ 
/*     */ 
/*     */   protected void walkMoveTreeBreadthFirst(ContinuationList continuation, int number)
/*     */   {
/* 104 */     ChessMove move = null;
/* 105 */     boolean isBlackMove = true;
/*     */     
/* 107 */     StringBuffer moveBuffer = new StringBuffer(12);
/*     */     
/* 109 */     if ((continuation != null) && (!continuation.isTerminal())) {
/* 110 */       isBlackMove = isBlackMove(continuation, isBlackMove);
/* 111 */       int variationSize = continuation.size();
/*     */       
/*     */ 
/* 114 */       for (int variantCount = 0; 
/* 115 */           ((variantCount == 0) || (this.exportVariations)) && (variantCount < variationSize); variantCount++)
/*     */       {
/* 117 */         move = (ChessMove)continuation.get(variantCount);
/*     */         
/*     */ 
/* 120 */         if (variantCount > 0) {
/* 121 */           if (this.variationsDeep == 0) {
/* 122 */             this.startVariation = getCurrentBuffer().length();
/*     */           }
/* 124 */           formatBeginVariation(variantCount);
/* 125 */           this.variationsDeep += 1;
/*     */         }
/*     */         
/*     */ 
/* 129 */         formatPrenotation(move);
/* 130 */         formatPoints(number, isBlackMove, moveBuffer, variantCount, this.needNumber);
/*     */         
/*     */ 
/* 133 */         this.needNumber = false;
/* 134 */         moveBuffer.append(this.notation.moveToString(move));
/* 135 */         boolean willShowBoard = isWillShowBoard(move, variationSize, variantCount);
/*     */         
/*     */ 
/* 138 */         if (willShowBoard)
/*     */         {
/* 140 */           doShowBoard(move, moveBuffer);
/*     */         }
/*     */         
/* 143 */         ChessAnnotation annotation = retrieveAnnotation(move, moveBuffer);
/* 144 */         formatMoveColor(isBlackMove, moveBuffer);
/*     */         
/* 146 */         moveBuffer.delete(0, moveBuffer.length());
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 155 */         if (formatComments(annotation)) {
/* 156 */           this.needNumber = true;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 161 */         if ((move != null) && (this.exportVariations) && (variantCount > 0)) {
/* 162 */           walkMoveTreeBreadthFirst(move.getContinuationList(), 
/* 163 */             number + (isBlackMove ? 1 : 0));
/*     */           
/* 165 */           formatEndVariation();
/* 166 */           this.variationsDeep -= 1;
/* 167 */           if (this.variationsDeep == 0) {
/* 168 */             this.endVariation = getCurrentBuffer().length();
/*     */           }
/* 170 */           this.needNumber = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 175 */       move = (ChessMove)continuation.get(0);
/* 176 */       if (move != null) {
/* 177 */         if (move.isEndOfGame()) {
/* 178 */           doShowBoard(move, moveBuffer);
/*     */         }
/*     */         
/*     */ 
/* 182 */         walkMoveTreeBreadthFirst(move.getContinuationList(), 
/* 183 */           number + (isBlackMove ? 1 : 0));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isWillShowBoard(ChessMove move, int variationSize, int variantCount)
/*     */   {
/* 192 */     Move nextMove = move.getNext();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */     return (this.variationsDeep == 0) && (((variationSize > 1) && (variantCount == 0)) || (this.currentComment.length() > 0) || (move.isEndOfGame()));
/*     */   }
/*     */   
/*     */ 
/*     */   private void doShowBoard(ChessMove move, StringBuffer moveBuffer)
/*     */   {
/* 206 */     String bufferString = getCurrentBuffer().toString();
/* 207 */     String mainString = 
/* 208 */       (this.startVariation > 1) && (this.startVariation < bufferString.length()) ? 
/* 209 */       bufferString.substring(0, this.startVariation) : 
/* 210 */       bufferString;
/*     */     
/*     */ 
/* 213 */     String variationString = 
/* 214 */       this.endVariation < bufferString.length() ? 
/* 215 */       bufferString
/* 216 */       .substring(this.startVariation, this.endVariation) : 
/* 217 */       bufferString.substring(this.startVariation, bufferString.length());
/*     */     
/* 219 */     String mainAftervariationString = 
/* 220 */       (this.endVariation < bufferString.length()) && (this.startVariation > 1) ? 
/* 221 */       bufferString
/* 222 */       .substring(this.endVariation, getCurrentBuffer().length()) : 
/* 223 */       "";
/*     */     
/* 225 */     showBoard(this.game, move.getPrev(), 
/* 226 */       moveBuffer.toString(), 
/* 227 */       mainString, 
/* 228 */       variationString, 
/*     */       
/* 230 */       mainAftervariationString, 
/* 231 */       this.currentComment);
/*     */     
/* 233 */     this.runningBuffer.delete(0, this.runningBuffer.length());
/* 234 */     this.totalBuffer.delete(0, this.totalBuffer.length());
/* 235 */     this.buffer.delete(0, this.buffer.length());
/* 236 */     this.currentComment = "";
/* 237 */     this.startVariation = 0;
/* 238 */     this.endVariation = 0;
/*     */   }
/*     */   
/*     */   private void formatEndVariation() {
/* 242 */     formatOutput(")", 4);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean formatNags(ChessAnnotation annotation)
/*     */   {
/* 248 */     boolean needNumber = false;
/* 249 */     short[] nags; if ((annotation != null) && 
/* 250 */       (this.glyphStyle != 0) && 
/* 251 */       (this.glyphStyle != 4) && 
/* 252 */       ((nags = annotation.getNAGs()) != null))
/*     */     {
/*     */ 
/* 255 */       for (int j = (NAG.isSuffix(nags[0])) && 
/* 256 */             (this.glyphStyle != 2) ? 1 : 0; 
/* 257 */           j < nags.length; j++)
/*     */       {
/* 259 */         needNumber = true;
/*     */         
/* 261 */         switch (this.glyphStyle)
/*     */         {
/*     */         case 2: 
/* 264 */           formatOutput(NAG.numberToString(nags[j], true), 
/* 265 */             6);
/* 266 */           break;
/*     */         
/*     */         case 3: 
/* 269 */           if (NAG.isSymbol(nags[j]))
/* 270 */             formatOutput(NAG.numberToString(nags[j]), 
/* 271 */               6);
/* 272 */           break;
/*     */         
/*     */         case 1: 
/* 275 */           formatOutput(NAG.numberToString(nags[j]), 
/* 276 */             6);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 281 */     return needNumber;
/*     */   }
/*     */   
/*     */   protected ChessAnnotation retrieveAnnotation(ChessMove move, StringBuffer moveBuffer)
/*     */   {
/* 286 */     ChessAnnotation annotation = (ChessAnnotation)move.getAnnotation();
/*     */     
/* 288 */     if ((annotation != null) && 
/* 289 */       (this.glyphStyle != 0) && 
/* 290 */       (this.glyphStyle != 2) && 
/* 291 */       (annotation.getSuffix() != 0)) {
/* 292 */       moveBuffer.append(NAG.numberToString(annotation.getSuffix()));
/*     */     }
/* 294 */     return annotation;
/*     */   }
/*     */   
/*     */   protected void formatPoints(int number, boolean isBlackMove, StringBuffer moveBuffer, int variantCount, boolean needNumber) {
/* 298 */     if (!isBlackMove) {
/* 299 */       moveBuffer.append(number).append(".");
/*     */     }
/* 301 */     if (((variantCount > 0) || (needNumber)) && (isBlackMove))
/* 302 */       moveBuffer.append(number).append("...");
/*     */   }
/*     */   
/*     */   protected void formatBeginVariation(int variantCount) {
/* 306 */     if (variantCount > 0) {
/* 307 */       formatOutput("(", 3);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void formatMoveColor(boolean isBlackMove, StringBuffer moveBuffer)
/*     */   {
/* 313 */     formatOutput(moveBuffer.toString(), 
/* 314 */       isBlackMove ? 2 : 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isBlackMove(ContinuationList cont, boolean isBlackMove)
/*     */   {
/* 321 */     ChessMove currMove = null;
/* 322 */     if (cont.get(0) != null) {
/* 323 */       currMove = (ChessMove)cont.get(0);
/* 324 */     } else if (cont.get(1) != null) {
/* 325 */       currMove = (ChessMove)cont.get(1);
/*     */     }
/* 327 */     if (currMove.isNullMove()) {
/* 328 */       currMove = (ChessMove)currMove.getPrev();
/*     */     }
/* 330 */     isBlackMove = currMove.isBlackMove();
/*     */     
/* 332 */     return isBlackMove;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void formatOutput(String str, int type)
/*     */   {
/* 338 */     super.formatOutput(str, type);
/* 339 */     if (this.buffer.length() > this.runningBuffer.length()) {
/* 340 */       this.runningBuffer = new StringBuffer();
/* 341 */       this.runningBuffer.append(this.buffer.toString());
/*     */     } else {
/* 343 */       this.totalBuffer.append(this.runningBuffer.toString());
/* 344 */       this.runningBuffer = new StringBuffer();
/*     */     }
/*     */   }
/*     */   
/*     */   private StringBuffer getCurrentBuffer() {
/* 349 */     StringBuffer currentBuffer = new StringBuffer();
/* 350 */     currentBuffer.append(this.totalBuffer.toString());
/* 351 */     currentBuffer.append(this.runningBuffer.toString());
/* 352 */     return currentBuffer;
/*     */   }
/*     */   
/*     */   public void println(String str) {}
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\writer\ExtendedPGNWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */