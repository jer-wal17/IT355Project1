// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO05-J. Do not expose buffers or their backing arrays methods to untrusted code
// Recommendation 13: FIO50-J. Do not make assumptions about file creation

package main.java.derek;

public class FIO05_J
{
    private char[] dataArray;

    public FIO05_J() {
        dataArray = new char[10];
    }

    public static void main(String[] args) throws Exception
    {
        getBufferCopy();
    }
    
    public CharBuffer getBufferCopy() {
        CharBuffer cb = CharBuffer.allocate(dataArray.length);
        cb.put(dataArray);
        return cb;
    }
}
