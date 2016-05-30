import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekeocha on 5/30/2016.
 */
@XmlRootElement(name = "textFiles")
public class TextFiles {
    private List<TextFile> textFiles;

    @XmlElement(name = "textFile")
    public List<TextFile> getTextFiles() {
        return textFiles;
    }

    public void setTextFiles(List<TextFile> textFiles) {
        this.textFiles = textFiles;
    }

    public TextFiles(){
        this.textFiles = new ArrayList<TextFile>();
    }
}
