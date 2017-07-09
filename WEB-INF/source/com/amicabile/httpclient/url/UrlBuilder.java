package com.amicabile.httpclient.url;

import com.amicabile.httpclient.url.UrlInfo;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;

public class UrlBuilder {

   private UrlInfo urlInfo;


   public UrlBuilder(UrlInfo newUrlInfo) {
      this.urlInfo = newUrlInfo;
   }

   public String getUrlString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.urlInfo.getUrlLocation());
      boolean questionMarkAdded = false;
      if(!ArrayUtils.isEmpty((Object[])this.urlInfo.getValuePairs())) {
         NameValuePair[] var6 = this.urlInfo.getValuePairs();
         int var4 = 0;

         for(int var5 = var6.length; var4 < var5; ++var4) {
            NameValuePair nameValuePair = var6[var4];
            if(!questionMarkAdded) {
               buffer.append("?");
               questionMarkAdded = true;
            } else {
               buffer.append("&");
            }

            buffer.append(nameValuePair.getName());
            buffer.append("=");
            buffer.append(nameValuePair.getValue());
         }
      }

      return buffer.toString();
   }
}
