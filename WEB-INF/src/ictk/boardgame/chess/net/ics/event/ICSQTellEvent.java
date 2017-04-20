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
/*    */ 
/*    */ 
/*    */ public abstract class ICSQTellEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   public static final int QTELL_EVENT = 20;
/*    */   
/*    */   public ICSQTellEvent(ICSProtocolHandler server)
/*    */   {
/* 42 */     super(server, 20);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSQTellEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */