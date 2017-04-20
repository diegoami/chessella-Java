/*     */ package com.amicabile.openingtrainer.dwr;
/*     */ 
/*     */ import com.amicabile.openingtrainer.controller.MoveTreePool;
/*     */ import com.amicabile.openingtrainer.controller.OpeningTrainerControllerException;
/*     */ import com.amicabile.openingtrainer.controller.freemarker.BoardPrinter;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMove;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveKey;
/*     */ import com.amicabile.openingtrainer.model.notation.VelocityMoveTree;
/*     */ import com.amicabile.openingtrainer.model.state.GamePrinterState;
/*     */ import com.amicabile.openingtrainer.repository.GameRepository;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.DefaultObjectWrapper;
/*     */ import ictk.boardgame.Game;
/*     */ import java.io.StringWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ import uk.ltd.getahead.dwr.ExecutionContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoardForwarder
/*     */ {
/*  31 */   private static Logger log = Logger.getLogger(BoardForwarder.class.getName());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getCommonBoardRepresentation(String gameStateString, String flipped, int forward, int previous)
/*     */     throws OpeningTrainerControllerException
/*     */   {
/*  42 */     Configuration cfg = new Configuration();
/*  43 */     boolean flippedBool = "1".equals(flipped);
/*     */     try
/*     */     {
/*  46 */       ServletContext servletContext = ExecutionContext.get()
/*  47 */         .getServletContext();
/*  48 */       cfg.setServletContextForTemplateLoading(servletContext, "games");
/*  49 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/*     */     }
/*     */     catch (Exception e) {
/*  52 */       e.printStackTrace();
/*     */     }
/*  54 */     HttpServletRequest httpServletRequest = ExecutionContext.get()
/*  55 */       .getHttpServletRequest();
/*  56 */     String base = httpServletRequest.getContextPath();
/*     */     
/*     */     try
/*     */     {
/*  60 */       StringWriter writer = new StringWriter();
/*  61 */       GameAndMove gameAndMove = getMoveForState(gameStateString, forward, 
/*  62 */         previous);
/*  63 */       VelocityMove toUseMove = gameAndMove.velocityMove;
/*     */       
/*  65 */       Map map = new HashMap();
/*     */       
/*     */ 
/*  68 */       map.put("base", base);
/*  69 */       map.put("flipboard", flippedBool ? "2" : "");
/*     */       
/*  71 */       map.put("user", httpServletRequest.getSession().getAttribute("user"));
/*     */       
/*  73 */       map.put("move", toUseMove);
/*     */       
/*  75 */       BoardPrinter boardPrinter = new BoardPrinter(
/*  76 */         "chessnetboardtemplate.ftl", cfg, gameAndMove.game, 
/*  77 */         toUseMove != null ? toUseMove.getMove() : null, flippedBool);
/*     */       
/*  79 */       boardPrinter.showTemplate(map, writer);
/*     */       
/*  81 */       return writer.toString();
/*     */     } catch (Exception e) {
/*  83 */       e.printStackTrace();
/*  84 */       throw new OpeningTrainerControllerException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getMoveFiveForward(String gameStateString, String flipped)
/*     */     throws OpeningTrainerControllerException
/*     */   {
/*  91 */     log.debug("getMoveFiveForward(" + gameStateString + ")");
/*  92 */     return getGameStateForState(gameStateString, 10, 0);
/*     */   }
/*     */   
/*     */   public String getMoveAllForward(String gameStateString, String flipped) throws OpeningTrainerControllerException
/*     */   {
/*  97 */     log.debug("getMoveAllForward(" + gameStateString + ")");
/*  98 */     return getGameStateForState(gameStateString, 1000, 0);
/*     */   }
/*     */   
/*     */   public String getMoveFivePrevious(String gameStateString, String flipped) throws OpeningTrainerControllerException
/*     */   {
/* 103 */     log.debug("getMoveAllPrevious(" + gameStateString + ")");
/*     */     
/* 105 */     return getGameStateForState(gameStateString, 0, 10);
/*     */   }
/*     */   
/*     */   public String getMoveAllPrevious(String gameStateString, String flipped)
/*     */     throws OpeningTrainerControllerException
/*     */   {
/* 111 */     log.debug("getMoveAllPrevious(" + gameStateString + ")");
/*     */     
/* 113 */     return getGameStateForState(gameStateString, 0, 1000);
/*     */   }
/*     */   
/*     */   public String getMoveForward(String gameStateString, String flipped) throws OpeningTrainerControllerException
/*     */   {
/* 118 */     log.debug("getMoveForward(" + gameStateString + ")");
/*     */     
/* 120 */     return getGameStateForState(gameStateString, 1, 0);
/*     */   }
/*     */   
/*     */   public String getMovePrevious(String gameStateString, String flipped) throws OpeningTrainerControllerException
/*     */   {
/* 125 */     log.debug("getMovePrevious(" + gameStateString + ")");
/*     */     
/* 127 */     return getGameStateForState(gameStateString, 0, 1);
/*     */   }
/*     */   
/*     */   public String getGameStateForState(String gameStateString, int forward, int previous) throws OpeningTrainerControllerException
/*     */   {
/* 132 */     GamePrinterState state = new GamePrinterState(gameStateString);
/*     */     
/* 134 */     VelocityMove moveForState = getMoveForState(gameStateString, forward, 
/* 135 */       previous).velocityMove;
/*     */     
/* 136 */     if (moveForState != null) {
/* 137 */       state.setBlackMove(moveForState.isBlackMove());
/* 138 */       state.setDepth(moveForState.getDepth());
/* 139 */       state.setMoveNumber(moveForState.getNumber());
/* 140 */       state.setMoveString(moveForState.getMoveString());
/* 141 */       state.setBoardHashCode(moveForState.getBoardHashCode());
/*     */       
/* 143 */       return state.toString();
/*     */     }
/* 145 */     return state.getBoardNumber() + "_X_0_false_0_0";
/*     */   }
/*     */   
/*     */ 
/*     */   public GameAndMove getMoveForState(String gameStateString, int forward, int previous)
/*     */     throws OpeningTrainerControllerException
/*     */   {
/* 152 */     GamePrinterState state = new GamePrinterState(gameStateString);
/* 153 */     Game game = null;
/* 154 */     if (game == null) {
/* 155 */       game = GameRepository.getInstance().getGame(state.getBoardNumber());
/*     */     }
/* 157 */     VelocityMoveTree moveTree = MoveTreePool.getInstance()
/* 158 */       .retrieveMoveTree(game, state.getBoardNumber());
/* 159 */     game = moveTree.getGame();
/* 160 */     VelocityMove velocityMove = null;
/* 161 */     if (state.getMoveNumber() == 0) {
/* 162 */       velocityMove = null;
/*     */     }
/*     */     else {
/* 165 */       velocityMove = moveTree.getMoveFor(new VelocityMoveKey(
/*     */       
/* 167 */         state.getMoveString(), state.getMoveNumber(), state.getDepth(), 
/* 168 */         state.getBlackMove(), state.getBoardHashCode()));
/*     */     }
/* 170 */     for (int forwardCounter = 0; forwardCounter < forward; forwardCounter++) {
/* 171 */       if (velocityMove == null) {
/* 172 */         velocityMove = moveTree.getFirstMove();
/*     */       }
/* 174 */       else if (velocityMove.getNextMove() != null) {
/* 175 */         velocityMove = velocityMove.getNextMove();
/*     */       }
/*     */     }
/*     */     
/* 179 */     for (int previousCounter = 0; previousCounter < previous; previousCounter++) {
/* 180 */       if (velocityMove != null) {
/* 181 */         if (velocityMove.getPrevMove() != null) {
/* 182 */           velocityMove = velocityMove.getPrevMove();
/*     */         } else {
/* 184 */           velocityMove = null;
/* 185 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 189 */     GameAndMove gameAndMove = new GameAndMove();
/* 190 */     gameAndMove.game = game;
/* 191 */     gameAndMove.velocityMove = velocityMove;
/* 192 */     return gameAndMove;
/*     */   }
/*     */   
/*     */   public String getBoardRepresentation(String gameStateString, String flipvalue)
/*     */     throws OpeningTrainerControllerException
/*     */   {
/* 198 */     log.debug("getBoardRepresentation(" + gameStateString + ")");
/* 199 */     return getCommonBoardRepresentation(gameStateString, flipvalue, 0, 0);
/*     */   }
/*     */   
/*     */   class GameAndMove
/*     */   {
/*     */     Game game;
/*     */     VelocityMove velocityMove;
/*     */     
/*     */     GameAndMove() {}
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\dwr\BoardForwarder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */