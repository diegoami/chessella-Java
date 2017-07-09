package ictk.util;

import java.io.PrintStream;
import java.util.regex.Matcher;

public class Log {

   public static final boolean debug = true;
   public static PrintStream err = System.err;
   public static PrintStream out = System.out;
   public static final int PROG_CRITICAL = 1;
   public static final int PROG_ERROR = 2;
   public static final int PROG_WARNING = 3;
   public static final int USER_CRITICAL = 4;
   public static final int USER_ERROR = 5;
   public static final int USER_WARNING = 6;
   public static long History = 1L;
   public static long Board = 2L * History;
   public static long Move = 2L * Board;
   public static long MoveNotation = 2L * Move;
   public static long BoardNotation = 2L * MoveNotation;
   public static long GameReader = 2L * BoardNotation;
   public static long GameWriter = 2L * GameReader;
   public static long GameInfo = 2L * GameWriter;
   public static long ICSEvent = 2L * GameInfo;
   public static long ICSEventParser = 2L * ICSEvent;
   public static boolean isFullyQualifiedClass = false;
   protected static long mask_level = 0L;


   static {
      getSystemProperties();
   }

   protected static void getSystemProperties() {
      if(System.getProperty("Debug.History") != null) {
         addMask(History);
      }

      if(System.getProperty("Debug.Board") != null) {
         addMask(Board);
      }

      if(System.getProperty("Debug.Move") != null) {
         addMask(Move);
      }

      if(System.getProperty("Debug.MoveNotation") != null) {
         addMask(MoveNotation);
      }

      if(System.getProperty("Debug.BoardNotation") != null) {
         addMask(BoardNotation);
      }

      if(System.getProperty("Debug.GameWriter") != null) {
         addMask(GameWriter);
      }

      if(System.getProperty("Debug.GameReader") != null) {
         addMask(GameReader);
      }

   }

   public static long getMask() {
      return mask_level;
   }

   public static void setMask(long mask) {
      mask_level = mask;
   }

   public static void addMask(long mask) {
      mask_level |= mask;
   }

   public static void removeMask(long mask) {
      mask_level &= ~mask;
   }

   public static boolean isDebug(long mask) {
      return (mask_level & mask) == mask;
   }

   public static void debug(long mask, Object o) {
      if((mask_level & mask) == mask) {
         err.println("[" + getCaller(2) + "] " + o);
      }

   }

   public static void debug2(long mask, Object o) {
      if((mask_level & mask) == mask) {
         err.println(o);
      }

   }

   public static void errorIf(boolean t, int severity, String msg) {
      if(!t) {
         errorReport(severity, msg);
      }

   }

   public static void error(int severity, String msg) {
      errorReport(severity, msg);
   }

   protected static void errorReport(int severity, String msg) {
      if(severity < 4) {
         err.print("[" + getCaller(3) + "] ");
      }

      switch(severity) {
      case 1:
      case 4:
         err.print("CRITICAL: ");
         break;
      case 2:
      case 5:
         err.print("ERROR: ");
         break;
      case 3:
      case 6:
         err.print("WARNING: ");
         break;
      default:
         err.print("UNKNOWN: ");
      }

      err.println(msg);
   }

   public static void debug(long mask, String description, Matcher m) {
      if((mask_level & mask) == mask) {
         err.println("[" + getCaller(2) + "] " + description);

         for(int i = 0; i <= m.groupCount(); ++i) {
            out.println(i + ": " + m.group(i));
         }
      }

   }

   public static boolean debug(long mask) {
      return (mask_level & mask) == mask;
   }

   protected static String getCaller(int lvl) {
      StackTraceElement[] stack = (StackTraceElement[])null;
      String caller = null;
      String tmp = null;
      stack = (new Throwable()).getStackTrace();
      if(stack.length >= lvl) {
         tmp = stack[lvl].getClassName();
         if(isFullyQualifiedClass) {
            caller = tmp;
         } else {
            caller = tmp.substring(tmp.lastIndexOf(".") + 1);
         }

         caller = caller + "." + stack[lvl].getMethodName() + "():";
         if(stack[lvl].getLineNumber() >= 0) {
            caller = caller + stack[lvl].getLineNumber();
         } else {
            caller = caller + "?";
         }
      } else {
         caller = "???";
      }

      return caller;
   }
}
