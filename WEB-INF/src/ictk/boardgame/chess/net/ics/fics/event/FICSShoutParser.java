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
/*     */ public class FICSShoutParser
/*     */   extends ICSEventParser
/*     */ {
/*  50 */   public static final Pattern masterPattern = Pattern.compile(
/*  51 */     "^:?(((-->\\s)(?:\\n\\\\\\s+)?([\\w]+)((?:\\([A-Z*]+\\))*)((?:.|\\s+\\\\|\\s+:)*))|(([\\w]+)((?:\\([A-Z*]+\\))*)\\s+(?:([sct])-)?shouts:\\s)((?:.|\\s+\\\\|\\s+:)*))", 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     8);
/*     */   
/*  72 */   public static FICSShoutParser singleton = new FICSShoutParser();
/*     */   
/*     */ 
/*     */   protected FICSShoutParser()
/*     */   {
/*  77 */     super(masterPattern);
/*     */   }
/*     */   
/*     */   public static ICSEventParser getInstance()
/*     */   {
/*  82 */     return singleton;
/*     */   }
/*     */   
/*     */   public ICSEvent createICSEvent(Matcher match)
/*     */   {
/*  87 */     ICSEvent evt = new ICSChannelEvent();
/*  88 */     assignMatches(match, evt);
/*     */     
/*  90 */     return evt;
/*     */   }
/*     */   
/*     */   public void assignMatches(Matcher m, ICSEvent event)
/*     */   {
/*  95 */     ICSChannelEvent evt = (ICSChannelEvent)event;
/*     */     
/*  97 */     if (this.debug) {
/*  98 */       Log.debug(DEBUG, "assigning matches", m);
/*     */     }
/*     */     
/* 101 */     evt.setFake(detectFake(m.group(0)));
/*     */     
/* 103 */     evt.setEventType(7);
/*     */     
/* 105 */     if (m.group(3) != null) {
/* 106 */       evt.setChannel(2);
/*     */       
/* 108 */       evt.setPlayer(m.group(4));
/*     */       
/*     */ 
/* 111 */       evt.setAccountType(parseICSAccountType(m, 5));
/*     */       
/*     */ 
/* 114 */       evt.setMessage(m.group(6));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 119 */       evt.setPlayer(m.group(8));
/*     */       
/*     */ 
/* 122 */       evt.setAccountType(parseICSAccountType(m, 9));
/*     */       
/*     */ 
/* 125 */       evt.setMessage(m.group(11));
/*     */       
/* 127 */       if (m.group(10) != null) {
/* 128 */         switch (m.group(10).charAt(0)) {
/*     */         case 's': 
/* 130 */           evt.setChannel(4);
/* 131 */           break;
/*     */         case 'c': 
/* 133 */           evt.setChannel(3);
/* 134 */           break;
/*     */         case 't': 
/* 136 */           evt.setChannel(5);
/* 137 */           break;
/*     */         default: 
/* 139 */           Log.error(3, 
/* 140 */             "Received unknown shout type: '" + 
/* 141 */             m.group(10).charAt(0) + "' from " + m.group(0));
/* 142 */           evt.setEventType(0);
/* 143 */           evt.setMessage(m.group(0));
/* 144 */           return;break;
/*     */         }
/*     */         
/*     */       } else {
/* 148 */         evt.setChannel(1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toNative(ICSEvent event)
/*     */   {
/* 157 */     if (event.getEventType() == 0) {
/* 158 */       return event.getMessage();
/*     */     }
/* 160 */     ICSChannelEvent evt = (ICSChannelEvent)event;
/* 161 */     StringBuffer sb = new StringBuffer(80);
/*     */     
/* 163 */     if (evt.isFake()) { sb.append(":");
/*     */     }
/* 165 */     if (evt.getChannel() == 2)
/*     */     {
/*     */ 
/* 168 */       sb.append("--> ").append(evt.getPlayer()).append(evt.getAccountType());
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 174 */       sb.append(evt.getPlayer()).append(evt.getAccountType()).append(" ");
/*     */       
/* 176 */       switch (evt.getChannel())
/*     */       {
/*     */       case 1: 
/*     */         break;
/*     */       case 4: 
/* 181 */         sb.append("s-");
/* 182 */         break;
/*     */       
/*     */       case 3: 
/* 185 */         sb.append("c-");
/* 186 */         break;
/*     */       
/*     */       case 5: 
/* 189 */         sb.append("t-");
/* 190 */         break;
/*     */       case 2: 
/*     */       default: 
/* 193 */         throw new IllegalStateException(
/* 194 */           "Tried to get a toNative() with the ShoutParser when the channel is not a shout -- should use the Channel Parser for channel(" + 
/*     */           
/* 196 */           evt.getChannel() + ")");
/*     */       }
/* 198 */       sb.append("shouts: ");
/*     */     }
/*     */     
/* 201 */     sb.append(evt.getMessage());
/*     */     
/*     */ 
/* 204 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\event\FICSShoutParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */