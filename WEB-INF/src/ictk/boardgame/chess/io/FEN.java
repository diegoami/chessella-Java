/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
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
/*     */ public class FEN
/*     */   implements ChessBoardNotation
/*     */ {
/*  44 */   public static long DEBUG = Log.BoardNotation;
/*     */   
/*     */ 
/*  47 */   protected static SAN san = new SAN();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   Locale locale;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocale(Locale loc) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/*  64 */     return this.locale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Board stringToBoard(String str)
/*     */     throws IOException
/*     */   {
/*  74 */     ChessBoard board = null;
/*  75 */     char[][] matrix = new char[8][8];
/*  76 */     char[] strArray = (char[])null;
/*  77 */     int rank = 7;
/*  78 */     int file = 0;
/*  79 */     boolean isBlackMove = false;
/*  80 */     boolean canWhiteCastleKingside = false;
/*  81 */     boolean canWhiteCastleQueenside = false;
/*  82 */     boolean canBlackCastleKingside = false;
/*  83 */     boolean canBlackCastleQueenside = false;
/*  84 */     char enpassantFile = '-';
/*  85 */     int plyCount = 0;int moveNumber = 1;
/*     */     
/*  87 */     str.trim();
/*  88 */     strArray = str.toCharArray();
/*     */     
/*     */ 
/*     */ 
/*  92 */     for (int i = 0; i < strArray.length; i++)
/*     */     {
/*  94 */       if (strArray[i] == '/') {
/*  95 */         rank--;
/*  96 */         file = 0;
/*     */ 
/*     */       }
/*  99 */       else if (Character.isDigit(strArray[i])) {
/* 100 */         file += Character.digit(strArray[i], 10);
/*     */       }
/* 102 */       else if (isPieceChar(strArray[i])) {
/* 103 */         matrix[(file++)][rank] = strArray[i];
/*     */       } else {
/* 105 */         if (strArray[i] == ' ') {
/*     */           break;
/*     */         }
/*     */         
/* 109 */         throw new IOException("Unsupported character found in FEN at:" + 
/* 110 */           i);
/*     */       }
/*     */     }
/*     */     
/* 114 */     i++;
/*     */     
/*     */ 
/* 117 */     if (strArray[i] == 'w') {
/* 118 */       isBlackMove = false;
/* 119 */     } else if (strArray[i] == 'b') {
/* 120 */       isBlackMove = true;
/*     */     } else
/* 122 */       throw new IOException("Unsupported character found in FEN at:" + 
/* 123 */         i + "(" + strArray[i] + ") expecting who to move");
/* 124 */     i++;
/*     */     
/* 126 */     i++;
/* 129 */     for (; 
/*     */         
/* 129 */         (i < strArray.length) && (strArray[i] != ' '); i++) {
/* 130 */       switch (strArray[i]) {
/* 131 */       case 'K':  canWhiteCastleKingside = true; break;
/* 132 */       case 'Q':  canWhiteCastleQueenside = true; break;
/* 133 */       case 'k':  canBlackCastleKingside = true; break;
/* 134 */       case 'q':  canBlackCastleQueenside = true;
/*     */       }
/*     */       
/*     */     }
/* 138 */     i++;
/*     */     
/*     */ 
/* 141 */     if (strArray[i] != '-') {
/* 142 */       enpassantFile = strArray[i];
/* 143 */       i++;
/*     */     }
/* 145 */     i++;
/*     */     
/* 147 */     i++;
/*     */     
/*     */     int j;
/*     */     
/* 151 */     String strPly = str.substring(i, j = str.indexOf(" ", i));
/*     */     try {
/* 153 */       plyCount = Integer.parseInt(strPly);
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 156 */       throw new IOException("Unsupported character found in FEN at:" + 
/* 157 */         i + " (" + strPly + ") expecting ply count");
/*     */     }
/* 159 */     i = j;
/*     */     
/*     */ 
/* 162 */     i++;
/*     */     
/*     */ 
/* 165 */     String strMoves = str.substring(i, str.length());
/*     */     try {
/* 167 */       moveNumber = Integer.parseInt(strMoves);
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 170 */       throw new IOException("Unsupported character found in FEN at:" + 
/* 171 */         i + " (" + strMoves + ") expecting move number");
/*     */     }
/*     */     
/* 174 */     board = new ChessBoard(matrix, 
/* 175 */       isBlackMove, 
/* 176 */       canWhiteCastleKingside, 
/* 177 */       canWhiteCastleQueenside, 
/* 178 */       canBlackCastleKingside, 
/* 179 */       canBlackCastleQueenside, 
/* 180 */       enpassantFile, 
/* 181 */       plyCount, 
/* 182 */       moveNumber);
/*     */     
/* 184 */     return board;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String boardToString(Board b)
/*     */   {
/* 192 */     ChessBoard board = (ChessBoard)b;
/* 193 */     char[][] ray = board.toCharArray();
/* 194 */     StringBuffer buff = new StringBuffer();
/*     */     
/*     */ 
/*     */ 
/* 198 */     int count = 0;
/* 199 */     for (int r = 7; r >= 0; r--) {
/* 200 */       if (r != 7) { buff.append("/");
/*     */       }
/* 202 */       count = 0;
/* 203 */       for (int f = 0; f < 8; f++)
/* 204 */         if (Character.isLetter(ray[f][r])) {
/* 205 */           if (count > 0) {
/* 206 */             buff.append(count);
/* 207 */             count = 0;
/*     */           }
/* 209 */           buff.append(ray[f][r]);
/*     */         } else {
/* 211 */           count++;
/*     */         }
/* 213 */       if (count > 0) {
/* 214 */         buff.append(count);
/*     */       }
/*     */     }
/* 217 */     buff.append(" ");
/*     */     
/*     */ 
/* 220 */     buff.append(board.isBlackMove() ? 'b' : 'w');
/*     */     
/* 222 */     buff.append(" ");
/*     */     
/*     */ 
/* 225 */     boolean castle = false;
/* 226 */     if (board.isWhiteCastleableKingside()) {
/* 227 */       castle = true;
/* 228 */       buff.append("K");
/*     */     }
/* 230 */     if (board.isWhiteCastleableQueenside()) {
/* 231 */       castle = true;
/* 232 */       buff.append("Q");
/*     */     }
/* 234 */     if (board.isBlackCastleableKingside()) {
/* 235 */       castle = true;
/* 236 */       buff.append("k");
/*     */     }
/* 238 */     if (board.isBlackCastleableQueenside()) {
/* 239 */       castle = true;
/* 240 */       buff.append("q");
/*     */     }
/* 242 */     if (!castle) {
/* 243 */       buff.append("-");
/*     */     }
/* 245 */     buff.append(" ");
/*     */     
/*     */ 
/* 248 */     if (board.getEnPassantFile() != 0) {
/* 249 */       buff.append(san.fileToChar(board.getEnPassantFile()));
/* 250 */       if (board.isBlackMove()) {
/* 251 */         buff.append("3");
/*     */       } else {
/* 253 */         buff.append("6");
/*     */       }
/*     */     } else {
/* 256 */       buff.append("-");
/*     */     }
/* 258 */     buff.append(" ");
/*     */     
/*     */ 
/* 261 */     buff.append(board.get50MoveRulePlyCount());
/*     */     
/* 263 */     buff.append(" ");
/*     */     
/*     */ 
/* 266 */     buff.append(board.getCurrentMoveNumber());
/*     */     
/* 268 */     return buff.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isPieceChar(char c)
/*     */   {
/* 278 */     switch (c)
/*     */     {
/*     */ 
/*     */     case 'B': 
/*     */     case 'K': 
/*     */     case 'N': 
/*     */     case 'P': 
/*     */     case 'Q': 
/*     */     case 'R': 
/*     */     case 'b': 
/*     */     case 'k': 
/*     */     case 'n': 
/*     */     case 'p': 
/*     */     case 'q': 
/*     */     case 'r': 
/* 293 */       return true;
/*     */     }
/*     */     
/* 296 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\FEN.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */