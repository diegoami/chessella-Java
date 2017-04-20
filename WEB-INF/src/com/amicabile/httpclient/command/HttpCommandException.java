/*    */ package com.amicabile.httpclient.command;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableException;
/*    */ 
/*    */ public class HttpCommandException extends NestableException {
/*    */   public HttpCommandException(String msg) {
/*  7 */     super(msg);
/*    */   }
/*    */   
/*    */   public HttpCommandException(Throwable throwable) {
/* 11 */     super(throwable);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\httpclient\command\HttpCommandException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */