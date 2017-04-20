/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSSeekClearEvent;
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
/*     */ public class FICSSeekClearParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^(<sc>)", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  56 */     8);
/*     */   
/*  58 */   public static FICSSeekClearParser singleton = new FICSSeekClearParser();
/*     */   
/*     */ 
/*     */   protected FICSSeekClearParser()
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
/*  73 */     ICSEvent evt = new ICSSeekClearEvent();
/*  74 */     assignMatches(match, evt);
/*     */     
/*  76 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  81 */     ICSSeekClearEvent evt = (ICSSeekClearEvent)event;
/*     */     
/*  83 */     if (this.debug) {
/*  84 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/*  94 */     if (event.getEventType() == 0) {
/*  95 */       return event.getMessage();
/*     */     }
/*  97 */     ICSSeekClearEvent evt = (ICSSeekClearEvent)event;
/*  98 */     StringBuffer sb = new StringBuffer(5);
/*     */     
/* 100 */     sb.append("<sc>");
/*     */     
/*     */ 
/* 103 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSSeekClearParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */