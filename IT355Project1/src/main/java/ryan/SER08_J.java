// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER08-J. Do not use the default serial form for implementation-defined invariants
// Reccomendation 7: ERR54-J. Provide a mechanism to handle exceptional conditions

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient
    // private String sensitiveData; // Non compliant code: Sensitive data is not marked as transient

    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

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

public class SER08_J {
    public static void main(String[] args) {
        // Create an object with sensitive data
        SerializableClass obj = new SerializableClass("Confidential Info");

        // Serialize the object to a file
        String filename = "sensitive_object.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
            System.out.println("Object with sensitive data serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedObj = (SerializableClass) in.readObject();
            System.out.println("Object with sensitive data deserialized from " + filename);
            System.out.println(deserializedObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}


