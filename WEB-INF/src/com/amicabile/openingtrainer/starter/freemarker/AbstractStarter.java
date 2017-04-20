/*    */ package com.amicabile.openingtrainer.starter.freemarker;
/*    */ 
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.DefaultObjectWrapper;
/*    */ import freemarker.template.Template;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AbstractStarter
/*    */ {
/* 16 */   protected Map rootMap = new HashMap();
/*    */   protected Configuration cfg;
/*    */   protected String templateFileName;
/*    */   
/*    */   public AbstractStarter() {
/*    */     try {
/* 22 */       Configuration cfg = new Configuration();
/* 23 */       cfg.setDirectoryForTemplateLoading(
/* 24 */         new File("templates"));
/* 25 */       cfg.setObjectWrapper(new DefaultObjectWrapper());
/*    */     }
/*    */     catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   protected Template createTemplate()
/*    */     throws Exception
/*    */   {
/* 35 */     Template template = null;
/*    */     
/*    */     try
/*    */     {
/* 39 */       template = this.cfg.getTemplate(this.templateFileName);
/*    */     }
/*    */     catch (Exception rnfe)
/*    */     {
/* 43 */       rnfe.printStackTrace();
/*    */     }
/* 45 */     return template;
/*    */   }
/*    */   
/*    */   protected void writeContextToTemplate(Template template, OutputStreamWriter osw) throws Exception
/*    */   {
/* 50 */     BufferedWriter writer = writer = new BufferedWriter(
/* 51 */       osw);
/*    */     
/* 53 */     template.process(this.rootMap, writer);
/* 54 */     writer.flush();
/*    */   }
/*    */   
/*    */   protected void writeContextToTemplate(Template template, String fileName) throws Exception, IOException
/*    */   {
/* 59 */     File file = new File(fileName);
/* 60 */     FileWriter fwriter = new FileWriter(file);
/* 61 */     writeContextToTemplate(template, fwriter);
/*    */   }
/*    */   
/*    */   protected void writeContextToTemplate(Template template) throws Exception, IOException {
/* 65 */     writeContextToTemplate(template, new OutputStreamWriter(System.out));
/*    */   }
/*    */   
/*    */   public void printOut() throws Exception {
/* 69 */     writeContextToTemplate(createTemplate());
/*    */   }
/*    */   
/*    */   public void printToFile(String fileName) throws Exception {
/* 73 */     writeContextToTemplate(createTemplate(), fileName);
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\starter\freemarker\AbstractStarter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */