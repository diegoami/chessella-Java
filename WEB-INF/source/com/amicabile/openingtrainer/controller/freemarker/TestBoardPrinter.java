package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.controller.MoveTreePool;
import com.amicabile.openingtrainer.controller.freemarker.BoardPrinter;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
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
import junit.textui.TestRunner;

public class TestBoardPrinter extends TestCase {

   public static void main(String[] args) {
      TestRunner.run(TestBoardPrinter.class);
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
         BoardPrinter boardPrinter = new BoardPrinter("chessnetboardtemplate.ftl", cfg, game);
         HashMap map = new HashMap();
         boardPrinter.showTemplate(map, bufferedWriter);
         moveTree.resetCurrentMove();
         bufferedWriter.close();
      }

   }
}
