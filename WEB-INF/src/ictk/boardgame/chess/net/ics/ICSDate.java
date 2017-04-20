/*     */ package ictk.boardgame.chess.net.ics;
/*     */ 
/*     */ import java.util.Calendar;
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
/*     */ public class ICSDate
/*     */ {
/*     */   public static final String REGEX = "((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))";
/*  54 */   public static final Pattern pattern = Pattern.compile("((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))");
/*     */   protected Calendar calendar;
/*     */   protected String original;
/*     */   protected String tzoneID;
/*     */   protected String dayOfWeek;
/*     */   protected String month;
/*     */   protected int year;
/*     */   protected int day;
/*     */   protected int hour;
/*     */   protected int minute;
/*     */   
/*     */   public ICSDate() {}
/*     */   
/*     */   public ICSDate(String str)
/*     */   {
/*  69 */     set(str);
/*     */   }
/*     */   
/*     */   public void set(String str) throws IllegalArgumentException {
/*  73 */     Matcher m = pattern.matcher(str);
/*     */     
/*  75 */     if (!m.find()) {
/*  76 */       throw new IllegalArgumentException("Can't understand " + str);
/*     */     }
/*  78 */     this.tzoneID = m.group(7);
/*  79 */     if (this.tzoneID.equals("???")) { this.tzoneID = null;
/*     */     }
/*  81 */     this.dayOfWeek = m.group(2);
/*  82 */     this.month = m.group(3);
/*     */     
/*  84 */     int i = 0;
/*     */     try {
/*  86 */       this.day = Integer.parseInt(m.group(i = 4));
/*  87 */       this.hour = Integer.parseInt(m.group(i = 5));
/*  88 */       this.minute = Integer.parseInt(m.group(i = 6));
/*  89 */       this.year = Integer.parseInt(m.group(i = 8));
/*     */     }
/*     */     catch (NumberFormatException e) {
/*  92 */       throw new IllegalArgumentException("Can't understand " + str);
/*     */     }
/*     */   }
/*     */   
/*  96 */   public String getMonthID() { return this.month; }
/*  97 */   public int getYear() { return this.year; }
/*  98 */   public int getDay() { return this.day; }
/*  99 */   public int getHour() { return this.hour; }
/* 100 */   public int getMinute() { return this.minute; }
/* 101 */   public String getTimeZoneID() { return this.tzoneID; }
/* 102 */   public String getDayOfWeekID() { return this.dayOfWeek; }
/*     */   
/* 104 */   public void setMonth(String m) { this.month = m; }
/* 105 */   public void setYear(int y) { this.year = y; }
/* 106 */   public void setDay(int dayOfMonth) { this.day = dayOfMonth; }
/* 107 */   public void setHour(int twentyfour) { this.hour = twentyfour; }
/* 108 */   public void setMinute(int min) { this.minute = min; }
/* 109 */   public void setTimeZone(String id) { this.tzoneID = id; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 116 */     StringBuffer sb = new StringBuffer(27);
/* 117 */     sb.append(this.dayOfWeek)
/* 118 */       .append(" ")
/* 119 */       .append(this.month)
/* 120 */       .append(" ");
/*     */     
/* 122 */     if (this.day < 10) {
/* 123 */       sb.append(" ");
/*     */     }
/*     */     
/* 126 */     sb.append(this.day).append(", ");
/*     */     
/* 128 */     if (this.hour < 10) {
/* 129 */       sb.append("0");
/*     */     }
/*     */     
/* 132 */     sb.append(this.hour).append(":");
/*     */     
/* 134 */     if (this.minute < 10) {
/* 135 */       sb.append("0");
/*     */     }
/*     */     
/* 138 */     sb.append(this.minute).append(" ");
/*     */     
/* 140 */     if (this.tzoneID == null) {
/* 141 */       sb.append("???");
/*     */     } else {
/* 143 */       sb.append(this.tzoneID);
/*     */     }
/*     */     
/* 146 */     sb.append(" ").append(this.year);
/*     */     
/* 148 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\ICSDate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */