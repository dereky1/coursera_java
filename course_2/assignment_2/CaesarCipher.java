
/**
 * Write a description of CaesarCipher here.
 * 
 * @derek
 * @1.0.0
 */
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder plainText = new StringBuilder(input);
        String lalpha = "abcdefghijklmnopqrstuvwxyz";
        String ualpha = lalpha.toUpperCase();
        String lower = lalpha.substring(key) + lalpha.substring(0,key);
        String upper = ualpha.substring(key) + ualpha.substring(0,key);
        for (int i = 0; i < input.length(); i++){
            char currChar = plainText.charAt(i);
            int letterIndex = lalpha.indexOf(currChar);
            if(letterIndex == -1){
                letterIndex = ualpha.indexOf(currChar);
                if(letterIndex == -1)
                    continue;
                plainText.setCharAt(i, upper.charAt(letterIndex));
            }
            else if(letterIndex != -1)
                plainText.setCharAt(i, lower.charAt(letterIndex));
        }
        return plainText.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder plainText = new StringBuilder(input);
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String ALPHA = alpha.toUpperCase();
        String key1Lower = alpha.substring(key1) + alpha.substring(0,key1);
        String key1Upper = key1Lower.toUpperCase();
        String key2Lower = alpha.substring(key2) + alpha.substring(0,key2);
        String key2Upper = key2Lower.toUpperCase();
        for (int i = 0 ; i < input.length() ; i++){
            char currChar = plainText.charAt(i);
            int currIndex = alpha.indexOf(currChar);
            boolean lowercase = true;
            if(currIndex == -1){
                currIndex = ALPHA.indexOf(currChar);
                if(currIndex == -1)
                    continue;
                lowercase = false;
            }
            if(i % 2 == 0){ //key1
                if(lowercase)
                    plainText.setCharAt(i, key1Lower.charAt(currIndex));
                else if (!lowercase)
                    plainText.setCharAt(i, key1Upper.charAt(currIndex));
            }
            else{ //key2
                if(lowercase)
                    plainText.setCharAt(i, key2Lower.charAt(currIndex));
                else if (!lowercase)
                    plainText.setCharAt(i, key2Upper.charAt(currIndex));
            }
        }
        return plainText.toString();
    }
}
