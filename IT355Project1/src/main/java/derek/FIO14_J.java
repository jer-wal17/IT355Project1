// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO14-J. Perform proper cleanup at program termination

package main.java.derek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIO14_J
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File outFile = new File("output.txt");
        FileOutputStream out = new FileOutputStream(outFile);
        String content = "Hello World!";

        // Noncompliant implementation:
        // out.write(content.getBytes());
        // Runtime.getRuntime().exit(1);
        
        // Compliant implementation:
        try 
        {
            out.write(content.getBytes());
        } 
        finally 
        {
            try 
            {
                out.close();
            }
            catch (IOException e) 
            {  
                throw e;
            }
        }
        Runtime.getRuntime().exit(1);
    }
}
