package com.amicabile.openingtrainer.pgn;

import com.amicabile.openingtrainer.pgn.ChessGameGroup;
import com.amicabile.openingtrainer.pgn.PGNException;
import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Game;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.PGNWriter;
import ictk.boardgame.io.InvalidGameFormatException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class PGNAdapter {

   private static transient Logger log = Logger.getLogger(PGNAdapter.class);
   private static int MAX_GAMES = 5000;


   public static String getFenStringForMove(Game game, Move move) {
      FEN fen = new FEN();
      game.getHistory().rewind();
      if(move != null) {
         game.getHistory().goTo(move);
      }

      String fenString = fen.boardToString(((ChessGame)game).getBoard());
      return fenString;
   }

   public static String getFenStringForMove(Game game) {
      return getFenStringForMove(game, (Move)null);
   }

   public static String getLetterFromPiece(ChessPiece piece) {
      return piece.isBishop()?"b":(piece.isQueen()?"q":(piece.isKnight()?"n":(piece.isRook()?"r":(piece.isKing()?"k":(piece.isPawn()?"p":"")))));
   }

   public static ChessGameGroup getChessGameGroupFromFile(String gameFile) throws PGNException {
      log.info("Reading File : " + gameFile);
      FileReader freader = null;

      try {
         freader = new FileReader(gameFile);
         ChessGameGroup e = getChessGameGroupFromStream(freader);
         freader.close();
         log.info("Finished reading file " + gameFile);
         return e;
      } catch (Exception var3) {
         throw new PGNException(var3);
      }
   }

   public static ChessGameGroup getChessGameGroupFromStream(Reader freader) {
      ChessGameGroup gameGroup = new ChessGameGroup();
      PGNReader reader = new PGNReader(freader);
      ChessGame game = null;
      int gameCounter = 0;

      do {
         ++gameCounter;

         try {
            log.debug("Processing game : " + gameCounter);
            game = (ChessGame)reader.readGame();
            if(game != null) {
               log.debug("Successfully read game");
               log.debug("--Game Info--");
               log.debug(game.getGameInfo());
               log.debug("--Game History--");
               log.debug(game.getHistory());
            }

            if(game != null) {
               gameGroup.addChessGame(game);
               log.debug("Successfully read game " + game.getGameInfo().get("Board"));
            }
         } catch (Exception var6) {
            log.warn("COULD NOT READ GAME " + gameCounter + " in file ", var6);
            log.warn("Adding empty game ");
            gameGroup.addChessGame(new ChessGame());
         }
      } while(game != null && gameCounter < MAX_GAMES);

      return gameGroup;
   }

   public static ChessGame getGameFromStream(Reader freader) throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException {
      PGNReader reader = new PGNReader(freader);
      ChessGame game = (ChessGame)reader.readGame();
      if(game != null) {
         log.debug("Successfully read game");
         log.debug("--Game Info--");
         log.debug(game.getGameInfo());
         log.debug("--Game History--");
         log.debug(game.getHistory());
      }

      return game;
   }

   public static void writeGroupToFile(Writer fWriter, ChessGameGroup gameGroup) throws PGNException {
      try {
         PGNWriter e = new PGNWriter(fWriter);
         int counter = 0;
         List chessGameList = gameGroup.getGameList();
         log.info("Got sorted ordered games");
         Iterator var6 = chessGameList.iterator();

         while(var6.hasNext()) {
            ChessGame game = (ChessGame)var6.next();

            try {
               ++counter;
               e.writeGame(game);
            } catch (Exception var8) {
               log.error("Could not write game " + counter);
            }
         }

      } catch (Exception var9) {
         var9.printStackTrace();
         throw new PGNException(var9);
      }
   }

   public static void writeGroupToFile(String gameFile, ChessGameGroup gameGroup) throws PGNException {
      log.info("Now writing game group to " + gameFile);
      BufferedWriter bw = null;
      FileWriter fw = null;
      Object fwriter = null;

      try {
         bw = new BufferedWriter(fw = new FileWriter(gameFile));
         writeGroupToFile((Writer)bw, gameGroup);
      } catch (Exception var13) {
         var13.printStackTrace();
         throw new PGNException(var13);
      } finally {
         try {
            if(bw != null) {
               bw.close();
            }

            if(fw != null) {
               fw.close();
            }
         } catch (IOException var12) {
            var12.printStackTrace();
         }

      }

   }
}
