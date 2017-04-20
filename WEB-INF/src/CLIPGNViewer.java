/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.chess.io.ChessMoveNotation;
/*     */ import ictk.boardgame.chess.io.PGNReader;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ import ictk.boardgame.chess.ui.cli.CLIChessBoardDisplay;
/*     */ import ictk.boardgame.chess.ui.cli.TxChessBoardDisplay;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CLIPGNViewer
/*     */ {
/*     */   public static ChessGame game;
/*  38 */   public static ChessMoveNotation san = new SAN();
/*     */   
/*     */   public static void main(String[] args) {
/*  41 */     PGNReader reader = null;
/*  42 */     CLIChessBoardDisplay display = null;
/*     */     
/*     */ 
/*  45 */     if (args.length != 1) {
/*  46 */       System.err.println("usage: java CLIPGNViewer <pgn file>");
/*  47 */       System.exit(1);
/*     */     }
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  53 */       reader = new PGNReader(new FileReader(new File(args[0])));
/*     */       
/*     */ 
/*  56 */       game = (ChessGame)reader.readGame();
/*     */       
/*     */ 
/*  59 */       display = new TxChessBoardDisplay((ChessBoard)game.getBoard());
/*     */       
/*  61 */       game.getBoard().addBoardListener(display);
/*     */       
/*  63 */       help();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */       display.print();
/*     */       
/*     */       for (;;)
/*     */       {
/*  74 */         menu();
/*  75 */         processChoice();
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  80 */       System.err.println(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void help()
/*     */   {
/*  86 */     print("CLI PGN Viewer---------------");
/*  87 */     print("   After each board is displayed you will be presented");
/*  88 */     print("   with moves that continue from the position. Usually");
/*  89 */     print("   you will only see a main-line.  But sometimes the");
/*  90 */     print("   game branches in variation lines.");
/*  91 */     print("");
/*  92 */     print("   >  follows the main line.");
/*  93 */     print("   <  to go back one move");
/*  94 */     print("   #  the number indicates which line to follow.");
/*  95 */     print("   f  returns to the move before the current variation.");
/*  96 */     print("   << rewind to the beginning of the game.");
/*  97 */     print("   >> go to the end of the current line.");
/*  98 */     print("   q  to quit");
/*  99 */     print("   ?  to see this help file again.");
/* 100 */     print("");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void menu()
/*     */   {
/* 107 */     ContinuationList lines = null;
/* 108 */     Move move = null;
/*     */     
/* 110 */     move = game.getHistory().getCurrentMove();
/* 111 */     if (move == null) {
/* 112 */       lines = game.getHistory().getFirstAll();
/*     */     } else {
/* 114 */       lines = move.getContinuationList();
/*     */     }
/* 116 */     if (lines.isTerminal()) {
/* 117 */       System.out.println();
/* 118 */       System.out.print("You've reached the end of this line: ");
/* 119 */       if (move != null) {
/* 120 */         ChessResult result = (ChessResult)move.getResult();
/* 121 */         if (result == null) {
/* 122 */           print("undecided");
/*     */         } else {
/* 124 */           print(san.resultToString(result));
/*     */         }
/*     */       } else {
/* 127 */         print("undecided");
/*     */       }
/* 129 */       System.out.println();
/*     */     }
/*     */     else {
/* 132 */       System.out.println();
/* 133 */       print("Lines:");
/* 134 */       for (int i = 0; i < lines.size(); i++) {
/* 135 */         System.out.print("   [");
/* 136 */         if (i == 0) {
/* 137 */           System.out.print(">");
/*     */         } else
/* 139 */           System.out.print(i);
/* 140 */         System.out.print("] ");
/*     */         
/* 142 */         print(san.moveToString(lines.get(i)));
/*     */       }
/* 144 */       System.out.println();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void processChoice()
/*     */   {
/* 153 */     boolean goodChoice = true;
/* 154 */     String choice = "";
/* 155 */     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
/*     */     do
/*     */     {
/* 158 */       System.out.print("Choose (>,<,#,f,<<,>>,?,q): ");
/* 159 */       goodChoice = true;
/*     */       try
/*     */       {
/* 162 */         choice = in.readLine().trim();
/* 163 */         System.out.println();
/*     */       }
/*     */       catch (IOException e) {
/* 166 */         System.err.println(e);
/*     */       }
/*     */       
/* 169 */       if (">".equals(choice)) {
/* 170 */         game.getHistory().next();
/*     */       }
/* 172 */       else if ("<".equals(choice)) {
/* 173 */         game.getHistory().prev();
/*     */       }
/* 175 */       else if ("f".equals(choice)) {
/* 176 */         game.getHistory().rewindToLastFork();
/*     */       }
/* 178 */       else if ("<<".equals(choice)) {
/* 179 */         game.getHistory().rewind();
/*     */       }
/* 181 */       else if (">>".equals(choice)) {
/* 182 */         game.getHistory().goToEnd();
/*     */       }
/* 184 */       else if ("?".equals(choice)) {
/* 185 */         help();
/*     */       }
/* 187 */       else if ("q".equals(choice)) {
/* 188 */         System.exit(1);
/*     */       }
/* 190 */       else if (Character.isDigit(choice.charAt(0))) {
/*     */         try {
/* 192 */           game.getHistory().next(Integer.parseInt(choice));
/*     */         }
/*     */         catch (Exception e) {
/* 195 */           System.out.println("Error: don't understand that number");
/* 196 */           goodChoice = false;
/*     */         }
/*     */       }
/*     */       else {
/* 200 */         System.out.println("Error: don't understand that option.");
/* 201 */         goodChoice = false;
/*     */       }
/* 157 */     } while (!
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */       goodChoice);
/*     */   }
/*     */   
/*     */   public static void print(String s)
/*     */   {
/* 209 */     System.out.println(s);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\CLIPGNViewer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */