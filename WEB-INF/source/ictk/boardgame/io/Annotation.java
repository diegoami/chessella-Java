package ictk.boardgame.io;


public interface Annotation {

   String getComment();

   void setComment(String var1);

   void appendComment(String var1);

   String dump();
}
