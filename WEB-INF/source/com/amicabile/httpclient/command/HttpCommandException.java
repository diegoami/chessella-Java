package com.amicabile.httpclient.command;

import org.apache.commons.lang.exception.NestableException;

public class HttpCommandException extends NestableException {

   public HttpCommandException(String msg) {
      super(msg);
   }

   public HttpCommandException(Throwable throwable) {
      super(throwable);
   }
}
