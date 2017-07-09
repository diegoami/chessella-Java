package ictk.boardgame.chess.net.ics;


public interface ICSResponse {

   boolean isResponse();

   void setResponseKey(int var1);

   int getResponseKey();

   void setResponseKey(String var1);

   String getResponseKeyString();
}
