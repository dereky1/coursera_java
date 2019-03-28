/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(myWords.indexOf(word),1);
            }
            else{
                index = myWords.indexOf(word);
                myFreqs.set(index, myFreqs.get(index) + 1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int maxValue = 0;
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i++){
            int value = myFreqs.get(i);
            if(value > maxValue){
                maxValue = value;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        findUnique();
        System.out.println("Number of Unique Words: " + myFreqs.size());
        //for(int i = 0; i < myWords.size(); i++)
        //   System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        int maxIndex = findIndexOfMax();
        System.out.println("Max Index: " + maxIndex + "-" + myWords.get(maxIndex));
    }
}
