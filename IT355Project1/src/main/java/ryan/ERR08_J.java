// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 7: ERR08_J. Do not catch NullPointerException or any of its ancestors
// Reccomendation 7:  ERR50-J. Use exceptions only for exceptional conditions

package main.java.ryan;

public class ERR08_J {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        String str = null;

        // ERR50-J: Use exceptions only for exceptional conditions
        // Check for null explicitly instead of relying on NullPointerException
        if (str == null) {
            System.out.println("String is null"); // Handle the condition without exceptions
        } else {
            System.out.println(str.length());
        }

        /*
        non compliant code
        ERR50-J Violation: Using exceptions for control flow
        try {
           System.out.println(str.length()); // This will throw NullPointerException
        } catch (NullPointerException e) { // ERR08-J Violation: Catching NullPointerException
           System.out.println("Caught NullPointerException");
        }
        */
    }
}
