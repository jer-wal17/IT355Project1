// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 7: ERR08_J. Do not catch NullPointerException or any of its ancestors
// Recommendation 7: ERR50-J. Use exceptions only for exceptional conditions

package main.java.ryan;

/**
 * This class demonstrates compliance with Rule ERR08-J and Recommendation ERR50-J.
 * It shows how to handle null values without catching NullPointerException
 * and ensures exceptions are used only for exceptional conditions.
 */
public class ERR08_J {

    /**
     * Main method to demonstrate proper handling of null values.
     *
     * @param args Command-line arguments (not used in this example).
     */
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
         * Non-compliant code:
         * ERR50-J Violation: Using exceptions for control flow
         * try {
         *    System.out.println(str.length()); // This will throw NullPointerException
         * } catch (NullPointerException e) { // ERR08-J Violation: Catching NullPointerException
         *    System.out.println("Caught NullPointerException");
         * }
         */
    }
}