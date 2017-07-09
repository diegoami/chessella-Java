package com.amicabile.httpclient.url;

import org.apache.commons.httpclient.NameValuePair;

public class UrlInfo {

   private NameValuePair[] valuePairs;
   private String urlLocation;


   public UrlInfo(String newUrlLocation, NameValuePair ... newValuePairs) {
      this.urlLocation = newUrlLocation;
      this.valuePairs = newValuePairs;
   }

   public UrlInfo(String newUrlLocation, Object ... newValueObjects) {
      this.urlLocation = newUrlLocation;
      this.valuePairs = new NameValuePair[newValueObjects.length];

      for(int i = 0; i < newValueObjects.length; ++i) {
         this.valuePairs[i] = (NameValuePair)newValueObjects[i];
      }

   }

   public String getUrlLocation() {
      return this.urlLocation;
   }

   public void setUrlLocation(String urlLocation) {
      this.urlLocation = urlLocation;
   }

   public NameValuePair[] getValuePairs() {
      return this.valuePairs;
   }

   public void setValuePairs(NameValuePair[] valuePairs) {
      this.valuePairs = valuePairs;
   }
}
