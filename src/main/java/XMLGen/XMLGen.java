package XMLGen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by al on 13.12.2015.
 */
public class XMLGen {

    final Logger log = (Logger) LogManager.getLogger(XMLGen.class);

    public void generateXML() {


        XmlRoot root = new XmlRoot();
        ArrayList<Group> groups = new ArrayList<>();

        Group g1 = new Group(1, "ИВТ-132");

        ArrayList<Student> students = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("D.y", Locale.ENGLISH);

        Student student1 = new Student();
        Student student2 = new Student();

        student1.setId(1);
        student1.setName("Андрей");
        student1.setFamily("Петров");
        try {
            student1.setBirth_date(df.parse("311.1994"));
            student2.setBirth_date(df.parse("11.1993"));
        } catch (ParseException e) {
            log.error(e.getMessage());
            log.error("Date of birth has not been set");
        }
        ArrayList<ExamResult> examResults1 = new ArrayList<>();
        examResults1.add(new ExamResult("Гистология", 56));
        examResults1.add(new ExamResult("Правоведение", 98));
        examResults1.add(new ExamResult("Теория графов", 73));
        student1.setExamResults(examResults1);

        student2.setId(2);
        student2.setName("Григорий");
        student2.setFamily("Астапенко");
        ArrayList<ExamResult> examResults2 = new ArrayList<>();
        examResults2.add(new ExamResult("Гистология", 42));
        examResults2.add(new ExamResult("Правоведение", 56));
        examResults2.add(new ExamResult("Теория графов", 69));
        student2.setExamResults(examResults2);

        students.add(student1);
        students.add(student2);
        g1.setStudents(students);
        groups.add(g1);

        root.setGroups(groups);

        try {
            JAXBContext context = JAXBContext.newInstance(XmlRoot.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "schema1.xsd");
            marshaller.marshal(root, new File("generated.xml"));

            SchemaOutputResolver sor = new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                    return new StreamResult(new File(suggestedFileName));
                }
            };
            context.generateSchema(sor);
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage());
        }
    }

}
