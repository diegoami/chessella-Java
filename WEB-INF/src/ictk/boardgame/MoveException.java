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
/*    */ public class MoveException
/*    */   extends BoardGameException
/*    */ {
/*    */   protected Move m;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public MoveException() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 43 */   public MoveException(String s) { super(s); }
/*    */   
/* 45 */   public MoveException(String s, Move _m) { super(s);
/* 46 */     this.m = _m;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Move getMove()
/*    */   {
/* 54 */     return this.m;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\MoveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */