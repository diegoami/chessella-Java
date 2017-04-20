/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSDate;
/*     */ import ictk.boardgame.chess.net.ics.ICSMove;
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.ICSResult;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSMoveListEvent;
/*     */ import ictk.util.Log;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class FICSMoveListParser
/*     */   extends ICSEventParser
/*     */ {
/*  65 */   public static final Pattern masterPattern = Pattern.compile("^:?Movelist for game (\\d+):\\n:?\\n(\\S+)\\s(?:\\(\\s*([0-9+-]+[EP]?)\\))\\svs\\.\\s(\\S+)\\s(?:\\(\\s*([0-9+-]+[EP]?)\\))\\s---\\s((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))\\n^:?(Rated|Unrated)\\s(\\w+)\\smatch, initial time:\\s(\\d+)\\sminutes, increment:\\s(\\d+)\\sseconds\\.\\n\\nMove\\s+(\\S+)\\s+(\\S+)\\s*\\n^----\\s+---------------------\\s+---------------------\\n(.*)^\\s{6}\\{([^}]+)\\}\\s(\\S)", 
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
/*  94 */     40);
/*     */   
/*     */ 
/*  97 */   public static final Pattern moveLinePattern = Pattern.compile("^:?\\s*(\\d+)\\.\\s+(\\S+)\\s+(\\((\\d+):(\\d\\d)\\.?(\\d{3})?\\))?\\s*((\\S+)?\\s*(\\((\\d+):(\\d\\d)\\.(\\d{3})?\\))?)?\\s*$", 
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */     8);
/*     */   
/* 106 */   public static FICSMoveListParser singleton = new FICSMoveListParser();
/*     */   
/*     */ 
/*     */   protected FICSMoveListParser()
/*     */   {
/* 111 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/* 116 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/* 121 */     ICSEvent evt = new ICSMoveListEvent();
/* 122 */     assignMatches(match, evt);
/*     */     
/* 124 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/* 129 */     ICSMoveListEvent evt = (ICSMoveListEvent)event;
/*     */     
/* 131 */     if (this.debug) {
/* 132 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
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
/* 161 */     evt.setWhitePlayer(m.group(2));
/* 162 */     evt.setBlackPlayer(m.group(4));
/* 163 */     evt.setWhiteRating(new ICSRating(m.group(3)));
/* 164 */     evt.setBlackRating(new ICSRating(m.group(5)));
/* 165 */     evt.setVariant(new ICSVariant(m.group(15)));
/* 166 */     evt.setStatus(m.group(21));
/* 167 */     evt.setResult(new ICSResult(m.group(22)));
/*     */     
/* 169 */     evt.setRated("Rated".equals(m.group(14)));
/*     */     
/* 171 */     int i = 0;
/* 172 */     ICSDate date = new ICSDate(m.group(6));
/* 173 */     evt.setDate(date);
/*     */     try {
/* 175 */       i = 1;
/* 176 */       evt.setBoardNumber(Integer.parseInt(m.group(i)));
/* 177 */       i = 16;
/* 178 */       evt.setInitialTime(Integer.parseInt(m.group(i)));
/* 179 */       i = 17;
/* 180 */       evt.setIncrement(Integer.parseInt(m.group(i)));
/*     */     }
/*     */     catch (NumberFormatException e)
/*     */     {
/* 184 */       Log.error(3, 
/* 185 */         "Couldn't parse a number in: " + m.group(i));
/*     */     }
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
/*     */ 
/*     */ 
/* 235 */     Matcher mvMatch = moveLinePattern.matcher(m.group(20));
/* 236 */     List tmplist = new LinkedList();
/* 237 */     ICSMove tmp = null;
/* 238 */     int min = 0;int s = 0;int ms = 0;
/*     */     
/* 240 */     while (mvMatch.find())
/*     */     {
/* 242 */       if (this.debug) {
/* 243 */         Log.debug(ICSEventParser.DEBUG, "move", mvMatch);
/*     */       }
/* 245 */       if ((mvMatch.group(2) != null) && 
/* 246 */         (!mvMatch.group(2).equals("..."))) {
/* 247 */         tmp = new ICSMove();
/*     */         try
/*     */         {
/* 250 */           tmp.setSAN(mvMatch.group(i = 2));
/* 251 */           tmp.setBlack(false);
/* 252 */           tmp.setMoveNumber(Integer.parseInt(mvMatch.group(i = 1)));
/* 253 */           min = Integer.parseInt(mvMatch.group(i = 4));
/* 254 */           s = Integer.parseInt(mvMatch.group(i = 5));
/* 255 */           ms = Integer.parseInt(mvMatch.group(i = 6));
/* 256 */           tmp.setMoveTime(min * 60000 + s * 1000 + ms);
/*     */         }
/*     */         catch (NumberFormatException e) {
/* 259 */           Log.error(3, 
/* 260 */             "threw NumberFormatExceptionfor(" + 
/* 261 */             i + "): " + mvMatch.group(i) + 
/* 262 */             " of " + mvMatch.group(0));
/* 263 */           evt.setEventType(0);
/* 264 */           evt.setMessage(m.group(0));
/*     */           
/* 266 */           Log.debug(ICSEventParser.DEBUG, "regex", mvMatch);
/* 267 */           return;
/*     */         }
/* 269 */         tmplist.add(tmp);
/*     */       }
/*     */       
/* 272 */       if (mvMatch.group(8) != null) {
/* 273 */         tmp = new ICSMove();
/*     */         try
/*     */         {
/* 276 */           tmp.setSAN(mvMatch.group(i = 8));
/* 277 */           tmp.setBlack(true);
/* 278 */           tmp.setMoveNumber(Integer.parseInt(mvMatch.group(i = 1)));
/* 279 */           min = Integer.parseInt(mvMatch.group(i = 10));
/* 280 */           s = Integer.parseInt(mvMatch.group(i = 11));
/* 281 */           ms = Integer.parseInt(mvMatch.group(i = 12));
/* 282 */           tmp.setMoveTime(min * 60000 + s * 1000 + ms);
/*     */         }
/*     */         catch (NumberFormatException e) {
/* 285 */           Log.error(3, 
/* 286 */             "threw NumberFormatExceptionfor(" + 
/* 287 */             i + "): " + mvMatch.group(i) + 
/* 288 */             " of " + mvMatch.group(0));
/* 289 */           evt.setEventType(0);
/* 290 */           evt.setMessage(m.group(0));
/*     */           
/* 292 */           Log.debug(ICSEventParser.DEBUG, "regex", mvMatch);
/* 293 */           return;
/*     */         }
/* 295 */         tmplist.add(tmp);
/*     */       }
/*     */     }
/*     */     
/* 299 */     ICSMove[] moves = (ICSMove[])null;
/* 300 */     if (tmplist.size() > 0) {
/* 301 */       moves = new ICSMove[tmplist.size()];
/* 302 */       for (i = 0; i < tmplist.size(); i++) {
/* 303 */         moves[i] = ((ICSMove)tmplist.get(i));
/*     */       }
/*     */     }
/* 306 */     evt.setMoves(moves);
/*     */   }
/*     */   
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 312 */     if (event.getEventType() == 0) {
/* 313 */       return event.getMessage();
/*     */     }
/* 315 */     ICSMoveListEvent evt = (ICSMoveListEvent)event;
/* 316 */     StringBuffer sb = new StringBuffer(20);
/*     */     
/* 318 */     if (evt.isFake()) { sb.append(":");
/*     */     }
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
/* 351 */     sb.append("Movelist for game ").append(evt.getBoardNumber()).append(":\n\n").append(evt.getWhitePlayer()).append(" (").append(evt.getWhiteRating()).append(") vs. ").append(evt.getBlackPlayer()).append(" (").append(evt.getBlackRating()).append(") --- ").append(evt.getDate()).append("\n");
/*     */     
/* 353 */     if (evt.isRated()) {
/* 354 */       sb.append("Rated ");
/*     */     } else {
/* 356 */       sb.append("Unrated ");
/*     */     }
/*     */     
/* 359 */     sb.append(evt.getVariant()).append(" match, initial time:");
/*     */     
/* 361 */     int time = evt.getInitialTime();
/* 362 */     if (time < 100)
/* 363 */       sb.append(" ");
/* 364 */     if (time < 10) {
/* 365 */       sb.append(" ");
/*     */     }
/* 367 */     sb.append(time).append(" minutes, increment:");
/*     */     
/* 369 */     int incr = evt.getIncrement();
/* 370 */     if (incr < 100)
/* 371 */       sb.append(" ");
/* 372 */     if (incr < 10) {
/* 373 */       sb.append(" ");
/*     */     }
/* 375 */     sb.append(incr).append(" seconds.\n\n");
/*     */     
/*     */ 
/* 378 */     sb.append("Move  ");
/* 379 */     pad(sb, evt.getWhitePlayer(), 21, false);
/* 380 */     sb.append("   ")
/* 381 */       .append(evt.getBlackPlayer())
/* 382 */       .append("\n----  ---------------------   ---------------------\n");
/*     */     
/*     */ 
/* 385 */     ICSMove[] moves = evt.getMoves();
/* 386 */     if (moves != null)
/*     */     {
/* 388 */       if (moves[0].isBlack()) {
/* 389 */         sb.append("  1.  ...     (0:00.000)      ");
/*     */       }
/* 391 */       for (int i = 0; i < moves.length; i++) {
/* 392 */         if (!moves[i].isBlack()) {
/* 393 */           pad(sb, moves[i].getMoveNumber(), 3, true);
/* 394 */           sb.append(".  ");
/*     */         }
/*     */         else {
/* 397 */           sb.append("   ");
/*     */         }
/* 399 */         pad(sb, moves[i].getSAN(), 8, false);
/*     */         
/* 401 */         if ((!moves[i].isBlack()) && (i < moves.length - 1)) {
/* 402 */           pad(sb, "(" + moves[i].getMoveTimeAsString() + ")", 13, false);
/*     */         }
/*     */         else
/*     */         {
/* 406 */           sb.append("(").append(moves[i].getMoveTimeAsString()).append(")\n");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 415 */     sb.append("      {").append(evt.getStatus()).append("} ").append(evt.getResult()).append("\n");
/*     */     
/* 417 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected void pad(StringBuffer sb, String str, int max, boolean rj) {
/* 421 */     int len = str.length();
/*     */     
/* 423 */     if (!rj) { sb.append(str);
/*     */     }
/* 425 */     for (int i = len; i < max; i++) {
/* 426 */       sb.append(" ");
/*     */     }
/* 428 */     if (rj) sb.append(str);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSMoveListParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */