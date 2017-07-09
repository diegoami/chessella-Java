package com.amicabile.openingtrainer.controller.freemarker;

import com.amicabile.openingtrainer.controller.freemarker.GenericPrinter;
import com.amicabile.openingtrainer.model.board.VelocityBoard;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
import freemarker.template.Configuration;
import ictk.boardgame.Game;
import ictk.boardgame.Move;
import java.util.Map;
import org.apache.log4j.Logger;

public class BoardPrinter extends GenericPrinter {

   private static Logger log = Logger.getLogger(BoardPrinter.class.getName());
   private Game game;
   private Move move;
   private VelocityBoard velocityBoard;
   private boolean flippedBool;


   public BoardPrinter(String templateName, Configuration config, Game game) {
      this(templateName, config, game, (Move)null, false);
   }

   public BoardPrinter(String templateName, Configuration config, VelocityBoard velocityBoard) {
      super(templateName, config);
      this.flippedBool = false;
      this.velocityBoard = velocityBoard;
   }

   public BoardPrinter(String templateName, Configuration config, String fenString) {
      super(templateName, config);
      this.flippedBool = false;
      this.velocityBoard = this.velocityBoard;
      this.fillBoardWithFenString(fenString);
   }

   public BoardPrinter(String templateName, Configuration config, Game game, Move move) {
      this(templateName, config, game, move, false);
   }

   public BoardPrinter(String templateName, Configuration config, Game game, Move move, boolean flippedBool) {
      super(templateName, config);
      this.flippedBool = false;
      this.setGame(game);
      this.move = move;
      this.flippedBool = flippedBool;
      String fenString = PGNAdapter.getFenStringForMove(game, move);
      this.fillBoardWithFenString(fenString);
   }

   private void fillBoardWithFenString(String fenString) {
      this.velocityBoard = new VelocityBoard();
      VelocityBoardFactory.getInstance().createBoardFromFenString(this.velocityBoard, fenString, this.flippedBool);
   }

   public void setGame(Game game) {
      this.game = game;
   }

   public void fillMap(Map map) {
      this.fillMap(map, this.velocityBoard, this.game);
   }

   private void fillMap(Map map, VelocityBoard velocityBoard, Game game) {
      map.put("board", velocityBoard);
      map.put("currentGame", game);
      map.put("gameInfo", game.getGameInfo());
   }
}
