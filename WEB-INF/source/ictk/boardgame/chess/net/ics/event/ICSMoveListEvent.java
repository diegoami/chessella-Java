package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSDate;
import ictk.boardgame.chess.net.ics.ICSMove;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSResult;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSBoardEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSMoveListParser;

public class ICSMoveListEvent extends ICSEvent implements ICSBoardEvent {

   protected static final int MOVE_LIST_EVENT = 22;
   protected int boardNumber;
   protected int initTime;
   protected int incrTime;
   protected String white;
   protected String black;
   protected String status;
   protected ICSRating whiteRating;
   protected ICSRating blackRating;
   protected ICSDate date;
   protected boolean isRated;
   protected ICSVariant variant;
   protected ICSResult result;
   protected ICSMove[] moves;


   public ICSMoveListEvent() {
      super(22);
   }

   public ICSMove[] getMoves() {
      return this.moves;
   }

   public void setMoves(ICSMove[] list) {
      this.moves = list;
   }

   public String getWhitePlayer() {
      return this.white;
   }

   public void setWhitePlayer(String player) {
      this.white = player;
   }

   public String getBlackPlayer() {
      return this.black;
   }

   public void setBlackPlayer(String player) {
      this.black = player;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public void setVariant(ICSVariant gameType) {
      this.variant = gameType;
   }

   public ICSRating getWhiteRating() {
      return this.whiteRating;
   }

   public void setWhiteRating(ICSRating rating) {
      this.whiteRating = rating;
   }

   public ICSRating getBlackRating() {
      return this.blackRating;
   }

   public void setBlackRating(ICSRating rating) {
      this.blackRating = rating;
   }

   public ICSResult getResult() {
      return this.result;
   }

   public void setResult(ICSResult res) {
      this.result = res;
   }

   public int getInitialTime() {
      return this.initTime;
   }

   public void setInitialTime(int minutes) {
      this.initTime = minutes;
   }

   public int getIncrement() {
      return this.incrTime;
   }

   public void setIncrement(int seconds) {
      this.incrTime = seconds;
   }

   public boolean isRated() {
      return this.isRated;
   }

   public void setRated(boolean rated) {
      this.isRated = rated;
   }

   public void setBoardNumber(int board) {
      this.boardNumber = board;
   }

   public int getBoardNumber() {
      return this.boardNumber;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setDate(ICSDate date) {
      this.date = date;
   }

   public ICSDate getDate() {
      return this.date;
   }

   public String getReadable() {
      return FICSMoveListParser.getInstance().toNative(this);
   }
}
