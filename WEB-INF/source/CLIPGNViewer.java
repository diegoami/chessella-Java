import ictk.boardgame.ContinuationList;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessBoard;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.ChessResult;
import ictk.boardgame.chess.io.ChessMoveNotation;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.SAN;
import ictk.boardgame.chess.ui.cli.TxChessBoardDisplay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIPGNViewer {

   public static ChessGame game;
   public static ChessMoveNotation san = new SAN();


   public static void main(String[] args) {
      PGNReader reader = null;
      TxChessBoardDisplay display = null;
      if(args.length != 1) {
         System.err.println("usage: java CLIPGNViewer <pgn file>");
         System.exit(1);
      }

      try {
         reader = new PGNReader(new FileReader(new File(args[0])));
         game = (ChessGame)reader.readGame();
         display = new TxChessBoardDisplay((ChessBoard)game.getBoard());
         game.getBoard().addBoardListener(display);
         help();
         display.print();

         while(true) {
            menu();
            processChoice();
         }
      } catch (Exception var4) {
         System.err.println(var4);
      }
   }

   public static void help() {
      print("CLI PGN Viewer---------------");
      print("   After each board is displayed you will be presented");
      print("   with moves that continue from the position. Usually");
      print("   you will only see a main-line.  But sometimes the");
      print("   game branches in variation lines.");
      print("");
      print("   >  follows the main line.");
      print("   <  to go back one move");
      print("   #  the number indicates which line to follow.");
      print("   f  returns to the move before the current variation.");
      print("   << rewind to the beginning of the game.");
      print("   >> go to the end of the current line.");
      print("   q  to quit");
      print("   ?  to see this help file again.");
      print("");
   }

   public static void menu() {
      ContinuationList lines = null;
      Move move = null;
      move = game.getHistory().getCurrentMove();
      if(move == null) {
         lines = game.getHistory().getFirstAll();
      } else {
         lines = move.getContinuationList();
      }

      if(lines.isTerminal()) {
         System.out.println();
         System.out.print("You\'ve reached the end of this line: ");
         if(move != null) {
            ChessResult i = (ChessResult)move.getResult();
            if(i == null) {
               print("undecided");
            } else {
               print(san.resultToString(i));
            }
         } else {
            print("undecided");
         }

         System.out.println();
      } else {
         System.out.println();
         print("Lines:");

         for(int var3 = 0; var3 < lines.size(); ++var3) {
            System.out.print("   [");
            if(var3 == 0) {
               System.out.print(">");
            } else {
               System.out.print(var3);
            }

            System.out.print("] ");
            print(san.moveToString(lines.get(var3)));
         }

         System.out.println();
      }

   }

   public static void processChoice() {
      boolean goodChoice = true;
      String choice = "";
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      do {
         System.out.print("Choose (>,<,#,f,<<,>>,?,q): ");
         goodChoice = true;

         try {
            choice = in.readLine().trim();
            System.out.println();
         } catch (IOException var4) {
            System.err.println(var4);
         }

         if(">".equals(choice)) {
            game.getHistory().next();
         } else if("<".equals(choice)) {
            game.getHistory().prev();
         } else if("f".equals(choice)) {
            game.getHistory().rewindToLastFork();
         } else if("<<".equals(choice)) {
            game.getHistory().rewind();
         } else if(">>".equals(choice)) {
            game.getHistory().goToEnd();
         } else if("?".equals(choice)) {
            help();
         } else if("q".equals(choice)) {
            System.exit(1);
         } else if(Character.isDigit(choice.charAt(0))) {
            try {
               game.getHistory().next(Integer.parseInt(choice));
            } catch (Exception var5) {
               System.out.println("Error: don\'t understand that number");
               goodChoice = false;
            }
         } else {
            System.out.println("Error: don\'t understand that option.");
            goodChoice = false;
         }
      } while(!goodChoice);

   }

   public static void print(String s) {
      System.out.println(s);
   }
}
