package XMLGen;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

/**
 * Created by al on 13.12.2015.
 */

@XmlRootElement
@XmlType(propOrder = {"groups"}, name = "root")
public class XmlRoot {

    ArrayList<Group> groups = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

}
