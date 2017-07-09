package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekClearParser;

public class ICSSeekClearEvent extends ICSEvent {

   protected static final int SEEK_CLEAR_EVENT = 16;


   public ICSSeekClearEvent() {
      super(16);
   }

   public String getReadable() {
      return FICSSeekClearParser.getInstance().toNative(this);
   }
}
