/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSSeekRemoveEvent;
/*     */ import ictk.util.Log;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class FICSSeekRemoveParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^(<sr>((?:\\s\\d+)+))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  56 */     8);
/*     */   
/*  58 */   public static FICSSeekRemoveParser singleton = new FICSSeekRemoveParser();
/*     */   
/*     */ 
/*     */   protected FICSSeekRemoveParser()
/*     */   {
/*  63 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  68 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  73 */     ICSEvent evt = new ICSSeekRemoveEvent();
/*  74 */     assignMatches(match, evt);
/*     */     
/*  76 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  81 */     ICSSeekRemoveEvent evt = (ICSSeekRemoveEvent)event;
/*     */     
/*  83 */     if (this.debug) {
/*  84 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  87 */     StringTokenizer st = new StringTokenizer(m.group(2));
/*     */     
/*  89 */     int[] ads = new int[st.countTokens()];
/*  90 */     int i = 0;
/*     */     try {
/*  92 */       while (st.hasMoreTokens()) {
/*  93 */         ads[i] = Integer.parseInt(st.nextToken());
/*  94 */         i++;
/*     */       }
/*     */     }
/*     */     catch (NumberFormatException e) {
/*  98 */       Log.error(3, 
/*  99 */         "Can't parse ads[" + i + "] " + 
/* 100 */         "of " + m.group(0));
/* 101 */       evt.setEventType(0);
/* 102 */       evt.setMessage(m.group(0));
/*     */       
/* 104 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 105 */       return;
/*     */     }
/* 107 */     evt.setAds(ads);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 114 */     if (event.getEventType() == 0) {
/* 115 */       return event.getMessage();
/*     */     }
/* 117 */     ICSSeekRemoveEvent evt = (ICSSeekRemoveEvent)event;
/* 118 */     StringBuffer sb = new StringBuffer(5);
/*     */     
/* 120 */     int[] ads = evt.getAds();
/* 121 */     sb.append("<sr>");
/* 122 */     for (int i = 0; i < ads.length; i++)
/*     */     {
/* 124 */       sb.append(" ").append(ads[i]);
/*     */     }
/*     */     
/* 127 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSSeekRemoveParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */