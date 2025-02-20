// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER01_J. Do not deviate from the proper signatures of serialization methods
// Reccomendation 7: ERR52-J. Avoid in-band error indicators

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public SerializableClass(String data) {
        /*
        non compliant code
        ERR52-J Violation: Using in-band error indicators (e.g., null to indicate an error)
            this.data = data; // No validation for null data
        }
        */
        if (data == null) {
            // ERR52-J: Avoid in-band error indicators (e.g., returning null or special values)
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.data = data;

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}

public class SER01_J {
    public static void main(String[] args) {
        // Serialization and deserialization logic here
    }
}
