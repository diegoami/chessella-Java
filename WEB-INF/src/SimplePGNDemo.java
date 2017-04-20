/*     */ import ictk.boardgame.ContinuationList;
/*     */ import ictk.boardgame.History;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessPiece;
/*     */ import ictk.boardgame.chess.io.ChessAnnotation;
/*     */ import ictk.boardgame.chess.io.ChessBoardNotation;
/*     */ import ictk.boardgame.chess.io.ChessMoveNotation;
/*     */ import ictk.boardgame.chess.io.FEN;
/*     */ import ictk.boardgame.chess.io.PGNReader;
/*     */ import ictk.boardgame.chess.io.PGNWriter;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
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
/*     */ public class SimplePGNDemo
/*     */ {
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  57 */     PGNReader reader = null;
/*  58 */     PGNWriter writer = null;
/*  59 */     ChessGame game = null;
/*     */     
/*     */ 
/*  62 */     if (args.length != 1) {
/*  63 */       System.err.println("usage: java SimplePGNReader <pgn file>");
/*  64 */       System.exit(1);
/*     */     }
/*     */     
/*     */ 
/*  68 */     reader = new PGNReader(new FileReader(new File(args[0])));
/*     */     do
/*     */     {
/*     */       try
/*     */       {
/*  73 */         game = (ChessGame)reader.readGame();
/*     */         
/*  75 */         if (game != null) {
/*  76 */           displayPGN(game);
/*  77 */           displayLastPosition(game);
/*  78 */           displayStats(game);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/*  82 */         e.printStackTrace();
/*     */       }
/*  84 */     } while (game != null);
/*     */   }
/*     */   
/*     */   public static void displayStats(ChessGame game)
/*     */   {
/*  89 */     ChessMoveNotation san = new SAN();
/*  90 */     ChessAnnotation anno = null;
/*  91 */     ChessMove move = null;
/*  92 */     ContinuationList cont = null;
/*  93 */     History history = game.getHistory();
/*  94 */     int count = 0;int comments = 0;int precomments = 0;int variations = 0;int goodmoves = 0;int greatmoves = 0;int badmoves = 0;int blunders = 0;int captures = 0;int promotions = 0;int checks = 0;int pmoves_w = 0;int pmoves_b = 0;int nmoves_w = 0;int nmoves_b = 0;int bmoves_w = 0;int bmoves_b = 0;int rmoves_w = 0;int rmoves_b = 0;int qmoves_w = 0;int qmoves_b = 0;int kmoves_w = 0;int kmoves_b = 0;
/*     */     
/*  96 */     history.rewind();
/*  97 */     while (history.hasNext()) {
/*  98 */       move = (ChessMove)history.next();
/*  99 */       count++;
/*     */       
/* 101 */       if (move.isCheck())
/* 102 */         checks++;
/* 103 */       if (move.getCasualty() != null) {
/* 104 */         captures++;
/*     */       }
/* 106 */       switch (move.getChessPiece().getIndex() % ChessPiece.BLACK_OFFSET) {
/*     */       case 5: 
/* 108 */         if (move.isBlackMove()) {
/* 109 */           pmoves_b++;
/*     */         } else
/* 111 */           pmoves_w++;
/* 112 */         if (move.getPromotion() != null)
/* 113 */           promotions++;
/* 114 */         break;
/*     */       case 4: 
/* 116 */         if (move.isBlackMove()) {
/* 117 */           nmoves_b++;
/*     */         } else
/* 119 */           nmoves_w++;
/* 120 */         break;
/*     */       case 3: 
/* 122 */         if (move.isBlackMove()) {
/* 123 */           bmoves_b++;
/*     */         } else
/* 125 */           bmoves_w++;
/* 126 */         break;
/*     */       case 2: 
/* 128 */         if (move.isBlackMove()) {
/* 129 */           rmoves_b++;
/*     */         } else
/* 131 */           rmoves_w++;
/* 132 */         break;
/*     */       case 1: 
/* 134 */         if (move.isBlackMove()) {
/* 135 */           qmoves_b++;
/*     */         } else
/* 137 */           qmoves_w++;
/* 138 */         break;
/*     */       case 0: 
/* 140 */         if (move.isBlackMove()) {
/* 141 */           kmoves_b++;
/*     */         } else {
/* 143 */           kmoves_w++;
/*     */         }
/*     */         break;
/*     */       }
/* 147 */       anno = (ChessAnnotation)move.getPrenotation();
/* 148 */       if ((anno != null) && 
/* 149 */         (anno.getComment() != null)) {
/* 150 */         precomments++;
/*     */       }
/*     */       
/* 153 */       anno = (ChessAnnotation)move.getAnnotation();
/* 154 */       if (anno != null) {
/* 155 */         if (anno.getComment() != null)
/* 156 */           comments++;
/* 157 */         switch (anno.getSuffix()) {
/*     */         case 1: 
/* 159 */           goodmoves++;
/* 160 */           break;
/*     */         case 2: 
/* 162 */           badmoves++;
/* 163 */           break;
/*     */         case 3: 
/* 165 */           greatmoves++;
/* 166 */           break;
/*     */         case 4: 
/* 168 */           blunders++;
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 173 */       cont = move.getContinuationList();
/* 174 */       if (cont.sizeOfVariations() > 0) {
/* 175 */         variations += cont.sizeOfVariations();
/*     */       }
/*     */     }
/* 178 */     println("Game Statistics------------");
/* 179 */     println("for the main-line of the game");
/* 180 */     println("          plies: " + count);
/* 181 */     println("           pawn: w" + pmoves_w + "\tb" + pmoves_b);
/* 182 */     println("         knight: w" + nmoves_w + "\tb" + nmoves_b);
/* 183 */     println("         bishop: w" + bmoves_w + "\tb" + bmoves_b);
/* 184 */     println("           rook: w" + rmoves_w + "\tb" + rmoves_b);
/* 185 */     println("          queen: w" + qmoves_w + "\tb" + qmoves_b);
/* 186 */     println("           king: w" + kmoves_w + "\tb" + kmoves_b);
/* 187 */     println("         checks: " + checks);
/* 188 */     println("       captures: " + captures);
/* 189 */     println("     promotions: " + promotions);
/* 190 */     println("  good moves(!): " + goodmoves);
/* 191 */     println("great moves(!!): " + greatmoves);
/* 192 */     println("   bad moves(?): " + badmoves);
/* 193 */     println("   blunders(??): " + blunders);
/* 194 */     println("   pre-comments: " + precomments);
/* 195 */     println("       comments: " + comments);
/* 196 */     println("     variations: " + variations);
/*     */     
/* 198 */     if (game.getResult() == null) {
/* 199 */       println("         result: undecided");
/*     */     } else
/* 201 */       println("         result: " + san.resultToString(game.getResult()));
/* 202 */     System.out.println();
/*     */   }
/*     */   
/*     */   public static void println(String str)
/*     */   {
/* 207 */     System.out.println(str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void displayLastPosition(ChessGame game)
/*     */   {
/* 214 */     ChessBoardNotation fen = new FEN();
/*     */     
/* 216 */     game.getHistory().goToEnd();
/* 217 */     println("Last board position--------");
/* 218 */     println("FEN: " + fen.boardToString(game.getBoard()));
/* 219 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void displayPGN(ChessGame game)
/*     */   {
/* 227 */     PGNWriter writer = null;
/*     */     try {
/* 229 */       writer = new PGNWriter(System.out);
/*     */       
/*     */ 
/* 232 */       writer.setIndentVariations(true);
/* 233 */       writer.setIndentComments(true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 238 */       println("PGN------------------------");
/* 239 */       writer.writeGame(game);
/* 240 */       System.out.println();
/*     */     } catch (IOException e) {
/* 242 */       System.err.println(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\SimplePGNDemo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */