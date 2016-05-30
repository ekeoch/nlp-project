import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Ekeocha on 5/30/2016.
 */
public class NLPEntryPoint {
    private static int threadCount = 5;
    private static final String THREAD_OVERRIDE_PROPERTY = "threadCount";

    //To test the program, I ran with the following input
    //"C:\Users\Ekeocha\IdeaProjects\nlp-project\src\main\resources" output.xml
    //Change accordingly to suite your need also remember to specify the NER location in ReferenceData.java
    public static void main(String[] args) throws InterruptedException, ExecutionException, JAXBException {
        validateArgs(args);

        //Grant user ability to override thread count
        String threadOverrideProperty = System.getProperty(THREAD_OVERRIDE_PROPERTY);
        if(threadOverrideProperty != null && !threadOverrideProperty.isEmpty()){
            int threadCountOverride = Integer.getInteger(threadOverrideProperty).intValue();
            if(threadCountOverride > 0){
                threadCount = threadCountOverride;
            }
        }

        TextFiles textFiles = new TextFiles();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        File[] inputDirectoryContent = new File(args[0]).listFiles();
        List<Callable<TextFile>> inputFiles = new ArrayList<Callable<TextFile>>();
        for(File file : inputDirectoryContent){
            if(!file.isDirectory() && !file.isHidden()) inputFiles.add(new TextFileParser(file.getAbsolutePath()));
        }
        List<Future<TextFile>> processedFiles = executorService.invokeAll(inputFiles);

        for(Future<TextFile> textFile : processedFiles){
            textFiles.getTextFiles().add(textFile.get());
        }
        executorService.shutdown();

        //Marshall all TextFiles processed into one file
        JAXBContext jaxbContext = JAXBContext.newInstance(TextFiles.class, TextFile.class, Word.class, Sentence.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(textFiles, new File(args[0] + "\\" + args[1]));
    }

    private static void validateArgs(String[] args){
        //Will just out aggregate file to same dir as input
        //Can also include a 3rd argument in specify the location of the proper nouns file
        final String usage = "Usage: SentenceParse [input dir] [output filename]";
        if(args.length != 2){
            System.out.println("Incorrect number of arguments, see usage.\n");
            System.out.println(usage);
            System.exit(1);
        }

        File file = new File(args[0]);

        if(!file.exists()){
            System.out.println("Input File path or directory doesn't exist.\n");
            System.out.println(usage);
            System.exit(2);
        }
        //If not directory process everything in that directory
        else if(file.isFile()){
            System.out.println("File input detected, using parent directory...");
            args[0] = file.getParent();
        }

    }
}
