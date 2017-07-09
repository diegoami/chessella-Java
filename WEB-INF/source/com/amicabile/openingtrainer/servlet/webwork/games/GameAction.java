package com.amicabile.openingtrainer.servlet.webwork.games;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import com.amicabile.openingtrainer.config.ShowBoardRulePrototypes;
import com.amicabile.openingtrainer.controller.MoveTreePool;
import com.amicabile.openingtrainer.model.dataobj.GameDataObj;
import com.amicabile.openingtrainer.model.dataobj.User;
import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import com.amicabile.openingtrainer.pgn.PGNException;
import com.amicabile.openingtrainer.repository.GameRepository;
import com.amicabile.openingtrainer.util.board.VelocityBoardFactory;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import ictk.boardgame.Game;
import ictk.boardgame.chess.ChessGame;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class GameAction extends ActionSupport {

   private static Logger log = Logger.getLogger(GameAction.class.getName());
   private String tag;
   private long id;
   private String pgnString;
   private String urlString;
   private boolean publicgame = false;
   private GameDataObj currentGameDataObj;
   private ShowBoardRule currentShowBoardRule = null;
   private File file;
   private String contentType;
   private String filename;
   private VelocityBoardFactory velocityBoardFactory = VelocityBoardFactory.getInstance();
   private ChessGameGroup chessGameGroup;


   public void setUpload(File file) {
      this.file = file;
   }

   public boolean isCanRead() {
      boolean canRead = false;
      if(this.currentGameDataObj != null && (this.currentGameDataObj.isPublicgame() || this.currentGameDataObj.getUser().getUsername().equals(this.getUser().getUsername()))) {
         canRead = true;
      }

      return canRead;
   }

   public void setUploadContentType(String contentType) {
      this.contentType = contentType;
   }

   public void setUploadFileName(String filename) {
      this.filename = filename;
   }

   public String getUrlString() {
      return this.urlString;
   }

   public void setUrlString(String urlString) {
      this.urlString = urlString;
   }

   public boolean isEditable() {
      User user = this.getUser();
      return user != null && this.currentGameDataObj != null && this.currentGameDataObj.getUser().getUsername().equals(user.getUsername());
   }

   public ShowBoardRule getShowBoardRule() {
      return this.getUser() != null?this.getUser().getShowBoardRule():(this.currentShowBoardRule != null?this.currentShowBoardRule:ShowBoardRulePrototypes.DEFAULT_RULE);
   }

   private User getUser() {
      Map session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      return user;
   }

   public String getStringId() {
      return String.valueOf(this.id);
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public List getGameIds() {
      Map session = ActionContext.getContext().getSession();
      List gameIds = (List)((List)session.get("gameIds"));
      return gameIds;
   }

   public String getNextGameId() {
      List gameIds = this.getGameIds();
      int indexOfId = gameIds.indexOf(Long.valueOf(this.getId()));
      int indexOfNextId = indexOfId + 1;
      return gameIds.size() > indexOfNextId?String.valueOf(gameIds.get(indexOfNextId)):null;
   }

   public String getPrevGameId() {
      List gameIds = this.getGameIds();
      int indexOfId = gameIds.indexOf(Long.valueOf(this.getId()));
      int indexOfPrevId = indexOfId - 1;
      return indexOfPrevId >= 0?String.valueOf(gameIds.get(indexOfPrevId)):null;
   }

   public ChessGameGroup getChessGameGroup() {
      return this.chessGameGroup;
   }

   public void setChessGameGroup(ChessGameGroup chessGameGroup) {
      this.chessGameGroup = chessGameGroup;
   }

   public MoveTreePool getMoveTreePool() {
      return MoveTreePool.getInstance();
   }

   public VelocityBoardFactory getVelocityBoardFactory() {
      return this.velocityBoardFactory;
   }

   public String getPgnString() {
      return this.pgnString;
   }

   public String getEncodedPgnString() {
      return this.pgnString != null?StringEscapeUtils.escapeHtml(this.pgnString):"";
   }

   public void setPgnString(String pgnString) {
      this.pgnString = pgnString;
   }

   public String saveGameFromLink() {
      User user = this.getUser();
      if(user != null) {
         try {
            String he = user.getUsername();
            if(this.urlString == null) {
               return "input";
            } else if(GameRepository.getInstance().canAddGames(he)) {
               HttpClient httpClient = new HttpClient();
               GetMethod method = new GetMethod(this.urlString);
               httpClient.executeMethod(method);
               InputStream resourceAsStream = method.getResponseBodyAsStream();
               InputStreamReader reader = new InputStreamReader(resourceAsStream);

               try {
                  this.fillChessGameGroupFromReader(he, reader, this.getUser().getMaxgames());
                  log.info("Successfully executed saveGameFromLink");
                  return "success";
               } catch (PGNException var12) {
                  this.addActionError(var12.getMessage());
                  log.error(var12.getMessage(), var12);
               } finally {
                  reader = null;
               }

               return "input";
            } else {
               this.addActionError("You have already saved your maximal amount of games");
               return "input";
            }
         } catch (Exception var14) {
            log.error("Exception in saveGameFromLink", var14);
            this.addActionError("Could not add games : " + var14.getMessage());
            return "error";
         }
      } else {
         return "login";
      }
   }

   public String uploadPgn() {
      User user = this.getUser();
      if(user != null) {
         String username = user.getUsername();

         try {
            if(this.file == null) {
               return "input";
            } else if(GameRepository.getInstance().canAddGames(username)) {
               FileReader he = new FileReader(this.filename);

               try {
                  this.fillChessGameGroupFromReader(username, he, this.getUser().getMaxgames());
                  log.info("Successfully executed uploadPgn");
                  return "success";
               } catch (PGNException var9) {
                  this.addActionError("You cannot add more than " + this.getUser().getMaxgames() + " games ");
               } finally {
                  if(he != null) {
                     he.close();
                  }

                  he = null;
               }

               return "input";
            } else {
               this.addActionError("You have already saved your maximal amount of games");
               return "input";
            }
         } catch (Exception var11) {
            log.error("Exception in saveGameFromPgn", var11);
            this.addActionError("Could not add games : " + var11.getMessage());
            return "error";
         }
      } else {
         return "login";
      }
   }

   public String saveGameFromPgn() {
      User user = this.getUser();
      if(user != null) {
         String username = user.getUsername();

         try {
            if(StringUtils.isEmpty(this.pgnString)) {
               this.pgnString = "";
               return "input";
            } else if(GameRepository.getInstance().canAddGames(username)) {
               StringReader he = new StringReader(this.pgnString);

               try {
                  this.fillChessGameGroupFromReader(username, he, this.getUser().getMaxgames());
                  log.info("Successfully executed saveGameFromPgn");
                  return "success";
               } catch (PGNException var9) {
                  this.addActionError("You cannot add more than " + this.getUser().getMaxgames() + " games ");
               } finally {
                  he = null;
               }

               return "input";
            } else {
               this.addActionError("You have already saved your maximal amount of games");
               return "input";
            }
         } catch (Exception var11) {
            log.error("Exception in saveGameFromPgn", var11);
            this.addActionError("Could not add games : " + var11.getMessage());
            return "error";
         }
      } else {
         return "login";
      }
   }

   public String showGameById() {
      try {
         this.chessGameGroup = new ChessGameGroup();
         GameRepository he = GameRepository.getInstance();
         GameDataObj gameDataObj = he.getGameDataObj(this.id);
         this.currentGameDataObj = gameDataObj;
         this.currentShowBoardRule = gameDataObj.getUser().getShowBoardRule();
         Game game = he.getGameForGameDataObj(this.id, gameDataObj);
         this.chessGameGroup.addChessGame((ChessGame)game);
         log.info("Successfully executed showGameById");
         return "success";
      } catch (Exception var4) {
         log.error("Exception in showGameById", var4);
         this.addActionError("Could not show game: " + var4.getMessage());
         return "error";
      }
   }

   public String modifyPgnStringById() {
      try {
         GameRepository he = GameRepository.getInstance();
         GameDataObj gameDataObj = he.getGameDataObj(this.id);
         gameDataObj.setPgnstring(this.getPgnString());
         gameDataObj.setPublicgame(this.isPublicgame());
         he.updateAndFillGameDataObj(gameDataObj);
         MoveTreePool.getInstance().clearMoveTree(gameDataObj.getId().longValue());
         this.showGameById();
         return "success";
      } catch (Exception var3) {
         log.error("Exception in modifyPgnStringById", var3);
         return "error";
      }
   }

   public String showPgnStringById() {
      try {
         GameRepository he = GameRepository.getInstance();
         GameDataObj gameDataObj = he.getGameDataObj(this.id);
         this.setPgnString(gameDataObj.getPgnstring());
         this.setPublicgame(gameDataObj.isPublicgame());
         this.currentGameDataObj = gameDataObj;
         log.info("Successfully executed showPgnStringById");
         return "success";
      } catch (Exception var3) {
         log.error("Exception in showPgnStringById", var3);
         return "error";
      }
   }

   public String deleteGameById() {
      User user = this.getUser();
      if(user != null) {
         String username = user.getUsername();

         try {
            GameRepository.getInstance().deleteGame(username, this.id);
            log.info("Successfully executed deleteGameById");
            return "success";
         } catch (Exception var4) {
            log.error("Exception in deleteGameById", var4);
            return "error";
         }
      } else {
         return "login";
      }
   }

   public String switchGameStateById() {
      User user = this.getUser();
      if(user != null) {
         String username = user.getUsername();

         try {
            GameRepository.getInstance().switchPublicStateGame(username, this.id);
            log.info("Successfully executed switchPublicStateGame");
            return "success";
         } catch (Exception var4) {
            log.error("Exception in switchPublicStateGame", var4);
            return "error";
         }
      } else {
         return "login";
      }
   }

   private void fillChessGameGroupFromReader(String username, Reader reader, int maxgames) throws PGNException {
      this.chessGameGroup = PGNAdapter.getChessGameGroupFromStream(reader);
      if(this.chessGameGroup.getGameList().size() > maxgames) {
         throw new PGNException("You cannot add more than " + this.getUser().getMaxgames() + " games ");
      } else {
         Iterator var5 = this.chessGameGroup.getGameList().iterator();

         while(var5.hasNext()) {
            Game game = (Game)var5.next();
            long addGame = GameRepository.getInstance().addGame(game, username, this.tag, this.publicgame);
            game.getGameInfo().getAuxilleryProperties().setProperty("GameId", String.valueOf(addGame));
         }

         this.setChessGameGroup(this.chessGameGroup);
      }
   }

   public GameDataObj getCurrentGameDataObj() {
      return this.currentGameDataObj;
   }

   public void setCurrentGameDataObj(GameDataObj currentGameDataObj) {
      this.currentGameDataObj = currentGameDataObj;
   }

   public String getTag() {
      return this.tag;
   }

   public void setTag(String tag) {
      this.tag = tag;
   }

   public boolean isPublicgame() {
      return this.publicgame;
   }

   public void setPublicgame(boolean publicgame) {
      this.publicgame = publicgame;
   }
}
