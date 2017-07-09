package ictk.boardgame;

import ictk.boardgame.Move;

public interface ContinuationList {

   ContinuationList subList(int var1);

   Move getDepartureMove();

   boolean isTerminal();

   boolean setMainLineTerminal();

   boolean exists(int var1);

   boolean exists(Move var1);

   boolean hasMainLine();

   Move getMainLine();

   boolean hasVariations();

   Move get(int var1);

   int size();

   int sizeOfVariations();

   void add(Move var1, boolean var2);

   void add(Move var1);

   int getIndex(Move var1);

   Move[] find(Move var1);

   int[] findIndex(Move var1);

   void remove(int var1);

   void remove(Move var1);

   void removeAll();

   void removeAllVariations();

   void dispose();

   int promote(Move var1, int var2);

   int demote(Move var1, int var2);

   String dump();
}
