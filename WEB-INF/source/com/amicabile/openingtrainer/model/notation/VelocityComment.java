package com.amicabile.openingtrainer.model.notation;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class VelocityComment {

   private String text;


   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public boolean isEmpty() {
      return StringUtils.isEmpty(this.text);
   }

   public String toString() {
      return this.getText() != null?StringEscapeUtils.escapeHtml(this.getText()):"";
   }

   public VelocityComment(String arg) {
      this.text = arg;
   }
}
