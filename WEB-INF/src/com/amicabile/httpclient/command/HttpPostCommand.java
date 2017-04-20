/*    */ package com.amicabile.httpclient.command;
/*    */ 
/*    */ import org.apache.commons.httpclient.HttpClient;
/*    */ import org.apache.commons.httpclient.NameValuePair;
/*    */ import org.apache.commons.httpclient.methods.PostMethod;
/*    */ 
/*    */ public class HttpPostCommand extends HttpCommand
/*    */ {
/*    */   private NameValuePair[] postData;
/*    */   
/*    */   public HttpPostCommand(HttpClient newHttpClient, com.amicabile.httpclient.url.UrlInfo newUrlInfo, NameValuePair... newPostData)
/*    */   {
/* 13 */     super(newHttpClient, newUrlInfo);
/* 14 */     this.postData = newPostData;
/*    */   }
/*    */   
/*    */   public byte[] doPost()
/*    */     throws HttpCommandException
/*    */   {
/*    */     try
/*    */     {
/* 22 */       PostMethod method = new PostMethod(this.urlString);
/* 23 */       method.setRequestBody(this.postData);
/* 24 */       return service(method);
/*    */     } catch (Exception e) {
/* 26 */       throw new HttpCommandException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\httpclient\command\HttpPostCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */