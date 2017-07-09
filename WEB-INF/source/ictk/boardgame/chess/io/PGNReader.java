package ictk.boardgame.chess.io;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Board;
import ictk.boardgame.Game;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessPlayer;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.ChessMoveNotation;
import ictk.boardgame.chess.io.ChessReader;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.NAG;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.io.InvalidGameFormatException;
import ictk.util.Log;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.GregorianCalendar;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PGNReader extends ChessReader {

   private String firstGameLine = null;
   public static final long DEBUG = Log.GameReader;
   protected static FEN fen = new FEN();
   protected static final Pattern giPattern = Pattern.compile("\\[\\s*(\\w+)\\s+\"(.*)\"\\s*\\]");
   protected ChessMoveNotation notation = new SAN();
   protected ChessGame game;
   protected ChessGameInfo gameInfo;
   protected ChessBoard board;


   public PGNReader(Reader _ir) {
      super(_ir);
   }

   public Game readGame() throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException {
      History history = null;
      this.gameInfo = (ChessGameInfo)this.readGameInfo();
      this.board = (ChessBoard)this.readBoard();
      if(this.board == null) {
         this.board = new ChessBoard();
      }

      try {
         this.game = new ChessGame(this.gameInfo, this.board);
         history = this.readHistory();
      } catch (Exception var5) {
         var5.printStackTrace();
         this.game = new ChessGame(this.gameInfo, this.board = new ChessBoard());

         try {
            history = this.readHistory();
         } catch (Exception var4) {
            System.out.println(this.gameInfo.toString());
            var4.printStackTrace();
         }
      }

      return this.gameInfo == null && history == null?null:this.game;
   }

   public Game getGame() {
      return this.game;
   }

   public GameInfo readGameInfo() throws IOException {
      String line = null;
      Matcher result = null;
      ChessGameInfo gameInfo = new ChessGameInfo();
      boolean headerFound = false;
      boolean headerDone = false;
      String key = null;
      String value = null;
      this.firstGameLine = null;
      this.mark(100);

      while(!headerDone && (line = this.readLine()) != null) {
         if(!line.startsWith("%")) {
            if(headerFound && line.equals("")) {
               headerDone = true;
            } else {
               result = giPattern.matcher(line);
               if(result.find()) {
                  headerFound = true;
                  Log.debug(DEBUG, "GameInfo header", result);
                  key = result.group(1);
                  value = result.group(2);
                  this._setGameInfo(gameInfo, key, value);
                  this.mark(100);
               } else if(line.startsWith("1.") || line.startsWith("{")) {
                  headerDone = true;
                  this.firstGameLine = line;
                  this.reset();
               }
            }
         }
      }

      return !headerFound?null:gameInfo;
   }

   public History readHistory() throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException {
      History history = this.game.getHistory();
      boolean finished = false;
      boolean done = false;
      Object line = null;
      String tok = null;
      String tok2 = null;
      ChessMove move = null;
      ChessMove lastMove = null;
      int count = 0;
      boolean i = false;
      ChessResult res = null;
      Stack forks = new Stack();
      ChessAnnotation anno = null;
      String savedComment = null;
      boolean nag = false;
      StreamTokenizer st = new StreamTokenizer(this);
      new StringBuffer();
      Log.debug(DEBUG, "reading History");
      st.ordinaryChars(33, 255);
      st.wordChars(33, 255);
      st.ordinaryChar(46);
      st.ordinaryChar(40);
      st.ordinaryChar(41);
      st.ordinaryChar(123);
      st.ordinaryChar(125);
      st.ordinaryChar(59);
      st.ordinaryChar(42);
      st.ordinaryChar(9);
      st.eolIsSignificant(true);

      while(!finished && st.nextToken() != -1) {
         tok = st.sval;
         Log.debug(DEBUG, "token: " + tok);
         ChessMove e;
         if(tok != null) {
            short var22;
            if((var22 = NAG.stringToNumber(tok)) != 0) {
               Log.debug(DEBUG, "NAG symbol(nag): " + tok);
               if(lastMove != null) {
                  anno = (ChessAnnotation)lastMove.getAnnotation();
                  if(anno == null) {
                     anno = new ChessAnnotation();
                  }

                  anno.addNAG(var22);
                  lastMove.setAnnotation(anno);
               }
            } else if(Character.isDigit(tok.charAt(0))) {
               if((res = (ChessResult)this.notation.stringToResult(tok)) != null) {
                  finished = true;
                  Log.debug(DEBUG, "Result token: " + tok);
                  if(lastMove != null) {
                     lastMove.setResult(res);
                     Log.debug(DEBUG, "Result set(" + lastMove + "): " + res);
                     e = (ChessMove)lastMove.getPrev();
                     if(e != null) {
                        Log.debug(DEBUG, "Result set(" + lastMove + "): " + res + " prev move: " + lastMove.getPrev().dump());
                     }
                  } else {
                     Log.debug(DEBUG, "Result not set; no last move");
                  }
               }
            } else if(Character.isLetter(tok.charAt(0))) {
               try {
                  move = (ChessMove)this.notation.stringToMove(this.board, tok);
                  if(move == null) {
                     Log.debug(DEBUG, "Thought this was a move: " + tok);
                     throw new IOException("Thought this was a move: " + tok);
                  }

                  history.add(move);
                  if(savedComment != null) {
                     anno = new ChessAnnotation();
                     anno.setComment(savedComment);
                     move.setPrenotation(anno);
                     Log.debug(DEBUG, "prenotation set: " + move.getPrenotation().getComment());
                     savedComment = null;
                  }

                  lastMove = move;
                  ++count;
               } catch (OutOfTurnException var19) {
                  Log.debug(DEBUG, var19);
                  Log.debug2(DEBUG, "From Token: " + tok);
                  Log.debug2(DEBUG, "Board: \n" + this.board);
                  throw var19;
               } catch (AmbiguousMoveException var20) {
                  Log.debug(DEBUG, var20);
                  Log.debug2(DEBUG, "From Token: " + tok);
                  Log.debug2(DEBUG, "Board: \n" + this.board);
                  throw var20;
               } catch (IllegalMoveException var21) {
                  Log.debug(DEBUG, var21);
                  Log.debug2(DEBUG, "From Token: " + tok);
                  Log.debug2(DEBUG, "Board: \n" + this.board);
                  throw var21;
               }
            } else if("--".equals(tok)) {
               move = new ChessMove(this.board);
               if(move != null) {
                  history.add(move);
               }

               lastMove = move;
               ++count;
            } else {
               Log.debug(DEBUG, "No idea what this is: <" + tok + ">");
            }
         } else {
            StringBuffer sb;
            switch(st.ttype) {
            case 10:
            case 46:
            default:
               break;
            case 40:
               history.prev();
               forks.push(history.getCurrentMove());
               Log.debug(DEBUG, "starting variation from " + history.getCurrentMove());
               if(savedComment == null) {
                  break;
               }

               anno = (ChessAnnotation)lastMove.getAnnotation();
               if(anno != null && anno.getComment() != null) {
                  anno.appendComment(" " + savedComment);
               } else {
                  if(anno == null) {
                     anno = new ChessAnnotation();
                  }

                  anno.setComment(savedComment);
               }

               savedComment = null;
               break;
            case 41:
               e = (ChessMove)forks.pop();
               history.goTo(e);
               Log.debug(DEBUG, "ending variation from " + e);
               history.next();
               if(savedComment != null) {
                  anno = (ChessAnnotation)lastMove.getAnnotation();
                  if(anno != null && anno.getComment() != null) {
                     anno.appendComment(" " + savedComment);
                  } else {
                     if(anno == null) {
                        anno = new ChessAnnotation();
                     }

                     anno.setComment(savedComment);
                  }

                  savedComment = null;
               }

               lastMove = (ChessMove)history.getCurrentMove();
               break;
            case 42:
               Log.debug(DEBUG, "Result token: " + tok);
               if(lastMove != null) {
                  lastMove.setResult(new ChessResult(0));
               }

               finished = true;
               break;
            case 59:
               sb = new StringBuffer();
               st.ordinaryChar(32);

               while(st.nextToken() != 10) {
                  tok2 = st.sval;
                  if(st.ttype != 9) {
                     if(tok2 != null) {
                        sb.append(tok2);
                     } else {
                        sb.append((char)st.ttype);
                     }
                  }
               }

               st.whitespaceChars(32, 32);
               Log.debug(DEBUG, "eol comment: {" + sb.toString() + "}");
               if(lastMove != null && lastMove == history.getCurrentMove()) {
                  anno = (ChessAnnotation)lastMove.getAnnotation();
                  if(anno != null && anno.getComment() != null) {
                     anno.appendComment(" " + sb.toString());
                  } else {
                     if(anno == null) {
                        anno = new ChessAnnotation();
                     }

                     anno.setComment(sb.toString());
                     lastMove.setAnnotation(anno);
                     Log.debug(DEBUG, "eol comment for (" + lastMove + "): " + lastMove.getAnnotation().getComment());
                  }

                  anno = null;
                  break;
               }

               savedComment = sb.toString();
               break;
            case 123:
               sb = new StringBuffer();
               done = false;
               st.ordinaryChar(32);

               while(!done && st.nextToken() != -1) {
                  tok2 = st.sval;
                  switch(st.ttype) {
                  case 9:
                     break;
                  case 10:
                     sb.append(' ');
                     break;
                  case 125:
                     done = true;
                     break;
                  default:
                     if(tok2 != null) {
                        sb.append(tok2);
                     } else {
                        sb.append((char)st.ttype);
                     }
                  }
               }

               st.whitespaceChars(32, 32);
               Log.debug(DEBUG, "comment: {" + sb.toString() + "}");
               if(lastMove != null && lastMove == history.getCurrentMove()) {
                  anno = (ChessAnnotation)lastMove.getAnnotation();
                  if(anno != null && anno.getComment() != null) {
                     if(savedComment != null) {
                        lastMove.getAnnotation().appendComment(" " + savedComment);
                     }

                     savedComment = sb.toString();
                  } else {
                     if(anno == null) {
                        anno = new ChessAnnotation();
                     }

                     anno.setComment(sb.toString());
                     lastMove.setAnnotation(anno);
                     anno = null;
                  }
               } else {
                  savedComment = sb.toString();
               }
            }
         }
      }

      history.goToEnd();
      if(history.getCurrentMove() != null) {
         Log.debug(DEBUG, "final result is: " + history.getCurrentMove().getResult());
      }

      history.rewind();
      if(count == 0) {
         Log.debug(DEBUG, "finished reading History: empty");
         return null;
      } else {
         Log.debug(DEBUG, "finished reading History");
         return history;
      }
   }

   public Board readBoard() throws IOException {
      Object board = null;
      String fenStr = null;
      Board fenOut = null;
      if(this.gameInfo == null) {
         return null;
      } else {
         fenStr = this.gameInfo.get("FEN");
         if(fenStr == null) {
            return null;
         } else {
            try {
               fenOut = fen.stringToBoard(fenStr);
               return fenOut;
            } catch (Exception var5) {
               System.err.println("Could not parse ");
               System.err.println(fenStr);
               var5.printStackTrace();
               return null;
            }
         }
      }
   }

   protected void _setGameInfo(ChessGameInfo gi, String key, String value) {
      StringTokenizer st = null;
      String tok = null;
      ChessPlayer p = null;
      if("Event".equalsIgnoreCase(key)) {
         if(!value.equals("?")) {
            gi.setEvent(value);
         }
      } else if("Site".equalsIgnoreCase(key)) {
         if(!value.equals("?")) {
            gi.setSite(value);
         }
      } else if("Round".equalsIgnoreCase(key)) {
         if(!value.equals("-") && !value.equals("?")) {
            gi.setRound(value);
         }
      } else if("SubRound".equalsIgnoreCase(key)) {
         if(!value.equals("-") && !value.equals("?")) {
            gi.setSubRound(value);
         }
      } else if("White".equalsIgnoreCase(key)) {
         if(!value.equals("")) {
            p = new ChessPlayer(value);
            gi.setWhite(p);
         }
      } else if("Black".equalsIgnoreCase(key)) {
         if(!value.equals("")) {
            p = new ChessPlayer(value);
            gi.setBlack(p);
         }
      } else if("Result".equalsIgnoreCase(key)) {
         gi.setResult((ChessResult)this.notation.stringToResult(value));
      } else if("WhiteElo".equalsIgnoreCase(key)) {
         try {
            gi.setWhiteRating(Integer.parseInt(value));
         } catch (NumberFormatException var12) {
            ;
         }
      } else if("BlackElo".equalsIgnoreCase(key)) {
         try {
            gi.setBlackRating(Integer.parseInt(value));
         } catch (NumberFormatException var11) {
            ;
         }
      } else if("ECO".equalsIgnoreCase(key)) {
         gi.setECO(value);
      } else if("TimeControl".equalsIgnoreCase(key)) {
         st = new StringTokenizer(value, "+", false);

         try {
            if(st.hasMoreTokens()) {
               gi.setTimeControlInitial(Integer.parseInt(st.nextToken()));
               if(st.hasMoreTokens()) {
                  gi.setTimeControlIncrement(Integer.parseInt(st.nextToken()));
               }
            }
         } catch (NumberFormatException var10) {
            ;
         }
      } else if("Date".equalsIgnoreCase(key)) {
         if(!value.equals("????.??.??")) {
            GregorianCalendar date = new GregorianCalendar();
            st = new StringTokenizer(value, "./", false);
            tok = null;

            try {
               if(st.hasMoreTokens()) {
                  tok = st.nextToken();
                  if(!tok.startsWith("?")) {
                     date.set(1, Integer.parseInt(tok));
                     gi.setYear(Integer.parseInt(tok));
                  }

                  if(st.hasMoreTokens()) {
                     tok = st.nextToken();
                     if(!tok.startsWith("?")) {
                        date.set(2, Integer.parseInt(tok) - 1);
                        gi.setMonth(Integer.parseInt(tok));
                     }

                     if(st.hasMoreTokens()) {
                        tok = st.nextToken();
                        if(!tok.startsWith("?")) {
                           date.set(5, Integer.parseInt(tok));
                           gi.setDay(Integer.parseInt(tok));
                        }
                     }
                  }
               }
            } catch (NumberFormatException var9) {
               ;
            }

            gi.setDate(date);
         }
      } else {
         gi.add(key, value);
      }

   }
}
