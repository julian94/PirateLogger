/*
 * This is released to the public domain.
 */
package piratelogger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author Julian Silden Langlo / LB5QG
 */
public class PirateLoggerEntry {

    private String myCallsign;
    private String myName;
    private int band;
    private boolean running;
    private Timestamp tidspunkt;
    private int myDoubloons;
    private String theirCallsign;
    private int theirDoubloons;
    private String theirName;
    private boolean plank;
    private boolean dupe;

    public PirateLoggerEntry(String myCallsign, String myName, int band, boolean running, Timestamp tidspunkt, int myDoubloons, String theirCallsign, int theirDoubloons, String theirName, boolean plank, boolean dupe) {
        this.myCallsign = myCallsign;
        this.myName = myName;
        securify(myName);
        this.band = band;
        this.running = running;
        this.tidspunkt = tidspunkt;
        this.myDoubloons = myDoubloons;
        this.theirCallsign = theirCallsign;
        this.theirDoubloons = theirDoubloons;
        this.theirName = theirName;
        securify(theirName);
        this.plank = plank;
        this.dupe = dupe;
    }

    public static void securify(String s){
        s.replaceAll("\\s","");
    }
    
    /**
     * @return the myCallsign
     */
    public String getMyCallsign() {
        return myCallsign;
    }

    /**
     * @param myCallsign the myCallsign to set
     */
    public void setMyCallsign(String myCallsign) {
        this.myCallsign = myCallsign;
    }

    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }

    /**
     * @param myName the myName to set
     */
    public void setMyName(String myName) {
        this.myName = myName;
        securify(this.myName);
    }

    /**
     * @return the band
     */
    public int getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(int band) {
        this.band = band;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the tidspunkt
     */
    public Timestamp getTidspunkt() {
        return tidspunkt;
    }

    /**
     * @param tidspunkt the tidspunkt to set
     */
    public void setTidspunkt(Timestamp tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    /**
     * @return the myDoubloons
     */
    public int getMyDoubloons() {
        return myDoubloons;
    }

    /**
     * @param myDoubloons the myDoubloons to set
     */
    public void setMyDoubloons(int myDoubloons) {
        this.myDoubloons = myDoubloons;
    }

    /**
     * @return the theirCallsign
     */
    public String getTheirCallsign() {
        return theirCallsign;
    }

    /**
     * @param theirCallsign the theirCallsign to set
     */
    public void setTheirCallsign(String theirCallsign) {
        this.theirCallsign = theirCallsign;
    }

    /**
     * @return the theirDoubloons
     */
    public int getTheirDoubloons() {
        return theirDoubloons;
    }

    /**
     * @param theirDoubloons the theirDoubloons to set
     */
    public void setTheirDoubloons(int theirDoubloons) {
        this.theirDoubloons = theirDoubloons;
    }

    /**
     * @return the theirName
     */
    public String getTheirName() {
        return theirName;
    }

    /**
     * @param theirName the theirName to set
     */
    public void setTheirName(String theirName) {
        this.theirName = theirName;
        securify(this.theirName);
    }

    /**
     * @return the plank
     */
    public boolean isPlank() {
        return plank;
    }

    /**
     * @param plank the plank to set
     */
    public void setPlank(boolean plank) {
        this.plank = plank;
    }

    public String toString() {
        String out = "Operator: " + myCallsign;
        out += " Name: " + myName;
        out += " Band: " + band + "m";
        out += running ? " R " : " S ";
        out += tidspunkt.toString();
        out += "My Doubloons: " + myDoubloons;
        out += " Contact: " + theirCallsign;
        out += " Name: " + theirName;
        out += " Their Doubloons: " + theirDoubloons;
        out += plank ? "Walked plank" : "No plank";
        return out;
    }

    public String toListString() {
        String out = dupe ? "DUPE!" : "";
        out += "Operator: " + myCallsign;
        out += ", Band: " + band + "m";
        out += running ? ", R " : ", S ";
        out += tidspunkt.toString();
        out += ", Contact: " + theirCallsign;
        out += ", Name: " + theirName;
        out += ", Dbl: " + theirDoubloons;
        out += plank ? ", Plank" : ", No plank";
        return out;
    }

    public String toLog() {//Example: QSO: 21 R 2014-9-20 0134 30 JA5RA 31 Blackbeard N {1 point for W6RRR}
        String out = "QSO: ";
        out += band;
        out += running ? " R " : " S ";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HHmm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        out += sdf.format(tidspunkt);
        
        out += " " + myDoubloons;
        out += " " + theirCallsign;
        out += " " + theirDoubloons;
        out += " " + theirName;
        out += plank ? " Y" : " N";

        
        if (dupe) {
            return out;
        }
        
        boolean selfRed = false;
        for (int i = 0; i < myCallsign.length(); i++) {
            if (myCallsign.charAt(i) <= '9' && myCallsign.charAt(i) >= '0') {
                if ((myCallsign.charAt(i + 1) <= 'm' && myCallsign.charAt(i + 1) >= 'a') || (myCallsign.charAt(i + 1) <= 'M' && myCallsign.charAt(i + 1) >= 'A')) {
                    selfRed = true;
                }

            }
        }
        boolean otherRed = false;
        for (int i = 0; i < theirCallsign.length(); i++) {
            if (theirCallsign.charAt(i) <= '9' && theirCallsign.charAt(i) >= '0') {
                if ((theirCallsign.charAt(i + 1) <= 'm' && theirCallsign.charAt(i + 1) >= 'a') || (theirCallsign.charAt(i + 1) <= 'M' && theirCallsign.charAt(i + 1) >= 'A')) {
                    otherRed = true;
                }

            }
        }
        int points = 0;
        int dGain = 0;
        if (running) {
            if (selfRed == otherRed) {
                out += " {1 point for " + myCallsign + "}";
            } else {
                points += 4;
                dGain -= 1;
                if (plank) {
                    out += " {4 point 1 doubloon for " + myCallsign + "}";
                } else {
                    out += " {4 point -1 doubloon for " + myCallsign + "}";
                }
            }
        } else {
            if (selfRed == otherRed) {
                out += " {1 point for " + myCallsign + "}";
            } else {
                points += 1;
                dGain += 1;
                if (plank) {
                    out += " {1 point -1 doubloon for " + myCallsign + "}";
                } else {
                    out += " {1 point 1 doubloon for " + myCallsign + "}";
                }
            }
        }

        return out;
    }
}
