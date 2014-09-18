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
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Julian
 */
public class PirateLogParser {

    private static ArrayList<PirateLog> runners;
    private static ArrayList<PirateLog> stalkers;
    private static ArrayList<Pirate> pirates;

    public static void main(String[] args) {
        readLogs();
        deDupe();
        compare();
    }

    private static void readLogs() {
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
                    boolean plank = false;
                    for (int i = 0; i < stringList.length; i++) {
                        if (stringList[i].equals("Y")) {
                            plank = true;
                            break;
                        }
                    }
                    if (stringList[2].equals("R")) {
                        runners.add(new PirateLog(p.getCall(), stringList[6], Integer.parseInt(stringList[1]), true, plank));
                    } else {
                        stalkers.add(new PirateLog(stringList[6], p.getCall(), Integer.parseInt(stringList[1]), false, plank));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void deDupe() {
        Set setItems = new LinkedHashSet(runners);
        runners.clear();
        runners.addAll(setItems);

        setItems = new LinkedHashSet(stalkers);
        stalkers.clear();
        stalkers.addAll(setItems);

        setItems = new LinkedHashSet(pirates);
        pirates.clear();
        pirates.addAll(setItems);
    }

    private static void compare() {
        for (PirateLog r : runners) {
            for (PirateLog s : stalkers) {
                if (r.compare(s)) {
                    if (r.isTeam()){
                        
                    }
                    break;
                }
            }
        }
    }
}
