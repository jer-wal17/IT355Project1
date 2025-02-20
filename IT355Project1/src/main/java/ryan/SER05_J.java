// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER05_J. Do not serialize unencrypted sensitive data
// Reccomendation 7: ERR53-J. Use meaningful exception messages

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public SerializableClass(String data) {
        this.data = data;
    }
}

public class SER05_J {
    public static void main(String[] args) {
        try {
            // Serialization logic here
        } catch (IOException e) {
            // ERR53-J: Use meaningful exception messages
            System.err.println("Serialization failed: " + e.getMessage());
        }
    }
}

/*
public class main{
    class InnerClass implements Serializable { // Non-compliant: Inner class is serializable
        private static final long serialVersionUID = 1L;
        private String data;

        public InnerClass(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        try {
            // Serialization logic here
        } catch (IOException e) {
            // ERR53-J Violation: Generic exception message
            System.err.println("Error occurred");
    }
} 
*/
