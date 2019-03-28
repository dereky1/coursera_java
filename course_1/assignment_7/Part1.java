
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100)
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
        }
    }
    public void totalBirths(FileResource fr){
        int totalBirths = 0, totalBoys = 0, totalGirls = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M"))
                totalBoys += numBorn;
            else
                totalGirls += numBorn;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource("us_babynames_test/example-small.csv");
        totalBirths(fr);
    }
    public int getRank(String year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int rank = 1;
        boolean stop = false;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(!rec.get(1).equals(gender))
                continue;
            if(rec.get(0).equals(name))
                return rank;
            if(!stop)
                rank++;
        }
        return -1;
    }
    public String getName(String year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int index = 1;
        boolean stop = false;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(!rec.get(1).equals(gender))
                continue;
            if(index == rank)
                return rec.get(0);
            if(!stop)
                index++;
        }
        return "NO NAME";
    }
    public String whatIsNameInYear(String name, String year, String newYear, String gender){
        int rank = getRank(year, name, gender);
        return getName(newYear, rank, gender);
    }
    public int yearOfHighestRank(String name, String gender){
        int highest = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String year = f.getName().substring(3,7);
            FileResource fr = new FileResource(f);
            for (CSVRecord rec: fr.getCSVParser(false)){
                if(!rec.get(1).equals(gender))
                    continue;
                if(rec.get(0).equals(name)){
                    int number = Integer.parseInt(rec.get(2));
                    if(number > highest){
                        highest = Integer.parseInt(year);
                        break;
                    }
                }
            }
        }
        return highest;
    }
    public double getAverageRank(String name, String gender){
        double avgRank = -1.0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String year = f.getName().substring(3,7);
            double fileRank = getRank(year, name, gender);
            if(fileRank > 0){
                if(avgRank == -1.0)
                    avgRank = 0;
                avgRank += fileRank;
                count++;
            }
        }
        return avgRank/count;
    }    
    public int getTotalBirthsRankedHigher(String year, String name, String gender){
        int count = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(!rec.get(1).equals(gender))
                continue;
            if(rec.get(0).equals(name))
                break;
            count += Integer.parseInt(rec.get(2));
        }
        return count;
    }
}
