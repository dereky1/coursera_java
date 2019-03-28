
/**
 * Write a description of Part3 here.
 * 
 * @derek
 * @1.0.0
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        int count = (index == -1) ? 0 : 1; 
        while (index != -1){
            index = stringb.indexOf(stringa,index+stringa.length());
            if(index != -1)
                count += 1;
        }
        return count > 1;
    }
    
    public void testing() {
        String one = "banana";
        String two = "Avril Lavgine Avril Lavigne";
        String three = "pei ni chang wo men ai de ge";
        String four = "wo men yi qi xue mao jiao";
        System.out.println("Testing " + one + " with 'a': " + twoOccurrences("a", one));
        System.out.println("Testing " + two + " with 'Avril': " + twoOccurrences("Avril", two));
        System.out.println("Testing " + three + " with 'wo': " + twoOccurrences("wo", three));
        System.out.println("Testing " + four + " with 'mao': " + twoOccurrences("mao", four));
        System.out.println("The part after a in '" + one + "' is :" + lastPart("a",one));
        System.out.println("The part after Avril in '" + two + "' is :" + lastPart("Avril",two));
        System.out.println("The part after wo in '" + three + "' is :" + lastPart("wo",three));
        System.out.println("The part after mao in '" + four + "' is :" + lastPart("mao",four));
    }
    
    public String lastPart(String  stringa, String stringb){
        return (stringb.indexOf(stringa) == -1) ? stringb : stringb.substring(stringb.indexOf(stringa)+stringa.length());
    }
}
