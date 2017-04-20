/*    */ package com.amicabile.support;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Enumeration;
/*    */ import java.util.jar.JarEntry;
/*    */ import java.util.jar.JarFile;
/*    */ 
/*    */ public class JarUtil
/*    */ {
/*    */   public static Collection<String> getEntriesInFile(String fileName)
/*    */   {
/* 15 */     return getEntriesInFile(fileName, null);
/*    */   }
/*    */   
/*    */   public static Collection<String> getEntriesInFile(String fileName, String filter) {
/* 19 */     Collection<String> entryCollection = new ArrayList();
/*    */     try {
/* 21 */       File file = new File(fileName);
/*    */       
/* 23 */       JarFile jarFile = new JarFile(file);
/* 24 */       Enumeration<JarEntry> jarEntries = jarFile.entries();
/* 25 */       while (jarEntries.hasMoreElements()) {
/* 26 */         JarEntry jarEntry = (JarEntry)jarEntries.nextElement();
/* 27 */         String jarName = jarEntry.getName();
/* 28 */         if ((filter == null) || (RegExUtil.matches(jarName, filter))) {
/* 29 */           entryCollection.add(jarEntry.getName());
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (IOException e) {
/* 34 */       e.printStackTrace();
/*    */     }
/* 36 */     return entryCollection;
/*    */   }
/*    */   
/*    */   public static Collection<String> getFileContents(String fileName, String filter) {
/* 40 */     Collection<String> stringCollection = new ArrayList();
/*    */     try
/*    */     {
/* 43 */       Collection<String> entryCollection = new ArrayList();
/* 44 */       File file = new File(fileName);
/* 45 */       JarFile jarFile = new JarFile(file);
/*    */       
/* 47 */       Enumeration<JarEntry> jarEntries = jarFile.entries();
/* 48 */       while (jarEntries.hasMoreElements()) {
/* 49 */         JarEntry jarEntry = (JarEntry)jarEntries.nextElement();
/* 50 */         String jarName = jarEntry.getName();
/* 51 */         if ((filter == null) || (RegExUtil.matches(jarName, filter))) {
/* 52 */           java.io.InputStream stream = jarFile.getInputStream(jarEntry);
/* 53 */           byte[] byteArray = new byte[(int)Math.pow(2.0D, 15.0D)];
/* 54 */           stream.read(byteArray);
/* 55 */           String resultString = new String(byteArray);
/* 56 */           stringCollection.add(resultString);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/* 62 */       e.printStackTrace();
/*    */     }
/* 64 */     return stringCollection;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\JarUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */