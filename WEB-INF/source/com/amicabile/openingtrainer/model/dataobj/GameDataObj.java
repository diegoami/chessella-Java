package com.amicabile.openingtrainer.model.dataobj;

import com.amicabile.openingtrainer.model.dataobj.User;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GameDataObj implements Serializable {

   private boolean publicgame;
   private boolean deleted;
   private String annotator;
   private Long id;
   private int boardnumber;
   private String white;
   private String black;
   private String result;
   private Date date;
   private String event;
   private String site;
   private String round;
   private String eco;
   private String tags = "";
   private String pgnstring;
   private User user;


   public GameDataObj(int boardnumber, String white, String black, String result, Date date, String event, String site, String round, String eco, String tags, String pgnstring, User user) {
      this.boardnumber = boardnumber;
      this.white = white;
      this.black = black;
      this.result = result;
      this.date = date;
      this.event = event;
      this.site = site;
      this.round = round;
      this.eco = eco;
      this.tags = tags;
      this.pgnstring = pgnstring;
      this.user = user;
   }

   public GameDataObj() {}

   public GameDataObj(String pgnstring) {
      this.pgnstring = pgnstring;
   }

   public Long getId() {
      return this.id;
   }

   void setId(Long id) {
      this.id = id;
   }

   public int getBoardnumber() {
      return this.boardnumber;
   }

   public void setBoardnumber(int boardnumber) {
      this.boardnumber = boardnumber;
   }

   public String getWhite() {
      return this.white;
   }

   public void setWhite(String white) {
      this.white = white;
   }

   public String getBlack() {
      return this.black;
   }

   public void setBlack(String black) {
      this.black = black;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public Date getDate() {
      return this.date;
   }

   public void setDate(Date date) {
      this.date = date;
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

   public String getRound() {
      return this.round;
   }

   public void setRound(String round) {
      this.round = round;
   }

   public String getEco() {
      return this.eco;
   }

   public void setEco(String eco) {
      this.eco = eco;
   }

   public String getTags() {
      return this.tags != null?this.tags:"";
   }

   public void setTags(String tags) {
      this.tags = tags;
   }

   public String getPgnstring() {
      return this.pgnstring;
   }

   public String getEncodedPgnstring() {
      return StringEscapeUtils.escapeHtml(this.pgnstring);
   }

   public void setPgnstring(String pgnstring) {
      this.pgnstring = pgnstring;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getSubmitter() {
      return this.user != null?this.user.getUsername():"";
   }

   public String toString() {
      return (new ToStringBuilder(this)).append("id", (Object)this.getId()).toString();
   }

   public boolean equals(Object other) {
      if(!(other instanceof GameDataObj)) {
         return false;
      } else {
         GameDataObj castOther = (GameDataObj)other;
         return (new EqualsBuilder()).append((Object)this.getId(), (Object)castOther.getId()).isEquals();
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder()).append((Object)this.getId()).toHashCode();
   }

   public String getAnnotator() {
      return this.annotator;
   }

   public void setAnnotator(String annotator) {
      this.annotator = annotator;
   }

   public boolean isPublicgame() {
      return this.publicgame;
   }

   public void setPublicgame(boolean publicgame) {
      this.publicgame = publicgame;
   }

   public boolean isDeleted() {
      return this.deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }
}
