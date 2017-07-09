package com.amicabile.support;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class SimpleReader extends FilterReader {

   public SimpleReader(Reader in) {
      super(in);
   }

   public int read(char[] cbuf, int off, int len) throws IOException {
      int ch;
      for(int i = 0; i < len; cbuf[off + i++] = (char)ch) {
         ch = this.read();
         if(ch == -1) {
            if(i == 0) {
               return -1;
            }

            return i;
         }

         System.out.println(ch);
      }

      return len;
   }
}
