// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO14-J. Perform proper cleanup at program termination
// Recommendation 13: FIO50-J. Do not make assumptions about file creation

package main.java.derek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIO14_J
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        String filename = "output";
        String fileType = ".txt";
        String content = "Hello World!";

        if (!new File(filename + fileType).createNewFile()) 
        {
            // File cannot be created... try with a new name
            int fileNameAddition = 1;
            while (!new File(filename + fileNameAddition + fileType).createNewFile())
            {
                fileNameAddition++;
            }
            filename += fileNameAddition + fileType;
        }
        FileOutputStream out = new FileOutputStream(filename);

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
