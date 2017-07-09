package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import com.amicabile.openingtrainer.controller.freemarker.GenericPrinter;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import freemarker.template.Configuration;
import java.util.Map;
import org.apache.log4j.Logger;

public class GameListPrinter extends GenericPrinter {

   private static Logger log = Logger.getLogger(GameListPrinter.class.getName());
   private ShowBoardRule showBoardRule;
   private ChessGameGroup chessGameGroup;


   public GameListPrinter(String templateName, Configuration config, VelocityMoveTree moveTree, ChessGameGroup chessGameGroup) {
      super(templateName, config);
      this.chessGameGroup = chessGameGroup;
   }

   public GameListPrinter(String templateName, Configuration config, ChessGameGroup chessGameGroup, ShowBoardRule showBoardRule) {
      super(templateName, config);
      this.chessGameGroup = chessGameGroup;
      this.showBoardRule = showBoardRule;
   }

   public void fillMap(Map map) {
      map.put("showBoardRule", this.showBoardRule);
      map.put("chessGameGroup", this.chessGameGroup);
   }
}
