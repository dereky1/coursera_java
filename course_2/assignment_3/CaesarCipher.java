
/**
 * Write a description of CaesarCipher here.
 * 
 * @derek
 * @1.0.0
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        String encrypt = "";
        for(int i = 0; i < input.length(); i++){
            char letter = input.charAt(i);
            boolean upper = (letter == Character.toUpperCase(letter)) ? true : false;
            if(Character.isLetter(letter)){
                if(upper)
                    encrypt += shiftedAlphabet.charAt(alphabet.indexOf(Character.toUpperCase(letter)));
                else
                    encrypt += Character.toLowerCase(shiftedAlphabet.charAt(alphabet.indexOf(Character.toUpperCase(letter))));
            }
            else{
                if(upper)
                    encrypt += letter;
                else
                    encrypt += Character.toLowerCase(letter);
            }
        }
        return encrypt;
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
