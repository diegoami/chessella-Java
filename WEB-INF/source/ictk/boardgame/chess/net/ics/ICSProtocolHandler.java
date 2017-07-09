package ictk.boardgame.chess.net.ics;

import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSEventRouter;
import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;
import ictk.boardgame.chess.net.ics.event.ICSConnectionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class ICSProtocolHandler implements Runnable {

   protected Thread thread = new Thread(this);
   protected Socket socket;
   protected PrintWriter out;
   protected InputStreamReader in;
   protected ICSEventRouter router;
   protected String handle;
   protected String passwd;
   protected ICSAccountType acctType;
   protected String host;
   protected int port;
   protected boolean isLagCompensated;
   protected boolean isLoggedIn;
   protected ICSConnectionListener[] conSubscribers;


   public void setHandle(String handle) {
      this.handle = handle;
   }

   public String getHandle() {
      return this.handle;
   }

   public void setPassword(String password) {
      this.passwd = password;
   }

   public String getPassword() {
      return this.passwd;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getHost() {
      return this.host;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public int getPort() {
      return this.port;
   }

   public void setLagCompensation(boolean t) {
      if(this.isConnected()) {
         throw new IllegalStateException("Cannot set lag compensation after connection already established.");
      } else {
         this.isLagCompensated = t;
      }
   }

   public boolean isLagCompensated() {
      return this.isLagCompensated;
   }

   public boolean isConnected() {
      return this.socket == null?false:this.socket.isClosed();
   }

   public boolean isLoggedIn() {
      return this.isLoggedIn;
   }

   public void setEventRouter(ICSEventRouter router) {
      this.router = router;
   }

   public ICSEventRouter getEventRouter() {
      return this.router;
   }

   public abstract void connect() throws UnknownHostException, IOException;

   public abstract void disconnect();

   public abstract void sendCommand(String var1);

   public abstract void sendCommand(String var1, boolean var2);

   public void addConnectionListener(ICSConnectionListener listener) {
      ICSConnectionListener[] tmp = (ICSConnectionListener[])null;
      if(this.conSubscribers == null) {
         tmp = new ICSConnectionListener[]{listener};
      } else {
         tmp = new ICSConnectionListener[this.conSubscribers.length + 1];
         System.arraycopy(this.conSubscribers, 0, tmp, 0, this.conSubscribers.length);
         tmp[this.conSubscribers.length] = listener;
      }

      this.conSubscribers = tmp;
   }

   public void removeConnectionListener(ICSConnectionListener listener) {
      ICSConnectionListener[] tmp = (ICSConnectionListener[])null;
      if(this.conSubscribers != null && this.conSubscribers.length > 1) {
         tmp = new ICSConnectionListener[this.conSubscribers.length - 1];
         int count = 0;

         for(int i = 0; i < this.conSubscribers.length; ++i) {
            if(this.conSubscribers[i] != listener) {
               tmp[count++] = this.conSubscribers[i];
            }
         }
      }

      this.conSubscribers = tmp;
   }

   public void dispatchConnectionEvent(ICSConnectionEvent evt) {
      if(this.conSubscribers != null) {
         for(int i = 0; i < this.conSubscribers.length; ++i) {
            this.conSubscribers[i].connectionStatusChanged(evt);
         }
      }

   }
}
