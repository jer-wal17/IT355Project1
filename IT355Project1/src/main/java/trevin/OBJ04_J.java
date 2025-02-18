package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/16/2025
//Description: Compliant code for rule OBJ04_J

import java.util.Calendar;

class Node{
    private Calendar currentDate;
    /**
     * Constructor, gets a new calendar instance then copies the details over from parameter
     * @param date takes a calendar object to get the date.
     */
    public Node(Calendar date) {
        this.currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
        currentDate.set(Calendar.MONTH, date.get(Calendar.MONTH)); // Month is 0-indexed
        currentDate.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
    }
    /**toString method override */
    public String toString() {
        // Displaying months as 1-indexed
        return currentDate.get(Calendar.YEAR) + ", " 
                + (currentDate.get(Calendar.MONTH)+1) + ", "
                + currentDate.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * method to get the current date stored in the node class object
     * @return a Calender object
     */
    public Calendar getCurrentDate(){
        return (Calendar) currentDate.clone();
    }
}

public class OBJ04_J {
    /*create a blank Instant class array */
    private static Node[] calendarArray = new Node[10];
    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        /*set every node to a new node  */
        System.out.println("starting program...");
        for (int i = 0; i < 10;i++){
            /**get random day, month, and year*/
            int randomDay = (int) (Math.random() * 20) + 1; //
            int randomMonth = (int) (Math.random() * 12) + 1;
            int year = 2025;
            /**setup calendar */
            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(Calendar.YEAR, year);
            newCalendar.set(Calendar.MONTH, randomMonth);
            newCalendar.set(Calendar.DAY_OF_MONTH, randomDay);
            calendarArray[i] = new Node(newCalendar);
            System.out.println(calendarArray[i].toString());
        }
        System.out.println("getting the current instant in time of index 3: " + calendarArray[3].getCurrentDate());
        System.out.println("program test over...");
    }
}
