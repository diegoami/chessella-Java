package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.event.ICSMessageEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParser;

public class ICSChannelEvent extends ICSMessageEvent {

   protected static final int CHANNEL_EVENT = 6;
   public static final int SHOUT_CHANNEL = 1;
   public static final int EMOTE_CHANNEL = 2;
   public static final int CSHOUT_CHANNEL = 3;
   public static final int SSHOUT_CHANNEL = 4;
   public static final int TSHOUT_CHANNEL = 5;
   protected String player;
   protected ICSAccountType acctType;
   protected int channel;


   public ICSChannelEvent() {
      super(6);
   }

   public String getPlayer() {
      return this.player;
   }

   public ICSAccountType getAccountType() {
      return this.acctType;
   }

   public int getChannel() {
      return this.channel;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public void setAccountType(ICSAccountType acctType) {
      this.acctType = acctType;
   }

   public void setChannel(int channel) {
      this.channel = channel;
   }

   public String getReadable() {
      String str = null;
      switch(this.getEventType()) {
      case 6:
         str = FICSChannelParser.getInstance().toNative(this);
         break;
      case 7:
         str = FICSShoutParser.getInstance().toNative(this);
      }

      return str;
   }
}
