
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @derek
 * @1.0.0
 */
import edu.duke.*;

public class TestCaesarCipher {
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
        for (int i = 0; i < counts.length; i++){
            if(counts[i] > max)
                max = i;
        }
        return max;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println("Encrypted String: " + encrypted);
        //System.out.println("Decrypted String: " + cc.decrypt(encrypted));
        CaesarCipher ccc = new CaesarCipher(breakCaesarCipher(encrypted));
        System.out.println("Decrypted String: " + ccc.decrypt(encrypted));
    }
    public int breakCaesarCipher(String input){
        return maxIndex(countLetters(input))-4;
    }
}
