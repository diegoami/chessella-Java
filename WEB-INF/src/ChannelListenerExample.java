/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChannelListenerExample
/*     */   implements ICSEventListener
/*     */ {
/*  38 */   public boolean showTimestamp = true;
/*  39 */   public static Calendar cal = new GregorianCalendar();
/*     */   
/*     */   public static final char ESC = '\033';
/*     */   public static final String BLACK = "\033[0;30";
/*     */   public static final String YELLOW = "\033[0;33m";
/*     */   public static final String CYAN = "\033[0;36m";
/*     */   public static final String BOLD_BLACK = "\033[1;30m";
/*     */   public static final String BOLD_RED = "\033[1;31m";
/*     */   public static final String BOLD_YELLOW = "\033[1;33m";
/*     */   public static final String BOLD_CYAN = "\033[1;36m";
/*     */   public static final String PLAIN = "\033[0;m";
/*     */   
/*     */   public void icsEventDispatched(ICSEvent evt)
/*     */   {
/*  53 */     ICSChannelEvent chEvt = (ICSChannelEvent)evt;
/*  54 */     String prefix = null;
/*     */     
/*  56 */     if (this.showTimestamp) {
/*  57 */       System.out.print("\033[1;30m" + 
/*  58 */         getTimestampAsString(evt.getTimestamp()) + 
/*  59 */         "\033[0;m");
/*     */     }
/*     */     
/*  62 */     System.out.print("<" + evt.getEventType() + ">");
/*     */     
/*  64 */     switch (chEvt.getChannel()) {
/*     */     case 1: 
/*  66 */       System.out.print("\033[1;30m");
/*  67 */       System.out.print("[help]");
/*     */       
/*  69 */       if (chEvt.getAccountType().is(4)) {
/*  70 */         System.out.print("\033[1;33m");
/*  71 */       } else if (chEvt.getAccountType().is(5)) {
/*  72 */         System.out.print("\033[1;31m");
/*     */       } else {
/*  74 */         System.out.print("\033[0;m");
/*     */       }
/*  76 */       System.out.print(chEvt.getPlayer());
/*  77 */       System.out.print("\033[1;30m");
/*  78 */       System.out.print(": ");
/*  79 */       System.out.print("\033[0;36m");
/*  80 */       System.out.print(chEvt.getMessage());
/*  81 */       System.out.println("\033[0;m");
/*  82 */       break;
/*     */     default: 
/*  84 */       System.out.println("subscribed to unhandled channel [" + 
/*  85 */         chEvt.getChannel() + "]");
/*     */     }
/*  87 */     System.out.flush();
/*     */   }
/*     */   
/*     */   public String getTimestampAsString(Date date) {
/*  91 */     StringBuffer sb = new StringBuffer(5);
/*  92 */     int tmp = 0;
/*  93 */     cal.setTime(date);
/*     */     
/*  95 */     tmp = cal.get(11);
/*  96 */     if (tmp < 10)
/*  97 */       sb.append("0");
/*  98 */     sb.append(tmp).append(":");
/*     */     
/* 100 */     tmp = cal.get(12);
/* 101 */     if (tmp < 10)
/* 102 */       sb.append("0");
/* 103 */     sb.append(tmp);
/*     */     
/* 105 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ChannelListenerExample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */