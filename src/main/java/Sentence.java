import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekeocha on 5/29/2016.
 */
@XmlRootElement(name = "sentence")
public class Sentence {
    private List<Word> words;
    private RelevantEntities relevantEntities;
    private String wordDelimiter = " ";

    @XmlElement(name = "word")
    public List<Word> getWords() {
        return words;
    }

    @XmlElement(name =  "relevantEntities")
    public RelevantEntities getRelevantEntities() {
        return relevantEntities;
    }

    @XmlAttribute(name = "word_delimiter")
    public String getWordDelimiter() {
        return wordDelimiter;
    }

    public void setWordDelimiter(String wordDelimiter) {
        this.wordDelimiter = wordDelimiter;
    }

    public Sentence(){
        this.relevantEntities = new RelevantEntities();
        this.words = new ArrayList<Word>();
    }

    public void addSentenceObject(Word word){
        this.words.add(word);
    }

    public void addRelevantEntity(String entity){
        this.relevantEntities.addEntity(entity);
    }
}
