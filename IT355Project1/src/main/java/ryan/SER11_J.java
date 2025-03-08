// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER11-J. Do not use the default readObject() method when deserializing sensitive data

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER11-J.
 * It shows how to customize the deserialization process for sensitive data
 * by overriding the readObject method.
 */
class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient

    /**
     * Constructor for SerializableClass.
     *
     * @param sensitiveData The sensitive data to be stored in the object.
     */
    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    /**
     * Custom deserialization method.
     * This method ensures that sensitive data is handled securely during deserialization.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Perform additional validation or decryption here
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
 * with secure handling of sensitive data during deserialization.
 */
public class SER11_J {

    /**
     * Main method to demonstrate serialization and deserialization of sensitive data.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an object with sensitive data
        SerializableClass obj = new SerializableClass("Highly Confidential");

        // Serialize the object to a file
        String filename = "sensitive_read_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Sensitive object serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Sensitive object deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}