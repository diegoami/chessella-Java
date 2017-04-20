/*     */ package com.amicabile.support;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*  16 */   static transient Logger log = Logger.getLogger(FileUtil.class);
/*     */   
/*  18 */   public static void moveFileToDir(String fileName, String directoryName) { File file = new File(fileName);
/*  19 */     File dirFile = new File(directoryName);
/*  20 */     file.renameTo(new File(dirFile, file.getName()));
/*     */   }
/*     */   
/*     */   public static void deleteFilesInDir(String directoryName, String filterString) {
/*  24 */     Collection<File> filesInDir = getFilesInDir(directoryName, filterString);
/*     */     
/*  26 */     for (File file : filesInDir) {
/*  27 */       file.delete();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void copyFileToDir(String fileName, String directoryName) throws java.io.IOException
/*     */   {
/*  33 */     File in = new File(fileName);
/*  34 */     File dirFile = new File(directoryName);
/*  35 */     File out = new File(dirFile, in.getName());
/*  36 */     FileInputStream fis = new FileInputStream(in);
/*  37 */     FileOutputStream fos = new FileOutputStream(out);
/*  38 */     byte[] buf = new byte['Ѐ'];
/*  39 */     int i = 0;
/*  40 */     while ((i = fis.read(buf)) != -1) {
/*  41 */       fos.write(buf, 0, i);
/*     */     }
/*  43 */     fis.close();
/*  44 */     fos.close();
/*     */   }
/*     */   
/*     */   public static String addDir(String fileName, String dirToAdd)
/*     */   {
/*  49 */     File file = new File(fileName);
/*     */     
/*  51 */     File returnFile = new File(file.getParent() + "/" + dirToAdd, file.getName());
/*  52 */     return returnFile.getPath();
/*     */   }
/*     */   
/*     */   public static void renameFile(String sourceName, String destName) {
/*  56 */     File file = new File(sourceName);
/*  57 */     file.renameTo(new File(destName));
/*     */   }
/*     */   
/*     */ 
/*     */   public static Collection<File> getFilesInDir(String directoryName, String filterString)
/*     */   {
/*  63 */     return getFilesInDir(directoryName, filterString, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Collection<File> getFilesInDir(String directoryName, String filterString, boolean recursive)
/*     */   {
/*  71 */     File dirFile = new File(directoryName);
/*     */     
/*  73 */     String[] children = dirFile.list();
/*  74 */     if (!ArrayUtils.isEmpty(children)) {
/*  75 */       Collection<File> childWithDir = new ArrayList();
/*  76 */       for (String child : children)
/*     */       {
/*  78 */         String fullName = directoryName + child;
/*     */         
/*     */ 
/*  81 */         File file = new File(fullName);
/*  82 */         if (file.isFile()) {
/*  83 */           if (child.indexOf(filterString) != -1) {
/*  84 */             childWithDir.add(file);
/*     */           }
/*     */         }
/*  87 */         else if (recursive) {
/*  88 */           String dirName = file.getPath() + "/";
/*     */           
/*     */ 
/*  91 */           Collection<File> subDirResult = getFilesInDir(dirName, filterString, true);
/*  92 */           childWithDir.addAll(subDirResult);
/*     */         }
/*     */       }
/*     */       
/*  96 */       return childWithDir;
/*     */     }
/*     */     
/*  99 */     return new ArrayList();
/*     */   }
/*     */   
/*     */   public static File getLastModifiedFileInDir(String directoryName, String filterString)
/*     */   {
/* 104 */     List<File> files = new ArrayList(getFilesInDir(directoryName, filterString));
/* 105 */     Collections.sort(files, new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/* 107 */         File f1 = (File)o1;
/* 108 */         File f2 = (File)o2;
/* 109 */         return (int)(f1.lastModified() - f2.lastModified());
/*     */       }
/*     */     });
/* 112 */     if (!files.isEmpty()) {
/* 113 */       return (File)files.get(files.size() - 1);
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\FileUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */