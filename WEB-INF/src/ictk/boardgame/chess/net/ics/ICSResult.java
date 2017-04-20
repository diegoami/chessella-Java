/*    */ package ictk.boardgame.chess.net.ics;
/*    */ 
/*    */ import ictk.util.Log;
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
/*    */ public class ICSResult
/*    */ {
/*    */   public static final int UNDECIDED = 0;
/*    */   public static final int WHITE_WIN = 1;
/*    */   public static final int DRAW = 2;
/*    */   public static final int BLACK_WIN = 3;
/*    */   protected int result;
/*    */   protected String desc;
/*    */   
/*    */   public ICSResult()
/*    */   {
/* 40 */     this.result = 0;
/*    */   }
/*    */   
/*    */   public ICSResult(String s) {
/* 44 */     this();
/* 45 */     setResultCode(s);
/*    */   }
/*    */   
/* 48 */   public int getResultCode() { return this.result; }
/* 49 */   public void setResultCode(int res) { this.result = res; }
/*    */   
/*    */   public void setResultCode(String s) {
/* 52 */     if ("*".equals(s)) {
/* 53 */       this.result = 0;
/* 54 */     } else if ("1-0".equals(s)) {
/* 55 */       this.result = 1;
/* 56 */     } else if ("1/2-1/2".equals(s)) {
/* 57 */       this.result = 2;
/* 58 */     } else if ("0-1".equals(s)) {
/* 59 */       this.result = 3;
/*    */     } else {
/* 61 */       Log.error(3, 
/* 62 */         "ICSResult received '" + s + "' as a result.");
/* 63 */       this.result = 0;
/*    */     }
/*    */   }
/*    */   
/*    */   public String getReadableResult() {
/* 68 */     String s = null;
/*    */     
/* 70 */     switch (this.result) {
/* 71 */     case 0:  s = "*"; break;
/* 72 */     case 1:  s = "1-0"; break;
/* 73 */     case 2:  s = "1/2-1/2"; break;
/* 74 */     case 3:  s = "0-1";
/*    */     }
/*    */     
/* 77 */     return s;
/*    */   }
/*    */   
/* 80 */   public String getDescription() { return this.desc; }
/* 81 */   public void setDescription(String s) { this.desc = s; }
/*    */   
/*    */   public String toString() {
/* 84 */     return getReadableResult();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */