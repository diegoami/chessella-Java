package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSChallengeParser;

public class ICSChallengeEvent extends ICSEvent {

   protected static final int CHALLENGE_EVENT = 11;
   protected String challenger;
   protected ICSRating challengerRating;
   protected boolean isColorSpecified;
   protected boolean isWhite;
   protected String challenged;
   protected ICSRating challengedRating;
   protected boolean rated;
   protected ICSVariant variant;
   protected int time;
   protected int incr;
   protected boolean computer;
   protected boolean abuser;


   public ICSChallengeEvent() {
      super(11);
   }

   public String getChallenger() {
      return this.challenger;
   }

   public ICSRating getChallengerRating() {
      return this.challengerRating;
   }

   public boolean isColorSpecified() {
      return this.isColorSpecified;
   }

   public boolean isWhite() {
      return this.isWhite;
   }

   public String getChallenged() {
      return this.challenged;
   }

   public ICSRating getChallengedRating() {
      return this.challengedRating;
   }

   public boolean isRated() {
      return this.rated;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public int getInitialTime() {
      return this.time;
   }

   public int getIncrementTime() {
      return this.incr;
   }

   public boolean isComputer() {
      return this.computer;
   }

   public boolean isAbuser() {
      return this.abuser;
   }

   public void setChallenger(String challenger) {
      this.challenger = challenger;
   }

   public void setChallengerRating(ICSRating challengerRating) {
      this.challengerRating = challengerRating;
   }

   public void setColorSpecified(boolean isColorSpecified) {
      this.isColorSpecified = isColorSpecified;
   }

   public void setWhite(boolean isWhite) {
      this.isWhite = isWhite;
   }

   public void setChallenged(String challenged) {
      this.challenged = challenged;
   }

   public void setChallengedRating(ICSRating challengedRating) {
      this.challengedRating = challengedRating;
   }

   public void setRated(boolean rated) {
      this.rated = rated;
   }

   public void setVariant(ICSVariant variant) {
      this.variant = variant;
   }

   public void setInitialTime(int time) {
      this.time = time;
   }

   public void setIncrementTime(int incr) {
      this.incr = incr;
   }

   public void setComputer(boolean computer) {
      this.computer = computer;
   }

   public void setAbuser(boolean abuser) {
      this.abuser = abuser;
   }

   public String getReadable() {
      return FICSChallengeParser.getInstance().toNative(this);
   }
}
