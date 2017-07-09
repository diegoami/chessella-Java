package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;

public interface ICSConnectionListener {

   void connectionStatusChanged(ICSConnectionEvent var1);
}
