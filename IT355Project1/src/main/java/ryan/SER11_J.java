// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER11-J. Do not use the default readObject() method when deserializing sensitive data

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String sensitiveData; // Mark sensitive data as transient

    public SerializableClass(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    // non compliant code uses the default readObject instead of a personalized one
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Perform additional validation or decryption here
    }

    @Override
    public String toString() {
        return "Sensitive Data: " + sensitiveData;
    }
}

public class SER11_J {
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
