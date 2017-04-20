/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
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
/*     */ public class FICSSeekAdReadableParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^(([\\w]+)((?:\\([A-Z*]+\\))*)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\sseeking\\s(\\d+)\\s(\\d+)\\s(rated|unrated)\\s(\\S+)(?:\\s\\[(\\w+)\\])?(?:\\s(m))?(?:\\s(f))?\\s\\(\"play\\s(\\d+)\"\\sto\\srespond\\))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */     8);
/*     */   
/*  74 */   public static FICSSeekAdReadableParser singleton = new FICSSeekAdReadableParser();
/*     */   
/*     */ 
/*     */   protected FICSSeekAdReadableParser()
/*     */   {
/*  79 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  84 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  89 */     ICSEvent evt = new ICSSeekAdEvent();
/*  90 */     assignMatches(match, evt);
/*     */     
/*  92 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  97 */     ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
/*     */     
/*  99 */     if (this.debug) {
/* 100 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*     */ 
/* 104 */     evt.setPlayer(m.group(2));
/*     */     
/*     */ 
/* 107 */     evt.setAccountType(parseICSAccountType(m, 3));
/*     */     
/*     */ 
/* 110 */     evt.setRating(parseICSRating(m, 4));
/*     */     
/*     */     try
/*     */     {
/* 114 */       evt.setInitialTime(Integer.parseInt(m.group(5)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 117 */       Log.error(3, 
/* 118 */         "Can't parse time for: " + 
/* 119 */         m.group(5) + 
/* 120 */         " of " + m.group(0));
/* 121 */       evt.setEventType(0);
/* 122 */       evt.setMessage(m.group(0));
/*     */       
/* 124 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 125 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 130 */       evt.setIncrement(Integer.parseInt(m.group(6)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 133 */       Log.error(3, 
/* 134 */         "Can't parse incr for: " + 
/* 135 */         m.group(6) + 
/* 136 */         " of " + m.group(0));
/* 137 */       evt.setEventType(0);
/* 138 */       evt.setMessage(m.group(0));
/*     */       
/* 140 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 141 */       return;
/*     */     }
/*     */     
/*     */ 
/* 145 */     evt.setVariant(new ICSVariant(m.group(8)));
/*     */     
/*     */     try
/*     */     {
/* 149 */       evt.setAdNumber(Integer.parseInt(m.group(12)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 152 */       Log.error(3, 
/* 153 */         "Can't parse number for: " + 
/* 154 */         m.group(12) + 
/* 155 */         " of " + m.group(0));
/* 156 */       evt.setEventType(0);
/* 157 */       evt.setMessage(m.group(0));
/*     */       
/* 159 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 160 */       return;
/*     */     }
/*     */     
/*     */ 
/* 164 */     evt.setEventType(14);
/*     */     
/*     */ 
/* 167 */     evt.setRated(m.group(7).equals("rated"));
/*     */     
/*     */ 
/* 170 */     if (m.group(9) == null) {
/* 171 */       evt.setColor(0);
/* 172 */     } else if (m.group(9).equals("white")) {
/* 173 */       evt.setColor(1);
/*     */     } else {
/* 175 */       evt.setColor(2);
/*     */     }
/*     */     
/* 178 */     evt.setManual(m.group(10) != null);
/*     */     
/*     */ 
/* 181 */     evt.setRestrictedByFormula(m.group(11) != null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 188 */     if (event.getEventType() == 0) {
/* 189 */       return event.getMessage();
/*     */     }
/* 191 */     ICSSeekAdEvent evt = (ICSSeekAdEvent)event;
/* 192 */     StringBuffer sb = new StringBuffer(80);
/*     */     
/* 194 */     sb.append(evt.getPlayer())
/* 195 */       .append(evt.getAccountType())
/* 196 */       .append(" (");
/*     */     
/* 198 */     int rating = evt.getRating().get();
/*     */     
/* 200 */     if ((!evt.getRating().isNotSet()) && 
/* 201 */       (!evt.getRating().isNotApplicable())) {
/* 202 */       if (rating < 1000)
/* 203 */         sb.append(" ");
/* 204 */       if (rating < 100)
/* 205 */         sb.append(" ");
/* 206 */       if (rating < 10) {
/* 207 */         sb.append(" ");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 214 */     sb.append(evt.getRating()).append(") seeking ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement());
/*     */     
/* 216 */     if (evt.isRated()) {
/* 217 */       sb.append(" rated ");
/*     */     } else {
/* 219 */       sb.append(" unrated ");
/*     */     }
/* 221 */     sb.append(evt.getVariant());
/*     */     
/* 223 */     if (evt.getColor() == 1) {
/* 224 */       sb.append(" [white]");
/* 225 */     } else if (evt.getColor() == 2) {
/* 226 */       sb.append(" [black]");
/*     */     }
/* 228 */     if (evt.isManual()) {
/* 229 */       sb.append(" m");
/*     */     }
/* 231 */     if (evt.isRestrictedByFormula()) {
/* 232 */       sb.append(" f");
/*     */     }
/*     */     
/*     */ 
/* 236 */     sb.append(" (\"play ").append(evt.getAdNumber()).append("\" to respond)");
/*     */     
/*     */ 
/* 239 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSSeekAdReadableParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */