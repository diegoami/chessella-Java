/*    */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
/*    */ import java.awt.event.WindowAdapter;
/*    */ import java.awt.event.WindowEvent;
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
/*    */ final class CommandInput$1
/*    */   extends WindowAdapter
/*    */ {
/*    */   CommandInput$1(CommandInput paramCommandInput) {}
/*    */   
/*    */   public void windowClosing(WindowEvent evt)
/*    */   {
/* 55 */     this.this$0.ics.disconnect();
/* 56 */     System.exit(0);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\CommandInput$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */