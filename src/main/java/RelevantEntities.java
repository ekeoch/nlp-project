import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekeocha on 5/29/2016.
 */
@XmlRootElement(name = "relevantEntities")
public class RelevantEntities {

    @XmlElement(name = "entity")
    public List<String> relevantEntities;

    public RelevantEntities(){
        relevantEntities = new ArrayList<String>();
    }

    public void addEntity(String entity){
        relevantEntities.add(entity);
    }

}
