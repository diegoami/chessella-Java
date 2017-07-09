package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
import ictk.boardgame.chess.net.ics.event.ICSEvent;

public class ICSUnknownEvent extends ICSEvent {

   public static final int UNKNOWN_EVENT = 0;


   public ICSUnknownEvent(ICSProtocolHandler server) {
      super(server, 0);
   }

   public ICSUnknownEvent(ICSProtocolHandler server, String mesg) {
      super(server, 0);
      this.message = mesg;
   }

   public String getReadable() {
      return this.message;
   }
}
