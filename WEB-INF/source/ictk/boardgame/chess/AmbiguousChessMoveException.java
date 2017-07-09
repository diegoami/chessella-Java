package ictk.boardgame.chess;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.chess.ChessPiece;
import java.util.List;

public class AmbiguousChessMoveException extends AmbiguousMoveException {

   protected ChessPiece[] pieces;
   protected int p;
   protected int of;
   protected int or;
   protected int df;
   protected int dr;


   public AmbiguousChessMoveException() {}

   public AmbiguousChessMoveException(String mesg) {
      super(mesg);
   }

   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr) {
      this(mesg, p, of, or, df, dr, (List)null);
   }

   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr, List dupes) {
      super(mesg);
      this.p = p;
      this.of = of;
      this.or = or;
      this.df = df;
      this.dr = dr;
      if(dupes != null) {
         Object[] objs = dupes.toArray();
         this.pieces = new ChessPiece[objs.length];

         for(int i = 0; i < objs.length; ++i) {
            this.pieces[i] = (ChessPiece)objs[i];
         }
      }

   }

   public AmbiguousChessMoveException(String mesg, int p, int of, int or, int df, int dr, ChessPiece[] dupes) {
      super(mesg);
      this.p = p;
      this.of = of;
      this.or = or;
      this.df = df;
      this.dr = dr;
      this.pieces = dupes;
   }

   public ChessPiece[] getPieces() {
      return this.pieces;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append(this.getMessage()).append(" ");
      if(this.p != 0 && this.p != 32) {
         sb.append(this.p);
      }

      if(this.of != 0 && this.of != 32) {
         sb.append(this.of);
      }

      if(this.or != 0 && this.of != 32) {
         sb.append(this.or);
      }

      if(this.df != 0 && this.of != 32) {
         sb.append(this.df);
      }

      if(this.dr != 0 && this.of != 32) {
         sb.append(this.dr);
      }

      return sb.toString();
   }
}
