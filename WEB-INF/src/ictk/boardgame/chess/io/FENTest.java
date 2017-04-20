/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.AmbiguousMoveException;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.OutOfTurnException;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import junit.framework.TestCase;
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
/*     */ public class FENTest
/*     */   extends TestCase
/*     */ {
/*  37 */   FEN fen = null;
/*  38 */   SAN san = null;
/*  39 */   ChessMove move = null;
/*     */   ChessBoard board;
/*     */   ChessBoard board2;
/*     */   
/*  43 */   public FENTest(String name) { super(name); }
/*     */   
/*     */   public void setUp()
/*     */   {
/*  47 */     this.fen = new FEN();
/*  48 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  52 */     this.fen = null;
/*  53 */     this.san = null;
/*  54 */     this.board = null;
/*  55 */     this.board2 = null;
/*  56 */     this.move = null;
/*  57 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */   public void testReadRandom1()
/*     */     throws IOException
/*     */   {
/*  63 */     Log.addMask(FEN.DEBUG);
/*  64 */     char[][] position = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  65 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  66 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  67 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  68 */       { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
/*  69 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  70 */       { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
/*  71 */       { 'K', ' ', ' ', ' ', ' ', ' ', ' ', 'k' } };
/*     */     
/*  73 */     this.board = new ChessBoard(position);
/*  74 */     this.board2 = 
/*  75 */       ((ChessBoard)this.fen.stringToBoard("7k/4P3/8/8/8/8/8/7K w - - 0 1"));
/*     */     
/*  77 */     assertTrue(this.board.isWhiteCastleableKingside() == 
/*  78 */       this.board2.isWhiteCastleableKingside());
/*     */     
/*  80 */     assertTrue(this.board.isBlackCastleableKingside() == 
/*  81 */       this.board2.isBlackCastleableKingside());
/*     */     
/*  83 */     assertTrue(this.board.isWhiteCastleableQueenside() == 
/*  84 */       this.board2.isWhiteCastleableQueenside());
/*     */     
/*  86 */     assertTrue(this.board.isBlackCastleableQueenside() == 
/*  87 */       this.board2.isBlackCastleableQueenside());
/*     */     
/*  89 */     assertTrue(this.board.getEnPassantFile() == 
/*  90 */       this.board2.getEnPassantFile());
/*     */     
/*  92 */     assertTrue(this.board.get50MoveRulePlyCount() == 
/*  93 */       this.board2.get50MoveRulePlyCount());
/*     */     
/*  95 */     if (!this.board.equals(this.board2)) {
/*  96 */       Log.debug(FEN.DEBUG, "Boards not equal");
/*  97 */       Log.debug2(FEN.DEBUG, this.board.dump());
/*  98 */       Log.debug2(FEN.DEBUG, this.board2.dump());
/*     */     }
/*     */     
/* 101 */     assertTrue(this.board.equals(this.board2));
/* 102 */     assertTrue(this.board.toString().equals(this.board2.toString()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void testReadDefault()
/*     */     throws IOException
/*     */   {
/* 110 */     this.board = new ChessBoard();
/* 111 */     this.board2 = 
/* 112 */       ((ChessBoard)this.fen.stringToBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
/*     */     
/* 114 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testReadDefaultMove1e4()
/*     */     throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException
/*     */   {
/* 125 */     this.board = new ChessBoard();
/* 126 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 127 */     this.board.playMove(this.move);
/* 128 */     this.board2 = 
/* 129 */       ((ChessBoard)this.fen.stringToBoard("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
/*     */     
/*     */ 
/* 132 */     assertTrue(this.board.isWhiteCastleableKingside() == 
/* 133 */       this.board2.isWhiteCastleableKingside());
/*     */     
/* 135 */     assertTrue(this.board.isBlackCastleableKingside() == 
/* 136 */       this.board2.isBlackCastleableKingside());
/*     */     
/* 138 */     assertTrue(this.board.isWhiteCastleableQueenside() == 
/* 139 */       this.board2.isWhiteCastleableQueenside());
/*     */     
/* 141 */     assertTrue(this.board.isBlackCastleableQueenside() == 
/* 142 */       this.board2.isBlackCastleableQueenside());
/*     */     
/* 144 */     assertTrue(this.board.getEnPassantFile() == 
/* 145 */       this.board2.getEnPassantFile());
/*     */     
/* 147 */     assertTrue(this.board.get50MoveRulePlyCount() == 
/* 148 */       this.board2.get50MoveRulePlyCount());
/*     */     
/* 150 */     if (!this.board.equals(this.board2)) {
/* 151 */       Log.debug(FEN.DEBUG, "Boards not equal");
/* 152 */       Log.debug2(FEN.DEBUG, this.board.dump());
/* 153 */       Log.debug2(FEN.DEBUG, this.board2.dump());
/*     */     }
/*     */     
/* 156 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testReadDefaultMove1e4c5()
/*     */     throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException
/*     */   {
/* 167 */     this.board = new ChessBoard();
/* 168 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 169 */     this.board.playMove(this.move);
/* 170 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "c5"));
/* 171 */     this.board.playMove(this.move);
/* 172 */     this.board2 = 
/* 173 */       ((ChessBoard)this.fen.stringToBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
/*     */     
/*     */ 
/* 176 */     assertTrue(this.board.isWhiteCastleableKingside() == 
/* 177 */       this.board2.isWhiteCastleableKingside());
/*     */     
/* 179 */     assertTrue(this.board.isBlackCastleableKingside() == 
/* 180 */       this.board2.isBlackCastleableKingside());
/*     */     
/* 182 */     assertTrue(this.board.isWhiteCastleableQueenside() == 
/* 183 */       this.board2.isWhiteCastleableQueenside());
/*     */     
/* 185 */     assertTrue(this.board.isBlackCastleableQueenside() == 
/* 186 */       this.board2.isBlackCastleableQueenside());
/*     */     
/* 188 */     assertTrue(this.board.getEnPassantFile() == 
/* 189 */       this.board2.getEnPassantFile());
/*     */     
/* 191 */     assertTrue(this.board.get50MoveRulePlyCount() == 
/* 192 */       this.board2.get50MoveRulePlyCount());
/*     */     
/* 194 */     if (!this.board.equals(this.board2)) {
/* 195 */       Log.debug(FEN.DEBUG, "Boards not equal");
/* 196 */       Log.debug2(FEN.DEBUG, this.board.dump());
/* 197 */       Log.debug2(FEN.DEBUG, this.board2.dump());
/*     */     }
/*     */     
/* 200 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void testReadDefaultMove1e4c5Nf3()
/*     */     throws IOException, IllegalMoveException, AmbiguousMoveException, OutOfTurnException
/*     */   {
/* 211 */     this.board = new ChessBoard();
/* 212 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "e4"));
/* 213 */     this.board.playMove(this.move);
/* 214 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "c5"));
/* 215 */     this.board.playMove(this.move);
/* 216 */     this.move = ((ChessMove)this.san.stringToMove(this.board, "Nf3"));
/* 217 */     this.board.playMove(this.move);
/* 218 */     this.board2 = 
/* 219 */       ((ChessBoard)this.fen.stringToBoard("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2"));
/*     */     
/*     */ 
/* 222 */     assertTrue(this.board.isWhiteCastleableKingside() == 
/* 223 */       this.board2.isWhiteCastleableKingside());
/*     */     
/* 225 */     assertTrue(this.board.isBlackCastleableKingside() == 
/* 226 */       this.board2.isBlackCastleableKingside());
/*     */     
/* 228 */     assertTrue(this.board.isWhiteCastleableQueenside() == 
/* 229 */       this.board2.isWhiteCastleableQueenside());
/*     */     
/* 231 */     assertTrue(this.board.isBlackCastleableQueenside() == 
/* 232 */       this.board2.isBlackCastleableQueenside());
/*     */     
/* 234 */     assertTrue(this.board.getEnPassantFile() == 
/* 235 */       this.board2.getEnPassantFile());
/*     */     
/* 237 */     assertTrue(this.board.get50MoveRulePlyCount() == 
/* 238 */       this.board2.get50MoveRulePlyCount());
/*     */     
/* 240 */     if (!this.board.equals(this.board2)) {
/* 241 */       Log.debug(FEN.DEBUG, "Boards not equal");
/* 242 */       Log.debug2(FEN.DEBUG, this.board.dump());
/* 243 */       Log.debug2(FEN.DEBUG, this.board2.dump());
/*     */     }
/*     */     
/* 246 */     assertTrue(this.board.equals(this.board2));
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\FENTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */