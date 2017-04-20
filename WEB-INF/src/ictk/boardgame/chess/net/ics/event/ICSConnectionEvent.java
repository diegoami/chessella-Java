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
/*    */ 
/*    */ public class ICSConnectionEvent
/*    */ {
/*    */   protected ICSProtocolHandler connection;
/*    */   
/*    */   public ICSConnectionEvent(ICSProtocolHandler con)
/*    */   {
/* 39 */     this.connection = con;
/*    */   }
/*    */   
/* 42 */   public ICSProtocolHandler getConnection() { return this.connection; }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSConnectionEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */