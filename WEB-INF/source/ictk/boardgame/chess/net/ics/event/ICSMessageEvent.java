package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
import ictk.boardgame.chess.net.ics.event.ICSEvent;

public abstract class ICSMessageEvent extends ICSEvent {

   String player;


   public ICSMessageEvent(ICSProtocolHandler server, int eventType) {
      super(server, eventType);
   }

   public ICSMessageEvent(int eventType) {
      super(eventType);
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public String getPlayer() {
      return this.player;
   }
}
