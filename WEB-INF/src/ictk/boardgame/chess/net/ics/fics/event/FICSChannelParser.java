/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
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
/*     */ public class FICSChannelParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(([\\w]+)((?:\\([A-Z*]+\\))*)\\(([T])?(\\d+)\\):\\s((?:.|\\s+\\\\|\\s+:)*))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */     8);
/*     */   
/*  64 */   public static FICSChannelParser singleton = new FICSChannelParser();
/*     */   
/*     */ 
/*     */   protected FICSChannelParser()
/*     */   {
/*  69 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  74 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  79 */     ICSEvent evt = new ICSChannelEvent();
/*  80 */     assignMatches(match, evt);
/*     */     
/*  82 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  87 */     ICSChannelEvent evt = (ICSChannelEvent)event;
/*     */     
/*  89 */     if (this.debug) {
/*  90 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  93 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/*  96 */     evt.setPlayer(m.group(2));
/*     */     
/*     */ 
/*  99 */     evt.setAccountType(parseICSAccountType(m, 3));
/*     */     
/*     */     try
/*     */     {
/* 103 */       evt.setChannel(Integer.parseInt(m.group(5)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 106 */       Log.error(3, 
/* 107 */         "Can't parse channel for: " + 
/* 108 */         m.group(5) + 
/* 109 */         " of " + m.group(0));
/* 110 */       evt.setEventType(0);
/* 111 */       evt.setMessage(m.group(0));
/*     */       
/* 113 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 114 */       return;
/*     */     }
/*     */     
/*     */ 
/* 118 */     evt.setMessage(m.group(6));
/*     */     
/* 120 */     if ((m.group(4) != null) && 
/* 121 */       ("T".equals(m.group(4)))) {
/* 122 */       evt.setEventType(8);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 130 */     if (event.getEventType() == 0) {
/* 131 */       return event.getMessage();
/*     */     }
/* 133 */     ICSChannelEvent evt = (ICSChannelEvent)event;
/* 134 */     StringBuffer sb = new StringBuffer(80);
/*     */     
/* 136 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/* 138 */     String str = null;
/*     */     
/* 140 */     sb.append(evt.getPlayer())
/* 141 */       .append(evt.getAccountType());
/*     */     
/* 143 */     sb.append("(");
/*     */     
/* 145 */     if (evt.getEventType() == 8) {
/* 146 */       sb.append("T");
/*     */     }
/*     */     
/* 149 */     sb.append(evt.getChannel()).append("): ");
/*     */     
/* 151 */     sb.append(evt.getMessage());
/*     */     
/*     */ 
/* 154 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSChannelParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */