/*     */ package ictk.boardgame;
/*     */ 
/*     */ import ictk.boardgame.io.Annotation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Move
/*     */ {
/*  66 */   protected boolean executed = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   protected boolean verified = false;
/*     */   
/*     */ 
/*     */   protected History history;
/*     */   
/*     */ 
/*     */   protected ContinuationList continuation;
/*     */   
/*     */ 
/*     */   protected Move prev;
/*     */   
/*     */   protected Result result;
/*     */   
/*     */   protected Annotation prenotation;
/*     */   
/*     */   protected Annotation annotation;
/*     */   
/*     */ 
/*     */   public abstract boolean isNullMove();
/*     */   
/*     */ 
/*     */   public Move()
/*     */   {
/*  94 */     this.continuation = new ContinuationArrayList(this);
/*     */   }
/*     */   
/*     */ 
/*  98 */   public History getHistory() { return this.history; }
/*     */   
/*     */   public abstract Board getBoard();
/*     */   
/* 102 */   public void setHistory(History h) { this.history = h; }
/*     */   
/*     */ 
/*     */   protected abstract void setBoard(Board paramBoard);
/*     */   
/*     */   protected void setPrev(Move m)
/*     */   {
/* 109 */     this.prev = m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContinuationList getContinuationList()
/*     */   {
/* 117 */     return this.continuation;
/*     */   }
/*     */   
/*     */ 
/*     */   public Move getPrev()
/*     */   {
/* 123 */     return this.prev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 130 */     return !this.continuation.hasMainLine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Move getNext()
/*     */   {
/* 141 */     return this.continuation.getMainLine();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addNext(Move move)
/*     */   {
/* 149 */     this.continuation.add(move);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 158 */     if (isExecuted())
/* 159 */       throw new IllegalStateException(
/* 160 */         "Cannot dispose of an executed move.  Rewind history first.");
/* 161 */     this.history = null;
/* 162 */     this.prev = null;
/* 163 */     this.continuation.dispose();
/* 164 */     this.continuation = null;
/* 165 */     this.history = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean isLegal();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void execute()
/*     */     throws IllegalMoveException, OutOfTurnException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void unexecute();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isExecuted()
/*     */   {
/* 196 */     return this.executed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isVerified()
/*     */   {
/* 205 */     return this.verified;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Annotation getPrenotation()
/*     */   {
/* 216 */     return this.prenotation;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPrenotation(Annotation anno)
/*     */   {
/* 222 */     this.prenotation = anno;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Annotation getAnnotation()
/*     */   {
/* 232 */     return this.annotation;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAnnotation(Annotation anno)
/*     */   {
/* 238 */     this.annotation = anno;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Result getResult()
/*     */   {
/* 247 */     return this.result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setResult(Result res)
/*     */   {
/* 255 */     this.result = res;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract String toString();
/*     */   
/*     */ 
/*     */   public String dump()
/*     */   {
/* 265 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 267 */     sb.append("prev: ").append(this.prev.toString())
/* 268 */       .append("\n")
/* 269 */       .append("continuation#:").append(this.continuation.dump())
/* 270 */       .append("\n")
/* 271 */       .append("prenotation: ").append(this.prenotation)
/* 272 */       .append("\n")
/* 273 */       .append("annotation: ").append(this.annotation)
/* 274 */       .append("\n")
/* 275 */       .append("result: ").append(this.result);
/*     */     
/* 277 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\Move.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */