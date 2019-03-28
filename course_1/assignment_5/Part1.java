
/**
 * Write a description of Part1 here.
 * 
 * @derek 
 * @1.0.0
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public String countryInfo(CSVParser parser, String country){
        String ret="NOT FOUND";
        String index;
        for(CSVRecord record : parser){
            index = record.get("Country");
            if(index.equals(country))
                ret = new String(index + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
        }
        return ret;
    }
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        String index;
        for(CSVRecord record: parser){
            index = record.get("Exports");
            if(index.contains(exportitem1) && index.contains(exportitem2))
                System.out.println(record.get("Country"));
        }    
    }
    public int numberOfExporters(CSVParser parser, String exportitem){
        int count = 0;
        for(CSVRecord record: parser){
            if(record.get("Exports").contains(exportitem))
                count++;
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        String value;
        int amountSize = amount.length();
        for (CSVRecord record: parser){
            value = record.get("Value (dollars)");
            if(value.length() > amountSize)
                System.out.println(record.get("Country") + " " + value);
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing countryInfo:\n" + countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        System.out.println("Testing listExportersTwoProducts:");
        listExportersTwoProducts(parser, "fish", "nuts");
        parser = fr.getCSVParser();
        System.out.println("Testing numberOfExporters:\n" + numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser();
        System.out.println("Testing bigExporters:");
        bigExporters(parser, "$999,999,999,999");
    }
}
