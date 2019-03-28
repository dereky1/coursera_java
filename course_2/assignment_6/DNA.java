
/**
 * Write a description of DNA here.
 * 
 * @derek
 * @1.0.0
 */
import java.util.*;

public class DNA {
    private HashMap<String, Integer> dnaCount;
    
    public DNA(){
        dnaCount = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        dnaCount.clear();
        int dnaSize = dna.length();
        for(int i = start; i < dnaSize; i += 3){
            if(i+3 <= dnaSize){
                String key = dna.substring(i, i+3);
                int count = (dnaCount.containsKey(key)) ? dnaCount.get(key) : 0;
                dnaCount.put(key, count + 1);
            }
        }
        //for(String s : dnaCount.keySet())
        //    System.out.println(s + ": " + dnaCount.get(s));
    }
    
    public String getMostCommonCodon(){
        String dna = "";
        int count = 0;
        for(String s : dnaCount.keySet()){
            int temp = dnaCount.get(s);
            if(temp > count){
                dna = s;
                count = temp;
            }
        }    
        return dna;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : dnaCount.keySet()){
            int count = dnaCount.get(s);
            if(count >= start && count <= end)
                System.out.print(s + ": " + count + "\n");
        }
    }
}
