/*     */ package com.amicabile.openingtrainer.model.search;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class GameSearchCriteria
/*     */ {
/*  11 */   private static Logger log = Logger.getLogger(GameSearchCriteria.class.getName());
/*     */   
/*  13 */   private String white = "";
/*  14 */   private String black = "";
/*     */   
/*     */   private boolean ignoreColor;
/*     */   
/*     */   private Date beforeDate;
/*     */   
/*     */   private Date afterDate;
/*     */   private String submitter;
/*     */   private String loggedinUser;
/*     */   private String result;
/*     */   private String resultKey;
/*     */   private String event;
/*     */   private String site;
/*     */   private String tags;
/*     */   private String beforeECO;
/*     */   private String afterECO;
/*     */   
/*     */   public String getTags()
/*     */   {
/*  33 */     return this.tags;
/*     */   }
/*     */   
/*  36 */   public void setTags(String tags) { this.tags = tags; }
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
/*     */   public boolean isEnough()
/*     */   {
/*  53 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean longEnough(String arg)
/*     */   {
/*  59 */     boolean result = (!StringUtils.isEmpty(arg)) && (arg.length() > 2);
/*  60 */     if (result) {
/*  61 */       log.info(arg + " is long enough");
/*     */     }
/*  63 */     return result;
/*     */   }
/*     */   
/*     */ 
/*  67 */   private static Map<String, String> resultMap = new LinkedHashMap();
/*     */   
/*     */   static {
/*  70 */     resultMap.put("1", "");
/*  71 */     resultMap.put("2", "1-0");
/*  72 */     resultMap.put("3", "1/2-1/2");
/*  73 */     resultMap.put("4", "0-1");
/*  74 */     resultMap.put("5", "*");
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, String> getResultMap()
/*     */   {
/*  80 */     return resultMap;
/*     */   }
/*     */   
/*     */ 
/*  84 */   private String searchValid = null;
/*     */   
/*     */   public String getSearchValid()
/*     */   {
/*  88 */     return this.searchValid;
/*     */   }
/*     */   
/*  91 */   public void setSearchValid(String searchValid) { this.searchValid = searchValid; }
/*     */   
/*     */   public Date getAfterDate() {
/*  94 */     return this.afterDate;
/*     */   }
/*     */   
/*  97 */   public void setAfterDate(Date afterDate) { this.afterDate = afterDate; }
/*     */   
/*     */   public Date getBeforeDate() {
/* 100 */     return this.beforeDate;
/*     */   }
/*     */   
/* 103 */   public void setBeforeDate(Date beforeDate) { this.beforeDate = beforeDate; }
/*     */   
/*     */   public String getBeforeECO()
/*     */   {
/* 107 */     return this.beforeECO;
/*     */   }
/*     */   
/* 110 */   public void setBeforeECO(String beforeECO) { this.beforeECO = beforeECO; }
/*     */   
/*     */   public String getBlack() {
/* 113 */     return this.black;
/*     */   }
/*     */   
/* 116 */   public void setBlack(String black) { this.black = black; }
/*     */   
/*     */   public boolean isIgnoreColor() {
/* 119 */     return this.ignoreColor;
/*     */   }
/*     */   
/* 122 */   public void setIgnoreColor(boolean ignoreColor) { this.ignoreColor = ignoreColor; }
/*     */   
/*     */   public String getSubmitter() {
/* 125 */     return this.submitter;
/*     */   }
/*     */   
/* 128 */   public void setSubmitter(String submitter) { this.submitter = submitter; }
/*     */   
/*     */   public String getWhite()
/*     */   {
/* 132 */     return this.white;
/*     */   }
/*     */   
/* 135 */   public void setWhite(String white) { this.white = white; }
/*     */   
/*     */   public String getResult() {
/* 138 */     return (String)resultMap.get(this.resultKey);
/*     */   }
/*     */   
/* 141 */   public String getResultKey() { return this.resultKey; }
/*     */   
/*     */   public void setResultKey(String resultKey) {
/* 144 */     this.resultKey = resultKey;
/*     */   }
/*     */   
/* 147 */   public String getEvent() { return this.event; }
/*     */   
/*     */   public void setEvent(String event) {
/* 150 */     this.event = event;
/*     */   }
/*     */   
/* 153 */   public String getSite() { return this.site; }
/*     */   
/*     */   public void setSite(String site) {
/* 156 */     this.site = site;
/*     */   }
/*     */   
/* 159 */   public String getAfterECO() { return this.afterECO; }
/*     */   
/*     */   public void setAfterECO(String afterECO) {
/* 162 */     this.afterECO = afterECO;
/*     */   }
/*     */   
/* 165 */   public String getLoggedinUser() { return this.loggedinUser; }
/*     */   
/*     */   public void setLoggedinUser(String loggedinUser) {
/* 168 */     this.loggedinUser = loggedinUser;
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\com\amicabile\openingtrainer\model\search\GameSearchCriteria.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */