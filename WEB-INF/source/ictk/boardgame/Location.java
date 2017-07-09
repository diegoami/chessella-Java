package ictk.boardgame;

import ictk.boardgame.Piece;

public interface Location {

   int getX();

   int getY();

   Piece getPiece();

   void setX(int var1);

   void setY(int var1);

   Piece setPiece(Piece var1);
}
