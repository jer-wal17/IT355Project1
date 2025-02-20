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
    private void writeObject(ObjectOutputStream out) throws IOException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("serialize"));
        }
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("deserialize"));
        }
        in.defaultReadObject();
    }
}

public class SER04_J {
    public static void main(String[] args) {
        // Serialization and deserialization logic here
    }
}
