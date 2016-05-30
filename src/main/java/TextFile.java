import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekeocha on 5/29/2016.
 */
@XmlRootElement(name = "file")
public class TextFile {
    private List<Sentence> sentences;
    private String fileName;

    @XmlAttribute(name = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlElement(name = "sentence")
    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public TextFile(){
        this.sentences = new ArrayList<Sentence>();
    }

    public void addSentence(Sentence sentence){
        sentences.add(sentence);
    }
}
