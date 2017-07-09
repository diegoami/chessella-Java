package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekRemoveParser;

public class ICSSeekRemoveEvent extends ICSEvent {

   protected static final int SEEK_REMOVE_EVENT = 13;
   protected int[] ads;


   public ICSSeekRemoveEvent() {
      super(13);
   }

   public int[] getAds() {
      return this.ads;
   }

   public void setAds(int[] ads) {
      this.ads = ads;
   }

   public String getReadable() {
      return FICSSeekRemoveParser.getInstance().toNative(this);
   }
}
