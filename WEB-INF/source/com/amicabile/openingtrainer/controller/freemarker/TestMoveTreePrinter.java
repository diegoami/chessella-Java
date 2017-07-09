package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
import com.amicabile.openingtrainer.controller.MoveTreePool;
import com.amicabile.openingtrainer.controller.freemarker.MoveTreePrinter;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import ictk.boardgame.chess.ChessGame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import junit.framework.TestCase;

public class TestMoveTreePrinter extends TestCase {

   public static void main(String[] args) throws Exception {
      (new TestMoveTreePrinter()).testFiller();
   }

   public void testFiller() throws Exception {
      Configuration cfg = new Configuration();

      try {
         cfg.setDirectoryForTemplateLoading(new File("templates"));
         cfg.setObjectWrapper(new DefaultObjectWrapper());
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      ChessGameGroup chessGameGroupFromFile = PGNAdapter.getChessGameGroupFromFile("pgn/kehrsam.pgn");
      Iterator var4 = chessGameGroupFromFile.getGameList().iterator();

      while(var4.hasNext()) {
         ChessGame game = (ChessGame)var4.next();
         BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
         VelocityMoveTree moveTree = MoveTreePool.getInstance().retrieveMoveTree(game);
         MoveTreePrinter moveTreePrinter = new MoveTreePrinter("notationtemplate.ftl", cfg, moveTree, game, ShowBoardRulePrototypes.DEFAULT_RULE);
         HashMap map = new HashMap();
         map.put("velocityBoardFactory", VelocityBoardFactory.getInstance());
         moveTreePrinter.showTemplate(map, bufferedWriter);
         moveTree.resetCurrentMove();
         bufferedWriter.close();
      }

   }
}
