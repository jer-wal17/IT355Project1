// Written by Ryan Pukacz
// For IT355 - Secure Software Development

// Rule 14: SER01_J. Do not deviate from the proper signatures of serialization methods
// Reccomendation 7: ERR52-J. Avoid in-band error indicators

package main.java.ryan;

import java.io.*;

class SerializableClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data;

    public SerializableClass(String data) {
        /*
        non compliant code
        ERR52-J Violation: Using in-band error indicators (e.g., null to indicate an error)
            this.data = data; // No validation for null data
        }
        */
        if (data == null) {
            // ERR52-J: Avoid in-band error indicators (e.g., returning null or special values)
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.data = data;

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    // only used for the example to make the data output look right with strings
    @Override
    public String toString() {
        return "Data: " + data;
    }
}

public class SER01_J {
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
