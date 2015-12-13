package XMLGen;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by al on 12.12.2015.
 */


@XmlRootElement
public class Group {

    Integer id;
    String name;
    ArrayList<Student> students;

    public Group(){}

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(Integer id, String name, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public Group(Integer id) {

        this.id = id;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
