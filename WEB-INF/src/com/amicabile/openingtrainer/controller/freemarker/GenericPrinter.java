/*    */ package com.amicabile.openingtrainer.controller.freemarker;
/*    */ 
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.Template;
/*    */ import java.io.Writer;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenericPrinter
/*    */ {
/* 15 */   private static Logger log = Logger.getLogger(GenericPrinter.class
/* 16 */     .getName());
/*    */   
/*    */   protected Configuration config;
/*    */   protected String templateName;
/*    */   
/*    */   public GenericPrinter(String templateName, Configuration config)
/*    */   {
/* 23 */     this.templateName = templateName;
/* 24 */     this.config = config;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void fillMap(Map map) {}
/*    */   
/*    */ 
/*    */   public void showTemplate(Map map, Writer writer)
/*    */   {
/*    */     try
/*    */     {
/* 36 */       Template template = this.config.getTemplate(this.templateName);
/* 37 */       fillMap(map);
/* 38 */       template.process(map, writer);
/*    */       
/* 40 */       writer.flush();
/*    */     }
/*    */     catch (Exception re) {
/* 43 */       log.error(re);
/* 44 */       re.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\controller\freemarker\GenericPrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */