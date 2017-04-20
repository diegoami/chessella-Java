package ictk.boardgame;

public abstract interface ContinuationList
{
  public abstract ContinuationList subList(int paramInt);
  
  public abstract Move getDepartureMove();
  
  public abstract boolean isTerminal();
  
  public abstract boolean setMainLineTerminal();
  
  public abstract boolean exists(int paramInt);
  
  public abstract boolean exists(Move paramMove);
  
  public abstract boolean hasMainLine();
  
  public abstract Move getMainLine();
  
  public abstract boolean hasVariations();
  
  public abstract Move get(int paramInt);
  
  public abstract int size();
  
  public abstract int sizeOfVariations();
  
  public abstract void add(Move paramMove, boolean paramBoolean);
  
  public abstract void add(Move paramMove);
  
  public abstract int getIndex(Move paramMove);
  
  public abstract Move[] find(Move paramMove);
  
  public abstract int[] findIndex(Move paramMove);
  
  public abstract void remove(int paramInt);
  
  public abstract void remove(Move paramMove);
  
  public abstract void removeAll();
  
  public abstract void removeAllVariations();
  
  public abstract void dispose();
  
  public abstract int promote(Move paramMove, int paramInt);
  
  public abstract int demote(Move paramMove, int paramInt);
  
  public abstract String dump();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\ContinuationList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */