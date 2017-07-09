package ictk.boardgame.chess.ui.cli;

import ictk.boardgame.Board;
import ictk.boardgame.BoardListener;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessPiece;
import ictk.boardgame.chess.Square;
import ictk.boardgame.chess.io.ChessMoveNotation;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.chess.ui.cli.CLIChessBoardDisplay;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class TxChessBoardDisplay implements CLIChessBoardDisplay, BoardListener {

   protected PrintWriter out;
   protected ChessBoard board;
   protected ChessMoveNotation notation;
   protected boolean whiteOnBottom;
   protected boolean sideToMoveOnBottom;
   protected int coordMask;
   protected boolean compact;
   protected boolean inverse;
   protected boolean lowercaseCoords;
   protected boolean waitingForTraversalEnd;


   public TxChessBoardDisplay(ChessBoard board, OutputStream out) {
      this(board, new PrintWriter(out));
   }

   public TxChessBoardDisplay(ChessBoard board, Writer out) {
      this(board, new PrintWriter(out, true));
   }

   public TxChessBoardDisplay(ChessBoard board, PrintWriter out) {
      this.out = new PrintWriter(System.out, true);
      this.notation = new SAN();
      this.whiteOnBottom = true;
      this.sideToMoveOnBottom = false;
      this.coordMask = 12;
      this.waitingForTraversalEnd = false;
      this.board = board;
      this.out = out;
   }

   public TxChessBoardDisplay(ChessBoard board) {
      this.out = new PrintWriter(System.out, true);
      this.notation = new SAN();
      this.whiteOnBottom = true;
      this.sideToMoveOnBottom = false;
      this.coordMask = 12;
      this.waitingForTraversalEnd = false;
      this.board = board;
   }

   public void boardUpdate(Board b, int event) {
      if(event == 3) {
         this.waitingForTraversalEnd = true;
      }

      if(event == 4) {
         this.waitingForTraversalEnd = false;
      }

      if(!this.waitingForTraversalEnd) {
         this.update();
      }

   }

   public void setCompact(boolean t) {
      this.compact = t;
   }

   public boolean isCompact() {
      return this.compact;
   }

   public void setBoard(Board board) {
      this.board = (ChessBoard)board;
   }

   public Board getBoard() {
      return this.board;
   }

   public void update() {
      this.print();
   }

   public void setWhiteOnBottom(boolean t) {
      this.whiteOnBottom = t;
   }

   public boolean isWhiteOnBottom() {
      return this.whiteOnBottom;
   }

   public void setSideToMoveOnBottom(boolean t) {
      this.sideToMoveOnBottom = t;
   }

   public boolean getSideToMoveOnBottom() {
      return this.sideToMoveOnBottom;
   }

   public void setVisibleCoordinates(int mask) {
      this.coordMask = mask;
   }

   public int getVisibleCoordinates() {
      return this.coordMask;
   }

   public void setLowerCaseCoordinates(boolean t) {
      this.lowercaseCoords = t;
   }

   public boolean isLowerCaseCoordinates() {
      return this.lowercaseCoords;
   }

   public void setInverse(boolean t) {
      this.inverse = t;
   }

   public boolean isInverse() {
      return this.inverse;
   }

   public void setWriter(PrintWriter out) {
      this.out = out;
   }

   public Writer getWriter() {
      return this.out;
   }

   public void print() {
      this.print(this.board);
   }

   public void print(Board board) {
      ChessBoard b = (ChessBoard)board;
      Square sq = null;
      StringBuffer last_line = null;
      boolean c = true;
      boolean top = (this.coordMask & 1) == 1;
      boolean left = (this.coordMask & 8) == 8;
      boolean right = (this.coordMask & 2) == 2;
      boolean bottom = (this.coordMask & 4) == 4;
      boolean flipped = !this.whiteOnBottom && !this.sideToMoveOnBottom || b.isBlackMove() && this.sideToMoveOnBottom;
      int blackSquare = this.inverse?35:32;
      int whiteSquare = this.inverse?32:35;
      int f;
      if(top) {
         if(left) {
            this.out.print("  ");
            if(!this.compact) {
               this.out.print("  ");
            }
         }

         byte r = 1;
         f = flipped?8:1;

         while(true) {
            if(flipped) {
               if(f < 1) {
                  break;
               }
            } else if(f > 8) {
               break;
            }

            sq = b.getSquare(f, (int)r);
            if(this.lowercaseCoords) {
               this.out.print(Character.toLowerCase(this.notation.fileToChar(sq.getFile())));
            } else {
               this.out.print(Character.toUpperCase(this.notation.fileToChar(sq.getFile())));
            }

            if(!this.compact && f != (flipped?1:8)) {
               this.out.print(" ");
            }

            if(flipped) {
               --f;
            } else {
               ++f;
            }
         }

         this.out.println();
         this.out.println();
      }

      if(bottom) {
         last_line = new StringBuffer(this.compact?10:20);
         last_line.append("\n");
         if(left) {
            last_line.append("  ");
            if(!this.compact) {
               last_line.append("  ");
            }
         }
      }

      int var17 = flipped?1:8;
      f = flipped?8:1;

      while(true) {
         if(flipped) {
            if(var17 > 8) {
               break;
            }
         } else if(var17 < 1) {
            break;
         }

         sq = b.getSquare(f, var17);
         if(left) {
            if(this.lowercaseCoords) {
               this.out.print(Character.toLowerCase(this.notation.rankToChar(sq.getRank())));
            } else {
               this.out.print(Character.toUpperCase(this.notation.rankToChar(sq.getRank())));
            }

            this.out.print(" ");
            if((top || bottom) && !this.compact) {
               this.out.print(" ");
            }

            if(!this.compact) {
               this.out.print(" ");
            }
         }

         f = flipped?8:1;

         while(true) {
            if(flipped) {
               if(f < 1) {
                  break;
               }
            } else if(f > 8) {
               break;
            }

            sq = b.getSquare(f, var17);
            if(sq.isOccupied()) {
               char var16 = this.notation.pieceToChar((ChessPiece)sq.getPiece());
               if(((ChessPiece)sq.getPiece()).isBlack()) {
                  var16 = Character.toLowerCase(var16);
               }

               this.out.print(var16);
            } else if(sq.isBlack()) {
               this.out.print((char)blackSquare);
            } else {
               this.out.print((char)whiteSquare);
            }

            if(!this.compact && f != (flipped?1:8)) {
               this.out.print(" ");
            }

            if(bottom && var17 == (flipped?1:8)) {
               if(this.lowercaseCoords) {
                  last_line.append(Character.toLowerCase(this.notation.fileToChar(sq.getFile())));
               } else {
                  last_line.append(Character.toUpperCase(this.notation.fileToChar(sq.getFile())));
               }

               if(!this.compact && f != (flipped?1:8)) {
                  last_line.append(" ");
               }
            }

            if(flipped) {
               --f;
            } else {
               ++f;
            }
         }

         if(right) {
            this.out.print(" ");
            if((top || bottom) && !this.compact) {
               this.out.print(" ");
            }

            if(!this.compact) {
               this.out.print(" ");
            }

            if(this.lowercaseCoords) {
               this.out.print(Character.toLowerCase(this.notation.rankToChar(sq.getRank())));
            } else {
               this.out.print(Character.toUpperCase(this.notation.rankToChar(sq.getRank())));
            }
         }

         if(flipped) {
            f = 8;
         } else {
            f = 1;
         }

         this.out.println();
         if(flipped) {
            ++var17;
         } else {
            --var17;
         }
      }

      if(bottom) {
         this.out.println(last_line.toString());
      }

   }
}
