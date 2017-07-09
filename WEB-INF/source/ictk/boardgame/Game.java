package ictk.boardgame;

import ictk.boardgame.Board;
import ictk.boardgame.GameInfo;
import ictk.boardgame.History;
import ictk.boardgame.Result;

public interface Game {

   int getNumberOfPlayers();

   GameInfo getGameInfo();

   void setGameInfo(GameInfo var1);

   History getHistory();

   void setHistory(History var1);

   Result getCurrentResult();

   Result getResult();

   void setResult(Result var1);

   Board[] getBoards();

   Board getBoard();

   int[] getPlayersToMove();
}
