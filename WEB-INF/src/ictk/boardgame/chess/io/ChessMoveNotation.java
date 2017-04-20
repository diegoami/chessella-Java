/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.boardgame.chess.ChessPiece;
/*     */ import ictk.boardgame.io.MoveNotation;
/*     */ import ictk.util.Log;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChessMoveNotation
/*     */   implements MoveNotation
/*     */ {
/*  46 */   public static final long DEBUG = Log.MoveNotation;
/*     */   
/*     */ 
/*  49 */   public static final char[][] PIECE_SETS = {
/*  50 */     { 'P', 'N', 'B', 'R', 'Q', 'K' }, 
/*  51 */     { 'P', 'J', 'S', 'V', 'D', 'K' }, 
/*  52 */     { 'B', 'S', 'L', 'T', 'D', 'K' }, 
/*  53 */     { 'P', 'R', 'O', 'V', 'L', 'K' }, 
/*  54 */     { 'P', 'R', 'L', 'T', 'D', 'K' }, 
/*  55 */     { 'P', 'C', 'F', 'T', 'D', 'R' }, 
/*  56 */     { 'B', 'S', 'L', 'T', 'D', 'K' }, 
/*  57 */     { 'G', 'H', 'F', 'B', 'V', 'K' }, 
/*  58 */     { 'P', 'R', 'B', 'H', 'D', 'K' }, 
/*  59 */     { 'P', 'C', 'A', 'T', 'D', 'R' }, 
/*  60 */     { 'B', 'S', 'L', 'T', 'D', 'K' }, 
/*  61 */     { 'P', 'S', 'G', 'W', 'H', 'K' }, 
/*  62 */     { 'P', 'C', 'B', 'T', 'D', 'R' }, 
/*  63 */     { 'P', 'C', 'N', 'T', 'D', 'R' }, 
/*  64 */     { 'P', 'C', 'A', 'T', 'D', 'R' }, 
/*  65 */     { 'B', 'S', 'L', 'T', 'D', 'K' }, 
/*  66 */     { 'P', 'S', 'L', 'T', 'D', 'K' } };
/*     */   
/*     */ 
/*     */ 
/*  70 */   public static final char[][] FILE_SETS = {
/*  71 */     { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' } };
/*     */   
/*     */ 
/*     */ 
/*  75 */   public static final char[][] RANK_SETS = {
/*  76 */     { '1', '2', '3', '4', '5', '6', '7', '8' } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  81 */   protected char[] pieceSet = PIECE_SETS[0];
/*     */   
/*  83 */   protected char[] fileSet = FILE_SETS[0];
/*     */   
/*  85 */   protected char[] rankSet = RANK_SETS[0];
/*     */   
/*     */   protected Locale locale;
/*     */   
/*     */   public ChessMoveNotation()
/*     */   {
/*  91 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChessMoveNotation(Locale loc)
/*     */   {
/*  99 */     this.locale = loc;
/* 100 */     setLocale(this.locale);
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
/*     */   public boolean setLocale(Locale loc)
/*     */   {
/* 114 */     String lang = null;
/* 115 */     if ((loc == null) || 
/* 116 */       ("eng".equals(lang = loc.getISO3Language()))) {
/* 117 */       this.pieceSet = PIECE_SETS[0];
/* 118 */     } else if ("gem".equals(lang)) {
/* 119 */       this.pieceSet = PIECE_SETS[6];
/* 120 */     } else if ("fra".equals(lang)) {
/* 121 */       this.pieceSet = PIECE_SETS[5];
/* 122 */     } else if ("ces".equals(lang)) {
/* 123 */       this.pieceSet = PIECE_SETS[1];
/* 124 */     } else if ("dan".equals(lang)) {
/* 125 */       this.pieceSet = PIECE_SETS[2];
/* 126 */     } else if ("est".equals(lang)) {
/* 127 */       this.pieceSet = PIECE_SETS[3];
/* 128 */     } else if ("fin".equals(lang)) {
/* 129 */       this.pieceSet = PIECE_SETS[4];
/* 130 */     } else if ("hun".equals(lang)) {
/* 131 */       this.pieceSet = PIECE_SETS[7];
/* 132 */     } else if ("isl".equals(lang)) {
/* 133 */       this.pieceSet = PIECE_SETS[8];
/* 134 */     } else if ("ita".equals(lang)) {
/* 135 */       this.pieceSet = PIECE_SETS[9];
/* 136 */     } else if ("nor".equals(lang)) {
/* 137 */       this.pieceSet = PIECE_SETS[10];
/* 138 */     } else if ("nor".equals(lang)) {
/* 139 */       this.pieceSet = PIECE_SETS[10];
/* 140 */     } else if ("pol".equals(lang)) {
/* 141 */       this.pieceSet = PIECE_SETS[11];
/* 142 */     } else if ("por".equals(lang)) {
/* 143 */       this.pieceSet = PIECE_SETS[12];
/* 144 */     } else if ("ron".equals(lang)) {
/* 145 */       this.pieceSet = PIECE_SETS[13];
/* 146 */     } else if ("spa".equals(lang)) {
/* 147 */       this.pieceSet = PIECE_SETS[14];
/* 148 */     } else if ("swe".equals(lang)) {
/* 149 */       this.pieceSet = PIECE_SETS[15];
/* 150 */     } else if ("swe".equals(lang)) {
/* 151 */       this.pieceSet = PIECE_SETS[15];
/* 152 */     } else if ("hrv".equals(lang)) {
/* 153 */       this.pieceSet = PIECE_SETS[16];
/*     */     } else {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/* 165 */     return this.locale;
/*     */   }
/*     */   
/*     */   public char[] getPieceSet()
/*     */   {
/* 170 */     return this.pieceSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPieceSet(char[] set)
/*     */   {
/* 179 */     if (set.length != 6)
/* 180 */       throw new IllegalArgumentException(
/* 181 */         "The set must contain 6 characters in the order of [PNBRQK].");
/* 182 */     this.pieceSet = set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte pieceToNum(String s)
/*     */   {
/* 192 */     return pieceToNum(s.charAt(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte pieceToNum(char c)
/*     */   {
/* 200 */     byte p = ChessPiece.NULL_PIECE;
/*     */     
/* 202 */     c = Character.toUpperCase(c);
/*     */     
/*     */ 
/* 205 */     if ((c == this.pieceSet[0]) || (c == ' ')) {
/* 206 */       p = 5;
/* 207 */     } else if (c == this.pieceSet[1]) {
/* 208 */       p = 4;
/* 209 */     } else if (c == this.pieceSet[2]) {
/* 210 */       p = 3;
/* 211 */     } else if (c == this.pieceSet[3]) {
/* 212 */       p = 2;
/* 213 */     } else if (c == this.pieceSet[4]) {
/* 214 */       p = 1;
/* 215 */     } else if (c == this.pieceSet[5]) {
/* 216 */       p = 0;
/*     */     }
/* 218 */     if (p == ChessPiece.NULL_PIECE)
/*     */     {
/* 220 */       Log.debug(DEBUG, "unknown piece: <" + c + ">");
/* 221 */       throw new ArrayIndexOutOfBoundsException("Unknown ChessPiece");
/*     */     }
/*     */     
/* 224 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char pieceToChar(int p)
/*     */   {
/* 233 */     char c = ' ';
/* 234 */     switch (p % ChessPiece.BLACK_OFFSET) {
/* 235 */     case 5:  c = this.pieceSet[0]; break;
/* 236 */     case 4:  c = this.pieceSet[1]; break;
/* 237 */     case 3:  c = this.pieceSet[2]; break;
/* 238 */     case 2:  c = this.pieceSet[3]; break;
/* 239 */     case 1:  c = this.pieceSet[4]; break;
/* 240 */     case 0:  c = this.pieceSet[5]; break;
/*     */     
/*     */     default: 
/* 243 */       Log.debug(DEBUG, "unknown piece index: <" + p + ">");
/* 244 */       throw new ArrayIndexOutOfBoundsException("Unknown ChessPiece");
/*     */     }
/*     */     
/* 247 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char pieceToChar(ChessPiece p)
/*     */   {
/* 255 */     return pieceToChar(p.getIndex());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte fileToNum(String s)
/*     */   {
/* 263 */     return fileToNum(s.charAt(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte fileToNum(char c)
/*     */   {
/* 272 */     byte p = 0;
/*     */     
/* 274 */     p = mapSetToNum(Character.toLowerCase(c), this.fileSet);
/*     */     
/* 276 */     if (p == 0) {
/* 277 */       if ((c == '-') || (c == ' ')) { return p;
/*     */       }
/*     */       
/* 280 */       Log.debug(DEBUG, "unknown file: <" + c + ">");
/* 281 */       throw new ArrayIndexOutOfBoundsException(
/* 282 */         "file out of range: " + c);
/*     */     }
/* 284 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte rankToNum(char c)
/*     */   {
/* 293 */     byte p = 0;
/*     */     
/* 295 */     p = mapSetToNum(Character.toLowerCase(c), this.rankSet);
/*     */     
/* 297 */     if (p == 0) {
/* 298 */       if ((c == '-') || (c == ' ')) { return p;
/*     */       }
/*     */       
/* 301 */       Log.debug(DEBUG, "unknown file: <" + c + ">");
/* 302 */       throw new ArrayIndexOutOfBoundsException(
/* 303 */         "rank out of range: " + c);
/*     */     }
/*     */     
/* 306 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char fileToChar(int i)
/*     */   {
/* 314 */     char c = ' ';
/*     */     
/* 316 */     if ((i > 0) && (i <= this.fileSet.length)) {
/* 317 */       c = this.fileSet[(i - 1)];
/*     */     }
/*     */     else {
/* 320 */       Log.debug(DEBUG, "file out of range: <" + i + ">");
/* 321 */       throw new ArrayIndexOutOfBoundsException("file out of range (" + 
/* 322 */         i + ")");
/*     */     }
/* 324 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char rankToChar(int i)
/*     */   {
/* 332 */     char c = ' ';
/*     */     
/* 334 */     if ((i > 0) && (i <= this.rankSet.length)) {
/* 335 */       c = this.rankSet[(i - 1)];
/*     */     }
/*     */     else {
/* 338 */       Log.debug(DEBUG, "rank out of range: <" + i + ">");
/* 339 */       throw new ArrayIndexOutOfBoundsException("rank out of range");
/*     */     }
/* 341 */     return c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected byte mapSetToNum(char c, char[] set)
/*     */   {
/* 348 */     byte f = 0;
/* 349 */     for (byte i = 0; (f == 0) && (i < set.length); i = (byte)(i + 1))
/* 350 */       if (c == set[i])
/* 351 */         f = (byte)(i + 1);
/* 352 */     return f;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\ChessMoveNotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */