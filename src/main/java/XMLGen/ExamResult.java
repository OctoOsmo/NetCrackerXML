package XMLGen;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by al on 12.12.2015.
 */

@XmlType(name = "ExamResult", propOrder = {"course_name", "grade"})
public class ExamResult {
    String course_name;
    Integer grade;

    public ExamResult(){}

    public ExamResult(String course_name, Integer grade) {
        this.grade = grade;
        this.course_name = course_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
