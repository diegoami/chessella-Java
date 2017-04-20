/*     */ package ictk.boardgame;
/*     */ 
/*     */ import ictk.util.Log;
/*     */ import java.util.Calendar;
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
/*     */ public abstract class GameInfo
/*     */ {
/*  38 */   public static final long DEBUG = Log.GameInfo;
/*  39 */   public Properties props = new Properties();
/*     */   
/*     */ 
/*     */   protected Player[] players;
/*     */   
/*     */ 
/*     */   protected Calendar date;
/*     */   
/*     */   protected String event;
/*     */   
/*     */   protected String site;
/*     */   
/*     */   protected String round;
/*     */   protected String subround;
/*     */   protected Result result;
/*     */   protected int year;
/*     */   protected int month;
/*     */   protected int day;
/*     */   
/*  58 */   public Player[] getPlayers() { return this.players; }
/*  59 */   public String getEvent() { return this.event; }
/*  60 */   public String getSite() { return this.site; }
/*  61 */   public Calendar getDate() { return this.date; }
/*  62 */   public String getRound() { return this.round; }
/*  63 */   public String getSubRound() { return this.subround; }
/*  64 */   public int getYear() { return this.year; }
/*  65 */   public int getMonth() { return this.month; }
/*  66 */   public int getDay() { return this.day; }
/*     */   
/*  68 */   public Result getResult() { return this.result; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Properties getAuxilleryProperties()
/*     */   {
/*  75 */     return this.props;
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
/*     */   public String getDateString()
/*     */   {
/* 120 */     StringBuffer sb = new StringBuffer(10);
/* 121 */     if (this.year > 0) {
/* 122 */       if (this.year < 10)
/* 123 */         sb.append(" ");
/* 124 */       if (this.year < 100)
/* 125 */         sb.append(" ");
/* 126 */       if (this.year < 1000)
/* 127 */         sb.append(" ");
/* 128 */       sb.append(this.year);
/*     */     }
/*     */     else {
/* 131 */       sb.append("????");
/*     */     }
/* 133 */     sb.append(".");
/*     */     
/* 135 */     if (this.month > 0) {
/* 136 */       if (this.month < 10)
/* 137 */         sb.append("0");
/* 138 */       sb.append(this.month);
/*     */     }
/*     */     else {
/* 141 */       sb.append("??");
/*     */     }
/* 143 */     sb.append(".");
/*     */     
/* 145 */     if (this.day > 0) {
/* 146 */       if (this.day < 10)
/* 147 */         sb.append("0");
/* 148 */       sb.append(this.day);
/*     */     }
/*     */     else {
/* 151 */       sb.append("??");
/*     */     }
/* 153 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 158 */   public void setEvent(String event) { this.event = event; }
/* 159 */   public void setSite(String site) { this.site = site; }
/* 160 */   public void setDate(Calendar date) { this.date = date; }
/* 161 */   public void setRound(String round) { this.round = round; }
/* 162 */   public void setSubRound(String round) { this.subround = round; }
/* 163 */   public void setYear(int i) { this.year = i; }
/* 164 */   public void setMonth(int i) { this.month = i; }
/* 165 */   public void setDay(int i) { this.day = i; }
/* 166 */   public void setResult(Result res) { this.result = res; }
/*     */   
/*     */ 
/*     */   public void setAuxilleryProperties(Properties p)
/*     */   {
/* 171 */     this.props = p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void add(String key, String value)
/*     */   {
/* 178 */     this.props.setProperty(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String get(String key)
/*     */   {
/* 185 */     return this.props.getProperty(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 192 */     String tmp = null;
/*     */     
/* 194 */     tmp = "[Event: " + this.event + "]\n" + 
/* 195 */       "[Site:  " + this.site + "]\n" + 
/* 196 */       "[Date:  " + getDateString() + "]\n" + 
/* 197 */       "[Round: " + this.round + "]\n" + 
/* 198 */       "[SubRound: " + this.subround + "]\n" + 
/* 199 */       "[Result: " + this.result + "]\n";
/* 200 */     return tmp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 207 */     if (o == this) return true;
/* 208 */     if ((o == null) || (o.getClass() != getClass())) {
/* 209 */       return false;
/*     */     }
/* 211 */     GameInfo gi = (GameInfo)o;
/* 212 */     boolean t = true;
/*     */     
/* 214 */     Log.debug(DEBUG, "checking for equality");
/*     */     
/* 216 */     if ((t) && (this.players == gi.players)) {
/* 217 */       t = true;
/* 218 */     } else if ((t) && 
/* 219 */       (this.players != null) && (gi.players != null) && 
/* 220 */       (this.players.length == gi.players.length))
/*     */     {
/* 222 */       for (int i = 0; (t) && (i < this.players.length); i++) {
/* 223 */         t = (t) && (isSame(this.players[i], gi.players[i]));
/*     */         
/* 225 */         if (!t) {
/* 226 */           Log.debug2(DEBUG, 
/* 227 */             "players[" + i + "]: " + this.players[i] + 
/* 228 */             " / " + gi.players[i]);
/*     */         }
/*     */       }
/*     */     }
/* 232 */     if (t) {
/* 233 */       t = (t) && (isSame(this.event, gi.event));
/* 234 */       if (!t) {
/* 235 */         Log.debug2(DEBUG, 
/* 236 */           "event: " + this.event + 
/* 237 */           " / " + gi.event);
/*     */       }
/*     */     }
/* 240 */     if (t) {
/* 241 */       t = (t) && (equalDates(gi.date));
/* 242 */       if (!t) {
/* 243 */         Log.debug2(DEBUG, 
/* 244 */           "date: " + this.date + 
/* 245 */           " / " + gi.date);
/*     */       }
/*     */     }
/* 248 */     if (t) {
/* 249 */       t = (t) && (isSame(this.round, gi.round));
/* 250 */       if (!t) {
/* 251 */         Log.debug2(DEBUG, 
/* 252 */           "round: " + this.round + 
/* 253 */           " / " + gi.round);
/*     */       }
/*     */     }
/* 256 */     if (t) {
/* 257 */       t = (t) && (isSame(this.subround, gi.subround));
/* 258 */       if (!t) {
/* 259 */         Log.debug2(DEBUG, 
/* 260 */           "subround: " + this.subround + 
/* 261 */           " / " + gi.subround);
/*     */       }
/*     */     }
/* 264 */     if (t) {
/* 265 */       t = (t) && (isSame(this.result, gi.result));
/* 266 */       if (!t) {
/* 267 */         Log.debug2(DEBUG, 
/* 268 */           "result: " + this.result + 
/* 269 */           " / " + gi.result);
/*     */       }
/*     */     }
/* 272 */     if (t) {
/* 273 */       t = (t) && (isSame(this.props, gi.props));
/* 274 */       if (!t) {
/* 275 */         Log.debug2(DEBUG, 
/* 276 */           "aux: " + this.props + 
/* 277 */           " / " + gi.props);
/*     */       }
/*     */     }
/* 280 */     if (t)
/* 281 */       Log.debug2(DEBUG, "equal");
/* 282 */     return t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSame(Object o, Object p)
/*     */   {
/* 291 */     return (o == p) || ((o != null) && (o.equals(p)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean equalDates(Calendar cal)
/*     */   {
/* 301 */     boolean t = true;
/*     */     
/* 303 */     if ((this.date == null) && (this.date == cal)) {
/* 304 */       return true;
/*     */     }
/* 306 */     t = (t) && (cal != null);
/* 307 */     t = (t) && (this.date.isSet(1) == cal.isSet(1));
/* 308 */     t = (t) && (this.date.isSet(1)) && 
/* 309 */       (this.date.get(1) == cal.get(1));
/* 310 */     t = (t) && (this.date.isSet(2)) && 
/* 311 */       (this.date.get(2) == cal.get(2));
/* 312 */     t = (t) && (this.date.isSet(5)) && 
/* 313 */       (this.date.get(5) == 
/* 314 */       cal.get(5));
/* 315 */     return t;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 320 */     int hash = 5;
/*     */     
/* 322 */     if (this.players != null)
/* 323 */       for (int i = 0; i < this.players.length; i++)
/* 324 */         hash = 31 * hash + (this.players[i] == null ? 0 : this.players[i].hashCode());
/* 325 */     hash = 31 * hash + getDateString().hashCode();
/* 326 */     hash = 31 * hash + (this.event == null ? 0 : this.event.hashCode());
/* 327 */     hash = 31 * hash + (this.site == null ? 0 : this.site.hashCode());
/* 328 */     hash = 31 * hash + (this.round == null ? 0 : this.round.hashCode());
/* 329 */     hash = 31 * hash + (this.subround == null ? 0 : this.subround.hashCode());
/*     */     
/* 331 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void dump()
/*     */   {
/* 338 */     StringBuffer sb = new StringBuffer();
/* 339 */     sb.append("##GameInfo Dump")
/* 340 */       .append(toString())
/* 341 */       .append("Aux Data:");
/* 342 */     if (this.props == null) {
/* 343 */       sb.append("None");
/*     */     } else {
/* 345 */       Enumeration enumer = this.props.propertyNames();
/* 346 */       String key = null;
/* 347 */       while (enumer.hasMoreElements()) {
/* 348 */         key = (String)enumer.nextElement();
/* 349 */         sb.append(key).append(" = ")
/* 350 */           .append(this.props.getProperty(key, null));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\GameInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */