package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSProtocolHandler;

public class ICSConnectionEvent {

   protected ICSProtocolHandler connection;


   public ICSConnectionEvent(ICSProtocolHandler con) {
      this.connection = con;
   }

   public ICSProtocolHandler getConnection() {
      return this.connection;
   }
}
