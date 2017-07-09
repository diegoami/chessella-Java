package ictk.boardgame.chess.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Game;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.ChessReader;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.PGNReaderTest;
import ictk.boardgame.chess.io.PGNWriter;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.io.InvalidGameFormatException;
import ictk.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import junit.framework.TestCase;

public class PGNWriterTest extends TestCase {

   public String dataDir = "./";
   String pgn_nonvariation = "test_nonvariation.pgn";
   String pgn_variation = "test_variation.pgn";
   String pgn_annotation = "test_annotation.pgn";
   String pgn_debug = "test_debug.pgn";
   PGNWriter writer;
   SAN san;
   ChessBoard board;
   ChessResult res;
   ChessMove move;
   ChessMove move2;
   ChessReader in;
   Game game;
   Game game2;
   StringWriter sw;
   StringReader sr;
   PGNReader spgnin;
   ChessAnnotation anno;
   List list;


   public PGNWriterTest(String name) {
      super(name);
      if(System.getProperty("ictk.boardgame.chess.io.dataDir") != null) {
         this.dataDir = System.getProperty("ictk.boardgame.chess.io.dataDir");
      }

   }

   public void setUp() {
      this.san = new SAN();
   }

   public void tearDown() {
      this.spgnin = null;
      this.sw = null;
      this.sr = null;
      this.writer = null;
      this.san = null;
      this.board = null;
      this.res = null;
      this.move = null;
      this.game = null;
      this.game2 = null;
      this.in = null;
      this.anno = null;
      Log.removeMask(SAN.DEBUG);
      Log.removeMask(ChessBoard.DEBUG);
      Log.removeMask(ChessGameInfo.DEBUG);
      Log.removeMask(PGNWriter.DEBUG);
   }

