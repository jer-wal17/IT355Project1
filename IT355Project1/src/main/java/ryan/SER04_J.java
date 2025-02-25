// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER04-J. Do not allow serialization and deserialization to bypass the SecurityManager

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public SerializableClass(String data) {
        this.data = data;
    }

    // non compliant code has no write object or read object methods to check and stop bypass of the SecurityManager
    @SuppressWarnings("removal")
    private void writeObject(ObjectOutputStream out) throws IOException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("serialize"));
        }
        out.defaultWriteObject();
    }

    @SuppressWarnings("removal")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("deserialize"));
        }
        in.defaultReadObject();
    }

    @Override
    public String toString() {
        return "Data: " + data;
    }
}

public class SER04_J {
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
