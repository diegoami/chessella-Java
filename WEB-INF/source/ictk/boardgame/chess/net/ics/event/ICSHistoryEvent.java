package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSGameInfo;
import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
import ictk.boardgame.chess.net.ics.event.ICSEvent;

public class ICSHistoryEvent extends ICSEvent {

   protected static final int HISTORY_EVENT = 28;
   ICSGameInfo[] list;
   String player;


   public ICSHistoryEvent(ICSProtocolHandler server) {
      super(server, 28);
   }

   public String getPlayer() {
      return this.player;
   }

   public void setPlayer(String name) {
      this.player = name;
   }

   public ICSGameInfo[] getHistoryList() {
      return this.list;
   }

   public void setHistoryList(ICSGameInfo[] l) {
      this.list = l;
   }

   public String getReadable() {
      return "History List";
   }
}
