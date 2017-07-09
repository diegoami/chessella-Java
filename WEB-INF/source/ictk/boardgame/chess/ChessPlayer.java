package ictk.boardgame.chess;

import ictk.boardgame.Player;

public class ChessPlayer extends Player {

   public static final byte NO_TITLE = 0;
   public static final byte GM = 1;
   public static final byte WGM = 2;
   public static final byte IM = 3;
   public static final byte WIM = 4;
   public static final byte FM = 5;
   public static final byte WFM = 6;
   public static final byte NM = 7;
   public static final byte WNM = 8;
   public static final byte OTHER_TITLE = 9;
   protected byte title;
   protected int rating;


   public ChessPlayer() {}

   public ChessPlayer(String n) {
      super(n);
   }

   public ChessPlayer(String n, int _rating) {
      super(n);
      this.rating = _rating;
   }

   public byte getTitle() {
      return this.title;
   }

   public int getRating() {
      return this.rating;
   }

   public void setTitle(int t) {
      if(t <= 128 && t >= -128) {
         this.title = (byte)t;
      } else {
         throw new IllegalArgumentException("Title needs to be of byte size");
      }
   }

   public void setRating(int rating) {
      this.rating = rating;
   }

   public String toString() {
      String str = this.getName();
      if(this.rating > 0) {
         str = str + "(" + this.rating + ")";
      }

      return str;
   }
}
