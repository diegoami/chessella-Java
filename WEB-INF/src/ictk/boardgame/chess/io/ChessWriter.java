/*    */ package ictk.boardgame.chess.io;
/*    */ 
/*    */ import ictk.boardgame.io.GameWriter;
/*    */ import java.io.OutputStream;
/*    */ import java.io.Writer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ChessWriter
/*    */   extends GameWriter
/*    */ {
/*    */   public ChessWriter(OutputStream _out)
/*    */   {
/* 40 */     super(_out);
/*    */   }
/*    */   
/*    */   public ChessWriter(Writer _out) {
/* 44 */     super(_out);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\io\ChessWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */