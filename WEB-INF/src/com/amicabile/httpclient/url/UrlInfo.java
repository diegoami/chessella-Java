/*    */ package com.amicabile.httpclient.url;
/*    */ 
/*    */ import org.apache.commons.httpclient.NameValuePair;
/*    */ 
/*    */ 
/*    */ public class UrlInfo
/*    */ {
/*    */   private NameValuePair[] valuePairs;
/*    */   private String urlLocation;
/*    */   
/*    */   public UrlInfo(String newUrlLocation, NameValuePair... newValuePairs)
/*    */   {
/* 13 */     this.urlLocation = newUrlLocation;
/* 14 */     this.valuePairs = newValuePairs;
/*    */   }
/*    */   
/*    */   public UrlInfo(String newUrlLocation, Object... newValueObjects)
/*    */   {
/* 19 */     this.urlLocation = newUrlLocation;
/* 20 */     this.valuePairs = new NameValuePair[newValueObjects.length];
/* 21 */     for (int i = 0; i < newValueObjects.length; i++) {
/* 22 */       this.valuePairs[i] = ((NameValuePair)newValueObjects[i]);
/*    */     }
/*    */   }
/*    */   
/*    */   public String getUrlLocation() {
/* 27 */     return this.urlLocation;
/*    */   }
/*    */   
/*    */   public void setUrlLocation(String urlLocation) {
/* 31 */     this.urlLocation = urlLocation;
/*    */   }
/*    */   
/*    */   public NameValuePair[] getValuePairs() {
/* 35 */     return this.valuePairs;
/*    */   }
/*    */   
/*    */   public void setValuePairs(NameValuePair[] valuePairs) {
/* 39 */     this.valuePairs = valuePairs;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\httpclient\url\UrlInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */