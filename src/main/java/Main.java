import XMLGen.XMLGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;

/**
 * Created by al on 07.12.2015.
 */
public class Main {

    static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        XMLGen gen = new XMLGen();
        gen.generateXML();

    }
}
