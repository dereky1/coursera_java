
/**
 * Write a description of WordsInFiles here.
 * 
 * @derek   
 * @1.0.0
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList> hash;
    
    public WordsInFiles(){
        hash = new HashMap<String, ArrayList>();
    }
    
    private void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for(String s : fr.words()){
            ArrayList<String> fileNames = (hash.containsKey(s)) ? hash.get(s) : new ArrayList<String>();
            if(!fileNames.contains(fileName)){
                fileNames.add(fileName);
                hash.put(s, fileNames);
            }
        }
    }
    
    public void buildWordFileMap(){
        hash.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String key : hash.keySet()){
            int temp = hash.get(key).size();
            max = (temp > max) ? temp : max;
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String key: hash.keySet()){
            if(hash.get(key).size() == number)
                words.add(key);
        }
        return words;
    }
    
    public void printFilesIn(String word){
        if(hash.containsKey(word)){
            ArrayList<String> temp = hash.get(word);
            for(int i = 0; i < temp.size(); i++)
                System.out.println(temp.get(i));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("Max Files Word: " + maxNumber());
        System.out.println("Words in 3 Files: ");
        ArrayList<String> temp = wordsInNumFiles(3);
        for(int i = 0; i < temp.size(); i++) 
            System.out.println(temp.get(i));
        System.out.println("Files that 'cats' appears in: ");
        printFilesIn("cats");  
    }
}
