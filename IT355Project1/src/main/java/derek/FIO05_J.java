// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO05-J. Do not expose buffers or their backing arrays methods to untrusted code

package main.java.derek;

import java.nio.CharBuffer;

public class FIO05_J 
{
    private static Wrapper wrapper;

    public static void main(String[] args)
    {
        wrapper = new Wrapper();
        CharBuffer bufferCopy = wrapper.getBufferCopy();
        bufferCopy.clear();
        bufferCopy.put("New Text!");
        char[] dataArrayCopy = bufferCopy.array();
        System.out.println("Buffer copy contents: " + wrapper.printCharArray(dataArrayCopy));
        System.out.println("Original data contents: " + wrapper.toString());
        // Was the original data modified?
        // If the original data is unchanged, we complied with the rule!
    }
    
    private static class Wrapper  {
        private char[] dataArray;
        
        public Wrapper() 
        {
            dataArray = new char[15];
            for(int i = 0; i < dataArray.length; i++) {
                dataArray[i] = (char)(97 + i);
            }
        }
        /** Get a Buffer containing a copy of this object's data array
         * 
         * @return A CharBuffer using a copy of the data array as its backing
         */
        public CharBuffer getBufferCopy() 
        {
            // Noncompliant implementation:
            // return CharBuffer.wrap(dataArray);

            // Compliant implementation:
            CharBuffer originalBuffer = CharBuffer.allocate(dataArray.length);
            originalBuffer.put(dataArray);
            return originalBuffer;
        }
        /**
         * 
         * @return a String representation of this object
         */
        public String toString()
        {
            return printCharArray(dataArray);
        }
        /** Convert a char array into a String object
         * 
         * @param array Data to process
         * @return A String representation of the input char array
         */
        public String printCharArray(char[] array) 
        {
            String result = "";
            for(int i = 0; i < array.length; i++) {
                result += array[i];
            }
            return result;
        }
    }
}