   public void testWrongDisambiguationBug() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      try {
         this.list = PGNReaderTest.loadGames(this.dataDir + this.pgn_variation, false, 9);
         this.game = (ChessGame)this.list.get(9);
         this.writer = new PGNWriter(this.sw = new StringWriter());
         this.writer.writeGame(this.game);
         String pgn = this.sw.toString();
         assertTrue(pgn.indexOf("5xd4") < 0);
      } finally {
         Log.removeMask(SAN.DEBUG);
         Log.removeMask(PGNReader.DEBUG);
         Log.removeMask(ChessGameInfo.DEBUG);
         Log.removeMask(PGNWriter.DEBUG);
      }

   }

   public void testVariationNesting() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      try {
         this.game = new ChessGame();
         this.board = (ChessBoard)((ChessGame)this.game).getBoard();
         this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
         this.game.getHistory().add(this.move);
         this.move = (ChessMove)this.san.stringToMove(this.board, "e5");
         this.game.getHistory().add(this.move);
         this.game.getHistory().prev();
         this.move = (ChessMove)this.san.stringToMove(this.board, "c5");
         this.game.getHistory().add(this.move);
         this.game.getHistory().prev();
         this.move = (ChessMove)this.san.stringToMove(this.board, "e6");
         this.game.getHistory().add(this.move);
         this.writer = new PGNWriter(this.sw = new StringWriter());
         this.writer.writeGame(this.game);
         BufferedReader in = new BufferedReader(new StringReader(this.sw.toString()));
         boolean found = false;

         String line;
         for(line = null; !found && (line = in.readLine()) != null; found = line.startsWith("1.e4")) {
            ;
         }

         assertTrue(found);
         int openVarIndex = line.indexOf(40);
         assertTrue(openVarIndex != -1);
         int closeVarIndex = line.indexOf(41);
         assertTrue(closeVarIndex != -1);
         int nextOpenVarIndex = line.indexOf(40, openVarIndex + 1);
         assertTrue(nextOpenVarIndex != -1);
         assertTrue(closeVarIndex < nextOpenVarIndex);
      } finally {
         Log.removeMask(SAN.DEBUG);
         Log.removeMask(PGNReader.DEBUG);
         Log.removeMask(ChessGameInfo.DEBUG);
         Log.removeMask(PGNWriter.DEBUG);
      }
   }

   public void testBulkNonVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      try {
         boolean count = false;
         this.in = new PGNReader(new FileReader(new File(this.dataDir + this.pgn_nonvariation)));

         while((this.game = this.in.readGame()) != null) {
            this.writer = new PGNWriter(this.sw = new StringWriter());
            this.writer.writeGame(this.game);
            this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
            this.game2 = this.spgnin.readGame();
            assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
            assertTrue(((ChessGameInfo)this.game.getGameInfo()).equals(this.game2.getGameInfo()));
         }
      } finally {
         Log.removeMask(SAN.DEBUG);
         Log.removeMask(PGNReader.DEBUG);
         Log.removeMask(ChessGameInfo.DEBUG);
         Log.removeMask(PGNWriter.DEBUG);
      }

   }

   public void testBulkVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      int count = 0;

      try {
         for(this.in = new PGNReader(new FileReader(new File(this.dataDir + this.pgn_variation))); (this.game = this.in.readGame()) != null; ++count) {
            this.writer = new PGNWriter(this.sw = new StringWriter());
            this.writer.writeGame(this.game);
            this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
            this.game2 = this.spgnin.readGame();
            assertTrue(((ChessGameInfo)this.game.getGameInfo()).equals(this.game2.getGameInfo()));
            assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
            assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
            assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
         }
      } catch (Exception var6) {
         if(Log.isDebug(PGNWriter.DEBUG)) {
            System.out.println("Game: " + count);
            (new PGNWriter(System.err)).writeGame(this.game);
         }

         throw var6;
      } finally {
         Log.removeMask(SAN.DEBUG);
         Log.removeMask(PGNReader.DEBUG);
         Log.removeMask(ChessGameInfo.DEBUG);
         Log.removeMask(PGNWriter.DEBUG);
         Log.removeMask(History.DEBUG);
      }

   }

   public void testZeroGameInfoZeroHistory() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.game = new ChessGame();
      this.board = (ChessBoard)((ChessGame)this.game).getBoard();
      this.writer = new PGNWriter(this.sw = new StringWriter());
      this.writer.writeGame(this.game);
      Log.removeMask(PGNWriter.DEBUG);
   }

   public void testPrenotation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      try {
         this.game = new ChessGame();
         this.board = (ChessBoard)((ChessGame)this.game).getBoard();
         this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
         this.game.getHistory().add(this.move);
         this.move.setPrenotation(new ChessAnnotation("before1"));
         this.writer = new PGNWriter(this.sw = new StringWriter());
         this.writer.writeGame(this.game);
         this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
         this.game2 = this.spgnin.readGame();
         assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
         assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
         assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
         this.game.getHistory().next();
         this.game2.getHistory().next();
         assertTrue(this.game.getHistory().getCurrentMove().equals(this.game2.getHistory().getCurrentMove()));
         this.move = (ChessMove)this.game.getHistory().getCurrentMove();
         this.move2 = (ChessMove)this.game2.getHistory().getCurrentMove();
         assertTrue(this.move.getPrenotation().equals(this.move2.getPrenotation()));
      } catch (Exception var5) {
         throw var5;
      } finally {
         Log.removeMask(PGNWriter.DEBUG);
         Log.removeMask(History.DEBUG);
      }

   }

   public void testAnnotationThenPrenotation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.game = new ChessGame();
      this.board = (ChessBoard)((ChessGame)this.game).getBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.game.getHistory().add(this.move);
      this.move.setAnnotation(new ChessAnnotation("after1"));
      this.move = (ChessMove)this.san.stringToMove(this.board, "e5");
      this.game.getHistory().add(this.move);
      this.move.setPrenotation(new ChessAnnotation("before2"));
      this.writer = new PGNWriter(this.sw = new StringWriter());
      this.writer.writeGame(this.game);
      this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
      this.game2 = this.spgnin.readGame();
      assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
      this.game.getHistory().next();
      this.game2.getHistory().next();
      assertTrue(this.game.getHistory().getCurrentMove().equals(this.game2.getHistory().getCurrentMove()));
      this.move = (ChessMove)this.game.getHistory().getCurrentMove();
      this.move2 = (ChessMove)this.game2.getHistory().getCurrentMove();
      assertTrue(this.move.getAnnotation().equals(this.move2.getAnnotation()));
      this.game.getHistory().next();
      this.game2.getHistory().next();
      this.move = (ChessMove)this.game.getHistory().getCurrentMove();
      this.move2 = (ChessMove)this.game2.getHistory().getCurrentMove();
      assertTrue(this.move.getPrenotation().equals(this.move2.getPrenotation()));
      Log.removeMask(PGNWriter.DEBUG);
   }

   public void testPrenotationForVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.game = new ChessGame();
      this.board = (ChessBoard)((ChessGame)this.game).getBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.game.getHistory().add(this.move);
      this.move = (ChessMove)this.san.stringToMove(this.board, "e5");
      this.game.getHistory().add(this.move);
      this.game.getHistory().prev();
      this.move = (ChessMove)this.san.stringToMove(this.board, "c5");
      this.game.getHistory().add(this.move);
      this.move.setPrenotation(new ChessAnnotation("Sicilian"));
      this.writer = new PGNWriter(this.sw = new StringWriter());
      this.writer.writeGame(this.game);
      this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
      this.game2 = this.spgnin.readGame();
      assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
      this.game.getHistory().next();
      this.game2.getHistory().next();
      this.game.getHistory().next(1);
      this.game2.getHistory().next(1);
      this.move = (ChessMove)this.game.getHistory().getCurrentMove();
      this.move2 = (ChessMove)this.game2.getHistory().getCurrentMove();
      assertTrue(this.move.getPrenotation().equals(this.move2.getPrenotation()));
      Log.removeMask(PGNWriter.DEBUG);
   }

   public void testNAGSymetry() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.game = new ChessGame();
      this.board = (ChessBoard)((ChessGame)this.game).getBoard();
      this.move = (ChessMove)this.san.stringToMove(this.board, "e4");
      this.anno = new ChessAnnotation();
      this.anno.addNAG(1);
      this.anno.addNAG(123);
      this.anno.addNAG(145);
      this.move.setAnnotation(this.anno);
      this.game.getHistory().add(this.move);
      this.move = (ChessMove)this.san.stringToMove(this.board, "e5");
      this.game.getHistory().add(this.move);
      this.writer = new PGNWriter(this.sw = new StringWriter());
      this.writer.writeGame(this.game);
      if(Log.isDebug(PGNWriter.DEBUG)) {
         this.writer = new PGNWriter(System.out);
         this.writer.writeGame(this.game);
      }

      this.spgnin = new PGNReader(new StringReader(this.sw.toString()));
      this.game2 = this.spgnin.readGame();
      assertTrue(this.game.getHistory().equals(this.game2.getHistory()));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), false));
      assertTrue(this.game.getHistory().deepEquals(this.game2.getHistory(), true));
      Log.removeMask(PGNWriter.DEBUG);
   }
}
