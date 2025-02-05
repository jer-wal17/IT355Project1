// JD Waldron
// Rule 00: IDS00-J. Prevent SQL Injection

package main.java.jd;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class IDS00_J
{
    public static void main(String[] args)
    {

    }

    public void doPrivilegedAction(String username, char[] password) throws SQLException
    {
        Connection connection = getConnection();
        if (connection == null)
        {
            // Handle error
        }
        try
        {
            String pwd = hashPassword(password);

            // Validate username length
            if (username.length() > 8)
            {
                // Handle error
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
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException x)
            {
                // Forward to handler
            }
        }
    }

    public Connection getConnection()
    {
        String connectionString = "jdbc:mysql://localhost:3306/?user=root";
        String username = "root";
        String password = "password";

        try
        {
            return DriverManager.getConnection(connectionString, username, password);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    // TODO
    public String hashPassword(char[] password)
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

        byte[] hash = factory.generateSecret(spec).getEncoded();
    }
}
