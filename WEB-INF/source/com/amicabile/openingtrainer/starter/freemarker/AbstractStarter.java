package com.amicabile.openingtrainer.starter.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class AbstractStarter {

   protected Map rootMap = new HashMap();
   protected Configuration cfg;
   protected String templateFileName;


   public AbstractStarter() {
      try {
         Configuration e = new Configuration();
         e.setDirectoryForTemplateLoading(new File("templates"));
         e.setObjectWrapper(new DefaultObjectWrapper());
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   protected Template createTemplate() throws Exception {
      Template template = null;

      try {
         template = this.cfg.getTemplate(this.templateFileName);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return template;
   }

   protected void writeContextToTemplate(Template template, OutputStreamWriter osw) throws Exception {
      BufferedWriter writer = new BufferedWriter(osw);
      template.process(this.rootMap, writer);
      writer.flush();
   }

   protected void writeContextToTemplate(Template template, String fileName) throws Exception, IOException {
      File file = new File(fileName);
      FileWriter fwriter = new FileWriter(file);
      this.writeContextToTemplate(template, (OutputStreamWriter)fwriter);
   }

   protected void writeContextToTemplate(Template template) throws Exception, IOException {
      this.writeContextToTemplate(template, new OutputStreamWriter(System.out));
   }

   public void printOut() throws Exception {
      this.writeContextToTemplate(this.createTemplate());
   }

   public void printToFile(String fileName) throws Exception {
      this.writeContextToTemplate(this.createTemplate(), fileName);
   }
}
