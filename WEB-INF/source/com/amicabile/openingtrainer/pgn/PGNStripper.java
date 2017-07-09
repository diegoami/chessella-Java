package com.amicabile.openingtrainer.pgn;

import ictk.boardgame.ContinuationList;
import ictk.boardgame.History;
import ictk.boardgame.Move;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.io.PGNReader;
import ictk.boardgame.chess.io.PGNWriter;
import ictk.boardgame.io.Annotation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import org.apache.commons.digester.SimpleRegexMatcher;
import org.apache.log4j.Logger;

public class PGNStripper {

   private static transient Logger log = Logger.getLogger(PGNStripper.class);


   private String stripAnnotation(Annotation annotation) {
      String comment = annotation.getComment();
      if(comment == null) {
         return null;
      } else if(comment.contains("[")) {
         return "";
      } else {
         SimpleRegexMatcher regexMatcher = new SimpleRegexMatcher();
         return regexMatcher.match(comment, "[a-zA-Z0-9 ]*")?"":comment;
      }
   }

   public void stripComments(Reader freader, Writer writer) {
      PGNReader reader = new PGNReader(freader);
      PGNWriter pgnWriter = new PGNWriter(writer);
      ChessGame game = null;
      int gameCounter = 0;

      do {
         ++gameCounter;

         try {
            game = (ChessGame)reader.readGame();
            log.debug("READ GAME " + gameCounter);
            if(game != null) {
               History e = game.getHistory();
               e.rewind();
               Move firstMove = e.getFirst();
               ContinuationList continuationList = firstMove.getContinuationList();

               while(e.getNext() != null) {
                  Move nextMove = e.next();
                  log.debug("READ MOVE = " + nextMove);
                  Annotation annotation = nextMove.getAnnotation();
                  Annotation prenotation = nextMove.getPrenotation();
                  if(annotation != null) {
                     annotation.setComment(this.stripAnnotation(annotation));
                  }

                  if(prenotation != null) {
                     prenotation.setComment(this.stripAnnotation(prenotation));
                  }
               }

               pgnWriter.writeGame(game);
            }
         } catch (Exception var13) {
            var13.printStackTrace();
         }
      } while(game != null);

   }

   public static void main(String[] args) throws Exception {
      if(args.length < 2) {
         System.out.println("java com.amicabile.openingtrainer.pgn.PGNStripper <infile> <outfile>");
      } else {
         String fileInString = args[0];
         String fileOutString = args[1];
         FileWriter fw = null;
         Object fwriter = null;
         BufferedWriter bw = null;
         FileReader fr = null;
         Object freader = null;
         BufferedReader br = null;

         try {
            bw = new BufferedWriter(fw = new FileWriter(fileOutString));
            br = new BufferedReader(fr = new FileReader(fileInString));
            (new PGNStripper()).stripComments(br, bw);
         } finally {
            br.close();
            bw.close();
            fr.close();
            fw.close();
         }
      }

   }
}
