package ictk.boardgame.chess.net.ics.event;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.event.ICSMessageEvent;
import ictk.boardgame.chess.net.ics.fics.event.FICSTellParser;

public class ICSTellEvent extends ICSMessageEvent {

   protected static final int TELL_EVENT = 9;
   protected String player;
   protected ICSAccountType acctType;
   protected String mesg;


   public ICSTellEvent() {
      super(9);
   }

   public String getPlayer() {
      return this.player;
   }

   public ICSAccountType getAccountType() {
      return this.acctType;
   }

   public String getMessage() {
      return this.mesg;
   }

   public void setPlayer(String player) {
      this.player = player;
   }

   public void setAccountType(ICSAccountType acctType) {
      this.acctType = acctType;
   }

   public void setMessage(String mesg) {
      this.mesg = mesg;
   }

   public String getReadable() {
      return FICSTellParser.getInstance().toNative(this);
   }
}
