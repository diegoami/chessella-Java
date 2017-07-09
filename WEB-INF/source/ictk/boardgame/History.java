package ictk.boardgame;

import ictk.boardgame.AmbiguousMoveException;
import ictk.boardgame.Board;
import ictk.boardgame.ContinuationArrayList;
import ictk.boardgame.ContinuationList;
import ictk.boardgame.Game;
import ictk.boardgame.IllegalMoveException;
import ictk.boardgame.Move;
import ictk.boardgame.MoveException;
import ictk.boardgame.OutOfTurnException;
import ictk.boardgame.chess.ChessGame;
import ictk.boardgame.chess.io.FEN;
import ictk.util.Log;
import java.util.LinkedList;
import java.util.ListIterator;

public class History {

   public static final long DEBUG = Log.History;
   protected Game game;
   protected ContinuationList head;
   protected Move currMove;
   protected int initialMoveNumber;
   protected int currMoveNumber;


   public History(Game game) {
      this(game, 1);
   }

   public History(Game game, int initMoveNum) {
      this.initialMoveNumber = 1;
      this.currMoveNumber = 0;
      this.game = game;
      this.head = new ContinuationArrayList((Move)null);
      this.currMove = null;
      this.currMoveNumber = this.initialMoveNumber = initMoveNum;
   }

   public void setGame(Game newGame) {
      this.game = newGame;
   }

   public Object clone() {
      FEN fen = new FEN();
      fen.boardToString(this.getGame().getBoard());
      ChessGame newGame = new ChessGame(this.getGame().getGameInfo(), this.getGame().getBoard(), this);
      History history = new History(newGame, this.currMoveNumber);
      newGame.setHistory(history);
      history.currMove = this.currMove;
      history.head = this.head;
      return history;
   }

   public int getInitialMoveNumber() {
      return this.initialMoveNumber;
   }

   public void setInitialMoveNumber(int i) {
      this.currMoveNumber += i - this.initialMoveNumber;
      this.initialMoveNumber = i;
   }

   public int getCurrentMoveNumber() {
      return this.currMoveNumber;
   }

   public void add(Move _move, boolean asMainLine) throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException {
      Object next = null;
      boolean found = false;
      ContinuationList cont = null;
      _move.setHistory(this);
      if(this.currMove != null) {
         cont = this.currMove.getContinuationList();
      } else {
         cont = this.head;
      }

      cont.add(_move, asMainLine);

      try {
         this._next(_move);
      } catch (OutOfTurnException var7) {
         this._delBadAdd(_move);
         throw var7;
      } catch (IllegalMoveException var8) {
         this._delBadAdd(_move);
         throw var8;
      } catch (AmbiguousMoveException var9) {
         this._delBadAdd(_move);
         throw var9;
      }
   }

   public void add(Move m) throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException {
      ContinuationList cont = this.head;
      if(this.currMove != null) {
         cont = this.currMove.getContinuationList();
      }

      if(cont.hasMainLine()) {
         this.add(m, false);
      } else {
         this.add(m, true);
      }

   }

   public void addVariation(Move m) throws IllegalMoveException, IndexOutOfBoundsException, OutOfTurnException, AmbiguousMoveException {
      this.add(m, false);
   }

   public Move getFirst() {
      return this.head.getMainLine();
   }

   public ContinuationList getFirstAll() {
      return this.head;
   }

   public Move next() {
      try {
         return this.next(0);
      } catch (IndexOutOfBoundsException var2) {
         throw new IndexOutOfBoundsException("No main line for move:" + this.currMove);
      }
   }

   public Move next(int i) {
      try {
         return this._next(i);
      } catch (MoveException var3) {
         assert false : "Already verified move threw: " + var3.getMessage() + " for move: " + var3.getMove().dump();

         return null;
      }
   }

   public Move next(Move m) {
      ContinuationList cont = this.head;
      if(this.currMove != null) {
         cont = this.currMove.getContinuationList();
      }

      if(!cont.exists(m)) {
         throw new IndexOutOfBoundsException("this move is not found in the current list of continuations.");
      } else {
         try {
            return this._next(m);
         } catch (MoveException var4) {
            assert false : "Already verified move threw: " + var4.getMessage() + " for move: " + var4.getMove().dump();

            return null;
         }
      }
   }

