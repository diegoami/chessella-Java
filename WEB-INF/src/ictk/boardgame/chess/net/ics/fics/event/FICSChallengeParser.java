/*     */ package ictk.boardgame.chess.net.ics.fics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSChallengeEvent;
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
/*     */ public class FICSChallengeParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(Challenge:\\s([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(?:\\[(white|black)\\])?\\s?([\\w]+)\\s\\(\\s*([0-9+-]+[EP]?)\\)\\s(rated|unrated)\\s(\\S+)\\s(\\d+)\\s(\\d+)\\.\\n(?:--\\*\\*\\s([\\w]+)\\sis\\san?\\s(computer|abuser)\\s\\*\\*--\\n)?You\\scan\\s\"accept\"\\sor\\s\"decline\",\\sor\\spropose\\sdifferent\\sparameters\\.)", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */     8);
/*     */   
/*  78 */   public static FICSChallengeParser singleton = new FICSChallengeParser();
/*     */   
/*     */ 
/*     */   protected FICSChallengeParser()
/*     */   {
/*  83 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  88 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  93 */     ICSEvent evt = new ICSChallengeEvent();
/*  94 */     assignMatches(match, evt);
/*     */     
/*  96 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/* 101 */     ICSChallengeEvent evt = (ICSChallengeEvent)event;
/*     */     
/* 103 */     if (this.debug) {
/* 104 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/* 107 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/*     */ 
/* 110 */     evt.setChallenger(m.group(2));
/*     */     
/*     */ 
/* 113 */     evt.setChallengerRating(parseICSRating(m, 3));
/*     */     
/*     */ 
/* 116 */     evt.setChallenged(m.group(5));
/*     */     
/*     */ 
/* 119 */     evt.setChallengedRating(parseICSRating(m, 6));
/*     */     
/*     */ 
/* 122 */     evt.setVariant(new ICSVariant(m.group(8)));
/*     */     
/*     */     try
/*     */     {
/* 126 */       evt.setInitialTime(Integer.parseInt(m.group(9)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 129 */       Log.error(3, 
/* 130 */         "Can't parse time for: " + 
/* 131 */         m.group(9) + 
/* 132 */         " of " + m.group(0));
/* 133 */       evt.setEventType(0);
/* 134 */       evt.setMessage(m.group(0));
/*     */       
/* 136 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 137 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 142 */       evt.setIncrementTime(Integer.parseInt(m.group(10)));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 145 */       Log.error(3, 
/* 146 */         "Can't parse incr for: " + 
/* 147 */         m.group(10) + 
/* 148 */         " of " + m.group(0));
/* 149 */       evt.setEventType(0);
/* 150 */       evt.setMessage(m.group(0));
/*     */       
/* 152 */       Log.debug(ICSEventParser.DEBUG, "regex", m);
/* 153 */       return;
/*     */     }
/*     */     
/* 156 */     if (m.group(4) != null) {
/* 157 */       evt.setColorSpecified(true);
/* 158 */       if (m.group(4).equals("white")) {
/* 159 */         evt.setWhite(true);
/*     */       }
/*     */     }
/* 162 */     evt.setRated("rated".equals(m.group(7)));
/*     */     
/*     */ 
/* 165 */     if (m.group(12) != null) {
/* 166 */       if ("computer".equals(m.group(12))) {
/* 167 */         evt.setComputer(true);
/* 168 */       } else if ("abuser".equals(m.group(12))) {
/* 169 */         evt.setAbuser(true);
/*     */       } else {
/* 171 */         Log.error(3, 
/* 172 */           "unknown Challenge event alert: " + m.group(12));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 180 */     if (event.getEventType() == 0) {
/* 181 */       return event.getMessage();
/*     */     }
/* 183 */     ICSChallengeEvent evt = (ICSChallengeEvent)event;
/* 184 */     StringBuffer sb = new StringBuffer(154);
/*     */     
/* 186 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 192 */     sb.append("Challenge: ").append(evt.getChallenger()).append(" (").append(evt.getChallengerRating()).append(") ");
/*     */     
/* 194 */     if (evt.isColorSpecified()) {
/* 195 */       if (evt.isWhite()) {
/* 196 */         sb.append("[white] ");
/*     */       } else {
/* 198 */         sb.append("[black] ");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 204 */     sb.append(evt.getChallenged()).append(" (").append(evt.getChallengedRating()).append(") ");
/*     */     
/*     */ 
/* 207 */     if (evt.isRated()) {
/* 208 */       sb.append("rated ");
/*     */     } else {
/* 210 */       sb.append("unrated ");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 217 */     sb.append(evt.getVariant()).append(" ").append(evt.getInitialTime()).append(" ").append(evt.getIncrementTime()).append(".\n");
/*     */     
/* 219 */     if (evt.isComputer())
/*     */     {
/*     */ 
/* 222 */       sb.append("--** ").append(evt.getChallenger()).append(" is a computer **--\n");
/*     */     }
/*     */     
/* 225 */     if (evt.isAbuser())
/*     */     {
/*     */ 
/* 228 */       sb.append("--** ").append(evt.getChallenger()).append(" is an abuser **--\n");
/*     */     }
/*     */     
/* 231 */     sb.append(
/* 232 */       "You can \"accept\" or \"decline\", or propose different parameters.");
/*     */     
/*     */ 
/* 235 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSChallengeParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */