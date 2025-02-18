// JD Waldron
// Rule 00: IDS16-J. Prevent XML injection

package main.java.jd;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class IDS16_J
{
    /**
     * The main method
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        BufferedOutputStream bufOut = new BufferedOutputStream(System.out);
        try
        {
            OnlineStore.createXMLStream(bufOut, "2");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

class OnlineStore
{
    /**
     * A method that creates an XML stream to protect against XML injection
     * 
     * @param outStream the stream to output to
     * @param quantity  the number of items
     * @throws IOException
     * @throws NumberFormatException
     */
    static void createXMLStream(final BufferedOutputStream outStream, final String quantity)
            throws IOException, NumberFormatException
    {
        // Write XML string only if quantity is an unsigned integer (count).
        int count = Integer.parseUnsignedInt(quantity);
        String xmlString = "<item>\n<description>Widget</description>\n" + "<price>500</price>\n" + "<quantity>" + count
                + "</quantity></item>";
        outStream.write(xmlString.getBytes());
        outStream.flush();
    }
}
