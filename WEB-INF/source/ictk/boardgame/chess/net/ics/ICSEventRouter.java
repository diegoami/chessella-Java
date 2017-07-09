package ictk.boardgame.chess.net.ics;

import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventListener;
import java.util.HashMap;

public class ICSEventRouter {

   protected static int OFFSET = 1000;
   ICSEventListener defaultListener;
   protected ICSEventListener[][] subscribers = new ICSEventListener[29][];
   protected HashMap chSubscribers = new HashMap();
   protected HashMap chExclusive = new HashMap();
   protected boolean[] exclusive = new boolean[29];


   public void setDefaultListener(ICSEventListener eh) {
      this.defaultListener = eh;
   }

   public ICSEventListener getDefaultListener() {
      return this.defaultListener;
   }

   public void dispatch(ICSEvent evt) {
      Integer key = null;
      int type = evt.getEventType();
      boolean i = false;
      ICSEventListener[] listeners = (ICSEventListener[])null;
      boolean done = false;
      boolean done2 = false;
      switch(type) {
      case 6:
      case 7:
      case 8:
         key = new Integer(type * OFFSET + ((ICSChannelEvent)evt).getChannel());
         listeners = (ICSEventListener[])this.chSubscribers.get(key);
         done = listeners != null && this.isChannelExclusive(type, ((ICSChannelEvent)evt).getChannel());
      case 1:
      case 17:
      case 18:
      case 19:
      default:
         int var8;
         if(listeners != null) {
            for(var8 = 0; var8 < listeners.length; ++var8) {
               listeners[var8].icsEventDispatched(evt);
            }
         }

         if(!done && (listeners = this.subscribers[type]) != null) {
            for(var8 = 0; var8 < listeners.length; ++var8) {
               listeners[var8].icsEventDispatched(evt);
            }

            done2 = true;
         }

         if(!this.exclusive[type] && this.defaultListener != null) {
            this.defaultListener.icsEventDispatched(evt);
         }

      }
   }

   public void addEventListener(int icsEventNumber, ICSEventListener eh) {
      this.subscribers[icsEventNumber] = this._addListener(this.subscribers[icsEventNumber], eh);
   }

   public void setExclusive(int eventType, boolean t) {
      this.exclusive[eventType] = t;
   }

   public boolean isExclusive(int eventType) {
      return this.exclusive[eventType];
   }

   public void addBoardListener(ICSEventListener eh, int boardNumber, int type) {}

   public void addChannelListener(int channelType, int channelNumber, ICSEventListener eh) {
      Integer key = new Integer(channelType * OFFSET + channelNumber);
      ICSEventListener[] list = (ICSEventListener[])this.chSubscribers.get(key);
      list = this._addListener(list, eh);
      this.chSubscribers.put(key, list);
   }

   public void removeChannelListener(int channelType, int channelNumber, ICSEventListener eh) {
      Integer key = new Integer(channelType * OFFSET + channelNumber);
      ICSEventListener[] list = (ICSEventListener[])this.chSubscribers.get(key);
      list = this._removeListener(list, eh);
      if(list == null) {
         this.chSubscribers.remove(key);
      } else {
         this.chSubscribers.put(key, list);
      }

   }

   public void setChannelExclusive(int channelType, int channelNumber, boolean t) {
      Integer key = new Integer(channelType * OFFSET + channelNumber);
      this.chExclusive.put(key, t?Boolean.TRUE:Boolean.FALSE);
   }

   public boolean isChannelExclusive(int channelType, int channelNumber) {
      Integer key = new Integer(channelType * OFFSET + channelNumber);
      Boolean b = null;
      return (b = (Boolean)this.chExclusive.get(key)) != null && b == Boolean.TRUE;
   }

   protected ICSEventListener[] _addListener(ICSEventListener[] list, ICSEventListener evt) {
      ICSEventListener[] tmp = (ICSEventListener[])null;
      if(list == null) {
         tmp = new ICSEventListener[]{evt};
      } else {
         tmp = new ICSEventListener[list.length + 1];
         System.arraycopy(list, 0, tmp, 0, list.length);
         tmp[list.length] = evt;
      }

      return tmp;
   }

   protected ICSEventListener[] _removeListener(ICSEventListener[] list, ICSEventListener evt) {
      ICSEventListener[] tmp = (ICSEventListener[])null;
      if(list != null && list.length > 1) {
         tmp = new ICSEventListener[list.length - 1];
         int count = 0;

         for(int i = 0; i < list.length; ++i) {
            if(list[i] != evt) {
               tmp[count++] = list[i];
            }
         }
      }

      return tmp;
   }
}
