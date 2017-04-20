/*    */ package com.amicabile.openingtrainer.model.notation;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class VelocityComment
/*    */ {
/*    */   private String text;
/*    */   
/*    */   public String getText()
/*    */   {
/* 11 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 15 */     this.text = text;
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 19 */     return StringUtils.isEmpty(this.text);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 23 */     return getText() != null ? 
/* 24 */       org.apache.commons.lang.StringEscapeUtils.escapeHtml(getText()) : 
/* 25 */       "";
/*    */   }
/*    */   
/*    */   public VelocityComment(String arg) {
/* 29 */     this.text = arg;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\notation\VelocityComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */