// JD Waldron
// Rule 00: IDS17-J. Prevent XML External Entity Attacks

package main.java.jd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class IDS17_J
{
    private static void receiveXMLStream(InputStream inStream, DefaultHandler defaultHandler)
            throws ParserConfigurationException, SAXException, IOException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        // Create an XML reader to set the entity resolver.
        XMLReader reader = saxParser.getXMLReader();
        reader.setEntityResolver(new CustomResolver());
        reader.setContentHandler(defaultHandler);

        InputSource is = new InputSource(inStream);
        reader.parse(is);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        try
        {
            receiveXMLStream(new FileInputStream("evil.xml"), new DefaultHandler());
        }
        catch (java.net.MalformedURLException mue)
        {
            System.err.println("Malformed URL Exception: " + mue);
        }
    }
}

class CustomResolver implements EntityResolver
{
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
    {

        // Check for known good entities
        String entityPath = "file:/Users/onlinestore/good.xml";
        if (systemId.equals(entityPath))
        {
            System.out.println("Resolving entity: " + publicId + " " + systemId);
            return new InputSource(entityPath);
        }
        else
        {
            // Disallow unknown entities by returning a blank path
            return new InputSource();
        }
    }
}
