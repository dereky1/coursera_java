
/**
 * Write a description of Part2 here.
 * 
 * @derek 
 * @1.0.0
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int index = 0;
        int siz = stringa.length();
        while(true){
            index = stringb.indexOf(stringa, index) + siz;
            if(index == siz-1)
                break;
            count += 1;
        }
        return count;
    }
    
    public void testHowMany(){
        String one = "ATATATATATATATAT";
        String two = "GATTTATTAAATTGAT";
        String three = "FURRYFURRYFUURRRY";
        
        System.out.println(one + ":\n" + howMany("AT",one));
        System.out.println(two + ":\n" + howMany("GAT",two));
        System.out.println(two + ":\n" + howMany("TA",two));
        System.out.println(three + ":\n" + howMany("RR",three));
    }
}
