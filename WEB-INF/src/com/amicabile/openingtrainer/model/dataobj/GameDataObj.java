/*     */ package com.amicabile.openingtrainer.model.dataobj;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
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
/*     */ public class GameDataObj
/*     */   implements Serializable
/*     */ {
/*     */   private boolean publicgame;
/*     */   private boolean deleted;
/*     */   private String annotator;
/*     */   private Long id;
/*     */   private int boardnumber;
/*     */   private String white;
/*     */   private String black;
/*     */   private String result;
/*     */   private Date date;
/*     */   private String event;
/*     */   private String site;
/*     */   private String round;
/*     */   private String eco;
/*  49 */   private String tags = "";
/*     */   
/*     */ 
/*     */   private String pgnstring;
/*     */   
/*     */   private User user;
/*     */   
/*     */ 
/*     */   public GameDataObj(int boardnumber, String white, String black, String result, Date date, String event, String site, String round, String eco, String tags, String pgnstring, User user)
/*     */   {
/*  59 */     this.boardnumber = boardnumber;
/*  60 */     this.white = white;
/*  61 */     this.black = black;
/*  62 */     this.result = result;
/*  63 */     this.date = date;
/*  64 */     this.event = event;
/*  65 */     this.site = site;
/*  66 */     this.round = round;
/*  67 */     this.eco = eco;
/*  68 */     this.tags = tags;
/*  69 */     this.pgnstring = pgnstring;
/*  70 */     this.user = user;
/*     */   }
/*     */   
/*     */ 
/*     */   public GameDataObj() {}
/*     */   
/*     */ 
/*     */   public GameDataObj(String pgnstring)
/*     */   {
/*  79 */     this.pgnstring = pgnstring;
/*     */   }
/*     */   
/*     */   public Long getId() {
/*  83 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(Long id) {
/*  87 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getBoardnumber() {
/*  91 */     return this.boardnumber;
/*     */   }
/*     */   
/*     */   public void setBoardnumber(int boardnumber) {
/*  95 */     this.boardnumber = boardnumber;
/*     */   }
/*     */   
/*     */   public String getWhite() {
/*  99 */     return this.white;
/*     */   }
/*     */   
/*     */   public void setWhite(String white) {
/* 103 */     this.white = white;
/*     */   }
/*     */   
/*     */   public String getBlack() {
/* 107 */     return this.black;
/*     */   }
/*     */   
/*     */   public void setBlack(String black) {
/* 111 */     this.black = black;
/*     */   }
/*     */   
/*     */   public String getResult() {
/* 115 */     return this.result;
/*     */   }
/*     */   
/*     */   public void setResult(String result) {
/* 119 */     this.result = result;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 123 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 127 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getEvent() {
/* 131 */     return this.event;
/*     */   }
/*     */   
/*     */   public void setEvent(String event) {
/* 135 */     this.event = event;
/*     */   }
/*     */   
/*     */   public String getSite() {
/* 139 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/* 143 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getRound() {
/* 147 */     return this.round;
/*     */   }
/*     */   
/*     */   public void setRound(String round) {
/* 151 */     this.round = round;
/*     */   }
/*     */   
/*     */   public String getEco() {
/* 155 */     return this.eco;
/*     */   }
/*     */   
/*     */   public void setEco(String eco) {
/* 159 */     this.eco = eco;
/*     */   }
/*     */   
/*     */   public String getTags() {
/* 163 */     return this.tags != null ? this.tags : "";
/*     */   }
/*     */   
/*     */   public void setTags(String tags) {
/* 167 */     this.tags = tags;
/*     */   }
/*     */   
/*     */   public String getPgnstring() {
/* 171 */     return this.pgnstring;
/*     */   }
/*     */   
/*     */   public String getEncodedPgnstring() {
/* 175 */     return StringEscapeUtils.escapeHtml(this.pgnstring);
/*     */   }
/*     */   
/*     */   public void setPgnstring(String pgnstring) {
/* 179 */     this.pgnstring = pgnstring;
/*     */   }
/*     */   
/*     */   public User getUser() {
/* 183 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(User user) {
/* 187 */     this.user = user;
/*     */   }
/*     */   
/*     */   public String getSubmitter() {
/* 191 */     if (this.user != null) {
/* 192 */       return this.user.getUsername();
/*     */     }
/*     */     
/* 195 */     return "";
/*     */   }
/*     */   
/*     */   public String toString() {
/* 199 */     return 
/*     */     
/* 201 */       new ToStringBuilder(this).append("id", getId()).toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 205 */     if (!(other instanceof GameDataObj)) return false;
/* 206 */     GameDataObj castOther = (GameDataObj)other;
/* 207 */     return new EqualsBuilder()
/* 208 */       .append(getId(), castOther.getId())
/* 209 */       .isEquals();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 213 */     return 
/*     */     
/* 215 */       new HashCodeBuilder().append(getId()).toHashCode();
/*     */   }
/*     */   
/*     */   public String getAnnotator() {
/* 219 */     return this.annotator;
/*     */   }
/*     */   
/*     */   public void setAnnotator(String annotator) {
/* 223 */     this.annotator = annotator;
/*     */   }
/*     */   
/*     */   public boolean isPublicgame() {
/* 227 */     return this.publicgame;
/*     */   }
/*     */   
/*     */   public void setPublicgame(boolean publicgame) {
/* 231 */     this.publicgame = publicgame;
/*     */   }
/*     */   
/*     */   public boolean isDeleted() {
/* 235 */     return this.deleted;
/*     */   }
/*     */   
/*     */   public void setDeleted(boolean deleted) {
/* 239 */     this.deleted = deleted;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\dataobj\GameDataObj.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */