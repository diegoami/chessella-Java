/*    */ package ictk.boardgame.io;
/*    */ 
/*    */ import ictk.boardgame.AmbiguousMoveException;
/*    */ import ictk.boardgame.Board;
/*    */ import ictk.boardgame.Game;
/*    */ import ictk.boardgame.GameInfo;
/*    */ import ictk.boardgame.History;
/*    */ import ictk.boardgame.IllegalMoveException;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
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
/*    */ public abstract class GameReader
/*    */   extends BufferedReader
/*    */ {
/*    */   public GameReader(Reader _ir)
/*    */   {
/* 45 */     super(_ir);
/*    */   }
/*    */   
/*    */   public abstract Game readGame()
/*    */     throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException;
/*    */   
/*    */   public abstract GameInfo readGameInfo()
/*    */     throws IOException;
/*    */   
/*    */   public abstract History readHistory()
/*    */     throws InvalidGameFormatException, IllegalMoveException, AmbiguousMoveException, IOException;
/*    */   
/*    */   public abstract Board readBoard()
/*    */     throws IOException;
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\io\GameReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */