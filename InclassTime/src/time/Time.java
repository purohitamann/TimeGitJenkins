package time;

import java.util.Scanner;

/**
 * @modified by Liz Dancy
 * Used to show how to catch multiple Exceptions and then as a base class
 * for the testing exercise with JUnit
 */

import javax.swing.JOptionPane;

/**
 * Taken from Wendi Jollymore :http://www-acad.sheridanc.on.ca/~jollymor/prog24178/oop2.html
 * @modified by Liz Dancy
 * Used as a base class for the testing exercise with JUnit
 * Winter 2021
 */
public class Time{ 
    //new commit
    public static void main(String[] args) {
        int totalSeconds = getTotalSeconds("10:10:10");
        System.out.println("Github Seconds = " + totalSeconds);
    }

    public static int getTotalSeconds(String time) throws NumberFormatException, StringIndexOutOfBoundsException {
        int hours = getTotalHours(time);
        int minutes = getTotalMinutes(time);
        int seconds = getSeconds(time);
        int milliseconds = getMilliseconds(time);
        return (hours * 3600 + minutes * 60 + seconds) + milliseconds / 1000;
    }

    public static int getMilliseconds(String timeStr) {
        String[] timeParts = timeStr.split(":");
        if (timeParts.length == 3) {  // no milliseconds provided
            return 0;  // Return 0 if there are no milliseconds
        } else if (timeParts.length == 4) {  // milliseconds included
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);
            int milliseconds = Integer.parseInt(timeParts[3]);

            return (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + milliseconds;
        } else {
            throw new NumberFormatException("Invalid time format, expected hh:mm:ss or hh:mm:ss:ms");
        }
    }

    public static int getTotalMilliseconds(String time) throws NumberFormatException, StringIndexOutOfBoundsException {
        int hours = getTotalHours(time);
        int minutes = getTotalMinutes(time);
        int seconds = getSeconds(time);
        int milliseconds = getMilliseconds(time);

        return (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + milliseconds;
    }

    public static int getSeconds(String time) throws NumberFormatException, StringIndexOutOfBoundsException {
        return Integer.parseInt(time.substring(6, 8));
    }

    public static int getTotalMinutes(String time) throws NumberFormatException, StringIndexOutOfBoundsException {
        if (time.length() > 11) {
            throw new NumberFormatException("Your time was too long!");
        }
        return Integer.parseInt(time.substring(3, 5));
    }

    public static int getTotalHours(String time) throws NumberFormatException, StringIndexOutOfBoundsException {
        return Integer.parseInt(time.substring(0, 2));
    }
}
