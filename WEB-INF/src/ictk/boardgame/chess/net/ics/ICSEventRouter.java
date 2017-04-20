/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.event.ICSChannelEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSEventListener;
/*     */ import java.util.HashMap;
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
/*     */ public class ICSEventRouter
/*     */ {
/*  36 */   protected static int OFFSET = 1000;
/*     */   
/*     */ 
/*     */ 
/*     */   ICSEventListener defaultListener;
/*     */   
/*     */ 
/*     */ 
/*     */   protected ICSEventListener[][] subscribers;
/*     */   
/*     */ 
/*     */ 
/*     */   protected HashMap chSubscribers;
/*     */   
/*     */ 
/*     */ 
/*     */   protected HashMap chExclusive;
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean[] exclusive;
/*     */   
/*     */ 
/*     */ 
/*     */   public ICSEventRouter()
/*     */   {
/*  62 */     this.subscribers = new ICSEventListener[29][];
/*  63 */     this.chSubscribers = new HashMap();
/*  64 */     this.chExclusive = new HashMap();
/*  65 */     this.exclusive = new boolean[29];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDefaultListener(ICSEventListener eh)
/*     */   {
/*  74 */     this.defaultListener = eh;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ICSEventListener getDefaultListener()
/*     */   {
/*  83 */     return this.defaultListener;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void dispatch(ICSEvent evt)
/*     */   {
/*  91 */     Integer key = null;
/*  92 */     int type = evt.getEventType();
/*  93 */     int i = 0;
/*  94 */     ICSEventListener[] listeners = (ICSEventListener[])null;
/*  95 */     boolean done = false;
/*  96 */     boolean done2 = false;
/*     */     
/*  98 */     switch (type)
/*     */     {
/*     */     case 6: 
/*     */     case 7: 
/*     */     case 8: 
/* 103 */       key = new Integer(type * OFFSET + 
/* 104 */         ((ICSChannelEvent)evt).getChannel());
/*     */       
/* 106 */       listeners = (ICSEventListener[])this.chSubscribers.get(key);
/* 107 */       done = (listeners != null) && 
/* 108 */         (isChannelExclusive(type, 
/* 109 */         ((ICSChannelEvent)evt).getChannel()));
/*     */       
/* 111 */       break;
/*     */     }
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
/* 124 */     if (listeners != null) {
/* 125 */       for (i = 0; i < listeners.length; i++) {
/* 126 */         listeners[i].icsEventDispatched(evt);
/*     */       }
/*     */     }
/* 129 */     if ((!done) && 
/* 130 */       ((listeners = this.subscribers[type]) != null)) {
/* 131 */       for (i = 0; i < listeners.length; i++)
/* 132 */         listeners[i].icsEventDispatched(evt);
/* 133 */       done2 = true;
/*     */     }
/*     */     
/*     */ 
/* 137 */     if ((this.exclusive[type] == 0) && (this.defaultListener != null)) {
/* 138 */       this.defaultListener.icsEventDispatched(evt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEventListener(int icsEventNumber, ICSEventListener eh)
/*     */   {
/* 150 */     this.subscribers[icsEventNumber] = 
/* 151 */       _addListener(this.subscribers[icsEventNumber], eh);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExclusive(int eventType, boolean t)
/*     */   {
/* 162 */     this.exclusive[eventType] = t;
/*     */   }
/*     */   
/*     */   public boolean isExclusive(int eventType)
/*     */   {
/* 167 */     return this.exclusive[eventType];
/*     */   }
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
/*     */   public void addBoardListener(ICSEventListener eh, int boardNumber, int type) {}
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
/*     */   public void addChannelListener(int channelType, int channelNumber, ICSEventListener eh)
/*     */   {
/* 200 */     Integer key = new Integer(channelType * OFFSET + channelNumber);
/*     */     
/*     */ 
/* 203 */     ICSEventListener[] list = (ICSEventListener[])this.chSubscribers.get(key);
/* 204 */     list = _addListener(list, eh);
/*     */     
/* 206 */     this.chSubscribers.put(key, list);
/*     */   }
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
/*     */   public void removeChannelListener(int channelType, int channelNumber, ICSEventListener eh)
/*     */   {
/* 222 */     Integer key = new Integer(channelType * OFFSET + channelNumber);
/*     */     
/*     */ 
/* 225 */     ICSEventListener[] list = (ICSEventListener[])this.chSubscribers.get(key);
/* 226 */     list = _removeListener(list, eh);
/*     */     
/* 228 */     if (list == null) {
/* 229 */       this.chSubscribers.remove(key);
/*     */     } else {
/* 231 */       this.chSubscribers.put(key, list);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChannelExclusive(int channelType, int channelNumber, boolean t)
/*     */   {
/* 243 */     Integer key = new Integer(channelType * OFFSET + channelNumber);
/* 244 */     this.chExclusive.put(key, t ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isChannelExclusive(int channelType, int channelNumber)
/*     */   {
/* 255 */     Integer key = new Integer(channelType * OFFSET + channelNumber);
/* 256 */     Boolean b = null;
/*     */     
/* 258 */     return ((b = (Boolean)this.chExclusive.get(key)) != null) && (b == Boolean.TRUE);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ICSEventListener[] _addListener(ICSEventListener[] list, ICSEventListener evt)
/*     */   {
/* 264 */     ICSEventListener[] tmp = (ICSEventListener[])null;
/*     */     
/* 266 */     if (list == null) {
/* 267 */       tmp = new ICSEventListener[1];
/* 268 */       tmp[0] = evt;
/*     */     }
/*     */     else {
/* 271 */       tmp = new ICSEventListener[list.length + 1];
/* 272 */       System.arraycopy(list, 0, tmp, 0, list.length);
/* 273 */       tmp[list.length] = evt;
/*     */     }
/* 275 */     return tmp;
/*     */   }
/*     */   
/*     */ 
/*     */   protected ICSEventListener[] _removeListener(ICSEventListener[] list, ICSEventListener evt)
/*     */   {
/* 281 */     ICSEventListener[] tmp = (ICSEventListener[])null;
/* 282 */     if ((list != null) && (list.length > 1)) {
/* 283 */       tmp = new ICSEventListener[list.length - 1];
/* 284 */       int count = 0;
/* 285 */       for (int i = 0; i < list.length; i++)
/* 286 */         if (list[i] != evt)
/* 287 */           tmp[(count++)] = list[i];
/*     */     }
/* 289 */     return tmp;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSEventRouter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */