/*    */ package ictk.boardgame.chess.net.ics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.ICSGameInfo;
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
/*    */ 
/*    */ public class ICSHistoryEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   protected static final int HISTORY_EVENT = 28;
/*    */   ICSGameInfo[] list;
/*    */   String player;
/*    */   
/*    */   public ICSHistoryEvent(ICSProtocolHandler server)
/*    */   {
/* 46 */     super(server, 28);
/*    */   }
/*    */   
/* 49 */   public String getPlayer() { return this.player; }
/* 50 */   public void setPlayer(String name) { this.player = name; }
/*    */   
/* 52 */   public ICSGameInfo[] getHistoryList() { return this.list; }
/* 53 */   public void setHistoryList(ICSGameInfo[] l) { this.list = l; }
/*    */   
/*    */ 
/*    */   public String getReadable()
/*    */   {
/* 58 */     return "History List";
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSHistoryEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */