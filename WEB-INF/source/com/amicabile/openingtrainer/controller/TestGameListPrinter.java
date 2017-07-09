package com.amicabile.openingtrainer.controller;

import com.amicabile.openingtrainer.controller.MoveTreeFiller;
import com.amicabile.openingtrainer.controller.MoveTreePrinter;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import ictk.boardgame.chess.ChessGame;
import java.util.Iterator;
import junit.framework.TestCase;
import junit.textui.TestRunner;

public class TestGameListPrinter extends TestCase {

   public static void main(String[] args) {
      TestRunner.run(TestGameListPrinter.class);
   }

   public void testFiller() throws Exception {
      ChessGameGroup chessGameGroupFromFile = PGNAdapter.getChessGameGroupFromFile("pgn/testread.pgn");
      Iterator var3 = chessGameGroupFromFile.getGameList().iterator();

      while(var3.hasNext()) {
         ChessGame game = (ChessGame)var3.next();
         MoveTreeFiller moveTreeFiller = new MoveTreeFiller(System.out);
         moveTreeFiller.writeGame(game);
         MoveTreePrinter moveTreePrinter = new MoveTreePrinter(moveTreeFiller.getMoveTree());
         moveTreePrinter.printOut();
      }

   }
}
