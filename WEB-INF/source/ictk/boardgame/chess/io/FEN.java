package ictk.boardgame.chess.io;

import ictk.boardgame.Board;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.io.ChessBoardNotation;
import ictk.boardgame.chess.io.SAN;
import ictk.util.Log;
import java.io.IOException;
import java.util.Locale;

public class FEN implements ChessBoardNotation {

   public static long DEBUG = Log.BoardNotation;
   protected static SAN san = new SAN();
   Locale locale;


   public void setLocale(Locale loc) {}

   public Locale getLocale() {
      return this.locale;
   }

   public Board stringToBoard(String str) throws IOException {
      ChessBoard board = null;
      char[][] matrix = new char[8][8];
      char[] strArray = (char[])null;
      int rank = 7;
      int file = 0;
      boolean isBlackMove = false;
      boolean canWhiteCastleKingside = false;
      boolean canWhiteCastleQueenside = false;
      boolean canBlackCastleKingside = false;
      boolean canBlackCastleQueenside = false;
      char enpassantFile = 45;
      boolean plyCount = false;
      boolean moveNumber = true;
      str.trim();
      strArray = str.toCharArray();

      int i;
      for(i = 0; i < strArray.length; ++i) {
         if(strArray[i] == 47) {
            --rank;
            file = 0;
         } else if(Character.isDigit(strArray[i])) {
            file += Character.digit(strArray[i], 10);
         } else {
            if(!this.isPieceChar(strArray[i])) {
               if(strArray[i] != 32) {
                  throw new IOException("Unsupported character found in FEN at:" + i);
               }
               break;
            }

            matrix[file++][rank] = strArray[i];
         }
      }

      ++i;
      if(strArray[i] == 119) {
         isBlackMove = false;
      } else {
         if(strArray[i] != 98) {
            throw new IOException("Unsupported character found in FEN at:" + i + "(" + strArray[i] + ") expecting who to move");
         }

         isBlackMove = true;
      }

      ++i;
      ++i;

      for(; i < strArray.length && strArray[i] != 32; ++i) {
         switch(strArray[i]) {
         case 75:
            canWhiteCastleKingside = true;
            break;
         case 81:
            canWhiteCastleQueenside = true;
            break;
         case 107:
            canBlackCastleKingside = true;
            break;
         case 113:
            canBlackCastleQueenside = true;
         }
      }

      ++i;
      if(strArray[i] != 45) {
         enpassantFile = strArray[i];
         ++i;
      }

      ++i;
      ++i;
      int j;
      String strPly = str.substring(i, j = str.indexOf(" ", i));

      int var22;
      try {
         var22 = Integer.parseInt(strPly);
      } catch (NumberFormatException var21) {
         throw new IOException("Unsupported character found in FEN at:" + i + " (" + strPly + ") expecting ply count");
      }

      i = j + 1;
      String strMoves = str.substring(i, str.length());

      int var23;
      try {
         var23 = Integer.parseInt(strMoves);
      } catch (NumberFormatException var20) {
         throw new IOException("Unsupported character found in FEN at:" + i + " (" + strMoves + ") expecting move number");
      }

      board = new ChessBoard(matrix, isBlackMove, canWhiteCastleKingside, canWhiteCastleQueenside, canBlackCastleKingside, canBlackCastleQueenside, enpassantFile, var22, var23);
      return board;
   }

   public String boardToString(Board b) {
      ChessBoard board = (ChessBoard)b;
      char[][] ray = board.toCharArray();
      StringBuffer buff = new StringBuffer();
      boolean count = false;

      for(int castle = 7; castle >= 0; --castle) {
         if(castle != 7) {
            buff.append("/");
         }

         int var8 = 0;

         for(int f = 0; f < 8; ++f) {
            if(Character.isLetter(ray[f][castle])) {
               if(var8 > 0) {
                  buff.append(var8);
                  var8 = 0;
               }

               buff.append(ray[f][castle]);
            } else {
               ++var8;
            }
         }

         if(var8 > 0) {
            buff.append(var8);
         }
      }

      buff.append(" ");
      buff.append((char)(board.isBlackMove()?'b':'w'));
      buff.append(" ");
      boolean var9 = false;
      if(board.isWhiteCastleableKingside()) {
         var9 = true;
         buff.append("K");
      }

      if(board.isWhiteCastleableQueenside()) {
         var9 = true;
         buff.append("Q");
      }

      if(board.isBlackCastleableKingside()) {
         var9 = true;
         buff.append("k");
      }

      if(board.isBlackCastleableQueenside()) {
         var9 = true;
         buff.append("q");
      }

      if(!var9) {
         buff.append("-");
      }

      buff.append(" ");
      if(board.getEnPassantFile() != 0) {
         buff.append(san.fileToChar(board.getEnPassantFile()));
         if(board.isBlackMove()) {
            buff.append("3");
         } else {
            buff.append("6");
         }
      } else {
         buff.append("-");
      }

      buff.append(" ");
      buff.append(board.get50MoveRulePlyCount());
      buff.append(" ");
      buff.append(board.getCurrentMoveNumber());
      return buff.toString();
   }

   protected boolean isPieceChar(char c) {
      switch(c) {
      case 66:
      case 75:
      case 78:
      case 80:
      case 81:
      case 82:
      case 98:
      case 107:
      case 110:
      case 112:
      case 113:
      case 114:
         return true;
      default:
         return false;
      }
   }
}
