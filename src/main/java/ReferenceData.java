import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekeocha on 5/30/2016.
 */
public class ReferenceData {
    private List<String> properNouns = new ArrayList<String>();
    private static ReferenceData referenceData;

    private ReferenceData(){
        loadProperNouns();
    }

    public static ReferenceData getInstance(){
        if(referenceData == null){
            referenceData = new ReferenceData();
        }
        return referenceData;
    }

    private void loadProperNouns(){
        try {
            //For now I just hard coded a location for RefData. Could be passed in as input
            Reader reader = new FileReader("C:\\Users\\Ekeocha\\IdeaProjects\\nlp-project\\src\\main\\resources\\refData\\NER.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String current;
            while ((current = bufferedReader.readLine()) != null) {
                if(!current.isEmpty()) properNouns.add(current);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<String> getProperNouns(){
        return this.properNouns;
    }
}
