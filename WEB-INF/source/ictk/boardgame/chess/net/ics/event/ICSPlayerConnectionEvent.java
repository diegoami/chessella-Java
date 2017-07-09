package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerConnectionParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerNotificationParser;

public class ICSPlayerConnectionEvent extends ICSEvent {

   protected static final int PLAYER_CONNECTION_EVENT = 27;
   protected String player;
   protected ICSAccountType acctType;
   protected boolean connected;
   protected boolean notified;
   protected boolean onNotifyList;


   public ICSPlayerConnectionEvent() {
      super(27);
   }

   public String getPlayer() {
      return this.player;
   }

   public ICSAccountType getAccountType() {
      return this.acctType;
   }

   public boolean isConnected() {
      return this.connected;
   }

   public boolean isNotification() {
      return this.notified;
   }

   public boolean isOnNotifyList() {
      return this.onNotifyList;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public void setAccountType(ICSAccountType acctType) {
      this.acctType = acctType;
   }

   public void setConnected(boolean connected) {
      this.connected = connected;
   }

   public void setNotification(boolean notified) {
      this.notified = notified;
   }

   public void setOnNotifyList(boolean onNotifyList) {
      this.onNotifyList = onNotifyList;
   }

   public String getReadable() {
      String str = null;
      switch(this.getEventType()) {
      case 24:
         str = FICSPlayerNotificationParser.getInstance().toNative(this);
      case 25:
      case 26:
      default:
         break;
      case 27:
         str = FICSPlayerConnectionParser.getInstance().toNative(this);
      }

      return str;
   }
}
