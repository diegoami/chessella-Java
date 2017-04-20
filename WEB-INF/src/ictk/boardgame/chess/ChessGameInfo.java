/*     */ package ictk.boardgame.chess;
/*     */ 
/*     */ import ictk.boardgame.GameInfo;
/*     */ import ictk.boardgame.Player;
/*     */ import ictk.boardgame.Result;
/*     */ import ictk.util.Log;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
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
/*     */ public class ChessGameInfo
/*     */   extends GameInfo
/*     */ {
/*  41 */   public static final long DEBUG = Log.GameInfo;
/*     */   
/*     */   protected String eco;
/*     */   
/*     */   protected int whiteRating;
/*     */   
/*     */   protected int blackRating;
/*     */   
/*     */   protected int timeControlInitial;
/*     */   
/*     */   protected int timeControlIncrement;
/*     */   
/*     */   public ChessGameInfo()
/*     */   {
/*  55 */     this.players = new Player[2];
/*  56 */     this.result = new ChessResult(0);
/*     */   }
/*     */   
/*     */   public ChessGameInfo(ChessPlayer _white, ChessPlayer _black) {
/*  60 */     this.players = new Player[2];
/*  61 */     this.players[0] = _white;
/*  62 */     this.players[1] = _black;
/*     */   }
/*     */   
/*     */ 
/*  66 */   public ChessPlayer getWhite() { return (ChessPlayer)this.players[0]; }
/*  67 */   public ChessPlayer getBlack() { return (ChessPlayer)this.players[1]; }
/*  68 */   public int getTimeControlInitial() { return this.timeControlInitial; }
/*  69 */   public int getTimeControlIncrement() { return this.timeControlIncrement; }
/*  70 */   public int getWhiteRating() { return this.whiteRating; }
/*  71 */   public int getBlackRating() { return this.blackRating; }
/*  72 */   public String getECO() { return this.eco; }
/*     */   
/*  74 */   public Result getResult() { return this.result; }
/*     */   
/*     */ 
/*  77 */   public void setWhite(ChessPlayer w) { this.players[0] = w; }
/*  78 */   public void setBlack(ChessPlayer b) { this.players[1] = b; }
/*  79 */   public void setTimeControlInitial(int i) { this.timeControlInitial = i; }
/*  80 */   public void setTimeControlIncrement(int i) { this.timeControlIncrement = i; }
/*  81 */   public void setWhiteRating(int rating) { this.whiteRating = rating; }
/*  82 */   public void setBlackRating(int rating) { this.blackRating = rating; }
/*  83 */   public void setECO(String eco) { this.eco = eco; }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  89 */     String tmp = null;
/*     */     
/*  91 */     tmp = "[White: " + this.players[0] + "]\n" + 
/*  92 */       "[Black: " + this.players[1] + "]\n" + 
/*  93 */       "[Event: " + this.event + "]\n" + 
/*  94 */       "[Site:  " + this.site + "]\n" + 
/*  95 */       "[Date:  " + getDateString() + "]\n" + 
/*  96 */       "[Round: " + this.round + "]\n" + 
/*  97 */       "[SubRound: " + this.subround + "]\n" + 
/*  98 */       "[TimeControlInitial: " + this.timeControlInitial + "]\n" + 
/*  99 */       "[TimeControlIncrement: " + this.timeControlIncrement + "]\n" + 
/* 100 */       "[Result: " + this.result + "]\n" + 
/* 101 */       "[WhiteElo: " + this.whiteRating + "]\n" + 
/* 102 */       "[BlackElo: " + this.blackRating + "]\n" + 
/* 103 */       "[ECO: " + this.eco + "]";
/* 104 */     return tmp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 111 */     if (this == obj) return true;
/* 112 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 113 */       return false;
/*     */     }
/* 115 */     ChessGameInfo gi = (ChessGameInfo)obj;
/* 116 */     boolean t = true;
/* 117 */     Log.debug(DEBUG, "checking for equality");
/* 118 */     t = (t) && (super.equals(obj));
/*     */     
/* 120 */     if (t) {
/* 121 */       t = (t) && (this.timeControlInitial == gi.timeControlInitial);
/* 122 */       if (!t) {
/* 123 */         Log.debug2(DEBUG, 
/* 124 */           "tcInit: " + this.timeControlInitial + 
/* 125 */           " / " + gi.timeControlInitial);
/*     */       }
/*     */     }
/* 128 */     if (t) {
/* 129 */       t = (t) && (this.timeControlIncrement == gi.timeControlIncrement);
/* 130 */       if (!t) {
/* 131 */         Log.debug2(DEBUG, 
/* 132 */           "tcIncr: " + this.timeControlIncrement + 
/* 133 */           " / " + gi.timeControlIncrement);
/*     */       }
/*     */     }
/* 136 */     if (t) {
/* 137 */       t = (t) && (
/* 138 */         ((this.result == null) && (gi.result == null)) || (
/* 139 */         (this.result != null) && (this.result.equals(gi.result))));
/* 140 */       if (!t) {
/* 141 */         Log.debug2(DEBUG, 
/* 142 */           "result: " + this.result + 
/* 143 */           " / " + gi.result);
/*     */       }
/*     */     }
/* 146 */     if (t) {
/* 147 */       t = (t) && (this.whiteRating == gi.whiteRating);
/* 148 */       if (!t) {
/* 149 */         Log.debug2(DEBUG, 
/* 150 */           "whiteRating: " + this.whiteRating + 
/* 151 */           " / " + gi.whiteRating);
/*     */       }
/*     */     }
/* 154 */     if (t) {
/* 155 */       t = (t) && (this.blackRating == gi.blackRating);
/* 156 */       if (!t) {
/* 157 */         Log.debug2(DEBUG, 
/* 158 */           "blackRating: " + this.blackRating + 
/* 159 */           " / " + gi.blackRating);
/*     */       }
/*     */     }
/* 162 */     if (t) {
/* 163 */       t = (t) && (
/* 164 */         ((this.eco == null) && (gi.eco == null)) || (
/* 165 */         (this.eco != null) && (this.eco.equals(gi.eco))));
/* 166 */       if (!t) {
/* 167 */         Log.debug2(DEBUG, 
/* 168 */           "eco: " + this.eco + 
/* 169 */           " / " + gi.eco);
/*     */       }
/*     */     }
/* 172 */     if (t) {
/* 173 */       t = (t) && (
/* 174 */         ((this.props == null) && (gi.props == null)) || (
/* 175 */         (this.props != null) && (this.props.equals(gi.props))));
/* 176 */       if (!t) {
/* 177 */         Log.debug2(DEBUG, 
/* 178 */           "aux: " + this.props + 
/* 179 */           " / " + gi.props);
/*     */       }
/*     */     }
/* 182 */     if (t)
/* 183 */       Log.debug2(DEBUG, "equal");
/* 184 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void dump()
/*     */   {
/* 191 */     StringBuffer sb = new StringBuffer();
/* 192 */     sb.append("##GameInfo Dump")
/* 193 */       .append(toString())
/* 194 */       .append("Aux Data:");
/* 195 */     if (this.props == null) {
/* 196 */       sb.append("None");
/*     */     } else {
/* 198 */       Enumeration enumer = this.props.propertyNames();
/* 199 */       String key = null;
/* 200 */       while (enumer.hasMoreElements()) {
/* 201 */         key = (String)enumer.nextElement();
/* 202 */         sb.append(key).append(" = ")
/* 203 */           .append(this.props.getProperty(key, null));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessGameInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */