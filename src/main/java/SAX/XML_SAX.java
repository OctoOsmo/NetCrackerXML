package SAX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by al on 25.12.2015.
 */
public class XML_SAX extends DefaultHandler{

    final Logger log = LogManager.getLogger(XML_SAX.class);

    private File file;
    int deep = 0;

    private String getTabDeep(){
        String res = "";
        for (int i = 0; i < deep; i++) {
            res += "\t";
        }
        return res;
    }

    public XML_SAX(String fileName){
        file = new File(fileName);
    }

    @Override
    public void startDocument(){
        log.info("Parsing with SAX started");
    }

    @Override
    public void endDocument(){
        log.info("Parsing with SAX ended");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String rawName, Attributes attrs)
    {
        String msg = rawName;
        for (int i = 0; i < attrs.getLength(); i++) {
            msg += " [" + attrs.getLocalName(i)+ " " + attrs.getValue(i) + "]";
        }
        log.info(getTabDeep() + msg);
        deep++;
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String rawName)
    {
        deep--;
    }

    public void characters(char ch[], int start, int length){
        String msg = new String(ch, start, length);
        if(msg.trim().length() > 0)
            log.info(getTabDeep() + " > " + msg + " < ");
    }

    public void parse(){
        try{
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            sp.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
