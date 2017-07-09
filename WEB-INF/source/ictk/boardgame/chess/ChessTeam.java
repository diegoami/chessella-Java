package ictk.boardgame.chess;

import ictk.boardgame.Team;
import ictk.boardgame.chess.ChessPlayer;
import java.util.ArrayList;
import java.util.List;

public class ChessTeam extends ChessPlayer implements Team {

   List players = new ArrayList(2);


   public ChessTeam() {}

   public ChessTeam(String n) {
      super(n);
   }

   public String getTeamName() {
      return this.lastname;
   }

   public void setTeamName(String name) {
      this.lastname = name;
   }

   public List getPlayers() {
      return this.players;
   }

   public int getRating() {
      if(this.rating > 0) {
         return this.rating;
      } else {
         int rating = 0;

         for(int i = 0; i < this.players.size(); ++i) {
            rating += ((ChessPlayer)this.players.get(i)).getRating();
         }

         if(this.players.size() > 0) {
            rating /= this.players.size();
         }

         return rating;
      }
   }

   public String toString() {
      String str = this.getName();
      return str;
   }
}
