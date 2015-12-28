import DOM.XMLFileReader;
import SAX.XML_SAX;
import XMLGen.XMLGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by al on 07.12.2015.
 */
public class Main {

    static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        XMLGen gen = new XMLGen();
        gen.generateXML();
        XMLFileReader reader = new XMLFileReader("generated.xml");
        try {
            reader.readToDOM();
            reader.readWithXPath();
        } catch (XPathExpressionException | IOException | SAXException | ParserConfigurationException e) {
            log.error(e.getMessage());
        }
        XML_SAX sax = new XML_SAX("generated.xml");
        sax.parse();
    }
}
