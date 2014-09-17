/*
 * This is released to the public domain.
 */
package piratelogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Julian Silden Langlo / LB5QG
 */
public class PirateLogger {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<PirateLoggerEntry> log;
    private static String[] finalInfo;
    private static int counter = 0;

    public static void main(String[] args) {
        log = new ArrayList<PirateLoggerEntry>();
        PirateLoggerGUI gui = new PirateLoggerGUI();
        gui.setVisible(true);
    }

    public static void addEntry(PirateLoggerEntry e) {
        log.add(e);
        storeLog();
    }

    public static PirateLoggerEntry getEntry(int i) {
        return log.get(i);
    }

    public static void removeEntry(int i) {
        log.remove(i);
    }

    public static ArrayList<PirateLoggerEntry> getEntries() {
        return log;
    }

    public static void prettify(String[] input) {
        finalInfo = input;
        storeLog();
    }

    private static boolean storeLog() {
        try {
            String fileName = "PirateLog_2014_" + log.get(0).getMyCallsign();
            if (counter == 0) {
                try {
                    File f = new File(fileName);
                    if (f.exists() && !f.isDirectory()) {
                        while (f.exists()) {
                            counter++;
                            f = new File(fileName + "_" + counter);
                        }
                    } else {
                        counter = -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fileName += counter == 0 ? "" : "_" + counter;
            fileName += ".txt";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
            printWriter.println("Callsign: " + log.get(0).getMyCallsign());
            printWriter.println("Contest: " + finalInfo[0]);
            printWriter.println("Category: " + finalInfo[1]);
            printWriter.println("Claimed Score: " + finalInfo[2]);
            printWriter.println("Email-address: " + finalInfo[3]);
            printWriter.println("Name: " + finalInfo[4]);
            printWriter.println("Address: " + finalInfo[5]);
            printWriter.println("Soapbox: " + finalInfo[6]);
            for (PirateLoggerEntry entry : log) {
                printWriter.println(entry.toLog());
            }
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
