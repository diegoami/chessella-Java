/*    */ package com.amicabile.openingtrainer.pgn;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableException;
/*    */ 
/*    */ public class PGNException extends NestableException {
/*    */   public PGNException(String msg) {
/*  7 */     super(msg);
/*    */   }
/*    */   
/*    */   public PGNException(Throwable throwable) {
/* 11 */     super(throwable);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\pgn\PGNException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */