package ictk.boardgame.io;

public abstract interface Annotation
{
  public abstract String getComment();
  
  public abstract void setComment(String paramString);
  
  public abstract void appendComment(String paramString);
  
  public abstract String dump();
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\io\Annotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */