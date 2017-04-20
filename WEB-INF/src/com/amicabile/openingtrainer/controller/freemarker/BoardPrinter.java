/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.board.VelocityBoard;
/*    */ import com.amicabile.openingtrainer.pgn.PGNAdapter;
/*    */ import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
/*    */ import freemarker.template.Configuration;
/*    */ import ictk.boardgame.Game;
/*    */ import ictk.boardgame.Move;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class BoardPrinter
/*    */   extends GenericPrinter
/*    */ {
/* 16 */   private static Logger log = Logger.getLogger(BoardPrinter.class
/* 17 */     .getName());
/*    */   
/*    */   private Game game;
/*    */   
/*    */   private Move move;
/*    */   private VelocityBoard velocityBoard;
/* 23 */   private boolean flippedBool = false;
/*    */   
/*    */   public BoardPrinter(String templateName, Configuration config, Game game)
/*    */   {
/* 27 */     this(templateName, config, game, null, false);
/*    */   }
/*    */   
/*    */   public BoardPrinter(String templateName, Configuration config, VelocityBoard velocityBoard) {
/* 31 */     super(templateName, config);
/* 32 */     this.velocityBoard = velocityBoard;
/*    */   }
/*    */   
/*    */   public BoardPrinter(String templateName, Configuration config, String fenString) {
/* 36 */     super(templateName, config);
/* 37 */     this.velocityBoard = this.velocityBoard;
/* 38 */     fillBoardWithFenString(fenString);
/*    */   }
/*    */   
/*    */   public BoardPrinter(String templateName, Configuration config, Game game, Move move)
/*    */   {
/* 43 */     this(templateName, config, game, move, false);
/*    */   }
/*    */   
/*    */   public BoardPrinter(String templateName, Configuration config, Game game, Move move, boolean flippedBool)
/*    */   {
/* 48 */     super(templateName, config);
/* 49 */     setGame(game);
/* 50 */     this.move = move;
/* 51 */     this.flippedBool = flippedBool;
/* 52 */     String fenString = PGNAdapter.getFenStringForMove(game, move);
/* 53 */     fillBoardWithFenString(fenString);
/*    */   }
/*    */   
/*    */   private void fillBoardWithFenString(String fenString)
/*    */   {
/* 58 */     this.velocityBoard = new VelocityBoard();
/* 59 */     VelocityBoardFactory.getInstance().createBoardFromFenString(
/* 60 */       this.velocityBoard, fenString, this.flippedBool);
/*    */   }
/*    */   
/*    */   public void setGame(Game game) {
/* 64 */     this.game = game;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fillMap(Map map)
/*    */   {
/* 72 */     fillMap(map, this.velocityBoard, this.game);
/*    */   }
/*    */   
/*    */   private void fillMap(Map map, VelocityBoard velocityBoard, Game game)
/*    */   {
/* 77 */     map.put("board", velocityBoard);
/* 78 */     map.put("currentGame", game);
/* 79 */     map.put("gameInfo", game.getGameInfo());
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\BoardPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */