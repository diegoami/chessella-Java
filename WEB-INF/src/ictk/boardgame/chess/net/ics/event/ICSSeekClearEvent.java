/*    */ package ictk.boardgame.chess.net.ics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekClearParser;
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
/*    */ 
/*    */ 
/*    */ public class ICSSeekClearEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   protected static final int SEEK_CLEAR_EVENT = 16;
/*    */   
/*    */   public ICSSeekClearEvent()
/*    */   {
/* 56 */     super(16);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getReadable()
/*    */   {
/* 64 */     return FICSSeekClearParser.getInstance().toNative(this);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSSeekClearEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */