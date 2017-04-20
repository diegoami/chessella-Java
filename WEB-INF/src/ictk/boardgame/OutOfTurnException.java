/*    */ package ictk.boardgame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutOfTurnException
/*    */   extends IllegalMoveException
/*    */ {
/*    */   public OutOfTurnException() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 43 */   public OutOfTurnException(String s) { super(s); }
/* 44 */   public OutOfTurnException(String s, Move m) { super(s, m); }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\OutOfTurnException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */