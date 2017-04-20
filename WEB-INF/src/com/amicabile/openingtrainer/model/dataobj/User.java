/*     */ package com.amicabile.openingtrainer.model.dataobj;
/*     */ 
/*     */ import com.amicabile.openingtrainer.config.ShowBoardRule;
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class User
/*     */   implements Serializable
/*     */ {
/*  14 */   private static int MAX_SIZE = 10;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private int maxgames;
/*     */   
/*     */   private boolean showBigBoard;
/*     */   
/*     */   private String username;
/*     */   
/*     */   private String email;
/*     */   
/*     */   private String password;
/*     */   
/*     */   private boolean showBeforeBranch;
/*     */   private boolean showBeforeImportantMove;
/*     */   private boolean showBeforeComment;
/*     */   
/*     */   public User(String username, String email, String password)
/*     */   {
/*  34 */     this.username = username;
/*  35 */     this.email = email;
/*  36 */     this.password = password;
/*     */   }
/*     */   
/*     */ 
/*     */   public User() {}
/*     */   
/*     */ 
/*     */   public User(String username)
/*     */   {
/*  45 */     this.username = username;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  49 */     return this.id;
/*     */   }
/*     */   
/*     */   protected void setId(Integer id) {
/*  53 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  57 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  61 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  65 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/*  69 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  73 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/*  77 */     this.password = password;
/*     */   }
/*     */   
/*     */   public ShowBoardRule getShowBoardRule() {
/*  81 */     ShowBoardRule showBoardRule = new ShowBoardRule();
/*  82 */     showBoardRule.setShowBeforeBranch(isShowBeforeBranch());
/*  83 */     showBoardRule.setShowBeforeComment(isShowBeforeComment());
/*  84 */     showBoardRule.setShowBeforeImportantMove(isShowBeforeImportantMove());
/*  85 */     return showBoardRule;
/*     */   }
/*     */   
/*     */   public void setShowBoardRule(ShowBoardRule showBoardRule) {
/*  89 */     setShowBeforeBranch(showBoardRule.isShowBeforeBranch());
/*  90 */     setShowBeforeComment(showBoardRule.isShowBeforeComment());
/*  91 */     setShowBeforeImportantMove(showBoardRule.isShowBeforeImportantMove());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShowBeforeComment()
/*     */   {
/*  99 */     return this.showBeforeComment;
/*     */   }
/*     */   
/* 102 */   public void setShowBeforeComment(boolean showBeforeComment) { this.showBeforeComment = showBeforeComment; }
/*     */   
/*     */   public boolean isShowBeforeBranch() {
/* 105 */     return this.showBeforeBranch;
/*     */   }
/*     */   
/* 108 */   public void setShowBeforeBranch(boolean showBeforeBranch) { this.showBeforeBranch = showBeforeBranch; }
/*     */   
/*     */   public boolean isShowBeforeImportantMove() {
/* 111 */     return this.showBeforeImportantMove;
/*     */   }
/*     */   
/* 114 */   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) { this.showBeforeImportantMove = showBeforeImportantMove; }
/*     */   
/*     */   public String toString()
/*     */   {
/* 118 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */       new ToStringBuilder(this).append("id", getId()).append("username", getUsername()).append("password", getPassword()).append("showBeforeComment", isShowBeforeComment()).append("showBeforeBranch", isShowBeforeBranch()).append("showBeforeImportantMove", isShowBeforeImportantMove()).toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 130 */     if (!(other instanceof User)) return false;
/* 131 */     User castOther = (User)other;
/* 132 */     return new EqualsBuilder()
/* 133 */       .append(getId(), castOther.getId())
/* 134 */       .isEquals();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 138 */     return 
/*     */     
/* 140 */       new HashCodeBuilder().append(getId()).toHashCode();
/*     */   }
/*     */   
/*     */   public int getMaxgames() {
/* 144 */     return Math.max(this.maxgames, MAX_SIZE);
/*     */   }
/*     */   
/*     */   public void setMaxgames(int maxgames) {
/* 148 */     this.maxgames = maxgames;
/*     */   }
/*     */   
/*     */   public boolean isShowBigBoard() {
/* 152 */     return this.showBigBoard;
/*     */   }
/*     */   
/*     */   public void setShowBigBoard(boolean showBigBoard) {
/* 156 */     this.showBigBoard = showBigBoard;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\dataobj\User.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */