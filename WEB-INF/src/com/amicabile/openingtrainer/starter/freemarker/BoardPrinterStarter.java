/*    */ package com.amicabile.openingtrainer.starter.freemarker;
/*    */ 
/*    */ import com.amicabile.openingtrainer.model.board.VelocityBoard;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class BoardPrinterStarter extends AbstractStarter
/*    */ {
/*  9 */   private static Logger LOG = Logger.getLogger(BoardPrinterStarter.class);
/*    */   private VelocityBoard board;
/*    */   
/*    */   public BoardPrinterStarter(VelocityBoard board, String templateFileName)
/*    */   {
/* 14 */     this.board = board;
/* 15 */     this.templateFileName = templateFileName;
/* 16 */     fillBoard();
/*    */   }
/*    */   
/*    */ 
/*    */   public void fillBoard()
/*    */   {
/* 22 */     this.rootMap.put("board", this.board);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\starter\freemarker\BoardPrinterStarter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */