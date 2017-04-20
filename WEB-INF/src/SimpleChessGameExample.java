/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessGameInfo;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessPlayer;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.chess.io.ChessAnnotation;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ import ictk.boardgame.io.MoveNotation;
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
/*     */ 
/*     */ public class SimpleChessGameExample
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  36 */     ChessGame game = null;
/*  37 */     Board board = null;
/*  38 */     History history = null;
/*  39 */     Move move = null;
/*  40 */     Move e4 = null;
/*  41 */     MoveNotation san = new SAN();
/*  42 */     PGNWriter writer = null;
/*  43 */     ChessGameInfo gi = null;
/*  44 */     ChessPlayer player = null;
/*     */     try
/*     */     {
/*  47 */       System.out.println("SimpleChessGameExample: demonstrates how to enter the moves of a game with the API");
/*     */       
/*  49 */       System.out.println();
/*     */       
/*  51 */       game = new ChessGame();
/*     */       
/*  53 */       gi = new ChessGameInfo();
/*     */       
/*  55 */       player = new ChessPlayer();
/*  56 */       player.setFirstName("Bobby");
/*  57 */       player.setLastName("Fischer");
/*  58 */       gi.setWhite(player);
/*     */       
/*     */ 
/*  61 */       player = new ChessPlayer();
/*  62 */       player.setFirstName("Boris");
/*  63 */       player.setLastName("Spasky");
/*  64 */       gi.setBlack(player);
/*     */       
/*  66 */       game.setGameInfo(gi);
/*     */       
/*     */ 
/*     */ 
/*  70 */       history = game.getHistory();
/*  71 */       board = game.getBoard();
/*     */       
/*     */ 
/*  74 */       move = new ChessMove((ChessBoard)board, 5, 2, 5, 4);
/*  75 */       history.add(move);
/*     */       
/*     */ 
/*  78 */       move = (ChessMove)san.stringToMove(board, "e5");
/*  79 */       history.add(move);
/*     */       
/*     */ 
/*  82 */       history.add(san.stringToMove(board, "Nc3"));
/*     */       
/*     */ 
/*     */ 
/*  86 */       history.prev();
/*  87 */       history.prev();
/*  88 */       e4 = history.getCurrentMove();
/*     */       
/*  90 */       move = san.stringToMove(board, "c5");
/*  91 */       move.setAnnotation(new ChessAnnotation("The Sicilian"));
/*  92 */       history.add(move);
/*     */       
/*     */ 
/*  95 */       history.add(san.stringToMove(board, "Nc3"));
/*     */       
/*     */ 
/*  98 */       history.rewindToLastFork();
/*  99 */       if (history.getCurrentMove() != e4) {
/* 100 */         System.err.println("Ooops, did something wrong.");
/*     */       }
/*     */       
/* 103 */       game.setResult(new ChessResult(1));
/*     */       
/* 105 */       writer = new PGNWriter(System.out);
/* 106 */       writer.writeGame(game);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 110 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\SimpleChessGameExample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */