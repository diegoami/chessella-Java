/*    */ package com.amicabile.httpclient.command;
/*    */ 
/*    */ import com.amicabile.httpclient.url.UrlBuilder;
/*    */ import com.amicabile.httpclient.url.UrlInfo;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
/*    */ import org.apache.commons.httpclient.HttpClient;
/*    */ import org.apache.commons.httpclient.HttpException;
/*    */ import org.apache.commons.httpclient.HttpMethodBase;
/*    */ import org.apache.commons.httpclient.methods.GetMethod;
/*    */ import org.apache.commons.httpclient.params.HttpMethodParams;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpCommand
/*    */ {
/*    */   protected UrlInfo urlInfo;
/*    */   private HttpClient httpClient;
/*    */   protected String urlString;
/*    */   
/*    */   public HttpCommand(HttpClient newHttpClient, UrlInfo newUrlInfo)
/*    */   {
/* 24 */     this.urlInfo = newUrlInfo;
/* 25 */     this.httpClient = newHttpClient;
/* 26 */     UrlBuilder urlBuilder = new UrlBuilder(this.urlInfo);
/* 27 */     this.urlString = urlBuilder.getUrlString();
/*    */   }
/*    */   
/*    */   protected byte[] service(HttpMethodBase method)
/*    */     throws IOException, HttpException
/*    */   {
/* 33 */     method.getParams().setParameter("http.method.retry-handler", 
/* 34 */       new DefaultHttpMethodRetryHandler(3, false));
/*    */     
/* 36 */     int statusCode = this.httpClient.executeMethod(method);
/*    */     
/* 38 */     if (statusCode != 200) {
/* 39 */       System.err.println("Method failed: " + method.getStatusLine());
/*    */     }
/*    */     
/* 42 */     byte[] body = method.getResponseBody();
/* 43 */     return body;
/*    */   }
/*    */   
/*    */   public byte[] doGet() throws HttpCommandException
/*    */   {
/*    */     try
/*    */     {
/* 50 */       GetMethod method = new GetMethod(this.urlString);
/* 51 */       return service(method);
/*    */     } catch (Exception e) {
/* 53 */       throw new HttpCommandException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\httpclient\command\HttpCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */