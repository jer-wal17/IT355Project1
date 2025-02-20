// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER08-J. Do not use the default serial form for implementation-defined invariants
// Reccomendation 7: ERR54-J. Provide a mechanism to handle exceptional conditions

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient
    // private String sensitiveData; // Non compliant code: Sensitive data is not marked as transient

    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}

public class SER08_J {
    public static void main(String[] args) {
        // ERR54-J: Provide a mechanism to handle exceptional conditions
        // non compliant code ERR54-J Violation: Has no mechanism to handle exceptional conditions (no try catch in main)
        try {
            // Serialization and deserialization logic here
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during serialization/deserialization: " + e.getMessage());
        }
    }
}


