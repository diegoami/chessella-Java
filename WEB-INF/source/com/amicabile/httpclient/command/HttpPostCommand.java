package com.amicabile.httpclient.command;

import com.amicabile.httpclient.command.HttpCommand;
import com.amicabile.httpclient.command.HttpCommandException;
import com.amicabile.httpclient.url.UrlInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpPostCommand extends HttpCommand {

   private NameValuePair[] postData;


   public HttpPostCommand(HttpClient newHttpClient, UrlInfo newUrlInfo, NameValuePair ... newPostData) {
      super(newHttpClient, newUrlInfo);
      this.postData = newPostData;
   }

   public byte[] doPost() throws HttpCommandException {
      try {
         PostMethod e = new PostMethod(this.urlString);
         e.setRequestBody(this.postData);
         return this.service(e);
      } catch (Exception var2) {
         throw new HttpCommandException(var2);
      }
   }
}
