package ictk.boardgame.chess.io;

import ictk.boardgame.Board;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.Result;
import ictk.boardgame.chess.AmbiguousChessMoveException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.Square;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.ChessMoveNotation;
import ictk.boardgame.chess.io.NAG;
import ictk.util.Log;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SAN extends ChessMoveNotation {

   public static final long DEBUG = ChessMoveNotation.DEBUG;
   protected static final Pattern defaultMovePattern = getLocalePattern(PIECE_SETS[0], FILE_SETS[0], RANK_SETS[0]);
   protected static final NAG nag = new NAG();
   protected Pattern movePattern;
   protected boolean pawnSpace;


   public SAN() {
      this(false);
   }

   public SAN(boolean pawnSpace) {
      this.pawnSpace = false;
      this.pawnSpace = pawnSpace;
   }

   public SAN(Locale loc) {
      this(loc, false);
   }

   public SAN(Locale loc, boolean pawnSpace) {
      super(loc);
      this.pawnSpace = false;
      this.pawnSpace = pawnSpace;
   }

   public void setPawnAsSpace(boolean t) {
      this.pawnSpace = t;
   }

   public boolean isPawnAsSpace() {
      return this.pawnSpace;
   }

   public boolean setLocale(Locale loc) {
      if(!super.setLocale(loc)) {
         return false;
      } else {
         Object lang = null;
         if(loc != null && !"eng".equals(loc.getISO3Language())) {
            this.movePattern = getLocalePattern(this.pieceSet, this.fileSet, this.rankSet);
         } else {
            this.movePattern = defaultMovePattern;
         }

         return true;
      }
   }

   public Move stringToMove(Board b, String s) throws AmbiguousChessMoveException, IllegalMoveException {
      if(b == null) {
         Log.debug(DEBUG, "cannot associate a move with a null ChessBoard");
         throw new IllegalArgumentException("Cannot associate a move with a null ChessBoard");
      } else if(!(b instanceof ChessBoard)) {
         Log.debug(DEBUG, "non ChessBoard send to stringToMove");
         return null;
      } else {
         ChessBoard board = (ChessBoard)b;
         ChessMove move = null;
         Matcher result = null;
         Object rest_of_string = null;
         ChessAnnotation anno = null;
         if(board == null) {
            throw new IllegalArgumentException("can\'t make move on a null board");
         } else if(s == null) {
            throw new IllegalArgumentException("can\'t make a move out of a null string");
         } else {
            byte piece = ChessPiece.NULL_PIECE;
            byte promo = ChessPiece.NULL_PIECE;
            byte orig_f = 0;
            byte orig_r = 0;
            Square orig = null;
            Square dest = null;
            result = this.movePattern.matcher(s);
            if(result.find()) {
               Log.debug(DEBUG, "regex result for: " + s, result);
               if(!result.group(1).equals("O-O-O") && !result.group(1).equals("0-0-0")) {
                  if(!result.group(1).equals("O-O") && !result.group(1).equals("0-0")) {
                     if(result.group(2) != null) {
                        piece = this.pieceToNum(result.group(2).charAt(0));
                     }

                     if(result.group(3) != null) {
                        orig_f = this.fileToNum(result.group(3).charAt(0));
                     }

                     if(result.group(4) != null) {
                        orig_r = this.rankToNum(result.group(4).charAt(0));
                     }

                     dest = board.getSquare((int)this.fileToNum(result.group(6).charAt(0)), (int)this.rankToNum(result.group(7).charAt(0)));
                     if(orig_f >= 1 && orig_r >= 1) {
                        orig = board.getSquare((int)orig_f, (int)orig_r);
                     } else {
                        if(piece == ChessPiece.NULL_PIECE) {
                           piece = 5;
                        }

                        try {
                           orig = board.getOrigin(piece, orig_f, orig_r, dest);
                        } catch (IllegalMoveException var16) {
                           var16.setMoveString(s);
                           throw var16;
                        }
                     }

                     if(result.group(8) != null) {
                        promo = this.pieceToNum(result.group(8).charAt(1));
                     }

                     if(promo == ChessPiece.NULL_PIECE) {
                        move = new ChessMove(board, orig, dest);
                     } else {
                        move = new ChessMove(board, orig, dest, ChessPiece.toChessPiece(promo));
                     }
                  } else {
                     move = new ChessMove(board, 1);
                  }
               } else {
                  move = new ChessMove(board, -1);
               }

               if(result.end() < s.length()) {
                  short[] nags = (short[])null;
                  nags = NAG.stringToNumbers(s.substring(result.end()));
                  if(nags != null) {
                     anno = new ChessAnnotation();

                     for(int i = 0; i < nags.length; ++i) {
                        anno.addNAG(nags[i]);
                     }
                  }

                  move.setAnnotation(anno);
               }
            }

            return move;
         }
      }
   }

   public Result stringToResult(String s) {
      ChessResult result = null;
      if(s.startsWith("1-0")) {
         result = new ChessResult(2);
      } else if(s.startsWith("0-1")) {
         result = new ChessResult(3);
      } else if(s.startsWith("1/2-1/2")) {
         result = new ChessResult(1);
      } else if(s.startsWith("*")) {
         result = new ChessResult(0);
      }

      return result;
   }

   public String moveToString(Move move) {
      return this.moveToString(move, false);
   }

   public String moveToString(Move move, boolean showSuffix) {
      if(!(move instanceof ChessMove)) {
         return null;
      } else if(move.isNullMove()) {
         return "--";
      } else {
         ChessMove m = (ChessMove)move;
         if(m == null) {
            throw new NullPointerException("can\'t convert null move to string");
         } else {
            Log.debug(DEBUG, "move: " + move + " showSuffix?: " + showSuffix);
            StringBuffer sb = new StringBuffer();
            char piece = this.pieceToChar(m.getChessPiece());
            int[] coords = m.getCoordinates();
            int take = m.getCasualty() == null?45:120;
            char promo = m.getPromotion() == null?32:this.pieceToChar(m.getPromotion());
            if(m.isCastleKingside()) {
               sb.append("O-O");
            } else if(m.isCastleQueenside()) {
               sb.append("O-O-O");
            } else {
               if(piece == this.pieceSet[0]) {
                  if(this.pawnSpace) {
                     sb.append(' ');
                  }
               } else {
                  sb.append(piece);
               }

               if(!m.isRankUnique()) {
                  sb.append(this.fileToChar(coords[0]));
               }

               if(!m.isFileUnique()) {
                  sb.append(this.rankToChar(coords[1]));
               }

               if(m.getCasualty() != null) {
                  if(piece == this.pieceSet[0] && m.isFileUnique() && m.isRankUnique()) {
                     sb.append(this.fileToChar(coords[0]));
                  }

                  sb.append((char)take);
               }

               sb.append(this.fileToChar(coords[2])).append(this.rankToChar(coords[3]));
               if(promo != 32) {
                  sb.append("=").append(promo);
               }

               if(m.isCheckmate()) {
                  sb.append('#');
               } else if(m.isCheck()) {
                  sb.append('+');
               }
            }

            if(showSuffix && m.getAnnotation() != null && ((ChessAnnotation)m.getAnnotation()).getSuffix() != 0) {
               sb.append(NAG.numberToString(((ChessAnnotation)m.getAnnotation()).getSuffix()));
            }

            return sb.toString();
         }
      }
   }

   public String moveToString(ChessMove m) {
      return this.moveToString(m, true);
   }

   public String resultToString(Result result) {
      ChessResult res = (ChessResult)result;
      String str = null;
      switch(res.getIndex()) {
      case 0:
         str = "*";
         break;
      case 1:
         str = "1/2-1/2";
         break;
      case 2:
         str = "1-0";
         break;
      case 3:
         str = "0-1";
      }

      return str;
   }

   protected static Pattern getLocalePattern(char[] pieceSet, char[] fileSet, char[] rankSet) {
      StringBuffer sb = new StringBuffer();
      boolean i = false;
      sb.append("[^0O]*([0O]-[0O]-[0O]").append("|[0O]-[0O]").append("|^([");

      int var5;
      for(var5 = 0; var5 < pieceSet.length; ++var5) {
         sb.append(pieceSet[var5]);
      }

      sb.append("])?([");

      for(var5 = 0; var5 < fileSet.length; ++var5) {
         sb.append(fileSet[var5]).append(Character.toUpperCase(fileSet[var5]));
      }

      sb.append("])?([");

      for(var5 = 0; var5 < rankSet.length; ++var5) {
         sb.append(rankSet[var5]);
      }

      sb.append("])?([xX])?([");

      for(var5 = 0; var5 < fileSet.length; ++var5) {
         sb.append(fileSet[var5]).append(Character.toUpperCase(fileSet[var5]));
      }

      sb.append("])([");

      for(var5 = 0; var5 < rankSet.length; ++var5) {
         sb.append(rankSet[var5]);
      }

      sb.append("])").append("(=[");

      for(var5 = 0; var5 < pieceSet.length - 1; ++var5) {
         sb.append(pieceSet[var5]);
      }

      sb.append("])?)").append("(\\+)?(\\+)?(#)?");
      return Pattern.compile(sb.toString());
   }
}
