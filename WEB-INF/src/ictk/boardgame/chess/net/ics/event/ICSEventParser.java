/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSAccountType;
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.util.Log;
/*     */ import java.io.IOException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public abstract class ICSEventParser
/*     */ {
/*  39 */   public static final long DEBUG = Log.ICSEventParser;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String REGEX_date = "((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  50 */   protected int eventType = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Pattern pattern;
/*     */   
/*     */ 
/*     */   protected boolean debug;
/*     */   
/*     */ 
/*     */ 
/*     */   protected ICSEventParser(Pattern master)
/*     */   {
/*  63 */     this.pattern = master;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDebug(boolean t)
/*     */   {
/*  70 */     this.debug = t;
/*     */   }
/*     */   
/*     */ 
/*     */   public Pattern getPattern()
/*     */   {
/*  76 */     return this.pattern;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getEventType()
/*     */   {
/*  83 */     return this.eventType;
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
/*     */   public Matcher match(CharSequence s)
/*     */   {
/* 100 */     Matcher m = this.pattern.matcher(s);
/*     */     
/* 102 */     if (m.find()) {
/* 103 */       if (this.debug)
/* 104 */         Log.debug(DEBUG, "matched: " + s, m);
/* 105 */       return m;
/*     */     }
/*     */     
/* 108 */     if (this.debug)
/* 109 */       Log.debug(DEBUG, "failed: " + s);
/* 110 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ICSEvent createICSEvent(CharSequence s)
/*     */   {
/* 122 */     Matcher m = match(s);
/* 123 */     if (m != null) {
/* 124 */       return createICSEvent(m);
/*     */     }
/* 126 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract ICSEvent createICSEvent(Matcher paramMatcher);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void assignMatches(Matcher paramMatcher, ICSEvent paramICSEvent);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean detectFake(CharSequence s)
/*     */   {
/* 152 */     return s.charAt(0) == ':';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract String toNative(ICSEvent paramICSEvent);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ICSAccountType parseICSAccountType(Matcher match, int index)
/*     */   {
/* 166 */     ICSAccountType acct = null;
/*     */     try {
/* 168 */       if (match.group(index) != null) {
/* 169 */         acct = new ICSAccountType(match.group(index));
/*     */       } else {
/* 171 */         acct = new ICSAccountType();
/*     */       }
/*     */     } catch (IOException e) {
/* 174 */       Log.error(3, 
/* 175 */         "Can't parse account type: " + 
/* 176 */         match.group(index) + " of " + match.group(0));
/* 177 */       acct = new ICSAccountType();
/* 178 */       if (this.debug)
/* 179 */         Log.debug(DEBUG, "regex:", match);
/*     */     }
/* 181 */     return acct;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ICSRating parseICSRating(Matcher match, int index)
/*     */   {
/* 192 */     ICSRating rating = null;
/*     */     try
/*     */     {
/* 195 */       if (match.group(index) != null) {
/* 196 */         rating = new ICSRating(match.group(index));
/*     */       }
/*     */     } catch (NumberFormatException e) {
/* 199 */       Log.error(3, 
/* 200 */         "Can't parse rating" + 
/* 201 */         match.group(index) + " of " + match.group(0));
/* 202 */       if (this.debug)
/* 203 */         Log.debug(DEBUG, "regex:", match);
/*     */     }
/* 205 */     return rating;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSEventParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */