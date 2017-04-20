/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSPlayerConnectionEvent;
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
/*     */ public class FICSPlayerConnectionParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^(\\[([\\w]+)\\shas\\s(connected|disconnected)\\.\\])", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */     8);
/*     */   
/*  61 */   public static FICSPlayerConnectionParser singleton = new FICSPlayerConnectionParser();
/*     */   
/*     */ 
/*     */   protected FICSPlayerConnectionParser()
/*     */   {
/*  66 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  71 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  76 */     ICSEvent evt = new ICSPlayerConnectionEvent();
/*  77 */     assignMatches(match, evt);
/*     */     
/*  79 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  84 */     ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
/*     */     
/*  86 */     if (this.debug) {
/*  87 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*     */ 
/*  91 */     evt.setPlayer(m.group(2));
/*     */     
/*  93 */     evt.setConnected("connected".equals(m.group(3)));
/*  94 */     evt.setNotification(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 101 */     if (event.getEventType() == 0) {
/* 102 */       return event.getMessage();
/*     */     }
/* 104 */     ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
/* 105 */     StringBuffer sb = new StringBuffer(50);
/*     */     
/* 107 */     sb.append("[")
/* 108 */       .append(evt.getPlayer())
/* 109 */       .append(" has ");
/* 110 */     if (evt.isConnected()) {
/* 111 */       sb.append("connected");
/*     */     } else
/* 113 */       sb.append("disconnected");
/* 114 */     sb.append(".]");
/*     */     
/*     */ 
/* 117 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSPlayerConnectionParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */