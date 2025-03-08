// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER03-J. Do not serialize unencrypted sensitive data

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER03-J.
 * It shows how to prevent sensitive data from being serialized in plaintext
 * by marking it as transient.
 */
class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient
    // private String sensitiveData; // Non-compliant code: Sensitive data is not encrypted

    /**
     * Constructor for SerializableClass.
     *
     * @param sensitiveData The sensitive data to be stored in the object.
     */
    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    /**
     * Custom serialization method.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs during serialization.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string containing the object's sensitive data.
     */
    @Override
    public String toString() {
        return "Sensitive Data: " + sensitiveData;
    }
}

/**
 * This class demonstrates the serialization and deserialization of SerializableClass.
 */
public class SER03_J {

    /**
     * Main method to demonstrate serialization and deserialization of sensitive data.
     *
     * @param args Command-line arguments (not used in this example).
     */
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
 * Non-compliant code:
 * import java.io.*;
 *
 * class SerializableClass implements Serializable {
 *     private static final long serialVersionUID = 1L;
 *     private String sensitiveData; // Non-compliant: Sensitive data is not encrypted
 *
 *     public SerializableClass(String sensitiveData) {
 *         this.sensitiveData = sensitiveData;
 *     }
 * }
 *
 * public class Main {
 *     public static void main(String[] args) {
 *         // Serialization and deserialization logic here
 *     }
 * }
 */