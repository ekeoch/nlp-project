import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ekeocha on 5/30/2016.
 */
public class TextFileParser implements Callable<TextFile> {
    private String filePath;

    public TextFileParser(String filePath) {
        this.filePath = filePath;
    }

    public TextFile call() throws Exception {
        Reader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String current;
        while ((current = bufferedReader.readLine()) != null) {
            stringBuilder.append(current);
        }
        bufferedReader.close();
        Pattern pattern1 = Pattern.compile("[A-Z][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.COMMENTS);
        Pattern pattern2 = Pattern.compile("[a-zA-Z-\\d]+");

        Matcher matcher1 = pattern1.matcher(stringBuilder.toString());
        TextFile textFile = new TextFile();
        textFile.setFileName(new File(filePath).getName());

        while (matcher1.find()) {
            Sentence sentence = new Sentence();
            ReferenceData referenceData = ReferenceData.getInstance();
            for (String string : referenceData.getProperNouns()) {
                if (matcher1.group().contains(string)) sentence.addRelevantEntity(string);
            }
            Matcher matcher2 = pattern2.matcher(matcher1.group());
            while (matcher2.find()) {
                Word word = new Word();
                word.setValue(matcher2.group());
                sentence.addSentenceObject(word);
            }
            textFile.addSentence(sentence);
        }
        return textFile;
    }
}
