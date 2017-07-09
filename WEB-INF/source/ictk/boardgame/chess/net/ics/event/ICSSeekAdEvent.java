package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSRating;
import ictk.boardgame.chess.net.ics.ICSVariant;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdReadableParser;

public class ICSSeekAdEvent extends ICSEvent {

   protected static final int SEEK_AD_EVENT = 12;
   public static final int COLOR_UNSPECIFIED = 0;
   public static final int COLOR_WHITE = 1;
   public static final int COLOR_BLACK = 2;
   protected int number;
   protected String player;
   protected ICSAccountType acctType;
   protected ICSRating rating;
   protected ICSVariant variant;
   protected boolean isRestrictedByFormula;
   protected boolean meetsFormula;
   protected int color;
   protected boolean rated;
   protected boolean notified;
   protected boolean manual;
   protected int time;
   protected int incr;
   protected int rangeLow;
   protected int rangeHigh;


   public ICSSeekAdEvent() {
      super(12);
   }

   public int getAdNumber() {
      return this.number;
   }

   public String getPlayer() {
      return this.player;
   }

   public ICSAccountType getAccountType() {
      return this.acctType;
   }

   public ICSRating getRating() {
      return this.rating;
   }

   public ICSVariant getVariant() {
      return this.variant;
   }

   public boolean isRestrictedByFormula() {
      return this.isRestrictedByFormula;
   }

   public boolean meetsFormula() {
      return this.meetsFormula;
   }

   public int getColor() {
      return this.color;
   }

   public boolean isRated() {
      return this.rated;
   }

   public boolean isNotification() {
      return this.notified;
   }

   public boolean isManual() {
      return this.manual;
   }

   public int getInitialTime() {
      return this.time;
   }

   public int getIncrement() {
      return this.incr;
   }

   public int getRatingRangeLow() {
      return this.rangeLow;
   }

   public int getRatingRangeHigh() {
      return this.rangeHigh;
   }

   public void setAdNumber(int number) {
      this.number = number;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public void setAccountType(ICSAccountType acctType) {
      this.acctType = acctType;
   }

   public void setRating(ICSRating rating) {
      this.rating = rating;
   }

   public void setVariant(ICSVariant variant) {
      this.variant = variant;
   }

   public void setRestrictedByFormula(boolean isRestrictedByFormula) {
      this.isRestrictedByFormula = isRestrictedByFormula;
   }

   public void meetsFormula(boolean meetsFormula) {
      this.meetsFormula = meetsFormula;
   }

   public void setColor(int color) {
      this.color = color;
   }

   public void setRated(boolean rated) {
      this.rated = rated;
   }

   public void setNotification(boolean notified) {
      this.notified = notified;
   }

   public void setManual(boolean manual) {
      this.manual = manual;
   }

   public void setInitialTime(int time) {
      this.time = time;
   }

   public void setIncrement(int incr) {
      this.incr = incr;
   }

   public void setRatingRangeLow(int rangeLow) {
      this.rangeLow = rangeLow;
   }

   public void setRatingRangeHigh(int rangeHigh) {
      this.rangeHigh = rangeHigh;
   }

   public String getReadable() {
      String str = null;
      switch(this.getEventType()) {
      case 12:
         str = FICSSeekAdParser.getInstance().toNative(this);
      case 13:
      default:
         break;
      case 14:
         str = FICSSeekAdReadableParser.getInstance().toNative(this);
      }

      return str;
   }
}
