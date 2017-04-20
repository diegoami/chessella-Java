/*     */ package com.amicabile.openingtrainer.util.board;
/*     */ 
/*     */ import com.amicabile.openingtrainer.model.PieceEnum;
/*     */ import com.amicabile.openingtrainer.model.board.VelocityBoard;
/*     */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*     */ import com.amicabile.openingtrainer.util.ColorEnumFactory;
/*     */ import com.amicabile.openingtrainer.util.PieceEnumFactory;
/*     */ import ictk.boardgame.Game;
/*     */ import ictk.boardgame.Move;
/*     */ import java.util.Scanner;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VelocityBoardFactory
/*     */ {
/*  19 */   private static Logger log = Logger.getLogger(VelocityBoardFactory.class);
/*     */   
/*  21 */   private static VelocityBoardFactory velocityBoardFactory = new VelocityBoardFactory();
/*     */   
/*     */   public VelocityBoard createBoardFromFenString(String fenString) {
/*  24 */     return createBoardFromFenString(fenString, false);
/*     */   }
/*     */   
/*     */   public VelocityBoard createBoardFromFenString(String fenString, boolean flipped) {
/*  28 */     VelocityBoard velocityBoard = new VelocityBoard();
/*  29 */     return createBoardFromFenString(velocityBoard, fenString, flipped);
/*     */   }
/*     */   
/*     */   public VelocityBoard createBoardFromFenString(Game game, Move move)
/*     */   {
/*  34 */     return createBoardFromFenString(game, move, false);
/*     */   }
/*     */   
/*     */   public VelocityBoard createBoardFromFenString(Game game, Move move, boolean flipped) {
/*  38 */     String fenString = PGNAdapter.getFenStringForMove(game, move);
/*  39 */     return createBoardFromFenString(fenString, flipped);
/*     */   }
/*     */   
/*     */   public VelocityBoard createBoardFromFenString(Game game) {
/*  43 */     return createBoardFromFenString(game, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public VelocityBoard createBoardFromFenString(Game game, boolean flipped)
/*     */   {
/*  49 */     String fenString = PGNAdapter.getFenStringForMove(game);
/*  50 */     return createBoardFromFenString(fenString, flipped);
/*     */   }
/*     */   
/*     */ 
/*     */   public VelocityBoard createBoardFromFenString(VelocityBoard board, String fenString, boolean flipped)
/*     */   {
/*  56 */     Scanner scanner = new Scanner(fenString);
/*  57 */     scanner.useDelimiter(" ");
/*  58 */     String piecesFenString = scanner.next();
/*     */     
/*  60 */     fillBoardFromFenString(board, piecesFenString, flipped);
/*  61 */     String moveFenString = scanner.next();
/*     */     
/*     */ 
/*  64 */     fillToMoveFromFenString(board, moveFenString);
/*     */     
/*  66 */     return board;
/*     */   }
/*     */   
/*     */   public VelocityBoard fillToMoveFromFenString(VelocityBoard board, String colorFenString) {
/*  70 */     if (StringUtils.isNotEmpty(colorFenString)) {
/*  71 */       board.setToMove(ColorEnumFactory.createFromFenNotation(colorFenString.charAt(0)));
/*     */     }
/*  73 */     return board;
/*     */   }
/*     */   
/*     */   public VelocityBoard fillBoardFromFenString(VelocityBoard board, String piecesFenString, boolean flipped)
/*     */   {
/*  78 */     log.debug("Trying to read " + piecesFenString);
/*  79 */     Scanner scanner = new Scanner(piecesFenString);
/*  80 */     scanner.useDelimiter("/");
/*  81 */     int rowCount = 1;
/*  82 */     while (scanner.hasNext())
/*     */     {
/*  84 */       String row = scanner.next();
/*  85 */       log.debug("Scanning row " + row);
/*     */       
/*  87 */       int colCount = 1;
/*  88 */       for (int i = 0; i < row.length(); i++)
/*     */       {
/*  90 */         char rowChar = row.charAt(i);
/*  91 */         log.debug("Processing char " + rowChar);
/*  92 */         if ((rowChar >= '0') && (rowChar <= '9')) {
/*  93 */           int intCount = rowChar - '0';
/*  94 */           colCount += intCount;
/*     */         }
/*     */         else
/*     */         {
/*  98 */           PieceEnum piece = PieceEnumFactory.createFromFenNotation(rowChar);
/*  99 */           if (flipped) {
/* 100 */             board.setPieceAt(9 - rowCount, 9 - colCount, piece);
/*     */           } else {
/* 102 */             board.setPieceAt(rowCount, colCount, piece);
/*     */           }
/*     */           
/* 105 */           colCount++;
/*     */         }
/*     */       }
/* 108 */       rowCount++;
/*     */     }
/* 110 */     return board;
/*     */   }
/*     */   
/*     */ 
/*     */   public static VelocityBoardFactory getInstance()
/*     */   {
/* 116 */     return velocityBoardFactory;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\util\board\VelocityBoardFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */