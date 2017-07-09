package com.amicabile.openingtrainer.dwr;

import com.amicabile.openingtrainer.controller.MoveTreePool;
import com.amicabile.openingtrainer.controller.OpeningTrainerControllerException;
import com.amicabile.openingtrainer.controller.freemarker.BoardPrinter;
import com.amicabile.openingtrainer.model.notation.VelocityMove;
import com.amicabile.openingtrainer.model.notation.VelocityMoveKey;
import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
import com.amicabile.openingtrainer.model.state.GamePrinterState;
import com.amicabile.openingtrainer.repository.GameRepository;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import ictk.boardgame.Game;
import java.io.StringWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import uk.ltd.getahead.dwr.ExecutionContext;

public class BoardForwarder {

   private static Logger log = Logger.getLogger(BoardForwarder.class.getName());


   private String getCommonBoardRepresentation(String gameStateString, String flipped, int forward, int previous) throws OpeningTrainerControllerException {
      Configuration cfg = new Configuration();
      boolean flippedBool = "1".equals(flipped);

      try {
         ServletContext httpServletRequest = ExecutionContext.get().getServletContext();
         cfg.setServletContextForTemplateLoading(httpServletRequest, "games");
         cfg.setObjectWrapper(new DefaultObjectWrapper());
      } catch (Exception var15) {
         var15.printStackTrace();
      }

      HttpServletRequest httpServletRequest1 = ExecutionContext.get().getHttpServletRequest();
      String base = httpServletRequest1.getContextPath();

      try {
         StringWriter e = new StringWriter();
         BoardForwarder.GameAndMove gameAndMove = this.getMoveForState(gameStateString, forward, previous);
         VelocityMove toUseMove = gameAndMove.velocityMove;
         HashMap map = new HashMap();
         map.put("base", base);
         map.put("flipboard", flippedBool?"2":"");
         map.put("user", httpServletRequest1.getSession().getAttribute("user"));
         map.put("move", toUseMove);
         BoardPrinter boardPrinter = new BoardPrinter("chessnetboardtemplate.ftl", cfg, gameAndMove.game, toUseMove != null?toUseMove.getMove():null, flippedBool);
         boardPrinter.showTemplate(map, e);
         return e.toString();
      } catch (Exception var14) {
         var14.printStackTrace();
         throw new OpeningTrainerControllerException(var14);
      }
   }

   public String getMoveFiveForward(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMoveFiveForward(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 10, 0);
   }

   public String getMoveAllForward(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMoveAllForward(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 1000, 0);
   }

   public String getMoveFivePrevious(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMoveAllPrevious(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 0, 10);
   }

   public String getMoveAllPrevious(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMoveAllPrevious(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 0, 1000);
   }

   public String getMoveForward(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMoveForward(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 1, 0);
   }

   public String getMovePrevious(String gameStateString, String flipped) throws OpeningTrainerControllerException {
      log.debug("getMovePrevious(" + gameStateString + ")");
      return this.getGameStateForState(gameStateString, 0, 1);
   }

   public String getGameStateForState(String gameStateString, int forward, int previous) throws OpeningTrainerControllerException {
      GamePrinterState state = new GamePrinterState(gameStateString);
      VelocityMove moveForState = this.getMoveForState(gameStateString, forward, previous).velocityMove;
      if(moveForState != null) {
         state.setBlackMove(moveForState.isBlackMove());
         state.setDepth(moveForState.getDepth());
         state.setMoveNumber(moveForState.getNumber());
         state.setMoveString(moveForState.getMoveString());
         state.setBoardHashCode(moveForState.getBoardHashCode());
         return state.toString();
      } else {
         return state.getBoardNumber() + "_X_0_false_0_0";
      }
   }

   public BoardForwarder.GameAndMove getMoveForState(String gameStateString, int forward, int previous) throws OpeningTrainerControllerException {
      GamePrinterState state = new GamePrinterState(gameStateString);
      Game game = null;
      if(game == null) {
         game = GameRepository.getInstance().getGame(state.getBoardNumber());
      }

      VelocityMoveTree moveTree = MoveTreePool.getInstance().retrieveMoveTree(game, state.getBoardNumber());
      game = moveTree.getGame();
      VelocityMove velocityMove = null;
      if(state.getMoveNumber() == 0) {
         velocityMove = null;
      } else {
         velocityMove = moveTree.getMoveFor(new VelocityMoveKey(state.getMoveString(), state.getMoveNumber(), state.getDepth(), state.getBlackMove(), state.getBoardHashCode()));
      }

      int gameAndMove;
      for(gameAndMove = 0; gameAndMove < forward; ++gameAndMove) {
         if(velocityMove == null) {
            velocityMove = moveTree.getFirstMove();
         } else if(velocityMove.getNextMove() != null) {
            velocityMove = velocityMove.getNextMove();
         }
      }

      for(gameAndMove = 0; gameAndMove < previous; ++gameAndMove) {
         if(velocityMove != null) {
            if(velocityMove.getPrevMove() == null) {
               velocityMove = null;
               break;
            }

            velocityMove = velocityMove.getPrevMove();
         }
      }

      BoardForwarder.GameAndMove var9 = new BoardForwarder.GameAndMove();
      var9.game = game;
      var9.velocityMove = velocityMove;
      return var9;
   }

   public String getBoardRepresentation(String gameStateString, String flipvalue) throws OpeningTrainerControllerException {
      log.debug("getBoardRepresentation(" + gameStateString + ")");
      return this.getCommonBoardRepresentation(gameStateString, flipvalue, 0, 0);
   }

   class GameAndMove {

      Game game;
      VelocityMove velocityMove;


   }
}
