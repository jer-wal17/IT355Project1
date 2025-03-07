// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 13: FIO08-J. Distinguish between characters or bytes read from a stream and -1
// Recommendation 13: FIO51-J. Identify files using multiple file attributes

package main.java.derek;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
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
                BasicFileAttributes attr1 = Files.readAttributes(Paths.get(inFile.toURI()), BasicFileAttributes.class);
                FileTime creation1 = attr1.creationTime();
                FileTime modified1 = attr1.lastModifiedTime();

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

                BasicFileAttributes attr2 = Files.readAttributes(Paths.get(inFile.toURI()), BasicFileAttributes.class);
                FileTime creation2 = attr2.creationTime();
                FileTime modified2 = attr2.lastModifiedTime();

                FileOutputStream out = new FileOutputStream(inFile);
                out.write(fileContents.getBytes());

                out.close();

                if ( (!creation1.equals(creation2)) ||
                    (!modified1.equals(modified2)) ) 
                {
                    // File was tampered with, handle error
                    System.out.println("The file read was different from the file being written to!");
                }
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
