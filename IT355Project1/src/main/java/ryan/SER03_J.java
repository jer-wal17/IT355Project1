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

    @Override
    public String toString() {
        return "Sensitive Data: " + sensitiveData;
    }
}

public class SER03_J {
    public static void main(String[] args) {
        // Create an object with sensitive data
        SerializableClass obj = new SerializableClass("Top Secret");

        // Serialize the object to a file
        String filename = "unencrypted_sensitive_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Unencrypted sensitive object serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Unencrypted sensitive object deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
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