package ictk.boardgame.chess.net.ics.fics;

public abstract interface FICSBlockMode
{
  public static final int BLOCK_START = 21;
  public static final int BLOCK_SEPARATOR = 22;
  public static final int BLOCK_END = 23;
  public static final int BLOCK_POSE_START = 24;
  public static final int BLOCK_POSE_END = 25;
  public static final int BLK_NULL = 0;
  public static final int BLK_GAME_MOVE = 1;
  public static final int BLK_ABORT = 10;
  public static final int BLK_ACCEPT = 11;
  public static final int BLK_ADDLIST = 12;
  public static final int BLK_ADJOURN = 13;
  public static final int BLK_ALLOBSERVERS = 14;
  public static final int BLK_ASSESS = 15;
  public static final int BLK_BACKWARD = 16;
  public static final int BLK_BELL = 17;
  public static final int BLK_BEST = 18;
  public static final int BLK_BNAME = 19;
  public static final int BLK_BOARDS = 20;
  public static final int BLK_BSETUP = 21;
  public static final int BLK_BUGWHO = 22;
  public static final int BLK_CBEST = 23;
  public static final int BLK_CLEARMESSAGES = 24;
  public static final int BLK_CLRSQUARE = 25;
  public static final int BLK_CONVERT_BCF = 26;
  public static final int BLK_CONVERT_ELO = 27;
  public static final int BLK_CONVERT_USCF = 28;
  public static final int BLK_COPYGAME = 29;
  public static final int BLK_CRANK = 30;
  public static final int BLK_CSHOUT = 31;
  public static final int BLK_DATE = 32;
  public static final int BLK_DECLINE = 33;
  public static final int BLK_DRAW = 34;
  public static final int BLK_ECO = 35;
  public static final int BLK_EXAMINE = 36;
  public static final int BLK_FINGER = 37;
  public static final int BLK_FLAG = 38;
  public static final int BLK_FLIP = 39;
  public static final int BLK_FMESSAGE = 40;
  public static final int BLK_FOLLOW = 41;
  public static final int BLK_FORWARD = 42;
  public static final int BLK_GAMES = 43;
  public static final int BLK_GETGI = 44;
  public static final int BLK_GETPI = 45;
  public static final int BLK_GINFO = 46;
  public static final int BLK_GOBOARD = 47;
  public static final int BLK_HANDLES = 48;
  public static final int BLK_HBEST = 49;
  public static final int BLK_HELP = 50;
  public static final int BLK_HISTORY = 51;
  public static final int BLK_HRANK = 52;
  public static final int BLK_INCHANNEL = 53;
  public static final int BLK_INDEX = 54;
  public static final int BLK_INFO = 55;
  public static final int BLK_ISET = 56;
  public static final int BLK_IT = 57;
  public static final int BLK_IVARIABLES = 58;
  public static final int BLK_JKILL = 59;
  public static final int BLK_JOURNAL = 60;
  public static final int BLK_JSAVE = 61;
  public static final int BLK_KIBITZ = 62;
  public static final int BLK_LIMITS = 63;
  public static final int BLK_LINE = 64;
  public static final int BLK_LLOGONS = 65;
  public static final int BLK_LOGONS = 66;
  public static final int BLK_MAILHELP = 67;
  public static final int BLK_MAILMESS = 68;
  public static final int BLK_MAILMOVES = 69;
  public static final int BLK_MAILOLDMOVES = 70;
  public static final int BLK_MAILSOURCE = 71;
  public static final int BLK_MAILSTORED = 72;
  public static final int BLK_MATCH = 73;
  public static final int BLK_MESSAGES = 74;
  public static final int BLK_MEXAMINE = 75;
  public static final int BLK_MORETIME = 76;
  public static final int BLK_MOVES = 77;
  public static final int BLK_NEWS = 78;
  public static final int BLK_NEXT = 79;
  public static final int BLK_OBSERVE = 80;
  public static final int BLK_OLDMOVES = 81;
  public static final int BLK_OLDSTORED = 82;
  public static final int BLK_OPEN = 83;
  public static final int BLK_PARTNER = 84;
  public static final int BLK_PASSWORD = 85;
  public static final int BLK_PAUSE = 86;
  public static final int BLK_PENDING = 87;
  public static final int BLK_PFOLLOW = 88;
  public static final int BLK_POBSERVE = 89;
  public static final int BLK_PREFRESH = 90;
  public static final int BLK_PRIMARY = 91;
  public static final int BLK_PROMOTE = 92;
  public static final int BLK_PSTAT = 93;
  public static final int BLK_PTELL = 94;
  public static final int BLK_PTIME = 95;
  public static final int BLK_QTELL = 96;
  public static final int BLK_QUIT = 97;
  public static final int BLK_RANK = 98;
  public static final int BLK_RCOPYGAME = 99;
  public static final int BLK_RFOLLOW = 100;
  public static final int BLK_REFRESH = 101;
  public static final int BLK_REMATCH = 102;
  public static final int BLK_RESIGN = 103;
  public static final int BLK_RESUME = 104;
  public static final int BLK_REVERT = 105;
  public static final int BLK_ROBSERVE = 106;
  public static final int BLK_SAY = 107;
  public static final int BLK_SERVERS = 108;
  public static final int BLK_SET = 109;
  public static final int BLK_SHOUT = 110;
  public static final int BLK_SHOWLIST = 111;
  public static final int BLK_SIMABORT = 112;
  public static final int BLK_SIMALLABORT = 113;
  public static final int BLK_SIMADJOURN = 114;
  public static final int BLK_SIMALLADJOURN = 115;
  public static final int BLK_SIMGAMES = 116;
  public static final int BLK_SIMMATCH = 117;
  public static final int BLK_SIMNEXT = 118;
  public static final int BLK_SIMOBSERVE = 119;
  public static final int BLK_SIMOPEN = 120;
  public static final int BLK_SIMPASS = 121;
  public static final int BLK_SIMPREV = 122;
  public static final int BLK_SMOVES = 123;
  public static final int BLK_SMPOSITION = 124;
  public static final int BLK_SPOSITION = 125;
  public static final int BLK_STATISTICS = 126;
  public static final int BLK_STORED = 127;
  public static final int BLK_STYLE = 128;
  public static final int BLK_SUBLIST = 129;
  public static final int BLK_SWITCH = 130;
  public static final int BLK_TAKEBACK = 131;
  public static final int BLK_TELL = 132;
  public static final int BLK_TIME = 133;
  public static final int BLK_TOMOVE = 134;
  public static final int BLK_TOURNSET = 135;
  public static final int BLK_UNALIAS = 136;
  public static final int BLK_UNEXAMINE = 137;
  public static final int BLK_UNOBSERVE = 138;
  public static final int BLK_UNPAUSE = 139;
  public static final int BLK_UPTIME = 140;
  public static final int BLK_USCF = 141;
  public static final int BLK_USTAT = 142;
  public static final int BLK_VARIABLES = 143;
  public static final int BLK_WHENSHUT = 144;
  public static final int BLK_WHISPER = 145;
  public static final int BLK_WHO = 146;
  public static final int BLK_WITHDRAW = 147;
  public static final int BLK_WNAME = 148;
  public static final int BLK_XKIBITZ = 149;
  public static final int BLK_XTELL = 150;
  public static final int BLK_XWHISPER = 151;
  public static final int BLK_ZNOTIFY = 152;
  public static final int BLK_REPLY = 153;
  public static final int BLK_SUMMON = 154;
  public static final int BLK_SEEK = 155;
  public static final int BLK_UNSEEK = 156;
  public static final int BLK_SOUGHT = 157;
  public static final int BLK_PLAY = 158;
  public static final int BLK_ALIAS = 159;
  public static final int BLK_NEWBIES = 160;
  public static final int BLK_SR = 161;
  public static final int BLK_CA = 162;
  public static final int BLK_TM = 163;
  public static final int BLK_GETGAME = 164;
  public static final int BLK_CCNEWSE = 165;
  public static final int BLK_CCNEWSF = 166;
  public static final int BLK_CCNEWSI = 167;
  public static final int BLK_CCNEWSP = 168;
  public static final int BLK_CCNEWST = 169;
  public static final int BLK_CSNEWSE = 170;
  public static final int BLK_CSNEWSF = 171;
  public static final int BLK_CSNEWSI = 172;
  public static final int BLK_CSNEWSP = 173;
  public static final int BLK_CSNEWST = 174;
  public static final int BLK_CTNEWSE = 175;
  public static final int BLK_CTNEWSF = 176;
  public static final int BLK_CTNEWSI = 177;
  public static final int BLK_CTNEWSP = 178;
  public static final int BLK_CTNEWST = 179;
  public static final int BLK_CNEWS = 180;
  public static final int BLK_SNEWS = 181;
  public static final int BLK_TNEWS = 182;
  public static final int BLK_RMATCH = 183;
  public static final int BLK_RSTAT = 184;
  public static final int BLK_CRSTAT = 185;
  public static final int BLK_HRSTAT = 186;
  public static final int BLK_GSTAT = 187;
  public static final int BLK_OLDPSTAT = 188;
  public static final int BLK_HSTAT = 189;
  public static final int BLK_PING = 190;
  public static final int BLK_EVENT = 191;
  public static final int BLK_ENDEVENT = 192;
  public static final int BLK_BOARDINFO = 193;
  public static final int BLK_AUDIOCHAT = 194;
  public static final int BLK_SHOWADMINS = 195;
  public static final int BLK_COMMIT = 196;
  public static final int BLK_TRUNCATE = 197;
  public static final int BLK_SHOWSRS = 198;
  public static final int BLK_CRASH = 1;
  public static final int BLK_ADDCOMMENT = 300;
  public static final int BLK_ADDPLAYER = 301;
  public static final int BLK_ADJUDICATE = 302;
  public static final int BLK_AHELP = 303;
  public static final int BLK_ADMIN = 304;
  public static final int BLK_ANEWS = 305;
  public static final int BLK_ANNOUNCE = 306;
  public static final int BLK_ANNUNREG = 307;
  public static final int BLK_ASETCLOCK = 308;
  public static final int BLK_ASETV = 309;
  public static final int BLK_ASETADMIN = 310;
  public static final int BLK_ASETBLITZ = 311;
  public static final int BLK_ASETBUGHOUSE = 312;
  public static final int BLK_ASETEMAIL = 313;
  public static final int BLK_ASETHANDLE = 314;
  public static final int BLK_ASETLIGHT = 315;
  public static final int BLK_ASETPASSWD = 316;
  public static final int BLK_ASETREALNAME = 317;
  public static final int BLK_ASETSTD = 318;
  public static final int BLK_ASETSUICIDE = 319;
  public static final int BLK_ASETWILD = 320;
  public static final int BLK_CHKFOPEN = 321;
  public static final int BLK_CHKIP = 322;
  public static final int BLK_CHKGAME = 323;
  public static final int BLK_CHKMALLOC = 324;
  public static final int BLK_CHKMALLOC_OLD = 325;
  public static final int BLK_CHKPL = 326;
  public static final int BLK_CHKSC = 327;
  public static final int BLK_CHKTS = 328;
  public static final int BLK_CMUZZLE = 329;
  public static final int BLK_CNEWSI = 330;
  public static final int BLK_CNEWSF = 331;
  public static final int BLK_CANEWSI = 332;
  public static final int BLK_CANEWSF = 333;
  public static final int BLK_FTELL = 334;
  public static final int BLK_HIDEINFO = 335;
  public static final int BLK_LCHANLIST = 336;
  public static final int BLK_LISTEXPUNGE = 337;
  public static final int BLK_MUZZLE = 338;
  public static final int BLK_NUKE = 339;
  public static final int BLK_POSE = 340;
  public static final int BLK_ASETMAXPLAYER = 341;
  public static final int BLK_QUOTA = 342;
  public static final int BLK_RAISEDEAD = 343;
  public static final int BLK_RATING_RECALC = 344;
  public static final int BLK_REMPLAYER = 345;
  public static final int BLK_RERANK = 346;
  public static final int BLK_SHOWCOMMENT = 347;
  public static final int BLK_SHUTDOWN = 348;
  public static final int BLK_STOPML = 349;
  public static final int BLK_CHKDOMAIN = 350;
  public static final int BLK_CHKEMAIL = 351;
  public static final int BLK_CHKUSERNAME = 352;
  public static final int BLK_DUMPUSAGE = 353;
  public static final int BLK_FIXDB = 354;
  public static final int BLK_PATHLEN = 355;
  public static final int BLK_ASETCRAZYHOUSE = 356;
  public static final int BLK_CNEWSE = 357;
  public static final int BLK_CNEWSP = 358;
  public static final int BLK_CNEWST = 359;
  public static final int BLK_CANEWSE = 360;
  public static final int BLK_CANEWSP = 361;
  public static final int BLK_CANEWST = 362;
  public static final int BLK_LNEWS = 363;
  public static final int BLK_LTOLERANCE = 364;
  public static final int BLK_CHECKPASSWORD = 365;
  public static final int BLK_DISCONNECT = 366;
  public static final int BLK_INTERFACE = 367;
  public static final int BLK_FIXADJOURNED = 368;
  public static final int BLK_RESETBLITZ = 369;
  public static final int BLK_RESETSTD = 370;
  public static final int BLK_RESETLIGHT = 371;
  public static final int BLK_RESETWILD = 372;
  public static final int BLK_RESETBUGHOUSE = 373;
  public static final int BLK_RESETCRAZYHOUSE = 374;
  public static final int BLK_RESETSUICIDE = 375;
  public static final int BLK_BESTRESETBLITZ = 376;
  public static final int BLK_BESTRESETSTD = 377;
  public static final int BLK_BESTRESETLIGHT = 378;
  public static final int BLK_BESTRESETWILD = 379;
  public static final int BLK_BESTRESETBUGHOUSE = 380;
  public static final int BLK_BESTRESETCRAZYHOUSE = 381;
  public static final int BLK_BESTRESETSUICIDE = 382;
  public static final int BLK_CHKID = 383;
  public static final int BLK_ERROR_BADCOMMAND = 512;
  public static final int BLK_ERROR_BADPARAMS = 513;
  public static final int BLK_ERROR_AMBIGUOUS = 514;
  public static final int BLK_ERROR_RIGHTS = 515;
  public static final int BLK_ERROR_OBSOLETE = 516;
  public static final int BLK_ERROR_REMOVED = 517;
  public static final int BLK_ERROR_NOTPLAYING = 518;
  public static final int BLK_ERROR_NOSEQUENCE = 519;
  public static final int BLK_ERROR_LENGTH = 520;
}


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\ictk\boardgame\chess\net\ics\fics\FICSBlockMode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */