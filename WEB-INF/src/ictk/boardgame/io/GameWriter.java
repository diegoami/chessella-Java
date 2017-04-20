/*    */ package ictk.boardgame.io;
/*    */ 
/*    */ import ictk.boardgame.Board;
/*    */ import ictk.boardgame.Game;
/*    */ import ictk.boardgame.GameInfo;
/*    */ import ictk.boardgame.History;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintWriter;
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
/*    */ public abstract class GameWriter
/*    */   extends PrintWriter
/*    */ {
/*    */   public GameWriter(OutputStream _out)
/*    */   {
/* 43 */     super(_out, true);
/*    */   }
/*    */   
/*    */   public GameWriter(Writer _out) {
/* 47 */     super(_out);
/*    */   }
/*    */   
/*    */   public abstract void setMoveNotation(MoveNotation paramMoveNotation);
/*    */   
/*    */   public abstract MoveNotation getMoveNotation();
/*    */   
/*    */   public abstract void setExportVariations(boolean paramBoolean);
/*    */   
/*    */   public abstract boolean isExportVariations();
/*    */   
/*    */   public abstract void setExportComments(boolean paramBoolean);
/*    */   
/*    */   public abstract boolean isExportComments();
/*    */   
/*    */   public abstract void writeGame(Game paramGame)
/*    */     throws IOException;
/*    */   
/*    */   public abstract void writeGameInfo(GameInfo paramGameInfo)
/*    */     throws IOException;
/*    */   
/*    */   public abstract void writeHistory(History paramHistory)
/*    */     throws IOException;
/*    */   
/*    */   public abstract void writeBoard(Board paramBoard)
/*    */     throws IOException;
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\io\GameWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */