package ictk.boardgame.chess.net.ics.fics.event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class TestChunker {

   public static String[] processFile(File file) throws IOException {
      LinkedList list = new LinkedList();
      StringBuffer sb = new StringBuffer(80);
      String line = null;
      BufferedReader in = new BufferedReader(new FileReader(file));
      int lines = 0;

      while((line = in.readLine()) != null) {
         if(line.startsWith("#")) {
            if(lines != 0) {
               list.add(sb.toString());
               sb = new StringBuffer(80);
               lines = 0;
            }
         } else {
            sb.append(line);
            ++lines;
         }
      }

      if(lines != 0) {
         list.add(sb.toString());
      }

      String[] mesg = new String[list.size()];

      for(int i = 0; i < list.size(); ++i) {
         mesg[i] = (String)list.get(i);
      }

      return mesg;
   }
}
