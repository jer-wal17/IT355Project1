// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER11-J. Do not use the default readObject() method when deserializing sensitive data

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient

    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    // non compliant code uses the default readObject instead of a personalized one
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Perform additional validation or decryption here
    }
}

public class SER11_J {
    public static void main(String[] args) {
        // Serialization and deserialization logic here
    }
}
