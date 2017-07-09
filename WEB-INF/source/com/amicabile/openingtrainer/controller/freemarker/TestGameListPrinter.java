package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
import com.amicabile.openingtrainer.controller.MoveTreePool;
import com.amicabile.openingtrainer.controller.freemarker.GameListPrinter;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import junit.framework.TestCase;

public class TestGameListPrinter extends TestCase {

   public static void main(String[] args) throws Exception {
      (new TestGameListPrinter()).testFiller();
   }

   public void testFiller() throws Exception {
      Configuration cfg = new Configuration();

      try {
         cfg.setDirectoryForTemplateLoading(new File("templates"));
         cfg.setObjectWrapper(new DefaultObjectWrapper());
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
      ChessGameGroup chessGameGroupFromFile = PGNAdapter.getChessGameGroupFromFile("pgn/1-4.pgn");
      GameListPrinter gameListPrinter = new GameListPrinter("gamelisttemplate.ftl", cfg, chessGameGroupFromFile, ShowBoardRulePrototypes.DEFAULT_RULE);
      HashMap map = new HashMap();
      map.put("velocityBoardFactory", VelocityBoardFactory.getInstance());
      map.put("moveTreePool", MoveTreePool.getInstance());
      gameListPrinter.showTemplate(map, bufferedWriter);
      bufferedWriter.close();
   }
}
