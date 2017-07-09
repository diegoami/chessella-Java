package com.amicabile.openingtrainer.starter.freemarker;

import com.amicabile.openingtrainer.model.board.VelocityBoard;
import com.amicabile.openingtrainer.starter.freemarker.AbstractStarter;
import org.apache.log4j.Logger;

public class BoardPrinterStarter extends AbstractStarter {

   private static Logger LOG = Logger.getLogger(BoardPrinterStarter.class);
   private VelocityBoard board;


   public BoardPrinterStarter(VelocityBoard board, String templateFileName) {
      this.board = board;
      this.templateFileName = templateFileName;
      this.fillBoard();
   }

   public void fillBoard() {
      this.rootMap.put("board", this.board);
   }
}
