/*     */ package ictk.boardgame.chess.net.ics.event;
/*     */ 
/*     */ import ictk.boardgame.chess.net.ics.ICSRating;
/*     */ import ictk.boardgame.chess.net.ics.ICSVariant;
/*     */ import ictk.boardgame.chess.net.ics.fics.event.FICSChallengeParser;
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
/*     */ public class ICSChallengeEvent
/*     */   extends ICSEvent
/*     */ {
/*     */   protected static final int CHALLENGE_EVENT = 11;
/*     */   protected String challenger;
/*     */   protected ICSRating challengerRating;
/*     */   protected boolean isColorSpecified;
/*     */   protected boolean isWhite;
/*     */   protected String challenged;
/*     */   protected ICSRating challengedRating;
/*     */   protected boolean rated;
/*     */   protected ICSVariant variant;
/*     */   protected int time;
/*     */   protected int incr;
/*     */   protected boolean computer;
/*     */   protected boolean abuser;
/*     */   
/*     */   public ICSChallengeEvent()
/*     */   {
/*  69 */     super(11);
/*     */   }
/*     */   
/*     */   public String getChallenger()
/*     */   {
/*  74 */     return this.challenger;
/*     */   }
/*     */   
/*     */   public ICSRating getChallengerRating() {
/*  78 */     return this.challengerRating;
/*     */   }
/*     */   
/*     */   public boolean isColorSpecified() {
/*  82 */     return this.isColorSpecified;
/*     */   }
/*     */   
/*     */   public boolean isWhite() {
/*  86 */     return this.isWhite;
/*     */   }
/*     */   
/*     */   public String getChallenged() {
/*  90 */     return this.challenged;
/*     */   }
/*     */   
/*     */   public ICSRating getChallengedRating() {
/*  94 */     return this.challengedRating;
/*     */   }
/*     */   
/*     */   public boolean isRated() {
/*  98 */     return this.rated;
/*     */   }
/*     */   
/*     */   public ICSVariant getVariant() {
/* 102 */     return this.variant;
/*     */   }
/*     */   
/*     */   public int getInitialTime() {
/* 106 */     return this.time;
/*     */   }
/*     */   
/*     */   public int getIncrementTime() {
/* 110 */     return this.incr;
/*     */   }
/*     */   
/*     */   public boolean isComputer() {
/* 114 */     return this.computer;
/*     */   }
/*     */   
/*     */   public boolean isAbuser() {
/* 118 */     return this.abuser;
/*     */   }
/*     */   
/*     */   public void setChallenger(String challenger)
/*     */   {
/* 123 */     this.challenger = challenger;
/*     */   }
/*     */   
/*     */   public void setChallengerRating(ICSRating challengerRating) {
/* 127 */     this.challengerRating = challengerRating;
/*     */   }
/*     */   
/*     */   public void setColorSpecified(boolean isColorSpecified) {
/* 131 */     this.isColorSpecified = isColorSpecified;
/*     */   }
/*     */   
/*     */   public void setWhite(boolean isWhite) {
/* 135 */     this.isWhite = isWhite;
/*     */   }
/*     */   
/*     */   public void setChallenged(String challenged) {
/* 139 */     this.challenged = challenged;
/*     */   }
/*     */   
/*     */   public void setChallengedRating(ICSRating challengedRating) {
/* 143 */     this.challengedRating = challengedRating;
/*     */   }
/*     */   
/*     */   public void setRated(boolean rated) {
/* 147 */     this.rated = rated;
/*     */   }
/*     */   
/*     */   public void setVariant(ICSVariant variant) {
/* 151 */     this.variant = variant;
/*     */   }
/*     */   
/*     */   public void setInitialTime(int time) {
/* 155 */     this.time = time;
/*     */   }
/*     */   
/*     */   public void setIncrementTime(int incr) {
/* 159 */     this.incr = incr;
/*     */   }
/*     */   
/*     */   public void setComputer(boolean computer) {
/* 163 */     this.computer = computer;
/*     */   }
/*     */   
/*     */   public void setAbuser(boolean abuser) {
/* 167 */     this.abuser = abuser;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 173 */     return FICSChallengeParser.getInstance().toNative(this);
/*     */   }
/*     */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\event\ICSChallengeEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */