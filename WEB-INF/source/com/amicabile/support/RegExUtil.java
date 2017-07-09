package com.amicabile.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {

   public static boolean matches(String input, String regex) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      return matcher.find();
   }
}
