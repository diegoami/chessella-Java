/*    */ package ictk.boardgame.chess.io;
/*    */ 
/*    */ import ictk.boardgame.Game;
/*    */ import ictk.boardgame.io.GameReader;
/*    */ import java.io.Reader;
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
/*    */ public abstract class ChessReader
/*    */   extends GameReader
/*    */ {
/*    */   public ChessReader(Reader _ir)
/*    */   {
/* 48 */     super(_ir);
/*    */   }
/*    */   
/*    */   public abstract Game getGame();
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\ChessReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */