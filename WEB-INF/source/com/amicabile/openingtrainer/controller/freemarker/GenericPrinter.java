package com.amicabile.openingtrainer.controller.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.Writer;
import java.util.Map;
import org.apache.log4j.Logger;

public class GenericPrinter {

   private static Logger log = Logger.getLogger(GenericPrinter.class.getName());
   protected Configuration config;
   protected String templateName;


   public GenericPrinter(String templateName, Configuration config) {
      this.templateName = templateName;
      this.config = config;
   }

   public void fillMap(Map map) {}

   public void showTemplate(Map map, Writer writer) {
      try {
         Template re = this.config.getTemplate(this.templateName);
         this.fillMap(map);
         re.process(map, writer);
         writer.flush();
      } catch (Exception var4) {
         log.error(var4);
         var4.printStackTrace();
      }

   }
}
