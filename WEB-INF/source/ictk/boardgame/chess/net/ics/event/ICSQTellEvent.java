package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
import ictk.boardgame.chess.net.ics.event.ICSEvent;

public abstract class ICSQTellEvent extends ICSEvent {

   public static final int QTELL_EVENT = 20;


   public ICSQTellEvent(ICSProtocolHandler server) {
      super(server, 20);
   }
}
