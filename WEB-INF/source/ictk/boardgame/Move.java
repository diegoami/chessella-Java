package ictk.boardgame;

import ictk.boardgame.Board;
import ictk.boardgame.ContinuationArrayList;
import ictk.boardgame.ContinuationList;
import ictk.boardgame.History;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.Result;
import ictk.boardgame.io.Annotation;

public abstract class Move {

   protected boolean executed = false;
   protected boolean verified = false;
   protected History history;
   protected ContinuationList continuation = new ContinuationArrayList(this);
   protected Move prev;
   protected Result result;
   protected Annotation prenotation;
   protected Annotation annotation;


   public abstract boolean isNullMove();

   public History getHistory() {
      return this.history;
   }

   public abstract Board getBoard();

   public void setHistory(History h) {
      this.history = h;
   }

   protected abstract void setBoard(Board var1);

   protected void setPrev(Move m) {
      this.prev = m;
   }

   public ContinuationList getContinuationList() {
      return this.continuation;
   }

   public Move getPrev() {
      return this.prev;
   }

   public boolean hasNext() {
      return !this.continuation.hasMainLine();
   }

   public Move getNext() {
      return this.continuation.getMainLine();
   }

   protected void addNext(Move move) {
      this.continuation.add(move);
   }

   public void dispose() {
      if(this.isExecuted()) {
         throw new IllegalStateException("Cannot dispose of an executed move.  Rewind history first.");
      } else {
         this.history = null;
         this.prev = null;
         this.continuation.dispose();
         this.continuation = null;
         this.history = null;
      }
   }

   public abstract boolean isLegal();

   protected abstract void execute() throws IllegalMoveException, OutOfTurnException;

   protected abstract void unexecute();

   public boolean isExecuted() {
      return this.executed;
   }

   public boolean isVerified() {
      return this.verified;
   }

   public Annotation getPrenotation() {
      return this.prenotation;
   }

   public void setPrenotation(Annotation anno) {
      this.prenotation = anno;
   }

   public Annotation getAnnotation() {
      return this.annotation;
   }

   public void setAnnotation(Annotation anno) {
      this.annotation = anno;
   }

   public Result getResult() {
      return this.result;
   }

   public void setResult(Result res) {
      this.result = res;
   }

   public abstract String toString();

   public String dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("prev: ").append(this.prev.toString()).append("\n").append("continuation#:").append(this.continuation.dump()).append("\n").append("prenotation: ").append(this.prenotation).append("\n").append("annotation: ").append(this.annotation).append("\n").append("result: ").append(this.result);
      return sb.toString();
   }
}
