package com.amicabile.openingtrainer.pgn;

import org.apache.commons.lang.exception.NestableException;

public class PGNException extends NestableException {

   public PGNException(String msg) {
      super(msg);
   }

   public PGNException(Throwable throwable) {
      super(throwable);
   }
}
