package com.amicabile.support;

import java.util.Calendar;
import java.util.Date;

public class StringUtil {

   public static String getCurrentDateString() {
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
      StringBuffer buffer = new StringBuffer();
      buffer.append(cal.get(1));
      buffer.append(cal.get(2) + 1);
      buffer.append(cal.get(5));
      buffer.append("_");
      buffer.append(cal.get(11));
      buffer.append(cal.get(12));
      buffer.append(cal.get(13));
      return buffer.toString();
   }
}
