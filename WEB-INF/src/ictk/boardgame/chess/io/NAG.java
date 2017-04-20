/*     */ package ictk.boardgame.chess.io;
/*     */ 
/*     */ import ictk.util.Log;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class NAG
/*     */ {
/*  46 */   public static short MAX_NAG = 255;
/*     */   
/*     */   protected Locale locale;
/*     */   
/*     */ 
/*     */   public NAG() {}
/*     */   
/*     */ 
/*     */   public NAG(Locale locale)
/*     */   {
/*  56 */     setLocale(locale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean setLocale(Locale locale)
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   public static short[] stringToNumbers(String s)
/*     */   {
/*  75 */     if ((s == null) || (s.equals(""))) return null;
/*  76 */     StringTokenizer st = new StringTokenizer(s, " ", false);
/*  77 */     String tok = null;
/*  78 */     short[] nags = (short[])null;
/*  79 */     short nag = 0;
/*  80 */     short[] rnags = (short[])null;
/*     */     
/*  82 */     if (st.countTokens() > 0) {
/*  83 */       nags = new short[st.countTokens()];
/*     */     }
/*  85 */     int count = 0;
/*  86 */     while (st.hasMoreTokens()) {
/*  87 */       tok = st.nextToken();
/*     */       
/*  89 */       nag = stringToNumber(tok);
/*  90 */       if (nag > 0) {
/*  91 */         nags[(count++)] = nag;
/*  92 */         nag = 0;
/*     */       }
/*     */     }
/*     */     
/*  96 */     if ((nags != null) && (count > 0) && (count < nags.length)) {
/*  97 */       rnags = new short[count];
/*  98 */       System.arraycopy(nags, 0, rnags, 0, rnags.length);
/*  99 */       nags = rnags;
/*     */     }
/* 101 */     if (count == 0) {
/* 102 */       nags = (short[])null;
/*     */     }
/* 104 */     return nags;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static short stringToNumber(String str)
/*     */   {
/* 113 */     short nag = 0;
/* 114 */     nag = symbolToNumber(str);
/* 115 */     if ((nag == 0) && (str.charAt(0) == '$')) {
/*     */       try {
/* 117 */         nag = Short.parseShort(str.substring(1));
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/* 121 */         Log.error(6, "NAG parsed a bad token: " + 
/* 122 */           str.substring(1));
/*     */       }
/*     */     }
/*     */     
/* 126 */     return nag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isSuffix(String str)
/*     */   {
/* 135 */     return isSuffix(symbolToNumber(str));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isSuffix(int s)
/*     */   {
/* 143 */     boolean t = false;
/* 144 */     switch (s) {
/*     */     case 1: 
/*     */     case 2: 
/*     */     case 3: 
/*     */     case 4: 
/*     */     case 5: 
/*     */     case 6: 
/* 151 */       t = true;
/* 152 */       break;
/*     */     default: 
/* 154 */       t = false;
/*     */     }
/* 156 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isSymbol(int s)
/*     */   {
/* 166 */     boolean t = false;
/* 167 */     switch (s)
/*     */     {
/*     */ 
/*     */     case 1: 
/*     */     case 2: 
/*     */     case 3: 
/*     */     case 4: 
/*     */     case 5: 
/*     */     case 6: 
/*     */     case 10: 
/*     */     case 11: 
/*     */     case 12: 
/*     */     case 13: 
/*     */     case 14: 
/*     */     case 15: 
/*     */     case 16: 
/*     */     case 17: 
/*     */     case 18: 
/*     */     case 19: 
/*     */     case 145: 
/*     */     case 146: 
/*     */     case 201: 
/* 189 */       t = true;
/*     */     }
/*     */     
/* 192 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isSymbol(String str)
/*     */   {
/* 202 */     return symbolToNumber(str) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static short symbolToNumber(String str)
/*     */   {
/* 210 */     short nag = 0;
/* 211 */     if ("!".equals(str)) { nag = 1;
/* 212 */     } else if ("?".equals(str)) { nag = 2;
/* 213 */     } else if ("!!".equals(str)) { nag = 3;
/* 214 */     } else if ("??".equals(str)) { nag = 4;
/* 215 */     } else if ("!?".equals(str)) { nag = 5;
/* 216 */     } else if ("?!".equals(str)) { nag = 6;
/*     */     }
/* 218 */     else if ("=".equals(str)) { nag = 10;
/*     */     }
/* 220 */     else if ("~".equals(str)) { nag = 13;
/* 221 */     } else if ("+=".equals(str)) { nag = 14;
/* 222 */     } else if ("=+".equals(str)) { nag = 15;
/* 223 */     } else if ("+/-".equals(str)) { nag = 16;
/* 224 */     } else if ("-/+".equals(str)) { nag = 17;
/* 225 */     } else if ("+-".equals(str)) { nag = 18;
/* 226 */     } else if ("-+".equals(str)) { nag = 19;
/*     */ 
/*     */     }
/* 229 */     else if ("RR".equals(str)) { nag = 145;
/* 230 */     } else if ("N".equals(str)) { nag = 146;
/*     */ 
/*     */     }
/* 233 */     else if ("D".equals(str)) { nag = 201;
/*     */     }
/* 235 */     return nag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String numberToString(int nag)
/*     */   {
/* 243 */     return numberToString(nag, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String numberToString(int nag, boolean allNumeric)
/*     */   {
/* 255 */     String s = null;
/*     */     
/*     */ 
/* 258 */     if (allNumeric) {
/* 259 */       if (nag <= MAX_NAG) {
/* 260 */         s = "$" + nag;
/*     */       }
/*     */     }
/*     */     else
/* 264 */       switch (nag) {
/* 265 */       case 1:  s = "!"; break;
/* 266 */       case 2:  s = "?"; break;
/* 267 */       case 3:  s = "!!"; break;
/* 268 */       case 4:  s = "??"; break;
/* 269 */       case 5:  s = "!?"; break;
/* 270 */       case 6:  s = "?!"; break;
/*     */       case 10: 
/*     */       case 11: 
/*     */       case 12: 
/* 274 */         s = "="; break;
/*     */       case 13: 
/* 276 */         s = "~"; break;
/* 277 */       case 14:  s = "+="; break;
/* 278 */       case 15:  s = "=+"; break;
/* 279 */       case 16:  s = "+/-"; break;
/* 280 */       case 17:  s = "-/+"; break;
/* 281 */       case 18:  s = "+-"; break;
/* 282 */       case 19:  s = "-+"; break;
/* 283 */       case 20:  s = "+-"; break;
/* 284 */       case 21:  s = "-+"; break;
/*     */       case 145: 
/* 286 */         s = "RR"; break;
/* 287 */       case 146:  s = "N"; break;
/*     */       case 201: 
/* 289 */         s = "D"; break;
/*     */       default: 
/* 291 */         if (nag <= MAX_NAG)
/* 292 */           s = "$" + nag;
/*     */         break;
/*     */       }
/* 295 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String numberToDescription(int nag)
/*     */   {
/* 304 */     String s = null;
/* 305 */     switch (nag) {
/*     */     case 1: 
/* 307 */       s = "good move"; break;
/* 308 */     case 2:  s = "poor move"; break;
/* 309 */     case 3:  s = "very good move"; break;
/* 310 */     case 4:  s = "very poor move"; break;
/* 311 */     case 5:  s = "speculative move"; break;
/* 312 */     case 6:  s = "questionable move"; break;
/* 313 */     case 7:  s = "forced move (all others lose quickly)"; break;
/* 314 */     case 8:  s = "singular move (no reasonable alternatives)"; break;
/* 315 */     case 9:  s = "worst move"; break;
/* 316 */     case 10:  s = "drawish position"; break;
/* 317 */     case 11:  s = "equal chances, quiet position"; break;
/* 318 */     case 12:  s = "equal chances, active position"; break;
/* 319 */     case 13:  s = "unclear position"; break;
/* 320 */     case 14:  s = "White has a slight advantage"; break;
/* 321 */     case 15:  s = "Black has a slight advantage"; break;
/* 322 */     case 16:  s = "White has a moderate advantage"; break;
/* 323 */     case 17:  s = "Black has a moderate advantage"; break;
/* 324 */     case 18:  s = "White has a decisive advantage"; break;
/* 325 */     case 19:  s = "Black has a decisive advantage"; break;
/* 326 */     case 20:  s = "White has a crushing advantage (Black should resign)"; break;
/* 327 */     case 21:  s = "Black has a crushing advantage (White should resign)"; break;
/* 328 */     case 22:  s = "White is in zugzwang"; break;
/* 329 */     case 23:  s = "Black is in zugzwang"; break;
/* 330 */     case 24:  s = "White has a slight space advantage"; break;
/* 331 */     case 25:  s = "Black has a slight space advantage"; break;
/* 332 */     case 26:  s = "White has a moderate space advantage"; break;
/* 333 */     case 27:  s = "Black has a moderate space advantage"; break;
/* 334 */     case 28:  s = "White has a decisive space advantage"; break;
/* 335 */     case 29:  s = "Black has a decisive space advantage"; break;
/* 336 */     case 30:  s = "White has a slight time (development) advantage"; break;
/* 337 */     case 31:  s = "Black has a slight time (development) advantage"; break;
/* 338 */     case 32:  s = "White has a moderate time (development) advantage"; break;
/* 339 */     case 33:  s = "Black has a moderate time (development) advantage"; break;
/* 340 */     case 34:  s = "White has a decisive time (development) advantage"; break;
/* 341 */     case 35:  s = "Black has a decisive time (development) advantage"; break;
/* 342 */     case 36:  s = "White has the initiative"; break;
/* 343 */     case 37:  s = "Black has the initiative"; break;
/* 344 */     case 38:  s = "White has a lasting initiative"; break;
/* 345 */     case 39:  s = "Black has a lasting initiative"; break;
/* 346 */     case 40:  s = "White has the attack"; break;
/* 347 */     case 41:  s = "Black has the attack"; break;
/* 348 */     case 42:  s = "White has insufficient compensation for material deficit"; break;
/* 349 */     case 43:  s = "Black has insufficient compensation for material deficit"; break;
/* 350 */     case 44:  s = "White has sufficient compensation for material deficit"; break;
/* 351 */     case 45:  s = "Black has sufficient compensation for material deficit"; break;
/* 352 */     case 46:  s = "White has more than adequate compensation for material deficit"; break;
/* 353 */     case 47:  s = "Black has more than adequate compensation for material deficit"; break;
/* 354 */     case 48:  s = "White has a slight center control advantage"; break;
/* 355 */     case 49:  s = "Black has a slight center control advantage"; break;
/* 356 */     case 50:  s = "White has a moderate center control advantage"; break;
/* 357 */     case 51:  s = "Black has a moderate center control advantage"; break;
/* 358 */     case 52:  s = "White has a decisive center control advantage"; break;
/* 359 */     case 53:  s = "Black has a decisive center control advantage"; break;
/* 360 */     case 54:  s = "White has a slight kingside control advantage"; break;
/* 361 */     case 55:  s = "Black has a slight kingside control advantage"; break;
/* 362 */     case 56:  s = "White has a moderate kingside control advantage"; break;
/* 363 */     case 57:  s = "Black has a moderate kingside control advantage"; break;
/* 364 */     case 58:  s = "White has a decisive kingside control advantage"; break;
/* 365 */     case 59:  s = "Black has a decisive kingside control advantage"; break;
/* 366 */     case 60:  s = "White has a slight queenside control advantage"; break;
/* 367 */     case 61:  s = "Black has a slight queenside control advantage"; break;
/* 368 */     case 62:  s = "White has a moderate queenside control advantage"; break;
/* 369 */     case 63:  s = "Black has a moderate queenside control advantage"; break;
/* 370 */     case 64:  s = "White has a decisive queenside control advantage"; break;
/* 371 */     case 65:  s = "Black has a decisive queenside control advantage"; break;
/* 372 */     case 66:  s = "White has a vulnerable first rank"; break;
/* 373 */     case 67:  s = "Black has a vulnerable first rank"; break;
/* 374 */     case 68:  s = "White has a well protected first rank"; break;
/* 375 */     case 69:  s = "Black has a well protected first rank"; break;
/* 376 */     case 70:  s = "White has a poorly protected king"; break;
/* 377 */     case 71:  s = "Black has a poorly protected king"; break;
/* 378 */     case 72:  s = "White has a well protected king"; break;
/* 379 */     case 73:  s = "Black has a well protected king"; break;
/* 380 */     case 74:  s = "White has a poorly placed king"; break;
/* 381 */     case 75:  s = "Black has a poorly placed king"; break;
/* 382 */     case 76:  s = "White has a well placed king"; break;
/* 383 */     case 77:  s = "Black has a well placed king"; break;
/* 384 */     case 78:  s = "White has a very weak pawn structure"; break;
/* 385 */     case 79:  s = "Black has a very weak pawn structure"; break;
/* 386 */     case 80:  s = "White has a moderately weak pawn structure"; break;
/* 387 */     case 81:  s = "Black has a moderately weak pawn structure"; break;
/* 388 */     case 82:  s = "White has a moderately strong pawn structure"; break;
/* 389 */     case 83:  s = "Black has a moderately strong pawn structure"; break;
/* 390 */     case 84:  s = "White has a very strong pawn structure"; break;
/* 391 */     case 85:  s = "Black has a very strong pawn structure"; break;
/* 392 */     case 86:  s = "White has poor knight placement"; break;
/* 393 */     case 87:  s = "Black has poor knight placement"; break;
/* 394 */     case 88:  s = "White has good knight placement"; break;
/* 395 */     case 89:  s = "Black has good knight placement"; break;
/* 396 */     case 90:  s = "White has poor bishop placement"; break;
/* 397 */     case 91:  s = "Black has poor bishop placement"; break;
/* 398 */     case 92:  s = "White has good bishop placement"; break;
/* 399 */     case 93:  s = "Black has good bishop placement"; break;
/* 400 */     case 94:  s = "White has poor rook placement"; break;
/* 401 */     case 95:  s = "Black has poor rook placement"; break;
/* 402 */     case 96:  s = "White has good rook placement"; break;
/* 403 */     case 97:  s = "Black has good rook placement"; break;
/* 404 */     case 98:  s = "White has poor queen placement"; break;
/* 405 */     case 99:  s = "Black has poor queen placement"; break;
/* 406 */     case 100:  s = "White has good queen placement"; break;
/* 407 */     case 101:  s = "Black has good queen placement"; break;
/* 408 */     case 102:  s = "White has poor piece coordination"; break;
/* 409 */     case 103:  s = "Black has poor piece coordination"; break;
/* 410 */     case 104:  s = "White has good piece coordination"; break;
/* 411 */     case 105:  s = "Black has good piece coordination"; break;
/* 412 */     case 106:  s = "White has played the opening very poorly"; break;
/* 413 */     case 107:  s = "Black has played the opening very poorly"; break;
/* 414 */     case 108:  s = "White has played the opening poorly"; break;
/* 415 */     case 109:  s = "Black has played the opening poorly"; break;
/* 416 */     case 110:  s = "White has played the opening well"; break;
/* 417 */     case 111:  s = "Black has played the opening well"; break;
/* 418 */     case 112:  s = "White has played the opening very well"; break;
/* 419 */     case 113:  s = "Black has played the opening very well"; break;
/* 420 */     case 114:  s = "White has played the middlegame very poorly"; break;
/* 421 */     case 115:  s = "Black has played the middlegame very poorly"; break;
/* 422 */     case 116:  s = "White has played the middlegame poorly"; break;
/* 423 */     case 117:  s = "Black has played the middlegame poorly"; break;
/* 424 */     case 118:  s = "White has played the middlegame well"; break;
/* 425 */     case 119:  s = "Black has played the middlegame well"; break;
/* 426 */     case 120:  s = "White has played the middlegame very well"; break;
/* 427 */     case 121:  s = "Black has played the middlegame very well"; break;
/* 428 */     case 122:  s = "White has played the ending very poorly"; break;
/* 429 */     case 123:  s = "Black has played the ending very poorly"; break;
/* 430 */     case 124:  s = "White has played the ending poorly"; break;
/* 431 */     case 125:  s = "Black has played the ending poorly"; break;
/* 432 */     case 126:  s = "White has played the ending well"; break;
/* 433 */     case 127:  s = "Black has played the ending well"; break;
/* 434 */     case 128:  s = "White has played the ending very well"; break;
/* 435 */     case 129:  s = "Black has played the ending very well"; break;
/* 436 */     case 130:  s = "White has slight counterplay"; break;
/* 437 */     case 131:  s = "Black has slight counterplay"; break;
/* 438 */     case 132:  s = "White has moderate counterplay"; break;
/* 439 */     case 133:  s = "Black has moderate counterplay"; break;
/* 440 */     case 134:  s = "White has decisive counterplay"; break;
/* 441 */     case 135:  s = "Black has decisive counterplay"; break;
/* 442 */     case 136:  s = "White has moderate time control pressure"; break;
/* 443 */     case 137:  s = "Black has moderate time control pressure"; break;
/* 444 */     case 138:  s = "White has severe time control pressure"; break;
/* 445 */     case 139:  s = "Black has severe time control pressure"; break;
/*     */     case 140: 
/* 447 */       s = "With the idea..."; break;
/* 448 */     case 141:  s = "Aimed against..."; break;
/* 449 */     case 142:  s = "Better move"; break;
/* 450 */     case 143:  s = "Worse move"; break;
/* 451 */     case 144:  s = "Equivalent move"; break;
/* 452 */     case 145:  s = "Editor's Remark"; break;
/* 453 */     case 146:  s = "Novelty"; break;
/* 454 */     case 147:  s = "Weak point"; break;
/* 455 */     case 148:  s = "Endgame"; break;
/* 456 */     case 149:  s = "Line"; break;
/* 457 */     case 150:  s = "Diagonal"; break;
/* 458 */     case 151:  s = "White has a pair of Bishops"; break;
/* 459 */     case 152:  s = "Black has a pair of Bishops"; break;
/* 460 */     case 153:  s = "Bishops of opposite color"; break;
/* 461 */     case 154:  s = "Bishops of same color"; break;
/*     */     case 190: 
/* 463 */       s = "Etc."; break;
/* 464 */     case 191:  s = "Double pawns"; break;
/* 465 */     case 192:  s = "Isolated pawn"; break;
/* 466 */     case 193:  s = "Connected pawns"; break;
/* 467 */     case 194:  s = "Hanging pawns"; break;
/* 468 */     case 195:  s = "Backwards pawn"; break;
/*     */     case 201: 
/* 470 */       s = "Diagram"; break;
/*     */     case 155: case 156: case 157: case 158: case 159: case 160: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 169: case 170: case 171: case 172: case 173: case 174: case 175: case 176: case 177: case 178: case 179: case 180: case 181: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 196: case 197: case 198: case 199: case 200: default: 
/* 472 */       s = null;
/*     */     }
/*     */     
/* 475 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\NAG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */