package ictk.boardgame.chess.net.ics;

import ictk.boardgame.chess.net.ics.ICSEco;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSResult;
import ictk.boardgame.chess.net.ics.ICSVariant;
import java.util.Calendar;

public class ICSGameInfo {

   protected boolean isBlack;
   protected boolean isRated;
   protected int index;
   protected int initTime;
   protected int incrTime;
   protected String player;
   protected String opponent;
   protected String endBy;
   protected ICSRating playerRating;
   protected ICSRating oppRating;
   protected ICSVariant variant;
   protected ICSEco eco;
   protected ICSResult result;
   protected Calendar date;


   public int getIndex() {
      return this.index;
   }

   public void setIndex(int idx) {
      this.index = idx;
   }

   public String getPlayer() {
      return this.player;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public String getOpponent() {
      return this.opponent;
   }

   public void setOpponent(String name) {
      this.opponent = name;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public void setVariant(ICSVariant gameType) {
      this.variant = gameType;
   }

   public ICSRating getPlayerRating() {
      return this.playerRating;
   }

   public void setPlayerRating(ICSRating rating) {
      this.playerRating = rating;
   }

   public ICSRating getOpponentRating() {
      return this.oppRating;
   }

   public void setOpponentRating(ICSRating rating) {
      this.oppRating = rating;
   }

   public ICSEco getEco() {
      return this.eco;
   }

   public void setEco(ICSEco eco) {
      this.eco = eco;
   }

   public Calendar getDate() {
      return this.date;
   }

   public void setDate(Calendar date) {
      this.date = date;
   }

   public ICSResult getResult() {
      return this.result;
   }

   public void setResult(ICSResult res) {
      this.result = res;
   }

   public boolean isBlack() {
      return this.isBlack;
   }

   public void setBlack(boolean t) {
      this.isBlack = t;
   }

   public int getInitialTime() {
      return this.initTime;
   }

   public void setInitialTime(int minutes) {
      this.initTime = minutes;
   }

   public int getIncrementTime() {
      return this.incrTime;
   }

   public void setIncrementTime(int seconds) {
      this.incrTime = seconds;
   }

   public boolean isRated() {
      return this.isRated;
   }

   public void setRated(boolean rated) {
      this.isRated = rated;
   }

   public String toString() {
      return "HISTORY: " + this.getPlayer() + "[" + this.getPlayerRating() + "] v " + this.getOpponent() + "[" + this.getOpponentRating() + "]";
   }
}
