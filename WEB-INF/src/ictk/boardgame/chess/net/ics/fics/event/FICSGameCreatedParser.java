/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSGameCreatedEvent;
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
/*     */ public class FICSGameCreatedParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(\\{Game\\s(\\d+)\\s\\(([\\w]+)\\svs\\.\\s([\\w]+)\\)\\s(Creating|Continuing)\\s(rated|unrated)\\s(\\S+)\\smatch\\.})", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  67 */     8);
/*     */   
/*  69 */   public static FICSGameCreatedParser singleton = new FICSGameCreatedParser();
/*     */   
/*     */ 
/*     */   protected FICSGameCreatedParser()
/*     */   {
/*  74 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  79 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  84 */     ICSEvent evt = new ICSGameCreatedEvent();
/*  85 */     assignMatches(match, evt);
/*     */     
/*  87 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  92 */     ICSGameCreatedEvent evt = (ICSGameCreatedEvent)event;
/*     */     
/*  94 */     if (this.debug) {
/*  95 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  98 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */     try
/*     */     {
/* 102 */       evt.setBoardNumber(Integer.parseInt(m.group(2)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 105 */       Log.error(3, 
/* 106 */         "Can't parse boardNumber for: " + 
/* 107 */         m.group(2) + 
/* 108 */         " of " + m.group(0));
/* 109 */       evt.setEventType(0);
/* 110 */       evt.setMessage(m.group(0));
/*     */       
/* 112 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 113 */       return;
/*     */     }
/*     */     
/*     */ 
/* 117 */     evt.setWhitePlayer(m.group(3));
/*     */     
/*     */ 
/* 120 */     evt.setBlackPlayer(m.group(4));
/*     */     
/*     */ 
/* 123 */     evt.setVariant(new ICSVariant(m.group(7)));
/*     */     
/* 125 */     evt.setContinued("Continuing".equals(m.group(5)));
/* 126 */     evt.setRated("rated".equals(m.group(6)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 133 */     if (event.getEventType() == 0) {
/* 134 */       return event.getMessage();
/*     */     }
/* 136 */     ICSGameCreatedEvent evt = (ICSGameCreatedEvent)event;
/* 137 */     StringBuffer sb = new StringBuffer(50);
/*     */     
/* 139 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */     sb.append("{Game ").append(evt.getBoardNumber()).append(" (").append(evt.getWhitePlayer()).append(" vs. ").append(evt.getBlackPlayer()).append(") ");
/*     */     
/* 149 */     if (evt.isContinued()) {
/* 150 */       sb.append("Continuing ");
/*     */     } else {
/* 152 */       sb.append("Creating ");
/*     */     }
/* 154 */     if (!evt.isRated()) {
/* 155 */       sb.append("un");
/*     */     }
/*     */     
/*     */ 
/* 159 */     sb.append("rated ").append(evt.getVariant()).append(" match.}");
/*     */     
/*     */ 
/* 162 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSGameCreatedParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */