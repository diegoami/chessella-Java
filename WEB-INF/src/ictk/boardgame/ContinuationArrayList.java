/*     */ package ictk.boardgame;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public class ContinuationArrayList
/*     */   implements ContinuationList
/*     */ {
/*     */   protected Move departureMove;
/*     */   protected Move[] branches;
/*     */   
/*     */   public ContinuationList subList(int sizearg)
/*     */   {
/*  46 */     ContinuationArrayList list = new ContinuationArrayList();
/*  47 */     int n = Math.min(sizearg, size());
/*  48 */     list.branches = new Move[n];
/*  49 */     for (int i = 0; i < n; i++) {
/*  50 */       list.branches[i] = this.branches[i];
/*     */     }
/*  52 */     if (n < size()) {
/*  53 */       list.departureMove = this.branches[n];
/*     */     } else
/*  55 */       list.departureMove = this.departureMove;
/*  56 */     return list;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/*  61 */     if (o == this) return true;
/*  62 */     if ((o == null) || (o.getClass() != getClass()))
/*  63 */       return false;
/*  64 */     ContinuationArrayList otherList = (ContinuationArrayList)o;
/*     */     
/*  66 */     if ((this.branches == null) && (otherList.branches != null)) return false;
/*  67 */     if ((otherList.branches == null) && (this.branches != null)) { return false;
/*     */     }
/*  69 */     if ((this.departureMove == null) && (otherList.departureMove != null)) return false;
/*  70 */     if ((otherList.departureMove == null) && (this.departureMove != null)) { return false;
/*     */     }
/*     */     
/*  73 */     boolean branchesEqual = Arrays.equals(this.branches, otherList.branches);
/*     */     
/*     */ 
/*  76 */     boolean departureEqual = this.departureMove == null ? 
/*  77 */       false : otherList.departureMove == null ? true : 
/*  78 */       this.departureMove.equals(otherList.departureMove);
/*  79 */     return (branchesEqual) && (departureEqual);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  83 */     int hash = 7;
/*  84 */     hash = 31 * hash + (this.branches == null ? 0 : this.branches.hashCode());
/*  85 */     hash = 31 * hash + (this.departureMove == null ? 0 : this.departureMove.hashCode());
/*  86 */     return hash;
/*     */   }
/*     */   
/*     */   public ContinuationArrayList(Move m)
/*     */   {
/*  91 */     this.branches = new Move[1];
/*  92 */     setDepartureMove(m);
/*     */   }
/*     */   
/*     */   public ContinuationArrayList() {
/*  96 */     this(null);
/*     */   }
/*     */   
/*     */   protected void setDepartureMove(Move m) {
/* 100 */     this.departureMove = m;
/*     */   }
/*     */   
/* 103 */   public Move getDepartureMove() { return this.departureMove; }
/*     */   
/*     */   public boolean isTerminal()
/*     */   {
/* 107 */     return (getMainLine() == null) && (sizeOfVariations() == 0);
/*     */   }
/*     */   
/*     */   public boolean setMainLineTerminal()
/*     */   {
/* 112 */     if ((this.branches == null) || (this.branches[0] == null)) {
/* 113 */       return false;
/*     */     }
/* 115 */     add(null, true);
/* 116 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean exists(int variation)
/*     */   {
/*     */     try
/*     */     {
/* 124 */       if ((this.branches != null) && (this.branches[variation] != null)) {
/* 125 */         return true;
/*     */       }
/* 127 */       return false;
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException e) {}
/*     */     
/* 131 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean exists(Move move)
/*     */   {
/* 137 */     return getIndex(move) != -1;
/*     */   }
/*     */   
/*     */   public boolean hasMainLine() {
/* 141 */     return exists(0);
/*     */   }
/*     */   
/*     */   public Move getMainLine()
/*     */   {
/* 146 */     if (this.branches == null) return null;
/* 147 */     return this.branches[0];
/*     */   }
/*     */   
/*     */   public boolean hasVariations() {
/* 151 */     return sizeOfVariations() > 0;
/*     */   }
/*     */   
/*     */   public Move get(int i) {
/* 155 */     if ((i == 0) && (this.branches == null)) return null;
/* 156 */     return this.branches[i];
/*     */   }
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
/*     */   public int size()
/*     */   {
/* 183 */     int len = 0;
/*     */     
/* 185 */     if (this.branches == null) { return 0;
/*     */     }
/* 187 */     len = this.branches.length;
/* 188 */     if ((len == 1) && (this.branches[0] == null)) {
/* 189 */       len = 0;
/*     */     }
/*     */     
/* 192 */     return len;
/*     */   }
/*     */   
/*     */   public int sizeOfVariations()
/*     */   {
/* 197 */     if (this.branches == null) {
/* 198 */       return 0;
/*     */     }
/* 200 */     return this.branches.length - 1;
/*     */   }
/*     */   
/*     */   public void add(Move m, boolean isMain)
/*     */   {
/* 205 */     Move[] tmp = (Move[])null;
/*     */     
/* 207 */     assert ((this.departureMove == null) || 
/* 208 */       (this.departureMove.getHistory().getCurrentMove() == this.departureMove)) : 
/* 209 */       "You cannot add a continuation to moves other than the last move played on the board (History.getCurrentMove())";
/*     */     
/*     */ 
/*     */ 
/* 213 */     if (this.branches == null)
/*     */     {
/*     */ 
/* 216 */       if (isMain) {
/* 217 */         this.branches = new Move[1];
/* 218 */         this.branches[0] = m;
/*     */       }
/*     */       else
/*     */       {
/* 222 */         this.branches = new Move[2];
/* 223 */         this.branches[1] = m;
/*     */       }
/*     */     }
/* 226 */     else if ((isMain) && (this.branches[0] == null)) {
/* 227 */       this.branches[0] = m;
/*     */     }
/*     */     else
/*     */     {
/* 231 */       tmp = new Move[this.branches.length + 1];
/*     */       
/*     */ 
/* 234 */       if (isMain) {
/* 235 */         tmp[0] = m;
/* 236 */         System.arraycopy(this.branches, 0, tmp, 1, this.branches.length);
/*     */       }
/*     */       else
/*     */       {
/* 240 */         System.arraycopy(this.branches, 0, tmp, 0, this.branches.length);
/* 241 */         tmp[(tmp.length - 1)] = m;
/*     */       }
/* 243 */       this.branches = tmp;
/*     */     }
/*     */     
/*     */ 
/* 247 */     if (m != null) {
/* 248 */       m.prev = this.departureMove;
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(Move m) {
/* 253 */     Move[] tmp = (Move[])null;
/* 254 */     assert (this.departureMove.getHistory().getCurrentMove() == this.departureMove) : 
/* 255 */       "You cannot add a continuation to moves other than the last move played on the board (History.getCurrentMove())";
/*     */     
/* 257 */     if (this.branches == null) {
/* 258 */       this.branches = new Move[1];
/* 259 */       this.branches[0] = m;
/*     */     }
/* 261 */     else if ((this.branches.length == 1) && (this.branches[0] == null)) {
/* 262 */       this.branches[0] = m;
/*     */     } else {
/* 264 */       tmp = new Move[this.branches.length + 1];
/* 265 */       System.arraycopy(this.branches, 0, tmp, 0, this.branches.length);
/* 266 */       tmp[(tmp.length - 1)] = m;
/*     */     }
/*     */     
/*     */ 
/* 270 */     m.prev = this.departureMove;
/*     */   }
/*     */   
/*     */   public int getIndex(Move m)
/*     */   {
/* 275 */     int i = 0;
/*     */     
/* 277 */     if (this.branches == null) {
/* 278 */       return -1;
/*     */     }
/* 280 */     for (i = 0; i < this.branches.length; i++) {
/* 281 */       if (this.branches[i] == m)
/*     */         break;
/*     */     }
/* 284 */     if (i < this.branches.length) {
/* 285 */       return i;
/*     */     }
/* 287 */     return -1;
/*     */   }
/*     */   
/*     */   public Move[] find(Move m)
/*     */   {
/* 292 */     Move[] moves = (Move[])null;
/* 293 */     int[] idx = findIndex(m);
/*     */     
/* 295 */     if (idx != null) {
/* 296 */       moves = new Move[idx.length];
/* 297 */       for (int i = 0; i < idx.length; i++)
/* 298 */         moves[i] = this.branches[idx[i]];
/*     */     }
/* 300 */     return moves;
/*     */   }
/*     */   
/*     */   public int[] findIndex(Move m)
/*     */   {
/* 305 */     int i = 0;
/* 306 */     int count = 0;
/* 307 */     int[] rvalue = (int[])null;
/* 308 */     int[] tmp = (int[])null;
/*     */     
/* 310 */     if (this.branches == null) {
/* 311 */       return null;
/*     */     }
/* 313 */     tmp = new int[this.branches.length];
/*     */     
/* 315 */     for (i = 0; i < this.branches.length; i++) {
/* 316 */       if ((this.branches[i] != null) && (this.branches[i].equals(m))) {
/* 317 */         tmp[(count++)] = i;
/*     */       }
/*     */     }
/* 320 */     if (count == 0)
/* 321 */       return null;
/* 322 */     if (count == this.branches.length) {
/* 323 */       return tmp;
/*     */     }
/* 325 */     rvalue = new int[count];
/* 326 */     System.arraycopy(tmp, 0, rvalue, 0, rvalue.length);
/* 327 */     return rvalue;
/*     */   }
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
/*     */   protected boolean compressVariations()
/*     */   {
/* 343 */     boolean compressed = false;
/* 344 */     Move[] compBranches = (Move[])null;
/* 345 */     int count = 0;
/*     */     
/* 347 */     for (int i = 1; i < this.branches.length; i++) {
/* 348 */       if (this.branches[i] != null) { count++;
/*     */       } else
/* 350 */         compressed = true;
/*     */     }
/* 352 */     if ((compressed) && (count > 0)) {
/* 353 */       compBranches = new Move[count + 1];
/*     */       
/* 355 */       count = 0;
/* 356 */       for (int i = 0; i < this.branches.length; i++) {
/* 357 */         if ((i < 1) || (this.branches[i] != null))
/* 358 */           compBranches[(count++)] = this.branches[i];
/*     */       }
/* 360 */       this.branches = compBranches;
/*     */     }
/*     */     
/* 363 */     return (compressed) && (count > 0);
/*     */   }
/*     */   
/*     */   public void remove(int i)
/*     */   {
/* 368 */     if (this.branches == null) {
/* 369 */       throw new ArrayIndexOutOfBoundsException("no moves to remove");
/*     */     }
/* 371 */     if (i == -1) {
/* 372 */       throw new ArrayIndexOutOfBoundsException(
/* 373 */         "move does not exist in continuation list");
/*     */     }
/* 375 */     if (this.branches[i] != null)
/*     */     {
/* 377 */       if (this.branches[i].isExecuted()) {
/* 378 */         this.departureMove.getHistory().goTo(this.departureMove);
/*     */       }
/* 380 */       this.branches[i].dispose();
/* 381 */       this.branches[i] = null;
/*     */       
/*     */ 
/* 384 */       compressVariations();
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(Move m) {
/* 389 */     remove(getIndex(m));
/*     */   }
/*     */   
/*     */   public void removeAll()
/*     */   {
/* 394 */     if (this.branches != null)
/*     */     {
/* 396 */       for (int i = 0; i < this.branches.length; i++)
/*     */       {
/*     */ 
/* 399 */         if (this.branches[i].isExecuted()) {
/* 400 */           this.departureMove.getHistory().goTo(this.departureMove);
/*     */         }
/* 402 */         this.branches[i].dispose();
/* 403 */         this.branches[i] = null;
/*     */       } }
/* 405 */     this.branches = null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeAllVariations()
/*     */   {
/* 411 */     if ((this.branches != null) && (sizeOfVariations() > 0)) {
/* 412 */       for (int i = 1; i < this.branches.length; i++) {
/* 413 */         if (this.branches[i] != null)
/*     */         {
/* 415 */           if (this.branches[i].isExecuted()) {
/* 416 */             this.departureMove.getHistory().goTo(this.departureMove);
/*     */           }
/* 418 */           this.branches[i].dispose();
/* 419 */           this.branches[i] = null;
/*     */         }
/*     */       }
/* 422 */       compressVariations();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 430 */     this.departureMove = null;
/* 431 */     removeAll();
/* 432 */     this.branches = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int promote(Move move, int num)
/*     */   {
/* 439 */     if (num < 0) {
/* 440 */       throw new IllegalArgumentException(
/* 441 */         "cannot perform negative promotion");
/*     */     }
/* 443 */     Move tmp = null;
/* 444 */     int newIndex = 0;
/* 445 */     int oldIndex = getIndex(move);
/*     */     
/* 447 */     if (oldIndex == -1) {
/* 448 */       throw new NullPointerException("Move is not a current variation");
/*     */     }
/* 450 */     move = this.branches[oldIndex];
/*     */     
/* 452 */     if (num != 0) {
/* 453 */       newIndex = oldIndex - num;
/*     */     }
/* 455 */     if (newIndex < 0) {
/* 456 */       throw new ArrayIndexOutOfBoundsException(
/* 457 */         "Move cannot be promoted beyond the main line");
/*     */     }
/*     */     
/* 460 */     if ((newIndex == 0) && (this.branches[0] == null))
/*     */     {
/* 462 */       this.branches[oldIndex] = null;
/* 463 */       this.branches[0] = move;
/* 464 */       compressVariations();
/*     */     }
/*     */     else
/*     */     {
/* 468 */       this.branches[oldIndex] = null;
/* 469 */       for (int i = newIndex; i <= oldIndex; i++) {
/* 470 */         tmp = this.branches[i];
/* 471 */         this.branches[i] = move;
/* 472 */         move = tmp;
/*     */       }
/* 474 */       compressVariations();
/*     */     }
/*     */     
/* 477 */     return newIndex;
/*     */   }
/*     */   
/*     */   public int demote(Move move, int num)
/*     */   {
/* 482 */     if (num < 0) {
/* 483 */       throw new IllegalArgumentException(
/* 484 */         "cannot perform negative demotion");
/*     */     }
/* 486 */     Move tmp = null;
/* 487 */     int newIndex = 0;
/* 488 */     int oldIndex = getIndex(move);
/*     */     
/* 490 */     if (oldIndex == -1) {
/* 491 */       throw new NullPointerException("Move is not a current variation");
/*     */     }
/* 493 */     move = this.branches[oldIndex];
/*     */     
/* 495 */     if (num != 0) {
/* 496 */       newIndex = oldIndex + num;
/*     */     } else {
/* 498 */       newIndex = this.branches.length - 1;
/*     */     }
/* 500 */     if (newIndex >= this.branches.length) {
/* 501 */       throw new ArrayIndexOutOfBoundsException(
/* 502 */         "Move cannot be demoted beyond the end of the list");
/*     */     }
/* 504 */     for (int i = oldIndex; i < newIndex; i++) {
/* 505 */       this.branches[i] = this.branches[(i + 1)];
/*     */     }
/* 507 */     this.branches[newIndex] = move;
/*     */     
/* 509 */     return newIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String dump()
/*     */   {
/* 516 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 518 */     sb.append("branches(")
/* 519 */       .append(this.branches.length).append("): \n");
/* 520 */     for (int i = 0; i < this.branches.length; i++) {
/* 521 */       sb.append("   branches[").append(i).append("]:");
/* 522 */       if (this.branches[i] == null) {
/* 523 */         sb.append("null");
/*     */       } else {
/* 525 */         sb.append(this.branches[i].toString());
/*     */       }
/* 527 */       sb.append("\n");
/*     */     }
/* 529 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\ContinuationArrayList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */