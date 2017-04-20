/*    */ package ictk.boardgame.chess.net.ics.event;
/*    */ 
/*    */ import ictk.boardgame.chess.net.ics.fics.event.FICSSeekRemoveParser;
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
/*    */ 
/*    */ public class ICSSeekRemoveEvent
/*    */   extends ICSEvent
/*    */ {
/*    */   protected static final int SEEK_REMOVE_EVENT = 13;
/*    */   protected int[] ads;
/*    */   
/*    */   public ICSSeekRemoveEvent()
/*    */   {
/* 58 */     super(13);
/*    */   }
/*    */   
/*    */   public int[] getAds()
/*    */   {
/* 63 */     return this.ads;
/*    */   }
/*    */   
/*    */   public void setAds(int[] ads)
/*    */   {
/* 68 */     this.ads = ads;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getReadable()
/*    */   {
/* 74 */     return FICSSeekRemoveParser.getInstance().toNative(this);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSSeekRemoveEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */