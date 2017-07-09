package ictk.boardgame.chess.net.ics.fics;

import free.freechess.timeseal.TimesealingSocket;
import ictk.boardgame.chess.net.ics.ICSAccountType;
import ictk.boardgame.chess.net.ics.ICSEventRouter;
import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
import ictk.boardgame.chess.net.ics.event.ICSConnectionEvent;
import ictk.boardgame.chess.net.ics.event.ICSEvent;
import ictk.boardgame.chess.net.ics.event.ICSEventParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSBoardUpdateStyle12Parser;
import ictk.boardgame.chess.net.ics.fics.event.FICSChallengeParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSChannelParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameCreatedParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameNotificationParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSGameResultParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSKibitzParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSMoveListParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerConnectionParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSPlayerNotificationParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekAdReadableParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekClearParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSSeekRemoveParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSShoutParser;
import ictk.boardgame.chess.net.ics.fics.event.FICSTellParser;
import ictk.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FICSProtocolHandler extends ICSProtocolHandler {

   public static final int SOCKET_TIMEOUT = 180000;
   protected int SOCKET_DELAY;
   protected String LOGIN_PROMPT;
   protected String PASSWD_PROMPT;
   protected String CMD_PROMPT;
   protected String GUEST_PROMPT;
   protected String START_SESSION;
   protected String INVALID_PASSWD;
   protected String INTERFACE_NAME;
   protected static final String REGEX_handle = "([\\w]+)";
   protected static final String REGEX_acct_type = "(\\(\\S*\\))?";
   protected final ICSEventParser[] eventFactories;
   boolean isBlockMode;
   int BUFFER_SIZE;
   CharBuffer buffer;


   public FICSProtocolHandler() {
      this.SOCKET_DELAY = 1000;
      this.LOGIN_PROMPT = "login:";
      this.PASSWD_PROMPT = "password:";
      this.CMD_PROMPT = "\nfics% ";
      this.GUEST_PROMPT = "Press return to enter the server as \"";
      this.START_SESSION = "**** Starting FICS session as ";
      this.INVALID_PASSWD = "**** Invalid password! ****";
      this.INTERFACE_NAME = "-=[ ictk ]=- v0.2 http://ictk.sourceforge.net";
      this.isBlockMode = false;
      this.BUFFER_SIZE = 134144;
      this.buffer = CharBuffer.allocate(this.BUFFER_SIZE);
      this.host = "64.71.131.140";
      this.port = 5000;
      byte i = 0;
      this.eventFactories = new ICSEventParser[16];
      int var2 = i + 1;
      this.eventFactories[i] = FICSBoardUpdateStyle12Parser.getInstance();
      this.eventFactories[var2++] = FICSMoveListParser.getInstance();
      this.eventFactories[var2++] = FICSTellParser.getInstance();
      this.eventFactories[var2++] = FICSKibitzParser.getInstance();
      this.eventFactories[var2++] = FICSChannelParser.getInstance();
      this.eventFactories[var2++] = FICSShoutParser.getInstance();
      this.eventFactories[var2++] = FICSGameResultParser.getInstance();
      this.eventFactories[var2++] = FICSGameCreatedParser.getInstance();
      this.eventFactories[var2++] = FICSPlayerConnectionParser.getInstance();
      this.eventFactories[var2++] = FICSPlayerNotificationParser.getInstance();
      this.eventFactories[var2++] = FICSGameNotificationParser.getInstance();
      this.eventFactories[var2++] = FICSSeekClearParser.getInstance();
      this.eventFactories[var2++] = FICSSeekAdParser.getInstance();
      this.eventFactories[var2++] = FICSSeekRemoveParser.getInstance();
      this.eventFactories[var2++] = FICSSeekAdReadableParser.getInstance();
      this.eventFactories[var2++] = FICSChallengeParser.getInstance();
      this.router = new ICSEventRouter();
   }

   public FICSProtocolHandler(String host, int port) {
      this();
      this.host = host;
      this.port = port;
   }

   public void connect() throws UnknownHostException, IOException {
      if(this.handle != null && this.passwd != null) {
         if(this.isLagCompensated) {
            this.socket = new TimesealingSocket(this.host, this.port);
         } else {
            this.socket = new Socket(this.host, this.port);
         }

         try {
            this.socket.setKeepAlive(true);
         } catch (SocketException var2) {
            Log.error(6, var2.getMessage());
         }

         this.in = new InputStreamReader(this.socket.getInputStream());
         this.out = new PrintWriter(this.socket.getOutputStream());
         this.thread.start();
      } else {
         throw new IllegalStateException("Both handle and password must be set before login");
      }
   }

   public void run() {
      try {
         this.isLoggedIn = this.doLogin();
         if(this.isLoggedIn) {
            this.setLoginVars();
            this.processServerOutput();
         }

         if(this.socket != null) {
            this.socket.isClosed();
         }
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      this.dispatchConnectionEvent(new ICSConnectionEvent(this));
   }

   protected boolean doLogin() throws IOException {
      boolean seenLogin = false;
      boolean seenPasswd = false;
      String tmp = null;
      boolean b = false;
      boolean c = true;
      boolean mark = false;
      Matcher match = null;
      Pattern REGEX_sessionStart = Pattern.compile("^\\*\\*\\*\\* Starting FICS session as ([\\w]+)(\\(\\S*\\))? \\*\\*\\*\\*", 8);

      int b1;
      while((b1 = this.in.read()) != -1) {
         if(b1 == 10 || b1 == 13 || b1 >= 32 && b1 <= 126) {
            char c1 = (char)b1;
            if(c1 != 13) {
               this.buffer.put(c1);
            }

            if(c1 == 10 && !seenPasswd) {
               this.buffer.limit(this.buffer.position());
               this.buffer.rewind();
               System.out.print(this.buffer.toString());
               System.out.flush();
               this.buffer.clear();
            } else {
               int mark1;
               if(c1 == 58) {
                  mark1 = this.buffer.position();
                  this.buffer.limit(mark1);
                  this.buffer.rewind();
                  tmp = this.buffer.toString();
                  if(!seenLogin && tmp.lastIndexOf(this.LOGIN_PROMPT) > -1) {
                     System.out.print(tmp);
                     System.out.print(" ");
                     System.out.flush();
                     this.buffer.rewind();
                     this.buffer.clear();
                     this.sendCommand(this.handle);
                     seenLogin = true;
                  } else if(seenLogin && !seenPasswd && tmp.lastIndexOf(this.PASSWD_PROMPT) > -1) {
                     System.out.print(tmp);
                     System.out.print(" ");
                     System.out.flush();
                     this.buffer.rewind();
                     this.buffer.clear();
                     this.sendCommand(this.passwd, false);
                     seenPasswd = true;
                     System.out.println();
                  } else if(seenLogin && !seenPasswd && tmp.lastIndexOf(this.GUEST_PROMPT) > -1) {
                     System.out.print(tmp);
                     System.out.flush();
                     this.buffer.rewind();
                     this.buffer.clear();
                     this.sendCommand("");
                     seenPasswd = true;
                  } else {
                     this.buffer.limit(this.buffer.capacity());
                     this.buffer.position(mark1);
                  }
               } else if(c1 == 10 && seenPasswd) {
                  mark1 = this.buffer.position();
                  this.buffer.limit(mark1);
                  this.buffer.rewind();
                  tmp = this.buffer.toString();
                  if(tmp.lastIndexOf(this.INVALID_PASSWD) > -1) {
                     System.out.print(tmp);
                     System.out.flush();
                     this.buffer.rewind();
                     this.buffer.clear();
                     return false;
                  }

                  if(tmp.lastIndexOf(this.START_SESSION) > -1) {
                     match = REGEX_sessionStart.matcher(tmp);
                     if(match.find()) {
                        this.handle = match.group(1);

                        try {
                           if(match.group(2) == null) {
                              this.acctType = new ICSAccountType();
                           } else {
                              this.acctType = new ICSAccountType(match.group(2));
                           }
                        } catch (IOException var10) {
                           Log.error(2, "On Login: " + var10.getMessage());
                        }
                     } else {
                        Log.error(2, "On Login: never matched session start: " + tmp);
                     }

                     System.out.print(tmp);
                     System.out.flush();
                     this.buffer.rewind();
                     this.buffer.clear();
                     return true;
                  }

                  this.buffer.limit(this.buffer.capacity());
                  this.buffer.position(mark1);
               }
            }
         }
      }

      return true;
   }

   protected void setLoginVars() {
      this.sendCommand("set prompt", false);
      this.sendCommand("set style 12", false);
      this.sendCommand("iset ms 1", false);
      this.sendCommand("set interface " + this.INTERFACE_NAME, false);
      this.sendCommand("set bell 0", false);
   }

   protected void processServerOutput() {
      if(this.isBlockMode) {
         this.chunkByBlockMode();
      } else {
         this.chunkByPrompt();
      }

   }

   protected void chunkByPrompt() {
      boolean b = true;
      boolean c = true;
      byte ptr = 0;
      char[] prompt = this.CMD_PROMPT.toCharArray();

      try {
         int var7;
         while((var7 = this.in.read()) != -1) {
            if(var7 == 10 || var7 == 13 || var7 >= 32 && var7 <= 126) {
               char var8 = (char)var7;
               if(var8 != 13) {
                  this.buffer.put(var8);
                  if(var8 == prompt[ptr]) {
                     ++ptr;
                     if(prompt.length == ptr) {
                        this.buffer.limit(this.buffer.position() - prompt.length);
                        this.buffer.rewind();
                        this.parse(this.buffer);
                        this.buffer.clear();
                        ptr = 0;
                     }
                  } else if(ptr != 1 || var8 != prompt[0]) {
                     ptr = 0;
                  }
               }
            }
         }

         if(this.buffer.position() > 0) {
            this.buffer.limit(this.buffer.position());
            this.buffer.rewind();
            this.parse(this.buffer);
         }
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   protected void chunkByBlockMode() {
      short block_state = 0;
      int id = 0;
      int cmd = 0;
      boolean c = true;
      boolean b = true;
      short ptr = 0;
      CharBuffer idBuff = CharBuffer.allocate(6);
      CharBuffer cmdBuff = CharBuffer.allocate(3);
      char[] prompt = this.CMD_PROMPT.toCharArray();

      try {
         int var19;
         while((var19 = this.in.read()) != -1) {
            if(var19 != 21 && var19 != 22) {
               if(var19 == 23) {
                  idBuff.limit(idBuff.position());
                  idBuff.rewind();
                  cmdBuff.limit(cmdBuff.position());
                  cmdBuff.rewind();
                  this.buffer.limit(this.buffer.position());
                  this.buffer.rewind();

                  try {
                     id = Integer.parseInt(idBuff.toString());
                     cmd = Integer.parseInt(cmdBuff.toString());
                  } catch (NumberFormatException var15) {
                     idBuff.rewind();
                     cmdBuff.rewind();
                     Log.error(2, "Couldn\'t parse an int for the block{" + block_state + "}" + " id(" + idBuff.toString() + ") or cmd(" + cmdBuff.toString() + "):" + this.buffer.toString());
                  } finally {
                     this.parseResponse(id, cmd, this.buffer);
                     idBuff.clear();
                     cmdBuff.clear();
                     this.buffer.clear();
                     block_state = 0;
                     ptr = 0;
                  }
               } else if(var19 != 10 && var19 != 13 && (var19 < 32 || var19 > 126)) {
                  String e = "[" + var19 + "]";

                  for(int z = 0; z < e.length(); ++z) {
                     this.buffer.put(e.charAt(z));
                  }
               } else {
                  char var18 = (char)var19;
                  switch(block_state) {
                  case 0:
                     if(var18 != 13) {
                        this.buffer.put(var18);
                        if(var18 == prompt[ptr]) {
                           ++ptr;
                           if(prompt.length == ptr) {
                              this.buffer.limit(this.buffer.position() - prompt.length);
                              this.buffer.rewind();
                              this.parse(this.buffer);
                              this.buffer.clear();
                              ptr = 0;
                           }
                        } else {
                           ptr = 0;
                        }
                     }
                     break;
                  case 1:
                     idBuff.put(var18);
                     break;
                  case 2:
                     cmdBuff.put(var18);
                     break;
                  case 3:
                     if(var18 != 13) {
                        this.buffer.put(var18);
                     }
                     break;
                  default:
                     Log.error(2, "FICSChunker has a block_state of " + block_state);
                     block_state = 0;

                     assert false : "FICSChunker error in block_state";
                  }
               }
            } else {
               ++block_state;
            }
         }

         if(this.buffer.position() > 0) {
            this.buffer.rewind();
            this.parse(this.buffer);
         }
      } catch (IOException var17) {
         var17.printStackTrace();
      }

   }

   public void disconnect() {
      this.sendCommand("exit");
   }

   public void sendCommand(String cmd, boolean echo) {
      if(echo) {
         System.out.println(cmd);
      }

      if(this.isBlockMode) {
         this.out.println("1 " + cmd);
      } else {
         this.out.println(cmd);
      }

      this.out.flush();
   }

   public void sendCommand(String cmd) {
      this.sendCommand(cmd, true);
   }

   protected void parse(CharSequence str) {
      ICSEvent icsEvent = null;
      Matcher matcher = null;
      boolean found = false;

      for(int more = 0; more < this.eventFactories.length && !found; ++more) {
         if((matcher = this.eventFactories[more].match(str)) != null) {
            icsEvent = this.eventFactories[more].createICSEvent(matcher);

            assert icsEvent != null : "parser matched, but event null?";

            icsEvent.setServer(this);
            this.router.dispatch(icsEvent);
            found = true;
         }
      }

      CharSequence var7 = str;

      while(matcher != null && icsEvent != null && (icsEvent.getEventType() == 16 || icsEvent.getEventType() == 12)) {
         matcher = FICSSeekAdParser.getInstance().match(var7 = var7.subSequence(matcher.end(), var7.length()));
         if(matcher != null) {
            icsEvent = FICSSeekAdParser.getInstance().createICSEvent(matcher);

            assert icsEvent != null : "parser matched, but event null?";

            icsEvent.setServer(this);
            this.router.dispatch(icsEvent);
         }
      }

      if(!found) {
         System.out.println(str);
      }

      if(matcher != null) {
         int end = matcher.end();
         if(end < str.length() && str.charAt(end) == 10) {
            ++end;
         }

         if(end < str.length()) {
            System.out.println(str.subSequence(end, str.length()));
         }
      }

   }

   private void parseResponse(int id, int cmd, CharSequence str) {}
}
