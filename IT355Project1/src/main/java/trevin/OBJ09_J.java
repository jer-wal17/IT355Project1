package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/17/2025
//Description: Compliant code for rule OBJ09_J and recommendation OBJ53-J

import java.nio.ByteBuffer;

public class OBJ09_J {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting program.");
        //create a mainBuffer, we'll be using it alot so we will be following the recommendation and use a direct buffer
        ByteBuffer mainBuffer = ByteBuffer.allocateDirect(200);
        //we're just gonna put like 3 types of stuff into the buffer
        mainBuffer.putInt(54321);
        mainBuffer.putChar('A');
        mainBuffer.putFloat(93.23f);
        mainBuffer.putDouble(32.53);
        //flip buffer to get mode
        mainBuffer.flip();
        //to test for the rule, we're grabbing 2 of the types out of the buffer
        int tempInt = mainBuffer.getInt();
        char tempChar = mainBuffer.getChar();
        //now we're gonna put them in two separate classes
        Integer newInteger = Integer.valueOf(tempInt);
        String newString = String.valueOf(tempChar);
        System.out.println(newString.getClass());
        System.out.println(newInteger);
        //do comparisons to see if they're integer and string classes respectively
        if (newString.getClass() == java.lang.String.class){
            System.out.println("The newString object is part of the String class");
        }
        if (newInteger.getClass() == java.lang.Integer.class){
            System.out.println("The newInteger object is part of the Integer class");
        }
        //clear the buffer
        mainBuffer.clear();
        System.out.println("end program");
    }
}
