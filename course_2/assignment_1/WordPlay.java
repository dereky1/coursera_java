
/**
 * Write a description of WordPlay here.
 * 
 * @derek
 * @1.0.0
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiouAEIOU";
        return (vowels.indexOf(ch) == -1) ? false : true;
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder tempPhrase = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            if(isVowel(tempPhrase.charAt(i)))
                tempPhrase.setCharAt(i,ch);
        }
        return tempPhrase.toString();
    }
    public String emphasize(String phrase, char ch){
        StringBuilder tempPhrase = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            if(tempPhrase.charAt(i) == Character.toLowerCase(ch) || tempPhrase.charAt(i) == Character.toUpperCase(ch))
                tempPhrase.setCharAt(i,(i % 2==0) ? '*' : '+');
        }
        return tempPhrase.toString();
    }
}
