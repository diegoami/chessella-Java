/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.Board;
/*     */ import ictk.boardgame.IllegalMoveException;
/*     */ import ictk.boardgame.Move;
/*     */ import ictk.boardgame.Result;
/*     */ import ictk.boardgame.chess.AmbiguousChessMoveException;
/*     */ import ictk.boardgame.chess.ChessBoard;
/*     */ import ictk.boardgame.chess.ChessMove;
/*     */ import ictk.boardgame.chess.ChessPiece;
/*     */ import ictk.boardgame.chess.ChessResult;
/*     */ import ictk.boardgame.chess.Square;
/*     */ import ictk.util.Log;
/*     */ import java.util.Locale;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class SAN
/*     */   extends ChessMoveNotation
/*     */ {
/*  45 */   public static final long DEBUG = ChessMoveNotation.DEBUG;
/*     */   
/*     */ 
/*  48 */   protected static final Pattern defaultMovePattern = getLocalePattern(PIECE_SETS[0], 
/*  49 */     FILE_SETS[0], 
/*  50 */     RANK_SETS[0]);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   protected static final NAG nag = new NAG();
/*     */   
/*     */ 
/*     */ 
/*     */   protected Pattern movePattern;
/*     */   
/*     */ 
/*     */ 
/*  64 */   protected boolean pawnSpace = false;
/*     */   
/*     */   public SAN() {
/*  67 */     this(false);
/*     */   }
/*     */   
/*     */   public SAN(boolean pawnSpace) {
/*  71 */     this.pawnSpace = pawnSpace;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SAN(Locale loc)
/*     */   {
/*  79 */     this(loc, false);
/*     */   }
/*     */   
/*     */   public SAN(Locale loc, boolean pawnSpace) {
/*  83 */     super(loc);
/*  84 */     this.pawnSpace = pawnSpace;
/*     */   }
/*     */   
/*     */ 
/*  88 */   public void setPawnAsSpace(boolean t) { this.pawnSpace = t; }
/*  89 */   public boolean isPawnAsSpace() { return this.pawnSpace; }
/*     */   
/*     */   public boolean setLocale(Locale loc)
/*     */   {
/*  93 */     if (!super.setLocale(loc)) {
/*  94 */       return false;
/*     */     }
/*  96 */     String lang = null;
/*     */     
/*     */ 
/*  99 */     if ((loc == null) || 
/* 100 */       ("eng".equals(lang = loc.getISO3Language()))) {
/* 101 */       this.movePattern = defaultMovePattern;
/*     */     } else {
/* 103 */       this.movePattern = getLocalePattern(this.pieceSet, this.fileSet, this.rankSet);
/*     */     }
/* 105 */     return true;
/*     */   }
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
/*     */   public Move stringToMove(Board b, String s)
/*     */     throws AmbiguousChessMoveException, IllegalMoveException
/*     */   {
/* 121 */     if (b == null)
/*     */     {
/* 123 */       Log.debug(DEBUG, "cannot associate a move with a null ChessBoard");
/* 124 */       throw new IllegalArgumentException(
/* 125 */         "Cannot associate a move with a null ChessBoard");
/*     */     }
/* 127 */     if (!(b instanceof ChessBoard))
/*     */     {
/* 129 */       Log.debug(DEBUG, "non ChessBoard send to stringToMove");
/* 130 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 134 */     ChessBoard board = (ChessBoard)b;
/* 135 */     ChessMove move = null;
/* 136 */     Matcher result = null;
/* 137 */     String rest_of_string = null;
/* 138 */     ChessAnnotation anno = null;
/*     */     
/* 140 */     if (board == null)
/* 141 */       throw new IllegalArgumentException(
/* 142 */         "can't make move on a null board");
/* 143 */     if (s == null) {
/* 144 */       throw new IllegalArgumentException(
/* 145 */         "can't make a move out of a null string");
/*     */     }
/* 147 */     byte piece = ChessPiece.NULL_PIECE;
/* 148 */     byte promo = ChessPiece.NULL_PIECE;
/* 149 */     byte orig_f = 0;
/* 150 */     byte orig_r = 0;
/*     */     
/* 152 */     Square orig = null;
/* 153 */     Square dest = null;
/*     */     
/* 155 */     result = this.movePattern.matcher(s);
/*     */     
/* 157 */     if (result.find())
/*     */     {
/*     */ 
/*     */ 
/* 161 */       Log.debug(DEBUG, "regex result for: " + s, result);
/*     */       
/*     */ 
/* 164 */       if ((result.group(1).equals("O-O-O")) || 
/* 165 */         (result.group(1).equals("0-0-0"))) {
/* 166 */         move = new ChessMove(board, -1);
/*     */       }
/* 168 */       else if ((result.group(1).equals("O-O")) || 
/* 169 */         (result.group(1).equals("0-0"))) {
/* 170 */         move = new ChessMove(board, 1);
/*     */       }
/*     */       else {
/* 173 */         if (result.group(2) != null) {
/* 174 */           piece = pieceToNum(result.group(2).charAt(0));
/*     */         }
/* 176 */         if (result.group(3) != null) {
/* 177 */           orig_f = fileToNum(result.group(3).charAt(0));
/*     */         }
/* 179 */         if (result.group(4) != null) {
/* 180 */           orig_r = rankToNum(result.group(4).charAt(0));
/*     */         }
/* 182 */         dest = board.getSquare(fileToNum(result.group(6).charAt(0)), 
/* 183 */           rankToNum(result.group(7).charAt(0)));
/*     */         
/*     */ 
/* 186 */         if ((orig_f < 1) || (orig_r < 1)) {
/* 187 */           if (piece == ChessPiece.NULL_PIECE) piece = 5;
/*     */           try {
/* 189 */             orig = board.getOrigin(piece, orig_f, orig_r, dest);
/*     */           }
/*     */           catch (IllegalMoveException e) {
/* 192 */             e.setMoveString(s);
/* 193 */             throw e;
/*     */           }
/*     */         }
/*     */         else {
/* 197 */           orig = board.getSquare(orig_f, orig_r);
/*     */         }
/*     */         
/*     */ 
/* 201 */         if (result.group(8) != null) {
/* 202 */           promo = pieceToNum(result.group(8).charAt(1));
/*     */         }
/* 204 */         if (promo == ChessPiece.NULL_PIECE) {
/* 205 */           move = new ChessMove(board, orig, dest);
/*     */         } else {
/* 207 */           move = new ChessMove(board, orig, dest, 
/* 208 */             ChessPiece.toChessPiece(promo));
/*     */         }
/*     */       }
/*     */       
/* 212 */       if (result.end() < s.length()) {
/* 213 */         short[] nags = (short[])null;
/*     */         
/* 215 */         nags = NAG.stringToNumbers(s.substring(result.end()));
/* 216 */         if (nags != null) {
/* 217 */           anno = new ChessAnnotation();
/* 218 */           for (int i = 0; i < nags.length; i++)
/* 219 */             anno.addNAG(nags[i]);
/*     */         }
/* 221 */         move.setAnnotation(anno);
/*     */       }
/*     */     }
/* 224 */     return move;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result stringToResult(String s)
/*     */   {
/* 232 */     ChessResult result = null;
/*     */     
/* 234 */     if (s.startsWith("1-0")) {
/* 235 */       result = new ChessResult(2);
/* 236 */     } else if (s.startsWith("0-1")) {
/* 237 */       result = new ChessResult(3);
/* 238 */     } else if (s.startsWith("1/2-1/2")) {
/* 239 */       result = new ChessResult(1);
/* 240 */     } else if (s.startsWith("*")) {
/* 241 */       result = new ChessResult(0);
/*     */     }
/* 243 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String moveToString(Move move)
/*     */   {
/* 252 */     return moveToString(move, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String moveToString(Move move, boolean showSuffix)
/*     */   {
/* 261 */     if (!(move instanceof ChessMove)) {
/* 262 */       return null;
/*     */     }
/* 264 */     if (move.isNullMove()) {
/* 265 */       return "--";
/*     */     }
/* 267 */     ChessMove m = (ChessMove)move;
/*     */     
/* 269 */     if (m == null) {
/* 270 */       throw new NullPointerException("can't convert null move to string");
/*     */     }
/*     */     
/*     */ 
/* 274 */     Log.debug(DEBUG, "move: " + move + " showSuffix?: " + showSuffix);
/*     */     
/* 276 */     StringBuffer sb = new StringBuffer();
/* 277 */     char piece = pieceToChar(m.getChessPiece());
/* 278 */     int[] coords = m.getCoordinates();
/* 279 */     char take = m.getCasualty() == null ? '-' : 'x';
/* 280 */     char promo = m.getPromotion() == null ? ' ' : 
/* 281 */       pieceToChar(m.getPromotion());
/*     */     
/* 283 */     if (m.isCastleKingside()) {
/* 284 */       sb.append("O-O");
/* 285 */     } else if (m.isCastleQueenside()) {
/* 286 */       sb.append("O-O-O");
/*     */     } else {
/* 288 */       if (piece == this.pieceSet[0]) {
/* 289 */         if (this.pawnSpace) sb.append(' ');
/*     */       }
/*     */       else {
/* 292 */         sb.append(piece);
/*     */       }
/*     */       
/* 295 */       if (!m.isRankUnique())
/* 296 */         sb.append(fileToChar(coords[0]));
/* 297 */       if (!m.isFileUnique()) {
/* 298 */         sb.append(rankToChar(coords[1]));
/*     */       }
/*     */       
/* 301 */       if (m.getCasualty() != null) {
/* 302 */         if ((piece == this.pieceSet[0]) && 
/* 303 */           (m.isFileUnique()) && 
/* 304 */           (m.isRankUnique()))
/* 305 */           sb.append(fileToChar(coords[0]));
/* 306 */         sb.append(take);
/*     */       }
/*     */       
/*     */ 
/* 310 */       sb.append(fileToChar(coords[2])).append(rankToChar(coords[3]));
/*     */       
/* 312 */       if (promo != ' ') {
/* 313 */         sb.append("=").append(promo);
/*     */       }
/* 315 */       if (m.isCheckmate()) {
/* 316 */         sb.append('#');
/* 317 */       } else if (m.isCheck()) {
/* 318 */         sb.append('+');
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 323 */     if ((showSuffix) && 
/* 324 */       (m.getAnnotation() != null) && 
/* 325 */       (((ChessAnnotation)m.getAnnotation()).getSuffix() != 0)) {
/* 326 */       sb.append(
/* 327 */         NAG.numberToString(
/* 328 */         ((ChessAnnotation)m.getAnnotation()).getSuffix()));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 333 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String moveToString(ChessMove m) {
/* 337 */     return moveToString(m, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String resultToString(Result result)
/*     */   {
/* 345 */     ChessResult res = (ChessResult)result;
/* 346 */     String str = null;
/*     */     
/* 348 */     switch (res.getIndex()) {
/* 349 */     case 2:  str = "1-0"; break;
/* 350 */     case 1:  str = "1/2-1/2"; break;
/* 351 */     case 3:  str = "0-1"; break;
/* 352 */     case 0:  str = "*";
/*     */     }
/* 354 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static Pattern getLocalePattern(char[] pieceSet, char[] fileSet, char[] rankSet)
/*     */   {
/* 367 */     StringBuffer sb = new StringBuffer();
/* 368 */     int i = 0;
/*     */     
/* 370 */     sb.append("[^0O]*([0O]-[0O]-[0O]")
/* 371 */       .append("|[0O]-[0O]")
/*     */       
/* 373 */       .append("|^([");
/* 374 */     for (i = 0; i < pieceSet.length; i++)
/* 375 */       sb.append(pieceSet[i]);
/* 376 */     sb.append("])?([");
/*     */     
/* 378 */     for (i = 0; i < fileSet.length; i++)
/*     */     {
/* 380 */       sb.append(fileSet[i]).append(Character.toUpperCase(fileSet[i]));
/*     */     }
/* 382 */     sb.append("])?([");
/* 383 */     for (i = 0; i < rankSet.length; i++)
/* 384 */       sb.append(rankSet[i]);
/* 385 */     sb.append("])?([xX])?([");
/*     */     
/* 387 */     for (i = 0; i < fileSet.length; i++)
/*     */     {
/* 389 */       sb.append(fileSet[i]).append(Character.toUpperCase(fileSet[i])); }
/* 390 */     sb.append("])([");
/*     */     
/* 392 */     for (i = 0; i < rankSet.length; i++) {
/* 393 */       sb.append(rankSet[i]);
/*     */     }
/*     */     
/* 396 */     sb.append("])").append("(=[");
/* 397 */     for (i = 0; i < pieceSet.length - 1; i++) {
/* 398 */       sb.append(pieceSet[i]);
/*     */     }
/*     */     
/* 401 */     sb.append("])?)").append("(\\+)?(\\+)?(#)?");
/*     */     
/*     */ 
/* 404 */     return Pattern.compile(sb.toString());
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\SAN.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */