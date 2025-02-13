// JD Waldron
// Rule 00: IDS00-J. Prevent SQL Injection
// Rec. 05: OBJ51-J. Minimize the accessibility of classes and their members

package main.java.jd;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class IDS00_J
{
    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        String pWord = "password";
        try
        {
            doPrivilegedAction("jerwal17", pWord.toCharArray());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("SQL Error in Main");
        }
    }

    /**
     * Dummy method that represents doing an action that needs higher credentials
     * 
     * @param username
     * @param password
     * @throws SQLException
     */
    static void doPrivilegedAction(String username, char[] password) throws SQLException
    {
        Connection connection = getConnection();
        if (connection == null)
        {
            System.err.println("Connection is null");
            System.exit(1);
        }
        try
        {
            String pwd = hashPassword(password);

            // Validate username length
            if (username.length() > 8)
            {
                System.err.println("Username longer than 8 characters");
                System.exit(1);
            }

            String sqlString = "select * from db_user where username=? and password=?";
            PreparedStatement stmt = connection.prepareStatement(sqlString);
            stmt.setString(1, username);
            stmt.setString(2, pwd);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
            {
                throw new SecurityException("User name or password incorrect");
            }

            // Authenticated; proceed
            System.out.println("Authenticated");
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException x)
            {
                System.err.println("Connection did not close");
            }
        }
    }

    /**
     * Method that gets the connection to the SQL database
     * 
     * @return the connection if successful, null if not
     */
    static Connection getConnection()
    {
        String connectionString = "jdbc:mysql://localhost:3306/it355_group_project";
        String username = "root";
        String password = "password";

        try
        {
            return DriverManager.getConnection(connectionString, username, password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that hashes and returns the given password
     * 
     * @param password
     * @return the hashed password
     */
    static String hashPassword(char[] password)
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKeyFactory factory = null;
        try
        {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        byte[] hash = "".getBytes();

        try
        {
            hash = factory.generateSecret(spec).getEncoded();
        }
        catch (Exception e)
        {
            return "";
        }

        return hash.toString();
    }
}
