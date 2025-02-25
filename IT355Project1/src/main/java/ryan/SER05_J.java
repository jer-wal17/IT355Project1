// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER05_J. Do not serialize unencrypted sensitive data
// Reccomendation 7: ERR53-J. Use meaningful exception messages

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public SerializableClass(String data) {
        this.data = data;
    }

    @Override
        public String toString() {
            return "InnerClass Data: " + data;
        }
}

public class SER05_J {
    public static void main(String[] args) {
        // Create an instance of the inner class
        SerializableClass innerObj = new SerializableClass("Hello, Inner World!");

        // Serialize the inner class object to a file
        String filename = "inner_class.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(innerObj);
            System.out.println("Inner class object serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        // Deserialize the inner class object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            SerializableClass deserializedInnerObj = (SerializableClass) in.readObject();
            System.out.println("Inner class object deserialized from " + filename);
            System.out.println(deserializedInnerObj); // Print the deserialized object
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }
}

/*
public class main{
    class InnerClass implements Serializable { // Non-compliant: Inner class is serializable
        private static final long serialVersionUID = 1L;
        private String data;

        public InnerClass(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        try {
            // Serialization logic here
        } catch (IOException e) {
            // ERR53-J Violation: Generic exception message
            System.err.println("Error occurred");
    }
} 
*/
