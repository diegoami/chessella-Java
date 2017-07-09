package ictk.boardgame.chess.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.ContinuationList;
import ictk.boardgame.Game;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Result;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.ChessReader;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.io.InvalidGameFormatException;
import ictk.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;

public class PGNReaderTest extends TestCase {

   public String dataDir = "./";
   String pgn_nonvariation = "test_nonvariation.pgn";
   String pgn_variation = "test_variation.pgn";
   String pgn_annotation = "test_annotation.pgn";
   String pgn_bad = "test_bad.pgn";
   String pgn_debug = "test_debug.pgn";
   SAN san;
   ChessBoard board;
   ChessResult res;
   ChessMove move;
   ChessReader in;
   Game game;
   List games;
   ChessAnnotation anno;


   public PGNReaderTest(String name) {
      super(name);
      if(System.getProperty("ictk.boardgame.chess.io.dataDir") != null) {
         this.dataDir = System.getProperty("ictk.boardgame.chess.io.dataDir");
      }

   }

   public void setUp() {
      this.san = new SAN();
   }

   public void tearDown() {
      this.san = null;
      this.board = null;
      this.res = null;
      this.move = null;
      this.game = null;
      this.in = null;
      this.games = null;
      this.anno = null;
      Log.removeMask(SAN.DEBUG);
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testDebug() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_debug, false, -1);
   }

   public void testBulkNonVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_nonvariation, false, -1);
      assertTrue(this.games.size() > 0);
   }

   public void testBulkVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_variation, false, -1);
      assertTrue(this.games.size() > 0);
   }

   public void testDuplicateContinuation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_variation, false, 7);
      assertTrue(this.games.size() > 0);
      this.game = (ChessGame)this.games.get(7);
      this.game.getHistory().fastforward(50);
      ContinuationList cont = this.game.getHistory().getCurrentMove().getContinuationList();
      assertTrue(cont.size() == 2);
   }

   public void testMainLineResultOnVariationBugTest() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_variation, false, 8);
      assertTrue(this.games.size() > 0);
      this.game = (ChessGame)this.games.get(8);
      ChessResult res1 = (ChessResult)this.game.getResult();
      ChessResult res2 = (ChessResult)this.game.getHistory().getFinalMove(true).getResult();
      assertTrue(res1 != null);
      assertTrue(res2 != null);
      assertTrue(res1.equals(res2));
      assertTrue(res1.equals(new ChessResult(1)));
   }

   public void testBulkAnnotation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, -1);
      assertTrue(this.games.size() > 0);
   }

   public void testAnnotationCommentAfterMove() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 0);
      this.game = (Game)this.games.get(0);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno.getComment().equals("Best by test"));
   }

   public void testNonVariationResultSwitchBug() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_nonvariation, false, 4);
      this.game = (ChessGame)this.games.get(4);
      this.game.getHistory().rewind();
      this.res = (ChessResult)this.game.getResult();
      this.game.getHistory().goToEnd();
      Result res2 = this.game.getHistory().getCurrentMove().getResult();
      assertTrue(this.res.equals(res2));
      assertTrue(this.res.isWhiteWin());
   }

   public void testAnnotationExclaimation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 1);
      this.game = (Game)this.games.get(1);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next();
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno.getSuffix() == 1);
   }

   public void testAnnotationCommentBeforeGame() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 2);
      this.game = (Game)this.games.get(2);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getPrenotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("Comment Before Game"));
   }

   public void testAnnotationComment2ndMoveWithCommentAfter1st() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 3);
      this.game = (Game)this.games.get(3);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("after1"));
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getPrenotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("before2"));
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("after2"));
   }

   public void testAnnotation2CommentsAfter1stOneBefore2nd() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 4);
      this.game = (Game)this.games.get(4);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("after1 after1a"));
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getPrenotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("before2"));
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("after2"));
   }

   public void testAnnotationEndLineComment() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 6);
      this.game = (Game)this.games.get(6);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("Best by test"));
   }

   public void testAnnotation2EndLineCommentsInARow() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 7);
      this.game = (Game)this.games.get(7);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("Best by test so says Fischer"));
   }

   public void testAnnotationHeadingVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 8);
      this.game = (Game)this.games.get(8);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next(1);
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getPrenotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("Sicilian"));
   }

   public void testAnnotationHeadingVariationEOL() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 9);
      this.game = (Game)this.games.get(9);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next(1);
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getPrenotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment().equals("Sicilian"));
   }

   public void testAnnotationBeforeVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 10);
      this.game = (Game)this.games.get(10);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment() != null);
      assertTrue(this.anno.getComment().equals("post"));
   }

   public void testAnnotationAfterNAGBeforeVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 11);
      this.game = (Game)this.games.get(11);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment() != null);
      assertTrue(this.anno.getComment().equals("post"));
   }

   public void testDoubleAnnotationBeforeVariation() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 12);
      this.game = (Game)this.games.get(12);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment() != null);
      assertTrue(this.anno.getComment().equals("post repost"));
   }

   public void testDoubleAnnotationBeforeVariationEnd() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 13);
      this.game = (Game)this.games.get(13);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      history.next(1);
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getComment() != null);
      assertTrue(this.anno.getComment().equals("post repost"));
   }

   public void testNAGOver8() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 14);
      this.game = (Game)this.games.get(14);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getNAG(0) == 9);
   }

   public void testNAGSymbol() throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      this.games = loadGames(this.dataDir + this.pgn_annotation, false, 15);
      this.game = (Game)this.games.get(15);
      assertTrue(this.game != null);
      History history = this.game.getHistory();
      history.rewind();
      history.next();
      assertTrue(history.getCurrentMove() != null);
      this.anno = (ChessAnnotation)history.getCurrentMove().getAnnotation();
      assertTrue(this.anno != null);
      assertTrue(this.anno.getNAG(0) == 145);
   }

   public void testBadPGNs() throws FileNotFoundException, InvalidGameFormatException, Exception {
      boolean count = false;
      this.in = new PGNReader(new FileReader(new File(this.dataDir + this.pgn_bad)));

      try {
         this.game = this.in.readGame();
         fail("read in bad game but no error?");
      } catch (IOException var8) {
         this.game = this.in.getGame();
         assertTrue(28 == this.game.getHistory().size());
      } catch (IllegalMoveException var9) {
         fail("wrong error for game 1: " + var9);
      } catch (AmbiguousMoveException var10) {
         fail("wrong error for game 1: " + var10);
      } finally {
         Log.removeMask(SAN.DEBUG);
         Log.removeMask(PGNReader.DEBUG);
      }

   }

   protected static List loadGames(String file, boolean debug, int gameToDebug) throws FileNotFoundException, IOException, InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, Exception {
      Game game = null;
      PGNReader in = null;
      LinkedList list = new LinkedList();
      if(debug && gameToDebug < 0) {
         Log.addMask(SAN.DEBUG);
         Log.addMask(PGNReader.DEBUG);
      }

      try {
         int e = 0;
         Log.debug(PGNReader.DEBUG, "Reading file: " + file);
         in = new PGNReader(new FileReader(new File(file)));
         if(debug && gameToDebug == e) {
            System.out.println("turing logs on");
            Log.addMask(SAN.DEBUG);
            Log.addMask(PGNReader.DEBUG);
         }

         for(; (game = in.readGame()) != null; game = null) {
            list.add(game);
            if(debug && gameToDebug == e) {
               Log.removeMask(SAN.DEBUG);
               Log.removeMask(PGNReader.DEBUG);
            }

            ++e;
            if(debug && gameToDebug == e) {
               System.out.println("turing logs on");
               Log.addMask(SAN.DEBUG);
               Log.addMask(PGNReader.DEBUG);
            }
         }
      } catch (Exception var10) {
         throw var10;
      } finally {
         if(debug) {
            Log.removeMask(SAN.DEBUG);
            Log.removeMask(PGNReader.DEBUG);
         }

      }

      return list;
   }
}
