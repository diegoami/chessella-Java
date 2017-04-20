/*     */ package ictk.boardgame;
/*     */ 
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessGame;
/*     */ import ictk.boardgame.chess.io.SAN;
/*     */ import ictk.util.Log;
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
/*     */ public class ContinuationListTest
/*     */   extends TestCase
/*     */ {
/*     */   ChessGame game;
/*     */   SAN san;
/*     */   History history;
/*     */   ChessBoard board;
/*     */   ChessBoard board2;
/*     */   Move move;
/*     */   ContinuationList cont;
/*     */   
/*     */   public ContinuationListTest(String name)
/*     */   {
/*  55 */     super(name);
/*     */   }
/*     */   
/*     */   public void setUp() {
/*  59 */     this.san = new SAN();
/*     */   }
/*     */   
/*     */   public void tearDown() {
/*  63 */     this.game = null;
/*  64 */     this.history = null;
/*  65 */     this.board = (this.board2 = null);
/*  66 */     this.san = null;
/*  67 */     this.cont = null;
/*  68 */     Log.removeMask(ChessBoard.DEBUG);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testHeadList()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/*  75 */     this.game = new ChessGame();
/*  76 */     this.history = this.game.getHistory();
/*  77 */     this.board = ((ChessBoard)this.game.getBoard());
/*  78 */     assertTrue(this.board != null);
/*     */     
/*  80 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/*  81 */     this.history.prev();
/*     */     
/*  83 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "d4"));
/*  84 */     this.history.prev();
/*  85 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "Nf3"));
/*  86 */     this.history.prev();
/*  87 */     this.history.add(this.san.stringToMove(this.game.getBoard(), "g3"));
/*  88 */     this.history.prev();
/*     */     
/*  90 */     this.cont = this.history.getFirstAll();
/*     */     
/*  92 */     assertTrue(this.cont.size() == 4);
/*  93 */     assertTrue(this.cont.exists(3));
/*  94 */     assertFalse(this.cont.exists(4));
/*  95 */     assertTrue(this.cont.hasMainLine());
/*  96 */     assertFalse(this.cont.isTerminal());
/*  97 */     assertTrue(this.cont.hasVariations());
/*  98 */     assertTrue(this.cont.getMainLine() == this.move);
/*  99 */     assertTrue(this.cont.get(0) == this.move);
/* 100 */     assertTrue(this.cont.sizeOfVariations() == 3);
/* 101 */     assertTrue(this.cont.getIndex(this.move) == 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testShufflePromote1()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 108 */     Move move4 = null;
/* 109 */     this.game = new ChessGame();
/* 110 */     this.history = this.game.getHistory();
/*     */     
/* 112 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 113 */     this.history.prev();
/*     */     Move move2;
/* 115 */     this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 116 */     this.history.prev();
/* 117 */     Move move3; this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 118 */     this.history.prev();
/* 119 */     this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
/* 120 */     this.history.prev();
/*     */     
/* 122 */     this.cont = this.history.getFirstAll();
/*     */     
/* 124 */     assertTrue(this.cont.size() == 4);
/* 125 */     assertTrue(this.cont.getMainLine() == this.move);
/* 126 */     this.cont.promote(move2, 1);
/*     */     
/* 128 */     assertTrue(this.cont.size() == 4);
/* 129 */     assertTrue(this.cont.getMainLine() == move2);
/*     */     
/* 131 */     assertTrue(this.cont.get(1) == this.move);
/*     */     
/* 133 */     assertTrue(this.cont.get(3) == move4);
/* 134 */     this.cont.promote(move4, 2);
/* 135 */     assertTrue(this.cont.get(1) == move4);
/* 136 */     assertTrue(this.cont.get(3) == move3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testMainLineTerminal()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 143 */     Move move4 = null;
/* 144 */     this.game = new ChessGame();
/* 145 */     this.history = this.game.getHistory();
/*     */     
/* 147 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 148 */     this.history.prev();
/*     */     Move move2;
/* 150 */     this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 151 */     this.history.prev();
/* 152 */     Move move3; this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 153 */     this.history.prev();
/* 154 */     this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
/* 155 */     this.history.prev();
/*     */     
/* 157 */     this.cont = this.history.getFirstAll();
/*     */     
/* 159 */     assertTrue(this.cont.size() == 4);
/* 160 */     assertTrue(this.cont.getMainLine() == this.move);
/* 161 */     assertTrue(this.cont.setMainLineTerminal());
/* 162 */     assertTrue(this.cont.get(0) == null);
/* 163 */     assertTrue(this.cont.get(1) == this.move);
/*     */     
/* 165 */     assertTrue(this.cont.size() == 5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testShufflePromote2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 172 */     Move move4 = null;
/* 173 */     this.game = new ChessGame();
/* 174 */     this.history = this.game.getHistory();
/*     */     
/* 176 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 177 */     this.history.prev();
/*     */     Move move2;
/* 179 */     this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 180 */     this.history.prev();
/* 181 */     Move move3; this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 182 */     this.history.prev();
/* 183 */     this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
/* 184 */     this.history.prev();
/*     */     
/* 186 */     this.cont = this.history.getFirstAll();
/*     */     
/* 188 */     assertTrue(this.cont.size() == 4);
/* 189 */     assertTrue(this.cont.getMainLine() == this.move);
/* 190 */     assertTrue(this.cont.setMainLineTerminal());
/* 191 */     assertTrue(this.cont.get(0) == null);
/* 192 */     assertTrue(this.cont.get(1) == this.move);
/*     */     
/* 194 */     assertTrue(this.cont.size() == 5);
/*     */     
/* 196 */     this.cont.promote(move3, 0);
/* 197 */     assertTrue(this.cont.size() == 4);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testShuffleDemote()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 204 */     Move move4 = null;
/* 205 */     this.game = new ChessGame();
/* 206 */     this.history = this.game.getHistory();
/*     */     
/* 208 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 209 */     this.history.prev();
/*     */     Move move2;
/* 211 */     this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 212 */     this.history.prev();
/* 213 */     Move move3; this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 214 */     this.history.prev();
/* 215 */     this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
/* 216 */     this.history.prev();
/*     */     
/* 218 */     this.cont = this.history.getFirstAll();
/*     */     
/* 220 */     assertTrue(this.cont.size() == 4);
/* 221 */     this.cont.demote(this.move, 1);
/* 222 */     assertTrue(this.cont.size() == 4);
/* 223 */     assertTrue(this.cont.getMainLine() == move2);
/* 224 */     assertTrue(this.cont.get(1) == this.move);
/* 225 */     assertTrue(this.cont.get(2) == move3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void testShuffleDemote2()
/*     */     throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException
/*     */   {
/* 232 */     Move move4 = null;
/* 233 */     this.game = new ChessGame();
/* 234 */     this.history = this.game.getHistory();
/*     */     
/* 236 */     this.history.add(this.move = this.san.stringToMove(this.game.getBoard(), "e4"));
/* 237 */     this.history.prev();
/*     */     Move move2;
/* 239 */     this.history.add(move2 = this.san.stringToMove(this.game.getBoard(), "d4"));
/* 240 */     this.history.prev();
/* 241 */     Move move3; this.history.add(move3 = this.san.stringToMove(this.game.getBoard(), "Nf3"));
/* 242 */     this.history.prev();
/* 243 */     this.history.add(move4 = this.san.stringToMove(this.game.getBoard(), "g3"));
/* 244 */     this.history.prev();
/*     */     
/* 246 */     this.cont = this.history.getFirstAll();
/*     */     
/* 248 */     assertTrue(this.cont.size() == 4);
/* 249 */     this.cont.demote(this.move, 0);
/* 250 */     assertTrue(this.cont.size() == 4);
/* 251 */     assertTrue(this.cont.getMainLine() == move2);
/* 252 */     assertTrue(this.cont.get(1) == move3);
/* 253 */     assertTrue(this.cont.get(2) == move4);
/* 254 */     assertTrue(this.cont.get(3) == this.move);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\ContinuationListTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */