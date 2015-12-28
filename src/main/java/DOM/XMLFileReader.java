package DOM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

/**
 * Created by al on 13.12.2015.
 */
public class XMLFileReader {

    final Logger log = LogManager.getLogger(XMLFileReader.class);

    File file;
    XPath xpath = XPathFactory.newInstance().newXPath();

    public XMLFileReader(String fileName) {
        file = new File(fileName);
    }

    public void readToDOM() throws ParserConfigurationException, IOException, SAXException {
        log.debug("Parsing with DOM only");
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        log.info("Root element: " + doc.getDocumentElement().getNodeName());
        printNodeList(doc.getElementsByTagName("Root"));
    }

    private void printNodeList(NodeList nl){
        printNodeList(nl, "");
    }

    private void printNodeList(NodeList nl, String deep){
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getChildNodes().getLength() < 1 && nl.item(i).getTextContent().trim().length() > 0)
            {
                String nodeValue = deep + nl.item(i).getParentNode().getNodeName()
                        + " ["+nl.item(i).getTextContent().trim() + "]";
                log.info(nodeValue);

            }else{
                printNodeList(nl.item(i).getChildNodes(), deep + " ");
            }
        }
    }

    public void readWithXPath()
            throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
//        InputSource inputSource = new InputSource("generated.xml");
        log.debug("Parsing with XPath");
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        String expression = "/Root/groups/students/student";
        NodeList nodes = (NodeList)xpath.evaluate(expression, doc, XPathConstants.NODESET);
        nodes = (NodeList)xpath.evaluate(expression, nodes.item(0), XPathConstants.NODESET);
        log.info("students in current XML file:");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node element = nodes.item(i);
            NamedNodeMap nnm = element.getAttributes();
            log.info(nnm.item(0));
            NodeList nameNode = (NodeList)xpath.evaluate("child::name", element, XPathConstants.NODESET);
            log.info(nameNode.item(0).getTextContent());
            NodeList famNode = (NodeList)xpath.evaluate("child::family", element, XPathConstants.NODESET);
            log.info(famNode.item(0).getTextContent());
        }
    }
}
