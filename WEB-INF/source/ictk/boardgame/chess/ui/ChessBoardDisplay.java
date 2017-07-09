package ictk.boardgame.chess.ui;


public interface ChessBoardDisplay {

   int NO_COORDINATES = 0;
   int TOP_COORDINATES = 1;
   int RIGHT_COORDINATES = 2;
   int BOTTOM_COORDINATES = 4;
   int LEFT_COORDINATES = 8;


   void setWhiteOnBottom(boolean var1);

   boolean isWhiteOnBottom();

   void setSideToMoveOnBottom(boolean var1);

   boolean getSideToMoveOnBottom();

   void setVisibleCoordinates(int var1);

   int getVisibleCoordinates();

   void setLowerCaseCoordinates(boolean var1);

   boolean isLowerCaseCoordinates();
}
