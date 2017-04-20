/*    */ package ictk.boardgame.chess.net.ics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
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
/*    */ public abstract class ICSMessageEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   String player;
/*    */   
/*    */   public ICSMessageEvent(ICSProtocolHandler server, int eventType)
/*    */   {
/* 39 */     super(server, eventType);
/*    */   }
/*    */   
/*    */   public ICSMessageEvent(int eventType) {
/* 43 */     super(eventType);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setPlayer(String player)
/*    */   {
/* 51 */     this.player = player;
/*    */   }
/*    */   
/*    */   public String getPlayer() {
/* 55 */     return this.player;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSMessageEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */