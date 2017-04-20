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
/*     */ public class FICSPlayerNotificationParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(Notification:\\s([\\w]+)((?:\\([A-Z*]+\\))*)\\shas\\s(arrived|departed)(\\sand\\sisn't\\son\\syour\\snotify\\slist)?\\.)", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */     8);
/*     */   
/*  63 */   public static FICSPlayerNotificationParser singleton = new FICSPlayerNotificationParser();
/*     */   
/*     */ 
/*     */   protected FICSPlayerNotificationParser()
/*     */   {
/*  68 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  73 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  78 */     ICSEvent evt = new ICSPlayerConnectionEvent();
/*  79 */     assignMatches(match, evt);
/*     */     
/*  81 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  86 */     ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
/*     */     
/*  88 */     if (this.debug) {
/*  89 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  92 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/*  95 */     evt.setPlayer(m.group(2));
/*     */     
/*     */ 
/*  98 */     evt.setAccountType(parseICSAccountType(m, 3));
/*     */     
/* 100 */     evt.setConnected("arrived".equals(m.group(4)));
/* 101 */     evt.setNotification(true);
/* 102 */     evt.setOnNotifyList(m.group(5) != null);
/* 103 */     evt.setEventType(24);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 110 */     if (event.getEventType() == 0) {
/* 111 */       return event.getMessage();
/*     */     }
/* 113 */     ICSPlayerConnectionEvent evt = (ICSPlayerConnectionEvent)event;
/* 114 */     StringBuffer sb = new StringBuffer(40);
/*     */     
/* 116 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 121 */     sb.append("Notification: ").append(evt.getPlayer()).append(evt.getAccountType()).append(" has ");
/*     */     
/* 123 */     if (evt.isConnected()) {
/* 124 */       sb.append("arrived");
/*     */     } else {
/* 126 */       sb.append("departed");
/*     */     }
/* 128 */     if (evt.isOnNotifyList()) {
/* 129 */       sb.append(" and isn't on your notify list");
/*     */     }
/* 131 */     sb.append(".");
/*     */     
/*     */ 
/* 134 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSPlayerNotificationParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */