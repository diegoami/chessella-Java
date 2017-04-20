/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSResult;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSGameResultEvent;
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
/*     */ public class FICSGameResultParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(\\{Game\\s(\\d+)\\s\\(([\\w]+)\\svs\\.\\s([\\w]+)\\)\\s([^}]+)\\}\\s(\\S+))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  64 */     8);
/*     */   
/*  66 */   public static FICSGameResultParser singleton = new FICSGameResultParser();
/*     */   
/*     */ 
/*     */   protected FICSGameResultParser()
/*     */   {
/*  71 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  76 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  81 */     ICSEvent evt = new ICSGameResultEvent();
/*  82 */     assignMatches(match, evt);
/*     */     
/*  84 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  89 */     ICSGameResultEvent evt = (ICSGameResultEvent)event;
/*     */     
/*  91 */     if (this.debug) {
/*  92 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  95 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */     try
/*     */     {
/*  99 */       evt.setBoardNumber(Integer.parseInt(m.group(2)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 102 */       Log.error(3, 
/* 103 */         "Can't parse boardNumber for: " + 
/* 104 */         m.group(2) + 
/* 105 */         " of " + m.group(0));
/* 106 */       evt.setEventType(0);
/* 107 */       evt.setMessage(m.group(0));
/*     */       
/* 109 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 110 */       return;
/*     */     }
/*     */     
/*     */ 
/* 114 */     evt.setWhitePlayer(m.group(3));
/*     */     
/*     */ 
/* 117 */     evt.setBlackPlayer(m.group(4));
/*     */     
/*     */ 
/* 120 */     evt.setResult(new ICSResult(m.group(6)));
/*     */     
/* 122 */     evt.getResult().setDescription(m.group(5));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 129 */     if (event.getEventType() == 0) {
/* 130 */       return event.getMessage();
/*     */     }
/* 132 */     ICSGameResultEvent evt = (ICSGameResultEvent)event;
/* 133 */     StringBuffer sb = new StringBuffer(40);
/*     */     
/* 135 */     if (evt.isFake()) { sb.append(":");
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
/* 146 */     sb.append("{Game ").append(evt.getBoardNumber()).append(" (").append(evt.getWhitePlayer()).append(" vs. ").append(evt.getBlackPlayer()).append(") ").append(evt.getResult().getDescription()).append("} ").append(evt.getResult());
/*     */     
/*     */ 
/* 149 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSGameResultParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */