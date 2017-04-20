/*     */ import ictk.boardgame.chess.net.ics.ICSEventRouter;
/*     */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;
/*     */ import ictk.boardgame.chess.net.ics.event.ICSConnectionListener;
/*     */ import ictk.boardgame.chess.net.ics.fics.FICSProtocolHandler;
/*     */ import ictk.boardgame.chess.net.ics.ui.cli.ANSIConsole;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Date;
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
/*     */ public class SimpleICSClient
/*     */   implements ICSConnectionListener
/*     */ {
/*     */   String handle;
/*     */   String passwd;
/*     */   ICSProtocolHandler ics;
/*     */   ANSIConsole ansiConsole;
/*     */   CommandInput commandLine;
/*     */   
/*     */   public SimpleICSClient(String handle, String passwd)
/*     */   {
/*  58 */     this.handle = handle;
/*  59 */     this.passwd = passwd;
/*     */     
/*  61 */     this.ics = new FICSProtocolHandler();
/*  62 */     this.ics.addConnectionListener(this);
/*     */     
/*  64 */     this.ansiConsole = new ANSIConsole();
/*     */     
/*  66 */     ICSEventRouter router = this.ics.getEventRouter();
/*  67 */     router.setDefaultListener(this.ansiConsole);
/*     */     
/*     */ 
/*  70 */     router.addChannelListener(6, 1, 
/*  71 */       new ChannelListenerExample());
/*     */     
/*     */ 
/*  74 */     this.ics.getEventRouter().addEventListener(6, this.ansiConsole);
/*     */     
/*     */ 
/*  77 */     router.setChannelExclusive(6, 1, true);
/*     */     
/*  79 */     router.setExclusive(6, true);
/*     */     
/*  81 */     this.commandLine = new CommandInput("Simple ICS Client", this.ics);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void connect()
/*     */   {
/*  89 */     if (!this.ics.isConnected()) {
/*     */       try {
/*  91 */         System.out.println("[Client] attempting to connect");
/*  92 */         this.ics.setHandle(this.handle);
/*  93 */         this.ics.setPassword(this.passwd);
/*  94 */         this.ics.setLagCompensation(true);
/*  95 */         this.ics.connect();
/*     */       }
/*     */       catch (UnknownHostException e) {
/*  98 */         e.printStackTrace();
/*     */       }
/*     */       catch (IOException e) {
/* 101 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setVisible(boolean t) {
/* 107 */     this.commandLine.setVisible(t);
/*     */   }
/*     */   
/*     */   public void connectionStatusChanged(ICSConnectionEvent evt) {
/* 111 */     ICSProtocolHandler conn = evt.getConnection();
/*     */     
/* 113 */     if (!conn.isConnected()) {
/* 114 */       System.err.println("Connection Terminated: " + new Date());
/* 115 */       System.exit(0);
/*     */     }
/*     */     else {
/* 118 */       System.err.println("Connection Live but received event: " + evt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 126 */     SimpleICSClient client = null;
/* 127 */     if (args.length < 2) {
/* 128 */       System.err.println("You must supply username and password");
/* 129 */       System.exit(1);
/*     */     }
/* 131 */     client = new SimpleICSClient(args[0], args[1]);
/* 132 */     client.setVisible(true);
/* 133 */     client.connect();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\SimpleICSClient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */