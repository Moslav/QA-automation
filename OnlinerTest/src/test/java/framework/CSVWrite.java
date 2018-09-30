package framework;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVWrite {

    private static CSVWriter writer;
    private static final Logger logger = LogManager.getRootLogger();

    public static void createCSVFileAndWriteValue(String sourcePage, String regexp){
        Pattern r = Pattern.compile(regexp);
        Matcher m = r.matcher(sourcePage);
        try {
            writer = new CSVWriter(new FileWriter(PropertyReader.getTestProperty("csv")));
            while (m.find()) {
                writer.writeNext(m.group(1).split("-"));
            }
            writer.close();
        }catch(IOException e){
            logger.info("File not exist!");
        }

    }
}
