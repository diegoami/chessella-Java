package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import com.amicabile.openingtrainer.controller.freemarker.GenericPrinter;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import freemarker.template.Configuration;
import ictk.boardgame.Game;
import java.util.Map;
import org.apache.log4j.Logger;

public class MoveTreePrinter extends GenericPrinter {

   private static Logger log = Logger.getLogger(MoveTreePrinter.class.getName());
   private ShowBoardRule showBoardRule;
   private Game game;
   private VelocityMoveTree moveTree;


   public MoveTreePrinter(String templateName, Configuration config, VelocityMoveTree moveTree, Game game) {
      super(templateName, config);
      this.moveTree = moveTree;
      this.game = game;
   }

   public MoveTreePrinter(String templateName, Configuration config, VelocityMoveTree moveTree, Game game, ShowBoardRule showBoardRule) {
      super(templateName, config);
      this.moveTree = moveTree;
      this.game = game;
      this.showBoardRule = showBoardRule;
   }

   public void fillMap(Map map) {
      map.put("moveTree", this.moveTree);
      map.put("showBoardRule", this.showBoardRule);
      map.put("game", this.game);
   }
}
