/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
/*     */ import ictk.util.Log;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ICSEvent
/*     */ {
/*  45 */   public static final long DEBUG = Log.ICSEvent;
/*     */   
/*     */   public static final int UNKNOWN_EVENT = 0;
/*     */   
/*     */   public static final int BOARD_UPDATE_EVENT = 1;
/*     */   
/*     */   public static final int GAME_CREATED_EVENT = 2;
/*     */   
/*     */   public static final int GAME_RESULT_EVENT = 3;
/*     */   
/*     */   public static final int GAME_NOTIFICATION_EVENT = 4;
/*     */   
/*     */   public static final int TAKEBACK_REQUEST_EVENT = 5;
/*     */   
/*     */   public static final int CHANNEL_EVENT = 6;
/*     */   
/*     */   public static final int SHOUT_EVENT = 7;
/*     */   
/*     */   public static final int TOURNAMENT_CHANNEL_EVENT = 8;
/*     */   
/*     */   public static final int TELL_EVENT = 9;
/*     */   
/*     */   public static final int SAY_EVENT = 10;
/*     */   
/*     */   public static final int CHALLENGE_EVENT = 11;
/*     */   public static final int SEEK_AD_EVENT = 12;
/*     */   public static final int SEEK_REMOVE_EVENT = 13;
/*     */   public static final int SEEK_AD_READABLE_EVENT = 14;
/*     */   public static final int SEEK_REMOVE_READABLE_EVENT = 15;
/*     */   public static final int SEEK_CLEAR_EVENT = 16;
/*     */   public static final int KIBITZ_EVENT = 17;
/*     */   public static final int WHISPER_EVENT = 18;
/*     */   public static final int BOARD_SAY_EVENT = 19;
/*     */   public static final int QTELL_EVENT = 20;
/*     */   public static final int AUTO_SALUTE_EVENT = 21;
/*     */   public static final int MOVE_LIST_EVENT = 22;
/*     */   public static final int MATCH_REQUEST_EVENT = 23;
/*     */   public static final int PLAYER_NOTIFICATION_EVENT = 24;
/*     */   public static final int AVAIL_INFO_EVENT = 25;
/*     */   public static final int USER_DEFINED_EVENT = 26;
/*     */   public static final int PLAYER_CONNECTION_EVENT = 27;
/*     */   public static final int HISTORY_EVENT = 28;
/*     */   public static final int NUM_EVENTS = 29;
/*  88 */   protected int eventType = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ICSEventParser eventParser;
/*     */   
/*     */ 
/*     */ 
/*     */   protected ICSProtocolHandler server;
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isFake;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Date timestamp;
/*     */   
/*     */ 
/*     */ 
/*     */   protected String message;
/*     */   
/*     */ 
/*     */ 
/*     */   protected String original;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ICSEvent(ICSProtocolHandler server, int eventType)
/*     */   {
/* 120 */     this.server = server;
/* 121 */     this.eventType = eventType;
/* 122 */     this.timestamp = new Date();
/*     */   }
/*     */   
/*     */   public ICSEvent(int eventType) {
/* 126 */     this.eventType = eventType;
/* 127 */     this.timestamp = new Date();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ICSProtocolHandler getServer()
/*     */   {
/* 134 */     return this.server;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setServer(ICSProtocolHandler server)
/*     */   {
/* 141 */     this.server = server;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getEventType()
/*     */   {
/* 148 */     return this.eventType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEventType(int type)
/*     */   {
/* 155 */     this.eventType = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getTimestamp()
/*     */   {
/* 162 */     return this.timestamp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTimestamp(Date timestamp)
/*     */   {
/* 169 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage()
/*     */   {
/* 178 */     return this.message;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMessage(String mesg)
/*     */   {
/* 187 */     this.message = mesg;
/*     */   }
/*     */   
/*     */   public ICSEventParser getEventParser() {
/* 191 */     return this.eventParser;
/*     */   }
/*     */   
/*     */   public void setEventParser(ICSEventParser parser) {
/* 195 */     this.eventParser = parser;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFake(boolean t)
/*     */   {
/* 201 */     this.isFake = t;
/*     */   }
/*     */   
/*     */   public boolean isFake()
/*     */   {
/* 206 */     return this.isFake;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String getReadable();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOriginal(String s)
/*     */   {
/* 221 */     this.original = s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String DEBUG_getOriginal()
/*     */   {
/* 232 */     return this.original;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 240 */     return getReadable();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */