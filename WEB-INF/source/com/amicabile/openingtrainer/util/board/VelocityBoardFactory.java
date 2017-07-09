package com.amicabile.openingtrainer.util.board;

import com.amicabile.openingtrainer.model.PieceEnum;
import com.amicabile.openingtrainer.model.board.VelocityBoard;
import com.amicabile.openingtrainer.pgn.PGNAdapter;
import com.amicabile.openingtrainer.util.ColorEnumFactory;
import com.amicabile.openingtrainer.util.PieceEnumFactory;
import ictk.boardgame.Game;
import ictk.boardgame.Move;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class VelocityBoardFactory {

   private static Logger log = Logger.getLogger(VelocityBoardFactory.class);
   private static VelocityBoardFactory velocityBoardFactory = new VelocityBoardFactory();


   public VelocityBoard createBoardFromFenString(String fenString) {
      return this.createBoardFromFenString(fenString, false);
   }

   public VelocityBoard createBoardFromFenString(String fenString, boolean flipped) {
      VelocityBoard velocityBoard = new VelocityBoard();
      return this.createBoardFromFenString(velocityBoard, fenString, flipped);
   }

   public VelocityBoard createBoardFromFenString(Game game, Move move) {
      return this.createBoardFromFenString(game, move, false);
   }

   public VelocityBoard createBoardFromFenString(Game game, Move move, boolean flipped) {
      String fenString = PGNAdapter.getFenStringForMove(game, move);
      return this.createBoardFromFenString(fenString, flipped);
   }

   public VelocityBoard createBoardFromFenString(Game game) {
      return this.createBoardFromFenString(game, false);
   }

   public VelocityBoard createBoardFromFenString(Game game, boolean flipped) {
      String fenString = PGNAdapter.getFenStringForMove(game);
      return this.createBoardFromFenString(fenString, flipped);
   }

   public VelocityBoard createBoardFromFenString(VelocityBoard board, String fenString, boolean flipped) {
      Scanner scanner = new Scanner(fenString);
      scanner.useDelimiter(" ");
      String piecesFenString = scanner.next();
      this.fillBoardFromFenString(board, piecesFenString, flipped);
      String moveFenString = scanner.next();
      this.fillToMoveFromFenString(board, moveFenString);
      return board;
   }

   public VelocityBoard fillToMoveFromFenString(VelocityBoard board, String colorFenString) {
      if(StringUtils.isNotEmpty(colorFenString)) {
         board.setToMove(ColorEnumFactory.createFromFenNotation(colorFenString.charAt(0)));
      }

      return board;
   }

   public VelocityBoard fillBoardFromFenString(VelocityBoard board, String piecesFenString, boolean flipped) {
      log.debug("Trying to read " + piecesFenString);
      Scanner scanner = new Scanner(piecesFenString);
      scanner.useDelimiter("/");

      for(int rowCount = 1; scanner.hasNext(); ++rowCount) {
         String row = scanner.next();
         log.debug("Scanning row " + row);
         int colCount = 1;

         for(int i = 0; i < row.length(); ++i) {
            char rowChar = row.charAt(i);
            log.debug("Processing char " + rowChar);
            if(rowChar >= 48 && rowChar <= 57) {
               int var11 = rowChar - 48;
               colCount += var11;
            } else {
               PieceEnum piece = PieceEnumFactory.createFromFenNotation(rowChar);
               if(flipped) {
                  board.setPieceAt(9 - rowCount, 9 - colCount, piece);
               } else {
                  board.setPieceAt(rowCount, colCount, piece);
               }

               ++colCount;
            }
         }
      }

      return board;
   }

   public static VelocityBoardFactory getInstance() {
      return velocityBoardFactory;
   }
}
