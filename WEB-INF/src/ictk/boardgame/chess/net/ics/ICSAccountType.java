/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class ICSAccountType
/*     */ {
/*     */   public static final int UNREGISTERED = 0;
/*     */   public static final int COMPUTER = 1;
/*     */   public static final int BLIND = 2;
/*     */   public static final int TEAM = 3;
/*     */   public static final int ADMIN = 4;
/*     */   public static final int SERVICE_REP = 5;
/*     */   public static final int HELPER = 6;
/*     */   public static final int CHESS_ADVISOR = 7;
/*     */   public static final int TOURNAMENT_MANAGER = 8;
/*     */   public static final int TOURNAMENT_DIRECTOR = 9;
/*     */   public static final int DISPLAY_MANAGER = 10;
/*     */   public static final int WFM = 11;
/*     */   public static final int FM = 12;
/*     */   public static final int WIM = 13;
/*     */   public static final int IM = 14;
/*     */   public static final int WGM = 15;
/*     */   public static final int GM = 16;
/*     */   public static final int DEMO = 17;
/*     */   public static final int NUM_FLAGS = 18;
/*     */   protected boolean[] flag;
/*     */   
/*     */   public ICSAccountType()
/*     */   {
/*  63 */     this.flag = new boolean[18];
/*     */   }
/*     */   
/*     */   public ICSAccountType(String s) throws IOException {
/*  67 */     this();
/*  68 */     if (s != null) {
/*  69 */       set(s);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void set(int type, boolean t)
/*     */   {
/*  76 */     this.flag[type] = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean is(int type)
/*     */   {
/*  83 */     return this.flag[type];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(String s)
/*     */     throws IOException
/*     */   {
/*  93 */     StringTokenizer st = new StringTokenizer(s, "()", false);
/*  94 */     String tok = null;
/*     */     
/*  96 */     while (st.hasMoreTokens()) {
/*  97 */       tok = st.nextToken();
/*  98 */       if ("U".equals(tok)) { this.flag[0] = true;
/*  99 */       } else if ("*".equals(tok)) { this.flag[4] = true;
/* 100 */       } else if ("C".equals(tok)) { this.flag[1] = true;
/* 101 */       } else if ("SR".equals(tok)) { this.flag[5] = true;
/* 102 */       } else if ("H".equals(tok)) { this.flag[6] = true;
/* 103 */       } else if ("TD".equals(tok)) { this.flag[9] = true;
/* 104 */       } else if ("TM".equals(tok)) { this.flag[8] = true;
/* 105 */       } else if ("FM".equals(tok)) { this.flag[12] = true;
/* 106 */       } else if ("IM".equals(tok)) { this.flag[14] = true;
/* 107 */       } else if ("B".equals(tok)) { this.flag[2] = true;
/* 108 */       } else if ("T".equals(tok)) { this.flag[3] = true;
/* 109 */       } else if ("D".equals(tok)) { this.flag[17] = true;
/* 110 */       } else if ("WIM".equals(tok)) { this.flag[13] = true;
/* 111 */       } else if ("WFM".equals(tok)) { this.flag[15] = true;
/* 112 */       } else if ("WGM".equals(tok)) { this.flag[15] = true;
/* 113 */       } else if ("GM".equals(tok)) { this.flag[16] = true;
/* 114 */       } else if ("CA".equals(tok)) { this.flag[7] = true;
/* 115 */       } else if ("DM".equals(tok)) { this.flag[10] = true;
/*     */       } else {
/* 117 */         throw new IOException("Unrecognized account type: " + tok);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static String typeToString(int type)
/*     */   {
/* 126 */     String s = null;
/*     */     
/* 128 */     switch (type) {
/* 129 */     case 0:  s = "U"; break;
/* 130 */     case 1:  s = "C"; break;
/* 131 */     case 2:  s = "B"; break;
/* 132 */     case 3:  s = "T"; break;
/* 133 */     case 4:  s = "*"; break;
/* 134 */     case 8:  s = "TM"; break;
/* 135 */     case 9:  s = "TD"; break;
/* 136 */     case 5:  s = "SR"; break;
/* 137 */     case 6:  s = "H"; break;
/* 138 */     case 7:  s = "CA"; break;
/* 139 */     case 10:  s = "DM"; break;
/* 140 */     case 11:  s = "WFM"; break;
/* 141 */     case 12:  s = "FM"; break;
/* 142 */     case 13:  s = "WIM"; break;
/* 143 */     case 14:  s = "IM"; break;
/* 144 */     case 15:  s = "WGM"; break;
/* 145 */     case 16:  s = "GM"; break;
/* 146 */     case 17:  s = "D"; break;
/*     */     default: 
/* 148 */       System.err.println("Received illegal account type: " + type);
/*     */     }
/* 150 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 158 */     StringBuffer sb = null;
/*     */     
/* 160 */     for (int i = 0; i < this.flag.length; i++) {
/* 161 */       if (this.flag[i] != 0) {
/* 162 */         if (sb == null)
/* 163 */           sb = new StringBuffer();
/* 164 */         sb.append("(").append(typeToString(i)).append(")");
/*     */       }
/*     */     }
/*     */     
/* 168 */     if (sb == null) return "";
/* 169 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSAccountType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */