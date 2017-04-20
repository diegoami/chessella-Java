/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSGameNotificationEvent;
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
/*     */ public class FICSGameNotificationParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(Game\\snotification:\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\svs\\.\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(\\w+)\\s(\\S+)\\s(\\d+)\\s(\\d+):\\sGame\\s(\\d+))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  74 */   public static FICSGameNotificationParser singleton = new FICSGameNotificationParser();
/*     */   
/*     */ 
/*     */   protected FICSGameNotificationParser()
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
/*  89 */     ICSEvent evt = new ICSGameNotificationEvent();
/*  90 */     assignMatches(match, evt);
/*     */     
/*  92 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  97 */     ICSGameNotificationEvent evt = (ICSGameNotificationEvent)event;
/*     */     
/*  99 */     if (this.debug) {
/* 100 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/* 103 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/* 106 */     evt.setWhitePlayer(m.group(2));
/*     */     
/*     */ 
/* 109 */     evt.setWhiteRating(parseICSRating(m, 3));
/*     */     
/*     */ 
/* 112 */     evt.setBlackPlayer(m.group(4));
/*     */     
/*     */ 
/* 115 */     evt.setBlackRating(parseICSRating(m, 5));
/*     */     
/*     */ 
/* 118 */     evt.setVariant(new ICSVariant(m.group(7)));
/*     */     
/*     */     try
/*     */     {
/* 122 */       evt.setInitialTime(Integer.parseInt(m.group(8)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 125 */       Log.error(3, 
/* 126 */         "Can't parse time for: " + 
/* 127 */         m.group(8) + 
/* 128 */         " of " + m.group(0));
/* 129 */       evt.setEventType(0);
/* 130 */       evt.setMessage(m.group(0));
/*     */       
/* 132 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 133 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 138 */       evt.setIncrement(Integer.parseInt(m.group(9)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 141 */       Log.error(3, 
/* 142 */         "Can't parse incr for: " + 
/* 143 */         m.group(9) + 
/* 144 */         " of " + m.group(0));
/* 145 */       evt.setEventType(0);
/* 146 */       evt.setMessage(m.group(0));
/*     */       
/* 148 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 149 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 154 */       evt.setBoardNumber(Integer.parseInt(m.group(10)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 157 */       Log.error(3, 
/* 158 */         "Can't parse boardNumber for: " + 
/* 159 */         m.group(10) + 
/* 160 */         " of " + m.group(0));
/* 161 */       evt.setEventType(0);
/* 162 */       evt.setMessage(m.group(0));
/*     */       
/* 164 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 165 */       return;
/*     */     }
/*     */     
/* 168 */     evt.setRated("rated".equals(m.group(6)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 175 */     if (event.getEventType() == 0) {
/* 176 */       return event.getMessage();
/*     */     }
/* 178 */     ICSGameNotificationEvent evt = (ICSGameNotificationEvent)event;
/* 179 */     StringBuffer sb = new StringBuffer(50);
/*     */     
/* 181 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 191 */     sb.append("Game notification: ").append(evt.getWhitePlayer()).append(" (").append(evt.getWhiteRating()).append(") vs. ").append(evt.getBlackPlayer()).append(" (").append(evt.getBlackRating()).append(") ");
/*     */     
/* 193 */     if (!evt.isRated()) {
/* 194 */       sb.append("un");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 203 */     sb.append("rated ").append(evt.getVariant()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrement()).append(": Game ").append(evt.getBoardNumber());
/*     */     
/*     */ 
/* 206 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSGameNotificationParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */