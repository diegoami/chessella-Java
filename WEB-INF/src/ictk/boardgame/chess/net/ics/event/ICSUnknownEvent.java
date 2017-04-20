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
/*    */ public class ICSUnknownEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   public static final int UNKNOWN_EVENT = 0;
/*    */   
/*    */   public ICSUnknownEvent(ICSProtocolHandler server)
/*    */   {
/* 39 */     super(server, 0);
/*    */   }
/*    */   
/*    */   public ICSUnknownEvent(ICSProtocolHandler server, String mesg) {
/* 43 */     super(server, 0);
/* 44 */     this.message = mesg;
/*    */   }
/*    */   
/*    */   public String getReadable() {
/* 48 */     return this.message;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSUnknownEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */