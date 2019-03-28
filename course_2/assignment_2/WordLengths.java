
/**
 * Write a description of WordLengths here.
 * 
 * @derek
 * @1.0.0
 * 
 * */
 import edu.duke.*;
 
public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts){
        char[] temp;
        for(String word : fr.words()){
            int size = word.length();
            int symbols = 0;
            temp = word.toCharArray();
            if(!Character.isLetter(temp[0])) 
                symbols++;
            if(!Character.isLetter(temp[size-1])) 
                symbols++;
            if(size-symbols-1 > counts.length)
                counts[counts.length-1]++;
            else 
                counts[size-symbols]++;
        }
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for(int i = 0; i < counts.length; i++)
            System.out.println("Words of length " + i + ": " + counts[i]);
        System.out.println("Max Index at " + indexOfMax(counts));
    }
    public int indexOfMax(int[] values){
        int index = 0;
        for(int i=0; i < values.length; i++){
            if(values[i] > values[index])
                index = i;
        }
        return index;
    }
}
