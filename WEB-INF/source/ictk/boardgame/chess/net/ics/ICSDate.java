package ictk.boardgame.chess.net.ics;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICSDate {

   public static final String REGEX = "((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))";
   public static final Pattern pattern = Pattern.compile("((\\w{3})\\s(\\w{3})\\s+(\\d+),\\s(\\d+):(\\d{2})\\s(\\w+)\\s(\\d{4}))");
   protected Calendar calendar;
   protected String original;
   protected String tzoneID;
   protected String dayOfWeek;
   protected String month;
   protected int year;
   protected int day;
   protected int hour;
   protected int minute;


   public ICSDate() {}

   public ICSDate(String str) {
      this.set(str);
   }

   public void set(String str) throws IllegalArgumentException {
      Matcher m = pattern.matcher(str);
      if(!m.find()) {
         throw new IllegalArgumentException("Can\'t understand " + str);
      } else {
         this.tzoneID = m.group(7);
         if(this.tzoneID.equals("???")) {
            this.tzoneID = null;
         }

         this.dayOfWeek = m.group(2);
         this.month = m.group(3);
         boolean i = false;

         try {
            i = true;
            this.day = Integer.parseInt(m.group(4));
            i = true;
            this.hour = Integer.parseInt(m.group(5));
            i = true;
            this.minute = Integer.parseInt(m.group(6));
            i = true;
            this.year = Integer.parseInt(m.group(8));
         } catch (NumberFormatException var5) {
            throw new IllegalArgumentException("Can\'t understand " + str);
         }
      }
   }

   public String getMonthID() {
      return this.month;
   }

   public int getYear() {
      return this.year;
   }

   public int getDay() {
      return this.day;
   }

   public int getHour() {
      return this.hour;
   }

   public int getMinute() {
      return this.minute;
   }

   public String getTimeZoneID() {
      return this.tzoneID;
   }

   public String getDayOfWeekID() {
      return this.dayOfWeek;
   }

   public void setMonth(String m) {
      this.month = m;
   }

   public void setYear(int y) {
      this.year = y;
   }

   public void setDay(int dayOfMonth) {
      this.day = dayOfMonth;
   }

   public void setHour(int twentyfour) {
      this.hour = twentyfour;
   }

   public void setMinute(int min) {
      this.minute = min;
   }

   public void setTimeZone(String id) {
      this.tzoneID = id;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer(27);
      sb.append(this.dayOfWeek).append(" ").append(this.month).append(" ");
      if(this.day < 10) {
         sb.append(" ");
      }

      sb.append(this.day).append(", ");
      if(this.hour < 10) {
         sb.append("0");
      }

      sb.append(this.hour).append(":");
      if(this.minute < 10) {
         sb.append("0");
      }

      sb.append(this.minute).append(" ");
      if(this.tzoneID == null) {
         sb.append("???");
      } else {
         sb.append(this.tzoneID);
      }

      sb.append(" ").append(this.year);
      return sb.toString();
   }
}
