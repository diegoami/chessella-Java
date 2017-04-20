/*     */ package com.amicabile.support;
/*     */ 
/*     */ import java.io.FilterReader;
/*     */ 
/*     */ public class AddSpaceReader
/*     */   extends FilterReader
/*     */ {
/*   8 */   public AddSpaceReader(java.io.Reader in) { super(in); }
/*     */   
/*  10 */   private boolean lastWasNewline = false;
/*  11 */   private int lineCharCounter = 0;
/*  12 */   private int CHARS_IN_LINE = 80;
/*  13 */   private int EMPTY_CHAR = 32;
/*  14 */   private boolean inGameInfo = true;
/*  15 */   private static enum StateEnum { READING_LINE,  AFTER_NEW_LINE,  CLOSING_LINE,  CLOSED_LINE; }
/*  16 */   private boolean openBrace = false;
/*  17 */   int ignoreNewLines = 0;
/*  18 */   private StateEnum state = StateEnum.CLOSED_LINE;
/*  19 */   private int ADD_CHAR = 32;
/*     */   
/*  21 */   public int read(char[] cbuf, int off, int len) throws java.io.IOException { int i = 0;
/*  22 */     while (i < len) {
/*  23 */       int ch = this.EMPTY_CHAR;
/*  24 */       if (this.state == StateEnum.READING_LINE) {
/*  25 */         ch = read();
/*  26 */         if (ch == -1) {
/*  27 */           if (i == 0) {
/*  28 */             return -1;
/*     */           }
/*  30 */           return i;
/*     */         }
/*     */         
/*  33 */         if (ch == 93) {
/*  34 */           this.inGameInfo = true;
/*     */         }
/*     */         
/*  37 */         if ((ch == 13) || (ch == 10)) {
/*  38 */           this.state = StateEnum.AFTER_NEW_LINE;
/*  39 */           continue;
/*     */         }
/*  41 */         this.lineCharCounter += 1;
/*     */       }
/*  43 */       else if (this.state == StateEnum.AFTER_NEW_LINE) {
/*  44 */         if ((this.openBrace) && (this.lineCharCounter < this.CHARS_IN_LINE)) {
/*  45 */           ch = this.EMPTY_CHAR;
/*  46 */           this.lineCharCounter += 1;
/*     */         } else {
/*  48 */           ch = 13;
/*  49 */           this.state = StateEnum.CLOSING_LINE;
/*     */         }
/*  51 */       } else if (this.state == StateEnum.CLOSING_LINE) {
/*  52 */         ch = 10;
/*  53 */         this.state = StateEnum.CLOSED_LINE;
/*  54 */       } else if (this.state == StateEnum.CLOSED_LINE) {
/*  55 */         ch = read();
/*  56 */         if ((ch == 13) || (ch == 10)) {
/*  57 */           this.ignoreNewLines += 1;
/*  58 */           if (this.ignoreNewLines < 2)
/*     */             continue;
/*     */         } else {
/*  61 */           this.lineCharCounter = 1;
/*  62 */           this.ignoreNewLines = 0;
/*  63 */           this.state = StateEnum.READING_LINE;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */       if (ch == 123) {
/* 101 */         this.openBrace = true;
/* 102 */       } else if (ch == 125) {
/* 103 */         this.openBrace = false;
/*     */       }
/*     */       
/* 106 */       System.out.println(ch);
/* 107 */       cbuf[(off + i++)] = ((char)ch);
/*     */     }
/* 109 */     return len;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\support\AddSpaceReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */