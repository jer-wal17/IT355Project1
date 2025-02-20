// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER03-J. Do not serialize unencrypted sensitive data

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient
    // private String sensitiveData; // Non compliant code: Sensitive data is not encrypted


    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    // non compliant code has no writeObject or readObject methods
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}

public class SER03_J {
    public static void main(String[] args) {
        // Serialization and deserialization logic here
    }
}

/*

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensitiveData; // Non-compliant: Sensitive data is not encrypted

    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }
}

public class Main {
    public static void main(String[] args) {
        // Serialization and deserialization logic here
    }
}
*/