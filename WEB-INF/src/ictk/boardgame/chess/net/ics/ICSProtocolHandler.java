/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSConnectionListener;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ICSProtocolHandler
/*     */   implements Runnable
/*     */ {
/*     */   protected Thread thread;
/*     */   protected Socket socket;
/*     */   protected PrintWriter out;
/*     */   protected InputStreamReader in;
/*     */   protected ICSEventRouter router;
/*     */   protected String handle;
/*     */   protected String passwd;
/*     */   protected ICSAccountType acctType;
/*     */   protected String host;
/*     */   protected int port;
/*     */   protected boolean isLagCompensated;
/*     */   protected boolean isLoggedIn;
/*     */   protected ICSConnectionListener[] conSubscribers;
/*     */   
/*     */   public ICSProtocolHandler()
/*     */   {
/*  74 */     this.thread = new Thread(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHandle(String handle)
/*     */   {
/*  83 */     this.handle = handle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getHandle()
/*     */   {
/*  92 */     return this.handle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 100 */     this.passwd = password;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 107 */     return this.passwd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHost(String host)
/*     */   {
/* 115 */     this.host = host;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getHost()
/*     */   {
/* 122 */     return this.host;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPort(int port)
/*     */   {
/* 129 */     this.port = port;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getPort()
/*     */   {
/* 136 */     return this.port;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLagCompensation(boolean t)
/*     */   {
/* 148 */     if (isConnected()) {
/* 149 */       throw new IllegalStateException(
/* 150 */         "Cannot set lag compensation after connection already established.");
/*     */     }
/*     */     
/* 153 */     this.isLagCompensated = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLagCompensated()
/*     */   {
/* 163 */     return this.isLagCompensated;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 170 */     if (this.socket == null)
/* 171 */       return false;
/* 172 */     return this.socket.isClosed();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLoggedIn()
/*     */   {
/* 180 */     return this.isLoggedIn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEventRouter(ICSEventRouter router)
/*     */   {
/* 188 */     this.router = router;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ICSEventRouter getEventRouter()
/*     */   {
/* 195 */     return this.router;
/*     */   }
/*     */   
/*     */ 
/*     */   public abstract void connect()
/*     */     throws UnknownHostException, IOException;
/*     */   
/*     */   public abstract void disconnect();
/*     */   
/*     */   public abstract void sendCommand(String paramString);
/*     */   
/*     */   public abstract void sendCommand(String paramString, boolean paramBoolean);
/*     */   
/*     */   public void addConnectionListener(ICSConnectionListener listener)
/*     */   {
/* 210 */     ICSConnectionListener[] tmp = (ICSConnectionListener[])null;
/*     */     
/* 212 */     if (this.conSubscribers == null) {
/* 213 */       tmp = new ICSConnectionListener[1];
/* 214 */       tmp[0] = listener;
/*     */     }
/*     */     else {
/* 217 */       tmp = new ICSConnectionListener[this.conSubscribers.length + 1];
/* 218 */       System.arraycopy(this.conSubscribers, 0, tmp, 0, this.conSubscribers.length);
/* 219 */       tmp[this.conSubscribers.length] = listener;
/*     */     }
/* 221 */     this.conSubscribers = tmp;
/*     */   }
/*     */   
/*     */   public void removeConnectionListener(ICSConnectionListener listener)
/*     */   {
/* 226 */     ICSConnectionListener[] tmp = (ICSConnectionListener[])null;
/*     */     
/* 228 */     if ((this.conSubscribers != null) && (this.conSubscribers.length > 1)) {
/* 229 */       tmp = new ICSConnectionListener[this.conSubscribers.length - 1];
/* 230 */       int count = 0;
/* 231 */       for (int i = 0; i < this.conSubscribers.length; i++)
/* 232 */         if (this.conSubscribers[i] != listener)
/* 233 */           tmp[(count++)] = this.conSubscribers[i];
/*     */     }
/* 235 */     this.conSubscribers = tmp;
/*     */   }
/*     */   
/*     */   public void dispatchConnectionEvent(ICSConnectionEvent evt)
/*     */   {
/* 240 */     if (this.conSubscribers != null) {
/* 241 */       for (int i = 0; i < this.conSubscribers.length; i++) {
/* 242 */         this.conSubscribers[i].connectionStatusChanged(evt);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSProtocolHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */