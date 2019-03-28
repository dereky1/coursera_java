import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    public VigenereBreaker(){
        FileResource fr = new FileResource();
        String mess = fr.asString();
        HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
        FileResource dictfr = new FileResource("dictionaries/Italian");
        HashSet<String> dictionary = readDictionary(dictfr);
        dicts.put("Italian", dictionary);
        dictfr = new FileResource("dictionaries/Danish");
        dictionary = readDictionary(dictfr);
        dicts.put("Danish", dictionary);
        dictfr = new FileResource("dictionaries/Dutch");
        dictionary = readDictionary(dictfr);
        dicts.put("Dutch", dictionary);
        dictfr = new FileResource("dictionaries/English");
        dictionary = readDictionary(dictfr);
        dicts.put("English", dictionary);
        dictfr = new FileResource("dictionaries/French");
        dictionary = readDictionary(dictfr);
        dicts.put("French", dictionary);
        dictfr = new FileResource("dictionaries/German");
        dictionary = readDictionary(dictfr);
        dicts.put("German", dictionary);
        dictfr = new FileResource("dictionaries/Portuguese");
        dictionary = readDictionary(dictfr);
        dicts.put("Portuguese", dictionary);
        dictfr = new FileResource("dictionaries/Spanish");
        dictionary = readDictionary(dictfr);
        dicts.put("Spanish", dictionary);
        System.out.println(breakForAllLangs(mess, dicts));
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
        StringBuilder mess = new StringBuilder(message);
        for(int i = whichSlice ; i <message.length(); i += totalSlices)
            slice.append(mess.charAt(i));
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i = 0; i < klength; i++)
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String file = fr.asString();
        int[] key = tryKeyLength(file, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(file));
        //for(int i = 0 ; i < key.length; i++)
        //    System.out.println(key[i]);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for(String s: fr.lines())
            hs.add(s.toLowerCase());
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String best = "";
        int count = 0;
        int keylen = 0;
        for(int i=1; i < 100; i++){
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, i, mostCommonCharIn(dictionary)));
            String temp = vc.decrypt(encrypted);
            int words = countWords(temp, dictionary);
            if(words > count){
                count = words;
                best = temp;
                keylen = i;
            }
        }
        System.out.println("Words: " + count + " Length of Key: " + keylen);
        return best;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>();
        for(String word : dictionary){
            StringBuilder string = new StringBuilder(word);
            for(int i = 0; i < string.length(); i++){
                if(!alphabet.containsKey(string.charAt(i)))    
                    alphabet.put(string.charAt(i),1);
                else
                    alphabet.put(string.charAt(i), alphabet.get(string.charAt(i)) + 1);
            }
        }
        int max = 0;
        char ret = 0;
        for(char key : alphabet.keySet()){
            if(alphabet.get(key) > max){
                max = alphabet.get(key);
                ret = key;
            }
        }
        return ret;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        String best = "";
        String lan = "";
        for(String lang : languages.keySet()){
            String temp = breakForLanguage(encrypted, languages.get(lang));
            int tempcount = countWords(temp, languages.get(lang));
            if(tempcount > max){
                max = tempcount;
                best = temp;
                lan = lang;
            }
        }
        System.out.println("language: " + lan + " words: " + max);
        return best;
    }
}
