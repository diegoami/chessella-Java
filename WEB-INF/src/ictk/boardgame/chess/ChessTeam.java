/*    */ package ictk.boardgame.chess;
/*    */ 
/*    */ import ictk.boardgame.Team;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChessTeam
/*    */   extends ChessPlayer
/*    */   implements Team
/*    */ {
/*    */   List players;
/*    */   
/*    */   public ChessTeam()
/*    */   {
/* 42 */     this.players = new ArrayList(2);
/*    */   }
/*    */   
/*    */   public ChessTeam(String n) {
/* 46 */     super(n);
/* 47 */     this.players = new ArrayList(2);
/*    */   }
/*    */   
/*    */   public String getTeamName() {
/* 51 */     return this.lastname;
/*    */   }
/*    */   
/*    */   public void setTeamName(String name) {
/* 55 */     this.lastname = name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List getPlayers()
/*    */   {
/* 65 */     return this.players;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getRating()
/*    */   {
/* 73 */     if (this.rating > 0) { return this.rating;
/*    */     }
/* 75 */     int rating = 0;
/* 76 */     for (int i = 0; i < this.players.size(); i++)
/* 77 */       rating += ((ChessPlayer)this.players.get(i)).getRating();
/* 78 */     if (this.players.size() > 0)
/* 79 */       rating /= this.players.size();
/* 80 */     return rating;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 88 */     String str = getName();
/* 89 */     return str;
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\ChessTeam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */