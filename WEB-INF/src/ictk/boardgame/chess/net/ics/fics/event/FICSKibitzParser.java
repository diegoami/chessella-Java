/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventParser;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSKibitzEvent;
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
/*     */ public class FICSKibitzParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(([\\w]+)((?:\\([A-Z*]+\\))*)(?:\\(\\s*([0-9+-]+[EP]?)\\))?\\[(\\d+)\\]\\s(kibitzes|whispers|says):\\s((?:.|\\s+\\\\|\\s+:)*))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  60 */     8);
/*     */   
/*  62 */   public static FICSKibitzParser singleton = new FICSKibitzParser();
/*     */   
/*     */ 
/*     */   protected FICSKibitzParser()
/*     */   {
/*  67 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  72 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  77 */     ICSEvent evt = new ICSKibitzEvent();
/*  78 */     assignMatches(match, evt);
/*     */     
/*  80 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  85 */     ICSKibitzEvent evt = (ICSKibitzEvent)event;
/*     */     
/*  87 */     if (this.debug) {
/*  88 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/*  91 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/*  94 */     evt.setPlayer(m.group(2));
/*     */     
/*     */ 
/*  97 */     evt.setAccountType(parseICSAccountType(m, 3));
/*     */     
/*     */ 
/* 100 */     evt.setRating(parseICSRating(m, 4));
/*     */     
/*     */     try
/*     */     {
/* 104 */       evt.setBoardNumber(Integer.parseInt(m.group(5)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 107 */       Log.error(3, 
/* 108 */         "Can't parse boardNumber for: " + 
/* 109 */         m.group(5) + 
/* 110 */         " of " + m.group(0));
/* 111 */       evt.setEventType(0);
/* 112 */       evt.setMessage(m.group(0));
/*     */       
/* 114 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 115 */       return;
/*     */     }
/*     */     
/*     */ 
/* 119 */     evt.setMessage(m.group(7));
/*     */     
/* 121 */     if ("whispers".equals(m.group(6))) {
/* 122 */       evt.setEventType(18);
/*     */     }
/* 124 */     else if ("says".equals(m.group(6))) {
/* 125 */       evt.setEventType(19);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 133 */     if (event.getEventType() == 0) {
/* 134 */       return event.getMessage();
/*     */     }
/* 136 */     ICSKibitzEvent evt = (ICSKibitzEvent)event;
/* 137 */     StringBuffer sb = new StringBuffer(20);
/*     */     
/* 139 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/* 141 */     String str = null;
/*     */     
/* 143 */     sb.append(evt.getPlayer())
/* 144 */       .append(evt.getAccountType());
/*     */     
/* 146 */     if (evt.getEventType() != 19) {
/* 147 */       sb.append("(");
/* 148 */       str = evt.getRating().toString();
/* 149 */       for (int i = 0; i < 4 - str.length(); i++)
/* 150 */         sb.append(" ");
/* 151 */       sb.append(str);
/* 152 */       sb.append(")");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 157 */     sb.append("[").append(evt.getBoardNumber()).append("]");
/*     */     
/* 159 */     switch (evt.getEventType()) {
/*     */     case 17: 
/* 161 */       sb.append(" kibitzes: ");
/* 162 */       break;
/*     */     
/*     */     case 18: 
/* 165 */       sb.append(" whispers: ");
/* 166 */       break;
/*     */     
/*     */     case 19: 
/* 169 */       sb.append(" says: ");
/*     */     }
/*     */     
/*     */     
/* 173 */     sb.append(evt.getMessage());
/*     */     
/*     */ 
/* 176 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSKibitzParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */