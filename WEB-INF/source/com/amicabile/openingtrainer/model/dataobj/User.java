package com.amicabile.openingtrainer.model.dataobj;

import com.amicabile.openingtrainer.config.ShowBoardRule;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class User implements Serializable {

   private static int MAX_SIZE = 10;
   private Integer id;
   private int maxgames;
   private boolean showBigBoard;
   private String username;
   private String email;
   private String password;
   private boolean showBeforeBranch;
   private boolean showBeforeImportantMove;
   private boolean showBeforeComment;


   public User(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = password;
   }

   public User() {}

   public User(String username) {
      this.username = username;
   }

   public Integer getId() {
      return this.id;
   }

   protected void setId(Integer id) {
      this.id = id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public ShowBoardRule getShowBoardRule() {
      ShowBoardRule showBoardRule = new ShowBoardRule();
      showBoardRule.setShowBeforeBranch(this.isShowBeforeBranch());
      showBoardRule.setShowBeforeComment(this.isShowBeforeComment());
      showBoardRule.setShowBeforeImportantMove(this.isShowBeforeImportantMove());
      return showBoardRule;
   }

   public void setShowBoardRule(ShowBoardRule showBoardRule) {
      this.setShowBeforeBranch(showBoardRule.isShowBeforeBranch());
      this.setShowBeforeComment(showBoardRule.isShowBeforeComment());
      this.setShowBeforeImportantMove(showBoardRule.isShowBeforeImportantMove());
   }

   public boolean isShowBeforeComment() {
      return this.showBeforeComment;
   }

   public void setShowBeforeComment(boolean showBeforeComment) {
      this.showBeforeComment = showBeforeComment;
   }

   public boolean isShowBeforeBranch() {
      return this.showBeforeBranch;
   }

   public void setShowBeforeBranch(boolean showBeforeBranch) {
      this.showBeforeBranch = showBeforeBranch;
   }

   public boolean isShowBeforeImportantMove() {
      return this.showBeforeImportantMove;
   }

   public void setShowBeforeImportantMove(boolean showBeforeImportantMove) {
      this.showBeforeImportantMove = showBeforeImportantMove;
   }

   public String toString() {
      return (new ToStringBuilder(this)).append("id", (Object)this.getId()).append("username", (Object)this.getUsername()).append("password", (Object)this.getPassword()).append("showBeforeComment", this.isShowBeforeComment()).append("showBeforeBranch", this.isShowBeforeBranch()).append("showBeforeImportantMove", this.isShowBeforeImportantMove()).toString();
   }

   public boolean equals(Object other) {
      if(!(other instanceof User)) {
         return false;
      } else {
         User castOther = (User)other;
         return (new EqualsBuilder()).append((Object)this.getId(), (Object)castOther.getId()).isEquals();
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder()).append((Object)this.getId()).toHashCode();
   }

   public int getMaxgames() {
      return Math.max(this.maxgames, MAX_SIZE);
   }

   public void setMaxgames(int maxgames) {
      this.maxgames = maxgames;
   }

   public boolean isShowBigBoard() {
      return this.showBigBoard;
   }

   public void setShowBigBoard(boolean showBigBoard) {
      this.showBigBoard = showBigBoard;
   }
}
