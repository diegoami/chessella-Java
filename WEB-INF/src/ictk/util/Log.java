/*     */ package ictk.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.regex.Matcher;
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
/*     */ public class Log
/*     */ {
/*     */   public static final boolean debug = true;
/*  42 */   public static PrintStream err = System.err;
/*     */   
/*  44 */   public static PrintStream out = System.out;
/*     */   
/*     */   public static final int PROG_CRITICAL = 1;
/*     */   
/*     */   public static final int PROG_ERROR = 2;
/*     */   public static final int PROG_WARNING = 3;
/*     */   public static final int USER_CRITICAL = 4;
/*     */   public static final int USER_ERROR = 5;
/*     */   public static final int USER_WARNING = 6;
/*  53 */   public static long History = 1L;
/*  54 */   public static long Board = 2L * History;
/*  55 */   public static long Move = 2L * Board;
/*  56 */   public static long MoveNotation = 2L * Move;
/*  57 */   public static long BoardNotation = 2L * MoveNotation;
/*  58 */   public static long GameReader = 2L * BoardNotation;
/*  59 */   public static long GameWriter = 2L * GameReader;
/*  60 */   public static long GameInfo = 2L * GameWriter;
/*  61 */   public static long ICSEvent = 2L * GameInfo;
/*  62 */   public static long ICSEventParser = 2L * ICSEvent;
/*     */   
/*     */ 
/*  65 */   public static boolean isFullyQualifiedClass = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  71 */   protected static long mask_level = 0L;
/*     */   
/*     */   static {
/*  74 */     getSystemProperties();
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
/*     */   protected static void getSystemProperties()
/*     */   {
/*  89 */     if (System.getProperty("Debug.History") != null)
/*  90 */       addMask(History);
/*  91 */     if (System.getProperty("Debug.Board") != null)
/*  92 */       addMask(Board);
/*  93 */     if (System.getProperty("Debug.Move") != null)
/*  94 */       addMask(Move);
/*  95 */     if (System.getProperty("Debug.MoveNotation") != null)
/*  96 */       addMask(MoveNotation);
/*  97 */     if (System.getProperty("Debug.BoardNotation") != null)
/*  98 */       addMask(BoardNotation);
/*  99 */     if (System.getProperty("Debug.GameWriter") != null)
/* 100 */       addMask(GameWriter);
/* 101 */     if (System.getProperty("Debug.GameReader") != null) {
/* 102 */       addMask(GameReader);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getMask()
/*     */   {
/* 111 */     return mask_level;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setMask(long mask)
/*     */   {
/* 117 */     mask_level = mask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addMask(long mask)
/*     */   {
/* 125 */     mask_level |= mask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void removeMask(long mask)
/*     */   {
/* 133 */     mask_level &= (mask ^ 0xFFFFFFFFFFFFFFFF);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean isDebug(long mask)
/*     */   {
/* 140 */     return (mask_level & mask) == mask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void debug(long mask, Object o)
/*     */   {
/* 150 */     if ((mask_level & mask) == mask)
/*     */     {
/* 152 */       err.println("[" + getCaller(2) + "] " + o);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void debug2(long mask, Object o)
/*     */   {
/* 162 */     if ((mask_level & mask) == mask) {
/* 163 */       err.println(o);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void errorIf(boolean t, int severity, String msg)
/*     */   {
/* 171 */     if (!t) {
/* 172 */       errorReport(severity, msg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void error(int severity, String msg)
/*     */   {
/* 179 */     errorReport(severity, msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void errorReport(int severity, String msg)
/*     */   {
/* 187 */     if (severity < 4)
/* 188 */       err.print("[" + getCaller(3) + "] ");
/* 189 */     switch (severity) {
/*     */     case 1: 
/*     */     case 4: 
/* 192 */       err.print("CRITICAL: ");
/* 193 */       break;
/*     */     case 2: 
/*     */     case 5: 
/* 196 */       err.print("ERROR: ");
/* 197 */       break;
/*     */     case 3: 
/*     */     case 6: 
/* 200 */       err.print("WARNING: ");
/* 201 */       break;
/*     */     default: 
/* 203 */       err.print("UNKNOWN: ");
/*     */     }
/* 205 */     err.println(msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void debug(long mask, String description, Matcher m)
/*     */   {
/* 213 */     if ((mask_level & mask) == mask) {
/* 214 */       err.println("[" + getCaller(2) + "] " + description);
/* 215 */       for (int i = 0; i <= m.groupCount(); i++) {
/* 216 */         out.println(i + ": " + m.group(i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean debug(long mask)
/*     */   {
/* 225 */     return (mask_level & mask) == mask;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static String getCaller(int lvl)
/*     */   {
/* 233 */     StackTraceElement[] stack = (StackTraceElement[])null;
/* 234 */     String caller = null;
/* 235 */     String tmp = null;
/*     */     
/* 237 */     stack = new Throwable().getStackTrace();
/*     */     
/* 239 */     if (stack.length >= lvl)
/*     */     {
/* 241 */       tmp = stack[lvl].getClassName();
/* 242 */       if (isFullyQualifiedClass) {
/* 243 */         caller = tmp;
/*     */       } else {
/* 245 */         caller = tmp.substring(tmp.lastIndexOf(".") + 1);
/*     */       }
/* 247 */       caller = caller + "." + stack[lvl].getMethodName() + "():";
/*     */       
/* 249 */       if (stack[lvl].getLineNumber() >= 0) {
/* 250 */         caller = caller + stack[lvl].getLineNumber();
/*     */       } else {
/* 252 */         caller = caller + "?";
/*     */       }
/*     */     } else {
/* 255 */       caller = "???"; }
/* 256 */     return caller;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\util\Log.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */