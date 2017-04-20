/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParser;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ICSChannelEvent
/*     */   extends ICSMessageEvent
/*     */ {
/*     */   protected static final int CHANNEL_EVENT = 6;
/*     */   public static final int SHOUT_CHANNEL = 1;
/*     */   public static final int EMOTE_CHANNEL = 2;
/*     */   public static final int CSHOUT_CHANNEL = 3;
/*     */   public static final int SSHOUT_CHANNEL = 4;
/*     */   public static final int TSHOUT_CHANNEL = 5;
/*     */   protected String player;
/*     */   protected ICSAccountType acctType;
/*     */   protected int channel;
/*     */   
/*     */   public ICSChannelEvent()
/*     */   {
/*  68 */     super(6);
/*     */   }
/*     */   
/*     */   public String getPlayer()
/*     */   {
/*  73 */     return this.player;
/*     */   }
/*     */   
/*     */   public ICSAccountType getAccountType() {
/*  77 */     return this.acctType;
/*     */   }
/*     */   
/*     */   public int getChannel() {
/*  81 */     return this.channel;
/*     */   }
/*     */   
/*     */   public void setPlayer(String player)
/*     */   {
/*  86 */     this.player = player;
/*     */   }
/*     */   
/*     */   public void setAccountType(ICSAccountType acctType) {
/*  90 */     this.acctType = acctType;
/*     */   }
/*     */   
/*     */   public void setChannel(int channel) {
/*  94 */     this.channel = channel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 101 */     String str = null;
/* 102 */     switch (getEventType())
/*     */     {
/*     */     case 6: 
/* 105 */       str = FICSChannelParser.getInstance().toNative(this);
/* 106 */       break;
/*     */     
/*     */     case 7: 
/* 109 */       str = FICSShoutParser.getInstance().toNative(this);
/*     */     }
/*     */     
/*     */     
/* 113 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSChannelEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */