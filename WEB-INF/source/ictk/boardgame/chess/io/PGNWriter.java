package ictk.boardgame.chess.io;

import ictk.boardgame.Board;
import ictk.boardgame.ContinuationList;
import ictk.boardgame.Game;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessGameInfo;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessAnnotation;
import ictk.boardgame.chess.io.ChessMoveNotation;
import ictk.boardgame.chess.io.ChessWriter;
import ictk.boardgame.chess.io.FEN;
import ictk.boardgame.chess.io.NAG;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.io.MoveNotation;
import ictk.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class PGNWriter extends ChessWriter {

   public static final long DEBUG = Log.GameWriter;
   public static final int NO_GLYPH = 0;
   public static final int SYMBOLIC_AND_NUMERIC_GLYPH = 1;
   public static final int NUMERIC_GLYPH = 2;
   public static final int SYMBOLIC_ONLY_GLYPH = 3;
   public static final int SUFFIX_ONLY_GLYPH = 4;
   protected static final int _NOTHING = 0;
   protected static final int _MOVE_W = 1;
   protected static final int _MOVE_B = 2;
   protected static final int _VARIATION_BEGIN = 3;
   protected static final int _VARIATION_END = 4;
   protected static final int _COMMENT = 5;
   protected static final int _GLYPH = 6;
   protected static final int _RESULT = 7;
   protected static final int _MISC = 8;
   protected StringBuffer buffer = null;
   protected int colWidth = 80;
   protected int glyphStyle = 1;
   protected boolean exportComments = true;
   protected boolean exportVariations = true;
   protected boolean indentComments = false;
   protected boolean indentVariations = false;
   protected boolean oneMovePerLine = false;
   private boolean needNumber = false;
   private int variationsDeep = 0;
   private String indentStr = "    ";
   protected ChessMoveNotation notation = new SAN();


   public PGNWriter(OutputStream _out) {
      super(_out);
   }

   public PGNWriter(Writer _out) {
      super(_out);
   }

   public void setMoveNotation(MoveNotation notation) {
      this.notation = (ChessMoveNotation)notation;
   }

   public MoveNotation getMoveNotation() {
      return this.notation;
   }

   public void setColumnWidth(int col) {
      this.colWidth = col;
   }

   public int getColumnWidth() {
      return this.colWidth;
   }

   public void setExportComments(boolean t) {
      this.exportComments = t;
   }

   public boolean isExportComments() {
      return this.exportComments;
   }

   public void setExportVariations(boolean t) {
      this.exportVariations = t;
   }

   public boolean isExportVariations() {
      return this.exportVariations;
   }

   public void setIndentComments(boolean t) {
      this.indentComments = t;
   }

   public boolean isIndentComments() {
      return this.indentComments;
   }

   public void setIndentVariations(boolean t) {
      this.indentVariations = t;
   }

   public boolean isIndentVariations() {
      return this.indentVariations;
   }

   public void setAnnotationGlyphStyle(int style) {
      this.glyphStyle = style;
   }

   public int getAnnotationGlyphStyle() {
      return this.glyphStyle;
   }

   public void writeGame(Game game) throws IOException {
      ChessGame g = (ChessGame)game;
      if(g == null) {
         Log.debug(DEBUG, "can\'t write a null game");
         throw new NullPointerException("can\'t write null game");
      } else {
         Log.debug(DEBUG, "Writing game");
         g.getHistory().rewind();
         this.writeGameInfo(g.getGameInfo());
         if(!g.getBoard().isInitialPositionDefault()) {
            this.writeBoard(g.getBoard());
         }

         this.println();
         this.writeHistory(g.getHistory());
      }
   }

   public void writeGameInfo(GameInfo gameinfo) throws IOException {
      ChessGameInfo gi = (ChessGameInfo)gameinfo;
      StringBuffer sb = new StringBuffer();
      String result = null;
      String black = null;
      String white = null;
      String round = null;
      String date = null;
      String site = null;
      String event = null;
      if(gi == null) {
         Log.debug(DEBUG, "gameInfo is null, so writing default header");
      }

      if(gi != null) {
         event = gi.getEvent();
         site = gi.getSite();
         date = gi.getDateString();
         round = gi.getRound();
         if(gi.getWhite() != null) {
            white = gi.getWhite().getName();
         }

         if(gi.getBlack() != null) {
            black = gi.getBlack().getName();
         }

         result = this.notation.resultToString(gi.getResult());
      }

      if(event == null) {
         event = "?";
      }

      if(site == null) {
         site = "?";
      }

      if(date == null) {
         date = "????.??.??";
      }

      if(white == null) {
         white = "?";
      }

      if(black == null) {
         black = "?";
      }

      if(round == null) {
         round = "-";
      }

      if(result == null) {
         result = "*";
      }

      sb.append("[Event \"").append(event).append("\"]\n");
      sb.append("[Site \"").append(site).append("\"]\n");
      sb.append("[Date \"").append(date).append("\"]\n");
      sb.append("[Round \"").append(round).append("\"]\n");
      if(gi != null && gi.getSubRound() != null && !gi.getSubRound().equals("")) {
         sb.append("[SubRound \"").append(gi.getSubRound()).append("\"]\n");
      }

      sb.append("[White \"").append(white).append("\"]\n");
      sb.append("[Black \"").append(black).append("\"]\n");
      sb.append("[Result \"").append(result).append("\"]\n");
      if(gi != null) {
         if(gi.getWhiteRating() > 0) {
            sb.append("[WhiteElo \"").append(gi.getWhiteRating()).append("\"]\n");
         }

         if(gi.getBlackRating() > 0) {
            sb.append("[BlackElo \"").append(gi.getBlackRating()).append("\"]\n");
         }

         if(gi.getECO() != null) {
            sb.append("[ECO \"").append(gi.getECO()).append("\"]\n");
         }

         if(gi.getTimeControlInitial() > 0) {
            sb.append("[TimeControl \"").append(gi.getTimeControlInitial()).append("+").append(gi.getTimeControlIncrement()).append("\"]\n");
         }

         Enumeration keys = gi.props.propertyNames();
         String key = null;
         String value = null;

         while(keys.hasMoreElements()) {
            key = (String)keys.nextElement();
            value = gi.props.getProperty(key);
            sb.append("[").append(key).append(" \"").append(value).append("\"]\n");
         }
      }

      Log.debug(DEBUG, "writing gameInfo block to stream");
      this.print(sb);
   }

   public synchronized void writeHistory(History history) throws IOException {
      Object currMove = null;
      Object lastMove = null;
      Move walker = null;
      ChessResult result = null;
      int num = history.getInitialMoveNumber();
      if(history == null) {
         Log.debug(DEBUG, "can\'t write a null history");
         throw new NullPointerException("can\'t write null history");
      } else {
         this.buffer = new StringBuffer(this.colWidth);
         Log.debug(DEBUG, "walking the History move tree");
         this.walkMoveTreeBreadthFirst(history.getFirstAll(), num);
         walker = history.getFinalMove(true);
         if(walker != null) {
            result = (ChessResult)walker.getResult();
         }

         if(result != null && !result.isUndecided()) {
            this.formatOutput(this.notation.resultToString(result), 7);
         } else {
            this.formatOutput("*", 7);
         }

         if(this.buffer.length() != 0) {
            this.print(this.buffer.toString());
         }

         this.println();
      }
   }

   protected void walkMoveTreeBreadthFirst(ContinuationList cont, int num) {
      ChessMove m = null;
      boolean isBlackMove = true;
      short[] nags = (short[])null;
      StringBuffer sbtmp = new StringBuffer(12);
      ChessAnnotation anno = null;
      Log.debug(DEBUG, "continuations(" + cont.size() + ")" + (cont.isTerminal()?" is ":" is not ") + "terminal");
      if(cont != null && !cont.isTerminal()) {
         ChessMove currMove = null;
         if(cont.get(0) != null) {
            currMove = (ChessMove)cont.get(0);
         } else if(cont.get(1) != null) {
            currMove = (ChessMove)cont.get(1);
         } else {
            assert false : "ContinuationList contained null mainline, no variations and was not isTerminal().";
         }

         if(currMove.isNullMove()) {
            isBlackMove = !((ChessMove)currMove.getPrev()).isBlackMove();
         } else {
            isBlackMove = currMove.isBlackMove();
         }

         for(int i = 0; (i == 0 || this.exportVariations) && i < cont.size(); ++i) {
            m = (ChessMove)cont.get(i);
            if(i > 0) {
               ++this.variationsDeep;
               this.formatOutput("(", 3);
            }

            if(this.exportComments && m.getPrenotation() != null && m.getPrenotation().getComment() != null) {
               this.formatOutput(" {" + m.getPrenotation().getComment() + "} ", 5);
            }

            if(!isBlackMove) {
               sbtmp.append(num).append(".");
            }

            if((i > 0 || this.needNumber) && isBlackMove) {
               sbtmp.append(num).append("...");
            }

            this.needNumber = false;
            sbtmp.append(this.notation.moveToString(m));
            anno = (ChessAnnotation)m.getAnnotation();
            if(anno != null && this.glyphStyle != 0 && this.glyphStyle != 2 && anno.getSuffix() != 0) {
               sbtmp.append(NAG.numberToString(anno.getSuffix()));
            }

            this.formatOutput(sbtmp.toString(), isBlackMove?2:1);
            sbtmp.delete(0, sbtmp.length());
            if(anno != null && this.glyphStyle != 0 && this.glyphStyle != 4 && (nags = anno.getNAGs()) != null) {
               for(int j = NAG.isSuffix(nags[0]) && this.glyphStyle != 2?1:0; j < nags.length; ++j) {
                  this.needNumber = true;
                  switch(this.glyphStyle) {
                  case 1:
                     this.formatOutput(NAG.numberToString(nags[j]), 6);
                     break;
                  case 2:
                     this.formatOutput(NAG.numberToString(nags[j], true), 6);
                     break;
                  case 3:
                     if(NAG.isSymbol(nags[j])) {
                        this.formatOutput(NAG.numberToString(nags[j]), 6);
                     }
                  }
               }
            }

            if(this.exportComments && anno != null && anno.getComment() != null) {
               this.formatOutput("{" + anno.getComment() + "}", 5);
               this.needNumber = true;
            }

            if(m != null && this.exportVariations && i > 0) {
               Log.debug(DEBUG, m + " descending variation");
               this.walkMoveTreeBreadthFirst(m.getContinuationList(), num + (isBlackMove?1:0));
               this.formatOutput(")", 4);
               --this.variationsDeep;
               this.needNumber = true;
            }
         }

         m = (ChessMove)cont.get(0);
         if(m != null) {
            Log.debug(DEBUG, m + " descending mainline");
            this.walkMoveTreeBreadthFirst(m.getContinuationList(), num + (isBlackMove?1:0));
         }
      }

   }

   protected void formatOutput(String str, int type) {
      boolean spacer = this.buffer.length() != 0;
      int length = this.buffer.length() + str.length() + (spacer?1:0);
      Log.debug(DEBUG, "[" + length + "/" + this.colWidth + "] " + "buffer(" + this.buffer.length() + ") + \"" + str + "\"(" + str.length() + ")");
      if(this.indentVariations && type == 3 && this.variationsDeep > 0) {
         this.println(this.buffer.toString());
         this.buffer.delete(0, this.buffer.length());
         if(this.variationsDeep == 1) {
            this.buffer.append(this.indentStr);
         } else {
            this.buffer.append(this.indentStr).append(this.indentStr);
         }

         this.buffer.append(str);
      } else if(this.indentVariations && type == 4 && this.variationsDeep > 0) {
         if(this.buffer.length() == 0) {
            if(this.variationsDeep == 1) {
               this.print(this.indentStr);
               this.println(str);
            } else {
               this.print(this.indentStr);
               this.print(this.indentStr);
               this.println(str);
               if(this.variationsDeep == 2) {
                  this.buffer.append(this.indentStr);
               } else {
                  this.buffer.append(this.indentStr).append(this.indentStr);
               }
            }
         } else {
            this.print(this.buffer.toString());
            this.print(" ");
            this.println(str);
            this.buffer.delete(0, this.buffer.length());
         }
      } else if(this.indentComments && type == 5 && this.variationsDeep == 0) {
         this.println(this.buffer.toString());
         this.buffer.delete(0, this.buffer.length());
         if(this.indentStr.length() + str.length() > this.colWidth) {
            this.formatLongComment(str);
         } else {
            this.println(this.indentStr + str);
         }
      } else if(length <= this.colWidth) {
         if(spacer) {
            this.buffer.append(" ");
         }

         this.buffer.append(str);
         Log.debug(DEBUG, "appending: " + str);
      } else if(type != 5) {
         this.println(this.buffer.toString());
         Log.debug(DEBUG, "writing: " + this.buffer.toString());
         this.buffer.delete(0, this.buffer.length());
         if(this.indentVariations) {
            if(this.variationsDeep > 0) {
               this.buffer.append(this.indentStr);
            }

            if(this.variationsDeep > 1) {
               this.buffer.append(this.indentStr);
            }
         }

         this.buffer.append(str);
      } else {
         this.formatLongComment(str);
      }

   }

   protected void formatLongComment(String str) {
      int len = this.buffer.length();
      String tok = null;
      StringTokenizer st = new StringTokenizer(str, " ", false);

      while(st.hasMoreTokens()) {
         tok = st.nextToken();
         if(len + 1 + tok.length() <= this.colWidth) {
            if(this.buffer.length() == 0) {
               if(this.indentComments && this.variationsDeep == 0) {
                  this.buffer.append(this.indentStr);
               }
            } else {
               this.buffer.append(" ");
            }

            this.buffer.append(tok);
            len = this.buffer.length();
         } else {
            Log.debug(DEBUG, "writing: " + this.buffer.toString());
            this.println(this.buffer.toString());
            this.buffer.delete(0, this.buffer.length());
            if(this.indentComments) {
               if(this.variationsDeep == 0) {
                  this.buffer.append(this.indentStr);
               } else if(this.indentVariations && this.variationsDeep > 0) {
                  if(this.variationsDeep == 1) {
                     this.buffer.append(this.indentStr);
                  } else {
                     this.buffer.append(this.indentStr).append(this.indentStr);
                  }
               }
            }

            this.buffer.append(tok);
            len = this.buffer.length();
         }
      }

      if(this.indentComments && this.variationsDeep == 0 && this.buffer.length() > 0) {
         this.println(this.buffer.toString());
         this.buffer.delete(0, this.buffer.length());
      }

   }

   public void writeBoard(Board board) throws IOException {
      StringBuffer buff = new StringBuffer();
      buff.append("[FEN \"");
      buff.append((new FEN()).boardToString(board));
      buff.append("\"]");
      this.println(buff.toString());
   }
}
