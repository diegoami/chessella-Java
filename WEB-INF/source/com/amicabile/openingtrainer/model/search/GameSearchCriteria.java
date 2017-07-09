package com.amicabile.openingtrainer.model.search;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class GameSearchCriteria {

   private static Logger log = Logger.getLogger(GameSearchCriteria.class.getName());
   private String white = "";
   private String black = "";
   private boolean ignoreColor;
   private Date beforeDate;
   private Date afterDate;
   private String submitter;
   private String loggedinUser;
   private String result;
   private String resultKey;
   private String event;
   private String site;
   private String tags;
   private String beforeECO;
   private String afterECO;
   private static Map resultMap = new LinkedHashMap();
   private String searchValid = null;


   static {
      resultMap.put("1", "");
      resultMap.put("2", "1-0");
      resultMap.put("3", "1/2-1/2");
      resultMap.put("4", "0-1");
      resultMap.put("5", "*");
   }

   public String getTags() {
      return this.tags;
   }

   public void setTags(String tags) {
      this.tags = tags;
   }

   public boolean isEnough() {
      return true;
   }

   private boolean longEnough(String arg) {
      boolean result = !StringUtils.isEmpty(arg) && arg.length() > 2;
      if(result) {
         log.info(arg + " is long enough");
      }

      return result;
   }

   public Map getResultMap() {
      return resultMap;
   }

   public String getSearchValid() {
      return this.searchValid;
   }

   public void setSearchValid(String searchValid) {
      this.searchValid = searchValid;
   }

   public Date getAfterDate() {
      return this.afterDate;
   }

   public void setAfterDate(Date afterDate) {
      this.afterDate = afterDate;
   }

   public Date getBeforeDate() {
      return this.beforeDate;
   }

   public void setBeforeDate(Date beforeDate) {
      this.beforeDate = beforeDate;
   }

   public String getBeforeECO() {
      return this.beforeECO;
   }

   public void setBeforeECO(String beforeECO) {
      this.beforeECO = beforeECO;
   }

   public String getBlack() {
      return this.black;
   }

   public void setBlack(String black) {
      this.black = black;
   }

   public boolean isIgnoreColor() {
      return this.ignoreColor;
   }

   public void setIgnoreColor(boolean ignoreColor) {
      this.ignoreColor = ignoreColor;
   }

   public String getSubmitter() {
      return this.submitter;
   }

   public void setSubmitter(String submitter) {
      this.submitter = submitter;
   }

   public String getWhite() {
      return this.white;
   }

   public void setWhite(String white) {
      this.white = white;
   }

   public String getResult() {
      return (String)resultMap.get(this.resultKey);
   }

   public String getResultKey() {
      return this.resultKey;
   }

   public void setResultKey(String resultKey) {
      this.resultKey = resultKey;
   }

   public String getEvent() {
      return this.event;
   }

   public void setEvent(String event) {
      this.event = event;
   }

   public String getSite() {
      return this.site;
   }

   public void setSite(String site) {
      this.site = site;
   }

   public String getAfterECO() {
      return this.afterECO;
   }

   public void setAfterECO(String afterECO) {
      this.afterECO = afterECO;
   }

   public String getLoggedinUser() {
      return this.loggedinUser;
   }

   public void setLoggedinUser(String loggedinUser) {
      this.loggedinUser = loggedinUser;
   }
}
