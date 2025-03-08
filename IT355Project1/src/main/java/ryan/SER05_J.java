// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER05_J. Do not serialize unencrypted sensitive data
// Recommendation 7: ERR53-J. Use meaningful exception messages

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER05-J and Recommendation ERR53-J.
 * It shows how to serialize and deserialize a class while avoiding the serialization
 * of inner classes and using meaningful exception messages.
 */
class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    /**
     * Constructor for SerializableClass.
     *
     * @param data The data to be stored in the object.
     */
    public SerializableClass(String data) {
        this.data = data;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string containing the object's data.
     */
    @Override
    public String toString() {
        return "InnerClass Data: " + data;
    }
}

/**
 * This class demonstrates the serialization and deserialization of SerializableClass.
 */
public class SER05_J {

    /**
     * Main method to demonstrate serialization and deserialization.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an instance of the class
        SerializableClass innerObj = new SerializableClass("Hello, Inner World!");

        // Serialize the object to a file
        String filename = "inner_class.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(innerObj);
            System.out.println("Inner class object serialized to " + filename);
        } catch (IOException e) {
            // ERR53-J: Use meaningful exception messages
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedInnerObj = (SerializableClass) in.readObject();
            System.out.println("Inner class object deserialized from " + filename);
            System.out.println(deserializedInnerObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            // ERR53-J: Use meaningful exception messages
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}

/*
 * Non-compliant code:
 * public class Main {
 *     class InnerClass implements Serializable { // Non-compliant: Inner class is serializable
 *         private static final long serialVersionUID = 1L;
 *         private String data;
 *
 *         public InnerClass(String data) {
 *             this.data = data;
 *         }
 *     }
 *
 *     public static void main(String[] args) {
 *         try {
 *             // Serialization logic here
 *         } catch (IOException e) {
 *             // ERR53-J Violation: Generic exception message
 *             System.err.println("Error occurred");
 *         }
 *     }
 * }
 */