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
/*    */ public class IllegalMoveException
/*    */   extends MoveException
/*    */ {
/*    */   String moveString;
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
/*    */   public IllegalMoveException() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 35 */   public IllegalMoveException(String s) { super(s); }
/* 36 */   public IllegalMoveException(String s, Move _m) { super(s, _m); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setMoveString(String m)
/*    */   {
/* 43 */     this.moveString = m;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 48 */   public String getMoveString() { return this.moveString; }
/*    */   
/*    */   public String toString() {
/* 51 */     if (this.moveString == null) {
/* 52 */       return getMessage();
/*    */     }
/* 54 */     return getMessage() + ": " + this.moveString;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\IllegalMoveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */