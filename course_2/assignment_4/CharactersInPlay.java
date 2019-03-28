
/**
 * Write a description of CharactersInPlay here.
 * 
 * @derek
 * @1.0.0
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        person = person.toLowerCase();
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            freqs.add(1);
        }
        else{
            index = names.indexOf(person);
            freqs.set(index, freqs.get(index) + 1);
        }
    }
    
    public void findAllCharacter(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int period = line.indexOf(".");
            if(period == -1)
                continue;
            update(line.substring(0,period));
        }
    }
    
    public void tester(){
        findAllCharacter();
        for(int i = 0; i < names.size(); i++)
            System.out.println(names.get(i) + ": " + freqs.get(i));
        System.out.println("\nCharacter in Range: ");
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < names.size(); i++){
            int freq = freqs.get(i);
            if(freq >= num1 && freq <= num2)
                System.out.println(names.get(i) + ": " + freq);
        }
    }
}
