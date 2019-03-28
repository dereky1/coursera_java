
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @derek
 * @1.0.0
 * 
 **/
 import edu.duke.*;
 
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        char[] temp = message.toCharArray(); 
        StringBuilder ret = new StringBuilder();
        for (int i = start ; i < temp.length; i += 2){
            ret.append(temp[i]);
        }

        return ret.toString();
    }
    public int[] countLetters(String s){
        int[] counts = new int[26];
        char[] temp = s.toCharArray();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        for(int i = 0; i < temp.length; i++){
            index = alpha.indexOf(Character.toLowerCase(temp[i]));
            if(index != -1)
                counts[index]++;
        }
        return counts;
    }
    public int maxIndex(int[] counts){
        int max = 0;
        int maxValue = 0;
        for (int i = 0; i < counts.length; i++){
            if(counts[i] > maxValue){
                max = i;
                maxValue = counts[i];
            }
        }
        return max;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        String encrypted = cc2.encrypt(fr.asString());
        System.out.println("Encrypted String: " + encrypted);
        //System.out.println("Decrypted String: " + cc2.decrypt(encrypted));
        System.out.println("Decrypted String: " + breakCaesarCipher(encrypted));
    }
    public String breakCaesarCipher(String input){
        String frag1 = halfOfString(input, 0);
        String frag2 = halfOfString(input, 1);
        int newKey1 = maxIndex(countLetters(frag1))-4;
        int newKey2 = maxIndex(countLetters(frag2))-4;
        CaesarCipherTwo cc2 = new CaesarCipherTwo(newKey1,newKey2);
        return cc2.decrypt(input);
    }
}
