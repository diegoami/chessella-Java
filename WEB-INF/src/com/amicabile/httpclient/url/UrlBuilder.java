/*    */ package com.amicabile.httpclient.url;
/*    */ 
/*    */ import org.apache.commons.httpclient.NameValuePair;
/*    */ 
/*    */ public class UrlBuilder
/*    */ {
/*    */   private UrlInfo urlInfo;
/*    */   
/*    */   public UrlBuilder(UrlInfo newUrlInfo) {
/* 10 */     this.urlInfo = newUrlInfo;
/*    */   }
/*    */   
/*    */   public String getUrlString() {
/* 14 */     StringBuffer buffer = new StringBuffer();
/* 15 */     buffer.append(this.urlInfo.getUrlLocation());
/* 16 */     boolean questionMarkAdded = false;
/*    */     
/* 18 */     if (!org.apache.commons.lang.ArrayUtils.isEmpty(this.urlInfo.getValuePairs()))
/*    */     {
/* 20 */       for (NameValuePair nameValuePair : this.urlInfo.getValuePairs()) {
/* 21 */         if (!questionMarkAdded) {
/* 22 */           buffer.append("?");
/* 23 */           questionMarkAdded = true;
/*    */         } else {
/* 25 */           buffer.append("&");
/*    */         }
/* 27 */         buffer.append(nameValuePair.getName());
/* 28 */         buffer.append("=");
/* 29 */         buffer.append(nameValuePair.getValue());
/*    */       }
/*    */     }
/*    */     
/* 33 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\httpclient\url\UrlBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */