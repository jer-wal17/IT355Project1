// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER01_J. Do not deviate from the proper signatures of serialization methods
// Recommendation 7: ERR52-J. Avoid in-band error indicators

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER01-J and Recommendation ERR52-J.
 * It shows how to properly implement serialization methods and avoid in-band error indicators.
 */
class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    /**
     * Constructor for SerializableClass.
     *
     * @param data The data to be stored in the object. Must not be null.
     * @throws IllegalArgumentException If the data is null.
     */
    public SerializableClass(String data) {
        /*
         * Non-compliant code:
         * ERR52-J Violation: Using in-band error indicators (e.g., null to indicate an error)
         * this.data = data; // No validation for null data
         */
        if (data == null) {
            // ERR52-J: Avoid in-band error indicators (e.g., returning null or special values)
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.data = data;
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
     * @return A string containing the object's data.
     */
    @Override
    public String toString() {
        return "Data: " + data;
    }
}

/**
 * This class demonstrates the serialization and deserialization of SerializableClass.
 */
public class SER01_J {

    /**
     * Main method to demonstrate serialization and deserialization.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an object to serialize
        SerializableClass obj = new SerializableClass("Hello, World!");

        // Serialize the object to a file
        String filename = "serialized_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Object serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Object deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}