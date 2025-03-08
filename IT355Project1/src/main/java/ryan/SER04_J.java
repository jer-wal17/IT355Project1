// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER04-J. Do not allow serialization and deserialization to bypass the SecurityManager

package main.java.ryan;

import java.io.*;

/**
 * This class demonstrates compliance with Rule SER04-J.
 * It shows how to prevent serialization and deserialization from bypassing the SecurityManager
 * by checking permissions in the writeObject and readObject methods.
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
     * Custom serialization method that checks for the "serialize" permission.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @SuppressWarnings("removal")
    private void writeObject(ObjectOutputStream out) throws IOException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("serialize"));
        }
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method that checks for the "deserialize" permission.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    @SuppressWarnings("removal")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("deserialize"));
        }
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
 * This class demonstrates the serialization and deserialization of SerializableClass
 * with SecurityManager checks.
 */
public class SER04_J {

    /**
     * Main method to demonstrate serialization and deserialization with SecurityManager checks.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an object
        SerializableClass obj = new SerializableClass("Secure Data");

        // Serialize the object to a file
        String filename = "secure_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Secure object serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Secure object deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}