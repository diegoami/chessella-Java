package com.amicabile.httpclient.command;

import com.amicabile.httpclient.command.HttpCommandException;
import com.amicabile.httpclient.url.UrlBuilder;
import com.amicabile.httpclient.url.UrlInfo;
import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpCommand {

   protected UrlInfo urlInfo;
   private HttpClient httpClient;
   protected String urlString;


   public HttpCommand(HttpClient newHttpClient, UrlInfo newUrlInfo) {
      this.urlInfo = newUrlInfo;
      this.httpClient = newHttpClient;
      UrlBuilder urlBuilder = new UrlBuilder(this.urlInfo);
      this.urlString = urlBuilder.getUrlString();
   }

   protected byte[] service(HttpMethodBase method) throws IOException, HttpException {
      method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));
      int statusCode = this.httpClient.executeMethod(method);
      if(statusCode != 200) {
         System.err.println("Method failed: " + method.getStatusLine());
      }

      byte[] body = method.getResponseBody();
      return body;
   }

   public byte[] doGet() throws HttpCommandException {
      try {
         GetMethod e = new GetMethod(this.urlString);
         return this.service(e);
      } catch (Exception var2) {
         throw new HttpCommandException(var2);
      }
   }
}
