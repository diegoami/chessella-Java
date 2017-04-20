/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSTellEvent;
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
/*     */ public class FICSTellParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(([\\w]+)((?:\\([A-Z*]+\\))*)\\s(tells\\syou|says):\\s((?:.|\\s+\\\\|\\s+:)*))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */     8);
/*     */   
/*  60 */   public static FICSTellParser singleton = new FICSTellParser();
/*     */   
/*     */ 
/*     */   protected FICSTellParser()
/*     */   {
/*  65 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  70 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  75 */     ICSEvent evt = new ICSTellEvent();
/*  76 */     assignMatches(match, evt);
/*     */     
/*  78 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  83 */     ICSTellEvent evt = (ICSTellEvent)event;
/*     */     
/*  85 */     if (this.debug) {
/*  86 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  89 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/*  92 */     evt.setPlayer(m.group(2));
/*     */     
/*     */ 
/*  95 */     evt.setAccountType(parseICSAccountType(m, 3));
/*     */     
/*     */ 
/*  98 */     evt.setMessage(m.group(5));
/*     */     
/* 100 */     if ("tells you".equals(m.group(4))) {
/* 101 */       evt.setEventType(9);
/*     */     }
/* 103 */     else if ("says".equals(m.group(4))) {
/* 104 */       evt.setEventType(10);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 112 */     if (event.getEventType() == 0) {
/* 113 */       return event.getMessage();
/*     */     }
/* 115 */     ICSTellEvent evt = (ICSTellEvent)event;
/* 116 */     StringBuffer sb = new StringBuffer(20);
/*     */     
/* 118 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/*     */     
/* 121 */     sb.append(evt.getPlayer()).append(evt.getAccountType());
/*     */     
/* 123 */     switch (evt.getEventType()) {
/*     */     case 9: 
/* 125 */       sb.append(" tells you: ");
/* 126 */       break;
/*     */     
/*     */     case 10: 
/* 129 */       sb.append(" says: ");
/*     */     }
/*     */     
/*     */     
/* 133 */     sb.append(evt.getMessage());
/*     */     
/*     */ 
/* 136 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSTellParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */