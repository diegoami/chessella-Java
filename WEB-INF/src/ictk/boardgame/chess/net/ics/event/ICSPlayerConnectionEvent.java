/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerConnectionParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerNotificationParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSPlayerConnectionEvent
/*     */   extends ICSEvent
/*     */ {
/*     */   protected static final int PLAYER_CONNECTION_EVENT = 27;
/*     */   protected String player;
/*     */   protected ICSAccountType acctType;
/*     */   protected boolean connected;
/*     */   protected boolean notified;
/*     */   protected boolean onNotifyList;
/*     */   
/*     */   public ICSPlayerConnectionEvent()
/*     */   {
/*  61 */     super(27);
/*     */   }
/*     */   
/*     */   public String getPlayer()
/*     */   {
/*  66 */     return this.player;
/*     */   }
/*     */   
/*     */   public ICSAccountType getAccountType() {
/*  70 */     return this.acctType;
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/*  74 */     return this.connected;
/*     */   }
/*     */   
/*     */   public boolean isNotification() {
/*  78 */     return this.notified;
/*     */   }
/*     */   
/*     */   public boolean isOnNotifyList() {
/*  82 */     return this.onNotifyList;
/*     */   }
/*     */   
/*     */   public void setPlayer(String player)
/*     */   {
/*  87 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void setAccountType(ICSAccountType acctType) {
/*  91 */     this.acctType = acctType;
/*     */   }
/*     */   
/*     */   public void setConnected(boolean connected) {
/*  95 */     this.connected = connected;
/*     */   }
/*     */   
/*     */   public void setNotification(boolean notified) {
/*  99 */     this.notified = notified;
/*     */   }
/*     */   
/*     */   public void setOnNotifyList(boolean onNotifyList) {
/* 103 */     this.onNotifyList = onNotifyList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 110 */     String str = null;
/* 111 */     switch (getEventType())
/*     */     {
/*     */     case 27: 
/* 114 */       str = FICSPlayerConnectionParser.getInstance().toNative(this);
/* 115 */       break;
/*     */     
/*     */     case 24: 
/* 118 */       str = FICSPlayerNotificationParser.getInstance().toNative(this);
/*     */     }
/*     */     
/*     */     
/* 122 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSPlayerConnectionEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */