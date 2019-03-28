/**
 * Write a description of Part1 here.
 * 
 * @derek
 * @1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest = null;
        double cold = Double.POSITIVE_INFINITY;
        double index;
        for(CSVRecord record : parser){
            index = Double.parseDouble(record.get("TemperatureF"));
            if(index == -9999.0)
                continue;
            if(index < cold){
                coldest = record;
                cold = index;
            }
        }
        return coldest;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing coldestHourInFile:\n"+coldestHourInFile(parser).get("TemperatureF"));
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldest = null;
        CSVRecord index = null;
        String ret = "";
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            index = coldestHourInFile(parser);
            if(coldest == null)
                coldest = index;
            else if(Double.parseDouble(index.get("TemperatureF")) < Double.parseDouble(coldest.get("TemperatureF"))){
                coldest = index;
                ret = f.getName();
            }
        }
        return ret;
    }
    public void testFileWithColdestTemperature(){
        String file = fileWithColdestTemperature();
        System.out.println(file);
        FileResource fr = new FileResource("nc_weather/2014/"+file);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record =  coldestHourInFile(parser);
        System.out.println("Coldest day was in file "+ file);
        System.out.println("Coldest temperature on that day was "+ record.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest dat were:"); 
        parser = fr.getCSVParser();
        for(CSVRecord r : parser){
            System.out.println(r.get("DateUTC") + ": " + r.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null;
        String humidity;
        double index;
        double low = Double.POSITIVE_INFINITY;
        for(CSVRecord r : parser){
            humidity = r.get("Humidity");
            if(humidity.equals("N/A"))
                continue;
            index = Double.parseDouble(humidity);
            if(index < low){
                low = index;
                lowest = r;
            }
        }
        return lowest;
    }   
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowest = null;
        CSVRecord index = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            index = lowestHumidityInFile(parser);
            if(lowest == null)
                lowest = index;
            else if(Double.parseDouble(index.get("Humidity")) < Double.parseDouble(lowest.get("Humidity")))
                lowest = index;
        }
        return lowest;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double avg = 0;
        int count = 0;
        String humidity = "";
        for(CSVRecord r : parser){
            humidity = r.get("TemperatureF");
            if(humidity.equals("N/A"))
                continue;
            avg += Double.parseDouble(humidity);
            count++;
        }
        return avg/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double avg = 0, holder;
        int count = 0;
        String humidity = "";
        for(CSVRecord record : parser){
            humidity = record.get("Humidity");
            if(humidity.equals("N/A") || Double.parseDouble(humidity) < value)
                continue;
            avg += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return (count == 0) ? -1 : avg/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg == -1)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average temperature when high Humidity is " + avg);        
    }
}
