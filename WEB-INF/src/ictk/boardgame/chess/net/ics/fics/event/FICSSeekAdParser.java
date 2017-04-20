/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSSeekAdEvent;
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
/*     */ public class FICSSeekAdParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^(<s(n)?>\\s(\\d+)\\sw=([\\w]+)\\sti=(\\d+)\\srt=(\\d+[\\sPE])\\st=(\\d+)\\si=(\\d+)\\sr=([ur])\\stp=([\\S]+)\\sc=([BW\\?])\\srr=(\\d+)-(\\d+)\\sa=([tf])\\sf=([tf]))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */     8);
/*     */   
/*  82 */   public static FICSSeekAdParser singleton = new FICSSeekAdParser();
/*     */   
/*     */ 
/*     */   protected FICSSeekAdParser()
/*     */   {
/*  87 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  92 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  97 */     ICSEvent evt = new ICSSeekAdEvent();
/*  98 */     assignMatches(match, evt);
/*     */     
/* 100 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/* 105 */     ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
/*     */     
/* 107 */     if (this.debug) {
/* 108 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 113 */       evt.setAdNumber(Integer.parseInt(m.group(3)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 116 */       Log.error(3, 
/* 117 */         "Can't parse number for: " + 
/* 118 */         m.group(3) + 
/* 119 */         " of " + m.group(0));
/* 120 */       evt.setEventType(0);
/* 121 */       evt.setMessage(m.group(0));
/*     */       
/* 123 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 124 */       return;
/*     */     }
/*     */     
/*     */ 
/* 128 */     evt.setPlayer(m.group(4));
/*     */     
/*     */ 
/* 131 */     evt.setRating(parseICSRating(m, 6));
/*     */     
/*     */     try
/*     */     {
/* 135 */       evt.setInitialTime(Integer.parseInt(m.group(7)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 138 */       Log.error(3, 
/* 139 */         "Can't parse time for: " + 
/* 140 */         m.group(7) + 
/* 141 */         " of " + m.group(0));
/* 142 */       evt.setEventType(0);
/* 143 */       evt.setMessage(m.group(0));
/*     */       
/* 145 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 146 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 151 */       evt.setIncrement(Integer.parseInt(m.group(8)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 154 */       Log.error(3, 
/* 155 */         "Can't parse incr for: " + 
/* 156 */         m.group(8) + 
/* 157 */         " of " + m.group(0));
/* 158 */       evt.setEventType(0);
/* 159 */       evt.setMessage(m.group(0));
/*     */       
/* 161 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 162 */       return;
/*     */     }
/*     */     
/*     */ 
/* 166 */     evt.setVariant(new ICSVariant(m.group(10)));
/*     */     
/*     */     try
/*     */     {
/* 170 */       evt.setRatingRangeLow(Integer.parseInt(m.group(12)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 173 */       Log.error(3, 
/* 174 */         "Can't parse rangeLow for: " + 
/* 175 */         m.group(12) + 
/* 176 */         " of " + m.group(0));
/* 177 */       evt.setEventType(0);
/* 178 */       evt.setMessage(m.group(0));
/*     */       
/* 180 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 181 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 186 */       evt.setRatingRangeHigh(Integer.parseInt(m.group(13)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 189 */       Log.error(3, 
/* 190 */         "Can't parse rangeHigh for: " + 
/* 191 */         m.group(13) + 
/* 192 */         " of " + m.group(0));
/* 193 */       evt.setEventType(0);
/* 194 */       evt.setMessage(m.group(0));
/*     */       
/* 196 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 197 */       return;
/*     */     }
/*     */     
/*     */ 
/* 201 */     if (m.group(2) == null) {
/* 202 */       evt.meetsFormula(true);
/* 203 */     } else if (m.group(2).charAt(0) == 'n') {
/* 204 */       evt.meetsFormula(false);
/*     */     } else {
/* 206 */       Log.error(3, 
/* 207 */         "Received unknown character in <s[n]?> area: " + 
/* 208 */         m.group(2) + " of " + m.group(0));
/*     */     }
/*     */     
/* 211 */     evt.setRated(m.group(9).charAt(0) == 'r');
/*     */     
/*     */ 
/* 214 */     switch (m.group(11).charAt(0)) {
/*     */     case '?': 
/* 216 */       evt.setColor(0); break;
/*     */     case 'W': 
/* 218 */       evt.setColor(1); break;
/*     */     case 'B': 
/* 220 */       evt.setColor(2); break;
/*     */     default: 
/* 222 */       Log.error(3, 
/* 223 */         "Received unknown character in c=[WB\\?] area: " + 
/* 224 */         m.group(11) + " of " + m.group(0));
/*     */     }
/*     */     
/*     */     
/* 228 */     evt.setManual(m.group(14).charAt(0) == 'f');
/*     */     
/*     */ 
/* 231 */     evt.setRestrictedByFormula(m.group(15).charAt(0) == 't');
/*     */     
/*     */ 
/* 234 */     int acct = 0;
/*     */     try {
/* 236 */       acct = Integer.parseInt(m.group(5), 16);
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 239 */       Log.error(3, 
/* 240 */         "Can't parser number " + 
/* 241 */         m.group(5) + " of " + m.group(0));
/* 242 */       evt.setEventType(0);
/* 243 */       evt.setMessage(m.group(0));
/* 244 */       return;
/*     */     }
/*     */     
/* 247 */     ICSAccountType accttype = new ICSAccountType();
/*     */     
/* 249 */     evt.setAccountType(accttype);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 256 */     if (event.getEventType() == 0) {
/* 257 */       return event.getMessage();
/*     */     }
/* 259 */     ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
/* 260 */     StringBuffer sb = new StringBuffer(80);
/*     */     
/* 262 */     sb.append("<s");
/* 263 */     if (!evt.meetsFormula()) {
/* 264 */       sb.append("n");
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
/* 282 */     sb.append("> ").append(evt.getAdNumber()).append(" w=").append(evt.getPlayer()).append(" ti=").append("00").append(" rt=").append(evt.getRating()).append(" t=").append(evt.getInitialTime()).append(" i=").append(evt.getIncrement()).append(" r=").append(evt.isRated() ? 'r' : 'u').append(" tp=").append(evt.getVariant()).append(" c=");
/* 283 */     switch (evt.getColor()) {
/*     */     case 0: 
/* 285 */       sb.append("?"); break;
/*     */     case 1: 
/* 287 */       sb.append("W"); break;
/*     */     case 2: 
/* 289 */       sb.append("B");
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 298 */     sb.append(" rr=").append(evt.getRatingRangeLow()).append("-").append(evt.getRatingRangeHigh()).append(" a=").append(evt.isManual() ? 'f' : 't').append(" f=").append(evt.isRestrictedByFormula() ? 't' : 'f');
/*     */     
/*     */ 
/* 301 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSSeekAdParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */