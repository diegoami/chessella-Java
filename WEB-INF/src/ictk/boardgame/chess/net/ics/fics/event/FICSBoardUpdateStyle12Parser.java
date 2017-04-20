/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSBoardUpdateEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.util.Log;
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
/*     */ public class FICSBoardUpdateStyle12Parser
/*     */   extends ICSEventParser
/*     */ {
/*  54 */   public static final Pattern masterPattern = Pattern.compile("^:?(<12>\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([rnbqkpRNBQKP-]{8})\\s([BW])\\s(-?[0-7])\\s([01])\\s([01])\\s([01])\\s([01])\\s(\\d+)\\s(\\d+)\\s([\\w]+)\\s([\\w]+)\\s([-]?[0-3])\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(-?\\d+)\\s(-?\\d+)\\s(\\d+)\\s(\\S+)\\s\\((\\d+):(\\d+)\\.?(\\d+)?\\)\\s(\\S+)\\s([01])\\s([01])\\s(\\d+))", 
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     8);
/*     */   
/* 123 */   public static FICSBoardUpdateStyle12Parser singleton = new FICSBoardUpdateStyle12Parser();
/*     */   
/*     */ 
/*     */   protected FICSBoardUpdateStyle12Parser()
/*     */   {
/* 128 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/* 133 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/* 138 */     ICSEvent evt = new ICSBoardUpdateEvent();
/* 139 */     assignMatches(match, evt);
/*     */     
/* 141 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/* 146 */     ICSBoardUpdateEvent evt = (ICSBoardUpdateEvent)event;
/*     */     
/* 148 */     if (this.debug) {
/* 149 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/* 151 */     int rank = 7;
/* 152 */     int gr = 2;
/* 153 */     String srank = null;
/* 154 */     char[][] board = new char[8][8];
/*     */     
/* 156 */     while ((rank < 8) && (rank >= 0)) {
/* 157 */       srank = m.group(gr);
/* 158 */       for (int file = 0; file < 8; file++) {
/* 159 */         if (srank.charAt(file) != '-') {
/* 160 */           board[file][rank] = srank.charAt(file);
/*     */         } else
/* 162 */           board[file][rank] = 32;
/*     */       }
/* 164 */       rank--;
/* 165 */       gr++;
/*     */     }
/* 167 */     evt.setBoardArray(board);
/*     */     
/* 169 */     if (m.group(10).charAt(0) == 'B') {
/* 170 */       evt.setBlackMove(true);
/*     */     }
/* 172 */     evt.setWhitePlayer(m.group(18));
/* 173 */     evt.setBlackPlayer(m.group(19));
/*     */     
/* 175 */     evt.setWhiteCastleableKingside(m.group(12).charAt(0) == '1');
/* 176 */     evt.setWhiteCastleableQueenside(m.group(13).charAt(0) == '1');
/* 177 */     evt.setBlackCastleableKingside(m.group(14).charAt(0) == '1');
/* 178 */     evt.setBlackCastleableQueenside(m.group(15).charAt(0) == '1');
/*     */     
/* 180 */     evt.setVerboseMove(m.group(28));
/*     */     
/* 182 */     evt.setSAN(m.group(32));
/* 183 */     evt.setFlipBoard(m.group(33).charAt(0) == '1');
/* 184 */     evt.setClockMoving(m.group(34).charAt(0) == '1');
/*     */     
/*     */ 
/* 187 */     int i = 0;
/*     */     try {
/* 189 */       evt.setEnPassantFile(
/* 190 */         Integer.parseInt(m.group(i = 11)) + 1);
/* 191 */       evt.setPlySinceLastIrreversableMove(
/* 192 */         Integer.parseInt(m.group(i = 16)));
/* 193 */       evt.setBoardNumber(Integer.parseInt(m.group(i = 17)));
/* 194 */       evt.setRelation(Integer.parseInt(m.group(i = 20)));
/* 195 */       evt.setInitialTime(Integer.parseInt(m.group(i = 21)));
/* 196 */       evt.setIncrement(Integer.parseInt(m.group(i = 22)));
/* 197 */       evt.setWhiteMaterial(Integer.parseInt(m.group(i = 23)));
/* 198 */       evt.setBlackMaterial(Integer.parseInt(m.group(i = 24)));
/* 199 */       evt.setWhiteClock(Integer.parseInt(m.group(i = 25)));
/* 200 */       evt.setBlackClock(Integer.parseInt(m.group(i = 26)));
/* 201 */       evt.setMoveNumber(Integer.parseInt(m.group(i = 27)));
/*     */       
/* 203 */       evt.setMoveTime(Integer.parseInt(m.group(i = 29)) * 60000 + 
/* 204 */         Integer.parseInt(m.group(i = 30)) * 1000 + 
/* 205 */         Integer.parseInt(m.group(i = 31)));
/*     */       
/* 207 */       evt.setLag(Integer.parseInt(m.group(i = 35)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 210 */       Log.error(3, 
/* 211 */         "threw NumberFormatExceptionfor(" + 
/* 212 */         i + "): " + m.group(i));
/* 213 */       evt.setEventType(0);
/* 214 */       evt.setMessage(m.group(0));
/*     */       
/* 216 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 217 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 224 */     if (event.getEventType() == 0) {
/* 225 */       return event.getMessage();
/*     */     }
/* 227 */     ICSBoardUpdateEvent evt = (ICSBoardUpdateEvent)event;
/* 228 */     StringBuffer sb = new StringBuffer(180);
/*     */     
/* 230 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/* 232 */     sb.append("<12> ");
/*     */     
/*     */ 
/* 235 */     char[][] board = evt.getBoardArray();
/*     */     
/* 237 */     for (int r = 7; r >= 0; r--) {
/* 238 */       for (int f = 0; f < board.length; f++) {
/* 239 */         if (board[f][r] == ' ') {
/* 240 */           sb.append('-');
/*     */         } else
/* 242 */           sb.append(board[f][r]);
/*     */       }
/* 244 */       sb.append(' ');
/*     */     }
/*     */     
/*     */ 
/* 248 */     if (evt.isBlackMove()) {
/* 249 */       sb.append('B');
/*     */     } else {
/* 251 */       sb.append('W');
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
/* 291 */     sb.append(" ").append(evt.getEnPassantFile() - 1).append(evt.isWhiteCastleableKingside() ? " 1" : " 0").append(evt.isWhiteCastleableQueenside() ? " 1" : " 0").append(evt.isBlackCastleableKingside() ? " 1" : " 0").append(evt.isBlackCastleableQueenside() ? " 1" : " 0").append(" ").append(evt.getPlySinceLastIrreversableMove()).append(" ").append(evt.getBoardNumber()).append(" ").append(evt.getWhitePlayer()).append(" ").append(evt.getBlackPlayer()).append(" ").append(evt.getRelation()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement()).append(" ").append(evt.getWhiteMaterial()).append(" ").append(evt.getBlackMaterial()).append(" ").append(evt.getWhiteClock()).append(" ").append(evt.getBlackClock()).append(" ").append(evt.getMoveNumber()).append(" ").append(evt.getVerboseMove()).append(" (").append(getClockAsString(evt.getMoveTime(), true)).append(") ").append(evt.getSAN()).append(evt.isFlipBoard() ? " 1" : " 0").append(evt.isClockMoving() ? " 1 " : " 0 ").append(evt.getLag());
/*     */     
/* 293 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected String getClockAsString(int clock, boolean move) {
/* 297 */     StringBuffer sb = new StringBuffer(7);
/*     */     
/*     */ 
/* 300 */     int h = clock / 3600000;
/* 301 */     int m = clock % 3600000 / 60000;
/* 302 */     int s = clock % 60000 / 1000;
/* 303 */     int ms = clock % 1000;
/*     */     
/* 305 */     if ((move) && (h > 1)) {
/* 306 */       sb.append(h).append(":");
/* 307 */       if (m < 10)
/* 308 */         sb.append(0);
/*     */     }
/* 310 */     sb.append(m).append(":");
/* 311 */     if (s < 10)
/* 312 */       sb.append(0);
/* 313 */     sb.append(s).append(".");
/* 314 */     if (ms < 100)
/* 315 */       sb.append(0);
/* 316 */     if (ms < 10)
/* 317 */       sb.append(0);
/* 318 */     sb.append(ms);
/* 319 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSBoardUpdateStyle12Parser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */