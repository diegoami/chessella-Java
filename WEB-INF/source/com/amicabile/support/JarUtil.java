package com.amicabile.support;

import com.amicabile.support.RegExUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtil {

   public static Collection getEntriesInFile(String fileName) {
      return getEntriesInFile(fileName, (String)null);
   }

   public static Collection getEntriesInFile(String fileName, String filter) {
      ArrayList entryCollection = new ArrayList();

      try {
         File e = new File(fileName);
         JarFile jarFile = new JarFile(e);
         Enumeration jarEntries = jarFile.entries();

         while(jarEntries.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry)jarEntries.nextElement();
            String jarName = jarEntry.getName();
            if(filter == null || RegExUtil.matches(jarName, filter)) {
               entryCollection.add(jarEntry.getName());
            }
         }
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      return entryCollection;
   }

   public static Collection getFileContents(String fileName, String filter) {
      ArrayList stringCollection = new ArrayList();

      try {
         new ArrayList();
         File file = new File(fileName);
         JarFile jarFile = new JarFile(file);
         Enumeration jarEntries = jarFile.entries();

         while(jarEntries.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry)jarEntries.nextElement();
            String jarName = jarEntry.getName();
            if(filter == null || RegExUtil.matches(jarName, filter)) {
               InputStream stream = jarFile.getInputStream(jarEntry);
               byte[] byteArray = new byte[(int)Math.pow(2.0D, 15.0D)];
               stream.read(byteArray);
               String resultString = new String(byteArray);
               stringCollection.add(resultString);
            }
         }
      } catch (IOException var12) {
         var12.printStackTrace();
      }

      return stringCollection;
   }
}
