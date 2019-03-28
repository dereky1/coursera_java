
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @derek
 * @1.0.0
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        String encrypted = "";
        for(int i = 0; i < input.length(); i++){
            char letter = input.charAt(i);
            boolean upper = (letter == Character.toUpperCase(letter)) ? true : false;
            if(i % 2 == 0){
                if(Character.isLetter(letter)){
                if(upper)
                    encrypted += shiftedAlphabet1.charAt(alphabet.indexOf(Character.toUpperCase(letter)));
                else
                    encrypted += Character.toLowerCase(shiftedAlphabet1.charAt(alphabet.indexOf(Character.toUpperCase(letter))));
                }
                else{
                    if(upper)
                        encrypted += letter;
                    else
                        encrypted += Character.toLowerCase(letter);
                }
            }
            else{
                if(Character.isLetter(letter)){
                if(upper)
                    encrypted += shiftedAlphabet2.charAt(alphabet.indexOf(Character.toUpperCase(letter)));
                else
                    encrypted += Character.toLowerCase(shiftedAlphabet2.charAt(alphabet.indexOf(Character.toUpperCase(letter))));
                }
                else{
                    if(upper)
                        encrypted += letter;
                    else
                        encrypted += Character.toLowerCase(letter);
                }
            }
        }
        return encrypted;
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc2.encrypt(input);
    }
}
