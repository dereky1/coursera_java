
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("short-test_log");
        logan.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("short-test_log");
        System.out.println("Unique IPs: " + logan.countUniqueIPs());
    }
    
    public void testHigherNumber(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        logan.printAllHigherThanNum(400);
    }
    
    public void testIPOnDay() {
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        ArrayList<String> u = logan.uniqueIPVisitsOnDay("Mar 17");
        for(int i = 0; i < u.size(); i++)
            System.out.println(u.get(i));
        System.out.println("Array Size: " + u.size());
    }
    
    public void testUniqueIPInRange(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Unique IPs: " + logan.countUniqueIPsInRange(200,299));
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Unique IPs Map: " + logan.countsVisitsPerIP());
    }
    
    public void testMaxCountVisitsPerIP(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Unique IPs Map: " + logan.mostNumberVisitsByIP(logan.countsVisitsPerIP()));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Most IPs Visited Map: " + logan.IPsMostVisits(logan.countsVisitsPerIP()));
    }
    
    public void testIPsForDays(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog3-short_log");
        System.out.println("Ips for Days Map: " + logan.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Day with most Visits: " + logan.dayWithMostIPVisits(logan.iPsForDays()));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer logan = new LogAnalyzer();
        logan.readFile("weblog1_log");
        System.out.println("Sep 30: " + logan.iPsWithMostVisitsOnDay(logan.iPsForDays(),"Mar 17"));
    }
}
