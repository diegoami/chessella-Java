package com.amicabile.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

public class FileUtil {

   static transient Logger log = Logger.getLogger(FileUtil.class);


   public static void moveFileToDir(String fileName, String directoryName) {
      File file = new File(fileName);
      File dirFile = new File(directoryName);
      file.renameTo(new File(dirFile, file.getName()));
   }

   public static void deleteFilesInDir(String directoryName, String filterString) {
      Collection filesInDir = getFilesInDir(directoryName, filterString);
      Iterator var4 = filesInDir.iterator();

      while(var4.hasNext()) {
         File file = (File)var4.next();
         file.delete();
      }

   }

   public static void copyFileToDir(String fileName, String directoryName) throws IOException {
      File in = new File(fileName);
      File dirFile = new File(directoryName);
      File out = new File(dirFile, in.getName());
      FileInputStream fis = new FileInputStream(in);
      FileOutputStream fos = new FileOutputStream(out);
      byte[] buf = new byte[1024];
      boolean i = false;

      int i1;
      while((i1 = fis.read(buf)) != -1) {
         fos.write(buf, 0, i1);
      }

      fis.close();
      fos.close();
   }

   public static String addDir(String fileName, String dirToAdd) {
      File file = new File(fileName);
      File returnFile = new File(file.getParent() + "/" + dirToAdd, file.getName());
      return returnFile.getPath();
   }

   public static void renameFile(String sourceName, String destName) {
      File file = new File(sourceName);
      file.renameTo(new File(destName));
   }

   public static Collection getFilesInDir(String directoryName, String filterString) {
      return getFilesInDir(directoryName, filterString, false);
   }

   public static Collection getFilesInDir(String directoryName, String filterString, boolean recursive) {
      File dirFile = new File(directoryName);
      String[] children = dirFile.list();
      if(!ArrayUtils.isEmpty((Object[])children)) {
         ArrayList childWithDir = new ArrayList();
         String[] var9 = children;
         int var7 = 0;

         for(int var8 = children.length; var7 < var8; ++var7) {
            String child = var9[var7];
            String fullName = directoryName + child;
            File file = new File(fullName);
            if(file.isFile()) {
               if(child.indexOf(filterString) != -1) {
                  childWithDir.add(file);
               }
            } else if(recursive) {
               String dirName = file.getPath() + "/";
               Collection subDirResult = getFilesInDir(dirName, filterString, true);
               childWithDir.addAll(subDirResult);
            }
         }

         return childWithDir;
      } else {
         return new ArrayList();
      }
   }

   public static File getLastModifiedFileInDir(String directoryName, String filterString) {
      ArrayList files = new ArrayList(getFilesInDir(directoryName, filterString));
      Collections.sort(files, new Comparator() {
         public int compare(Object o1, Object o2) {
            File f1 = (File)o1;
            File f2 = (File)o2;
            return (int)(f1.lastModified() - f2.lastModified());
         }
      });
      return !files.isEmpty()?(File)files.get(files.size() - 1):null;
   }
}
