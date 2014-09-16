/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PirateLogParser;

/**
 *
 * @author Julian
 */
public class PirateLog {

    private String aCall;
    private String bCall;
    private int band;
    private boolean running;
    private boolean plank;
    private boolean checked = false;
    private boolean confirmed = false;

    public PirateLog(String aCall, String bCall, int band, boolean running, boolean plank) {
        this.aCall = aCall;
        this.bCall = bCall;
        this.band = band;
        this.running = running;
        this.plank = plank;
    }

    /**
     * @return the aCall
     */
    public String getaCall() {
        return aCall;
    }

    /**
     * @param aCall the aCall to set
     */
    public void setaCall(String aCall) {
        this.aCall = aCall;
    }

    /**
     * @return the bCall
     */
    public String getbCall() {
        return bCall;
    }

    /**
     * @param bCall the bCall to set
     */
    public void setbCall(String bCall) {
        this.bCall = bCall;
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

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * @param confirmed the confirmed to set
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean compare(PirateLog other) {
        if (other.isRunning() != running && other.getBand() == band && other.getaCall().equals(bCall) && other.getbCall().equals(aCall) && other.isPlank() == plank) {
            other.setConfirmed(true);
            confirmed = true;
            return true;
        } else {
            return false;
        }
    }
}
