/*     */ package ictk.boardgame.chess.net.ics.ui.cli;
/*     */ 
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
/*     */ public class ANSIConsole
/*     */   implements ICSEventListener
/*     */ {
/*  38 */   public boolean debug = false;
/*  39 */   protected boolean showTimestamp = true;
/*  40 */   protected static Calendar cal = new GregorianCalendar();
/*     */   
/*     */   public static final char ESC = '\033';
/*     */   
/*     */   public static final String BLACK = "[0;30";
/*     */   public static final String RED = "[0;31m";
/*     */   public static final String GREEN = "[0;32m";
/*     */   public static final String YELLOW = "[0;33m";
/*     */   public static final String BLUE = "[0;34m";
/*     */   public static final String MAGENTA = "[0;35m";
/*     */   public static final String CYAN = "[0;36m";
/*     */   public static final String WHITE = "[0;37m";
/*     */   public static final String BOLD_BLACK = "[1;30m";
/*     */   public static final String BOLD_RED = "[1;31m";
/*     */   public static final String BOLD_GREEN = "[1;32m";
/*     */   public static final String BOLD_YELLOW = "[1;33m";
/*     */   public static final String BOLD_BLUE = "[1;34m";
/*     */   public static final String BOLD_MAGENTA = "[1;35m";
/*     */   public static final String BOLD_CYAN = "[1;36m";
/*     */   public static final String BOLD_WHITE = "[1;37m";
/*     */   public static final String PLAIN = "[0;m";
/*     */   
/*     */   public void icsEventDispatched(ICSEvent evt)
/*     */   {
/*  64 */     String prefix = null;
/*     */     
/*  66 */     switch (evt.getEventType())
/*     */     {
/*     */     case 6: 
/*  69 */       switch (((ICSChannelEvent)evt).getChannel()) {
/*  70 */       case 1:  prefix = "\033[0;36m"; break;
/*     */       case 85: case 88: 
/*  72 */         prefix = "\033[0;33m"; break;
/*  73 */       default:  prefix = "\033[1;36m";
/*     */       }
/*  75 */       break;
/*     */     
/*     */     case 7: 
/*  78 */       switch (((ICSChannelEvent)evt).getChannel()) {
/*     */       case 1: case 2: 
/*  80 */         prefix = "\033[0;32m";
/*  81 */         break;
/*     */       case 3: 
/*     */       case 4: 
/*     */       case 5: 
/*  85 */         prefix = "\033[1;32m";
/*     */       }
/*  87 */       break;
/*     */     
/*     */     case 9: 
/*     */     case 10: 
/*     */     case 19: 
/*  92 */       prefix = "\033[1;33m";
/*  93 */       break;
/*     */     
/*     */     case 17: 
/*  96 */       prefix = "\033[1;35m";
/*  97 */       break;
/*     */     
/*     */     case 18: 
/* 100 */       prefix = "\033[0;35m";
/* 101 */       break;
/*     */     
/*     */ 
/*     */     case 2: 
/*     */     case 3: 
/*     */     case 12: 
/*     */     case 13: 
/*     */     case 16: 
/*     */     case 24: 
/*     */     case 27: 
/* 111 */       prefix = "\033[1;30m";
/* 112 */       break;
/*     */     
/*     */     case 4: 
/*     */     case 14: 
/* 116 */       prefix = "\033[0;34m";
/* 117 */       break;
/*     */     
/*     */     case 1: 
/*     */     case 22: 
/* 121 */       prefix = "\033[0;33m";
/* 122 */       break;
/*     */     case 5: case 8: 
/*     */     case 11: case 15: 
/*     */     case 20: case 21: 
/*     */     case 23: case 25: 
/*     */     case 26: default: 
/* 128 */       prefix = "\033[1;31m";
/*     */     }
/*     */     
/*     */     
/* 132 */     if (this.showTimestamp) {
/* 133 */       System.out.print("\033[1;30m" + 
/* 134 */         getTimestampAsString(evt.getTimestamp()) + 
/* 135 */         '\033' + "[0;m");
/*     */     }
/*     */     
/* 138 */     if (this.debug) {
/* 139 */       System.out.print("<" + evt.getEventType() + ">");
/*     */     }
/* 141 */     if (prefix != null) {
/* 142 */       System.out.println(prefix + evt + '\033' + "[0;m");
/*     */     } else {
/* 144 */       System.out.println(evt);
/*     */     }
/* 146 */     System.out.flush();
/*     */   }
/*     */   
/*     */   protected String getTimestampAsString(Date date)
/*     */   {
/* 151 */     StringBuffer sb = new StringBuffer(5);
/* 152 */     int tmp = 0;
/* 153 */     cal.setTime(date);
/*     */     
/* 155 */     tmp = cal.get(11);
/* 156 */     if (tmp < 10)
/* 157 */       sb.append("0");
/* 158 */     sb.append(tmp).append(":");
/*     */     
/* 160 */     tmp = cal.get(12);
/* 161 */     if (tmp < 10)
/* 162 */       sb.append("0");
/* 163 */     sb.append(tmp);
/*     */     
/* 165 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public void setTimestampVisible(boolean t)
/*     */   {
/* 170 */     this.showTimestamp = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isTimestampVisible()
/*     */   {
/* 177 */     return this.showTimestamp;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ui\cli\ANSIConsole.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */