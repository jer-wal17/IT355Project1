// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO08-J. Distinguish between characters or bytes read from a stream and -1
// TODO: Recommendation 13: FIO51-J. Identify files using multiple file attributes

package main.java.derek;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class FIO08_J
{
    private static Wrapper wrapper;

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        wrapper = new Wrapper();
        File inFile = new File("test.txt");
        System.out.println(wrapper.outputFile(inFile));
    }
    
    private static class Wrapper 
    {
        public Wrapper() 
        {
            return;
        }
        
        public String outputFile(File inFile) throws FileNotFoundException, IOException
        {
            String fileContents = "";
            try 
            {
                FileInputStream in = new FileInputStream(inFile);

                // Noncompliant implementation:
                // byte data;
                // while ((data = (byte) in.read()) != -1) {
                //     fileContents += (char)data;
                // }

                // Compliant implementation:
                int inbuff;
                while ((inbuff = in.read()) != -1) {
                    fileContents += (char)inbuff;
                }
                in.close();
            }
            catch (FileNotFoundException e) 
            {
                throw e;
            }
            catch (IOException e) 
            {
                throw e;
            }
            return fileContents;
        }
    }
}