   protected Move _next(Move m) throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      m.execute();
      ++this.currMoveNumber;
      this.currMove = m;
      return this.currMove;
   }

   protected Move _next(int i) throws IllegalMoveException, OutOfTurnException, AmbiguousMoveException {
      Move m = null;
      if(this.currMove != null) {
         m = this.currMove.getContinuationList().get(i);
      } else {
         m = this.head.get(i);
      }

      if(m == null) {
         throw new IndexOutOfBoundsException("variation [" + i + "] does not exist for " + this.currMove);
      } else {
         this._next(m);
         return this.currMove;
      }
   }

   public Move getNext() {
      return this.getNext(0);
   }

   public Move getNext(int i) {
      return this.currMove == null?this.head.get(i):this.currMove.getContinuationList().get(i);
   }

   public ContinuationList getContinuationList() {
      return this.currMove == null?this.head:this.currMove.getContinuationList();
   }

   public Move prev() {
      Move prev = null;
      if(this.currMove == null) {
         prev = null;
      } else {
         this.currMove.unexecute();
         prev = this.currMove;
         this.currMove = this.currMove.prev;
         --this.currMoveNumber;
      }

      return prev;
   }

   public Move goTo(Move m) {
      if(m == null) {
         this.rewind();
         return null;
      } else {
         LinkedList tracks = new LinkedList();
         Move walker = m;

         do {
            tracks.addFirst(walker);
         } while((walker = walker.prev) != null);

         this.notifyBoardsOfTraversal(true);
         this._rewind();

         for(ListIterator li = tracks.listIterator(0); li.hasNext(); this.currMove = walker) {
            walker = (Move)li.next();

            try {
               walker.execute();
               ++this.currMoveNumber;
            } catch (OutOfTurnException var6) {
               assert false : "Previously verified move threw " + var6 + " History is out of sequence for some reason.";
            } catch (IllegalMoveException var7) {
               assert false : "Previously verified move threw " + var7;
            }
         }

         this.notifyBoardsOfTraversal(false);

         assert this.getCurrentMove() == m : "History couldn\'t walk back to " + m + " last move is " + this.getCurrentMove();

         return m;
      }
   }

   protected void _rewind() {
      while(this.currMove != null) {
         this.currMove.unexecute();
         this.currMove = this.currMove.prev;
      }

      this.currMoveNumber = this.initialMoveNumber;
   }

   public void rewind() {
      this.notifyBoardsOfTraversal(true);
      this._rewind();
      this.notifyBoardsOfTraversal(false);
   }

   public int fastforward(int n) {
      this.notifyBoardsOfTraversal(true);

      int count;
      for(count = 0; this.hasNext() && count < n; ++count) {
         this.next();
      }

      this.notifyBoardsOfTraversal(false);
      return count;
   }

   public void goToEnd() {
      this.notifyBoardsOfTraversal(true);

      while(this.hasNext()) {
         this.next();
      }

      this.notifyBoardsOfTraversal(false);
   }

   public Move rewindToLastFork() {
      if(this.currMove == null) {
         return this.currMove;
      } else {
         this.notifyBoardsOfTraversal(true);

         do {
            this.currMove.unexecute();
            --this.currMoveNumber;
            this.currMove = this.currMove.prev;
         } while(this.currMove != null && !this.currMove.getContinuationList().hasVariations());

         this.notifyBoardsOfTraversal(false);
         return this.currMove;
      }
   }

   public boolean hasNext() {
      boolean hasNext = true;
      if(this.currMove == null) {
         hasNext = this.head.hasMainLine();
      } else {
         hasNext = this.currMove.getContinuationList().hasMainLine();
      }

      return hasNext;
   }

   public boolean isEmpty() {
      return this.head.size() == 0;
   }

   public Move getCurrentMove() {
      return this.currMove;
   }

   public Move getFinalMove(boolean trueMainLine) {
      Move walker = null;
      if(trueMainLine) {
         walker = this.head.get(0);
      } else {
         walker = this.currMove;
      }

      while(walker != null && !walker.getContinuationList().isTerminal()) {
         walker = walker.getContinuationList().getMainLine();
      }

      return walker;
   }

   public Move removeLastMove() {
      Move gonner = null;
      gonner = this.prev();
      if(this.currMove != null) {
         this.currMove.getContinuationList().remove(gonner);
      } else {
         this.head.remove(gonner);
      }

      return gonner;
   }

   public Move truncate(int i) {
      if(i == -1) {
         if(this.currMove != null) {
            this.currMove.getContinuationList().removeAll();
         } else {
            this.head.removeAll();
         }
      } else if(this.currMove != null) {
         this.currMove.getContinuationList().remove(i);
      } else {
         this.head.remove(i);
      }

      return this.currMove;
   }

   public Move truncate() {
      return this.truncate(-1);
   }

   protected void _delBadAdd(Move gonner) {
      if(this.currMove != null) {
         this.currMove.getContinuationList().remove(gonner);
      } else {
         this.head.remove(gonner);
      }

   }

   public int size() {
      Move walker = null;
      int i = 0;

      for(walker = this.head.getMainLine(); walker != null; walker = walker.getContinuationList().getMainLine()) {
         ++i;
      }

      return i;
   }

   public String toString() {
      Move walker = null;
      String str = new String("");
      String tmp = null;
      walker = this.head.getMainLine();
      int i = 1;

      while(walker != null) {
         if(i % 2 == 1) {
            tmp = walker.toString();

            for(int j = tmp.length(); j < 9; ++j) {
               tmp = tmp + " ";
            }

            str = str + (i / 2 + 1) + (i < 19?".  ":". ");
            str = str + tmp;
         } else {
            str = str + " " + walker + "\n";
         }

         ++i;
         if(walker.getContinuationList().hasMainLine()) {
            walker = walker.getContinuationList().getMainLine();
         } else {
            walker = null;
         }
      }

      return str;
   }

   public boolean equals(History hist) {
      Move walker = this.head.getMainLine();

      Move walker2;
      for(walker2 = hist.head.getMainLine(); walker != null && walker2 != null && walker.equals(walker2); walker2 = walker2.getContinuationList().getMainLine()) {
         walker = walker.getContinuationList().getMainLine();
      }

      return walker == walker2 && walker == null;
   }

   public boolean equalsPartly(History hist) {
      Move walker = this.head.getMainLine();
      Move walker2 = hist.head.getMainLine();
      Move previous = null;

      Move previous2;
      for(previous2 = null; walker != null && walker2 != null && walker.equals(walker2); walker2 = walker2.getContinuationList().getMainLine()) {
         previous = walker;
         previous2 = walker2;
         walker = walker.getContinuationList().getMainLine();
      }

      return previous == null && previous2 == null?true:(previous == null && previous2 != null?false:(previous2 == null && previous != null?false:previous.toString().equals(previous2.toString())));
   }

   public boolean deepEquals(History hist, boolean checkAnno) {
      boolean t = false;
      Log.debug(DEBUG, "beginning probe:" + (checkAnno?"":" not") + " checking Annotations");
      t = this.probeDeepEquals(this.head, hist.head, checkAnno);
      Log.debug(DEBUG, "histories: " + (t?"same":"different"));
      return t;
   }

   public Game getGame() {
      return this.game;
   }

   protected boolean probeDeepEquals(ContinuationList cont, ContinuationList cont2, boolean checkAnno) {
      Move move1 = null;
      Move move2 = null;
      Move[] possibleMatches = (Move[])null;
      boolean t = true;
      boolean found = false;
      boolean isMatch = true;
      boolean j = false;
      if(cont.isTerminal() && cont2.isTerminal()) {
         Log.debug(DEBUG, "+ both histories terminate");
         return t;
      } else {
         for(int i = 0; t && i < cont.size(); ++i) {
            move1 = cont.get(i);
            if(i == 0 && move1 == null && cont2.get(0) == null) {
               Log.debug(DEBUG, "+ both mainlines are null");
               t = true;
            } else if((possibleMatches = cont2.find(move1)) == null) {
               Log.debug(DEBUG, "- move (" + move1 + ") not found in continuation list of game 2");
               t = false;
            } else {
               found = false;
               Log.debug(DEBUG, "+ (" + possibleMatches.length + ") possible matches of (" + move1 + ")");

               int var12;
               for(var12 = 0; var12 < possibleMatches.length && !found; ++var12) {
                  move2 = possibleMatches[var12];
                  isMatch = move2 != null;
                  if(!isMatch) {
                     Log.error(2, "- hmm, possible match was null?");
                  }

                  if(isMatch && checkAnno) {
                     isMatch = move1.getAnnotation() == move2.getAnnotation() || move1.getAnnotation() != null && move2.getAnnotation() != null && move1.getAnnotation().equals(move2.getAnnotation());
                     Log.debug(DEBUG, (isMatch?"+":"-") + " [" + (var12 + 1) + "/" + possibleMatches.length + "] (" + move1 + ") annotation: " + (isMatch?"same":"different"));
                  }

                  if(isMatch && checkAnno) {
                     isMatch = move1.getPrenotation() == move2.getPrenotation() || move1.getPrenotation() != null && move2.getPrenotation() != null && move1.getPrenotation().equals(move2.getPrenotation());
                     Log.debug(DEBUG, (isMatch?"+":"-") + " [" + (var12 + 1) + "/" + possibleMatches.length + "] (" + move1 + ") prenotation: " + (isMatch?"same":"different"));
                  }

                  if(isMatch && checkAnno) {
                     isMatch = (move1.getResult() == null || move1.getResult().isUndecided()) && move2.getResult() == null || move2.getResult().isUndecided() || move1.getResult() != null && move2.getResult() != null && move1.getResult().equals(move2.getResult());
                     if(move1.getResult() != null || move2.getResult() != null) {
                        Log.debug(DEBUG, (isMatch?"+":"-") + " [" + (var12 + 1) + "/" + possibleMatches.length + "] (" + move1 + ") result: " + (isMatch?"same":"different") + (move1.getResult() != null && move2.getResult() != null?"":" {" + (move1.getResult() == null?"null/undecided":"undecided/null") + "}"));
                     }
                  }

                  isMatch = isMatch && this.probeDeepEquals(move1.getContinuationList(), move2.getContinuationList(), checkAnno);
                  found = isMatch;
               }

               Log.debug(DEBUG, "(" + move1 + ") continuation: " + (found?"[" + var12 + "/" + possibleMatches.length + "] matched":"no match found"));
               t = found;
            }
         }

         return t;
      }
   }

   public int hashCode() {
      return 0;
   }

   protected void notifyBoardsOfTraversal(boolean begin) {
      Board[] boards = (Board[])null;
      int event = begin?3:4;
      boards = this.game.getBoards();
      if(boards != null) {
         for(int i = 0; i < boards.length; ++i) {
            boards[i].fireBoardEvent(event);
         }
      }

   }
}
