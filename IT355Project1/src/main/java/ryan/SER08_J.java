// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER08-J. Do not use the default serial form for implementation-defined invariants
// Recommendation 7: ERR54-J. Provide a mechanism to handle exceptional conditions

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER08-J and Recommendation ERR54-J.
 * It shows how to avoid using the default serial form for sensitive data
 * by marking it as transient and providing proper exception handling.
 */
class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient
    // private String sensitiveData; // Non-compliant code: Sensitive data is not marked as transient

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
 * This class demonstrates the serialization and deserialization of SerializableClass
 * with proper handling of sensitive data and exceptional conditions.
 */
public class SER08_J {

    /**
     * Main method to demonstrate serialization and deserialization of sensitive data.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an object with sensitive data
        SerializableClass obj = new SerializableClass("Confidential Info");

        // Serialize the object to a file
        String filename = "sensitive_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Object with sensitive data serialized to " + filename);
        } catch (IOException e) {
            // ERR54-J: Provide a mechanism to handle exceptional conditions
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Object with sensitive data deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            // ERR54-J: Provide a mechanism to handle exceptional conditions
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}