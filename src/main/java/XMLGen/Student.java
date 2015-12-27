package XMLGen;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


/**
 * Created by al on 12.12.2015.
 */

//@XmlType(propOrder = {"examResults"}, name = "student")
public class Student extends Person {

    Integer id;
    ArrayList<ExamResult> examResults;

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ExamResult> getExamResults() {
        return examResults;
    }

    @XmlElementWrapper(name = "examResults")
    @XmlElement(name = "examResult")
    public void setExamResults(ArrayList<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public void addExamResult(ExamResult examResult){
        examResults.add(examResult);
    }

    public void clearExamResults(){
        examResults.clear();
    }
}
