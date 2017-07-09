package ictk.boardgame.chess.ui.cli;

import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessMove;
import ictk.boardgame.chess.ui.cli.TxChessBoardDisplay;
import ictk.util.Log;
import java.io.StringWriter;
import junit.framework.TestCase;

public class TxChessBoardDisplayTest extends TestCase {

   TxChessBoardDisplay display;
   ChessBoard board;
   ChessMove move;
   StringWriter swriter;
   String str;
   String str2;


   public TxChessBoardDisplayTest(String name) {
      super(name);
   }

   public void setUp() {
      this.board = new ChessBoard();
      this.display = new TxChessBoardDisplay(this.board, this.swriter = new StringWriter());
   }

   public void tearDown() {
      this.board = null;
      this.move = null;
      this.swriter = null;
      this.str = null;
      this.str2 = null;
      this.display = null;
      Log.removeMask(ChessBoard.DEBUG);
   }

   public void testDefaultInitialPosition() {
      try {
         this.str2 = "8   r n b q k b n r\n7   p p p p p p p p\n6   #   #   #   #  \n5     #   #   #   #\n4   #   #   #   #  \n3     #   #   #   #\n2   P P P P P P P P\n1   R N B Q K B N R\n\n    A B C D E F G H\n";
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testDefaultInitialPositionFlipped() {
      try {
         this.str2 = "1   R N B K Q B N R\n2   P P P P P P P P\n3   #   #   #   #  \n4     #   #   #   #\n5   #   #   #   #  \n6     #   #   #   #\n7   p p p p p p p p\n8   r n b k q b n r\n\n    H G F E D C B A\n";
         this.display.setWhiteOnBottom(false);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testNoCoordinates() {
      try {
         this.str2 = "r n b q k b n r\np p p p p p p p\n#   #   #   #  \n  #   #   #   #\n#   #   #   #  \n  #   #   #   #\nP P P P P P P P\nR N B Q K B N R\n";
         this.display.setVisibleCoordinates(0);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testTopCoordinates() {
      try {
         this.str2 = "A B C D E F G H\n\nr n b q k b n r\np p p p p p p p\n#   #   #   #  \n  #   #   #   #\n#   #   #   #  \n  #   #   #   #\nP P P P P P P P\nR N B Q K B N R\n";
         this.display.setVisibleCoordinates(1);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testRightCoordinates() {
      try {
         this.str2 = "r n b q k b n r  8\np p p p p p p p  7\n#   #   #   #    6\n  #   #   #   #  5\n#   #   #   #    4\n  #   #   #   #  3\nP P P P P P P P  2\nR N B Q K B N R  1\n";
         this.display.setVisibleCoordinates(2);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testBottomCoordinates() {
      try {
         this.str2 = "r n b q k b n r\np p p p p p p p\n#   #   #   #  \n  #   #   #   #\n#   #   #   #  \n  #   #   #   #\nP P P P P P P P\nR N B Q K B N R\n\nA B C D E F G H\n";
         this.display.setVisibleCoordinates(4);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testLeftCoordinates() {
      try {
         this.str2 = "8  r n b q k b n r\n7  p p p p p p p p\n6  #   #   #   #  \n5    #   #   #   #\n4  #   #   #   #  \n3    #   #   #   #\n2  P P P P P P P P\n1  R N B Q K B N R\n";
         this.display.setVisibleCoordinates(8);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testAllCoordinates() {
      try {
         this.str2 = "    A B C D E F G H\n\n8   r n b q k b n r   8\n7   p p p p p p p p   7\n6   #   #   #   #     6\n5     #   #   #   #   5\n4   #   #   #   #     4\n3     #   #   #   #   3\n2   P P P P P P P P   2\n1   R N B Q K B N R   1\n\n    A B C D E F G H\n";
         this.display.setVisibleCoordinates(1 | 2 | 4 | 8);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testCompact() {
      try {
         this.str2 = "8 rnbqkbnr\n7 pppppppp\n6 # # # # \n5  # # # #\n4 # # # # \n3  # # # #\n2 PPPPPPPP\n1 RNBQKBNR\n\n  ABCDEFGH\n";
         this.display.setCompact(true);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testCompactAllCoordinates() {
      try {
         this.str2 = "  ABCDEFGH\n\n8 rnbqkbnr 8\n7 pppppppp 7\n6 # # # #  6\n5  # # # # 5\n4 # # # #  4\n3  # # # # 3\n2 PPPPPPPP 2\n1 RNBQKBNR 1\n\n  ABCDEFGH\n";
         this.display.setVisibleCoordinates(1 | 2 | 4 | 8);
         this.display.setCompact(true);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testLowerCaseCoords() {
      try {
         this.str2 = "8   r n b q k b n r\n7   p p p p p p p p\n6   #   #   #   #  \n5     #   #   #   #\n4   #   #   #   #  \n3     #   #   #   #\n2   P P P P P P P P\n1   R N B Q K B N R\n\n    a b c d e f g h\n";
         this.display.setLowerCaseCoordinates(true);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }

   public void testInverse() {
      try {
         this.str2 = "8   r n b q k b n r\n7   p p p p p p p p\n6     #   #   #   #\n5   #   #   #   #  \n4     #   #   #   #\n3   #   #   #   #  \n2   P P P P P P P P\n1   R N B Q K B N R\n\n    A B C D E F G H\n";
         this.display.setInverse(true);
         this.display.print();
         this.str = this.swriter.toString();
         assertTrue(this.str.equals(this.str2));
      } finally {
         Log.removeMask(ChessBoard.DEBUG);
      }

   }
}
