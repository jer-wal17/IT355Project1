// JD Waldron
// Rule 00: IDS07-J. Sanitize untrusted data passed to the Runtime.exec() method
// Rec. 05: OBJ58-J. Limit the extensibility of classes and methods with invariants

package main.java.jd;

import java.io.File;

public class IDS07_J
{
    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        DirList.listFiles();
    }
}

final class DirList
{
    /**
     * Method that lists the files in the current VSCode directory
     */
    public static void listFiles()
    {
        File dir = new File(System.getProperty("user.dir"));
        if (!dir.isDirectory())
        {
            System.out.println("Not a directory");
        }
        else
        {
            for (String file : dir.list())
            {
                System.out.println(file);
            }
        }
    }
}