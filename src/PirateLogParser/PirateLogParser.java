/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PirateLogParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Julian
 */
public class PirateLogParser {

    private ArrayList<PirateLog> runners;
    private ArrayList<PirateLog> stalkers;
    private ArrayList<Pirate> pirates;

    public static void main(String[] args) {

    }

    private void deDuping() {
        
    }

    private void readLogs() {
        File log = new File("\\logs\\");
        File[] logs = log.listFiles();
        int lognr = logs.length;

        for (File f : logs) {
            try {
                FileReader fileReader = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String s = bufferedReader.readLine();
                String[] stringList = s.split(" ");
                Pirate p = new Pirate(stringList[1]);
                pirates.add(p);
                while (true) {
                    s = bufferedReader.readLine();
                    if (null == s) {
                        break;
                    }
                    stringList = s.split(" ");
                    if (!stringList[0].equals("QSO:")) {
                        break;
                    }
                    if (stringList[2].equals("R")) {
                        runners.add(new PirateLog(p.getCall(), stringList[6], Integer.parseInt(stringList[1]), true, stringList[9].equals("Y")));
                    } else {
                        stalkers.add(new PirateLog(stringList[6], p.getCall(), Integer.parseInt(stringList[1]), false, stringList[9].equals("Y")));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
