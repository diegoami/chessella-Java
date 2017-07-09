package ictk.boardgame;

import ictk.boardgame.Player;
import ictk.boardgame.Result;
import ictk.util.Log;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

public abstract class GameInfo {

   public static final long DEBUG = Log.GameInfo;
   public Properties props = new Properties();
   protected Player[] players;
   protected Calendar date;
   protected String event;
   protected String site;
   protected String round;
   protected String subround;
   protected Result result;
   protected int year;
   protected int month;
   protected int day;


   public Player[] getPlayers() {
      return this.players;
   }

   public String getEvent() {
      return this.event;
   }

   public String getSite() {
      return this.site;
   }

   public Calendar getDate() {
      return this.date;
   }

   public String getRound() {
      return this.round;
   }

   public String getSubRound() {
      return this.subround;
   }

   public int getYear() {
      return this.year;
   }

   public int getMonth() {
      return this.month;
   }

   public int getDay() {
      return this.day;
   }

   public Result getResult() {
      return this.result;
   }

   public Properties getAuxilleryProperties() {
      return this.props;
   }

   public String getDateString() {
      StringBuffer sb = new StringBuffer(10);
      if(this.year > 0) {
         if(this.year < 10) {
            sb.append(" ");
         }

         if(this.year < 100) {
            sb.append(" ");
         }

         if(this.year < 1000) {
            sb.append(" ");
         }

         sb.append(this.year);
      } else {
         sb.append("????");
      }

      sb.append(".");
      if(this.month > 0) {
         if(this.month < 10) {
            sb.append("0");
         }

         sb.append(this.month);
      } else {
         sb.append("??");
      }

      sb.append(".");
      if(this.day > 0) {
         if(this.day < 10) {
            sb.append("0");
         }

         sb.append(this.day);
      } else {
         sb.append("??");
      }

      return sb.toString();
   }

   public void setEvent(String event) {
      this.event = event;
   }

   public void setSite(String site) {
      this.site = site;
   }

   public void setDate(Calendar date) {
      this.date = date;
   }

   public void setRound(String round) {
      this.round = round;
   }

   public void setSubRound(String round) {
      this.subround = round;
   }

   public void setYear(int i) {
      this.year = i;
   }

   public void setMonth(int i) {
      this.month = i;
   }

   public void setDay(int i) {
      this.day = i;
   }

   public void setResult(Result res) {
      this.result = res;
   }

   public void setAuxilleryProperties(Properties p) {
      this.props = p;
   }

   public void add(String key, String value) {
      this.props.setProperty(key, value);
   }

   public String get(String key) {
      return this.props.getProperty(key);
   }

   public String toString() {
      String tmp = null;
      tmp = "[Event: " + this.event + "]\n" + "[Site:  " + this.site + "]\n" + "[Date:  " + this.getDateString() + "]\n" + "[Round: " + this.round + "]\n" + "[SubRound: " + this.subround + "]\n" + "[Result: " + this.result + "]\n";
      return tmp;
   }

   public boolean equals(Object o) {
      if(o == this) {
         return true;
      } else if(o != null && o.getClass() == this.getClass()) {
         GameInfo gi = (GameInfo)o;
         boolean t = true;
         Log.debug(DEBUG, "checking for equality");
         if(t && this.players == gi.players) {
            t = true;
         } else if(t && this.players != null && gi.players != null && this.players.length == gi.players.length) {
            for(int i = 0; t && i < this.players.length; ++i) {
               t = t && this.isSame(this.players[i], gi.players[i]);
               if(!t) {
                  Log.debug2(DEBUG, "players[" + i + "]: " + this.players[i] + " / " + gi.players[i]);
               }
            }
         }

         if(t) {
            t = t && this.isSame(this.event, gi.event);
            if(!t) {
               Log.debug2(DEBUG, "event: " + this.event + " / " + gi.event);
            }
         }

         if(t) {
            t = t && this.equalDates(gi.date);
            if(!t) {
               Log.debug2(DEBUG, "date: " + this.date + " / " + gi.date);
            }
         }

         if(t) {
            t = t && this.isSame(this.round, gi.round);
            if(!t) {
               Log.debug2(DEBUG, "round: " + this.round + " / " + gi.round);
            }
         }

         if(t) {
            t = t && this.isSame(this.subround, gi.subround);
            if(!t) {
               Log.debug2(DEBUG, "subround: " + this.subround + " / " + gi.subround);
            }
         }

         if(t) {
            t = t && this.isSame(this.result, gi.result);
            if(!t) {
               Log.debug2(DEBUG, "result: " + this.result + " / " + gi.result);
            }
         }

         if(t) {
            t = t && this.isSame(this.props, gi.props);
            if(!t) {
               Log.debug2(DEBUG, "aux: " + this.props + " / " + gi.props);
            }
         }

         if(t) {
            Log.debug2(DEBUG, "equal");
         }

         return t;
      } else {
         return false;
      }
   }

   protected boolean isSame(Object o, Object p) {
      return o == p || o != null && o.equals(p);
   }

   protected boolean equalDates(Calendar cal) {
      boolean t = true;
      if(this.date == null && this.date == cal) {
         return true;
      } else {
         t = t && cal != null;
         t = t && this.date.isSet(1) == cal.isSet(1);
         t = t && this.date.isSet(1) && this.date.get(1) == cal.get(1);
         t = t && this.date.isSet(2) && this.date.get(2) == cal.get(2);
         t = t && this.date.isSet(5) && this.date.get(5) == cal.get(5);
         return t;
      }
   }

   public int hashCode() {
      int hash = 5;
      if(this.players != null) {
         for(int i = 0; i < this.players.length; ++i) {
            hash = 31 * hash + (this.players[i] == null?0:this.players[i].hashCode());
         }
      }

      hash = 31 * hash + this.getDateString().hashCode();
      hash = 31 * hash + (this.event == null?0:this.event.hashCode());
      hash = 31 * hash + (this.site == null?0:this.site.hashCode());
      hash = 31 * hash + (this.round == null?0:this.round.hashCode());
      hash = 31 * hash + (this.subround == null?0:this.subround.hashCode());
      return hash;
   }

   public void dump() {
      StringBuffer sb = new StringBuffer();
      sb.append("##GameInfo Dump").append(this.toString()).append("Aux Data:");
      if(this.props == null) {
         sb.append("None");
      } else {
         Enumeration enumer = this.props.propertyNames();
         String key = null;

         while(enumer.hasMoreElements()) {
            key = (String)enumer.nextElement();
            sb.append(key).append(" = ").append(this.props.getProperty(key, (String)null));
         }
      }

   }
}
