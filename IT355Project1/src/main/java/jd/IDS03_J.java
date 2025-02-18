// JD Waldron
// Rule 00: IDS03-J. Do not log unsanitized user input
// Rec. 05: OBJ50-J. Never confuse the immutability of a reference with that of the referenced object

package main.java.jd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IDS03_J
{
    /**
     * The main method
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Logger logger = new Logger();
        String username = "jerwal17";
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username: ");
        String inputUsername = in.nextLine();
        boolean loginSuccessful = username.equals(inputUsername);

        if (loginSuccessful)
        {
            logger.log("User login succeeded for: " + sanitizeUser(inputUsername));
        }
        else
        {
            logger.log("User login failed for: " + sanitizeUser(inputUsername));
        }

        in.close();
    }

    /**
     * A method that returns a username sanitized according to the given rules
     * 
     * @param username
     * @return the sanitized username
     */
    public static String sanitizeUser(String username)
    {
        return Pattern.matches("[A-Za-z0-9_]+", username) ? username : "unauthorized user";
    }
}

class Logger
{
    private final OutputStream outStream;

    Logger()
    {
        outStream = System.out;
    }

    Logger(OutputStream oStream)
    {
        outStream = oStream;
    }

    /**
     * A method that writes the input to the logger's outputStream
     * 
     * @param input
     */
    void log(String input)
    {
        input += "\n";
        try
        {
            outStream.write(input.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
