/*    */ package ictk.boardgame.chess.net.ics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*    */ import ictk.boardgame.chess.net.ics.fics.event.FICSTellParser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ICSTellEvent
/*    */   extends ICSMessageEvent
/*    */ {
/*    */   protected static final int TELL_EVENT = 9;
/*    */   protected String player;
/*    */   protected ICSAccountType acctType;
/*    */   protected String mesg;
/*    */   
/*    */   public ICSTellEvent()
/*    */   {
/* 58 */     super(9);
/*    */   }
/*    */   
/*    */   public String getPlayer()
/*    */   {
/* 63 */     return this.player;
/*    */   }
/*    */   
/*    */   public ICSAccountType getAccountType() {
/* 67 */     return this.acctType;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 71 */     return this.mesg;
/*    */   }
/*    */   
/*    */   public void setPlayer(String player)
/*    */   {
/* 76 */     this.player = player;
/*    */   }
/*    */   
/*    */   public void setAccountType(ICSAccountType acctType) {
/* 80 */     this.acctType = acctType;
/*    */   }
/*    */   
/*    */   public void setMessage(String mesg) {
/* 84 */     this.mesg = mesg;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getReadable()
/*    */   {
/* 90 */     return FICSTellParser.getInstance().toNative(this);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSTellEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */