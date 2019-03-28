
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public void decrypt(String encrypted){
        CaesarCipher code = new CaesarCipher();
        for(int i = 0; i < 26; i++ )
        System.out.println(i + ": " + code.encrypt(encrypted, i));
    }
    public void testDecrypt(int key){
        FileResource fr = new FileResource();
        String file =  fr.asString();
        CaesarCipher code = new CaesarCipher();
        String enc = code.encrypt(file, key);
        System.out.println("Original String: " + file);
        System.out.println("Encrypted String: " + enc);
        System.out.println("Decrypted String: ");
        decrypt(enc);
    }
    public String halfOfString(String message, int start){
        char[] temp = message.toCharArray(); 
        StringBuilder ret = new StringBuilder();
        for (int i = start ; i < temp.length; i += 2){
            ret.append(temp[i]);
        }

        return ret.toString();
    }
    public int getKey(String s){
        int index = maxIndex(countLetters(s));
        System.out.println(index);
        return 26-index;
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
    public String decryptTwoKeys(String encrypted){
        String one = halfOfString(encrypted, 0);
        String two = halfOfString(encrypted, 1);
        int keyOne = getKey(one);
        int keyTwo = getKey(two);
        CaesarCipher code = new CaesarCipher();
        return code.encryptTwoKeys(encrypted, keyOne, keyTwo);
    }
    public void testDecryptTwoKeys(int key1, int key2){
        FileResource fr = new FileResource();
        String file =  fr.asString();
        CaesarCipher code = new CaesarCipher();
        String enc = code.encryptTwoKeys(file, key1, key2);
        System.out.println("Original String: " + file);
        System.out.println("Encrypted String: " + enc);
        System.out.println("Decrypted String: " + decryptTwoKeys(enc));
    }
}
