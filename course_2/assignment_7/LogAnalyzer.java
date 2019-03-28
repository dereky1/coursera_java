
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines())
            records.add(WebLogParser.parseEntry(line));
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++){
             String ipadd = records.get(i).getIpAddress();
             if(!unique.contains(ipadd))
                unique.add(ipadd);
         }
         return unique.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(int i = 0; i < records.size(); i++){
             ArrayList<LogEntry> log = new ArrayList<LogEntry>();
             int stat = records.get(i).getStatusCode();
             if(stat > num){
                log.add(records.get(i));
                System.out.println(records.get(i));
            }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> unique = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++){
             Date date = records.get(i).getAccessTime();
             String ipadd = records.get(i).getIpAddress();
             if(date.toString().substring(4,10).equals(someday))
                if(!unique.contains(ipadd))
                    unique.add(ipadd);
         }
         return unique;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> log = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++){
             int stat = records.get(i).getStatusCode();
             String ipadd = records.get(i).getIpAddress();
             if(stat >= low && stat <= high){
                if(!log.contains(ipadd))
                    log.add(ipadd);
             }
         }
         return log.size();
     }
     
     public HashMap<String,Integer> countsVisitsPerIP(){
         HashMap<String,Integer> count = new HashMap<String,Integer>();
         for(int i = 0; i < records.size(); i++){
            String ip = records.get(i).getIpAddress();
            if(!count.containsKey(ip))
                count.put(ip, 1);
            else
                count.put(ip, count.get(ip) + 1);
         }
         return count;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> hash){
        int max = 0;
         for(String key : hash.keySet()){
             if(hash.get(key) > max)
                 max = hash.get(key);
        }
        return max;
    }
    
    public ArrayList<String> IPsMostVisits(HashMap<String, Integer> hash){
        int max = mostNumberVisitsByIP(hash);
        ArrayList<String> maxIPs = new ArrayList<String>();
        for(String key: hash.keySet()){
            if(hash.get(key) == max){
                if(!maxIPs.contains(key))
                    maxIPs.add(key);
            }    
        }
        return maxIPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> hash = new HashMap<String,ArrayList<String>>();
        for(int i = 0 ; i < records.size(); i++){
            String date = records.get(i).getAccessTime().toString().substring(4,10);
            String ip = records.get(i).getIpAddress();
            ArrayList<String> temp;
            if(!hash.containsKey(date))
                temp = new ArrayList<String>();
            else
                temp = hash.get(date);
            temp.add(ip);
            hash.put(date, temp);
        }
        return hash;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hash){
        String date = "";
        int max = 0;
        for(String key : hash.keySet()){
            int size = hash.get(key).size();
            if(size > max){
                date = key;
                max = size;
            }
        }
        return date;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> hash, String day){
        ArrayList<String> ipsOnDay = hash.get(day);
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for(int i=0; i<ipsOnDay.size(); i++){
            String temp = ipsOnDay.get(i);
            if(!count.containsKey(temp))
                count.put(temp, 1);
            else
                count.put(temp, count.get(temp) + 1);
        }
        return IPsMostVisits(count);
    }
}
