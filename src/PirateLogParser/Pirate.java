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
public class Pirate {
    private String call;
    private int doubloons;
    private int points;
    
    public Pirate(String call){
        this.call = call;
    }
    public Pirate(String call, int doubloons, int points){
        this.call = call;
        this.doubloons = doubloons;
        this.points = points;
    }

    /**
     * @return the call
     */
    public String getCall() {
        return call;
    }

    /**
     * @param call the call to set
     */
    public void setCall(String call) {
        this.call = call;
    }

    /**
     * @return the doubloons
     */
    public int getDoubloons() {
        return doubloons;
    }

    /**
     * @param doubloons the doubloons to set
     */
    public void setDoubloons(int doubloons) {
        this.doubloons = doubloons;
    }
    
    public void addDoubloons(int doubloons) {
        this.doubloons += doubloons;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void addPoints(int points) {
        this.points += points;
    }
    
    public int getScore() {
        return points * doubloons;
    }
}
